package com.wingnest.play2.frames.plugin.neo4j.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.wingnest.play2.frames.plugin.FramesLogger;

import play.Play;
import play.Configuration;
import static com.wingnest.play2.frames.plugin.neo4j.ConfigConsts.*;

final public class ApplicationConfUtils {

	private ApplicationConfUtils() {
	}

	public static File getNeo4jProperties() {
		final String propsFile = getGetProperty(CONF_NEO4J_PROP_FILE, "conf/neo4j.properties").trim();
		return new File(propsFile);
	}

	public static File getNeo4jServerProperties() {
		final String propsFile = getGetProperty(CONF_NEO4J_SERVER_PROP_FILE, "conf/neo4j-server.properties").trim();
		return new File(propsFile);
	}

	public static Properties loadProperties(final File file) {
		final Properties props = new Properties();
		if ( !file.exists() ) {
			FramesLogger.error(" properties file not found : %s", file.getAbsoluteFile());
			return props;
		}
		FramesLogger.info("properties file found : %s", file.getAbsoluteFile());
		try {
			InputStream inStream = new FileInputStream(file);
			props.load(inStream);
			return props;
		} catch ( Exception e ) {
			FramesLogger.error("", e);
			return props;
		}
	}

	public static boolean isEnableWebServer() {
		final String enable = getGetProperty(CONF_NEO4J_ENABLE_SERVER, "false").trim();
		return enable.equalsIgnoreCase("true");
	}

	public static String getGetProperty(final String propName, final String defaultVal) {
		final Configuration c = Play.application().configuration();
		final String val = c.getString(propName);
		return  val == null ? defaultVal : val;
	}
}
