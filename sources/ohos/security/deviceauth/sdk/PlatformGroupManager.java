package ohos.security.deviceauth.sdk;

import android.content.Context;
import android.util.Pair;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import com.huawei.security.deviceauth.GroupOperation;
import com.huawei.security.deviceauth.HichainAuthManager;
import com.huawei.security.deviceauth.HwDeviceGroupManager;
import defpackage.uwn;
import defpackage.uwq;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class PlatformGroupManager implements GroupManager {

    /* renamed from: a, reason: collision with root package name */
    private HwDeviceGroupManager f15671a;
    private Context b;
    private final CallbackProxy c = new CallbackProxy();
    private final ExecutorService e = Executors.newSingleThreadExecutor();
    private Pair<String, DeviceAuthCallback> d = null;

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int confirmRequest(long j, String str, String str2) {
        return 0;
    }

    private void e(final long j, final int i, final int i2, final String str) {
        Runnable runnable;
        if (i2 == 0) {
            runnable = new Runnable() { // from class: ohos.security.deviceauth.sdk.PlatformGroupManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlatformGroupManager.this.d(j, i, str);
                }
            };
        } else {
            runnable = new Runnable() { // from class: ohos.security.deviceauth.sdk.PlatformGroupManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlatformGroupManager.this.c(j, i, i2);
                }
            };
        }
        this.e.execute(runnable);
    }

    /* synthetic */ void d(long j, int i, String str) {
        ((DeviceAuthCallback) this.d.second).onFinish(j, i, str);
    }

    /* synthetic */ void c(long j, int i, int i2) {
        ((DeviceAuthCallback) this.d.second).onError(j, i, i2, null);
    }

    private boolean c(String str) {
        Pair<String, DeviceAuthCallback> pair;
        if (this.f15671a != null && (pair = this.d) != null && ((String) pair.first).equals(str)) {
            return false;
        }
        uwn.e("PlatformGroupManager", "Please call initService and registerCallback first.");
        return true;
    }

    private HwDeviceGroupManager.HichainGroupCallback a() {
        return new HwDeviceGroupManager.HichainGroupCallback() { // from class: ohos.security.deviceauth.sdk.PlatformGroupManager.1
            public void onError(long j, GroupOperation groupOperation, int i, String str) {
            }

            public void onFinish(long j, GroupOperation groupOperation, String str) {
            }

            public String onRequest(long j, GroupOperation groupOperation, String str) {
                return "";
            }
        };
    }

    private HwDeviceGroupManager.HichainGroupCallback d(final String str) {
        return new HwDeviceGroupManager.HichainGroupCallback() { // from class: ohos.security.deviceauth.sdk.PlatformGroupManager.2
            public String onRequest(long j, GroupOperation groupOperation, String str2) {
                if (PlatformGroupManager.this.d == null || !((String) PlatformGroupManager.this.d.first).equals(str)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("confirmation", -2147483643);
                    } catch (JSONException unused) {
                        uwn.e("PlatformGroupManager", "Pass confirmation failed.");
                    }
                    return jSONObject.toString();
                }
                try {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject2.has("deviceId")) {
                        jSONObject3.put("deviceId", jSONObject2.getString("deviceId"));
                    }
                    if (jSONObject2.has("groupVisibility")) {
                        jSONObject3.put("groupVisibility", jSONObject2.getInt("groupVisibility"));
                    }
                    jSONObject2.put("groupInfo", jSONObject3);
                    return ((DeviceAuthCallback) PlatformGroupManager.this.d.second).onRequest(j, getOperationCode(groupOperation), jSONObject2.toString());
                } catch (JSONException unused2) {
                    uwn.e("PlatformGroupManager", "onRequest: parse json failed");
                    return ((DeviceAuthCallback) PlatformGroupManager.this.d.second).onRequest(j, getOperationCode(groupOperation), str2);
                }
            }

            public void onError(long j, GroupOperation groupOperation, int i, String str2) {
                if (PlatformGroupManager.this.d == null || !((String) PlatformGroupManager.this.d.first).equals(str)) {
                    return;
                }
                ((DeviceAuthCallback) PlatformGroupManager.this.d.second).onError(j, getOperationCode(groupOperation), i, str2);
            }

            public void onFinish(long j, GroupOperation groupOperation, String str2) {
                if (PlatformGroupManager.this.d == null || !((String) PlatformGroupManager.this.d.first).equals(str)) {
                    return;
                }
                ((DeviceAuthCallback) PlatformGroupManager.this.d.second).onFinish(j, getOperationCode(groupOperation), str2);
            }

            private int getOperationCode(GroupOperation groupOperation) {
                if (groupOperation == null) {
                    groupOperation = GroupOperation.CODE_NULL;
                }
                return groupOperation.toInt();
            }
        };
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public boolean isDeviceInGroup(String str, String str2, String str3) {
        HwDeviceGroupManager hwDeviceGroupManager = this.f15671a;
        if (hwDeviceGroupManager == null) {
            uwn.e("PlatformGroupManager", "isDeviceInGroup: Please call initService first.");
            return false;
        }
        List listTrustedDevices = hwDeviceGroupManager.listTrustedDevices(str2);
        if (listTrustedDevices == null || listTrustedDevices.isEmpty()) {
            uwn.e("PlatformGroupManager", "isDeviceInGroup: device list is empty, retry one time.");
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                uwn.b("PlatformGroupManager", "retry directly.");
            }
            listTrustedDevices = this.f15671a.listTrustedDevices(str2);
            if (listTrustedDevices == null || listTrustedDevices.isEmpty()) {
                uwn.e("PlatformGroupManager", "isDeviceInGroup: device list is empty.");
                return false;
            }
        }
        return listTrustedDevices.contains(str3);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public List<String> getGroupInfo(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            jSONObject.put("appId", str);
            return HichainAuthManager.getInstance(this.b).getGroupInfo(jSONObject.toString());
        } catch (JSONException unused) {
            return new ArrayList(0);
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int processData(long j, byte[] bArr) {
        HwDeviceGroupManager hwDeviceGroupManager = this.f15671a;
        if (hwDeviceGroupManager == null) {
            uwn.e("PlatformGroupManager", "processData: Please call initService first.");
            return -1;
        }
        return ((Integer) uwq.e(hwDeviceGroupManager, "processRequestData", (Class<?>[]) new Class[]{Long.TYPE, byte[].class}, new Object[]{Long.valueOf(j), bArr}, Integer.class)).intValue();
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int deleteMemberFromGroup(long j, String str, String str2) {
        Object newProxyInstance;
        if (c(str)) {
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("connectParams")) {
                if (jSONObject.getBoolean("isClient")) {
                    return this.f15671a.deleteMemberFromGroup(str, j, str2, jSONObject.getString("connectParams"));
                }
                return 0;
            }
            boolean z = jSONObject.getBoolean("isIgnoreChannel");
            boolean z2 = jSONObject.getBoolean("isForceDelete");
            Class<?> e = uwq.e("com.huawei.security.deviceauth.HwDeviceGroupManager$CommunicationChannel");
            if (z2 && z) {
                newProxyInstance = null;
                int intValue = ((Integer) uwq.e(this.f15671a, "deleteMemberFromGroupByChannel", (Class<?>[]) new Class[]{String.class, Long.TYPE, String.class, e}, new Object[]{str, Long.valueOf(j), str2, newProxyInstance}, Integer.class)).intValue();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("groupId", jSONObject.getString("groupId"));
                jSONObject2.put("deleteId", jSONObject.getString("deleteId"));
                e(j, 4, intValue, jSONObject2.toString());
                return intValue;
            }
            newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{e}, this.c);
            int intValue2 = ((Integer) uwq.e(this.f15671a, "deleteMemberFromGroupByChannel", (Class<?>[]) new Class[]{String.class, Long.TYPE, String.class, e}, new Object[]{str, Long.valueOf(j), str2, newProxyInstance}, Integer.class)).intValue();
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("groupId", jSONObject.getString("groupId"));
            jSONObject22.put("deleteId", jSONObject.getString("deleteId"));
            e(j, 4, intValue2, jSONObject22.toString());
            return intValue2;
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int addMemberToGroup(long j, String str, String str2) {
        if (c(str)) {
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int i = jSONObject.getInt("groupType");
            if (jSONObject.has("connectParams")) {
                if (jSONObject.getBoolean("isClient")) {
                    return this.f15671a.addMemberToGroup(str, j, str2, jSONObject.getString("connectParams"), i);
                }
                return 0;
            }
            Class<?> e = uwq.e("com.huawei.security.deviceauth.HwDeviceGroupManager$CommunicationChannel");
            return ((Integer) uwq.e(this.f15671a, "addMemberToGroupByChannel", (Class<?>[]) new Class[]{String.class, Long.TYPE, String.class, e, Integer.TYPE}, new Object[]{str, Long.valueOf(j), str2, Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{e}, this.c), Integer.valueOf(i)}, Integer.class)).intValue();
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int deleteGroup(long j, String str, String str2) {
        if (c(str)) {
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int deleteGroup = this.f15671a.deleteGroup(jSONObject.getString("groupId"));
            e(j, 1, deleteGroup, jSONObject.toString());
            return deleteGroup;
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int createGroup(long j, String str, String str2) {
        if (c(str)) {
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            return this.f15671a.createGroup(str, jSONObject.getString(ComplaintConstants.GROUP_NAME_PARAM_KEY), jSONObject.getInt("groupType"), str2);
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int unregisterCallback(String str) {
        Pair<String, DeviceAuthCallback> pair = this.d;
        if (pair == null || !((String) pair.first).equals(str)) {
            uwn.a("PlatformGroupManager", "The appId is not registered, no need to unregister.");
            return 0;
        }
        this.d = null;
        return 0;
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int registerCallback(String str, DeviceAuthCallback deviceAuthCallback) {
        if (deviceAuthCallback == null || str == null) {
            uwn.e("PlatformGroupManager", "registerCallback: parameters cannot be null");
            return -1;
        }
        HwDeviceGroupManager hwDeviceGroupManager = HwDeviceGroupManager.getInstance(this.b, str, d(str));
        this.f15671a = hwDeviceGroupManager;
        if (hwDeviceGroupManager == null) {
            uwn.e("PlatformGroupManager", "registerCallback: register callback fail, retry one time");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException unused) {
                uwn.b("PlatformGroupManager", "retry directly.");
            }
            HwDeviceGroupManager hwDeviceGroupManager2 = HwDeviceGroupManager.getInstance(this.b, str, d(str));
            this.f15671a = hwDeviceGroupManager2;
            if (hwDeviceGroupManager2 == null) {
                uwn.e("PlatformGroupManager", "registerCallback: retry fail!");
                return -1;
            }
        }
        this.d = new Pair<>(str, deviceAuthCallback);
        return 0;
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public void destroyService() {
        HwDeviceGroupManager hwDeviceGroupManager = this.f15671a;
        if (hwDeviceGroupManager != null) {
            hwDeviceGroupManager.unbindHwGroupManageService();
            this.f15671a = null;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int initService(Context context) {
        if (context == null) {
            return -1;
        }
        this.b = context;
        HwDeviceGroupManager hwDeviceGroupManager = HwDeviceGroupManager.getInstance(context, context.getPackageName(), a());
        this.f15671a = hwDeviceGroupManager;
        if (hwDeviceGroupManager != null) {
            return 0;
        }
        uwn.e("PlatformGroupManager", "initService: init service fail, retry one time");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
            uwn.b("PlatformGroupManager", "retry directly.");
        }
        HwDeviceGroupManager hwDeviceGroupManager2 = HwDeviceGroupManager.getInstance(context, context.getPackageName(), a());
        this.f15671a = hwDeviceGroupManager2;
        if (hwDeviceGroupManager2 != null) {
            return 0;
        }
        uwn.e("PlatformGroupManager", "initService: retry fail!");
        return -1;
    }

    class CallbackProxy implements InvocationHandler {
        CallbackProxy() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (objArr.length != 2 || objArr[0].getClass() != Long.class || objArr[1].getClass() != byte[].class) {
                return -1;
            }
            return Integer.valueOf(((DeviceAuthCallback) PlatformGroupManager.this.d.second).onTransmit(((Long) objArr[0]).longValue(), (byte[]) objArr[1]) ? 0 : -1);
        }
    }
}
