<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phonebook</title>
</head>
<body>
	<div class="container">
		<h1 class="well">Phonebook</h1>
		
		<div class="row">
			<form action="/mvc/save-contact" method="POST" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-4">Firstname</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="firstName" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Lastname</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="lastName" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Gender</label>
					<div class="col-sm-6">
					<label><input type="radio" name="gender" value="Male"> Male</label>
					<label><input type="radio" name="gender" value="Female"> Female</label>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Email address</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="email" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Phone number</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="phone" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Address</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="address" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">City</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="city" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">State</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="state" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Country</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" name="country" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4"></label>
					<div class="col-sm-6">
					<button class="btn btn-primary">Add to phonebook</button>
					</div>
				</div>
			</form>
		</div>
		
		
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="p">
				<tr>
					<td>${p.gender=="Male" ? "Mr.": "Ms."} 
						${p.firstName} 
						${p.lastName}</td>
					<td>${p.email}</td>
					<td>${p.phone}</td>
					<td>${p.address}
						<br>${p.city}
						<br>${p.state} - ${p.country}
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
</body>
</html>












