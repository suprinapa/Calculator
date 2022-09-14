package com.example.confrence.dto;

import com.example.confrence.domain.Formula;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Component
public class FormulaResponseData implements Serializable {
    List<Formula> data;
}
