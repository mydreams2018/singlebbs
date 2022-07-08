package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReplyPort {
    private Long id;

    private String userAccount;

    private Integer replyYear;

    private Integer replyWeek;

    private Integer replyNumber;

    private String alias;
    private String userImg;
    private Integer vipLevel;
    private String authenticate;

}