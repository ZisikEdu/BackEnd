package org.zisik.edu.flow.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlowMemo is a Querydsl query type for FlowMemo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowMemo extends EntityPathBase<FlowMemo> {

    private static final long serialVersionUID = -1247871492L;

    public static final QFlowMemo flowMemo = new QFlowMemo("flowMemo");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> flowSessionId = createNumber("flowSessionId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pageNumber = createNumber("pageNumber", Integer.class);

    public QFlowMemo(String variable) {
        super(FlowMemo.class, forVariable(variable));
    }

    public QFlowMemo(Path<? extends FlowMemo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlowMemo(PathMetadata metadata) {
        super(FlowMemo.class, metadata);
    }

}

