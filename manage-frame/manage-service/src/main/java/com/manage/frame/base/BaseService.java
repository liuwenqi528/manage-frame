package com.manage.frame.base;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/19
 * Time: 15:47
 */
public interface BaseService<T,D> {
    /**
     * 根据主键ID查询对象
     */
    T getOne(D id);

    /**
     * 根据条件查询对象
     *
     */
    T getByParam(T t);

    /**
     * 查询所有数据
     *
     */
    List<T> findAll();

    /**
     * 根据条件查询对象
     *
     */
    List<T> findByQuery(T t);

    /**
     * 保存对象
     *
     */
    int insert(T t);

    /**
     * 修改对象
     *
     */
    int update(T t);

    /**
     * 根据ID删除对象---物理删除
     *
     */
    int delete(D id);

    /**
     * 根据条件删除---物理删除
     *
     */
    int deleteByParam(T t);
}
