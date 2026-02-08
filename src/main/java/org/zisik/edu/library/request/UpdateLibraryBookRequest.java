package org.zisik.edu.library.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.zisik.edu.library.domain.ReadingStatus;

@Getter
@Setter
public class UpdateLibraryBookRequest {

    private ReadingStatus status;

    @Min(value = 0, message = "Page number cannot be negative")
    private Integer currentPage;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer userRating;
}
