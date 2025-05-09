package com.huawei.agconnect.apms.collect;

import android.app.Application;
import android.content.Context;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.util.HiAnalyticTools;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes.dex */
public class HiAnalyticsManager {
    public static final String APM_EVENT_ID = "APMS";
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    private static final int OM_TAG = 1;
    private static final String SERVICE_TAG = "APMS";
    private static volatile HiAnalyticsManager instance;
    private HiAnalyticsInstance analyticsInstance;
    private String collectionUrl;
    private Context context;
    private final Set<abc> hiAnalyticsActionListeners = new HashSet();

    /* loaded from: classes2.dex */
    public interface abc {
        void abc(Context context);
    }

    private HiAnalyticsManager() {
    }

    public static HiAnalyticsManager getInstance() {
        if (instance == null) {
            synchronized (HiAnalyticsManager.class) {
                if (instance == null) {
                    instance = new HiAnalyticsManager();
                }
            }
        }
        return instance;
    }

    private void init(Context context) {
        HiAnalyticsInstance instanceByTag = com.huawei.hianalytics.process.HiAnalyticsManager.getInstanceByTag("APMS");
        this.analyticsInstance = instanceByTag;
        if (instanceByTag == null) {
            initDebugLog(context);
            try {
                this.analyticsInstance = new HiAnalyticsInstance.Builder(context).create("APMS");
            } catch (Throwable th) {
                LOG.error("exception occurred when initialize HiAnalytics instance: " + th.getMessage());
            }
        }
        refreshOMConfig(true);
    }

    private void initDebugLog(Context context) {
        try {
            if (LOG.getLevel() == 3) {
                HiAnalyticTools.enableLog(context, 3);
            } else {
                HiAnalyticTools.enableLog(context, 4);
            }
        } catch (Throwable th) {
            LOG.error("exception occurred when initialize HiAnalytics log: " + th.getMessage());
        }
    }

    private void notifyRefreshCollectUrlSuccess() {
        synchronized (this.hiAnalyticsActionListeners) {
            for (abc abcVar : this.hiAnalyticsActionListeners) {
                if (abcVar != null) {
                    abcVar.abc(this.context);
                }
            }
        }
    }

    private void refreshOMConfig(boolean z) {
        if (this.analyticsInstance == null) {
            LOG.error("can not operate with HiAnalytics null instance.");
            return;
        }
        if (this.collectionUrl == null) {
            LOG.info("waiting for collection url to be set.");
            return;
        }
        try {
            this.analyticsInstance.refresh(1, new HiAnalyticsConfig.Builder().setCollectURL(this.collectionUrl).setEnableUUID(z).build());
            notifyRefreshCollectUrlSuccess();
            LOG.debug("collection url has been set to " + this.collectionUrl);
        } catch (Throwable th) {
            LOG.error("exception occurred when refresh HiAnalytics omConfig: " + th.getMessage());
        }
    }

    public void addActionListener(abc abcVar) {
        synchronized (this.hiAnalyticsActionListeners) {
            this.hiAnalyticsActionListeners.add(abcVar);
        }
    }

    public void onEvent(String str, LinkedHashMap<String, String> linkedHashMap) {
        HiAnalyticsInstance hiAnalyticsInstance = this.analyticsInstance;
        if (hiAnalyticsInstance == null) {
            LOG.error("can not operate with HiAnalytics null instance.");
            return;
        }
        try {
            hiAnalyticsInstance.onEvent(1, str, linkedHashMap);
        } catch (Throwable th) {
            LOG.error("exception occurred when operate HiAnalytics: " + th.getMessage());
        }
    }

    public void onReport() {
        HiAnalyticsInstance hiAnalyticsInstance = this.analyticsInstance;
        if (hiAnalyticsInstance == null) {
            LOG.error("can not operate with HiAnalytics null instance.");
            return;
        }
        try {
            hiAnalyticsInstance.onReport(1);
        } catch (Throwable th) {
            LOG.error("exception occurred when operate HiAnalytics: " + th.getMessage());
        }
    }

    public void removeActionListener(abc abcVar) {
        synchronized (this.hiAnalyticsActionListeners) {
            this.hiAnalyticsActionListeners.remove(abcVar);
        }
    }

    public void setCollectionUrlAndEnableUUID(String str, boolean z) {
        if (str == null || str.isEmpty()) {
            LOG.error("can not set collection url to empty string.");
            return;
        }
        String str2 = this.collectionUrl;
        this.collectionUrl = str;
        AgentLog agentLog = LOG;
        agentLog.debug("collection url has been cached: " + this.collectionUrl);
        Context context = this.context;
        if (context != null) {
            init(context);
        } else {
            agentLog.error("the context is null when initialize HiAnalytics.");
        }
        if (this.analyticsInstance == null) {
            agentLog.error("failed to initialize HiAnalytics.");
            return;
        }
        if (str2 != null && !str2.equals(this.collectionUrl)) {
            try {
                this.analyticsInstance.clearData();
                agentLog.info("cached data has been cleared for the new collection url.");
            } catch (Throwable th) {
                LOG.error("exception occurred when clearing HiAnalytics cached data: " + th.getMessage());
            }
        }
        refreshOMConfig(z);
    }

    public void setContext(Context context) {
        if (context instanceof Application) {
            this.context = context;
        } else {
            this.context = context == null ? null : context.getApplicationContext();
        }
    }
}
