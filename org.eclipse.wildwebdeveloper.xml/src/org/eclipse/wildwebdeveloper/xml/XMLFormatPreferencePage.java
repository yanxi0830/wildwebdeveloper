package org.eclipse.wildwebdeveloper.xml;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class XMLFormatPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private IPreferenceStore store;
	private Button enable;
	private Button splitAttribute;
	private Button joinCDATALines;
	private Button joinCommentLines;
	private Button formatComments;
	private Button joinContentLines;
	private Button spaceBeforeEmptyCloseTag;
	private Button autoCloseTags;
	
	@Override
	public void init(IWorkbench workbench) {
		store = doGetPreferenceStore();
	}

	@Override
	protected Control createContents(Composite parent) {
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);
		Label formatLabel = new Label(parent, SWT.NONE);
		formatLabel.setText("Set Formatting Options");

		enable = new Button(parent, SWT.CHECK);
		enable.setText("Enable/disable ability to format document");
		enable.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_ENABLED));
		
		splitAttribute = new Button(parent, SWT.CHECK);
		splitAttribute.setText("Split multiple attributes each onto a new line");
		splitAttribute.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_SPLIT_ATTRIBUTES));
		
		joinCDATALines = new Button(parent, SWT.CHECK);
		joinCDATALines.setText("Join lines in a CDATA tag's content");
		joinCDATALines.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_CDATA_LINES));
		
		joinCommentLines = new Button(parent, SWT.CHECK);
		joinCommentLines.setText("Join comment content on format");
		joinCommentLines.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_COMMENT_LINES));
		
		formatComments = new Button(parent, SWT.CHECK);
		formatComments.setText("Format comments");
		formatComments.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_FORMAT_COMMENTS));
		
		joinContentLines = new Button(parent, SWT.CHECK);
		joinContentLines.setText("Join lines in an element's content");
		joinContentLines.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_JOIN_CONTENT_LINES));
		
		spaceBeforeEmptyCloseTag = new Button(parent, SWT.CHECK);
		spaceBeforeEmptyCloseTag.setText("Insert space before end of self closing tag. i.e. <tag/> -> <tag />");
		spaceBeforeEmptyCloseTag.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_FORMAT_SPACE_BEFORE_EMPTY_CLOSE_TAG));
		
		autoCloseTags = new Button(parent, SWT.CHECK);
		autoCloseTags.setText("Enable/disable autoclosing of XML tags");
		autoCloseTags.setSelection(store.getBoolean(XMLPreferenceInitializer.XML_COMPLETION_AUTO_CLOSE_TAGS));
		return new Composite(parent, SWT.NONE);
	}
	
	@Override
	public boolean performOk() {
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_ENABLED, enable.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_SPLIT_ATTRIBUTES, splitAttribute.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_JOIN_CDATA_LINES, joinCDATALines.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_JOIN_COMMENT_LINES, joinCommentLines.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_FORMAT_COMMENTS, formatComments.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_JOIN_CONTENT_LINES, joinContentLines.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_FORMAT_SPACE_BEFORE_EMPTY_CLOSE_TAG, spaceBeforeEmptyCloseTag.getSelection());
		store.setValue(XMLPreferenceInitializer.XML_COMPLETION_AUTO_CLOSE_TAGS, autoCloseTags.getSelection());
		return super.performOk();
	}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

}
