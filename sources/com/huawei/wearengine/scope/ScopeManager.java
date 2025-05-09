package com.huawei.wearengine.scope;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.wearengine.scope.cache.ScopeCache;
import defpackage.tos;
import defpackage.tot;
import defpackage.tqn;
import defpackage.trf;
import defpackage.tri;
import defpackage.trk;
import defpackage.tro;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
public class ScopeManager {
    private static final long DEFAULT_EXPIRED_TIME = 86400000;
    private static final String TAG = "ScopeManager";
    private Map<String, String> mAppIdMap;
    private final Object mAppIdMapLock = new Object();
    private Map<Integer, String> mAppUidMap;
    private ScopeCache mScopeCache;
    private Map<String, String> mScopeUrlMap;

    public ScopeManager(Context context) {
        tot.a(context);
        this.mScopeUrlMap = new ConcurrentHashMap();
        this.mAppUidMap = new ConcurrentHashMap();
        this.mAppIdMap = new ConcurrentHashMap();
        this.mScopeCache = new tqn();
    }

    public void setScopeServerUrl(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.mScopeUrlMap.put(str, str2);
    }

    private void clearAppIdMap(String str) {
        if (!TextUtils.isEmpty(str) && this.mAppIdMap.containsValue(str)) {
            this.mAppIdMap.values().remove(str);
        }
    }

    public void setAppId(int i, int i2, String str) {
        if (i2 == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        String appKey = getAppKey(i, i2);
        synchronized (this.mAppIdMapLock) {
            clearAppIdMap(str);
            this.mAppIdMap.put(appKey, str);
        }
    }

    private String getAppKey(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("_");
        stringBuffer.append(i2);
        return stringBuffer.toString();
    }

    public void setAppUid(int i, String str) {
        if (str != null) {
            this.mAppUidMap.put(Integer.valueOf(i), str);
        }
    }

    public boolean checkScopeAvailability(String str, int i, int i2, String str2) {
        if (TextUtils.isEmpty(str)) {
            tos.d(TAG, "checkScopeAvailability permission isEmpty");
            return false;
        }
        String appKey = getAppKey(i, i2);
        synchronized (this.mAppIdMapLock) {
            if (!this.mAppIdMap.containsKey(appKey)) {
                tos.d(TAG, "checkScopeAvailability mAppIdMap not containsKey:" + appKey);
                return false;
            }
            String str3 = this.mAppIdMap.get(appKey);
            return checkScopeInfoResponseAvailability(str, str3, i2, getScope(str3, str2));
        }
    }

    public boolean checkScopeAvailability(String str, int i) {
        int j;
        tos.b(TAG, "checkScopeAvailability enter!");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!this.mAppUidMap.containsKey(Integer.valueOf(i))) {
            String b = tri.b(i, tot.a());
            if (TextUtils.isEmpty(b) || (j = tri.j(tot.a(), b)) == 0) {
                return false;
            }
            this.mAppUidMap.put(Integer.valueOf(i), String.valueOf(j));
        }
        tos.b(TAG, "checkScopeAvailability permission:" + str);
        return checkScopeInfoResponseAvailability(str, this.mAppUidMap.get(Integer.valueOf(i)), i, getScope(this.mAppUidMap.get(Integer.valueOf(i)), ""));
    }

    private boolean checkScopeInfoResponseAvailability(String str, String str2, int i, ScopeInfoResponse scopeInfoResponse) {
        String str3;
        if (scopeInfoResponse == null) {
            tos.d(TAG, "checkScopeInfoResponseAvailability appScope isEmpty");
            return false;
        }
        tos.b(TAG, "ScopeInfoResponse:" + scopeInfoResponse.toString());
        String[] packagesForUid = tot.a().getPackageManager().getPackagesForUid(i);
        if (packagesForUid.length == 1) {
            str3 = packagesForUid[0];
        } else {
            String str4 = null;
            for (String str5 : packagesForUid) {
                if (String.valueOf(tri.j(tot.a(), str5)).equals(str2)) {
                    str4 = str5;
                }
            }
            str3 = str4;
        }
        if (TextUtils.isEmpty(str3)) {
            tos.d(TAG, "packageName == null");
            return false;
        }
        if (trf.d(str3, scopeInfoResponse) && checkPermission(str, scopeInfoResponse)) {
            return true;
        }
        tos.d(TAG, "checkScopeInfoResponseAvailability checkCertFingerprint or checkPermission fail");
        return false;
    }

    public ScopeInfoResponse getScope(String str, String str2) {
        tos.a(TAG, "getScope:" + str);
        ScopeInfoResponse scope = this.mScopeCache.getScope((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? str : getScopeKey(str, str2));
        if (scope == null) {
            return getScopeFromServer(str, str2);
        }
        return isExpired(scope) ? getScopeFromServer(str, str2) : scope;
    }

    private String getScopeKey(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("_");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    private ScopeInfoResponse getScopeFromServer(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            tos.d(TAG, "getScopeFromServer appId isEmpty! appId=" + str);
            return null;
        }
        if (!this.mScopeUrlMap.containsKey(str)) {
            tos.d(TAG, "getScopeFromServer ScopeUrl isEmpty! appId=" + str);
            return null;
        }
        String str3 = this.mScopeUrlMap.get(str);
        if (!isUrlLegal(str3)) {
            tos.d(TAG, "getScopeFromServer ScopeUrl Illegal! appId=" + str);
            return null;
        }
        Response c = tro.c(str3, str);
        tos.a(TAG, "getScopeFromServer end of response");
        try {
            if (c == null) {
                tos.d(TAG, "get scope is failed!");
                return null;
            }
            try {
                if (c.isSuccessful() && c.getBody() != null) {
                    String str4 = new String(((ResponseBody) c.getBody()).bytes(), "UTF-8");
                    tos.b(TAG, "getScope response.body:".concat(str4));
                    ScopeInfoResponse scopeInfoResponse = (ScopeInfoResponse) trk.b(str4, ScopeInfoResponse.class);
                    saveScopeToMemoryAndDatabase(str, str2, scopeInfoResponse);
                    return scopeInfoResponse;
                }
                tos.d(TAG, "get scope is failed!");
                c.close();
                try {
                    c.close();
                } catch (IOException unused) {
                    tos.b(TAG, " handlerResponse close IOException");
                }
                return null;
            } catch (IOException unused2) {
                tos.b(TAG, " handlerResponse IOException");
                try {
                    c.close();
                } catch (IOException unused3) {
                    tos.b(TAG, " handlerResponse close IOException");
                }
                return null;
            }
        } finally {
            try {
                c.close();
            } catch (IOException unused4) {
                tos.b(TAG, " handlerResponse close IOException");
            }
        }
    }

    private void saveScopeToMemoryAndDatabase(String str, String str2, ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null || TextUtils.isEmpty(str) || scopeInfoResponse.getScopes() == null || scopeInfoResponse.getScopes().isEmpty()) {
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            str = getScopeKey(str, str2);
        }
        scopeInfoResponse.setTimeStamp(System.currentTimeMillis());
        scopeInfoResponse.setAppId(str);
        this.mScopeCache.saveScope(str, scopeInfoResponse);
    }

    private boolean isExpired(ScopeInfoResponse scopeInfoResponse) {
        long currentTimeMillis = System.currentTimeMillis() - scopeInfoResponse.getTimeStamp();
        if (currentTimeMillis < 86400000 && currentTimeMillis >= 0) {
            return false;
        }
        tos.b(TAG, "isExpired, appID: " + scopeInfoResponse.getAppId() + ", cacheExpiryTimestamp: 86400000, interval: " + currentTimeMillis);
        return true;
    }

    private boolean checkPermission(String str, ScopeInfoResponse scopeInfoResponse) {
        if (TextUtils.isEmpty(str) || scopeInfoResponse == null) {
            tos.d(TAG, "checkPermission permission is empty or scopeInfoResponse is null");
            return false;
        }
        List<ScopeInfo> scopes = scopeInfoResponse.getScopes();
        if (scopes == null || scopes.isEmpty()) {
            tos.d(TAG, "checkPermission scopes is null or scopes is empty");
            return false;
        }
        Iterator<ScopeInfo> it = scopes.iterator();
        while (it.hasNext()) {
            List<String> permissions = it.next().getPermissions();
            if (permissions != null && permissions.contains(str)) {
                tos.b(TAG, "checkPermission permissionList.contains(permission), permission=" + str);
                return true;
            }
        }
        tos.d(TAG, "checkPermissionscopes fail");
        return false;
    }

    private boolean isUrlLegal(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.d(TAG, "isUrlLegal urlString isEmpty");
            return false;
        }
        return Patterns.WEB_URL.matcher(str).matches();
    }
}
