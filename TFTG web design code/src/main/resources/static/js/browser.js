var tfNamePara = ''
var tfFamilyPara = ''
var tfClassPara = ''
var cellmultOnchange = [];
var currentf = ''
var currenMethod = "SE";
var currengene = "";
var thistf;

function setmethod(method) {
    if (method == null || method == "") {
    } else {
        currenMethod = method;
        $(".methodclass").find("option[value='" + currenMethod + "']").attr("selected", true);
        // alert("zhegeshi:"+currenMethod+":")
    }
}

function setgene(gene) {
    currengene = gene;
}

function outtf(tf) {
    thistf = tf
}

function browsermethod(method,obj) {
    $("button").removeClass("active");
    console.log(obj);
    $(obj).addClass("active");
    if (method == "SE") {
        browserse(thistf)
    } else if (method == "TE") {
        browserte(thistf)
    } else if (method == "ATAC") {
        browseratac(thistf)
    } else if (method == "Promoter") {
        browserpromoter(thistf)
    } else if (method == "Silencer") {
        browsersilencer(thistf)
    } else if (method == "Genemapper") {
        browsergenemapper(thistf)
    } else if (method == "BETA") {
        browserbeta(thistf)
    } else if (method == "Curate") {
        browsercurate(thistf)
    } else if (method == "Knock") {
        browserknock(thistf)
    } else if (method == "M_SE") {
        browsermse(thistf)
    } else if (method == "M_TE") {
        browsermte(thistf)
    } else if (method == "M_ATAC") {
        browsermatac(thistf)
    } else if (method == "M_Silencer") {
        browsermsilencer(thistf)
    } else if (method == "M_Promoter") {
        browsermpromoter(thistf)
    }
}

/*转录因子找基因*/
function browserse(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downse?symbol="+thistf+"' download='downse.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>SE_chr</th><th>SE_start</th><th>SE_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>SE source</th><th>Cell line</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsesetab",
            type: "GET",
            data: {"symbol": thistf, "method": "SE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        buttons: [],
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browserte(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downte?symbol="+thistf+"' download='downte.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='te' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>TE_chr</th><th>TE_start</th><th>TE_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>TE source</th><th>Cell line</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#te').DataTable({
        ajax: {
            url: "browsetetab",
            type: "GET",
            data: {"symbol": thistf, "method": "TE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browseratac(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downatac?symbol="+thistf+"' download='downatac.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>ATAC_chr</th><th>ATAC_start</th><th>ATAC_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>ATAC source</th><th>Cell line</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browseatactab",
            type: "GET",
            data: {"symbol": thistf, "method": "ATAC"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browsersilencer(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downsilencer?symbol="+thistf+"' download='downsilencer.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Silencer_chr</th><th>Silencer_start</th><th>Silencer_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Silencer source</th><th>Cell line</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsesilencertab",
            type: "GET",
            data: {"symbol": thistf, "method": "Silencer"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browserpromoter(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downpromoter?symbol="+thistf+"' download='downpromoter.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Promoter_chr</th><th>Promoter_start</th><th>Promoter_end</th><th>Gene</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsepromotertab",
            type: "GET",
            data: {"symbol": thistf, "method": "Promoter"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browserbeta(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downbeta?symbol="+thistf+"' download='downbeta.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>RefseqID</th><th>Gene</th><th>Distance</th><th>Score<span class='glyphicon glyphicon-question-sign' title='The potential power for the TF-target regulations using the BETA.'></span></th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsebetatab",
            type: "GET",
            data: {"symbol": thistf, "method": "BETA"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
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

function browsergenemapper(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downgenemapper?symbol="+thistf+"' download='downgenemapper.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_chr</th><th>TF_start</th><th>TF_end</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsegenemappertab",
            type: "GET",
            data: {"symbol": thistf, "method": "Genemapper"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "chr"},
            {"data": "start"},
            {"data": "end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"}
        ]
    });
}

function browsercurate(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downcurate?symbol="+thistf+"' download='downcurate.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF</th><th>Gene</th><th>Source</th><th>PubmedID</th><th>Biosample_name</th><th>Repression_Activation</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsecuratetab",
            type: "GET",
            data: {"symbol": thistf, "method": "Curate"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf"},
            {"data": "gene", className: "genebg"},
            {"data": "source"},
            {"data": "pubmedID"},
            {"data": "biosample_name"},
            {"data": "repression_Activation"}
        ]
    });
}

function browserknock(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downknock?symbol="+thistf+"' download='downknock.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF</th><th>Gene</th><th>FC</th><th>Log2FC</th><!--<th>Rank</th>--><th>P_value</th><!--<th>Up_down</th>--><th>Sample_source</th><th>Sample_experiment_accession</th><th>Sample_method</th><th>Sample_biosample_name</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browseknocktab",
            type: "GET",
            data: {"symbol": thistf, "method": "Knock"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf"},
            {"data": "gene", className: "genebg"},
            {"data": "fc"},
            {"data": "log2fc"},
            /*{"data": "rank"},*/
            {"data": "p_value"},
            /*{"data": "up_down"},*/
            {"data": "sample_source"},
            {"data": "sample_experiment_accession"},
            {"data": "sample_method"},
            {"data": "sample_biosample_name"}
        ]
    });
}

function browsermse(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downmse?symbol="+thistf+"' download='downmse.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>SE_Chr</th><th>SE_Start</th><th>SE_End</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Cell line</th><th>SE_source</th><th>P_Value</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsemsetab",
            type: "GET",
            data: {"symbol": thistf, "method": "M_SE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "se_chr"},
            {"data": "se_start"},
            {"data": "se_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "cell_line"},
            {"data": "se_source"},
            /*{"data": "motif_id"},*/
            /*{"data": "q_value"},*/
            {"data": "p_value"}
            /*{"data": "sequence"}*/
        ]
    });
}

function browsermte(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downmte?symbol="+thistf+"' download='downmte.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>TE_Chr</th><th>TE_Start</th><th>TE_End</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Cell line</th><th>TE_source</th><th>P_Value</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsemtetab",
            type: "GET",
            data: {"symbol": thistf, "method": "M_TE"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "te_chr"},
            {"data": "te_start"},
            {"data": "te_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "cell_line"},
            {"data": "te_source"},
            /*{"data": "motif_id"},
            {"data": "q_value"},*/
            {"data": "p_value"}
            /*{"data": "sequence"}*/
        ]
    });
}

function browsermatac(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downmatac?symbol="+thistf+"' download='downmatac.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>ATAC_Chr</th><th>ATAC_Start</th><th>ATAC_End</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Cell line</th><th>ATAC_source</th><th>P_Value</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsematactab",
            type: "GET",
            data: {"symbol": thistf, "method": "M_ATAC"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "atac_chr"},
            {"data": "atac_start"},
            {"data": "atac_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "cell_line"},
            {"data": "atac_source"},
            /*{"data": "motif_id"},
            {"data": "q_value"},*/
            {"data": "p_value"}
            /*{"data": "sequence"}*/
        ]
    });
}

function browsermsilencer(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downmsilencer?symbol="+thistf+"' download='downmsilencer.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>Silencer_Chr</th><th>Silencer_Start</th><th>Silencer_End</th><th>Overlap gene</th><th>Proximal gene</th><th>Closest gene</th><th>Cell line</th><th>Silencer_source</th><th>P_Value</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsemsilencertab",
            type: "GET",
            data: {"symbol": thistf, "method": "M_Silencer"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        buttons:[],
        dom: 'Bfrtip',
        lengthChange: false,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "silencer_chr"},
            {"data": "silencer_start"},
            {"data": "silencer_end"},
            {"data": "overlap_gene", className: "genebg"},
            {"data": "proximal_gene", className: "genebg"},
            {"data": "closest_gene", className: "genebg"},
            {"data": "cell_line"},
            {"data": "silencer_source"},
            /*{"data": "motif_id"},
            {"data": "q_value"},*/
            {"data": "p_value"}
            /*{"data": "sequence"}*/
        ]
    });
}

function browsermpromoter(thistf) {
    $("#browsepage").empty();
    var table = "<a href='downmpromoter?symbol="+thistf+"' download='downmpromoter.txt'><button class='btn btn-default' style='margin-bottom: -45px;'><span class=\"glyphicon glyphicon-floppy-save\"></span></button></a><table id='se' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>Pro_Chr</th><th>Pro_Start</th><th>Pro_End</th><th>Gene</th><th>Strand</th><th>P_Value</th></tr></thead><tbody></tbody></table>";
    $("#browsepage").html(table);
    $('#se').DataTable({
        ajax: {
            url: "browsempromotertab",
            type: "GET",
            data: {"symbol": thistf, "method": "M_Promoter"}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: false,
        scrollX: true,
        dom: 'Bfrtip',
        lengthChange: false,
        buttons:[],
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "pro_chr"},
            {"data": "pro_start"},
            {"data": "pro_end"},
            {"data": "gene", className: "genebg"},
            {"data": "strand"},
            /*{"data": "motif_id"},
            {"data": "q_value"},*/
            {"data": "p_value"}
            /*{"data": "sequence"}*/
        ]
    });
}

/*切换方法时执行*/
function method(method) {
    currenMethod = method;

    if (currenMethod == "Genemapper") {
        $("#imgs").attr("src", "img/Genemapper_cjx.png");
        $(".active").removeClass("active");
        $("#genemapperli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        genemapper(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "SE") {
        $("#imgs").attr("src", "img/SE_cjx.png");
        $(".active").removeClass("active");
        $("#seli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        se(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "TE") {
        $("#imgs").attr("src", "img/TE_cjx.png");
        $(".active").removeClass("active");
        $("#teli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        te(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "ATAC") {
        $("#imgs").attr("src", "img/ATAC_cjx.png");
        $(".active").removeClass("active");
        $("#atacli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        atac(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "Silencer") {
        $("#imgs").attr("src", "img/Silencer_cjx.png");
        $(".active").removeClass("active");
        $("#silencerli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        silencer(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "Promoter") {
        $("#imgs").attr("src", "img/promoter_cjx.png");
        $(".active").removeClass("active");
        $("#promoterli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        promoter(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "BETA") {
        $("#imgs").attr("src", "img/beta_cjx.png");
        $(".active").removeClass("active");
        $("#betali").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        beta(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "Knock") {
        $("#imgs").attr("src", "img/Knock_cjx.png");
        $(".active").removeClass("active");
        $("#knockli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        knock(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "Curate") {
        $("#imgs").attr("src", "img/Curate_cjx.png");
        $(".active").removeClass("active");
        $("#curateli").addClass("active");
        var data = getCell(currentf);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        curate(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "M_SE") {
        $("#imgs").attr("src", "img/fimo_SE_cjx.png");
        $(".active").removeClass("active");
        $("#mseli").addClass("active");
        var data = getCellBymotif(currentf, "M_SE", currengene);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        motif_se(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "M_TE") {
        $("#imgs").attr("src", "img/fimo_TE_cjx.png");
        $(".active").removeClass("active");
        $("#mteli").addClass("active");
        var data = getCellBymotif(currentf, "M_TE", currengene);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        motif_te(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "M_ATAC") {
        $("#imgs").attr("src", "img/fimo_ATAC_cjx.png");
        $(".active").removeClass("active");
        $("#matacli").addClass("active");
        var data = getCellBymotif(currentf, "M_ATAC", currengene);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        motif_atac(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "M_Silencer") {
        $("#imgs").attr("src", "img/fimo_silencer_cjx.png");
        $(".active").removeClass("active");
        $("#msilencerli").addClass("active");
        var data = getCellBymotif(currentf, "M_Silencer", currengene);
        cellmultOnchange = [];
        if (data.length != 0) {
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);
        motif_silencer(currentf, gene, cellmultOnchange)
    } else if (currenMethod == "M_Promoter") {
        $("#imgs").attr("src", "img/fimo_promoter_cjx.png");
        $(".active").removeClass("active");
        $("#mpromoterli").addClass("active");
        /*var data = getCellBymotif(currentf,"M_Promoter",currengene);
        cellmultOnchange=[];
        if (data.length!=0){
            cellmultOnchange.push(data[0].label)
        }
        CellMult(data);*/
        $("#tfchange").hide();
        motif_promoter(currentf, gene, cellmultOnchange)
    }
}

/*不切换方法时tf与cell变化时执行*/
function selectmethod(tf, gene, method) {
    if (method == "Genemapper") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        genemapper(tf, gene, cellmultOnchange)
    } else if (method == "SE") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        se(tf, gene, cellmultOnchange)
    } else if (method == "TE") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        te(tf, gene, cellmultOnchange)
    } else if (method == "ATAC") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        atac(tf, gene, cellmultOnchange)
    } else if (method == "Silencer") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        silencer(tf, gene, cellmultOnchange)
    } else if (method == "Promoter") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        promoter(tf, gene, cellmultOnchange)
    } else if (method == "BETA") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        beta(tf, gene, cellmultOnchange)
    } else if (method == "Knock") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        knock(tf, gene, cellmultOnchange)
    } else if (method == "Curate") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        curate(tf, gene, cellmultOnchange)
    } else if (method == "Knock") {
        var val = getCell(tf);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        knock(tf, gene, cellmultOnchange)
    } else if (method == "M_TE") {
        var val = getCellBymotif(tf, "M_TE", currengene);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        motif_te(tf, gene, cellmultOnchange)
    } else if (method == "M_SE") {
        var val = getCellBymotif(tf, "M_SE", currengene);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        motif_se(tf, gene, cellmultOnchange)
    } else if (method == "M_Silencer") {
        var val = getCellBymotif(tf, "M_Silencer", currengene);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        motif_silencer(tf, gene, cellmultOnchange)
    } else if (method == "M_ATAC") {
        var val = getCellBymotif(tf, "M_ATAC", currengene);
        cellmultOnchange = [];
        if (val.length != 0) {
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);
        motif_atac(tf, gene, cellmultOnchange)
    } else if (method == "M_Promoter") {
        var val = getCellBymotif(tf, "M_Promoter", currengene);
        /*cellmultOnchange=[];
        if (val.length!=0){
            cellmultOnchange.push(val[0].label)
        }
        CellMult(val);*/
        motif_promoter(tf, gene, cellmultOnchange)
    }
}

function selectmethodCell(tf, gene, cells, method) {
    if (method == "Genemapper") {
        genemapper(tf, gene, cells)
    } else if (method == "SE") {
        se(tf, gene, cells)
    } else if (method == "TE") {
        te(tf, gene, cells)
    } else if (method == "ATAC") {
        atac(tf, gene, cells)
    } else if (method == "Silencer") {
        silencer(tf, gene, cells)
    } else if (method == "Promoter") {
        promoter(tf, gene, cells)
    } else if (method == "BETA") {
        beta(tf, gene, cells)
    } else if (method == "Knock") {
        knock(tf, gene, cells)
    } else if (method == "Curate") {
        curate(tf, gene, cells)
    } else if (method == "M_TE") {
        motif_te(tf, gene, cells)
    } else if (method == "M_SE") {
        motif_se(tf, gene, cells)
    } else if (method == "M_Silencer") {
        motif_silencer(tf, gene, cells)
    } else if (method == "M_ATAC") {
        motif_atac(tf, gene, cells)
    } else if (method == "M_Promoter") {
        motif_promoter(tf, gene, cells)
    }
}

/*browserDetailGene*/
function genemapper(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>Chr</th><th>Start</th><th>End</th><th>TF name</th><th>Biosample name</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "Genemapper"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "chr"},
            {"data": "start"},
            {"data": "end"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function se(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>SE_Chr</th><th>SE_Start</th><th>SE_End</th><th>SE_Source</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th><th>TF name</th><th>Biosample name</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "SE"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "se_chr"},
            {"data": "se_start"},
            {"data": "se_end"},
            {"data": "se_source"},
            {"data": "rol"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function te(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>TE_Chr</th><th>TE_Start</th><th>TE_End</th><th>TE_Source</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th><th>TF name</th><th>Biosample name</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "TE"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "te_chr"},
            {"data": "te_start"},
            {"data": "te_end"},
            {"data": "te_source"},
            {"data": "rol"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function atac(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>ATAC_Chr</th><th>ATAC_Start</th><th>ATAC_End</th><th>ATAC_Source</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th><th>TF name</th><th>Biosample name</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "ATAC"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "atac_chr"},
            {"data": "atac_start"},
            {"data": "atac_end"},
            {"data": "atac_source"},
            {"data": "rol"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function silencer(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>Silencer_Chr</th><th>Silencer_Start</th><th>Silencer_End</th><th>Silencer_Source</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th><th>TF name</th><th>Biosample name</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "Silencer"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "silencer_chr"},
            {"data": "silencer_start"},
            {"data": "silencer_end"},
            {"data": "silencer_source"},
            {"data": "rol"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function promoter(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>Promoter_Chr</th><th>Promoter_Start</th><th>Promoter_End</th><th>ROL<span class='glyphicon glyphicon-question-sign' title='The overlapping length between the peak of TF ChIP-seq and regulatory region.'></span></th><th>TF name</th><th>Biosample name</th><th>Gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "Promoter"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "promoter_chr"},
            {"data": "promoter_start"},
            {"data": "promoter_end"},
            {"data": "rol"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "gene"}
        ]
    });
}

function beta(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>SampleID</th><th>TF_Chr</th><th>TF_Start</th><th>TF_End</th><th>RefSeqID</th><th>Distance</th><th>Score<span class='glyphicon glyphicon-question-sign' title='The potential power for the TF-target regulations using the BETA.'></span></th><th>TF name</th><th>Biosample name</th><th>Gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "BETA"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {
                "data": "sampleID",
                "render": function (data, type, row, meta) {
                    return "<a href='sampleid?sampleid=" + row.sampleID + "'>" + row.sampleID + "</a>";
                }
            },
            {"data": "tf_chr"},
            {"data": "tf_start"},
            {"data": "tf_end"},
            {"data": "refseqID"},
            {"data": "distance"},
            {"data": "score"},
            {"data": "tf_name"},
            {"data": "biosample_name_specific"},
            {"data": "gene"}
        ]
    });
}

function curate(symbol, gene, cells) {
    $("#tfchange").hide();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF</th><th>Gene</th><th>Source</th><th>PubmedID</th><th>Biosample_name</th><th>Repression_Activation</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "Curate"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf"},
            {"data": "gene"},
            {"data": "source"},
            {"data": "pubmedID"},
            {"data": "biosample_name"},
            {"data": "repression_Activation"}
        ]
    });
}

function knock(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>Sample ID</th><th>TF</th><th>FC</th><th>P_value</th><th>Sample_biosample_name</th><th>Sample_method</th><th>Gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "Knock"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "sample_ID"},
            {"data": "tf"},
            {"data": "fc"},
            {"data": "p_value"},
            /*{"data": "up_down"},*/
            {"data": "sample_biosample_name"},
            {"data": "sample_method"},
            {"data": "gene"}
        ]
    });
}

function motif_se(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>SE_Chr</th><th>SE_Start</th><th>SE_End</th><th>Cell line</th><th>SE_source</th><th>P_Value</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "M_SE"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "se_chr"},
            {"data": "se_start"},
            {"data": "se_end"},
            {"data": "cell_line"},
            {"data": "se_source"},
            {"data": "p_value"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function motif_te(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>TE_Chr</th><th>TE_Start</th><th>TE_End</th><th>Cell line</th><th>TE_source</th><th>P_Value</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "M_TE"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "te_chr"},
            {"data": "te_start"},
            {"data": "te_end"},
            {"data": "cell_line"},
            {"data": "te_source"},
            {"data": "p_value"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function motif_atac(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>ATAC_Chr</th><th>ATAC_Start</th><th>ATAC_End</th><th>Cell line</th><th>ATAC_source</th><th>P_Value</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "M_ATAC"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "atac_chr"},
            {"data": "atac_start"},
            {"data": "atac_end"},
            {"data": "cell_line"},
            {"data": "atac_source"},
            {"data": "p_value"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"}
        ]
    });
}

function motif_silencer(symbol, gene, cells) {
    $("#tfchange").show();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>Silencer_Chr</th><th>Silencer_Start</th><th>Silencer_End</th><th>Overlap_gene</th><th>Proximal_gene</th><th>Closest_gene</th><th>Cell line</th><th>Silencer_source</th><th>P_Value</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "M_Silencer"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "silencer_chr"},
            {"data": "silencer_start"},
            {"data": "silencer_end"},
            {"data": "overlap_gene"},
            {"data": "proximal_gene"},
            {"data": "closest_gene"},
            {"data": "cell_line"},
            {"data": "silencer_source"},
            {"data": "p_value"}
        ]
    });
};

function motif_promoter(symbol, gene, cells) {
    $("#tfchange").hide();
    $("#table").empty();
    var table = "<table id='runtable' class='table table-striped table-bordered' style='width:100%'><thead><tr><th>TF name</th><th>TF_motif_Chr</th><th>TF_motif_Start</th><th>TF_motif_End</th><th>Pro_Chr</th><th>Pro_Start</th><th>Pro_End</th><th>Gene</th><th>strand</th><th>P_Value</th></tr></thead><tbody></tbody></table>"
    $("#table").html(table);
    $('#runtable').DataTable({
        ajax: {
            url: "runtable",
            type: "GET",
            data: {"symbol": symbol, "gene": gene, "cells": cells.toString(), "method": "M_Promoter"}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        destroy: true,
        columns: [
            {"data": "tf_name"},
            {"data": "tf_motif_chr"},
            {"data": "tf_motif_start"},
            {"data": "tf_motif_end"},
            {"data": "pro_chr"},
            {"data": "pro_start"},
            {"data": "pro_end"},
            {"data": "gene"},
            {"data": "strand"},
            {"data": "p_value"}
        ]
    });
};
/*
* tf 与 cell type的mult功能
* */

/*tfname与cellline数据获取*/
function getTf() {
    var data;
    $.ajax({
        url: "tF_names",
        async: false,
        success: function (_data) {
            data = _data.data;
        },
        dataType: "json"
    });
    return data;
}

function getCell(param) {
    var data;
    $.ajax({
        url: "cellType",
        data: {tf: param, method: currenMethod, gene: currengene},
        async: false,
        success: function (_data) {
            data = _data.data;
        },
        dataType: "json"
    });
    return data;
}

function getCellBymotif(param, motifMethod, gene) {
    var data;
    $.ajax({
        url: "cellByMotif",
        data: {tf: param, "method": motifMethod, "gene": gene},
        async: false,
        success: function (_data) {
            data = _data.data;
        },
        dataType: "json"
    });
    return data;
}

function TfMult(symbol, gene, fristCell) {
    var tfData = getTf();
    currentf = symbol;
    cellmultOnchange.push(fristCell);
    tfData.unshift({label: symbol, value: symbol});
    $('#example-tf').multiselect({
        enableFiltering: true,
        includeSelectAllOption: false,
        buttonWidth: '300px',
        maxHeight: 250,
        enableCollapsibleOptGroups: true,
        dropUp: false,
        onChange: function (option, checked, select) {
            currentf = $(option).val();
            /*var val = getCell($(option).val());
            cellmultOnchange=[];
            cellmultOnchange.push(val[0].label)
            CellMult(val);*/
            selectmethod($(option).val(), gene, currenMethod)
        }
    });
    /*tfname与cell line多选框*/
    $('#example-cell').multiselect({
        enableFiltering: true,
        includeSelectAllOption: false,
        buttonWidth: '300px',
        maxHeight: 250,
        enableCollapsibleOptGroups: true,
        dropUp: false,
        onDropdownHide: function (event) {
            selectmethodCell(currentf, gene, cellmultOnchange, currenMethod)
            console.log(cellmultOnchange)
        },
        onChange: function (option, checked, select) {
            if (checked) {
                cellmultOnchange.push($(option).val());
            } else {
                cellmultOnchange.splice($.inArray($(option).val(), cellmultOnchange), 1);
            }
        }
    });
    $('#example-tf').multiselect('dataprovider', tfData);
}

function CellMult(data) {
    $('#example-cell').multiselect('dataprovider', data);
}


/*browserDetail*/
function browserData(url) {
    $('#browser').DataTable({
        ajax: {
            url: url,
            type: "GET",
            data: {"tfNamePara": tfNamePara, "tfFamilyPara": tfFamilyPara, "tfClassPara": tfClassPara}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        lengthMenu: [[15, 25, 50, -1], [15, 25, 50, "All"]],
        destroy: true,
        dom: 'Bfrtip',
        buttons:['excel','csv'],
        columns: [
            {
                "data": "symbol",
                "render": function (data, type, row, meta) {
                    return "<a href='browserDetail?symbol=" + row.symbol + "'>" + row.symbol + "</a>";
                }
            },
            {"data": "geneID"},
            {"data": "ensembl"},
            {"data": "name"},
            {"data": "family"},
            {"data": "tfClass"},
            {"data": "sample_num"}
        ]
    });
}

function tfName(url) {
    $.ajax({
        url: url,
        type: "get",
        data: {"tfNamePara": tfNamePara, "tfFamilyPara": tfFamilyPara, "tfClassPara": tfClassPara},
        async: true,
        success: function (res) {
            itemSpan(res, 4, "tfName", "tfName_Type")
        },
        dataType: "json"
    });
}

function tfFamily(url) {
    $.ajax({
        url: url,
        type: "get",
        data: {"tfNamePara": tfNamePara, "tfFamilyPara": tfFamilyPara, "tfClassPara": tfClassPara},
        async: true,
        success: function (res) {
            itemSpan(res, 4, "tfFamily", "tfFamily_Type")
        },
        dataType: "json"
    });
}

function tfClass(url) {
    $.ajax({
        url: url,
        type: "get",
        data: {"tfNamePara": tfNamePara, "tfFamilyPara": tfFamilyPara, "tfClassPara": tfClassPara},
        async: true,
        success: function (res) {
            itemSpan(res, 4, "tfClass", "tfClass_Type")
        },
        dataType: "json"
    });
}

function tfNameClick(param) {
    if (param != '') {
        if (param == tfNamePara) {
            tfNamePara = ''
        } else tfNamePara = param
        //    tfNamePara参数已设置
        browserFourTable()
    }
}

function tfFamilyClick(param) {
    if (param != '') {
        if (param == tfFamilyPara) {
            tfFamilyPara = ''
        } else tfFamilyPara = param
        //    biosampleNamePara参数已设置
        browserFourTable()
    }
}

function tfClassClick(param) {
    if (param != '') {
        if (param == tfClassPara) {
            tfClassPara = ''
        } else tfClassPara = param
        //    biosampleNamePara参数已设置
        browserFourTable()
    }
}

function browserFourTable() {
    browserData("browserData")
    tfName("tfName")
    tfFamily("tfFamily")
    tfClass("tfClass")
    datatablesShow()
}

function datatablesShow() {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // 当切换tab时，强制重新计算列宽
        $.fn.dataTable.tables({
            visible: true,
            api: true
        }).columns.adjust();
    });
    /* datatables配置结束 */
}

function itemSpan(res, size, id, id_Type) {
    $("#" + id).empty();
    for (var i = 0; i < res.data.length; i++) {
        var html
        if (i <= size) {
            html = "<span class=\"list-group-item\" onclick='" + id + "Click(\"" + res.data[i].name + "\")'><span class=\"badge\">" + res.data[i].sum + "</span>" + res.data[i].name + "</span>";
            $("#" + id).append(html);
            if (i == size) {
                $("#" + id).append("<div class=\"collapse\" id=\"" + id_Type + "\"></div>");
            }
        } else {
            html = "<span class=\"list-group-item\" onclick='" + id + "Click(\"" + res.data[i].name + "\")'><span class=\"badge\">" + res.data[i].sum + "</span>" + res.data[i].name + "</span>";
            $("#" + id_Type).append(html);
        }
    }
    if (res.data.length > size + 1) {
        $("#" + id).append("<a id=\"tissue_collapse_btn\" class=\"text-center list-group-item\" role=\"button\" data-toggle=\"collapse\" href=\"#" + id_Type + "\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-chevron-down\"></span></a>")
    }
}

function infotab(symbol) {
    $.ajax({
        url: "infotab",
        type: "get",
        data: {"symbol": symbol},
        async: true,
        success: function (res) {
            $("#num1").text(res.data.symbol);
            $("#num2").text(res.data.geneID);
            $("#num3").text(res.data.ensembl);
            $("#num4").text(res.data.name);
            $("#num5").text(res.data.family);
            $("#num6").text(res.data.sample_num);
            $("#num7").text(res.data.disease);
            $("#num8").text(res.data.goterm);
            $("#num9").text(res.data.pathway);
            $("#num10").text(res.data.hallmark);

            $("#num11").html(res.data.protein + "<span onclick='hide()' class='glyphicon glyphicon-resize-small'></span>");
            $("#num12").html(res.data.proteinSplit + "<span onclick='show()' class='glyphicon glyphicon-resize-full'></span>");
        },
        dataType: "json"
    });
}

function hide() {
    $("#num11").hide();
    $("#num12").show();
}

function show() {
    $("#num11").show();
    $("#num12").hide();
}

function tfMeWe(symbol) {
    $('#tfMeWe').DataTable({
        ajax: {
            url: "tfMeWe",
            type: "GET",
            async: true,
            data: {"symbol": symbol}
        },
        bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        serverSide: true,
        searching: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        buttons:[],
        lengthChange: false,
        "order": [[15, "desc"]],
        columns: [
            {
                "data": "gene",
                "render": function (data, type, row, meta) {
                    return "<a href='infoByGene?gene=" + row.gene + "&symbol=" + symbol + "'>" + row.gene + "</a>";
                }
            },
            {
                "data": "se",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "te",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "atac",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "silencer",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "promoter",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "beta",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "genemapper",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "knock",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "curate",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "m_se",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "m_te",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "m_atac",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "m_silencer",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "m_promoter",
                "render": function (data, type, row, meta) {
                    if (data == 1) {
                        return "<svg t=\"1619582761558\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7585\" width=\"16\" height=\"16\"><path d=\"M129.26379393 496.75646972L171.69848631 436.77111815l199.64904787 153.09448244L857.41015625 193.9458008l40.95153809 39.22119139L374.72583008 747.65673827 129.26379393 496.75646972z m0 1e-8\" fill=\"#1A8CFE\" p-id=\"7586\"></path></svg>"
                    } else return "<svg t=\"1619582359969\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"6506\" width=\"16\" height=\"16\"><path d=\"M339.839316 322.547229L702.776102 685.485231l-86.692748 86.692747L253.147785 409.239976z\" fill=\"#FF8C8C\" p-id=\"6507\"></path><path d=\"M871.071396 240.792645L339.821071 772.041753l-86.692747-86.692747 531.249108-531.249108z\" fill=\"#FF8C8C\" p-id=\"6508\"></path></svg>";
                }
            },
            {
                "data": "weight",
                "render": function (data, type, row, meta) {
                    return "<span class='badge1'>" + data + "</span>";
                }
            }
        ],
        columnDefs : [
            {targets : 1,"orderable" : false},
            {targets : 2,"orderable" : false},
            {targets : 3,"orderable" : false},
            {targets : 4,"orderable" : false},
            {targets : 5,"orderable" : false},
            {targets : 6,"orderable" : false},
            {targets : 7,"orderable" : false},
            {targets : 8,"orderable" : false},
            {targets : 9,"orderable" : false},
            {targets : 10,"orderable" : false},
            {targets : 11,"orderable" : false},
            {targets : 12,"orderable" : false},
            {targets : 12,"orderable" : false},
            {targets : 13,"orderable" : false},
            {targets : 14,"orderable" : false}
        ],
        "order" : [[ 15, 'desc' ]]
    });
}

function load() {
    $("#litu").empty();
    var img = "<img src='img/loading2.gif' style='width: 500px;'>";
    $("#litu").html(img);
}

function weigthGraph(symbol, freq, method, cellLine, sampleID) {
    var graph;
    $.ajax({
        url: "graph",
        type: "get",
        data: {"symbol": symbol, "freq": freq, "method": method, "cellLine": cellLine, "sampleID": sampleID},
        async: false,
        success: function (res) {
            $("#litu").empty();
            var div = "<div id='main' style='width: 100%;height: 500px;'></div>";
            $("#litu").html(div);
            var myChart = echarts.init(document.getElementById("main"));
            graph = res;
            graph.nodes.forEach(function (node) {
                node.label = {
                    show: node.value
                };
            });
            option = {
                title: {
                    text: symbol + ' Target gene network',
                    // subtext: 'Default layout',
                    top: 'top',
                    left: 'center'
                },
                tooltip: {},
                legend: [{
                    // selectedMode: 'single',
                    data: graph.categories.map(function (a) {
                        return a.name;
                    }),
                    top: '5%'
                }],
                animationDuration: 1500,
                animationEasingUpdate: 'quinticInOut',
                color: ["#5dd544", "#ffa500"],
                series: [
                    {
                        name: 'Les Miserables',
                        type: 'graph',
                        layout: 'force',
                        data: graph.nodes,
                        links: graph.links,
                        categories: graph.categories,
                        roam: true,
                        focusNodeAdjacency: true,
                        label: {
                            position: 'center',
                            formatter: '{b}'
                        },
                        lineStyle: {
                            color: '#d1d1d1',
                            curveness: 0
                        },
                        force: {
                            layoutAnimation: false,
                            repulsion: 200
                        },
                        emphasis: {
                            focus: 'adjacency',
                            lineStyle: {
                                width: 5
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
        dataType: "json"
    });

}

function geneinfo(gene) {
    $.ajax({
        url: "geneinfo",
        type: "get",
        data: {"gene": gene},
        async: true,
        success: function (res) {
            $("#gnum1").text(res.data.geneName);
            $("#gnum2").text(res.data.geneID);
            $("#gnum3").text(res.data.geneBiotype);
            $("#gnum4").text(res.data.geneSource);
            $("#gnum5").text(res.data.chr);
            $("#gnum6").text(res.data.start);
            $("#gnum7").text(res.data.end);
            $("#gnum8").text(res.data.strand);
        },
        dataType: "json"
    });
}

function pathwayTab(symbol) {
    $('#pathwayTab').DataTable({
        ajax: {
            url: "pathwayTab",
            type: "GET",
            data: {"symbol": symbol}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {"data": "tf"},
            {"data": "pathway_name"},
            {"data": "pathway_source"},
            {"data": "gene_number"},
            {"data": "edge_number"}
        ]
    });
}

function gotermTab(symbol) {
    $('#gotermTab').DataTable({
        ajax: {
            url: "gotermTab",
            type: "GET",
            data: {"symbol": symbol}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {"data": "tf"},
            {"data": "go_term"}
        ]
    });
}

function showls123(sampleid, state) {
    console.log(sampleid+","+state)
    var ida = "#" + sampleid + "a";
    var ids = "#" + sampleid + "s";
    if (state == "all") {
        console.log("allllllllllll")
        console.log(ida)
        console.log(ids)
        $(ida).toggle();
        $(ids).toggle();
    } else {
        console.log("shortttttttt")
        $(ida).toggle();
        $(ids).toggle();
    }
    console.log("00000000000000000000")
}

function anatable(userID,method){
    console.log(userID+method)
    var number = "num";
    $('#analysisResult').DataTable({
        ajax: {
            url: "analysisgetresult",
            type: "GET",
            data: {"userID": userID,"method":method}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {
                "data": "tf",
                "render": function (data, type, row, meta) {
                    return "<a class='link-info' href='browserDetail?symbol=" + row.tf + "'>" + row.tf + "</a>";
                }
            },
            {
                "data": "sampleid",
                "render": function (data, type, row, meta) {
                    number=number+1
                    if (row.sampleid.substring(0,1)=="_"){
                        return row.sampleid
                    }else {
                        return "<a class='link-info' href='sampleid?sampleid=" + row.sampleid + "'>" + row.sampleid + "</a>";
                    }
                }
            },
            /*{"data": "method"},*/
            {"data": "biosample_name"},
            {
                "data": "core_enrichment",
                "render": function (data, type, row, meta) {
                    if (row.core_enrichment==""){
                        return row.core_enrichment
                    }else if(row.core_enrichment.length>20){
                        return "<p id='"+row.sampleid+"s'>"+row.core_enrichment.substring(0,20)+"<span class='glyphicon glyphicon-resize-full' onclick='showls123(&quot;"+row.sampleid+"&quot;,&quot;all&quot;)'></span></p>"+
                            "<p id='"+row.sampleid+"a' style='display: none'>"+row.core_enrichment+"<span class='glyphicon glyphicon-resize-small' onclick='showls123(&quot;"+row.sampleid+"&quot;,&quot;short&quot;)'></span></p>"
                    }else {
                        return row.core_enrichment;
                    }
                }
            },
            {"data": "genenumber"},
            {"data": "pvalue"},
            {"data": "fdr"},
            {"data": "tf",
                "render": function (data, type, row, meta) {
                    return '<img src="img/venn.png" ' +
                        'onclick="venngraph(\''+row.tf+'\',\''+row.sampleid+'\',\''+row.method+'\',\''+row.core_enrichment+'\')" ' +
                        'data-toggle="modal" data-target="#ana1Modal" style="width:18px">';
                }
            }
        ]
    });
}
function hallmarkTab(symbol) {
    $('#hallmarkTab').DataTable({
        ajax: {
            url: "hallmarkTab",
            type: "GET",
            data: {"symbol": symbol}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {"data": "gene"},
            {"data": "cancer_hallmark"},
            {"data": "go_term_id"},
            {"data": "go_term_name"}
        ]
    });
}

function diseaseTab(symbol) {
    $('#diseaseTab').DataTable({
        ajax: {
            url: "diseaseTab",
            type: "GET",
            data: {"symbol": symbol}
        },
        // bStateSave: false,
        oLanguage: {
            sLoadingRecords: '<img src="img/loading.gif" style="width: 350px;">'
        },
        // serverSide: true,
        searching: true,
        paging: true,
        ordering: true,
        scrollX: true,
        dom: 'Bfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {"data": "tf"},
            {"data": "diseasename"},
            {"data": "diseasetype"},
            {"data": "diseaseclass"},
            {"data": "diseasesemantictype"},
            {"data": "source"}
        ]
    });
}

function graph(tf) {
    var method = currenMethod;
    var v_freq = "";
    var v_sampleID = "";
    var v_cell = "";
    var vue = new Vue({
        el: '#app',
        data: {
            cellLines: ["K562", "Viridiplantae"],
            cellLine: "K562",
            sampleIDs: ["Sample_02_2075", "Sample_02_2084"],
            sampleID: "Sample_02_2075",
            freqs: ["12", "7"],
            freq: "12",
        },
        methods: {
            init: function () {
                var _self = this;
                load()
                $.get("cellLines", {tf: tf, method: currenMethod}, function (_data) {
                    _self.cellLines = _data.data;
                    _self.cellLine = _data.data[0];
                    v_cell = _data.data[0];
                    $.get("sampleIDs", {tf: tf, method: currenMethod, cellLine: _data.data[0]}, function (_data) {
                        _self.sampleIDs = _data.data;
                        _self.sampleID = _data.data[0];
                        v_sampleID = _data.data[0];
                        $.get("freqs", {
                            tf: tf,
                            method: currenMethod,
                            cellLine: _self.cellLine,
                            sampleID: _data.data[0]
                        }, function (_data) {
                            _self.freqs = _data.data;
                            _self.freq = _data.freqDe;
                            v_freq = _data.freqDe;
                            weigthGraph(tf, v_freq, currenMethod, v_cell, v_sampleID)
                        }, "json");
                    }, "json");
                }, "json");
            },
            change1: function change1() {
                var _self = this;
                load()
                var method = $("#method ").val();
                $.get("cellLines", {tf: tf, method: method}, function (_data) {
                    _self.cellLines = _data.data;
                    _self.cellLine = _data.data[0];
                    v_cell = _data.data[0];
                    $.get("sampleIDs", {tf: tf, method: method, cellLine: _data.data[0]}, function (_data) {
                        _self.sampleIDs = _data.data;
                        _self.sampleID = _data.data[0];
                        v_sampleID = _data.data[0];
                        $.get("freqs", {
                            tf: tf,
                            method: method,
                            cellLine: _self.cellLine,
                            sampleID: _data.data[0]
                        }, function (_data) {
                            _self.freqs = _data.data;
                            _self.freq = _data.freqDe;
                            v_freq = _data.freqDe;
                            weigthGraph(tf, v_freq, method, v_cell, v_sampleID)
                        }, "json");
                    }, "json");
                }, "json");
            },
            change2: function change2() {
                var _self = this;
                load()
                var method = $("#method ").val();
                var cell = $("#cell").val();
                $.get("sampleIDs", {tf: tf, method: method, cellLine: cell}, function (_data) {
                    _self.sampleIDs = _data.data;
                    _self.sampleID = _data.data[0];
                    v_sampleID = _data.data[0];
                    $.get("freqs", {
                        tf: tf,
                        method: method,
                        cellLine: _self.cellLine,
                        sampleID: _data.data[0]
                    }, function (_data) {
                        _self.freqs = _data.data;
                        _self.freq = _data.freqDe;
                        v_freq = _data.freqDe;
                        weigthGraph(tf, v_freq, method, cell, v_sampleID)
                    }, "json");
                }, "json");
            },
            change3: function change3() {
                var _self = this;
                load()
                var method = $("#method ").val();
                var cell = $("#cell").val();
                var sample = $("#sample").val();
                $.get("freqs", {tf: tf, method: method, cellLine: cell, sampleID: sample}, function (_data) {
                    _self.freqs = _data.data;
                    _self.freq = _data.freqDe;
                    v_freq = _data.freqDe;
                    weigthGraph(tf, v_freq, method, cell, sample)
                }, "json");
            },
            change4: function change4() {
                load()
                var method = $("#method ").val();
                var cell = $("#cell").val();
                var sample = $("#sample").val();
                var freq = $("#freq").val();
                weigthGraph(tf, freq, method, cell, sample)
            }
        }
    });
    vue.init();
}
