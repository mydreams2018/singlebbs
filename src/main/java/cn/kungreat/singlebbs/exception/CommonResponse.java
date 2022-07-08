package cn.kungreat.singlebbs.exception;

import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *实现统一响应
 * */
@RestControllerAdvice(value = "cn.kungreat.singlebbs")
public class CommonResponse implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行处理 true false 
     * */
    @Override
    public boolean supports(MethodParameter methodParameter, 
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
/*
* 响应之前的一个处理
*/
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if(o == null){
            o = new JsonResult(false,"error","",0,"");
        }
        return o;
    }
}