function param(tfs) {
    var input = $("#genestxt").text()
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
                $.get("methodParamGeneUrl", {tfs: tfs.toString()}, function (_data) {
                    _self.methodParams = _data.data;
                    _self.methodParam = _data.data[0];
                    if (_self.methodParam=="C_SE"||_self.methodParam=="C_Silencer"){
                        see1()
                        $.get("cellLineCParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data
                            if (_self.methodParam=="C_SE"){
                                if($.inArray("K562",_data.data)!=-1){
                                    _self.cellLineParam = "K562";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                            }else {
                                if($.inArray("HepG2",_data.data)!=-1){
                                    _self.cellLineParam = "HepG2";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                            }
                            $.get("freqCParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                _self.freqParams = _data.data
                                if (_self.methodParam=="C_SE"){
                                    if($.inArray("1",_data.data)!=-1){
                                        _self.freqParam = "1";
                                    }else {
                                        _self.freqParam = _data.data[0];
                                    }
                                }else {
                                    if($.inArray("1",_data.data)!=-1){
                                        _self.freqParam = "1";
                                    }else {
                                        _self.freqParam = _data.data[0];
                                    }
                                }
                                $.get("graphCParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                        see2()
                        $.get("cellLineMParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            if (_self.methodParam=="M_TE"){
                                if($.inArray("A549",_data.data)!=-1){
                                    _self.cellLineParam = "A549";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                                _self.geneParam = "closest_gene";
                                _self.scoreParam = "100";
                            }else if (_self.methodParam=="M_ATAC"){
                                if($.inArray("Hela",_data.data)!=-1){
                                    _self.cellLineParam = "Hela";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                                _self.geneParam = "closest_gene";
                                _self.scoreParam = "100";
                            }else if (_self.methodParam=="M_Silencer"){
                                if($.inArray("gastrocnemius_medials",_data.data)!=-1){
                                    _self.cellLineParam = "gastrocnemius_medials";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                                _self.geneParam = "closest_gene";
                                _self.scoreParam = "100";
                            }else if (_self.methodParam=="M_SE"){
                                if($.inArray("suprapubic_skin",_data.data)!=-1){
                                    _self.cellLineParam = "suprapubic_skin";
                                }else {
                                    _self.cellLineParam = _data.data[0];
                                }
                                _self.geneParam = "all";
                                _self.scoreParam = "all";
                            }
                            $.get("graphMParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="C_Promoter"){
                        see3()
                        $.get("cellLineCproParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            _self.cellLineParam = _data.data[0];
                            $.get("freqCproParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                _self.freqParams = _data.data;
                                _self.freqParam = _data.data[0];
                                $.get("graphCproParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="M_Promoter"){
                        see4()
                        $.get("graphMproParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }else if (_self.methodParam=="Curate"){
                        see5()
                        $.get("cellLineCurateParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            if($.inArray("MCF",_data.data)!=-1){
                                _self.cellLineParam = "MCF";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            $.get("graphCurateParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="Knock"){
                        see6()
                        $.get("cellLineKnockParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            if($.inArray("K562",_data.data)!=-1){
                                _self.cellLineParam = "K562";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            $.get("graphKnockParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="C_BETA"){
                        see7()
                        $.get("cellLineCbetaParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            _self.cellLineParam = _data.data[0];
                            $.get("graphCbetaParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="C_Genemapper"){
                        see8()
                        $.get("cellLineatgParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            console.log($.inArray("22RV1",_data.data))
                            if($.inArray("22RV1",_data.data)!=-1){
                                _self.cellLineParam = "22RV1";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            _self.geneParam = "closest_gene";
                            $.get("graphgenemappParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                console.log(_data)
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }else if (_self.methodParam=="C_TE"){
                        see9()
                        $.get("cellLineatgParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                            _self.cellLineParams = _data.data;
                            _self.cellLineParam = _data.data[0];
                            $.get("freqCteParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                _self.freqParams = _data.data;
                                _self.freqParam = _data.data[0];
                                $.get("graphCteParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                    network(_data)
                                    tablegene(_data.userID)
                                }, "json");
                            }, "json");
                        }, "json");
                    }
                }, "json");
            },
            change1: function () {
                load()
                var _self = this;

                if (_self.methodParam == "C_SE" || _self.methodParam == "C_Silencer") {
                    see1()
                    $.get("cellLineCParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        _self.cellLineParam = _data.data[0];
                        $.get("freqCParamUrl", {
                            tfs: tfs.toString(),
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            _self.freqParams = _data.data;
                            _self.freqParam = _data.data[0];
                            $.get("graphCParamUrl", {
                                tfs: tfs.toString(),
                                input: input,
                                method: _self.methodParam,
                                genetype: _self.geneParam,
                                cellLine: _self.cellLineParam,
                                freq: _self.freqParam,
                                score: _self.scoreParam
                            }, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "M_SE" || _self.methodParam == "M_TE" || _self.methodParam == "M_ATAC" || _self.methodParam == "M_Silencer") {
                    see2()
                    $.get("cellLineMParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        if (_self.methodParam=="M_TE"){
                            if($.inArray("A549",_data.data)!=-1){
                                _self.cellLineParam = "A549";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            _self.geneParam = "closest_gene";
                            _self.scoreParam = "100";
                        }else if (_self.methodParam=="M_ATAC"){
                            if($.inArray("Hela",_data.data)!=-1){
                                _self.cellLineParam = "Hela";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            _self.geneParam = "closest_gene";
                            _self.scoreParam = "100";
                        }else if (_self.methodParam=="M_Silencer"){
                            if($.inArray("gastrocnemius_medials",_data.data)!=-1){
                                _self.cellLineParam = "gastrocnemius_medials";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            _self.geneParam = "closest_gene";
                            _self.scoreParam = "100";
                        }else if (_self.methodParam=="M_SE"){
                            if($.inArray("suprapubic_skin",_data.data)!=-1){
                                _self.cellLineParam = "suprapubic_skin";
                            }else {
                                _self.cellLineParam = _data.data[0];
                            }
                            _self.geneParam = "closest_gene";
                            _self.scoreParam = "100";
                        }
                        $.get("graphMParamUrl", {
                            tfs: tfs.toString(),
                            input: input,
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "C_Promoter") {
                    see3()
                    $.get("cellLineCproParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        _self.cellLineParam = _data.data[0];
                        $.get("freqCproParamUrl", {
                            tfs: tfs.toString(),
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            _self.freqParams = _data.data;
                            _self.freqParam = _data.data[0];
                            $.get("graphCproParamUrl", {
                                tfs: tfs.toString(),
                                input: input,
                                method: _self.methodParam,
                                genetype: _self.geneParam,
                                cellLine: _self.cellLineParam,
                                freq: _self.freqParam,
                                score: _self.scoreParam
                            }, function (_data) {
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "M_Promoter") {
                    see4()
                    $.get("graphMproParamUrl", {
                        tfs: tfs.toString(),
                        input: input,
                        method: _self.methodParam,
                        genetype: _self.geneParam,
                        cellLine: _self.cellLineParam,
                        freq: _self.freqParam,
                        score: _self.scoreParam
                    }, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                } else if (_self.methodParam == "Curate") {
                    see5()
                    $.get("cellLineCurateParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        if($.inArray("MCF",_data.data)!=-1){
                            _self.cellLineParam = "MCF";
                        }else {
                            _self.cellLineParam = _data.data[0];
                        }
                        $.get("graphCurateParamUrl", {
                            tfs: tfs.toString(),
                            input: input,
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "Knock") {
                    see6()
                    $.get("cellLineKnockParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        if($.inArray("K562",_data.data)!=-1){
                            _self.cellLineParam = "K562";
                        }else {
                            _self.cellLineParam = _data.data[0];
                        }
                        $.get("graphKnockParamUrl", {
                            tfs: tfs.toString(),
                            input: input,
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "C_BETA") {
                    see7()
                    $.get("cellLineCbetaParamUrl", {tfs: tfs.toString(), method: _self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        _self.cellLineParam = _data.data[0];
                        $.get("graphCbetaParamUrl", {
                            tfs: tfs.toString(),
                            input: input,
                            method: _self.methodParam,
                            genetype: _self.geneParam,
                            cellLine: _self.cellLineParam,
                            freq: _self.freqParam,
                            score: _self.scoreParam
                        }, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                } else if (_self.methodParam == "C_Genemapper"){
                    see8()
                    $.get("cellLineatgParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        console.log($.inArray("22RV1",_data.data))
                        if($.inArray("22RV1",_data.data)!=-1){
                            _self.cellLineParam = "22RV1";
                        }else {
                            _self.cellLineParam = _data.data[0];
                        }
                        _self.geneParam = "closest_gene";
                        $.get("graphgenemappParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            console.log(_data)
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                } else if (_self.methodParam=="C_TE"){
                    see9()
                    $.get("cellLineatgParamUrl", {tfs: tfs.toString(),method:_self.methodParam}, function (_data) {
                        _self.cellLineParams = _data.data;
                        _self.cellLineParam = _data.data[0];
                        $.get("freqCteParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            _self.freqParams = _data.data;
                            _self.freqParam = _data.data[0];
                            $.get("graphCteParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                                console.log(_data)
                                network(_data)
                                tablegene(_data.userID)
                            }, "json");
                        }, "json");
                    }, "json");
                }

            },
            change2: function () {
                load()
                var _self = this;
                if (_self.methodParam=="C_SE"||_self.methodParam=="C_Silencer"){
                    $.get("freqCParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        _self.freqParams = _data.data;
                        _self.freqParam = _data.data[0];
                        $.get("graphCParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                    $.get("graphMParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Promoter"){
                    $.get("freqCproParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        _self.freqParams = _data.data;
                        _self.freqParam = _data.data[0];
                        $.get("graphCproParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                }else if (_self.methodParam=="M_Promoter"){

                }else if (_self.methodParam=="Curate"){
                    $.get("graphCurateParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="Knock"){
                    $.get("graphKnockParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_BETA"){
                    $.get("graphCbetaParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Genemapper"){
                    $.get("graphgenemappParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        console.log(_data)
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                } else if (_self.methodParam=="C_TE"){
                    $.get("freqCteParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        _self.freqParams = _data.data;
                        _self.freqParam = _data.data[0];
                        $.get("graphCteParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                }
            },
            change3: function () {
                load()
                var _self = this;
                if (_self.methodParam=="C_SE"||_self.methodParam=="C_Silencer"){
                    $.get("graphCParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                    $.get("graphMParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Promoter"){

                }else if (_self.methodParam=="M_Promoter"){

                }else if (_self.methodParam=="Curate"){

                }else if (_self.methodParam=="Knock"){

                }else if (_self.methodParam=="C_BETA"){

                }else if (_self.methodParam=="C_Genemapper"){
                    $.get("graphgenemappParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        console.log(_data)
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                } else if (_self.methodParam=="C_TE"){
                    $.get("freqCteParamUrl", {tfs: tfs.toString(),method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        _self.freqParams = _data.data;
                        _self.freqParam = _data.data[0];
                        $.get("graphCteParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                            network(_data)
                            tablegene(_data.userID)
                        }, "json");
                    }, "json");
                }
            },
            change4: function () {
                load()
                var _self = this;
                if (_self.methodParam=="C_SE"||_self.methodParam=="C_Silencer"){
                    $.get("graphCParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                    $.get("graphMParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Promoter"){
                    $.get("graphCproParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="M_Promoter"){

                }else if (_self.methodParam=="Curate"){

                }else if (_self.methodParam=="Knock"){

                }else if (_self.methodParam=="C_BETA"){

                }else if (_self.methodParam=="C_Genemapper"){

                }else if (_self.methodParam=="C_TE"){
                    $.get("graphCteParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }
            },
            change5: function () {
                load()
                var _self = this;
                if (_self.methodParam=="C_SE"||_self.methodParam=="C_Silencer"){

                }else if (_self.methodParam=="M_SE"||_self.methodParam=="M_TE"||_self.methodParam=="M_ATAC"||_self.methodParam=="M_Silencer"){
                    $.get("graphMParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Promoter"){

                }else if (_self.methodParam=="M_Promoter"){
                    $.get("graphMproParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="Curate"){

                }else if (_self.methodParam=="Knock"){
                    $.get("graphKnockParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_BETA"){
                    $.get("graphCbetaParamUrl", {tfs: tfs.toString(),input:input,method:_self.methodParam,genetype:_self.geneParam,cellLine:_self.cellLineParam,freq:_self.freqParam,score:_self.scoreParam}, function (_data) {
                        network(_data)
                        tablegene(_data.userID)
                    }, "json");
                }else if (_self.methodParam=="C_Genemapper"){


                }
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

function see1() {
    $("#celllines").show();
    $("#genes").show();
    $("#freqs").show();
    $("#scores").hide();
}
function see2() {
    $("#celllines").show();
    $("#genes").show();
    $("#freqs").hide();
    $("#scores").show();
}
function see3() {
    $("#celllines").show();
    $("#genes").hide();
    $("#freqs").show();
    $("#scores").hide();
}
function see4() {
    $("#celllines").hide();
    $("#genes").hide();
    $("#freqs").hide();
    $("#scores").show();
}
function see5() {
    $("#celllines").show();
    $("#genes").hide();
    $("#freqs").hide();
    $("#scores").hide();
}
function see6() {
    $("#celllines").show();
    $("#genes").hide();
    $("#freqs").hide();
    $("#scores").show();
}
function see7() {
    $("#celllines").show();
    $("#genes").hide();
    $("#freqs").hide();
    $("#scores").show();
}
function see8() {
    $("#celllines").show();
    $("#genes").show();
    $("#freqs").hide();
    $("#scores").hide();
}
function see9() {
    $("#celllines").show();
    $("#genes").show();
    $("#freqs").show();
    $("#scores").hide();
}