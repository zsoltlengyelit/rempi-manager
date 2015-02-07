<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<#assign home><@spring.url relativeUrl="/"/></#assign>
<#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
<link rel="stylesheet" href="${bootstrap}" />
</head>
<body>
	<div class="container">
		<h1>Error Page</h1>
		<div id="created">${timestamp?datetime}</div>
		<div>
			There was an unexpected error (type=${error}, status=${status}).
		</div>
		<div>${message}</div>
		<div>
			Please contact the operator with the above information.
		</div>
	</div>
</body>
</html>