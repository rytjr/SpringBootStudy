package com.metabuild.user.exception;

//사용자 정의 예외
//1, Exception을 상속 받는다
//2. 생성자를 오버로드해서 생성자 안에서 super("예외메시지")호출
public class NoMemberException extends Exception {

    public NoMemberException() {
        super("회원이 아닙니다");   //예외 메시지로 등록된다
    }

    public NoMemberException(String msg) {
        super(msg);
    }

}
