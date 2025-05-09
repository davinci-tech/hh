package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.squareup.okhttp.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class hgf extends xyz {
    public static final AgentLog cde = AgentLogManager.getAgentLog();

    public static class abc {
        public static final ExecutorService abc;
        public static final int bcd;
        public static final int cde;

        static {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            bcd = availableProcessors;
            int i = availableProcessors < 2 ? 2 : availableProcessors;
            cde = i;
            abc = new ThreadPoolExecutor(i, i, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        }
    }

    public static String abc(Response response, String str) {
        if (!TextUtils.isEmpty(str)) {
            String header = response.header(str);
            if (!TextUtils.isEmpty(header)) {
                return header;
            }
        }
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0045, code lost:
    
        if (android.text.TextUtils.isEmpty(r3) != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.squareup.okhttp.Response abc(com.huawei.agconnect.apms.wxy r13, com.squareup.okhttp.Response r14) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.hgf.abc(com.huawei.agconnect.apms.wxy, com.squareup.okhttp.Response):com.squareup.okhttp.Response");
    }
}
