package com.hwx.goodsSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ErrorController {
    @RequestMapping("/Error")
    public String error(@RequestParam("code_message")String code_message, Map<String,String> map){
        String[] String1=code_message.split("_");
        map.put("code",String1[0]);
        map.put("message",String1[1]);
        return  "Error";
    }
}
