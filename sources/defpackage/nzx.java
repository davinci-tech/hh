package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.devicepair.api.MessageNotificationApi;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.systemmanager.notificationmanager.HwNotificationManagerEx;
import com.huawei.systemmanager.notificationmanager.IHwNotificationManager;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.jje;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.MagicBuild;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@ApiDefine(uri = MessageNotificationApi.class)
@Singleton
/* loaded from: classes6.dex */
public class nzx implements MessageNotificationApi {
    private String b;

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public boolean isSupportNotify(String str) {
        if (!oae.c(BaseApplication.getContext()).h()) {
            LogUtil.a("MessageNotificationApiImpl", "not support intelligent");
            new NotificationPushInteractor(BaseApplication.getContext()).a(bfg.e, 0);
        }
        DeviceCapability e = cvs.e(str);
        if (e != null) {
            return e.isMessageAlert();
        }
        return false;
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public int queryMessageNotificationStatus() {
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(BaseApplication.getContext());
        if (!notificationPushInteractor.e()) {
            notificationPushInteractor.e(0);
        }
        return notificationPushInteractor.b() ? 1 : 0;
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public int setMessageNotificationStatus(Context context, String str, int i) {
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(BaseApplication.getContext());
        if (i == 1) {
            notificationPushInteractor.d(1);
            if (!notificationPushInteractor.e()) {
                LogUtil.a("MessageNotificationApiImpl", "mNotificationSwitch true isAuthorizeEnabled is false");
                c(notificationPushInteractor, str, context);
            } else {
                LogUtil.a("MessageNotificationApiImpl", "mNotificationSwitch true isAuthorizeEnabled is true");
                a(notificationPushInteractor, str);
            }
            if (nwy.h()) {
                LogUtil.a("MessageNotificationApiImpl", "is support notification push icon.");
                b(context);
            }
            e(notificationPushInteractor);
            jjd.b(BaseApplication.getContext()).c(true, true);
            e(context, str, true);
        } else {
            LogUtil.a("MessageNotificationApiImpl", "mNotificationSwitch false isAuthorizeEnabled is false");
            if (notificationPushInteractor.b()) {
                notificationPushInteractor.d(0);
                ContentResolver bJc_ = jrg.bJc_(context, "MessageNotificationApiImpl");
                if (bJc_ != null) {
                    jrg.bJe_(bJc_, false, "MessageNotificationApiImpl");
                }
                a(notificationPushInteractor, str);
                e(context, str, false);
            } else {
                e(context, str, false);
            }
        }
        return 1;
    }

    private void c(NotificationPushInteractor notificationPushInteractor, String str, Context context) {
        ContentResolver bJc_ = jrg.bJc_(context, "MessageNotificationApiImpl");
        boolean bJe_ = bJc_ != null ? jrg.bJe_(bJc_, true, "MessageNotificationApiImpl") : false;
        LogUtil.a("MessageNotificationApiImpl", "checkedChangeSub isSuccess is ", Boolean.valueOf(bJe_));
        if (bJe_) {
            a(notificationPushInteractor, str);
        }
    }

    protected void e(Context context, String str, boolean z) {
        DeviceCapability e = cvs.e(str);
        if (e != null && e.isSupportMessageAlertInfo()) {
            boolean c = jjb.b().c();
            LogUtil.a("MessageNotificationApiImpl", "createNotificationEnableIntent notificationStatus is ", Boolean.valueOf(c), ",isTurnOnSwitch is.", Boolean.valueOf(z));
            Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
            intent.setAction("com.huawei.health.ACTION_NOTIFICATION_ENABLE_PUSH");
            intent.putExtra("notificationEnablePushType", "notificationEnablePushMainSwitch");
            intent.putExtra("notificationEnableStatus", c);
            intent.putExtra("notificationEnableTurnOnSwitch", z);
            context.startService(intent);
            return;
        }
        LogUtil.h("MessageNotificationApiImpl", "createNotificationEnableIntent currentDevice is not support notification");
    }

    public static void a(NotificationPushInteractor notificationPushInteractor, String str) {
        if (MagicBuild.f13130a && !CommonUtil.y(BaseApplication.getContext())) {
            LogUtil.h("MessageNotificationApiImpl", "reportStatusToMidware isNewHonor oversea phone return");
            return;
        }
        boolean z = notificationPushInteractor.d(bfg.e) != 0;
        LogUtil.a("MessageNotificationApiImpl", "isAuthorizeEnabled:", Boolean.valueOf(notificationPushInteractor.b()), " prompt: ", Boolean.valueOf(z));
        if (cvs.e(str) == null || !cvs.e(str).isSupportMidware()) {
            return;
        }
        jjb.b().b(notificationPushInteractor.b(), z);
    }

    private void b(Context context) {
        Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
        intent.setPackage(context.getPackageName());
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        Bundle bundle = new Bundle();
        bundle.putString("notificationSwitchChangeType", "notificationSwitchChangeType");
        intent.putExtras(bundle);
        context.startService(intent);
    }

    private void e(NotificationPushInteractor notificationPushInteractor) {
        LogUtil.a("MessageNotificationApiImpl", "enter saveFirstOpenAppToDb");
        int parseInt = Integer.parseInt("1");
        for (String str : bfg.d) {
            if (notificationPushInteractor.d(str) == parseInt) {
                NotificationContentProviderUtil.b(str, parseInt);
            }
        }
        if (notificationPushInteractor.d(bfg.e) == parseInt) {
            NotificationContentProviderUtil.b(bfg.e, parseInt);
        }
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public boolean setNotificationRemindStatus(Context context, int i, int i2) {
        if (i == 2) {
            a(context, "switch_smart_notify_reminder", i2);
            return true;
        }
        a(context, "switch_silent_notify_using_phone", i2);
        return true;
    }

    private void a(final Context context, final String str, int i) {
        jqi.a().setSwitchSetting(str, String.valueOf(i), new IBaseResponseCallback() { // from class: nzx.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                nzx.this.c(context);
                if ("switch_smart_notify_reminder".equals(str)) {
                    nwy.e();
                }
                if ("switch_smart_notify_reminder".equals(str) && i2 == 0) {
                    NotificationContentProviderUtil.f();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
        intent.setPackage(context.getPackageName());
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        Bundle bundle = new Bundle();
        bundle.putString("notificationSwitchChangeType", "notification_collaborate_switch_change");
        intent.putExtras(bundle);
        context.startService(intent);
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public void queryApplicationInfoList(final Context context, final String str, final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().d("MessageNotificationApiImpl", new Runnable() { // from class: nzx.2
            @Override // java.lang.Runnable
            public void run() {
                nzx nzxVar = nzx.this;
                Context context2 = context;
                iBaseResponseCallback.d(0, nzxVar.d(context2, (List<jje>) nzxVar.d(context2), str));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<jje> d(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        return d(context, context.getPackageManager().queryIntentActivities(intent, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<bgf> d(Context context, List<jje> list, String str) {
        ArrayList arrayList = new ArrayList(16);
        for (jje jjeVar : list) {
            bgf bgfVar = new bgf();
            Drawable bHv_ = jjeVar.bHv_();
            if (bHv_ == null && (bHv_ = jrg.bJa_(jjeVar.g())) == null) {
                bHv_ = context.getResources().getDrawable(R.mipmap.notification_icon_sms);
                LogUtil.h("MessageNotificationApiImpl", "onBindViewHolder drawable is null ", jjeVar.g());
            }
            bgfVar.a(nzy.cTv_(nrf.cHF_(bHv_)));
            bgfVar.d(jjeVar.c());
            bgfVar.c(jjeVar.g());
            bgfVar.b(String.valueOf(jjeVar.b()));
            bgfVar.e(jjeVar.d() == 1 ? "1" : "0");
            a(context, jjeVar, str);
            d(context, jjeVar);
            arrayList.add(bgfVar);
        }
        return arrayList;
    }

    private void a(Context context, jje jjeVar, String str) {
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(context);
        int i = jjeVar.d() == 1 ? 1 : 0;
        boolean z = jjeVar.d() == 1;
        LogUtil.a("MessageNotificationApiImpl", "current authority:", Integer.valueOf(i), ", flag:", Integer.valueOf(jjeVar.d()), " pkg:", jjeVar.g());
        if (i != jjeVar.d()) {
            jjeVar.b(i);
            notificationPushInteractor.a(jjeVar.g(), i);
        }
        nwx.d().d(jjeVar.g(), z, str, notificationPushInteractor);
    }

    private void d(Context context, jje jjeVar) {
        LogUtil.a("MessageNotificationApiImpl", "getView isSetFirstOpen: ", Boolean.valueOf("true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", 10001))));
        int intValue = Integer.valueOf("1").intValue();
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(context);
        LogUtil.a("MessageNotificationApiImpl", "packagename:", jjeVar.c(), " is autho: ", Integer.valueOf(jjeVar.d()), " is auto 2:", Integer.valueOf(notificationPushInteractor.d(jjeVar.g())));
        if (notificationPushInteractor.d(jjeVar.g()) == intValue) {
            NotificationContentProviderUtil.b(jjeVar.g(), intValue);
        }
    }

    private List<jje> d(Context context, List<ResolveInfo> list) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        a(context, arrayList, arrayList2);
        d(context, arrayList, arrayList2);
        d(context, list, arrayList, arrayList2, arrayList3);
        Collections.sort(arrayList3, new jje.b());
        ArrayList arrayList4 = new ArrayList(arrayList.size() + arrayList2.size() + arrayList3.size());
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        LogUtil.a("MessageNotificationApiImpl", "loadAllAppInfo size:", Integer.valueOf(arrayList4.size()));
        return arrayList4;
    }

    private void a(Context context, List<jje> list, List<jje> list2) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(bfg.d);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            jje jjeVar = new jje();
            if (c(context, str, jjeVar, true)) {
                if (jjeVar.d() == 1) {
                    list.add(jjeVar);
                } else {
                    list2.add(jjeVar);
                }
            }
        }
    }

    private void d(Context context, List<ResolveInfo> list, List<jje> list2, List<jje> list3, List<jje> list4) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).activityInfo.packageName;
            if (!bfg.c.contains(str) && !bfg.d.contains(str)) {
                if (arrayList2.contains(str)) {
                    LogUtil.h("MessageNotificationApiImpl", "obtainOtherPushApp contains packageName:", str);
                } else {
                    jje jjeVar = new jje();
                    if (c(context, str, jjeVar, false)) {
                        arrayList2.add(str);
                        if (jjeVar.d() == 1) {
                            list2.add(jjeVar);
                        } else if (bfg.f348a.contains(str)) {
                            arrayList.add(jjeVar);
                        } else {
                            list4.add(jjeVar);
                        }
                    }
                }
            }
        }
        arrayList2.clear();
        list3.addAll(nwy.e(arrayList));
    }

    private boolean c(Context context, String str, jje jjeVar, boolean z) {
        PackageInfo bJd_ = jrg.bJd_(str);
        if (bJd_ == null) {
            LogUtil.h("MessageNotificationApiImpl", "hasObtainSingleAppInfo packageInfo is null, packageName:", str);
            return false;
        }
        String c = nwy.c(context, str);
        if (TextUtils.isEmpty(c) || TextUtils.equals(str, c) || "Calendar data migrator".equals(c)) {
            LogUtil.h("MessageNotificationApiImpl", "hasObtainSingleAppInfo appName is invalid, packageName:", str);
            return false;
        }
        int cRn_ = nwy.cRn_(context.getPackageManager(), str);
        jjeVar.c(str);
        jjeVar.d(String.valueOf(bJd_.versionCode));
        jjeVar.c(cRn_);
        String e = kiq.e("MessageNotificationApiImpl");
        if (TextUtils.isEmpty(this.b)) {
            this.b = c();
        }
        boolean ak = LanguageUtil.ak(context);
        if (!(ak && TextUtils.equals(str, e)) && (ak || !TextUtils.equals(str, this.b))) {
            jjeVar.a(c);
        } else {
            if (!z) {
                return false;
            }
            jjeVar.a(context.getResources().getString(R.string.IDS_short_message));
            jjeVar.bHw_(context.getResources().getDrawable(R.mipmap.notification_icon_sms));
            LogUtil.a("MessageNotificationApiImpl", "hasObtainSingleAppInfo smsApp style changed, origin name:", c, " packageName:", str);
        }
        int d = new NotificationPushInteractor(BaseApplication.getContext()).d(str);
        LogUtil.a("MessageNotificationApiImpl", "hasObtainSingleAppInfo packageName:", str, " appName:", jjeVar.c(), " authorizeFlag:", Integer.valueOf(d));
        jjeVar.b(a(str, d, jjeVar));
        return true;
    }

    private int a(String str, int i, jje jjeVar) {
        if ((!TextUtils.equals(kiq.e("MessageNotificationApiImpl"), str) && !bfg.d.contains(str)) || "true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", 10001)) || i == 1) {
            return i;
        }
        jjeVar.b(1);
        new NotificationPushInteractor(BaseApplication.getContext()).a(str, 1);
        return 1;
    }

    private String c() {
        String str;
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList("com.android.mms", "cn.nubia.mms", "com.hihonor.mms", "com.samsung.android.messaging", "com.htc.sense.mms", "com.oneplus.mms", "com.google.android.apps.messaging"));
        Iterator<ResolveInfo> it = BaseApplication.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:")), 1048576).iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            ResolveInfo next = it.next();
            if (next != null && next.activityInfo != null && unmodifiableList.contains(next.activityInfo.packageName)) {
                str = next.activityInfo.packageName;
                break;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a("MessageNotificationApiImpl", "getSystemSmsPackageName resolveInfos packageName: ", str);
            return str;
        }
        Iterator it2 = unmodifiableList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            String str2 = (String) it2.next();
            if (jrg.bJd_(str2) != null) {
                str = str2;
                break;
            }
        }
        LogUtil.a("MessageNotificationApiImpl", "getSystemSmsPackageName matchThirdPart packageName", str);
        return str;
    }

    private void d(Context context, List<jje> list, List<jje> list2) {
        jje jjeVar = new jje();
        if (!nwx.d().c(context, jjeVar, new NotificationPushInteractor(BaseApplication.getContext()))) {
            LogUtil.h("MessageNotificationApiImpl", "obtainIntelligentPushApp isSupportIntelligent false");
            return;
        }
        int d = new NotificationPushInteractor(BaseApplication.getContext()).d(bfg.e);
        if (d == 1) {
            list.add(nwx.d().e(context, jjeVar, new NotificationPushInteractor(BaseApplication.getContext())));
        } else if (b(bfg.e, d, jjeVar) == 1) {
            list.add(nwx.d().e(context, jjeVar, new NotificationPushInteractor(BaseApplication.getContext())));
        } else {
            LogUtil.a("MessageNotificationApiImpl", "obtainIntelligentPushApp intelligent add mainAppList");
            list2.add(nwx.d().e(context, jjeVar, new NotificationPushInteractor(BaseApplication.getContext())));
        }
    }

    private int b(String str, int i, jje jjeVar) {
        jjeVar.c(bfg.e);
        if ("true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG_ADD", 10001))) {
            LogUtil.h("MessageNotificationApiImpl", "getIsPushEnableIntelligent already set value");
            return i;
        }
        boolean d = nwy.d(jjeVar);
        if (i == 1 || !d) {
            return i;
        }
        jjeVar.b(1);
        new NotificationPushInteractor(BaseApplication.getContext()).a(str, 1);
        LogUtil.a("03", 1, "MessageNotificationApiImpl", "set authorizeFlag auto!");
        return 1;
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public bgf setApplicationNotificationStatus(Context context, String str, String str2, int i) {
        NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(context);
        int d = notificationPushInteractor.d(str);
        LogUtil.a("MessageNotificationApiImpl", "setApplicationNotificationStatus: ", str, Integer.valueOf(i), Integer.valueOf(d));
        if (i != d) {
            nwx.d().a(context, str);
        }
        notificationPushInteractor.a(str, i);
        boolean z = 1 == i;
        nwx.d().d(str, z, str2, new NotificationPushInteractor(context));
        if (z) {
            nwx.d().e(context, str);
        }
        bgf bgfVar = new bgf();
        bgfVar.c(str);
        bgfVar.e(String.valueOf(i));
        return bgfVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public bgh queryNotificationRemind(String str) {
        Object[] objArr = nwy.c() && nwy.i();
        Object[] objArr2 = nwy.j() && cvz.a() != 1;
        bgh bghVar = new bgh();
        if (objArr2 != true && objArr != true) {
            bghVar.b(0);
            return bghVar;
        }
        if (objArr != false) {
            bghVar.b(2);
            boolean[] b = b();
            bghVar.a(b[0] ? 1 : 0);
            if (b[1]) {
                bghVar.d(true);
            }
            return bghVar;
        }
        bghVar.b(1);
        bghVar.a(c(false));
        return bghVar;
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public boolean isMessagePermission(Context context) {
        if (jrg.b()) {
            return true;
        }
        ContentResolver bJc_ = jrg.bJc_(context, "MessageNotificationApiImpl");
        if (bJc_ != null) {
            return jrg.bJe_(bJc_, true, "MessageNotificationApiImpl");
        }
        return false;
    }

    private boolean[] b() {
        final boolean[] zArr = new boolean[2];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jqi.a().getSwitchSetting("switch_smart_notify_reminder", new IBaseResponseCallback() { // from class: nzx.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MessageNotificationApiImpl", "initSmartNotifyReminderView errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    zArr[1] = true;
                    int c = nzx.this.c(true);
                    if (c == -1 && nwy.f()) {
                        LogUtil.a("MessageNotificationApiImpl", "getSmartNotifyStatus isSupportNotifyReminderSwitchClose");
                        c = 0;
                    }
                    boolean[] zArr2 = zArr;
                    boolean z = c == 1;
                    zArr2[0] = z;
                    LogUtil.a("MessageNotificationApiImpl", "getSmartNotifyStatus:", Boolean.valueOf(z));
                } else if (obj instanceof String) {
                    LogUtil.a("MessageNotificationApiImpl", "getSmartNotifyStatus objectData: ", obj);
                    zArr[0] = !"0".equals(obj);
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("MessageNotificationApiImpl", "getNotifyStatus exception");
        }
        return zArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(final boolean z) {
        final int[] iArr = new int[1];
        final int[] iArr2 = {-1};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jqi.a().getSwitchSetting("switch_silent_notify_using_phone", new IBaseResponseCallback() { // from class: nzx.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str;
                LogUtil.a("MessageNotificationApiImpl", "getNotifyStatus errorCode:", Integer.valueOf(i));
                if (i == 0) {
                    if (obj instanceof String) {
                        str = (String) obj;
                        LogUtil.a("MessageNotificationApiImpl", "getNotifyStatus buttonStatus:", str);
                    } else {
                        str = "1";
                    }
                    if ("0".equals(str)) {
                        iArr[0] = 0;
                    } else {
                        iArr[0] = 1;
                    }
                    iArr2[0] = iArr[0];
                } else if (z) {
                    iArr2[0] = nzx.this.d(true);
                } else {
                    iArr[0] = nzx.this.d(false);
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("MessageNotificationApiImpl", "getNotifyStatus exception");
        }
        return z ? iArr2[0] : iArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(boolean z) {
        final int[] iArr = new int[1];
        final int[] iArr2 = {-1};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jqi.a().getSwitchSetting("lock_screen_reminder_switch", new IBaseResponseCallback() { // from class: nzx.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.h("MessageNotificationApiImpl", "enter  getReminderOldSwitch");
                boolean z2 = (i == 0 || !nwy.f()) ? 0 : 1;
                if (i == 0 && (obj instanceof String)) {
                    boolean equals = "0".equals(obj);
                    z2 = !equals;
                    iArr2[0] = equals ? 1 : 0;
                    LogUtil.a("MessageNotificationApiImpl", "getReminderOldSwitch isChecked:", Boolean.valueOf(z2));
                }
                iArr[0] = !z2;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("MessageNotificationApiImpl", "getNotifyStatus exception");
        }
        return z ? iArr2[0] : iArr[0];
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public boolean isAppNotificationEnabled(Context context, bgf bgfVar) {
        return a(context, bgfVar);
    }

    private boolean a(Context context, bgf bgfVar) {
        LogUtil.a("MessageNotificationApiImpl", "getUidByPackageName system is:", Integer.valueOf(EmuiBuild.f13113a));
        if (context == null || bgfVar == null) {
            LogUtil.h("MessageNotificationApiImpl", "remindOpenSystemAuthority error");
            return false;
        }
        if (!CommonUtil.ar()) {
            return true;
        }
        int parseInt = Integer.parseInt(bgfVar.d());
        if (TextUtils.isEmpty(bgfVar.d()) || nwy.d(bgfVar.a(), parseInt)) {
            return true;
        }
        LogUtil.a("MessageNotificationApiImpl", "remindOpenSystemAuthority is not open");
        return false;
    }

    @Override // com.huawei.devicepair.api.MessageNotificationApi
    public void enabledAppNotification(Context context, bgf bgfVar) {
        if (bgfVar == null) {
            LogUtil.h("MessageNotificationApiImpl", "enabledAppNotification, app is null");
            return;
        }
        IHwNotificationManager notificationManager = HwNotificationManagerEx.getNotificationManager();
        try {
            String a2 = bgfVar.a();
            String d = bgfVar.d();
            if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(d)) {
                return;
            }
            notificationManager.setNotificationsEnabledForPackage(a2, Integer.parseInt(d), true);
        } catch (RemoteException | SecurityException e) {
            LogUtil.b("MessageNotificationApiImpl", "openSystemAuthority exception: ", LogAnonymous.b(e));
        }
    }
}
