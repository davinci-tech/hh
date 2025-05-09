package defpackage;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import ohos.security.deviceauth.sdk.GroupManager;
import ohos.security.deviceauth.sdk.NativeDas;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
class uwl implements GroupManager {
    private boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private long f17565a = 0;

    uwl() {
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int initService(Context context) {
        if (this.d) {
            return 0;
        }
        if (!NativeDas.isLibraryLoaded()) {
            uwn.e("NativeGroupManager", "The local library was not loaded successfully, the service could not be initialized.");
            return -1;
        }
        if (context == null || context.getFilesDir() == null) {
            uwn.e("NativeGroupManager", "initService: Invalid context.");
            return -1;
        }
        int initService = NativeDas.initService(context.getFilesDir().getPath());
        if (initService != 0) {
            uwn.e("NativeGroupManager", "initService: Init group manager service failed.");
            return initService;
        }
        long gmInstance = NativeDas.getGmInstance();
        this.f17565a = gmInstance;
        if (gmInstance == 0) {
            uwn.e("NativeGroupManager", "initService: Get instance of gm failed.");
            return -1;
        }
        this.d = true;
        return 0;
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public void destroyService() {
        if (this.d) {
            NativeDas.destroyService();
            this.d = false;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int registerCallback(String str, DeviceAuthCallback deviceAuthCallback) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.registerGmCallback(this.f17565a, str, deviceAuthCallback);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int unregisterCallback(String str) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.unregisterGmCallback(this.f17565a, str);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int createGroup(long j, String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.createGroup(this.f17565a, j, str, str2);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int deleteGroup(long j, String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.deleteGroup(this.f17565a, j, str, str2);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int addMemberToGroup(long j, String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        try {
            if (new JSONObject(str2).getBoolean("isClient")) {
                return NativeDas.addMemberToGroup(this.f17565a, j, str, str2);
            }
            return 0;
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int deleteMemberFromGroup(long j, String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        try {
            if (new JSONObject(str2).getBoolean("isClient")) {
                return NativeDas.deleteMemberFromGroup(this.f17565a, j, str, str2);
            }
            return 0;
        } catch (JSONException unused) {
            return -1;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int processData(long j, byte[] bArr) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.processGmData(this.f17565a, j, bArr);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public int confirmRequest(long j, String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.confirmRequest(this.f17565a, j, str, str2);
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public List<String> getGroupInfo(String str, String str2) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return new ArrayList(0);
        }
        String groupInfo = NativeDas.getGroupInfo(this.f17565a, str, str2);
        if (groupInfo == null || groupInfo.isEmpty()) {
            return new ArrayList(0);
        }
        try {
            JSONArray jSONArray = new JSONArray(groupInfo);
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getJSONObject(i).toString());
            }
            return arrayList;
        } catch (JSONException unused) {
            return new ArrayList(0);
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupManager
    public boolean isDeviceInGroup(String str, String str2, String str3) {
        if (!this.d) {
            uwn.e("NativeGroupManager", "The service is not started. Please call the initService first.");
            return false;
        }
        return NativeDas.isDeviceInGroup(this.f17565a, str, str2, str3);
    }
}
