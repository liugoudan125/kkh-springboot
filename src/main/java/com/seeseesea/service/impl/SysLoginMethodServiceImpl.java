package com.seeseesea.service.impl;


import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.dao.SysLoginMethodDao;
import com.seeseesea.model.SysLoginMethod;
import com.seeseesea.model.dto.SysLoginMethodDTO;
import com.seeseesea.service.SysLoginMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (SysLoginMethod)业务层接口实现类
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
@RequiredArgsConstructor
@Service
public class SysLoginMethodServiceImpl implements SysLoginMethodService {
    private final SysLoginMethodDao sysLoginMethodDao;

    @Override
    public SysLoginMethodDTO getByIdentifierAndMethodType(String identifier, String methodType) {
        SysLoginMethod sysLoginMethod = sysLoginMethodDao.selectByIdentifierAndMethodType(identifier, methodType);
        if (sysLoginMethod == null) {
            return null;
        }
        return BeanCopyUtils.copy(sysLoginMethod, SysLoginMethodDTO::new);
    }

    @Override
    public void save(SysLoginMethod sysLoginMethod) {
        sysLoginMethodDao.insert(sysLoginMethod);
    }
}
