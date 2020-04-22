package com.dw.pay.paystoreapi.web.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import response.ApiResult;

@Api(description = "demo")
@RestController
public class DemoController {

    /**
     *  此接口的用途
     * @return
     */
    @GetMapping(value = "/demo")
    @ApiOperation(value = "demo",notes = "demo")
    public ApiResult demo(){
        ApiResult apiResult = null;
        try {
            String data = "welcome";
            apiResult = new ApiResult(data);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(ApiResult.CODE_FAIL,e.getMessage());
        }
        return apiResult;
    }
}
