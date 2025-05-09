package com.huawei.healthcloud.plugintrack.runningroute.utils;

import defpackage.hjd;

/* loaded from: classes4.dex */
public class RunningRouteConstants {
    public static final hjd d = new hjd(39.908722d, 116.397499d);

    public enum BiFromActivity {
        INVALID(0),
        FROM_LIST(1),
        FROM_MAP(2),
        FROM_DEEPLINK(3),
        FROM_HISTORY(5),
        FROM_SPORT_RECORD(6),
        FROM_RECORD_LIST_DIALOG(7),
        FROM_RECORD_DIALOG(8);

        private int mIndex;

        BiFromActivity(int i) {
            this.mIndex = i;
        }

        public int getIndex() {
            return this.mIndex;
        }
    }
}
