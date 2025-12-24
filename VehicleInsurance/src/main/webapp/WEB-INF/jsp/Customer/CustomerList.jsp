<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>
<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
				<h6 class="mb-0 text-uppercase">Customer List</h6>
				<hr/>
				<div class="card">
					<div class="card-body">
						  <div class="table-responsive">
							<table id="example" class="table table-striped table-bordered" style="width:100%">
								<thead>
                                <tr>
                                    <th>User ID</th>
                                    <th>User Name</th>
                                    <th>Phone No</th>
                                    <th>Email</th>
                                    <th>Address</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cust" items="${customerList}">
                                    <tr>
                                        <td>${cust.userId}</td>
                                        <td>${cust.userName}</td>
                                        <td>${cust.phoneNo}</td>
                                        <td>${cust.emailAddress}</td>
                                        <td>${cust.address}</td>
                                        <td>
                                            <a href="editCustomer?userId=${cust.userId}" 
                                               class="btn btn-sm btn-primary">Update</a>
                                            <a href="deleteCustomer?userId=${cust.userId}" 
                                               class="btn btn-sm btn-danger"
                                               onclick="return confirm('Are you sure you want to delete this customer?');">
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
