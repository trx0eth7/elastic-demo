package com.demo.elastic.model.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElementStateEnum {
    FORMING("forming"),
    REVIEW("review"),
    APPROVE("approve"),
    REJECT("reject"),
    ARCHIVE("archive");

    private String state;

}
