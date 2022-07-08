package cn.kungreat.singlebbs.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {
    private boolean result = true;
    private String msg="success";
    private String action;
    private int status = 1;
    private String id;
    private String jwtToken;
    private String rememberMe;
    public JsonResult(boolean result, String msg,String path,int sta,String id) {
        this.result = result;
        this.msg = msg;
        this.action = path;
        this.status = sta;
        this.id = id;
    }
    public JsonResult(){}
}