<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
<div class="container bg-white thumbnail box">
    <div class="col-md-12">

        <form id="checkListModel" class="form-horizontal" role="form"
              action="javascript:void(0)" autocomplete="on">
            <div class="form-group text-center bg-primary">
                <div class="col-md-12">
                    <h3>Cadastro de Modelo de CheckList</h3>
                    <p>Construa abaixo o modelo base para os CheckList realizados.</p>
                </div>
            </div>
            <%@include file="form-inputs.jsp" %>
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="submit" id="cadastrar" value="Cadastrar" class="btn btn-success col-xs-12">
                </div>
            </div>

            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>
        </form>
    </div>
</div>

<script src="<c:url value="/assets/js/checkListModel.js"/>"></script>
<script>
    checkListModel.addItemsCheckList(); //Para adicionar uma linha inicialmente.
    $("#AddModelItemsCheckList").on("click",checkListModel.addItemsCheckList);
    $(".removeItemCheckList").on("click",checkListModel.removeItemsCheckList);
    $("#cadastrar").on("click",function(){
        checkListModel.save(
                '<c:url value="/checkListModel/save"/>',
                '<c:url value="/checkListModel/form"/>',
                'POST',
                'checkListModel'
        );
    });
</script>

<%@include file="../footer.jsp" %>
