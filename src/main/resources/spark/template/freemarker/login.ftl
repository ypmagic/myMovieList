<#assign content>
  <div class="container colored-border content-padding">
    <div class = "page-header">
      <h1> MyMovieList </h1>
    </div>

    <div class = "jumbotron">
      <div class = "row">
        <div class = "col text-center mx-auto">
          <h3> Browse Movies </h3>
        </div>
        <div class = "col text-center mx-auto">
          <img src="https://i.imgur.com/sFUFOyO.png" class = "img-fluid">
          <h3> Rate and Recommend </h3>
        </div>
        <div class = "col text-center mx-auto">
          <h3> Create and Share Lists </h3>
        </div>
      </div>
      <br/>
      <#if !exists>
        <div class="row">
          <div class="col text-center mx-auto">
            <h3> Join Now!</h3>
            <br>
            <form action="/register">
              <button type="submit" class="btn btn-dark">Sign Up</button>
            </form>
          </div>
        </div>
      </#if>
    </div>
  </div>
  <#if !exists>
    <div class="content-align-form" align="center">
      <form class="form-inline" id="login">
        <input type="text" class="form-control form-width-control" name="username" placeholder="username">
        <input type="password" class="form-control form-width-control" name="password" placeholder="password">
        <button type="submit" class = "btn btn-dark">Login</button>
      </form>
      <br>
    </div>
  <#else>
    <div class="container">
      <div class = "row">
        <div class = "col text-center mx-auto">
          <a href="/home" class="bolded-link"><p>Go Back to Movies</p></a>
        </div>
      </div>
    </div>
  </#if>
</#assign>

<#include "no-nav.ftl">
