package org.netbeans.gpx.binding.converter;

import org.netbeans.gpx.editor.binding.converter.EmailConverter;
import org.netbeans.gpx.model.entity.Email;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class EmailConverterTest {
    
    private static final String ID = "me";
    private static final String DOMAIN = "web.de";
    private static final String ADDR = ID + "@" + DOMAIN;


    /**
     * Test of convertForward method, of class EMailConverter.
     */
    @Test
    public void testConvertForward() {
        
        Email value = new Email();
        value.setId(ID);
        value.setDomain(DOMAIN);
        EmailConverter instance = new EmailConverter();
        String result = instance.convertForward(value);
        assertEquals(ADDR, result);
    }

    /**
     * Test of convertReverse method, of class EMailConverter.
     */
    @Test
    public void testConvertReverse() {

        EmailConverter instance = new EmailConverter();
        Email result = instance.convertReverse(ADDR);
        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals(DOMAIN, result.getDomain());
    }
}
