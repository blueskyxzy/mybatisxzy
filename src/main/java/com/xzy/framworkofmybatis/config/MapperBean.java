package com.xzy.framworkofmybatis.config;

import java.util.List;

/**
 * created by xzy on 2019/1/15
 */
public class MapperBean{

    private String namespace;

    private List<Function> list;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }

}
