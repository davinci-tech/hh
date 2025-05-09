package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.service.ScaleUpdateService;

/* loaded from: classes5.dex */
public class kxp extends HwBaseManager {
    private Context b;

    private kxp(Context context) {
        super(context);
        this.b = context;
    }

    public static kxp c() {
        return b.e;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 1003;
    }

    public void a(String str, String str2, String str3, Boolean bool, String str4) {
        LogUtil.a("Scale_HwScaleVersionManager", "checkBandNewVersionService isAuto" + bool);
        Intent intent = new Intent(this.b, (Class<?>) ScaleUpdateService.class);
        if (bool != null && bool.booleanValue()) {
            intent.setAction("action_scale_auto_check_new_version");
        } else {
            intent.setAction("action_scale_check_new_version");
        }
        intent.putExtra("scale_name", str);
        intent.putExtra("scale_mac_address", str3);
        intent.putExtra("extra_band_version", str2);
        intent.putExtra("extra_band_unique_id", str4);
        this.b.startService(intent);
    }

    public void b(String str) {
        kxy.d(this.b, str);
    }

    public String c(String str) {
        LogUtil.c("Scale_HwScaleVersionManager", "enter getCheckNewBandVersion ");
        return kxy.a(this.b, str);
    }

    public String g(String str) {
        return kxy.c(this.b, str);
    }

    public void e(String str, String str2) {
        kxy.h(str, this.b, str2);
    }

    public void d(String str, String str2) {
        kxy.a(str, this.b, str2);
    }

    public void e(Boolean bool) {
        LogUtil.a("Scale_HwScaleVersionManager", "reportStatus isApp isSuccess =  ", bool);
        Intent intent = new Intent(this.b, (Class<?>) ScaleUpdateService.class);
        if (bool.booleanValue()) {
            intent.setAction("action_band_update_success");
        } else {
            intent.setAction("action_band_update_failed");
        }
        try {
            this.b.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.b("Scale_HwScaleVersionManager", "reportStatus Exception", e.getMessage());
        }
    }

    public void c(String str, String str2) {
        LogUtil.c("Scale_HwScaleVersionManager", "resetBandUpdate");
        b(str);
        kxy.d(this.b, str, str2);
    }

    public void e(String str) {
        LogUtil.c("Scale_HwScaleVersionManager", "downloadPackage");
        Intent intent = new Intent(this.b, (Class<?>) ScaleUpdateService.class);
        intent.setAction("action_scale_download_new_version");
        intent.putExtra("scale_name", str);
        this.b.startService(intent);
    }

    public void a(String str) {
        LogUtil.c("Scale_HwScaleVersionManager", "cancelDownload");
        Intent intent = new Intent(this.b, (Class<?>) ScaleUpdateService.class);
        intent.setAction("action_cancel_download_app");
        intent.putExtra("scale_name", str);
        this.b.startService(intent);
    }

    public void d(String str) {
        kxy.d(this.b, str);
    }

    static class b {
        private static final kxp e = new kxp(BaseApplication.getContext());
    }
}
