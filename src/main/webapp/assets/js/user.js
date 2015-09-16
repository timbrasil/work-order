/**
 * Created by IgorVasconcelos on 16/09/2015.
 */

var user = {
    save: function (url,name,email,register,password,cpassword,region,area,redirect){
        var errors = "";
        //Valida��es
        if(name=="" || name==null){
            errors+="� necess�rio informar o nome do usu�rio<br>";
        }
        if(email=="" || email==null){
            errors+="� necess�rio informar o email do usu�rio<br>";
        }
        if(register=="" || register==null){
            errors+="� necess�rio informar a matr�cula do usu�rio<br>";
        }
        if(register.length!=8){
            errors+="A matr�cula deve possuir 8 caracteres, como no exemplo: F8012345<br>";
        }
        if(password=="" || password==null){
            errors+="� necess�rio informar a senha do usu�rio<br>";
        }
        if(cpassword=="" || cpassword==null){
            errors+="� necess�rio confirmar a senha do usu�rio<br>";
        }
        if(region=="" || region==null || region==0){
            errors+="� necess�rio informar a regi�o do usu�rio<br>";
        }
        if(area=="" || area==null || area==0){
            errors+="� necess�rio informar a �rea do usu�rio<br>";
        }
        if(password!=cpassword){
            errors+="As senhas digitadas n�o coincidem<br>";
        }

        if(typeof password != 'undefined'){
            if(password.length<8 || password.length>40){
                errors+="A senha deve conter entre 8 e 40 d�gitos<br>";
            }
        }

        if(errors!=""){
            var modal = $("#alertModal").modal({keyboard:false});
            modal.find('.modal-title').text('Existe erros no formul�rio');
            modal.find('.modal-body').html(errors);
            modal.show();
            return false;
        }

        $.ajax({
            url: url,
            type: "POST",
            async: false,
            datatype: "JSON",
            data: {
                'user.name': name,
                'user.email': email,
                'user.register': register,
                'user.password': password,
                'user.cpassword': cpassword,
                'user.region': region,
                'user.area': area
            },
            success: function (response) {
                if(response.status==true){
                    var modal = $("#alertModal").modal({keyboard:false});
                    modal.find('.modal-title').text('Cadastro realizado com sucesso');
                    modal.find('.modal-body').html('O seu cadastro foi realizado com successo!');
                    modal.show();
                    modal.find('.modal-dismiss').on("click",function(){window.location = redirect})
                }
            }
        });
    }
};

