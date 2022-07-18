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
    private Integer classId;
    private String ids;
    private String tableName;
//管理员表格自带的分页使用
    private Integer page;
    private Integer limit;
    private String startTime;
    private String endTime;

    public String getTableName(){
        switch (classId){
            case 1:
                tableName= "report_back";
                break;
            case 2:
                tableName= "report_front";
                break;
            case 3:
                tableName= "report_data";
                break;
            case 4:
                tableName= "report_talk";
        }
        return tableName;
    }
}
