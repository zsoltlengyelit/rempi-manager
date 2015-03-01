package org.landasource.rempi.manager.core.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;

@Component
@Configuration
public class ViewConfiguration {

	@Bean
	public ViewResolver viewResolver() {
		final WiidgetViewResolver wiidgetViewResolver = new WiidgetViewResolver();
		wiidgetViewResolver.setPrefix("/templates/");
		wiidgetViewResolver.setSuffix(".wdgt");
		wiidgetViewResolver.setCache(false);

		return wiidgetViewResolver;
	}

}
