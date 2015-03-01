package org.landasource.rempi.manager.core.view;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.engine.configuration.ClassPathFileLoader;

@Component
public class SpringWiidgetFileLoader extends ClassPathFileLoader {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public InputStream getFile(final String path) {

		final String filePath = path;

		// if (filePath.startsWith("/")) {
		// filePath = filePath.substring(1);
		// }

		final Resource resource = applicationContext.getResource("classpath:"
				+ filePath);
		if (null != resource) {
			try {
				return resource.getInputStream();
			} catch (final IOException e) {
				return null;
			}
		}

		return super.getFile(path);
	}

}
