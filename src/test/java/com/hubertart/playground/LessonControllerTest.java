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
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

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
}
