package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class d extends xyz {
    public static final AgentLog cde = AgentLogManager.getAgentLog();

    public static String abc(Response response, String str) {
        if (!TextUtils.isEmpty(str)) {
            String header = response.header(str);
            if (!TextUtils.isEmpty(header)) {
                return header;
            }
        }
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
    
        if (android.text.TextUtils.isEmpty(r5) != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okhttp3.Response abc(com.huawei.agconnect.apms.wxy r13, okhttp3.Response r14) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.d.abc(com.huawei.agconnect.apms.wxy, okhttp3.Response):okhttp3.Response");
    }
}
