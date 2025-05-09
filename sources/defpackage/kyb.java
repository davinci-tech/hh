package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kyb {
    public static void e(final Context context, final boolean z, final CheckUpdateCallBack checkUpdateCallBack) {
        RemoteConfigUtils.d("grayVersionInfo", new RemoteConfigUtils.ConfigCallback() { // from class: kyb.5
            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onSuccess(String str) {
                LogUtil.a("ThirdPartyGrayscaleUtil", "RemoteConfig, getValue is: ", str);
                if (TextUtils.isEmpty(str)) {
                    kyb.b(context, checkUpdateCallBack, z);
                    return;
                }
                c cVar = (c) new Gson().fromJson(str, c.class);
                if (cVar == null || cVar.i() <= CommonUtil.d(context)) {
                    kyb.b(context, checkUpdateCallBack, z);
                } else {
                    checkUpdateCallBack.onUpdateInfo(kyb.bSC_(7, cVar));
                }
            }

            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onFailure(Exception exc, String str) {
                LogUtil.a("ThirdPartyGrayscaleUtil", "RemoteConfig, fetch onFailure");
                kyb.b(context, checkUpdateCallBack, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Intent bSC_(int i, c cVar) {
        LogUtil.a("ThirdPartyGrayscaleUtil", "getApkIntent() in");
        Intent intent = new Intent();
        intent.putExtras(bSD_(i, cVar));
        return intent;
    }

    private static Bundle bSD_(int i, c cVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", i);
        ApkUpgradeInfo apkUpgradeInfo = new ApkUpgradeInfo();
        apkUpgradeInfo.setVersion_(cVar.d());
        apkUpgradeInfo.setSize_(cVar.c());
        apkUpgradeInfo.setNewFeatures_(cVar.e());
        apkUpgradeInfo.setVersionCode_(cVar.i());
        apkUpgradeInfo.setDownurl_(cVar.a());
        apkUpgradeInfo.setSha256_(cVar.b());
        bundle.putParcelable(UpdateKey.INFO, apkUpgradeInfo);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z) {
        LogUtil.a("ThirdPartyGrayscaleUtil", "checkNewVersionFromSdk() in");
        if (z) {
            kyd.b(context, checkUpdateCallBack, false, kyd.b(), false);
        } else {
            kyd.b(context, BaseApplication.getAppPackage(), checkUpdateCallBack);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("message")
        private String f14694a;

        @SerializedName("size")
        private int b;

        @SerializedName("sha256")
        private String c;

        @SerializedName("updateUri")
        private String d;

        @SerializedName("versionCode")
        private int e;

        @SerializedName("version")
        private String i;

        private c() {
        }

        public String d() {
            return this.i;
        }

        public String a() {
            return this.d;
        }

        public int c() {
            return this.b;
        }

        public String e() {
            return this.f14694a;
        }

        public int i() {
            return this.e;
        }

        public String b() {
            return this.c;
        }

        public String toString() {
            return "GrayVersionInfo{versionName='" + this.i + "', updateUri='" + this.d + "', size=" + this.b + ", message='" + this.f14694a + "', versionCode='" + this.e + ", sha256='" + this.c + '}';
        }
    }
}
