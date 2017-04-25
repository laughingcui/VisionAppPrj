package com.android.cuisy.visionappprj.xmlParse;

import com.android.cuisy.visionappprj.entity.Camera;
import com.android.cuisy.visionappprj.entity.CameraDetail;
import com.android.cuisy.visionappprj.entity.Depart;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuisy on 2017/4/8.
 */

public class ParseXml {
    //获取树节点
    public static List getTreeNodeByXml(String xmlData) {

        List<Depart> treeNodelist = new LinkedList<Depart>();


        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();
            String status = "";
            String name = "";
            String des = "";
            String domain = "";
            String type = "";
            String parent = "";
            String remote = "";
            String count = "" ;
            String localdomain = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("start document");
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("start tag" + nodeName);
                        if ("node".equals(nodeName)){
                            name = xmlPullParser.getAttributeValue(0);
                            des = xmlPullParser.getAttributeValue(1);
                            type = xmlPullParser.getAttributeValue(2);
                            parent = xmlPullParser.getAttributeValue(3);
                            domain = xmlPullParser.getAttributeValue(4);
                            remote = xmlPullParser.getAttributeValue(5);
                            status = xmlPullParser.getAttributeValue(6);
                        }else if ("nodes".equals(nodeName)){
                            count = xmlPullParser.getAttributeValue(0);
                        }else if ("devices".equals(nodeName)){
                            localdomain = xmlPullParser.getAttributeValue(0);
                        }
                        break;

                    //完成解析某个节点
                    case XmlPullParser.END_TAG:
                        Depart treeNode = new Depart();
                        System.out.println("end tag");
                        if ("node".equals(nodeName)){
                            treeNode.setName(name);
                            treeNode.setDes(des);
                            treeNode.setType(type);
                            treeNode.setParent(parent);
                            treeNode.setDomain(domain);
                            treeNode.setRemote(remote);
                            treeNode.setStatus(status);
                            treeNodelist.add(treeNode);
                            System.out.println("~~~~~~~~~name = " + name);
                            System.out.println("~~~~~~~~~des = " + des);
                        }else if ("nodes".equals(nodeName)){
                            System.out.println("count = ");
                        }else if ("devices".equals(nodeName)){
                            System.out.println("localDomain = ");
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return treeNodelist;
    }

    //获取摄像头节点
    public static List getCameraNodeByXml(String xml) {

        List<Camera> cameraNodelist = new LinkedList<Camera>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xml));

            int eventType = xmlPullParser.getEventType();
            String name = "";
            String des = "";
            String channel = "";
            String domain = "";
            String type = "";
            String depart = "";
            String status = "";
            String lat = "";
            String longitude = "";
            String netstatus = "";
            String parent = "";
            String preset = "";
            String count = "";
            String localdomain = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("start document");
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("start tag" + nodeName);
                        if ("camera".equals(nodeName)) {
                            name = xmlPullParser.getAttributeValue(0);
                            des = xmlPullParser.getAttributeValue(1);
                            channel = xmlPullParser.getAttributeValue(2);
                            domain = xmlPullParser.getAttributeValue(3);
                            type = xmlPullParser.getAttributeValue(4);
                            depart = xmlPullParser.getAttributeValue(5);
                            status = xmlPullParser.getAttributeValue(6);
                            lat = xmlPullParser.getAttributeValue(7);
                            longitude = xmlPullParser.getAttributeValue(8);
                            netstatus = xmlPullParser.getAttributeValue(9);
                            parent = xmlPullParser.getAttributeValue(10);
                            preset = xmlPullParser.getAttributeValue(11);
                        } else if ("nodes".equals(nodeName)) {
                            count = xmlPullParser.getAttributeValue(0);
                        } else if ("devices".equals(nodeName)) {
                            localdomain = xmlPullParser.getAttributeValue(0);
                        }
                        break;

                    //完成解析某个节点
                    case XmlPullParser.END_TAG:
                        Camera cameraNode = new Camera();
                        System.out.println("end tag");
                        if ("camera".equals(nodeName)) {
                            cameraNode.setName(name);
                            cameraNode.setDes(des);
                            cameraNode.setChannel(channel);
                            cameraNode.setDomain(domain);
                            cameraNode.setType(type);
                            cameraNode.setDepart(depart);
                            cameraNode.setStatus(status);
                            cameraNode.setLat(lat);
                            cameraNode.setLongitude(longitude);
                            cameraNode.setNetstatus(netstatus);
                            cameraNode.setParent(parent);
                            cameraNode.setPreset(preset);
                            cameraNodelist.add(cameraNode);
                            System.out.println("camera : name = " + name);

                        } else if ("cameras".equals(nodeName)) {
                            System.out.println("count = " + count);
                        } else if ("devices".equals(nodeName)) {
                            System.out.println("localDomain = " + localdomain);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cameraNodelist;
    }

    //获取摄像头的详细信息
    public static List getCameraDetailByXml(String xml)
    {
        List<CameraDetail> cameraDetailList = new LinkedList<CameraDetail>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xml));
            int eventType = xmlPullParser.getEventType();

            String name = "";
            String des = "";
            String channel = "";
            String type = "";
            String devname = "";
            String depart = "";
            String departdes = "";
            String status = "";
            String domain = "";
            String address = "";
            String port = "";
            String username = "";
            String password = "";
            String devtype = "";
            String netstatus = "";
            String lat = "";
            String longitude = "";
            String css_ip = "";
            String css_port = "";
            String css_uid = "";
            String css_pwd = "";
            String css_chan = "";
            String url = "";
            String count = "";
            String localdomain = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("start document");
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("start tag" + nodeName);
                        if ("node".equals(nodeName)) {
                            name = xmlPullParser.getAttributeValue(0);
                            des = xmlPullParser.getAttributeValue(1);
                            channel = xmlPullParser.getAttributeValue(2);
                            type = xmlPullParser.getAttributeValue(3);
                            devname = xmlPullParser.getAttributeValue(4);
                            depart = xmlPullParser.getAttributeValue(5);
                            departdes = xmlPullParser.getAttributeValue(6);
                            status = xmlPullParser.getAttributeValue(7);
                            domain = xmlPullParser.getAttributeValue(8);
                            address = xmlPullParser.getAttributeValue(9);
                            port = xmlPullParser.getAttributeValue(10);
                            username = xmlPullParser.getAttributeValue(11);
                            password = xmlPullParser.getAttributeValue(12);
                            devtype = xmlPullParser.getAttributeValue(13);
                            netstatus = xmlPullParser.getAttributeValue(14);
                            lat = xmlPullParser.getAttributeValue(15);
                            longitude = xmlPullParser.getAttributeValue(16);
                            css_ip = xmlPullParser.getAttributeValue(17);
                            css_port = xmlPullParser.getAttributeValue(18);
                            css_uid = xmlPullParser.getAttributeValue(19);
                            css_pwd = xmlPullParser.getAttributeValue(20);
                            css_chan = xmlPullParser.getAttributeValue(21);
                            url = xmlPullParser.getAttributeValue(22);

                        } else if ("nodes".equals(nodeName)) {
                            count = xmlPullParser.getAttributeValue(0);
                        } else if ("devices".equals(nodeName)) {
                            localdomain = xmlPullParser.getAttributeValue(0);
                        }
                        break;

                    //完成解析某个节点
                    case XmlPullParser.END_TAG:
                        System.out.println("end tag");
                        CameraDetail cameraDetail1 = new CameraDetail();
                        if ("node".equals(nodeName)) {
                            cameraDetail1.setName(name);
                            cameraDetail1.setDes(des);
                            cameraDetail1.setChannel(channel);
                            cameraDetail1.setType(type);
                            cameraDetail1.setDevname(devname);
                            cameraDetail1.setDepart(depart);
                            cameraDetail1.setDepartdes(departdes);
                            cameraDetail1.setStatus(status);
                            cameraDetail1.setDomain(domain);
                            cameraDetail1.setAddress(address);
                            cameraDetail1.setPort(port);
                            cameraDetail1.setUsername(username);
                            cameraDetail1.setPassword(password);
                            cameraDetail1.setDevtype(devtype);
                            cameraDetail1.setNetstatus(netstatus);
                            cameraDetail1.setLat(lat);
                            cameraDetail1.setLongitude(longitude);
                            cameraDetail1.setCss_ip(css_ip);
                            cameraDetail1.setCss_port(css_port);
                            cameraDetail1.setCss_uid(css_uid);
                            cameraDetail1.setCss_pwd(css_pwd);
                            cameraDetail1.setCss_chan(css_chan);
                            cameraDetail1.setUrl(url);
                            cameraDetailList.add(cameraDetail1);
                        } else if ("nodes".equals(nodeName)) {
                            System.out.println("count = " + count);
                        } else if ("devices".equals(nodeName)) {
                            System.out.println("localDomain = " + localdomain);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cameraDetailList;
    }
}
