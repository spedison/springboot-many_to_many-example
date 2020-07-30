package com.example.demodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tb_teste_student", schema = "site")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
class Student {

    @Id
    Long id;

    String nome;

    @ManyToMany()
    @JoinTable(
            name = "tb_test_course_like",
            schema = "site",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> likedCourses;
}
