package com.metabuild.board.controller;

import com.metabuild.board.domain.BoardDTO;
import com.metabuild.board.domain.PagingDTO;
import com.metabuild.board.service.BoardService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    //byName으로 주입 --> @Resource 객체 이름으로 주입
    @Resource(name = "boardServiceImpl")
    private BoardService boardService;

    @Value("${file.upload_dir}")
    private String uploadDir;    //c:/Lecture/devSource/upload
    //정적 리소스 매핑해야 한다. (webConfig클래스에서 매핑하자

    @GetMapping("/form")
    public String boardForm() {

        log.info("uploadDIr == {}",uploadDir);

        return "board/write";
    }

    @GetMapping("/list")
    public String boardList(Model model, PagingDTO paging) {

        //1. 총 게시글 수 가져오기
        int totalCount = boardService.getTotalCount(paging);
        //2. 게시글 가져오기
        List<BoardDTO> boardList = boardService.listBoard(paging);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("boardList",boardList);

        return "board/list";
    }

    @PostMapping("/write")
    public String boardWriteAndUpdate(BoardDTO dto, @RequestParam("mfile") MultipartFile mfile , Model model) {

        log.info("dto===={}, boardService---{}", dto,boardService);

        //1. 유효성 체크

        //2. 파일 업로드
        File dir = new File(uploadDir);
        if(!dir.exists()) {
            //해당 디렉토리가 없다면
            dir.mkdirs();   //디렉토리 생성
        }
        if(!mfile.isEmpty()) {
            //파일을 첨부했다면
            //첨부파일명 얻기
            String origin = mfile.getOriginalFilename();   //원본 파일명
            //물리적 파일명 만들기 "uuid_원본파일명.확장자"
            UUID uid = UUID.randomUUID();
            String fileName = uid + "_" + origin;
            log.info("fileName == {}",fileName);

            //첨부파일 크기 알아내기
            long fsize = mfile.getSize();
            dto.setOriginFileName(origin);
            dto.setFileName(fileName);
            dto.setFileSize(fsize);
            //업로드 처리 --> transferTo(파일객체)
            try {
                mfile.transferTo(new File(uploadDir, fileName));
                log.info("업로드 성공 : {}",origin);
            }catch(IOException ex) {
                log.error("파일 업로드 중 에러 : {}", ex.getMessage());
            }

            //예전에 업로드했던 파일이 있따면 서버에서 삭제 처리
            if("edit".equals(dto.getMode())) {   //수정 모드라면
                if(dto.getOldFile() != null) {
                    File tmp = new File(uploadDir, dto.getOldFile());
                    if(tmp.exists()) {
                        boolean b = tmp.delete();   //파일 삭제 처리
                        log.info("예전 첨부파일 삭제 여부 == {}" , b);
                    }
                }
            }
        }

        String str = "테스으";
        String loc = "list";
        int n = 0;
        //3. 글쓰기(mode = write) 또는 글수정(mode = edit)
        if(dto.getMode().equals("write")) {
            n = boardService.insertBoard(dto);
            str = (n > 0) ? "글쓰기 성공" : "글쓰기 실패";
            loc = (n > 0) ? "list" : "javascript:history.back()";
        } else if(dto.getMode().equals("edit")) {
            n = boardService.updateBoard(dto);  //수정 처리
            str = (n > 0) ? "글 수정 성공" : "글 수정 실패";
            loc = (n > 0) ? "list" : "javascript:history.back()";
        }


        model.addAttribute("message",str);
        model.addAttribute("loc",loc);
        return "message";
    }

    @GetMapping("/view")
    public String boardView(Model model, @RequestParam(defaultValue = "0") int id) {
        if(id == 0) {
            return "redirect:list";
        }
        //1, 조회수 증가
        int n = boardService.updateReadNum(id);

        //2. 해당 글 가져오기
        BoardDTO boardDTO = boardService.findById(id);

        model.addAttribute("board", boardDTO);

        return "board/view";
    }

    @PostMapping("/edit")
    public String editForm(BoardDTO dto, Model model){   //글번호, 비밀번호
        int id = dto.getId();   //수정할 글 번호
        String pwd = dto.getPwd();  //글 비밀번호
        if(id == 0 || pwd == null) {
            return "redirect:/board/list";
        }

        BoardDTO board = boardService.findById(id);
        if(board == null) {

            model.addAttribute("message", id + "번 글은 없습니다.");
            model.addAttribute("loc","list");

            return "message";
        }

        //비밀번호 체크
        if(!board.getPwd().equals(dto.getPwd())) {

            model.addAttribute("message","글 비밀번호가 일지하지 않아요");
            model.addAttribute("loc","javascript:history.back()");

            return "message";
        }
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping("/delete")
    public String deleteForm(BoardDTO dto, Model model) {

        int id = dto.getId();

        BoardDTO board = boardService.findById(id);

        if(board.getPwd().equals(dto.getPwd())) {
            model.addAttribute("message", "비밀번호가 올바르지 않습니다.");
            model.addAttribute("loc","javascript:history.back()");
            return "message";
        }

        if(dto.getFileName() != null) {
        }

        int n = boardService.deleteBoard(id);

        return "/board/list";
    }

}
