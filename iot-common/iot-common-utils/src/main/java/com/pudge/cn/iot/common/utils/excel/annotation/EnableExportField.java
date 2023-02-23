package com.pudge.cn.iot.common.utils.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 设置该字段允许导出，并且可以设置宽度
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableExportField {
    int colWidth() default 100*50;
    String colName();
}
