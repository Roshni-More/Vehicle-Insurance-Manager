<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules2/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">

            <h6 class="mb-0 text-uppercase">My Claims</h6>
            <hr/>

            <div class="card">
                <div class="card-body">

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead class="table-dark">
                                <tr>
                                    <th>Vehicle</th>
                                    <th>Policy ID</th>
                                    <th>Accident Date</th>
                                    <th>Claim Amount</th>
                                    <th>Status</th>
                                    <th>Apply Date</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="c" items="${claimList}">
                                    <tr>

                                        <!-- Vehicle Details -->
                                        <td>
                                            ${c.vehicleName}
                                        </td>

                                        <!-- Policy ID -->
                                        <td>${c.policyId}</td>

                                        <!-- Accident Date -->
                                        <td>${c.accidentDate}</td>

                                        <!-- Claim Amount -->
                                        <td>${c.claimAmount}</td>

                                        <!-- Status -->
                                        <td>
                                            <c:choose>
                                                <c:when test="${c.status == 'APPROVED'}">
                                                    <span class="badge bg-success">Approved</span>
                                                </c:when>
                                                <c:when test="${c.status == 'REJECTED'}">
                                                    <span class="badge bg-danger">Rejected</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-warning">Pending</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- Apply Date -->
                                        <td>${c.applyDate}</td>

                                    </tr>
                                </c:forEach>

                                <c:if test="${empty claimList}">
                                    <tr>
                                        <td colspan="6" class="text-center text-muted">
                                            No claims found
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
