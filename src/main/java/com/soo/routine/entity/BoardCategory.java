package com.soo.routine.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BoardCategory {

    NOTICE("CATEGORY_NOTICE", "공지사항"),
    FAQ("CATEGORY_FAQ", "FAQ"),
    QNA("CATEGORY_QNA", "QnA");

    private final String key;
    private final String value;

}
