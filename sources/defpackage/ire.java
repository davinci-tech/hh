package defpackage;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ire;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class ire {

    /* renamed from: a, reason: collision with root package name */
    private static ICommonReport f13553a;
    private static volatile boolean c;
    private static CountDownLatch d;
    private static List<Integer> f;
    private static HealthOpenSDK h;
    private static final Set<DataObservableNoCallback> e = new CopyOnWriteArraySet();
    private static iqo b = new iqo();

    private ire() {
    }

    public static boolean d(DataObservableNoCallback dataObservableNoCallback) {
        n();
        if (!c) {
            return false;
        }
        e.add(dataObservableNoCallback);
        b.a(new ResultCallback() { // from class: irk
            @Override // com.huawei.health.suggestion.ResultCallback
            public final void onResult(int i, Object obj) {
                ire.b(i, obj);
            }
        });
        return true;
    }

    static /* synthetic */ void b(int i, Object obj) {
        if ((obj instanceof Intent) && "com.huawei.hihealth.action.get.step".equals(((Intent) obj).getAction())) {
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k() {
        HealthOpenSDK healthOpenSDK = h;
        if (healthOpenSDK == null) {
            ReleaseLogUtil.d("HiH_OpenSdkManger", "opensdk is null, cancel scheduled job");
        } else {
            healthOpenSDK.e(new IExecuteResult() { // from class: ire.1
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    LogUtil.a("OpenSdkManger", "step counter report success ", Integer.valueOf(((Bundle) obj).getInt("standSteps", 0)));
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "openSDK getStandSteps failed");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "openSDK getStandSteps onServiceException");
                }
            });
        }
    }

    public static boolean a(DataObservableNoCallback dataObservableNoCallback) {
        Set<DataObservableNoCallback> set = e;
        if (!set.remove(dataObservableNoCallback)) {
            return false;
        }
        if (!set.isEmpty() || h == null) {
            return true;
        }
        m();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n() {
        synchronized (ire.class) {
            LogUtil.a("OpenSdkManger", "start init sdk, init result before ", Boolean.valueOf(c));
            if (!c) {
                try {
                    h = new HealthOpenSDK();
                    d = new CountDownLatch(1);
                    h.initSDK(BaseApplication.e(), new d(), "Health Kit");
                    ReleaseLogUtil.e("HiH_OpenSdkManger", "registerOpenSdk isOnTime: ", Boolean.valueOf(d.await(2000L, TimeUnit.MILLISECONDS)));
                } catch (InterruptedException e2) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "initOpenSDK interrupt", LogAnonymous.b((Throwable) e2));
                } catch (Exception e3) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "initOpenSDK error", LogAnonymous.b((Throwable) e3));
                }
            }
        }
    }

    public static void g() {
        if (h != null) {
            ReleaseLogUtil.e("HiH_OpenSdkManger", "destroy openSdk");
            h.d(f13553a);
            h.destorySDK();
            h = null;
            f13553a = null;
            c = false;
            d = null;
        }
    }

    public static void i() {
        LogUtil.a("OpenSdkManger", "report now once");
        HealthOpenSDK healthOpenSDK = h;
        if (healthOpenSDK == null) {
            ReleaseLogUtil.d("HiH_OpenSdkManger", "opensdk is null");
        } else {
            healthOpenSDK.b(new IExecuteResult() { // from class: ire.5
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    if (obj instanceof Bundle) {
                        Iterator it = ire.e.iterator();
                        while (it.hasNext()) {
                            ((DataObservableNoCallback) it.next()).notifyDataChanged((Bundle) obj);
                        }
                        return;
                    }
                    ReleaseLogUtil.d("HiH_OpenSdkManger", "get sport data success, but object is not instance of bundle", obj);
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "failed to get today sport data", obj);
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                    ReleaseLogUtil.c("HiH_OpenSdkManger", "service error when get today sport data", obj);
                }
            });
        }
    }

    public static void o() {
        m();
        b.b(new ResultCallback() { // from class: irl
            @Override // com.huawei.health.suggestion.ResultCallback
            public final void onResult(int i, Object obj) {
                ire.c(i, obj);
            }
        });
    }

    static /* synthetic */ void c(int i, Object obj) {
        if ((obj instanceof Intent) && "com.huawei.hihealth.action.subscribe.data".equals(((Intent) obj).getAction())) {
            l();
            b.a();
        }
    }

    public static void m() {
        b.e();
        g();
    }

    /* renamed from: ire$4, reason: invalid class name */
    class AnonymousClass4 implements HiSubscribeListener {
        AnonymousClass4() {
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            Object[] objArr = new Object[2];
            objArr[0] = "register ";
            objArr[1] = list.isEmpty() ? " success " : " fail ";
            ReleaseLogUtil.e("HiH_OpenSdkManger", objArr);
            List unused = ire.f = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ire.n();
            ire.b.a(new ResultCallback() { // from class: iri
                @Override // com.huawei.health.suggestion.ResultCallback
                public final void onResult(int i2, Object obj) {
                    ire.AnonymousClass4.a(i2, obj);
                }
            });
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(ire.f, new HiUnSubscribeListener() { // from class: irm
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    ire.AnonymousClass4.a(z);
                }
            });
        }

        static /* synthetic */ void a(int i, Object obj) {
            if ((obj instanceof Intent) && "com.huawei.hihealth.action.get.step".equals(((Intent) obj).getAction())) {
                ire.k();
            }
        }

        static /* synthetic */ void a(boolean z) {
            Object[] objArr = new Object[2];
            objArr[0] = "unregister ";
            objArr[1] = z ? " success " : " fail ";
            ReleaseLogUtil.e("HiH_OpenSdkManger", objArr);
        }
    }

    private static void l() {
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(40002, new AnonymousClass4());
    }

    static class d implements IExecuteResult {
        private d() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            ReleaseLogUtil.e("HiH_OpenSdkManger", "healthOpenSDKCallback initSDK success");
            ICommonReport unused = ire.f13553a = new c();
            if (ire.h != null) {
                ire.h.b(ire.f13553a, new e());
            } else {
                ReleaseLogUtil.d("HiH_OpenSdkManger", "opensdk is null");
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            ire.d.countDown();
            ReleaseLogUtil.d("HiH_OpenSdkManger", "healthOpenSDKCallback : initSDK Failed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            ire.d.countDown();
            ReleaseLogUtil.d("HiH_OpenSdkManger", "healthOpenSDKCallback : onServiceException");
        }
    }

    static class c implements ICommonReport {
        private c() {
        }

        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
            ReleaseLogUtil.e("HiH_OpenSdkManger", "total step value ", Integer.valueOf(bundle.getInt("step")));
            if (bundle == null || !ire.b.d()) {
                return;
            }
            Iterator it = ire.e.iterator();
            while (it.hasNext()) {
                ((DataObservableNoCallback) it.next()).notifyDataChanged(bundle);
            }
        }
    }

    static class e implements IExecuteResult {
        e() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            boolean unused = ire.c = true;
            ReleaseLogUtil.e("HiH_OpenSdkManger", "registerStepReport success");
            ire.d.countDown();
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            ire.d.countDown();
            ReleaseLogUtil.d("HiH_OpenSdkManger", "registerStepReport fail");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            ire.d.countDown();
            ReleaseLogUtil.d("HiH_OpenSdkManger", "registerStepReport exception");
        }
    }
}
