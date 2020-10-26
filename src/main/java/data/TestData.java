package data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name="DeviceList")
    public Object[][] getDeviceList(){
        return new Object[][]
                {
                        { "Mobiles"},
                        { "Tablets"},
                        { "Laptops"},
                        {"Desktops Computers"},
                        {"Gaming Consoles"},
                        {"Action/Video Cameras"},
                        {"Security Cameras"},
                        {"Digital Cameras"},
                        {"Gadgets & Drones"}
                };

    }

    @DataProvider(name="childDeviceList")
    public Object[][] getChildDeviceList(){
        return new Object[][]
                {
                        { "Laptops & Notebooks"},
                        { "Gaming Laptops"},
                        { "2-in-1s"}
                };

    }
}
