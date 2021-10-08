package com.selada.kebonmobile;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;


public class KebonApplication extends Application {
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://13.250.108.11:8081");
//            mSocket = IO.socket("http://chat.socket.io");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
