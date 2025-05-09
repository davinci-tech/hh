package com.huawei.hianalytics;

import android.content.Context;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.storage.CommonHeaderEx;
import com.huawei.hianalytics.core.storage.DaoManager;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public class n implements IStorageHandler {

    /* renamed from: a, reason: collision with root package name */
    public static IStorageHandler f3887a;

    /* renamed from: a, reason: collision with other field name */
    public final m f57a;

    public static IStorageHandler a(Context context) {
        if (f3887a == null) {
            synchronized (n.class) {
                if (f3887a == null) {
                    f3887a = new n(context);
                }
            }
        }
        return f3887a;
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public boolean checkAndClearTable() {
        this.f57a.getClass();
        return DaoManager.getInstance().checkAndClearTable();
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public void deleteAll() {
        m mVar = this.f57a;
        mVar.getClass();
        DaoManager.getInstance().deleteAllEvents();
        mVar.m575a("", "");
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public void deleteByTag(String str) {
        m mVar = this.f57a;
        mVar.getClass();
        DaoManager.getInstance().deleteEvents(str, "");
        mVar.m575a(str, "");
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public void deleteByTagType(String str, String str2) {
        m mVar = this.f57a;
        mVar.getClass();
        DaoManager.getInstance().deleteEvents(str, str2);
        mVar.m575a(str, str2);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public void deleteCommonHeaderExAll() {
        this.f57a.getClass();
        DaoManager.getInstance().deleteAllHeaders();
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public int deleteMcInfo(List<String> list) {
        this.f57a.getClass();
        return DaoManager.getInstance().deleteMcInfo(list);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public int deleteMcTag(List<String> list) {
        this.f57a.getClass();
        return DaoManager.getInstance().deleteMcTag(list);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long insert(CommonHeaderEx commonHeaderEx) {
        this.f57a.getClass();
        return DaoManager.getInstance().insert(commonHeaderEx);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long insertMcInfo(String str) {
        this.f57a.getClass();
        return DaoManager.getInstance().insertMcInfo(str);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long insertMcTagList(List<String> list) {
        this.f57a.getClass();
        return DaoManager.getInstance().insertMcTagList(list);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public List<String> queryMcInfo() {
        this.f57a.getClass();
        return DaoManager.getInstance().queryMcInfo();
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public List<String> queryMcTag(String str) {
        this.f57a.getClass();
        return DaoManager.getInstance().queryMcTag(str);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public CommonHeaderEx readCommonHeaderEx(String str) {
        this.f57a.getClass();
        return DaoManager.getInstance().readHeader(str);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long readEventSize(String str) {
        m mVar = this.f57a;
        AtomicLong b = mVar.b(str, "oper");
        long j = b != null ? b.get() : mVar.a(str, "oper").get();
        AtomicLong b2 = mVar.b(str, "maint");
        return j + (b2 != null ? b2.get() : mVar.a(str, "maint").get());
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public List<Event> readEvents(String str, String str2) {
        m mVar = this.f57a;
        if (mVar.f54a == null) {
            mVar.f54a = j.b(EnvUtils.getAppContext());
        }
        return DaoManager.getInstance().readEvents(str, str2, mVar.f54a, -1L);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long readEventsAllSize() {
        this.f57a.getClass();
        return DaoManager.getInstance().countEvents();
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public List<Event> readOldEvents(String str, String str2) {
        m mVar = this.f57a;
        if (mVar.f54a == null) {
            mVar.f54a = j.b(EnvUtils.getAppContext());
        }
        long j = i.a().m550a().f50b;
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        return DaoManager.getInstance().readEvents(str, str2, mVar.f54a, j);
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long readEventSize(String str, String str2) {
        m mVar = this.f57a;
        AtomicLong b = mVar.b(str, str2);
        return b != null ? b.get() : mVar.a(str, str2).get();
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public void deleteEvents(List<Event> list) {
        m mVar = this.f57a;
        mVar.getClass();
        if (list == null || list.isEmpty()) {
            return;
        }
        DaoManager.getInstance().deleteEvents(list);
        String servicetag = list.get(0).getServicetag();
        String evttype = list.get(0).getEvttype();
        long j = 0;
        while (list.iterator().hasNext()) {
            j -= r10.next().getSubCount();
        }
        AtomicLong b = mVar.b(servicetag, evttype);
        if (b == null) {
            mVar.a(servicetag, evttype);
        } else if (b.addAndGet(j) < 0) {
            mVar.m575a(servicetag, evttype);
        }
    }

    @Override // com.huawei.hianalytics.core.storage.IStorageHandler
    public long insert(Event event) {
        m mVar = this.f57a;
        mVar.getClass();
        long insert = DaoManager.getInstance().insert(event);
        if (insert > 0) {
            String servicetag = event.getServicetag();
            String evttype = event.getEvttype();
            long subCount = event.getSubCount();
            AtomicLong b = mVar.b(servicetag, evttype);
            if (b == null) {
                mVar.a(servicetag, evttype);
            } else if (b.addAndGet(subCount) < 0) {
                mVar.m575a(servicetag, evttype);
            }
        }
        return insert;
    }

    public n(Context context) {
        this.f57a = m.a(context);
    }
}
