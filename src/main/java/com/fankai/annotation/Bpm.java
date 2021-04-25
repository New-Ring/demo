package com.fankai.annotation;

import com.fankai.enums.ProcessDefId;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Bpm {

    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    ProcessDefId processDefId() default ProcessDefId.PROJECT_AC_BUDGET_SEND;
}
