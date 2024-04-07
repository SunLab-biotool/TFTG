function pic0(symbol) {
    var pico = echarts.init(document.getElementById('pic0'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp0",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic1(symbol) {
    var pico = echarts.init(document.getElementById('pic1'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp1",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic2(symbol) {
    var pico = echarts.init(document.getElementById('pic2'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp2",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic3(symbol) {
    var pico = echarts.init(document.getElementById('pic3'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp3",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic4(symbol) {
    var pico = echarts.init(document.getElementById('pic4'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp4",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 70},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic5(symbol) {
    var pico = echarts.init(document.getElementById('pic5'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp5",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}

function pic6(symbol) {
    var pico = echarts.init(document.getElementById('pic6'));
    var datas = [];
    var names = [];
    $.ajax({
        url: "exp6",
        type: "get",
        data: {"symbol": symbol},
        async: false,
        success: function (res) {
            for (var p in res.data) {
                names.push(p);
                datas.push(res.data[p])
            }

        },
        dataType: "json"
    });

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category', axisLabel: {interval: 0, rotate: 30},
            data: names
        },
        yAxis: {},
        series: [{
            data: datas,
            itemStyle: {
                normal: {
                    //每根柱子颜色设置
                    color: function (params) {
                        let colorList = [
                            "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0", "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#bda29a", "#546570", "#c4ccd3", "#4babde", "#ffde76", "#e43c59", "#5F9EA0"
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
            type: 'bar'
        }]
    };
    pico.setOption(option);
}