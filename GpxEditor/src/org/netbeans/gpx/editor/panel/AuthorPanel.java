package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Email;
import com.topografix.gpx.model.Link;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.Person;
import javax.swing.JComponent;
import org.netbeans.gpx.editor.binding.converter.EmailConverter;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionView;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author msc
 */
public class AuthorPanel extends AbstractMetadataPanel {

    private Person person;
    private EmailConverter emailConverter;

    public AuthorPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView, gpxDataObject);

        emailConverter = new EmailConverter();

        Metadata metadata = getMetadata();
        if (metadata != null) {
            this.person = metadata.getAuthor();
        }

        initComponents();
        setValues();
        addModifiers();
    }

    private void addModifiers() {
        addModifier(txtAuthorName);
        addModifier(txtAuthorEmail);
    }

    private void checkPerson() {
        if (person == null) {
            person = new Person();
        }
    }

    private void setValues() {

        if (person != null) {
            txtAuthorName.setText(person.getName());
            Email email = person.getEmail();
            if (email != null) {
                txtAuthorEmail.setText(emailConverter.convertForward(email));
            }
            Link link = person.getLink();
            if (link != null) {
                lblLinkValue.setText(link.getText());
                lblLinkValue.setToolTipText(link.getHref());
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAuthorName = new javax.swing.JTextField();
        txtAuthorEmail = new javax.swing.JTextField();
        lblAuthorEmail = new javax.swing.JLabel();
        lblAuthorName = new javax.swing.JLabel();
        lblAuthorLink = new javax.swing.JLabel();
        lblLinkValue = new javax.swing.JLabel();
        btnEditLink = new javax.swing.JButton();

        lblAuthorEmail.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorEmail.text")); // NOI18N

        lblAuthorName.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorName.text")); // NOI18N

        lblAuthorLink.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblAuthorLink.text")); // NOI18N

        lblLinkValue.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.lblLinkValue.text")); // NOI18N

        btnEditLink.setText(org.openide.util.NbBundle.getMessage(AuthorPanel.class, "AuthorPanel.btnEditLink.text")); // NOI18N
        btnEditLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLinkActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblAuthorName)
                    .add(lblAuthorEmail)
                    .add(lblAuthorLink))
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(lblLinkValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(btnEditLink))
                    .add(txtAuthorEmail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .add(txtAuthorName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .add(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblAuthorName)
                    .add(txtAuthorName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblAuthorEmail)
                    .add(txtAuthorEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblLinkValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btnEditLink, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(lblAuthorLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLinkActionPerformed

        checkPerson();

        Link copy;
        Link link = person.getLink();
        if (link == null) {
            copy = new Link();
        }else{
            copy = new Link(link);
        }

        LinkEditPanel panel = new LinkEditPanel(copy);
        DialogDescriptor descriptor = new DialogDescriptor(panel, "Link");
        if (DialogDisplayer.getDefault().notify(descriptor) == NotifyDescriptor.OK_OPTION
                && link.hasContent()) {
            person.setLink(copy);
            setValues();
        }
    }//GEN-LAST:event_btnEditLinkActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditLink;
    private javax.swing.JLabel lblAuthorEmail;
    private javax.swing.JLabel lblAuthorLink;
    private javax.swing.JLabel lblAuthorName;
    private javax.swing.JLabel lblLinkValue;
    private javax.swing.JTextField txtAuthorEmail;
    private javax.swing.JTextField txtAuthorName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setValue(JComponent source, Object value) {
        checkPerson();

        if (source == txtAuthorName) {
            person.setName((String) value);
        } else if (source == txtAuthorEmail) {
            Email email = emailConverter.convertReverse((String) value);
            person.setEmail(email);
        }
    }

    @Override
    protected void merge() {
        Metadata metadata = checkMetadata();
        metadata.setAuthor(person);
        super.merge();
    }

    @Override
    public void linkButtonPressed(Object ddBean, String ddProperty) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JComponent getErrorComponent(String errorId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
