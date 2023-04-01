package cn.kungreat.singlebbs.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailsTextQuery extends Paging{
    private Long id;
    private String replyIds;
    @JsonIgnore
    private String likeAccount;
    private Integer likeNumber;
    private Long portId;
    private Integer authFlag;
    private String orderType;
    private Long updateTime;
    private Integer classId;
    private String tableName;
    private Integer portIsauth;
    private String detailsText;
    private String beginTime;
    private String endTime;
    public String getTableName(){
        if(classId != null){
            switch (classId) {
                case 1 -> tableName = "details_text_back";
                case 2 -> tableName = "details_text_front";
                case 3 -> tableName = "details_text_data";
                case 4 -> tableName = "details_text_talk";
            }
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