package org.landasource.rempi.manager.core.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.engine.configuration.DefaultClassLoader;

@Component
public class SpringClassLoader extends DefaultClassLoader {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Class<?> loadClass(final String name) throws ClassNotFoundException {

		try {
			return applicationContext.getClassLoader().loadClass(name);
		} catch (final ClassNotFoundException e) {
			return super.loadClass(name);
		}

	}

}
