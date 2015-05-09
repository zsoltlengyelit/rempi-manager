package org.landasource.rempi.manager.core.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.landasource.wiidget.WiidgetException;
import com.landasource.wiidget.engine.configuration.ClassPathFileLoader;

@Component
public class SpringWiidgetFileLoader extends ClassPathFileLoader {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public InputStream getFile(final String path) {

		final File absolute = new File(path);
		if (absolute.exists() && absolute.canRead()) {
			try {
				return new FileInputStream(absolute);
			} catch (final FileNotFoundException e) {
				throw new WiidgetException("Cannot found file", e);
			}
		}

		final String filePath = path;

		final Resource resource = applicationContext.getResource("classpath:" + filePath);
		if (null != resource) {
			try {
				return resource.getInputStream();
			} catch (final IOException e) {
			}
		}

		return super.getFile(path);
	}

	@Override
	public Path getPath(final String value) {

		final String filePath = value;

		final Resource resource = applicationContext.getResource("classpath:" + filePath);
		if (null != resource) {
			try {
				return resource.getFile().toPath();
			} catch (final IOException e) {
			}
		}

		return super.getPath(value);
	}

}
