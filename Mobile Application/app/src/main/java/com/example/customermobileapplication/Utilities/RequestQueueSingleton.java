package com.example.customermobileapplication.Utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class RequestQueueSingleton {

    private static RequestQueueSingleton instance;

    // private ImageLoader imageLoader;

    private RequestQueue requestQueue;

    private static Context ctx;


    private RequestQueueSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        //imageLoader = new ImageLoader(requestQueue,
        //        new ImageLoader.ImageCache() {
        //            private final LruCache<String, Bitmap>
        //                    cache = new LruCache<String, Bitmap>(20);

        //            @Override
        //            public Bitmap getBitmap(String url) {
        //                return cache.get(url);
        //            }

        //            @Override
        //            public void putBitmap(String url, Bitmap bitmap) {
        //                cache.put(url, bitmap);
        //            }
        //        });
    }


    public static synchronized RequestQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueSingleton(context);
        }
        return instance;
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    //public ImageLoader getImageLoader() {
    //    return imageLoader;
    //}
}
