<#assign content>
  <div class="container">
    <div class = "page-header">
      <h1> MyMovieList </h1>
    </div>

    <div class = "jumbotron">
      <div class = "row">
        <div class = "col">
          <p>
            col
          </p>
        </div>
        <div class = "col">
          <p>
            col
          </p>
        </div>
        <div class = "col">
          <form id="login">
            <div class = "form-group">
              <label for="usr"> Username: </label>
              <input type="text" class="form-control" name="username">
            </div>
            <div class = "form-group">
              <label for="pwd"> Password: </label>
              <input type="password" class="form-control" name="password">
            </div>
            <button type="submit" class = "btn btn-default">Login</button>
          </form>
          
          <form action="/register">
            <button type="submit" class="btn btn-default">Sign Up</button>
          </form>
        </div>
      </div>
    </div>
  </div>

</#assign>

<#include "main.ftl">
