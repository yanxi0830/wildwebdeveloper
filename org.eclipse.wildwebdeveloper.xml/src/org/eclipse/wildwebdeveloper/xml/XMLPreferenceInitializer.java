package org.eclipse.wildwebdeveloper.xml;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class XMLPreferenceInitializer extends AbstractPreferenceInitializer {
	
	private static final IPreferenceStore STORE = Activator.getDefault().getPreferenceStore();
	public static final String XML_PREFERENCES_CATAGLOGS = "wildwebdeveloper.xml.catalogs";
	public static final String XML_PREFERENCES_AUTO_CLOSE_TAGS = "wildwebdeveloper.xml.completion.autoCloseTags";
	public static final String XML_FORMAT_ENABLED = "wildwebdeveloper.xml.format.enabled";
	public static final String XML_FORMAT_SPLIT_ATTRIBUTES = "wildwebdeveloper.xml.format.splitAttributes";
	public static final String XML_FORMAT_JOIN_CDATA_LINES = "wildwebdeveloper.xml.format.joinCDATALines";
	public static final String XML_FORMAT_JOIN_COMMENT_LINES = "wildwebdeveloper.xml.format.joinCommentLines";
	public static final String XML_FORMAT_FORMAT_COMMENTS = "wildwebdeveloper.xml.format.formatComments";
	public static final String XML_FORMAT_JOIN_CONTENT_LINES = "wildwebdeveloper.xml.format.joinContentLines";
	public static final String XML_FORMAT_SPACE_BEFORE_EMPTY_CLOSE_TAG = "wildwebdeveloper.xml.format.spaceBeforeEmptyCloseTag";
	public static final String XML_COMPLETION_AUTO_CLOSE_TAGS = "wildwebdeveloper.xml.completion.autoCloseTags";
	
	@Override
	public void initializeDefaultPreferences() {
		STORE.setDefault(XML_PREFERENCES_CATAGLOGS, "");
		STORE.setDefault(XML_PREFERENCES_AUTO_CLOSE_TAGS, true);
		STORE.setDefault(XML_FORMAT_ENABLED, true);
		STORE.setDefault(XML_FORMAT_SPLIT_ATTRIBUTES, false);
		STORE.setDefault(XML_FORMAT_JOIN_CDATA_LINES, false);
		STORE.setDefault(XML_FORMAT_JOIN_COMMENT_LINES, false);
		STORE.setDefault(XML_FORMAT_FORMAT_COMMENTS, true);
		STORE.setDefault(XML_FORMAT_JOIN_CONTENT_LINES, false);
		STORE.setDefault(XML_FORMAT_SPACE_BEFORE_EMPTY_CLOSE_TAG, true);
		STORE.setDefault(XML_COMPLETION_AUTO_CLOSE_TAGS, true);
	}
	

}
