package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class mdq {
    public static void a(Context context, HashMap<String, String> hashMap) {
        if (context == null || hashMap == null) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            LogUtil.a("PLGACHIEVE_AchieveMedalParserUtil", "refreshFirstTabRedPointSP firstabname =", key, " newMedal =", value);
            mct.b(context, key, value);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c8 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(java.lang.String r19, org.json.JSONObject r20, java.util.List<defpackage.mcz> r21) {
        /*
            r12 = r19
            r0 = r20
            boolean r1 = defpackage.koq.b(r21)
            java.lang.String r13 = "PLGACHIEVE_AchieveMedalParserUtil"
            if (r1 != 0) goto Ld8
            boolean r1 = android.text.TextUtils.isEmpty(r19)
            if (r1 == 0) goto L14
            goto Ld8
        L14:
            r1 = 1
            java.util.Iterator r2 = r21.iterator()     // Catch: org.json.JSONException -> L88
        L19:
            boolean r3 = r2.hasNext()     // Catch: org.json.JSONException -> L88
            if (r3 == 0) goto L7f
            java.lang.Object r3 = r2.next()     // Catch: org.json.JSONException -> L88
            mcz r3 = (defpackage.mcz) r3     // Catch: org.json.JSONException -> L88
            boolean r4 = r3 instanceof com.huawei.pluginachievement.manager.model.MedalConfigInfo     // Catch: org.json.JSONException -> L88
            if (r4 != 0) goto L2a
            goto L19
        L2a:
            com.huawei.pluginachievement.manager.model.MedalConfigInfo r3 = (com.huawei.pluginachievement.manager.model.MedalConfigInfo) r3     // Catch: org.json.JSONException -> L88
            java.lang.String r4 = r3.acquireMedalID()     // Catch: org.json.JSONException -> L88
            boolean r4 = r12.equals(r4)     // Catch: org.json.JSONException -> L88
            if (r4 == 0) goto L19
            java.lang.String r2 = r3.acquireGrayListStyle()     // Catch: org.json.JSONException -> L88
            java.lang.String r4 = "grayListStyle"
            java.lang.String r4 = r0.getString(r4)     // Catch: org.json.JSONException -> L88
            boolean r2 = e(r2, r4)     // Catch: org.json.JSONException -> L88
            java.lang.String r4 = r3.acquireGrayDetailStyle()     // Catch: org.json.JSONException -> L89
            java.lang.String r5 = "grayDetailStyle"
            java.lang.String r5 = r0.getString(r5)     // Catch: org.json.JSONException -> L89
            boolean r4 = e(r4, r5)     // Catch: org.json.JSONException -> L89
            java.lang.String r5 = r3.acquireLightListStyle()     // Catch: org.json.JSONException -> L8a
            java.lang.String r6 = "lightListStyle"
            java.lang.String r6 = r0.getString(r6)     // Catch: org.json.JSONException -> L8a
            boolean r5 = e(r5, r6)     // Catch: org.json.JSONException -> L8a
            java.lang.String r6 = r3.acquireLightDetailStyle()     // Catch: org.json.JSONException -> L7d
            java.lang.String r7 = "lightDetailStyle"
            java.lang.String r7 = r0.getString(r7)     // Catch: org.json.JSONException -> L7d
            boolean r6 = e(r6, r7)     // Catch: org.json.JSONException -> L7d
            java.lang.String r3 = r3.acquireShareImageUrl()     // Catch: org.json.JSONException -> L8c
            java.lang.String r7 = "shareImageUrl"
            java.lang.String r0 = r0.getString(r7)     // Catch: org.json.JSONException -> L8c
            boolean r1 = e(r3, r0)     // Catch: org.json.JSONException -> L8c
            goto L95
        L7d:
            r6 = r1
            goto L8c
        L7f:
            r14 = r1
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            goto L9d
        L88:
            r2 = r1
        L89:
            r4 = r1
        L8a:
            r5 = r1
            r6 = r5
        L8c:
            java.lang.String r0 = "refreshMedalSp Exception"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r13, r0)
        L95:
            r18 = r1
            r14 = r2
            r15 = r4
            r16 = r5
            r17 = r6
        L9d:
            java.lang.String r0 = "refreshMedalSp Id "
            java.lang.String r2 = "isGrayListC "
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r14)
            java.lang.String r4 = "isGrayDetailC "
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r15)
            java.lang.String r6 = "isLightListC "
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r16)
            java.lang.String r8 = "isLightDetailC "
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r17)
            java.lang.String r10 = "isShareC "
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r18)
            r1 = r19
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11}
            com.huawei.hwlogsmodel.LogUtil.a(r13, r0)
            if (r14 != 0) goto Ld0
            if (r16 != 0) goto Ld0
            if (r15 != 0) goto Ld0
            if (r17 != 0) goto Ld0
            if (r18 == 0) goto Ld7
        Ld0:
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            c(r12, r0)
        Ld7:
            return
        Ld8:
            java.lang.String r0 = "refreshMedalSp medalConfigDb isEmpty."
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r12}
            com.huawei.hwlogsmodel.LogUtil.a(r13, r0)
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            c(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mdq.c(java.lang.String, org.json.JSONObject, java.util.List):void");
    }

    private static boolean e(String str, String str2) {
        if (str == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalParserUtil", "isMedalUrlChange dbUrl is null.");
            return true;
        }
        return !str.equals(str2);
    }

    public static void c(String str, Context context) {
        if (context == null) {
            LogUtil.c("PLGACHIEVE_AchieveMedalParserUtil", "refreshSharePrefrence context is null");
            return;
        }
        String e = mfg.e(mct.b(context, "_medalPngStatusDownload"), str);
        if (TextUtils.isEmpty(e)) {
            LogUtil.c("PLGACHIEVE_AchieveMedalParserUtil", "refreshSharePrefrence failed");
        } else {
            mct.b(context, "_medalPngStatusDownload", e);
        }
    }

    public static void a(String str, Context context) {
        if (context == null) {
            LogUtil.c("PLGACHIEVE_AchieveMedalParserUtil", "refreshTextureSharePrefrence context is null");
            return;
        }
        String d = mfg.d(mct.b(context, "_medalTextureDownload"), str);
        if (TextUtils.isEmpty(d)) {
            LogUtil.c("PLGACHIEVE_AchieveMedalParserUtil", "refreshTextureSharePrefrence failed");
        } else {
            mct.b(context, "_medalTextureDownload", d);
        }
    }

    public static boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (Integer.parseInt(str) > 102) {
                if (TextUtils.isEmpty(str2)) {
                    if (!TextUtils.isEmpty(str3)) {
                        try {
                            if (System.currentTimeMillis() > Long.parseLong(str3) + 2592000000L) {
                                return false;
                            }
                        } catch (NumberFormatException unused) {
                            LogUtil.b("PLGACHIEVE_AchieveMedalParserUtil", "NumberFormatException");
                        }
                    }
                    return true;
                }
                try {
                } catch (NumberFormatException unused2) {
                    LogUtil.b("PLGACHIEVE_AchieveMedalParserUtil", "NumberFormatException");
                }
                return System.currentTimeMillis() <= Long.parseLong(str2);
            }
        } catch (NumberFormatException unused3) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParserUtil", "NumberFormatException");
        }
        return false;
    }

    public static boolean c(String str, String str2, String str3) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3);
    }
}
