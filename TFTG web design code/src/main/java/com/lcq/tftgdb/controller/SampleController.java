package com.lcq.tftgdb.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lcq.tftgdb.mapper.Search;
import com.lcq.tftgdb.pojo.*;
import com.lcq.tftgdb.until.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {
    @Autowired(required = false)
    private Search search;

    @RequestMapping("sampleid")
    @ResponseBody
    public ModelAndView sampleid(HttpServletRequest request) throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        String sampleid = request.getParameter("sampleid");
        TF_chipseq_infomation data = new TF_chipseq_infomation();
        data = search.getSampleInfoBySampleID(sampleid);
        mv.setViewName("messages/SampleInfo");
        mv.addObject("sampleinfo", data);
        mv.addObject("sampleid", sampleid);
        return mv;
    }

    @RequestMapping("samplegraph")
    @ResponseBody
    public Map samplegraph(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();
        String sampleid = request.getParameter("sampleid");
        List<GraphChr> data = new ArrayList<>();
        System.out.println(sampleid);
        try {
            data = search.getSampleGraphBySampleID(sampleid);
        } catch (Exception e) {
            GraphChr gc = new GraphChr();
            data.add(gc);
        }
        map.put("data", data);
        return map;
    }

    @RequestMapping("distancegraph")
    @ResponseBody
    public Map distancegraph(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();
        String sampleid = request.getParameter("sampleid");
        List<Datadistance> data = new ArrayList<>();
        Label label = new Label();
        DecimalFormat df = new DecimalFormat("0.00");
        int all = search.getDistance(sampleid);
        int kb01 = search.getDistance1Kb(sampleid);
        int kb03 = search.getDistance3Kb(sampleid);
        int kb05 = search.getDistance5Kb(sampleid);
        int kb10 = search.getDistance10Kb(sampleid);
        int kb100 = search.getDistance100Kb(sampleid);
        int kb100a = search.getDistance100aKb(sampleid);
        int kb01_ = search.getDistance1Kb_(sampleid);
        int kb03_ = search.getDistance3Kb_(sampleid);
        int kb05_ = search.getDistance5Kb_(sampleid);
        int kb10_ = search.getDistance10Kb_(sampleid);
        int kb100_ = search.getDistance100Kb_(sampleid);
        int kb100a_ = search.getDistance100aKb_(sampleid);
        Datadistance disdata = new Datadistance();
        disdata.setName("0-1kb");
        //            disdata.setLabel(label);
        float[] fkb01 = new float[1];
        fkb01[0] = Float.parseFloat(df.format((float) kb01 / all));
        disdata.setData(fkb01);
        disdata.setStack("总量");
        disdata.setType("bar");
        data.add(disdata);

        Datadistance disdata2 = new Datadistance();
        disdata2.setName("0-1kb");
        //            disdata2.setLabel(label);
        float[] fkb01_ = new float[1];
        fkb01_[0] = -Float.parseFloat(df.format((float) kb01_ / all));
        disdata2.setData(fkb01_);
        disdata2.setStack("总量");
        disdata2.setType("bar");
        data.add(disdata2);

        Datadistance disdata3 = new Datadistance();
        disdata3.setName("1-3kb");
        //            disdata3.setLabel(label);
        float[] fkb03 = new float[1];
        fkb03[0] = Float.parseFloat(df.format((float) kb03 / all));
        disdata3.setData(fkb03);
        disdata3.setStack("总量");
        disdata3.setType("bar");
        data.add(disdata3);

        Datadistance disdata4 = new Datadistance();
        disdata4.setName("1-3kb");
        //            disdata4.setLabel(label);
        float[] fkb03_ = new float[1];
        fkb03_[0] = -Float.parseFloat(df.format((float) kb03_ / all));
        disdata4.setData(fkb03_);
        disdata4.setStack("总量");
        disdata4.setType("bar");
        data.add(disdata4);

        Datadistance disdata5 = new Datadistance();
        disdata5.setName("3-5kb");
        //            disdata5.setLabel(label);
        float[] fkb05 = new float[1];
        fkb05[0] = Float.parseFloat(df.format((float) kb05 / all));
        disdata5.setData(fkb05);
        disdata5.setStack("总量");
        disdata5.setType("bar");
        data.add(disdata5);

        Datadistance disdata6 = new Datadistance();
        disdata6.setName("3-5kb");
        //            disdata6.setLabel(label);
        float[] fkb05_ = new float[1];
        fkb05_[0] = -Float.parseFloat(df.format((float) kb05_ / all));
        disdata6.setData(fkb05_);
        disdata6.setStack("总量");
        disdata6.setType("bar");
        data.add(disdata6);

        Datadistance disdata7 = new Datadistance();
        disdata7.setName("5-10kb");
        //            disdata7.setLabel(label);
        float[] fkb10 = new float[1];
        fkb10[0] = Float.parseFloat(df.format((float) kb10 / all));
        disdata7.setData(fkb10);
        disdata7.setStack("总量");
        disdata7.setType("bar");
        data.add(disdata7);

        Datadistance disdata8 = new Datadistance();
        disdata8.setName("5-10kb");
        //            disdata8.setLabel(label);
        float[] fkb10_ = new float[1];
        fkb10_[0] = -Float.parseFloat(df.format((float) kb10_ / all));
        disdata8.setData(fkb10_);
        disdata8.setStack("总量");
        disdata8.setType("bar");
        data.add(disdata8);

        Datadistance disdata9 = new Datadistance();
        disdata9.setName("10-100kb");
        //            disdata9.setLabel(label);
        float[] fkb100 = new float[1];
        fkb100[0] = Float.parseFloat(df.format((float) kb100 / all));
        disdata9.setData(fkb100);
        disdata9.setStack("总量");
        disdata9.setType("bar");
        data.add(disdata9);

        Datadistance disdata10 = new Datadistance();
        disdata10.setName("10-100kb");
        //            disdata10.setLabel(label);
        float[] fkb100_ = new float[1];
        fkb100_[0] = -Float.parseFloat(df.format((float) kb100_ / all));
        disdata10.setData(fkb100_);
        disdata10.setStack("总量");
        disdata10.setType("bar");
        data.add(disdata10);

        Datadistance disdata11 = new Datadistance();
        disdata11.setName(">100kb");
        //            disdata11.setLabel(label);
        float[] fkb100a = new float[1];
        fkb100a[0] = Float.parseFloat(df.format((float) kb100a / all));
        disdata11.setData(fkb100a);
        disdata11.setStack("总量");
        disdata11.setType("bar");
        data.add(disdata11);

        Datadistance disdata12 = new Datadistance();
        disdata12.setName(">100kb");
        //            disdata12.setLabel(label);
        float[] fkb100a_ = new float[1];
        fkb100a_[0] = -Float.parseFloat(df.format((float) kb100a_ / all));
        disdata12.setData(fkb100a_);
        disdata12.setStack("总量");
        disdata12.setType("bar");
        data.add(disdata12);
        map.put("data", data);
        return map;
    }

    @RequestMapping("sampleRegion")
    @ResponseBody
    public Map sampleRegion(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();
        String sampleid = request.getParameter("sampleid");
        List<GraphChr> data = new ArrayList<>();
        try {
            data = search.sampleRegion(sampleid);
        } catch (Exception e) {
            GraphChr gc = new GraphChr();
            data.add(gc);
        }
        map.put("data", data);
        return map;
    }


}
