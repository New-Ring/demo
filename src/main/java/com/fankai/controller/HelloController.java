package com.fankai.controller;

import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.util.DBSql;
import com.fankai.util.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/say")
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "卢莹大傻逼";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("loginName", "admin");
        model.addAttribute("loginId", "27");
        return "success";
    }

    @RequestMapping("/getRemortIP")
    public String getRemortIP(HttpServletRequest request){
        return IpAddressUtil.getIP(request);
    }

    @RequestMapping("/downloadLocalXlsxFile")
    public void downloadLocalXlsxFile(HttpServletResponse res, HttpServletRequest req) throws Exception {
        String localFilename = "测试文件_下载.xlsx";
        String localFilepath = getClass().getResource("/templates/" + localFilename).getPath();

        res.setContentType("multipart/form-data");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");

        String userAgent = req.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            // IE Core
            localFilename = java.net.URLEncoder.encode(localFilename, "UTF-8");
        } else {
            // Non-IE Core
            localFilename = new String((localFilename).getBytes("UTF-8"), "ISO-8859-1");
        }
        res.setHeader("Content-Disposition", "attachment;fileName=" + localFilename);

        localFilepath = URLDecoder.decode(localFilepath, "UTF-8");
        FileInputStream instream = new FileInputStream(localFilepath);
        ServletOutputStream outstream = res.getOutputStream();
        int b = 0;
        byte[] buffer = new byte[1024];
        while ((b = instream.read(buffer)) != -1) {
            outstream.write(buffer, 0, b);
        }
        instream.close();

        if (outstream != null) {
            outstream.flush();
            outstream.close();
        }
    }

    @RequestMapping("/updateBpm")
    public void updateBpm(HttpServletRequest request){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from BO_EU_BYY_JXLC_MX  where id=? " );
        List<RowMap> pnoList = DBSql.getMaps(sql.toString(), new Object[]{"1e9ff99c-cbab-4c6a-9fe7-37382ab531fa"});
        if (pnoList.size()>0) {
            RowMap rowMap = pnoList.get(0);
            /*for (Map project : projects) {
                project.put("GLLC", rowMap.getString("BT"));
                project.put("GLLCID", rowMap.getString("BINDID"));
            }*/
        }
        /*try {
            DBSql.update("UPDATE BO_EU_BYY_JXLC_MX set DYKHRPF=25 ,ZPF=25 WHERE ID=? ", new Object[]{"1e9ff99c-cbab-4c6a-9fe7-37382ab531fa"});
        } catch (Exception e) {
            log.error("考核人评分更新错误：" + e.getMessage());
        }*/
    }
}
