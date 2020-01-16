package com.demo.elastic.search.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor @Getter @Setter
public class ElasticDocument implements Serializable {
    private static final long serialVersionUID = -6941253526507531954L;

    //Base element
    private UUID id;
    private String name;
    private String type;
    private String status;
    private Double zet;
    private LocalDate beginDate;
    private LocalDate endDate;

    //Programme element
    private Long cost;
    private Long govCost;
    private UUID orgId;

    //Interactive element
    private UUID reviewerId;

    //Education element
    private Boolean outbound;

}
