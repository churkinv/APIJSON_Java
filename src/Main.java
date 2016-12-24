
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsonapi.utils.NetworkUtils;
import jsonapi.utils.JSONHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonny
 */
public class Main {

    private static final String TITLE_KEY = "title";
    private static final String PHONE_KEY = "phone";
    private static final String ADDRESS_KEY = "address";
    private static final String CURRENCY_KEY = "currency";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String jsonResult;
        
        
            jsonResult = NetworkUtils.get("http://resources.finance.ua/ru/public/currency-cash.json");
            System.out.println("result is = "+ jsonResult);
           
       
        try {
            JSONObject object = new JSONObject (jsonResult);
            System.out.println("keys - " + JSONHelper.getKyes(object));
            
            ArrayList<String> keys = JSONHelper.getKyes(object);
            System.out.println("keys - "+ keys);
            JSONObject currencyObject = object.getJSONObject(keys.get(6));
            
            System.out.println("currency keys - " + JSONHelper.getKyes(currencyObject));
            JSONArray organizations = object.getJSONArray(keys.get(5));
            System.out.println(organizations.get(0));   
            
            HashMap <String,Object> resultMap = new HashMap<>();
            double minCurrency = Double.parseDouble(((JSONObject) organizations.get (0))
                    .getJSONObject ("currencies")
                    .getJSONObject ("EUR")
                    .getString("bid"));
            
           // resultMap =  JSONHelper.createMapFromJSON(((JSONObject)organizations.get(0)));
            
            
            resultMap.put(TITLE_KEY,((JSONObject)organizations.get(0)).getString("title"));
            resultMap.put (PHONE_KEY,((JSONObject)organizations.get(0)).getString("phone"));
            resultMap.put (ADDRESS_KEY,((JSONObject)organizations.get(0)).getString("address"));
            resultMap.put(CURRENCY_KEY,((JSONObject)organizations.get(0))
                    .getJSONObject ("currencies")
                    .getJSONObject ("EUR")
                    .getString("bid"));
            
            System.out.println("start min" + minCurrency);
            
            for (int i = 0; i<organizations.length(); i++){
                JSONObject org = (JSONObject)organizations.get(i);
               
                try{
                System.out.println(org.getString("title"));
                JSONObject curr = org.getJSONObject("currencies");
                System.out.println("EUR - "+curr.getString("EUR"));
                System.out.println("USD - "+curr.getString("USD"));
                
                String bidUSDCurrency = curr.getJSONObject("USD").getString("bid");
                if (Double.parseDouble(bidUSDCurrency)< minCurrency){
                resultMap.put(TITLE_KEY,org.getString("title"));
                resultMap.put (PHONE_KEY,org.getString("phone"));
                resultMap.put (ADDRESS_KEY,org.getString("address"));
                resultMap.put(CURRENCY_KEY,bidUSDCurrency);
                minCurrency = Double.parseDouble (bidUSDCurrency);
                }
                
                System.out.println("-----------------------------");
                }
                catch (JSONException ex) {
                    System.out.println(org.get("title") + "error!!");
                }
            }
            System.out.println("min currency " +resultMap.toString()) ;
        
        }
                      
         catch (JSONException ex) {
                    }
        
        
    }
    
}
