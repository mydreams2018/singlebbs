package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Setter
@Getter
public class User{
    private Long id;
    @JsonIgnore
    private String account;
    @JsonIgnore
    private String password;
    private String alias;
    private Long phone;
    private String img="/api/userImg/default.jpg";
    private Byte state;
    private String email;
    private String description;
    private String originFrom="default";
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registerTime;
    private Integer vipLevel=0;
    private Integer registerYear;
    //积分
    private Integer accumulatePoints=0;
    private Byte isManager=0;
    private String fromCity;
    private String authenticate;
    private String rePass;

    public String validMessage(){
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isEmpty(account) || account.getBytes().length < 6 || account.getBytes().length > 12
            || StringUtils.isEmpty(password) || password.getBytes().length < 6 || password.getBytes().length > 12){
            builder.append("用户密码必须6-12位,");
        }
        if(StringUtils.isEmpty(alias)){
            builder.append("别名不能为空,");
        }else if(alias.length() > 6){
            builder.append("别名只能6位,");
        }else if(alias.equals("游客")){
            builder.append("别名不能用游客,");
        }
        byte[] bytes = account.getBytes();
        byte[] ps = password.getBytes();
        for(int x =0;x<bytes.length;x++){
            if(bytes[x] > 47 && bytes[x] < 58
                    || bytes[x] > 96 && bytes[x] < 123){
            }else{
                builder.append("账户只能是小写字母和数字,");
                break;
            }
        }
        for(int x =0;x < ps.length; x++){
            if(ps[x] > 47 && ps[x] < 58
                    || ps[x] > 96 && ps[x] < 123 || ps[x] > 64 && ps[x] < 91){
            }else{
                builder.append("密码只能是字母和数字");
                break;
            }
        }
        return builder.toString();
    }

    public String validPass(){
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isEmpty(rePass) || rePass.getBytes().length < 6 || rePass.getBytes().length > 12){
            builder.append("密码必须6-12位,");
        }
        byte[] ps = rePass.getBytes();
        for(int x =0;x < ps.length; x++){
            if(ps[x] > 47 && ps[x] < 58
                    || ps[x] > 96 && ps[x] < 123 || ps[x] > 64 && ps[x] < 91){
            }else{
                builder.append("密码只能是字母和数字");
                break;
            }
        }
        return builder.toString();
    }

    public String getAlias(){
        return alias!=null?alias.trim(): null;
    }
}