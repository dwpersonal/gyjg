package com.zcreate.offline.gyjg.redistohive.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类
 */
public class PropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;

    static {
        loadProps("common-config.properties");
    }

    public static String getProperty(String key) {
        if (null == props) {
            throw new NullPointerException("props is null");
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            throw new NullPointerException("props is null");
        }
        return props.getProperty(key, defaultValue);
    }

    private Properties getProps(String path) {
        if (props == null) {
            loadProps(path);
        }
        return props;
    }

    private synchronized static void loadProps(String path) {

        props = new Properties();
        InputStream in = null;

        try {
            path = path;
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(path);
            props.load(in);

        } catch (FileNotFoundException e) {
            logger.error("loadProps error", e);

        } catch (IOException e) {
            logger.error("loadProps error", e);

        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }
}