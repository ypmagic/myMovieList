<#assign content>
<!-- Each of these flex containers should contain a row of movie poster images,
as well as a clickable link to the respective movie link-->
<div class="flex-container" id="top-movie-container">
  <!-- change this for category name -->
  <h2>Top Rated</h2>
  <div class="flex-row">
    <#list moviesTop as movie>
      <div class="flex-col">
        <!-- image of the movie -->
        <a href="/m/${movie.getImdbID()}"><img src="${movie.getImg()}"/></a>
      </div>
    </#list>
  </div>
</div>
<div class="flex-container" id="top-mid-movie-container">
  <!-- change this for category name -->
  <h2>Top Rated</h2>
  <div class="flex-row">
    <#list moviesTopMid as movie>
      <div class="flex-col">
        <!-- image of the movie -->
        <a href="/m/${movie.getImdbID()}"><img src="${movie.getImg()}"/></a>
      </div>
    </#list>
  </div>
</div>
<div class="flex-container" id="bot-mid-movie-container">
  <!-- change this for category name -->
  <h2>Top Rated</h2>
  <div class="flex-row">
    <#list moviesBotMid as movie>
      <div class="flex-col">
        <!-- image of the movie -->
        <a href="/m/${movie.getImdbID()}"><img src="${movie.getImg()}"/></a>
      </div>
    </#list>
  </div>
</div>
<div class="flex-container" id="bot-movie-container">
  <!-- change this for category name -->
  <h2>Top Rated</h2>
  <div class="flex-row">
    <#list moviesBot as movie>
      <div class="flex-col">
        <!-- image of the movie -->
        <a href="/m/${movie.getImdbID()}"><img src="${movie.getImg()}"/></a>
      </div>
    </#list>
  </div>
</div>
</#assign>
<#include "main.ftl">
