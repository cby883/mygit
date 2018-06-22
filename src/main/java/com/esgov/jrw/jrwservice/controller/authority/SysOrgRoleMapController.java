package com.esgov.jrw.jrwservice.controller.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.service.authority.SysOrgRoleMapService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 机构角色服务Controller
 *
 * @author Yjm
 * @create 2018-04-09 下午3:05
 */
@RestController
@RequestMapping("/sysmgr/service/orgrole/map")
@Api(value = "org-role-map",
    description = "机构角色关系")
@ApiResponses(value = {@ApiResponse(code = 0,message = "操作成功",response = Integer.class),
        @ApiResponse(code = 1,message = "操作失败",response = Integer.class)})
public class SysOrgRoleMapController {
    @Autowired
    private SysOrgRoleMapService sysOrgRoleMapService;

    @ApiOperation(value = "获取机构角色",
                    notes = "根据机构Id获取相关联的角色")
    @PostMapping("/getRelateRoleId/{orgId}")
    public ServiceResponse getRelatedRoleId(@ApiParam("机构Id") @PathVariable("orgId")String orgId){
        return sysOrgRoleMapService.getRelatedRoleId(orgId);
    }

    @ApiOperation(value = "关联机构角色",
            notes = "根据机构Id批量关联角色（新增或删除）")
    @PostMapping("/mapOrgRole")
    public ServiceResponse mapOrgRole(@ApiParam("机构Id") @RequestParam("orgId") String orgId,
                                      @ApiParam("新增的角色Ids")  @RequestParam("addRoleIds") String addRoleIds,
                                      @ApiParam("删除的角色Ids")  @RequestParam("delRoleIds") String delRoleIds){
        return sysOrgRoleMapService.mapOrgRole(orgId,addRoleIds,delRoleIds);
    }

    @ApiOperation(value = "获取角色机构",
            notes = "根据角色Id获取相关联的机构")
    @PostMapping("/getRelateOrgId/{roleId}")
    public ServiceResponse getRelatedOrgId(@ApiParam("角色Id") @PathVariable("roleId")String roleId){
        return sysOrgRoleMapService.getRelatedOrgId(roleId);
    }

    @ApiOperation(value = "关联角色机构",
            notes = "根据角色Id批量关联机构（新增或删除）")
    @PostMapping("/mapRoleOrg")
    public ServiceResponse mapRoleOrg(@ApiParam("角色Id")@RequestParam("roleId") String roleId,
                                      @ApiParam("新增的机构Ids")@RequestParam("addOrgIds") String addOrgIds,
                                      @ApiParam("删除的机构Ids") @RequestParam("delOrgIds") String delOrgIds){
        return sysOrgRoleMapService.mapRoleOrg(roleId,addOrgIds,delOrgIds);
    }

}
