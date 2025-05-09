package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiMultiDataClientListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwonesdk.process.HiHealthProcess;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kvh implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<Integer> preProcess(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("HiHealthProcess_ReadDataSourceProcess", "preProcess input is null");
            return arrayList;
        }
        kuj kujVar = (kuj) moj.e(str, kuj.class);
        if (kujVar == null || kujVar.d() == null) {
            LogUtil.b("HiHealthProcess_ReadDataSourceProcess", "dataSourceRequest is ", kujVar);
            return arrayList;
        }
        List<kun> e2 = kujVar.d().e();
        if (CollectionUtils.d(e2)) {
            LogUtil.b("HiHealthProcess_ReadDataSourceProcess", "dataTypes is empty");
            return arrayList;
        }
        Iterator<kun> it = e2.iterator();
        while (it.hasNext()) {
            arrayList.addAll(kuf.a().e(new int[]{it.next().a()}));
        }
        return arrayList;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        if (!(obj instanceof List)) {
            LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "postProcess null");
            return "";
        }
        List<HiHealthClient> list = (List) obj;
        if (CollectionUtils.d(list)) {
            LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "hiHealthClients is empty");
            return "";
        }
        if ((obj2 instanceof List) && e((List<Integer>) obj2)) {
            spx.a((List<HiHealthClient>) list);
        }
        ArrayList arrayList = new ArrayList();
        for (HiHealthClient hiHealthClient : list) {
            spx.c(hiHealthClient);
            arrayList.add(new kux(hiHealthClient));
        }
        LogUtil.a("HiHealthProcess_ReadDataSourceProcess", "arkUiDataSourceResponses is ", moj.e(arrayList));
        return moj.e(arrayList);
    }

    private boolean e(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 2002 || intValue == 2018) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess_ReadDataSourceProcess", "doAction callback is null");
            return;
        }
        kuj kujVar = (kuj) moj.e(str, kuj.class);
        if (kujVar != null && kujVar.a() != null) {
            HiHealthManager.d(BaseApplication.e()).fetchDataClientByUniqueId(1, kujVar.a(), new e(this, iBaseResponseCallback));
            return;
        }
        List<Integer> preProcess = preProcess(str);
        if (CollectionUtils.d(preProcess)) {
            ReleaseLogUtil.c("HiHealthProcess_ReadDataSourceProcess", "types is empty in doAction");
            iBaseResponseCallback.d(-1, "");
        } else {
            HiTimeInterval hiTimeInterval = new HiTimeInterval();
            hiTimeInterval.setStartTime(kujVar.d().a());
            hiTimeInterval.setEndTime(kujVar.d().c());
            HiHealthManager.d(BaseApplication.e()).fetchDataSourceByTypes(preProcess, hiTimeInterval, true, new c(this, preProcess, iBaseResponseCallback));
        }
    }

    static class c implements HiMultiDataClientListener {

        /* renamed from: a, reason: collision with root package name */
        private final IBaseResponseCallback f14626a;
        private final List<Integer> b;
        private final WeakReference<kvh> e;

        c(kvh kvhVar, List<Integer> list, IBaseResponseCallback iBaseResponseCallback) {
            this.e = new WeakReference<>(kvhVar);
            this.f14626a = iBaseResponseCallback;
            this.b = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
        public void onMultiTypeResult(Map<Integer, List<HiHealthClient>> map) {
            LogUtil.a("HiHealthProcess_ReadDataSourceProcess", "onMultiTypeResult:", map.toString());
        }

        @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
        public void onMergeTypeResult(List<HiHealthClient> list) {
            LogUtil.a("HiHealthProcess_ReadDataSourceProcess", "onMergeTypeResult:", list);
            if (this.f14626a == null) {
                LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "callback is null");
                return;
            }
            kvh kvhVar = this.e.get();
            if (kvhVar == null || CollectionUtils.d(list)) {
                LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "process is null or no clientList");
                this.f14626a.d(-1, "");
            } else {
                this.f14626a.d(0, kvhVar.postProcess(list, this.b));
            }
        }
    }

    static class e implements HiDataClientListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<kvh> f14627a;
        private final IBaseResponseCallback e;

        e(kvh kvhVar, IBaseResponseCallback iBaseResponseCallback) {
            this.f14627a = new WeakReference<>(kvhVar);
            this.e = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            LogUtil.a("HiHealthProcess_ReadDataSourceProcess", "onResult: ", list);
            if (this.e == null) {
                LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "callback is null");
                return;
            }
            kvh kvhVar = this.f14627a.get();
            if (kvhVar == null || CollectionUtils.d(list)) {
                LogUtil.h("HiHealthProcess_ReadDataSourceProcess", "process is null or no clientList");
                this.e.d(-1, "");
            } else {
                this.e.d(0, kvhVar.postProcess(list, null));
            }
        }
    }
}
