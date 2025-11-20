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
                                    <th>Policy ID</th>
                                    <th>Vehicle</th>
                                    <th>Policy Type</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Premium</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="p" items="${policyList}">
                                    <tr>
                                        <td>${p.policyId}</td>
                                        <td>
                                            ${p.vehicle.vehicleName} 
                                            (${p.vehicle.model}) 
                                            - ${p.vehicle.vehicleNumber}
                                        </td>
                                        <td>${p.policyType}</td>
                                        <td>${p.startDate}</td>
                                        <td>${p.endDate}</td>
                                        <td>${p.premium}</td>

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
