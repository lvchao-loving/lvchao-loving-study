package com.lvchao.mysql.datasource;

public enum SourceName {
    read("read"),
    write("write");

    private String value;

    SourceName(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}