package org.netbeans.gpx.editor.view;

import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ToolBarMultiViewElement;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.netbeans.modules.xml.multiview.ui.ToolBarDesignEditor;

/**
 * Base class for all toolbar elements.
 * @author msc
 */
public abstract class AbstractToolBarElement extends ToolBarMultiViewElement {

    protected GpxDataObject gpxDataObject;
    protected ToolBarDesignEditor toolbarEditor;
    private AbstractGpxView view;

    public AbstractToolBarElement(GpxDataObject gpxDataObject) {
        super(gpxDataObject);
        this.gpxDataObject = gpxDataObject;

        toolbarEditor = new ToolBarDesignEditor();
        setVisualEditor(toolbarEditor);
        
    }

    @Override
    public SectionView getSectionView() {
        return view;
    }

    @Override
    public void componentShowing() {
        super.componentShowing();

        gpxDataObject.setChangedFromUI(false);
        gpxDataObject.updateModel();

        view = createView();
        toolbarEditor.setContentView(view);

        view.open(gpxDataObject, toolbarEditor.getLastActive());
    }

    protected abstract AbstractGpxView createView();
    
}
