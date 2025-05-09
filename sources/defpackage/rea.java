package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudHttpCallBack;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class rea {
    private static volatile rea d;
    private Context e;

    private rea(Context context) {
        LogUtil.a("UIDV_LightCloud", "LightCloud");
        this.e = context.getApplicationContext();
    }

    public static rea c(Context context) {
        LogUtil.a("UIDV_LightCloud", "getInstance");
        if (d == null) {
            synchronized (rea.class) {
                if (d == null) {
                    d = new rea(context);
                }
            }
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(final com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack r14, final java.util.List<java.lang.String> r15) {
        /*
            r13 = this;
            android.content.Context r0 = r13.e
            r1 = 10000(0x2710, float:1.4013E-41)
            java.lang.String r2 = java.lang.Integer.toString(r1)
            java.lang.String r3 = "lightcloudisoversea"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r2, r3)
            int r2 = health.compact.a.Utils.d()
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            r5 = 0
            if (r4 == 0) goto L2d
            android.content.Context r0 = r13.e
            health.compact.a.StorageParams r4 = new health.compact.a.StorageParams
            r4.<init>()
            java.lang.String r7 = java.lang.String.valueOf(r1)
            java.lang.String r8 = java.lang.String.valueOf(r2)
            health.compact.a.SharedPreferenceManager.e(r0, r7, r3, r8, r4)
            goto L39
        L2d:
            java.lang.String r3 = java.lang.String.valueOf(r2)
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L39
            r3 = r5
            goto L3c
        L39:
            r3 = 86400000(0x5265c00, double:4.2687272E-316)
        L3c:
            java.util.Iterator r0 = r15.iterator()
        L40:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L65
            java.lang.Object r7 = r0.next()
            java.lang.String r7 = (java.lang.String) r7
            android.content.Context r8 = r13.e
            java.lang.String r9 = java.lang.Integer.toString(r1)
            java.lang.String r7 = health.compact.a.SharedPreferenceManager.b(r8, r9, r7)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L64
            java.lang.String r8 = "1"
            boolean r7 = r8.equals(r7)
            if (r7 != 0) goto L40
        L64:
            r3 = r5
        L65:
            android.content.Context r0 = r13.e
            java.lang.String r7 = java.lang.Integer.toString(r1)
            java.lang.String r8 = "lightcloudbatchtime"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r7, r8)
            android.content.Context r7 = r13.e
            long r9 = health.compact.a.CommonUtil.n(r7, r0)
            long r11 = java.lang.System.currentTimeMillis()
            int r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r0 == 0) goto L98
            long r11 = r11 - r9
            int r0 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r0 >= 0) goto L98
            java.lang.String r15 = "doRefresh less than interval time"
            java.lang.Object[] r15 = new java.lang.Object[]{r15}
            java.lang.String r0 = "UIDV_LightCloud"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r15)
            java.lang.String r15 = ""
            r0 = 77777(0x12fd1, float:1.08989E-40)
            r14.onResponce(r15, r0)
            return
        L98:
            health.compact.a.StorageParams r0 = new health.compact.a.StorageParams
            r0.<init>()
            android.content.Context r3 = r13.e
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            health.compact.a.SharedPreferenceManager.e(r3, r1, r8, r4, r0)
            com.huawei.haf.threadpool.ThreadPoolManager r0 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            rdz r1 = new rdz
            r1.<init>()
            r0.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rea.b(com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack, java.util.List):void");
    }

    /* synthetic */ void d(final List list, final LightCloudCallBack lightCloudCallBack, final int i) {
        rej.d(GRSManager.a(this.e).getNoCheckUrl("getBatchPluginUrl", "") + "com.huawei.health_common_config", "", new LightCloudHttpCallBack() { // from class: reg
            @Override // com.huawei.ui.main.stories.lightcloud.service.LightCloudHttpCallBack
            public final void onResponce(int i2, String str) {
                rea.this.c(list, lightCloudCallBack, i, i2, str);
            }
        });
    }

    /* synthetic */ void c(List list, LightCloudCallBack lightCloudCallBack, int i, int i2, String str) {
        LogUtil.a("UIDV_LightCloud", "pullRefresh resCode = ", Integer.valueOf(i2));
        if (i2 == 200) {
            a(ref.c(str, (List<String>) list), lightCloudCallBack);
            SharedPreferenceManager.e(this.e, String.valueOf(10000), "lightcloudisoversea", String.valueOf(i), new StorageParams());
            return;
        }
        lightCloudCallBack.onResponce("", -1);
    }

    private void a(List<reh> list, final LightCloudCallBack lightCloudCallBack) {
        LogUtil.a("UIDV_LightCloud", "downloadBatch");
        if (list != null) {
            LogUtil.a("UIDV_LightCloud", "pullRefresh list.size =", Integer.valueOf(list.size()));
            if (list.size() == 0) {
                lightCloudCallBack.onResponce("", 20000);
            }
            for (final reh rehVar : list) {
                final long currentTimeMillis = System.currentTimeMillis();
                ree.e(this.e).e(rehVar, new LightCloudHttpCallBack() { // from class: rea.3
                    @Override // com.huawei.ui.main.stories.lightcloud.service.LightCloudHttpCallBack
                    public void onResponce(int i, String str) {
                        LogUtil.a("UIDV_LightCloud", "doDownload:", "resCode = ", Integer.valueOf(i), " result = ", str);
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (i == 0 && "success".equals(str)) {
                            ree.e(rea.this.e).a(rehVar, lightCloudCallBack);
                        } else {
                            reh rehVar2 = rehVar;
                            if (rehVar2 != null) {
                                lightCloudCallBack.onResponce(rehVar2.d(), -4);
                            } else {
                                lightCloudCallBack.onResponce("", -4);
                            }
                        }
                        reh rehVar3 = rehVar;
                        if (rehVar3 != null) {
                            rea.this.c(i, currentTimeMillis, currentTimeMillis2, rehVar3.d());
                        }
                    }
                });
            }
            return;
        }
        LogUtil.h("UIDV_LightCloud", "download list is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, long j, long j2, String str) {
        int i2 = i == 0 ? 0 : 1;
        long j3 = j2 - j;
        if ("servicefw_v1".equals(str)) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("target", "2");
            linkedHashMap.put(OpAnalyticsConstants.TARGET_SOURCE_TYPE, "4");
            linkedHashMap.put(OpAnalyticsConstants.DELAY_MS, String.valueOf(j3));
            linkedHashMap.put("flag", String.valueOf(i2));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEPEND_85040001.value(), linkedHashMap);
            return;
        }
        if ("airule_v1".equals(str)) {
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
            linkedHashMap2.put("target", "2");
            linkedHashMap2.put(OpAnalyticsConstants.TARGET_SOURCE_TYPE, "3");
            linkedHashMap2.put(OpAnalyticsConstants.DELAY_MS, String.valueOf(j3));
            linkedHashMap2.put("flag", String.valueOf(i2));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEPEND_85040001.value(), linkedHashMap2);
            return;
        }
        LogUtil.h("UIDV_LightCloud", "no branch!");
    }
}
