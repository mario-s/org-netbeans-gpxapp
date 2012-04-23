/*
 * $$LastChangedRevision: 1 $$
 * Created on 06.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Gpx;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.openide.util.NbBundle;
import org.netbeans.modules.xml.multiview.Error;

/**
 *
 * @author msc
 */
public class GpxBasicPanel extends AbstractInnerPanel {

    private Gpx gpx;

    public GpxBasicPanel(SectionView sectionView, GpxDataObject dataObject) {
        super(sectionView, dataObject);
        this.gpx = getGpx();
        initComponents();

        setCreatorInText();
        addModifier(txtCreator);
    }

    @Override
    public void setValue(JComponent source, Object value) {
        if (source == txtCreator) {
            gpx.setCreator((String) value);
        }
    }

    @Override
    public void linkButtonPressed(Object o, String string) {
    }

    @Override
    public JComponent getErrorComponent(String errorId) {
        JComponent comp = null;
        if (errorId.equals("creator")) {
            comp = txtCreator;
        }
        return comp;
    }

    @Override
    public void rollbackValue(JTextComponent source) {
        if (source == txtCreator) {
            setCreatorInText();
        }
    }

    private void setCreatorInText() {
        txtCreator.setText(gpx.getCreator());
    }

    @Override
    public void documentChanged(JTextComponent source, String value) {
        if (source == txtCreator) {
            if (value.length() == 0) {
                getSectionView().getErrorPanel().setError(new Error(Error.MISSING_VALUE_MESSAGE, "title", source));
                return;
            }
            getSectionView().getErrorPanel().clearError();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new BindingGroup();

        lblCreator = new JLabel();
        lblVersion = new JLabel();
        txtCreator = new JTextField();
        lblVersionValue = new JLabel();

        lblCreator.setText(NbBundle.getMessage(GpxBasicPanel.class, "GpxBasicPanel.lblCreator.text")); // NOI18N

        lblVersion.setText(NbBundle.getMessage(GpxBasicPanel.class, "GpxBasicPanel.lblVersion.text")); // NOI18N

        Binding binding = Bindings.createAutoBinding(UpdateStrategy.READ_ONCE, this, ELProperty.create("${model.version}"), lblVersionValue, BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVersion)
                        .addGap(18, 18, 18)
                        .addComponent(lblVersionValue))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCreator)
                        .addGap(18, 18, 18)
                        .addComponent(txtCreator, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblVersion)
                    .addComponent(lblVersionValue))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblCreator)
                    .addComponent(txtCreator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCreator.getAccessibleContext().setAccessibleName(NbBundle.getMessage(GpxBasicPanel.class, "GpxPanel.jLabel1.AccessibleContext.accessibleName")); // NOI18N
        lblVersion.getAccessibleContext().setAccessibleName(NbBundle.getMessage(GpxBasicPanel.class, "GpxPanel.jLabel2.AccessibleContext.accessibleName")); // NOI18N

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lblCreator;
    private JLabel lblVersion;
    private JLabel lblVersionValue;
    private JTextField txtCreator;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public Gpx getModel() {
        return gpx;
    }
}