package com.hubertart.playground;

import org.springframework.web.bind.annotation.*;

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

}
