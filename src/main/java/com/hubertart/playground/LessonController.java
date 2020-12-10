package com.hubertart.playground;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    public LessonController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lessons> all(){
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lessons create(@RequestBody Lessons lessons){
        return this.repository.save(lessons);
    }

    @GetMapping("/{id}")
    public Optional<Lessons> findById(@PathVariable Long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
    }
}
