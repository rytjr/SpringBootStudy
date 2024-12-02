package com.metabuild.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이징 처리 및 검색 기능을 모듈화해서 재사용 가능하도록 처리하는 객체
@Setter
@Getter
@ToString
public class PagingDTO {

    //페이징 처리 관련 프러퍼티
    private int pageNum; //현재 보여줄 페이지 번호(1,2,3,4)
    private int pageSize; //한 페이지 당 보여줄 목록 개수 (한 페이지에 5개씩 목록 보여주기)
    private int totalCount;  //총 게시글 수 또는 검색한 게시글 수(DB에서 가져와 설정)
    private int pageCount; //총 페이지 수(totalCount와 PageSize와의 연산으로 설정)

    //DB에서 끊어오기 위한 프로퍼티(oracle일 경우)
    private int start;
    private int end;

    //DB에서 끊어오기 위한 프로퍼티(MySQL일 경우)
    private int offset;
    private int limit;

    //페이징 블럭 처리 위한 프로퍼티
    private int pagingBlock = 5; //한 블럭당 보여줄 페이지 수
    private int prevBlock; //이전 5개 블럭
    private int nextBlock; //이휴 5개 블럭

    //검색 관련 프로프티
    private String findType = ""; //검색 유형(ex 작성자,글내용,글제목)
    private String findKeyword = ""; //검색어(빈 문자열로 초기화하자. 검색하지 않을 경우 null갖ㅅ 들어오는 것 방지)

    public void init(){
        //페이지 수 구하기 둘다 같은 공식 확인 하려고 두개 씀
        pageCount = (totalCount - 1) / pageSize + 1;
//        System.out.println("pageCount1" + pageCount);
//        pageCount = (int)Math.ceil((double)totalCount / pageSize);
//        System.out.println("pageCount2" + pageCount);
        if(pageNum < 1) {
            pageNum = 1;  //1페이지를 디폴트로 설정
        }
        if(pageNum > pageCount) {
            pageNum = pageCount;  //마지막 페이지로 설정
        }

        //DB에서 끊어올 값 구하기(orcle의 경우)
        start = (pageNum - 1) * pageSize;
        end = start + (pageSize + 1);
        /*select * from(
        select rownum rn, a.* from
        (select * from board order by id desc) a
        )
        where rn > 0 and rn < 6*/

        prevBlock = (pageNum - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + pagingBlock + 1;
    }

    /**페이지 네비게이션 문자열을 반환하는 메서드
     * myctx: 컨텍스트명(/)
     * loc : board/list
     * */
    public String getPageNavi(String myctx, String loc) {
        String link=myctx+"/"+loc; // "board/list"
        String qStr="?findType="+findType+"&findKeyword="+findKeyword;
        link+=qStr;
        //link===> "/board/list?findType=1&findKeyword=abc"
        StringBuilder buf=new StringBuilder();
        buf.append("<ul class='pagination justify-content-center'>");

        if(prevBlock>0) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+prevBlock+"'>")
                    .append("Prev")
                    .append("</a>")
                    .append("</li>");
        }
        for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount  ;i++) {
            String css=(i==pageNum)?"active":"";
            buf.append("<li class='page-item "+css+"'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+i+"'>")
                    .append(i)
                    .append("</a>")
                    .append("</li>");

        }//for------
        if(nextBlock <= pageCount) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+nextBlock+"'>")
                    .append("Next")
                    .append("</a>")
                    .append("</li>");
        }
        buf.append("</ul>");
        return buf.toString();
    }//----------------------------------------------
}
