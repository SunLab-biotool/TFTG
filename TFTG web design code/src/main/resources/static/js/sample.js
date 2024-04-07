var sampleid = "";

function setsample(sampleid) {
    sampleid = sampleid;
}

function browserse(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>SE_chr</th><th>SE_start</th><th>SE_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>SE source</th><th>Cell line</th><th>ROL</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "samplese",
            type: "GET",
            data: {"symbol": "", "sampleid": sampleid, "method": "SE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "se_chr"},
            {"data": "se_start"},
            {"data": "se_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "se_source"},
            {"data": "cell_line"},
            {"data": "rol"}
        ]
    });
}

function browserte(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='te' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>TE_chr</th><th>TE_start</th><th>TE_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>TE source</th><th>Cell line</th><th>ROL</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#te').DataTable({
        ajax: {
            url: "samplete",
            type: "GET",
            data: {"sampleid": sampleid, "method": "TE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "te_chr"},
            {"data": "te_start"},
            {"data": "te_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "te_source"},
            {"data": "cell_line"},
            {"data": "rol"}
        ]
    });
}

function browsermethod(method,obj) {
    $("button").removeClass("active");
    console.log(obj);
    $(obj).addClass("active");
    if (method == "SE") {
        browserse(sampleid)
    } else if (method == "TE") {
        browserte(sampleid)
    } else if (method == "ATAC") {
        browseratac(sampleid)
    } else if (method == "Promoter") {
        browserpromoter(sampleid)
    } else if (method == "Silencer") {
        browsersilencer(sampleid)
    } else if (method == "Genemapper") {
        browsergenemapper(sampleid)
    } else if (method == "BETA") {
        browserbeta(sampleid)
    }
}

function browseratac(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>ATAC_chr</th><th>ATAC_start</th><th>ATAC_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>ATAC source</th><th>Cell line</th><th>ROL</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "sampleatac",
            type: "GET",
            data: {"sampleid": sampleid, "method": "ATAC"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "atac_chr"},
            {"data": "atac_start"},
            {"data": "atac_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "atac_source"},
            {"data": "cell_line"},
            {"data": "rol"}
        ]
    });
}

function browsersilencer(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Silencer_chr</th><th>Silencer_start</th><th>Silencer_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Silencer source</th><th>Cell line</th><th>ROL</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "samplesilencer",
            type: "GET",
            data: {"sampleid": sampleid, "method": "Silencer"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "silencer_chr"},
            {"data": "silencer_start"},
            {"data": "silencer_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "silencer_source"},
            {"data": "cell_line"},
            {"data": "rol"}
        ]
    });
}

function browserpromoter(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Promoter_chr</th><th>Promoter_start</th><th>Promoter_end</th><th>Gene</th><th>ROL</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "samplepromoter",
            type: "GET",
            data: {"sampleid": sampleid, "method": "Promoter"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "promoter_chr"},
            {"data": "promoter_start"},
            {"data": "promoter_end"},
            {"data": "gene", className: "genebg"},
            {"data": "rol"}
        ]
    });
}

function browserbeta(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>RefseqID</th><th>Gene</th><th>Distance</th><th>Score</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "samplebeta",
            type: "GET",
            data: {"sampleid": sampleid, "method": "BETA"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "refseqID"},
            {"data": "gene", className: "genebg"},
            {"data": "distance"},
            {"data": "score"}
        ]
    });
}

function browsergenemapper(sampleid) {
    $("#browsepage").empty();
    var table = "<table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "samplegenemapper",
            type: "GET",
            data: {"sampleid": sampleid, "method": "Genemapper"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: false,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "sampleID"},
            {"data": "chr"},
            {"data": "start"},
            {"data": "end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"}
        ]
    });
}

function distance() {
    var datastr;
    $.ajax({
        url: "distancegraph",
        async: false,
        data: {"sampleid": sampleid},
        success: function (_data) {
            datastr = _data.data;
        },
        dataType: "json"
    });

    var myChart = echarts.init(document.getElementById('distance'));
    var option = {
        color: ["#9ecae1", "#3182bd", "#c7a76c", "#86b875", "#39beb1", "#cd99d8"],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var result = '';
                params.forEach(function (item) {
                    result += item.marker + " " + item.seriesName + " : " + item.value + "%</br>";
                });
                return result;
            }
        },
        legend: {
            data: ['0-1kb', '1-3kb', '3-5kb', '5-10kb', '10-100kb', '>100kb']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            name: ' ',
            nameLocation: 'center',
            scale: 'false',
            nameGap: '7',
            nameTextStyle: {
                backgroundColor: '#fff',
                fontWeight: 'bold'
            }
        },

        yAxis: {
            type: 'category',
            axisLine: {
                show: true, //是否显示y轴
                lineStyle: {
                    color: '#000', // y坐标轴的轴线颜色
                    width: 3, //这里是坐标轴的宽度,可以去掉
                }
            },

            data: ' '
        },
        series: datastr
    };

    myChart.setOption(option);
}

function samplechr() {
    var datastr;
    $.ajax({
        url: "samplegraph",
        async: false,
        data: {"sampleid": sampleid},
        success: function (_data) {
            datastr = _data.data;
        },
        dataType: "json"
    });
    var myChart = echarts.init(document.getElementById('samplechr'));
    var option = {
        color: ["#94ca79", "#ed9e5c", "#7980bd", "#e15a7a", "#dbcc55", "#2c8a8a", "#e65959", "#95d2d0", "#79addd", "#414348"],
        title: {
            text: 'TF distribution of chromosomes',
            left: '40%'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['chr1', 'chr2', 'chr3', 'chr4', 'chr5', 'chr6', 'chr7', 'chr8', 'chr9', 'chr10', 'chr11', 'chr12', 'chr13', 'chr14', 'chr15', 'chr16', 'chr17', 'chr18', 'chr19', 'chr20', 'chr21', 'chr22', 'chrX', 'chrY']
        },
        series: [
            {
                name: '',
                type: 'pie',
                radius: '55%',
                center: ['65%', '50%'],
                data: datastr,
                /*[
                    {value:1,name:'chr11'},{value:1,name:'chr12'},{value:1,name:'chr15'},{value:2,name:'chr3'},{value:4,name:'chr6'},{value:1,name:'chr7'},{value:1,name:'chr8'},{value:1,name:'chrX'},
                ]*/
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}

function sampleRegion() {
    var datastr;
    $.ajax({
        url: "sampleRegion",
        async: false,
        data: {"sampleid": sampleid},
        success: function (_data) {
            datastr = _data.data;
        },
        dataType: "json"
    });
    var myChart = echarts.init(document.getElementById('sampleRegion'));
    var option = {
        color: ["#94ca79", "#ed9e5c", "#7980bd", "#e15a7a", "#dbcc55", "#2c8a8a", "#e65959", "#95d2d0", "#79addd"],
        title: {
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            top: 'center',
            data: ["Promoter", "5' UTR", "3' UTR", "1st Exon", "Other Exon", "1st Intron", "Other Intron", "Downstream (<=300)", "Distal Intergenic",]
        },
        series: [
            {
                name: '',
                type: 'pie',
                radius: '45%',
                center: ['67%', '45%'],
                data: datastr,
                radius: ["0%", "54%"],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}