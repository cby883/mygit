package com.esgov.jrw.jrwservice.controller.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.entity.authority.SysUser;
import com.esgov.jrw.jrwservice.service.authority.SysUserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 单点登录用户验证解耦
 *
 * @author Qitengfei
 * @create 2018-4-15 10:50:53
 */
@RestController
@RequestMapping("/sysmgr/service/sso")
@Api(value = "oauth", description = "登录认证api")
@ApiResponses(value = {@ApiResponse(code = 0, message = "操作成功", response = Integer.class),
        @ApiResponse(code = 1, message = "操作失败", response = Integer.class)})
public class SsoController {
    @Autowired
    SysUserService sysUserService;

    /**
     * cas 登录接口
     *
     * @param
     * @return
     */
    @ApiOperation(value = "cas 登录接口",
            notes = "cas 登录接口notes")
    @RequestMapping(value = "/cas/login", method = RequestMethod.POST)
    ResponseEntity<CasUser> getUserByLoginName(@RequestHeader HttpHeaders httpHeaders, HttpServletResponse response) {
        ResponseEntity<CasUser> result = null;
        try {
            SysUser userWeb = this.obtainUserFormHeader(httpHeaders);

            //当没有 传递 参数的情况
            if (userWeb == null) {
                result = new ResponseEntity<CasUser>(HttpStatus.NOT_FOUND);
            }

            ServiceResponse<SysUser> userDb = sysUserService.getUserByLoginName(userWeb.getLoginName());

            SysUser dbUser = userDb.getData();

            if (dbUser != null) {
                if (!dbUser.getLoginPwd().equals(userWeb.getLoginPwd())) {
                    //密码不匹配
                    result = new ResponseEntity<CasUser>(HttpStatus.BAD_REQUEST);
                } else if ("0".equals(dbUser.getStatus())) {
                    //禁用 403
                    result = new ResponseEntity<CasUser>(HttpStatus.FORBIDDEN);
                }
//                else if (dbUser.isLocked()) {
//                    //锁定 423
//                    result = new ResponseEntity<CasUser>(HttpStatus.LOCKED);
//                }else if (dbUser.isExpired()) {
//                    //过期 428
//                    result = new ResponseEntity<CasUser>(HttpStatus.PRECONDITION_REQUIRED);
//                }
                else {
                    //正常的数据
                    //直接返回User数据
                    CasUser casUser = new CasUser();
                    casUser.setUsername(dbUser.getLoginName());
                    result = new ResponseEntity<CasUser>(casUser, HttpStatus.OK);
                }
            } else {
                //不存在 404
                result = new ResponseEntity<CasUser>(HttpStatus.NOT_FOUND);
            }
        } catch (UnsupportedEncodingException e) {
            result = new ResponseEntity<CasUser>(HttpStatus.BAD_REQUEST);
        }

        return result;
    }


    /**
     * 将授权加密的信息返回
     * 根据请求头获取用户名及密码
     *
     * @param httpHeaders
     * @return
     * @throws UnsupportedEncodingException
     */
    private SysUser obtainUserFormHeader(HttpHeaders httpHeaders) throws UnsupportedEncodingException {
        /**
         *
         * This allows the CAS server to reach to a remote REST endpoint via a POST for verification of credentials.
         * Credentials are passed via an Authorization header whose value is Basic XYZ where XYZ is a Base64 encoded version of the credentials.
         */
        //根据官方文档，当请求过来时，会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
        String authorization = httpHeaders.getFirst("authorization");//将得到 Basic Base64(用户名:密码)
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }

        String baseCredentials = authorization.split(" ")[1];
        String usernamePassword = new String(Base64Utils.decodeFromString(baseCredentials), "UTF-8");//用户名:密码
        String credentials[] = usernamePassword.split(":");

        SysUser user = new SysUser();
        user.setLoginName(credentials[0]);
        user.setLoginPwd(credentials[1]);

        return user;
    }


    /**
     * 用于传递给CAS服务器验证数据
     */
    private class CasUser {

        @JsonProperty("id")
        private String username;

        @JsonProperty("@class")
        // 需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
        private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";

        @JsonProperty("attributes")
        private Map<String, Object> attributes = new HashMap<String, Object>();

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
        }
    }

}
