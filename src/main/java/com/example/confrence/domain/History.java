package com.example.confrence.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String inputExpression;

    String outputExpression;

    Boolean status = true;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    Formula formula;
}
