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
            </tr>
         </thead>

         <tbody>
         <#list users as user>
            <tr>
            <td>${user.id}</td>
            <td>${user.fullName}</td>
            <td>${user.username}</td>
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
