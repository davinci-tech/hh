package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.IBaseCallback;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.auth.HiAuthorization;
import com.huawei.hihealthservice.auth.HiScope;
import com.huawei.hihealthservice.auth.HiUserAuth;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearkit.IDetectCommonCallback;
import com.huawei.wearkit.IRealTimeCallback;
import com.huawei.wearkit.IWearCommonCallback;
import com.huawei.wearkit.IWearReadCallback;
import com.huawei.wearkit.IWearWriteCallback;
import health.compact.a.AuthorizationUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class ioy {
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    private static boolean d;
    private static boolean e;

    private ioy() {
    }

    public static List<igs> e(HiAuthorization hiAuthorization) {
        if (hiAuthorization == null) {
            return null;
        }
        List<HiScope> scopes = hiAuthorization.getScopes();
        if (scopes == null || scopes.isEmpty()) {
            LogUtil.a("Debug_HiAuthUtil", "hiscopes is null or empty hisopes = ", scopes);
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (HiScope hiScope : scopes) {
            String[] permissions = hiScope.getPermissions();
            if (permissions == null || permissions.length <= 0) {
                LogUtil.a("Debug_HiAuthUtil", "permissions is null or empty permissions = ", permissions);
                return null;
            }
            for (String str : permissions) {
                if (HiScopeUtil.c(str)) {
                    igs igsVar = new igs();
                    igsVar.b(hiScope.getId());
                    igsVar.c(hiScope.getUri());
                    igsVar.e(hiScope.getName());
                    igsVar.a(str);
                    arrayList.add(igsVar);
                }
            }
        }
        return arrayList;
    }

    public static int c(String str, HiUserAuth hiUserAuth) {
        if (str != null && hiUserAuth != null) {
            for (String str2 : hiUserAuth.getScope().split(" ")) {
                if (str.equalsIgnoreCase(str2)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static boolean a(Context context, int i) {
        HiAppInfo b2 = iip.b().b(ivw.a(context));
        if (b2 != null) {
            return ivv.b(b2.getSignature()) == i;
        }
        ReleaseLogUtil.c("HiH_HiAuthUtil", "isUidValid, AppInfo is null");
        return false;
    }

    public static <T> boolean b(String str, T t) throws RemoteException {
        synchronized (ioy.class) {
            if (iqt.b()) {
                ReleaseLogUtil.e("HiH_HiAuthUtil", "isPrivacyPermitted, FA return true");
                return true;
            }
            e = false;
            d = false;
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            b.execute(new Runnable() { // from class: ioy.2
                @Override // java.lang.Runnable
                public void run() {
                    boolean unused = ioy.e = AuthorizationUtils.a(BaseApplication.getContext());
                    boolean unused2 = ioy.d = iqt.b(BaseApplication.getContext());
                    countDownLatch.countDown();
                }
            });
            try {
                ReleaseLogUtil.e("HiH_HiAuthUtil", str, " isPrivacyPermitted is on time: ", Boolean.valueOf(countDownLatch.await(1L, TimeUnit.SECONDS)));
            } catch (InterruptedException e2) {
                ReleaseLogUtil.c("HiH_HiAuthUtil", str, " InterruptedException: ", e2.getMessage());
            }
            if (!e) {
                d(t);
            }
            ReleaseLogUtil.e("HiH_HiAuthUtil", str, " app privacy term status = ", Boolean.valueOf(e));
            LogUtil.a("Debug_HiAuthUtil", "health kit app linked status = ", Boolean.valueOf(d));
            return e;
        }
    }

    private static <T> void d(T t) throws RemoteException {
        String b2 = ipd.b(1003);
        if (t instanceof ICommonListener) {
            ((ICommonListener) t).onSuccess(1003, null);
            return;
        }
        if (t instanceof IRealTimeDataCallback) {
            ((IRealTimeDataCallback) t).onResult(1003);
            return;
        }
        if (t instanceof IRealTimeCallback) {
            ((IRealTimeCallback) t).onResult(1003);
            return;
        }
        if (t instanceof IWearReadCallback) {
            ((IWearReadCallback) t).onResult(1003, b2, null);
            return;
        }
        if (t instanceof IWearWriteCallback) {
            ((IWearWriteCallback) t).onResult(1003, b2);
            return;
        }
        if (t instanceof IWearCommonCallback) {
            ((IWearCommonCallback) t).onResult(1003, b2);
            return;
        }
        if (t instanceof IDataReadResultListener) {
            ((IDataReadResultListener) t).onResult(null, 1003, 2);
            return;
        }
        if (t instanceof IDataOperateListener) {
            ((IDataOperateListener) t).onResult(1003, null);
            return;
        }
        if (t instanceof IWriteCallback) {
            ((IWriteCallback) t).onResult(1003, b2);
            return;
        }
        if (t instanceof IReadCallback) {
            ((IReadCallback) t).onResult(1003, b2, null);
            return;
        }
        if (t instanceof ICommonCallback) {
            ((ICommonCallback) t).onResult(1003, b2);
            return;
        }
        if (t instanceof ISportDataCallback) {
            ((ISportDataCallback) t).onResult(1003);
            return;
        }
        if (t instanceof IBaseCallback) {
            ((IBaseCallback) t).onResult(1003, null);
            return;
        }
        if (t instanceof IRegisterRealTimeCallback) {
            ((IRegisterRealTimeCallback) t).onResult(1003, ipd.b(1003));
            return;
        }
        if (t instanceof IDetectCommonCallback) {
            ((IDetectCommonCallback) t).onResult(1003, null, ipd.b(1003));
        } else if (t instanceof ISubScribeCallback) {
            ((ISubScribeCallback) t).onResult(new ArrayList(), Arrays.asList(new Notification(0, 1003, ipd.b(1003))));
        } else {
            ReleaseLogUtil.c("HiH_HiAuthUtil", "chargeListenerType", " on else branch");
        }
    }

    public static boolean e(String str, Context context) {
        boolean b2;
        synchronized (ioy.class) {
            b2 = iqt.b(str, context);
            LogUtil.a("Debug_HiAuthUtil", "is white app=", Boolean.valueOf(b2));
        }
        return b2;
    }

    public static int a(String str) {
        synchronized (ioy.class) {
            int c = iqz.c(str);
            ReleaseLogUtil.e("HiH_HiAuthUtil", "data sharing enable=", Boolean.valueOf(d), " health kit version=", Integer.valueOf(c));
            if (d) {
                ReleaseLogUtil.e("HiH_HiAuthUtil", "package is whiteApp, Auth success");
                return 0;
            }
            if (c >= 6) {
                ReleaseLogUtil.c("HiH_HiAuthUtil", "HealthKit data sharing not enabled");
                return 1008;
            }
            ReleaseLogUtil.c("HiH_HiAuthUtil", "permission exception");
            return 1001;
        }
    }
}
