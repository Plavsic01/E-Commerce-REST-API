package com.plavsic.ecommerce.generic.service;

import com.plavsic.ecommerce.security.AuthUserDetails;
import org.springframework.data.domain.Pageable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public interface GenericService<T> {
    T findById(Long id, AuthUserDetails userDetails) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException;
    Map<String, Object> findAll(Pageable pageable);
    T save(T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    T update(Long id,T t,AuthUserDetails userDetails) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    boolean delete(Long id,AuthUserDetails userDetails);
}
