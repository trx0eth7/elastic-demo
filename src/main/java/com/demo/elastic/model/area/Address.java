package com.demo.elastic.model.area;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 9024653831876794054L;

    @NotNull
    @Column(name = "CODE")
    private String code;
}
