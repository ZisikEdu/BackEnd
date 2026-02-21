package org.zisik.edu.book.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManualBookCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    private String author;
    private String isbn;
    private String category;
    private String genre;
    private String imageUrl;
    private String description;
    private String publisher;
    private Integer totalPages;
}
