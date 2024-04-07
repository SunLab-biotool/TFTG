package com.lcq.tftgdb.controller;

import com.lcq.tftgdb.mapper.Network;
import com.lcq.tftgdb.pojo.*;
import com.lcq.tftgdb.until.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@Controller
public class NetworkController {
    @Autowired(required = false)
    private Network network;
    String outpath = "/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/";
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

    public List<String> getTableList(String tfs,String method) throws Exception {
        List<String> tableList = new ArrayList<>();
        if (method.equals("C_SE")){
            method = "SE";
        }else if (method.equals("C_TE")){
            method = "TE";
        }else if (method.equals("C_ATAC")){
            method = "ATAC";
        }else if (method.equals("C_Silencer")){
            method = "Silencer";
        }else if (method.equals("C_Promoter")){
            method = "Promoter";
        }else if (method.equals("C_BETA")){
            method = "BETA";
        }else if (method.equals("C_Genemapper")){
            method = "Genemapper";
        }
        String[] tfarr = tfs.split(",");
        for (int i=0;i<tfarr.length;i++){
            tableList.add(tfarr[i]+"_"+method);
        }
        return tableList;
    }

    public String networktable1(String method,String cellline,String inputgenes) throws Exception {
        Map map = new HashMap();

        Tools tool = new Tools();
        String userID = "usr_" + tool.getUUID();
        if (cellline==""||cellline=="null"||cellline==null){
            cellline = "";
        }
        System.out.println(method+","+cellline+","+inputgenes);
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
        return userID;
    }
    @RequestMapping("graph1")
    @ResponseBody
    public Map graph1(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        String method = request.getParameter("method");
        String sample = request.getParameter("sample");
        String cellline = request.getParameter("cellline");
        String gene = request.getParameter("gene");
        String freq = request.getParameter("freq");
        String tfs = request.getParameter("tfs");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        if (score.equals("all")){
            score = "10000";
        }
        System.out.println(method + "," + sample + "," + gene + "," + freq + "," + tfs+","+score+","+cellline+","+input);
        /*跑network下的表*/
        String userID = networktable1(method,cellline,input);
        /*跑network下的表*/

        String[] tfarr = tfs.split(",");
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();

        List<String> tablist = getTableList(tfs,method);

        List<String> seidlist = new ArrayList<>();
        List<String> seid = new ArrayList<>();
        List<String> genelist = new ArrayList<>();
        List<String> promotergene = new ArrayList<>();
        List<String> mappergene = new ArrayList<>();
        List<String> mgene = new ArrayList<>();
        List<String> betagene = new ArrayList<>();
        List<String> curategene = new ArrayList<>();
        List<String> knockgene = new ArrayList<>();
        List<String> mpromotergene = new ArrayList<>();
        List<String> genes = new ArrayList<>();
        Edgechart edge = null;
        String secol = "";
        if (method.equals("C_SE")){
            secol = "SEID";
        }else if (method.equals("C_TE")){
            secol = "TEID";
        }else if (method.equals("C_ATAC")){
            secol = "ATACID";
        }else if (method.equals("C_Silencer")){
            secol = "SilencerID";
        }
        if (method.equals("Curate")){
            for (int i=0;i<tfarr.length;i++){
                curategene = network.getgeneCurate(tfarr[i],cellline);
                for (int j = 0; j < curategene.size(); j++) {
                    String str = curategene.get(j).substring(1, curategene.get(j).length() - 1);
                    String[] strArr = str.split(",");
                    for (int k = 0; k < strArr.length; k++) {
                        if (strArr[k].length() != 0) {
                            edge = new Edgechart();
                            edge.setSource(tfarr[i]+"_tf");
                            edge.setTarget(strArr[k]);
                            edgeList.add(edge);
                            genes.add(strArr[k]);
                        }
                    }
                }
            }
            genelist.addAll(genes);
        }else if (method.equals("Knock")){
            for (int i=0;i<tfarr.length;i++){
                knockgene = network.getgeneknock(tfarr[i],cellline,score);
                for (int j = 0; j < knockgene.size(); j++) {
                    String str = knockgene.get(j).substring(1, knockgene.get(j).length() - 1);
                    String[] strArr = str.split(",");
                    for (int k = 0; k < strArr.length; k++) {
                        if (strArr[k].length() != 0) {
                            edge = new Edgechart();
                            edge.setSource(tfarr[i]+"_tf");
                            edge.setTarget(strArr[k]);
                            edgeList.add(edge);
                            genes.add(strArr[k]);
                        }
                    }
                }
            }
            genelist.addAll(genes);
        }else if (method.equals("M_Promoter")){
            for (int i=0;i<tfarr.length;i++){
                mpromotergene = network.getgenempromoter(tfarr[i],score);
                for (int j = 0; j < mpromotergene.size(); j++) {
                    String str = mpromotergene.get(j).substring(1, mpromotergene.get(j).length() - 1);
                    String[] strArr = str.split(",");
                    for (int k = 0; k < strArr.length; k++) {
                        if (strArr[k].length() != 0) {
                            edge = new Edgechart();
                            edge.setSource(tfarr[i]+"_tf");
                            edge.setTarget(strArr[k]);
                            edgeList.add(edge);
                            genes.add(strArr[k]);
                        }
                    }
                }
            }
            genelist.addAll(genes);
        }else {
            for (int i=0;i<tablist.size();i++){
                if (method.equals("C_SE")||method.equals("C_TE")||method.equals("C_ATAC")||method.equals("C_Silencer")){
                    try{
                        seid = network.getSe(tablist.get(i),sample.split(","),freq,secol);
                        seidlist.addAll(seid);
                    }catch (Exception e){}
                }

                List<SEAndGene> seAndGenes = new ArrayList<>();
                if (gene.equals("overlap_gene")){
                    if (method.equals("C_Genemapper")){
                        try{
                            mappergene.addAll(network.getGeneBymapperoverlap(tablist.get(i),sample.split(",")));
                        }catch (Exception e){}
                    }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
                        try{
                            mgene.addAll(network.getGeneBymoverlap(method,tfarr[i],cellline,score));
                        }catch (Exception e){}
                    }else {
                        try {
                            List<SEAndGene> overlap = network.getGeneByoverlap(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(overlap);
                        }catch (Exception e){}
                    }
                }else if (gene.equals("proximal_gene")){
                    if (method.equals("C_Genemapper")){
                        try {
                            mappergene.addAll(network.getGeneBymapperproximal(tablist.get(i),sample.split(",")));
                        }catch (Exception e){}
                    }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
                        try{
                            mgene.addAll(network.getGeneBymproximal(method,tfarr[i],cellline,score));
                        }catch (Exception e){}
                    }else {
                        try{
                            List<SEAndGene> proximal = network.getGeneByproximal(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(proximal);
                        }catch (Exception e){}
                    }
                }else if (gene.equals("closest_gene")){
                    if (method.equals("C_Genemapper")){
                        try{
                            mappergene.addAll(network.getGeneBymapperclosest(tablist.get(i),sample.split(",")));
                        }catch (Exception e){}
                    }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
                        try{
                            mgene.addAll(network.getGeneBymclosest(method,tfarr[i],cellline,score));
                        }catch (Exception e){}
                    }else {
                        try {
                            List<SEAndGene> closest = network.getGeneByclosest(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(closest);
                        }catch (Exception e){}
                    }
                }else if (gene.equals("all")){
                    if (method.equals("C_Genemapper")){
                        try {
                            mappergene.addAll(network.getGeneBymapperoverlap(tablist.get(i),sample.split(",")));
                            mappergene.addAll(network.getGeneBymapperproximal(tablist.get(i),sample.split(",")));
                            mappergene.addAll(network.getGeneBymapperclosest(tablist.get(i),sample.split(",")));
                        }catch (Exception e){}
                    }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
                        mgene.addAll(network.getGeneBymoverlap(method,tfarr[i],cellline,score));
                        mgene.addAll(network.getGeneBymproximal(method,tfarr[i],cellline,score));
                        mgene.addAll(network.getGeneBymclosest(method,tfarr[i],cellline,score));
                    }else {
                        try {
                            List<SEAndGene> overlap = network.getGeneByoverlap(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(overlap);
                            List<SEAndGene> proximal = network.getGeneByproximal(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(proximal);
                            List<SEAndGene> closest = network.getGeneByclosest(tablist.get(i),sample.split(","),freq,secol);
                            seAndGenes.addAll(closest);
                        }catch (Exception e){}
                    }
                }else if (gene.equals("genepromoter")){
                    try {
                        promotergene = network.getGeneBygene(tablist.get(i),sample.split(","),freq);
                    }catch (Exception e){}
                }else if (gene.equals("genebeta")){
                    if (score.equals("all")){
                        score = "10000";
                    }
                    try {
                        betagene = network.getGeneBybetagene(tablist.get(i),sample.split(","),score);
                    }catch (Exception e){}
                }

                /*genelist edge*/
                if (method.equals("C_Promoter")){
                    for (int j = 0; j < promotergene.size(); j++) {
                        String str = promotergene.get(j).substring(1, promotergene.get(j).length() - 1);
                        String[] strArr = str.split(",");
                        for (int k = 0; k < strArr.length; k++) {
                            if (strArr[k].length() != 0) {
                                edge = new Edgechart();
                                edge.setSource(tfarr[i]+"_tf");
                                edge.setTarget(strArr[k]);
                                edgeList.add(edge);
                                genes.add(strArr[k]);
                            }
                        }
                    }
                }else if (method.equals("C_Genemapper")){
                    for (int j = 0; j < mappergene.size(); j++) {
                        String str = mappergene.get(j).substring(1, mappergene.get(j).length() - 1);
                        String[] strArr = str.split(",");
                        for (int k = 0; k < strArr.length; k++) {
                            if (strArr[k].length() != 0) {
                                edge = new Edgechart();
                                edge.setSource(tfarr[i]+"_tf");
                                edge.setTarget(strArr[k]);
                                edgeList.add(edge);
                                genes.add(strArr[k]);
                            }
                        }
                    }
                }else if (method.equals("C_BETA")){
                    for (int j = 0; j < betagene.size(); j++) {
                        String str = betagene.get(j).substring(1, betagene.get(j).length() - 1);
                        String[] strArr = str.split(",");
                        for (int k = 0; k < strArr.length; k++) {
                            if (strArr[k].length() != 0) {
                                edge = new Edgechart();
                                edge.setSource(tfarr[i]+"_tf");
                                edge.setTarget(strArr[k]);
                                edgeList.add(edge);
                                genes.add(strArr[k]);
                            }
                        }
                    }
                }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
                    for (int j = 0; j < mgene.size(); j++) {
                        String str = mgene.get(j).substring(1, mgene.get(j).length() - 1);
                        String[] strArr = str.split(",");
                        for (int k = 0; k < strArr.length; k++) {
                            if (strArr[k].length() != 0) {
                                edge = new Edgechart();
                                edge.setSource(tfarr[i]+"_tf");
                                edge.setTarget(strArr[k]);
                                edgeList.add(edge);
                                genes.add(strArr[k]);
                            }
                        }
                    }
                }else {
                    for (int j = 0; j < seAndGenes.size(); j++) {
                        String str = seAndGenes.get(j).getGene().substring(1, seAndGenes.get(j).getGene().length() - 1);
                        String[] strArr = str.split(",");
                        for (int k = 0; k < strArr.length; k++) {
                            if (strArr[k].length() != 0) {
                                edge = new Edgechart();
                                edge.setSource(seAndGenes.get(j).getSe());
                                edge.setTarget(strArr[k]);
                                edgeList.add(edge);
                                genes.add(strArr[k]);
                            }
                        }
                    }
                }

                genelist.addAll(genes);

                /*selist edge*/
                if (method.equals("C_SE")||method.equals("C_TE")||method.equals("C_ATAC")||method.equals("C_Silencer")){
                    for (int j=0;j<seid.size();j++){
                        edge = new Edgechart();
                        edge.setSource(tfarr[i]+"_tf");
                        edge.setTarget(seid.get(j));
                        edgeList.add(edge);
                    }
                }
            }
        }


        /* SE node start */
        HashSet h = new HashSet(seidlist);
        seidlist.clear();
        seidlist.addAll(h);
        /* tf node start */
        for (int i = 0; i < tfarr.length; i++) {
            Node node = new Node();
            node.setId(tfarr[i]+"_tf");
            node.setName(tfarr[i]);
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
            nodeList.add(node);
        }
        /* tf node end */
        /*此处三行为seidlist去重*/
        if (method.equals("C_SE")||method.equals("C_TE")||method.equals("C_ATAC")||method.equals("C_Silencer")){
            for (int j = 0; j < seidlist.size(); j++) {
                Node node = new Node();
                node.setId(seidlist.get(j));
                node.setName(seidlist.get(j));
                node.setCategory(1);
                double x = minb + new Random().nextDouble() * (maxb - minb);
                node.setX(x);
                if (Math.abs(node.getX()) < r3) {
                    Double ymin3 = (Double) Math.sqrt(r3 * r3 - node.getX() * node.getX());
                    Double ymax3 = (Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                    Double ymax4 = -(Double) Math.sqrt(r3 * r3 - node.getX() * node.getX());
                    Double ymin4 = -(Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());

                    Double y3 = ymin3 + new Random().nextDouble() * (ymax3 - ymin3);
                    Double y4 = ymin4 + new Random().nextDouble() * (ymax4 - ymin4);
                    Double y;
                    if (new Random().nextFloat() < 0.5) {
                        y = y3;
                    } else {
                        y = y4;
                    }
                    node.setY(y);
                } else {
                    Double ymin3 = -(Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                    Double ymax3 = (Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                    Double y = (double) (ymin3 + new Random().nextDouble() * (ymax3 - ymin3));
                    node.setY(y);
                }
                nodeList.add(node);
            }
        }
        /* SE node end */


        /* gene node start */
        HashSet hh = new HashSet(genelist);
        genelist.clear();
        genelist.addAll(hh);
        /*此处三行为genelist去重*/
        for (int j = 0; j < genelist.size(); j++) {
            Node node = new Node();
            node.setId(genelist.get(j));
            node.setName(genelist.get(j));
            node.setCategory(2);
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
            nodeList.add(node);
        }
        /* gene node end */

        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("cellLineMParamUrl")
    @ResponseBody
    public Map cellLineMParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");

        if (method.equals("M_SE")){
            cellLineList = network.getCellLineMse(tfs.split(","));
        }else if (method.equals("M_TE")){
            cellLineList = network.getCellLineMte(tfs.split(","));
        }else if (method.equals("M_ATAC")){
            cellLineList = network.getCellLineMatac(tfs.split(","));
        }else if (method.equals("M_Silencer")){
            cellLineList = network.getCellLineMsilencer(tfs.split(","));
        }
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("cellLineCbetaParamUrl")
    @ResponseBody
    public Map cellLineCbetaParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        cellLineList = network.getCellLineCbeta(tfs.split(","));
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("cellLineatgParamUrl")
    @ResponseBody
    public Map cellLineatgParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        if (method.equals("C_Genemapper")){
            cellLineList = network.getCellLineCmapper();
        }else if (method.equals("C_ATAC")){
            cellLineList = network.getCellLineatac();
        }else if (method.equals("C_TE")){
            cellLineList = network.getCellLineCte2();
        }
        map.put("data", cellLineList);
        return map;
    }

    @RequestMapping("cellLineCParamUrl")
    @ResponseBody
    public Map cellLineCParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();

        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");

        if (method.equals("C_SE")){
            cellLineList = network.getCellLineCse(tfs.split(","));
        }else if (method.equals("C_TE")){
            cellLineList = network.getCellLineCte(tfs.split(","));
        }else if (method.equals("C_ATAC")){
            cellLineList = network.getCellLineCatac(tfs.split(","));
        }else if (method.equals("C_Silencer")){
            cellLineList = network.getCellLineCsilencer(tfs.split(","));
        }
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("cellLineCproParamUrl")
    @ResponseBody
    public Map cellLineCproParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        cellLineList = network.getCellLineCpro(tfs.split(","));
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("cellLineCurateParamUrl")
    @ResponseBody
    public Map cellLineCurateParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        cellLineList = network.getCellLineCurate2(tfs.split(","));
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("cellLineKnockParamUrl")
    @ResponseBody
    public Map cellLineKnockParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        cellLineList = network.getCellLineKnock2(tfs.split(","));
        map.put("data", cellLineList);
        return map;
    }
    @RequestMapping("freqCproParamUrl")
    @ResponseBody
    public Map freqCproParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> freqList = new ArrayList<>();

        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        freqList = network.getfreqCpro(tfs.split(","),cellLine);

        map.put("data", freqList);
        return map;
    }
    @RequestMapping("freqCteParamUrl")
    @ResponseBody
    public Map freqCteParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> freqList = new ArrayList<>();

        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String gene_type = request.getParameter("genetype");
        String table = "";
        try {
            table = "C_TE_"+network.getidtecell(cellLine);
            freqList = network.getFreqte2(tfs.split(","),cellLine,table,gene_type);
        }catch (Exception e){
            freqList = new ArrayList<>();
        }
        map.put("data", freqList);
        return map;
    }
    @RequestMapping("freqCParamUrl")
    @ResponseBody
    public Map freqCParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> freqList = new ArrayList<>();

        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        if (method.equals("C_SE")){
            freqList = network.getfreqCse(tfs.split(","),cellLine);
        }else if (method.equals("C_TE")){
            freqList = network.getfreqCte(tfs.split(","),cellLine);
        }else if (method.equals("C_ATAC")){
            freqList = network.getfreqCatac(tfs.split(","),cellLine);
        }else if (method.equals("C_Silencer")){
            freqList = network.getfreqCsilencer(tfs.split(","),cellLine);
        }
        map.put("data", freqList);
        return map;
    }
    @RequestMapping("freqatgParamUrl")
    @ResponseBody
    public Map freqatgParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> freqList = new ArrayList<>();

        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String cellid = "";
        if (method.equals("C_Genemapper")){
            cellid = network.getidcell(cellLine);
        }else if (method.equals("C_TE")){
            freqList = network.getfreqCte(tfs.split(","),cellLine);
        }else if (method.equals("C_ATAC")){
            freqList = network.getfreqCatac(tfs.split(","),cellLine);
        }
        map.put("data", freqList);
        return map;
    }
    @RequestMapping("graphMParamUrl")
    @ResponseBody
    public Map graphMParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodeseid = new ArrayList<>();
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);
        String table = "";
        if (method.equals("M_SE")){
            table = "motif_SE_gene_tf";
        }else if (method.equals("M_TE")){
            table = "motif_TE_gene_tf";
        }else if (method.equals("M_ATAC")){
            table = "motif_ATAC_gene_tf";
        }else if (method.equals("M_Silencer")){
            table = "motif_Silencer_gene_tf";
        }

        for (int i=0;i<nodegene.size();i++){
            if (genetype.equals("all")){
                List<String> seid = network.getMseidall(table,nodegene.get(i),cellLine,score);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getMtfall(table,nodegene.get(i),cellLine,score,seid.get(j));
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }else {
                List<String> seid = network.getMseid(table,nodegene.get(i),genetype,cellLine,score);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getMtf(table,nodegene.get(i),genetype,cellLine,score,seid.get(j));
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }
        }

        HashSet h1 = new HashSet(nodeseid);
        nodeseid.clear();
        nodeseid.addAll(h1);
        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */
        /* seid node start*/
        nodeList.addAll(getnodelist34(nodeseid));
        /* seid node end */
        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }

    @RequestMapping("graphCParamUrl")
    @ResponseBody
    public Map graphCParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodeseid = new ArrayList<>();
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);
        String table = "";
        if (method.equals("C_SE")){
            table = "C_SE_gene_tf";
        }else if (method.equals("C_TE")){
            table = "C_TE_gene_tf";
        }else if (method.equals("C_ATAC")){
            table = "C_ATAC_gene_tf";
        }else if (method.equals("C_Silencer")){
            table = "C_Silencer_gene_tf";
        }

        for (int i=0;i<nodegene.size();i++){
            if (genetype.equals("all")){
                List<String> seid = network.getCseidall(table,nodegene.get(i),cellLine,freq);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getCtfall(table,nodegene.get(i),cellLine,freq,seid.get(j));
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }else {
                List<String> seid = network.getCseid(table,nodegene.get(i),cellLine,freq);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getCtf(table,nodegene.get(i),cellLine,freq,seid.get(j));
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }
        }

        HashSet h1 = new HashSet(nodeseid);
        nodeseid.clear();
        nodeseid.addAll(h1);
        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */
        /* seid node start*/
        nodeList.addAll(getnodelist34(nodeseid));
        /* seid node end */
        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphgenemappParamUrl")
    @ResponseBody
    public Map graphgenemappParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodeseid = new ArrayList<>();
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);
        String table = "";
        if (method.equals("C_Genemapper")){
            String cellid = network.getidcell(cellLine);
            table = "C_Genemapper_"+cellid;
        }

        for (int i=0;i<nodegene.size();i++){
            if (genetype.equals("all")){
                List<String> tf = network.getCmapperall(table,nodegene.get(i),cellLine);
                for (int k=0;k<tf.size();k++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(tf.get(k)+"_tf");
                    edgeList.add(edge);
                    nodetf.add(tf.get(k)+"_tf");
                }
            }else {
                List<String> tf = network.getCmapper(table,nodegene.get(i),cellLine,genetype);
                for (int k=0;k<tf.size();k++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(tf.get(k)+"_tf");
                    edgeList.add(edge);
                    nodetf.add(tf.get(k)+"_tf");
                }
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */
        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphCbetaParamUrl")
    @ResponseBody
    public Map graphCbetaParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);

        for (int i=0;i<nodegene.size();i++){
            List<String> tf = network.getCbetatf(nodegene.get(i),cellLine,score);
            for (int j=0;j<tf.size();j++){
                edge = new Edgechart();
                edge.setSource(nodegene.get(i));
                edge.setTarget(tf.get(j)+"_tf");
                edgeList.add(edge);
                nodetf.add(tf.get(j)+"_tf");
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */

        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphMproParamUrl")
    @ResponseBody
    public Map graphMproParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);

        for (int i=0;i<nodegene.size();i++){
            List<String> tf = network.getMprotf(nodegene.get(i),score);
            for (int j=0;j<tf.size();j++){
                edge = new Edgechart();
                edge.setSource(nodegene.get(i));
                edge.setTarget(tf.get(j)+"_tf");
                edgeList.add(edge);
                nodetf.add(tf.get(j)+"_tf");
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */

        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphCurateParamUrl")
    @ResponseBody
    public Map graphCurateParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);

        for (int i=0;i<nodegene.size();i++){
            List<String> tf = network.getCuratetf(nodegene.get(i),cellLine);
            for (int j=0;j<tf.size();j++){
                edge = new Edgechart();
                edge.setSource(nodegene.get(i));
                edge.setTarget(tf.get(j)+"_tf");
                edgeList.add(edge);
                nodetf.add(tf.get(j)+"_tf");
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */

        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphKnockParamUrl")
    @ResponseBody
    public Map graphKnockParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);

        for (int i=0;i<nodegene.size();i++){
            List<String> tf = network.getKnocktf(nodegene.get(i),cellLine,score);
            for (int j=0;j<tf.size();j++){
                edge = new Edgechart();
                edge.setSource(nodegene.get(i));
                edge.setTarget(tf.get(j)+"_tf");
                edgeList.add(edge);
                nodetf.add(tf.get(j)+"_tf");
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */

        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphCproParamUrl")
    @ResponseBody
    public Map graphCproParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);

        for (int i=0;i<nodegene.size();i++){
            List<String> tf = network.getCprotf(nodegene.get(i),cellLine,freq);
            for (int j=0;j<tf.size();j++){
                edge = new Edgechart();
                edge.setSource(nodegene.get(i));
                edge.setTarget(tf.get(j)+"_tf");
                edgeList.add(edge);
                nodetf.add(tf.get(j)+"_tf");
            }
        }

        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */

        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */
        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("graphCteParamUrl")
    @ResponseBody
    public Map graphCteParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        String cellLine = request.getParameter("cellLine");
        String genetype = request.getParameter("genetype");
        String score = request.getParameter("score");
        String input = request.getParameter("input");
        /*跑network下的表*/
        String userID = networktable1(method,cellLine,input);
        /*跑network下的表*/
        if (score.equals("all")){
            score = "10000";
        }
        String freq = request.getParameter("freq");
        if (freq==null||freq==""){
            freq = "1";
        }
        System.out.println(tfs+","+method+","+cellLine+","+genetype+","+score+","+freq);
        List<String> nodetf = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<String> nodeseid = new ArrayList<>();
        List<Edgechart> edgeList = new ArrayList<>();
        Edgechart edge = null;
        List<String> nodegene = network.getMethodGene(tfs.split(","),method);
        String table = "C_TE_"+network.getidtecell(cellLine);
        for (int i=0;i<nodegene.size();i++){
            if (genetype.equals("all")){
                List<String> seid = network.getCtetfall(table,nodegene.get(i),cellLine,freq);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getCtetfallse(table,nodegene.get(i),cellLine,freq,seid.get(j));
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }else {
                List<String> seid = network.getCtetf22(table,nodegene.get(i),cellLine,freq,genetype);
                for (int j=0;j<seid.size();j++){
                    edge = new Edgechart();
                    edge.setSource(nodegene.get(i));
                    edge.setTarget(seid.get(j));
                    edgeList.add(edge);
                    nodeseid.add(seid.get(j));
                    List<String> tf = network.getCtetf2(table,nodegene.get(i),cellLine,freq,seid.get(j),genetype);
                    for (int k=0;k<tf.size();k++){
                        edge = new Edgechart();
                        edge.setSource(seid.get(j));
                        edge.setTarget(tf.get(k)+"_tf");
                        edgeList.add(edge);
                        nodetf.add(tf.get(k)+"_tf");
                    }
                }
            }
        }

        HashSet h1 = new HashSet(nodeseid);
        nodeseid.clear();
        nodeseid.addAll(h1);
        HashSet h2 = new HashSet(nodetf);
        nodetf.clear();
        nodetf.addAll(h2);
        HashSet h3 = new HashSet(nodegene);
        nodegene.clear();
        nodegene.addAll(h3);
        /* tf node start */
        nodeList.addAll(getnodelist12(nodetf));
        /* tf node end */
        /* seid node start*/
        nodeList.addAll(getnodelist34(nodeseid));
        /* seid node end */
        /* gene node start*/
        nodeList.addAll(getnodelist56(nodegene));
        /* gene node end */

        map.put("node",nodeList);
        map.put("edge",edgeList);
        map.put("userID",userID);
        return map;
    }
    @RequestMapping("cellLineParamUrl")
    @ResponseBody
    public Map cellLineParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        List<String> cellLineList = new ArrayList<>();
        List<SampleAndCellline> sampleAndCelllineList = new ArrayList<>();
        String tfs = request.getParameter("tfs");
        String method = request.getParameter("method");
        if (method.equals("Curate")){
            cellLineList = network.getCellLineCurate(tfs.split(","));
        }else if (method.equals("Knock")){
            cellLineList = network.getCellLineKnock(tfs.split(","));
        }else if (method.equals("M_SE")||method.equals("M_TE")||method.equals("M_ATAC")||method.equals("M_Silencer")){
            cellLineList = network.getCellLineM(tfs.split(","),method);
        }else {
            /*fool judge start*/
            List<String> tableList = getTableList(tfs,method);
            /*fool judge end*/
            List<String> sampleList = new ArrayList<>();
            for (int i=0;i<tableList.size();i++){
                List<String> data = null;
                try{
                    data = network.getSampleID(tableList.get(i));
                }catch (Exception e){
                    data = new ArrayList<>();
                }
                sampleList.addAll(data);
            }
            cellLineList = network.getCellLine(sampleList);
            sampleAndCelllineList = network.getSampleAndCellLine(sampleList);
        }
        map.put("sCList", sampleAndCelllineList);
        map.put("cellLineList", cellLineList);
        return map;
    }
    @RequestMapping("freqParamUrl")
    @ResponseBody
    public Map freqParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String method = request.getParameter("method");
        String sample = request.getParameter("sample");
        String tfs = request.getParameter("tfs");
        List<Integer> data = new ArrayList<>();
        /*fool judge start*/
        List<String> tableList = getTableList(tfs,method);

        for (int i=0;i<tableList.size();i++){
            try {
                data.addAll(network.getFreq(tableList.get(i),sample.split(",")));
            }catch (Exception e){
                data = new ArrayList<>();
            }
        }
        /*fool judge end*/
        List<Integer> datauniq = data.stream().distinct().collect(Collectors.toList());
        map.put("data", datauniq);
        return map;
    }

    @RequestMapping("methodParamUrl")
    @ResponseBody
    public Map methodParamUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        List<String> data = new ArrayList<>();
        String[] tfarr = tfs.split(",");
        data = network.getMethod(tfarr);
        map.put("data", data);
        return map;
    }
    @RequestMapping("methodParamGeneUrl")
    @ResponseBody
    public Map methodParamGeneUrl(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        String tfs = request.getParameter("tfs");
        List<String> data = new ArrayList<>();
        String[] tfarr = tfs.split(",");
        data = network.getMethodBygene(tfarr);
        map.put("data", data);
        return map;
    }
    @RequestMapping("networkResult")
    @ResponseBody
    public ModelAndView networkResult(MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String fileName = "";
        try {
            fileName = file.getOriginalFilename();
        }catch (Exception e){
            mv.setViewName("messages/networkAnalysis");
            return mv;
        }

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
        String rido = request.getParameter("optionsRadiosinline");
        System.out.println(rido+"******************");
        if (rido.equals("nospecificity")){
            mv.setViewName("messages/networkResultNoSpe");
        }else {
            mv.setViewName("messages/networkResult");
        }
        String[] genearr = genes.split("\r\n");
        List<String> tfs = network.getTF(genearr);
        mv.addObject("tfs", tfs);
        mv.addObject("genes", genes);
        return mv;
    }

    @RequestMapping("networkResultGene")
    @ResponseBody
    public ModelAndView networkResultGene(MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String fileName = "";
        try {
            fileName = file.getOriginalFilename();
        }catch (Exception e){
            mv.setViewName("messages/networkAnalysisGene");
            return mv;
        }

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
        String rido = request.getParameter("optionsRadiosinline");
        if (rido.equals("nospecificity")){
            mv.setViewName("messages/networkResultNoSpe");
        }else {
            mv.setViewName("messages/networkResultGene");
        }
        String[] genearr = genes.split("\r\n");

        mv.addObject("tfs", genearr);
        mv.addObject("genes", genes);
        return mv;
    }

    public List<Node> getnodelist12(List<String> list) throws Exception {
        List<Node> nodelist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Node node = new Node();
            node.setId(list.get(i));
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

    public List<Node> getnodelist34(List<String> list) throws Exception {
        List<Node> nodelist = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            Node node = new Node();
            node.setId(list.get(j));
            node.setName(list.get(j));
            node.setCategory(1);
            double x = minb + new Random().nextDouble() * (maxb - minb);
            node.setX(x);
            if (Math.abs(node.getX()) < r3) {
                Double ymin3 = (Double) Math.sqrt(r3 * r3 - node.getX() * node.getX());
                Double ymax3 = (Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                Double ymax4 = -(Double) Math.sqrt(r3 * r3 - node.getX() * node.getX());
                Double ymin4 = -(Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());

                Double y3 = ymin3 + new Random().nextDouble() * (ymax3 - ymin3);
                Double y4 = ymin4 + new Random().nextDouble() * (ymax4 - ymin4);
                Double y;
                if (new Random().nextFloat() < 0.5) {
                    y = y3;
                } else {
                    y = y4;
                }
                node.setY(y);
            } else {
                Double ymin3 = -(Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                Double ymax3 = (Double) Math.sqrt(r4 * r4 - node.getX() * node.getX());
                Double y = (double) (ymin3 + new Random().nextDouble() * (ymax3 - ymin3));
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
            node.setId(list.get(j));
            node.setName(list.get(j));
            node.setCategory(2);
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

}
