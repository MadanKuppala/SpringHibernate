<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.submiybuttons{
    margin-left: 83%;
    margin-top: 7%;
}
.labelAdjust{
    margin-top: 6px;
    margin-left: 7px;
}
</style>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Save Student</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Basic Form Elements</div>
				<div class="panel-body">
					<div class="row">
						<form role="form" method="post" action="submitDetails">
							<div class="col-lg-12">

								<div class="form-group col-lg-12">
									<div class="col-lg-1">
										<label class="labelAdjust">Name</label>
									</div>
									<div class="col-lg-5">
										<input type="text" class="form-control" style="width:75%"
											placeholder="Enter name" name="name" autofocus="autofocus">
									</div>

									<div class="col-lg-1">
										<label class="labelAdjust">Branch</label>
									</div>
									<div class="col-lg-5">
										<input type="text" class="form-control" style="width:75%"
											placeholder="Enter branch" name="branch">
									</div>
								</div>
								<div class="submiybuttons">
									<button type="submit" class="btn btn-outline btn-info">save</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>