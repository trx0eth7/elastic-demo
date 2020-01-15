package com.demo.elastic.model.org;

import com.demo.elastic.model.area.Address;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "DEMO_ORG")
@Getter @Setter @NoArgsConstructor
public class Org {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LEVEL", nullable = false)
    private Integer level = 1;

    @Column(name = "REGISTER_DATE")
    private LocalDate registerDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "CODE"))
    })
    protected Address address;
}
