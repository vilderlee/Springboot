package com.vilderlee.tools.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

/**
 *
 * 性能很好
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/28      Create this file
 * </pre>
 */
public class Dom4JXmlUtil {
    public static void main(String[] args) throws Exception {
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        //创建Document对象
        Document document = reader.read("test.xml");
        //创建Document的根节点
        Element element = document.getRootElement();

        //迭代根节点下面可能存在的Document对象
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element childElement = (Element) iterator.next();
            List<Attribute> attributes = childElement.attributes();
            attributes.forEach(attribute -> {
                System.out.println("attribute.getNamespace()"+attribute.getNamespace() );
                System.out.println("attribute.getNamespacePrefix()"+attribute.getNamespacePrefix());
                System.out.println("attribute.getNamespaceURI()"+attribute.getNamespaceURI());
                System.out.println("attribute.getNodeType() "+attribute.getNodeType());
                System.out.println("attribute.getNodeTypeName()"+attribute.getNodeTypeName());
                System.out.println("attribute.getQName()"+attribute.getQName().getName());
                System.out.println("attribute.getText()"+attribute.getText());
                System.out.println("attribute.getValue()"+attribute.getValue());
            });

            //子节点下面的
            Iterator childElementIterator =  childElement.elementIterator();
            while (childElementIterator.hasNext()){
                Element element1 = (Element) childElementIterator.next();
                System.out.println("节点名称：" + element1.getName());
                System.out.println("节点值：" + element1.getStringValue());
            }
        }



    }
}
