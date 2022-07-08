package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class UserMessage {
    private Long id;

    private Integer classId;

    private Long portId;

    private Long detailsId;
    private String srcAlias;

    private String receiveAlias;
    private Set<String> receiveAliasSet;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date receiveDate;

    private Boolean authFlag;
}