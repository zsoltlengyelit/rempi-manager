<#import "/layout/main.ftl" as layout> <#-- <#import "spring.ftl" as spring /> --> <@layout.main>
<#import "/forms/html.ftl" as html>

<#global result=result!''>

<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">Panel title</h3>
   </div>
   <div class="panel-body">

      <form role="form" method="post">

         <@html.formGroup label="Username" field="username"> <input type="text" name="username" class="form-control" value="${form.username!''}" /> </@html.formGroup>
         <@html.formGroup label="Password" field="password"> <input type="password" name="password" class="form-control" value="" /> </@html.formGroup>
         <@html.formGroup label="Full name" field="fullName"> <input type="text" name="fullName" class="form-control" value="${form.fullName!''}" /> </@html.formGroup>
         <@html.formGroup label="Active" field="enabled"> <input type="checkbox" name="enabled" class="form-control" ${form.enabled?string('checked', '')} /> </@html.formGroup>

         <button type="submit" class="btn btn-default">Submit</button>         
      </form>

   </div>
</div>

<a href="/user" class="btn btn-default btn-small">Back</a>


</@layout.main>
