<#assign content>
  <div class = "container content-align">
    <div class = "row">
      <h1 id = "username">${username}'s Profile</h1>
    </div>
    <#list ret as movieList>
      <div class = "row">
        <h2> ${movieList.getName()} </h2>
        <#assign movies>${movieList.getMovies()}</#assign>
        <#list movies as movie>
          <div class = "col">
            <p> id: ${movie.getLeft()}</p>
            <p> name: ${movie.getRight()}</p>
          </div>
        </#list>
      </div>
    </#list>
  </div>
</#assign>
<#include "main.ftl">
