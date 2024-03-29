package cn.kungreat.singlebbs.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportQuery extends Paging{
    private String portState;
    private Boolean isEssence;
    private String orderType;
    private Integer classId;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String userAccount;
    private Integer portIsauth;
    private String partitionName;
    private String tableName;
    private Boolean isTop;
    private String beginTime;
    private String endTime;
    private Integer authFlag;
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

    public String getOrderType() {
        if(orderType != null){
            return OrderFiled.ORDER_VALUES.contains(orderType)?orderType:null;
        }
        return null;
    }
}