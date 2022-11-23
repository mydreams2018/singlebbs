package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class DetailsText {
    private Long id;
//采纳
    private Boolean isAdoption=false;

    private Integer likeNumber;

    private Long portId;
    @JsonIgnore
    private String userAccount;
    private String likeAccount;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createData;
    private Long updateTime;
    private Integer authFlag;
    private String authDescribe;
    private String detailsText;
    private Boolean isPort;
    private Long replyParent;
    private List<DetailsText> childAnswers;
    //标记字段
    private Integer classId;
    private String tableName;
    private String alias;
    private String userImg;
    private Integer vipLevel;
    private String authenticate;
    private Byte isManager;
    private Integer portIsauth;
    public String validMessage(){
        if(StringUtils.isEmpty(detailsText) ||
                portId == null || classId == null){
            return "标题,类型,内容不能为空";
        }
        return null;
    }
    public String getTableName(){
        if(classId != null){
            switch (classId){
                case 1:
                    tableName= "details_text_back";
                    break;
                case 2:
                    tableName= "details_text_front";
                    break;
                case 3:
                    tableName= "details_text_data";
                    break;
                case 4:
                    tableName= "details_text_talk";
            }
        }
        return tableName;
    }
}