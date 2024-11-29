function check(){
    let uid=document.querySelector('#userId');
    let upw=document.querySelector('#passwd');
    let upw2=document.querySelector('#passwd2');
    let uname=document.querySelector('#name');
    //아이디 4-12자리의 영어와 숫자
    let pattern = /^[a-zA-Z0-9]{4,12}$/;
    if(! pattern.test(uid.value)){
        //정규식 패턴에 부합하면 true를 반환, 그렇지 않으면 false를 반환
        alert("아이디는 영어,숫자 포함 4-12자리 이내여야 해요");
        uid.select();
        return false;
    }

    let pattern2 = /^[\w]{4,8}$/;
    if(! pattern2.test(upw.value)){
        alert("비밀번호는 4-8자리 이내여야 해요")
        upw.select();
        return false;
    }

    if(upw.value != upw2.value) {
        alert('비밀번호가 다릅니다.');
        upw2.select();
        return false;
    }

    if(! uname.value){
        alert("이름을 입력하세요");
        uname.focus();
        return false;
    }

    return true;
}
function openWin(){
    //window.open(url,target,options)
    let url = "/idCheck";
    let win = open(url, "idCheck", "width=400px, height = 400px")

    //id 중복체크 팝업창 열기

}