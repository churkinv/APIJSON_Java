package jsonapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author san
 */
public class NetworkUtils {

    public static String get(String address) {
        
        StringBuffer stringBuffer = new StringBuffer();
        
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            int requestCode = connection.getResponseCode();
            
            System.out.println("request - "+requestCode);
            if(requestCode!=200) return  "Bad reauest";
            
            
            InputStreamReader is = new InputStreamReader (connection.getInputStream());
            BufferedReader in = new BufferedReader(is);
            
            String line = null;
            while ((line = in.readLine())!=null) stringBuffer.append(line);
            
        } 
        catch (MalformedURLException e) {
            e.printStackTrace();
               }
        catch (IOException e){
        e.printStackTrace();
        }
        return stringBuffer.toString();
    }
        
    
}
