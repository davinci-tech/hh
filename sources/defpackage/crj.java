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
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.crj;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes3.dex */
public class crj {
    public static boolean c(String str, String str2) {
        List<msa> e;
        DeviceDownloadSourceInfo j;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("VersionVerifyUtil", "getPluginIndexInfo uuid is empty");
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("VersionVerifyUtil", "getPluginIndexInfo deviceType is empty");
            return false;
        }
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(str2)) {
            List<msx> c = mst.a().c(str);
            LogUtil.a("VersionVerifyUtil", "isPublishVersion devicePluginInfoBeans is ", HiJsonUtil.e(c));
            if (koq.c(c)) {
                msx msxVar = c.get(0);
                if (msxVar.d() != null && msxVar.d().size() > 0) {
                    j = c.get(0).d().get(0).d();
                } else {
                    j = c.get(0).j();
                }
                if (j != null && j.getSite() == 1) {
                    e = cxl.b().d(j.getIndexName());
                } else {
                    e = EzPluginManager.a().e(str2);
                }
            } else {
                e = EzPluginManager.a().e(str2);
            }
        } else {
            e = EzPluginManager.a().b();
        }
        if (e == null) {
            LogUtil.h("VersionVerifyUtil", "deviceList == null");
            return true;
        }
        LogUtil.a("VersionVerifyUtil", "deviceList.size() = ", Integer.valueOf(e.size()));
        for (msa msaVar : e) {
            if (str.equals(msaVar.b())) {
                return cwc.e(msaVar.f());
            }
        }
        LogUtil.h("VersionVerifyUtil", "not in the deviceList");
        return true;
    }

    public static void Lu_(Context context, Activity activity) {
        Lv_(context, activity, null);
    }

    public static void Lv_(final Context context, final Activity activity, final VersionNoSupportCallback versionNoSupportCallback) {
        if (context != null && activity != null) {
            NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(activity).e(context.getResources().getString(R.string.IDS_device_mgr_no_support_device_tips) + System.lineSeparator() + context.getResources().getString(R.string.IDS_device_mgr_no_support_device_common_content)).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: crj.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    boolean bh = CommonUtil.bh();
                    boolean o = Utils.o();
                    LogUtil.a("VersionVerifyUtil", "noSupportDevice isHuaweiSystem:", Boolean.valueOf(bh), " isOverSea:", Boolean.valueOf(o));
                    if (bh && !o) {
                        crj.Lp_(context, activity, versionNoSupportCallback);
                    } else if (o) {
                        crj.Lm_(context, activity, versionNoSupportCallback);
                    } else {
                        crj.Lo_(activity, versionNoSupportCallback);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string._2130843886_res_0x7f0218ee, new View.OnClickListener() { // from class: crj.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VersionNoSupportCallback versionNoSupportCallback2 = VersionNoSupportCallback.this;
                    if (versionNoSupportCallback2 != null) {
                        versionNoSupportCallback2.onDialogClose();
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
            return;
        }
        LogUtil.a("VersionVerifyUtil", "noSupportDevice parameters contain null");
    }

    private static void Ln_(final Context context, final Activity activity) {
        ThreadPoolManager.d().d("jumpToAppGalleryView", new Runnable() { // from class: crj.2
            @Override // java.lang.Runnable
            public void run() {
                String commonCountryCode = GRSManager.a(context).getCommonCountryCode();
                String noCheckUrl = GRSManager.a(context).getNoCheckUrl("domainAppgalleryCloudHuawei", commonCountryCode);
                LogUtil.a("VersionVerifyUtil", "jumpToAppGalleryView countryCode:", commonCountryCode);
                if (TextUtils.isEmpty(noCheckUrl)) {
                    LogUtil.h("VersionVerifyUtil", "jumpToAppGalleryView appGalleryUrl is invalid");
                } else {
                    activity.runOnUiThread(new AnonymousClass1(noCheckUrl));
                }
            }

            /* renamed from: crj$2$1, reason: invalid class name */
            class AnonymousClass1 implements Runnable {
                final /* synthetic */ String b;

                AnonymousClass1(String str) {
                    this.b = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(this.b + "/uowap/index.html#/detailApp/C10414141"));
                    PackageManager packageManager = activity.getPackageManager();
                    if (packageManager != null) {
                        LogUtil.a("VersionVerifyUtil", "jumpToBrowserDownload packageManager is not null");
                        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                        if (resolveActivity != null) {
                            LogUtil.a("VersionVerifyUtil", "jumpToBrowserDownload resolveInfo is not null");
                            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                            String str = resolveActivity.activityInfo.packageName;
                            Activity activity = activity;
                            String string = activity.getString(R.string._2130847432_res_0x7f0226c8);
                            final Activity activity2 = activity;
                            nsn.cLO_(str, activity, string, new View.OnClickListener() { // from class: crm
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    crj.AnonymousClass2.AnonymousClass1.Lw_(activity2, intent, view);
                                }
                            }, null);
                        }
                    }
                }

                static /* synthetic */ void Lw_(Activity activity, Intent intent, View view) {
                    activity.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Lp_(Context context, final Activity activity, final VersionNoSupportCallback versionNoSupportCallback) {
        try {
            final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.health"));
            intent.addFlags(268435456);
            if (jdm.b(context, "com.huawei.appmarket")) {
                intent.setPackage("com.huawei.appmarket");
                nsn.cLO_("com.huawei.appmarket", activity, activity.getString(R.string.IDS_device_fragment_application_market), new View.OnClickListener() { // from class: cro
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        crj.Ls_(activity, intent, versionNoSupportCallback, view);
                    }
                }, new View.OnClickListener() { // from class: crp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        crj.Lt_(VersionNoSupportCallback.this, view);
                    }
                });
            } else {
                LogUtil.a("VersionVerifyUtil", "Not installed Market");
                nrh.d(activity, context.getResources().getString(R.string._2130841726_res_0x7f02107e));
                if (versionNoSupportCallback != null) {
                    versionNoSupportCallback.onDialogClose();
                }
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("VersionVerifyUtil", "jumpToMarket ActivityNotFoundException");
        }
    }

    static /* synthetic */ void Ls_(Activity activity, Intent intent, VersionNoSupportCallback versionNoSupportCallback, View view) {
        activity.startActivity(intent);
        if (versionNoSupportCallback != null) {
            versionNoSupportCallback.onDialogClose();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Lt_(VersionNoSupportCallback versionNoSupportCallback, View view) {
        if (versionNoSupportCallback != null) {
            versionNoSupportCallback.onDialogClose();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Lm_(Context context, final Activity activity, final VersionNoSupportCallback versionNoSupportCallback) {
        try {
            if (jdm.b(context, "com.huawei.appmarket")) {
                LogUtil.a("VersionVerifyUtil", "jumpToAppGallery AppGallery is installed");
                final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.health"));
                intent.addFlags(268435456);
                intent.setPackage("com.huawei.appmarket");
                nsn.cLO_("com.huawei.appmarket", activity, activity.getString(R.string.IDS_device_fragment_application_market), new View.OnClickListener() { // from class: crh
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        crj.Lq_(activity, intent, versionNoSupportCallback, view);
                    }
                }, new View.OnClickListener() { // from class: crn
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        crj.Lr_(VersionNoSupportCallback.this, view);
                    }
                });
            } else {
                LogUtil.h("VersionVerifyUtil", "jumpToAppGallery Not installed AppGallery");
                Ln_(context, activity);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("VersionVerifyUtil", "jumpToAppGallery ActivityNotFoundException");
        }
    }

    static /* synthetic */ void Lq_(Activity activity, Intent intent, VersionNoSupportCallback versionNoSupportCallback, View view) {
        activity.startActivity(intent);
        if (versionNoSupportCallback != null) {
            versionNoSupportCallback.onDialogClose();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Lr_(VersionNoSupportCallback versionNoSupportCallback, View view) {
        if (versionNoSupportCallback != null) {
            versionNoSupportCallback.onDialogClose();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Lo_(final Activity activity, final VersionNoSupportCallback versionNoSupportCallback) {
        jdx.b(new Runnable() { // from class: crj.4

            /* renamed from: crj$4$3, reason: invalid class name */
            class AnonymousClass3 implements Runnable {
                final /* synthetic */ String b;

                AnonymousClass3(String str) {
                    this.b = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final Intent intent = new Intent();
                        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                        intent.setData(Uri.parse(this.b + "/app/C10414141?appId=C10414141&channel=4026633"));
                        PackageManager packageManager = activity.getPackageManager();
                        if (packageManager != null) {
                            LogUtil.a("VersionVerifyUtil", "jumpToBrowserDownload packageManager is not null");
                            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                            if (resolveActivity != null) {
                                LogUtil.a("VersionVerifyUtil", "jumpToBrowserDownload resolveInfo is not null");
                                intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                                String str = resolveActivity.activityInfo.packageName;
                                Activity activity = activity;
                                String string = activity.getString(R.string._2130847432_res_0x7f0226c8);
                                final Activity activity2 = activity;
                                final VersionNoSupportCallback versionNoSupportCallback = versionNoSupportCallback;
                                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: crq
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        crj.AnonymousClass4.AnonymousClass3.Lx_(activity2, intent, versionNoSupportCallback, view);
                                    }
                                };
                                final VersionNoSupportCallback versionNoSupportCallback2 = versionNoSupportCallback;
                                nsn.cLO_(str, activity, string, onClickListener, new View.OnClickListener() { // from class: crr
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        crj.AnonymousClass4.AnonymousClass3.Ly_(VersionNoSupportCallback.this, view);
                                    }
                                });
                            }
                        }
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("VersionVerifyUtil", "jumpToBrowserDownload ActivityNotFoundException");
                    }
                }

                static /* synthetic */ void Lx_(Activity activity, Intent intent, VersionNoSupportCallback versionNoSupportCallback, View view) {
                    activity.startActivity(intent);
                    if (versionNoSupportCallback != null) {
                        versionNoSupportCallback.onDialogClose();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }

                static /* synthetic */ void Ly_(VersionNoSupportCallback versionNoSupportCallback, View view) {
                    if (versionNoSupportCallback != null) {
                        versionNoSupportCallback.onDialogClose();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                activity.runOnUiThread(new AnonymousClass3(GRSManager.a(BaseApplication.getContext()).getUrl("domainAVmall")));
            }
        });
    }
}
