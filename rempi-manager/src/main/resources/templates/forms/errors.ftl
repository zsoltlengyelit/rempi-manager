<#macro globalError result>
   
   <#if result != "" && result.hasGlobalErrors()>
   
   <#list result.getGlobalErrors() as error>
      <div class="alert alert-danger">
         ${error.getDefaultMessage()}
      </div>
   </#list>

</#if>

</#macro>