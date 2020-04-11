<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action="register	" method="post">
		<h2 style="color: blue">WELCOME TO EMP REGISTER PAGE</h2>
		<pre>
Emp Name : <input type="text" name="ename" required="required"
				placeholder="Enter name">
Emp Addr : <input type="text" name="addr" required="required"
				placeholder="Enter Address">
Emp DOJ  : <input type="date" name="doj" required="required">
Emp Sal	 : <input type="number" name="sal" required="required"
				placeholder="Enter Salary">

		<input type="submit" value="Register" class="f-halfbutton">

</pre>
	</form>
</body>
</html>