package com.huawei.phoneservice.feedbackcommon.utils;

/* loaded from: classes6.dex */
public class h {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(java.lang.String r7, java.lang.String r8, java.lang.String r9) throws java.io.UnsupportedEncodingException {
        /*
            java.lang.String r0 = "?"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 512(0x200, float:7.17E-43)
            r1.<init>(r2)
            java.lang.String r2 = ""
            r3 = 0
            boolean r4 = r7.contains(r0)     // Catch: java.lang.Exception -> L4b
            if (r4 == 0) goto L41
            r4 = 63
            int r4 = r7.indexOf(r4)     // Catch: java.lang.Exception -> L4b
            r5 = 0
            java.lang.String r2 = r7.substring(r5, r4)     // Catch: java.lang.Exception -> L4b
            int r4 = r7.indexOf(r0)     // Catch: java.lang.Exception -> L4b
            java.lang.String r4 = r7.substring(r4)     // Catch: java.lang.Exception -> L4b
            int r4 = r4.length()     // Catch: java.lang.Exception -> L4b
            r5 = 1
            if (r4 <= r5) goto L3b
            com.huawei.phoneservice.feedbackcommon.utils.d r4 = new com.huawei.phoneservice.feedbackcommon.utils.d     // Catch: java.lang.Exception -> L4b
            int r0 = r7.indexOf(r0)     // Catch: java.lang.Exception -> L4b
            int r0 = r0 + r5
            java.lang.String r0 = r7.substring(r0)     // Catch: java.lang.Exception -> L4b
            r4.<init>(r0)     // Catch: java.lang.Exception -> L4b
            goto L64
        L3b:
            com.huawei.phoneservice.feedbackcommon.utils.d r4 = new com.huawei.phoneservice.feedbackcommon.utils.d     // Catch: java.lang.Exception -> L4b
            r4.<init>(r3)     // Catch: java.lang.Exception -> L4b
            goto L64
        L41:
            com.huawei.phoneservice.feedbackcommon.utils.d r4 = new com.huawei.phoneservice.feedbackcommon.utils.d     // Catch: java.lang.Exception -> L48
            r4.<init>(r3)     // Catch: java.lang.Exception -> L48
            r2 = r7
            goto L64
        L48:
            r0 = move-exception
            r2 = r7
            goto L4c
        L4b:
            r0 = move-exception
        L4c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "getAuthorizationHeader failed because of request format error. "
            r4.<init>(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.String r4 = "HeaderUtils"
            com.huawei.phoneservice.faq.base.util.i.d(r4, r0)
            r4 = r3
        L64:
            java.lang.String r0 = "POST&"
            r1.append(r0)
            java.lang.String r0 = "/platform"
            boolean r7 = r7.contains(r0)
            java.lang.String r0 = "&"
            if (r7 == 0) goto Lb6
            if (r4 != 0) goto L76
            goto L7c
        L76:
            java.lang.String r7 = "channelID"
            java.lang.String r3 = r4.d(r7)
        L7c:
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.String r7 = java.lang.String.valueOf(r5)
            r1.append(r2)
            r1.append(r0)
            r1.append(r4)
            r1.append(r0)
            r1.append(r8)
            java.lang.String r8 = "&channelID="
            r1.append(r8)
            r1.append(r3)
            java.lang.String r8 = "&timestamp="
            r1.append(r8)
            r1.append(r7)
            java.lang.String r8 = r1.toString()
            java.lang.String r8 = com.huawei.phoneservice.feedbackcommon.utils.i.c(r8, r9)
            java.lang.Object[] r7 = new java.lang.Object[]{r3, r7, r8}
            java.lang.String r8 = "HMAC-SHA256 channelID={0}, timestamp={1}, signature=\"{2}\""
            java.lang.String r7 = java.text.MessageFormat.format(r8, r7)
            return r7
        Lb6:
            if (r4 != 0) goto Lb9
            goto Lbf
        Lb9:
            java.lang.String r7 = "appID"
            java.lang.String r3 = r4.d(r7)
        Lbf:
            java.lang.String r7 = "/"
            int r7 = r2.indexOf(r7)
            java.lang.String r7 = r2.substring(r7)
            r1.append(r7)
            r1.append(r0)
            r1.append(r4)
            r1.append(r0)
            r1.append(r8)
            java.lang.String r7 = "&appID="
            r1.append(r7)
            r1.append(r3)
            java.lang.String r7 = r1.toString()
            java.lang.String r7 = com.huawei.phoneservice.feedbackcommon.utils.i.c(r7, r9)
            java.lang.String r8 = "FeedBackPresenter1"
            com.huawei.phoneservice.faq.base.util.i.d(r8, r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r3, r7}
            java.lang.String r8 = "HMAC-SHA256 appID={0}, signature=\"{1}\""
            java.lang.String r7 = java.text.MessageFormat.format(r8, r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.h.d(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}
