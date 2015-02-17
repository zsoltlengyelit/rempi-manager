<#import "/layout/main.ftl" as layout>
<#import "/forms/html.ftl" as html>

 <#-- <#import "spring.ftl" as spring /> --> <@layout.main>

<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">Control: <b>${device.serial}</b></h3>
   </div>
   <div class="panel-body">
         
         <ul class="list-group">           
            <#list namedPins as gpio>
               <li class="list-group-item">${device.wiring.getPinName(gpio)}: 
                  <#if state[gpio].isEnabled()>
                     <a class="btn btn-small btn-success" href="/device/control/${device.id}/gpio/${gpio.pinNumber}/disable">Disable</a>
                  <#else>
                     <a class="btn btn-small btn-danger" href="/device/control/${device.id}/gpio/${gpio.pinNumber}/enable">Enable</a>
                  </#if>
               </li>
            </#list>
         </ul>
   
   </div>
</div>

<a href="/device" class="btn btn-default btn-small">Back</a>


</@layout.main>