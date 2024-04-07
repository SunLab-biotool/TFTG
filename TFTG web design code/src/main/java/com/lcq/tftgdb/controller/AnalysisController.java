package com.lcq.tftgdb.controller;

import com.lcq.tftgdb.mapper.Analysis;
import com.lcq.tftgdb.pojo.*;
import com.lcq.tftgdb.until.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AnalysisController {
    @Autowired(required = false)
    private Analysis analysis;

    /*生成坐标参数
     * */
    Double r1 = 50.0;
    Double r2 = 100.0;
    Double r3 = 120.0;
    Double r4 = 150.0;
    Double r5 = 170.0;
    Double r6 = 230.0;
    Double minc = -r2;
    Double maxc = r2;
    Double minb = (double) -r4;
    Double maxb = (double) r4;
    Double mind = (double) -r6;
    Double maxd = (double) r6;

    String outpath = "/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/";
//    String outpath = "E://";

    @RequestMapping("venngraph")
    @ResponseBody
    public Map venngraph(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tf = request.getParameter("tf");
        String sampleid = request.getParameter("sampleid");
        String method = request.getParameter("method");
        String input = request.getParameter("input");//,分隔
        String core = request.getParameter("core");//;分隔
        String gene = analysis.getvenn(tf, sampleid, method);//；分隔

        int inputsize = Integer.parseInt(input);
        int coresize = Integer.parseInt(core);
        int genesize = 0;
        String[] genearr = null;
        if (gene != null) {
            genearr = gene.split(";");
            genesize = genearr.length;
        }
        List<Venn> vennlist = new ArrayList<>();
        Venn venn1 = new Venn();
        venn1.setName("target genes");
        venn1.setValue(genesize);
        vennlist.add(venn1);
        Venn venn2 = new Venn();
        venn2.setName("the submitted genes");
        venn2.setValue(inputsize);
        vennlist.add(venn2);
        Venn venn3 = new Venn();
        venn3.setName("inter genes");
        venn3.setValue(coresize);
        vennlist.add(venn3);
        map.put("data", vennlist);
        return map;
    }

    @RequestMapping("pathwaygraph")
    @ResponseBody
    public Map pathwaygraph(HttpServletRequest request) {
        String pathwayid = request.getParameter("pathwayid");
        String tfs = request.getParameter("tfs");
        String[] tfarr = tfs.split(";");
        List<NodeSub> nodelist = new ArrayList<>();
        List<Edgechart> edgelist = new ArrayList<>();
        NodeSub nodeSub = null;
        Edgechart edge = null;
        for (int i = 0; i < tfarr.length; i++) {
            nodeSub = new NodeSub();
            edge = new Edgechart();
            nodeSub.setId(tfarr[i]);
            nodeSub.setName(tfarr[i]);
            nodeSub.setValue(tfarr[i]);
            nodeSub.setCategory("TF");
            nodelist.add(nodeSub);
            edge.setSource(pathwayid);
            edge.setTarget(tfarr[i]);
            edgelist.add(edge);
        }
        NodeSub nodepathway = new NodeSub();
        nodepathway.setId(pathwayid);
        nodepathway.setName(pathwayid);
        nodepathway.setValue(pathwayid);
        nodepathway.setCategory("Pathway");
        nodelist.add(nodepathway);
        List<Categories> categoriesList = new ArrayList<>();
        Categories categories1 = new Categories();
        categories1.setName("Pathway");
        categoriesList.add(categories1);
        Categories categories2 = new Categories();
        categories2.setName("TF");
        categoriesList.add(categories2);
        Map map = new HashMap();
        map.put("node", nodelist);
        map.put("edge", edgelist);
        map.put("categories", categoriesList);
        return map;
    }


    @RequestMapping("getchildTable")
    @ResponseBody
    public Map getchildTable(HttpServletRequest request) throws Exception {
        String userID = request.getParameter("userID");
        String gene = request.getParameter("gene");
        String tf = request.getParameter("tf");
        Pattern p= Pattern.compile("'>(\\w+)</a>");
        Matcher m=p.matcher(tf);
        String y = "";
        while(m.find()){
            y+=m.group(1)+",";
        }
        y = y.substring(0,y.length()-1);
        y = "y=c('" + y + "')";
        y = y.replace(",","','");
        Map map = new HashMap();
        Tools tool = new Tools();

        //      更改R脚本，插入参数
        Tools.changeRPathwayAnaChlid(outpath, userID, gene, y);
//      创建shell脚本，执行指定的R脚本
        Tools.changeshana32(outpath, userID);
//      使run.sh文件有执行权限
        Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/runana32.sh");
        ps.waitFor();
//      执行run.sh文件进而执行R脚本
        Tools.runShell(outpath + userID + "/runana32.sh");

//        List<PathwayChlidTf> list = tool.readPathwayChlidTf("E:\\web\\周新源网站\\TFTG-Change2\\result.txt");
        List<PathwayChlidTf> list = tool.readPathwayChlidTf(outpath+userID+"/result32.txt");
        map.put("data", list);
        return map;
    }

    @RequestMapping("getpathwayresult")
    @ResponseBody
    public Map getpathwayresult(HttpServletRequest request) throws Exception {
        String userID = request.getParameter("userID");
        Map map = new HashMap();
        Tools tool = new Tools();

        List<PathwayResult> list = tool.readPathwayResult(outpath+userID+"/pathwayresult.txt");
        map.put("data", list);
        return map;
    }

    @RequestMapping("runpathwayanalysis")
    @ResponseBody
    public ModelAndView runpathwayanalysis(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String[] method = request.getParameterValues("databases");
        String adjust = request.getParameter("adjust");
        if (adjust == null) {
            adjust = "0";
        }
        String threshold = request.getParameter("threshold");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        String genes = request.getParameter("genes");
        List<String> methodlist = new ArrayList<>();
        List<String> genelist = new ArrayList<>();
        if (method != null || method.length != 0) {
            for (int i = 0; i < method.length; i++) {
                methodlist.add(method[i]);
            }
        }
        String gene = genes.replace("\r\n", "','");
        gene = "x=c('" + gene + "')";
        List<PathwayInfo> pathwayInfoList = analysis.getPathwayinfo(methodlist, min, max);
        Tools tool = new Tools();
        String userID = "usr_" + tool.getUUID();

        File file = new File(outpath + userID);
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
//      把R执行所需数据存入服务器pathway_information.txt文件中
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/pathway_information.txt")));
        for (int i = 0; i < pathwayInfoList.size(); i++) {
            out.println(pathwayInfoList.get(i));
        }
        out.close();
//      更改R脚本，插入参数
        Tools.changeRPathwayAna(outpath, userID, adjust, threshold, gene);
//      创建shell脚本，执行指定的R脚本
        Tools.changesh(outpath, userID);
//      使run.sh文件有执行权限
        Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/runpathway.sh");
        ps.waitFor();
//      执行run.sh文件进而执行R脚本
        Tools.runShell(outpath + userID + "/runpathway.sh");
//      读取R生成的文件
//      List<PathwayResult> list = tool.readPathwayResult(outpath + userID + "/pathwayresult.txt");
//      mv.addObject("data", list);
        mv.addObject("userID",userID);
        mv.addObject("gene",gene);
        mv.setViewName("messages/pathwayResult");
        return mv;
    }

    @RequestMapping("runnetworknoSpecificity")
    @ResponseBody
    public Map runnetworknoSpecificity(HttpServletRequest request) throws Exception {
        String weight = request.getParameter("weight");
        String genes = request.getParameter("genes");
        System.out.println(weight);
        System.out.println(genes);
        String[] genearr = genes.split("\r\n");
        List<String> inputgenelist = new ArrayList<>();
        List<Categories> categoriesList = new ArrayList<>();
        Categories categories1 = new Categories();
        categories1.setName("TF");
        categoriesList.add(categories1);
        Categories categories2 = new Categories();
        categories2.setName("Gene");
        categoriesList.add(categories2);
        List<Node> nodeList = new ArrayList<>();
        try {
            for (int i = 0; i < genearr.length; i++) {
                inputgenelist.add(genearr[i]);
            }
            Tools tool = new Tools();
            String userID = "usr_" + tool.getUUID();

            File file = new File(outpath + userID);
            if (!file.exists()) {//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            Tools.createFile(outpath + userID + "/tuo.txt", genes);
            Tools.changeRAnalysis2Nospe(outpath, userID, weight);
            Tools.changeananos2sh(outpath, userID);
            //使run.sh文件有执行权限
            Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/runananos2.sh");
            ps.waitFor();
//      执行run.sh文件进而执行R脚本
            Tools.runShell(outpath + userID + "/runananos2.sh");
//      读取用户输入的gene
            List<String> nodeusrlist = tool.inputout(outpath + userID + "/tuo.txt");
            Node node = null;

//      读取R生成的文件
            Map mapread = tool.readAnalysis2Result2(outpath + userID + "/net.txt");
            List<Edgechart> edge = (List<Edgechart>) mapread.get("edge");
            List<String> tflist = (List<String>) mapread.get("tflist");
            List<String> genelist = (List<String>) mapread.get("genelist");
            List<String> tfs = new ArrayList<>();
            List<String> gen = new ArrayList<>();
            int tflen = 0;
            if (tflist.size() >= 200) {
                tflen = 200;
            } else tflen = tflist.size();

            for (int i = 0; i < tflen; i++) {
                tfs.add(tflist.get(i));
            }
            nodeList.addAll(getnodelist12(tfs));

            for (int i = 0; i < genelist.size(); i++) {
                gen.add(genelist.get(i));
            }
            nodeList.addAll(getnodelist56(gen));
            Map map = new HashMap();
            map.put("node", nodeList);
            map.put("edge", edge);
            map.put("userID", userID);
            return map;
        } catch (Exception e) {
            System.out.println(e);
            Node node = new Node();
            nodeList.add(node);
            List<Edgechart> edge = new ArrayList<>();
            Map map = new HashMap();
            map.put("node", nodeList);
            map.put("edge", edge);
            map.put("categories", categoriesList);
            map.put("userID", "");
            return map;
        }

    }
    public List<Node> getnodelist12(List<String> list) throws Exception {
        List<Node> nodelist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Node node = new Node();
            node.setId(list.get(i)+"_tf");
            node.setName(list.get(i));
            node.setCategory(0);
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
            nodelist.add(node);
        }
        return nodelist;
    }
    public List<Node> getnodelist56(List<String> list) throws Exception {
        List<Node> nodelist = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            Node node = new Node();
            node.setId(list.get(j)+"_gene");
            node.setName(list.get(j));
            node.setCategory(1);
            double x = mind + new Random().nextDouble() * (maxd - mind);
            node.setX(x);
            if (Math.abs(node.getX()) < r5) {
                Double ymin5 = (Double) Math.sqrt(r5 * r5 - node.getX() * node.getX());
                Double ymax5 = (Double) Math.sqrt(r6 * r6 - node.getX() * node.getX());
                Double ymax6 = -(Double) Math.sqrt(r5 * r5 - node.getX() * node.getX());
                Double ymin6 = -(Double) Math.sqrt(r6 * r6 - node.getX() * node.getX());

                Double y5 = ymin5 + new Random().nextDouble() * (ymax5 - ymin5);
                Double y6 = ymin6 + new Random().nextDouble() * (ymax6 - ymin6);
                Double y;
                if (new Random().nextFloat() < 0.5) {
                    y = y5;
                } else {
                    y = y6;
                }
                node.setY(y);
            } else {
                Double ymin5 = -(Double) Math.sqrt(r6 * r6 - node.getX() * node.getX());
                Double ymax5 = (Double) Math.sqrt(r6 * r6 - node.getX() * node.getX());
                Double y = (double) (ymin5 + new Random().nextDouble() * (ymax5 - ymin5));
                node.setY(y);
            }
            nodelist.add(node);
        }
        return nodelist;
    }

/*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */
    public Map networktable2(String method,String cellline,String inputgenes) throws Exception {
        Map map = new HashMap();

        Tools tool = new Tools();
        String userID = "usr_" + tool.getUUID();

        File file = new File(outpath + userID);
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        Tools.createFile(outpath + userID + "/tuo.txt", inputgenes);
        Tools.changeRAnalysis2(outpath, userID, method, cellline);
        Tools.changeanas2sh(outpath, userID);
        //      使run.sh文件有执行权限
        Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/runana2.sh");
        ps.waitFor();
    //      执行run.sh文件进而执行R脚本
        Tools.runShell(outpath + userID + "/runana2.sh");
        //      读取用户输入的gene
        List<String> nodeusrlist = tool.inputout(outpath + userID + "/tuo.txt");

        //      读取R生成的文件
        Map mapread = tool.readAnalysis2Result2(outpath + userID + "/net.txt");
        map.put("userID",userID);
        return map;
    }

    @RequestMapping("runnetwork")
    @ResponseBody
    public Map runnetwork(HttpServletRequest request) throws Exception {
        String method = request.getParameter("method");
        String cellline = request.getParameter("cellline");
        String genes = request.getParameter("genes");
        String[] genearr = genes.split("\r\n");
        List<String> inputgenelist = new ArrayList<>();
        for (int i = 0; i < genearr.length; i++) {
            inputgenelist.add(genearr[i]);
        }

        List<NodeSub> nodeSubList = new ArrayList<>();
        List<Categories> categoriesList = new ArrayList<>();
        Categories categories1 = new Categories();
        categories1.setName("TF");
        categoriesList.add(categories1);
        Categories categories2 = new Categories();
        categories2.setName("Gene");
        categoriesList.add(categories2);
        try {
            Tools tool = new Tools();
            String userID = "usr_" + tool.getUUID();

            File file = new File(outpath + userID);
            if (!file.exists()) {//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            Tools.createFile(outpath + userID + "/tuo.txt", genes);
            Tools.changeRAnalysis2(outpath, userID, method, cellline);
            Tools.changeanas2sh(outpath, userID);
            //      使run.sh文件有执行权限
            Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/runana2.sh");
            ps.waitFor();
//      执行run.sh文件进而执行R脚本
            Tools.runShell(outpath + userID + "/runana2.sh");
//      读取用户输入的gene
            List<String> nodeusrlist = tool.inputout(outpath + userID + "/tuo.txt");
            NodeSub node = null;

//      读取R生成的文件
            Map mapread = tool.readAnalysis2Result2(outpath + userID + "/net.txt");
            List<Edgechart> edge = (List<Edgechart>) mapread.get("edge");
            List<String> tflist = (List<String>) mapread.get("tflist");
            List<String> genelist = (List<String>) mapread.get("genelist");
            /*判断，若是tflist长度大于200则只显示200个tf节点*/
            int tflen = 0;
            if (tflist.size() >= 200) {
                tflen = 200;
            } else tflen = tflist.size();
            for (int i = 0; i < tflen; i++) {
                node = new NodeSub();
                node.setId(tflist.get(i) + "_tf");
                node.setName(tflist.get(i));
                node.setValue(tflist.get(i));
                node.setCategory("TF");
                for (int j = 0; j < inputgenelist.size(); j++) {
                    if (tflist.get(i).equals(inputgenelist.get(j))) {
                        ItemStyle itemStyle = new ItemStyle();
                        Normal normal = new Normal();
                        normal.setBorderColor("#e96f6a");
                        normal.setBorderWidth("1");
                        normal.setShadowBlur("10");
                        normal.setShadowColor("rgba(0, 0, 0, 0.3)");
                        itemStyle.setNormal(normal);
                        node.setItemStyle(itemStyle);
                    }
                }
                nodeSubList.add(node);
            }

            for (int i = 0; i < genelist.size(); i++) {
                node = new NodeSub();
                node.setId(genelist.get(i) + "_gene");
                node.setName(genelist.get(i));
                node.setValue(genelist.get(i));
                node.setCategory("Gene");
                for (int j = 0; j < inputgenelist.size(); j++) {
                    if (genelist.get(i).equals(inputgenelist.get(j))) {
                        ItemStyle itemStyle = new ItemStyle();
                        Normal normal = new Normal();
                        normal.setBorderColor("#e96f6a");
                        normal.setBorderWidth("1");
                        normal.setShadowBlur("10");
                        normal.setShadowColor("rgba(0, 0, 0, 0.3)");
                        itemStyle.setNormal(normal);
                        node.setItemStyle(itemStyle);
                    }
                }
                nodeSubList.add(node);
            }

            Map map = new HashMap();
            map.put("node", nodeSubList);
            map.put("edge", edge);
            map.put("categories", categoriesList);
            map.put("userID", userID);
            return map;
        } catch (Exception e) {
            NodeSub node = new NodeSub();
            nodeSubList.add(node);
            List<Edgechart> edge = new ArrayList<>();
            Map map = new HashMap();
            map.put("node", nodeSubList);
            map.put("edge", edge);
            map.put("categories", categoriesList);
            map.put("userID", "");
            return map;
        }

    }

    @RequestMapping("nospecificity")
    @ResponseBody
    public Map nospecificity(HttpServletRequest request) throws IOException {
        String genes = request.getParameter("genes");
        String[] genearr = genes.split("\r\n");
        List<String> genelist = new ArrayList<>();
        for (int i = 0; i < genearr.length; i++) {
            genelist.add(genearr[i]);
        }
        List<String> weight = new ArrayList<>();
        weight = analysis.getWeight(genelist);
        Map map = new HashMap();
        map.put("data", weight);
        return map;
    }

    @RequestMapping("ana2network")
    @ResponseBody
    public Map ana2network(HttpServletRequest request) throws IOException {
        Tools tool = new Tools();
        Map map = new HashMap();
        String userID = request.getParameter("userID");
        if (userID == null) {
            List<Analysis2ResultTab> tab = new ArrayList<>();
            map.put("data", tab);
            return map;
        }
        try {
            List<Analysis2ResultTab> tab = tool.readAnalysis2Result2Tab(outpath + userID + "/table_gene.txt");
            map.put("data", tab);
            return map;
        }catch (Exception e){
            List<Analysis2ResultTab> tab = new ArrayList<>();
            map.put("data", tab);
            return map;
        }

    }

    @RequestMapping("ana2cellLines")
    @ResponseBody
    public Map ana2cellLines(HttpServletRequest request) {
        String method = request.getParameter("method");
        String genes = request.getParameter("genes");
        String[] genearr = genes.split("\r\n");
        List<String> genelist = new ArrayList<>();
        for (int i = 0; i < genearr.length; i++) {
            genelist.add(genearr[i]);
        }
        Map map = new HashMap();
        String table = "ana2_" + method;
        List<String> data = new ArrayList<>();

        data = analysis.getana2CellByMethod(table, genelist);
        map.put("data", data);
        return map;
    }

    @RequestMapping("runNetworkAnalysis")
    @ResponseBody
    public ModelAndView runNetworkAnalysis(MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String fileName = file.getOriginalFilename();
        String genes = "";
        if (!fileName.isEmpty()) {
            // 获取文件后缀
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            File newfile = File.createTempFile("genes", prefix);
            // MultipartFile to File
            file.transferTo(newfile);

            //你的业务逻辑
            String genestr = "";
            try {
                if (newfile.isFile() && newfile.exists()) {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(newfile), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String lineTxt = null;
                    while ((lineTxt = br.readLine()) != null) {
                        genestr += lineTxt + "\r\n";
                    }
                    br.close();
                } else {
                    System.out.println("文件不存在!");
                }
            } catch (Exception e) {
                System.out.println("文件读取错误!");
            }

            //程序结束时，删除临时文件
            if (newfile.exists()) {
                newfile.delete();
            }
            genes = genestr;

        } else if (request.getParameter("genes") != null || request.getParameter("genes") != "" || request.getParameter("genes") != "\r\n" || request.getParameter("genes").length() != 0) {
            genes = request.getParameter("genes");
        }
        mv.addObject("genes", genes);
        mv.setViewName("messages/networkResult");
        return mv;
    }

    @RequestMapping("celllineMult")
    @ResponseBody
    public Map celllineMult(HttpServletRequest request) {
        Map map = new HashMap();
        List<String> methodlist = new ArrayList<>();
        if (request.getParameter("SE") != null) methodlist.add("SE");
        if (request.getParameter("TE") != null) methodlist.add("TE");
        if (request.getParameter("ATAC") != null) methodlist.add("ATAC");
        if (request.getParameter("Silencer") != null) methodlist.add("Silencer");
        if (request.getParameter("Promoter") != null) methodlist.add("Promoter");
        if (request.getParameter("BETA") != null) methodlist.add("BETA");
        if (request.getParameter("Genemapper") != null) methodlist.add("Genemapper");
        if (request.getParameter("Curate") != null) methodlist.add("Curate");
        if (request.getParameter("Knock") != null) methodlist.add("Knock");
        if (request.getParameter("M_SE") != null) methodlist.add("M_SE");
        if (request.getParameter("M_TE") != null) methodlist.add("M_TE");
        if (request.getParameter("M_ATAC") != null) methodlist.add("M_ATAC");
        if (request.getParameter("M_Silencer") != null) methodlist.add("M_Silencer");
        if (request.getParameter("M_Promoter") != null) methodlist.add("M_Promoter");
        List<Multselect> data = new ArrayList<>();
        System.out.println(methodlist);
        data = analysis.getCellByMethod(methodlist);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLabel().equals("MCF7")) {
                data.get(i).setSelected(true);
                break;
            }
        }
        map.put("data", data);
        return map;
    }

    @RequestMapping("analysis1")
    @ResponseBody
    public ModelAndView analysis1(MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String fileName = "";
        try {
            fileName = file.getOriginalFilename();
        }catch (Exception e){
            mv.setViewName("messages/geneAnalysis");
            return mv;
        }
        try {
            String[] method = request.getParameterValues("method");
            String adjust = request.getParameter("adjust");
            if (adjust == null) {
                adjust = "0";
            }
            String threshold = request.getParameter("threshold");
            String min = request.getParameter("min");
            String max = request.getParameter("max");
            String[] cell = request.getParameterValues("cell");
            String genes = request.getParameter("genes");
            List<String> methodlist = new ArrayList<>();
            List<String> celllist = new ArrayList<>();
            List<String> genelist = new ArrayList<>();
            List<Analysis1Input> M_SElist = new ArrayList<>();
            List<Analysis1Input> M_TElist = new ArrayList<>();
            List<Analysis1Input> M_ATAClist = new ArrayList<>();
            List<Analysis1Input> M_Silencerlist = new ArrayList<>();
            List<Analysis1Input> M_Promoterlist = new ArrayList<>();
            List<Analysis1Input> Curatelist = new ArrayList<>();
            if (method != null || method.length != 0) {
                for (int i = 0; i < method.length; i++) {
                    methodlist.add(method[i]);
                    if (method[i].equals("M_SE")){
                        M_SElist = analysis.getAnalysis1M_SE("M_SE", min, max);
                    }
                    if (method[i].equals("M_TE")){
                        M_TElist = analysis.getAnalysis1M_TE("M_TE", min, max);
                    }
                    if (method[i].equals("M_ATAC")){
                        M_ATAClist = analysis.getAnalysis1M_ATAC("M_ATAC", min, max);
                    }
                    if (method[i].equals("M_Silencer")){
                        M_Silencerlist = analysis.getAnalysis1M_Silencer("M_Silencer", min, max);
                    }
                    if (method[i].equals("M_Promoter")){
                        M_Promoterlist = analysis.getAnalysis1M_Promoter("M_Promoter", min, max);
                    }
                    if (method[i].equals("Curate")){
                        Curatelist = analysis.getAnalysis1Curate("Curate", min, max);
                    }
                }
            }
            if (cell != null) {
                for (int i = 0; i < cell.length; i++) {
                    celllist.add(cell[i]);
                }
            }
            String gene = "x=c('')";
            System.out.println(genes);
            if (!fileName.isEmpty()) {
                // 获取文件后缀
                String prefix = fileName.substring(fileName.lastIndexOf("."));
                // 用uuid作为文件名，防止生成的临时文件重复
                File newfile = File.createTempFile("genes", prefix);
                // MultipartFile to File
                file.transferTo(newfile);

                //你的业务逻辑
                String genestr = "";
                try {
                    if (newfile.isFile() && newfile.exists()) {
                        InputStreamReader isr = new InputStreamReader(new FileInputStream(newfile), "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        String lineTxt = null;
                        while ((lineTxt = br.readLine()) != null) {
                            genestr += "'" + lineTxt + "',";
                        }
                        br.close();
                    } else {
                        System.out.println("文件不存在!");
                    }
                } catch (Exception e) {
                    System.out.println("文件读取错误!");
                }

                //程序结束时，删除临时文件
                if (newfile.exists()) {
                    newfile.delete();
                }
                gene = "";
                gene = "x=c(" + genestr.substring(0, genestr.length() - 1) + ")";
            } else if (genes != null || genes != "" || genes != "\r\n" || genes.length() != 0) {
                String geneinput = "";
                geneinput = genes.replace("\r\n", "','");
                gene = "x=c('" + geneinput + "')";
            }
            System.out.println("this is :" + gene);
            List<Analysis1Input> analysisInput = analysis.getAnalysis1(methodlist, min, max, celllist);
            analysisInput.addAll(M_SElist);
            analysisInput.addAll(M_TElist);
            analysisInput.addAll(M_ATAClist);
            analysisInput.addAll(M_Silencerlist);
            analysisInput.addAll(M_Promoterlist);
            analysisInput.addAll(Curatelist);
            Tools tool = new Tools();
            String userID = "usr_" + tool.getUUID();

            File file1 = new File(outpath + userID);
            if (!file1.exists()) {//如果文件夹不存在
                file1.mkdir();//创建文件夹
            }
//      把R执行所需数据存入服务器input.txt文件中
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/input.txt")));
            for (int i = 0; i < analysisInput.size(); i++) {
                out.println(analysisInput.get(i));
            }
            out.close();
            Tools.changeanash(outpath, userID);
//      使run.sh文件有执行权限
            Process ps = Runtime.getRuntime().exec("chmod 777 -R " + outpath + userID + "/run.sh");
            ps.waitFor();
//      更改R脚本，插入参数
            Tools.changeRAnalysis1(outpath, userID, adjust, threshold, gene);
//      执行run.sh文件进而执行R脚本
            Tools.runShell(outpath + userID + "/run.sh");

            Collections.replaceAll(methodlist, "Knock", "Perturbation");
            Collections.replaceAll(methodlist, "SE", "C_SE");
            Collections.replaceAll(methodlist, "TE", "C_TE");
            Collections.replaceAll(methodlist, "ATAC", "C_ATAC");
            Collections.replaceAll(methodlist, "Promoter", "C_Promoter");
            Collections.replaceAll(methodlist, "Silencer", "C_Silencer");
            Collections.replaceAll(methodlist, "BETA", "C_BETA");
            Collections.replaceAll(methodlist, "Genemapper", "C_Genemapper");
            mv.addObject("input", gene.split(",").length);
            mv.addObject("methods", methodlist);
            mv.addObject("userID",userID);
            mv.setViewName("messages/analysis1Result");
            return mv;
        } catch (Exception e) {
            List<Analysis1Result> list = new ArrayList<>();
            mv.addObject("data", list);
            mv.setViewName("messages/analysis1Result");
            return mv;
        }

    }
    @RequestMapping("analysis11")
    @ResponseBody
    public ModelAndView analysis12(MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String fileName = file.getOriginalFilename();
        try {
            List<String> methodlist = new ArrayList<>();
            methodlist.add("SE");
            methodlist.add("TE");
            methodlist.add("ATAC");
            methodlist.add("Silencer");
            methodlist.add("Promoter");
            methodlist.add("BETA");
            methodlist.add("Genemapper");
            methodlist.add("Curate");
            methodlist.add("Knock");
            methodlist.add("M_SE");
            methodlist.add("M_TE");
            methodlist.add("M_ATAC");
            methodlist.add("M_Silencer");
            methodlist.add("M_Promoter");

            mv.addObject("input", "9");
            mv.addObject("methods", methodlist);
            mv.addObject("userID","userID");
            mv.setViewName("messages/analysis1Result");
            return mv;
        } catch (Exception e) {
            List<Analysis1Result> list = new ArrayList<>();
            mv.addObject("data", list);
            mv.setViewName("messages/analysis1Result");
            return mv;
        }

    }
    @RequestMapping("analysisgetresult")
    @ResponseBody
    public Map analysis1_1(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        Tools tool = new Tools();
        String method = request.getParameter("method");
        String userID = request.getParameter("userID");
        List<Analysis1Result> list = new ArrayList<>();
//        list = tool.readAnalysis1Result("C:/Users/ZLW/Desktop/result.txt",method);
        list = tool.readAnalysis1Result(outpath + userID + "/result.txt",method);
        map.put("data",list);
        return map;
    }

    @RequestMapping("analysisgetresultgraph")
    @ResponseBody
    public Map analysisgetresultgraph(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        Tools tool = new Tools();
        String method = request.getParameter("method");
        String userID = request.getParameter("userID");
        map = tool.readAnalysis1Resultname(outpath + userID + "/result.txt",method);
//        map = tool.readAnalysis1Resultname("C:/Users/ZLW/Desktop/result.txt",method);
        return map;
    }

    @RequestMapping("analysisgetresultgraphbulle")
    @ResponseBody
    public Map analysisgetresultgraphbulle(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        Tools tool = new Tools();
        String method = request.getParameter("method");
        String userID = request.getParameter("userID");

        map = tool.readAnalysis1Resultname2(outpath + userID + "/result.txt",method);
//        map = tool.readAnalysis1Resultname2("C:/Users/ZLW/Desktop/result.txt",method);
        return map;
    }
}
