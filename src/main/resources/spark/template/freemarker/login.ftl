<#assign content>
<div class="container">
  <form id="login">
  	Username:<br>
  	<input type="text" name="username"><br>
  	Password:<br>
  	<input type="password" name="password"><br><br>
  	<input value="Log in" type="submit">
  </form>
    <br>
    <form action="/register">
        <input type = "submit" value="Register New User">
    </form>
</div>

</#assign>

<#include "main.ftl">
