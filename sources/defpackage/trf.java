package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.HiAppInfo;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.repository.AuthInfoRepositoryImpl;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.ScopeManager;
import com.huawei.wearengine.scope.ScopeServerRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class trf {
    private static Map<String, Permission> c;

    static {
        HashMap hashMap = new HashMap(16);
        c = hashMap;
        hashMap.put(Permission.DEVICE_MANAGER.getName(), Permission.DEVICE_MANAGER);
        c.put(Permission.NOTIFY.getName(), Permission.NOTIFY);
        c.put(Permission.SENSOR.getName(), Permission.SENSOR);
        c.put(Permission.MOTION_SENSOR.getName(), Permission.MOTION_SENSOR);
        c.put(Permission.WEAR_USER_STATUS.getName(), Permission.WEAR_USER_STATUS);
        c.put(Permission.DEVICE_SN.getName(), Permission.DEVICE_SN);
    }

    public static boolean c(List<AuthInfo> list, Permission permission) {
        if (list == null || list.isEmpty() || permission == null) {
            tos.e("AuthUtil", "isAuthorized authInfoList isEmpty");
            return false;
        }
        tos.b("AuthUtil", "isAuthorized authInfoList:" + list);
        tos.b("AuthUtil", "isAuthorized permission:" + permission.getName());
        for (AuthInfo authInfo : list) {
            if (authInfo.getPermission().equals(permission.getName())) {
                return authInfo.getOpenStatus() != 0;
            }
        }
        return false;
    }

    public static boolean c() {
        return Process.myUid() == Binder.getCallingUid();
    }

    public static Intent ffc_(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            tos.d("AuthUtil", "createAuthIntent packageName is invalid");
            return null;
        }
        if (strArr == null || strArr.length == 0) {
            tos.d("AuthUtil", "createAuthIntent permissionTypes is invalid");
            return null;
        }
        Intent intent = new Intent();
        intent.setClassName("com.huawei.health", "com.huawei.ui.main.stories.devicecapacity.AuthActivity");
        intent.setPackage("com.huawei.health");
        intent.setFlags(268435456);
        intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        intent.addFlags(524288);
        intent.addFlags(8388608);
        intent.putExtra("third_party_package_name", str);
        intent.putExtra("permissionTypes", strArr);
        HiAppInfo a2 = tri.a(tot.a(), str);
        if (a2 == null) {
            tos.d("AuthUtil", " getAppInfo is null");
            return null;
        }
        String c2 = tri.c(tot.a(), str);
        if (TextUtils.isEmpty(c2)) {
            tos.d("AuthUtil", "getAppName is null");
            return null;
        }
        a2.setAppName(c2);
        tos.b("AuthUtil", "getAppInfo HiAppInfo:" + a2.toString());
        intent.putExtra(MapKeyNames.APP_INFO, a2);
        tos.a("AuthUtil", "startActivity");
        intent.putExtra("come_from", "third_party_app");
        return intent;
    }

    public static boolean c(String str, ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null) {
            tos.d("AuthUtil", "checkFingerprintFromScopeServer appScope isEmpty");
            return false;
        }
        tos.b("AuthUtil", "ScopeInfoResponse:" + scopeInfoResponse.toString());
        if (TextUtils.isEmpty(str)) {
            tos.d("AuthUtil", "packageName == null");
            return false;
        }
        if (d(str, scopeInfoResponse)) {
            return true;
        }
        tos.d("AuthUtil", "checkFingerprintFromScopeServer checkCertFingerprint or checkPermission fail");
        return false;
    }

    public static ScopeInfoResponse c(int i) {
        if (i == 0) {
            tos.e("AuthUtil", "getScope appId is invalid");
            return null;
        }
        String valueOf = String.valueOf(i);
        ScopeServerRequest scopeServerRequest = new ScopeServerRequest(valueOf);
        Context a2 = tot.a();
        String url = scopeServerRequest.getUrl(tri.e(a2));
        ScopeManager scopeManager = new ScopeManager(a2);
        scopeManager.setScopeServerUrl(valueOf, url);
        ScopeInfoResponse scope = scopeManager.getScope(valueOf, "wearEngine");
        if (scope != null) {
            tos.b("AuthUtil", "getScope scopeInfoResponse is :" + scope.toString());
        }
        return scope;
    }

    public static boolean d(String str, ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null) {
            tos.d("AuthUtil", "checkCertFingerprint scopeInfoResponse isEmpty");
            return false;
        }
        String c2 = tri.c(str);
        if (TextUtils.isEmpty(c2)) {
            tos.d("AuthUtil", "checkCertFingerprint appCert isEmpty");
            return false;
        }
        tos.b("AuthUtil", "appCert=" + c2);
        String certFingerprintExtra = scopeInfoResponse.getCertFingerprintExtra();
        if (c2.equals(scopeInfoResponse.getCertFingerprint()) || c(c2, certFingerprintExtra)) {
            return true;
        }
        tos.d("AuthUtil", "checkCertFingerprint appCert not equals");
        return false;
    }

    public static boolean c(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            for (String str3 : str2.split(";")) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean c(String[] strArr, List<String> list) {
        tos.a("AuthUtil", "isAllPermissionsAuthorized enter");
        if (strArr == null || strArr.length == 0) {
            tos.d("AuthUtil", "isAllPermissionsAuthorized permission is null");
            return false;
        }
        if (list == null || list.isEmpty()) {
            tos.d("AuthUtil", "isAllPermissionsAuthorized permissionList is null");
            return false;
        }
        for (String str : strArr) {
            if (!list.contains(str)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> a(String str) {
        tos.a("AuthUtil", "getAuthorizedPermissions enter");
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            tos.d("AuthUtil", "getAuthorizedPermissions callingPackageName is null");
            return arrayList;
        }
        HiAppInfo a2 = tri.a(tot.a(), str);
        if (a2 == null) {
            tos.d("AuthUtil", "getAuthorizedPermissions hiAppInfo is null");
            return arrayList;
        }
        List<AuthInfo> auth = new AuthInfoRepositoryImpl(tot.a()).getAuth(a2.getAppUid(), a2.getUserId(), a2.getAppId());
        if (auth == null || auth.isEmpty()) {
            tos.d("AuthUtil", "getAuthorizedPermissions authInfoList is null");
            return arrayList;
        }
        for (AuthInfo authInfo : auth) {
            if (authInfo.getOpenStatus() != 0) {
                arrayList.add(authInfo.getPermission());
            }
        }
        return arrayList;
    }

    public static Permission[] c(List<String> list) {
        tos.a("AuthUtil", "getPermissionByPermissionList enter");
        if (list == null || list.isEmpty()) {
            tos.d("AuthUtil", "getPermissionByPermissionList permissionsList is null");
            return null;
        }
        Permission[] permissionArr = new Permission[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (c.containsKey(list.get(i))) {
                permissionArr[i] = c.get(list.get(i));
            } else {
                permissionArr[i] = null;
            }
        }
        return permissionArr;
    }
}
