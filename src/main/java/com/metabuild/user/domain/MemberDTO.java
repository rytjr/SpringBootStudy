package com.metabuild.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {

    private int no;
    private String name;
    private String userId;
    private String passwd;
    private String hp1;
    private String hp2;
    private String hp3;
    private java.sql.Date indate;

    public String getAllHp() {
        return hp1 + "-" + hp2 + "-" + hp3;
    }
}
