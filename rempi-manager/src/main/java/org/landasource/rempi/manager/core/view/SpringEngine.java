package org.landasource.rempi.manager.core.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.engine.DefaultEngine;
import com.landasource.wiidget.engine.configuration.Configuration;

/**
 *
 * @author lzsolt
 *
 */
@Component
@Scope(org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST)
public class SpringEngine extends DefaultEngine {

	@Autowired
	private SpringWiidgetConfiguration conf;

	@Override
	public Configuration getConfiguration() {
		return conf;
	}

}
