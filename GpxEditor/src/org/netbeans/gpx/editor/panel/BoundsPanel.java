/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BoundsPanel.java
 *
 * Created on 31.03.2012, 21:43:52
 */
package org.netbeans.gpx.editor.panel;

import com.topografix.gpx.model.Bounds;
import com.topografix.gpx.model.Metadata;
import java.math.BigDecimal;
import javax.swing.JComponent;
import org.netbeans.gpx.editor.GpxDataObject;
import org.netbeans.gpx.editor.binding.converter.BigDecimalConverter;
import org.netbeans.modules.xml.multiview.ui.SectionView;

/**
 *
 * @author msc
 */
public class BoundsPanel extends AbstractMetadataPanel {

    private Bounds bounds;
    
    private BigDecimalConverter converter;
    

    /** Creates new form BoundsPanel */
    public BoundsPanel(SectionView view, GpxDataObject gpxDataObject) {
        super(view, gpxDataObject);
        
        converter = new BigDecimalConverter();

        Metadata metaData = getGpx().getMetadata();
        if (metaData != null) {
            this.bounds = metaData.getBounds();
        }
        
        initComponents();
        setValues();
        addModifiers();
    }

    private void setValues() {
        
        if(bounds != null){
            txtMinLat.setText(converter.convertForward(bounds.getMinlat()));
            txtMinLon.setText(converter.convertForward(bounds.getMinlon()));
            txtMaxLat.setText(converter.convertForward(bounds.getMaxlat()));
            txtMaxLon.setText(converter.convertForward(bounds.getMaxlon()));
        }
    }
    
    private void addModifiers() {
        addModifier(txtMinLat);
        addModifier(txtMinLon);
        addModifier(txtMaxLat);
        addModifier(txtMaxLon);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaxLon = new javax.swing.JTextField();
        txtMinLat = new javax.swing.JTextField();
        lblMin = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        lblLon = new javax.swing.JLabel();
        txtMinLon = new javax.swing.JTextField();
        lblLat = new javax.swing.JLabel();
        txtMaxLat = new javax.swing.JTextField();

        lblMin.setText(org.openide.util.NbBundle.getMessage(BoundsPanel.class, "BoundsPanel.lblMin.text")); // NOI18N

        lblMax.setText(org.openide.util.NbBundle.getMessage(BoundsPanel.class, "BoundsPanel.lblMax.text")); // NOI18N

        lblLon.setText(org.openide.util.NbBundle.getMessage(BoundsPanel.class, "BoundsPanel.lblLon.text")); // NOI18N

        lblLat.setText(org.openide.util.NbBundle.getMessage(BoundsPanel.class, "BoundsPanel.lblLat.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblMin)
                    .add(lblMax))
                .add(36, 36, 36)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtMinLat, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .add(txtMaxLat, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .add(lblLat))
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtMinLon, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .add(lblLon)
                    .add(txtMaxLon, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblLat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblLon))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblMin)
                    .add(txtMinLat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtMinLon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblMax)
                    .add(txtMaxLat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtMaxLon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLat;
    private javax.swing.JLabel lblLon;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JTextField txtMaxLat;
    private javax.swing.JTextField txtMaxLon;
    private javax.swing.JTextField txtMinLat;
    private javax.swing.JTextField txtMinLon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setValue(JComponent source, Object value) {
        
        if(bounds == null){
            bounds = new Bounds();
        }
        
        BigDecimal newValue = converter.convertReverse((String)value);
        if(source == txtMaxLat){
            bounds.setMaxlat(newValue);
        }else if(source == txtMaxLon){
            bounds.setMaxlat(newValue);
        }else if(source == txtMinLat){
            bounds.setMinlat(newValue);
        }else if(source == txtMinLon){
            bounds.setMinlon(newValue);
        }
    }

    @Override
    protected void merge() {
        Metadata metadata = checkMetadata();
        metadata.setBounds(bounds);
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
