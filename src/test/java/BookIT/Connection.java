package BookIT;


import utility.ConfigurationReader;
import utility.DB_Utility;



public class Connection  {

    public static void main(String[] args) {

        String url = ConfigurationReader.getProperty("url");
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        DB_Utility.createConnection(url,username,password);
    }
}
