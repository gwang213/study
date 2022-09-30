package org.zerock.ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //별도의 화면없이 데이터를 json 타입으로 전송할 수 있다.
public class SampleController {

    @GetMapping("/hello")   //localhost/hello로 연결
    public String[] hello(){    //String 배열 타입으로 2개을 문자 값을 리턴 출력
        return new String[]{"Hello", "world"};
    }
}
