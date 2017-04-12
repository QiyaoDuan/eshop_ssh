package com.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.model.Category;
import com.service.ICategoryService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:beans.xml")  
public class CategoryServiceImplTest {  
  
    @Resource  
    private ICategoryService categoryService;  
      
    @Test  
    public void testQueryJoinAccount() {  
    	for(Category c : categoryService.queryJoinAccount("",1,2)) { //显示第一页，每页2条数据   
            System.out.println(c);  
            System.out.println(c.getAccount());  
       }  
   }  
}  