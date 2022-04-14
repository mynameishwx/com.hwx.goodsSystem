package com.hwx.goodsSystem.Controller;

import com.hwx.goodsSystem.entity.commonResult;
import com.hwx.goodsSystem.entity.test;
import com.hwx.goodsSystem.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class enterCont {


    @Autowired
    private testService testService;

    @GetMapping("/test")
    public commonResult<List<test>> index(){
        commonResult<List<test>> result=new commonResult<>();
        List<test> listtest=testService.gettest();
        return  new commonResult<>(200,"查询成功",listtest);
    }
}
