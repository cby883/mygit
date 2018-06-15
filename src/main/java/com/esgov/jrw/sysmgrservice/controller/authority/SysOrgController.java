package com.esgov.jrw.sysmgrservice.controller.authority;


import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysOrg;
import com.esgov.jrw.sysmgrservice.service.authority.SysOrgService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 机构管理controller
 *
 * @author Yangjinming
 * @create 2018-03-29 下午7:33
 */
@RestController
@RequestMapping("/sysmgr/service/sysorg/orgs")
@Api(value = "org",description = "机构管理")
@ApiResponses(value = {@ApiResponse(code = 0,message = "操作成功",response = Integer.class),
                        @ApiResponse(code = 1,message = "操作失败",response = Integer.class)})
public class SysOrgController {
    @Autowired
    SysOrgService sysOrgService;

    @ApiOperation(value = "新增机构",
                    notes = "机构保存")
    @PostMapping
    ServiceResponse save(@ApiParam(value = "机构信息",required = true)@RequestBody SysOrg sysOrg){
        return sysOrgService.save(sysOrg);
    }

    @ApiOperation(value = "删除机构",
            notes = "根据url的id删除指定的机构")
    @DeleteMapping(value ="/{id}")
    ServiceResponse delete(@ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.delete(id);
    }
    @ApiOperation(value = "批量删除机构",
            notes = "根据传入的id数组删除指定的机构")
    @PostMapping(value = "/batch")
    ServiceResponse deleteAll(@ApiParam(value = "机构id数组",required = true)@RequestParam(value = "delete[]") String[] delete){
        return sysOrgService.deleteAll(delete);
    }
    @ApiOperation(value = "更新机构",
            notes = "更新对应机构信息")
    @PutMapping
    ServiceResponse update(@ApiParam(value = "机构信息",required = true)@RequestBody SysOrg sysOrg){
        return sysOrgService.update(sysOrg);
    }

    @ApiOperation(value = "查询机构",
            notes = "获取对应机构信息")
    @GetMapping(value = "/{id}")
    ServiceResponse get(@ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.get(id);
    }

    @ApiOperation(value = "分页查询",
            notes = "根据条件分页查询对应机构信息")
    @PostMapping(value = "/{currentPage}/{pageSize}")
    ServiceResponse queryPage(@ApiParam(value = "查询条件",required = true)@RequestBody SysOrg sysOrg,
                              @ApiParam(value = "当前页",required = true)@PathVariable("currentPage") int currentPage,
                              @ApiParam(value = "页码",required = true)@PathVariable("pageSize") int pageSize){
        return sysOrgService.queryPage(sysOrg,currentPage,pageSize);
    }

    @ApiOperation(value = "获取机构树",
            notes = "获取所有机构树")
    @GetMapping(value = "/trees")
    ServiceResponse getTree(){
        return sysOrgService.getTree();
    }

    @ApiOperation(value = "获取机构子树",
            notes = "根据id获取机构子树")
    @GetMapping(value = "/childs/{id}")
    ServiceResponse orgChildList(@ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.orgChildList(id);
    }

    @ApiOperation(value = "权限获取机构树",
            notes = "根据id和权限获取机构树")
    @GetMapping(value = "/authority/{userDataAuthority}/trees/{id}")
    ServiceResponse getAuthorityTree(@ApiParam(value = "权限", example = "unlimit,currentDepartment,childrenDepartment",required = true)@PathVariable("userDataAuthority") String userDataAuthority,
                                     @ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.getAuthorityTree(userDataAuthority,id);
    }

    @ApiOperation(value = "启用机构",
            notes = "根据id启用机构")
    @PutMapping(value = "/used/{id}")
    ServiceResponse enableOrg(@ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.enableOrg(id);
    }
    @ApiOperation(value = "禁用机构",
            notes = "根据id禁用机构")
    @PutMapping(value = "/unused/{id}")
    ServiceResponse disableOrg(@ApiParam(value = "机构id",required = true)@PathVariable("id") String id){
        return sysOrgService.disableOrg(id);
    }
}
