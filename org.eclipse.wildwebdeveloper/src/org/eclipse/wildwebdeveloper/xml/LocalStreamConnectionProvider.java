package org.eclipse.wildwebdeveloper.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import javax.annotation.Nullable;

import org.eclipse.lsp4e.server.StreamConnectionProvider;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;

public class LocalStreamConnectionProvider implements StreamConnectionProvider {
	
	private InputStream inputStream;
	private OutputStream outputStream;
	private InputStream errorStream;
	private LanguageServer server;
	protected Launcher<LanguageClient> launcher;
	protected Future<?> future;

	public LocalStreamConnectionProvider(LanguageServer server) {
		this.setServer(server);
	}
	
	@Override
	public void start() throws IOException {
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in2 = new PipedInputStream();
		PipedOutputStream out2 = new PipedOutputStream();
		errorStream = new ByteArrayInputStream("Error output on console".getBytes(StandardCharsets.UTF_8));

		in.connect(out2);
		out.connect(in2);
		
		launcher = LSPLauncher.createServerLauncher(getServer(), in2, out2);
		inputStream = in;
		outputStream = out;
		future = launcher.startListening();
	}
	
	@Override
	public InputStream getInputStream() {
		return inputStream;
	}
	
	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}
	
	@Override
	public @Nullable InputStream getErrorStream() {
		return errorStream;
	}
	
	@Override
	public void stop() {
		System.out.println("STOP");
		// input stream closed before stopping?
//		future.cancel(true);
//		server = null;
//		try {
//			inputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		inputStream = null;
//		try {
//			outputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		outputStream = null;
	}

	public LanguageServer getServer() {
		return server;
	}

	public void setServer(LanguageServer server) {
		this.server = server;
	}

}
