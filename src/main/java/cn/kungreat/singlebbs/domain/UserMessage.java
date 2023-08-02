package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class UserMessage {
    private Long id;

    private Integer classId;

    private Long portId;
    private String portTitle;
    private Long detailsId;

    private String detailsText;
    private String userAccount;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date viewDate;

    private Integer msgState;

    private Integer msgType;

    private String msgInfo;

    private Integer authFlag;

    private String userAlias;

}