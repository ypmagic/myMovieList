<#assign content>

<div class="container content-align">
  <#list movies as movie>
    <p>${movie.getTitle()}</p>
  </#list>
</div>
</#assign>
<#include "main.ftl">
