package org.landasource.rempi.manager.core.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.engine.configuration.ClassLoader;
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

	@Autowired
	private SpringClassLoader classLoader;

	@Override
	public FileLoader getFileLoader() {
		return fileLoader;
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}
}
