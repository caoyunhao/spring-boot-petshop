package com.caoyunhao.petshop.common;

import com.caoyunhao.petshop.PetshopInternApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jie.yao
 * @date 2017/9/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PetshopInternApplication.class)
public class IndexControllerTest {
    MockMvc mvc;

    @Autowired
    private IndexController indexController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    /**
     * 测试index接口
     *
     * @throws Exception
     */
    @Test
    public void testIndex() throws Exception {
        String uri = "/";
        mvc.perform(get(uri).accept(
                MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errorCode").value(0));
    }

    /**
     * 测试404错误
     *
     * @throws Exception
     */
    @Test
    public void test404Error() throws Exception {
        String uri = "/notValid";
        mvc.perform(get(uri).accept(
                MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
