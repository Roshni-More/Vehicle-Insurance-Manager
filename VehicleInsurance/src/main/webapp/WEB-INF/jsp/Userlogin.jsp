<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background-color: #f5f5f5;
}
.login-card {
    margin-top: 120px;
    border-radius: 12px;
    box-shadow: 0 0 12px rgba(0,0,0,0.15);
}
</style>
</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="card login-card">
                <div class="card-header text-center bg-primary text-white">
                    <h4>LOGIN</h4>
                </div>

                <div class="card-body p-4">

                    <!-- Error Message -->
                    <c:if test="${not empty errormsg}">
                        <div class="alert alert-danger text-center">
                            ${errormsg}
                        </div>
                    </c:if>

                    <!-- Success Message -->
                    <c:if test="${not empty msg}">
                        <div class="alert alert-success text-center">
                            ${msg}
                        </div>
                    </c:if>

                    <form action="loginpage" method="post" class="needs-validation" novalidate>

                        <!-- Name -->
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" name="username" class="form-control"
                                   placeholder="Enter Name"
                                   required>
                            <div class="invalid-feedback">Name required</div>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control"
                                   placeholder="Enter Email"
                                   required>
                            <div class="invalid-feedback">Valid email required</div>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control"
                                   placeholder="Enter Password"
                                   required>
                            <div class="invalid-feedback">Password required</div>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">
                            Login
                        </button>

                        <p class="text-center mt-3 text-muted" style="font-size:14px;">
                            First time login? Password will be set automatically.
                        </p>

                    </form>
						
						<hr>
						
						<div class="text-center">
						    <span class="text-muted">For Admin Login </span>
						    <a href="${pageContext.request.contextPath}/getadminlogin"
						       class="fw-bold text-decoration-none">
						        Click Here
						    </a>
						</div>


                </div>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
(() => {
  'use strict';
  const forms = document.querySelectorAll('.needs-validation');
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }
      form.classList.add('was-validated');
    }, false);
  });
})();
</script>

</body>
</html>
