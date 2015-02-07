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
            <#list items as type>
            <tr>
               <td>${type.id}</td>
               <td>${type.name}</td>
              
               <td>

                  <div class="btn-group">
                     <a href="/deviceType/edit/${type.id}" class="btn btn-info btn-small">Edit</a>
                     <a href="/deviceType/delete/${type.id}" class="btn btn-danger btn-small">Delete</a>
                  </div>

               </td>

            </tr>
            </#list>
         </tbody>

      </table>


   </div>
</div>

<div>
   <a href="/deviceType/add" class="btn btn-primary">Add type</a>
</div>

</@layout.main>
