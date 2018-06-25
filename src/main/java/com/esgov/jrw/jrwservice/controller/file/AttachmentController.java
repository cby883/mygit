package com.esgov.jrw.jrwservice.controller.file;


import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.entity.authority.SysOrg;
import com.esgov.jrw.jrwservice.entity.file.Attachment;
import com.esgov.jrw.jrwservice.service.authority.SysOrgService;
import com.esgov.jrw.jrwservice.service.file.AttachmentService;
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
@RequestMapping("/service/file/attachment")
@Api(value = "attachment", description = "附件管理")
@ApiResponses(value = {@ApiResponse(code = 0, message = "操作成功", response = Integer.class),
        @ApiResponse(code = 1, message = "操作失败", response = Integer.class)})
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @ApiOperation(value = "新增附件",
            notes = "新增附件")
    @PostMapping
    public ServiceResponse save(@ApiParam(value = "附件信息", required = true) @RequestBody Attachment entity) {
        return attachmentService.save(entity);
    }


    @ApiOperation(value = "删除附件",
            notes = "根据id删除指定的附件")
    @DeleteMapping(value ="/{id}")
    ServiceResponse delete(@ApiParam(value = "附件id",required = true)@PathVariable("id") String id){
        return attachmentService.delete(id);
    }

    @ApiOperation(value = "根据ID查询附件",
            notes = "根据id查询附件")
    @GetMapping(value ="/{id}")
    ServiceResponse get(@ApiParam(value = "附件id",required = true)@PathVariable("id") String id){
        return attachmentService.get(id);
    }

    @ApiOperation(value = "查询附件",
            notes = "根据url的id删除指定的附件")
    @GetMapping(value ="/target")
    ServiceResponse findByTarget(@ApiParam(value = "targetType",required = true)
                                 @RequestParam("targetType") String targetType,
                                 @ApiParam(value = "targetId",required = true)
                                 @RequestParam("targetId") String targetId,
                                 @RequestParam(value = "targetFlag",required = false) String targetFlag){

        return attachmentService.findByTarget(targetType,targetId,targetFlag);
    }
}
