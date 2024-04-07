package com.lcq.tftgdb.mapper;

import com.github.pagehelper.Page;
import com.lcq.tftgdb.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Search {
    @Select({"SELECT DISTINCT Weight FROM ${table} order by Weight"})
    List<String> getweightbymethod(String table);

    @Select({"SELECT tF_All.*,Weight FROM tF_All LEFT JOIN ${table} on symbol=tf WHERE gene=#{gene} and Weight>=${weight}"})
    List<TF_All> getInfoByGene(String table, String gene, String weight, String method);

    @Select({"SELECT * FROM tF_All WHERE family=#{family}"})
    List<TF_All> getInfoByFamily(String family);

    @Select({"SELECT DISTINCT symbol FROM tF_All WHERE family=#{family}"})
    List<String> getTfByFamily(String family);

    @Select({"SELECT DISTINCT symbol as label,symbol as value FROM tF_All WHERE family=#{family}"})
    List<Multselect> gettfmultseletByFamily(String family);

    @Select({"SELECT DISTINCT symbol as label,symbol as value FROM tF_All"})
    List<Multselect> gettfAllmultseletByFamily();

    @Select({"SELECT DISTINCT symbol FROM tF_All"})
    List<String> getTfToAllFamily1();

    @Select({"SELECT * FROM TF_chipseq_information WHERE sample_id=#{sampleid}"})
    TF_chipseq_infomation getSampleInfoBySampleID(String sampleid);

    @Select({"SELECT chr as name,count(chr) as value FROM ${sampleid} group by chr"})
    List<GraphChr> getSampleGraphBySampleID(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid}"})
    int getDistance(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between 0 and 1000"})
    int getDistance1Kb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between 1000 and 3000"})
    int getDistance3Kb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between 3000 and 5000"})
    int getDistance5Kb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between 5000 and 10000"})
    int getDistance10Kb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between 10000 and 100000"})
    int getDistance100Kb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance >=100000"})
    int getDistance100aKb(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between -1000 and 0"})
    int getDistance1Kb_(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between -3000 and -1000"})
    int getDistance3Kb_(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between -5000 and -3000"})
    int getDistance5Kb_(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between -10000 and -5000"})
    int getDistance10Kb_(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance between -100000 and -10000"})
    int getDistance100Kb_(String sampleid);

    @Select({"SELECT COUNT(distance) FROM ${sampleid} where distance <=-100000"})
    int getDistance100aKb_(String sampleid);

    @Select({"SELECT feature as name,frequency as value FROM freq_pic where sample = #{sampleid}"})
    List<GraphChr> sampleRegion(String sampleid);

    @Select({"SELECT sample_id as id,data_sources as source,sample_type as type,biosample_name as bioname FROM download_ChIPseq_table"})
    List<Download> downChip();

    @Select({"SELECT Dataset_id as id,Source as source,knock_method as method,biosample_name as bioname FROM download_RNAseq_table"})
    List<Download> downRNA();
}
