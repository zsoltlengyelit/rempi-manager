<#import "/layout/main.ftl" as layout>
<#import "/forms/html.ftl" as html>

 <#-- <#import "spring.ftl" as spring /> --> <@layout.main>

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
            <input type="text" name="serial" class="form-control" value="${form.serial!''}" />
         </@html.formGroup>
         
         <@html.formGroup label="Type" field="deviceTypeId">
            <select name="deviceTypeId" class="form-control">
            <#list deviceTypes as type>
               <#if form.deviceTypeId?? && type.id == form.deviceTypeId>
                  <option value="${type.id}" selected>${type.name}</option>
               <#else>
                  <option value="${type.id}">${type.name}</option>
               </#if>
            </#list>
            </select>
         </@html.formGroup>
         
         <@html.formGroup label="Wiring" field="wiringId">
            <select name="wiringId" class="form-control">
            <#list wirings as wiring>
               <#if form.wiringId?? && wiring.id == form.wiringId>
                  <option value="${wiring.id}" selected>${wiring.name}</option>
               <#else>
                  <option value="${wiring.id}">${wiring.name}</option>
               </#if>
            </#list>
            </select>
         </@html.formGroup>
                      
         <button type="submit" class="btn btn-default">Submit</button>
         <button type="reset" class="btn btn-default">Reset</button>

      </form>

   </div>
</div>

<a href="/device" class="btn btn-default btn-small">Back</a>


</@layout.main>
