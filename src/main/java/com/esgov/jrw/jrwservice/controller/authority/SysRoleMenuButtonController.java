package com.esgov.jrw.jrwservice.controller.authority;

import com.esgov.jrw.jrwservice.common.util.FastJSONUtil;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.service.authority.SysRoleMenuButtonMappService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 描述: 角色菜单按钮Controller
 *
 * @author Yangjinming
 * @create 2018-04-17 下午1:20
 */
@RestController
@RequestMapping("/sysmgr/service/rolemenubutton/map")
@Api(value = "role-menu-button-map",description = "角色菜单按钮关联")
@ApiResponses(value = {@ApiResponse(code = 0,message = "操作成功",response = Integer.class),
        @ApiResponse(code = 1,message = "操作失败",response = Integer.class)})
public class SysRoleMenuButtonController {
    @Autowired
    SysRoleMenuButtonMappService roleMenuButtonMappService;

    @ApiOperation(value = "获取角色、菜单、按钮关联信息",
            notes = "根据角色Id获取相关联的菜单、按钮")
    @PostMapping("/getRoleMenuButtonMapps/{roleId}")
    public ServiceResponse getRoleMenuButtonMapps(@ApiParam("角色Id") @PathVariable("roleId")String roleId){
        return roleMenuButtonMappService.getRoleMenuButtonMapps(roleId);
    }

    @ApiOperation(value = "关联角色菜单",
            notes = "根据一个或多个RoleId 批量关联菜单按钮")
    @PostMapping("/mapOrgRole")
    public ServiceResponse mapRoleMenuButton(@ApiParam("角色id组 字符串，以逗号隔开") @RequestParam("roleId") String roleId,
                                      @ApiParam("新增的菜单json")  @RequestParam("addMenus") String addMenus,
                                      @ApiParam("删除的菜单Id组")  @RequestParam("delMenus") String delMenus){
        List<HashMap<String,String>> menus = (List<HashMap<String,String>>)FastJSONUtil.convertToList(addMenus, (new HashMap<String,String>()).getClass());
        return roleMenuButtonMappService.mapRoleMenuButton(roleId,menus,delMenus);
    }

}
