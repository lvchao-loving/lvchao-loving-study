package com.lvchao.auth.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * Description: 认证服务器配置
 * 说明: AuthorizationServerConfigurerAdapter 实现了 AuthorizationServerConfigurer三个空空方法
 *  其中实现共有三个方法:
 *      (1)AuthorizationServerSecurityConfigurer:用来配置令牌（token）的访问端点和令牌服务(token services).
 *      (2)ClientDetailsServiceConfigurer:用来配置客户端详情服务（ClientDetailsService），客户端详情信息在 这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
 *      (3)AuthorizationServerEndpointsConfigurer:用来配置令牌端点的安全约束.
 * @author lvchao
 * @date Create on 2020/12/21
 */

@Configuration
@EnableAuthorizationServer
public class LvchaoAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {


    // 1 配置ClientDetailsServiceConfigurer开始
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 第一版学习,使用内存存储
        clients.inMemory()// 使用in‐memory存储
                .withClient("c1")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("res1")// 资源id
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围,只是一个标识
                .autoApprove(false) // fasle会跳转到授权页面,true不会跳转
                .redirectUris("http://www.baidu.com");
    }
    // 1 配置ClientDetailsServiceConfigurer结束

    // 2 配置AuthorizationServerEndpointsConfigurer开始
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() { //设置授权码模式的授权码如何存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    private String SIGNING_KEY = "uaa123";
    @Bean
    public TokenStore tokenStore() {
        //使用内存存储令牌（普通令牌）
        // return new InMemoryTokenStore();
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);// 客户端服务信息
        service.setSupportRefreshToken(true);// 是否产生刷新令牌
        service.setTokenStore(tokenStore());// 令牌存储策略

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter()));
        service.setTokenEnhancer(tokenEnhancerChain);

        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) // 密码模式需要
                .authorizationCodeServices(authorizationCodeServices()) // 授权码方式需要
                .tokenServices(tokenService()) // 不管什么方式都需要加入令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // 允许post提交
    }
    // 2 配置AuthorizationServerEndpointsConfigurer结束

    // 3 配置AuthorizationServerSecurityConfigurer开始
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // /oauth/token_key公开
                .checkTokenAccess("permitAll()") // /oauth/check_token公开
                .allowFormAuthenticationForClients(); // 表单认证,申请令牌
    }
    // 3 配置AuthorizationServerSecurityConfigurer结束
}
