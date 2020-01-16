package com.demo.elastic.model.person;

import com.demo.elastic.model.area.Address;
import com.demo.elastic.model.element.InteractiveElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DEMO_PERSON")
@Getter @Setter @NoArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 4932375548261992983L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "CODE"))
    })
    private Address address;

    @OneToMany(mappedBy = "reviewer")
    private List<InteractiveElement> interactiveElements;

}
