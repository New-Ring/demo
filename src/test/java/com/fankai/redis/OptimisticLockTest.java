package com.fankai.redis;

import com.actionsoft.sdk.service.model.ProcessInstance;
import com.actionsoft.sdk.service.response.ListMapResponse;
import com.fankai.bpm.BpmUtil;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author fankai
 * @date 2021年06月09日 11:16
 */
public class OptimisticLockTest {

    /*@Test
    void redisTest() throws Exception{
        List<Map<String, Object>> result = BpmUtil.boQuery().getData();
        Map<String, Object> bo = result.get(0);
        System.out.println(bo.getOrDefault("UPDATEDATE","").toString());
        *//*for(Map<String, Object> map :result){
            System.out.println(map.toString());

            System.out.println(map.getOrDefault("UPDATEDATE","").toString(););
        }*//*

    }

    @Test
    void test() throws Exception{
        String encode = Base64.getEncoder().encodeToString("fankai01".getBytes("UTF-8"));
        System.out.println(encode);
        // 解码
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode, "UTF-8"));
    }*/

    /*@Test
    public void test11() throws Exception{
        *//*Object res= BpmUtil.processHistoryParticipantsGet("obj_5eb3f197e35248bab7b9409444b1a753","obj_c96a44bf8ba00001d44611c01eb07470");
        System.out.println("1111");
        System.out.println(res.toString());*//*


        List<String> res= BpmUtil.processHistoryParticipantsGet1("839c7fe6-5e0d-44f1-9119-ec7452ec66d2","obj_c96a44bf8ba00001d44611c01eb07470");
        for(String str:res){
            System.out.println(str);
        }
        System.out.println("1111");
        System.out.println(res.toString());
    }*/

}
