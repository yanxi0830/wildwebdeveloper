package org.eclipse.wildwebdeveloper.xml;

import java.io.IOException;

import org.eclipse.lsp4xml.XMLLanguageServer;

public class LocalXMLLanguageServer extends LocalStreamConnectionProvider{
	
	public LocalXMLLanguageServer() {
		super(new XMLLanguageServer());
	}
	
	@Override
	public void start() throws IOException {
		super.start();
		((XMLLanguageServer) this.getServer()).setClient(launcher.getRemoteProxy());
	}

}
