package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.service.IProductInfoService;
import com.sxs.bookstore.service.IProductManaService;
import com.sxs.bookstore.utils.IdUtils;
import com.sxs.bookstore.utils.PageModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/11/011
 */
@Controller
@RequestMapping("/productManage")
public class ProductManaHandler {

    @Autowired
    private IProductManaService service;

    @Autowired
    private IProductInfoService pInfoservice;

    @RequestMapping("/findProductByManyCondition.do")
    public String findAllProducts(Product product,String minPrice,String maxPrice,Model model,@RequestParam(defaultValue = "1") int pageIndex){
        List cList = service.findAllCategories();
        model.addAttribute("cList",cList);
        model.addAttribute("product",product);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        Map map = new HashMap();
        map.put("product",product);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("pageIndex",pageIndex);
        PageModel productModel = service.findAllProducts(map);
        model.addAttribute("productModel",productModel);
        return "/admin/products/list.jsp";
    }

    // 添加商品信息
    @RequestMapping("/addProduct.do")
    @ResponseBody
    public String addProduct(Product product,@RequestParam("upload") MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            String filename = file.getOriginalFilename();
            String url = IdUtils.getUUID()+filename;
            String path = "F:/MyProjects/bookStore/src/main/webapp/temp";
            product.setImgurl("/temp/"+url);
            File file1 = new File(path,url);
            if (!new File(path).exists()){
                new File(path).mkdirs();
            }
            file.transferTo(file1);
            int rows = service.addProduct(product);
            if (rows > 0){
                return "添加成功，<a href='/bookstore/productManage/findProductByManyCondition.do'>点击查看</a>";
            }else {
                return "添加失败，<a href='#' onclick='history.go(-1)'>重新添加</a>";
            }
        }
        return "请选择上传图片，<a href='#' onclick='history.go(-1)'>重新添加</a>";
    }


    // 删除商品信息
    @RequestMapping("/deteleProduct.do")
    @ResponseBody
    public String deteleProduct(String id){
        int rows = service.removeProduct(id);
        if (rows > 0){
            return "OK";
        }else {
            return "FAIL";
        }
    }

    //修改商品信息
    @RequestMapping("/modifyProduct.do")
    @ResponseBody
    public String modifyProduct(Product product,@RequestParam("upload") MultipartFile mfile,Model model) throws IOException {
        if (!mfile.isEmpty()){
            Product p =pInfoservice.findProductById(product.getId());
            String imgurl = p.getImgurl();
            String path = "F:/MyProjects/bookStore/src/main/webapp";
            File targetfile = new File(path,imgurl);
            if (targetfile.exists()){
                targetfile.delete();
            }
            String filename = mfile.getOriginalFilename();
            String url = IdUtils.getUUID()+filename;
            product.setImgurl("/temp/"+url);
            File file = new File(path+"/temp",url);
            if (!new File("F:/MyProjects/bookStore/src/main/webapp/temp").exists()){
                new File("F:/MyProjects/bookStore/src/main/webapp/temp").mkdirs();
            }
            mfile.transferTo(file);
            int rows = service.modifyProduct(product);
            if (rows > 0){
                return "修改成功，<a href='/bookstore/productManage/findProductByManyCondition.do'>点击查看</a>";
            }else {
                return "修改失败，<a href='#' onclick='history.go(-1)'>重新编辑</a>";
            }
        }
        return "请选择上传图片，<a href='#' onclick='history.go(-1)'>返回</a>";
    }

    //此功能提供下载最新的销售榜单
    @RequestMapping("/download.do")
    public void download(int year, int month, HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Product> list = service.findProductSalList(year,month);

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet("sheet1");
        //创建首行
        HSSFRow headerRow = sheet.createRow(0);

        //创建两列
        headerRow.createCell(0).setCellValue("名称");
        headerRow.createCell(1).setCellValue("销售量");

        //遍历List集合，动态添加数据
        for (Product product:list){
            //每遍历一次，在末尾动态的添加一行
            HSSFRow data = sheet.createRow(sheet.getLastRowNum()+1);
            data.createCell(0).setCellValue(product.getName());
            data.createCell(1).setCellValue(product.getSalnum());
        }

        //添加完成，使用输出流进行下载
        OutputStream out = response.getOutputStream();
        String filename = System.currentTimeMillis()+"-sxs.xls";
        String contentType = request.getServletContext().getMimeType(filename);
        // 设置文件类型
        response.setContentType(contentType);
        //设置响应头，指定下载文件名
        response.setHeader("content-disposition","attachment;filename="+filename);

        //使用workbook的write方法下载
        hssfWorkbook.write(out);
    }
}
