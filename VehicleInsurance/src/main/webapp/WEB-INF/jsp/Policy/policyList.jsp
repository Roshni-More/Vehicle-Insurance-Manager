<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <h6 class="mb-0 text-uppercase">Policy List</h6>
            <hr/>

            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">

                        <table class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Vehicle</th>
                                    <th>Policy Type</th>
                                    <th>Start Date</th>
                                    <th>Premium</th>
                                    <th>Status</th>
                                    <th>Expiry Date</th>
                                    <th>Renew Count</th>
                                    <th>Renewal Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="p" items="${list}">
                                <tr>

                                    <td>
                                        ${p.vehicle.vehicleName}
                                        (${p.vehicle.model})
                                        - ${p.vehicle.vehicleNumber}
                                    </td>

                                    <td>${p.policyType}</td>

                                    <td>${p.startDate}</td>

                                    <td>${p.premium}</td>

                                    <!-- STATUS -->
                                    <td>
                                        <c:choose>

                                            <c:when test="${empty p.status 
                                                            or p.status == 'PENDING' 
                                                            or p.status == 'Pending'}">
                                                <form action="updatePolicyStatus" method="post">
                                                    <input type="hidden" name="policyId"
                                                           value="${p.policyId}" />
                                                    <select name="status"
                                                            class="form-select form-select-sm"
                                                            onchange="this.form.submit()">
                                                        <option value="">-- Select --</option>
                                                        <option value="ACTIVE">Approve</option>
                                                        <option value="REJECTED">Reject</option>
                                                    </select>
                                                </form>
                                            </c:when>

                                            <c:when test="${p.status == 'ACTIVE' or p.status == 'Active'}">
                                                <span class="badge bg-success">Active</span>
                                            </c:when>

                                            <c:when test="${p.status == 'REJECTED' or p.status == 'Rejected'}">
                                                <span class="badge bg-danger">Rejected</span>
                                            </c:when>

                                            <c:when test="${p.status == 'EXPIRED' or p.status == 'Expired'}">
                                                <span class="badge bg-dark">Expired</span>
                                            </c:when>

                                        </c:choose>
                                    </td>

                                    <td>${p.expiryDate}</td>

                                    <td>${p.renewCount}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty p.renewalDate}">
                                                ${p.renewalDate}
                                            </c:when>
                                            <c:otherwise>N/A</c:otherwise>
                                        </c:choose>
                                    </td>

                                    <!-- ACTION -->
                                    <td>
                                        <c:if test="${p.status == 'ACTIVE' or p.status == 'Active'}">
                                            <a href="editPolicy?policyId=${p.policyId}"
                                               class="btn btn-sm btn-primary">
                                                Update
                                            </a>

                                            <a href="renewPolicy?policyId=${p.policyId}"
                                               class="btn btn-sm btn-success">
                                                Renew
                                            </a>
                                        </c:if>

                                        <a href="deletePolicy?policyId=${p.policyId}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('Are you sure?')">
                                            Delete
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>
