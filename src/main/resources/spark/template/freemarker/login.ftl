<#assign content>
  <div class="container">
    <div class = "page-header">
      <h1> MyMovieList </h1>
    </div>

    <div class = "jumbotron">
      <div class = "row">
        <div class = "col text-center mx-auto">
          <img src="https://i.imgur.com/5gKsLLp.png" class = "img-fluid">
          <h3> Browse Movies </h3>
        </div>
        <div class = "col text-center mx-auto">
          <img src="https://i.imgur.com/sFUFOyO.png" class = "img-fluid">
          <h3> Rate and Recommend </h3>
        </div>

        <div class = "col">
          <h3> Join Now!</h3>
          <form id="login">
            <div class = "form-group">
              <label for="usr"> Username: </label>
              <input type="text" class="form-control" name="username">
            </div>
            <div class = "form-group">
              <label for="pwd"> Password: </label>
              <input type="password" class="form-control" name="password">
            </div>
            <button type="submit" class = "btn btn-dark">Login</button>
          </form>

          <br>

          <form action="/register">
            <button type="submit" class="btn btn-dark">Sign Up</button>
          </form>
        </div>
      </div>
    </div>
  </div>

</#assign>

<#include "main.ftl">
