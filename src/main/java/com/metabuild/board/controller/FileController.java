package com.metabuild.board.controller;

import com.metabuild.board.domain.BoardDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@Slf4j
public class FileController {

    @Value("${file.upload_dir}")
    private String uploadDir;

    /*ResponseEntity 타입: 데이터와 함께 헤더 상태 메시지를 전달하고자 할 때 사용함.
     * Http헤더를 다뤄야 할 경우 ResponseEntity를 통해 헤더 정보나 데이터를 전달할 수 있다.
     * HttpEntity를 상속받아 구현한 클래스
     *   RequestEntity
     *   ResponseEntity (HttpStatus, HttpHeaders, HttpBody를 포함한다)
     * */

    //appilcation/octet-stream 설정하면 응용프로그램 형식으로 브라우저가 인식하여 다운로드를 시킬려고함
    @PostMapping(value="/fileDownload", produces = "application/octet-stream")
    @ResponseBody   //응답 데이터만 보내고자 할 때
    public ResponseEntity<Resource> download(BoardDTO dto, @RequestHeader("User-Agent") String userAgent) {
        log.info("fileName=={}, originFileName == {}",dto.getFileName(),dto.getOriginFileName());
        String filepath = uploadDir + "/" + dto.getFileName();   //다운로드할 파일의 절대경로
        log.info("filepath == {}",filepath);
        org.springframework.core.io.Resource resource = new FileSystemResource(filepath);
        //FileStreamResource가 알아서 파일과 스트림 연결을 한다
        if(!resource.exists()) {
            //해당 파일이 없다면
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);   //404 응답을 보낸다
        }
        // 브라우저별 인코딩 처리
        boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);

        String downName = null;  //파일명 인코딩 처리해서 받을 예정
        try {
            if(checkIE) {  //IE
                downName = URLEncoder.encode(dto.getOriginFileName(), "UTF-8").replaceAll("\\+", " ");
            } else {
                downName = new String(dto.getOriginFileName().getBytes("UTF-8"), "ISO-8859-1");
            }
        }catch (UnsupportedEncodingException ex) {
            log.error("에러 : " + ex.getMessage());
        }


        // HttpHeaders객체 통해서 헤더 정보 설정
        // 응답 헤더에 첨부파일명을 기술하면 해당 파일명으로 다운로드창을 실행시킨다
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename = " + downName);  //원본 파일명
        //파일 다운로드 창에 원본파일명이 디폴트로 나온다

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
