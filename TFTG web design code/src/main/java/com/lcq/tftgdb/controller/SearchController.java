package com.lcq.tftgdb.controller;

import com.lcq.tftgdb.mapper.Search;
import com.lcq.tftgdb.pojo.*;
import com.lcq.tftgdb.until.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class SearchController {
    @Autowired(required = false)
    private Search search;
    String outpath = "/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/ana3/";

    @RequestMapping("datatab1")
    @ResponseBody
    public Map datatab1(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();

        List<Download> data = new ArrayList<>();
        data = search.downChip();
        map.put("data", data);
        return map;
    }

    @RequestMapping("datatab2")
    @ResponseBody
    public Map datatab2(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();

        List<Download> data = new ArrayList<>();
        data = search.downRNA();
        map.put("data", data);
        return map;
    }

    @RequestMapping("weightbymethod")
    @ResponseBody
    public Map weightbymethod(HttpServletRequest request) throws InterruptedException {
        String method = request.getParameter("method");
        Map map = new HashMap();
        System.out.println("method::::" + method);
        String table = "search2_" + method;
        List<String> data = new ArrayList<>();
        data = search.getweightbymethod(table);
        map.put("data", data);
        return map;
    }

    @RequestMapping("searchByGene")
    @ResponseBody
    public ModelAndView searchByGene(HttpServletRequest request) throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        String gene = request.getParameter("gene");
        String method = request.getParameter("method");
        String weight = request.getParameter("weight");
        String table = "search2_" + method;
        Map map = new HashMap();
        List<TF_All> data = new ArrayList<>();
        data = search.getInfoByGene(table, gene, weight, method);
        mv.addObject("data", data);
        mv.addObject("method", method);
        mv.setViewName("messages/SearchByGene");
        return mv;
    }

    @RequestMapping("tfbedtools")
    @ResponseBody
    public ModelAndView tfbedtools(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String chipmethod = request.getParameter("chipmethod");
        String chromosome = request.getParameter("chromosome");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        try {
            Tools tool = new Tools();
            String userID = "usr_" + tool.getUUID();

            File file = new File(outpath + userID);
            if (!file.exists()) {//如果文件夹不存在
                file.mkdir();//创建文件夹
            }

//      把bedtools执行所需数据存入服务器input.txt文件中
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/input.txt")));
            String input = chromosome + "\t" + start + "\t" + end;
            out.println(input);
            out.close();
//      更改bedtools.sh脚本，插入参数
            Tools.bedtoolsChange(outpath, chipmethod, userID, chromosome);
//      使run.sh文件有执行权限
            Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/bedtools.sh");
            ps.waitFor();
//      执行bedtools.sh文件进而执行bedtools脚本
            Tools.runShell(outpath + userID + "/bedtools.sh");
//      读取bedtools生成的文件
            List<All> list = new ArrayList<>();
            if (chipmethod.equals("SE")) {
                list = tool.readBedtoolsResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtools");
                return mv;
            } else if (chipmethod.equals("Silencer")) {
                list = tool.readBedtoolsResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolssilencer");
                return mv;
            } else if (chipmethod.equals("ATAC")) {
                list = tool.readBedtoolsResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolsatac");
                return mv;
            } else if (chipmethod.equals("TE")) {
                list = tool.readBedtoolsResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolste");
                return mv;
            } else if (chipmethod.equals("Genemapper")) {
                list = tool.readBedtoolsGenemapperResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolsGenemapper");
                return mv;
            } else if (chipmethod.equals("BETA")) {
                list = tool.readBedtoolsBETAResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolsBETA");
                return mv;
            } else if (chipmethod.equals("Promoter")) {
                list = tool.readBedtoolsPromoterResult(outpath + userID + "/output.txt");
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtoolsPromoter");
                return mv;
            } else {
                mv.addObject("data", list);
                mv.setViewName("messages/SearchByBedtools");
                return mv;
            }
        } catch (Exception e) {
            List<All> list = new ArrayList<>();
            mv.addObject("data", list);
            mv.setViewName("messages/SearchByBedtools");
            return mv;
        }
    }


    @RequestMapping("tfByFamily")
    @ResponseBody
    public ModelAndView tfByFamily(HttpServletRequest request) throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        String family = request.getParameter("family");
        System.out.println(family);
        List<TF_All> data = new ArrayList<>();
        data = search.getInfoByFamily(family);
        mv.addObject("data", data);
        mv.setViewName("messages/SearchByFamily");
        return mv;
    }

    @RequestMapping("tfByFamily1")
    @ResponseBody
    public Map tfByFamily1(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();
        String family = request.getParameter("family");
        System.out.println(family);
        List<String> data = new ArrayList<>();
        if (family.equals("ALL")){
            data = search.getTfToAllFamily1();
        }else {
            data = search.getTfByFamily(family);
        }
        map.put("data", data);
        return map;
    }

    @RequestMapping("tfMultSelectByFamily1")
    @ResponseBody
    public Map tfMultSelectByFamily1(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap();
        String family = request.getParameter("family");
        System.out.println(family);
        List<Multselect> data = new ArrayList<>();
        if (family.equals("ALL")){
            data = search.gettfAllmultseletByFamily();
        }else {
            data = search.gettfmultseletByFamily(family);
        }
        map.put("data", data);
        return map;
    }
}
