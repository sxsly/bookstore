package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.dao.IProductInfoDao;
import com.sxs.bookstore.dao.IProductManaDao;
import com.sxs.bookstore.service.IProductManaService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/11/011
 */
@Service
public class IProductManaServiceImpl implements IProductManaService {

    @Autowired
    private IProductManaDao dao;

    @Autowired
    private IProductInfoDao pInfodao;

    @Override
    public PageModel findAllProducts(Map map) {
        int rows = dao.findTotalRows(map);
        PageModel pageModel = new PageModel();
        pageModel.setRecordCount(rows);
        pageModel.setPageIndex((Integer) map.get("pageIndex"));
        pageModel.setPageSize(4);
        map.put("firstLimitParam",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        List list = dao.findAllProducts(map);
        pageModel.setData(list);
        return pageModel;
    }

    @Override
    public List findAllCategories() {
        List list = dao.findAllCategories();
        return list;
    }

    @Override
    public int addProduct(Product product) {
        int rows = dao.addProduct(product);
        return rows;
    }

    @Override
    public int removeProduct(String id) {
        Product p = pInfodao.findProductById(id);
        String imgurl = p.getImgurl();
        String path = "F:/MyProjects/bookStore/src/main/webapp"+imgurl;
        File targetfile = new File(path);
        if (targetfile.exists()){
            targetfile.delete();
        }
        int rows = dao.removeProduct(id);
        return rows;
    }

    @Override
    public int modifyProduct(Product product) {
        int rows = dao.modifyProduct(product);
        return rows;
    }

    @Override
    public List findProductSalList(int year, int month) {
        List list = dao.findProductSalList(year,month);
        return list;
    }
}
