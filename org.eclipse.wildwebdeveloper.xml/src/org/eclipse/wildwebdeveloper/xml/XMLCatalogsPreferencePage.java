package org.eclipse.wildwebdeveloper.xml;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class XMLCatalogsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private IPreferenceStore store;
	private List catalogsList;
	@Override
	public void init(IWorkbench workbench) {
		store = doGetPreferenceStore();
	}

	@Override
	protected Control createContents(Composite parent) {

		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);
		Label catalogsLabel = new Label(parent, SWT.NONE);
		catalogsLabel.setText(
				"Add catalogs file. Catalogs describe a mapping between external entity references and locally cached equivalents.");

		catalogsList = new List(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		catalogsList.setLayoutData(new GridData(GridData.FILL_BOTH));
		String catalogsStr = store.getString(XMLPreferenceInitializer.XML_PREFERENCES_CATAGLOGS);
		for (String filepath : catalogsStr.split(",")) {
			catalogsList.add(filepath);
		}
		
		Composite buttonsBar = new Composite(parent, SWT.NONE);
		buttonsBar.setLayout(new RowLayout());
		buttonsBar.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		Button addButton = new Button(buttonsBar, SWT.PUSH);
		addButton.setText("Add");
		addButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				InputDialog dialog = new InputDialog(Display.getCurrent().getActiveShell(), "", "New XML Catalog", "", null);
				dialog.create();
				if (dialog.open() == Window.OK) {
					catalogsList.add(dialog.getValue());
				}
			}
		});

 		Button removeButton = new Button(buttonsBar, SWT.PUSH);
		removeButton.setText("Remove");
		removeButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				catalogsList.remove(catalogsList.getSelectionIndex());
			}
		});

		parent.layout();

		return new Composite(parent, SWT.NONE);
	}
	
	@Override
	public boolean performOk() {
		String catalogsStr = "";
		for (String s : catalogsList.getItems()) {
			catalogsStr += s + ",";
		}
		store.setValue(XMLPreferenceInitializer.XML_PREFERENCES_CATAGLOGS, catalogsStr.substring(0, catalogsStr.length() - 1));
		return super.performOk();
	}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

}
