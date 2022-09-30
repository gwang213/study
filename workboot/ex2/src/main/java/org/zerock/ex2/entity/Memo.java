package org.zerock.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "tbl_memo")
@ToString   //문자변환
@Getter //게터 생성
@Builder    //빌더를 이용하여 객체를 생성
@AllArgsConstructor //빌더를 이용하기 위해서 생성자
@NoArgsConstructor  //@AllArgsConstructor, @NoArgsConstructor 필수
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
