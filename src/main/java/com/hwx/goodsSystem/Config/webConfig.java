package com.hwx.goodsSystem.Config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hwx.goodsSystem.Component.goodsPand;
import com.hwx.goodsSystem.entity.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class webConfig implements WebMvcConfigurer {
   @Autowired
   private goodsPand goodsPand;

   @Value("${shop.imgNameUrl}")
   private String shopImgUrl;


   @Value("${goods.imgNameUrl}")
   private String goodsImgUrl;

   @Bean
   public  WebMvcConfigurer WebMvcConfigurer(){
      WebMvcConfigurer WebMvcConfigurer=new WebMvcConfigurer() {
         @Override
         public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/enroll").setViewName("enroll");
            registry.addViewController("/setting").setViewName("setting");
         }
      };
      return WebMvcConfigurer;
   }


   @Bean
   public ServletContextInitializer servletContextInitializer() {
      return new ServletContextInitializer() {
         @Override
         public void onStartup(ServletContext servletContext) throws ServletException {
            servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE) );
         }
      };
   }


   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(goodsPand).
              excludePathPatterns("**/*.css","**/*.jpg","**/*.ico","**/*.png","**/*.js","**/*.jpeg");
   }

   /**
    * ??????????????????
    * @param registry
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      /**
       * ??????????????????
       */
      String Type= String.valueOf(shopImgUrl.charAt(0));
      if(Type.equals("E")){
         /**
          * ????????????
          */
         registry.addResourceHandler("/goodsImg/**").addResourceLocations("file:"+goodsImgUrl);
         registry.addResourceHandler("/shopImg/**").addResourceLocations("file:"+shopImgUrl);
      }else {
         /**
          * ????????????
          */
         registry.addResourceHandler("/goodsImg/**").addResourceLocations("file:"+goodsImgUrl);
         registry.addResourceHandler("/shopImg/**").addResourceLocations("file:"+shopImgUrl);
      }
   }

   @Bean
   public shop  shop(){
      return  new shop();
   }

}
