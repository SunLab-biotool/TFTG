var method = {};
method["SE"] = "SE";
method["TE"] = "TE";
method["ATAC"] = "ATAC";
method["Promoter"] = "Promoter";
method["Silencer"] = "Silencer";
method["BETA"] = "BETA";
method["Genemapper"] = "Genemapper";
method["Curate"] = "Curate";
method["Knock"] = "Knock";
method["M_SE"] = "M_SE";
method["M_TE"] = "M_TE";
method["M_ATAC"] = "M_ATAC";
method["M_Silencer"] = "M_Silencer";
method["M_Promoter"] = "M_Promoter";

function celllineMults() {
    console.log(method)
    var data;
    $.ajax({
        url: "celllineMult",
        data: method,
        async: false,
        success: function (_data) {
            data = _data.data;
        },
        dataType: "json"
    });
    $('#celllineMults').multiselect({
        enableFiltering: true,
        includeSelectAllOption: false,
        buttonWidth: '300px',
        maxHeight: 250,
        enableCollapsibleOptGroups: true,
        dropUp: false
    });
    $('#celllineMults').multiselect('dataprovider', data);
}

$("#adjust").click(function () {
    if (this.checked) {
        $("#adjust").val("1");
    } else {
        $("#adjust").val("0");
    }
});
$("#C_SE").click(function () {
    if (this.checked) {
        method["SE"] = "SE";
    } else {
        delete method["SE"];
    }
    celllineMults()
});
$("#C_TE").click(function () {
    if (this.checked) {
        method["TE"] = "TE";
    } else {
        delete method["TE"];
    }
    celllineMults()
});
$("#C_ATAC").click(function () {
    if (this.checked) {
        method["ATAC"] = "ATAC";
    } else {
        delete method["ATAC"];
    }
    celllineMults()
});
$("#C_Promoter").click(function () {
    if (this.checked) {
        method["Promoter"] = "Promoter";
    } else {
        delete method["Promoter"];
    }
    celllineMults()
});
$("#C_Silencer").click(function () {
    if (this.checked) {
        method["Silencer"] = "Silencer";
    } else {
        delete method["Silencer"];
    }
    celllineMults()
});
$("#C_BETA").click(function () {
    if (this.checked) {
        method["BETA"] = "BETA";
    } else {
        delete method["BETA"];
    }
    celllineMults()
});
$("#C_Genemapper").click(function () {
    if (this.checked) {
        method["Genemapper"] = "Genemapper";
    } else {
        delete method["Genemapper"];
    }
    celllineMults()
});
$("#Curate").click(function () {
    if (this.checked) {
        method["Curate"] = "Curate";
    } else {
        delete method["Curate"];
    }
    celllineMults()
});
$("#Knock").click(function () {
    if (this.checked) {
        method["Knock"] = "Knock";
    } else {
        delete method["Knock"];
    }
    celllineMults()
});
$("#M_SE").click(function () {
    if (this.checked) {
        method["M_SE"] = "M_SE";
    } else {
        delete method["M_SE"];
    }
    celllineMults()
});
$("#M_TE").click(function () {
    if (this.checked) {
        method["M_TE"] = "M_TE";
    } else {
        delete method["M_TE"];
    }
    celllineMults()
});
$("#M_ATAC").click(function () {
    if (this.checked) {
        method["M_ATAC"] = "M_ATAC";
    } else {
        delete method["M_ATAC"];
    }
    celllineMults()
});
$("#M_Silencer").click(function () {
    if (this.checked) {
        method["M_Silencer"] = "M_Silencer";
    } else {
        delete method["M_Silencer"];
    }
    celllineMults()
});
$("#M_Promoter").click(function () {
    if (this.checked) {
        method["M_Promoter"] = "M_Promoter";
    } else {
        delete method["M_Promoter"];
    }
    celllineMults()
});

function f() {
    $.each(method, function (_key) {
        console.log("删除后的结果：" + _key + "==" + method[_key] + "\r\n");
    });
}
