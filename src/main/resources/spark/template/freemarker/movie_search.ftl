<#assign content>

<div class="container content-align">
  <#list movies as movie>
    <a href="/m/${movie.getImdbID()}">${movie.getTitle()}</a><br>
  </#list>
</div>
</#assign>
<#include "main.ftl">
