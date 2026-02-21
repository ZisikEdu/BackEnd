package org.zisik.edu.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commit {
    @Id
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "account_id")
    private Long accountId;
    private String contents;
}
