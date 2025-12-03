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

                        <h4 class="mb-4 text-center text-success">Update Claim</h4>

                        <!-- Update Claim Form -->
                        <form action="modifyClaim" method="post">

                            <!-- Hidden Claim ID -->
                            <input type="hidden" name="claimId" value="${claim.claimId}">

                            <!-- Select Policy -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Select Policy</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="policyId" required>
                                        <option value="">-- Choose Policy --</option>
                                        <c:forEach var="p" items="${policyList}">
                                            <option value="${p.policyId}" 
                                                <c:if test="${p.policyId == claim.policyId}">selected</c:if>>
                                                ${p.policyType} - ${p.vehicle.vehicleName} (${p.vehicle.vehicleNumber})
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <!-- Accident Date -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Accident Date</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" name="accidentDate" 
                                           value="${claim.accidentDate}" required>
                                </div>
                            </div>

                            <!-- Claim Amount -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Claim Amount</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" name="claimAmount" 
                                           value="${claim.claimAmount}" placeholder="Enter Claim Amount" required>
                                </div>
                            </div>

                            <!-- Details / Description -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Details</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" name="details" rows="4" 
                                              placeholder="Describe the accident" required>${claim.details}</textarea>
                                </div>
                            </div>

                            <!-- Status -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Status</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="status" required>
                                        <option value="Pending" <c:if test="${claim.status == 'Pending'}">selected</c:if>>Pending</option>
                                        <option value="Approved" <c:if test="${claim.status == 'Approved'}">selected</c:if>>Approved</option>
                                        <option value="Rejected" <c:if test="${claim.status == 'Rejected'}">selected</c:if>>Rejected</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label"></label>
                                <div class="col-sm-9">
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-success px-4">Update Claim</button>
                                        <a href="${pageContext.request.contextPath}/claimshow" class="btn btn-secondary px-4">Cancel</a>
                                    </div>
                                </div>
                            </div>

                        </form>
                        <!-- Update Claim Form Ends -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>