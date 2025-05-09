package com.huawei.hianalytics.visual;

import android.content.SharedPreferences;
import com.huawei.hianalytics.visual.c0;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class e0 extends c0<String> {

    public class a implements c0.a<String> {
        @Override // com.huawei.hianalytics.visual.c0.a
        public String a() {
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.huawei.hianalytics.visual.c0.a
        public String a(String str) {
            return str;
        }

        @Override // com.huawei.hianalytics.visual.c0.a
        public String a(String str) {
            return str;
        }
    }

    public e0(Future<SharedPreferences> future) {
        super(future, "visual_config", new a());
    }
}
