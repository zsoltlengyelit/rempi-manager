
function init(){
	$('.gpio-mode-btn').click(function(event){
		
		event.preventDefault();		
		var $a = $(this);		
		var href = $a.attr('href');
		var panelId = '#device-panel';
		
		$.get(href, function(data){
			
			var panel = $(data).find(panelId);
			$(panelId).replaceWith(panel);
			
			init();
		});
				
	});
}

$(function(){
	
	init();
	
});