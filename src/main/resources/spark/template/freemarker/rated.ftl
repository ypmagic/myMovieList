<#assign content>
  <div class = "container content-align">
    <div class = "row">
      <h1>Rated Movies</h1>
    </div>
    <div class="flex-row-cont">
      <div class = "flex-row">
        <#list list as movie>
          <div class = "flex-col">
            <#assign id>${movie.getLeft().getImdbID()}</#assign>
            <form class = "remove-rated">
              <#if movie.getLeft().getImg()??>
                <a href="/m/${id}"><img class="movie-img ${movie} colored-border-img" src="${movie.getLeft().getImg()}"/></a>
              <#else>
                <p class="movie-img ${movie}">${movie.getLeft().getTitle()}</p>
              </#if>
              <button value="${id}" type="submit" class = "btn-pad btn btn-dark">Remove</button>
              <p><strong>${movie.getRight()}</strong></p>
            </form>
            <form class= "ratingChange">
              <input type="number" min="1" max="10">
              <button value="${id}" type="submit" class = "btn btn-dark">Change Rating</button>
            </form>
          </div>
        </#list>
      </div>
    </div>
  </div>
</#assign>
<#include "main.ftl">
