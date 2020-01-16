package com.demo.elastic.model.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DEMO_ELEMENT")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BE")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public abstract class BaseElement implements Serializable {
    private static final long serialVersionUID = -8021925067977489430L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 1000)
    private String name;

    @NotNull
    @Column(name = "STATE")
    private ElementStateEnum state;

    @NotNull
    @Column(name = "ZET", nullable = false)
    private Double zet = 0D;

    @Column(name = "BEGIN_DATE")
    protected LocalDate beginDate;

    @Column(name = "END_DATE")
    protected LocalDate endDate;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany
    @JoinTable(name = "DEMO_ELEMENT_TAG_LINK",
            joinColumns = @JoinColumn(name = "ELEMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ELEMENT_TAG_ID"))
    protected List<ElementTag> elementTags = new ArrayList<>();
}
