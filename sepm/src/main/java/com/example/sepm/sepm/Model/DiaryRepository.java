package com.example.sepm.sepm.Model;

import java.util.List;
import java.util.Optional;

import com.example.sepm.sepm.Model.data.Diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    Optional<Diary> findById(Integer id);

    List<Diary> findByAvailable(Integer available);
}