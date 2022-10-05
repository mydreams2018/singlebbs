package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
@Setter
@Getter
public class Report {
    private Long id;

    private String name;
//飞吻
    private Byte experience;
    @JsonIgnore
    private String userAccount;
//加精
    private Boolean isEssence=false;

    private String portState;
//置顶
    private Boolean isTop=false;

    private Byte replyNumber;

    private Byte lookNumber;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Boolean authFlag;
    private String partitionName;
    private Integer classId;
    private String tableName;
    private String detailsText;
    private String alias;
    private String userImg;
    private Integer vipLevel;
    private String authenticate;
    private Byte isManager;
    private DetailsText details;
    private Integer portIsauth;

    public String validMessage(){
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(detailsText) || classId == null){
            builder.append("标题,类型,内容不能为空");
        }
        if(experience != null && experience < 0){
            builder.append("飞吻不能为负值");
        }
        return builder.toString();
    }

    public String getTableName() {
        if(classId != null){
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
        }
        return tableName;
    }
}