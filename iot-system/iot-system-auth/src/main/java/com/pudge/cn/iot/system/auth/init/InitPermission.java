package com.pudge.cn.iot.system.auth.init;

import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.entity.RolePermission;
import com.pudge.cn.iot.common.cache.RedisService;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.system.auth.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author mu_zhen
 * @description 利用实现ApplicationRunner接口完成权限初始化加载
 * @Date 2023/3/23 23:53
 */
@Component
public class InitPermission implements ApplicationRunner {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private RedisService redisService;

    /**
     * 权限初始化加载
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //加载所有权限   0：完全匹配    1：通配符匹配
        List<Permission> match0 = permissionService.findByMatch(0);
        List<Permission> match1 = permissionService.findByMatch(1);

        // 所有角色的权限   查询角色权限映射关系
        List<RolePermission> rolePermissions = permissionService.allRolePermissions();

        //匹配每个角色拥有的权限列表
        Map<String, Set<Permission>> rolePermissionMapping = rolePermissionFilter(match0, match1, rolePermissions);
        redisService.hSet("RolePermissionAll","Absolutely",match0);
        redisService.hSet("RolePermissionAll","Wildcard",match1);
        redisService.hSetAll("RolePermissionMap",rolePermissionMapping);
    }

    /**
     * 每个角色拥有的权限
     */
    private Map<String, Set<Permission>> rolePermissionFilter(List<Permission> match0,
                                      List<Permission> match1,
                                      List<RolePermission> rolePermissions){
        // 每个角色拥有哪些权限  存入 容器中
        // 分为完全匹配  和  通配符匹配
        Map<String, Set<Permission>> rolePermissionMapping = new HashMap<>();
        
        //循环所有的角色关系映射
        for (RolePermission rolePermission : rolePermissions) {
            int rid = rolePermission.getRid();
            int pid = rolePermission.getPid();

            Set<Permission> permissionSet0 = rolePermissionMapping.get(AuthConstant.Auth_Absolutely_Match+rid);
            if (null == permissionSet0){
                permissionSet0 = new HashSet<>();
            }
            Set<Permission> permissionSet1 = rolePermissionMapping.get(AuthConstant.Auth_Wildcard_Match+rid);
            if (null == permissionSet1){
                permissionSet1 = new HashSet<>();
            }
            // 查找每个角色对应的权限 - 完全匹配
            for (Permission permission : match0) {
                //权限完全匹配
                if (permission.getId() == pid){
                    permissionSet0.add(permission);
                    break;
                }
            }
            // 查找每个角色对应的权限 - 通配符匹配
            for (Permission permission : match1) {
                //权限统配符匹配
                if (permission.getId() == pid){
                    permissionSet1.add(permission);
                    break;
                }
            }
            if(permissionSet0.size()>0)
                rolePermissionMapping.put(AuthConstant.Auth_Absolutely_Match+rid,permissionSet0);
            if (permissionSet1.size()>0)
                rolePermissionMapping.put(AuthConstant.Auth_Wildcard_Match+rid,permissionSet1);
        }

        return rolePermissionMapping;
    }

}
