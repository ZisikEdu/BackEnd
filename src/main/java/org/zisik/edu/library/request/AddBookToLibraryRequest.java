package org.zisik.edu.library.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.zisik.edu.library.domain.ReadingStatus;

@Getter
@Setter
public class AddBookToLibraryRequest {

    @NotNull(message = "Book ID is required")
    private Long bookId;

    private ReadingStatus status = ReadingStatus.PLAN_TO_READ;
}
