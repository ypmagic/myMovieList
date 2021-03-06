<#assign content>

  <div class = "container content-align">
    <div class = "row">
      <h1 id = "username">${username}'s Profile</h1>
    </div>
    <div class = "row">
      <div class = "col">
        <h2 class="contrast-color"> Create a new list</h2>
      </div>
      <div class = "col">
        <h2 class="contrast-color"> Delete a list</h2>
      </div>
    </div>
    <div class = "row">
      <div class = "col">
        <form id="newList">
          <div class="form-group form-inline">
            <input type="text" class="form-control" name="listName" placeholder="List Name">
            <button type="submit" class = "btn btn-dark">Add List</button>
          </div>
        </form>
      </div>
      <div class = "col">
        <form id= "delList">
          <div class="form-group form-inline">
            <select class="form-control">
              <#list lists as movieList>
                <option value=${movieList.getId()}>${movieList.getName()}</option>
              </#list>
            </select>
            <button type="submit" class = "btn btn-dark">Delete List</button>
          </div>
        </form>
      </div>
    </div>
    <div class = "row">
      <h2 class="contrast-color"> Current Lists </h2>
    </div>
    <#list lists as movieList>
      <div class = "row">
        <h4><a class="boldless-link" href="/l/${movieList.getId()}">${movieList.getName()}</a></h4>
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
                <button name="${movieList.getId()}" value="${movie.getImdbID()}" type="submit" class = "btn-pad btn btn-dark">Remove</button>
              </form>
            </div>
          </#list>
        </div>
      </div>
    </#list>
    </div>
</#assign>
<#include "main.ftl">
