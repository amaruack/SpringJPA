package com.example.springboot.config.util;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect extends H2Dialect {

    public MyH2Dialect() {
        super();
        registerFunction("testson", new StandardSQLFunction("testson", StandardBasicTypes.STRING));
    }
}
