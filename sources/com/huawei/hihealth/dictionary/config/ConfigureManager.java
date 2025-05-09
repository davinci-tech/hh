package com.huawei.hihealth.dictionary.config;

import android.content.Context;
import com.huawei.hihealth.dictionary.model.HiHealthDictionary;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ConfigureManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4121a = new Object();
    private static volatile ConfigureManager c;
    private List<ConfigureLoader> b = new ArrayList(1);

    private ConfigureManager() {
    }

    public static ConfigureManager c() {
        if (c == null) {
            synchronized (f4121a) {
                if (c == null) {
                    LogUtil.b("HiH_DictConfigureManager", "start to create ConfigureManager.");
                    ConfigureManager configureManager = new ConfigureManager();
                    configureManager.e(new LocalConfigLoader());
                    configureManager.e(new CloudConfigLoader());
                    c = configureManager;
                }
            }
        }
        return c;
    }

    public void e(ConfigureLoader configureLoader) {
        if (configureLoader == null) {
            LogUtil.c("HiH_DictConfigureManager", "configureLoader is null");
        } else {
            this.b.add(configureLoader);
        }
    }

    public List<HiHealthDictionary> a(Context context) {
        ArrayList arrayList = new ArrayList();
        for (ConfigureLoader configureLoader : this.b) {
            LogUtil.d("HiH_DictConfigureManager", "start to load dict config from:", configureLoader.getName());
            HiHealthDictionary loadConfig = configureLoader.loadConfig(context);
            LogUtil.d("HiH_DictConfigureManager", "finish load dict config from:", configureLoader.getName());
            if (loadConfig == null) {
                LogUtil.c("HiH_DictConfigureManager", "dict load failed from ", configureLoader.getName());
            } else if (!loadConfig.h()) {
                LogUtil.c("HiH_DictConfigureManager", "dict data is illegal. version:", Integer.valueOf(loadConfig.f()));
            } else {
                loadConfig.d();
                loadConfig.c();
                loadConfig.e();
                arrayList.add(loadConfig);
            }
        }
        return arrayList;
    }
}
