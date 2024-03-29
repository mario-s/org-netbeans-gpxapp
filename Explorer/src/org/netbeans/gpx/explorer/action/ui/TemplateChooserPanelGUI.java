/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or sbbuild/licenses/cddl-gpl-2-cp.txt. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * sbbuild/licenses/cddl-gpl-2-cp.txt.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 2004-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */


package org.netbeans.gpx.explorer.action.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeListener;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataShadow;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.AsyncGUIJob;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/** If you are looking for the non-GUI part of the panel please look
 * into new file wizard
 */

/**
 * Provides the GUI for the template chooser panel.
 * @author Jesse Glick
 */
final class TemplateChooserPanelGUI extends javax.swing.JPanel implements PropertyChangeListener, AsyncGUIJob {
    
    /** prefered dimmension of the panels */
    private static final java.awt.Dimension PREF_DIM = new java.awt.Dimension (500, 340);
    
    private final ChangeSupport changeSupport = new ChangeSupport(this);

    //Templates folder root
    private FileObject templatesFolder;

    //GUI Builder
    private TemplatesPanelGUI.Builder builder;
    private String category;
    private String template;
    private boolean isWarmUp = true;
    private ListCellRenderer projectCellRenderer;
    
    public TemplateChooserPanelGUI() {
        this.builder = new FileChooserBuilder ();
        initComponents();
        setPreferredSize( PREF_DIM );
        setName (org.openide.util.NbBundle.getMessage(TemplateChooserPanelGUI.class, "LBL_TemplateChooserPanelGUI_Name")); // NOI18N
        Utilities.attachInitJob (this, this);
     }


    public void readValues (String category, String template) {
        boolean wf;
        synchronized (this) {
            this.category = category;
            this.template = template;
            wf = this.isWarmUp;
        }
        if (!wf) {
            ((TemplatesPanelGUI)this.templatesPanel).setSelectedCategoryByName (this.category);
            ((TemplatesPanelGUI)this.templatesPanel).setSelectedTemplateByName (this.template);
        }
    }

    /** Called from readSettings, to initialize the GUI with proper components
     */
    private void initValues( ) {
    }



    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }
    
    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }
    
    private void fireChange() {
        changeSupport.fireChange();
    }
    
    public FileObject getTemplate() {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedTemplate ();
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        fireChange();
    }
    
    
    public java.awt.Dimension getPreferredSize() {
        return PREF_DIM;
    }
    
    public String getCategoryName () {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedCategoryName ();
    }

    public String getTemplateName () {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedTemplateName ();
    }

    public void setCategory (String category) {
        ((TemplatesPanelGUI)this.templatesPanel).setSelectedCategoryByName (category);
    }
    
//    public void addNotify () {
//        super.addNotify ();
//        if (projectCellRenderer == null) {
//            projectCellRenderer = new ProjectCellRenderer ();
//        }
//        projectsComboBox.setRenderer (projectCellRenderer);
//    }
    
//    public void removeNotify () {
//        super.removeNotify ();
//        projectCellRenderer = null;
//        projectsComboBox.setRenderer (null);
//    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        templatesPanel = new TemplatesPanelGUI (this.builder);

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(templatesPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel templatesPanel;
    // End of variables declaration//GEN-END:variables
    
    
    private final class TemplateChildren extends Children.Keys<DataFolder> implements ActionListener {
        
        private final DataFolder folder;
        
        TemplateChildren(DataFolder folder) {
            this.folder = folder;
        }
        
        protected void addNotify() {
            super.addNotify();
//            projectsComboBox.addActionListener( this );
            updateKeys();
        }
        
        protected void removeNotify() {
            setKeys(Collections.<DataFolder>emptySet());
//            projectsComboBox.removeActionListener( this );
            super.removeNotify();
        }
        
        private void updateKeys() {
            List<DataFolder> l = new ArrayList<DataFolder>();
            for (DataObject d : folder.getChildren()) {
                FileObject prim = d.getPrimaryFile();
                if ( acceptTemplate( d, prim ) ) {
                    // has children?
                    if (hasChildren (/*(Project)projectsComboBox.getSelectedItem (),*/ d)) {
                        l.add((DataFolder) d);
                    }
                }
            }
            setKeys(l);
        }
        
        protected Node[] createNodes(DataFolder d) {
            boolean haveChildren = false;
            for (DataObject child : d.getChildren()) {
                if ((child instanceof DataFolder) && !isTemplate(child)) {
                    haveChildren = true;
                    break;
                }
            }
            if (!haveChildren) {
                return new Node[] {new FilterNode(d.getNodeDelegate(), Children.LEAF )};
            } else {
                return new Node[] {new FilterNode(d.getNodeDelegate(), new TemplateChildren(d))};
            }
        }
        
        public void actionPerformed (ActionEvent event) {
            final String cat = getCategoryName ();
            String template =  ((TemplatesPanelGUI)TemplateChooserPanelGUI.this.templatesPanel).getSelectedTemplateName();
            this.setKeys(Collections.<DataFolder>emptySet());
            this.updateKeys ();
            setCategory (cat);
            ((TemplatesPanelGUI)TemplateChooserPanelGUI.this.templatesPanel).setSelectedTemplateByName(template);
        }
                
        
        /** Uncoment if you want to have the templates sorted alphabeticaly
         
        // Comparator ---------------------------------------------------------- 
         
        public int compare(Object o1, Object o2) {
            DataObject d1 = (DataObject)o1;
            DataObject d2 = (DataObject)o2;
            if ((d1 instanceof DataFolder) && !(d2 instanceof DataFolder)) {
                return 1;
            } else if (!(d1 instanceof DataFolder) && (d2 instanceof DataFolder)) {
                return -1;
            } else {
                return NATURAL_NAME_SORT.compare(d1.getNodeDelegate().getDisplayName(), d2.getNodeDelegate().getDisplayName());
            }
        }
        */

        // Private methods -----------------------------------------------------
        
        private boolean acceptTemplate( DataObject d, FileObject primaryFile ) {            
            if (d instanceof DataFolder && !isTemplate((DataFolder)d))  {
                Object o = primaryFile.getAttribute ("simple"); // NOI18N
                return o == null || Boolean.TRUE.equals (o);
            }
            return false;
        }
        
    }
    
    
    private final class FileChildren extends Children.Keys<DataObject> {
        
        private DataFolder root;
                
        public FileChildren (DataFolder folder) {
            this.root = folder;
            assert this.root != null : "Root can not be null";  //NOI18N
        }
        
        protected void addNotify () {
            this.setKeys (this.root.getChildren());
        }
        
        protected void removeNotify () {
            this.setKeys (new DataObject[0]);
        }
        
        protected Node[] createNodes(DataObject dobj) {
            if (isTemplate(dobj) /*&& OpenProjectList.isRecommended (getProject (), dobj.getPrimaryFile ())*/) {
                    if (dobj instanceof DataShadow) {
                        dobj = ((DataShadow)dobj).getOriginal();
                    }
                    return new Node[] { new FilterNode(dobj.getNodeDelegate(), Children.LEAF) };
            }
            return new Node[0];
        }        

    }
    
  
    private final class FileChooserBuilder implements TemplatesPanelGUI.Builder {
        
        public Children createCategoriesChildren(DataFolder folder) {
            return new TemplateChildren (folder);
        }
        
        public Children createTemplatesChildren(DataFolder folder) {
            return new FileChildren (folder);
        }
        
        public void fireChange() {
            TemplateChooserPanelGUI.this.fireChange();
        }
        
        public String getCategoriesName() {
            return NbBundle.getMessage (TemplateChooserPanelGUI.class,"CTL_Categories");
        }
        
        public String getTemplatesName() {
            return NbBundle.getMessage (TemplateChooserPanelGUI.class,"CTL_Files");
        }  

    }
    
//    private static class ProjectCellRenderer extends JLabel implements ListCellRenderer  {
//        
//        
//        public ProjectCellRenderer() {
//            setOpaque(true);
//        }
//        
//        public Component getListCellRendererComponent(
//                JList list,
//                Object value,
//                int index,
//                boolean isSelected,
//                boolean cellHasFocus) {
//            setText( value == null ? "" : value.toString() ); // NOI18N
//            setIcon( null );
//            if ( isSelected ) {
//                setBackground(list.getSelectionBackground());
//                setForeground(list.getSelectionForeground());
//            } else {
//                setBackground(list.getBackground());
//                setForeground(list.getForeground());
//                
//            }
//            return this;
//        }
//        
//    }
    
    private static boolean isTemplate (DataObject dobj) {
        if (dobj.isTemplate())
            return true;
        if (dobj instanceof DataShadow) {
            return ((DataShadow)dobj).getOriginal().isTemplate();
        }
        return false;
    }
    
    private boolean hasChildren (/*Project p,*/ DataObject folder) { 
        if (!(folder instanceof DataFolder)) {
            return false;
        }
        
        DataFolder f = (DataFolder) folder;
        DataObject[] ch = f.getChildren ();
        boolean ok = false;
        for (int i = 0; i < ch.length; i++) {
            if (isTemplate (ch[i])/* && OpenProjectList.isRecommended(p, ch[i].getPrimaryFile ())*/) {
                // XXX: how to filter link to Package template in each java types folder?
                if (!(ch[i] instanceof DataShadow)) {
                    ok = true;
                    break;
                }
            }
        }
        return ok;
    }
    
    public void construct () {
        this.templatesFolder = FileUtil.getConfigFile("Templates"); // NOI18N
        ((TemplatesPanelGUI)this.templatesPanel).warmUp(this.templatesFolder);
    }
    
    public void finished () {
        //In the awt
        Cursor cursor = null;
        try {
            String c,t;
            synchronized (this) {
                c = this.category;
                t = this.template;
            }
            cursor = TemplateChooserPanelGUI.this.getCursor();
            TemplateChooserPanelGUI.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            initValues( /*p*/ );
            ((TemplatesPanelGUI)this.templatesPanel).doFinished (this.templatesFolder, c, t);
        } finally {
            synchronized (this) {
                isWarmUp = false;
            }
            if (cursor != null) {
                this.setCursor (cursor);
            }
        }
    }
    
}
