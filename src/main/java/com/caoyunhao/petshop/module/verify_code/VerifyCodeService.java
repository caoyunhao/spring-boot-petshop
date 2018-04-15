package com.caoyunhao.petshop.module.verify_code;

import com.caoyunhao.petshop.common.BaseService;
import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.DatetimeUtil;
import com.caoyunhao.petshop.entity.VerifyRecord;
import com.caoyunhao.petshop.repository.VerifyRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/4
 */
@Service
@Transactional
public class VerifyCodeService extends BaseService {

    @Autowired
    VerifyRecordRepository verifyRecordRepository;

    @Value("${anonymous-verify-code-overdue}")
    int VERIFY_CODE_OVER_DUE;

    public VerifyCodeResponseData requestVerifyCode() throws WebBackendException {
        VerifyCode verifyCode;
        VerifyCodeFactory verifyCodeFactory = new VerifyCodeFactory();
        if (!((verifyCode = verifyCodeFactory.creator()).isValid())) {
            throw new WebBackendException(ErrorCode.VERIFY_CODE_GET_FAILURE);
        }

        addVerifyRecord(verifyCode);

        VerifyCodeResponseData verifyCodeResponseData;
        BeanUtils.copyProperties(verifyCode, verifyCodeResponseData = new VerifyCodeResponseData());

        return verifyCodeResponseData;
    }

    private void addVerifyRecord(VerifyCode verifyCode) {
        VerifyRecord verifyRecord = new VerifyRecord();

        verifyRecord.setVerifyToken(verifyCode.getToken());
        verifyRecord.setVerifyCode(verifyCode.getText());
        verifyRecord.setOverdueTime(new Timestamp(System.currentTimeMillis() + VERIFY_CODE_OVER_DUE));

        verifyRecordRepository.save(verifyRecord);
    }

    public boolean checkVerifyRecord(String verifyCode, String verifyToken) {
        Optional<VerifyRecord> verifyRecord;
        return (verifyRecord = verifyRecordRepository.findByVerifyCodeAndVerifyToken(verifyCode, verifyToken)).isPresent()
                && !DatetimeUtil.getCurrentTimestamp().after(verifyRecord.get().getOverdueTime());
    }
}