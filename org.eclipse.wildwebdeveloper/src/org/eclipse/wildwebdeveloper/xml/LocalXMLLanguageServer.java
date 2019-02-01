package org.eclipse.wildwebdeveloper.xml;

import java.io.IOException;

import org.eclipse.lsp4xml.XMLLanguageServer;

public class LocalXMLLanguageServer extends LocalStreamConnectionProvider{
	private static final XMLLanguageServer XML_LANGUAGE_SERVER = new XMLLanguageServer();
	
	public LocalXMLLanguageServer() {
		super(XML_LANGUAGE_SERVER);
	}
	
	@Override
	public void start() throws IOException {
		super.start();
		XML_LANGUAGE_SERVER.setClient(launcher.getRemoteProxy());
	}

}
