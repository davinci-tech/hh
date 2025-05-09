package defpackage;

import android.content.ContentValues;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ijq {
    private final ihj c;

    private ijq() {
        this.c = ihj.b();
    }

    static class a {
        private static final ijq d = new ijq();
    }

    public static ijq e() {
        return a.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(int r4, com.huawei.hihealth.HiSampleConfig r5) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            if (r1 != 0) goto L14
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            goto L15
        L14:
            r1 = r0
        L15:
            boolean r4 = r3.a(r4, r5)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            if (r1 == 0) goto L22
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
            defpackage.ivu.j(r5, r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L50
        L22:
            if (r1 == 0) goto L2b
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r0)
        L2b:
            return r4
        L2c:
            r4 = move-exception
            goto L33
        L2e:
            r4 = move-exception
            r1 = r0
            goto L51
        L31:
            r4 = move-exception
            r1 = r0
        L33:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "insertOrUpdateSampleConfig: "
            r5[r0] = r2     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r4)     // Catch: java.lang.Throwable -> L50
            r2 = 1
            r5[r2] = r4     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = "HiH_SampleConfigManager"
            com.huawei.hihealth.util.ReleaseLogUtil.d(r4, r5)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L4f
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r4, r0)
        L4f:
            return r0
        L50:
            r4 = move-exception
        L51:
            if (r1 == 0) goto L5a
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            defpackage.ivu.c(r5, r0)
        L5a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijq.b(int, com.huawei.hihealth.HiSampleConfig):boolean");
    }

    private boolean a(int i, HiSampleConfig hiSampleConfig) {
        long j;
        LogUtil.a("HiH_SampleConfigManager", "insertOrUpdateSampleConfig sampleConfig is ", hiSampleConfig);
        List<HiSampleConfig> a2 = a(iis.d().g(i), new HiSampleConfigKey.Builder().b(hiSampleConfig.getType()).d(hiSampleConfig.getConfigId()).e(hiSampleConfig.getScopeApp()).a(hiSampleConfig.getScopeDeviceType()).d());
        if (HiCommonUtil.d(a2)) {
            j = b(hiSampleConfig);
        } else {
            long j2 = 1;
            for (HiSampleConfig hiSampleConfig2 : a2) {
                if (hiSampleConfig2.getModifiedTime() < System.currentTimeMillis() && hiSampleConfig2.getModifiedTime() >= hiSampleConfig.getModifiedTime()) {
                    LogUtil.a("HiH_SampleConfigManager", "oldSampleConfig modifiedTime is bigger!");
                } else {
                    int d = d(hiSampleConfig2.getClientId(), hiSampleConfig);
                    if (d <= 0) {
                        j2 = d;
                    }
                }
            }
            j = j2;
        }
        return iil.a(j);
    }

    public List<HiSampleConfig> a(List<Integer> list, HiSampleConfigKey hiSampleConfigKey) {
        StringBuffer a2 = a(hiSampleConfigKey);
        String[] e = e(hiSampleConfigKey, list.size());
        iil.a("client_id", list, a2, e, e.length - list.size());
        return ihj.bxR_(this.c.query(a2.toString(), e, null, null, null));
    }

    public List<HiSampleConfig> d(List<Integer> list, HiSampleConfigKey hiSampleConfigKey, int i, int i2) {
        StringBuffer d = d(hiSampleConfigKey);
        String[] d2 = d(hiSampleConfigKey, list.size(), i);
        iil.a("client_id", list, d, d2, d2.length - list.size());
        if (i2 > 0) {
            iij.a(d, 0, i2);
        }
        return ihj.bxR_(this.c.query(d.toString(), d2, null, null, null));
    }

    public boolean b(List<Integer> list, HiSampleConfigKey hiSampleConfigKey, int i) {
        StringBuffer d = d(hiSampleConfigKey);
        String[] d2 = d(hiSampleConfigKey, list.size(), i);
        iil.a("client_id", list, d, d2, d2.length - list.size());
        return iil.a(this.c.delete(d.toString(), d2));
    }

    public boolean a(long j, int i) {
        return iil.a(this.c.update(ihj.bxP_(i), "_id =? ", new String[]{Long.toString(j)}));
    }

    private String[] e(HiSampleConfigKey hiSampleConfigKey, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.toString(2));
        if (hiSampleConfigKey.getType() != null) {
            arrayList.add(hiSampleConfigKey.getType());
        }
        if (hiSampleConfigKey.getConfigId() != null) {
            arrayList.add(hiSampleConfigKey.getConfigId());
        }
        if (hiSampleConfigKey.getScopeApp() != null) {
            arrayList.add(hiSampleConfigKey.getScopeApp());
        }
        if (hiSampleConfigKey.getScopeDeviceType() != null) {
            arrayList.add(hiSampleConfigKey.getScopeDeviceType());
        }
        String[] strArr = new String[arrayList.size() + i];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = (String) arrayList.get(i2);
        }
        return strArr;
    }

    private StringBuffer a(HiSampleConfigKey hiSampleConfigKey) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status");
        if (hiSampleConfigKey.getType() != null) {
            stringBuffer.append(" !=? and type");
        }
        if (hiSampleConfigKey.getConfigId() != null) {
            stringBuffer.append(" =? and config_id");
        }
        if (hiSampleConfigKey.getScopeApp() != null) {
            stringBuffer.append(" =? and scope_app");
        }
        if (hiSampleConfigKey.getScopeDeviceType() != null) {
            stringBuffer.append(" =? and scope_device_type");
        }
        stringBuffer.append(CommonUtil.EQUAL);
        return stringBuffer;
    }

    private String[] d(HiSampleConfigKey hiSampleConfigKey, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.toString(i2));
        if (hiSampleConfigKey != null) {
            if (hiSampleConfigKey.getType() != null) {
                arrayList.add(hiSampleConfigKey.getType());
            }
            if (hiSampleConfigKey.getConfigId() != null) {
                arrayList.add(hiSampleConfigKey.getConfigId());
            }
            if (hiSampleConfigKey.getScopeApp() != null) {
                arrayList.add(hiSampleConfigKey.getScopeApp());
            }
            if (hiSampleConfigKey.getScopeDeviceType() != null) {
                arrayList.add(hiSampleConfigKey.getScopeDeviceType());
            }
        }
        String[] strArr = new String[arrayList.size() + i];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            strArr[i3] = (String) arrayList.get(i3);
        }
        return strArr;
    }

    private StringBuffer d(HiSampleConfigKey hiSampleConfigKey) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status");
        if (hiSampleConfigKey != null) {
            if (hiSampleConfigKey.getType() != null) {
                stringBuffer.append(" =? and type");
            }
            if (hiSampleConfigKey.getConfigId() != null) {
                stringBuffer.append(" =? and config_id");
            }
            if (hiSampleConfigKey.getScopeApp() != null) {
                stringBuffer.append(" =? and scope_app");
            }
            if (hiSampleConfigKey.getScopeDeviceType() != null) {
                stringBuffer.append(" =? and scope_device_type");
            }
        }
        stringBuffer.append(CommonUtil.EQUAL);
        return stringBuffer;
    }

    private long b(HiSampleConfig hiSampleConfig) {
        return this.c.insert(ihj.bxN_(hiSampleConfig));
    }

    private int d(int i, HiSampleConfig hiSampleConfig) {
        ContentValues bxO_ = ihj.bxO_(hiSampleConfig);
        HiSampleConfigKey d = new HiSampleConfigKey.Builder().b(hiSampleConfig.getType()).d(hiSampleConfig.getConfigId()).e(hiSampleConfig.getScopeApp()).a(hiSampleConfig.getScopeDeviceType()).d();
        StringBuffer a2 = a(d);
        String[] e = e(d, 1);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i));
        iil.a("client_id", arrayList, a2, e, e.length - arrayList.size());
        return this.c.update(bxO_, a2.toString(), e);
    }
}
