package cn.kungreat.singlebbs.domain;

import cn.kungreat.singlebbs.util.Patterns;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Setter
@Getter
public class User {
    private Long id;
    @JsonIgnore
    private String account;
    @JsonIgnore
    private String password;
    private String alias;
    private Long phone;
    private String img = "/api/userImg/default.jpg";
    private Byte state;
    private String email;
    private String description = "此人很懒,没有描述信息";
    private String originFrom = "default";
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registerTime;
    private Integer vipLevel = 0;
    private Integer registerYear;
    //积分
    private Integer accumulatePoints = 0;
    private Byte isManager = 0;
    private String fromCity;
    private String authenticate = "普通用户";
    private String rePass;

    public String validMessage() {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(account) || !Patterns.PASSWORD.matcher(account).matches()
                || StringUtils.isEmpty(password) || !Patterns.PASSWORD.matcher(password).matches()) {
            builder.append("账号或密码只能8-12位英文字母|数字|符号,");
        }
        if (StringUtils.isEmpty(alias) || !Patterns.USER_ALIAS.matcher(alias).matches()) {
            builder.append("别名只能2-12位,");
        } else if (alias.equals("游客")) {
            builder.append("别名不能用游客,");
        }
        return builder.toString();
    }

    public String validPass() {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(rePass) || !Patterns.PASSWORD.matcher(rePass).matches()) {
            builder.append("密码只能8-12位,英文字母|数字|符号");
        }
        return builder.toString();
    }

    public String getAlias() {
        return alias != null ? alias.trim() : "";
    }
}