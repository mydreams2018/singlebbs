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
    private Boolean authFlag;
    private String orderType;
    private Integer classId;
    private String tableName;
    private Integer portIsauth;
    private String detailsText;
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
}