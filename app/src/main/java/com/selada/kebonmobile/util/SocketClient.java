package com.selada.kebonmobile.util;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;

public class SocketClient {
    private static final String TAG = SocketClient.class.getSimpleName();
    private static final String SOCKET_URI = "http://13.250.108.11:8081";
    private static final String SOCKET_PATH = "/your_path";
    private static final String[] TRANSPORTS = {
            "websocket"
    };
    private static io.socket.client.Socket instance;

    /**
     * @return socket instance
     */
    public static io.socket.client.Socket getInstance() {
        if (instance == null) {
            try {
                final IO.Options options = new IO.Options();
//                options.path = SOCKET_PATH;
                options.transports = TRANSPORTS;
                instance = IO.socket(SOCKET_URI, options);
            } catch (final URISyntaxException e) {
                Log.e(TAG, e.toString());
            }
        }
        return instance;
    }
}