package cn.kungreat.singlebbs.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMessageQuery {
    private String alias;
    private Integer classId;
    private Long detailsId;
    private Long portId;
    private Integer deleteNum;
}
