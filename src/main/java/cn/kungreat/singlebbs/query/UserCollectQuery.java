package cn.kungreat.singlebbs.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCollectQuery extends Paging{
    private String userAccount;
    private String beginTime;
    private String endTime;
    private String name;
    private String ids;
}
