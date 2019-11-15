$(".mainList").tree({
    onClick: function (node) {
        var tab = $("#indexTab").tabs('getTab',node.text);
        if (tab) {//选项卡存在，选择已存在的选项卡
            $("#indexTab").tabs("select", node.text);
        } else {//选项卡不存在则添加
            $('#indexTab').tabs('add',{
                title:node.text,
                selected: true,
                closable:true,
                href:node.attributes.url
            });
        }
    }
})

/*
function newUser(){
    $('#dlg').dialog('open').dialog('setTitle','New User');
    $('#fm').form('clear');
    url = '/save_user';
}*/

//格式化时间
function formatTime(v, r, i) {
    var d = new Date(v);
    var year = d.getFullYear();
    var mouth = d.getMonth() + 1;
    var day = d.getDate();
    return year + "年" + mouth + "月" + day + "日";
}