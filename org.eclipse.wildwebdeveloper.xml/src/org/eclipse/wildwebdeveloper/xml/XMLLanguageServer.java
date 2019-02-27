/*******************************************************************************
 * Copyright (c) 2016 Red Hat Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Xi Yan (Red Hat Inc.) - initial implementation
 *******************************************************************************/
package org.eclipse.wildwebdeveloper.xml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;

public class XMLLanguageServer extends ProcessStreamConnectionProvider {
	
	private static final IPreferenceStore STORE = Activator.getDefault().getPreferenceStore();
	
	public XMLLanguageServer() {
		List<String> commands = new ArrayList<>();
		commands.add(computeJavaPath());
		commands.add("-classpath");
		try {
			URL url = FileLocator
					.toFileURL(getClass().getResource("/language-servers/server/org.eclipse.lsp4xml-all.jar"));
			commands.add(new java.io.File(url.getPath()).getAbsolutePath());
			commands.add("org.eclipse.lsp4xml.XMLServerLauncher");
			setCommands(commands);
			setWorkingDirectory(System.getProperty("user.dir"));
		} catch (IOException e) {
			Activator.getDefault().getLog().log(
					new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(), e.getMessage(), e));
		}
	}
	
	@Override
	public Object getInitializationOptions(URI rootUri) {
		return buildCustomSettings();
	}
	
	private String computeJavaPath() {
		String javaPath = "java";
		boolean existsInPath = Stream.of(System.getenv("PATH").split(Pattern.quote(File.pathSeparator))).map(Paths::get)
				.anyMatch(path -> Files.exists(path.resolve("java")));
		if (!existsInPath) {
			File f = new File(System.getProperty("java.home"),
					"bin/java" + (Platform.getOS().equals(Platform.OS_WIN32) ? ".exe" : ""));
			javaPath = f.getAbsolutePath();
		}
		return javaPath;
	}

	@Override
	public String toString() {
		return "XML Language Server: " + super.toString();
	}
	
	private Object buildCustomSettings() {
		Map<String, Object> options = new HashMap<>();
		
		// XML Catalog
		List<String> catalogsList = new LinkedList<>();
		for (String s : STORE.getString(XMLPreferenceInitializer.XML_PREFERENCES_CATAGLOGS).split(",")) {
			catalogsList.add(s);
		}
		options.put("catalogs", catalogsList);
		
		// Format Options
		Map<String, Object> format = new HashMap<>();
		format.put("enabled", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_ENABLED));
		format.put("splitAttributes", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_SPLIT_ATTRIBUTES));
		format.put("joinCDATALines", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_CDATA_LINES));
		format.put("joinCommentLines", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_COMMENT_LINES));
		format.put("formatComments", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_FORMAT_COMMENTS));
		format.put("joinContentLines", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_CONTENT_LINES));
		format.put("spaceBeforeEmptyCloseTag", STORE.getBoolean(XMLPreferenceInitializer.XML_FORMAT_SPACE_BEFORE_EMPTY_CLOSE_TAG));
		options.put("format", format);
		
		// AutoClosingTag
		Map<String, Object> completion = new HashMap<>();
		completion.put("autoCloseTags", STORE.getBoolean(XMLPreferenceInitializer.XML_COMPLETION_AUTO_CLOSE_TAGS));
		options.put("completion", completion);
		
		// Wrap around settings.xml
		Map<String, Object> xml = new HashMap<>();
		xml.put("xml", options);
		Map<String, Object> settings = new HashMap<>();
		settings.put("settings", xml);
		
		return settings;
	}
}
