<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<jsp:include page="../modules/header.jsp"/>
<!--start page wrapper -->
		<div class="page-wrapper">
			<div class="page-content">
			    <div class="row">
                    <div class="col-lg-8 mx-auto">
						<div class="card">
							<div class="card-body p-4">
								<h5 class="mb-4">Horizontal Form</h5>
									<div class="row mb-3">
										<label for="input35" class="col-sm-3 col-form-label">Enter Your Name</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="input35" placeholder="Enter Your Name">
										</div>
									</div>
									<div class="row mb-3">
										<label for="input36" class="col-sm-3 col-form-label">Phone No</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="input36" placeholder="Phone No">
										</div>
									</div>
									<div class="row mb-3">
										<label for="input37" class="col-sm-3 col-form-label">Email Address</label>
										<div class="col-sm-9">
											<input type="email" class="form-control" id="input37" placeholder="Email Address">
										</div>
									</div>
									<div class="row mb-3">
										<label for="input38" class="col-sm-3 col-form-label">Choose Password</label>
										<div class="col-sm-9">
											<input type="email" class="form-control" id="input38" placeholder="Choose Password">
										</div>
									</div>
									<div class="row mb-3">
										<label for="input39" class="col-sm-3 col-form-label">Select Country</label>
										<div class="col-sm-9">
											<select class="form-select" id="input39">
												<option selected>Open this select menu</option>
												<option value="1">One</option>
												<option value="2">Two</option>
												<option value="3">Three</option>
											  </select>
										</div>
									</div>
									<div class="row mb-3">
										<label for="input40" class="col-sm-3 col-form-label">Address</label>
										<div class="col-sm-9">
											<textarea class="form-control" id="input40" rows="3" placeholder="Address"></textarea>
										</div>
									</div>
									<div class="row mb-3">
										<label class="col-sm-3 col-form-label"></label>
										<div class="col-sm-9">
											<div class="form-check">
												<input class="form-check-input" type="checkbox" id="input41">
												<label class="form-check-label" for="input41">Check me out</label>
											</div>
										</div>
									</div>
									<div class="row">
										<label class="col-sm-3 col-form-label"></label>
										<div class="col-sm-9">
											<div class="d-md-flex d-grid align-items-center gap-3">
												<button type="button" class="btn btn-primary px-4">Submit</button>
												<button type="button" class="btn btn-light px-4">Reset</button>
											</div>
										</div>
									</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>	
<jsp:include page="../modules/footer.jsp"/>