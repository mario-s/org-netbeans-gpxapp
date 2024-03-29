package org.netbeans.gpx.editor.view.overall;

import org.netbeans.gpx.model.entity.Link;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 * Action which displays the link edit dialog.
 * @author msc
 */
class LinkEditAction extends AbstractAction {

    public static final String LINK_PROP = "link";

    private Link link;
    
    public LinkEditAction() {
        this(true);
    }
    
    public LinkEditAction(boolean enabled){
        putValue(NAME, NbBundle.getMessage(getClass(), "CTL.EditLink"));
        setEnabled(enabled);
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Link oldValue = new Link(link);
        LinkEditPanel panel = new LinkEditPanel(link);

        DialogDescriptor descriptor = new DialogDescriptor(panel, "Link");
        if (DialogDisplayer.getDefault().notify(descriptor)
            == NotifyDescriptor.OK_OPTION) {
            firePropertyChange(LINK_PROP, oldValue, link);
        }
    }

}
