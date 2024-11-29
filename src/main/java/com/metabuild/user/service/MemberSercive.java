package com.metabuild.user.service;

import com.metabuild.user.domain.MemberDTO;
import com.metabuild.user.exception.NoMemberException;

import java.util.List;

public interface MemberSercive {

    //회원가입
    int insertMember(MemberDTO user);
    boolean idCheck(String userId);    //아이디 중복체크

    //회원목록
    List<MemberDTO> listMember();

    MemberDTO findByUserId(String userId);    //unique
    MemberDTO findByNo(int no);   //pk

    int deleteMember(int no);
    int updateMember(MemberDTO user);

    //로그인
    MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException;

    int getMemberCount();
}
