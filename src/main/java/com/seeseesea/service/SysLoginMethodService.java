package com.seeseesea.service;

import com.seeseesea.model.SysLoginMethod;
import com.seeseesea.model.dto.SysLoginMethodDTO;

/**
 * (SysLoginMethod)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
public interface SysLoginMethodService {

    SysLoginMethodDTO getByIdentifierAndMethodType(String identifier, String name);

    void save(SysLoginMethod sysLoginMethod);
}
