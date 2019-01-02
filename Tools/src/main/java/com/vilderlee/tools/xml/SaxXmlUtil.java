package com.vilderlee.tools.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * 属于基础方法
 * SAX的全称是Simple APIs for XML，也即XML简单应用程序接口。
 * 与DOM不同，SAX提供的访问模式是一种顺序模式，这是一种快速读写XML数据的方式。
 * 当使用SAX分析器对XML文档进行分析时，会触发一系列事件，并激活相应的事件处理函数，
 * 应用程序通过这些事件处理函数实现对XML文档的访问，因而SAX接口也被称作事件驱动接口。
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/28      Create this file
 * </pre>
 */
public class SaxXmlUtil {

    public static void main(String[] args) throws Exception {
        //创建一个SAXParseFactory 对象
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //通过factory获取parse获取实例
        SAXParser parser = saxParserFactory.newSAXParser();
        //获取SAXParseHandler对象

        //解析
//        parser.parse("test.xml", handler);
//
//        System.out.println("总共有"+ handler.get +"本书");


    }


    class SAXParserHandler extends DefaultHandler{

        @Override public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("开始解析");
        }

        @Override public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("结束解析");
        }

        @Override public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            //开始解析
            if(("book").equals(qName)){

            }

        }
    }
}
