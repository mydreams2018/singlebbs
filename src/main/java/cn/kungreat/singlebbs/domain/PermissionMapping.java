package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionMapping {
    private Long id;
    private String account;
    private String descript;
}