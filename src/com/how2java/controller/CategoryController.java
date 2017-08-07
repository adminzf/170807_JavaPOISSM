package com.how2java.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.how2java.vo.VoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;

// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

   /* @RequestMapping("/listCategory")
    public ModelAndView listCategory() {
        ModelAndView mav = new ModelAndView();
        List<VoCategory> cs = categoryService.getList();
        mav.addObject("cs", cs);
        mav.setViewName("/WEB-INF/jsp/listCategory.jsp");
        return mav;
    }*/

    @RequestMapping("/javapoi")
    public String add() throws IOException {

            List<Category> list = categoryService.javaPoi();
            /*Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/

            return "/success.jsp";
        }

    }
