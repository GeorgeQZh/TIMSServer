package ustc.sse.tims.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ustc.sse.tims.component.LoginHandlerInterceptor;


/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.config
 * @date 2019/3/13-16:42
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/index","/","/login","/error","/login.html",
                        "/static/**","/static/bootstrap/**","/static/css/**","/static/font-awesome/**","/static/ico/**","/static/images/**","/static/img/**","/static/js/**","/static/plugins/**","/static/scss/**","/webjars/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/dhcps.html").setViewName("dhcps");


    }
}
