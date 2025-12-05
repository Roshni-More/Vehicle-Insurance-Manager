<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
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
            <!-- Vehicle Details -->
            <td>
                ${p.vehicle.vehicleName} 
                (${p.vehicle.model}) 
                - ${p.vehicle.vehicleNumber}
            </td>

            <!-- Policy Type -->
            <td>${p.policyType}</td>

            <!-- Start Date -->
            <td>${p.startDate}</td>

            <!-- Premium -->
            <td>${p.premium}</td>

            <!-- Status -->
            <td>
                <c:choose>
                    <c:when test="${p.status == 'Active'}">
                        <span class="badge bg-success">Active</span>
                    </c:when>
                    <c:when test="${p.status == 'Expired'}">
                        <span class="badge bg-danger">Expired</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge bg-warning">Pending</span>
                    </c:otherwise>
                </c:choose>
            </td>

            <!-- Expiry Date -->
            <td>${p.expiryDate}</td>

            <!-- Renew Count -->
            <td>${p.renewCount}</td>

            <!-- Renewal Date -->
            <td>
                <c:choose>
                    <c:when test="${not empty p.renewalDate}">
                        ${p.renewalDate}
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </td>

            <!-- Action Buttons -->
            <td>
                <a href="editPolicy?policyId=${p.policyId}" 
                   class="btn btn-sm btn-primary">
                    Update
                </a>

                <a href="deletePolicy?policyId=${p.policyId}" 
                   class="btn btn-sm btn-danger"
                   onclick="return confirm('Are you sure you want to delete this policy?');">
                   Delete
                </a>

                <a href="renewPolicy?policyId=${p.policyId}" 
                   class="btn btn-sm btn-success">
                    Renew
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
