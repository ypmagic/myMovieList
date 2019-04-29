<#assign content>

<h1> ${name} </h1>
<h2> by: ${curator} </h2>

<#list movies as movie>
  <#assign movieId>${movie.getLeft()}</#assign>
  <a href="/m/${movieId}">${movieS.getRight()}</a><br>
</#list>
<br>

</#assign>
<#include "/main.ftl">