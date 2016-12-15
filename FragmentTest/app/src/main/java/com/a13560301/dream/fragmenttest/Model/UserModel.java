package com.a13560301.dream.fragmenttest.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dream on 12/13/2016.
 */

public class UserModel {
    private int user_id;
    private String username;
    private String displayname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public  void  setUserModel(String jsonObjstr){

        try {
            JSONObject data = new JSONObject(jsonObjstr);

            this.username = data.get("username").toString();
            this.displayname = data.getString("display_name");
            //this.user_id = Integer.parseInt(data.get("user_id").toString());
            this.user_id = data.getInt("user_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
