package com.huawei.hms.health;

import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.hihealth.HiHealthKitClient;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.data.HealthKitApiInvoker;
import com.huawei.hms.hihealth.data.HealthKitApiResult;
import com.huawei.hms.hihealth.options.aaba;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.support.api.client.Status;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class aaci {
    private static volatile aaci aabk;
    private static volatile HealthKitApiInvoker aabl;
    private long aab;
    private com.huawei.hms.hihealth.data.aab aaba;
    private com.huawei.hms.hihealth.data.aaba aabe;
    private int aabf;
    private long aabg;
    private long aabh;
    private long aabj;
    private Map<String, com.huawei.hms.hihealth.data.aabb> aabb = new HashMap();
    private Map<String, Pair<String, Long>> aabc = new HashMap();
    private Map<String, Long> aabd = new HashMap();
    private int aabi = -1;

    private String aab(HealthKitApiInvoker healthKitApiInvoker, boolean z) throws RemoteException, ApiException {
        boolean z2;
        if (HiHealthKitClient.getInstance().bindService(100) == null) {
            throw new ApiException(new Status(HiHealthStatusCodes.API_EXCEPTION_ERROR, "the client is not connected"));
        }
        if (!z) {
            HealthKitApiResult healthKitApiResult = (HealthKitApiResult) aacs.aab(aack.aabe().aabb().aabb(aacs.aab(healthKitApiInvoker)), (Type) HealthKitApiResult.class);
            if (healthKitApiResult == null) {
                aabz.aab("CommonMethodImpl", "result from core is null");
                return "";
            }
            if (!healthKitApiResult.isResultGzipped()) {
                return healthKitApiResult.getResponse();
            }
            StringBuilder aab2 = com.huawei.hms.health.aab.aab("the result length from core is ");
            aab2.append(healthKitApiResult.getResponse().length());
            aabz.aab("CommonMethodImpl", aab2.toString());
            return aacs.aabd(healthKitApiResult.getResponse());
        }
        String aab3 = aacs.aab(healthKitApiInvoker);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList();
        aack.aabe().aabb().aab(aab3, new aab(countDownLatch, arrayList));
        try {
            z2 = countDownLatch.await(1L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            aabz.aab("CommonMethodImpl", "invokeReadInterface interruptedException");
            z2 = false;
        }
        String str = null;
        if (!z2) {
            aabz.aab("CommonMethodImpl", "invokeReadInterface time Out");
            return null;
        }
        if (arrayList.isEmpty()) {
            aabz.aabc("CommonMethodImpl", "build result fail for return empty body");
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
            }
            aabz.aabb("CommonMethodImpl", "build result by StringBuilder appending");
            str = sb.toString();
        }
        HealthKitApiResult healthKitApiResult2 = (HealthKitApiResult) aacs.aab(str, (Type) HealthKitApiResult.class);
        if (healthKitApiResult2 != null) {
            return healthKitApiResult2.isResultGzipped() ? aacs.aabd(healthKitApiResult2.getResponse()) : healthKitApiResult2.getResponse();
        }
        aabz.aab("CommonMethodImpl", "result from core is null");
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0117 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String aaba(com.huawei.hms.hihealth.data.HealthKitApiInvoker r10, boolean r11) throws android.os.RemoteException, com.huawei.hms.common.ApiException {
        /*
            Method dump skipped, instructions count: 577
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aaci.aaba(com.huawei.hms.hihealth.data.HealthKitApiInvoker, boolean):java.lang.String");
    }

    private String aabc(HealthKitApiInvoker healthKitApiInvoker) {
        return healthKitApiInvoker.getInterfaceProvider() + "." + healthKitApiInvoker.getInterfaceInvoked();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005f A[Catch: all -> 0x008a, TryCatch #1 {, blocks: (B:6:0x0007, B:8:0x000b, B:28:0x002e, B:31:0x0035, B:11:0x0051, B:13:0x005f, B:16:0x0066, B:18:0x006a, B:20:0x0076, B:21:0x0085, B:24:0x007e, B:34:0x004a, B:36:0x0088), top: B:5:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0076 A[Catch: RuntimeException -> 0x0079, NameNotFoundException -> 0x007c, all -> 0x008a, TRY_LEAVE, TryCatch #1 {, blocks: (B:6:0x0007, B:8:0x000b, B:28:0x002e, B:31:0x0035, B:11:0x0051, B:13:0x005f, B:16:0x0066, B:18:0x006a, B:20:0x0076, B:21:0x0085, B:24:0x007e, B:34:0x004a, B:36:0x0088), top: B:5:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.health.aaci aabc() {
        /*
            com.huawei.hms.health.aaci r0 = com.huawei.hms.health.aaci.aabk
            if (r0 != 0) goto L8d
            java.lang.Class<com.huawei.hms.health.aaci> r0 = com.huawei.hms.health.aaci.class
            monitor-enter(r0)
            com.huawei.hms.health.aaci r1 = com.huawei.hms.health.aaci.aabk     // Catch: java.lang.Throwable -> L8a
            if (r1 != 0) goto L88
            com.huawei.hms.health.aaci r1 = new com.huawei.hms.health.aaci     // Catch: java.lang.Throwable -> L8a
            r1.<init>()     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.health.aaci.aabk = r1     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.data.HealthKitApiInvoker r1 = new com.huawei.hms.hihealth.data.HealthKitApiInvoker     // Catch: java.lang.Throwable -> L8a
            r1.<init>()     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.health.aaci.aabl = r1     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.HiHealthKitClient r1 = com.huawei.hms.hihealth.HiHealthKitClient.getInstance()     // Catch: java.lang.Throwable -> L8a
            android.content.Context r1 = r1.getContext()     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.data.HealthKitApiInvoker r2 = com.huawei.hms.health.aaci.aabl     // Catch: java.lang.Throwable -> L8a
            java.lang.String r3 = com.huawei.hms.utils.Util.getAppId(r1)     // Catch: java.lang.Throwable -> L8a
            r2.setAppId(r3)     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.data.HealthKitApiInvoker r2 = com.huawei.hms.health.aaci.aabl     // Catch: java.lang.Throwable -> L8a
            if (r1 == 0) goto L4f
            android.content.pm.PackageManager r3 = r1.getPackageManager()     // Catch: java.lang.Exception -> L45 android.content.pm.PackageManager.NameNotFoundException -> L48 java.lang.Throwable -> L8a
            if (r3 != 0) goto L35
            goto L4f
        L35:
            android.content.pm.PackageManager r3 = r1.getPackageManager()     // Catch: java.lang.Exception -> L45 android.content.pm.PackageManager.NameNotFoundException -> L48 java.lang.Throwable -> L8a
            java.lang.String r4 = r1.getPackageName()     // Catch: java.lang.Exception -> L45 android.content.pm.PackageManager.NameNotFoundException -> L48 java.lang.Throwable -> L8a
            r5 = 0
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r5)     // Catch: java.lang.Exception -> L45 android.content.pm.PackageManager.NameNotFoundException -> L48 java.lang.Throwable -> L8a
            java.lang.String r3 = r3.packageName     // Catch: java.lang.Exception -> L45 android.content.pm.PackageManager.NameNotFoundException -> L48 java.lang.Throwable -> L8a
            goto L51
        L45:
            java.lang.String r3 = "catch basic exception"
            goto L4a
        L48:
            java.lang.String r3 = "can not find PackageManager"
        L4a:
            java.lang.String r4 = "PackageManagerUtil"
            com.huawei.hms.health.aabz.aab(r4, r3)     // Catch: java.lang.Throwable -> L8a
        L4f:
            java.lang.String r3 = ""
        L51:
            r2.setPackageName(r3)     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.data.HealthKitApiInvoker r2 = com.huawei.hms.health.aaci.aabl     // Catch: java.lang.Throwable -> L8a
            java.lang.String r3 = "6.14.0.401"
            r2.setSdkVersion(r3)     // Catch: java.lang.Throwable -> L8a
            com.huawei.hms.hihealth.data.HealthKitApiInvoker r2 = com.huawei.hms.health.aaci.aabl     // Catch: java.lang.Throwable -> L8a
            if (r1 == 0) goto L83
            android.content.pm.PackageManager r3 = r1.getPackageManager()     // Catch: java.lang.Throwable -> L8a
            if (r3 != 0) goto L66
            goto L83
        L66:
            android.content.pm.PackageManager r3 = r1.getPackageManager()     // Catch: java.lang.Throwable -> L8a
            java.lang.String r1 = r1.getPackageName()     // Catch: java.lang.RuntimeException -> L79 android.content.pm.PackageManager.NameNotFoundException -> L7c java.lang.Throwable -> L8a
            r4 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r1 = r3.getPackageInfo(r1, r4)     // Catch: java.lang.RuntimeException -> L79 android.content.pm.PackageManager.NameNotFoundException -> L7c java.lang.Throwable -> L8a
            if (r1 == 0) goto L83
            java.lang.String r1 = r1.versionName     // Catch: java.lang.RuntimeException -> L79 android.content.pm.PackageManager.NameNotFoundException -> L7c java.lang.Throwable -> L8a
            goto L85
        L79:
            java.lang.String r1 = "getPackageInfo exists exception!"
            goto L7e
        L7c:
            java.lang.String r1 = "getAppVersion NameNotFoundException"
        L7e:
            java.lang.String r3 = "PackageManagerUtil"
            com.huawei.hms.health.aabz.aab(r3, r1)     // Catch: java.lang.Throwable -> L8a
        L83:
            java.lang.String r1 = ""
        L85:
            r2.setAppVersion(r1)     // Catch: java.lang.Throwable -> L8a
        L88:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8a
            goto L8d
        L8a:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8a
            throw r1
        L8d:
            com.huawei.hms.health.aaci r0 = com.huawei.hms.health.aaci.aabk
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aaci.aabc():com.huawei.hms.health.aaci");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aab(com.huawei.hms.hihealth.data.aabc aabcVar) {
        Objects.toString(aabcVar);
        if (aabcVar == null) {
            return;
        }
        this.aab = System.currentTimeMillis();
        this.aaba = aabcVar.aab();
        if (aabz.aaba(aabcVar.aaba()).booleanValue()) {
            for (com.huawei.hms.hihealth.data.aabb aabbVar : aabcVar.aaba()) {
                this.aabb.put(aabbVar.aaba(), aabbVar);
            }
        }
        this.aabe = aabcVar.aabb();
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(this.aaba != null);
        objArr[1] = Integer.valueOf(this.aabb.size());
        objArr[2] = Boolean.valueOf(this.aabe != null);
        Log.i("HmsHealth_kit CommonMethodImpl", aacs.aab("getInterfacePolicy success bgLimit %s, cachePolicy size %s, flow %s", objArr));
    }

    private boolean aab(HealthKitApiInvoker healthKitApiInvoker, String str) {
        int i;
        boolean z = false;
        if (!"AuthController".equals(healthKitApiInvoker.getInterfaceProvider())) {
            return false;
        }
        Long l = this.aabd.get(str);
        if (l != null) {
            long longValue = l.longValue();
            com.huawei.hms.hihealth.data.aabb aabbVar = this.aabb.get(aacs.aab("AuthController", "TIME_WINDOW"));
            if (aabbVar != null) {
                aabz.aabb("CommonMethodImpl", "getInvokeTimeWindow with timeWindowPolicy");
                i = aabbVar.aab();
            } else {
                aabz.aabb("CommonMethodImpl", "getInvokeTimeWindow with default TimeWindow");
                i = AwarenessStatusCodes.AWARENESS_APPLICATION_NOT_HUAWEI_PHONE;
            }
            if (aacs.aab(longValue, i)) {
                z = true;
            }
        }
        aabz.aabb("CommonMethodImpl", "timeWindow lastTime " + l + " result " + z);
        this.aabd.put(str, Long.valueOf(System.currentTimeMillis()));
        return z;
    }

    private void aaba(HealthKitApiInvoker healthKitApiInvoker, String str) {
        if (this.aabb.get(aabc(healthKitApiInvoker)) != null) {
            String aabb = aabb(healthKitApiInvoker);
            if (!"AuthController".equals(healthKitApiInvoker.getInterfaceProvider()) || (!TextUtils.isEmpty(str) && !"[]".equals(str))) {
                this.aabc.put(aabb, new Pair<>(str, Long.valueOf(System.currentTimeMillis())));
            } else {
                aabz.aabb("CommonMethodImpl", "skip cache empty result, and clear cache");
                this.aabc.remove(aabb);
            }
        }
    }

    public int aabb() {
        com.huawei.hms.hihealth.data.aab aabVar = this.aaba;
        if (aabVar == null || aabVar.aab() == -1 || this.aaba.aab() == 0) {
            return 30000;
        }
        return this.aaba.aab();
    }

    String aaba(HealthKitApiInvoker healthKitApiInvoker) throws RemoteException, ApiException {
        String aaba;
        synchronized (this) {
            aabz.aabb("CommonMethodImpl", "invoke read interface");
            aaba = aaba(healthKitApiInvoker, true);
        }
        return aaba;
    }

    HealthKitApiInvoker aaba() {
        return aabl;
    }

    public void aab() {
        this.aabc.clear();
    }

    String aab(HealthKitApiInvoker healthKitApiInvoker) throws RemoteException, ApiException {
        String aaba;
        synchronized (this) {
            aaba = aaba(healthKitApiInvoker, false);
        }
        return aaba;
    }

    private void aabd() {
        long j = this.aab;
        if (j == 0 || aacs.aab(j, 86400000L)) {
            StringBuilder aab2 = com.huawei.hms.health.aab.aab("init InterfacePolicy, last time ");
            aab2.append(this.aab);
            aabz.aabb("CommonMethodImpl", aab2.toString());
            aacp.aabg().aabd().addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.hms.health.aaci$$ExternalSyntheticLambda0
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    aaci.this.aab((com.huawei.hms.hihealth.data.aabc) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hms.health.aaci$$ExternalSyntheticLambda1
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    aabz.aabc("CommonMethodImpl", "init InterfacePolicy fail");
                }
            });
        }
    }

    private String aabb(HealthKitApiInvoker healthKitApiInvoker) {
        return healthKitApiInvoker.getInterfaceProvider() + healthKitApiInvoker.getInterfaceInvoked() + healthKitApiInvoker.getRequestBody().hashCode();
    }

    public static class aab extends aaba.aab {
        private CountDownLatch aab;
        private List<String> aaba;

        public void aab(List list, int i, int i2, int i3) throws RemoteException {
            String str;
            aabz.aabb("CommonMethodImpl", "onTransmissionResult enter");
            if (i == 3) {
                aabz.aabb("CommonMethodImpl", "transfer complete");
                this.aab.countDown();
                return;
            }
            if (list == null || list.isEmpty()) {
                return;
            }
            Object obj = list.get(0);
            if (i == 1) {
                StringBuilder aab = com.huawei.hms.health.aab.aab("transmission slice get:");
                aab.append(System.currentTimeMillis());
                aabz.aabb("CommonMethodImpl", aab.toString());
                if (obj instanceof String) {
                    this.aaba.add((String) obj);
                    return;
                }
                return;
            }
            if (i != 2) {
                str = "unknown transmissionType" + i;
            } else {
                if (obj instanceof String) {
                    this.aaba.add((String) obj);
                }
                this.aab.countDown();
                str = "transmission complete no need slice";
            }
            aabz.aabb("CommonMethodImpl", str);
        }

        aab(CountDownLatch countDownLatch, List<String> list) {
            this.aab = countDownLatch;
            this.aaba = list;
        }
    }
}
