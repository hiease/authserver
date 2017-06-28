package org.hiease.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.*;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//                .getKeyPair("test");
//        converter.setKeyPair(keyPair);
//        return converter;
//    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//                .inMemory()
//                .withClient("acme")
//                .secret("acmesecret")
//                .authorities("ROLE_TRUSTED_CLIENT")
//                .authorizedGrantTypes("implicit", "authorization_code", "refresh_token", "password")
//                .accessTokenValiditySeconds(60*60*24)
//                .scopes("openid")
//                .resourceIds("authserver")
//                .autoApprove(true);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
