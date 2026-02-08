package org.zisik.edu.home.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.home.domain.LiteraryCard;
import org.zisik.edu.home.domain.LiteraryType;

import java.time.LocalDate;

@Getter
@Builder
public class LiteraryCardResponse {

    private Long id;
    private LiteraryType type;
    private String content;
    private String author;
    private String source;
    private LocalDate displayDate;

    public static LiteraryCardResponse from(LiteraryCard card) {
        return LiteraryCardResponse.builder()
                .id(card.getId())
                .type(card.getType())
                .content(card.getContent())
                .author(card.getAuthor())
                .source(card.getSource())
                .displayDate(card.getDisplayDate())
                .build();
    }
}
