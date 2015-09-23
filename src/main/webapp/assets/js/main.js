/**
 * Created by IgorVasconcelos on 16/09/2015.
 */

var validate = {
    errors: [],
    checkAll: function (formId) {
        validate.requiredInputs(formId);
        validate.requiredSelects(formId);
        validate.date(formId);
    },
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
    date: function (formId) {
        if(typeof formId == "undefined"){
            formId="";
        }
        $(formId + ' .date').each(function(){
            dateString= $(this).val();
            // First check for the pattern
            if(!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString)){
                validate.errors.push("O formato da data do campo '"+$(this).parent().children('label').text()+"' deve ser dd/mm/aaaa.");
                return false;
            }

            // Parse the date parts to integers
            var parts = dateString.split("/");
            var day = parseInt(parts[0], 10);
            var month = parseInt(parts[1], 10);
            var year = parseInt(parts[2], 10);

            // Check the ranges of month and year
            if(year < 1000 || year > 3000 || month <= 0 || month > 12){
                validate.errors.push("A data informada do campo '"+$(this).parent().children('label').text()+"' é inválida.");
                return false;
            }

            var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

            // Adjust for leap years
            if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
                monthLength[1] = 29;

            // Check the range of the day
            if(day > 0 && day <= monthLength[month - 1]){
            }
            else{
                validate.errors.push("A data informada do campo '"+$(this).parent().children('label').text()+"' é inválida.");
                return false;
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