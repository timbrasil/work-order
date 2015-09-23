/**
 * Created by IgorVasconcelos on 16/09/2015.
 */

var validate = {
    errors: [],
    cleanErros: function(){
        validate.errors = [];
    },
    requiredInputs: function(formId){
        if(typeof formId == "undefined"){
            formId="";
        }
        $(formId + ' input:required').each(function(){
            if($(this).val()=="" || $(this).val()==null){
                validate.errors.push("O campo '"+$(this).parent().children('label').text()+"' é obrigatório.");
            }
        });
    },
    requiredSelects: function(formId){
        if(typeof formId == "undefined"){
            formId="";
        }
        $(formId + ' select:required').each(function(){
            if($(this).val()=="" || $(this).val()==null || $(this).val()=="null"){
                validate.errors.push("O campo '"+$(this).parent().children('label').text()+"' é obrigatório.");
            }
        });
    },
    isValidate: function(){
        if(validate.errors.length==0){
            return true;
        }
        else{
            return false;
        }
    },
    showModelAlert: function(){
        var modal = $("#alertModal").modal({keyboard:false});
        modal.find('.modal-title').text('Existem erros de validação');
        var htmlErros = "";
        for(i=0;i<validate.errors.length;i++){
            htmlErros+=validate.errors[i]+"<br>";
        }
        validate.errors = [];
        modal.find('.modal-body').html(htmlErros);
        modal.show();
    },
    putError: function(error){
        validate.errors.push(error);
    }
};

function showError(msg){
    var modal = $("#alertModal").modal();
    modal.find('.modal-title').text('Occoreu um erro ao tentar executar a operação');
    modal.find('.modal-body').html(msg);
    modal.show();
}