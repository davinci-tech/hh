package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gwn {

    /* renamed from: a, reason: collision with root package name */
    private Context f12973a;
    private gtq d;
    private PluginSportTrackAdapter e;
    private int g = 0;
    private int f = 0;
    private long b = 0;
    private boolean c = false;

    public gwn(PluginSportTrackAdapter pluginSportTrackAdapter, gtq gtqVar, Context context) {
        this.e = pluginSportTrackAdapter;
        this.d = gtqVar;
        this.f12973a = context;
    }

    public void e(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.e = pluginSportTrackAdapter;
    }

    public void d(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.f = i;
    }

    public void c() {
        if (gwg.i(this.g) && gwg.j(this.f)) {
            LogUtil.a("Track_ReportDataUtil", "registerReportData begin");
            if (this.e == null) {
                LogUtil.b("Track_ReportDataUtil", "registerReportData mPluginTrackAdapter is null");
                return;
            }
            final boolean az = gtx.c(this.f12973a).az();
            this.e.registerReportDataListener(new IReportDataCallback() { // from class: gwn.3
                @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
                public void onResult() {
                }

                @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
                public void onChange(kvq kvqVar) {
                    if (gwn.this.d != null && kvqVar != null) {
                        gtx.c(gwn.this.f12973a).c(az, kvqVar);
                        hac.a().b(gtx.c(BaseApplication.getContext()).f().b(), kvqVar.x());
                        gwn.this.b = kvqVar.m();
                        knv knvVar = new knv(kvqVar.m(), kvqVar.u());
                        LogUtil.a("Track_ReportDataUtil", "registerReportDataListener newStep = ", Integer.valueOf(kvqVar.u()));
                        ReleaseLogUtil.e("R_Track_ReportDataUtil", "registerReportDataListener onChange wear total steps");
                        gtw.e().d(knvVar);
                        gtx.c(BaseApplication.getContext()).b(kvqVar);
                        return;
                    }
                    LogUtil.a("Track_ReportDataUtil", "registerReportDataListener null");
                }
            });
            this.c = true;
            return;
        }
        LogUtil.b("Track_ReportDataUtil", "registerReportData not supportReportData");
    }

    public Bundle aUU_(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        kvq f = gtx.c(BaseApplication.getContext()).f();
        if (f == null || (this.b != 0 && System.currentTimeMillis() - this.b > 20000)) {
            LogUtil.h("Track_ReportDataUtil", "putReportData reportData is null or long time no update");
            return bundle;
        }
        int w = f.w();
        int s = f.s();
        int e = f.e();
        int q = f.q();
        int t = f.t();
        bundle.putString(MedalConstants.EVENT_STEPS, w > 0 ? String.valueOf(w) : "");
        bundle.putString("stepRate", s > 0 ? String.valueOf(s) : "");
        bundle.putString("altitude", String.valueOf(e));
        bundle.putString("totalCreep", q > 0 ? String.valueOf(q) : "");
        bundle.putString(BleConstants.TOTAL_DESCENT, t > 0 ? String.valueOf(t) : "");
        bundle.putString("calorie", String.valueOf(f.c() / 1000.0f));
        return bundle;
    }

    public void b() {
        if (this.c) {
            PluginSportTrackAdapter pluginSportTrackAdapter = this.e;
            if (pluginSportTrackAdapter != null) {
                pluginSportTrackAdapter.unRegisterReportDataListener();
            }
            this.c = false;
        }
    }
}
