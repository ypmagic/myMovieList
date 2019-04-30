<#assign content>
  <div class="container colored-border content-padding">
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
        <div class = "col text-center mx-auto">
          <img src="https://i.ibb.co/B4MV8Gq/checkboard.png" class = "img-fluid">
          <h3> Create and Share Lists </h3>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col text-center mx-auto">
          <h3> Join Now!</h3>
          <form action="/register">
            <button type="submit" class="btn btn-dark">Sign Up</button>
          </form>
        </div>
      </div>
    </div>
  </div>
    <div class="content-align-form" align="center">
      <form class="form-inline" id="login">
        <input type="text" class="form-control form-width-control" name="username" placeholder="username">
        <input type="password" class="form-control form-width-control" name="password" placeholder="password">
        <button type="submit" class = "btn btn-dark">Login</button>
      </form>

      <br>
    </div>
</#assign>

<#include "no-nav.ftl">
