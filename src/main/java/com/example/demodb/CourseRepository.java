package com.example.demodb;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    public Optional<Course> findById(Long id);
}
