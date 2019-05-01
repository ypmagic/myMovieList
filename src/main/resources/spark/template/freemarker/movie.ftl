<#assign content>

<div class="container content-align">
  <#if movie.title??>
    <h3>Film: ${movie.title}</h3>
    <h4>Rating: ${movie.imdbRating}</h4>
    <p>${movie.rated}
    <p><strong>Plot: </strong> ${movie.plot}</p>
    <br/>
  <#else>
    <h3>Invalid movie.</h3>
  </#if>
</div>
</#assign>
<#include "main.ftl">
