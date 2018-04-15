package com.caoyunhao.petshop.module.login;

import com.caoyunhao.petshop.PetshopInternApplication;
import com.caoyunhao.petshop.common.util.EncryptionUtil;
import com.caoyunhao.petshop.entity.VerifyRecord;
import com.caoyunhao.petshop.repository.VerifyRecordRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

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
public class LoginControllerTest {
    @Rule
    public ExpectedException throwException = ExpectedException.none();
    MockMvc mvc;
    @Value("${anonymous-verify-code-overdue}")
    int VERIFY_CODE_OVER_DUE;
    @Autowired
    private LoginController loginController;
    @Autowired
    private VerifyRecordRepository verifyRecordRepository;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    /**
     * 测试登录
     */
    @Test
    public void testLogin() throws Exception {
        //设置验证码记录以测试
        VerifyRecord verifyRecord = new VerifyRecord();
        String token = "test_token_ksdjvwqiuoofjcsidjvnwiornvlkjavnslksajfdnvoiwqruhvn";
        String verifyCode = "1234";
        verifyRecord.setVerifyToken(token);
        verifyRecord.setVerifyCode(verifyCode);
        verifyRecord.setOverdueTime(new Timestamp(System.currentTimeMillis() + VERIFY_CODE_OVER_DUE));
        verifyRecordRepository.save(verifyRecord);

        //设置request
        JSONObject requestJson = new JSONObject();
        JSONObject loginJson = new JSONObject();
        System.out.println(EncryptionUtil.encryptPassword(EncryptionUtil.encryptFrontString("test")));

        String encryptedUserName = EncryptionUtil.encryptFrontString("test");
        String encryptedPassword = EncryptionUtil.encryptFrontString("test");

        System.out.println("username = " + encryptedUserName);
        System.out.println("password = " + encryptedPassword);

        //loginJson.put("userName", "99aa7342ba09523f6b3e7ccdbea93fe3b18adeb9");
        loginJson.put("userName", encryptedUserName);
        //loginJson.put("password", "99aa7342ba09523f6b3e7ccdbea93fe3b18adeb9");
        loginJson.put("password", encryptedPassword);
        loginJson.put("verifyCode", verifyCode);
        loginJson.put("verifyToken", token);
        requestJson.put("data", loginJson);
        String uri = "/login";

        //执行mvc请求
        mvc.perform(post(uri)
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * 测试登录失败
     */
    @Test
    public void testLoginFail() throws Exception {
        //设置验证码记录以测试
        VerifyRecord verifyRecord = new VerifyRecord();
        String token = "test_token_ksdjvwqiuoofjcsidjvnwiornvlkjavnslksajfdnvoiwqruhvn";
        String verifyCode = "1234";
        verifyRecord.setVerifyToken(token);
        verifyRecord.setVerifyCode(verifyCode);
        verifyRecord.setOverdueTime(new Timestamp(System.currentTimeMillis() + VERIFY_CODE_OVER_DUE));
        verifyRecordRepository.save(verifyRecord);

        //设置request
        JSONObject requestJson = new JSONObject();
        JSONObject loginJson = new JSONObject();
        System.out.println(EncryptionUtil.encryptPassword(EncryptionUtil.encryptFrontString("test")));

        String encryptedUserName = EncryptionUtil.encryptFrontString("test");
        String encryptedPassword = EncryptionUtil.encryptFrontString("");

        System.out.println("username = " + encryptedUserName);
        System.out.println("password = " + encryptedPassword);

        loginJson.put("userName", encryptedUserName);
        loginJson.put("password", encryptedPassword);
        loginJson.put("verifyCode", verifyCode);
        loginJson.put("verifyToken", token);
        requestJson.put("data", loginJson);
        String uri = "/login";

        //throwException.expect(WebBackendException.class);
        //执行mvc请求
        mvc.perform(post(uri)
                .content(requestJson.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        //                .andExpect(status().isBadRequest());
        //                .andExpect((jsonPath("$.errorCode").value(10103)));
    }

    @Test
    public void logout() throws Exception {
        String uri = "/logout";
        mvc.perform(get(uri))
                .andExpect(jsonPath("$.errorCode").value(0))
                .andDo(print());
    }
}
