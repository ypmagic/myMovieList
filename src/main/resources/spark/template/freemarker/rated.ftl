<#assign content>
  <div class = "container content-align">
    <div class = "row">
      <h1>Rated Movies</h1>
    </div>
    <div class="flex-row-cont">
      <div class = "flex-row">
        <#list list as movie>
          <div class = "flex-col">
            <#if movie.getLeft().getImg()??>
              <a href="/m/${movie.getLeft().getImdbID()}"><img class="movie-img ${movie} colored-border-img" src="${movie.getLeft().getImg()}"/></a>
            <#else>
              <p class="movie-img ${movie}">${movie.getLeft().getTitle()}</p>
            </#if>
            <p> ${movie.getRight()}</p>
          </div>
        </#list>
      </div>
    </div>
  </div>
</#assign>
<#include "main.ftl">
