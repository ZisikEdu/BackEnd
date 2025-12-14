package org.zisik.edu.book.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    private Long id;
    private String isbn;
    private String title;                   //제목
    private String author;                  //저자
    private String category;                //장르
    private String imageUrl;                //대표사진
    private String description;             //설명
    private String publisher;               //출판사
    private LocalDateTime publishedDate;    //출간일
    private String totalPages;              //총 페이지수
    private int rating;                     //평점
    private List<String> chapters;          //챕터

    public void validate() {

    }
}
