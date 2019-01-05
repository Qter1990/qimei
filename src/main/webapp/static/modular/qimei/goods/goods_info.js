/**
 * 初始化商品管理详情对话框
 */
var GoodsInfoDlg = {
    goodsInfoData : {},
    goodName:"",
    imageUrl:"",
    unitId:"",
    specMutiString:"",
  //拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html(),
};

/**
 * 清除数据
 */
GoodsInfoDlg.clearData = function() {
    this.goodsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoodsInfoDlg.set = function(key, val) {
    this.goodsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoodsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GoodsInfoDlg.close = function() {
    parent.layer.close(window.parent.Goods.layerIndex);
}
/**
 * item获取新的id
 */
GoodsInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};
/**
 * 收集数据
 */
GoodsInfoDlg.collectData = function() {
	this.goodName = $("#name").val();
	this.imageUrl = $("#manImage").val();
	this.unitId = $("#unit").find("option:selected").val();

    var mutiString = "";
	$("[name='specItem']").each(function(){
        var specName = $(this).find("[name='itemName']").val();
        var specVlues = $(this).find("[name='itemValue']").val();
        mutiString = mutiString + (specName + ":" + specVlues + ";");
    });
	this.specMutiString = mutiString;
}

/**
 * 添加条目
 */
GoodsInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#specItem").attr("id", this.newId());
    $("#addButton").hide();
}

/*
 * 删除条目
 */
GoodsInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
}

/**
 * 提交添加
 */
GoodsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/add", function(data){
        Feng.success("添加成功!");
        window.parent.Goods.table.refresh();
        GoodsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set('goodName',this.goodName);
    ajax.set('imageUrl',this.imageUrl);
    ajax.set('unitId',this.unitId);
    ajax.set('specMutiString',this.specMutiString);
    ajax.start();
}


/**
 * 提交修改
 */
GoodsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/update", function(data){
        Feng.success("修改成功!");
        window.parent.Goods.table.refresh();
        GoodsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.goodsInfoData);
    ajax.start();
}

$(function() {
	// 初始化头像上传
    var manImageUp = new $WebUpload("manImage");
    manImageUp.setUploadBarId("progressBar");
    manImageUp.init();
});
