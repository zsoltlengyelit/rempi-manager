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

    public SpringWiidgetConfiguration() {
        super();
        //        addTransformRule(Transformers.create().match(RawWiidget.class).attr("class", "item", "list-group-item").build());
        //        addTransformRule(Transformers.create().match(RawWiidget.class).attr("class", "menu", "list-group").build());
    }

    @Override
    public FileLoader getFileLoader() {
        return fileLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }

}
