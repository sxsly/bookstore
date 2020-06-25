package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IProductInfoService;
import com.sxs.bookstore.utils.IdUtils;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductInfoHandler {
    @Autowired
    private IProductInfoService service;

    @RequestMapping("/findProductInfo.do")
    public String findProductInfo(@RequestParam(defaultValue = "1") int pageIndex, String category, Model model){
        model.addAttribute("category",category);
        PageModel productsModel = service.findProductsInfo(category,pageIndex);
        model.addAttribute("productsModel",productsModel);
        return "/client/product_list.jsp";
    }

    // 按书名进行查找
    @RequestMapping("/findBooksByName.do")
    public String findBooksByName(@RequestParam(defaultValue = "1") int pageIndex, String bookname, Model model){
         model.addAttribute("bookname",bookname);
         PageModel booksModel = service.findBooksByName(bookname,pageIndex);
         model.addAttribute("booksModel",booksModel);
         return "/client/product_search_list.jsp";
    }

    // 根据id查找对应的书籍
    @RequestMapping("/findProductById.do")
    public String findProductById(String id,Model model,String type){
        Product product = service.findProductById(id);
        model.addAttribute("product",product);
        if (type != null){
            return "/admin/products/edit.jsp";
        }else {
            return "/client/info.jsp";
        }
    }

    // 添加商品到购物车
    @RequestMapping("/addCart.do")
    public String addCart(String id,HttpSession session){
     Product product = service.findProductById(id);
     // 从session中获取购物车对象
     Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
     // 如果购物车为null，则创建购物车
     if (cart == null){
         cart = new HashMap<Product,Integer>();
     }
     // 如果count不为空，则说明商品在购物车中存在
     Integer count = cart.get(product);
     if (count != null){
         cart.put(product,count+1);
     }else {
         cart.put(product,1);
     }
     session.setAttribute("cart",cart);
     return "/client/cart.jsp";
    }

    // 改变购物车中商品的数量
    @RequestMapping("/changeCart.do")
    public String changeCart(String id, Integer count, HttpSession session){
        //根据id来查找商品信息
        Product product = service.findProductById(id);
        //获取session中的购物车
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if (count == 0){
            cart.remove(product);
        }else {
            cart.put(product,count);
        }
        return "/client/cart.jsp";
    }

    // 提交订单
    @RequestMapping("/createOrder.do")
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(Order order,HttpSession session,Model model){
        User login_user = (User) session.getAttribute("login_user");
        order.setId(IdUtils.getUUID());
        order.setUser(login_user);
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        service.createOrder(order,cart);
        session.removeAttribute("cart");
        model.addAttribute("order",order);
        return "/client/createOrderSuccess.jsp";
    }

}
