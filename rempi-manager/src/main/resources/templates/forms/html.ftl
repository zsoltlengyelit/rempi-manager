<#-- Simple closed tag -->
<#macro tag name attrs = {}>
<${name}<#list attrs?keys as key> ${key}=${attrs[key]}</#list> />
</#macro>

<#-- Tag with children -->
<#macro parentTag name attrs = {}>
<${name}<#list attrs?keys as key> ${key}=${attrs[key]}</#list> >
   <#nested>
</${name}>
</#macro>


<#macro inputText model field>
   <@tag name="input" attrs={"type":"text", "name": field, "value": model[field]} />
</#macro>

<#macro formGroup field label>
<div class="form-group">
   <label for="${field}">${label}</label> <#nested>
</div>
</#macro>