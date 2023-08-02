package cn.kungreat.singlebbs.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMessageQuery extends Paging {
    private String userAccount;
    private Integer classId;
    private Long detailsId;
    private Long portId;
    private Integer msgState;
    private Long id;
    private Integer authFlag;

}
