package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.framework.datahandler.ReportTask;
import com.huawei.hianalytics.framework.listener.HAEventManager;
import com.huawei.hianalytics.framework.listener.IHAEventListener;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public String f3861a;
    public String b;
    public String c;
    public final String d;
    public final String e;
    public final JSONObject f;
    public final long g;
    public final IMandatoryParameters h = d.a().b();
    public final int i;
    public final int j;
    public boolean k;
    public ICallback l;

    @Override // java.lang.Runnable
    public void run() {
        IHAEventListener eventListener;
        IHAEventListener eventListener2;
        JSONObject jSONObject;
        Event event = new Event();
        event.setServicetag(this.f3861a);
        event.setEvttype(this.d);
        event.setEvtid(this.e);
        JSONObject jSONObject2 = this.f;
        String jSONObject3 = jSONObject2 != null ? jSONObject2.toString() : "{}";
        event.setEvttime(String.valueOf(this.g));
        event.setSessionid("");
        event.setSessionname("");
        event.setSubCount((!Event.EventConstants.EVENT_ID_AGGREGATE.equals(this.e) || (jSONObject = this.f) == null) ? 1 : jSONObject.optInt(Event.EventConstants.SUB_COUNT, 1));
        event.setEvtExHashCode(this.b);
        IMandatoryParameters iMandatoryParameters = this.h;
        if (iMandatoryParameters == null) {
            return;
        }
        event.setProcessname(iMandatoryParameters.getProcessName());
        if (this.k) {
            event.setContent(jSONObject3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(event);
            i iVar = new i(this.f3861a, this.d, arrayList, this.l, "", this.i, this.j);
            iVar.f = true;
            iVar.a();
            return;
        }
        if (b.b(this.f3861a).isLocalEncrypted(this.d)) {
            event.setContent(b.a(jSONObject3, this.h));
            event.setIsEncrypted(1);
        } else {
            event.setContent(jSONObject3);
            event.setIsEncrypted(0);
        }
        IStorageHandler c = b.c(this.f3861a);
        IStoragePolicy d = b.d(this.f3861a);
        if (c == null || d == null) {
            HiLog.e("RecordTask", "storageHandler is null! tag: " + this.f3861a);
            return;
        }
        if (d.decide(IStoragePolicy.PolicyType.STORAGE_LENGTH, this.d)) {
            HiLog.e("RecordTask", "db file reach max size, clear db file, tag: " + this.f3861a);
            c.deleteAll();
        } else {
            long readEventSize = c.readEventSize(this.f3861a);
            if (readEventSize == 0 && event.getSubCount() == 1) {
                b.a(this.l, c, event);
                HiLog.i("RecordTask", "record evt size: 1, tag: " + this.f3861a + ", type: " + this.d);
                return;
            }
            if (readEventSize <= 5000) {
                b.a(this.l, c, event);
                e a2 = b.a(this.f3861a);
                if (a2 == null) {
                    HiLog.w("RecordTask", "framework config is null, tag: " + this.f3861a);
                    return;
                }
                Long l = a2.b.get(this.d);
                long longValue = l != null ? l.longValue() : 0L;
                long currentTimeMillis = System.currentTimeMillis();
                Long l2 = a2.c.get(this.d);
                long longValue2 = l2 != null ? l2.longValue() : 30000L;
                if (currentTimeMillis - longValue <= longValue2) {
                    return;
                }
                long readEventSize2 = c.readEventSize(this.f3861a, this.d);
                HiLog.i("RecordTask", "record evt size: " + readEventSize2 + ", tag: " + this.f3861a + ", type: " + this.d);
                if (d.decide(IStoragePolicy.PolicyType.STORAGE_SIZE, this.d, readEventSize2)) {
                    a2.b.put(this.d, Long.valueOf(System.currentTimeMillis()));
                    if (TextUtils.isEmpty(b.a(this.f3861a, this.d)) || !d.decide(IStoragePolicy.PolicyType.NETWORK, this.d)) {
                        HiLog.w("RecordTask", "network is invalid or collectUrl is empty, tag: " + this.f3861a + ", type: " + this.d);
                        return;
                    }
                    HiLog.i("RecordTask", "begin to auto report! tag: " + this.f3861a + ", type: " + this.d);
                    TaskThread.getReportThread().addToQueue(new ReportTask(this.f3861a, this.d, "", this.i, this.j));
                    if (longValue2 != OpAnalyticsConstants.H5_LOADING_DELAY && (eventListener2 = HAEventManager.getInstance().getEventListener()) != null) {
                        eventListener2.onEvent(this.f3861a, "$retry_list", "");
                    }
                    IHAEventListener eventListener3 = HAEventManager.getInstance().getEventListener();
                    if (eventListener3 != null) {
                        eventListener3.onReport(this.f3861a);
                    }
                    if (TextUtils.equals("HmsProfiler", this.f3861a) && (eventListener = HAEventManager.getInstance().getEventListener()) != null) {
                        eventListener.reportAppEvent();
                    }
                    IHAEventListener eventListener4 = HAEventManager.getInstance().getEventListener();
                    if (eventListener4 == null) {
                        return;
                    }
                    eventListener4.metricReport(this.f3861a);
                    return;
                }
                return;
            }
            HiLog.e("RecordTask", "cache failed, tag: " + this.f3861a + ", type: " + this.d);
            c.deleteByTag(this.f3861a);
        }
        b.a(this.l, c, event);
    }

    public g(String str, String str2, String str3, JSONObject jSONObject, long j, int i, int i2) {
        this.f3861a = str;
        this.e = str3;
        this.f = jSONObject;
        this.g = j;
        this.d = str2;
        this.i = i;
        this.j = i2;
    }
}
