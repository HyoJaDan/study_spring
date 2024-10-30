package com.ssafy.ws.step3.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * properties : key-action
 *              value - Controller 객체
 *              map에 저장
 *              
 */

public class HandlerMapping {
	private static Map<String, Controller> controllerMap = new HashMap<>();
	
    public static void mapping(String path) {
    	//map에 저장 : action=Controller
    	Properties props = new Properties();
    	
    	try {
			props.load(new FileInputStream(path));
			Set keys = props.keySet();
			Iterator<String> actions = keys.iterator();
			while(actions.hasNext()) {
				String key = actions.next();
				Controller controller =
					(Controller)Class.forName(props.getProperty(key))
					.getDeclaredConstructor().newInstance();
					;
				controllerMap.put(key, controller);
//				System.out.println(key+" : "+controller);
			}
		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    public static Controller getController(String action) {
    	return controllerMap.get(action);
    }

}
