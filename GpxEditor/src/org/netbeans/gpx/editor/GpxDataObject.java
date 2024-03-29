package org.netbeans.gpx.editor;

import org.netbeans.gpx.model.api.Selection;
import org.netbeans.gpx.editor.view.overall.OverallViewDesc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.netbeans.modules.xml.multiview.DesignMultiViewDesc;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataObject;
import org.netbeans.modules.xml.multiview.XmlMultiViewDataSynchronizer;
import org.netbeans.spi.xml.cookies.CheckXMLSupport;
import org.netbeans.spi.xml.cookies.DataObjectAdapters;
import org.netbeans.spi.xml.cookies.ValidateXMLSupport;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.nodes.Children;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.xml.sax.InputSource;

import org.netbeans.gpx.model.entity.Gpx;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.gpx.editor.view.track.TrackViewDesc;
import org.netbeans.gpx.model.ModelConverter;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.lookup.ProxyLookup;

public class GpxDataObject extends XmlMultiViewDataObject {

    private final ModelSynchronizer modelSynchronizer;

    private boolean changedFromUI;

    private Gpx gpx;

    public GpxDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException {
        super(pf, loader);
        modelSynchronizer = new ModelSynchronizer(this);

        CookieSet cookies = getCookieSet();
        InputSource is = DataObjectAdapters.inputSource(this);
        cookies.add((Node.Cookie) new CheckXMLSupport(is));
        cookies.add((Node.Cookie) new ValidateXMLSupport(is));

//        cookies.add((Node.Cookie) DataEditorSupport.create(this, getPrimaryEntry(), cookies));

    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.LEAF);
    }

    @Override
    protected String getPrefixMark() {
        return null;
    }

    @Override
    public Lookup getLookup() {
        return new ProxyLookup(super.getLookup(), Selection.Instance.getLookup());
    }

    @Override
    protected DesignMultiViewDesc[] getMultiViewDesc() {
        return new DesignMultiViewDesc[]{new OverallViewDesc(this),
                    new TrackViewDesc(this)};
    }

    private void parseDocument() throws IOException {
        if (gpx == null) {
            gpx = loadFromFile();
        } else {
            gpx = loadFromEditor();
        }
    }

    public Gpx getGpx() {

        if (gpx == null) {
            try {
                gpx = loadFromFile();
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.WARNING, null, ex);
            }
        }
        return gpx;
    }

    private Gpx loadFromFile() throws IOException {

        File file = FileUtil.toFile(getPrimaryFile());
        return build(new FileInputStream(file));
    }

    private Gpx loadFromEditor() throws IOException {
        InputStream stream = getEditorSupport().getInputStream();
        return build(stream);
    }

    private Gpx build(InputStream stream) throws IOException {

        Gpx model = null;

        try {
            int available = stream.available();
            if (available != 0) {
                model = ModelConverter.readModel(stream);
            }
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, null, ex);
        } 
        return model;
    }

    public void updateModel() {
        modelSynchronizer.requestUpdateData();
    }

    /**
     * class to synchronize model with editor lookupContent
     */
    private class ModelSynchronizer extends XmlMultiViewDataSynchronizer {

        public static final int DELAY = 500;

        public ModelSynchronizer(GpxDataObject dataObject) {
            this(dataObject, DELAY);
        }

        public ModelSynchronizer(GpxDataObject dataObject, int delay) {
            super(dataObject, delay);
        }

        @Override
        protected boolean mayUpdateData(boolean bln) {
            return true;
        }

        @Override
        protected void updateDataFromModel(Object model, FileLock lock, boolean modify) {
            if (model != null) {
                try {
                    OutputStream out = getDataCache().createOutputStream(lock, modify);
                    ModelConverter.writeModel((Gpx) model, out);
                    getDataCache().setData(lock, out.toString(), modify);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        @Override
        protected Object getModel() {

            return getGpx();
        }

        @Override
        protected void reloadModelFromData() {
            try {
                parseDocument();
            } catch (IOException ex) {
            }
        }
    }

    public boolean isChangedFromUI() {
        return changedFromUI;
    }

    public void setChangedFromUI(boolean changedFromUI) {
        this.changedFromUI = changedFromUI;
    }
}
