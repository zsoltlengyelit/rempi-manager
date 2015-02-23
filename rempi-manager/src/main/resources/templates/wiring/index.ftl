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
               <th></th>
            </tr>
         </thead>

         <tbody>
            <#list items as wiring>
            <tr>
               <td>${wiring.id}</td>
               <td>${wiring.name}</td>             
               <td>
                  <div class="btn-group">
                     <a href="/admin/wiring/edit/${wiring.id}" class="btn btn-info btn-small">Edit</a>                     
                     <a href="/admin/wiring/delete/${wiring.id}" class="btn btn-danger btn-small">Delete</a>
                  </div>
               </td>
            </tr>
            </#list>
         </tbody>

      </table>


   </div>
</div>

<div>
   <a href="/admin/wiring/add" class="btn btn-primary">Add wiring</a>
</div>

</@layout.main>
