package com.demo.elastic.model.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@DiscriminatorValue("EE")
@Getter @Setter @NoArgsConstructor
public class EducationElement extends BaseElement {
    private static final long serialVersionUID = -7949860657524802653L;

    @NotNull
    @Column(name = "outbound")
    private Boolean outbound = false;
}
