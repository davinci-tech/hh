package com.huawei.hianalytics.framework.datahandler;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.b;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.framework.d;
import com.huawei.hianalytics.framework.i;
import java.util.List;

/* loaded from: classes4.dex */
public class ReportTask implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final String f3858a;
    public final String b;
    public String c;
    public final IMandatoryParameters d = d.a().b();
    public final int e;
    public final int f;
    public boolean g;

    @Override // java.lang.Runnable
    public void run() {
        if (TextUtils.isEmpty(this.f3858a) && TextUtils.isEmpty(this.b)) {
            this.g = true;
            for (String str : this.d.getAllTags()) {
                a(str, "oper");
                a(str, "maint");
            }
            return;
        }
        if (!"_default_config_tag".equals(this.f3858a) || !"allType".equals(this.b)) {
            a(this.f3858a, this.b);
            return;
        }
        String str2 = this.f3858a;
        a(str2, "oper");
        a(str2, "maint");
    }

    public final void a(String str, String str2) {
        IStorageHandler c = b.c(str);
        if (c == null) {
            HiLog.e("ReportTask", "storage handler is null");
            return;
        }
        List<Event> readOldEvents = this.g ? c.readOldEvents(str, str2) : c.readEvents(str, str2);
        if (readOldEvents != null && !readOldEvents.isEmpty()) {
            new i(str, str2, readOldEvents, null, this.c, this.e, this.f).a();
            return;
        }
        HiLog.sw("ReportTask", "events size is empty, tag: " + str);
    }

    public ReportTask(String str, String str2, String str3, int i, int i2) {
        this.f3858a = str;
        this.b = str2;
        this.c = str3;
        this.e = i;
        this.f = i2;
    }
}
