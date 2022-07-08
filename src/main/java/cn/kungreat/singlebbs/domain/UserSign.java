package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class UserSign {
    private Long id;

    private String userAccount;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastSignTime;
    private Integer oldAccumulateSign;
    private Integer accumulateSign;
    private Boolean currentSign = false;
}