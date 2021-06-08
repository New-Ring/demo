package com.fankai.bpm;


import com.actionsoft.bpms.api.OpenApiClient;
import com.actionsoft.bpms.api.common.ApiResponse;
import com.actionsoft.sdk.service.response.BoolResponse;
import com.actionsoft.sdk.service.response.StringResponse;
import com.actionsoft.sdk.service.response.process.ProcessInstResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BpmUtil {

    public final static String PROCESS_DEF_ID = "processDefId";
    public final static String PROCESS_INST_ID = "processInstId";
    public static final String BO_CREATE = "bo.create";
    public static final String BO_CREATES = "bo.creates";
    public final static String UID = "uid";
    public final static String TITLE = "title";
    public final static String VARS = "vars";
    public final static String BO_NAME = "boName";
    public final static String RECORD_DATA = "recordData";
    public final static String RECORD_DATA_S = "recordDatas";
    public final static String BIND_ID = "bindId";
    public final static String MAIN_BO_ID = "mainBoId";
    public final static String SUB_BO_IDS = "subBoIds";
    public final static String TASK_ID = "taskId";
    public final static String PORT_URL = "portUrl";
    public final static String ATT_KEY = "attKey";
    public final static String ATT_NAME = "attName";

    public static final String PROCESS_DELETE = "process.delete";
    public static final String PROCESS_CREATE = "process.create";
    public static final String PROCESS_START = "process.start";
    public static final String PROCESS_INST_GET = "process.inst.get";

    private static String apiServer = "http://pal.zhengbang.com/portal/openapi";
    private static String accessKey = "webapis";
    private static String secret = "20191024#";
    public static String portUrl = "http://pal.zhengbang.com/portal/r/w?cmd=com.zb.common.sso_openForm";
    private static String filePath = "http://10.88.6.4:8800/BUILD/file/downPFile/";

    /**
     * 创建流程
     *
     * @param processDefId
     * @param uid
     * @param title
     * @param vars
     * @return
     */
    public static String processCreate(String processDefId, String uid, String title, HashMap<String, Object> vars) throws Exception {
        Map<String, Object> args = new HashMap<>(8);
        args.put(PROCESS_DEF_ID, processDefId);
        args.put(UID, uid);
        args.put(TITLE, title);
        args.put(VARS, vars);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(PROCESS_CREATE, args, ProcessInstResponse.class).getData().getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("流程创建失败");
        }
    }

    /**
     * 根据流程id删除流程
     *
     * @param processInstId
     * @param uid
     * @return
     * @throws Exception
     */
    public static boolean deleteProcess(String processInstId, String uid) throws Exception {
        Map<String, Object> args = new HashMap<>(8);
        args.put("processInstId", processInstId);
        args.put("uid", uid);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(PROCESS_DELETE, args, BoolResponse.class).isData();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("流程删除失败");
        }
    }

    /**
     * 创建与BPM的连接
     *
     * @return
     * @throws Exception
     */
    public static OpenApiClient getOpenApiClient() throws Exception {
        try {
            return new OpenApiClient(apiServer, accessKey, secret);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("获取BPM连接失败");
        }
    }

    /**
     * 推送主表数据
     *
     * @param bo
     * @param data
     * @param bindId
     * @param uid
     * @return
     * @throws Exception
     */
    public static String boCreate(String bo, JSONObject data, String bindId, String uid) throws Exception {
        Map<String, Object> args = new HashMap(8);
        args.put(BO_NAME, bo);
        args.put(RECORD_DATA, data);
        args.put(BIND_ID, bindId);
        args.put(UID, uid);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(BO_CREATE, args, StringResponse.class).getData();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("推送主表数据失败");
        }
    }

    /**
     * 子表数据推送
     *
     * @param bo
     * @param data
     * @param bindId
     * @param uid
     * @return
     * @throws Exception
     */
    public static String boCreates(String bo, JSONArray data, String bindId, String uid) throws Exception {
        Map<String, Object> args = new HashMap(8);
        args.put(BO_NAME, bo);
        args.put(RECORD_DATA_S, data);
        args.put(BIND_ID, bindId);
        args.put(UID, uid);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(BO_CREATES, args, StringResponse.class).getData();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("推送子表数据失败");
        }
    }

    /**
     * 启动流程
     *
     * @param bindId
     * @return
     * @throws Exception
     */
    public static String processStart(String bindId) throws Exception {
        Map<String, Object> args = new HashMap(8);
        args.put(PROCESS_INST_ID, bindId);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(PROCESS_START, args, ApiResponse.class).getResult();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("启动流程失败");
        }
    }

    /**
     * 获取任务ID
     *
     * @param bindId
     * @return
     * @throws Exception
     */
    public static String getTaskInitId(String bindId) throws Exception {
        Map<String, Object> args = new HashMap(4);
        args.put(PROCESS_INST_ID, bindId);
        OpenApiClient client = getOpenApiClient();
        try {
            return client.exec(PROCESS_INST_GET, args, ProcessInstResponse.class).getData().getStartTaskInstId();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("获取任务ID失败");
        }
    }

    public static JSONObject createBpmFlow(String processDefId, String uid, String title, String bo, JSONObject boData, JSONObject bosData) throws Exception {
        checkParams(processDefId, uid, title, bo, boData); //参数校验

        String processInstId = boData.getString(PROCESS_INST_ID);
        if (StringUtils.isNotEmpty(processInstId)) deleteProcess(processInstId, uid); //判断是否需要现删除流程

        HashMap<String, Object> vars = new HashMap<>();
        String bindId = processCreate(processDefId, uid, title, vars); //创建流程

        if (StringUtils.isNotEmpty(bindId) && StringUtils.isNotEmpty(bo)) {
            String mainBoId = boCreate(bo, boData, bindId, uid);  //推送主表数据
            StringBuffer suBoIds = bosCreate(boData, bosData, uid, bindId);  //推送子表数据
            processStart(bindId); //启动流程
            String taskInstId = getTaskInitId(bindId); //获取任务id

            JSONObject jsonObject = new JSONObject();
            String url = portUrl + "&processInstId=" + bindId + "&taskInstId=" + taskInstId + "&openState=1&uid=" + uid + "&ismobile=false";
            jsonObject.put(MAIN_BO_ID, mainBoId);
            jsonObject.put(PROCESS_INST_ID, bindId);
            jsonObject.put(TASK_ID, taskInstId);
            jsonObject.put(PORT_URL, url);
            jsonObject.put(SUB_BO_IDS, suBoIds);
            log.info("jsonObject---->{}", jsonObject.toJSONString());
            return jsonObject;
        }
        return null;
    }

    /**
     * 参数校验
     *
     * @param processDefId
     * @param uid
     * @param title
     * @param bo
     * @param boData
     */
    private static void checkParams(String processDefId, String uid, String title, String bo, JSONObject boData) {
        Assert.notNull(uid, "未获取到人员id");
        Assert.notNull(title, "未获取到标题");
        Assert.notNull(processDefId, "未获取到流程实例id");
        Assert.notNull(bo, "主表表名不存在");
        if (CollectionUtils.isNotEmpty(boData)) {
            throw new IllegalArgumentException("主表数据不存在");
        }
    }

    /**
     * 子表数据组装推送
     *
     * @param boData
     * @param bosData
     * @param uid
     * @param bindId
     * @return
     * @throws Exception
     */
    private static StringBuffer bosCreate(JSONObject boData, JSONObject bosData, String uid, String bindId) throws Exception {
        StringBuffer suBoIds = new StringBuffer();
        if (CollectionUtils.isNotEmpty(bosData)) {
            String attKey = boData.get(ATT_KEY) == null ? "" : String.valueOf(boData.get(ATT_KEY));
            for (String key : bosData.keySet()) {
                JSONArray array = new JSONArray();
                if (key.equals(attKey)) {
                    for (Object object : bosData.getJSONArray(key)) {
                        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
                        if (jsonObject.containsKey(ATT_NAME)) {
                            String attName = URLDecoder.decode(jsonObject.getString(ATT_NAME), "utf-8");
                            jsonObject.remove(ATT_NAME);
                            jsonObject.put(ATT_NAME, attName);
                        }
                        array.add(jsonObject);
                    }
                } else {
                    array = boData.getJSONArray(key);
                }
                String subBoId = boCreates(key, array, bindId, uid);
                String boId = subBoId.replaceAll("\"", "").replace("[", "").replaceAll("]", "");
                if (StringUtils.isNotEmpty(boId)) {
                    suBoIds.append(boId + ",");
                }
            }
        }
        return suBoIds;
    }
}
