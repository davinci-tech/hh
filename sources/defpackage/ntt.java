package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.ntt;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ntt {
    private static boolean b(int i) {
        return i == 3 || i == 10 || i == 32;
    }

    public static boolean d(cvc cvcVar, int i, int i2) {
        int i3 = -1;
        if (i == -1) {
            LogUtil.h("AddDeviceChildUtils", "deviceType is -1");
            return true;
        }
        boolean c = c(i);
        if (cvcVar == null || cvcVar.f() == null) {
            LogUtil.h("AddDeviceChildUtils", "ezPluginInfo is null or WearDeviceInfo is null");
            return c;
        }
        Map<String, Integer> d = cvcVar.f().d();
        if (d == null) {
            LogUtil.h("AddDeviceChildUtils", "appVersionMap is null");
            return c;
        }
        if (Utils.o()) {
            if (d.get("overseas") != null) {
                i3 = d.get("overseas").intValue();
            }
        } else if (d.get("domestic") != null) {
            i3 = d.get("domestic").intValue();
        }
        if (i3 <= 0) {
            LogUtil.h("AddDeviceChildUtils", "appversion <= 0");
            return c;
        }
        boolean z = i3 > i2;
        LogUtil.a("AddDeviceChildUtils", "appversion", cvcVar.f().d());
        LogUtil.a("AddDeviceChildUtils", "CommonUtil.getAppVersion(mContext)", Integer.valueOf(i2));
        return z;
    }

    private static boolean c(int i) {
        HashMap<String, Integer> i2 = jfu.i();
        Object[] objArr = new Object[4];
        objArr[0] = "getDefaultUpdate() :";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " uuidTypeMap:";
        objArr[3] = Boolean.valueOf(i2 == null);
        LogUtil.a("AddDeviceChildUtils", objArr);
        if (i2 == null) {
            return true;
        }
        for (Map.Entry<String, Integer> entry : i2.entrySet()) {
            if (entry != null && entry.getKey() != null) {
                if ((i2.get(entry.getKey()) != null ? i2.get(entry.getKey()).intValue() : 0) == i) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int a(cvc cvcVar) {
        if (cvcVar == null) {
            return -1;
        }
        String e = cvcVar.e();
        LogUtil.a("AddDeviceChildUtils", "getDeviceTypeOfPluginInfo pluginUuid is ", e);
        int b = jfu.b(e);
        if (b != -1) {
            return b;
        }
        LogUtil.a("AddDeviceChildUtils", "getDeviceTypeOfPluginInfo");
        jfu.e(cvcVar);
        return jfu.b(e);
    }

    public static void cNE_(Context context, Activity activity) {
        cNF_(context, activity, false);
    }

    public static void cNF_(Context context, final Activity activity, final boolean z) {
        try {
            final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.health"));
            intent.addFlags(268435456);
            if (jdm.b(context, "com.huawei.appmarket")) {
                intent.setPackage("com.huawei.appmarket");
                nsn.cLO_("com.huawei.appmarket", activity, activity.getString(R.string.IDS_device_fragment_application_market), new View.OnClickListener() { // from class: nts
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ntt.cNI_(activity, intent, z, view);
                    }
                }, new View.OnClickListener() { // from class: ntu
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ntt.cNJ_(activity, z, view);
                    }
                });
            } else {
                LogUtil.a("AddDeviceChildUtils", "Not installed Market");
                nrh.d(activity, context.getResources().getString(R.string._2130841726_res_0x7f02107e));
                cNy_(activity, z);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("AddDeviceChildUtils", "jumpToMarket ActivityNotFoundException");
        }
    }

    static /* synthetic */ void cNI_(Activity activity, Intent intent, boolean z, View view) {
        cNP_(activity, intent, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cNJ_(Activity activity, boolean z, View view) {
        cNy_(activity, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void cND_(Context context, Activity activity) {
        try {
            if (jdm.b(context, "com.android.vending") && jdp.c(context)) {
                LogUtil.a("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery GooglePlay is installed");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + context.getPackageName()));
                intent.addFlags(268435456);
                intent.setPackage("com.android.vending");
                activity.startActivity(intent);
            } else if (jdm.b(context, "com.huawei.appmarket")) {
                LogUtil.a("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery AppGallery is installed");
                Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + context.getPackageName()));
                intent2.addFlags(268435456);
                intent2.setPackage("com.huawei.appmarket");
                activity.startActivity(intent2);
            } else {
                LogUtil.h("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery Not installed GooglePlay and AppGallery");
                cNA_(context, activity);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery ActivityNotFoundException");
        }
    }

    private static void cNA_(Context context, Activity activity) {
        cNB_(context, activity, false);
    }

    private static void cNB_(final Context context, final Activity activity, final boolean z) {
        ThreadPoolManager.d().d("jumpToAppGalleryView", new Runnable() { // from class: ntt.1
            @Override // java.lang.Runnable
            public void run() {
                String commonCountryCode = GRSManager.a(context).getCommonCountryCode();
                String noCheckUrl = GRSManager.a(context).getNoCheckUrl("domainUrlDreCloudHuawei", commonCountryCode);
                LogUtil.a("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery countryCode:", commonCountryCode);
                if (TextUtils.isEmpty(noCheckUrl)) {
                    LogUtil.h("AddDeviceChildUtils", "jumpToGooglePlayOrAppGallery appGalleryUrl is invalid");
                } else {
                    activity.runOnUiThread(new AnonymousClass5(noCheckUrl));
                }
            }

            /* renamed from: ntt$1$5, reason: invalid class name */
            class AnonymousClass5 implements Runnable {
                final /* synthetic */ String e;

                AnonymousClass5(String str) {
                    this.e = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(this.e + "/97J6mTfn5S"));
                    PackageManager packageManager = activity.getPackageManager();
                    if (packageManager != null) {
                        LogUtil.a("AddDeviceChildUtils", "jumpToBrowserDownload packageManager is not null");
                        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                        if (resolveActivity != null) {
                            LogUtil.a("AddDeviceChildUtils", "jumpToBrowserDownload resolveInfo is not null");
                            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                            String str = resolveActivity.activityInfo.packageName;
                            Activity activity = activity;
                            String string = activity.getString(R.string._2130847432_res_0x7f0226c8);
                            final Activity activity2 = activity;
                            final boolean z = z;
                            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: ntz
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ntt.AnonymousClass1.AnonymousClass5.cNQ_(activity2, intent, z, view);
                                }
                            };
                            final Activity activity3 = activity;
                            final boolean z2 = z;
                            nsn.cLO_(str, activity, string, onClickListener, new View.OnClickListener() { // from class: nty
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ntt.AnonymousClass1.AnonymousClass5.cNR_(activity3, z2, view);
                                }
                            });
                        }
                    }
                }

                static /* synthetic */ void cNQ_(Activity activity, Intent intent, boolean z, View view) {
                    activity.startActivity(intent);
                    ntt.cNy_(activity, z);
                    ViewClickInstrumentation.clickOnView(view);
                }

                static /* synthetic */ void cNR_(Activity activity, boolean z, View view) {
                    ntt.cNy_(activity, z);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    public static void cNK_(Context context, Activity activity) {
        cNL_(context, activity, true);
    }

    public static void cNL_(Context context, Activity activity, boolean z) {
        boolean bh = CommonUtil.bh();
        boolean o = Utils.o();
        boolean haveNewAppVersion = ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).haveNewAppVersion(context);
        if (o) {
            if (jdm.b(context, "com.huawei.appmarket") || !haveNewAppVersion) {
                cNN_(context, activity, z);
                return;
            } else {
                cNO_(context, activity, z);
                return;
            }
        }
        if (bh || !haveNewAppVersion) {
            cNN_(context, activity, z);
        } else {
            cNO_(context, activity, z);
        }
    }

    private static void cNN_(final Context context, final Activity activity, final boolean z) {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(activity).e(context.getResources().getString(R.string.IDS_device_mgr_no_support_device_tips) + System.lineSeparator() + context.getResources().getString(R.string.IDS_device_mgr_no_support_device_common_content)).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: ntt.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean bh = CommonUtil.bh();
                boolean o = Utils.o();
                oau.c(100097, "");
                LogUtil.a("AddDeviceChildUtils", "noSupportDevice isHuaweiSystem:", Boolean.valueOf(bh), " isOverSea:", Boolean.valueOf(o));
                if (bh && !o) {
                    ntt.cNF_(context, activity, z);
                } else if (o) {
                    ntt.cNz_(context, activity, z);
                } else {
                    ntt.cNC_(activity, z);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: ntt.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity2;
                oau.c(100098, "");
                if (z && (activity2 = activity) != null && !activity2.isFinishing()) {
                    activity.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (activity.isFinishing()) {
            return;
        }
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cNz_(Context context, final Activity activity, final boolean z) {
        try {
            if (jdm.b(context, "com.huawei.appmarket")) {
                LogUtil.a("AddDeviceChildUtils", "jumpToAppGallery AppGallery is installed");
                final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.health"));
                intent.addFlags(268435456);
                intent.setPackage("com.huawei.appmarket");
                nsn.cLO_("com.huawei.appmarket", activity, activity.getString(R.string.IDS_device_fragment_application_market), new View.OnClickListener() { // from class: ntv
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ntt.cNG_(activity, intent, z, view);
                    }
                }, new View.OnClickListener() { // from class: ntw
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ntt.cNH_(activity, z, view);
                    }
                });
            } else {
                LogUtil.h("AddDeviceChildUtils", "jumpToAppGallery Not installed AppGallery");
                cNB_(context, activity, z);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("AddDeviceChildUtils", "jumpToAppGallery ActivityNotFoundException");
        }
    }

    static /* synthetic */ void cNG_(Activity activity, Intent intent, boolean z, View view) {
        activity.startActivity(intent);
        cNy_(activity, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cNH_(Activity activity, boolean z, View view) {
        cNy_(activity, z);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void cNO_(final Context context, final Activity activity, final boolean z) {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(activity).e(context.getResources().getString(R.string._2130839533_res_0x7f0207ed)).czC_(R.string._2130841856_res_0x7f021100, new View.OnClickListener() { // from class: ntt.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity2;
                oau.c(100097, "");
                HwVersionManager.c(context).c();
                Context context2 = context;
                nrh.c(context2, context2.getString(R.string._2130843516_res_0x7f02177c));
                if (z && (activity2 = activity) != null && !activity2.isFinishing()) {
                    activity.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: ntt.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity2;
                oau.c(100098, "");
                if (z && (activity2 = activity) != null && !activity2.isFinishing()) {
                    activity.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (activity.isFinishing()) {
            return;
        }
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cNC_(final Activity activity, final boolean z) {
        jdx.b(new Runnable() { // from class: ntt.9

            /* renamed from: ntt$9$4, reason: invalid class name */
            class AnonymousClass4 implements Runnable {
                final /* synthetic */ String c;

                AnonymousClass4(String str) {
                    this.c = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    final Intent intent = new Intent();
                    intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                    intent.setData(Uri.parse(this.c + "/1Lfn1eswP6"));
                    PackageManager packageManager = activity.getPackageManager();
                    if (packageManager != null) {
                        LogUtil.a("AddDeviceChildUtils", "jumpToBrowserDownload packageManager is not null");
                        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                        if (resolveActivity != null) {
                            LogUtil.a("AddDeviceChildUtils", "jumpToBrowserDownload resolveInfo is not null");
                            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                            String str = resolveActivity.activityInfo.packageName;
                            Activity activity = activity;
                            String string = activity.getString(R.string._2130847432_res_0x7f0226c8);
                            final Activity activity2 = activity;
                            final boolean z = z;
                            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: ntx
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ntt.AnonymousClass9.AnonymousClass4.cNS_(activity2, intent, z, view);
                                }
                            };
                            final Activity activity3 = activity;
                            final boolean z2 = z;
                            nsn.cLO_(str, activity, string, onClickListener, new View.OnClickListener() { // from class: nub
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ntt.AnonymousClass9.AnonymousClass4.cNT_(activity3, z2, view);
                                }
                            });
                        }
                    }
                }

                static /* synthetic */ void cNS_(Activity activity, Intent intent, boolean z, View view) {
                    ntt.cNP_(activity, intent, z);
                    ViewClickInstrumentation.clickOnView(view);
                }

                static /* synthetic */ void cNT_(Activity activity, boolean z, View view) {
                    ntt.cNy_(activity, z);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                activity.runOnUiThread(new AnonymousClass4(GRSManager.a(BaseApplication.getContext()).getUrl("domainUrlCloudHuawei")));
            }
        });
    }

    public static Intent cNM_(Intent intent, int i, oae oaeVar) {
        if (intent != null) {
            if (!b(i)) {
                intent.putExtra("pairGuideW1Success", false);
                return intent;
            }
            DeviceInfo a2 = oaeVar.a();
            if (a2 != null && a2.getDeviceBluetoothType() != 5) {
                LogUtil.a("AddDeviceChildUtils", "enterW1PairGuide deviceInfo ", a2.toString());
                if (b(a2.getProductType()) && a2.getDeviceConnectState() == 2) {
                    intent.putExtra("pairGuideW1Success", true);
                }
            }
        }
        return intent;
    }

    public static void c() {
        if (DeviceBindingFragment.getInstance() == null || DeviceBindingFragment.getInstance().get() == null) {
            return;
        }
        DeviceBindingFragment.getInstance().get().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cNP_(Activity activity, Intent intent, boolean z) {
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("AddDeviceChildUtils", "startActivity exception: ");
        }
        cNy_(activity, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cNy_(Activity activity, boolean z) {
        if (!z || activity == null || activity.isFinishing()) {
            return;
        }
        activity.finish();
    }
}
