package com.email.driver;

import com.common.dao.AgentDao;
import com.common.entity.AgentEntity;
import com.common.util.StringUtil;
import com.email.config.SpringAppConfig;
import com.api.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AgentUpdateRunner {
    private static Logger logger = LoggerFactory.getLogger(AgentUpdateRunner.class);
    private static AnnotationConfigApplicationContext ctx;
    private static AgentDao agentDao;

    public static void main(String[] args) throws Exception {
        ctx = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        agentDao = ctx.getBean(AgentDao.class);
        logger.info("============Info=============");
        logger.error("============Error=============");
        logger.debug("============Debug=============");
        run();
    }

    public static void run() {
        try {
            URL url = new URL("http://www.xicidaili.com/nt/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(20 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setConnectTimeout(5 * 1000);
            // conn.setRequestMethod("utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            // 得到输入流
            InputStream inputStream = conn.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String pattern = "<td>(.*?)</td>.*";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            int times = 0;
            String ip = "";
            String port = "";
            String type = "";
            String survivalTime = "";

            String line = "";
            while ((line = in.readLine()) != null) {
                if (StringUtil.isEmptyOrBlank(line)) {
                    continue;
                }
                if (Pattern.matches(".*<td>.*", line) && Pattern.matches(".*</td>.*", line)) {
                    times++;
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        String str = m.group(1);
                        if (times == 1) {
                            ip = str;
                        } else if (times == 2) {
                            port = str;
                        } else if (times == 3) {
                            type = str;
                        } else if (times == 4) {
                            survivalTime = str;
                        } else if (times == 5) {
                            // 如果其中一个为空则就跳过
                            if (StringUtil.isNotEmptyOrBlank(ip) && StringUtil.isNotEmptyOrBlank(port) && StringUtil.isNotEmptyOrBlank(type) && StringUtil.isNotEmptyOrBlank(survivalTime)) {
                                AgentEntity agent = agentDao.getAgentByIp(ip);
                                if (agent == null) {
                                    agentDao.createAgent(ip, port, type, survivalTime);
                                } else {
                                    agentDao.updateAgent(agent.getId(), port, type, survivalTime);
                                }
                            }
                            times = 0;
//                            String data = "ip=" + ip + "&port=" + port + "&type=" + type + "&survivalTime=" + survivalTime;
//                            restTemplate.postForObject("https://api.xupeisen.com/api/v1/agent/agent.sena?" + data, null, String.class);
                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("===========================================");
            logger.error(e + "");
            logger.error("===========================================\n");
        }
    }
}
