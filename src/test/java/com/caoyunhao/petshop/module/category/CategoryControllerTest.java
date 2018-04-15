package com.caoyunhao.petshop.module.category;

import com.caoyunhao.petshop.PetshopInternApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 
* CategoryController Tester. 
* 
* @author Li.Hou
* @date 10/16/2017
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PetshopInternApplication.class)
@Transactional
public class CategoryControllerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    MockMvc mvc;

    @Autowired
    CategoryController categoryController;
    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    } 

    @After
    public void after() throws Exception { 
    }

    /** 
    * 
    * Method: findAllCategories(PageParams pageParams) 
    * 
    */ 
    @Test
    public void testFindAllCategories() throws Exception {
        String url = "/categories";
        mvc.perform(get(url)
                .param("pageNum","1")
                .param("pageSize","2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

//    /**
//    *
//    * Method: findCategoryById(@PathVariable Long id)
//    *
//    */
//    @Test
//    public void testFindCategoryById() throws Exception {
//        String url = "/categories/20";
//        mvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0))
//                .andExpect(jsonPath("$.data").isNotEmpty());
//    }

//    /**
//    *
//    * Method: searchAllByCateName(@PathVariable(name = "cateName") String cateName, PageParams pageParams)
//    *
//    */
//    @Test
//    public void testSearchAllByCateName() throws Exception {
//        String url = "/categories/search/ca";
//        mvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0))
//                .andExpect(jsonPath("$.data").isNotEmpty());
//    }

//    /**
//    *
//    * Method: findCommoditiesByCategoryId(@PathVariable(name = "categoryId") long categoryId)
//    *
//    */
//    @Test
//    public void testFindCommoditiesByCategoryId() throws Exception {
//        String url = "/categories/showAllCommodities/20";
//        mvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0))
//                .andExpect(jsonPath("$.data").isNotEmpty());
//    }
} 
