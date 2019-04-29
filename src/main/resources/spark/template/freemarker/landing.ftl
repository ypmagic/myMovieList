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
        <img class="movie-img ${movie.getImdbID()}" src="${movie.getImg()}"/>
      </div>
      <div class="movie-popup colored-border" id="${movie.getImdbID()}">
        <div class="flex-row">
          <span class="exit-member-info">
            <span class="x x-member"></span>
          </span>
          <div class="flex-col">
            <!-- blown up image of the movie -->
            <img src="${movie.getImg()}"/>
          </div>
          <div class="flex-col">
            <!-- movie information like title, rating -->
            <h3>Film: ${movie.title}</h3>
            <h4>Rating: ${movie.imdbRating}</h4>
            <br/>
            <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
          </div>
        </div>
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
        <img class="movie-img ${movie.getImdbID()}" src="${movie.getImg()}"/>
      </div>
      <div class="movie-popup colored-border" id="${movie.getImdbID()}">
        <div class="flex-row">
          <span class="exit-member-info">
            <span class="x x-member"></span>
          </span>
          <div class="flex-col">
            <!-- blown up image of the movie -->
            <img src="${movie.getImg()}"/>
          </div>
          <div class="flex-col">
            <!-- movie information like title, rating -->
            <h3>Film: ${movie.title}</h3>
            <h4>Rating: ${movie.imdbRating}</h4>
            <br/>
            <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
          </div>
        </div>
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
        <img class="movie-img ${movie.getImdbID()}" src="${movie.getImg()}"/>
      </div>
      <div class="movie-popup colored-border" id="${movie.getImdbID()}">
        <div class="flex-row">
          <span class="exit-member-info">
            <span class="x x-member"></span>
          </span>
          <div class="flex-col">
            <!-- blown up image of the movie -->
            <img src="${movie.getImg()}"/>
          </div>
          <div class="flex-col">
            <!-- movie information like title, rating -->
            <h3>Film: ${movie.title}</h3>
            <h4>Rating: ${movie.imdbRating}</h4>
            <br/>
            <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
          </div>
        </div>
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
      <img class="movie-img ${movie.getImdbID()}" src="${movie.getImg()}"/>
    </div>
      <div class="movie-popup colored-border" id="${movie.getImdbID()}">
        <div class="flex-row">
          <span class="exit-member-info">
            <span class="x x-member"></span>
          </span>
          <div class="flex-col">
            <!-- blown up image of the movie -->
            <img src="${movie.getImg()}"/>
          </div>
          <div class="flex-col">
            <!-- movie information like title, rating -->
            <h3>Film: ${movie.title}</h3>
            <h4>Rating: ${movie.imdbRating}</h4>
            <br/>
            <p><a href="/m/${movie.getImdbID()}">More information on this movie...</a></p>
          </div>
        </div>
      </div>
    </#list>
  </div>
</div>
<!-- TO GREY OUT REST OF THE MOBILE SITE -->
<div class="mobile-overlay"></div>
</#assign>
<#include "main.ftl">
