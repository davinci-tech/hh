package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import defpackage.phk;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class phc {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16126a = BaseApplication.e();
    private HashMap<String, String> d = new HashMap<>(16);
    private int e = 0;

    public phc(edr edrVar) {
        if (edrVar == null) {
            ReleaseLogUtil.c("SCUI_ThreeCircleShareManager", "ActiveRecordData is null");
            return;
        }
        if (d(edrVar)) {
            c();
        }
        d(edrVar.af());
        e(edrVar);
    }

    private void e(edr edrVar) {
        if (edrVar.af()) {
            this.d.put("first_circle_data", b(edrVar.e()));
            this.d.put("three_circle_type", "1");
            this.d.put("first_circle_target", b(edrVar.c()));
        } else {
            this.d.put("first_circle_data", b(edrVar.y()));
            this.d.put("three_circle_type", "0");
            this.d.put("first_circle_target", b(edrVar.p()));
        }
        this.d.put("second_circle_target", b(edrVar.f()));
        this.d.put("third_circle_target", b(edrVar.q()));
        this.d.put("second_circle_data", b(edrVar.i()));
        this.d.put("third_circle_data", b(edrVar.s()));
        this.d.put("sport_date", a(edrVar.j()));
        this.d.put("three_circle_perfect_days", Integer.toString(this.e));
    }

    private boolean d(edr edrVar) {
        return jdl.ac(edrVar.j()) && b(edrVar);
    }

    private boolean b(edr edrVar) {
        return edrVar.e() >= edrVar.c() && edrVar.i() >= edrVar.f() && edrVar.s() >= edrVar.q();
    }

    private void c() {
        this.e = 0;
        if (System.currentTimeMillis() < 1676822400000L) {
            LogUtil.a("SCUI_ThreeCircleShareManager", "time is earlier than 20230220");
            return;
        }
        if (!"true".equals(rvo.e(this.f16126a).a(3))) {
            ReleaseLogUtil.e("SCUI_ThreeCircleShareManager", "PRIVACY_SPORT_DATA Sync is close");
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        IBaseResponseCallback iBaseResponseCallback = new IBaseResponseCallback() { // from class: phc.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("SCUI_ThreeCircleShareManager", " errorCode is ", Integer.valueOf(i), " objData is ", obj);
                if (!(obj instanceof phk.e)) {
                    ReleaseLogUtil.d("SCUI_ThreeCircleShareManager", "requestPerfectDays onResponse with error data");
                    countDownLatch.countDown();
                } else {
                    phc.this.a((phk.e) obj);
                    countDownLatch.countDown();
                }
            }
        };
        new phj().e(iBaseResponseCallback);
        try {
            if (countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            ReleaseLogUtil.d("SCUI_ThreeCircleShareManager", "RequestData wait timeout");
            iBaseResponseCallback.d(-1, null);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("SCUI_ThreeCircleShareManager", "interrupted while waiting for requestData");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(phk.e eVar) {
        int c = eVar.c();
        int d = eVar.d();
        this.e = eVar.b();
        if (c != DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault())) {
            this.e = 0;
            LogUtil.a("SCUI_ThreeCircleShareManager", "cloud data not today");
        } else if (d != 1) {
            this.e++;
            LogUtil.a("SCUI_ThreeCircleShareManager", "cloud data not achieve three circle today");
        }
    }

    private void d(boolean z) {
        if (z) {
            this.d.put("first_circle_unit", this.f16126a.getResources().getString(R$string.IDS_band_data_sport_energy_unit));
        } else {
            this.d.put("first_circle_unit", this.f16126a.getResources().getString(R$string.IDS_settings_steps_unit));
        }
        this.d.put("second_circle_unit", this.f16126a.getResources().getString(R$string.IDS_min));
        this.d.put("third_circle_unit", this.f16126a.getResources().getString(R$string.IDS_messagecenter_time_hour_value));
    }

    private String a(long j) {
        return DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD) + " " + UnitUtil.a(new Date(j), 2);
    }

    private String b(int i) {
        return Integer.toString(i);
    }

    public void c(int i) {
        String value;
        pxp.e(i);
        this.d.put(BleConstants.SPORT_TYPE, String.valueOf(1100));
        feh fehVar = new feh();
        fehVar.d(this.d);
        ReleaseLogUtil.e("SCUI_ThreeCircleShareManager", "ThreeCircleMap is ", a(this.d));
        ArrayList arrayList = new ArrayList(1);
        feb febVar = new feb();
        febVar.d(1165);
        febVar.e(R.drawable._2131430517_res_0x7f0b0c75);
        arrayList.add(febVar);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shareWaterMarkData", fehVar);
        bundle.putSerializable("waterMarkIds", arrayList);
        bundle.putString("shareSource", "threeCircle");
        bundle.putInt("downLoadId", 1100);
        bundle.putBoolean("isDownloadMarkFromCloud", true);
        if (i == 4) {
            value = AnalyticsValue.HEALTH_CIRCLE_RING_GUIDE_10401124.value();
        } else {
            value = AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040112.value();
        }
        bundle.putString("shareModuleId", value);
        AppRouter.b("/PluginSocialShare/CustomizeShareActivity").zF_(bundle).c(this.f16126a);
    }

    public String a(HashMap<String, String> hashMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry.getKey() instanceof String) {
                sb.append(entry.getKey().length());
                sb.append(" ");
                sb.append((Object) entry.getValue());
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
