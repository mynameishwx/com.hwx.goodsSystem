package com.hwx.goodsSystem.Config;

import com.hwx.goodsSystem.Component.goodsPand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
   @Autowired
   private goodsPand goodsPand;

   @Bean
   public  WebMvcConfigurer WebMvcConfigurer(){
      WebMvcConfigurer WebMvcConfigurer=new WebMvcConfigurer() {
         @Override
         public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("").setViewName("index");
            registry.addViewController("/").setViewName("index");
            registry.addViewController("/enroll").setViewName("enroll");
            registry.addViewController("/setting").setViewName("setting");
         }
      };
      return WebMvcConfigurer;
   }




   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(goodsPand).
              excludePathPatterns("**/*.css","**/*.jpg","**/*.png","**/*.js","**/*.jpeg");
   }
}
