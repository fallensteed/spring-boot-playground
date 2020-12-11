package com.hubertart.playground;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LessonRepository extends CrudRepository<Lessons, Long> {
    Lessons findByTitle(String title);

    @Query("SELECT a FROM Lessons a WHERE delivered_on BETWEEN ?1 AND ?2")
    Iterable<Lessons> findByDeliveredOn(String date1, String date2);
}
