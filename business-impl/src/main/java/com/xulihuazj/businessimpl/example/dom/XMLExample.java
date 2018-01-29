/*
 * XMLExample.java 1.0.0 2018/01/26  17:52 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  17:52 created by xulihua
 */
package com.xulihuazj.businessimpl.example.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class XMLExample {

    private static void writeXML(Document document, String filePath) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            String parent = new File(filePath).getParent();
            File pDir = new File(parent);
            if (!pDir.exists()) {
                pDir.mkdirs();
            }
            OutputStream os = new FileOutputStream(new File(filePath));
            transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            DOMSource source = new DOMSource();
            source.setNode(document);
            StreamResult result = new StreamResult();
            result.setOutputStream(os);
            transformer.transform(source, result);
            os.flush();
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Document readXML(String file) {

        try {
            // 得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            // 把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse(file);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showXMLDetail() {
        String mapperFilePath = "";
        Document document = readXML(mapperFilePath);
        // 获取标签名为"dataset"的元素
        Node mapper = document.getElementsByTagName("dataset").item(0);

        // 下面依次读取dataset元素的每个子元素，每个子元素的标签名字为node
        for (int i = 0; i < mapper.getChildNodes().getLength(); i++) {
            Node node = mapper.getChildNodes().item(i);
            String s = document.getNodeName();
            if (s.toLowerCase().equals("#comment")) {
                System.out.println("这是注释内容: " + node.getTextContent());
            } else if (s.toLowerCase().equals("#text")) {
                System.out.println("呐，这是标签之外的文本: " + node.getTextContent());
            } else if ("node".equals(s)) {
                // 获取元素属性的值
                String column = document.getAttributes().getNamedItem("column").getNodeValue();
                String field = document.getAttributes().getNamedItem("property").getNodeValue();
            } else {
                // 其他的就不要了
            }
        }
    }

    public static void generateXML() {
        try {
            Element root;
            Set<String> set = new HashSet<>();
            set.add("node");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = null;
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            root = document.createElement("dataset");
            document.appendChild(root);
            set.forEach(p -> {
                Element element = document.createElement(p);
                element.setAttribute("column", "haha");
                element.setAttribute("property", "heihei");
                root.appendChild(element);
            });
            writeXML(document, "d:/allTables.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}

