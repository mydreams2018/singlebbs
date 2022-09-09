package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class CollaborationCompany {
    private Integer id;

    private Integer onlyStatus;

    private String companyImages;

    private String describe;

    private Date avtiveTime;

    private Date endTime;

    private Boolean isActive;

    private String businessPeople;

    private String linkUrl;
}