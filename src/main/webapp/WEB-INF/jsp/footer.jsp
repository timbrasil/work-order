<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/assets/js/jquery.mask.js"/>"></script>
<script>
    $('.date').mask('00/00/0000',{placeholder: "__/__/____"});
    $('.date').datepicker({
        language: 'pt-BR'
    });
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
</script>

<nav class="navbar navbar-default" style="margin-bottom: 0;">
    <div class="container">
        <a class="navbar-brand" href="http://www.tim.com.br/" target="_blank" style="padding: 2px 0 0 0">
            <img src="<c:url value="/assets/images/LOGO_TIM_cor_fundo_claro.png"/>"  width="100px">
        </a>
        <div style="text-align:center; color:#000000; font-size:10px">Copyright &copy; 2015 TIM BRASIL
            | Design and Programming: <a href="mailto:icamasso@timbrasil.com.br">Igor Camasso</a>
        </div>
    </div>
</nav>

</body>
</html>