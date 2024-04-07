package com.lcq.tftgdb.controller;

import com.lcq.tftgdb.mapper.Browser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class mainController {
    @Autowired(required = false)
    private Browser browser;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("messages/index");
    }

    @GetMapping("browser")
    public ModelAndView browser() {
        return new ModelAndView("messages/browser");
    }

    @GetMapping("search")
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("messages/search");
        List<String> data = new ArrayList<>();
        data = browser.getFamilyData();
        data.add("ALL");
        mv.addObject("family", data);
        return mv;
    }

    @GetMapping("help")
    public ModelAndView help() {
        return new ModelAndView("messages/help");
    }

    @GetMapping("download")
    public ModelAndView Download() {
        return new ModelAndView("messages/Download");
    }

    @GetMapping("browserDetail")
    public ModelAndView browserDetail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/browserDetail");
        mv.addObject("symbol", request.getParameter("symbol"));
        mv.addObject("method", request.getParameter("method"));
        return mv;
    }

    @GetMapping("infoByGene")
    public ModelAndView browserDetailGene(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/browserDetailGene");
        mv.addObject("gene", request.getParameter("gene"));
        mv.addObject("symbol", request.getParameter("symbol"));
        return mv;
    }

    @GetMapping("geneAnalysis")
    public ModelAndView analysis(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/analysis");
        return mv;
    }

    @GetMapping("networkAnalysis")
    public ModelAndView networkAnalysis(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/networkAnalysis");
        return mv;
    }
    @GetMapping("networkAnalysisGene")
    public ModelAndView networkAnalysisGene(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/networkAnalysisGene");
        return mv;
    }
    @GetMapping("networkAnalysisVersion1")
    public ModelAndView networkAnalysisVersion1(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/networkAnalysis-version1");
        return mv;
    }

    @GetMapping("pathwayAnalysis")
    public ModelAndView pathwayAnalysis(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("messages/pathwayanalysis");
        return mv;
    }

}
