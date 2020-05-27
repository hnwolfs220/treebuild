package com.xinyu.manage.contorller;

import com.xinyu.manage.entity.DeptEntity;
import com.xinyu.manage.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangxk
 * @date 2020/5/26
 * @Description:
 */
@RestController
public class TreeController {
    @Autowired
    private TreeService treeService;

    @RequestMapping("/tree/{deptId}")
    public List<DeptEntity> tree(@PathVariable int deptId){
        return treeService.getNextNodeTree(deptId);
    }
    @RequestMapping("/treeShow")
    public List<DeptEntity> treeShow(@RequestParam("id") int id){
        return treeService.getNextNodeTree(id);
    }

    @RequestMapping("nodeTree")
    public DeptEntity nodeTree(){
        return treeService.getNodeTree();
    }

}