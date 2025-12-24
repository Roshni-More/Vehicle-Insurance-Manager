<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<jsp:include page="../modules/header.jsp"/>
<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body p-4">
                        <h4 class="mb-4 text-center text-primary">Add Customer</h4>

                        <!-- ✅ Form starts -->
                        <form action="${pageContext.request.contextPath}/insert" method="post">

                            <!-- Customer ID -->
                            <!-- <div class="row mb-3">
                                <label for="customerId" class="col-sm-3 col-form-label">Customer ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="customerId" name="customerId" placeholder="Enter Customer ID" required>
                                </div>
                            </div> -->

                              <!-- Customer Name -->
                            <div class="row mb-3">
                                <label for="customerName" class="col-sm-3 col-form-label">Customer Name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="customerName" name="userName" placeholder="Enter Customer Name" required>
                                </div>
                            </div>

                            <!-- Phone Number -->
                            <div class="row mb-3">
                                <label for="phoneNo" class="col-sm-3 col-form-label">Phone No</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="phoneNo" name="phoneNo" placeholder="Enter Phone Number" pattern="[0-9]{10}" title="Enter 10 digit number" required>
                                </div>
                            </div>

                            <!-- Email Address -->
                            <div class="row mb-3">
                                <label for="emailAddress" class="col-sm-3 col-form-label">Email Address</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="emailAddress" name="emailAddress" placeholder="Enter Email Address" required>
                                </div>
                            </div>

                            <!-- Address -->
                            <div class="row mb-3">
                                <label for="address" class="col-sm-3 col-form-label">Address</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="address" name="address" rows="3" placeholder="Enter Address" required></textarea>
                                </div>
                            </div>

                            <!-- Submit + Reset -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label"></label>
                                <div class="col-sm-9">
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-primary px-4">Submit</button>
                                        <button type="reset" class="btn btn-secondary px-4">Reset</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- ✅ Form ends -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>