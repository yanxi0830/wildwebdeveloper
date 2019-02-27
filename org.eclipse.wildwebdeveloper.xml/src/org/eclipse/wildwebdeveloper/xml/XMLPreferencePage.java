package org.eclipse.wildwebdeveloper.xml;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class XMLPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		noDefaultAndApplyButton();
	}

	@Override
	protected Control createContents(Composite parent) {
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);
		Link catalogsLink = new Link(parent, SWT.NONE);
		catalogsLink.setText("See <A>'Catalogs'</A> configure XML catalogs");
		catalogsLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (getContainer() instanceof IWorkbenchPreferenceContainer) {
					((IWorkbenchPreferenceContainer) getContainer())
							.openPage("org.eclipse.wildwebdeveloper.xml.XMLCatalogsPreferencePage", null);
				}
			}
		});

		Link formatOptionsLink = new Link(parent, SWT.NONE);
		formatOptionsLink.setText("See <A>'Formatting'</A> configure formatting preferences");
		formatOptionsLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (getContainer() instanceof IWorkbenchPreferenceContainer) {
					((IWorkbenchPreferenceContainer) getContainer())
							.openPage("org.eclipse.wildwebdeveloper.xml.XMLFormatPreferencePage", null);
				}
			}
		});

		return new Composite(parent, SWT.NONE);
	}

}
