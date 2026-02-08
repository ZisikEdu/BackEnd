package org.zisik.edu.external.aladin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.zisik.edu.exception.AladinApiException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AladinApiClient {

    private final AladinApiConfig config;
    private final WebClient.Builder webClientBuilder;

    public AladinSearchResult searchBooks(String query, int page, int size) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                    .path("/ItemSearch.aspx")
                    .queryParam("ttbkey", config.getTtbKey())
                    .queryParam("Query", query)
                    .queryParam("QueryType", "Keyword")
                    .queryParam("MaxResults", size)
                    .queryParam("start", page)
                    .queryParam("SearchTarget", "Book")
                    .queryParam("output", "js")
                    .queryParam("Version", "20131101")
                    .queryParam("Cover", "Big")
                    .build()
                    .toUriString();

            log.debug("Aladin API Search URL: {}", url);

            return webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(AladinSearchResult.class)
                    .block();

        } catch (Exception e) {
            log.error("Aladin API search error: {}", e.getMessage(), e);
            throw new AladinApiException("알라딘 API 검색 중 오류가 발생했습니다.", e);
        }
    }

    public AladinSearchResult getBookByIsbn(String isbn) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                    .path("/ItemLookUp.aspx")
                    .queryParam("ttbkey", config.getTtbKey())
                    .queryParam("itemIdType", "ISBN13")
                    .queryParam("ItemId", isbn)
                    .queryParam("output", "js")
                    .queryParam("Version", "20131101")
                    .queryParam("Cover", "Big")
                    .queryParam("OptResult", "subInfo")
                    .build()
                    .toUriString();

            log.debug("Aladin API Lookup URL: {}", url);

            return webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(AladinSearchResult.class)
                    .block();

        } catch (Exception e) {
            log.error("Aladin API lookup error: {}", e.getMessage(), e);
            throw new AladinApiException("알라딘 API 조회 중 오류가 발생했습니다.", e);
        }
    }
}
