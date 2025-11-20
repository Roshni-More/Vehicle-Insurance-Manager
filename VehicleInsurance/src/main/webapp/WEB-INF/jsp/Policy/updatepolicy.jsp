<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../modules/header.jsp"/>

<div class="page-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <div class="card shadow-lg border-0 rounded-4">
                    <div class="card-body p-4">

                        <h4 class="mb-4 text-center text-primary">Update Insurance Policy</h4>

                        <!-- Update Policy Form -->
                        <form action="updatePolicydata" method="post">

                            <!-- Hidden policyId -->
                            <input type="hidden" name="policyId" value="${policy.policyId}" />

                            <!-- Select Vehicle -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Select Vehicle</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="vehicleId" required>
                                        <option value="">-- Choose Vehicle --</option>

                                        <c:forEach var="v" items="${vehicleList}">
                                            <option value="${v.vehicleId}"
                                                ${v.vehicleId == policy.vehicleId ? 'selected' : ''}>
                                                ${v.vehicleName} - ${v.model} - ${v.vehicleNumber}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>

                            <!-- Policy Type -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Policy Type</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="policyType" name="policyType" required>
                                        <option value="">-- Select Type --</option>

                                        <option value="Basic"
                                            ${policy.policyType == 'Basic' ? 'selected' : ''}>
                                            Basic
                                        </option>

                                        <option value="Comprehensive"
                                            ${policy.policyType == 'Comprehensive' ? 'selected' : ''}>
                                            Comprehensive
                                        </option>

                                        <option value="Third-Party"
                                            ${policy.policyType == 'Third-Party' ? 'selected' : ''}>
                                            Third Party
                                        </option>

                                    </select>
                                </div>
                            </div>

                            <!-- Start Date -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Start Date</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" name="startDate"
                                           value="${policy.startDate}" required>
                                </div>
                            </div>

                            <!-- End Date -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">End Date</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" name="endDate"
                                           value="${policy.endDate}" required>
                                </div>
                            </div>

                            <!-- Premium (Auto Calculated) -->
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label">Premium</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" id="premium"
                                           name="premium" value="${policy.premium}"
                                           placeholder="Auto Calculated" readonly required>
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label"></label>
                                <div class="col-sm-9">
                                    <div class="d-flex justify-content-between">
                                        <button type="submit" class="btn btn-primary px-4">Update</button>
                                        <a href="policylist" class="btn btn-secondary px-4">Cancel</a>
                                    </div>
                                </div>
                            </div>

                        </form>

                        <!-- Auto Premium Update Script -->
                        <script>
                            document.getElementById("policyType").addEventListener("change", function() {

                                const type = this.value;
                                let premium = 0;

                                if (type === "Basic") premium = 2000;
                                else if (type === "Comprehensive") premium = 4000;
                                else if (type === "Third-Party") premium = 3000;

                                document.getElementById("premium").value = premium;
                            });
                        </script>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../modules/footer.jsp"/>
