package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthLog {
    private Long id;

    private String authAccount;

    private Long portId;

    private Integer portType;

    private Long authDate;

    private Integer authFlag;

    private Integer classId;

}