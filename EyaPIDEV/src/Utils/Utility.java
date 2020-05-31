/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hiro
 */
public class Utility {

    private static ConnectionRequest CurrentConnection;
    private static JSONParser CurrentJsonParser;
    private static String ResponseData;
    
    
    private Utility() {
    }

    public static ConnectionRequest GetConnection(){
        if (CurrentConnection == null)
        {
            CurrentConnection = new ConnectionRequest();
            return  CurrentConnection;
        }
        else return CurrentConnection;
    }
    
    public static JSONParser GetJsonParser(){
        if (CurrentJsonParser == null)
        {
            CurrentJsonParser = new JSONParser(); 
            return  CurrentJsonParser;
        }
        else return CurrentJsonParser;
    }
    
    public static String GetResponseData(){
        return ResponseData;
    }
    
    public static void MakeConnection(String Route, UtilityAction Action){
        ConnectionRequest ConnectionTemp = new ConnectionRequest();
        CurrentConnection = ConnectionTemp;
        if (ConnectionTemp == null) System.out.println("<Utility::MakeConnection> Connection could not be set" );
        else System.out.println("<Utility::MakeConnection> Connection has been set" );
        
        
        ConnectionTemp.setUrl(Route);
        System.out.println("<Utility::MakeConnection> Called the routing [ " + Route + " ]" );
	ConnectionTemp.addResponseListener((e) -> {
            System.out.println("<Utility::MakeConnection> Has detected a Response from the routing" );
            ResponseData = new String(ConnectionTemp.getResponseData());
            System.out.println("<Utility::MakeConnection> Found Response Data [" + ResponseData + "]" );
            Action.ActionToMake();
        });
        NetworkManager.getInstance().addToQueueAndWait(ConnectionTemp);
    }
    
    public static List<Map<String, Object>> MakeListFromJSON(){
        System.out.println("<Utility::MakeListFromJSON> Setting up for parsing");
        List<Map<String, Object>> CurrentList = new ArrayList<>();
        CurrentJsonParser = GetJsonParser();
        try{
        System.out.println("<Utility::MakeListFromJSON> Currently Parsing");
        Map<String, Object> G1 = CurrentJsonParser.parseJSON(new CharArrayReader(ResponseData.toCharArray()));
        CurrentList = (List<Map<String, Object>>) G1.get("root");
        } catch (IOException ex) {
            System.out.println("<Utility::MakeListJSON> Error has occured");
        }
        return CurrentList;
    }
}
