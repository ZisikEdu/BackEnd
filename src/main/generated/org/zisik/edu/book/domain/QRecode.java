package org.zisik.edu.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecode is a Querydsl query type for Recode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecode extends EntityPathBase<Recode> {

    private static final long serialVersionUID = 49432719L;

    public static final QRecode recode = new QRecode("recode");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final NumberPath<Integer> endPage = createNumber("endPage", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> readingTime = createTime("readingTime", java.time.LocalTime.class);

    public final DateTimePath<java.time.LocalDateTime> recodeDate = createDateTime("recodeDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> startPage = createNumber("startPage", Integer.class);

    public QRecode(String variable) {
        super(Recode.class, forVariable(variable));
    }

    public QRecode(Path<? extends Recode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecode(PathMetadata metadata) {
        super(Recode.class, metadata);
    }

}

