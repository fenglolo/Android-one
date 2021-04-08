package com.fw.androidone.activity.network;

import android.util.Log;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

/**
 * description :XML解析 测试类
 * author : apple
 * date : 2021/4/7 11:27
 */
public class XmlActivity extends BaseActivity {
    String xmlData = "<apps>" +
            "<app>" +
            "<id>1</id>" +
            "<name>Google Maps</name>" +
            "<version>1.0</version>" +
            "</app>" +
            "<app>" +
            "<id>1</id>" +
            "<name>Google Maps 2</name>" +
            "<version>1.1</version>" +
            "</app>" +
            "<app>" +
            "<id>1</id>" +
            "<name>Google Maps 3</name>" +
            "<version>1.2</version>" +
            "</app>" +
            "</apps>";

    @Override
    protected int getContentLayout() {
        return R.layout.activity_xml;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        parseXMLWithPull(xmlData);
        parseXMLWithSAX(xmlData);
    }

    //Pull解析方式
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();

            String id = "";
            String name = "";
            String version = "";
            while (eventType != xmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    //完成解析某个节点
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d("TAG", "id is =" + id);
                            Log.d("TAG", "name is =" + name);
                            Log.d("TAG", "version is =" + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    //SAX解析方式
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler = new ContentHandler();
            //将ContentHandler实例设置到XMLReader中；
            xmlReader.setContentHandler(contentHandler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ContentHandler extends DefaultHandler {

        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;

        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //记录当前节点名
            nodeName = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                Log.d("TAG", "id is =" + id.toString().trim());
                Log.d("TAG", "name is =" + name.toString().trim());
                Log.d("TAG", "version is =" + version.toString().trim());
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }
}
