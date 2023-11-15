package elidrissi.othmancontrolecontinueandroid.utility;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiCalls {

    public static void makeJsonArrayRequest(String url, int httpMethod, RequestQueue requestQueue, JsonArrayResponseHandler responseHandler) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(httpMethod, url, null, response -> {
            responseHandler.onSuccess(response);
        }, error -> {
            responseHandler.onError(error);
        });

        requestQueue.add(jsonArrayRequest);
    }

    public static void makeJsonObjectRequest(String url, int httpMethod, JSONObject input, RequestQueue requestQueue, JsonObjectResponseHandler responseHandler) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, url, input, response -> {
            responseHandler.onSuccess(response);
        }, error -> {
            responseHandler.onError(error);
        });

        requestQueue.add(jsonObjectRequest);
    }

    public interface JsonArrayResponseHandler {
        void onSuccess(JSONArray response);

        void onError(VolleyError error);
    }

    public interface JsonObjectResponseHandler {
        void onSuccess(JSONObject response);

        void onError(VolleyError error);
    }
}
