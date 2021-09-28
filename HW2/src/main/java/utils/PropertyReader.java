package utils;

import java.io.FileInputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertyReader {

    FileInputStream file;
    protected Properties property;

    @SneakyThrows
    public PropertyReader() {
        property = new Properties();
        file = new FileInputStream("src/test/resources/test.properties");
        property.load(file);
    }

    public Object getKey() {
        return property.get("key");
    }

    public Object getToken() {
        return property.get("token");
    }

    public String getDomain() {
        return property.get("domain").toString();
    }
}
