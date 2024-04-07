function cellline(genes) {
    var vue = new Vue({
        el: '#app',
        data: {
            cellLines: ["K562", "Viridiplantae"],
            cellLine: "K562",
            weights: ["1", "2"],
            weight: "1"
        },
        methods: {
            init: function () {
                load()
                var _self = this;
                var genes = $("#genestxt").text();
                $.get("nospecificity", {genes: genes}, function (_data) {
                    _self.weights = _data.data;
                    _self.weight = _data.data[0];
                    var weight = _data.data[0];
                    $.get("runnetworknoSpecificity", {method: "noSpecificity",weight: weight,genes: genes}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }, "json");
            },
            change3: function change3() {
                load()
                var weight = $("#weight").val();
                $.get("runnetworknoSpecificity", {method: "noSpecificity",weight: weight,genes: genes}, function (_data) {
                    console.log(_data)
                    network(_data)
                    tablegene(_data.userID)
                }, "json");
            },
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
    console.log(graph)
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