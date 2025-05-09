package com.huawei.hianalytics.framework;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.framework.listener.HAEventManager;
import com.huawei.hianalytics.framework.listener.IHAEventListener;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f3863a;
    public final String b;
    public final String c;
    public final String d;
    public final List<Event> e;
    public ICallback f;
    public boolean g;

    public final void a(IStorageHandler iStorageHandler) {
        IMandatoryParameters iMandatoryParameters = d.e.f3856a;
        if (iMandatoryParameters.isFlashKey() && iStorageHandler.readEventsAllSize() == 0) {
            iMandatoryParameters.refreshKey(EncryptUtil.generateSecureRandomStr(16), 1);
            iStorageHandler.deleteCommonHeaderExAll();
        }
    }

    public final void a(int i) {
        List<Event> list;
        if ((i == 400 || i == 413) && (list = this.e) != null && list.size() > 0) {
            if (this.e.size() > 100 && !"ha_default_collection".equals(this.b)) {
                e a2 = b.a(this.b);
                String str = this.d;
                a2.e.put(str + "_send_retry_flag", 100);
                return;
            }
            IStorageHandler c = b.c(this.b);
            if (c != null) {
                HiLog.i("SendTask", "storageHandler delete bad request events, tag: " + this.b + ", type: " + this.d);
                c.deleteEvents(this.e);
                a(c);
            }
            a();
        }
    }

    public final void a(IStorageHandler iStorageHandler, Event event, int i) {
        IStoragePolicy d = b.d(this.b);
        if (d == null) {
            HiLog.e("SendTask", "storage policy is null. tag: " + this.b + ", type: " + this.d);
            return;
        }
        if (d.decide(IStoragePolicy.PolicyType.STORAGE_LENGTH, this.d)) {
            HiLog.e("SendTask", "db file reach max size, tag: " + this.b + ", type: " + this.d);
            iStorageHandler.deleteAll();
            b.a(Boolean.FALSE, this.f, iStorageHandler, event, i);
            return;
        }
        long readEventSize = iStorageHandler.readEventSize(this.b);
        if (readEventSize == 0) {
            b.a(Boolean.FALSE, this.f, iStorageHandler, event, i);
            return;
        }
        if (readEventSize <= 5000) {
            b.a(Boolean.FALSE, this.f, iStorageHandler, event, i);
            return;
        }
        HiLog.e("SendTask", "evt size reach max size, tag: " + this.b + ", TYPE: " + this.d);
        iStorageHandler.deleteByTag(this.b);
        b.a(Boolean.FALSE, this.f, iStorageHandler, event, i);
    }

    public final void a() {
        int min;
        IHAEventListener eventListener = HAEventManager.getInstance().getEventListener();
        if (eventListener != null && (min = Math.min(5, this.e.size())) > 0) {
            Collections.sort(this.e, new Comparator() { // from class: com.huawei.hianalytics.framework.j$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return j.a((Event) obj, (Event) obj2);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < min; i++) {
                sb.append(this.e.get(i).getEvtid());
                sb.append(",");
            }
            eventListener.onEvent(this.b, "$discard_list", sb.substring(0, sb.length() - 1));
        }
    }

    public static /* synthetic */ int a(Event event, Event event2) {
        return event2.getContent().length() - event.getContent().length();
    }

    public j(byte[] bArr, String str, String str2, String str3, List<Event> list) {
        this.f3863a = (byte[]) bArr.clone();
        this.b = str;
        this.c = str3;
        this.d = str2;
        this.e = list;
    }
}
