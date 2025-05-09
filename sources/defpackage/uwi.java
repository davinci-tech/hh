package defpackage;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import ohos.security.deviceauth.sdk.GroupManager;
import ohos.security.deviceauth.sdk.PlatformGroupManager;
import ohos.security.deviceauth.sdk.utils.DeviceUtil;

/* loaded from: classes7.dex */
public class uwi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile GroupManager f17563a;
    private static final Object b = new Object();
    private static volatile uwi e;

    private uwi() {
    }

    public static uwi b() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new uwi();
                }
            }
        }
        return e;
    }

    public int a(Context context) {
        int c = uwp.c(context);
        if (c != 0) {
            uwn.e("DeviceGroupManager", "Init context failed.");
            return c;
        }
        d(context);
        return f17563a.initService(context);
    }

    public int c(String str, DeviceAuthCallback deviceAuthCallback) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "registerCallback:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.registerCallback(str, deviceAuthCallback);
    }

    public int b(long j, String str, String str2) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "createGroup:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.createGroup(j, str, str2);
    }

    public int d(long j, String str, String str2) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "deleteGroup:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.deleteGroup(j, str, str2);
    }

    public int c(long j, String str, String str2) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "addMemberToGroup:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.addMemberToGroup(j, str, str2);
    }

    public int a(long j, String str, String str2) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "deleteMemberFromGroup:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.deleteMemberFromGroup(j, str, str2);
    }

    public int b(long j, byte[] bArr) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "processData:The instance of groupManager is not initialized.");
            return -1;
        }
        return f17563a.processData(j, bArr);
    }

    public List<String> a(String str, String str2) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "getGroupInfo:The instance of groupManager is not initialized.");
            return new ArrayList(0);
        }
        return f17563a.getGroupInfo(str, str2);
    }

    public boolean e(String str, String str2, String str3) {
        if (f17563a == null) {
            uwn.e("DeviceGroupManager", "isDeviceInGroup:The instance of groupManager is not initialized.");
            return false;
        }
        return f17563a.isDeviceInGroup(str, str2, str3);
    }

    private static void d(Context context) {
        if (f17563a == null) {
            synchronized (b) {
                if (f17563a == null) {
                    if (DeviceUtil.isSystemServiceSupported(context)) {
                        f17563a = new PlatformGroupManager();
                    } else {
                        f17563a = new uwl();
                    }
                }
            }
        }
    }
}
