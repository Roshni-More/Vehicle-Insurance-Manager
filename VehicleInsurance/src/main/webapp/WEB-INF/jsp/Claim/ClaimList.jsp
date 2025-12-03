<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <h6 class="mb-0 text-uppercase">Claim List</h6>
            <hr/>

            <div class="card">
                <div class="card-body">

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered" style="width:100%">
                            
                            <thead>
                                <tr>
                                    <th>Claim ID</th>
                                    <th>Policy ID</th>
                                    <th>Accident Date</th>
                                    <th>Claim Amount</th>
                                    <th>Details</th>
                                    <th>Photo</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="c" items="${claimList}">
                                    <tr>
                                        <td>${c.claimId}</td>
                                        <td>${c.policyId}</td>
                                        <td>${c.accidentDate}</td>
                                        <td>${c.claimAmount}</td>
                                        <td>${c.details}</td>
                                         <td>
    <img src="resources/Upload/${c.imageName}" width="120" height="120">

</td>
                                        <td>
                                            <span class="badge 
                                                ${c.status == 'Approved' ? 'bg-success' : 
                                                  c.status == 'Rejected' ? 'bg-danger' : 
                                                  'bg-warning'}">
                                                ${c.status}
                                            </span>
                                        </td>
                                    
                                        <td>
                                            <a href="editclaim?claimId=${c.claimId}" 
                                               class="btn btn-sm btn-primary">
                                                Update
                                            </a>

                                            <a href="deleteclaim?claimId=${c.claimId}" 
                                               class="btn btn-sm btn-danger"
                                               onclick="return confirm('Are you sure you want to delete this claim?');">
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
