/**
 * Created by IgorVasconcelos on 22/09/2015.
 */

var workOrder = {
    save: function (url,redirect,type,formId){
        validate.checkAll();

        //Validações
        if(ticketId<0) {
            validate.putError("Número do TicketId Inválido.");
        }

        if(!validate.isValidate()){
            validate.showModelAlert();
            return false;
        }

        var serialized = $("#"+formId).serialize();

        var data = {
            'city.id': city,
            'address.street': address,
        };

        $.ajax({
            url: url,
            type: type,
            async: false,
            datatype: "JSON",
            data: serialized,
            success: function (response) {
                if(response.status==true){
                    var modal = $("#alertModal").modal({keyboard:false});
                    modal.find('.modal-title').text('Cadastro realizado com sucesso');
                    modal.find('.modal-body').html('O seu cadastro foi realizado com successo!');
                    modal.show();
                    modal.find('.modal-dismiss').on("click",function(){window.location = redirect})
                }
                else{
                    showError(response.error);
                }
            }
        });
    }
};