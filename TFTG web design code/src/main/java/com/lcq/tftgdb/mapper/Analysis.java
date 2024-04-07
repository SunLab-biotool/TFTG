package com.lcq.tftgdb.mapper;

import com.lcq.tftgdb.pojo.Analysis1Input;
import com.lcq.tftgdb.pojo.Multselect;
import com.lcq.tftgdb.pojo.PathwayInfo;
import com.lcq.tftgdb.pojo.TF_All;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Analysis {
    @Select("<script>"
            + "select DISTINCT biosample_name as label,biosample_name as value from analysis1 where method IN "
            + "<foreach item='item' index='index' collection='method' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<Multselect> getCellByMethod(@Param("method") List<String> method);

    @Select("<script>"
            + "select * from analysis1 where method IN "
            + "<foreach item='item' index='index' collection='method' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> and Biosample_name in "
            + "<foreach item='item' index='index' collection='celllist' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> and gene_number between ${min} and ${max}"
            + "</script>")
    List<Analysis1Input> getAnalysis1(@Param("method") List<String> method, @Param("min") String min, @Param("max") String max, @Param("celllist") List<String> celllist);

    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1M_SE(@Param("method") String method, @Param("min") String min, @Param("max") String max);
    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1M_TE(@Param("method") String method, @Param("min") String min, @Param("max") String max);
    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1M_ATAC(@Param("method") String method, @Param("min") String min, @Param("max") String max);
    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1M_Silencer(@Param("method") String method, @Param("min") String min, @Param("max") String max);
    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1M_Promoter(@Param("method") String method, @Param("min") String min, @Param("max") String max);
    @Select({"select * from analysis1 where method = #{method} and gene_number between ${min} and ${max}"})
    List<Analysis1Input> getAnalysis1Curate(@Param("method") String method, @Param("min") String min, @Param("max") String max);



    @Select("<script>"
            + "select DISTINCT biosample_name from ${table} where tf IN "
            + "<foreach item='item' index='index' collection='genelist' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> or gene in "
            + "<foreach item='item' index='index' collection='genelist' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<String> getana2CellByMethod(@Param("table") String table, @Param("genelist") List<String> genelist);

    @Select("<script>"
            + "select DISTINCT weight from ana2_non_specific where gene IN "
            + "<foreach item='item' index='index' collection='genelist' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> order by weight desc"
            + "</script>")
    List<String> getWeight(List<String> genelist);

    @Select("<script>"
            + "select * from pathway_information where pathway_source IN "
            + "<foreach item='item' index='index' collection='method' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach> and gene_number between ${min} and ${max}"
            + "</script>")
    List<PathwayInfo> getPathwayinfo(@Param("method") List<String> method, @Param("min") String min, @Param("max") String max);

    @Select({"SELECT gene FROM analysis1 where tf = #{tf} and method = #{method} and sampleid = #{sampleid}"})
    String getvenn(String tf, String sampleid, String method);
}
