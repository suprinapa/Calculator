package com.example.confrence.service.impl;

import com.example.confrence.domain.History;
import com.example.confrence.repository.HistoryRepository;
import com.example.confrence.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

//    @Override
//    public List<History> findAllHistory() {
//        return historyRepository.findAll(new Specification<History>() {
//            @Override
//            public Predicate toPredicate(Root<History> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("status"), true);
//            }
//        }, Sort.by(Sort.Direction.DESC, "id"));
//    }

    public Optional<History> findById(Long id) {
       return historyRepository.findById(id);
    }

    public History saveData(History history) {
        return historyRepository.save(history);
    }
    @Override
    public void deleteAllData(List<History> collect) {
        historyRepository.deleteAll();
    }

    @Override
    public void saveAllData(List<History> historyList) {
        historyRepository.saveAll(historyList);
    }

    @Override
    public List<History>    findAllHistory() {
        return historyRepository.findAll();
    }

    public List<History> findByOrderByIdDesc(){
        return historyRepository.findByOrderByIdDesc();
    }

    @Override
    public List<History> findByStatusOrderById(boolean status) {
        return historyRepository.findByStatusOrderByIdDesc(status);
    }

    @Override
    public Page<History> findAllHistoryByQuery(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return historyRepository.findAllHistory(pageable);
    }

    @Override
    public Page<History> pagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return historyRepository.findAll(pageable);
    }

}
