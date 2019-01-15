package com.xzy.framworkofmybatis;

import com.xzy.framworkofmybatis.config.Function;
import com.xzy.framworkofmybatis.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * created by xzy on 2019/1/15
 */
public class MyConfiguration {
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    // 读配置并读取数据库
    public Connection build(String resource) throws Exception {
        try {
            InputStream input = loader.getResourceAsStream(resource);
            // 读xml配置
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(input);
            Element rootElement = document.getRootElement();
            return evalDatasource(rootElement);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection evalDatasource(Element rootElement) throws Exception {
        /**
         * @Description  //读数据库配置 并且用jdbc操作数据库
         * @param  [rootElement]
         * @return  java.sql.Connection
        **/
        // 读配置并赋值
        if(!rootElement.getName().equals("database")){
            throw new RuntimeException("dont have database information");
        }
        String driverClassName = null;
        String username = null;
        String password = null;
        String url = null;
        // 读属性
        for (Object item : rootElement.elements("property")){
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null){
                throw new RuntimeException("property should contain value");
            }
            switch (name){
                case "driverClassName" : driverClassName = value; break;
                case "url" : url = value; break;
                case "username" : username = value; break;
                case "password" : password = value; break;
                default: throw new RuntimeException("have unknown property");
            }
        }
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    private String getValue(Element item) {
        return item.hasContent()?item.getText():item.attributeValue("value");
    }

    public MapperBean readMapper(String path){
        /**
         * @Description  //读取*mapper.xml配置
         * @param  [path]
         * @return  com.xzy.framworkofmybatis.config.MapperBean
        **/
        MapperBean mapperBean = new MapperBean();
        try {
            InputStream in = loader.getResourceAsStream(path);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(in);
            Element rootElement = document.getRootElement();
            mapperBean.setNamespace(rootElement.attributeValue("namespace").trim());
            List<Function> functions = new ArrayList();
            Iterator elementIterator = rootElement.elementIterator();
            while (elementIterator.hasNext()){
                Function function = new Function();
                Element element = (Element) elementIterator.next();
                function.setFuncName(element.attributeValue("id"));
                function.setSql(element.getText().trim());
                function.setSqltype(element.getName());
                String resultType = element.attributeValue("resultType");
                Object newInstance = null;
                try {
                    newInstance = Class.forName(resultType).newInstance();
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                } catch (InstantiationException e){
                    e.printStackTrace();
                }
                function.setResultType(newInstance);
                functions.add(function);
            }
            mapperBean.setList(functions);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapperBean;
    }
}
