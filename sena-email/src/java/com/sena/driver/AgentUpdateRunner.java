package com.sena.driver;

import com.sena.config.SpringAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AgentUpdateRunner {
    private static Logger logger = LoggerFactory.getLogger(AgentUpdateRunner.class);
//    private static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) throws Exception {
//        ctx = new AnnotationConfigApplicationContext(SpringAppConfig.class);
//        weeklyService = ctx.getBean(WeeklyServiceImpl.class);
//        agencyDao = ctx.getBean(AgencyDao.class);
        run();
    }

    public static void run() {
        try {
            logger.info("============Start=============");
            logger.info("==============================");
        } catch (Exception e) {
            logger.error("===========================================");
            logger.error(e + "");
            logger.error("===========================================\n");
        }
    }
}
