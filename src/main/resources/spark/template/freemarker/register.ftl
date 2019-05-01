<#assign content>

<div class="container content-align">
	<div class = "page-header">
		<h1>Register</h1>
	</div>

	<form id="register">
		<div class = "form-group">
			<label for="usr"> Username: </label>
			<input type="text" class="form-control" name="username">
		</div>
		<div class = "form-group">
			<label for="pwd"> Password: </label>
			<input type="password" class="form-control" name="password">
		</div>
		<button type="submit" class = "btn btn-dark">Sign Up</button>
	</form>
</div>
</#assign>
<#include "no-nav.ftl">
