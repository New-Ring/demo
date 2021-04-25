package com.fankai.enums;

public enum ProcessDefId {
    PROJECT_AC_BUDGET_SEND("obj_97f3814f3b384f66b3e4476f68c7f549", "项目建设预算抄送", "BO_EU_YSCS");

    private final String processDefId;//流程ID
    private final String desc;//说明
    private final String modelIdentification;//新版审批存储模型标识

    ProcessDefId(String processDefId, String desc, String modelIdentification) {
        this.processDefId = processDefId;
        this.desc = desc;
        this.modelIdentification = modelIdentification;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public String getDesc() {
        return desc;
    }

    public String getModelIdentification() {
        return modelIdentification;
    }

}
