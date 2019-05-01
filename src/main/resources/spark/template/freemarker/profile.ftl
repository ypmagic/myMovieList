<#assign content>

  <div class = "container content-align">
    <div class = "row">
      <h1 id = "username">${username}'s Profile</h1>
    </div>
    <div class = "row">
      <h2 class="contrast-color"> Create a new list</h2>
    </div>
    <div class = "row">
      <form id="newList">
        <div class="form-group form-inline">
          <input type="text" class="form-control" name="listName" placeholder="List Name">
          <button type="submit" class = "btn btn-dark">Add</button>
        </div>
      </form>
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
              <#if movie.getImg()??>
                <a href="/m/${movie.getImdbID()}"><img class="movie-img ${movie} colored-border-img" src="${movie.getImg()}"/></a>
              <#else>
                <p class="movie-img ${movie}">${movie.getTitle()}</p>
              </#if>
            </div>
          </#list>
        </div>
      </div>
    </#list>
    </div>
</#assign>
<#include "main.ftl">
