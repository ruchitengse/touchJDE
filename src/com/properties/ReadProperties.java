/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import com.web.servlet.CompileProgram;

import cse6324.uta.edu.ControllerServlet;

/**
 *
 * Properties file to read touchjde.properties
 */
public class ReadProperties {

    private static Properties props = null;
    private static ReadProperties instance = null;
    
    public static ReadProperties getInstance(){
        if(instance == null){
            instance = new ReadProperties();
        }
        return instance;
    }
    
    private ReadProperties(){
    	props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("../conf/touchjde.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ReadProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getValue(String key){
        return props.getProperty(key);
    }
    
    public void setValue(String key, String value){
        props.setProperty(key, value);
    }
}