package org.hiease.authserver.view;

import org.hiease.authserver.data.User;
import org.hiease.authserver.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by qihaiyan on 2016/11/13.
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/logout")
    public void logout(Principal principal, HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();

        String redirectUrl = request.getParameter("redirect");

        response.sendRedirect(redirectUrl);

        return;
    }

    @RequestMapping("/updatePasswd")
    public void updatePasswd(@RequestBody String passwd) {
        this.userRepository.updateUserPasswd(User.PASSWORD_ENCODER.encode(passwd));
    }
}