package com.example.confrence.controller;

import com.example.confrence.domain.History;
import com.example.confrence.service.HistoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("history/{pageNo}")
    public ModelAndView getAllData(@ModelAttribute("his") History history, @PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;
        Page<History> page = historyService.findAllHistoryByQuery(pageNo, pageSize);
        List<History> list = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("noOfPages", page.getTotalElements());
        model.addAttribute("list", list);
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("allHistoryList", list);
        return modelAndView;
    }

    @PostMapping("history/{id}/{pageNo}")
    public String deleteData(@PathVariable("id") Long id, @PathVariable(value = "pageNo") int pageNo) {
        Optional<History> byId = historyService.findById(id);
        History history = byId.get();
        history.setStatus(false);
        historyService.saveData(history);
        return "redirect:/history/" + pageNo;
    }

    @PostMapping("history/deleteAll")
    public String deleteAllData() {
        List<History> allHistory = historyService.findAllHistory();
        List<History> collect = new ArrayList<>();
        for (History history : allHistory) {
            history.setStatus(false);
            collect.add(history);
        }
        historyService.saveAllData(collect);
        return "redirect:/calculator";
    }
}
