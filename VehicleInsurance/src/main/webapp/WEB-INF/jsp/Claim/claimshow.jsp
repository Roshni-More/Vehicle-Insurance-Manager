<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-lg-11 mx-auto">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body p-4">

                        <h4 class="mb-4 text-center text-primary">Claims List</h4>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped align-middle">
                                <thead class="table-primary text-center">
                                    <tr>
                                        <th>Claim ID</th>
                                        <th>Policy ID</th>
                                        <th>Accident Date</th>
                                        <th>Claim Amount</th>
                                        <th>Details</th>
                                        <th>Image</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="c" items="${claimList}">
                                        <tr class="text-center">
                                            <td>${c.claimId}</td>
                                            <td>${c.policyId}</td>
                                            <td>${c.accidentDate}</td>
                                            <td>₹ ${c.claimAmount}</td>
                                            <td>${c.details}</td>

                                            <!-- Image -->
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty c.imageName}">
                                                        <img src="${pageContext.request.contextPath}/uploads/${c.imageName}"
                                                             width="60" height="60"
                                                             class="rounded"
                                                             alt="Claim Image"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-muted">No Image</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <!-- STATUS DROPDOWN (LIKE POLICY) -->
                                            <td>
                                                <form action="${pageContext.request.contextPath}/updateClaimStatus"
                                                      method="post">
                                                    <input type="hidden" name="claimId" value="${c.claimId}" />

                                                    <select name="status"
                                                            class="form-select form-select-sm"
                                                            onchange="this.form.submit()">
                                                        <option value="PENDING"
                                                            ${c.status == 'PENDING' ? 'selected' : ''}>
                                                            Pending
                                                        </option>
                                                        <option value="APPROVED"
                                                            ${c.status == 'APPROVED' ? 'selected' : ''}>
                                                            Approved
                                                        </option>
                                                        <option value="REJECTED"
                                                            ${c.status == 'REJECTED' ? 'selected' : ''}>
                                                            Rejected
                                                        </option>
                                                    </select>
                                                </form>
                                            </td>

                                            <!-- ACTION -->
                                            <td>
                                                <a href="${pageContext.request.contextPath}/editClaim?id=${c.claimId}"
                                                   class="btn btn-warning btn-sm">
                                                    Edit
                                                </a>
                                                <a href="${pageContext.request.contextPath}/deleteClaim?id=${c.claimId}"
                                                   class="btn btn-danger btn-sm"
                                                   onclick="return confirm('Are you sure?')">
                                                    Delete
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <!-- EMPTY LIST -->
                                    <c:if test="${empty claimList}">
                                        <tr>
                                            <td colspan="8" class="text-center text-muted">
                                                No claims found
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>

                        <div class="text-end">
                            <a href="${pageContext.request.contextPath}/insertclaims"
                               class="btn btn-primary mt-3">
                                Add New Claim
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>
