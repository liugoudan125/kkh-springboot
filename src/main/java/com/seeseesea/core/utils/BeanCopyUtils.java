package com.seeseesea.core.utils;

import lombok.experimental.UtilityClass;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * BeanUtils
 */
@UtilityClass
public class BeanCopyUtils {

    private static final Map<String, BeanCopier> beanCopierCache = new HashMap<>();

    private static <S, T> BeanCopier getBeanCopier(S s, Class<T> tClass) {
        String cacheKey = s.getClass().getName() + "_" + tClass.getName();
        BeanCopier beanCopier = beanCopierCache.get(cacheKey);
        if (null == beanCopier) {
            synchronized (BeanCopyUtils.class) {
                beanCopier = beanCopierCache.get(cacheKey);
                if (null == beanCopier) {
                    beanCopier = BeanCopier.create(s.getClass(), tClass, false);
                    beanCopierCache.put(cacheKey, beanCopier);
                }
            }
        }
        return beanCopier;
    }

    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> tClass) {
        if (null == sourceList || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            //无参构造
            Constructor<T> noArgsConstructor = tClass.getDeclaredConstructor();
            noArgsConstructor.setAccessible(true);
            List<T> list = new ArrayList<>(sourceList.size());
            for (S s : sourceList) {
                T t = noArgsConstructor.newInstance();
                list.add(copy(s, () -> t));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <S, T> T copy(S s, Supplier<T> supplier) {
        T t = supplier.get();
        BeanCopier beanCopier = getBeanCopier(s, t.getClass());
        beanCopier.copy(s, t, null);
        return t;
    }

}
