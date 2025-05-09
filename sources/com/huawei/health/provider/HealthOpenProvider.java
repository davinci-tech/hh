package com.huawei.health.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.provider.cursor.HealthCursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import defpackage.dlc;
import defpackage.dle;
import defpackage.ezb;
import defpackage.eze;
import defpackage.ezg;
import health.compact.a.ApplicationLazyLoad;
import health.compact.a.AuthorityHealthCursor;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.HealthFaDataHelper;
import health.compact.a.HuaweiHealth;
import health.compact.a.LogUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.ProviderAuthorityUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StatisticsSleepHealthCursor;
import health.compact.a.StatisticsSportHealthCursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HealthOpenProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final UriMatcher f2946a;
    private static boolean c = false;
    private static boolean d = false;
    private LogicalStepCounter b = null;

    private int f() {
        return 0;
    }

    private int h() {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f2946a = uriMatcher;
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "authority", 0);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "sport_statistics", 2000);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "sleep_statistics", 2001);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "health_statistics", 2002);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "wear_device_state", 2003);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "wear_device_state/wearEngine", 2004);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "health_fa_form", 2005);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "health_fa_ability", 2006);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "step_counter_detection", 2007);
        uriMatcher.addURI("com.huawei.healthcloud.health.provider", "single_motion_detection", 2008);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        HuaweiHealth.c(BaseApplication.getContext());
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.database.Cursor query(android.net.Uri r8, java.lang.String[] r9, java.lang.String r10, java.lang.String[] r11, java.lang.String r12) {
        /*
            r7 = this;
            java.lang.String r0 = "Step_HlthOpenProv"
            r1 = 0
            if (r8 != 0) goto L10
            java.lang.String r8 = "uri is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.e(r0, r8)
            return r1
        L10:
            android.content.UriMatcher r2 = com.huawei.health.provider.HealthOpenProvider.f2946a
            int r3 = r2.match(r8)
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()
            boolean r5 = com.huawei.health.provider.HealthOpenProvider.c
            if (r5 != 0) goto L3a
            boolean r5 = health.compact.a.DaemonServiceSpUtils.d(r4)
            if (r5 == 0) goto L3a
            java.lang.String r5 = "com.huawei.ohos.health"
            java.lang.String r6 = r7.getCallingPackage()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L3a
            r5 = 2000(0x7d0, float:2.803E-42)
            if (r3 != r5) goto L3a
            r7.e(r4)
            r5 = 1
            com.huawei.health.provider.HealthOpenProvider.c = r5
        L3a:
            boolean r5 = health.compact.a.ApplicationLazyLoad.e()
            if (r5 != 0) goto L4f
            java.lang.String r8 = "lazy load not finish."
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.e(r0, r8)
            android.database.Cursor r8 = r7.atX_(r3, r4)
            return r8
        L4f:
            int r4 = r2.match(r8)
            r5 = 2003(0x7d3, float:2.807E-42)
            if (r4 == r5) goto Lcd
            int r4 = r2.match(r8)
            r5 = 2004(0x7d4, float:2.808E-42)
            if (r4 != r5) goto L60
            goto Lcd
        L60:
            if (r9 != 0) goto L6d
            java.lang.String r8 = "projection is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.e(r0, r8)
            return r1
        L6d:
            if (r12 == 0) goto Lc2
            if (r11 != 0) goto L72
            goto Lc2
        L72:
            if (r10 != 0) goto L7f
            java.lang.String r8 = "selection is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.e(r0, r8)
            return r1
        L7f:
            int r10 = r2.match(r8)
            r11 = 2007(0x7d7, float:2.812E-42)
            if (r10 != r11) goto L8c
            boolean r10 = r7.d(r9)
            goto La1
        L8c:
            int r10 = r2.match(r8)
            r11 = 2008(0x7d8, float:2.814E-42)
            if (r10 != r11) goto L95
            goto Lb2
        L95:
            android.content.Context r10 = r7.getContext()
            java.lang.String r11 = r7.getCallingPackage()
            boolean r10 = health.compact.a.ProviderAuthorityUtil.b(r10, r11)
        La1:
            if (r10 != 0) goto Lb2
            java.lang.String r8 = "no AppAuthority pathType ="
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9}
            health.compact.a.LogUtil.c(r0, r8)
            return r1
        Lb2:
            java.lang.String r10 = "AppAuthority is ok!"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.LogUtil.c(r0, r10)
            if (r3 < 0) goto Lc1
            com.huawei.health.provider.cursor.HealthCursor r1 = r7.atV_(r3, r8, r9)
        Lc1:
            return r1
        Lc2:
            java.lang.String r8 = "sortOrder or selectionArgs is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.e(r0, r8)
            return r1
        Lcd:
            long r0 = android.os.Binder.clearCallingIdentity()
            android.database.Cursor r8 = r7.atW_(r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> Ld9
            android.os.Binder.restoreCallingIdentity(r0)
            return r8
        Ld9:
            r8 = move-exception
            android.os.Binder.restoreCallingIdentity(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.provider.HealthOpenProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private Cursor atX_(int i, Context context) {
        if (DaemonServiceSpUtils.d(context) || i != 2000) {
            return null;
        }
        ReleaseLogUtil.b("Step_HlthOpenProv", "return default cursor with not authorized ");
        HashMap hashMap = new HashMap(3);
        hashMap.put("support_version", Integer.valueOf(com.huawei.haf.application.BaseApplication.c()));
        hashMap.put("is_authorized", false);
        hashMap.put("app_authorized", false);
        return new StatisticsSportHealthCursor(hashMap);
    }

    private void e(Context context) {
        ReleaseLogUtil.b("Step_HlthOpenProv", "start DaemonService");
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.manager.DaemonService");
        intent.setPackage(context.getPackageName());
        intent.setAction("query_data_refresh_ui");
        try {
            context.startService(intent);
        } catch (Exception e) {
            ReleaseLogUtil.a("Step_HlthOpenProv", "processStartDaemonService ", e.getMessage());
        }
    }

    private boolean d(String[] strArr) {
        if (Arrays.asList(strArr).contains("step_counter_details")) {
            return ProviderAuthorityUtil.e(getCallingPackage(), 101003, true);
        }
        return true;
    }

    private HealthCursor atV_(int i, Uri uri, String[] strArr) {
        HealthCursor statisticsSportHealthCursor;
        if (i == 0) {
            LogUtil.c("Step_HlthOpenProv", "query GET_AUTHORITY");
            return new AuthorityHealthCursor();
        }
        if (i == 2007) {
            LogUtil.c("Step_HlthOpenProv", "query STEP_COUNT_DETECTION");
            return new eze(b(strArr));
        }
        if (i != 2008) {
            switch (i) {
                case 2000:
                    LogUtil.c("Step_HlthOpenProv", "query GET_SPORT_STATISTICS");
                    statisticsSportHealthCursor = new StatisticsSportHealthCursor(a());
                    ReleaseLogUtil.b("Step_HlthOpenProv", "CallingPackage ", getCallingPackage());
                    if ("com.huawei.ohos.health".equals(getCallingPackage())) {
                        d(true);
                        break;
                    }
                    break;
                case 2001:
                    ReleaseLogUtil.b("Step_HlthOpenProv", "query GET_SLEEP_STATISTICS");
                    HashMap hashMap = new HashMap(10);
                    LogicalStepCounter c2 = LogicalStepCounter.c(getContext());
                    this.b = c2;
                    if (c2 != null) {
                        c2.a(hashMap);
                    }
                    statisticsSportHealthCursor = new StatisticsSleepHealthCursor(hashMap);
                    break;
                case 2002:
                    LogUtil.c("Step_HlthOpenProv", "query GET_HEALTH_STATISTICS");
                    return HealthFaDataHelper.atU_(uri);
                default:
                    return null;
            }
            return statisticsSportHealthCursor;
        }
        LogUtil.c("Step_HlthOpenProv", "query SINGLE_MOTION_DETECTION");
        return new ezg(ezb.b(strArr));
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (!ApplicationLazyLoad.e()) {
            LogUtil.e("Step_HlthOpenProv", "lazy load not finish.");
            return null;
        }
        if (uri == null || contentValues == null) {
            LogUtil.e("Step_HlthOpenProv", "uri or values is null");
            return null;
        }
        int match = f2946a.match(uri);
        if (match == 2000) {
            LogUtil.c("Step_HlthOpenProv", "card_visible ", contentValues.getAsInteger("card_visible"));
            d(contentValues.getAsInteger("card_visible").intValue() != 0);
        } else if (match == 2005) {
            ReleaseLogUtil.b("Step_HlthOpenProv", "insert fa card. card: ", contentValues);
            dle WH_ = dlc.c().WH_(contentValues);
            if (WH_ != null) {
                return ContentUris.withAppendedId(uri, WH_.e());
            }
        } else if (match == 2006) {
            ReleaseLogUtil.b("Step_HlthOpenProv", " insert fa abilityL ", contentValues);
            dlc.c().c(contentValues.getAsString("HealthFaAbility"));
            return ContentUris.withAppendedId(uri, 0L);
        }
        Integer asInteger = contentValues.getAsInteger("app_id");
        String asString = contentValues.getAsString("app_package_name");
        if (asInteger != null && asString != null) {
            return null;
        }
        LogUtil.e("Step_HlthOpenProv", "appId or appPackage is null");
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        ReleaseLogUtil.b("Step_HlthOpenProv", " uri:", uri, "selection", " selectionArgs", strArr);
        if (!ApplicationLazyLoad.e()) {
            LogUtil.e("Step_HlthOpenProv", "lazy load not finish.");
            return -1;
        }
        if (uri == null) {
            LogUtil.e("Step_HlthOpenProv", "uri or values is null");
            return -1;
        }
        if (f2946a.match(uri) == 2005) {
            Object[] objArr = new Object[2];
            objArr[0] = "delete fa form, cardId:";
            objArr[1] = strArr == null ? null : Arrays.asList(strArr);
            ReleaseLogUtil.b("Step_HlthOpenProv", objArr);
            if (strArr != null) {
                return -1;
            }
            for (String str2 : strArr) {
                dlc.c().b(CommonUtil.g(str2));
            }
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        if (uri == null || contentValuesArr == null) {
            LogUtil.e("Step_HlthOpenProv", "uri or values is null");
            return 0;
        }
        int match = f2946a.match(uri);
        Integer asInteger = contentValuesArr[0].getAsInteger("app_id");
        if (asInteger == null || asInteger.intValue() < 1) {
            LogUtil.e("Step_HlthOpenProv", "invalid appId ");
            return 0;
        }
        if (match < 0) {
            return 0;
        }
        if (match == 1) {
            return h();
        }
        if (match != 2) {
            return 0;
        }
        return f();
    }

    private Map<String, Object> a() {
        LogicalStepCounter c2 = LogicalStepCounter.c(getContext());
        this.b = c2;
        HashMap hashMap = null;
        if (c2 != null) {
            Bundle akH_ = c2.akH_();
            if (akH_ == null) {
                LogUtil.e("Step_HlthOpenProv", "getSportDataDetail bundle is null");
                return null;
            }
            hashMap = new HashMap(8);
            hashMap.put("time", DateFormatUtil.a(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_8));
            hashMap.put("step", Integer.valueOf(akH_.getInt("step")));
            hashMap.put("distance", Integer.valueOf(akH_.getInt("distance")));
            hashMap.put("carior", Integer.valueOf(akH_.getInt("carior")));
            hashMap.put("floor", Integer.valueOf(akH_.getInt("floor")));
            hashMap.put("duration", Long.valueOf(akH_.getLong("duration")));
            boolean m = SharedPerferenceUtils.m(com.huawei.haf.application.BaseApplication.e());
            LogUtil.c("Step_HlthOpenProv", "isBrowseMode ", Boolean.valueOf(m));
            if (m) {
                hashMap.put("step_goal", 10000);
            } else {
                hashMap.put("step_goal", Integer.valueOf(akH_.getInt("stepTarget")));
            }
            hashMap.put("support_version", Integer.valueOf(com.huawei.haf.application.BaseApplication.c()));
            hashMap.put("is_browse_mode", Boolean.valueOf(m));
            hashMap.put("is_oversea", Boolean.valueOf(CloudUtils.d()));
            hashMap.put("is_authorized", Boolean.valueOf(AuthorizationUtils.a(getContext())));
            hashMap.put("app_authorized", Boolean.valueOf(AuthorizationUtils.a(getContext())));
        }
        return hashMap;
    }

    public static boolean d() {
        return d;
    }

    public static void d(boolean z) {
        d = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
    
        if (androidx.core.content.ContextCompat.checkSelfPermission(getContext(), "android.permission.ACTIVITY_RECOGNITION") == 0) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.LinkedHashMap<java.lang.String, java.lang.Object> b(java.lang.String[] r15) {
        /*
            Method dump skipped, instructions count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.provider.HealthOpenProvider.b(java.lang.String[]):java.util.LinkedHashMap");
    }

    private Cursor atW_(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        UriMatcher uriMatcher = f2946a;
        if (uriMatcher.match(uri) == 2003) {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"boundedDevice", "boundedWearableDevice", "connectedWearableDevice"}, 1);
            boolean a2 = DeviceUtil.a();
            boolean z = a2 || c();
            boolean e = e();
            Object[] objArr = new Object[3];
            objArr[0] = String.valueOf(z);
            objArr[1] = String.valueOf(a2);
            objArr[2] = String.valueOf(a2 && e);
            matrixCursor.addRow(objArr);
            Object[] objArr2 = new Object[6];
            objArr2[0] = "hasBoundedDevice = ";
            objArr2[1] = Boolean.valueOf(z);
            objArr2[2] = ",hasConnectedDevice = ";
            objArr2[3] = Boolean.valueOf(a2 && e);
            objArr2[4] = ",hasBoundedWearableDevice = ";
            objArr2[5] = Boolean.valueOf(a2);
            LogUtil.c("Step_HlthOpenProv", objArr2);
            return matrixCursor;
        }
        if (uriMatcher.match(uri) != 2004) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("selection isEmpty");
        }
        MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"isAllowBindService"}, 1);
        boolean e2 = e(str);
        LogUtil.c("Step_HlthOpenProv", "getWearEngineQueryResult = ", Boolean.valueOf(e2));
        matrixCursor2.addRow(new Object[]{String.valueOf(e2)});
        return matrixCursor2;
    }

    private boolean c() {
        ArrayList<ContentValues> bondedDevice = ((PluginDeviceApi) Services.c("PluginDevice", PluginDeviceApi.class)).getBondedDevice();
        return (bondedDevice == null || bondedDevice.isEmpty()) ? false : true;
    }

    private boolean e() {
        return b()[0];
    }

    private boolean e(String str) {
        LogUtil.c("Step_HlthOpenProv", "getWearEngineQueryResult callAPiName = ", str);
        boolean[] b = b();
        if (str.equals("getBondedDevices")) {
            return b[1];
        }
        if (str.equals("getCommonDevice")) {
            return b[2];
        }
        if (str.equals("getAllBondedDevices")) {
            return DeviceUtil.a() || c();
        }
        if (str.equals("hasAvailableDevices")) {
            return b[3];
        }
        return false;
    }

    private boolean[] b() {
        boolean[] zArr = {false, false, false, false};
        String e = SharedPreferenceManager.e(String.valueOf(53), "key_wearEngine_device", "");
        if (TextUtils.isEmpty(e)) {
            LogUtil.e("Step_HlthOpenProv", "getWearEngineDevice wearEngineDeviceInfo is null");
            return zArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(e);
            if (jSONObject.has("isHasConnectedWearDevice")) {
                zArr[0] = jSONObject.getBoolean("isHasConnectedWearDevice");
            }
            if (jSONObject.has("isHasSupportHiWearDevice")) {
                zArr[1] = jSONObject.getBoolean("isHasSupportHiWearDevice");
            }
            if (jSONObject.has("isHasSupportCommonDevice")) {
                zArr[2] = jSONObject.getBoolean("isHasSupportCommonDevice");
            }
            if (jSONObject.has("isHasAvailableDevice")) {
                zArr[3] = jSONObject.getBoolean("isHasAvailableDevice");
            }
        } catch (JSONException unused) {
            LogUtil.e("Step_HlthOpenProv", "getWearEngineDevice JSONException");
        }
        return zArr;
    }
}
