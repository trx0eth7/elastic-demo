package com.demo.elastic.model.element;

import com.demo.elastic.model.person.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@DiscriminatorValue("IE")
@Getter @Setter @NoArgsConstructor
public class InteractiveElement extends BaseElement {
    private static final long serialVersionUID = 2344930073215487874L;

    @OneToMany(mappedBy = "interactiveElement")
    private List<Person> reviews;
}
