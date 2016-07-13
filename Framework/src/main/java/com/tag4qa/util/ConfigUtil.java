package com.tag4qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * ConfigUtil is a Singleton Class where all configuration properties are
 * retrieved and returned
 * 
 * @author mperumal
 * 
 */
public class ConfigUtil {
	private static final ConfigUtil INSTANCE = new ConfigUtil();
	private final Properties ENV_CONFIG;
	private final String TEST_RESOURCE_DIR = "src/main/resources/com/tag4qa/config/";

	private ConfigUtil() {
		ENV_CONFIG = System.getProperties();

		/* Adding TEST ENVIRONMENT PROPERTIES files to Config */
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(TEST_RESOURCE_DIR + "env.properties"));
			ENV_CONFIG.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ConfigUtil instance() {
		return INSTANCE;
	}

	public static Properties envConfig() {
		return INSTANCE.ENV_CONFIG;
	}
}
