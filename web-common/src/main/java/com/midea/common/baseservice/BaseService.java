package com.midea.common.baseservice;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface BaseService<T> {
    int save(T t);

    int delete(T t);

    int deleteById(Integer id);

    int update(T t);

    T getById(Integer id);

    List<T> getAll();

    int saveAll(List<T> list);

    List<T> searchByExample(Example example);
}
