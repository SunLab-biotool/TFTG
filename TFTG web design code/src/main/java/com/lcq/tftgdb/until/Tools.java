package com.lcq.tftgdb.until;

import com.lcq.tftgdb.mapper.Analysis;
import com.lcq.tftgdb.pojo.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Tools {

    public static void changeRAnalysis2Nospe(String outpath, String userID, String weight) throws IOException {
        int row1 = 5;//要修改的行
        int row2 = 7;//要修改的行
        BufferedReader in = new BufferedReader(new FileReader(outpath + "ana2_nospecificDefult.R"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/ana2_nospecific.R")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("a", weight));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("folder", userID));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static void changeRAnalysis2(String outpath, String userID, String method, String cellline) throws IOException {
        int row1 = 4;//要修改的行
        int row2 = 5;
        int row3 = 7;
        BufferedReader in = new BufferedReader(new FileReader(outpath + "ana2_specificDefult.R"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/ana2_specific.R")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("a", method));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("b", cellline));  //替换abc成def
            } else if (count == row3) {
                out.println(line.replace("folder", userID));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static void changeRAnalysis1(String outpath, String userID, String adjust, String threshold, String gene) throws IOException {
        int row1 = 2;//要修改的行
        int row2 = 3;
        int row3 = 4;
        int row4 = 5;
        BufferedReader in = new BufferedReader(new FileReader(outpath + "ana1Defult.R"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/ana1.R")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("genes", gene));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("fdr", adjust));  //替换abc成def
            } else if (count == row3) {
                out.println(line.replace("yuzhi", threshold));  //替换abc成def
            } else if (count == row4) {
                out.println(line.replace("folder", userID));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static void changeRPathwayAnaChlid(String outpath, String userID, String x, String y) throws IOException {
        int row1 = 4;//要修改的行
        int row2 = 5;
        int row3 = 6;
        int row4 = 7;
        BufferedReader in = new BufferedReader(new FileReader(outpath + "ana32Defult.R"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/ana32.R")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("gene", x));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("tfs", y));  //替换abc成def
            } else if (count == row3) {
                out.println(line.replace("file", outpath));  //替换abc成def
            } else if (count == row4) {
                out.println(line.replace("name", userID));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static void changeRPathwayAna(String outpath, String userID, String adjust, String threshold, String gene) throws IOException {
        int row1 = 2;//要修改的行
        int row2 = 3;
        int row3 = 4;
        int row4 = 6;
        BufferedReader in = new BufferedReader(new FileReader(outpath + "pathwayDefult.R"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/pathwayana.R")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("fdr", adjust));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("yuzhi", threshold));  //替换abc成def
            } else if (count == row3) {
                out.println(line.replace("genes", gene));  //替换abc成def
            } else if (count == row4) {
                out.println(line.replace("name", userID));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static void changesh(String outpath, String userID) throws Exception {
        String rfile = "Rscript " + outpath + userID + "/pathwayana.R";
        Tools.createFile(outpath + userID + "/runpathway.sh", rfile);
    }

    public static void changeshana32(String outpath, String userID) throws Exception {
        String rfile = "Rscript " + outpath + userID + "/ana32.R";
        Tools.createFile(outpath + userID + "/runana32.sh", rfile);
    }

    public static void changeananos2sh(String outpath, String userID) throws Exception {
        String rfile = "Rscript " + outpath + userID + "/ana2_nospecific.R";
        Tools.createFile(outpath + userID + "/runananos2.sh", rfile);
    }

    public static void changeanas2sh(String outpath, String userID) throws Exception {
        String rfile = "Rscript " + outpath + userID + "/ana2_specific.R";
        Tools.createFile(outpath + userID + "/runana2.sh", rfile);
    }

    public static void changeanash(String outpath, String userID) throws Exception {
        String rfile = "Rscript " + outpath + userID + "/ana1.R";
        Tools.createFile(outpath + userID + "/run.sh", rfile);
    }

    public static void bedtoolsChange(String outpath, String chipmethod, String userID, String chromosome) throws IOException {
        int row1 = 3;//要修改的行
        int row2 = 4;
        int row3 = 5;
        BufferedReader in = new BufferedReader(new FileReader(outpath + "/bedtoolsDefult.sh"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outpath + userID + "/bedtools.sh")));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            if (count == row1) {
                out.println(line.replace("chip", chipmethod));  //替换abc成def
            } else if (count == row2) {
                out.println(line.replace("a", userID));  //替换abc成def
            } else if (count == row3) {
                out.println(line.replace("a", chromosome));  //替换abc成def
            } else {
                out.println(line);
            }
            count++;
        }
        in.close();
        out.close();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    public static String runShell(String shpath) throws Exception {
        if (shpath == null || shpath.equals("")) {
            return "shpath is empty";
        }
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();
        ps.destroy();
        return "shell run end";
    }

    public static List<String> inputout(String outDrugpath) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            String[] arr = line.split("\t");
            list.add(arr[0]);
            count++;
        }
        in.close();
        return list;
    }

    public static List<Analysis2ResultTab> readAnalysis2Result2Tab(String outDrugpath) throws IOException {
        Analysis2ResultTab result = null;
        List<Analysis2ResultTab> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            result = new Analysis2ResultTab();
            String[] arr = line.split("\t");
            result.setGene(arr[0]);
            result.setDegree(arr[1]);
            result.setBetweenness(arr[2]);
            result.setCloseness(arr[3]);
            result.setTfOrNot(arr[4]);
            result.setInputOrNot(arr[5]);
            list.add(result);
            count++;
        }
        in.close();
        if (list != null) list.remove(0);
        return list;
    }

    /*读取network文件*/
    public static Map readAnalysis2Result2(String outDrugpath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        List<String> tflist = new ArrayList<>();
        List<String> genelist = new ArrayList<>();
        List<Edgechart> edgelist = new ArrayList<>();
        Edgechart edge = null;
        while ((line = in.readLine()) != null) {
            edge = new Edgechart();
            String[] arr = line.split("\t");
            tflist.add(arr[0]);
            genelist.add(arr[1]);
            edge.setSource(arr[0] + "_tf");
            edge.setTarget(arr[1] + "_gene");
            edgelist.add(edge);
        }
        in.close();
        /*对第二列基因列去重*/
        tflist = tflist.stream().distinct().collect(Collectors.toList());
        genelist = genelist.stream().distinct().collect(Collectors.toList());
        Map map = new HashMap();
        map.put("tflist", tflist);
        map.put("genelist", genelist);
        map.put("edge", edgelist);
        return map;
    }

    public static List<Analysis1Result> readAnalysis1Result(String outDrugpath,String method) throws IOException {
        Analysis1Result result = null;
        List<Analysis1Result> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            result = new Analysis1Result();
            String[] arr = line.split("\t");
            if (arr[2].equals(method)){
                result.setTf(arr[0]);
                result.setSampleid(arr[1]);
                result.setMethod(arr[2]);
                result.setBiosample_name(arr[3]);
                result.setCore_enrichment(arr[4]);
                result.setGenenumber(arr[5]);
                result.setPvalue(arr[6]);
                result.setFdr(arr[7]);
                list.add(result);
            }
            count++;
        }
        in.close();

        return list;
    }

    public static Map readAnalysis1Resultname2(String outDrugpath,String method) throws IOException {
        Analysis1Result result = null;
        List<String> namelist = new ArrayList<>();
        List<String[]> summlist = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            result = new Analysis1Result();
            String[] arr = line.split("\t");
            if (arr[2].equals(method)){
                namelist.add(arr[1]);
                String[] str = new String[]{String.valueOf(-Math.log(Double.parseDouble(arr[6]))),arr[1],arr[5]};
                summlist.add(str);
            }
            count++;
        }
        in.close();
        Map map = new HashMap();
        map.put("namelist",namelist);
        map.put("summ",summlist);
        return map;
    }

    public static Map readAnalysis1Resultname(String outDrugpath,String method) throws IOException {
        Analysis1Result result = null;
        List<String> namelist = new ArrayList<>();
        List<Double> numlist = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            result = new Analysis1Result();
            String[] arr = line.split("\t");
            if (arr[2].equals(method)){
                namelist.add(arr[1]);
                numlist.add(-Math.log(Double.parseDouble(arr[6])));
            }
            count++;
        }
        in.close();
        Map map = new HashMap();
        map.put("namelist",namelist);
        map.put("numlist",numlist);
        return map;
    }
    public static List<All> readBedtoolsResult(String outDrugpath) throws IOException {
        All result = null;
        List<All> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        while ((line = in.readLine()) != null) {
            result = new All();
            String[] arr = line.split("\t");
            result.setCol1(arr[0]);
            result.setCol2(arr[1]);
            result.setCol3(arr[2]);
            result.setCol4(arr[3]);
            result.setCol5(arr[4]);
            result.setCol6(arr[5]);
            result.setCol7(arr[6]);
            result.setCol8(arr[7]);
            result.setCol9(arr[8]);
            result.setCol10(arr[9]);
            result.setCol11(arr[10]);
            result.setCol12(arr[11]);
            result.setCol13(arr[12]);
            result.setCol14(arr[13]);
            result.setCol15(arr[14]);
            result.setCol16(arr[15]);
            result.setCol17(arr[16]);
            list.add(result);
        }
        in.close();
        return list;
    }

    public static List<All> readBedtoolsGenemapperResult(String outDrugpath) throws IOException {
        All result = null;
        List<All> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        while ((line = in.readLine()) != null) {
            result = new All();
            String[] arr = line.split("\t");
            result.setCol1(arr[0]);
            result.setCol2(arr[1]);
            result.setCol3(arr[2]);
            result.setCol4(arr[3]);
            result.setCol5(arr[4]);
            result.setCol6(arr[5]);
            result.setCol7(arr[6]);
            result.setCol8(arr[7]);
            result.setCol9(arr[8]);
            result.setCol10(arr[9]);
            result.setCol11(arr[10]);
            list.add(result);
        }
        in.close();
        return list;
    }

    public static List<All> readBedtoolsBETAResult(String outDrugpath) throws IOException {
        All result = null;
        List<All> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        while ((line = in.readLine()) != null) {
            result = new All();
            String[] arr = line.split("\t");
            result.setCol1(arr[0]);
            result.setCol2(arr[1]);
            result.setCol3(arr[2]);
            result.setCol4(arr[3]);
            result.setCol5(arr[4]);
            result.setCol6(arr[5]);
            result.setCol7(arr[6]);
            result.setCol8(arr[7]);
            result.setCol9(arr[8]);
            result.setCol10(arr[9]);
            result.setCol11(arr[10]);
            result.setCol12(arr[11]);
            list.add(result);
        }
        in.close();
        return list;
    }

    public static List<All> readBedtoolsPromoterResult(String outDrugpath) throws IOException {
        All result = null;
        List<All> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        while ((line = in.readLine()) != null) {
            result = new All();
            String[] arr = line.split("\t");
            result.setCol1(arr[0]);
            result.setCol2(arr[1]);
            result.setCol3(arr[2]);
            result.setCol4(arr[3]);
            result.setCol5(arr[4]);
            result.setCol6(arr[5]);
            result.setCol7(arr[6]);
            result.setCol8(arr[7]);
            result.setCol9(arr[8]);
            result.setCol10(arr[9]);
            result.setCol11(arr[10]);
            result.setCol12(arr[11]);
            result.setCol13(arr[12]);
            list.add(result);
        }
        in.close();
        return list;
    }

    public static List<PathwayChlidTf> readPathwayChlidTf(String outDrugpath) throws IOException {
        PathwayChlidTf result = null;
        List<PathwayChlidTf> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        while ((line = in.readLine()) != null) {
            String[] arr = line.split("\t");
            if (Float.parseFloat(arr[4])<0.05){
                result = new PathwayChlidTf();
                result.setTf(arr[0]);
                result.setRank(arr[1]);
                result.setAnnotated_gene(arr[2]);
                result.setAnnotated_gene_number(arr[3]);
                result.setP_value(arr[4]);
                result.setFdr(arr[5]);
                result.setNum1(arr[6]);
                result.setNum2(arr[7]);
                result.setNum3(arr[8]);
                list.add(result);
            }
        }
        if (list != null) list.remove(0);
        in.close();
        return list;
    }

    public static List<PathwayResult> readPathwayResult(String outDrugpath) throws IOException {
        PathwayResult result = null;
        List<PathwayResult> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(outDrugpath));
        String line;
        int count = 1;
        while ((line = in.readLine()) != null) {
            result = new PathwayResult();
            String[] arr = line.split("\t");
            result.setPathway_ID(arr[0]);
            result.setId(arr[0]);
            result.setPathway_name(arr[1]);
            result.setPathway_source(arr[2]);
            result.setAnnotated_gene(arr[3]);
            result.setAnnotated_gene_number(arr[4]);
            result.setTotal_gene_number(arr[5]);
            result.setTertf(arr[6]);
            if (arr[6] != null || arr[6] != "") {
                System.out.println(arr[6]);
                String[] tfarr = arr[6].split(";");
                String str = "";
                for (int j = 0; j < tfarr.length; j++) {
                    String aherf1 = "";
                    aherf1 = "<a href='browserDetail?symbol=" + tfarr[j] + "'>" + tfarr[j] + "</a>;";
                    str += aherf1;
                }
                result.setThe_terminal_tf(str + "<span class='glyphicon glyphicon-resize-small' onclick='show(\"" + arr[0] + "\",\"all\")' id='" + arr[0] + "all'></span>");
                if (tfarr.length >= 3) {
                    String strshort = "";
                    for (int j = 0; j < 3; j++) {
                        String aherf2 = "";
                        aherf2 = "<a href='browserDetail?symbol=" + tfarr[j] + "'>" + tfarr[j] + "</a>;";
                        strshort += aherf2;
                    }
                    result.setThe_terminal_tf_short(strshort + "<span class='glyphicon glyphicon-resize-full' onclick='show(\"" + arr[0] + "\",\"short\")' id='" + arr[0] + "short'></span>");
                } else {
                    String strshort = "";
                    for (int j = 0; j < tfarr.length; j++) {
                        String aherf2 = "";
                        aherf2 = "<a href='browserDetail?symbol=" + tfarr[j] + "'>" + tfarr[j] + "</a>;";
                        strshort += aherf2;
                    }
                    result.setThe_terminal_tf_short(strshort + "<span class='glyphicon glyphicon-resize-full' onclick='show(\"" + arr[0] + "\",\"short\")' id='" + arr[0] + "short'></span>");
                }
            }
            result.setTf_number(arr[7]);
            result.setP_value(arr[8]);
            result.setFdr(arr[9]);
            list.add(result);
            count++;
        }
        in.close();
        if (list != null) list.remove(0);
        return list;
    }

    public static void createFile(String path, String str) throws Exception {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        file.setExecutable(true);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write(str);
        bf.newLine();
        bf.flush();
        bf.close();
    }
}
