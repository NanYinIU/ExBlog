package com.nanyin.config.common;

import com.nanyin.model.Users;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by NanYin on 2017-11-17 下午8:10.
 * 包名： com.nanyin.config.common
 * 类描述：密码修改的类
 */
@Component
public class ModifiPass {
    @Autowired
    UserService userService;

    private static final int SUCCESS_FOR_NOW = 1 ;

    private static final int PASSWORD_IS_NULL = 2 ;

    private static final int TWO_PASSWORD_IS_NOT_MATCH = 3 ;

    private static final int OLD_PASSWORD_IS_NOT_RIGHT = 4 ;


    public int checkOldPassWordIsRight(String userName, String oldPassword){
           Users users = userService.findUsersByName(userName);
           if(oldPassword.equals(users.getPassword())){
                return SUCCESS_FOR_NOW;
           }else {
               return OLD_PASSWORD_IS_NOT_RIGHT;
           }
    }

    public int checkNewPassIsNotNull(String newPassword,String newPassword1) {
        if (newPassword != null || ! "".equals(newPassword)) {
            return SUCCESS_FOR_NOW;
        }if(newPassword1 != null || ! "".equals(newPassword1)) {
            return SUCCESS_FOR_NOW;
        }
        else {
            return PASSWORD_IS_NULL;
        }
    }

    public int checkIsEquals(String newPassword,String newPassword1){
        if(newPassword.equals(newPassword1)){
            return SUCCESS_FOR_NOW;
        }else {
            return TWO_PASSWORD_IS_NOT_MATCH;
        }
    }

}
