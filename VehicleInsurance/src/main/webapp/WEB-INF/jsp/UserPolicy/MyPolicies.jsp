<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules2/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">

            <h6 class="mb-0 text-uppercase">My Policies</h6>
            <hr/>

            <div class="card">
                <div class="card-body">

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead class="table-dark">
                                <tr>
                                    <th>Vehicle</th>
                                    <th>Policy Type</th>
                                    <th>Start Date</th>
                                    <th>Premium</th>
                                    <th>Status</th>
                                    <th>Expiry Date</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="p" items="${policyList}">
                                    <tr>

                                        <!-- Vehicle Details -->
                                        <td>
    Vehicle ID : ${p.vehicleId}
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
                                                <c:when test="${p.status == 'ACTIVE'}">
                                                    <span class="badge bg-success">Active</span>
                                                </c:when>
                                                <c:when test="${p.status == 'EXPIRED'}">
                                                    <span class="badge bg-danger">Expired</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-warning">Pending</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- Expiry Date -->
                                        <td>${p.expiryDate}</td>

                                    </tr>
                                </c:forEach>

                                <c:if test="${empty policyList}">
                                    <tr>
                                        <td colspan="6" class="text-center text-muted">
                                            No policies found
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>

                        </table>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="../modules2/footer.jsp"/>
