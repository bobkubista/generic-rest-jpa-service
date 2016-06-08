package com.bobkubista.properties;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.function.Supplier;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config wrapper.
 *
 * @author Bob Kubista
 *
 */
public enum ServerPropertiesConfig {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerPropertiesConfig.class);

    private static final String SERVER_PROP_FILE = "server.properties";

    private static Supplier<Configuration> config = () -> ServerPropertiesConfig.getConfig();

    private static synchronized Configuration getConfig() {
        class InternalConfigFactory implements Supplier<Configuration> {

            private final Configuration config = this.create();

            @Override
            public Configuration get() {
                return this.config;
            }

            private Configuration create() {
                // load default properties from classpath

                // loop through default properties
                // check if system properties contains the default properties
                // if not available, then set default property to system
                // property
                // return system properties
                final SystemConfiguration systemConfig = new SystemConfiguration();
                final Configurations configs = new Configurations();
                final Configuration defaultConfig;
                try {
                    final URL serverPropLocation = Thread.currentThread()
                            .getContextClassLoader()
                            .getResource(SERVER_PROP_FILE);
                    defaultConfig = configs.properties(new File(serverPropLocation.toURI()));
                    defaultConfig.getKeys()
                            .forEachRemaining(key -> ServerPropertiesConfig.setDefaults(key, systemConfig, defaultConfig.getProperty(key)));
                } catch (final ConfigurationException | URISyntaxException e) {
                    LOGGER.error("Could not find default properties", e);
                }
                return systemConfig;
            }
        }

        if (!InternalConfigFactory.class.isInstance(config)) {
            config = new InternalConfigFactory();
        }
        return config.get();

    }

    private static void setDefaults(final String key, final SystemConfiguration systemConfig, final Object defaults) {
        LOGGER.debug("Setting defaults");
        if (!systemConfig.containsKey(key)) {
            LOGGER.trace("adding default properties with key {} and value {}", key, defaults);
            systemConfig.addProperty(key, defaults);
        }
    }

    public Configuration get() {
        return ServerPropertiesConfig.config.get();
    }

    public Properties getProperties() {
        final Properties props = new Properties();
        ServerPropertiesConfig.config.get()
                .getKeys()
                .forEachRemaining(key -> props.put(key, ServerPropertiesConfig.config.get()
                        .getProperty(key)));
        return props;
    }
}
