package org.hiease.authserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qihaiyan on 2016/12/2.
 */
@Component
@EnableWebSecurity
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    @Autowired
//    private ConsumerTokenServices consumerTokenServices;
//
//    @Autowired
//    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        System.out.println("test");

//        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
//        OAuth2AccessToken accessToken = authorizationServerTokenServices.getAccessToken(oAuth2Authentication);
//        consumerTokenServices.revokeToken(accessToken.getValue());

//        String redirectUrl = getLocalContextPathUrl(request)+"/logout?myRedirect="+getRefererUrl(request);
//        log.debug("Redirect URL: {}",redirectUrl);
//
//        response.sendRedirect(redirectUrl);
//        String redirect = request.getParameter("redirect");
//        if(redirect != null){
//            res.sendRedirect(redirect);
//        }
    }

}