package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Permission {
    private Integer id;
    private String permissionMethods;

    private String descript;

}