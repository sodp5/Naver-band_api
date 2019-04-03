package com.example.uploadcontentband;

import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TokenManager {
    private static final String RESPONSE_TYPE = "code";
    private static final String CLIENT_ID = "id";
    private static final String CLIENT_SECRET = "secret";
    private static final String REDIRECT_URI = "www.example.com";
    private static final String GRANT_TYPE = "authorization_code";

    private static final String AUTHORIZE_URL = "https://auth.band.us/oauth2/authorize?";
    private static final String TOKEN_URL = "https://auth.band.us/oauth2/token?";

    private String authorizationCode;

    private URLConnection urlConnection;

    public void setAuthorizationCode() {
        urlConnection = new URLConnection(AUTHORIZE_URL + "response_type=" + RESPONSE_TYPE + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI);

        try {
            authorizationCode = urlConnection.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getToken() {
        urlConnection = new URLConnection(TOKEN_URL + "grant_type=" + GRANT_TYPE + "&code=" + authorizationCode);
//        urlConnection = new URLConnection(TOKEN_URL + "grant_type=" + GRANT_TYPE + "&code=" + "ZQAAAUR4OuHjmItrRjo8DWXEzSrv_v9tlY4hAT0Q6R1F8i1WPksGE2iiruhJSp4sz9Jqek3isDt4O4JrNLzSwgRiThFuFI7UezrULo2hZn3l22fA");
        String base64Encode = Base64.encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes(), 0);
        urlConnection.setTokenHeader(base64Encode);

        String token = "FAIL GET TOKEN";


        try {
            token = urlConnection.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return token;
    }
}
