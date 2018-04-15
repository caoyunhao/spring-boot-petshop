package com.caoyunhao.petshop.module.logout;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.repository.CustomNonceRepository;
import com.caoyunhao.petshop.repository.CustomTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Transactional
@Service
public class LogoutService {
    @Autowired
    CustomTokenRepository customTokenRepository;
    @Autowired
    CustomNonceRepository customNonceRepository;

    public void logout(Long id) throws WebBackendException {
        try {
            customTokenRepository.deleteByCustomId(id);
            customNonceRepository.deleteByCustomId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebBackendException(ErrorCode.LOGOUT_FAILED);
        }
    }
}
