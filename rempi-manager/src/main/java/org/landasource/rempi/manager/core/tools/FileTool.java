package org.landasource.rempi.manager.core.tools;

import java.io.File;
import java.io.IOException;

public class FileTool {

	/**
	 *
	 * @param name
	 * @param dir
	 * @return
	 * @throws IOException
	 *            when cannot find file
	 */
	public String getFile(final String name, final String dir) throws IOException {
		final String file = new File(dir, name).getCanonicalPath();
		return file;
	}

}
