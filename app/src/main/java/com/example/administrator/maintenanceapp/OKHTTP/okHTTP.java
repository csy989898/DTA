package com.example.administrator.maintenanceapp.OKHTTP;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okHTTP {

    private String json;
    private String ip;
    private String portNum;
    private String RegInterface;
    private int msgWhat;
    private Handler handler;

    public okHTTP(String json, String ip , String portNum, String RegInterface, int msgWhat, Handler handler) {
        this.json = json;
        this.ip = ip;
        this.portNum = portNum;
        this.handler=handler;
        this.RegInterface = RegInterface;
        this.msgWhat = msgWhat;
    }
    public void getConnection(){
        OkHttpClient okHttpClient=new OkHttpClient();
        final MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody=RequestBody.create(mediaType, this.json);
        Request request=new Request.Builder()
                .post(requestBody)
                .url("http://"+this.ip+":"+this.portNum+this.RegInterface)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("error", "连接错误");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is= response.body().byteStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                ObjectMapper objectMapper = new ObjectMapper();
                String str="";
                String result="";
                while((str=bufferedReader.readLine())!=null){
                    result+=str;
                }
                Message message = new Message();
                message.what = msgWhat;
                message.obj = result;
                handler.sendMessage(message);
                is.close();
            }
        });
    }
}