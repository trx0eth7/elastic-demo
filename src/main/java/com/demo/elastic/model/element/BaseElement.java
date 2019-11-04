package com.demo.elastic.model.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ELEMENT")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BE")
@Getter @Setter @NoArgsConstructor
public abstract class BaseElement implements Serializable {
    private static final long serialVersionUID = -8021925067977489430L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "STATE")
    private ElementStateEnum state;

    @NotNull
    @Column(name = "ZET", nullable = false)
    private Double zet = 0D;

    @NotNull
    @Column(name = "LIKES", nullable = false)
    private Integer likes = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_DATE")
    protected Date beginDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    protected Date endDate;
}
