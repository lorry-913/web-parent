package com.midea.common.baseservice.impl;

import com.midea.common.basedao.BaseDao;
import com.midea.common.baseservice.BaseService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseDao<T> getDao();

    @Override
    public int save(T t) {
        return getDao().insert(t);
    }

    @Override
    public int delete(T t) {
        return getDao().delete(t);
    }

    @Override
    public int deleteById(Integer id) {
        return getDao().deleteByIds(id.toString());
    }

    @Override
    public int update(T t) {
        return getDao().updateByPrimaryKey(t);
    }

    @Override
    public T getById(Integer id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Override
    public List<T> getAll() {
        return getDao().selectAll();
    }

    @Override
    public int saveAll(List<T> list) {
        return getDao().insertList(list);
    }

    @Override
    public List<T> searchByExample(Example example) {
        return getDao().selectByExample(example);
    }
}
