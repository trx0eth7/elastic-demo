package com.demo.elastic.model.element;

import com.demo.elastic.model.org.Org;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DEMO_PROGRAMME_ELEMENT")
@DiscriminatorValue("PE")
@Getter @Setter @NoArgsConstructor
public class ProgrammeElement extends BaseElement {
    private static final long serialVersionUID = -4844944919398092553L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORG_ID")
    private Org org;

    @NotNull
    @Column(name = "COST")
    private Long cost;

    @NotNull
    @Column(name = "GOV_COST")
    private Long govCost;
}
