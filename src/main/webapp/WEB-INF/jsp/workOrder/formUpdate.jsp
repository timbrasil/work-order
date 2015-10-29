<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
  <div class="col-md-12">

    <form id="workOrderAdd" class="form-horizontal" role="form"
          action="javascript:void(0)" autocomplete="on">
      <div class="form-group text-center bg-primary">
        <div class="col-md-12">
          <h3>Alteração de Work Order</h3>
        </div>
      </div>
      <%@include file="form-inputs.jsp" %>
      <div class="form-group">
        <div class="col-xs-12">
          <input type="submit" id="alterar" value="Alterar" class="btn btn-success col-xs-12">
        </div>
      </div>

      <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
      </c:forEach>
    </form>
  </div>
</div>
<script src="<c:url value="/assets/js/workOrder.js"/>"></script>
<script>
  $("#alterar").on("click",function(){
    workOrder.save(
            '<c:url value="/workOrder/edit/save"/>',
            '<c:url value="/workOrder"/>',
            'POST',
            'workOrderAdd'
    );
  });
</script>
<%@include file="../footer.jsp" %>