<#assign content>
<<<<<<< HEAD
<div class="container">
  <form id="login">
  	Username:<br>
  	<input type="text" name="username"><br>
  	Password:<br>
  	<input type="text" name="password"><br><br>
  	<input value="Log in" type="submit">
  </form>
</div>
=======
    <div class="container">
        <form id="login">
            Username:<br>
            <input type="text" name="username"><br>
            Password:<br>
            <input type="text" name="password">
            <input type="submit">
        </form>
        <br>
        <form action="/register">
            <input type = "submit" value="Register New User">
        </form>
    </div>
>>>>>>> 21bfc5d710ac68298aa7a9291f34e6e523b44f46
</#assign>
<#include "main.ftl">
