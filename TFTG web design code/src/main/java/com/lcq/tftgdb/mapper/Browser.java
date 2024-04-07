package com.lcq.tftgdb.mapper;

import com.github.pagehelper.Page;
import com.lcq.tftgdb.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface Browser {
    @Select({"SELECT DISTINCT cell_line as label,cell_line as value FROM ${table} where overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%')"})
    List<Multselect> getCellType2(String tf, String table, String gene);

    @Select({"SELECT DISTINCT Biosample_name_specific as label,Biosample_name_specific as value FROM TF_chipseq_information WHERE Sample_ID in (SELECT DISTINCT SampleID FROM ${table} WHERE gene LIKE CONCAT('%,',#{gene},',%'))"})
    List<Multselect> getCellType3(String tf, String table, String gene);

    @Select({"SELECT DISTINCT Biosample_name_specific as label,Biosample_name_specific as value FROM TF_chipseq_information WHERE Sample_ID in (SELECT DISTINCT SampleID FROM ${table} WHERE overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"})
    List<Multselect> getCellType4(String tf, String table, String gene);

    @Select({"SELECT DISTINCT sample_biosample_name as label,sample_biosample_name as value FROM Knock WHERE TF = #{tf} and gene LIKE CONCAT('%,',#{gene},',%')"})
    List<Multselect> getCellType5(String tf, String table, String gene);

    @Select({"SELECT DISTINCT Biosample_name_specific as label,Biosample_name_specific as value FROM TF_chipseq_information where Tf_name=#{tf}"})
    List<Multselect> getCellType(String tf);

    @Select({"SELECT DISTINCT cell_line as label,cell_line as value FROM ${motifMethod} where Tf_name=#{tf} and (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"})
    List<Multselect> getCellByMotif(String tf, String motifMethod, String gene);

    @Select({"SELECT DISTINCT tf_name as label,tf_name as value FROM TF_chipseq_information"})
    List<Multselect> getTfNames();

    @Select("<script>"
            + "SELECT SampleID,chr,start,end,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND Biosample_name_specific IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<Genemapper> genemapper(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,se_chr,se_start,se_end,se_source,rol,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<SE> se(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,te_chr,te_start,te_end,te_source,rol,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<TE> te(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,atac_chr,atac_start,atac_end,atac_source,rol,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<ATAC> atac(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,silencer_chr,silencer_start,silencer_end,silencer_source,rol,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<Silencer> silencer(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,promoter_chr,promoter_start,promoter_end,rol,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM gene) as gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND Biosample_name_specific IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<Promoter> promoter(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "SELECT SampleID,tf_chr,tf_start,tf_end,refseqID,distance,score,TF_name,Biosample_name_specific,TRIM(BOTH ',' FROM gene) as gene FROM ${tf_method} LEFT JOIN TF_chipseq_information ON SampleID=Sample_id WHERE " +
            "TF_name=#{symbol} AND Biosample_name_specific IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<BETA> beta(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "select Sample_ID,tf,fc,p_value,up_down,sample_biosample_name,sample_method,TRIM(BOTH ',' FROM gene) as gene from Knock WHERE gene LIKE CONCAT('%,',#{gene},',%') and tf=#{symbol} and sample_biosample_name in "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> AND (gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<Knock> knock(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("SELECT tf,TRIM(BOTH ',' FROM gene) gene,source,pubmedID,biosample_name,repression_Activation FROM Curate WHERE tf=#{symbol} AND gene LIKE CONCAT('%,',#{gene},',%')\n")
    List<Curate> curate(@Param("gene") String gene, @Param("symbol") String symbol);

    @Select("<script>"
            + "select tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,se_chr,se_start,se_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,se_source,motif_id,q_value,p_value,sequence from M_SE where tf_name=#{symbol} AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%')) and cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<MotifSE> motifSE(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "select tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,te_chr,te_start,te_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,te_source,motif_id,q_value,p_value,sequence from M_TE where tf_name=#{symbol} AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%')) and cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<MotifTE> motifTE(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "select tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,atac_chr,atac_start,atac_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,atac_source,motif_id,q_value,p_value,sequence from M_ATAC where tf_name=#{symbol} AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%')) and cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<MotifATAC> motifATAC(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "select tf_motif_chr,tf_name,tf_motif_start,tf_motif_end,silencer_chr,silencer_start,silencer_end,cell_line,silencer_source,motif_id,q_value,p_value,sequence,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene from M_Silencer where tf_name=#{symbol} AND (overlap_gene LIKE CONCAT('%,',#{gene},',%') or proximal_gene LIKE CONCAT('%,',#{gene},',%') or closest_gene LIKE CONCAT('%,',#{gene},',%')) and cell_line IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<MotifSilencer> motifSilencer(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);

    @Select("<script>"
            + "select tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,pro_chr,pro_start,pro_end,TRIM(BOTH ',' FROM  gene) as gene,strand,motif_id,q_value,p_value,sequence from M_Promoter where tf_name=#{symbol} AND (gene LIKE CONCAT('%,',#{gene},',%'))"
            + "</script>")
    List<MotifPromoter> motifPromoter(@Param("gene") String gene, @Param("symbol") String symbol, @Param("tf_method") String tf_method, @Param("strList") List<String> strList);


    @Select({
            "<script>",
            "SELECT COUNT(Family) as sum , Family as name FROM tF_All WHERE 1=1",
            " <when test='tfFamilyPara !=\"\"'> ",
            " AND Family = #{tfFamilyPara}",
            " </when> ",
            " <when test='tfClassPara !=\"\"'> ",
            " AND TfClass = #{tfClassPara}",
            " </when> ",
            " <when test='tfNamePara !=\"\"'>",
            " AND symbol = #{tfNamePara}",
            " </when> ",
            " GROUP BY Family",
            "</script>"
    })
    List<EncodeCate> getFamily(BrowserParam param);

    @Select({
            "<script>",
            "SELECT COUNT(TfClass) as sum , TfClass as name FROM tF_All WHERE 1=1",
            " <when test='tfFamilyPara !=\"\"'> ",
            " AND Family = #{tfFamilyPara}",
            " </when> ",
            " <when test='tfClassPara !=\"\"'> ",
            " AND TfClass = #{tfClassPara}",
            " </when> ",
            " <when test='tfNamePara !=\"\"'>",
            " AND symbol = #{tfNamePara}",
            " </when> ",
            " GROUP BY TfClass",
            "</script>"
    })
    List<EncodeCate> getTfClass(BrowserParam param);

    @Select({
            "<script>",
            "SELECT COUNT(Symbol) as sum , Symbol as name FROM tF_All WHERE 1=1",
            " <when test='tfFamilyPara !=\"\"'> ",
            " AND Family = #{tfFamilyPara}",
            " </when> ",
            " <when test='tfClassPara !=\"\"'> ",
            " AND TfClass = #{tfClassPara}",
            " </when> ",
            " <when test='tfNamePara !=\"\"'>",
            " AND symbol = #{tfNamePara}",
            " </when> ",
            " GROUP BY Symbol",
            "</script>"
    })
    List<EncodeCate> getTfName(BrowserParam param);

    @Select({
            "<script>",
            "SELECT symbol,geneID,ensembl,name,family,sample_num,TfClass FROM tF_All WHERE 1=1",
            " <when test='tfFamilyPara !=\"\"'> ",
            " AND Family = #{tfFamilyPara}",
            " </when> ",
            " <when test='tfClassPara !=\"\"'> ",
            " AND TfClass = #{tfClassPara}",
            " </when> ",
            " <when test='tfNamePara !=\"\"'>",
            " AND symbol = #{tfNamePara}",
            " </when> ",
            "</script>"
    })
    List<TF_All> getEncodeData(BrowserParam param);

    @Select({"SELECT symbol,geneID,ensembl,name,family,sample_num,disease,Goterm,pathway,Hallmark,TfClass,Protein,Protein,LEFT(Protein,20) as ProteinSplit FROM tF_All WHERE symbol=#{symbol}"})
    TF_All getTfInfoBySymbol(String symbol);

    @Select({
            "<script>",
            "SELECT * FROM ${symbol} where gene LIKE CONCAT('%',#{searchValue},'%')",
            " <when test='order ==\"0\"'>",
            " order by gene ${dir}",
            " </when> ",
            " <when test='order ==\"15\"'>",
            " order by Weight ${dir}",
            " </when> ",
            "</script>"
    })
    Page<TfMethodWeig> getTfMeWeBySymbol(String symbol, String searchValue, String order, String dir);

    @Select({"SELECT * FROM ${symbol}"})
    List<TfMethodWeigdown> getTfMeWeBySymboldown(String symbol);

    @Select({"SELECT overlap_gene as overlap FROM ${table} where SampleID=#{sampleID} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolOver(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT proximal_gene as proximal FROM ${table} where SampleID=#{sampleID} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolPro(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT closest_gene as closest FROM ${table} where SampleID=#{sampleID} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolClo(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT overlap_gene as overlap FROM ${table} where SampleID=#{sampleID} limit ${num}"})
    List<String> getNodeBySymbolOverGenemapper(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT proximal_gene as proximal FROM ${table} where SampleID=#{sampleID} limit ${num}"})
    List<String> getNodeBySymbolProGenemapper(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT closest_gene as closest FROM ${table} where SampleID=#{sampleID} limit ${num}"})
    List<String> getNodeBySymbolCloGenemapper(String symbol, String table, String num, String sampleID, String cellLine);

    @Select({"SELECT overlap_gene as overlap FROM ${table} where tf_name=#{symbol} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolOverMotif(String symbol, String table, String num, String cellLine);

    @Select({"SELECT proximal_gene as proximal FROM ${table} where tf_name=#{symbol} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolProMotif(String symbol, String table, String num, String cellLine);

    @Select({"SELECT closest_gene as closest FROM ${table} where tf_name=#{symbol} and cell_line=#{cellLine} and freq >= ${num}"})
    List<String> getNodeBySymbolCloMotif(String symbol, String table, String num, String cellLine);

    @Select({"SELECT gene_name as geneName,gene_id as geneID,gene_biotype as geneBiotype,gene_source as geneSource,chr,start,end,strand FROM gene_information where gene_name = #{gene}"})
    GeneInfo getGeneInfoByGene(String gene);

    @Select({"SELECT * FROM pathway_information_TF where TF = #{symbol}"})
    List<PathwayTab> getPathwayTab(String symbol);

    @Select({"SELECT * FROM GO_TF where tf = #{symbol}"})
    List<GotermTab> getGotermTab(String symbol);

    @Select({"SELECT * FROM Hallmark_TF where gene = #{symbol}"})
    List<HallmarkTab> getHallmarkTab(String symbol);

    @Select({"SELECT * FROM disease_TF where TF = #{symbol}"})
    List<DiseaseTab> getDiseaseTab(String symbol);

    @Select({"SELECT DISTINCT Family FROM tF_All"})
    List<String> getFamilyData();

    @Select({"SELECT DISTINCT cell_line FROM ${table} group by cell_line"})
    List<String> getCellLines(String tf, String table);

    @Select({"SELECT DISTINCT cell_line FROM ${table} where TF_name=#{tf} group by cell_line"})
    List<String> getCellLinesMotif(String tf, String table);

    @Select({"SELECT DISTINCT SampleID FROM ${table} WHERE cell_line=#{cellLine}"})
    List<String> getSampleIDs(String tf, String table, String cellLine);

    @Select({"SELECT DISTINCT freq FROM ${table} WHERE sampleID=#{sampleID} order by freq"})
    List<String> getFreqs(String tf, String table, String cellLine, String sampleID);

    @Select({"SELECT DISTINCT freq FROM ${table} WHERE tf_name=#{tf} and cell_line=#{cellLine} order by freq"})
    List<String> getFreqsByMotif(String tf, String table, String cellLine, String sampleID);

    @Select({"SELECT DISTINCT num FROM ${method} WHERE sample=#{sampleID}"})
    String getFreqsDe(String method, String sampleID);

    @Select({"SELECT DISTINCT num FROM ${table1} WHERE sample=#{id}"})
    String getFreqsDeByMotif(String id, String table1);

    @Select({"SELECT DISTINCT Biosample_name_specific FROM TF_chipseq_information WHERE Tf_name=#{tf}"})
    List<String> getCellLinesPromoter(String tf);

    @Select({"SELECT DISTINCT sample_biosample_name FROM Knock WHERE tf=#{tf}"})
    List<String> getCellLinesKnock(String tf);

    @Select({"SELECT TRIM(BOTH ',' FROM gene) as gene FROM Curate WHERE tf=#{tf}"})
    List<String> getGeneFromCurate(String tf);

    @Select({"SELECT DISTINCT biosample_name_specific FROM Knock WHERE tf_name=#{tf}"})
    List<String> getCellLinesGenemapper(String tf);

    @Select({"SELECT DISTINCT Sample_id FROM TF_chipseq_information WHERE Tf_name=#{tf} and Biosample_name_specific=#{cellLine}"})
    List<String> getSampleIDsPromoter(String tf, String cellLine);

    @Select({"SELECT DISTINCT Sample_ID FROM Knock WHERE tf=#{tf} and sample_biosample_name=#{cellLine}"})
    List<String> getSampleIDsKnock(String tf, String cellLine);

    @Select({"SELECT DISTINCT freq FROM ${table} WHERE sampleID=#{sampleID} order by freq"})
    List<String> getFreqsPromoter(String tf, String table, String cellLine, String sampleID);

    @Select({"SELECT TRIM(BOTH ',' FROM gene) as gene FROM ${table} where SampleID=#{sampleID} and freq >= ${num}"})
    List<String> getNodeBySymbolGene(String table, String num, String sampleID);

    @Select({"SELECT DISTINCT score FROM ${table} WHERE sampleID=#{sampleID} order by score DESC"})
    List<String> getFreqsBETA(String tf, String table, String cellLine, String sampleID);

    @Select({"SELECT DISTINCT TRIM(BOTH ',' FROM gene) as gene FROM ${table} where SampleID=#{sampleID} ORDER BY score DESC LIMIT ${num}"})
    List<String> getNodeBySymbolBETA(String table, String num, String sampleID);

    @Select({"SELECT COUNT(Knock.Rank) FROM Knock WHERE sample_id=#{sampleID} and tf=#{tf} and sample_biosample_name=#{cellLine} order by Knock.Rank"})
    String getFreqsKnock(String tf, String table, String cellLine, String sampleID);

    @Select({"SELECT DISTINCT TRIM(BOTH ',' FROM gene) as gene FROM Knock where Sample_ID=#{sampleID} and TF=#{tf} and sample_biosample_name=#{cellLine} ORDER BY fc DESC LIMIT ${num}"})
    List<String> getNodeBySymbolKnock(String table, String num, String sampleID, String tf, String cellLine);

    @Select({"SELECT COUNT(sampleID) FROM ${table} WHERE sampleID=#{sampleID}"})
    Integer getFreqsGenemapper(String tf, String table, String cellLine, String sampleID);

    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,se_chr,se_start,se_end,se_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (se_start LIKE CONCAT('%',#{searchValue},'%') or se_chr LIKE CONCAT('%',#{searchValue},'%') or tf_end LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or se_source LIKE CONCAT('%',#{searchValue},'%') or rol LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<SE> getTFInSE(String table,String searchValue);
    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,se_chr,se_start,se_end,se_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table}"})
    List<SEdown> getTFInSEdown(String table);

    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,te_chr,te_start,te_end,te_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (te_start LIKE CONCAT('%',#{searchValue},'%') or te_chr LIKE CONCAT('%',#{searchValue},'%') or te_end LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or te_source LIKE CONCAT('%',#{searchValue},'%') or rol LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<TE> getTFInTE(String table,String searchValue);

    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,te_chr,te_start,te_end,te_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table}"})
    List<TEdown> getTFInTEdown(String table);

    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,atac_chr,atac_start,atac_end,atac_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (atac_start LIKE CONCAT('%',#{searchValue},'%') or atac_chr LIKE CONCAT('%',#{searchValue},'%') or atac_end LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or atac_source LIKE CONCAT('%',#{searchValue},'%') or rol LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<ATAC> getTFInATAC(String table,String searchValue);
    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,atac_chr,atac_start,atac_end,atac_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table}"})
    List<ATACdown> getTFInATACdown(String table);


    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,silencer_chr,silencer_start,silencer_end,silencer_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (silencer_start LIKE CONCAT('%',#{searchValue},'%') or silencer_chr LIKE CONCAT('%',#{searchValue},'%') or silencer_end LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or silencer_source LIKE CONCAT('%',#{searchValue},'%') or rol LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<Silencer> getTFInSilencer(String table,String searchValue);
    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,silencer_chr,silencer_start,silencer_end,silencer_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table}"})
    List<Silencerdown> getTFInSilencerdown(String table);


    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,promoter_chr,promoter_start,promoter_end,TRIM(BOTH ',' FROM  gene) as gene,rol FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (promoter_start LIKE CONCAT('%',#{searchValue},'%') or promoter_chr LIKE CONCAT('%',#{searchValue},'%') or promoter_end LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or gene LIKE CONCAT('%',#{searchValue},'%') or rol LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<Promoter> getTFInPromoter(String table,String searchValue);
    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,promoter_chr,promoter_start,promoter_end,TRIM(BOTH ',' FROM  gene) as gene,rol FROM ${table}"})
    List<Promoterdown> getTFInPromoterdown(String table);


    @Select({
            "<script>",
            "SELECT sampleID,tf_chr,tf_start,tf_end,refseqid,TRIM(BOTH ',' FROM  gene) as gene,distance,score FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (refseqid LIKE CONCAT('%',#{searchValue},'%') or distance LIKE CONCAT('%',#{searchValue},'%') or score LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or tf_chr LIKE CONCAT('%',#{searchValue},'%') or tf_start LIKE CONCAT('%',#{searchValue},'%') or gene LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<BETA> getTFInBeta(String table, String searchValue);
    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,refseqid,TRIM(BOTH ',' FROM  gene) as gene,distance,score FROM ${table}"})
    List<BETAdown> getTFInBetadown(String table);


    @Select({
            "<script>",
            "SELECT sampleID,chr,start,end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or chr LIKE CONCAT('%',#{searchValue},'%') or start LIKE CONCAT('%',#{searchValue},'%') or end LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<Genemapper> getTFInGenemapper(String table, String searchValue);
    @Select({
            "<script>",
            "SELECT sampleID,chr,start,end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${table} where 1=1",
            " <when test='searchValue !=\"\"'>",
            " AND (overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or sampleID LIKE CONCAT('%',#{searchValue},'%') or chr LIKE CONCAT('%',#{searchValue},'%') or start LIKE CONCAT('%',#{searchValue},'%') or end LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    List<Genemapperdown> getTFInGenemapperdown(String table);

    @Select({
            "<script>",
            "SELECT tf,TRIM(BOTH ',' FROM  gene) as gene,pubmedID,source,biosample_name,repression_Activation FROM ${table} where tf=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf LIKE CONCAT('%',#{searchValue},'%') or gene LIKE CONCAT('%',#{searchValue},'%') or pubmedID LIKE CONCAT('%',#{searchValue},'%') or source LIKE CONCAT('%',#{searchValue},'%') or biosample_name LIKE CONCAT('%',#{searchValue},'%') or repression_Activation LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<Curate> getTFInCurate(String table, String symbol,String searchValue);
    @Select({"SELECT tf,TRIM(BOTH ',' FROM  gene) as gene,pubmedID,source,biosample_name,repression_Activation FROM ${table} where tf=#{symbol}"})
    List<Curatedown> getTFInCuratedown(String table, String symbol);


    @Select({
            "<script>",
            "SELECT tf,TRIM(BOTH ',' FROM  gene) as gene,fc,log2fc,${table}.rank,p_value,up_down,sample_source,sample_experiment_accession,sample_method,sample_biosample_name FROM ${table} where tf=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf LIKE CONCAT('%',#{searchValue},'%') or gene LIKE CONCAT('%',#{searchValue},'%') or fc LIKE CONCAT('%',#{searchValue},'%') or sample_biosample_name LIKE CONCAT('%',#{searchValue},'%') or sample_method LIKE CONCAT('%',#{searchValue},'%') or sample_experiment_accession LIKE CONCAT('%',#{searchValue},'%') or sample_source LIKE CONCAT('%',#{searchValue},'%') or up_down LIKE CONCAT('%',#{searchValue},'%') or log2fc LIKE CONCAT('%',#{searchValue},'%') or ${table}.rank LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<Knock> getTFInKnock(String table, String symbol, String searchValue);
    @Select({"SELECT tf,TRIM(BOTH ',' FROM  gene) as gene,fc,log2fc,${table}.rank,p_value,up_down,sample_source,sample_experiment_accession,sample_method,sample_biosample_name FROM ${table} where tf=#{symbol}"})
    List<Knockdown> getTFInKnockdown(String table, String symbol);


    @Select({
            "<script>",
            "SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,se_chr,se_start,se_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,se_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf_name LIKE CONCAT('%',#{searchValue},'%') or tf_motif_chr LIKE CONCAT('%',#{searchValue},'%') or tf_motif_start LIKE CONCAT('%',#{searchValue},'%') or tf_motif_end LIKE CONCAT('%',#{searchValue},'%') or se_chr LIKE CONCAT('%',#{searchValue},'%') or sequence LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%') or q_value LIKE CONCAT('%',#{searchValue},'%') or motif_id LIKE CONCAT('%',#{searchValue},'%') or se_source LIKE CONCAT('%',#{searchValue},'%') or se_start LIKE CONCAT('%',#{searchValue},'%') or se_end LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or cell_line LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<MotifSE> getTFInMse(String table, String symbol, String searchValue);
    @Select({"SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,se_chr,se_start,se_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,se_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}"})
    List<MotifSEdown> getTFInMsedown(String table, String symbol);

    @Select({
            "<script>",
            "SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,te_chr,te_start,te_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,te_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf_name LIKE CONCAT('%',#{searchValue},'%') or tf_motif_chr LIKE CONCAT('%',#{searchValue},'%') or tf_motif_start LIKE CONCAT('%',#{searchValue},'%') or tf_motif_end LIKE CONCAT('%',#{searchValue},'%') or te_chr LIKE CONCAT('%',#{searchValue},'%') or sequence LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%') or q_value LIKE CONCAT('%',#{searchValue},'%') or motif_id LIKE CONCAT('%',#{searchValue},'%') or te_source LIKE CONCAT('%',#{searchValue},'%') or te_start LIKE CONCAT('%',#{searchValue},'%') or te_end LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or cell_line LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<MotifTE> getTFInMte(String table, String symbol, String searchValue);
    @Select({"SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,te_chr,te_start,te_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,te_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}"})
    List<MotifTEdown> getTFInMtedown(String table, String symbol);


    @Select({
            "<script>",
            "SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,atac_chr,atac_start,atac_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,atac_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf_name LIKE CONCAT('%',#{searchValue},'%') or tf_motif_chr LIKE CONCAT('%',#{searchValue},'%') or tf_motif_start LIKE CONCAT('%',#{searchValue},'%') or tf_motif_end LIKE CONCAT('%',#{searchValue},'%') or atac_chr LIKE CONCAT('%',#{searchValue},'%') or sequence LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%') or q_value LIKE CONCAT('%',#{searchValue},'%') or motif_id LIKE CONCAT('%',#{searchValue},'%') or atac_source LIKE CONCAT('%',#{searchValue},'%') or atac_start LIKE CONCAT('%',#{searchValue},'%') or atac_end LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or cell_line LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<MotifATAC> getTFInMatac(String table, String symbol, String searchValue);
    @Select({"SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,atac_chr,atac_start,atac_end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line,atac_source,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}"})
    List<MotifATACdown> getTFInMatacdown(String table, String symbol);

    @Select({
            "<script>",
            "SELECT tf_motif_chr,tf_name,tf_motif_start,tf_motif_end,silencer_chr,silencer_start,silencer_end,cell_line,silencer_source,motif_id,q_value,p_value,sequence,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${table} where tf_name=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf_name LIKE CONCAT('%',#{searchValue},'%') or tf_motif_chr LIKE CONCAT('%',#{searchValue},'%') or tf_motif_start LIKE CONCAT('%',#{searchValue},'%') or tf_motif_end LIKE CONCAT('%',#{searchValue},'%') or silencer_chr LIKE CONCAT('%',#{searchValue},'%') or sequence LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%') or q_value LIKE CONCAT('%',#{searchValue},'%') or motif_id LIKE CONCAT('%',#{searchValue},'%') or silencer_source LIKE CONCAT('%',#{searchValue},'%') or silencer_start LIKE CONCAT('%',#{searchValue},'%') or silencer_end LIKE CONCAT('%',#{searchValue},'%') or overlap_gene LIKE CONCAT('%',#{searchValue},'%') or proximal_gene LIKE CONCAT('%',#{searchValue},'%') or closest_gene LIKE CONCAT('%',#{searchValue},'%') or cell_line LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<MotifSilencer> getTFInMailencer(String table, String symbol, String searchValue);
    @Select({"SELECT tf_motif_chr,tf_name,tf_motif_start,tf_motif_end,silencer_chr,silencer_start,silencer_end,cell_line,silencer_source,motif_id,q_value,p_value,sequence,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${table} where tf_name=#{symbol}"})
    List<MotifSilencerdown> getTFInMailencerdown(String table, String symbol);

    @Select({
            "<script>",
            "SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,pro_chr,pro_start,pro_end,TRIM(BOTH ',' FROM  gene) as gene,strand,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}",
            " <when test='searchValue !=\"\"'>",
            " AND (tf_name LIKE CONCAT('%',#{searchValue},'%') or tf_motif_chr LIKE CONCAT('%',#{searchValue},'%') or tf_motif_start LIKE CONCAT('%',#{searchValue},'%') or tf_motif_end LIKE CONCAT('%',#{searchValue},'%') or sequence LIKE CONCAT('%',#{searchValue},'%') or pro_chr LIKE CONCAT('%',#{searchValue},'%') or p_value LIKE CONCAT('%',#{searchValue},'%') or q_value LIKE CONCAT('%',#{searchValue},'%') or motif_id LIKE CONCAT('%',#{searchValue},'%') or pro_start LIKE CONCAT('%',#{searchValue},'%') or pro_end LIKE CONCAT('%',#{searchValue},'%') or gene LIKE CONCAT('%',#{searchValue},'%') or strand LIKE CONCAT('%',#{searchValue},'%'))",
            " </when> ",
            "</script>"
    })
    Page<MotifPromoter> getTFInMpromoter(String table, String symbol, String searchValue);
    @Select({"SELECT tf_name,tf_motif_chr,tf_motif_start,tf_motif_end,pro_chr,pro_start,pro_end,TRIM(BOTH ',' FROM  gene) as gene,strand,motif_id,q_value,p_value,sequence FROM ${table} where tf_name=#{symbol}"})
    List<MotifPromoterdown> getTFInMpromoterdown(String table, String symbol);

    @Select({"SELECT * FROM gene_exp_tcga where gene_name=#{symbol}"})
    Exp0 getExp0(String symbol);

    @Select({"SELECT * FROM gene_exp_cell_line_ccle where gene_name=#{symbol}"})
    Exp1 getExp1(String symbol);

    @Select({"SELECT * FROM gene_exp_normal_tissue_gtex where gene_name=#{symbol}"})
    Exp2 getExp2(String symbol);

    @Select({"SELECT * FROM gene_exp_cell_line_encode where gene_name=#{symbol}"})
    Exp3 getExp3(String symbol);

    @Select({"SELECT * FROM gene_exp_primary_cell_encode where gene_name=#{symbol}"})
    Exp4 getExp4(String symbol);

    @Select({"SELECT * FROM gene_exp_tissue_ncbi where gene_name=#{symbol}"})
    Exp5 getExp5(String symbol);

    @Select({"SELECT * FROM gene_exp_in_vitro_differentiated_cell_encode where gene_name=#{symbol}"})
    Exp6 getExp6(String symbol);


    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,se_chr,se_start,se_end,se_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where sampleID=#{sampleid}"})
    Page<SE> getSampleInSE(String table, String sampleid);

    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,te_chr,te_start,te_end,te_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where sampleID=#{sampleid}"})
    Page<TE> getSampleInTE(String table, String sampleid);

    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,atac_chr,atac_start,atac_end,atac_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where sampleID=#{sampleid}"})
    Page<ATAC> getSampleInATAC(String table, String sampleid);

    @Select({"SELECT sampleID,tf_chr,tf_start,tf_end,silencer_chr,silencer_start,silencer_end,silencer_source,rol,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene,cell_line FROM ${table} where sampleID=#{sampleid}"})
    Page<Silencer> getSampleInSilencer(String table, String sampleid);

    @Select({"SELECT sampleID,TF_chr,tf_start,tf_end,Promoter_chr,Promoter_start,promoter_end,TRIM(BOTH ',' FROM  gene) as gene,rol FROM ${table} where sampleID=#{sampleid}"})
    Page<Promoter> getSampleInPromoter(String table, String sampleid);

    @Select({"SELECT sampleID,TF_chr,tf_start,tf_end,refseqID,TRIM(BOTH ',' FROM  gene) as gene,distance,score FROM ${table} where sampleID=#{sampleid}"})
    Page<BETA> getSampleInBeta(String table, String sampleid);

    @Select({"SELECT sampleID,chr,start,end,TRIM(BOTH ',' FROM  overlap_gene) as overlap_gene,TRIM(BOTH ',' FROM  proximal_gene) as proximal_gene,TRIM(BOTH ',' FROM  closest_gene) as closest_gene FROM ${table} where sampleID=#{sampleid}"})
    Page<Genemapper> getSampleInGenemapper(String table, String sampleid);

    @Select({"SELECT tf_name FROM TF_chipseq_information where sample_id = #{sampleid}"})
    String getTFBysampleid(String sampleid);
}
