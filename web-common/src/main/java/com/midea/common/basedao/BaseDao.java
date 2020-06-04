package com.midea.common.basedao;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BaseDao<T> extends Mapper<T>, InsertListMapper<T>, IdsMapper<T>, ConditionMapper<T> {
    /**
     * 其中Mapper包含了基本的增删改查，例如：
     * insertSelective(t);
     * delete(t);
     * updateByPrimaryKey(t);
     * selectByPrimaryKey(id);
     * ...
     *
     * InsertListMapper则包含了批量插入：
     *     @Options(useGeneratedKeys = true, keyProperty = "id")
     *     @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
     *     int insertList(List<? extends T> recordList);
     *
     * 作者：TinyThing
     * 链接：https://www.jianshu.com/p/b61fd2761ff0
     * 来源：简书
     * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     */
}
