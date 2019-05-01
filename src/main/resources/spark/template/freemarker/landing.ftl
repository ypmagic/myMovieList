<#assign content>
<!-- Each of these flex containers should contain a row of movie poster images,
as well as a clickable link to the respective movie link-->
<div class="flex-container content-align" id="top-movie-container">
  <div class="user-welcome">
    <h3>${username}</h3>
  </div>
  <!-- change this for category name -->
  <div class = "row">
    <h2>Top ${moviesTopGenre}</h2>
  </div>
  <div class="flex-row-cont solid-border">
    <div class="flex-row">
      <#list moviesTop as movie>
        <div class="flex-col">
          <!-- image of the movie -->
          <#if movie.getImg()??>
            <img class="movie-img ${movie.getImdbID()} colored-border-img" src="${movie.getImg()}"/>
          <#else>
            <p class="movie-img ${movie.getImdbID()} colored-border-img">${movie.getTitle()}</p>
          </#if>
        </div>
        <div class="movie-popup colored-border" id="${movie.getImdbID()}">
          <div class="flex-row">
            <span class="exit-member-info">
              <span class="x x-movie"></span>
            </span>
            <#if movie.getImg()??>
              <div class="flex-col">
                <!-- blown up image of the movie -->
                <img src="${movie.getImg()}"/>
              </div>
            </#if>
            <div class="flex-col">
              <!-- movie information like title, rating -->
              <h3>Film: ${movie.title}</h3>
              <h4>Rating: ${movie.imdbRating}</h4>
              <br/>
              <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
              <form class= "formlst">
                <div class="form-group">
                  <select class="form-control" name="${movie.getImdbID()}">
                    <#list userLists as lst>
                      <option value=${lst.getLeft()}>${lst.getRight()}</option>
                    </#list>
                  </select>
                </div>
                <button type="submit" class = "btn btn-dark" id = "add">Add To List</button>
              </form>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>

  <!-- change this for category name -->
  <div class = "row">
    <h2>Top ${moviesTopMidGenre}</h2>
  </div>
  <div class="flex-row-cont solid-border">
    <div class="flex-row">
      <#list moviesTopMid as movie>
        <div class="flex-col">
          <!-- image of the movie -->
          <#if movie.getImg()??>
            <img class="movie-img ${movie.getImdbID()} colored-border-img" src="${movie.getImg()}"/>
          <#else>
            <p class="movie-img ${movie.getImdbID()}">${movie.getTitle()}</p>
          </#if>
        </div>
        <div class="movie-popup colored-border" id="${movie.getImdbID()}">
          <div class="flex-row">
            <span class="exit-member-info">
              <span class="x x-movie"></span>
            </span>
            <#if movie.getImg()??>
              <div class="flex-col">
                <!-- blown up image of the movie -->
                <img src="${movie.getImg()}"/>
              </div>
            </#if>
            <div class="flex-col">
              <!-- movie information like title, rating -->
              <h3>Film: ${movie.title}</h3>
              <h4>Rating: ${movie.imdbRating}</h4>
              <br/>
              <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
              <form class="formlst">
                <div class="form-group">
                  <select class="form-control" name="${movie.getImdbID()}">
                    <#list userLists as lst>
                      <option value=${lst.getLeft()}>${lst.getRight()}</option>
                    </#list>
                  </select>
                </div>
                <button type="submit" class = "btn btn-dark" id = "add">Add To List</button>
              </form>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>

  <!-- change this for category name -->
  <div class = "row">
    <h2>Top ${moviesBotMidGenre}</h2>
  </div>
  <div class="flex-row-cont solid-border">
    <div class="flex-row">
      <#list moviesBotMid as movie>
        <div class="flex-col">
          <!-- image of the movie -->
          <#if movie.getImg()??>
            <img class="movie-img ${movie.getImdbID()} colored-border-img" src="${movie.getImg()}"/>
          <#else>
            <p class="movie-img ${movie.getImdbID()}">${movie.getTitle()}</p>
          </#if>
        </div>
        <div class="movie-popup colored-border" id="${movie.getImdbID()}">
          <div class="flex-row">
            <span class="exit-member-info">
              <span class="x x-movie"></span>
            </span>
            <#if movie.getImg()??>
              <div class="flex-col">
                <!-- blown up image of the movie -->
                <img src="${movie.getImg()}"/>
              </div>
            </#if>
            <div class="flex-col">
              <!-- movie information like title, rating -->
              <h3>Film: ${movie.title}</h3>
              <h4>Rating: ${movie.imdbRating}</h4>
              <br/>
              <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
              <form class="formlst">
                <div class="form-group">
                  <select class="form-control" name="${movie.getImdbID()}">
                    <#list userLists as lst>
                      <option value=${lst.getLeft()}>${lst.getRight()}</option>
                    </#list>
                  </select>
                </div>
                <button type="submit" class = "btn btn-dark" id = "add">Add To List</button>
              </form>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>

  <!-- change this for category name -->
  <div class = "row">
    <h2>Top ${moviesBotGenre}</h2>
  </div>
  <div class="flex-row-cont solid-border">
    <div class="flex-row">
      <#list moviesBot as movie>
        <div class="flex-col">
          <!-- image of the movie -->
          <#if movie.getImg()??>
            <img class="movie-img ${movie.getImdbID()} colored-border-img" src="${movie.getImg()}"/>
          <#else>
            <p class="movie-img ${movie.getImdbID()}">${movie.getTitle()}</p>
          </#if>
        </div>
        <div class="movie-popup colored-border" id="${movie.getImdbID()}">
          <div class="flex-row">
            <span class="exit-member-info">
              <span class="x x-movie"></span>
            </span>
            <#if movie.getImg()??>
              <div class="flex-col">
                <!-- blown up image of the movie -->
                <img src="${movie.getImg()}"/>
              </div>
            </#if>
            <div class="flex-col">
              <!-- movie information like title, rating -->
              <h3>Film: ${movie.title}</h3>
              <h4>Rating: ${movie.imdbRating}</h4>
              <br/>
              <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
              <form class="formlst">
                <div class="form-group">
                  <select class="form-control" name="${movie.getImdbID()}">
                    <#list userLists as lst>
                      <option value=${lst.getLeft()}>${lst.getRight()}</option>
                    </#list>
                  </select>
                </div>
                <button type="submit" class = "btn btn-dark" id = "add">Add To List</button>
              </form>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>
</#assign>
<#include "main.ftl">
