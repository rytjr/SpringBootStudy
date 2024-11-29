package com.metabuild.spring_mybatis.basic.ioc.ex3;

public class OracleDao implements Dao{

    public void daoInfo() {
        System.out.println("나는 Oracke DB와 연동합니다.");
    }
}
