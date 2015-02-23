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
               <th>Full name</th>
               <th>Username</th>
               <th>Active</th>
               <th></th>
            </tr>
         </thead>

         <tbody>
         <#list items as user>
            <tr>
            <td>${user.id}</td>
            <td>${user.fullName}</td>
            <td>${user.username}</td>
            <td>${user.enabled?string('Yes', 'No')}</td>
            
            <td>
            
            <a href="/user/edit/${user.id}" class="btn btn-info btn-small">Edit</a>
            <a href="/user/delete/${user.id}" class="btn btn-danger btn-small">Delete</a>
            
            </td>
            
            </tr>
         </#list>
         </tbody>

      </table>


   </div>
</div>

<div>
   <a href="/user/add" class="btn btn-primary">Add user</a>
</div>

</@layout.main>
