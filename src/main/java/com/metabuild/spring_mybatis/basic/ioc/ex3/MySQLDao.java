package com.metabuild.spring_mybatis.basic.ioc.ex3;

public class MySQLDao implements Dao{

    public void daoInfo() {
        System.out.println("나는 MySQL과 연동해요");
    }
}
