package com.zgame.config;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @projectName: ZGame
 * @package: com.zgame.config
 * @className: Config
 * @author: DaYon
 * @description: TODO
 * @date: 2022/10/13 22:37
 * @version: 1.0
 */
public class Config {
    public static String PUBLISH_RELEASE = "release";
    public static String PUBLISH_TEST = "test";
    public static String PUBLISH_DEBUG = "debug";

    // 必填项
    public String ZONE = "develop";         // 所属大区
    public int HOSTID = 1000;
    public String HOSTD_TYPE = "userd";
    public String NETD_LISTENER = null;
    public String WEBSOCKET_NETD_LISTENER = null;
    public String HTTPD_LISTENER = null;
    public String HOSTD_LISTENER = null;
    public String MONGODB_URI = "mongodb://192.168.0.104:27017/";
    public String DBNAME = "zgame_java";
    public String PUBLISH = PUBLISH_DEBUG;
    public String sentry_dsn;


    // 选填项
    public String AUTO_UPDATE_FILE = "logic/etc/autoupdate"; // 自动热更配置文件
    public String AUTO_UPDATE_CLASS_DIR = "logic/classes"; // 自动热更.class文件目录
    public String LOGS_PATH = "log";
    public int timerTick = 100;         // timer的最小时间间隔(毫秒)
    public int snapshotInterval = 60;  // 每隔指定秒数存盘一次(秒)

    public int BOSS_THREAD = 1; // netty 监听线程数量
    public int WORKER_THREAD = 4; // netty和zgame.thradpool.ThreadPool的工作线程数量

    public static <T extends Config> T parseFromFile(String filePath, Class<? extends Config> cls) throws IOException {
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(filePath);
        byte[] buf = new byte[(int) file.length()];
        stream.read(buf);
        T conf = JSON.parseObject(buf, cls);
        stream.close();
        return conf;
    }

    public Config() {
        // 保留一个核保证logic线程能正常运行
        WORKER_THREAD = Math.max(Runtime.getRuntime().availableProcessors() - 1, 1);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, true);
    }

}
