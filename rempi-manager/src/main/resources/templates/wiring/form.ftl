<#import "/layout/main.ftl" as layout>
<#import "/forms/html.ftl" as html>

<@layout.main>

<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">Panel title</h3>
   </div>
   <div class="panel-body">

      <form role="form" method="post">
         
         <@html.formGroup label="Name" field="name">
            <input type="text" name="name" class="form-control" value="${form.name!''}" />
         </@html.formGroup>
         
         <@html.formGroup label="Serial" field="serial">     
            <div class="container-fluid">
               <#list gpioPins as pin>
                  <div class="row">
                     <div class="col-lg-2">
                     ${pin}:
                     </div>
                     <div class="col-lg-10">    
                        <input type="text" name="pinTable[${pin}]" class="form-control" value="${form.pinTable[pin]!''}" />
                     </div>
                  </div>
               </#list>
            </div>
         </@html.formGroup>
     
                      
         <button type="submit" class="btn btn-default">Submit</button>
         <button type="reset" class="btn btn-default">Reset</button>

      </form>

   </div>
</div>

<a href="/admin/wiring" class="btn btn-default btn-small">Back</a>


</@layout.main>
