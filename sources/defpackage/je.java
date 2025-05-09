package defpackage;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.sdk.m.d0.a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class je implements a {

    /* renamed from: a, reason: collision with root package name */
    public static je f13758a;
    public static DataReportResult b;
    public BugTrackMessageService c;
    public w d;
    public DataReportService e;

    @Override // com.alipay.sdk.m.d0.a
    public boolean logCollect(String str) {
        BugTrackMessageService bugTrackMessageService;
        String str2;
        if (mq.e(str) || (bugTrackMessageService = this.c) == null) {
            return false;
        }
        try {
            str2 = bugTrackMessageService.logCollect(mq.j(str));
        } catch (Throwable unused) {
            str2 = null;
        }
        if (mq.e(str2)) {
            return false;
        }
        return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
    }

    @Override // com.alipay.sdk.m.d0.a
    public DataReportResult a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.e != null) {
            b = null;
            new Thread(new jb(this, dataReportRequest)).start();
            for (int i = 300000; b == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return b;
    }

    public static je a(Context context, String str) {
        je jeVar;
        synchronized (je.class) {
            if (f13758a == null) {
                f13758a = new je(context, str);
            }
            jeVar = f13758a;
        }
        return jeVar;
    }

    public je(Context context, String str) {
        this.d = null;
        this.c = null;
        this.e = null;
        aa aaVar = new aa();
        aaVar.a(str);
        h hVar = new h(context);
        this.d = hVar;
        this.c = (BugTrackMessageService) hVar.a(BugTrackMessageService.class, aaVar);
        this.e = (DataReportService) this.d.a(DataReportService.class, aaVar);
    }
}
