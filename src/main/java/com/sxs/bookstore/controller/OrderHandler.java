package com.sxs.bookstore.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sxs.bookstore.alipay.config.AlipayConfig;
import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IOrderManageService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/2/002
 */
@Controller
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private IOrderManageService service;

    // 根据用户查找订单
    @RequestMapping("/findOrderByUser.do")
    public String findOrderByUser(@RequestParam(defaultValue = "1") int pageIndex, HttpServletRequest request){
        User login_user = (User) request.getSession().getAttribute("login_user");
        PageModel orderModel = service.findOrderByUser(pageIndex,login_user);
        request.setAttribute("orderModel",orderModel);
        return "/client/orderlist.jsp";
    }

    // 根据ID查找订单
    @RequestMapping("/findOrderById.do")
    public String findOrderById(String id,HttpServletRequest request,String type){
        Order order = service.findOrderById(id);
        request.setAttribute("order",order);
        if (type != null){
            return "/admin/orders/view.jsp";
        }else {
            return "/client/orderInfo.jsp";
        }
    }

    // 删除订单
    @RequestMapping("/removeOrderById.do")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String removeOrderById(String id){
        int rows3 = 0;
        List<OrderItem> list = service.findOrderItemById(id);
        // for (int i=0;i<list.size();i++){
        //     OrderItem item = (OrderItem) list.get(i);
        //     rows3 = service.updateProductsPnum(item);
        // }
        int rows = service.removeOrderById(id);
        int rows2 = service.removeOrderItemById(id);
        for (OrderItem item:list){
            rows3 = service.updateProductsPnum(item);
        }
            if (rows > 0 && rows2 > 0 && rows3 > 0){
                // if (type != null){
                //     return "redirect:/orderManage/findAllOrders.do";
                // }
                // return "/client/delOrderSuccess.jsp";
                return "OK";
            }
            return "FAIL";
    }

    // 支付订单
    @RequestMapping("/pay.do")
    public void payOrder(String id,String money,HttpServletResponse response) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(id.getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(money.getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(id.getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String("商品".getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        response.setContentType("text/html,charset=utf-8");
        //输出
        response.getWriter().println(result);

    }

    // 支付成功处理
    @RequestMapping("/paySuccess.do")
    public String paySuccess(HttpServletRequest request) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 修改支付状态
            service.modifyPayStatus(out_trade_no);

            return "/client/paysuccess.jsp";
        }else {
            // out.println("验签失败");
            return "/client/error/payFail.jsp";
        }
    }
}
