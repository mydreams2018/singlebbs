package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class UserCollect {
    private Long id;

    private String userAccount;

    private Integer classId;

    private Long portId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date collectTime;

    private String portTitle;
}