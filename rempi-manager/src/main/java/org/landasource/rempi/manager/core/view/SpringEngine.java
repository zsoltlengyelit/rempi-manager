package org.landasource.rempi.manager.core.view;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.commons.Include;
import com.landasource.wiidget.commons.Out;
import com.landasource.wiidget.engine.DefaultEngine;
import com.landasource.wiidget.engine.configuration.Configuration;
import com.landasource.wiidget.library.template.Part;
import com.landasource.wiidget.library.template.SimplePart;
import com.landasource.wiidget.library.template.Template;
import com.landasource.wiidget.util.Properties;

/**
 *
 * @author lzsolt
 *
 */
@Component
@Scope(org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST)
public class SpringEngine extends DefaultEngine implements InitializingBean {

	@Autowired
	private SpringWiidgetConfiguration conf;

	@Autowired
	private SpringWiidgetProperties props;

	@Override
	public Configuration getConfiguration() {
		return conf;
	}

	@Override
	public Properties getProperties() {
		return props;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		getConfiguration().addDefaultImport(Out.class);

		// templating
		getConfiguration().addDefaultImport(Template.class);
		getConfiguration().addDefaultImport(Part.class);
		getConfiguration().addDefaultImport(SimplePart.class);
		getConfiguration().addDefaultImport(Include.class);

	}

}
