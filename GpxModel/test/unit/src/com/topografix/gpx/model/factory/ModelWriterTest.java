/*
 * $$LastChangedRevision: 1 $$
 * Created on 12.03.2012 by msc
 * $$LastChangedBy: msc $$ 
 * 
 */
package com.topografix.gpx.model.factory;

import com.topografix.gpx.model.Gpx;
import com.topografix.gpx.model.Metadata;
import com.topografix.gpx.model.Track;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msc
 */
public class ModelWriterTest {

    private ModelWriter classUnderTest;
    
    private final Logger logger;

    public ModelWriterTest() {
        logger = Logger.getLogger(getClass().getName());
    }
    

    @Before
    public void setUp() throws JAXBException {
        
        String creator = "TEST";
        String trackName = "a track";

        Gpx model = new Gpx();
        model.setCreator(creator);
        Metadata metadata = new Metadata();
        model.setMetadata(metadata);

        Track track = new Track();
        track.setName(trackName);
        model.getTracks().add(track);

        classUnderTest = new ModelWriter(model);
    }

    @Test
    public void testWrite() throws JAXBException {
        OutputStream stream = new ByteArrayOutputStream();
        classUnderTest.write(stream);
        String str = stream.toString();
        
        logger.log(Level.INFO, str);
        
        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains("\n"));
        assertTrue(str.contains("http://www.topografix.com/GPX/1/1/gpx.xsd"));
    }

}
