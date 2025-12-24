<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Admin</title>

<!-- ✅ Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
  body {
    background-image: url("${pageContext.request.contextPath}/resources/assets/images/carbackground.jpg");
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: 100vh;
    }    
  .login-card {
        margin-top: 70px;
        border-radius: 12px;
        box-shadow: 0 0 12px rgba(0,0,0,0.15);
    }
    .toggle-link {
        color: blue;
        cursor: pointer;
        font-weight: 600;
    }
</style>

</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card login-card">
                <div class="card-header text-center bg-primary text-white">
                    <h4 id="formTitle"> Admin-LOGIN</h4>
                </div>

                <div class="card-body p-4">

                    <!-- ✅ Show Error -->
                    <c:if test="${not empty errormsg}">
                        <div class="alert alert-danger text-center">
                            ${errormsg}
                        </div>
                    </c:if>

                    <!-- ✅ Show Success -->
                    <c:if test="${not empty msg}">
                        <div class="alert alert-success text-center">
                            ${msg}
                        </div>
                    </c:if>

                    <!-- ✅ LOGIN FORM -->
                    <form id="loginForm" action="adminpage" method="post" class="needs-validation" novalidate>

                        <!-- ✅ User Name -->
                        <div class="mb-3">
                            <label class="form-label">User Name</label>
                            <input type="text" name="username" class="form-control" 
                                   placeholder="Enter Username"
                                   maxlength="16"
                                   pattern="[A-Za-z0-9]{1,16}"
                                   title="Only alphanumeric, max 16 characters"
                                   required>
                            <div class="invalid-feedback">Enter valid username</div>
                        </div>

                        <!-- ✅ Password -->
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" 
                                   placeholder="Enter Password"
                                   maxlength="10"
                                   pattern="[A-Za-z0-9]{1,10}"
                                   title="Only alphanumeric, max 10 characters"
                                   required>
                            <div class="invalid-feedback">Enter valid password</div>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Login</button>

                        <p class="text-center mt-3">
                            Don’t have an account?  
                            <span class="toggle-link" onclick="showSignup()">Sign-Up</span>
                        </p>
                    </form>


                    <!-- ✅ SIGN-UP FORM -->
                    <form id="signupForm" action="signup" method="post" class="needs-validation" style="display:none;" novalidate>

                        <!-- ✅ User Name -->
                        <div class="mb-3">
                            <label class="form-label">User Name</label>
                            <input type="text" name="username" class="form-control"
                                   placeholder="Choose Username"
                                   maxlength="16"
                                   pattern="[A-Za-z0-9]{1,16}"
                                   title="Only alphanumeric, max 16 characters"
                                   required>
                            <div class="invalid-feedback">Enter valid username</div>
                        </div>

                        <!-- ✅ Email (Updated with validation) -->
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control"
                                   placeholder="Enter Email"
                                   required
                                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,}"
                                   title="Enter valid email (example@mail.com)">
                            <div class="invalid-feedback">Please enter valid email like example@gmail.com</div>
                        </div>

                        <!-- ✅ Password -->
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control"
                                   placeholder="Choose Password"
                                   maxlength="10"
                                   pattern="[A-Za-z0-9]{1,10}"
                                   title="Only alphanumeric, max 10 characters"
                                   required>
                            <div class="invalid-feedback">Enter valid password</div>
                        </div>

                        <!-- ✅ Role ONLY HERE -->
                        <div class="mb-3">
                            <label class="form-label">Role</label>
                            <select class="form-control" name="role" required>
                                <option value="">Select Role</option>
                                <option value="admin">Admin</option>
                                <option value="user">User</option>
                            </select>
                            <div class="invalid-feedback">Select a role</div>
                        </div>

                        <button type="submit" class="btn btn-success w-100">Sign-Up</button>

                        <p class="text-center mt-3">
                            Already have account?  
                            <span class="toggle-link" onclick="showLogin()">Login</span>
                        </p>
                    </form>
                    <div class="text-center">
					    <span class="text-muted">For User Login </span>
					    <a href="${pageContext.request.contextPath}/getuserlogin"
					       class="fw-bold text-decoration-none">
					        Click Here
					    </a>
					</div>


                </div>
            </div>
        </div>
    </div>
</div>

<!-- ✅ Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
function showSignup(){
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("signupForm").style.display = "block";
    document.getElementById("formTitle").innerText = "SIGN-UP";
}

function showLogin(){
    document.getElementById("signupForm").style.display = "none";
    document.getElementById("loginForm").style.display = "block";
    document.getElementById("formTitle").innerText = "LOGIN";
}
</script>

<!-- ✅ Bootstrap Form Validation -->
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
