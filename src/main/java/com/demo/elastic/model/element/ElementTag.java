package com.demo.elastic.model.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DEMO_ELEMENT_RULE")
@Getter @Setter @NoArgsConstructor
public class ElementTag {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "NAME")
    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany
    @JoinTable(name = "DEMO_ELEMENT_TAG_LINK",
            joinColumns = @JoinColumn(name = "ELEMENT_TAG_ID"),
            inverseJoinColumns = @JoinColumn(name = "ELEMENT_ID"))
    private List<BaseElement> baseElements = new ArrayList<>();
}
