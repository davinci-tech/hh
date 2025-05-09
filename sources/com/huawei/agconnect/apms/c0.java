package com.huawei.agconnect.apms;

import android.os.Environment;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes2.dex */
public class c0 {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static c0 bcd;
    public final Properties cde;

    public c0() throws IOException {
        Properties properties = new Properties();
        this.cde = properties;
        FileInputStream fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
        try {
            properties.load(fileInputStream);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                abc.warn("failed to close build prop file: " + e.getMessage());
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Exception e2) {
                abc.warn("failed to close build prop file: " + e2.getMessage());
            }
            throw th;
        }
    }
}
