package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Email;
import com.topografix.gpx.model.Link;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.Person;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.netbeans.gpx.editor.binding.converter.EmailConverter;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
public class AuthorPanel extends AbstractMetadataPanel implements PropertyChangeListener {

    private Person person;
    private EmailConverter emailConverter;
    private AuthorLinkEditAction linkEditAction;

    public AuthorPanel(SectionView sectionView, GpxDataObject gpxDataObject) {
        super(sectionView, gpxDataObject);

        emailConverter = new EmailConverter();

        Metadata metadata = getMetadata();
        if (metadata != null) {
            person = metadata.getAuthor();
        } 
        
        if(person == null){
            person = new Person();
        }

        initComponents();
        setValues();
        setLinkEditAction();
        addModifiers();
    }

    private void setValues() {

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

    private void setLinkEditAction() {
        linkEditAction = new AuthorLinkEditAction();
        linkEditAction.addPropertyChangeListener(this);
        btnEditLink.setAction(linkEditAction);
    }

    private void addModifiers() {
        addModifier(txtAuthorName);
        addModifier(txtAuthorEmail);
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(lblLinkValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(btnEditLink))
                    .add(txtAuthorEmail)
                    .add(txtAuthorName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                .add(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblAuthorName)
                    .add(txtAuthorName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblAuthorEmail)
                    .add(txtAuthorEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(btnEditLink, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, lblLinkValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, lblAuthorLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20))))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        if (person.hasContent()) {
            metadata.setAuthor(person);
        } else {
            metadata.setAuthor(null);
        }
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

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        Link link = (Link) event.getNewValue();
        if (link.hasContent()) {
            person.setLink(link);
            setValues();
        }
    }

    /**
     * specialized action for author's link
     */
    private class AuthorLinkEditAction extends LinkEditAction {

        public AuthorLinkEditAction() {

            Link link = person.getLink();
            if (link != null) {
                setLink(link);
            } else {
                setLink(new Link());
            }
        }
    }
}
