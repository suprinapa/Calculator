package com.example.confrence.repository;

import com.example.confrence.domain.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaSpecificationExecutor<History>, JpaRepository<History,Long> {

    List<History> findByStatusOrderByIdDesc(boolean status);
    List<History> findByStatus(boolean status);
    List<History> findByOrderByIdDesc();
    @Query("SELECT h from History h where h.status=true order by h.id DESC")
    Page<History> findAllHistory(Pageable pageable);
}
