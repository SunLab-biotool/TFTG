package com.lcq.tftgdb.mapper;

import com.lcq.tftgdb.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Network {
    @Select("<script>"
            + "select DISTINCT SampleID from ${table}"
            + "</script>")
    List<String> getSampleID(@Param("table") String table);
    @Select("<script>"
            + "select DISTINCT ${secol} from ${table} where freq &gt;= ${freq} and SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getSe(@Param("table") String table,@Param("sample")String[] sample,@Param("freq")String freq,@Param("secol")String secol);
    @Select("<script>"
            + "select DISTINCT gene from Curate where biosample_name = #{cellline} and TF = #{tfs} "
            + "</script>")
    List<String> getgeneCurate(@Param("tfs")String tfs,@Param("cellline")String cellline);
    @Select("<script>"
            + "select DISTINCT gene from Knock where sample_biosample_name = #{cellline} and TF = #{tfs} "
            + " order by ABS(Log2FC) limit ${score}"
            + "</script>")
    List<String> getgeneknock(@Param("tfs")String tfs,@Param("cellline")String cellline,@Param("score")String score);
    @Select("<script>"
            + "select DISTINCT gene from M_Promoter where TF_name = #{tfs} "
            + " order by p_value ASC limit ${score}"
            + "</script>")
    List<String> getgenempromoter(@Param("tfs")String tf,@Param("score")String score);
    @Select("<script>"
            + "select DISTINCT biosample_name from Curate where TF in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCurate(@Param("tfs") String[] tfs);

    @Select("<script>"
            + "select DISTINCT Cell_line from motif_SE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineMse(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_SE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCse(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_Promoter_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCpro(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from Curate_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCurate2(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from Knock_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineKnock2(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_BETA_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCbeta(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT cell_or_tissue from C_Genemapper_cell2"
            + "</script>")
    List<String> getCellLineCmapper();
    @Select("<script>"
            + "select DISTINCT cell_or_tissue from C_ATAC_cell2"
            + "</script>")
    List<String> getCellLineatac();
    @Select("<script>"
            + "select DISTINCT cell_or_tissue from C_TE_cell2"
            + "</script>")
    List<String> getCellLineCte2();
    @Select("<script>"
            + "select DISTINCT id from C_Genemapper_cell2 where cell_or_tissue = #{cellline}"
            + "</script>")
    String getidcell(@Param("cellline")String cellline);
    @Select("<script>"
            + "select DISTINCT id from C_TE_cell2 where cell_or_tissue = #{cellline}"
            + "</script>")
    String getidtecell(@Param("cellline")String cellline);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_TE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCte(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_ATAC_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCatac(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from C_Silencer_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineCsilencer(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT freq from C_Silencer_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline}"
            + "</script>")
    List<String> getfreqCsilencer(@Param("tfs") String[] tfs,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT freq from C_ATAC_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline}"
            + "</script>")
    List<String> getfreqCatac(@Param("tfs") String[] tfs,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT freq from C_TE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline}"
            + "</script>")
    List<String> getfreqCte(@Param("tfs") String[] tfs,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT freq from C_SE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline}"
            + "</script>")
    List<String> getfreqCse(@Param("tfs") String[] tfs,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT freq from C_Promoter_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline}"
            + "</script>")
    List<String> getfreqCpro(@Param("tfs") String[] tfs,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT freq from ${table} where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_line = #{cellline} and Gene_type = #{gene_type}"
            + "</script>")
    List<String> getFreqte(@Param("tfs") String[] tfs,@Param("cellline") String cellline,@Param("table") String table,@Param("gene_type") String gene_type);
    @Select("<script>"
            + "select DISTINCT freq from ${table} where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + " and cell_or_tissue = #{cellline} and Gene_type = #{gene_type}"
            + "</script>")
    List<String> getFreqte2(@Param("tfs") String[] tfs,@Param("cellline") String cellline,@Param("table") String table,@Param("gene_type") String gene_type);
    @Select("<script>"
            + "select DISTINCT Cell_line from motif_TE_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineMte(@Param("tfs") String[] tfs);

    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where gene = #{gene} and Cell_line = #{cellLine} and Gene_type = #{genetype} order by P_value ASC limit ${score}"
            + "</script>")
    List<String> getMseid(@Param("table") String table,@Param("gene") String gene,@Param("genetype") String genetype,@Param("cellLine") String cellLine,@Param("score") String score);
    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where gene = #{gene} and Cell_line = #{cellLine} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCseid(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq);
    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where gene = #{gene} and Cell_line = #{cellLine} order by P_value ASC limit ${score}"
            + "</script>")
    List<String> getMseidall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("score") String score);
    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where gene = #{gene} and Cell_line = #{cellLine} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCseidall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq);
    @Select("<script>"
            + "select DISTINCT TF from ${table} where gene = #{gene} and cell_or_tissue = #{cellLine}"
            + "</script>")
    List<String> getCmapperall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine);
    @Select("<script>"
            + "select DISTINCT TF from ${table} where gene = #{gene} and cell_or_tissue = #{cellLine} and Ele_ID = #{genetype}"
            + "</script>")
    List<String> getCmapper(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("genetype") String genetype);
    @Select("<script>"
            + "select DISTINCT tf from C_BETA_gene_tf where gene = #{gene} and Cell_line = #{cellLine} order by Beta_score DESC limit ${score}"
            + "</script>")
    List<String> getCbetatf(@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("score") String score);
    @Select("<script>"
            + "select DISTINCT tf from C_Promoter_gene_tf where gene = #{gene} and Cell_line = #{cellLine} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCprotf(@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq);
    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where Gene = #{gene} and cell_or_tissue = #{cellLine} and Gene_type = #{genetype} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCtetf22(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq,@Param("genetype") String genetype);
    @Select("<script>"
            + "select DISTINCT Ele_id from ${table} where Gene = #{gene} and cell_or_tissue = #{cellLine} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCtetfall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq);

    @Select("<script>"
            + "select DISTINCT tf from motif_Promoter_gene_tf where gene = #{gene} order by P_value ASC limit ${score}"
            + "</script>")
    List<String> getMprotf(@Param("gene") String gene,@Param("score") String score);
    @Select("<script>"
            + "select DISTINCT tf from Curate_gene_tf where gene = #{gene} and Cell_line = #{cellline}"
            + "</script>")
    List<String> getCuratetf(@Param("gene") String gene,@Param("cellline") String cellline);
    @Select("<script>"
            + "select DISTINCT tf from Knock_gene_tf where gene = #{gene} and Cell_line = #{cellline} order by ABS(Log2FC) limit ${score}"
            + "</script>")
    List<String> getKnocktf(@Param("gene") String gene,@Param("cellline") String cellline,@Param("score") String score);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and Cell_line = #{cellLine} and Gene_type = #{genetype} and Ele_id = #{seid} order by P_value ASC limit ${score}"
            + "</script>")
    List<String> getMtf(@Param("table") String table,@Param("gene") String gene,@Param("genetype") String genetype,@Param("cellLine") String cellLine,@Param("score") String score,@Param("seid") String seid);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and Cell_line = #{cellLine} and freq &gt;= ${freq} and Ele_id = #{seid}"
            + "</script>")
    List<String> getCtf(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq,@Param("seid") String seid);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and Cell_line = #{cellLine} and Ele_id = #{seid} order by P_value ASC limit ${score}"
            + "</script>")
    List<String> getMtfall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("score") String score,@Param("seid") String seid);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and Cell_line = #{cellLine} and Ele_id = #{seid} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCtfall(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq,@Param("seid") String seid);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and cell_or_tissue = #{cellLine} and Ele_id = #{seid} and freq &gt;= ${freq}"
            + "</script>")
    List<String> getCtetfallse(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq,@Param("seid") String seid);
    @Select("<script>"
            + "select DISTINCT tf from ${table} where gene = #{gene} and cell_or_tissue = #{cellLine} and Ele_id = #{seid} and freq &gt;= ${freq} and gene_type = #{genetype}"
            + "</script>")
    List<String> getCtetf2(@Param("table") String table,@Param("gene") String gene,@Param("cellLine") String cellLine,@Param("freq") String freq,@Param("seid") String seid,@Param("genetype") String genetype);
    @Select("<script>"
            + "select DISTINCT tf from method_gene where method=#{method} and tf in "
            + "<foreach item='item' index='index' collection='gene' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getMethodGene(@Param("gene") String[] gene,@Param("method") String method);


    @Select("<script>"
            + "select DISTINCT Cell_line from motif_ATAC_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineMatac(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT Cell_line from motif_Silencer_gene_tf where gene in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineMsilencer(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT sample_biosample_name from Knock where TF in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineKnock(@Param("tfs") String[] tfs);
    @Select("<script>"
            + "select DISTINCT cell_line from ${table} where TF_name in "
            + "<foreach item='item' index='index' collection='tfs' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLineM(@Param("tfs") String[] tfs,@Param("table") String table);
    @Select("<script>"
            + "select DISTINCT ${secol} as se, overlap_gene as gene from ${table} where freq &gt;= ${freq} and SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<SEAndGene> getGeneByoverlap(@Param("table") String table, @Param("sample")String[] sample, @Param("freq")String freq,@Param("secol")String secol);

    @Select("<script>"
            + "select DISTINCT overlap_gene as gene from ${table} where SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getGeneBymapperoverlap(@Param("table") String table, @Param("sample")String[] sample);

    @Select("<script>"
            + "select DISTINCT overlap_gene as gene from ${table} where tf_name = #{tf} and cell_line = #{cellline} order by p_value ASC limit ${score}"
            + "</script>")
    List<String> getGeneBymoverlap(@Param("table") String table,@Param("tf") String tf, @Param("cellline")String cellline,@Param("score")String score);
    @Select("<script>"
            + "select DISTINCT proximal_gene as gene from ${table} where tf_name = #{tf} and cell_line = #{cellline} order by p_value ASC limit ${score}"
            + "</script>")
    List<String> getGeneBymproximal(@Param("table") String table,@Param("tf") String tf, @Param("cellline")String cellline,@Param("score")String score);
    @Select("<script>"
            + "select DISTINCT closest_gene as gene from ${table} where tf_name = #{tf} and cell_line = #{cellline} order by p_value ASC limit ${score}"
            + "</script>")
    List<String> getGeneBymclosest(@Param("table") String table,@Param("tf") String tf, @Param("cellline")String cellline,@Param("score")String score);

    @Select("<script>"
            + "select DISTINCT proximal_gene as gene from ${table} where SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getGeneBymapperproximal(@Param("table") String table, @Param("sample")String[] sample);
    @Select("<script>"
            + "select DISTINCT closest_gene as gene from ${table} where SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getGeneBymapperclosest(@Param("table") String table, @Param("sample")String[] sample);

    @Select("<script>"
            + "select DISTINCT ${secol} as se, proximal_gene as gene from ${table} where freq &gt;= ${freq} and SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<SEAndGene> getGeneByproximal(@Param("table") String table, @Param("sample")String[] sample, @Param("freq")String freq,@Param("secol")String secol);
    @Select("<script>"
            + "select DISTINCT ${secol} as se, closest_gene as gene from ${table} where freq &gt;= ${freq} and SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<SEAndGene> getGeneByclosest(@Param("table") String table, @Param("sample")String[] sample, @Param("freq")String freq,@Param("secol")String secol);

    @Select("<script>"
            + "select DISTINCT Gene from ${table} where freq &gt;= ${freq} and SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getGeneBygene(@Param("table") String table, @Param("sample")String[] sample, @Param("freq")String freq);
    @Select("<script>"
            + "select DISTINCT Gene from ${table} where SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "order by score DESC limit ${score}"
            + "</script>")
    List<String> getGeneBybetagene(@Param("table") String table, @Param("sample")String[] sample, @Param("score")String score);


    @Select("<script>"
            + "select DISTINCT Freq as label from ${table} where SampleID in "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<Integer> getFreq(@Param("table") String table,@Param("sample")String[] sample);
    @Select("<script>"
            + "select DISTINCT Biosample_name_specific from TF_chipseq_information where Sample_id IN "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getCellLine(@Param("sample") List<String> sample);

    @Select("<script>"
            + "select DISTINCT Biosample_name_specific as cellLine,Sample_id as sampleID from TF_chipseq_information where Sample_id IN "
            + "<foreach item='item' index='index' collection='sample' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<SampleAndCellline> getSampleAndCellLine(@Param("sample") List<String> sample);

    @Select("<script>"
            + "select DISTINCT symbol as label from tF_All where symbol IN "
            + "<foreach item='item' index='index' collection='tf' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getTF(@Param("tf") String[] tf);
    @Select("<script>"
            + "select DISTINCT method from method_TF where tf IN "
            + "<foreach item='item' index='index' collection='tf' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getMethod(@Param("tf") String[] tf);
    @Select("<script>"
            + "select DISTINCT method from method_gene where tf IN "
            + "<foreach item='item' index='index' collection='gene' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getMethodBygene(@Param("gene") String[] tf);
}
