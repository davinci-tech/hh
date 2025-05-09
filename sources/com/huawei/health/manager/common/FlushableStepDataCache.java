package com.huawei.health.manager.common;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdl;
import health.compact.a.OneMinuteStepData;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepDataCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class FlushableStepDataCache extends StepDataCache {
    private Context c = null;
    private int d = -1;
    private int b = -1;

    /* renamed from: a, reason: collision with root package name */
    private List<InsertEvent> f2785a = new ArrayList(10);
    private UserInfo j = null;
    private String e = "unKnown";

    public interface MyFlushCallback {
        void onFailed();

        void onSuccess();
    }

    public FlushableStepDataCache(Context context) {
        d(context);
    }

    private void d(Context context) {
        synchronized (this) {
            if (context == null) {
                context = BaseApplication.getContext();
            }
            this.c = context;
            this.j = UserInfo.d();
            this.d = SharedPerferenceUtils.ab(this.c);
        }
    }

    public void b(MyFlushCallback myFlushCallback) {
        int i;
        SparseArray<OneMinuteStepData> akL_ = akL_();
        if (akL_ == null || akL_.size() <= 0) {
            LogUtil.a("Step_FlushableStepDataCache", "writeDataToDB(),dataList==null or dataList.size()== 0");
            if (myFlushCallback != null) {
                myFlushCallback.onSuccess();
                return;
            }
            return;
        }
        LogUtil.a("Step_FlushableStepDataCache", "writeDataToDB size ", Integer.valueOf(akL_.size()));
        synchronized (this) {
            i = this.d;
            for (InsertEvent insertEvent : this.f2785a) {
                if (insertEvent.e > i) {
                    i = insertEvent.e;
                }
            }
        }
        int a2 = (int) akL_.valueAt(akL_.size() - 1).a();
        InsertEvent insertEvent2 = new InsertEvent();
        insertEvent2.c = System.currentTimeMillis();
        insertEvent2.f2786a = i;
        insertEvent2.e = a2;
        synchronized (this) {
            this.f2785a.add(insertEvent2);
        }
        HiDataInsertOption akK_ = akK_(akL_, i, a2);
        if (akK_ != null) {
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(akK_, new InsertCallback(insertEvent2, myFlushCallback));
            return;
        }
        myFlushCallback.onSuccess();
        synchronized (this) {
            if (this.f2785a.contains(insertEvent2)) {
                this.f2785a.remove(insertEvent2);
            }
        }
    }

    private HiDataInsertOption akK_(SparseArray<OneMinuteStepData> sparseArray, int i, int i2) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.a("Step_FlushableStepDataCache", "upLoadOneMinuteDataToEngine(),oneMinuteData==null or dataArray.size() == 0");
            return null;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList(10);
        String str = this.e;
        synchronized (this) {
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                OneMinuteStepData valueAt = sparseArray.valueAt(i3);
                if (valueAt.a() >= i && valueAt.a() <= i2 && valueAt.h()) {
                    if (valueAt.a() >= this.b) {
                        LogUtil.c("Step_FlushableStepDataCache", "upLoadOneMinuteDataToEngine getTimestampInMinute() >= mTimeLogBegin");
                    }
                    valueAt.c(str, arrayList);
                    valueAt.a(str, this.j, arrayList);
                    valueAt.d(str, this.j, arrayList);
                    valueAt.a(str, arrayList);
                    valueAt.b(str, arrayList);
                }
            }
        }
        hiDataInsertOption.setDatas(arrayList);
        if (arrayList.size() > 0) {
            return hiDataInsertOption;
        }
        return null;
    }

    public void e(String str) {
        this.e = str;
    }

    public void a() {
        synchronized (this) {
            SparseArray<OneMinuteStepData> akL_ = akL_();
            if (akL_.size() > 80) {
                synchronized (this) {
                    int t = (int) (jdl.t(System.currentTimeMillis()) / 60000);
                    int i = this.d;
                    if (i < t) {
                        t = i;
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < akL_.size(); i3++) {
                        if (akL_.valueAt(i3).a() < t - 120) {
                            i2++;
                        }
                    }
                    akL_.removeAtRange(0, i2);
                }
            }
        }
    }

    class InsertCallback implements HiDataOperateListener {
        private MyFlushCallback b;
        private InsertEvent d;

        InsertCallback(InsertEvent insertEvent, MyFlushCallback myFlushCallback) {
            this.d = insertEvent;
            this.b = myFlushCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("Step_FlushableStepDataCache", "InsertCallback() onSuccess type = ", Integer.valueOf(i));
            if (obj != null) {
                Bundle bundle = new Bundle();
                if (i == 0) {
                    e();
                    return;
                }
                LogUtil.a("Step_FlushableStepDataCache", "insert failed isError");
                bundle.putBoolean("result", false);
                MyFlushCallback myFlushCallback = this.b;
                if (myFlushCallback != null) {
                    myFlushCallback.onFailed();
                    return;
                }
                return;
            }
            LogUtil.a("Step_FlushableStepDataCache", "InsertCallback data = null", "cb=", this.b, " mFlushCallback no to callback");
        }

        private void e() {
            synchronized (FlushableStepDataCache.this) {
                this.d.d = true;
                Iterator it = FlushableStepDataCache.this.f2785a.iterator();
                while (it.hasNext()) {
                    InsertEvent insertEvent = (InsertEvent) it.next();
                    if (!insertEvent.d) {
                        break;
                    }
                    FlushableStepDataCache.this.d = insertEvent.e;
                    LogUtil.a("Step_FlushableStepDataCache", "InsertEvent mIsSuccess begin:", Integer.valueOf(this.d.f2786a), " end:", Integer.valueOf(this.d.e));
                    SharedPerferenceUtils.a(FlushableStepDataCache.this.c, insertEvent.e);
                    it.remove();
                }
                FlushableStepDataCache.this.d();
            }
            MyFlushCallback myFlushCallback = this.b;
            if (myFlushCallback != null) {
                myFlushCallback.onSuccess();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Iterator<InsertEvent> it = this.f2785a.iterator();
        if (it.hasNext()) {
            if (System.currentTimeMillis() - it.next().c > 3000) {
                this.f2785a.clear();
            }
        }
    }

    @Override // health.compact.a.StepDataCache
    public void a(int i) {
        synchronized (this) {
            if (i < this.d) {
                this.d = i;
            }
            Iterator<InsertEvent> it = this.f2785a.iterator();
            while (it.hasNext()) {
                InsertEvent next = it.next();
                if (i < next.e) {
                    LogUtil.a("Step_FlushableStepDataCache", "insert conflict event timeBegin ", Integer.valueOf(next.f2786a), " timeEnd ", Integer.valueOf(next.e));
                    it.remove();
                }
            }
        }
    }

    static class InsertEvent {

        /* renamed from: a, reason: collision with root package name */
        int f2786a;
        long c;
        boolean d;
        int e;

        private InsertEvent() {
            this.d = false;
            this.c = 0L;
            this.f2786a = 0;
            this.e = 0;
        }
    }
}
