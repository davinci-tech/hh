package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;

/* loaded from: classes5.dex */
public class fg implements gb {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6871a = new byte[0];
    private static gb c;
    private final Context b;

    @Override // com.huawei.openalliance.ad.gb
    public int d(String str) {
        return e(str).getInt("resultAdReqType", 0);
    }

    @Override // com.huawei.openalliance.ad.gb
    public boolean c(String str) {
        return e(str).getBoolean(ConfigMapKeys.CLICK_VALID_CTRL, false);
    }

    @Override // com.huawei.openalliance.ad.gb
    public int b(String str) {
        return e(str).getInt("recommendPath", 0);
    }

    @Override // com.huawei.openalliance.ad.gb
    public boolean a(String str) {
        return e(str).getBoolean("recommendEnabled", false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0037, code lost:
    
        if (r0.intValue() == 1) goto L8;
     */
    @Override // com.huawei.openalliance.ad.gb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            r5 = this;
            android.content.SharedPreferences r6 = r5.e(r6)
            android.content.SharedPreferences$Editor r6 = r6.edit()
            java.lang.String r0 = "deAdRecmdEbl"
            java.lang.Object r0 = r7.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = com.huawei.openalliance.ad.utils.cz.h(r0)
            java.lang.String r1 = "fsdkDeRcmdWay"
            java.lang.Object r1 = r7.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Integer r1 = com.huawei.openalliance.ad.utils.cz.h(r1)
            java.lang.String r2 = "1"
            java.lang.String r3 = "clickValidCtrl"
            java.lang.Object r4 = r7.get(r3)
            boolean r2 = r2.equals(r4)
            r6.putBoolean(r3, r2)
            r2 = 0
            if (r0 == 0) goto L3a
            int r0 = r0.intValue()
            r3 = 1
            if (r0 != r3) goto L3a
            goto L3b
        L3a:
            r3 = r2
        L3b:
            java.lang.String r0 = "recommendEnabled"
            r6.putBoolean(r0, r3)
            if (r1 == 0) goto L47
            int r0 = r1.intValue()
            goto L48
        L47:
            r0 = r2
        L48:
            java.lang.String r1 = "recommendPath"
            r6.putInt(r1, r0)
            java.lang.String r0 = "resultAdReqType"
            java.lang.Object r7 = r7.get(r0)
            java.lang.String r7 = (java.lang.String) r7
            int r7 = com.huawei.openalliance.ad.utils.cz.a(r7, r2)
            r6.putInt(r0, r7)
            r6.apply()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.fg.a(java.lang.String, java.util.Map):void");
    }

    private SharedPreferences e(String str) {
        return this.b.getSharedPreferences("hiad_slot_cfg_" + str, 0);
    }

    private static gb b(Context context) {
        gb gbVar;
        synchronized (f6871a) {
            if (c == null) {
                c = new fg(context);
            }
            gbVar = c;
        }
        return gbVar;
    }

    public static gb a(Context context) {
        return b(context);
    }

    private fg(Context context) {
        this.b = com.huawei.openalliance.ad.utils.x.i(context.getApplicationContext());
    }
}
