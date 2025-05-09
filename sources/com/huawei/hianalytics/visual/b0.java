package com.huawei.hianalytics.visual;

import android.content.SharedPreferences;
import com.huawei.hianalytics.visual.c0;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class b0 extends c0<Boolean> {
    public b0(Future<SharedPreferences> future) {
        super(future, "app_install", new a());
    }

    public class a implements c0.a<Boolean> {
        @Override // com.huawei.hianalytics.visual.c0.a
        public String a(Boolean bool) {
            Boolean bool2 = bool;
            return bool2 == null ? Boolean.FALSE.toString() : String.valueOf(bool2);
        }

        @Override // com.huawei.hianalytics.visual.c0.a
        public Boolean a(String str) {
            return Boolean.valueOf(str);
        }

        @Override // com.huawei.hianalytics.visual.c0.a
        public Boolean a() {
            return Boolean.FALSE;
        }
    }
}
