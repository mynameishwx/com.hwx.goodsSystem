package com.hwx.goodsSystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class goodsJWT {
   /**
    * 密钥
    */
   public static final String  RUSPUT="2fed48eF-bd7e-4cO0-9728-8606e2859d4D";

   public  String  getJwt(Map<String,String> map){

      /**
       * 创建jwt
       */
      JWTCreator.Builder  builder= JWT.create();

      /**
       * 获取当前时间
       */
      Calendar Calendar= java.util.Calendar.getInstance();

      /**
       * 在当前时间上加上30分钟
       */
      Calendar.add(java.util.Calendar.MINUTE,30);

      /**
       * 设置过期时间
       */
      builder.withExpiresAt(Calendar.getTime());

      map.forEach((k,v)->{
             builder.withClaim(k,v);
      });

      return  builder.sign(Algorithm.HMAC256(RUSPUT));
   }

   /**
    * 验证JWT令牌
    * @param token
    * 需要验证的jwt令牌
    * @return
    */
   public DecodedJWT validate(String token){
      DecodedJWT  decodedJWT= JWT.require(Algorithm.HMAC256(RUSPUT)).build().verify(token);
      return  decodedJWT;
   }
}
