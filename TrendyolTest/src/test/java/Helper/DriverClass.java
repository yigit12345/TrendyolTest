package Helper;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverClass {

    static String username = "Input account";
    static String authkey = "Input Key";
    public RemoteWebDriver remoteWebDriver;

    public RemoteWebDriver setRemoteWebDriver(String name, String build, String browserName, String version, String platform, String screenResolution, String record_video, String record_network) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("name", name);
        caps.setCapability("build", build);
        caps.setCapability("browserName", browserName);
        caps.setCapability("version", version);
        caps.setCapability("platform", platform);
        caps.setCapability("screenResolution", screenResolution);
        caps.setCapability("record_video", record_video);
        caps.setCapability("record_network", record_network);

        remoteWebDriver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + "Input authley"), caps);
        System.out.println(remoteWebDriver.getSessionId());
        return remoteWebDriver;
    }
}
