package com.manage.frame.base;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/19
 * Time: 15:49
 */
@Service
public class BaseServiceImpl<T,D> implements BaseService<T,D> {

    @Resource
    private BaseDao<T,D> baseDao;

    /**
     * 根据主键ID查询对象
     *
     * @param id
     */
    @Override
    public T getOne(D id) {
        return baseDao.getOne(id);
    }

    /**
     * 根据条件查询对象
     *
     * @param t
     */
    @Override
    public T getByParam(T t) {
        return baseDao.getByParam(t);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    /**
     * 根据条件查询对象
     *
     * @param t
     */
    @Override
    public List<T> findByQuery(T t) {
        return baseDao.findByParam(t);
    }

    /**
     * 保存对象
     *
     * @param t
     */
    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    /**
     * 修改对象
     *
     * @param t
     */
    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    /**
     * 根据ID删除对象---物理删除
     *
     * @param id
     */
    @Override
    public int delete(D id) {
        return baseDao.delete(id);
    }

    /**
     * 根据条件删除---物理删除
     *
     * @param t
     */
    @Override
    public int deleteByParam(T t) {
        return baseDao.deleteByParam(t);
    }
}
