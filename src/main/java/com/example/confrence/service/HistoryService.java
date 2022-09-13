package com.example.confrence.service;

import com.example.confrence.domain.History;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    List<History> findAllHistory();
    List<History> findByOrderByIdDesc();
    List<History> findByStatusOrderById(boolean status);
    Page<History> findAllHistoryByQuery(int pageNo, int pageSize);
    Page<History> pagination(int pageNo,int pageSize);
    Optional<History> findById(Long id);
    History saveData(History history);
    void deleteAllData(List<History> collect);

    void saveAllData(List<History> collect);
}

