package com.plavsic.ecommerce.generic.service;

import org.springframework.data.domain.Pageable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public interface GenericService<T> {
    T findById(Long id);
    Map<String, Object> findAll(Pageable pageable);
    T save(T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    T update(Long id,T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    boolean delete(Long id);
}
