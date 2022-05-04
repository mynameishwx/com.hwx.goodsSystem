package com.hwx.goodsSystem.Config;

import com.hwx.goodsSystem.Component.goodsPand;
import com.hwx.goodsSystem.entity.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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




   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(goodsPand).
              excludePathPatterns("**/*.css","**/*.jpg","**/*.png","**/*.js","**/*.jpeg");
   }

   /**
    * 静态资源映射
    * @param registry
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      /**
       * 商店头像映射
       */
      String Type= String.valueOf(shopImgUrl.charAt(0));
      if(Type.equals("E")){
         /**
          * 开发环境
          */
         registry.addResourceHandler("/goodsImg/**").addResourceLocations("file:"+goodsImgUrl);
         registry.addResourceHandler("/shopImg/**").addResourceLocations("file:"+shopImgUrl);
      }else {
         /**
          * 生产环境
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
