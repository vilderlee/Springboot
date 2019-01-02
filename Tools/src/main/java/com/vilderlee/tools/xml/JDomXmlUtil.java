package com.vilderlee.tools.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/28      Create this file
 * </pre>
 */
public class JDomXmlUtil {
    private static ArrayList<Book> booksList = new ArrayList<>();

    public static void main(String[] args) throws JDOMException, IOException {

        //创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build("test.xml");

        //获取根节点
        Element rootElement = document.getRootElement();

        Element nameElement = rootElement.getChild("name");
        System.out.println(nameElement.getValue());

        List<Element> list = rootElement.getChildren();
        list.forEach(element -> {

            System.out.println(element.isRootElement());

            System.out.println(element.getName() + "-" + element.getValue());
        });
    }
}
