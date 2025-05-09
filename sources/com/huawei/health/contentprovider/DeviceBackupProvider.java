package com.huawei.health.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import defpackage.bio;
import defpackage.bjv;
import defpackage.bjy;
import defpackage.bll;
import defpackage.bmm;
import defpackage.cun;
import defpackage.cuv;
import defpackage.jnu;
import defpackage.jsz;
import defpackage.jta;
import defpackage.jte;
import defpackage.jut;
import defpackage.juu;
import defpackage.msp;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes8.dex */
public class DeviceBackupProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final UriMatcher f2214a;
    private String b;
    private boolean d = false;
    private String e;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f2214a = uriMatcher;
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/keyvaldb_encrypt_udsdevice", 1);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/keyvaldb_encrypt_udsdevice.crc", 2);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/keyvaldb_unencrypt", 3);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/keyvaldb_unencrypt.crc", 4);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "shared_prefs/relation.xml", 5);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/plugin.zip", 6);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "databases/notification.db", 7);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "databases/notification.db-journal", 8);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/1000", 9);
        uriMatcher.addURI("com.huawei.health.provider.DeviceBackupProvider", "files/mmkv/1000.crc", 10);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        LogUtil.c("DeviceBackupProvider", "onCreate");
        return true;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        e();
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("DeviceBackupProvider", "call unkonw method");
            return null;
        }
        if ("backup_start".equals(str)) {
            return Ds_(str2, bundle);
        }
        if ("backup_query".equals(str)) {
            return Dp_(str2, bundle);
        }
        if ("backup_complete".equals(str)) {
            return Do_();
        }
        if ("backup_recover_start".equals(str)) {
            return Dr_(bundle);
        }
        if ("backup_recover_complete".equals(str)) {
            return Dq_();
        }
        if ("notifyCloneFinish".equals(str)) {
            c();
            return null;
        }
        LogUtil.e("DeviceBackupProvider", "call unkonw method: ", str);
        return null;
    }

    private Bundle Ds_(String str, Bundle bundle) {
        LogUtil.c("DeviceBackupProvider", "handleBackupStart arg: ", str, " extras: ", bundle);
        String lowerCase = Build.BRAND.toLowerCase(Locale.ENGLISH);
        LogUtil.c("DeviceBackupProvider", "handleBackupStart phoneBrand: ", lowerCase, " versionCode: ", Integer.valueOf(Build.VERSION.SDK_INT));
        if ("backup".equals(str) && bundle != null) {
            LogUtil.c("DeviceBackupProvider", "handleBackupStart backup enter");
            if (!"huawei".equals(lowerCase) || CommonUtil.ao()) {
                LogUtil.c("DeviceBackupProvider", "handleBackupStart is emui system or third, not support.");
                return new Bundle();
            }
            Bundle bundle2 = bundle.getBundle("new_phone_ability_info");
            if (bundle2 == null) {
                LogUtil.c("DeviceBackupProvider", "handleBackupStart newPhoneBundle is null.");
                return new Bundle();
            }
            String string = bundle2.getString("version_number");
            String string2 = bundle2.getString("new_phone_bluetooth_name");
            String string3 = bundle2.getString("new_phone_brand_name");
            boolean z = bundle2.getBoolean("new_phone_is_emui");
            LogUtil.c("DeviceBackupProvider", "backup versionNumber: ", string, " newPhoneBluetoothName: ", string2, " newPhoneBrandName: ", string3, " newPhoneIsEmui: ", Boolean.valueOf(z));
            if (!"huawei".equals(string3) || z) {
                return new Bundle();
            }
            jut.c().b(string2);
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "handleBackupStart");
            if (deviceList == null || deviceList.size() == 0) {
                return new Bundle();
            }
            ArrayList arrayList = new ArrayList(16);
            ArrayList arrayList2 = new ArrayList(16);
            HashMap hashMap = new HashMap(16);
            for (DeviceInfo deviceInfo : deviceList) {
                if (bmm.a(deviceInfo, 223) && deviceInfo.getDeviceActiveState() == 1) {
                    arrayList2.add(deviceInfo.getDeviceIdentify());
                    hashMap.put(deviceInfo.getDeviceIdentify(), true);
                } else {
                    arrayList.add(deviceInfo.getDeviceIdentify());
                }
            }
            jut.c().e(hashMap);
            LogUtil.c("DeviceBackupProvider", "handleBackupStart mSupportConnectNewDevices: ", Arrays.asList(arrayList2));
            jut.c().d(arrayList2);
            jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
            return null;
        }
        if ("restore".equals(str)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("version_number", jnu.h());
            bundle3.putString("new_phone_bluetooth_name", a());
            bundle3.putString("new_phone_brand_name", lowerCase);
            bundle3.putBoolean("new_phone_is_emui", CommonUtil.ao());
            Bundle bundle4 = new Bundle();
            bundle4.putBundle("ability_info", bundle3);
            LogUtil.c("DeviceBackupProvider", "handleBackupStart restore abilityBundle: ", bundle3);
            return bundle4;
        }
        LogUtil.c("DeviceBackupProvider", "handleBackupStart else arg.");
        return null;
    }

    private Bundle Dp_(String str, Bundle bundle) {
        List<DeviceInfo> deviceList;
        LogUtil.c("DeviceBackupProvider", "handleBackupQuery arg: ", str);
        String lowerCase = Build.BRAND.toLowerCase(Locale.ENGLISH);
        LogUtil.c("DeviceBackupProvider", "handleBackupQuery phoneBrand: ", lowerCase, " versionCode: ", Integer.valueOf(Build.VERSION.SDK_INT));
        if (!"huawei".equals(lowerCase) || CommonUtil.ao()) {
            LogUtil.c("DeviceBackupProvider", "handleBackupQuery is emui system or third, not support.");
            return null;
        }
        if ("backup".equals(str) && ((deviceList = jsz.b(BaseApplication.getContext()).getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "handleBackupQuery")) == null || deviceList.size() == 0)) {
            LogUtil.a("DeviceBackupProvider", "handleBackupQuery no device, return.");
            return null;
        }
        LogUtil.c("DeviceBackupProvider", "handleBackupQuery insert data");
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_encrypt_udsdevice");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_encrypt_udsdevice.crc");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_unencrypt");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_unencrypt.crc");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/shared_prefs/relation.xml");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/plugin.zip");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/databases/notification.db");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/databases/notification.db-journal");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/1000");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/1000.crc");
        Bundle bundle2 = new Bundle();
        bundle2.putInt("version", 1);
        bundle2.putStringArrayList("openfile_uri_list", arrayList);
        LogUtil.c("DeviceBackupProvider", "handleBackupQuery result: ", bundle2);
        return bundle2;
    }

    private static String a() {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, "ro.config.marketing_name");
            if (invoke != null && (invoke instanceof String)) {
                return (String) invoke;
            }
        } catch (Exception e) {
            LogUtil.a("DeviceBackupProvider", "getMarketingName Exception: ", bll.a(e));
        }
        return "";
    }

    public void c() {
        LogUtil.c("DeviceBackupProvider", "notifyCloneFinish enter");
        if (NetworkUtil.i()) {
            LogUtil.c("DeviceBackupProvider", "notifyCloneFinish have network");
            h();
            return;
        }
        LogUtil.c("DeviceBackupProvider", "notifyCloneFinish no have network");
        if (this.d) {
            return;
        }
        this.d = true;
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        String accountInfo = LoginInit.getInstance(getContext()).getAccountInfo(1011);
        this.e = accountInfo;
        LogUtil.c("DeviceBackupProvider", "handleBackupRecoverComplete mOldUserId: ", accountInfo);
        HuaweiLoginManager.getInstance().hmsHasLogined(new ILoginCallback() { // from class: com.huawei.health.contentprovider.DeviceBackupProvider.5
            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginSuccess(Object obj) {
                DeviceBackupProvider deviceBackupProvider = DeviceBackupProvider.this;
                deviceBackupProvider.b = LoginInit.getInstance(deviceBackupProvider.getContext()).getAccountInfo(1011);
                LogUtil.c("DeviceBackupProvider", "backup onLoginSuccess currentHuid: ", DeviceBackupProvider.this.b, " mOldUserId: ", DeviceBackupProvider.this.e);
                DeviceBackupProvider.this.d = true;
                if (DeviceBackupProvider.this.b.equals(DeviceBackupProvider.this.e)) {
                    jut.c().a(true);
                    jut.c().b();
                    DeviceBackupProvider.this.g();
                    return;
                }
                List<DeviceInfo> deviceList = jsz.b(BaseApplication.getContext()).getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "cloneSignIn");
                ArrayList arrayList = new ArrayList();
                if (deviceList == null || deviceList.size() == 0) {
                    return;
                }
                for (DeviceInfo deviceInfo : deviceList) {
                    if (deviceInfo.getSupportAccountSwitch() == 1) {
                        arrayList.add(deviceInfo.getDeviceIdentify());
                    }
                }
                jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
            }

            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginFailed(Object obj) {
                if (DeviceBackupProvider.this.d) {
                    return;
                }
                DeviceBackupProvider.this.d = true;
                DeviceBackupProvider.this.j();
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        new Timer().schedule(new TimerTask() { // from class: com.huawei.health.contentprovider.DeviceBackupProvider.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (NetworkUtil.i()) {
                    LogUtil.c("DeviceBackupProvider", "notifyCloneFinish have network again");
                    DeviceBackupProvider.this.h();
                } else {
                    LogUtil.c("DeviceBackupProvider", "notifyCloneFinish no have network again");
                    DeviceBackupProvider.this.g();
                }
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (EnvironmentInfo.j() || bjv.e().b() == null) {
            return;
        }
        LogUtil.c("DeviceBackupProvider", "singIn isHarmony4AndLater startAllReconnect.");
        bjy.d().c(1);
    }

    private Bundle Do_() {
        LogUtil.c("DeviceBackupProvider", "handleBackupComplete enter.");
        return null;
    }

    private Bundle Dr_(Bundle bundle) {
        LogUtil.c("DeviceBackupProvider", "handleBackupRecoverStart start.");
        boolean z = bundle.getInt("version", 1) <= 1;
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_encrypt_udsdevice");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_encrypt_udsdevice.crc");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_unencrypt");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/keyvaldb_unencrypt.crc");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/shared_prefs/relation.xml");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/plugin.zip");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/databases/notification.db");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/databases/notification.db-journal");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/1000");
        arrayList.add("content://com.huawei.health.provider.DeviceBackupProvider/files/mmkv/1000.crc");
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("permit", z);
        bundle2.putStringArrayList("openfile_uri_list", arrayList);
        LogUtil.c("DeviceBackupProvider", "handleBackupRecoverStart result: ", bundle2);
        return bundle2;
    }

    private Bundle Dq_() {
        LogUtil.c("DeviceBackupProvider", "handleBackupRecoverComplete enter.");
        msp.c(getContext().getFilesDir().getPath() + File.separator + "plugin.zip", cuv.f11488a);
        juu.e();
        jte.a(true, true);
        jta.d();
        if (EnvironmentInfo.j()) {
            return null;
        }
        LogUtil.c("DeviceBackupProvider", "handleBackupRecoverComplete isHarmony4AndLater is true.");
        bio.e(true);
        bio.c();
        return null;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        String str2;
        boolean z;
        LogUtil.c("DeviceBackupProvider", "openFile uri: ", uri.toString(), " mode: ", str, " lastPath: ", uri.getLastPathSegment());
        int match = f2214a.match(uri);
        LogUtil.c("DeviceBackupProvider", "openFile code: ", Integer.valueOf(match));
        switch (match) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case 10:
                str2 = getContext().getFilesDir().getPath() + File.separator + "mmkv" + File.separator + uri.getLastPathSegment();
                break;
            case 5:
                str2 = getContext().getDataDir().getPath() + File.separator + "shared_prefs" + File.separator + uri.getLastPathSegment();
                break;
            case 6:
                str2 = getContext().getFilesDir().getPath() + File.separator + uri.getLastPathSegment();
                break;
            case 7:
            case 8:
                str2 = getContext().getDataDir().getPath() + File.separator + "databases" + File.separator + uri.getLastPathSegment();
                break;
            default:
                str2 = "";
                break;
        }
        LogUtil.c("DeviceBackupProvider", "openFile path: ", str2);
        File file = new File(str2);
        int i = "r".equals(str) ? 268435456 : 536870912;
        LogUtil.c("DeviceBackupProvider", "openFile openFileMode: ", Integer.valueOf(i));
        if (i == 536870912 && !file.exists()) {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                LogUtil.e("DeviceBackupProvider", "openFile createNewFile e: ", e.getMessage());
                z = false;
            }
            LogUtil.c("DeviceBackupProvider", "openFile isSuccess: ", Boolean.valueOf(z));
        }
        ParcelFileDescriptor open = ParcelFileDescriptor.open(file, i);
        LogUtil.c("DeviceBackupProvider", "openFile parcelFileDescriptor: ", open.toString());
        return open;
    }

    private void e() {
        int callingUid = Binder.getCallingUid();
        Context context = getContext();
        String readPermission = getReadPermission();
        int callingPid = Binder.getCallingPid();
        if (TextUtils.isEmpty(readPermission)) {
            return;
        }
        context.enforcePermission(readPermission, callingPid, callingUid, "Permission Denial: reading " + getClass().getName() + " from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " requires " + readPermission);
    }
}
