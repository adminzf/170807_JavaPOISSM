package com.how2java.service;

import java.io.IOException;
import java.util.List;

import com.how2java.pojo.Category;
import com.how2java.pojo.CategoryExample;
import com.how2java.vo.VoCategory;
import org.apache.poi.hssf.usermodel.HSSFCell;

public interface CategoryService {

    public  List<VoCategory> getList();
    public List<Category> javaPoi()throws IOException;
    public List<Category> readXls()throws  IOException;
    public String getValue(HSSFCell hssfCell);
}