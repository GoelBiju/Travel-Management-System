/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Goel
 */
public class APIConnection {
    
 
    
    public static Object[] GetData(String endPoint, Class tableClass)
    {
        Object[] object = null;
        try 
        {
		URL url = new URL("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api" + "/" + endPoint);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

	} 
        catch (IOException e)
        {
              e.printStackTrace();
        }
        return object;
    }
    
    public static Object getData(String tableName, Class tableClass, Integer id) {
        Object object = new Object();
        
        try {
            URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251C/api/" + tableName + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));           
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }
        
        return object;
    }
    
}

