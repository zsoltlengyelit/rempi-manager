package org.landasource.rempi.manager.core.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.engine.configuration.DefaultConfiguration;
import com.landasource.wiidget.io.FileLoader;

/**
 *
 * @author lzsolt
 *
 */
@Component
public class SpringWiidgetConfiguration extends DefaultConfiguration {

	@Autowired
	private SpringWiidgetFileLoader fileLoader;

	@Override
	public FileLoader getFileLoader() {
		return fileLoader;
	}
}
