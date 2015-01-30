<#import "/layout/main.ftl" as layout> <#-- <#import "spring.ftl" as spring /> --> <@layout.main>

<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">Panel title</h3>
   </div>
   <div class="panel-body">

      <form role="form" action="/user/add" method="post">

         <div class="form-group">
            <label for="username">Username</label> <input type="text" name="username" class="form-control" />
         </div>
         <div class="form-group">
            <label for="password">Password</label> <input type="password" name="password" class="form-control" />
         </div>
         <div class="form-group">
            <label for="fullName">Full name</label> <input type="text" name="fullName" class="form-control" />
         </div>

         <button type="submit" class="btn btn-default">Submit</button>
         <button type="reset" class="btn btn-default">Reset</button>

      </form>

   </div>
</div>


</@layout.main>
