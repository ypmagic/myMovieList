<#assign content>
    <div class="container">
        <form id="login">
            Username:<br>
            <input type="text" name="username"><br>
            Password:<br>
            <input type="text" name="password">
            <input type="submit">
        </form>
        <br>
        <form action="/registerPage">
            <input type = "submit" value="Register New User">
        </form>
    </div>
</#assign>
<#include "main.ftl">
