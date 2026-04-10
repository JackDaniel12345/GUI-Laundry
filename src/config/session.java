/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author jackdaniel
 */
public class session {
  public static int uid;
    public static String name;
    public static String email;
    public static String type;
    public static String Status; 
    public static String password;

    // Add this method so session.getUid() works
    public static int getUid() {
        return uid;
    }
}
    

