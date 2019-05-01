<#assign content>
  <div class = "container content-align">
    <div class = "row">
      <h1 id = "username">${username}'s Profile</h1>
    </div>
    <div class = "row">
      <h2 class="contrast-color"> Current Lists </h2>
    </div>
    <#list lists as movieList>
      <div class = "row">
        <h4> ${movieList.getName()} </h4>
      </div>
      <div class="flex-row-cont">
        <div class = "flex-row">
          <#list movieList.movies as movie>
            <div class = "flex-col">
              <form class= "remove-buttons">
              <#if movie.getImg()??>
                <a href="/m/${movie.getImdbID()}"><img class="movie-img ${movie} colored-border-img" src="${movie.getImg()}"/></a>
              <#else>
                <p class="movie-img ${movie}">${movie.getTitle()}</p>
              </#if>
              </form>
            </div>
          </#list>
        </div>
      </div>
    </#list>
  </div>
</#assign>
<#include "main.ftl">
