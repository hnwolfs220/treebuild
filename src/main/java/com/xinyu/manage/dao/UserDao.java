package com.xinyu.manage.dao;

import com.xinyu.manage.entity.DeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huangxk
 * @date 2020/5/26
 * @Description:
 */
@Mapper
public interface UserDao {
    DeptEntity getNodeTree();
    List<DeptEntity> getNextNodeTree(int deptId);
}