package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class qqk {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f16542a = new Object();
    private static volatile qqk c;
    private volatile List<Integer> b;
    private volatile boolean d = true;
    private volatile List<HiSubscribeListener> e;

    public static qqk a() {
        if (c == null) {
            synchronized (f16542a) {
                if (c == null) {
                    c = new qqk();
                }
            }
        }
        return c;
    }

    public void c() {
        synchronized (f16542a) {
            LogUtil.a("BloodSugarSubscribeCenter", "close");
            this.d = false;
        }
    }

    public void a(HiSubscribeListener hiSubscribeListener) {
        synchronized (f16542a) {
            LogUtil.a("BloodSugarSubscribeCenter", "unSubscribe");
            if (this.e == null) {
                LogUtil.a("BloodSugarSubscribeCenter", "mListenerList == null");
                return;
            }
            this.e.remove(hiSubscribeListener);
            if (this.e.isEmpty() && this.b != null) {
                LogUtil.a("BloodSugarSubscribeCenter", "execute unSubscribeHiHealthData");
                HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.b, new HiUnSubscribeListener() { // from class: qqi
                    @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                    public final void onResult(boolean z) {
                        LogUtil.a("BloodSugarSubscribeCenter", "unSubscribeHiHealthData=", Boolean.valueOf(z));
                    }
                });
                this.e = null;
                this.b = null;
                c = null;
            }
        }
    }

    public void d(HiSubscribeListener hiSubscribeListener) {
        LogUtil.a("BloodSugarSubscribeCenter", "subscribe");
        synchronized (f16542a) {
            LogUtil.a("BloodSugarSubscribeCenter", "execute subscribe");
            if (this.e == null) {
                this.e = new ArrayList(0);
            }
            if (this.b == null) {
                LogUtil.a("BloodSugarSubscribeCenter", "execute subscribe mSuccessList");
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(10);
                arrayList.add(Integer.valueOf(HiSubscribeType.e));
                HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new b(this));
            }
            this.e.add(hiSubscribeListener);
        }
    }

    static class b implements HiSubscribeListener {
        private final WeakReference<qqk> d;

        b(qqk qqkVar) {
            this.d = new WeakReference<>(qqkVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            qqk qqkVar = this.d.get();
            if (qqkVar == null) {
                LogUtil.h("BloodSugarSubscribeCenter", "subscribeCenter is null");
                return;
            }
            LogUtil.a("BloodSugarSubscribeCenter", "subscribe onResult");
            synchronized (qqk.f16542a) {
                if (koq.c(list)) {
                    LogUtil.a("BloodSugarSubscribeCenter", "successList=", list.toString());
                    qqkVar.b = list;
                }
                if (list2 != null) {
                    LogUtil.a("BloodSugarSubscribeCenter", "failList=", list2.toString());
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            qqk qqkVar = this.d.get();
            if (qqkVar != null) {
                synchronized (qqk.f16542a) {
                    LogUtil.a("BloodSugarSubscribeCenter", "onChange, type=", Integer.valueOf(i), ", changeKey=", str, ", isOpen=", Boolean.valueOf(qqkVar.d));
                    if (qqkVar.d || !ArkUIXConstants.DELETE.equals(str)) {
                        if (qqkVar.d && qqkVar.e != null) {
                            LogUtil.a("BloodSugarSubscribeCenter", "for onChange");
                            Iterator it = qqkVar.e.iterator();
                            while (it.hasNext()) {
                                ((HiSubscribeListener) it.next()).onChange(i, hiHealthClient, str, hiHealthData, j);
                            }
                        }
                        return;
                    }
                    LogUtil.a("BloodSugarSubscribeCenter", "set mIsOpen = true");
                    qqkVar.d = true;
                    return;
                }
            }
            LogUtil.h("BloodSugarSubscribeCenter", "subscribeCenter is null");
        }
    }
}
