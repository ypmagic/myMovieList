<#assign content>
  <div class = "container content-align">
    <#if list??>
      <div class = "row">
        <h1 id = "username">${list.curator}'s List</h1>
      </div>
      <div class = "row">
        <h2 class="contrast-color">${list.name}</h2>
      </div>
      <ul class="movie-list">
        <#list list.movies as movie>
          <li><a class="boldless-link" href="/m/${movie.getImdbID()}">${movie.getTitle()}</a><br></li>
        </#list>
      </ul>
    <#else>
      <h1>List does not exist.</h1>
    </#if>
  </div>
</#assign>
<#include "main.ftl">
