<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body p-4">
                        <h4 class="mb-4 text-center text-success">Update Customer</h4>

                        <!-- ✅ Update Form Starts -->
                        <form action="${pageContext.request.contextPath}/updateCustomer" method="post">

                            <!-- Hidden Customer ID -->
                            <input type="hidden" name="userId" value="${customer.userId}" />

                            <!-- Customer Name -->
                            <div class="row mb-3">
                                <label for="customerName" class="col-sm-3 col-form-label">Customer Name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userName" 
                                           name="userName" value="${customer.userName}" 
                                           placeholder="Enter Customer Name" required>
                                </div>
                            </div>

                            <!-- Phone Number -->
                            <div class="row mb-3">
                                <label for="phoneNo" class="col-sm-3 col-form-label">Phone No</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="phoneNo" 
                                           name="phoneNo" value="${customer.phoneNo}" 
                                           placeholder="Enter Phone Number" 
                                           pattern="[0-9]{10}" title="Enter 10 digit number" required>
                                </div>
                            </div>

                            <!-- Email Address -->
                            <div class="row mb-3">
                                <label for="emailAddress" class="col-sm-3 col-form-label">Email Address</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="emailAddress" 
                                           name="emailAddress" value="${customer.emailAddress}" 
                                           placeholder="Enter Email Address" required>
                                </div>
                            </div>

                            <!-- Address -->
                            <div class="row mb-3">
                                <label for="address" class="col-sm-3 col-form-label">Address</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="address" name="address" rows="3" 
                                              placeholder="Enter Address" required>${customer.address}</textarea>
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label"></label>
                                <div class="col-sm-9">
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-success px-4">Update</button>
                                        <a href="${pageContext.request.contextPath}/customershow" class="btn btn-secondary px-4">Cancel</a>
                                    </div>
                                </div>
                            </div>

                        </form>
                        <!-- ✅ Update Form Ends -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>