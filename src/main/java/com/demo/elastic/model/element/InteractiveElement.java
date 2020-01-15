package com.demo.elastic.model.element;

import com.demo.elastic.model.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEMO_INTERACTIVE_ELEMENT")
@DiscriminatorValue("IE")
@Getter @Setter @NoArgsConstructor
public class InteractiveElement extends BaseElement {
    private static final long serialVersionUID = 2344930073215487874L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERSON_ID")
    private Person reviewer;
}
