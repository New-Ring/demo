package com.fankai.redis;

import com.actionsoft.sdk.service.response.ListMapResponse;
import com.fankai.bpm.BpmUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author fankai
 * @date 2021年06月09日 11:16
 */
public class OptimisticLockTest {

    @Test
    void redisTest() throws Exception{
        List<Map<String, Object>> result = BpmUtil.boQuery().getData();
        for(Map<String, Object> map :result){
            System.out.println(map.toString());
        }

    }

}
