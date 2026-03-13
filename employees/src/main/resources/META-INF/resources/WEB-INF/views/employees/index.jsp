<%@include file="../common/top.jsp"%>
<%@include file="../common/header.jsp"%>

<div class="container">
    <div class="text-center" style="margin:30px">
        <h3>Employees List</h3>
    </div>
    <div class="container">
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle">
              <thead class="table-dark">
                <tr>
                  <th scope="col">Id</th>
                  <th scope="col">First Name</th>
                  <th scope="col">Last Name</th>
                  <th scope="col">Department</th>
                  <th scope="col">Position</th>
                  <th scope="col">Salary</th>
                  <th scope="col">Actions</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${employees}" var="emp">
                    <tr>
                        <th scope="row">${emp.id}</th>
                        <td>${emp.firstName}</td>
                        <td>${emp.lastName}</td>
                        <td>${emp.department}</td>
                        <td>${emp.position}</td>
                        <td>
                            <fmt:setLocale value="de_CH" scope="session"/>
                            <fmt:formatNumber type="currency" value="${emp.salary}"/>
                        </td>
                        <td>
                            <div class="d-flex">
                                <c:set var="urlEditEmployee">
                                    <c:url value="${urlEmployees}/edit">
                                        <c:param name="employee" value="${emp.id}"/>
                                    </c:url>
                                </c:set>
                                <c:set var="urlDeleteEmployee">
                                    <c:url value="${urlEmployees}/delete">
                                        <c:param name="employee" value="${emp.id}"/>
                                    </c:url>
                                </c:set>
                                <a class="btn btn-primary me-2" href="${urlEditEmployee}">Edit</a>
                                <form action="${urlDeleteEmployee}" method="post" style="display:inline;">
                                    <input type="hidden" name="employee" value="${emp.id}" />
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?');">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp"%>
<%@include file="../common/bottom.jsp"%>
