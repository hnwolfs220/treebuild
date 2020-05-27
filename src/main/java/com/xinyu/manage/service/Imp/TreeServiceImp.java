package com.xinyu.manage.service.Imp;

import com.xinyu.manage.dao.UserDao;
import com.xinyu.manage.entity.DeptEntity;
import com.xinyu.manage.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangxk
 * @date 2020/5/26
 * @Description:
 */
@Service
public class TreeServiceImp implements TreeService{
    @Autowired
    private UserDao userDao;

    public List<DeptEntity> getNextNodeTree(Integer deptId){
        return userDao.getNextNodeTree(deptId);
    }

    public DeptEntity getNodeTree(){
        return userDao.getNodeTree();
    }
}