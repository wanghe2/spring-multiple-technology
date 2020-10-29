package com.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用基于内存的用户存储
        auth.inMemoryAuthentication().withUser("wanghe").password("123456").roles("USER").and()
         .withUser("admin").password("admin").roles("USER","ADMIN")
         .and().passwordEncoder(new CustomPasswordEncoder());

        /*
         * 基于JDBC的用户权限配置
         auth.jdbcAuthentication().dataSource(dataSource).
         usersByUsernameQuery("select username,password,true from tb_user where username = ?").
         authoritiesByUsernameQuery("select username,'ROLE_USER' from tb_user where username = ?");
         */

        // 加入自定义的安全认证
        //auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .httpBasic()
//                .
//                authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()// 对请求授权

                .antMatchers("/data/**").permitAll() //对于访问 /wang/getTestbean 链接，不需要登录也可以访问
                .antMatchers( "/page/**").hasRole("ADMIN" ) //需要角色配合 （也需要用户认证） （token的权限会自动加上 ROLE_前缀）
                .anyRequest().authenticated()// 其他 url 需要身份认证，这些没有匹配上的其他的url请求，只需要用户被验证。
                .and()
                .formLogin()  //开启登录
//                .loginPage("/login") //自定义登录界面
//                .successHandler(authenticationSuccessHandler) //登录成功
//                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll();
    }
}


/**
 * springsecurity在进行密码验证时对密码编码格式有要求，这里自定义编码方式（明文密码）
 */
class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
