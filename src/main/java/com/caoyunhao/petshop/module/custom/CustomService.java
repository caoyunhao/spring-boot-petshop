package com.caoyunhao.petshop.module.custom;


import com.caoyunhao.petshop.common.BaseService;
import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.ConvertUtil;
import com.caoyunhao.petshop.common.util.EncryptionUtil;
import com.caoyunhao.petshop.common.util.RowConverter;
import com.caoyunhao.petshop.entity.*;
import com.caoyunhao.petshop.module.image.ImageService;
import com.caoyunhao.petshop.module.login.LoginData;
import com.caoyunhao.petshop.module.login.LoginService;
import com.caoyunhao.petshop.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Service
@Transactional
public class CustomService extends BaseService {
    @Autowired
    CustomRepository customRepository;
    @Autowired
    CustomRoleRepository customRoleRepository;
    @Autowired
    CustomWalletRepository customWalletRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    LoginService loginService;

    /**
     * 获取顾客
     */
    public Custom findCustomById(Long customId) throws Exception {
        return findCustomByCustomId(customId);
    }

    /**
     * 获取顾客Data
     */
    public CustomData findCustomDataById(Long customId) throws Exception {
        return toCustomData(findCustomByCustomId(customId));
    }

    /**
     * 添加顾客
     * 99aa7342ba09523f6b3e7ccdbea93fe3b18adeb9
     */
    public CustomData addCustom(CustomFormWithPassword customFormWithPassword) throws Exception {
        Custom custom = new Custom();
        BeanUtils.copyProperties(customFormWithPassword, custom);
        custom.setPassword(EncryptionUtil.encryptPassword(custom.getPassword()));

        //顾客名冲突
        if (customRepository.countByCustomName(custom.getCustomName()) > 0)
            throw new WebBackendException(ErrorCode.CUSTOM_EXISTS);

        Custom savedCustom = customRepository.save(custom);
        customRoleRepository.saveAll(generateCustomRoleList(customFormWithPassword.getRoleIdList(), custom.getId()));

        return toCustomData(savedCustom);
    }

    /**
     * 修改顾客
     */
    public CustomData updateCustom(CustomForm customForm, Long customId) throws Exception {
        Custom custom = findCustomByCustomId(customId);

        BeanUtils.copyProperties(customForm, custom);
        customRepository.save(custom);
        customRoleRepository.saveAll(generateCustomRoleList(customForm.getRoleIdList(), customId));
        return toCustomData(custom);
    }

    /**
     * 修改密码
     */
    public CustomModifyPasswordData modifyCustomPassword(CustomModifyPasswordForm customModifyPasswordForm, Long customId, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (!customModifyPasswordForm.isEqual()) {
            throw new WebBackendException(ErrorCode.TWO_PASSWORDS_NOT_EQUAL);
        }

        Custom custom = findCustomByCustomId(customId);

        if (!custom.getPassword().equals(EncryptionUtil.encryptPassword(customModifyPasswordForm.getOldPassword()))) {
            throw new WebBackendException(ErrorCode.MODIFY_PASSWORDS_FAILURE);
        }
        custom.setPassword(EncryptionUtil.encryptPassword(customModifyPasswordForm.getPassword()));
        customRepository.save(custom);

        return generateCustomModifyPasswordData(custom, httpSession, httpServletRequest, httpServletResponse);
    }

    /**
     * 根据顾客id获取其角色列表
     */
    private List<Role> getRoleList(Long customId) throws Exception {
        List<CustomRole> customRoleList = customRoleRepository.findByCustomId(customId);
        List<Role> roleList = new ArrayList<>();
        if (customRoleList == null)
            return null;
        for (CustomRole customRole : customRoleList) {
            roleList.add(roleRepository.findById(customRole.getRoleId()).get());
        }
        return roleList;
    }

    /**
     * 充值
     */
    public void recharge(Long id, CustomRechargeForm customRechargeForm) throws Exception {
        if (null == customRechargeForm) {
            throw new WebBackendException(ErrorCode.NOT_VALID_PARAM);
        }

        CustomWallet customWallet = this.findCustomWalletByCustomId(id);

        customWallet.setWalletBalance(customWallet.getWalletBalance() + customRechargeForm.getRechargeNumber());
        customWalletRepository.save(customWallet);
    }

    /**
     * 根据顾客和角色列表插入关联
     */
    private List<CustomRole> generateCustomRoleList(List<Long> roleIdList, Long customId) throws Exception {
        if (roleIdList == null || roleIdList.size() == 0)
            return null;
        List<CustomRole> customRoleList = new ArrayList<CustomRole>();
        for (Long roleId : roleIdList) {
            CustomRole customRole = new CustomRole();
            customRole.setCustomId(customId);
            customRole.setRoleId(roleId);
            customRoleList.add(customRole);
        }
        return customRoleList;
    }

    /**
     * 生成CustomModifyPasswordData
     */
    private CustomModifyPasswordData generateCustomModifyPasswordData(Custom custom, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        LoginData loginData = loginService.generateLoginData(custom, httpSession, httpServletRequest, httpServletResponse);
        CustomModifyPasswordData customModifyPasswordData = new CustomModifyPasswordData();
        BeanUtils.copyProperties(loginData, customModifyPasswordData);
        return customModifyPasswordData;
    }

    /**
     * 将Custom转变为CustomData
     */
    private CustomData toCustomData(Custom custom)
            throws WebBackendException {
        CustomData customData = new CustomData();
        BeanUtils.copyProperties(custom, customData);

        CustomWallet customWallet = findCustomWalletByCustomId(custom.getId());
        BeanUtils.copyProperties(customWallet, customData, "id");

        CustomImage customImage = imageService.getCustomImageByCustomId(custom.getId());
        if (customImage != null) {
            customData.setCustomImageId(customImage.getImageId());
        }

        customData.setCustomImageUrl("");

        return customData;
    }

    /**
     * 将Page<Custom>转变为Page<CustomData>
     */
    private Page<CustomData> toCustomDataPage(Page<Custom> customPage, Pageable pageable) throws Exception {
        return ConvertUtil.convertPage(customPage, pageable, customPage.getTotalElements(), new RowConverter<Custom, CustomData>() {
            @Override
            public CustomData convertRow(Custom custom) throws Exception {
                return toCustomData(custom);
            }
        });
    }

    /**
     * 通过Repository获得顾客
     */
    private Custom findCustomByCustomId(Long customId) throws Exception {
        Optional<Custom> customOptional = customRepository.findById(customId);
        //找不到顾客
        if (!customOptional.isPresent())
            throw new WebBackendException(ErrorCode.CUSTOM_NOT_FOUND);

        return customOptional.get();
    }

    /**
     * 通过Repository获得顾客钱包，若无则创建并返回
     */
    private CustomWallet findCustomWalletByCustomId(Long customId) {
        Optional<CustomWallet> customWalletOptional;
        CustomWallet customWallet;

        if ((customWalletOptional = customWalletRepository.findByCustomId(customId)).isPresent()) {
            customWallet = customWalletOptional.get();
        } else {
            customWallet = new CustomWallet();
            customWallet.setCustomId(customId);
        }
        return customWallet;
    }
}
