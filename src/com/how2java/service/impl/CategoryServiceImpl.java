package com.how2java.service.impl;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.pojo.CategoryExample;
import com.how2java.service.CategoryService;
import com.how2java.vo.VoCategory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<VoCategory> getList() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categoryList= categoryMapper.selectByExample(categoryExample);
           List<VoCategory> voCategoryList = new ArrayList<>();
           for(Category category:
                   categoryList){
               VoCategory voCategory = new VoCategory();
               voCategory.setId(category.getId());
               voCategory.setName(category.getName());
               voCategory.setPassword(category.getPassword());
               voCategory.setScore(category.getScore());
               voCategoryList.add(voCategory);
           }
           return voCategoryList;
    }

    @Override
    public List<Category> javaPoi()throws  IOException{
        List<Category> list = readXls();
        System.out.print(list);
        for(int i=0;i < list.size();i++){
            Category category = new Category();
            category = list.get(i);
          categoryMapper.insertSelective(category);
    }
      return list;
    }
     @Override
        public List<Category> readXls() throws IOException {
        InputStream is = new FileInputStream("E:\\IDEA\\0706_SSM\\lib\\category.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Category category = null;
        List<Category> list = new ArrayList<>();

        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                   category = new Category();
                    HSSFCell id = hssfRow.getCell(0);
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell password = hssfRow.getCell(2);
                    HSSFCell score = hssfRow.getCell(3);
                    category.setId(Integer.valueOf( getValue(id)));
                    category.setName(getValue(name));
                    category.setPassword(getValue(password));
                    category.setScore(getValue(score));
                    list.add(category);
                }
            }
        }
        return list;
    }
    @Override
    public String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {

            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {

            return String.valueOf(hssfCell.getNumericCellValue());
        } else {

            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}

