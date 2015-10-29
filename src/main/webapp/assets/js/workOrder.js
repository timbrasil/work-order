/**
 * Created by IgorVasconcelos on 22/09/2015.
 */

var workOrder = {
    save: function (url,redirect,type,formId){
        validate.checkAll(formId);

        //Validações
        if(ticketId<0) {
            validate.putError("Número do TicketId Inválido.");
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
                        'Operaçao realizada com sucesso',
                        'O seu cadastro foi realizado com successo!',
                        redirect
                    );
                }
                else{
                    show.error('Occoreu um erro ao tentar executar a operação',response.error);
                }
            }
        });
    },
    saveCheckList: function (url,redirect,type,formId){
        validate.checkAll(formId);

        if(!validate.isValidate()){
            validate.showModelAlert();
            return false;
        }

        var serialized = $("#"+formId).serialize();

        serialized += "&checkList.sampling="+$("#radioSamplingTrue").bootstrapSwitch('state');
        serialized += "&logStatus.status="+$("#statusWorkOrder").val();

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