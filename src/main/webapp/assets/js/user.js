/**
 * Created by IgorVasconcelos on 16/09/2015.
 */

var user = {
    save: function (url,name,email,register,password,cpassword,region,area,redirect){
        var errors = "";
        //Validações
        if(name=="" || name==null){
            errors+="É necessário informar o nome do usuário<br>";
        }
        if(email=="" || email==null){
            errors+="É necessário informar o email do usuário<br>";
        }
        if(register=="" || register==null){
            errors+="É necessário informar a matrícula do usuário<br>";
        }
        if(register.length!=8){
            errors+="A matrícula deve possuir 8 caracteres, como no exemplo: F8012345<br>";
        }
        if(password=="" || password==null){
            errors+="É necessário informar a senha do usuário<br>";
        }
        if(cpassword=="" || cpassword==null){
            errors+="É necessário confirmar a senha do usuário<br>";
        }
        if(region=="" || region==null || region==0){
            errors+="É necessário informar a região do usuário<br>";
        }
        if(area=="" || area==null || area==0){
            errors+="É necessário informar a área do usuário<br>";
        }
        if(password!=cpassword){
            errors+="As senhas digitadas não coincidem<br>";
        }

        if(typeof password != 'undefined'){
            if(password.length<8 || password.length>40){
                errors+="A senha deve conter entre 8 e 40 dígitos<br>";
            }
        }

        if(errors!=""){
            var modal = $("#alertModal").modal({keyboard:false});
            modal.find('.modal-title').text('Existe erros no formulário');
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

