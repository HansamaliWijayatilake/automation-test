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

    @DataProvider(name = "addPost")
    public Object[][] createPostTestData() {

        return new Object[][] {
                {"Test Post 1","Test content added from Post call", 1}
        };
    }

    @DataProvider(name = "updatePost")
    public Object[][] createPutTestData() {

        return new Object[][] {
                {100,"Test Post Updated","Test content Updated", 1}
        };
    }

    @DataProvider(name="userId")
    public Object[][] getUserId(){
        return new Object[][]
                {
                        {1}
                };

    }

    @DataProvider(name = "updateInvalid")
    public Object[][] createInvalidPutTestData() {

        return new Object[][] {
                {200,"Test Post Updated","Test content Updated", 1}
        };
    }

    @DataProvider(name="postId")
    public Object[][] getPostId(){
        return new Object[][]
                {
                        {2}
                };

    }

    @DataProvider(name="invalidUserId")
    public Object[][] getInvalidUserId(){
        return new Object[][]
                {
                        {55}
                };

    }

    @DataProvider(name = "addUser")
    public Object[][] addUserTestData() {
        return new Object[][] {
                {"Alex Perera","APerera", "aperera@gmail.com","A Street","B Suite","C city","00500","-37.3159",
                        "81.1496", "0703342299","hildegard.org","Hildegard Asia",
                        "Multi-layered client-server", "edPub"}
        };
    }
}
