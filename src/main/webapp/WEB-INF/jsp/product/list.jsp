<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>

  <div>
    <div class ="container min-container">
      <h2 class="basic-title">List products</h2>
        <div class="well">
          <table class="table table-condensed table-bordered table-striped table-hover">
          		  <thead>
	                  <tr>
	                  	<td>id</td>
		                  	<td>name</td>
		                  	<td>description</td>
		                  	<td>price</td>
						<td>actions</td>
	                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items='${list}' var='object'>         		
	                  <tr>
						<td><a href="<c:url value='/products'/>/${object.id}">${object.id}</a></td>
		                  	<td>${object.name}</td>
		                  	<td>${object.description}</td>
		                  	<td>${object.price}</td>
	                    <td><a href="<c:url value='/products/remove'/>/${object.id}">Remove</a></td>
					  </tr>
                  </c:forEach>
                  </tbody>
          </table>
		  
          <a href="<c:url value='/products/form'/>" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Add New</a>
        </div>
    </div>
  </div>

<%@include file="../footer.jsp" %>
