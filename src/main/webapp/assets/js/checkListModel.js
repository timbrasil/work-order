var checkListModel = {
    addItemsCheckList: function(){
        $("#placeModelItemsCheckList").append($("#DOM_ModelItemCheckList").html());
        $(".removeItemCheckList").unbind("click",checkListModel.removeItemsCheckList).on("click",checkListModel.removeItemsCheckList());
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