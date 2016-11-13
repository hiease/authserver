package org.hiease.authserver.view;

import org.hiease.authserver.data.User;
import org.hiease.authserver.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qihaiyan on 2016/11/13.
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/updatePasswd")
    public void updatePasswd(@RequestBody String passwd) {
        this.userRepository.updateUserPasswd(User.PASSWORD_ENCODER.encode(passwd));
    }
}
