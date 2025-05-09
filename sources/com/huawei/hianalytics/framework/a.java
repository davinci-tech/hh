package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.datahandler.ReportTask;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a implements HAFrameworkInstance {

    /* renamed from: a, reason: collision with root package name */
    public final String f3854a;

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void clearCacheDataAll() {
        l a2 = l.a();
        String str = this.f3854a;
        synchronized (a2) {
            try {
                IStorageHandler c = b.c(str);
                if (c != null) {
                    c.deleteByTag(str);
                }
            } catch (Exception unused) {
                HiLog.e("HES", "clearCacheDataByTag Exception");
            }
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void clearCacheDataByTag() {
        l a2 = l.a();
        String str = this.f3854a;
        synchronized (a2) {
            try {
                IStorageHandler c = b.c(str);
                if (c != null) {
                    c.deleteByTag(str);
                }
            } catch (Exception unused) {
                HiLog.e("HES", "clearCacheDataByTag Exception");
            }
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void clearCacheDataByTagType(String str) {
        l a2 = l.a();
        String str2 = this.f3854a;
        synchronized (a2) {
            try {
                IStorageHandler c = b.c(str2);
                if (c != null) {
                    c.deleteByTagType(str2, str);
                }
            } catch (Exception unused) {
                HiLog.e("HES", "clearCacheDataByTagType Exception");
            }
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onEvent(String str, String str2, JSONObject jSONObject, ICallback iCallback) {
        l a2 = l.a();
        String str3 = this.f3854a;
        synchronized (a2) {
            long currentTimeMillis = System.currentTimeMillis();
            IStoragePolicy d = b.d(str3);
            g gVar = new g(str3, str, str2, jSONObject, currentTimeMillis, d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str));
            gVar.l = iCallback;
            TaskThread.getRecordThread().addToQueue(gVar);
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onEventSync(String str, String str2, JSONObject jSONObject) {
        l a2 = l.a();
        String str3 = this.f3854a;
        synchronized (a2) {
            long currentTimeMillis = System.currentTimeMillis();
            IStoragePolicy d = b.d(str3);
            new g(str3, str, str2, jSONObject, currentTimeMillis, d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str)).run();
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onReport(String str) {
        StringBuilder sb;
        l a2 = l.a();
        String str2 = this.f3854a;
        a2.getClass();
        if (TextUtils.isEmpty(b.a(str2, str))) {
            sb = new StringBuilder("collectUrl is empty, tag: ");
        } else {
            IStoragePolicy d = b.d(str2);
            if (d == null) {
                sb = new StringBuilder("policy is invalid, tag: ");
            } else {
                if (d.decide(IStoragePolicy.PolicyType.NETWORK, str)) {
                    TaskThread.getReportThread().addToQueue(new ReportTask(str2, str, "", d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str)));
                    return;
                }
                sb = new StringBuilder("network is invalid, tag: ");
            }
        }
        sb.append(str2);
        sb.append(", type: ");
        sb.append(str);
        HiLog.w("HES", sb.toString());
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onEvent(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, ICallback iCallback) {
        l a2 = l.a();
        String str3 = this.f3854a;
        synchronized (a2) {
            long currentTimeMillis = System.currentTimeMillis();
            IStoragePolicy d = b.d(str3);
            try {
                h hVar = new h(str3, str, str2, jSONObject, jSONObject2, jSONObject3, currentTimeMillis, d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str));
                hVar.l = iCallback;
                TaskThread.getRecordThread().addToQueue(hVar);
            } catch (JSONException unused) {
                HiLog.w("HES", "headerEx or commonEx is not json");
            }
        }
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onStreamEvent(String str, String str2, JSONObject jSONObject, ICallback iCallback) {
        l a2 = l.a();
        String str3 = this.f3854a;
        a2.getClass();
        long currentTimeMillis = System.currentTimeMillis();
        IStoragePolicy d = b.d(str3);
        g gVar = new g(str3, str, str2, jSONObject, currentTimeMillis, d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str));
        gVar.k = true;
        gVar.l = iCallback;
        TaskThread.getRecordThread().addToQueue(gVar);
    }

    @Override // com.huawei.hianalytics.framework.HAFrameworkInstance
    public void onStreamEvent(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, ICallback iCallback) {
        l a2 = l.a();
        String str3 = this.f3854a;
        a2.getClass();
        long currentTimeMillis = System.currentTimeMillis();
        IStoragePolicy d = b.d(str3);
        try {
            h hVar = new h(str3, str, str2, jSONObject, jSONObject2, jSONObject3, currentTimeMillis, d.getDecryptBatchSize(str), d.getDecryptBatchSleepTime(str));
            hVar.k = true;
            hVar.l = iCallback;
            TaskThread.getRecordThread().addToQueue(hVar);
        } catch (JSONException unused) {
            HiLog.w("HES", "headerEx or commonEx is not json");
        }
    }

    public a(String str) {
        this.f3854a = str;
    }
}
