<#assign content>

<div class="flex-container content-align">
  <div class="flex-row">
    <div class="flex-col">
      <div class="search-info">
        <h1 class="contrast-color">${numResults}</h1>
        <h2> movie results found...</h2>
      </div>
      <ul class="movie-list">
        <#list movies as movie>
          <li><a class="boldless-link" href="/m/${movie.getImdbID()}">${movie.getTitle()}</a><br></li>
        </#list>
      </ul>
    </div>
    <!-- EDIT LATER FOR USER RESULTS -->
    <div class="flex-col">
      <div class="search-info">
        <h1 class="contrast-color">${numResults}</h1>
        <h2> user results found...</h2>
      </div>
      <ul class="movie-list">
        <#list movies as movie>
          <li><a class="boldless-link" href="/m/${movie.getImdbID()}">${movie.getTitle()}</a><br></li>
        </#list>
      </ul>
    </div>
    <!-- EDIT LATER FOR USER RESULTS -->
  </div>
</div>
</#assign>
<#include "main.ftl">
