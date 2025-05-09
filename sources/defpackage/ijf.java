package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ijf {
    private igy b;

    private ijf() {
        this.b = igy.d();
    }

    static class a {
        private static final ijf c = new ijf();
    }

    public static ijf d(Context context) {
        return a.c;
    }

    public static ijf e() {
        return a.c;
    }

    public long b(HiDeviceInfo hiDeviceInfo) {
        LogUtil.c("Debug_DeviceInfoManager", "insertDeviceInfoData()");
        return this.b.insert(iid.bzn_(hiDeviceInfo));
    }

    public HiDeviceInfo d(String str) {
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_DeviceInfoManager", "queryDeviceInfoData() deviceUUID = null");
            return null;
        }
        return iih.bAs_(this.b.query(c(), c(str), null, null, null));
    }

    private long a(HiDeviceInfo hiDeviceInfo) {
        if (hiDeviceInfo == null || HiCommonUtil.b(hiDeviceInfo.getDeviceUniqueCode())) {
            LogUtil.h("Debug_DeviceInfoManager", "updateDeviceInfoData() deviceUUID = null");
            return 0L;
        }
        ContentValues bzn_ = iid.bzn_(hiDeviceInfo);
        bzn_.remove("createTime");
        int update = this.b.update(bzn_, c(), c(hiDeviceInfo.getDeviceUniqueCode()));
        LogUtil.c("Debug_DeviceInfoManager", "updateDeviceInfoData() updateResult  updateResult = ", Integer.valueOf(update));
        return update;
    }

    private long c(HiDeviceInfo hiDeviceInfo) {
        new ContentValues().put("model", hiDeviceInfo.getModel());
        return this.b.update(r0, "device_unique_code =? ", new String[]{hiDeviceInfo.getDeviceUniqueCode()});
    }

    private boolean e(String str) {
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_DeviceInfoManager", "queryDeviceInfoDataExist() deviceUUID = null");
            return false;
        }
        return iih.bAv_(this.b.query(c(), c(str), null, null, null));
    }

    public boolean e(HiDeviceInfo hiDeviceInfo) {
        boolean z;
        long b;
        boolean equals = "-1".equals(hiDeviceInfo.getDeviceUniqueCode());
        try {
            if (equals) {
                ReleaseLogUtil.d("Debug_DeviceInfoManager", "deviceType or uuid is manual_input, register is not allowed");
                return false;
            }
            try {
                z = !ivu.i(BaseApplication.getContext(), 0) ? ivu.e(BaseApplication.getContext(), 0) : false;
                try {
                    if (e(hiDeviceInfo.getDeviceUniqueCode())) {
                        b = a(hiDeviceInfo);
                    } else {
                        b = b(hiDeviceInfo);
                        if (b > 0) {
                            HiBroadcastUtil.d(BaseApplication.getContext(), hiDeviceInfo);
                        }
                    }
                    if (z) {
                        ivu.j(BaseApplication.getContext(), 0);
                    }
                    boolean a2 = iil.a(b);
                    if (z) {
                        ivu.c(BaseApplication.getContext(), 0);
                    }
                    return a2;
                } catch (Exception e) {
                    e = e;
                    ReleaseLogUtil.d("Debug_DeviceInfoManager", "insertOrUpdateDeviceInfoData exception: ", LogAnonymous.b((Throwable) e));
                    if (z) {
                        ivu.c(BaseApplication.getContext(), 0);
                    }
                    return false;
                }
            } catch (Exception e2) {
                e = e2;
                z = false;
            } catch (Throwable th) {
                th = th;
                equals = false;
                if (equals) {
                    ivu.c(BaseApplication.getContext(), 0);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(com.huawei.hihealth.HiDeviceInfo r5) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            if (r1 != 0) goto L14
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            goto L15
        L14:
            r1 = r0
        L15:
            java.lang.String r2 = r5.getDeviceUniqueCode()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
            boolean r2 = r4.e(r2)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
            if (r2 == 0) goto L24
            long r2 = r4.c(r5)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
            goto L26
        L24:
            r2 = 0
        L26:
            if (r1 == 0) goto L2f
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
            defpackage.ivu.j(r5, r0)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
        L2f:
            boolean r5 = defpackage.iil.a(r2)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L61
            if (r1 == 0) goto L3c
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r1, r0)
        L3c:
            return r5
        L3d:
            r5 = move-exception
            goto L44
        L3f:
            r5 = move-exception
            r1 = r0
            goto L62
        L42:
            r5 = move-exception
            r1 = r0
        L44:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L61
            java.lang.String r3 = "insertOrUpdatePhoneModel exception: "
            r2[r0] = r3     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L61
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "Debug_DeviceInfoManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r5, r2)     // Catch: java.lang.Throwable -> L61
            if (r1 == 0) goto L60
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r5, r0)
        L60:
            return r0
        L61:
            r5 = move-exception
        L62:
            if (r1 == 0) goto L6b
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r1, r0)
        L6b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijf.d(com.huawei.hihealth.HiDeviceInfo):boolean");
    }

    public int a(String str) {
        LogUtil.c("Debug_DeviceInfoManager", "getDeviceId");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("Debug_DeviceInfoManager", "getDeviceId() deviceUUID = null");
            return 0;
        }
        return iih.bAH_(this.b.query(c(), c(str), null, null, null), "_id");
    }

    public HiDeviceInfo a(int i) {
        return iih.bAs_(this.b.query("_id =? ", new String[]{Integer.toString(i)}, null, null, null));
    }

    public List<HiDeviceInfo> b(List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("_id", list, stringBuffer, strArr, 0);
        return iih.bAt_(this.b.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int bBl_(int i, ContentValues contentValues) {
        return this.b.update(contentValues, "_id =? ", new String[]{Integer.toString(i)});
    }

    private String[] c(String str) {
        return new String[]{str};
    }

    private String c() {
        return "device_unique_code =? ";
    }
}
