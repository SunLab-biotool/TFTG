function param(tfs) {
    var input = $("#genestxt").text()
    var sCList;
    var vue = new Vue({
        el: '#app',
        data: {
            methodParams: ["C_SE", "C_TE"],
            methodParam: "C_SE",
            cellLineParams: ["H1", "H9"],
            cellLineParam: "H1",
            sampleParams: ["Sample_02_3099", "Sample_02_3098"],
            sampleParam: "Sample_02_3099",
            geneParams: ["overlap_gene", "proximal_gene","closest_gene","all"],
            geneParam: "closest_gene",
            freqParams: ["10", "8","6", "4","2", "1"],
            freqParam: "4",
            scoreParams: ["100", "200","500", "all"],
            scoreParam: "100",
        },
        methods: {
            init: function () {
                load()
                var _self = this;
                $.get("methodParamUrl", {tfs: tfs.toString()}, function (_data) {
                    _self.methodParams = _data.data;
                    _self.methodParam = _data.data[0];
                    if (_self.methodParam=="C_Promoter"){
                        $("#genes").hide();
                        $("#scores").hide();
                        $("#freqs").show();
                        $("#celllines").show();
                        _self.geneParams = ["genepromoter"];
                        _self.geneParam = "genepromoter";
                    }else if (_self.methodParam=="C_SE"||_self.methodParam=="C_TE"||_self.methodParam=="C_ATAC"||_self.methodParam=="C_Silencer"){
                        $("#genes").show();
                        $("#scores").hide();
                        $("#freqs").show();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "all";
                    }else if (_self.methodParam=="C_BETA"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["genebeta"];
                        _self.geneParam = "genebeta";
                    }else if (_self.methodParam=="C_Genemapper"){
                        $("#genes").show();
                        $("#freqs").hide();
                        $("#scores").hide();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "overlap_gene";
                    }else if (_self.methodParam=="Curate"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").hide();
                        $("#celllines").show();
                        _self.geneParams = ["genecurate"];
                        _self.geneParam = "genecurate";
                    }else if (_self.methodParam=="Knock"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["geneknock"];
                        _self.geneParam = "geneknock";
                    }else if (_self.methodParam=="M_Promoter"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").hide();
                        _self.scoreParam = "100";
                    }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                        $("#genes").show();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "overlap_gene";
                        _self.scoreParam = "100";
                    }
                    if (_self.methodParam=="M_Promoter"){
                        $.get("graph1", {tfs:tfs.toString(),cellline:_self.cellLineParam,input:input,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }else {
                        $.get("cellLineParamUrl", {method: _data.data[0], tfs: tfs.toString()}, function (_data) {
                            /*_data中有两个集合，分别是sampleidlist、celllinelist*/
                            _self.cellLineParams = _data.cellLineList;
                            _self.cellLineParam = _data.cellLineList[0];

                            if (_self.methodParam=="Curate"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else if (_self.methodParam=="Knock"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else {
                                if (_self.methodParam=="C_SE"){
                                    if($.inArray("A549",_data.cellLineList)!=-1){
                                        _self.cellLineParam = "A549";
                                    }else {
                                        _self.cellLineParam = _data.cellLineList[0];
                                    }
                                }
                                var sampleid = [];
                                sCList = _data.sCList;
                                for (var i=0;i<_data.sCList.length;i++){
                                    if (_self.cellLineParam==_data.sCList[i].cellLine){
                                        sampleid.push(_data.sCList[i].sampleID);
                                    }
                                }
                                _self.sampleParams = sampleid;
                                _self.sampleParam = sampleid[0];
                                if (_self.methodParam=="C_SE"||_self.methodParam=="C_TE"||_self.methodParam=="C_ATAC"||_self.methodParam=="C_Silencer"||_self.methodParam=="C_Promoter"){

                                    $.get("freqParamUrl", {sample: _self.sampleParams.toString(),method: _self.methodParam,tfs:tfs.toString()}, function (_data) {
                                        _self.freqParams=_data.data
                                        _self.freqParam="1"

                                        /*作图start
                                        * */
                                        $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                            network(_data)
                                            tablegene(_data.userID)
                                        }, "json");
                                        /*作图end
                                        * */
                                    }, "json");
                                }else {
                                    $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                        network(_data)
                                        tablegene(_data.userID)
                                    }, "json");
                                }
                            }
                        }, "json");
                    }
                }, "json");
            },
            change1: function () {
                load()
                var _self = this;
                $.get("methodParamUrl", {tfs: tfs.toString()}, function (_data) {
                    if (_self.methodParam=="C_Promoter"){
                        $("#genes").hide();
                        $("#scores").hide();
                        $("#freqs").show();
                        $("#celllines").show();
                        _self.geneParams = ["genepromoter"];
                        _self.geneParam = "genepromoter";
                    }else if (_self.methodParam=="C_SE"||_self.methodParam=="C_TE"||_self.methodParam=="C_ATAC"||_self.methodParam=="C_Silencer"){
                        $("#genes").show();
                        $("#scores").hide();
                        $("#freqs").show();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "overlap_gene";
                    }else if (_self.methodParam=="C_BETA"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["genebeta"];
                        _self.geneParam = "genebeta";
                    }else if (_self.methodParam=="C_Genemapper"){
                        $("#genes").show();
                        $("#freqs").hide();
                        $("#scores").hide();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "overlap_gene";
                    }else if (_self.methodParam=="Curate"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").hide();
                        $("#celllines").show();
                        _self.geneParams = ["genecurate"];
                        _self.geneParam = "genecurate";
                    }else if (_self.methodParam=="Knock"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["geneknock"];
                        _self.geneParam = "geneknock";
                    }else if (_self.methodParam=="M_Promoter"){
                        $("#genes").hide();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").hide();
                        _self.scoreParam = "100";
                    }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                        $("#genes").show();
                        $("#freqs").hide();
                        $("#scores").show();
                        $("#celllines").show();
                        _self.geneParams = ["overlap_gene", "proximal_gene","closest_gene","all"];
                        _self.geneParam = "overlap_gene";
                        _self.scoreParam = "100";
                    }
                    if (_self.methodParam=="M_Promoter"){
                        $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }else {
                        $.get("cellLineParamUrl", {method: _self.methodParam, tfs: tfs.toString()}, function (_data) {
                            /*_data中有两个集合，分别是sampleidlist、celllinelist*/
                            _self.cellLineParams = _data.cellLineList;
                            _self.cellLineParam = _data.cellLineList[0];
                            if (_self.methodParam=="Curate"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else if (_self.methodParam=="Knock"){
                                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }else {
                                var sampleid = [];
                                sCList = _data.sCList;
                                for (var i=0;i<_data.sCList.length;i++){
                                    if (_data.cellLineList[0]==_data.sCList[i].cellLine){
                                        sampleid.push(_data.sCList[i].sampleID);
                                    }
                                }
                                _self.sampleParams = sampleid;
                                _self.sampleParam = sampleid[0];
                                if (_self.methodParam=="C_SE"||_self.methodParam=="C_TE"||_self.methodParam=="C_ATAC"||_self.methodParam=="C_Silencer"||_self.methodParam=="C_Promoter"){
                                    $.get("freqParamUrl", {sample: _self.sampleParams.toString(),method: _self.methodParam,tfs:tfs.toString()}, function (_data) {
                                        _self.freqParams=_data.data
                                        _self.freqParam=_data.data[0]
                                        /*作图start
                                        * */
                                        $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                            network(_data)
                                            tablegene(_data.userID)
                                        }, "json");
                                        /*作图end
                                        * */
                                    }, "json");
                                }else {
                                    $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                        network(_data)
                                        tablegene(_data.userID)
                                    }, "json");
                                }
                            }
                        }, "json");
                    }
                }, "json");
            },
            change2: function () {
                load()
                var _self = this;
                if (_self.methodParam=="Curate"){
                    $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                    $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="Knock"){
                    $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else {
                    var cellline = _self.cellLineParam
                    var sampleid = [];
                    for (var i=0;i<sCList.length;i++){
                        if (cellline==sCList[i].cellLine){
                            sampleid.push(sCList[i].sampleID);
                        }
                    }
                    _self.sampleParams = sampleid;
                    _self.sampleParam = sampleid[0];
                    if (_self.methodParam=="C_SE"||_self.methodParam=="C_TE"||_self.methodParam=="C_ATAC"||_self.methodParam=="C_Silencer"||_self.methodParam=="C_Promoter"){
                        $.get("freqParamUrl", {sample: _self.sampleParams.toString(),method: _self.methodParam,tfs:tfs.toString()}, function (_data) {
                            _self.freqParams=_data.data
                            _self.freqParam=_data.data[0]
                            /*作图start
                            * */
                            $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                            /*作图end
                            * */
                        }, "json");
                    }else {
                        $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }
                }
            },
            change3: function () {
                load()
                var _self = this;
                /*作图start
                * */
                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                    network(_data)
                }, "json");
                /*作图end
                * */
            },
            change4: function () {
                load()
                var _self = this;
                /*作图start
                * */
                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                    network(_data)
                }, "json");
                /*作图end
                * */
            },
            change5: function () {
                load()
                var _self = this;
                /*作图start
                * */
                $.get("graph1", {tfs:tfs.toString(),input:input,cellline:_self.cellLineParam,method: _self.methodParam,sample:_self.sampleParams.toString(),gene:_self.geneParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                    network(_data)
                }, "json");
                /*作图end
                * */
            }
        }
    });
    vue.init();
}
function load() {
    $("#networkdiv").empty();
    var img = "<img src='img/loading2.gif' style='width: 400px;height: 600px'>";
    $("#networkdiv").html(img);
}
function network(graph) {
    $("#networkdiv").empty();
    var div = "<div id='networkAnalysis' style='width: 100%;height: 700px;'></div>";
    $("#networkdiv").html(div);
    var myChart = echarts.init(document.getElementById("networkAnalysis"));
    var categories = [];
    for (var i = 0; i < 4; i++) {
        if (i == 0) {
            categories[i] = {
                name: 'TF'
            };
        } else if (i == 1) {
            categories[i] = {
                name: 'Regulation element'
            };
        } else if (i == 2) {
            categories[i] = {
                name: 'Gene'
            };
        }
    }
    option = {
        title: {
            text: '',
            subtext: '',
            top: 'bottom',
            left: 'right'
        },
        tooltip: {},
        legend: [{
            orient: 'vertical',
            left: 'right',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        color: ["#c03531", "#2e4351", "#cc7d61"],
        series: [
            {
                name: '',
                type: 'graph',
                layout: 'none',
                data: graph.node,
                links: graph.edge,
                categories: categories,
                roam: false,
                focusNodeAdjacency: true,
                itemStyle: {
                    normal: {
                        borderColor: '#fff',
                        borderWidth: 1,
                        shadowBlur: 10,
                        shadowColor: 'rgba(0, 0, 0, 0.3)'
                    }
                },
                emphasis: {
                    lineStyle: {
                        width: 10
                    }
                },
                symbolSize: 20,
                label: {
                    normal: {
                        show: false,
                        position: "top",
                        textStyle: {
                            fontSize: 10,
                            color: "black",

                        },
                    },
                    position: 'center',
                    formatter: '{b}'
                },
                force: {
                    layoutAnimation: false,
                    repulsion: 200
                },
                lineStyle: {
                    color: '#d1d1d1',
                    curveness: 0
                }
            }
        ]
    };
    myChart.setOption(option);
}

function tablegene(userID) {
    $('#tablegene').DataTable({
        ajax: {
            url: "ana2network",
            data: {"userID": userID},
            type: "GET"
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
        "order": [4, "desc"],
        // lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        destroy: true,
        columns: [
            {
                "data": "gene",
                "render": function (data, type, row, meta) {
                    if (row.tfOrNot == 1) {
                        return "<a href='browserDetail?symbol=" + row.gene + "'>" + row.gene + "</a>";
                    } else return row.gene;
                }
            },
            {"data": "degree"},
            {"data": "betweenness"},
            {"data": "closeness"},
            {"data": "tfOrNot"},
            {"data": "inputOrNot"}
        ]
    });
}
