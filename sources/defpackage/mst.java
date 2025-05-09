package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceFittingsBean;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class mst {
    private static mst b;
    private static final Object c = new Object();
    private mss d = null;

    /* renamed from: a, reason: collision with root package name */
    private List<msx> f15156a = Collections.synchronizedList(new ArrayList(16));

    private mst() {
    }

    public static mst a() {
        mst mstVar;
        synchronized (c) {
            if (b == null) {
                b = new mst();
            }
            mstVar = b;
        }
        return mstVar;
    }

    public List<msx> c() {
        this.d = d();
        this.f15156a.clear();
        mss mssVar = this.d;
        if (mssVar == null) {
            return new ArrayList(0);
        }
        List<mta> b2 = mssVar.b();
        if (b2 != null && !b2.isEmpty()) {
            this.f15156a.addAll(e(b2));
        }
        return new ArrayList(this.f15156a);
    }

    private List<msx> e(List<mta> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list == null) {
            LogUtil.h("DevicePluginInfoManager", "kindBeans is null");
            return arrayList;
        }
        for (mta mtaVar : list) {
            List<msx> e = mtaVar.e();
            if (koq.b(e)) {
                LogUtil.h("DevicePluginInfoManager", "devicePluginInfoBeans is null");
            } else {
                for (msx msxVar : e) {
                    if (msxVar == null) {
                        LogUtil.h("DevicePluginInfoManager", mtaVar.c(), " devicePluginInfoBean is null");
                    } else {
                        arrayList.add(msxVar);
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean d(String str) {
        if (koq.b(this.f15156a)) {
            LogUtil.h("DevicePluginInfoManager", "isIntelligentDevice mAllDeviceList is null, reload deviceList");
            this.f15156a = c();
        }
        ArrayList<msx> arrayList = new ArrayList(16);
        arrayList.addAll(this.f15156a);
        if (koq.b(arrayList)) {
            LogUtil.h("DevicePluginInfoManager", "isIntelligentDevice allList is empty");
            return false;
        }
        for (msx msxVar : arrayList) {
            if (msxVar != null && !koq.b(msxVar.k()) && e(msxVar) && str.equals(msxVar.k().get(0))) {
                return true;
            }
        }
        return false;
    }

    public boolean e(msx msxVar) {
        return msxVar != null && msxVar.h() == 1;
    }

    public void b() {
        File file = new File(msv.a());
        boolean exists = file.exists();
        LogUtil.a("DevicePluginInfoManager", "updateIndexCacheForWear isExistThisIndex is = ", Boolean.valueOf(exists));
        if (exists) {
            a(mrx.e(file));
        } else {
            this.d = null;
        }
    }

    private mss d() {
        if (this.d == null) {
            b();
        }
        mss mssVar = this.d;
        if (mssVar != null) {
            LogUtil.a("DevicePluginInfoManager", "mIndexAllBean, version: ", mssVar.a());
            f();
        }
        return this.d;
    }

    private void a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DevicePluginInfoManager", "parseVersionIndexFile indexJson is null");
            return;
        }
        try {
            mss mssVar = this.d;
            if (mssVar == null) {
                str2 = "";
            } else {
                str2 = mssVar.a();
            }
            if (TextUtils.isEmpty(str2)) {
                this.d = msw.e(str);
                f();
            } else {
                if (str2.equals(new JSONObject(str).optString("version"))) {
                    return;
                }
                this.d = msw.e(str);
                f();
            }
        } catch (JSONException unused) {
            LogUtil.b("DevicePluginInfoManager", "parseVersionIndexFile JSONException");
        }
    }

    private void f() {
        mss mssVar = this.d;
        boolean c2 = mssVar != null ? c("img_index_all", mssVar.c()) : false;
        mss mssVar2 = this.d;
        boolean c3 = mssVar2 != null ? c("lang_index_all", mssVar2.d()) : false;
        if (!c2 || !c3) {
            this.d = null;
        }
        if (e()) {
            return;
        }
        this.d = null;
    }

    private boolean e() {
        return new File(msv.c()).exists();
    }

    private boolean c(String str, String str2) {
        String[] list;
        File file = new File(msv.c(str) + str2 + File.separator);
        return file.exists() && file.isDirectory() && (list = file.list()) != null && list.length != 0;
    }

    public List<msx> c(String str) {
        List<String> k;
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DevicePluginInfoManager", "getDeviceInfoByUuid, uuid is empty");
            return arrayList;
        }
        ArrayList<msx> arrayList2 = new ArrayList(16);
        if (koq.b(this.f15156a)) {
            LogUtil.h("DevicePluginInfoManager", "isIntelligentDevice  mAllDeviceList is null, reload deviceList");
            this.f15156a = c();
        }
        arrayList2.addAll(this.f15156a);
        if (koq.b(arrayList2)) {
            return arrayList;
        }
        for (msx msxVar : arrayList2) {
            if (msxVar != null && (k = msxVar.k()) != null && !k.isEmpty() && k.contains(str)) {
                arrayList.add(msxVar);
            }
        }
        return arrayList;
    }

    public List<DeviceFittingsBean> a(String str, String str2) {
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("DevicePluginInfoManager", "getFittingDeviceInfoByBluetoothName, hostProductId or bluetooth is empty");
            return arrayList;
        }
        List<msx> c2 = c(str);
        if (koq.b(c2)) {
            return arrayList;
        }
        msx msxVar = c2.get(0);
        String lowerCase = str2.toLowerCase(Locale.ENGLISH);
        if (msxVar == null) {
            return arrayList;
        }
        List<String> k = msxVar.k();
        if (!koq.b(k) && k.contains(str)) {
            List<DeviceFittingsBean> b2 = msxVar.b();
            if (koq.b(b2)) {
                return arrayList;
            }
            for (DeviceFittingsBean deviceFittingsBean : b2) {
                if (deviceFittingsBean != null) {
                    String bluetoothMatch = deviceFittingsBean.getBluetoothMatch();
                    if (!TextUtils.isEmpty(bluetoothMatch)) {
                        List<String> bluetoothNameList = deviceFittingsBean.getBluetoothNameList();
                        if (!koq.b(bluetoothNameList)) {
                            for (String str3 : bluetoothNameList) {
                                if (!TextUtils.isEmpty(str3) && e(bluetoothMatch, lowerCase, str3.toLowerCase(Locale.ENGLISH))) {
                                    arrayList.add(deviceFittingsBean);
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        if (r7 != 4) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto L15
            goto L83
        L15:
            r7.hashCode()
            int r0 = r7.hashCode()
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case -1926781294: goto L50;
                case -1838093487: goto L45;
                case 66096429: goto L3a;
                case 66409183: goto L2f;
                case 1669509300: goto L24;
                default: goto L23;
            }
        L23:
            goto L5b
        L24:
            java.lang.String r0 = "CONTAIN"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L2d
            goto L5b
        L2d:
            r7 = r2
            goto L5c
        L2f:
            java.lang.String r0 = "EXACT"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L38
            goto L5b
        L38:
            r7 = r3
            goto L5c
        L3a:
            java.lang.String r0 = "EMPTY"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L43
            goto L5b
        L43:
            r7 = r4
            goto L5c
        L45:
            java.lang.String r0 = "SUFFIX"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L4e
            goto L5b
        L4e:
            r7 = r5
            goto L5c
        L50:
            java.lang.String r0 = "PREFIX"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L59
            goto L5b
        L59:
            r7 = r1
            goto L5c
        L5b:
            r7 = -1
        L5c:
            if (r7 == 0) goto L7c
            if (r7 == r5) goto L75
            if (r7 == r4) goto L6e
            if (r7 == r3) goto L67
            if (r7 == r2) goto L6e
            goto L83
        L67:
            boolean r7 = java.util.Objects.equals(r8, r9)
            if (r7 == 0) goto L83
            return r5
        L6e:
            boolean r7 = r8.contains(r9)
            if (r7 == 0) goto L83
            return r5
        L75:
            boolean r7 = r8.endsWith(r9)
            if (r7 == 0) goto L83
            return r5
        L7c:
            boolean r7 = r8.startsWith(r9)
            if (r7 == 0) goto L83
            return r5
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mst.e(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
