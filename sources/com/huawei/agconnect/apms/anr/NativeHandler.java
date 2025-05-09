package com.huawei.agconnect.apms.anr;

import android.os.Build;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.ijk;
import java.util.HashSet;

/* loaded from: classes2.dex */
public class NativeHandler extends ijk {
    public static NativeHandler efg;

    public NativeHandler() {
        this.cde = new HashSet();
    }

    private static void anrCallback() {
        if (Agent.isDisabled()) {
            return;
        }
        bcd().abc();
    }

    public static NativeHandler bcd() {
        if (efg == null) {
            efg = new NativeHandler();
        }
        return efg;
    }

    private static native int initNativeAnr(int i);

    public void abc(boolean z) {
        if (Agent.isDisabled() || !z) {
            ijk.abc.warn("APMS agent or anr monitor is disabled, please enable.");
            return;
        }
        int i = Build.VERSION.SDK_INT;
        try {
            System.loadLibrary("apms_ndk_anr");
            try {
                int initNativeAnr = initNativeAnr(i);
                if (initNativeAnr != 0) {
                    ijk.abc.error("fail to init native anr, code: " + initNativeAnr);
                }
            } catch (Throwable th) {
                ijk.abc.error("fail to init native anr, " + th.getMessage());
            }
        } catch (Throwable th2) {
            ijk.abc.error("fail to load apm anr so library, " + th2.getMessage());
        }
    }
}
