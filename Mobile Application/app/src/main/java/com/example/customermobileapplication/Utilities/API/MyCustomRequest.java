package com.example.customermobileapplication.Utilities.API;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MyCustomRequest extends Request {

    private Response.Listener listener;

    private Gson gson;

    private Object requestBodyObject;

    private Map<String, String> headers;

    private Class responseClass;

    public MyCustomRequest(int method, String url, Object requestBodyObject, Class responseClass,
                           Map<String, String> headers, Response.Listener listener,
                           Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        // Use the GsonBuilder to state the specific datetime from the API which we will receive.
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        this.listener = listener;
        this.requestBodyObject = requestBodyObject;
        this.headers = headers;
        this.responseClass = responseClass;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return gson.toJson(requestBodyObject).getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));

            return Response.success(gson.fromJson(jsonString, responseClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }
}
