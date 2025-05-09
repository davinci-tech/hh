package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class oaf {
    private static final Object b = new Object();
    private static oaf d;

    /* renamed from: a, reason: collision with root package name */
    private int f15579a = -1;
    private Context c;

    oaf(Context context) {
        this.c = context;
    }

    public int d() {
        return this.f15579a;
    }

    public void c(int i) {
        this.f15579a = i;
    }

    public String e() {
        return kxz.j(this.c);
    }

    public void f(String str) {
        kxz.g(str, this.c);
    }

    public String b() {
        return kxz.f(this.c);
    }

    public void j(String str) {
        kxz.h(str, this.c);
    }

    public void g(String str) {
        kxz.j(str, this.c);
    }

    public static oaf b(Context context) {
        oaf oafVar;
        synchronized (b) {
            LogUtil.a("UpdateInteractors", "getmInstance,mInstance", d);
            if (d == null) {
                LogUtil.a("UpdateInteractors", "new UpdateInteractors()");
                d = new oaf(BaseApplication.getContext());
            }
            oafVar = d;
        }
        return oafVar;
    }

    public void e(String str) {
        Intent intent = new Intent(this.c, (Class<?>) HwUpdateService.class);
        intent.putExtra("extra_band_imei", str);
        intent.setAction("action_band_manual_update_new_version");
        this.c.startService(intent);
    }

    public void a(String str) {
        LogUtil.a("UpdateInteractors", "doDownloadAppFile ");
        if (this.c != null) {
            Intent intent = new Intent(this.c, (Class<?>) HwUpdateService.class);
            intent.putExtra("extra_band_imei", str);
            intent.setAction("action_band_download_new_version");
            try {
                this.c.startService(intent);
            } catch (IllegalStateException unused) {
                LogUtil.b("doDownloadAppFile IllegalStateException", new Object[0]);
            }
        }
    }

    public void d(String str) {
        jkj.d().d(str);
    }

    public String b(String str) {
        LogUtil.a("UpdateInteractors", "enter getBandCheckNewVersion");
        Context context = this.c;
        return context != null ? HwVersionManager.c(context).j(str) : "";
    }

    public String c(String str) {
        LogUtil.a("UpdateInteractors", "enter getBandStorePath");
        Context context = this.c;
        return context != null ? HwVersionManager.c(context).g(str) : "";
    }

    public boolean a() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = this.c.getSystemService("connectivity") instanceof ConnectivityManager ? (ConnectivityManager) this.c.getSystemService("connectivity") : null;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 0) ? false : true;
    }

    public String o(String str) {
        Context context = this.c;
        return context != null ? HwVersionManager.c(context).t(str) : "";
    }

    public boolean i(String str) {
        LogUtil.a("UpdateInteractors", "isOtaFileExist(): path = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("UpdateInteractors", "isOtaFileExist() error, file path is empty...");
            return false;
        }
        boolean exists = new File(str).exists();
        LogUtil.a("UpdateInteractors", "isOtaFileExist: bExist = ", Boolean.valueOf(exists));
        return exists;
    }

    public void h(String str) {
        LogUtil.a("UpdateInteractors", "initUpdateInteractors ");
        jkj.d().a(str);
    }

    public void a(DeviceInfo deviceInfo) {
        if (this.c == null) {
            LogUtil.a("UpdateInteractors", "deleteChangeLog mContext is null");
            return;
        }
        String str = this.c.getFilesDir() + File.separator + "changeLog" + File.separator + deviceInfo.getDeviceUdid();
        LogUtil.a("UpdateInteractors", "deleteChangeLog changeLogPath=", str);
        File file = new File(str);
        if (file.isDirectory() && file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    LogUtil.c("UpdateInteractors", "deleteChangeLog file=", file2.getName());
                    LogUtil.c("UpdateInteractors", "deleteUnUseVersionChangeLog isDelete=", Boolean.valueOf(file2.delete()));
                }
            }
            LogUtil.a("UpdateInteractors", "deleteUnUseVersionChangeLog isDeleteFileDir=", Boolean.valueOf(file.delete()));
        }
    }

    public void c() {
        LogUtil.a("UpdateInteractors", "activateDevice enter");
        Map<String, String> a2 = SharedPreferenceManager.a(this.c, String.valueOf(1017));
        if (a2 == null) {
            LogUtil.b("UpdateInteractors", "activateDevice.getAllSharedPreferencesById() is null!!");
            return;
        }
        DataDeviceInfo d2 = d(a2);
        if (d2 != null) {
            Intent intent = new Intent(this.c, (Class<?>) HwUpdateService.class);
            intent.setAction("action_band_check_new_version_to_activate");
            intent.putExtra("extra_band_type", d2.getDeviceType());
            intent.putExtra("extra_band_version", d2.getDeviceSoftVersion());
            intent.putExtra("extra_band_imei", d2.getDeviceBtMac());
            try {
                this.c.startService(intent);
            } catch (IllegalStateException unused) {
                LogUtil.b("activateDevice IllegalStateException", new Object[0]);
            }
        }
    }

    private DataDeviceInfo d(Map<String, String> map) {
        DataDeviceInfo dataDeviceInfo = null;
        if (map.size() > 9) {
            HashMap hashMap = new HashMap(20);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                DataDeviceInfo dataDeviceInfo2 = (DataDeviceInfo) new Gson().fromJson(entry.getValue(), DataDeviceInfo.class);
                if (dataDeviceInfo2 != null && "false".equalsIgnoreCase(dataDeviceInfo2.getDeviceEmmcId())) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
            SharedPreferenceManager.j(this.c, String.valueOf(1017));
            for (Map.Entry entry2 : hashMap.entrySet()) {
                dataDeviceInfo = (DataDeviceInfo) new Gson().fromJson((String) entry2.getValue(), DataDeviceInfo.class);
                SharedPreferenceManager.e(this.c, Integer.toString(1017), (String) entry2.getKey(), (String) entry2.getValue(), new StorageParams());
            }
        } else {
            for (Map.Entry<String, String> entry3 : map.entrySet()) {
                DataDeviceInfo dataDeviceInfo3 = (DataDeviceInfo) new Gson().fromJson(entry3.getValue(), DataDeviceInfo.class);
                if (dataDeviceInfo3 != null && "false".equalsIgnoreCase(dataDeviceInfo3.getDeviceEmmcId())) {
                    LogUtil.a("UpdateInteractors", "getActivatedDevice device=", iyl.d().e(dataDeviceInfo3.getDeviceBtMac()));
                    dataDeviceInfo = (DataDeviceInfo) new Gson().fromJson(entry3.getValue(), DataDeviceInfo.class);
                }
            }
        }
        return dataDeviceInfo;
    }

    public void c(String str, boolean z) {
        jkv.b().d(str, z);
    }
}
