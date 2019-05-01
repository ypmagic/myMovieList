<#assign content>

<div class="container content-align">
  <div class="search-info">
    <h1 id="num-results">${numResults}</h1> <h2> results found...</h2>
  </div>
  <ul class="movie-list">
    <#list movies as movie>
      <li><a class="boldless-link" href="/m/${movie.getImdbID()}">${movie.getTitle()}</a><br></li>
    </#list>
  </ul>
</div>
</#assign>
<#include "main.ftl">
