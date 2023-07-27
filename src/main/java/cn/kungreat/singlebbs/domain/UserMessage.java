package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class UserMessage {
    private Long id;

    private Integer classId;

    private Long portId;

    private Long detailsId;

    private String userAccount;

    private Date createDate;

    private Date viewDate;

    private Integer msgState;

    private Integer msgType;

    private String msgInfo;

    private Integer authFlag;

}