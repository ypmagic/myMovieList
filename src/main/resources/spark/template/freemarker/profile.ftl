<#assign content>

  <div class = "container content-align">
    <div class = "row">
      <h1 id = "username">${username}'s Profile</h1>
    </div>
    <div class = "row">
      <h2> Create a new list</h2>
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
      <h2> Current Lists </h2>
    </div>
    <#list lists as movieList>
      <div class = "row">
        <h4> ${movieList.getName()} </h4>
      </div>
      <div class = "row">
        <#list movieList.getMovies() as movie>
          <div class = "col">
            <#if movie.getRight().getImg()??>
              <a href="/m/${movie.getLeft()}"><img class="movie-img ${movie.getLeft()} colored-border-img" src="${movie.getRight().getImg()}"/></a>
            <#else>
              <p class="movie-img ${movie.getLeft()}">${movie.getRight().getTitle()}</p>
            </#if>
          </div>
        </#list>
      </div>
    </#list>
  </div>
</#assign>
<#include "main.ftl">
