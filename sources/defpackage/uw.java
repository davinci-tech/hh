package defpackage;

import com.huawei.ads.adsrec.ICABackFlowCallback;
import com.huawei.ads.adsrec.IDsRelationCallback;
import com.huawei.ads.adsrec.IEventReportCallback;
import com.huawei.ads.adsrec.IUtilCallback;

/* loaded from: classes2.dex */
public class uw {

    /* renamed from: a, reason: collision with root package name */
    private static volatile IEventReportCallback f17562a;
    private static volatile IUtilCallback c;
    private static volatile ICABackFlowCallback d;
    private static volatile IDsRelationCallback e;

    public static void e(IUtilCallback iUtilCallback) {
        c = iUtilCallback;
    }

    public static void a(IEventReportCallback iEventReportCallback) {
        f17562a = iEventReportCallback;
    }

    public static IUtilCallback d() {
        return c;
    }

    public static ICABackFlowCallback e() {
        return d;
    }

    public static IEventReportCallback b() {
        return f17562a;
    }

    public static IDsRelationCallback a() {
        return e;
    }
}
