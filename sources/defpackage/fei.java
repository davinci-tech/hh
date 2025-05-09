package defpackage;

import android.os.Bundle;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwbasemgr.IBaseStatusCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwvoiceplaymodel.IVoiceHandler;

/* loaded from: classes4.dex */
public class fei implements IVoiceHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12469a = new Object();
    private static fei c;
    private IBaseStatusCallback d = new IBaseStatusCallback() { // from class: fei.4
        @Override // com.huawei.hwbasemgr.IBaseStatusCallback
        public void onAvailable() {
            LogUtil.a("Track_PluginTrackVoiceHandler", "onAvailable invoke");
            feq.d(0);
        }

        @Override // com.huawei.hwbasemgr.IBaseStatusCallback
        public void onOccupied() {
            LogUtil.a("Track_PluginTrackVoiceHandler", "onOccupied invoke");
            feq.d(1);
        }
    };

    private fei() {
    }

    public static fei c() {
        fei feiVar;
        synchronized (f12469a) {
            if (c == null) {
                c = new fei();
            }
            feiVar = c;
        }
        return feiVar;
    }

    public void a() {
        gso.e().e(this.d);
    }

    @Override // com.huawei.hwvoiceplaymodel.IVoiceHandler
    public int handleVoiceEvent(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("Track_PluginTrackVoiceHandler", "handleVoiceEvent voiceMsg is null");
            return -4;
        }
        String string = bundle.getString("voiceMessage");
        if (string == null) {
            LogUtil.h("Track_PluginTrackVoiceHandler", "handleVoiceEvent message is null");
            return -4;
        }
        int e = e(string);
        LogUtil.a("Track_PluginTrackVoiceHandler", "handleVoiceEvent message = ", string, ", result = ", Integer.valueOf(e));
        return e;
    }

    private int e(String str) {
        int i = gso.e().i();
        LogUtil.a("Track_PluginTrackVoiceHandler", "dispatchVoiceEvent currentSportState = ", Integer.valueOf(i));
        if (kzc.n().q()) {
            LogUtil.a("Track_PluginTrackVoiceHandler", "indoor run ", Integer.valueOf(i));
            return -1;
        }
        if (i == 0) {
            fek.c().d(R.raw._2131886177_res_0x7f120061);
            return -1;
        }
        if (str.endsWith("queryHeartRate")) {
            feq.c("queryHeartRate");
            if (i != 1) {
                fek.c().d(R.raw._2131886170_res_0x7f12005a);
                return -1;
            }
            gso.e().d(ggx.d(), ggx.e(gxf.d(), ggx.a()));
            return gso.e().c(IndoorEquipManagerApi.KEY_HEART_RATE);
        }
        if (str.endsWith("queryCurrentDuration")) {
            feq.c("queryCurrentDuration");
            return gso.e().c("duration");
        }
        if (str.endsWith("queryCurrentPace")) {
            if (i != 1) {
                fek.c().d(R.raw._2131886170_res_0x7f12005a);
                return -1;
            }
            feq.c("queryCurrentPace");
            return gso.e().c("speed");
        }
        if (str.endsWith("queryAll")) {
            feq.c("queryAll");
            return gso.e().c("sportState");
        }
        if (str.endsWith("queryCurrentDistance")) {
            feq.c("queryCurrentDistance");
            return gso.e().c("distance");
        }
        if (str.endsWith("queryLastKilometerPace")) {
            feq.c("queryLastKilometerPace");
            return gso.e().c("pace");
        }
        LogUtil.a("Track_PluginTrackVoiceHandler", "dispatchVoiceEvent other condition");
        return -1;
    }
}
