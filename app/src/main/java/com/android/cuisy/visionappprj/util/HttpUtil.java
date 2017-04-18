package com.android.cuisy.visionappprj.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by cuisy on 2017/4/7.
 */

public class HttpUtil {
    //用户登录login
    public static String HttpLogIn(String url,String servletName,String [] argsName,String ...argsValue){
        String result=null;

        String urlAddr = null;
        urlAddr = url+servletName;
        urlAddr+="?";

        for(int i=0;i<argsName.length;i++){

            urlAddr+=argsName[i];
            urlAddr+="=";
            urlAddr+=argsValue[i];
            if (i <argsName.length -1 )
                urlAddr+="&";
            System.out.println("参数"+argsName[i]+":"+argsValue[i]);
        }

        //HttpPost httppost=new HttpPost("http://192.168.2.201:8001/getdtrees?sessionId=9321&departId=1");
        HttpPost httppost=new HttpPost(urlAddr);
        System.out.println("HttpLogIn_URL = " + urlAddr);

        try{
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpResponse.getEntity(),"GBK");
                System.out.println("HttpLogIn_服务器返回的数据是" + result);
            }
        }catch(Exception e){
            System.out.println("HttpLogIn_catch:" + e.getMessage());
        }
        return result;
    }

    //用户登出logout
    public static String HttpLogOut(String url,String servletName, String sessionId) {
        String result = null;
        String urlAddr = null;
        urlAddr = url + servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;

        System.out.println("LogOut:URL = " + urlAddr);
        HttpPost httppost = new HttpPost(urlAddr);
        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httppost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "GBK");
                System.out.println("HttpLogOut_服务器返回数据：" + result);
            }
        }catch (Exception e) {
            System.out.println("HttpLogOut_catch" + e.getMessage());
        }
        return result;
    }

    //用户登录刷新
    public static String HttpLogInRefresh(String url, String servletName,String sessionId) {
        String result = null;
        String urlAddr = null;
        urlAddr = url + servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;

        System.out.println("loginRefresh:URL = " + urlAddr);
        HttpPost httppost=new HttpPost(urlAddr);
        try {
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            if (httpResponse.getStatusLine().getStatusCode()==200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "GBK");
                System.out.println("HttpLogInRefresh_服务器返回数据：" + result);
            }
        }catch (Exception e) {
            System.out.println("HttpLogInRefresh_catch:" + e.getMessage());
        }
        return result;
    }

    //获取树节点
    public static String HttpGetTreeNode(String url,String servletName,String sessionId){

        String result=null;

        String urlAddr = null;
        urlAddr = url+servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;
        urlAddr+="&departId=1";

        System.out.println("HttpGetTreeNode_URL = " + urlAddr);

        //HttpPost httppost=new HttpPost("http://192.168.2.201:8001/getdtrees?sessionId=9321&departId=1");
        HttpPost httppost=new HttpPost(urlAddr);

        try{
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            System.out.println("try");
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpResponse.getEntity(),"GBK");
                System.out.println("服务器返回的数据是" + result);
            }
        }catch(Exception e){
            System.out.println("catch"+e.getMessage());
        }
        return result;
    }

    //获取摄像头节点
    public static String HttpGetCameraNode(String url,String servletName,String sessionId, String departId){

        String result=null;
        String urlAddr = null;
        urlAddr = url+servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;
        urlAddr+="&departId=";
        urlAddr+=departId;

        System.out.println("HttpGetCameraNode_URL = " + urlAddr);

        HttpPost httppost=new HttpPost(urlAddr);
        try{
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            System.out.println("try");
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpResponse.getEntity(),"GBK");
                System.out.println("HttpGetCameraNode_服务器返回的数据是 " + result);
            }
        }catch(Exception e){
            System.out.println("HttpGetCameraNode_catch" + e.getMessage());
        }
        return result;
    }

    //获取摄像头详细信息
    public static String HttpGetCamerMsg(String url,String servletName,String sessionId, String devId){

        String result=null;
        String urlAddr = null;
        urlAddr = url+servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;
        urlAddr+="&departId";
        urlAddr+=devId;

        System.out.println("HttpGetCameraNode_URL = " + urlAddr);

        HttpPost httppost=new HttpPost(urlAddr);
        try{
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            System.out.println("try");
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpResponse.getEntity(),"GBK");
                System.out.println("HttpGetCameraNode_服务器返回的数据是 " + result);
            }
        }catch(Exception e){
            System.out.println("HttpGetCameraNode_catch" + e.getMessage());
        }
        return result;
    }

    //设备控制
    public static String HttpDevCtrl(String url,String servletName,String sessionId, String cmd){

        String result=null;
        String urlAddr = null;
        urlAddr = url+servletName;
        urlAddr+="?sessionId=";
        urlAddr+=sessionId;
        urlAddr+="&departId";
        urlAddr+=cmd;

        System.out.println("HttpGetCameraNode_URL = " + urlAddr);

        HttpPost httppost=new HttpPost(urlAddr);
        try{
            HttpResponse httpResponse=new DefaultHttpClient().execute(httppost);
            System.out.println("try");
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpResponse.getEntity(),"GBK");
                System.out.println("HttpGetCameraNode_服务器返回的数据是 " + result);
            }
        }catch(Exception e){
            System.out.println("HttpGetCameraNode_catch" + e.getMessage());
        }
        return result;
    }
}
