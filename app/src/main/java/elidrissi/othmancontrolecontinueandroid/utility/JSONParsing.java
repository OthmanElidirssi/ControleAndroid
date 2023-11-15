package elidrissi.othmancontrolecontinueandroid.utility;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParsing {


    public static <T> T toJavaObject(JSONObject jsonObject, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), classOfT);
    }

    public static <T> List<T> toArrayOfJavaObjects(JSONArray jsonArray, Class<T> classOfT) throws JSONException {
        List<T> objects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            objects.add(toJavaObject(jsonObject, classOfT));
        }
        return objects;
    }
}
