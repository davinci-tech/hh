package com.huawei.hwdevice.outofprocess.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWindowAllocationException;
import android.database.SQLException;
import android.database.StaleDataException;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.jah;
import defpackage.jdz;
import defpackage.jqi;
import defpackage.khs;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class NotificationContentProviderUtil {
    private static boolean b;
    private static boolean d;
    private static String h;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6322a = new Object();
    private static final Set<NotificationLiveChangerListener> c = new LinkedHashSet(16);
    private static List<String> i = new ArrayList();
    private static Map<String, String> f = new HashMap();
    private static Map<String, List<String>> g = new HashMap();

    public interface NotificationLiveChangerListener {
        void onChanger(boolean z);
    }

    public static boolean d(String str) {
        Cursor query;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("NotifiContProvUt", "isAppSubSwitchPushEnable packageName is empty");
            return false;
        }
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.j), null, null, null, null);
            try {
                LogUtil.a("NotifiContProvUt", jdz.a("NotificationList", null, null, null, null));
            } finally {
            }
        } catch (Exception e2) {
            LogUtil.a("NotifiContProvUt", "isAppSubSwitchPushEnable Exception: ", LogAnonymous.b((Throwable) e2));
        }
        if (query != null && query.getCount() != 0) {
            while (query.moveToNext()) {
                Integer bGi_ = jdz.bGi_("name", query, "NotifiContProvUt");
                if (bGi_ == null) {
                    LogUtil.h("NotifiContProvUt", "isAppSubSwitchPushEnable columnIndex is null");
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                if (TextUtils.equals(str, query.getString(bGi_.intValue()))) {
                    if (query != null) {
                        query.close();
                    }
                    return true;
                }
            }
            if (query != null) {
                query.close();
            }
            return false;
        }
        LogUtil.h("NotifiContProvUt", "isAppSubSwitchPushEnable cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    public static void b(String str, int i2) {
        Uri parse = Uri.parse(jdz.j);
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        boolean d2 = d(str);
        LogUtil.a("NotifiContProvUt", "saveToDatabase packageName: ", str, " closeStatus: ", Integer.valueOf(i2), " hasKey: ", Boolean.valueOf(d2));
        try {
            if (d2 && i2 == 0) {
                contentResolver.delete(parse, "name=?", new String[]{str});
            } else if (!d2 && i2 == 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentResolver.insert(parse, contentValues);
            } else {
                LogUtil.h("NotifiContProvUt", "saveToDatabase else");
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("NotifiContProvUt", "saveToDatabase exception.");
        }
    }

    public static List<String> i() {
        Cursor query;
        ArrayList arrayList = new ArrayList(16);
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.j), null, null, null, null);
            try {
                LogUtil.a("NotifiContProvUt", jdz.a("NotificationList", null, null, null, null));
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (CursorWindowAllocationException unused) {
            LogUtil.b("NotifiContProvUt", "getPushEnableApps SQLECursorWindowAllocationException");
        } catch (SQLException unused2) {
            LogUtil.b("NotifiContProvUt", "getPushEnableApps SQLException");
        } catch (Exception unused3) {
            LogUtil.b("NotifiContProvUt", "getPushEnableApps Exception");
        }
        if (query != null && query.getCount() != 0) {
            while (query.moveToNext()) {
                Integer bGi_ = jdz.bGi_("name", query, "NotifiContProvUt");
                if (bGi_ == null) {
                    LogUtil.h("NotifiContProvUt", "getPushEnableApps columnIndex is null");
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                String string = query.getString(bGi_.intValue());
                if (string.contains(".")) {
                    arrayList.add(string);
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        }
        LogUtil.h("NotifiContProvUt", "getPushEnableApps cursor is null");
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public static void a(boolean z) {
        LogUtil.a("NotifiContProvUt", "setForbiddenValue intoProvider,USE_SYNERGY? isForbidden: ", Boolean.valueOf(z));
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri parse = Uri.parse(jdz.g);
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("value", (Integer) 1);
        } else {
            contentValues.put("value", (Integer) 0);
        }
        try {
            contentResolver.update(parse, contentValues, "name=?", new String[]{"is_Forbidden"});
        } catch (SQLException | IllegalArgumentException e2) {
            LogUtil.b("NotifiContProvUt", "setForbiddenValue Exception:", LogAnonymous.b(e2));
        }
    }

    public static void a(int i2) {
        LogUtil.a("NotifiContProvUt", "updateIsAuthorizedToNotificationFlagsDb status: ", Integer.valueOf(i2));
        d(i2, false);
    }

    public static void d(int i2, boolean z) {
        LogUtil.a("NotifiContProvUt", "updateIsAuthorizedToDb status: ", Integer.valueOf(i2), ",isHarmonyOS30SendNotify:", Boolean.valueOf(z));
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri parse = Uri.parse(jdz.g);
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Integer.valueOf(i2));
        contentValues.put("isNotify", Boolean.valueOf(z));
        try {
            contentResolver.update(parse, contentValues, "name=?", new String[]{"authorized"});
        } catch (SQLException | IllegalArgumentException e2) {
            LogUtil.b("NotifiContProvUt", "updateIsSupportNotificationToNotificationFlagsDb Exception:", LogAnonymous.b(e2));
        }
    }

    public static boolean e() {
        Cursor query;
        String[] strArr = {"value"};
        String[] strArr2 = {"authorized"};
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.g), strArr, "name=?", strArr2, null);
            try {
                LogUtil.a("NotifiContProvUt", jdz.a("NotificationFlags", strArr, "name=?", strArr2, null));
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (SQLException | StaleDataException | IllegalArgumentException e2) {
            LogUtil.a("NotifiContProvUt", "getNotificationStatus Exception: ", LogAnonymous.b(e2));
        }
        if (query != null && query.getCount() != 0) {
            if (!query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                return false;
            }
            Integer bGi_ = jdz.bGi_("value", query, "NotifiContProvUt");
            if (bGi_ == null) {
                LogUtil.h("NotifiContProvUt", "getNotificationStatus columnIndex is null");
                if (query != null) {
                    query.close();
                }
                return false;
            }
            boolean z = query.getInt(bGi_.intValue()) == 1;
            if (query != null) {
                query.close();
            }
            return z;
        }
        LogUtil.h("NotifiContProvUt", "getNotificationStatus cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    public static boolean g() {
        Cursor query;
        String[] strArr = {"value"};
        String[] strArr2 = {"authorized"};
        boolean z = true;
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.g), strArr, "name=?", strArr2, null);
            try {
                LogUtil.a("NotifiContProvUt", jdz.a("NotificationFlags", strArr, "name=?", strArr2, null));
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (SQLException | StaleDataException | IllegalArgumentException e2) {
            LogUtil.a("NotifiContProvUt", "isInitStatusNotificationAuthorized Exception: ", LogAnonymous.b(e2));
        }
        if (query != null && query.getCount() != 0) {
            if (query.moveToFirst()) {
                Integer bGi_ = jdz.bGi_("value", query, "NotifiContProvUt");
                if (bGi_ == null) {
                    LogUtil.h("NotifiContProvUt", "isInitStatusNotificationAuthorized columnIndex is null,result: ", true);
                    if (query != null) {
                        query.close();
                    }
                    return true;
                }
                int i2 = query.getInt(bGi_.intValue());
                if (i2 == 1 || i2 == 0) {
                    z = false;
                }
            }
            if (query != null) {
                query.close();
            }
            LogUtil.a("NotifiContProvUt", "isInitStatusNotificationAuthorized result is: ", Boolean.valueOf(z));
            return z;
        }
        LogUtil.h("NotifiContProvUt", "isInitStatusNotificationAuthorized cursor is null,result: ", true);
        if (query != null) {
            query.close();
        }
        return true;
    }

    public static void e(boolean z) {
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri parse = Uri.parse(jdz.g);
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("value", (Integer) 1);
        } else {
            contentValues.put("value", (Integer) 0);
        }
        try {
            contentResolver.update(parse, contentValues, "name=?", new String[]{"is_MessageAlert"});
            LogUtil.a("NotifiContProvUt", "updateIsSupportNotificationToNotificationFlagsDb isSupportNotification:", Boolean.valueOf(z));
        } catch (SQLException | IllegalArgumentException | SecurityException e2) {
            LogUtil.b("NotifiContProvUt", "updateIsSupportNotificationToNotificationFlagsDb Exception:", LogAnonymous.b(e2));
        }
    }

    public static boolean h() {
        Cursor query;
        String[] strArr = {"value"};
        String[] strArr2 = {"is_MessageAlert"};
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.g), strArr, "name=?", strArr2, null);
            try {
                LogUtil.a("NotifiContProvUt", jdz.a("NotificationFlags", strArr, "name=?", strArr2, null));
            } finally {
            }
        } catch (SQLException e2) {
            LogUtil.a("NotifiContProvUt", "isDeviceCapabilitySupportNotification SQLException: ", ExceptionUtils.d(e2));
        }
        if (query != null && query.getCount() != 0) {
            if (!query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                return false;
            }
            Integer bGi_ = jdz.bGi_("value", query, "NotifiContProvUt");
            if (bGi_ == null) {
                LogUtil.h("NotifiContProvUt", "isDeviceCapabilitySupportNotification columnIndex is null");
                if (query != null) {
                    query.close();
                }
                return false;
            }
            boolean z = query.getInt(bGi_.intValue()) == 1;
            if (query != null) {
                query.close();
            }
            return z;
        }
        LogUtil.h("NotifiContProvUt", "isDeviceCapabilitySupportNotification cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    public static void f() {
        LogUtil.a("NotifiContProvUt", "saveHarmonyNotificationCollaboration enter");
        BaseApplication.getContext().getContentResolver().insert(Uri.parse(jdz.c), new ContentValues());
    }

    public static void e(final IBaseResponseCallback iBaseResponseCallback) {
        if (!khs.j() || !e()) {
            LogUtil.a("NotifiContProvUt", "isJoinHarmonyNotificationCollaboration witch is false");
            iBaseResponseCallback.d(0, false);
        } else {
            jqi.a().getSwitchSetting("switch_smart_notify_reminder", new IBaseResponseCallback() { // from class: jrl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    NotificationContentProviderUtil.e(IBaseResponseCallback.this, i2, obj);
                }
            });
        }
    }

    public static /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, int i2, Object obj) {
        LogUtil.a("NotifiContProvUt", "isJoinHarmonyNotificationCollaboration errorCode:", Integer.valueOf(i2));
        if (i2 != 0 && khs.f()) {
            iBaseResponseCallback.d(0, false);
        } else if (i2 == 0 && (obj instanceof String)) {
            boolean z = !"0".equals(obj);
            LogUtil.a("NotifiContProvUt", "isJoinHarmonyNotificationCollaboration isChecked:", Boolean.valueOf(z));
            iBaseResponseCallback.d(0, Boolean.valueOf(z));
        }
    }

    public static boolean j() {
        if (!d) {
            m();
        }
        return b;
    }

    public static void a(NotificationLiveChangerListener notificationLiveChangerListener) {
        if (notificationLiveChangerListener == null) {
            return;
        }
        synchronized (e) {
            Set<NotificationLiveChangerListener> set = c;
            int size = set.size();
            set.add(notificationLiveChangerListener);
            ReleaseLogUtil.e("R_NotifiContProvUt", "registerNotificationLiveChanger oldSize=", Integer.valueOf(size), " currentSize=", Integer.valueOf(set.size()));
            if (!d) {
                m();
            }
        }
    }

    public static void d(NotificationLiveChangerListener notificationLiveChangerListener) {
        if (notificationLiveChangerListener == null) {
            return;
        }
        synchronized (e) {
            Set<NotificationLiveChangerListener> set = c;
            int size = set.size();
            set.remove(notificationLiveChangerListener);
            ReleaseLogUtil.e("R_NotifiContProvUt", "unRegisterNotificationLiveChanger oldSize=", Integer.valueOf(size), " currentSize=", Integer.valueOf(set.size()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k() {
        synchronized (e) {
            Set<NotificationLiveChangerListener> set = c;
            ReleaseLogUtil.e("R_NotifiContProvUt", "notifyNotificationLiveChanger currentSize=", Integer.valueOf(set.size()));
            Iterator<NotificationLiveChangerListener> it = set.iterator();
            while (it.hasNext()) {
                it.next().onChanger(b);
            }
        }
    }

    private static void m() {
        synchronized (e) {
            if (d) {
                return;
            }
            d = true;
            b = n();
            HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(102, new HiSubscribeListener() { // from class: com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil.4
                @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
                public void onResult(List<Integer> list, List<Integer> list2) {
                    LogUtil.a("NotifiContProvUt", "isNotificationLiveEnabled onResult");
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    LogUtil.a("NotifiContProvUt", "isNotificationLiveEnabled success");
                }

                @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
                public void onChange(int i2, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                    boolean n;
                    LogUtil.a("NotifiContProvUt", "registerNotificationLiveChanger onChange type = ", Integer.valueOf(i2), ",changeKey = ", str);
                    if (i2 == 102) {
                        if (("notification_live_switch".equals(str) || "HiSyncUserData".equals(str)) && NotificationContentProviderUtil.b != (n = NotificationContentProviderUtil.n())) {
                            LogUtil.a("NotifiContProvUt", "liveInfo cloud switch state is different,change to: ", Boolean.valueOf(n));
                            boolean unused = NotificationContentProviderUtil.b = n;
                            NotificationContentProviderUtil.k();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean n() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        final boolean[] zArr = {true};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jqi.a().getSwitchSetting("notification_live_switch", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_NotifiContProvUt", "fetch liveInfo cloud switch state,onResponse errorCode:", Integer.valueOf(i2), ",state in cloud:", obj);
                boolean z = true;
                if (i2 == 0 && (obj instanceof String)) {
                    z = true ^ "false".equals(obj);
                }
                zArr[0] = z;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("R_NotifiContProvUt", "fetch liveInfo cloud switch state InterruptedException");
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        ReleaseLogUtil.e("R_NotifiContProvUt", "fetch liveInfo cloud switch state cost time: ", Long.valueOf(elapsedRealtime2));
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("getNotificationLiveState", "time:" + elapsedRealtime2);
        return zArr[0];
    }

    public static List<String> b() {
        String e2 = jah.c().e("notification_live_support_apps");
        LogUtil.a("NotifiContProvUt", "getNotificationLiveSupportApps liveInfoSupportApps:", e2);
        if (TextUtils.isEmpty(e2)) {
            i = new ArrayList();
        } else if (!e2.equals(h)) {
            h = e2;
            String[] split = e2.trim().split(";");
            for (int i2 = 0; i2 < split.length; i2++) {
                split[i2] = split[i2].trim();
            }
            i = Arrays.asList(split);
        }
        return d(i);
    }

    public static List<String> c(String str) {
        List<String> list;
        String e2 = jah.c().e("notification_live_support_apps_".concat(str));
        LogUtil.a("NotifiContProvUt", "getNotificationLiveSupportApps level:" + str + " liveInfoSupportApps:", e2);
        synchronized (f6322a) {
            if (TextUtils.isEmpty(e2)) {
                list = new ArrayList<>();
            } else if (!e2.equals(f.get(str))) {
                f.put(str, e2);
                String[] split = e2.trim().split(";");
                for (int i2 = 0; i2 < split.length; i2++) {
                    split[i2] = split[i2].trim();
                }
                List<String> asList = Arrays.asList(split);
                g.put(str, asList);
                list = asList;
            } else {
                list = g.get(str);
            }
        }
        return d(list);
    }

    private static List<String> d(List<String> list) {
        if (list != null) {
            return list;
        }
        ReleaseLogUtil.d("R_NotifiContProvUt", "checkNotNull list is null");
        return new ArrayList();
    }
}
