package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.DownloadBlockInfo;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import java.util.List;

/* loaded from: classes5.dex */
public interface qq {
    ContentRecord a();

    void a(int i, int i2);

    void a(int i, int i2, List<String> list);

    void a(int i, long j);

    void a(long j);

    void a(long j, int i);

    void a(long j, int i, ot otVar);

    void a(long j, long j2, int i, int i2);

    void a(long j, long j2, int i, int i2, String str);

    void a(Context context, ContentRecord contentRecord);

    void a(EventType eventType, Integer num, Integer num2);

    void a(EventType eventType, Integer num, Integer num2, boolean z);

    void a(ContentRecord contentRecord);

    void a(EventRecord eventRecord, boolean z, boolean z2);

    void a(com.huawei.openalliance.ad.processor.eventbeans.a aVar);

    void a(com.huawei.openalliance.ad.processor.eventbeans.b bVar);

    void a(Integer num);

    void a(Integer num, Integer num2, String str, String str2, String str3);

    void a(Integer num, String str, int i, DownloadBlockInfo downloadBlockInfo, int i2, String str2, String str3, String str4);

    void a(Integer num, String str, int i, String str2, String str3, String str4);

    void a(Integer num, String str, DownloadBlockInfo downloadBlockInfo, int i, String str2, String str3, String str4);

    void a(String str, String str2, String str3);

    void a(List<FeedbackInfo> list);

    void b();

    void b(int i, int i2);

    void b(long j, long j2, int i, int i2);

    void b(com.huawei.openalliance.ad.analysis.b bVar, boolean z, boolean z2);

    void b(com.huawei.openalliance.ad.processor.eventbeans.a aVar);

    void b(Integer num, Integer num2, String str, String str2, String str3);

    void b(Integer num, String str, int i, DownloadBlockInfo downloadBlockInfo, int i2, String str2, String str3, String str4);

    void b(Integer num, String str, int i, String str2, String str3, String str4);

    void b(Integer num, String str, DownloadBlockInfo downloadBlockInfo, int i, String str2, String str3, String str4);

    void b(List<FeedbackInfo> list);

    void b(boolean z);

    void c();

    void c(long j, long j2, int i, int i2);

    void c(com.huawei.openalliance.ad.processor.eventbeans.a aVar);

    void c(Integer num, Integer num2, String str, String str2, String str3);

    void d();

    void d(long j, long j2, int i, int i2);

    void e();

    void f();

    void g();

    void h();

    void i();

    void j();

    void k();

    void l();

    void m();

    void n();

    void o();

    void p();

    void q();

    void r();

    void s();

    void t();

    void u();
}
