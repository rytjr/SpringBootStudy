package com.metabuild.user.service;

import com.metabuild.user.domain.MemberDTO;
import com.metabuild.user.exception.NoMemberException;
import com.metabuild.user.mapper.MemberMapper;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberServiceImpl")
@Slf4j
public class MemberServiceImpl implements MemberSercive{

    @Inject
    private MemberMapper memberMapper;

    @Inject
    private PasswordEncoder bcryptEncoder;

    @Override
    public int insertMember(MemberDTO user) {
        //비밀번호 암호화
        String encodePassword = bcryptEncoder.encode(user.getPasswd());
        log.info("encodPassword==={}",encodePassword);
        user.setPasswd(encodePassword);   //암호화된 비번으로 설정

        //MemberMapper.xml에 id가 insertMember인 요소를 찾아감
        return memberMapper.insertMember(user);
    }

    @Override
    public boolean idCheck(String userId) {

        MemberDTO user = memberMapper.findByUserId(userId);
        //userId가 이미 있다면 ==> user객체 반환 없다면 null반환
        if(user == null) return true;   //userId사용 가능

        return false;    //userId 사용 불가
    }

    @Override
    public List<MemberDTO> listMember() {
        return memberMapper.listMember();
    }

    @Override
    public MemberDTO findByUserId(String userId) {
        return memberMapper.findByUserId(userId);
    }

    @Override
    public MemberDTO findByNo(int no) {
        return null;
    }

    @Override
    public int deleteMember(int no) {
        return memberMapper.deleteMember(no);
    }

    @Override
    public int updateMember(MemberDTO user) {
        return memberMapper.updateMember(user);
    }

    @Override
    public MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException{
        //userId로 회원정보 가져오기
        MemberDTO user = this.findByUserId(tmpUser.getUserId());
        if(user == null) throw new NoMemberException("아이디 또는 비밀번호가 일치하지 않아요");

        //아이디는 맞으나 비밀번호를 체크해야함
        String dbPwd = user.getPasswd();
        if(!bcryptEncoder.matches(tmpUser.getPasswd(), dbPwd)) {
            throw new NoMemberException("아이디 또는 비밀번호가 일치하지 않아요");
        }

        return user;
    }

    @Override
    public int getMemberCount() {
        System.out.println("memberMapper" + memberMapper);
        return memberMapper.getMemberCount();
        //MemberMapper.xml에 가서 id가 getMemberCount인 태그를 찾아
        //해당 sql문을 실행함
    }
}
