package elidrissi.othmancontrolecontinueandroid.utility;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONFactory {

    public  static  <T> JSONObject getJSONObject(T object) throws JSONException {
        Gson gson=new Gson();
        return new JSONObject(gson.toJson(object));
    }

}
