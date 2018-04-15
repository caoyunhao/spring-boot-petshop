package com.caoyunhao.petshop.module.login;

import com.caoyunhao.petshop.common.BaseService;
import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.*;
import com.caoyunhao.petshop.entity.*;
import com.caoyunhao.petshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Transactional
@Service
public class LoginService extends BaseService {

    @Autowired
    CustomRepository customRepository;

    @Autowired
    CustomLoginRecordRepository customLoginRecordRepository;

    @Autowired
    VerifyRecordRepository verifyRecordRepository;

    @Autowired
    CustomTokenRepository customTokenRepository;

    @Autowired
    CustomNonceRepository customNonceRepository;

    @Value("${token-overdue}")
    private Long CUSTOM_TOKEN_TIME_DUE;

    @Value("${nonce-due-seconds}")
    private Long CUSTOM_NONCE_TIME_DUE;

    public LoginData login(LoginForm loginForm, HttpSession httpSession, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {
        String verifyCode = loginForm.getVerifyCode();
        String verifyToken = loginForm.getVerifyToken();

        if (!validVerifyCode(verifyCode, verifyToken)) {
            throw new WebBackendException(ErrorCode.VERIFY_CODE_NOT_VALID);
        }

        System.out.println("customName = " + loginForm.getCustomName());
        System.out.println("password = " + EncryptionUtil.encryptPassword(loginForm.getPassword()));

        Custom custom = validCustomNameAndPassword(loginForm.getCustomName(), loginForm.getPassword());

        if (null == custom) {
            throw new WebBackendException(ErrorCode.USER_PASSWORD_NOT_VALID);
        }

        addLoginRecord(custom, httpServletRequest);

        return generateLoginData(custom, httpSession, httpServletRequest, httpServletResponse);
    }

    public void refreshNonce(Long customId, HttpSession httpSession, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        CustomNonce customNonce = updateCustomNonce(customId, httpServletRequest.getRequestURI());

        String newNonce = customNonce.getCustomNonce();

        SignUtil.writeAuthorization(httpServletResponse, newNonce);
    }

    /**
     * 生成LoginData，重置Token和Nonce
     */
    public LoginData generateLoginData(Custom custom, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        CustomToken customToken = updateCustomToken(custom.getId(), httpSession);
        CustomNonce customNonce = updateCustomNonce(custom.getId(), httpServletRequest.getRequestURI());

        String newNonce = customNonce.getCustomNonce();

        SignUtil.writeAuthorization(httpServletResponse, newNonce);

        LoginData loginData = new LoginData();
        loginData.setCustomName(EncryptionUtil.encryptAesBase64(custom.getCustomName(), newNonce));
        loginData.setCustomId(EncryptionUtil.encryptAesBase64(String.valueOf(custom.getId()), newNonce));
        loginData.setCustomToken(EncryptionUtil.encryptAesBase64(customToken.getCustomToken(), newNonce));
        return loginData;
    }

    /**
     * 验证顾客名密码
     */
    private Custom validCustomNameAndPassword(String customName, String password)
            throws WebBackendException {
        Optional<Custom> optional = customRepository.findByCustomNameEncryptedAndPassword(customName, EncryptionUtil.encryptPassword(password));
        if (optional.isPresent())
            return optional.get();
        else
            throw new WebBackendException(ErrorCode.USER_PASSWORD_NOT_VALID);
    }

    /**
     * 验证验证码,包括验证过期时间
     */
    private boolean validVerifyCode(String verifyCode, String verifyToken) {
        Optional<VerifyRecord> optional = verifyRecordRepository.findByVerifyCodeAndVerifyToken(verifyCode, verifyToken);

        return optional.isPresent() && !DatetimeUtil.getCurrentTimestamp().after(optional.get().getOverdueTime());
    }

    /**
     * 写入登录日志
     */
    private void addLoginRecord(Custom custom, HttpServletRequest httpServletRequest) {
        CustomLoginRecord customLoginRecord = new CustomLoginRecord();

        customLoginRecord.setId(0);
        customLoginRecord.setCustomId(custom.getId());
        customLoginRecord.setIp(CommonUtil.getRemoteIP(httpServletRequest));
        customLoginRecord.setLoginTime(DatetimeUtil.getCurrentTimestamp());

        customLoginRecordRepository.save(customLoginRecord);
    }

    /**
     * 更新顾客Token, 包括时间
     */
    public CustomToken updateCustomToken(Long customId, HttpSession session) {
        CustomToken customToken = new CustomToken();

        customToken.setCustomToken(getNewCustomToken(session));
        customToken.setCustomId(customId);
        customToken.setOverdueTime(new Timestamp(System.currentTimeMillis() + CUSTOM_TOKEN_TIME_DUE));

        return customTokenRepository.save(customToken);
    }

    /**
     * 更新顾客Nonce, 包括时间
     */
    public CustomNonce updateCustomNonce(Long customId, String requestPath) {
        CustomNonce customNonce = new CustomNonce();

        customNonce.setCustomId(customId);
        customNonce.setCustomNonce(SignUtil.generateNonce(requestPath, customId));
        customNonce.setOverdueTime(new Timestamp(System.currentTimeMillis() + CUSTOM_NONCE_TIME_DUE));

        return customNonceRepository.save(customNonce);
    }

    /**
     * 更新token过期时间
     */
    public void updateTokenOverdue(CustomToken customToken) {
        customToken.setOverdueTime(new Timestamp(System.currentTimeMillis() + CUSTOM_TOKEN_TIME_DUE));
        customTokenRepository.save(customToken);
    }

    /**
     * 获取新的Token
     */
    private String getNewCustomToken(HttpSession session) {
        return EncryptionUtil.getMd5(session.getId() + DatetimeUtil.getCurrentTimestamp().toString());
    }

    public CustomToken findCustomTokenByCustomId(Long customId)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> customTokenRepository.findByCustomId(id_), customId);
    }

    public CustomNonce findCustomNonceByCustomId(Long customId)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> customNonceRepository.findByCustomId(id_), customId);
    }


}
