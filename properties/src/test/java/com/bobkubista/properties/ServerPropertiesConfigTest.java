/**
 * Bob Kubista's examples
 */
package com.bobkubista.properties;

import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.junit.Assert;
import org.junit.Test;

import com.bobkubista.properties.ServerPropertiesConfig;

/**
 * @author Bob
 *
 */
public class ServerPropertiesConfigTest {

    /**
     * Test method for
     * {@link ServerPropertiesConfig.example.utils.property.ApacheCommonsConfig#get()}.
     */
    @Test
    public void testGetDefaults() {
        final Configuration properties = ServerPropertiesConfig.INSTANCE.get();
        Assert.assertNotNull(properties);
        Assert.assertNotNull(properties.getKeys());
        Assert.assertEquals("test1", properties.getString("test"));
        Assert.assertNotNull(properties.getString("java.version"));
    }

    @Test
    public void testGetProperties() {
        final Properties properties = ServerPropertiesConfig.INSTANCE.getProperties();
        Assert.assertEquals("test1", properties.getProperty("test"));
        Assert.assertNotNull(properties.getProperty("user.home"));
    }

}
