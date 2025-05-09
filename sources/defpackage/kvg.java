package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwonesdk.process.HiHealthProcess;
import defpackage.kvg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class kvg implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<HiDataAggregateProOption> preProcess(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiHealthProcess_aggregateHealthData", "preProcess request is empty");
            return null;
        }
        return Arrays.asList(b((kui) moj.e(str, kui.class), new HiAggregateOption()));
    }

    private HiDataAggregateProOption b(kui kuiVar, HiAggregateOption hiAggregateOption) {
        if (kuiVar.k() == 0) {
            hiAggregateOption.setTimeInterval(0L, System.currentTimeMillis());
        } else {
            hiAggregateOption.setTimeInterval(kuiVar.k(), kuiVar.g());
        }
        hiAggregateOption.setAggregateType(kuiVar.d());
        hiAggregateOption.setGroupUnitSize(kuiVar.f());
        hiAggregateOption.setType(kuf.a().d(kuiVar.m()));
        hiAggregateOption.setConstantsKey((String[]) kuiVar.e().toArray(new String[0]));
        hiAggregateOption.setGroupUnitType(kuiVar.i());
        hiAggregateOption.setSortOrder(kuiVar.o());
        hiAggregateOption.setAnchor(kuiVar.b());
        hiAggregateOption.setCount(kuiVar.a());
        hiAggregateOption.setReadType(kuiVar.h());
        hiAggregateOption.setDeviceUuid(kuiVar.j());
        if (hiAggregateOption.getType().length != hiAggregateOption.getConstantsKey().length) {
            LogUtil.c("HiHealthProcess_aggregateHealthData", "length is not match,", hiAggregateOption);
            return null;
        }
        return HiDataAggregateProOption.builder().c(hiAggregateOption).c();
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        if (!(obj instanceof SparseArray) || !(obj2 instanceof kui)) {
            LogUtil.c("HiHealthProcess_aggregateHealthData", "postProcess null");
            return "";
        }
        kui kuiVar = (kui) obj2;
        ArrayList<kuy> arrayList = new ArrayList<>();
        Map<Integer, JSONObject> c = kuiVar.c();
        List<HiHealthData> list = (List) ((SparseArray) obj).get(0);
        for (Map.Entry<Integer, JSONObject> entry : c.entrySet()) {
            c(list, entry.getKey().intValue(), entry.getValue(), arrayList);
        }
        kuh.b(arrayList, kuiVar.o());
        LogUtil.d("HiHealthProcess_aggregateHealthData", "handleAggregateHealthData is ", moj.e(arrayList));
        return moj.e(arrayList);
    }

    private void c(List<HiHealthData> list, int i, JSONObject jSONObject, ArrayList<kuy> arrayList) {
        if (CollectionUtils.d(list)) {
            LogUtil.c("HiHealthProcess_aggregateHealthData", "hiHealthDatas is empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && hiHealthData.getType() == i) {
                try {
                    kuy kuyVar = new kuy(hiHealthData);
                    for (String str : moj.b(jSONObject.get(kuh.f14600a).toString(), String[].class)) {
                        kuyVar.d(str, hiHealthData.get(str));
                    }
                    kuyVar.b(0);
                    kuyVar.a(jSONObject.getInt(kuh.d));
                    arrayList.add(kuyVar);
                } catch (JSONException unused) {
                    LogUtil.e("HiHealthProcess_aggregateHealthData", "JSONException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kui a(String str) {
        return (kui) moj.e(str, kui.class);
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess_aggregateHealthData", "doAction callback is null");
            return;
        }
        List<HiDataAggregateProOption> preProcess = preProcess(str);
        if (CollectionUtils.d(preProcess)) {
            LogUtil.d("HiHealthProcess_aggregateHealthData", "HiDataAggregateProOption list is empty");
            iBaseResponseCallback.d(-1, "");
        } else {
            HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataProEx(preProcess, new e(this, str, iBaseResponseCallback));
        }
    }

    static class e implements HiAggregateListenerEx {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<kvg> f14625a;
        private final IBaseResponseCallback b;
        private final String d;

        e(kvg kvgVar, String str, IBaseResponseCallback iBaseResponseCallback) {
            this.f14625a = new WeakReference<>(kvgVar);
            this.d = str;
            this.b = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(final SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            LogUtil.d("HiHealthProcess_aggregateHealthData", "aggregateHiHealthDataProEx success, data is ", sparseArray);
            if (this.b == null) {
                LogUtil.c("HiHealthProcess_aggregateHealthData", "callback is null");
                return;
            }
            final kvg kvgVar = this.f14625a.get();
            if (kvgVar == null) {
                LogUtil.c("HiHealthProcess_aggregateHealthData", "process is null");
                this.b.d(-1, "");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: kve
                    @Override // java.lang.Runnable
                    public final void run() {
                        kvg.e.this.bRo_(kvgVar, sparseArray);
                    }
                });
            }
        }

        /* synthetic */ void bRo_(kvg kvgVar, SparseArray sparseArray) {
            this.b.d(0, kvgVar.postProcess(sparseArray, kvgVar.a(this.d)));
        }
    }
}
