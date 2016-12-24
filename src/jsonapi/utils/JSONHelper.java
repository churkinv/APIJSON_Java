package jsonapi.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class JSONHelper {
    
    
   
   

    public static ArrayList <String> getKyes(JSONObject object) {
     ArrayList <String> result =  new ArrayList<>();
        
    Iterator <?> keys = object.keys ();
    
    while (keys.hasNext()){
        result.add((String)keys.next());
    
    }
    return result;
    }    

    
    
    }
    


