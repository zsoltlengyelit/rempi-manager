
<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    login.bad.credentials
</#if>

<form action="/login" method="post">
    <label for="username">Username</label><input type="text" id="username" name="username"><br/>
    <label for="password">Password</label><input type="password" id="password" name="password"><br/>
   <input type="submit" value="Login!">
</form>