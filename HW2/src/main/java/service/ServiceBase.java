package service;

import java.io.FileInputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class ServiceBase {

    FileInputStream file;
    protected Properties property;

    @SneakyThrows
    public ServiceBase() {
        property = new Properties();
        file = new FileInputStream("src/test/resources/test.properties");
        property.load(file);
    }
}
