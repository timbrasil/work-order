<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp" %>
'<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>

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
    </form>
  </div>
</div>
<script src="<c:url value="/assets/js/workOrder.js"/>"></script>
<script>
  $("#alterar").on("click",function(){
    workOrder.save(
            '<c:url value="/workOrder/edit/save"/>',
            '<c:url value="/workOrder/${workOrder.id}"/>',
            'POST',
            'workOrderAdd'
    );
  });
</script>

<div id="techonologyModel" style="display: none">
    <div class="col-md-12 technologyParent" style="margin-bottom: 10px">
        <div class="input-group">
            <label style="display: none;">Tecnologia da Work Order</label>
            <select title="technology" class="form-control input-sm tecnologySelect" name="workOrder.technology" required>
                <option value="null" selected>Selecione uma Tecnologia</option>
                <%--@elvariable id="technologys" type="java.util.List<br.com.timbrasil.operations.models.Technology>"--%>
                <%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
                <c:forEach items="${technologys}" var="technology">
                    <option value="${technology}">${technology.nome}</option>
                </c:forEach>
            </select>
            <div class="input-group-btn input-sm" style="padding: 0 0 0 0">
                <button type="button" class="btn btn-danger technologyDelButton" style="height: 31px; width: 31px; padding: 2px 0 0 0"><span style="font-size: 16pt" class="glyphicon glyphicon-remove"></span></button>
            </div>
        </div>
    </div>
</div>
<script>
    technologyCounter = ${workOrder.technologies.size()+1};
    $(".technologyDelButton").unbind("click").on("click",function(){
        $(this).closest('.technologyParent').remove();
    });
    function addTechnology(){
        var technologyDOM = $("#techonologyModel");
        technologyDOM.find('.tecnologySelect').prop('name',"workOrder.technologies["+technologyCounter+"]");
        $("#tecnologyArea").append(technologyDOM.html());
        $(".technologyDelButton").unbind("click").on("click",function(){
            $(this).closest('.technologyParent').remove();
        });
        technologyCounter++;
    }
</script>
<%@include file="../footer.jsp" %>