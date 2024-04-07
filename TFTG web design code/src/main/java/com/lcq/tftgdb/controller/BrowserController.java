package com.lcq.tftgdb.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lcq.tftgdb.mapper.Browser;
import com.lcq.tftgdb.pojo.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

@Controller
public class BrowserController {
    @Autowired(required = false)
    private Browser browser;

    public static void writeCsvdownmpromoter(MotifPromoterdown headers, List<MotifPromoterdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (MotifPromoterdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downmpromoter")
    public void downmpromoter(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        MotifPromoterdown pojo = new MotifPromoterdown("TF name","TF_motif_Chr","TF_motif_Start","TF_motif_End","Pro_Chr","Pro_Start","Pro_End","Gene","Strand","Motif ID","qValue","pValue","Sequence");

        List<MotifPromoterdown> data = new ArrayList<>();
        data = browser.getTFInMpromoterdown("M_Promoter",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownmpromoter(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownmsilencer(MotifSilencerdown headers, List<MotifSilencerdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (MotifSilencerdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downmsilencer")
    public void downmsilencer(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        MotifSilencerdown pojo = new MotifSilencerdown("TF name","TF_motif_Chr","TF_motif_Start","TF_motif_End","Silencer_Chr","Silencer_Start","Silencer_End","Overlap gene","Proximal gene","Closest gene","Cell line","Silencer_source","Motif ID","qValue","pValue","Sequence");
        String table = tf + "_BETA";
        List<MotifSilencerdown> data = new ArrayList<>();
        data = browser.getTFInMailencerdown("M_Silencer",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownmsilencer(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownmatac(MotifATACdown headers, List<MotifATACdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (MotifATACdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downmatac")
    public void downmatac(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        MotifATACdown pojo = new MotifATACdown("TF name","TF_motif_Chr","TF_motif_Start","TF_motif_End","ATAC_Chr","ATAC_Start","ATAC_End","Overlap gene","Proximal gene","Closest gene","Cell line","ATAC_source","Motif ID","qValue","pValue","Sequence");
        String table = tf + "_BETA";
        List<MotifATACdown> data = new ArrayList<>();
        data = browser.getTFInMatacdown("M_ATAC",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownmatac(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownmte(MotifTEdown headers, List<MotifTEdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (MotifTEdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downmte")
    public void downmte(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        MotifTEdown pojo = new MotifTEdown("TF name","TF_motif_Chr","TF_motif_Start","TF_motif_End","TE_Chr","TE_Start","TE_End","Overlap gene","Proximal gene","Closest gene","Cell line","TE_source","Motif ID","qValue","pValue","Sequence");
        String table = tf + "_BETA";
        List<MotifTEdown> data = new ArrayList<>();
        data = browser.getTFInMtedown("M_TE",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownmte(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownmse(MotifSEdown headers, List<MotifSEdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (MotifSEdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downmse")
    public void downmse(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        MotifSEdown pojo = new MotifSEdown("TF name","TF_motif_Chr","TF_motif_Start","TF_motif_End","SE_Chr","SE_Start","SE_End","Overlap gene","Proximal gene","Closest gene","Cell line","SE_source","Motif ID","qValue","pValue","Sequence");
        String table = tf + "_BETA";
        List<MotifSEdown> data = new ArrayList<>();
        data = browser.getTFInMsedown("M_SE",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownmse(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdowngenemapper(Genemapperdown headers, List<Genemapperdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (Genemapperdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downgenemapper")
    public void downgenemapper(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        Genemapperdown pojo = new Genemapperdown("SampleID","TF_chr","TF_start","TF_end","Overlap gene","Proximal gene","Closest gene");
        String table = tf + "_BETA";
        List<Genemapperdown> data = new ArrayList<>();
        data = browser.getTFInGenemapperdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdowngenemapper(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownbeta(BETAdown headers, List<BETAdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (BETAdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downbeta")
    public void downbeta(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        BETAdown pojo = new BETAdown("SampleID","TF_chr","TF_start","TF_end","RefseqID","Gene","Distance","Score");
        String table = tf + "_BETA";
        List<BETAdown> data = new ArrayList<>();
        data = browser.getTFInBetadown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownbeta(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdowncurate(Curatedown headers, List<Curatedown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (Curatedown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downcurate")
    public void downcurate(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        Curatedown pojo = new Curatedown("TF","Gene","Source","PubmedID","Biosample_name","Repression_Activation");
        String table = tf + "_Curate";
        List<Curatedown> data = new ArrayList<>();
        data = browser.getTFInCuratedown("Curate",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdowncurate(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownknock(Knockdown headers, List<Knockdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (Knockdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downknock")
    public void downknock(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        Knockdown pojo = new Knockdown("TF","Gene","FC","Log2FC","Rank","P_value","Up_down","Sample_source","Sample_experiment_accession","Sample_method","Sample_biosample_name");
        String table = tf + "_Knock";
        List<Knockdown> data = new ArrayList<>();
        data = browser.getTFInKnockdown("Knock",tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownknock(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownpromoter(Promoterdown headers, List<Promoterdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (Promoterdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downpromoter")
    public void downpromoter(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        Promoterdown pojo = new Promoterdown("SampleID","TF_chr","TF_start","TF_end","Promoter_chr","Promoter_start","Promoter_end","Gene","ROL");
        String table = tf + "_Promoter";
        List<Promoterdown> data = new ArrayList<>();
        data = browser.getTFInPromoterdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownpromoter(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownsilencer(Silencerdown headers, List<Silencerdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (Silencerdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downsilencer")
    public void downsilencer(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        Silencerdown pojo = new Silencerdown("SampleID","TF_chr","TF_start","TF_end","Silencer_chr","Silencer_start","Silencer_end","Overlap gene","Proximal gene","Closest gene","Silencer source","Cell line","ROL");
        String table = tf + "_Silencer";
        List<Silencerdown> data = new ArrayList<>();
        data = browser.getTFInSilencerdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownsilencer(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownatac(ATACdown headers, List<ATACdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (ATACdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downatac")
    public void downatac(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        ATACdown pojo = new ATACdown("SampleID","TF_chr","TF_start","TF_end","ATAC_chr","ATAC_start","ATAC_end","Overlap gene","Proximal gene","Closest gene","ATAC source","Cell line","ROL");
        String table = tf + "_ATAC";
        List<ATACdown> data = new ArrayList<>();
        data = browser.getTFInATACdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownatac(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCsvdownte(TEdown headers, List<TEdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (TEdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downte")
    public void downte(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        TEdown pojo = new TEdown("SampleID","TF_chr","TF_start","TF_end","TE_chr","TE_start","TE_end","Overlap gene","Proximal gene","Closest gene","TE source","Cell line","ROL");
        String table = tf + "_TE";
        List<TEdown> data = new ArrayList<>();
        data = browser.getTFInTEdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownte(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCsvdownse(SEdown headers, List<SEdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (SEdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("downse")
    public void downse(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("symbol");
        System.out.println(tf);
        SEdown pojo = new SEdown("SampleID","TF_chr","TF_start","TF_end","SE_chr","SE_start","SE_end","Overlap gene","Proximal gene","Closest gene","SE source","Cell line","ROL");
        String table = tf + "_SE";
        List<SEdown> data = new ArrayList<>();
        data = browser.getTFInSEdown(table);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsvdownse(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCsv(TfMethodWeigdown headers, List<TfMethodWeigdown> data, String filePath) throws IOException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter = new FileWriter(filePath);
        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (TfMethodWeigdown lineData : data) {
                    printer.printRecord(lineData);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("CSV文件创建成功,文件路径:" + filePath);
    }

    @GetMapping("symboltargetinfo")
    public void exportTXT(HttpServletResponse response, HttpServletRequest request) {
        String tf = request.getParameter("tf");
        System.out.println(tf);
        TfMethodWeigdown pojo = new TfMethodWeigdown("Gene","C_SE","C_TE","C_ATAC","C_Silencer","C_Promoter","C_BETA","C_Genemapper","Knock","Curate","M_SE","M_TE","M_ATAC","M_Silencer","M_Promoter","Weight");

        List<TfMethodWeigdown> data = new ArrayList<>();
        data = browser.getTfMeWeBySymboldown(tf);
        try {
            //获取项目classes/static的地址
            String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath() + "/file/";
            writeCsv(pojo, data, staticPath + tf + ".txt");
            String filePath = staticPath + tf + ".txt";
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (filePath.contains("%")) {
                try {
                    filePath = URLDecoder.decode(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                String[] dir = filePath.split("/");
                String fileName = dir[dir.length - 1];
                String[] array = fileName.split("[.]");
                String fileType = array[array.length - 1].toLowerCase();
                //设置文件ContentType类型
                if ("jpg,jepg,gif,png".contains(fileType)) {//图片类型
                    response.setContentType("image/" + fileType);
                } else if ("pdf".contains(fileType)) {//pdf类型
                    response.setContentType("application/pdf");
                } else {//自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                }
                //设置文件头：最后一个参数是设置下载文件名
                //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
            } catch (Exception e) {
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("browsempromotertab")
    @ResponseBody
    public DataTabPageMPromoter browsempromotertab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageMPromoter tabs = new DataTabPageMPromoter();
        try{
            Page<MotifPromoter> list = browser.getTFInMpromoter(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<MotifPromoter> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsemsilencertab")
    @ResponseBody
    public DataTabPageMsilencer browsemsilencertab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageMsilencer tabs = new DataTabPageMsilencer();
        try{
            Page<MotifSilencer> list = browser.getTFInMailencer(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<MotifSilencer> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsematactab")
    @ResponseBody
    public DataTabPageMatac browsematactab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageMatac tabs = new DataTabPageMatac();
        try{
            Page<MotifATAC> list = browser.getTFInMatac(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<MotifATAC> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsemtetab")
    @ResponseBody
    public DataTabPageMte browsemtetab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageMte tabs = new DataTabPageMte();
        try{
            Page<MotifTE> list = browser.getTFInMte(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<MotifTE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsemsetab")
    @ResponseBody
    public DataTabPageMse browsemsetab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageMse tabs = new DataTabPageMse();
        try{
            Page<MotifSE> list = browser.getTFInMse(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<MotifSE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browseknocktab")
    @ResponseBody
    public DataTabPageKnock browseknocktab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageKnock tabs = new DataTabPageKnock();
        try{
            Page<Knock> list = browser.getTFInKnock(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Knock> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsecuratetab")
    @ResponseBody
    public DataTabPageCurate browsecuratetab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageCurate tabs = new DataTabPageCurate();
        try{
            Page<Curate> list = browser.getTFInCurate(table, symbol, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Curate> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsegenemappertab")
    @ResponseBody
    public DataTabPageGenemapper browsegenemappertab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageGenemapper tabs = new DataTabPageGenemapper();
        try{
            Page<Genemapper> list = browser.getTFInGenemapper(table,searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Genemapper> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsebetatab")
    @ResponseBody
    public DataTabPageBeta browsebetatab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        PageHelper.startPage(start / 10 + 1, 10);
        String searchValue = request.getParameter("search[value]");
        DataTabPageBeta tabs = new DataTabPageBeta();
        try{
            Page<BETA> list = browser.getTFInBeta(table, searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<BETA> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsepromotertab")
    @ResponseBody
    public DataTabPagePromoter browsepromotertab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPagePromoter tabs = new DataTabPagePromoter();
        try{
            Page<Promoter> list = browser.getTFInPromoter(table,searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Promoter> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsesilencertab")
    @ResponseBody
    public DataTabPageSilencer browsesilencertab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        System.out.println(table + "###########################");
        DataTabPageSilencer tabs = new DataTabPageSilencer();
        try{
            PageHelper.startPage(start / 10 + 1, 10);
            String searchValue = request.getParameter("search[value]");
            Page<Silencer> list = browser.getTFInSilencer(table,searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Silencer> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browseatactab")
    @ResponseBody
    public DataTabPageATAC browseatactab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        System.out.println(table + "###########################");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageATAC tabs = new DataTabPageATAC();
        try{
            Page<ATAC> list = browser.getTFInATAC(table,searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<ATAC> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsetetab")
    @ResponseBody
    public DataTabPageTE browsetetab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        String table = symbol + "_" + method;
        System.out.println(table);
        System.out.println("--------table-----");
        String searchValue = request.getParameter("search[value]");
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageTE tabs = new DataTabPageTE();
        try{
            Page<TE> list = browser.getTFInTE(table,searchValue);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<TE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("browsesetab")
    @ResponseBody
    public DataTabPageSE browsesetab(Integer start, Integer draw, String symbol, String method, HttpServletRequest request) throws InterruptedException {
        try {
            String table = symbol + "_SE";
            String searchValue = request.getParameter("search[value]");
            PageHelper.startPage(start / 10 + 1, 10);
            DataTabPageSE tabs = new DataTabPageSE();
            Page<SE> list = browser.getTFInSE(table,searchValue);
//        Page<SE> list = browser.getSampleInSE(table,sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
            return tabs;
        }catch (Exception e){
            DataTabPageSE tabs = new DataTabPageSE();
            List<SE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
            return tabs;
        }

    }

    @RequestMapping("samplese")
    @ResponseBody
    public DataTabPageSE samplese(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_SE";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageSE tabs = new DataTabPageSE();
        try{
            Page<SE> list = browser.getSampleInSE(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<SE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("samplete")
    @ResponseBody
    public DataTabPageTE samplete(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_TE";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageTE tabs = new DataTabPageTE();
        try{
            Page<TE> list = browser.getSampleInTE(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<TE> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("sampleatac")
    @ResponseBody
    public DataTabPageATAC sampleatac(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_ATAC";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageATAC tabs = new DataTabPageATAC();
        try{
            Page<ATAC> list = browser.getSampleInATAC(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<ATAC> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("samplesilencer")
    @ResponseBody
    public DataTabPageSilencer samplesilencer(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_Silencer";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageSilencer tabs = new DataTabPageSilencer();
        try{
            Page<Silencer> list = browser.getSampleInSilencer(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Silencer> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("samplepromoter")
    @ResponseBody
    public DataTabPagePromoter samplepromoter(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_Promoter";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPagePromoter tabs = new DataTabPagePromoter();
        try{
            Page<Promoter> list = browser.getSampleInPromoter(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Promoter> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("samplebeta")
    @ResponseBody
    public DataTabPageBeta samplebeta(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_BETA";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageBeta tabs = new DataTabPageBeta();
        try{
            Page<BETA> list = browser.getSampleInBeta(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<BETA> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("samplegenemapper")
    @ResponseBody
    public DataTabPageGenemapper samplegenemapper(Integer start, Integer draw, String sampleid, String method) throws InterruptedException {
        String tf = browser.getTFBysampleid(sampleid);
        String table = tf + "_Genemapper";
        PageHelper.startPage(start / 10 + 1, 10);
        DataTabPageGenemapper tabs = new DataTabPageGenemapper();
        try{
            Page<Genemapper> list = browser.getSampleInGenemapper(table, sampleid);
            tabs.setData(list.getResult());
            tabs.setRecordsTotal((int) list.getTotal());
            tabs.setRecordsFiltered((int) list.getTotal());
            tabs.setDraw(draw);
        }catch (Exception e){
            List<Genemapper> list = new ArrayList<>();
            tabs.setData(list);
            tabs.setRecordsTotal(0);
            tabs.setRecordsFiltered(0);
            tabs.setDraw(draw);
        }
        return tabs;
    }

    @RequestMapping("freqs")
    @ResponseBody
    public Map freqs(HttpServletRequest request) throws InterruptedException {
        String tf = request.getParameter("tf");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String sampleID = request.getParameter("sampleID");
        String table = tf + "_" + method;
        Map map = new HashMap();
        List<String> data = new ArrayList<>();
        String freqDe = "";
        try {
            if (method.equals("SE") || method.equals("TE") || method.equals("ATAC") || method.equals("Silencer")) {
                data = browser.getFreqs(tf, table, cellLine, sampleID);
                freqDe = browser.getFreqsDe(method, sampleID);
            } else if (method.equals("Promoter")) {
                data = browser.getFreqsPromoter(tf, table, cellLine, sampleID);
                freqDe = browser.getFreqsDe(method, sampleID);
            } else if (method.equals("BETA")) {
                data = browser.getFreqsBETA(tf, table, cellLine, sampleID);
                List<String> freqdata = new ArrayList<>();
                if (data.size() > 200) {
                    freqDe = "200";
                    freqdata.add(freqDe);
                    if (data.size() > 300) {
                        freqdata.add("300");
                    }
                    if (data.size() > 400) {
                        freqdata.add("400");
                    }
                    if (data.size() > 500) {
                        freqdata.add(String.valueOf(data.size()));
                    }
                } else {
                    freqDe = String.valueOf(data.size());
                    freqdata.add(freqDe);
                }
                data = freqdata;
            } else if (method.equals("Knock")) {
                String datacount = browser.getFreqsKnock(tf, table, cellLine, sampleID);
                int aaa = Integer.parseInt(datacount);
                List<String> freqdata = new ArrayList<>();
                if (aaa > 100) {
                    freqDe = "100";
                    freqdata.add(freqDe);
                    if (aaa > 200) {
                        freqdata.add("200");
                    }
                    if (aaa > 300) {
                        freqdata.add("300");
                    }
                    if (aaa > 400) {
                        freqdata.add("400");
                    }
                    if (aaa > 500) {
                        freqdata.add(String.valueOf(aaa));
                    }
                } else {
                    freqDe = String.valueOf(aaa);
                    freqdata.add(freqDe);
                }
                data = freqdata;
            } else if (method.equals("Genemapper")) {
                int nums = browser.getFreqsGenemapper(tf, table, cellLine, sampleID);
                List<String> freqdata = new ArrayList<>();
                if (nums > 50) {
                    freqDe = "50";
                    freqdata.add(freqDe);
                    if (nums > 100) {
                        freqdata.add("100");
                    }
                    if (nums > 150) {
                        freqdata.add("150");
                    }
                    if (nums > 151) {
                        freqdata.add(String.valueOf(nums));
                    }
                } else {
                    freqDe = String.valueOf(nums);
                    freqdata.add(freqDe);
                }
                data = freqdata;
            } else if (method.equals("M_SE") || method.equals("M_TE") || method.equals("M_ATAC") || method.equals("M_Silencer")) {
                table = method;
                String table1 = method + "_two";
                if (cellLine != null) {
                    data = browser.getFreqsByMotif(tf, table, cellLine, sampleID);
                    String id = tf + "_" + cellLine;
                    freqDe = browser.getFreqsDeByMotif(id, table1);
                }
            }
            map.put("data", data);
            map.put("freqDe", freqDe);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            map.put("freqDe", freqDe);
            return map;
        }


    }

    @RequestMapping("sampleIDs")
    @ResponseBody
    public Map sampleIDs(HttpServletRequest request) throws InterruptedException {
        String tf = request.getParameter("tf");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String table = tf + "_" + method;
        Map map = new HashMap();
        List<String> data = new ArrayList<>();
        try {
            if (method.equals("SE") || method.equals("TE") || method.equals("ATAC") || method.equals("Silencer")) {
                data = browser.getSampleIDs(tf, table, cellLine);
            } else if (method.equals("Promoter") || method.equals("BETA") || method.equals("Genemapper")) {
                data = browser.getSampleIDsPromoter(tf, cellLine);
            } else if (method.equals("Knock")) {
                data = browser.getSampleIDsKnock(tf, cellLine);
            } else if (method.equals("M_SE") || method.equals("M_TE") || method.equals("M_ATAC") || method.equals("M_Silencer")) {
                data = new ArrayList<>();
            }
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("cellLines")
    @ResponseBody
    public Map cellLines(HttpServletRequest request) throws InterruptedException {
        String tf = request.getParameter("tf");
        String method = request.getParameter("method");
        String table = tf + "_" + method;

        Map map = new HashMap();
        List<String> data = new ArrayList<>();
        try {
            if (method.equals("SE") || method.equals("TE") || method.equals("ATAC") || method.equals("Silencer")) {
                data = browser.getCellLines(tf, table);
            } else if (method.equals("Promoter") || method.equals("BETA") || method.equals("Genemapper")) {
                data = browser.getCellLinesPromoter(tf);
            } else if (method.equals("Knock")) {
                data = browser.getCellLinesKnock(tf);
            } else if (method.equals("Curate")) {
                data = new ArrayList<>();
            } else if (method.equals("M_SE") || method.equals("M_TE") || method.equals("M_ATAC") || method.equals("M_Silencer")) {
                table = method;
                data = browser.getCellLinesMotif(tf, table);
            }
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("graph")
    @ResponseBody
    public Map graph(HttpServletRequest request) throws InterruptedException {
        Double r1 = 10.0;
        Double r2 = 400.0;
        Double minc = -r2;
        Double maxc = r2;
        String symbol = request.getParameter("symbol");
        String num = request.getParameter("freq");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String sampleID = request.getParameter("sampleID");
        String table = symbol + "_" + method;

        List<String> gene = new ArrayList<>();
        Map map = new HashMap();
        try {
            if (method.equals("SE") || method.equals("TE") || method.equals("ATAC") || method.equals("Silencer")) {
                List<String> overlap = browser.getNodeBySymbolOver(symbol, table, num, sampleID, cellLine);
                List<String> proximal = browser.getNodeBySymbolPro(symbol, table, num, sampleID, cellLine);
                List<String> closest = browser.getNodeBySymbolClo(symbol, table, num, sampleID, cellLine);
                for (int i = 0; i < overlap.size(); i++) {
                    String str = overlap.get(i).substring(1, overlap.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < proximal.size(); i++) {
                    String str = proximal.get(i).substring(1, proximal.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < closest.size(); i++) {
                    String str = closest.get(i).substring(1, closest.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }

                HashSet set = new HashSet(gene);
                //把List集合所有元素清空
                gene.clear();
                //把HashSet对象添加至List集合
                gene.addAll(set);
            } else if (method.equals("Promoter")) {
                gene = browser.getNodeBySymbolGene(table, num, sampleID);
            } else if (method.equals("BETA")) {
                gene = browser.getNodeBySymbolBETA(table, num, sampleID);
            } else if (method.equals("Knock")) {
                gene = browser.getNodeBySymbolKnock(table, num, sampleID, symbol, cellLine);
            } else if (method.equals("Genemapper")) {
                List<String> overlap = browser.getNodeBySymbolOverGenemapper(symbol, table, num, sampleID, cellLine);
                List<String> proximal = browser.getNodeBySymbolProGenemapper(symbol, table, num, sampleID, cellLine);
                List<String> closest = browser.getNodeBySymbolCloGenemapper(symbol, table, num, sampleID, cellLine);
                for (int i = 0; i < overlap.size(); i++) {
                    String str = overlap.get(i).substring(1, overlap.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < proximal.size(); i++) {
                    String str = proximal.get(i).substring(1, proximal.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < closest.size(); i++) {
                    String str = closest.get(i).substring(1, closest.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }

                HashSet set = new HashSet(gene);
                //把List集合所有元素清空
                gene.clear();
                //把HashSet对象添加至List集合
                gene.addAll(set);
            } else if (method.equals("Curate")) {
                gene = browser.getGeneFromCurate(symbol);
            } else if (method.equals("M_SE") || method.equals("M_TE") || method.equals("M_ATAC") || method.equals("M_Silencer")) {
                table = method;
                List<String> overlap = new ArrayList<>();
                List<String> proximal = new ArrayList<>();
                List<String> closest = new ArrayList<>();
                if (cellLine != null) {
                    overlap = browser.getNodeBySymbolOverMotif(symbol, table, num, cellLine);
                    proximal = browser.getNodeBySymbolProMotif(symbol, table, num, cellLine);
                    closest = browser.getNodeBySymbolCloMotif(symbol, table, num, cellLine);
                }
                for (int i = 0; i < overlap.size(); i++) {
                    String str = overlap.get(i).substring(1, overlap.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < proximal.size(); i++) {
                    String str = proximal.get(i).substring(1, proximal.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }
                for (int i = 0; i < closest.size(); i++) {
                    String str = closest.get(i).substring(1, closest.get(i).length() - 1);
                    String[] strArr = str.split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].length() != 0) {
                            gene.add(strArr[j]);
                        }
                    }
                }

                HashSet set = new HashSet(gene);
                //把List集合所有元素清空
                gene.clear();
                //把HashSet对象添加至List集合
                gene.addAll(set);
            }


            List<Node> nodes = new ArrayList<>();
            List<Links> links = new ArrayList<>();
            Links link = null;
            Random r = new Random(1);
            Node node = null;
            for (int i = 0; i < gene.size(); i++) {
                node = new Node();
                node.setId(String.valueOf(i + 1));
                node.setSymbolSize("15");
                double x = minc + new Random().nextDouble() * (maxc - minc);
                node.setX(x);
                if (Math.abs(node.getX()) < r1) {
                    Double ymin1 = (Double) Math.sqrt(r1 * r1 - node.getX() * node.getX());
                    Double ymax1 = (Double) Math.sqrt(r2 * r2 - node.getX() * node.getX());
                    Double ymax2 = -(Double) Math.sqrt(r1 * r1 - node.getX() * node.getX());
                    Double ymin2 = -(Double) Math.sqrt(r2 * r2 - node.getX() * node.getX());

                    Double y1 = ymin1 + new Random().nextDouble() * (ymax1 - ymin1);
                    Double y2 = ymin2 + new Random().nextDouble() * (ymax2 - ymin2);
                    Double y;
                    if (new Random().nextFloat() < 0.5) {
                        y = y1;
                    } else {
                        y = y2;
                    }
                    node.setY(y);
                } else {
                    Double ymin1 = -(Double) Math.sqrt(r2 * r2 - node.getX() * node.getX());
                    Double ymax1 = (Double) Math.sqrt(r2 * r2 - node.getX() * node.getX());
                    Double y = (double) (ymin1 + new Random().nextDouble() * (ymax1 - ymin1));
                    node.setY(y);
                }
                node.setValue(gene.get(i));
                node.setName(gene.get(i));
                node.setCategory(1);
                link = new Links();
                link.setSource("0");
                link.setTarget(String.valueOf(i + 1));
                links.add(link);
                nodes.add(node);
            }
            Node source = new Node();
            source.setId("0");
            source.setX(0.0);
            source.setY(0.0);
            source.setName(symbol);
            source.setSymbolSize("20");
            source.setCategory(0);
            source.setValue("0");
            nodes.add(source);

            List<Categories> categoriesList = new ArrayList<>();
            Categories categories1 = new Categories();
            categories1.setName("TF");
            categoriesList.add(categories1);
            Categories categories2 = new Categories();
            categories2.setName("Gene");
            categoriesList.add(categories2);
            map.put("nodes", nodes);
            map.put("links", links);
            map.put("categories", categoriesList);
            return map;
        } catch (Exception e) {
            List<Node> nodes = new ArrayList<>();
            List<Links> links = new ArrayList<>();
            List<Categories> categoriesList = new ArrayList<>();
            map.put("nodes", nodes);
            map.put("links", links);
            map.put("categories", categoriesList);
            return map;
        }

    }

    @RequestMapping("runtable")
    @ResponseBody
    public Map runtable(Integer start, Integer draw, HttpServletRequest request) throws InterruptedException {
        /*3个类，9个sql，9个js查询方法*/
        String gene = request.getParameter("gene");
        String symbol = request.getParameter("symbol");
        String cells = request.getParameter("cells");
        String method = request.getParameter("method");
        System.out.println(gene + "###" + symbol + "###" + cells + "###" + method);
        String[] cell = cells.split(",");
        List<String> cellstr = new ArrayList<>();
        for (int i = 0; i < cell.length; i++) {
            cellstr.add(cell[i]);
        }
        Map map = new HashMap();

        String table = symbol + "_" + method;
        if (method.equals("Genemapper")) {
            List<Genemapper> data = new ArrayList<>();
            try {
                data = browser.genemapper(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("SE")) {
            List<SE> data = new ArrayList<>();
            try {
                data = browser.se(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("TE")) {
            List<TE> data = new ArrayList<>();
            try{
                data = browser.te(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("ATAC")) {
            List<ATAC> data = new ArrayList<>();
            try{
                data = browser.atac(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("Silencer")) {
            List<Silencer> data = new ArrayList<>();
            try{
                data = browser.silencer(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("Promoter")) {
            List<Promoter> data = new ArrayList<>();
            try{
                data = browser.promoter(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("BETA")) {
            List<BETA> data = new ArrayList<>();
            try{
                data = browser.beta(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("Knock")) {
            List<Knock> data = new ArrayList<>();
            try{
                data = browser.knock(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("Curate")) {
            List<Curate> data = new ArrayList<>();
            try{
                data = browser.curate(gene, symbol);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("M_SE")) {
            List<MotifSE> data = new ArrayList<>();
            try{
                data = browser.motifSE(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("M_TE")) {
            List<MotifTE> data = new ArrayList<>();
            try{
                data = browser.motifTE(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("M_ATAC")) {
            List<MotifATAC> data = new ArrayList<>();
            try{
                data = browser.motifATAC(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("M_Silencer")) {
            List<MotifSilencer> data = new ArrayList<>();
            try {
                data = browser.motifSilencer(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        } else if (method.equals("M_Promoter")) {
            List<MotifPromoter> data = new ArrayList<>();
            try{
                data = browser.motifPromoter(gene, symbol, table, cellstr);
                map.put("data", data);
            }catch (Exception e){
                map.put("data", data);
            }
        }
        return map;
    }

    @RequestMapping("cellType")
    @ResponseBody
    public Map cellTypes(HttpServletRequest request) throws InterruptedException {
        String tf = request.getParameter("tf");
        String gene = request.getParameter("gene");
        String method = request.getParameter("method");
        String table = tf + "_" + method;
        Map map = new HashMap();
        List<Multselect> data = new ArrayList<>();
        try {
            if (method.equals("SE") || method.equals("TE") || method.equals("ATAC") || method.equals("Silencer")) {
                data = browser.getCellType2(tf, table, gene);
            } else if (method.equals("Promoter") || method.equals("BETA")) {
                data = browser.getCellType3(tf, table, gene);
            } else if (method.equals("Genemapper")) {
                data = browser.getCellType4(tf, table, gene);
            } else if (method.equals("Knock")) {
                data = browser.getCellType5(tf, table, gene);
            }
            if (data.size() != 0) {
                data.get(0).setSelected(true);
            } else {
                Multselect multselect = new Multselect();
                data.add(multselect);
            }
            if (data.size() == 0) {
                Multselect mult = new Multselect();
                data.add(mult);
            }

            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("cellByMotif")
    @ResponseBody
    public Map cellByMotif(HttpServletRequest request) throws InterruptedException {
        String tf = request.getParameter("tf");
        String motifMethod = request.getParameter("method");
        String gene = request.getParameter("gene");
        Map map = new HashMap();
        List<Multselect> data = new ArrayList<>();
        /*try {*/
        if (motifMethod.equals("M_TE") || motifMethod.equals("M_SE") || motifMethod.equals("M_ATAC") || motifMethod.equals("M_Silencer")) {
            data = browser.getCellByMotif(tf, motifMethod, gene);
        }
        if (data.size() != 0) {
            data.get(0).setSelected(true);
        }
        map.put("data", data);
        return map;
        /*}catch (Exception e) {
            map.put("data",data);
            System.out.println("e.getMessage="+e.getMessage());
            return map;
        }*/
    }

    @RequestMapping("tF_names")
    @ResponseBody
    public Map tF_names() throws InterruptedException {
        Map map = new HashMap();

        List<Multselect> data = new ArrayList<>();
        try {
            data = browser.getTfNames();
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }


    @RequestMapping("browserData")
    @ResponseBody
    public Map browserData(HttpServletRequest request) throws InterruptedException {
        BrowserParam param = new BrowserParam(request.getParameter("tfFamilyPara"), request.getParameter("tfClassPara"), request.getParameter("tfNamePara"));
        Map map = new HashMap();
        List<TF_All> encodeData = new ArrayList<>();
        encodeData = browser.getEncodeData(param);

        try {
            map.put("data", encodeData);
            return map;
        } catch (Exception e) {
            map.put("data", encodeData);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("tfName")
    @ResponseBody
    public Map tfName(HttpServletRequest request) throws InterruptedException {
        BrowserParam param = new BrowserParam(request.getParameter("tfFamilyPara"), request.getParameter("tfClassPara"), request.getParameter("tfNamePara"));
        Map map = new HashMap();
        List<EncodeCate> tfName = browser.getTfName(param);
        try {
            map.put("data", tfName);
            return map;
        } catch (Exception e) {
            map.put("data", tfName);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("tfFamily")
    @ResponseBody
    public Map tfFamily(HttpServletRequest request) throws InterruptedException {
        BrowserParam param = new BrowserParam(request.getParameter("tfFamilyPara"), request.getParameter("tfClassPara"), request.getParameter("tfNamePara"));
        Map map = new HashMap();
        List<EncodeCate> tfFamily = browser.getFamily(param);
        try {
            map.put("data", tfFamily);
            return map;
        } catch (Exception e) {
            map.put("data", tfFamily);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("tfClass")
    @ResponseBody
    public Map tfClass(HttpServletRequest request) throws InterruptedException {
        BrowserParam param = new BrowserParam(request.getParameter("tfFamilyPara"), request.getParameter("tfClassPara"), request.getParameter("tfNamePara"));
        Map map = new HashMap();
        List<EncodeCate> tfClass = browser.getTfClass(param);
        try {
            map.put("data", tfClass);
            return map;
        } catch (Exception e) {
            map.put("data", tfClass);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("infotab")
    @ResponseBody
    public Map infotab(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        TF_All info = browser.getTfInfoBySymbol(symbol);
        try {
            map.put("data", info);
            return map;
        } catch (Exception e) {
            map.put("data", info);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("geneinfo")
    @ResponseBody
    public Map geneinfo(HttpServletRequest request) throws InterruptedException {
        String gene = request.getParameter("gene");
        Map map = new HashMap();
        GeneInfo info = browser.getGeneInfoByGene(gene);
        try {
            map.put("data", info);
            return map;
        } catch (Exception e) {
            map.put("data", info);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("tfMeWe")
    @ResponseBody
    public DataTabPage tfMeWe(Integer start, Integer draw, String symbol, HttpServletRequest request) throws InterruptedException {
        String searchValue = request.getParameter("search[value]");

        PageHelper.startPage(start / 10 + 1, 10);
        Page<TfMethodWeig> list = null;
        DataTabPage tabs = new DataTabPage();
        list = browser.getTfMeWeBySymbol(symbol, searchValue,request.getParameter("order[0][column]"),request.getParameter("order[0][dir]"));
        tabs.setData(list.getResult());
        tabs.setRecordsTotal((int) list.getTotal());
        tabs.setRecordsFiltered((int) list.getTotal());
        tabs.setDraw(draw);
        return tabs;
    }

    @RequestMapping("pathwayTab")
    @ResponseBody
    public Map pathwayTab(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        List<PathwayTab> data = browser.getPathwayTab(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("gotermTab")
    @ResponseBody
    public Map gotermTab(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        List<GotermTab> data = browser.getGotermTab(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("hallmarkTab")
    @ResponseBody
    public Map hallmarkTab(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        List<HallmarkTab> data = browser.getHallmarkTab(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("diseaseTab")
    @ResponseBody
    public Map diseaseTab(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        List<DiseaseTab> data = browser.getDiseaseTab(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp0")
    @ResponseBody
    public Map exp0(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp0 data = browser.getExp0(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp1")
    @ResponseBody
    public Map exp1(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp1 data = browser.getExp1(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp2")
    @ResponseBody
    public Map exp2(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp2 data = browser.getExp2(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp3")
    @ResponseBody
    public Map exp3(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp3 data = browser.getExp3(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp4")
    @ResponseBody
    public Map exp4(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp4 data = browser.getExp4(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp5")
    @ResponseBody
    public Map exp5(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp5 data = browser.getExp5(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }

    @RequestMapping("exp6")
    @ResponseBody
    public Map exp6(HttpServletRequest request) throws InterruptedException {
        String symbol = request.getParameter("symbol");
        Map map = new HashMap();
        Exp6 data = browser.getExp6(symbol);
        try {
            map.put("data", data);
            return map;
        } catch (Exception e) {
            map.put("data", data);
            System.out.println("e.getMessage=" + e.getMessage());
            return map;
        }
    }
}
