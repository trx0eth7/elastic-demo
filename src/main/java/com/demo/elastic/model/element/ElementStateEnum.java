package com.demo.elastic.model.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum ElementStateEnum {
    FORMING("forming"),
    REVIEW("review"),
    APPROVE("approve"),
    REJECT("reject"),
    ARCHIVE("archive");

    private String state;

    public static ElementStateEnum getRandomState(){
        return values()[new Random().nextInt(values().length)];
    }

}
