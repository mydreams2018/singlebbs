package cn.kungreat.singlebbs.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollaborationCompanyQuery extends Paging{
    private Boolean isActive;
    private String businessPeople;
    private Integer onlyStatus;
    private String beginTime;
    private String endTime;
}
