package com.caoyunhao.petshop.module.purchase_record;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class PurchaseRecordControllerTest {
    @Rule
    public ExpectedException throwException = ExpectedException.none();
    MockMvc mvc;
    @Autowired
    private PurchaseRecordController purchaseRecordController;

    private final String PURCHASERECORDS_ROOT = "/purchase_records";

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(purchaseRecordController).build();
    }

    @Test
    public void findPurchaseRecords() throws Exception {
        String uri = "/purchase_records/1";

        //执行mvc请求
        mvc.perform(get(uri)
                .param("pageNum", "1")
                .param("pageSize", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    public void findPurchaseRecord() throws Exception {
        String uri = "/purchase_records/1/20";

        //执行mvc请求
        mvc.perform(get(uri)
                .param("pageNum", "1")
                .param("pageSize", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    public void pay() throws Exception {
        String uri = "/purchase_records/pay/1";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commodityId","90");
        jsonObject.put("purchaseQuantity","1");

        JSONObject requestJson = new JSONObject();
        requestJson.put("data",jsonObject);

        //执行mvc请求
        mvc.perform(post(uri)
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));
    }

    @Test
    public void findPurchaseRecordByTimePeriod() throws Exception {
        String uri = "/purchase_records/time/1";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start","2017-10-20 16:36:43");
        jsonObject.put("end","2017-10-20 16:59:25");
        JSONObject requestJson = new JSONObject();
        requestJson.put("data",jsonObject);

        //执行mvc请求
        mvc.perform(post(uri)
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));
    }
}
