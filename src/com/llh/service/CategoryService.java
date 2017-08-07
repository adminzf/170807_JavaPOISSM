package com.llh.service;

import java.io.IOException;
import java.util.List;

import com.llh.pojo.Category;
import com.llh.vo.VoCategory;
import org.apache.poi.hssf.usermodel.HSSFCell;

public interface CategoryService {

    /*public  List<VoCategory> getList();*/
    public List<Category> javaPoi()throws IOException;
    public List<Category> readXls()throws  IOException;
    public String getValue(HSSFCell hssfCell);
}