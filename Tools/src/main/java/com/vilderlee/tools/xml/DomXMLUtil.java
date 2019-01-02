package com.vilderlee.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * 属于官方提供的
 * 基于DOM的XML分析器将一个XML文档转换成一个对象模型的集合（通常称DOM树），应用程序正是通过对这个对象模型的操作，来实现对XML文档数据的操作
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/28      Create this file
 * </pre>
 */
public class DomXMLUtil {

    public static void main(String[] args) throws Exception {

        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
        Document document = documentBuilder.parse("test.xml");
        // 获取所有book节点的集合
        NodeList nodeList = document.getElementsByTagName("book");
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
            Node book = nodeList.item(i);
            //获取book节点的所有属性集合
            NamedNodeMap attrs = book.getAttributes();
            //遍历book节点所有属性
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                System.out.println(attr.getNodeName() + ":" + attr.getNodeValue());
            }

            //解析book下面的子节点
            NodeList childNodeList = book.getChildNodes();
            System.out.println("第" + (i + 1) + "本书共有" + childNodeList.getLength() + "个子节点");
            for (int j = 0; j < childNodeList.getLength(); j++) {
                //区分出text类型的node以及element类型的node
                if (childNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //获取了element类型节点的节点名
                    System.out.print("第" + (j + 1) + "个节点的节点名：" + childNodeList.item(j).getNodeName());
                    //获取了element类型节点的节点值
                    System.out.println("--节点值是：" + childNodeList.item(j).getFirstChild().getNodeValue());
                }
            }

        }
    }
}
