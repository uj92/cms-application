package com.example.sepm.sepm.Model;

import java.util.List;

import com.example.sepm.sepm.Model.data.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface feedbackRepository extends JpaRepository<Feedback, Integer>{
    
    List<Feedback> findByDiaryid(Integer diaryid);
}