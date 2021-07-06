package com.hcmute.shop.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class CrunchifyGetPropertyValues {
    String result = "";
    InputStream inputStream;

    public String getPropValues(String value,String link) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = link;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            String values = prop.getProperty(value);


            result = values;
            ;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
