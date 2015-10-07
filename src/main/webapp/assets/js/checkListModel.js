var totalItemsCheckList = 0;
var checkListModel = {
    addItemsCheckList: function(){
        var html='<div class="col-sm-12 DOM_ModelItemCheckList" style="padding-top: 5px;">' +
            '<form class="formItemCheckList">' +
            '<div class="col-md-1">' +
            '<input type="text" class="form-control" id="NumberItemChekList" spellcheck="true" name="itemsCheckList[' + totalItemsCheckList +'].dirId" required>' +
            '</div>' +
            '<div class="col-md-10">' +
            '<input type="text" class="form-control" id="ItemChekList" spellcheck="true" name="itemsCheckList[' + totalItemsCheckList +'].description" required>' +
            '</div>' +
            '<div class="col-md-1">' +
            '<button class="btn btn-danger removeItemCheckList pull-right" type="button">' +
            '<span class="glyphicon glyphicon-remove"></span>' +
            '</button>' +
            '</div>' +
            '</form>' +
            '</div>';
        $("#placeModelItemsCheckList").append(html);
        $(".removeItemCheckList").unbind("click",checkListModel.removeItemsCheckList).on("click",checkListModel.removeItemsCheckList());
        totalItemsCheckList+=1;
    },
    removeItemsCheckList: function(){
        $(this).parents('.DOM_ModelItemCheckList').remove();
        $(".removeItemCheckList").unbind("click",checkListModel.removeItemsCheckList).on("click",checkListModel.removeItemsCheckList);
    },
    save: function(url,redirect,type,formId){
        validate.checkAll(formId);

        if($("#"+formId+" .DOM_ModelItemCheckList").length <1){
            validate.putError("É necessário ao menos 1 Item para inserir o Modelo de CheckList.");
        }

        if(!validate.isValidate()){
            validate.showModelAlert();
            return false;
        }

        var serialized = $("#"+formId).serialize();
        $("#"+formId+" .formItemCheckList").each(function(){
            serialized+="&"+$(this).serialize();
        });

        $.ajax({
            url: url,
            type: type,
            async: false,
            datatype: "JSON",
            data: serialized,
            success: function (response) {
                if(response.status==true){
                    show.success(
                        'Cadastro realizado com sucesso',
                        'O seu cadastro foi realizado com successo!',
                        redirect
                    );
                }
                else{
                    show.error('Occoreu um erro ao tentar executar a operação',response.error);
                }
            }
        });
    }
};