package org.landasource.rempi.manager.core;

import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoggerPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		final List<Field> fields = new Mirror().on(bean.getClass()).reflectAll().fields();
		for (final Field field : fields) {
			if (Logger.class.isAssignableFrom(field.getType()) && new Mirror().on(field).reflect().annotation(org.landasource.rempi.manager.core.Logger.class) != null) {
				new Mirror().on(bean).set().field(field).withValue(LoggerFactory.getLogger(bean.getClass()));
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}
}