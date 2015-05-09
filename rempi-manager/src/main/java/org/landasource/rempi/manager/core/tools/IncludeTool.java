package org.landasource.rempi.manager.core.tools;

import com.landasource.wiidget.Renderer;
import com.landasource.wiidget.engine.Engine;

/**
 *
 * @author Zsolti
 *
 */
public class IncludeTool {

	private final Engine engine;

	public IncludeTool(final Engine engine) {
		super();
		this.engine = engine;
	}

	public String include(final String path) {

		final String content = Renderer.create(engine).renderFile(path);
		return content;
	}

}
