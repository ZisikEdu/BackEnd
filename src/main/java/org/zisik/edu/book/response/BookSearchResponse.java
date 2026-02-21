package org.zisik.edu.book.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.external.aladin.AladinSearchResult;

import java.util.List;

@Getter
@Builder
public class BookSearchResponse {

    private int totalResults;
    private int page;
    private int size;
    private List<BookItem> items;

    @Getter
    @Builder
    public static class BookItem {
        private String itemId;
        private String title;
        private String author;
        private String publisher;
        private String pubDate;
        private String isbn;
        private String isbn13;
        private String cover;
        private String description;
        private String categoryName;
        private int priceStandard;
        private int customerReviewRank;
        private Integer itemPage;
    }

    public static BookSearchResponse from(AladinSearchResult result, int page, int size) {
        List<BookItem> items = result.getItem() == null ?
                List.of() :
                result.getItem().stream()
                        .map(item -> BookItem.builder()
                                .itemId(item.getItemId())
                                .title(item.getTitle())
                                .author(item.getAuthor())
                                .publisher(item.getPublisher())
                                .pubDate(item.getPubDate())
                                .isbn(item.getIsbn())
                                .isbn13(item.getIsbn13())
                                .cover(item.getCover())
                                .description(item.getDescription())
                                .categoryName(item.getCategoryName())
                                .priceStandard(item.getPriceStandard())
                                .customerReviewRank(item.getCustomerReviewRank())
                                .itemPage(item.getSubInfo() != null ? item.getSubInfo().getItemPage() : null)
                                .build())
                        .toList();

        return BookSearchResponse.builder()
                .totalResults(result.getTotalResults())
                .page(page)
                .size(size)
                .items(items)
                .build();
    }
}
