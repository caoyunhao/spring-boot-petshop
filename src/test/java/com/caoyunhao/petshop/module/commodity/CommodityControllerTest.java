package com.caoyunhao.petshop.module.commodity;

import com.caoyunhao.petshop.PetshopInternApplication;
import com.caoyunhao.petshop.repository.CommodityRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yunhao.Cao
 * @date 2017/9/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PetshopInternApplication.class)
@Transactional
public class CommodityControllerTest {

    @Rule
    public ExpectedException throwException = ExpectedException.none();
    MockMvc mvc;
    @Autowired
    private CommodityController commodityController;
    @Autowired
    private CommodityRepository commodityRepository;

    private final String COMMODITIES_ROOT = "/commodities";

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(commodityController).build();
    }

    @Test
    public void getCommodities() throws Exception {
        String uri = COMMODITIES_ROOT;

        //执行mvc请求
        mvc.perform(get(uri)
                .param("pageNum", "1")
                .param("pageSize", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    public void findCommodity() throws Exception {
        JSONObject requestJson = new JSONObject();
        String uri = COMMODITIES_ROOT + "/60";

        //执行mvc请求
        mvc.perform(get(uri)
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

//    @Test
//    public void addCommodity() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        jsonArray.put(19);
//        jsonArray.put(20);
//
//        jsonObject.put("commodityName", "test_add");
//        jsonObject.put("commodityPrice", 12.34);
//        jsonObject.put("commodityDescription", "test_add");
//        jsonObject.put("commodityCategoryIdList", jsonArray);
//
//        JSONObject requestJson = new JSONObject();
//        requestJson.put("data", jsonObject);
//
//        String uri = COMMODITIES_ROOT;
//
//        //执行mvc请求
//        mvc.perform(post(uri)
//                .content(requestJson.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0));
//
//    }

//    @Test
//    public void updateCommodity() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        jsonArray.put(19);
//        jsonArray.put(20);
//
//        jsonObject.put("commodityName", "test_add");
//        jsonObject.put("commodityPrice", 12.34);
//        jsonObject.put("commodityDescription", "test_add");
//        jsonObject.put("commodityCategoryIdList", jsonArray);
//
//        JSONObject requestJson = new JSONObject();
//        requestJson.put("data", jsonObject);
//
//        String uri = COMMODITIES_ROOT + "/60";
//
//        //执行mvc请求
//        mvc.perform(put(uri)
//                .content(requestJson.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0));
//
//    }

//    @Test
//    public void deleteCommodity() throws Exception {
//        String uri = COMMODITIES_ROOT + "/60";
//
//        //执行mvc请求
//        mvc.perform(delete(uri)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.errorCode").value(0));
//    }

    @Test
    public void searchCommodity() throws Exception {
        String uri = COMMODITIES_ROOT + "/search";

        //执行mvc请求
        mvc.perform(get(uri)
                .param("q", "11")
                .param("categoryId", "20")
                .param("pageNum", "1")
                .param("pageSize", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));
    }

}
