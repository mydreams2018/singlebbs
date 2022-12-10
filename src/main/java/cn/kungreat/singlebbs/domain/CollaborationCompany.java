package cn.kungreat.singlebbs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date avtiveTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    private Boolean isActive;

    private String businessPeople;

    private String linkUrl;

    private Integer baseOrder;
}