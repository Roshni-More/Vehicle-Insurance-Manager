<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <h6 class="mb-0 text-uppercase">Vehicle List</h6>
            <hr/>

            <div class="card">
                <div class="card-body">

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    
                                    <th>Vehicle Name</th>
                                    <th>Model</th>
                                    <th>Vehicle Number</th>
                                    <th>Type</th>
                                    <th>Purchase Year</th>
                                    <th>Engine No</th>
                                    <th>Customer ID</th>
                                    <th>Action</th>
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
                                        <td>${v.userId}</td>

                                        <td>
                                            <a href="editvehicle?vehicleId=${v.vehicleId}" 
                                               class="btn btn-sm btn-primary">Update</a>

                                            <a href="deletevehicle?vehicleId=${v.vehicleId}" 
                                               class="btn btn-sm btn-danger"
                                               onclick="return confirm('Are you sure you want to delete this vehicle?');">
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
