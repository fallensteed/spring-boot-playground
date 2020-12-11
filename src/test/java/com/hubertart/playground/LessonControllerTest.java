package com.hubertart.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testGetAllLessons() throws Exception {
        Lessons lesson = new Lessons();
        lesson.setTitle("Test Lesson");
        lesson.setDeliveredOn(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14,34).getTime());
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testPostALesson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, Object> lesson = new HashMap<String, Object>(){
            {
                put("title", "Test Lesson");
                put("delivered_on", new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14,34).getTime());
            }
        };

        String json = objectMapper.writeValueAsString(lesson);

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateALesson() throws Exception {
        Lessons lesson = new Lessons();
        lesson.setId(5L);
        lesson.setTitle("First Lesson");
        lesson.setDeliveredOn(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14,34).getTime());
        repository.save(lesson);

        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, Object> lessonUpdate = new HashMap<String, Object>(){
            {
                put("id", 5);
                put("title", "Updated First Lesson");
                put("delivered_on", "2020-12-09 14:34");
            }
        };

        String json = objectMapper.writeValueAsString(lessonUpdate);

        MockHttpServletRequestBuilder request = patch("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated First Lesson")));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindALessonByTitle() throws Exception {
        Lessons lesson = new Lessons();
        lesson.setTitle("SQL");
        lesson.setDeliveredOn(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14,34).getTime());
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons/find/SQL")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("SQL")));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindLessonsBetweenDates() throws Exception {
        Lessons lesson1 = new Lessons();
        lesson1.setTitle("SQL Part 1");
        lesson1.setDeliveredOn(new GregorianCalendar(2020, Calendar.OCTOBER, 9).getTime());
        repository.save(lesson1);
        Lessons lesson2 = new Lessons();
        lesson2.setTitle("SQL Part 2");
        lesson2.setDeliveredOn(new GregorianCalendar(2020, Calendar.NOVEMBER, 9).getTime());
        repository.save(lesson2);
        Lessons lesson3 = new Lessons();
        lesson3.setTitle("SQL Part 3");
        lesson3.setDeliveredOn(new GregorianCalendar(2020, Calendar.DECEMBER, 9).getTime());
        repository.save(lesson3);

        MockHttpServletRequestBuilder request = get("/lessons/between?date1=2020-10-15&date2=2020-12-25")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("SQL Part 2")))
                .andExpect(jsonPath("$[1].title", is("SQL Part 3")));
    }
}
