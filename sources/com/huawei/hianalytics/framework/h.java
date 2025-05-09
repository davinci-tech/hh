package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.CommonHeaderEx;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class h extends g {
    @Override // com.huawei.hianalytics.framework.g, java.lang.Runnable
    public void run() {
        if (!TextUtils.isEmpty(this.b)) {
            CommonHeaderEx commonHeaderEx = new CommonHeaderEx(this.b, this.c);
            IStorageHandler c = b.c(this.f3861a);
            if (c != null) {
                c.insert(commonHeaderEx);
            }
        }
        super.run();
    }

    public static String a(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2) {
        StringBuilder sb;
        if (jSONObject == null && jSONObject2 == null) {
            sb = new StringBuilder("header and evt is null, tag: ");
        } else {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("headerEx", jSONObject);
            jSONObject3.put("commonEx", jSONObject2);
            if (jSONObject3.length() != 0) {
                return jSONObject3.toString();
            }
            sb = new StringBuilder("content is empty, tag: ");
        }
        sb.append(str);
        sb.append(", type: ");
        sb.append(str2);
        HiLog.sw("RecordTaskEx", sb.toString());
        return "";
    }

    public static String a(String str) {
        return SHA.sha256Encrypt(str);
    }

    public h(String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, long j, int i, int i2) {
        super(str, str2, str3, jSONObject, j, i, i2);
        String a2 = a(jSONObject2, jSONObject3, str, str2);
        this.c = a2;
        if (TextUtils.isEmpty(a2)) {
            HiLog.sw("RecordTaskEx", "common and header Ex is empty, tag: " + str + ", type: " + str2);
            return;
        }
        IMandatoryParameters b = d.a().b();
        if (b == null || b.checkDebugModeEnabled()) {
            return;
        }
        this.b = a(this.c);
    }
}
