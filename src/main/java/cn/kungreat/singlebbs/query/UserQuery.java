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
    private String startTime;
    private String endTime;
    private String idList;

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
