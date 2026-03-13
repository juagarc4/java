<form:form action="${urlEmployees}/save" modelAttribute="employee" method="post" class="row g-3" novalidate="true">
    <form:hidden path="id" />
    <div class="col-md-6">
        <label for="firstName" class="form-label">First Name</label>
        <form:input path="firstName" type="text" class="form-control" id="firstName" required="true"/>
        <div class="invalid-feedback">
          Please provide a value.
        </div>
    </div>
    <div class="col-md-6">
        <label for="lastName" class="form-label">Last Name</label>
        <form:input path="lastName" type="text" class="form-control" id="lastName" required="true"/>
        <div class="invalid-feedback">
          Please provide a value.
        </div>
    </div>
    <div class="col-md-4">
        <label for="department" class="form-label">Department</label>
        <form:input path="department"  type="text" class="form-control" id="department" required="true"/>
        <div class="invalid-feedback">
          Please provide a value.
        </div>
    </div>
    <div class="col-md-4">
        <label for="position" class="form-label">Position</label>
        <form:input path="position"  type="text" class="form-control" id="position" required="true"/>
        <div class="invalid-feedback">
          Please provide a value.
        </div>
    </div>
    <div class="col-md-4">
        <label for="salary" class="form-label">Salary</label>
        <form:input path="salary"  type="number" class="form-control" id="salary" required="true"/>
        <div class="invalid-feedback">
          Please provide a value.
        </div>
    </div>
    <div class="col-12  text-center">
        <button type="submit" class="btn btn-warning btn-md me-3">Save</button>
        <a href="${urlEmployees}" class="btn btn-danger btn-md">Back</a>
    </div>
</form:form>