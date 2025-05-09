package com.huawei.agconnect.apms.instrument.webview;

import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.event.webview.WebViewLoadEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.rst;

/* loaded from: classes2.dex */
public class APMSJavaScriptBridge {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    /* loaded from: classes8.dex */
    public static class ReportH5LoadEventRunnable implements Runnable {
        private final JsonObject jsEvent;

        public ReportH5LoadEventRunnable(JsonObject jsonObject) {
            this.jsEvent = jsonObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            rst.cde.add(new WebViewLoadEvent(System.currentTimeMillis(), this.jsEvent));
        }
    }

    @JavascriptInterface
    public void reportJsEvent(String str) {
        if (Agent.isDisabled() || Agent.isWebViewMonitorDisabled()) {
            return;
        }
        LOG.debug("begin to report webView performance data: " + str);
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            Agent.getExecutor().execute(new ReportH5LoadEventRunnable((JsonObject) new Gson().fromJson(str, JsonObject.class)));
        } catch (Throwable unused) {
            LOG.warn("the jsEvent is not a json, please check.");
        }
    }
}
