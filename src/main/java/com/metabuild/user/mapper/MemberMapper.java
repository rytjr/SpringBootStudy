package com.metabuild.user.mapper;

import com.metabuild.user.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* xxxMapper 인터페이스를 구성하면
* 해당 인터페이스를 구현한 개체를 스프링 컨테이너가 알아서 제공한다
* MapperProxy 객체
* application.properties에 mapper파일(xml)이 위치한 곳을 찾아서 해당 mapper파일(xml)을 읽어서 실행시킨다.
*
* 1.com.metabuild.user.mapper.MemberMapper라는 네임스페이스를 갖는 Mapper 파일을 찾는다.
* 2.MemberMapper가 가지고 있는 추상메서드명을 id로 갖는 엘리먼트를 찾아서 실행시킨다,
* */

@Mapper
public interface MemberMapper {

    int insertMember(MemberDTO user);   //회원등록
    int getMemberCount();    //전체 회원수
    List<MemberDTO> listMember();    //회원목록
    int deleteMember(int no);


    MemberDTO findByUserId(String userId);
}
