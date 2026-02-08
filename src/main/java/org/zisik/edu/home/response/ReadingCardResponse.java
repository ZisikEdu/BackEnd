package org.zisik.edu.home.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.library.response.UserLibraryResponse;

import java.util.List;

@Getter
@Builder
public class ReadingCardResponse {

    private List<UserLibraryResponse> currentlyReading;
    private int totalCount;

    public static ReadingCardResponse from(List<UserLibraryResponse> books) {
        return ReadingCardResponse.builder()
                .currentlyReading(books)
                .totalCount(books.size())
                .build();
    }
}
