package com.caoyunhao.petshop.module.custom;

import com.caoyunhao.petshop.PetshopInternApplication;
import org.json.JSONObject;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PetshopInternApplication.class)
@Transactional
public class CustomControllerTest {

    @Rule
    public ExpectedException throwException = ExpectedException.none();
    MockMvc mvc;
    @Autowired
    private CustomController customController;

    private final String ROOT_URL = "/customs";

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(customController).build();
    }

    @Test
    public void findCustom() throws Exception {
        JSONObject requestJson = new JSONObject();
        String uri = ROOT_URL + "/1";

        //执行mvc请求
        mvc.perform(get(uri).content(requestJson.toString()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.errorCode").value(0)).andExpect(jsonPath("$.data").isNotEmpty());
    }

//    @Test
//    public void updateCustom() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        jsonArray.put(21);
//        jsonArray.put(24);
//
//        jsonObject.put("customNickname", "test_nickname");
//        jsonObject.put("customPhoneNumber", "18012345678");
//        jsonObject.put("customEmail", "test_add@admin.com");
//        jsonObject.put("roleIdList", jsonArray);
//
//        JSONObject requestJson = new JSONObject();
//        requestJson.put("data", jsonObject);
//
//        String uri = ROOT_URL + "/2";
//
//        //执行mvc请求
//        mvc.perform(put(uri).content(requestJson.toString()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.errorCode").value(0));
//
//    }

//    @Test
//    public void getWalletBalance() throws Exception {
//        String uri = ROOT_URL + "/wallet/balance/2";
//
//        //执行mvc请求
//        mvc.perform(get(uri)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.errorCode").value(0));
//    }

    @Test
    public void recharge() throws Exception {
        String uri = ROOT_URL + "/recharge/2";

        JSONObject jsonObject = new JSONObject();
        JSONObject requestObject = new JSONObject();

        jsonObject.put("rechargeNumber", 500);
        requestObject.put("data", jsonObject);

        //执行mvc请求
        mvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestObject.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));
    }
}
