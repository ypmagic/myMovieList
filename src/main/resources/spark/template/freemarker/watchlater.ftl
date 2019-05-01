<#assign content>
  <div class = "container content-align">
    <div class = "row">
      <h1>Watch Later</h1>
    </div>
    <div class="flex-row-cont">
      <div class = "flex-row">
        <#list list as movie>
          <div class = "flex-col">
            <#if movie.getImg()??>
              <a href="/m/${movie.getImdbID()}"><img class="movie-img ${movie} colored-border-img" src="${movie.getImg()}"/></a>
            <#else>
              <p class="movie-img ${movie}">${movie.getTitle()}</p>
            </#if>
          </div>
        </#list>
      </div>
    </div>
  </div>
</#assign>
<#include "main.ftl">
