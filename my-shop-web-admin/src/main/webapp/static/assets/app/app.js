var App=function () {
    //iCheck
    var _masterCheckbox;
    var _checkBox;

    //用户存放id的数组
    var _idArray;

    //默认的Dropzone参数
    var defaultDropzoneOpts={
        url: "",
        method:"post",
        paramName:"dropFile",
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };
    //私有方法
    var handlerInitCheckbox=function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //获取控制端checkbox
        _masterCheckbox= $('input[type="checkbox"].minimal.icheck_master');
        //获取所有checkbox
        _checkBox= $('input[type="checkbox"].minimal');
    };

    //checkbox全选
    var handlerCheckboxAll=function () {
        _masterCheckbox.on("ifClicked",function (e) {
            //返回true未选中
            if (e.target.checked){
                _checkBox.iCheck("uncheck");
            }else{
                _checkBox.iCheck("check");
            }
        });
    };

    //批量删除
    var handlerDeleteMulti=function (url) {
        _idArray=new Array();

        //将选中元素的ID放入数组中
        _checkBox.each(function () {
            var _id=$(this).attr("id");
            if(_id!=null&&_id!="undefine"&&$(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        //判断用户是否选择数据项
        if (_idArray.length===0){
            $("#model-msg").html("你尚未选择任何选项，请至少选择一项");
        }else{
            $("#model-msg").html("你确定删除数据项吗？");
        }
        //点击删除弹出模态框
        $("#modal-default").modal("show");
        //用户选择数据项调用删除方法
        $("#btnModalOK").bind("click",function () {
            del()
        });

        function del() {
            $("#modal-default").modal("hide");
            if (_idArray.length===0){

            }else{
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids":_idArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            //请求成功后都要提示
                            $("#btnModalOK").unbind("click");
                            if (data.status===200){
                                //成功
                                $("#btnModalOK").bind("click",function () {
                                    window.location.reload();
                                });
                            }else{
                                //失败
                                $("#btnModalOK").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            $("#model-msg").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);
            }
        }

    };

    //初始化datatables
    var handlerInitDataTables=function (url,columns) {
        var _dataTable= $('#dataTable').DataTable({
            "paging":true,
            "info":true,
            "lengthChange":false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    //查看详情
    var handlerShowDetail=function (url) {
        //将jsp装载进模态框
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function (data) {
                $("#model-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    var handlerInitZtree=function (url,autoParam,calback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);
        $("#btnModalOK").bind("click",function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }else{
                calback(nodes);
            }
        });
    };

    //初始化Dropzone
    var handlerInitDropzone =function (opts) {
        //关闭Dropzone的自动发现
        Dropzone.autoDiscover=false;
        $.extend(defaultDropzoneOpts,opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    }

    return {
        init:function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        deleteMulti:function (url) {
             handlerDeleteMulti(url);
        },
        initDataTables:function (url,columns) {
             return handlerInitDataTables(url,columns);
        },
        initZtree:function(url,autoParam,calback){
            handlerInitZtree(url,autoParam,calback);
        },
        initDropzone:function(opts){
            handlerInitDropzone(opts);
        },
        showDetail:function (url) {
            handlerShowDetail(url);
        }
    }
}();
$(document).ready(function () {
   App.init();
});