package com.xinyu.manage.entity;

/**
 * @author huangxk
 * @date 2020/5/26
 * @Description:
 */

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * 部门Tree结构
 */
//@TableName("sys_dept")
public class DeptEntity implements Serializable {

    /**
     * 部门id
     */
//    @TableId(value="dept_id",type= IdType.AUTO)
    private Integer deptId;
    /**
     * 父Id
     */
    private Integer parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序编号
     */
    private String orderNum;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 子节点
     */
    private List<DeptEntity> treeNode;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<DeptEntity> getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(List<DeptEntity> treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
//        原文链接：https://blog.csdn.net/qq_38164123/article/details/94358131