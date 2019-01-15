package com.xzy.framworkofmybatis;

public interface Excutor {
    public <T> T query(String statement, Object parameter);
}
