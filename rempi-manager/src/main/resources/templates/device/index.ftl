<#import "/layout/main.ftl" as layout> <@layout.main>

<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">Panel title</h3>
   </div>
   <div class="panel-body">

      <table class="table table-hover table-striped">
         <thead>
            <tr>
               <th>ID</th>
               <th>Name</th>
               <th>Serial</th>
               <th>Type</th>
               <th></th>
            </tr>
         </thead>

         <tbody>
            <#list items as device>
            <tr>
               <td>${device.id}</td>
               <td>${device.name}</td>
               <td><span class="label label-info">${device.serial}</span></td>
               <td>${device.deviceType.name}</td>

               <td>

                  <div class="btn-group">
                     <a href="/device/edit/${device.id}" class="btn btn-info btn-small">Edit</a>
                     <a href="/device/control/${device.id}" class="btn btn-info btn-small">Control</a>
                     <a href="/device/delete/${device.id}" class="btn btn-danger btn-small">Delete</a>
                  </div>

               </td>

            </tr>
            </#list>
         </tbody>

      </table>


   </div>
</div>

<div>
   <a href="/device/add" class="btn btn-primary">Add device</a>
</div>

</@layout.main>
