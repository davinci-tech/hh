package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.soundaction.manager.EventDispatcher;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwvoiceplaymodel.IVoiceHandler;

/* loaded from: classes8.dex */
public class fem implements EventDispatcher {
    private static final Object b = new Object();
    private static fem d;

    public static fem b() {
        fem femVar;
        synchronized (b) {
            if (d == null) {
                d = new fem();
            }
            femVar = d;
        }
        return femVar;
    }

    @Override // com.huawei.health.soundaction.manager.EventDispatcher
    public boolean dispatchEvent(Object obj) {
        String b2 = fel.b(obj);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        LogUtil.a("Track_VoiceRequestDispatcher", "dispatchEvent eventMsg = ", b2);
        Bundle bundle = new Bundle();
        bundle.putString("voiceMessage", b2);
        IVoiceHandler b3 = b(b2);
        if (b3 == null || kzc.n().q()) {
            return false;
        }
        LogUtil.a("Track_VoiceRequestDispatcher", "dispatchEvent result = ", Integer.valueOf(b3.handleVoiceEvent(bundle)));
        return true;
    }

    private IVoiceHandler b(String str) {
        if (str.startsWith("running")) {
            return fei.c();
        }
        if (str.startsWith("suggestion")) {
            return fej.e();
        }
        return null;
    }
}
