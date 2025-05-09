package defpackage;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.scope.ScopeManager;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class ird {
    private static volatile ird c;
    private Context b;
    private ScopeManager e;

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, String> f13552a = new HashMap();
    private static Map<Integer, String> d = new HashMap();

    static {
        f13552a.put(101002, "com.huawei.android.hms.health.profile.readonly");
        f13552a.put(101001, "com.huawei.android.hms.health.huaweiid.readonly");
        f13552a.put(101003, "com.huawei.android.hms.health.rtsport.readonly");
        f13552a.put(101202, "com.huawei.android.hms.health.device.data.readonly");
        f13552a.put(101201, "com.huawei.android.hms.health.device.readonly");
        f13552a.put(47201, "com.huawei.android.hms.health.health.bo.readonly");
        f13552a.put(47202, "com.huawei.android.hms.health.health.bo.readonly");
        f13552a.put(47203, "com.huawei.android.hms.health.health.bo.readonly");
        f13552a.put(47204, "com.huawei.android.hms.health.health.bo.readonly");
        f13552a.put(40002, "com.huawei.android.hms.health.sport.readonly");
        f13552a.put(40004, "com.huawei.android.hms.health.sport.readonly");
        f13552a.put(40003, "com.huawei.android.hms.health.sport.readonly");
        f13552a.put(47101, "com.huawei.android.hms.health.sport.readonly");
        f13552a.put(30005, "com.huawei.android.hms.health.motionpath.readonly");
        f13552a.put(30006, "com.huawei.android.hms.health.motionpath.readonly");
        f13552a.put(30007, "com.huawei.android.hms.health.motionpath.readonly");
        f13552a.put(10008, "com.huawei.android.hms.health.health.hr.readonly");
        f13552a.put(50001, "com.huawei.android.hms.health.health.rthr.readonly");
        f13552a.put(10001, "com.huawei.android.hms.health.health.bg.readonly");
        f13552a.put(10002, "com.huawei.android.hms.health.health.bp.readonly");
        f13552a.put(10006, "com.huawei.android.hms.health.health.wgt.readonly");
        f13552a.put(2104, "com.huawei.android.hms.health.health.temperature.readonly");
        f13552a.put(10007, "com.huawei.android.hms.health.health.slp.readonly");
        f13552a.put(44000, "com.huawei.android.hms.health.health.slp.readonly");
        f13552a.put(2034, "com.huawei.android.hms.health.health.ps.readonly");
        f13552a.put(31001, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31002, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31003, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31004, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31005, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31006, "com.huawei.android.hms.health.health.ecg.readonly");
        f13552a.put(31007, "com.huawei.android.hms.health.health.ecg.readonly");
        d.put(10001, "com.huawei.android.hms.health.health.bg");
        d.put(10002, "com.huawei.android.hms.health.health.bp");
        d.put(10006, "com.huawei.android.hms.health.health.wgt");
        d.put(101202, "com.huawei.android.hms.health.device.data");
        d.put(101203, "com.huawei.android.hms.health.device.basicctrl");
        d.put(101204, "com.huawei.android.hms.health.device.advancedctrl");
        d.put(31001, "com.huawei.android.hms.health.health.ecg");
    }

    private ird(Context context) {
        if (context == null) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "context is null.");
            return;
        }
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "isTestThirdDeviceVersion.");
            return;
        }
        this.b = context;
        try {
            this.e = new ScopeManager(context);
        } catch (NoClassDefFoundError e) {
            ReleaseLogUtil.c("R_HiH_HiHwKitAsst", "HiHwKitAssistant constructor error: ", e.getMessage());
        }
    }

    public static ird d(Context context) {
        if (c == null) {
            synchronized (ird.class) {
                if (c == null) {
                    c = new ird(context);
                }
            }
        }
        return c;
    }

    public void d() {
        ReleaseLogUtil.e("R_HiH_HiHwKitAsst", "init...");
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "isTestThirdDeviceVersion.");
        } else if (this.e == null) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "init:mScopeManager is null");
        } else {
            a(Binder.getCallingPid(), Binder.getCallingUid(), iwi.a(this.b));
        }
    }

    public boolean a(String str) {
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "hasPermission:isTestThirdDeviceVersion.");
            return true;
        }
        if (this.e == null) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "hasPermission:mScopeManager is null");
            return false;
        }
        int callingUid = Binder.getCallingUid();
        return this.e.checkScopeAvailability(str, Binder.getCallingPid(), callingUid, "HiHealth_HiHwKitAssistant");
    }

    public boolean e(String str, int i, int i2) {
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "hasPermission:isTestThirdDeviceVersion.");
            return true;
        }
        ScopeManager scopeManager = this.e;
        if (scopeManager == null) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "hasPermission:mScopeManager is null");
            return false;
        }
        return scopeManager.checkScopeAvailability(str, i2, i, "HiHealth_HiHwKitAssistant");
    }

    public int[] b(int[] iArr, boolean z) {
        ArrayList arrayList = new ArrayList();
        Map<Integer, String> map = z ? f13552a : d;
        if (iArr != null && iArr.length > 0) {
            for (int i : iArr) {
                if (!a(map.get(Integer.valueOf(i)))) {
                    ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "getScopeTypes ", Integer.valueOf(i), " scope deny");
                } else {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        int[] iArr2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public ArrayList<HiHealthUserPermission> b(ArrayList<HiHealthUserPermission> arrayList, boolean z, int i, int i2) {
        ArrayList<HiHealthUserPermission> arrayList2 = new ArrayList<>();
        Map<Integer, String> map = z ? f13552a : d;
        Iterator<HiHealthUserPermission> it = arrayList.iterator();
        while (it.hasNext()) {
            HiHealthUserPermission next = it.next();
            if (e(map.get(Integer.valueOf(next.getScopeId())), i, i2)) {
                arrayList2.add(next);
            } else {
                LogUtil.h("R_HiH_HiHwKitAsst", Integer.valueOf(next.getScopeId()), " scope deny");
            }
        }
        return arrayList2;
    }

    public void a(int i, int i2, String str) {
        String b;
        String str2 = "";
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "initScopeManager,isTestThirdDeviceVersion.");
            return;
        }
        if (this.e == null) {
            ReleaseLogUtil.d("R_HiH_HiHwKitAsst", "initScopeManager:mScopeManager is null");
            return;
        }
        String c2 = HiScopeUtil.c(this.b, str);
        this.e.setAppId(i, i2, c2);
        try {
            if (ioy.b("initScopeManager", null)) {
                b = GRSManager.a(this.b).getNoCheckUrl("domainScopeOauth", "");
            } else {
                ReleaseLogUtil.e("R_HiH_HiHwKitAsst", "get scope property from local");
                b = iqv.b(this.b, "domainScopeOauth");
            }
            str2 = b;
        } catch (RemoteException e) {
            ReleaseLogUtil.c("R_HiH_HiHwKitAsst", "initScopeManager remote Exception: ", e.getMessage());
        }
        String str3 = str2 + "?nsp_svc=nsp.scope.app.get&nsp_fmt=json&type=2&appid=" + c2;
        LogUtil.c("R_HiH_HiHwKitAsst", "appId = ", c2, " validateUrl = ", str3);
        this.e.setScopeServerUrl(c2, str3);
    }

    public String b(int i) {
        return f13552a.containsKey(Integer.valueOf(i)) ? f13552a.get(Integer.valueOf(i)) : "";
    }

    public String a(int i) {
        return d.containsKey(Integer.valueOf(i)) ? d.get(Integer.valueOf(i)) : "";
    }
}
