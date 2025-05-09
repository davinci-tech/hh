package com.huawei.agconnect.apms;

import android.os.FileObserver;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.HashSet;

/* loaded from: classes2.dex */
public class klm extends ijk {
    public static klm efg;
    public FileObserver fgh = null;
    public long ghi = 0;

    public class abc extends FileObserver {
        public abc(String str, int i) {
            super(str, i);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if (str == null) {
                return;
            }
            try {
                if (("/data/anr/" + str).contains("trace")) {
                    klm klmVar = klm.this;
                    klmVar.getClass();
                    if (Agent.isDisabled()) {
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - klmVar.ghi >= OpAnalyticsConstants.H5_LOADING_DELAY && klmVar.abc()) {
                        klmVar.ghi = currentTimeMillis;
                    }
                }
            } catch (Exception e) {
                ijk.abc.error("failed to observer anr file, " + e.getMessage());
            }
        }
    }

    public klm() {
        this.cde = new HashSet();
    }

    public void abc(boolean z) {
        if (Agent.isDisabled() || !z) {
            ijk.abc.warn("APMS agent or anr monitor is disabled, please enable.");
        }
    }
}
