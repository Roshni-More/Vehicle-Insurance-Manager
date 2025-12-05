<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row mb-3">
            <div class="col">
                <h4 class="mb-0 text-primary">Claim List</h4>
                <hr/>
            </div>
        </div>

        <div class="card shadow-lg rounded-4">
            <div class="card-body">

                <div class="table-responsive">
                    <table id="claimTable" class="table table-striped table-bordered">
                        <thead class="table-light">
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
                    <c:choose>
                        <c:when test="${not empty c.imageName}">
                            <img src="${pageContext.request.contextPath}${c.imageName}"
                                 width="120" height="120">
                        </c:when>
                        <c:otherwise>
                            <span class="text-muted">No Image</span>
                        </c:otherwise>
                    </c:choose>
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
                                        <a href="editclaim?claimId=${c.claimId}" class="btn btn-sm btn-primary mb-1">Update</a>
                                        <a href="deleteclaim?claimId=${c.claimId}" 
                                           class="btn btn-sm btn-danger mb-1"
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

<jsp:include page="../modules/footer.jsp"/>

<script>
    // Initialize DataTable if you want sorting/search/pagination
    $(document).ready(function() {
        $('#claimTable').DataTable();
    });
</script>