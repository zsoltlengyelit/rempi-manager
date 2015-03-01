package org.landasource.rempi.manager.core.view;

import java.util.Locale;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class WiidgetViewResolver extends AbstractTemplateViewResolver {

	public WiidgetViewResolver() {
		setViewClass(requiredViewClass());
	}

	@Override
	protected Class<?> requiredViewClass() {
		return WiidgetMarkupView.class;
	}

	/**
	 * This resolver supports i18n, so cache keys should contain the locale.
	 */
	@Override
	protected Object getCacheKey(final String viewName, final Locale locale) {
		return viewName + "_" + locale;
	}

}
