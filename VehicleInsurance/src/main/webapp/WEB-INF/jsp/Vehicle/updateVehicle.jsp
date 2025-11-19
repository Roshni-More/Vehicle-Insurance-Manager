<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body p-4">
                        <h4 class="mb-4 text-center text-primary">Update Vehicle</h4>

                        <!-- Update Vehicle Form -->
                        <form action="${pageContext.request.contextPath}/updatevehicle" method="post">

                            <!-- Hidden Vehicle ID -->
                            <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}" />

                            <!-- Vehicle Name -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Vehicle Name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="vehicleName"
                                           value="${vehicle.vehicleName}"
                                           placeholder="Enter Vehicle Name" required>
                                </div>
                            </div>

                            <!-- Vehicle Model -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Vehicle Model</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="model"
                                           value="${vehicle.model}"
                                           placeholder="Enter Vehicle Model" required>
                                </div>
                            </div>

                            <!-- Vehicle Number -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Vehicle Number</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="vehicleNumber"
                                           value="${vehicle.vehicleNumber}"
                                           placeholder="MH-12-AB-1234" required>
                                </div>
                            </div>

                            <!-- Vehicle Type -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Vehicle Type</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="vehicleType" required>
                                        <option value="">-- Select Type --</option>
                                        <option value="Car"  ${vehicle.vehicleType == 'Car' ? 'selected' : ''}>Car</option>
                                        <option value="Bike" ${vehicle.vehicleType == 'Bike' ? 'selected' : ''}>Bike</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Purchase Year -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Purchase Year</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" name="purchaseYear"
                                           value="${vehicle.purchaseYear}"
                                           placeholder="2020" min="1990" max="2025" required>
                                </div>
                            </div>

                            <!-- Engine Number -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Engine Number</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="engineNo"
                                           value="${vehicle.engineNo}"
                                           placeholder="Enter Engine Number" required>
                                </div>
                            </div>

                            <!-- Select Customer -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Select Customer</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="customerId" required>
                                        <option value="">-- Choose Customer --</option>
                                        
                                        <c:forEach var="c" items="${customerList}">
                                            <option value="${c.customerId}"
                                                ${vehicle.customerId == c.customerId ? 'selected' : ''}>
                                                ${c.customerName} - ${c.phoneNo}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label"></label>
                                <div class="col-sm-9">
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-primary px-4">Update</button>
                                        <a href="${pageContext.request.contextPath}/vehicleshow" 
                                           class="btn btn-secondary px-4">Cancel</a>
                                    </div>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>
