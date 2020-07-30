package com.example.demodb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class TesteRest {

    @Autowired
    StudentRepository sr;

    @Autowired
    CourseRepository cr;

    @GetMapping(value = {"2", "index2"})
    public String index2(HttpSession session) {
        sr.findAll().forEach(st -> {
                    st.getLikedCourses().add(cr.findById(st.getId()).get());
                    sr.save(st);
                }
        );
        return "teste 2 feito ...";
    }

    @GetMapping(value = {"3", "index3"})
    public String index3(HttpSession session) {
        sr.findAll().forEach(st -> {
                    if (st.getId() < 4) {
                        st.getLikedCourses().add(cr.findById(st.getId()+1L).get());
                        sr.save(st);
                    }
                }
        );
        return "teste 2 feito ...";
    }


    @GetMapping(value = {"", "index"})
    public String index(HttpSession session) {

        Set<Student> students = new LinkedHashSet<Student>();

        students.add(new Student(1L, "Edison 1", new LinkedHashSet<>()));
        students.add(new Student(2L, "Edison 2", new LinkedHashSet<>()));
        students.add(new Student(3L, "Edison 3", new LinkedHashSet<>()));
        students.add(new Student(4L, "Edison 4", new LinkedHashSet<>()));

        Set<Course> courses = new LinkedHashSet<>();
        courses.add(new Course(1L, "Curso 1", new LinkedHashSet<Student>()));
        courses.add(new Course(2L, "Curso 22", new LinkedHashSet<Student>()));
        courses.add(new Course(3L, "Curso 333", new LinkedHashSet<Student>()));
        courses.add(new Course(4L, "Curso 4444", new LinkedHashSet<Student>()));

        sr.saveAll(students);
        cr.saveAll(courses);
/*
        courses.stream().filter(i -> i.getId() == 1).
                forEach(i -> i.getLikes().
                        add(new Student(1L,null,null)));

        courses.stream().filter(i -> i.getId() == 2).
                forEach(i -> i.getLikes().
                        addAll(students.stream().
                                filter(ii -> ii.getId() == 2L).
                                collect(Collectors.toList())));

        courses.stream().filter(i -> i.getId() == 3).
                forEach(i -> i.getLikes().
                        addAll(students.stream().
                                filter(ii -> ii.getId() == 3L).
                                collect(Collectors.toList())));


        sr.saveAll(students);
        cr.saveAll(courses); */

/*        students.stream().filter(i -> i.getId() == 1L).forEach(i -> i.getLikedCourses().add(new Course(1L, "Curso 11", new LinkedHashSet<>(Arrays.asList(i)))));
        students.stream().filter(i -> i.getId() == 2L).forEach(i -> i.getLikedCourses().add(new Course(2L, "Curso 22", new LinkedHashSet<>(Arrays.asList(i)))));
        students.stream().filter(i -> i.getId() == 3L).forEach(i -> i.getLikedCourses().add(new Course(3L, "Curso 33", new LinkedHashSet<>(Arrays.asList(i)))));
        students.stream().filter(i -> i.getId() == 4L).forEach(i -> i.getLikedCourses().add(new Course(4L, "Curso 444", new LinkedHashSet<>(Arrays.asList(i))))); */


        return "Teste feito";
    }

}
