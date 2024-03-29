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

import java.awt.Component;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.TemplateWizard;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author  Petr Hrebejk
 */
final class TemplateChooserPanel implements WizardDescriptor.Panel<WizardDescriptor>, ChangeListener {

    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private TemplateChooserPanelGUI gui;

    TemplateChooserPanel() {
    }

    public Component getComponent() {
        if (gui == null) {
            gui = new TemplateChooserPanelGUI();
            gui.addChangeListener(this);
        }
        return gui;
    }

    public HelpCtx getHelp() {
        // XXX
        return null;
    }

    public boolean isValid() {
        return gui != null && gui.getTemplate() != null;
    }

    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    public void readSettings(WizardDescriptor settings) {
        TemplateChooserPanelGUI panel = (TemplateChooserPanelGUI) this.getComponent();
        final FileObject currentTemplate = Templates.getTemplate(settings);
        FileObject templates = FileUtil.getConfigFile("Templates");    //NOI18N
        String currentCategoryName = null;
        String currentTemplateName = null;
        if (templates != null && currentTemplate != null && currentTemplate.getParent() != null && templates.equals(currentTemplate.getParent().getParent())) {
            try {                
                final DataObject dobj = DataObject.find(currentTemplate);                
                final DataObject owner = DataObject.find(currentTemplate.getParent());
                currentTemplateName = dobj.getName();
                currentCategoryName = owner.getName();
            } catch (DataObjectNotFoundException e) {
                //Ignore and use default
            }
        }
        panel.readValues( currentCategoryName, currentTemplateName );
        settings.putProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, 0);
        settings.putProperty(WizardDescriptor.PROP_CONTENT_DATA, new String[] {
                NbBundle.getBundle (TemplateChooserPanel.class).getString ("LBL_TemplatesPanel_Name"), // NOI18N
                NbBundle.getBundle (TemplateChooserPanel.class).getString ("LBL_TemplatesPanel_Dots")}); // NOI18N
        // bugfix #44400: wizard title always changes
        settings.putProperty("NewFileWizard_Title", null); // NOI18N
    }

    public void storeSettings(WizardDescriptor wd) {
        Object value = wd.getValue();

        if ( NotifyDescriptor.CANCEL_OPTION != value &&
             NotifyDescriptor.CLOSED_OPTION != value ) {        
            try {
                if (gui.getTemplate() == null) {
                    return;
                }

                if (wd instanceof TemplateWizard) {
                    ((TemplateWizard)wd).setTemplate( DataObject.find( gui.getTemplate() ) );
                } else {
                    wd.putProperty(Templates.WIZARD_KEY_TEMPLATE, gui.getTemplate());
                }
            } catch (DataObjectNotFoundException e) {
                ErrorManager.getDefault().notify(e);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        changeSupport.fireChange();
    }
}    
