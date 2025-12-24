<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules2/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <h6 class="mb-0 text-uppercase">My Vehicle List</h6>
            <hr/>

            <div class="card">
                <div class="card-body">

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead class="table-dark">
                                <tr>
                                    <th>Vehicle Name</th>
                                    <th>Model</th>
                                    <th>Vehicle Number</th>
                                    <th>Type</th>
                                    <th>Purchase Year</th>
                                    <th>Engine No</th>
                                    <th>Action</th> <!-- ✅ NEW -->
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="v" items="${vehicleList}">
                                    <tr>
                                        <td>${v.vehicleName}</td>
                                        <td>${v.model}</td>
                                        <td>${v.vehicleNumber}</td>
                                        <td>${v.vehicleType}</td>
                                        <td>${v.purchaseYear}</td>
                                        <td>${v.engineNo}</td>

                                        <!-- ✅ APPLY FOR POLICY BUTTON -->
                                        <td>
                                            <a href="${pageContext.request.contextPath}/applypolicy?vehicleId=${v.vehicleId}"
                                               class="btn btn-sm btn-success">
                                                Apply for Policy
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty vehicleList}">
                                    <tr>
                                        <td colspan="7" class="text-center text-muted">
                                            No vehicles found
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
