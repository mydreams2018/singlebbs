package cn.kungreat.singlebbs.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserQuery extends Paging {
    private String category;
    private String alias;
    @JsonIgnore
    private String account;
//管理员表格自带的分页使用
    private Integer page;
    private Integer limit;
    private String startTime;
    private String endTime;
}
