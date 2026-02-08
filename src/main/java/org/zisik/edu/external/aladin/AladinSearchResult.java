package org.zisik.edu.external.aladin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinSearchResult {

    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private String version;
    private String searchCategoryId;
    private String searchCategoryName;
    private List<AladinBookItem> item;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AladinBookItem {
        private String title;
        private String link;
        private String author;
        private String pubDate;
        private String description;
        private String isbn;
        private String isbn13;
        private String itemId;
        private int priceSales;
        private int priceStandard;
        private String mallType;
        private String stockStatus;
        private int mileage;
        private String cover;
        private String categoryId;
        private String categoryName;
        private String publisher;
        private int salesPoint;
        private boolean adult;
        private boolean fixedPrice;
        private int customerReviewRank;

        private SubInfo subInfo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubInfo {
        private String subTitle;
        private String originalTitle;
        private int itemPage;
    }
}
