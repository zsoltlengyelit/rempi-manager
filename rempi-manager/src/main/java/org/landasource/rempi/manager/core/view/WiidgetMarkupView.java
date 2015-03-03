package org.landasource.rempi.manager.core.view;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.landasource.wiidget.Renderer;
import com.landasource.wiidget.engine.Engine;

public class WiidgetMarkupView extends AbstractTemplateView {

	@Override
	public boolean checkResource(final Locale locale) throws Exception {

		final String url = getUrl();

		return getEngine().getConfiguration().getFileLoader().exists(url);

	}

	/**
	 * Invoked at startup.
	 */
	@Override
	protected void initApplicationContext(final ApplicationContext context) {
		super.initApplicationContext();
	}

	/**
	 * Autodetect a MarkupTemplateEngine via the ApplicationContext. Called if a
	 * MarkupTemplateEngine has not been manually configured.
	 *
	 * @param request
	 * @param model
	 */
	protected Engine getEngine() throws BeansException {

		try {
			return BeanFactoryUtils.beanOfTypeIncludingAncestors(
					getApplicationContext(), SpringEngine.class, true, false);
		} catch (final NoSuchBeanDefinitionException ex) {
			throw new ApplicationContextException(
					"Expected a single Wiidget engine bean in the current "
							+ "Servlet web application context or the parent root context: GroovyMarkupConfigurer is "
							+ "the usual implementation. This bean may have any name.",
					ex);
		}
	}

	@Override
	protected void renderMergedTemplateModel(final Map<String, Object> model,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		final Engine engine = this.getEngine();
		engine.getContext().setAll(model);
		engine.getContext().set("request", request);
		engine.getContext().set("applicationContext", getApplicationContext());

		final Resource resource = getApplicationContext().getResource(
				"classpath:" + getUrl());

		final InputStream inputStream = resource.getInputStream();

		final Renderer renderer = Renderer.create(engine);
		final String content = renderer.render(inputStream);

		final BufferedWriter writer = new BufferedWriter(response.getWriter());
		writer.append(content);
		writer.flush();
	}

}
