package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lecturers")
public class Lecturer extends Person{

    @Column(unique = true,nullable = false)
    private String lecturerId;

    @OneToMany(mappedBy = "lecturer",fetch = FetchType.EAGER)
    @Getter(onMethod_ = @JsonIgnore)
    private List<Unit> units;
}
