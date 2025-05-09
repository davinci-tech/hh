package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwonesdk.process.HiHealthProcess;
import defpackage.kuk;
import defpackage.kum;
import defpackage.kvf;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kvf implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<HiDataAggregateProOption> preProcess(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiHealthProcess_aggregateData", "preProcess request is empty");
            return null;
        }
        kum kumVar = (kum) moj.e(str, kum.class);
        if (kumVar == null) {
            ReleaseLogUtil.c("HiHealthProcess_aggregateData", "aggregateRequest is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (kum.b bVar : kumVar.g()) {
            for (Integer num : bVar.a()) {
                HiAggregateOption hiAggregateOption = new HiAggregateOption();
                int c = kuf.a().c(kumVar, bVar.c(), num.intValue());
                if (c != -1) {
                    hiAggregateOption.setType(new int[]{c});
                    hiAggregateOption.setAggregateType(kuf.a().c(num.intValue()));
                } else {
                    hiAggregateOption.setAggregateType(num.intValue());
                    hiAggregateOption.setType(kuf.a().c(kumVar.e().a(), bVar.c()));
                }
                hiAggregateOption.setConstantsKey(new String[]{bVar.c()});
                if (hiAggregateOption.getType().length != hiAggregateOption.getConstantsKey().length) {
                    LogUtil.c("HiHealthProcess_aggregateData", "length is not match,", hiAggregateOption);
                } else {
                    HiDataAggregateProOption e = e(kumVar, hiAggregateOption);
                    if (e != null) {
                        arrayList.add(e);
                    }
                }
            }
        }
        return arrayList;
    }

    private HiDataAggregateProOption e(kum kumVar, HiAggregateOption hiAggregateOption) {
        try {
            hiAggregateOption.setTimeInterval(DateFormatUtil.b(DateFormatUtil.d(kumVar.h(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY).getTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_8), DateFormatUtil.b(DateFormatUtil.d(kumVar.b(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY).getTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_8), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            hiAggregateOption.setGroupUnitType(kumVar.a().d());
            hiAggregateOption.setSortOrder(kumVar.i());
            hiAggregateOption.setCount(kumVar.c());
            if (kumVar.d() != null && !"matchAll".equals(kumVar.d().a())) {
                hiAggregateOption.setDeviceUuid(kumVar.d().a());
                hiAggregateOption.setReadType(2);
            }
            return HiDataAggregateProOption.builder().c(hiAggregateOption).c();
        } catch (ParseException unused) {
            return null;
        }
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        if (obj == null || !(obj instanceof SparseArray) || !(obj2 instanceof kum)) {
            LogUtil.c("HiHealthProcess_aggregateData", "postProcess null");
            return "";
        }
        kum kumVar = (kum) obj2;
        SparseArray sparseArray = (SparseArray) obj;
        ArrayList<kuu> arrayList = new ArrayList<>();
        for (Map.Entry<Integer, JsonObject> entry : kumVar.f().entrySet()) {
            List<HiHealthData> list = (List) sparseArray.get(entry.getKey().intValue());
            if (!koq.b(list)) {
                a(list, kumVar.e().a(), entry.getValue(), arrayList, kumVar.a().d());
            }
        }
        kuh.b(arrayList, kumVar.i());
        LogUtil.d("HiHealthProcess_aggregateData", "arkUiDataList is ", moj.e(arrayList));
        return moj.e(arrayList);
    }

    private void a(List<HiHealthData> list, int i, JsonObject jsonObject, ArrayList<kuu> arrayList, int i2) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                int type = hiHealthData.getType();
                HiHealthDictType f = HiHealthDictManager.d((Context) null).f(type);
                if (type == jsonObject.get(kuh.i).getAsInt() || ((f != null && f.i() == jsonObject.get(kuh.i).getAsInt()) || a(jsonObject, i2))) {
                    kuu e = kuh.e(arrayList, hiHealthData, i2);
                    kuv kuvVar = new kuv();
                    if (e == null) {
                        e = new kuu(hiHealthData);
                        e.e(new kun(i, kuf.a().d(i)));
                        arrayList.add(e);
                    } else {
                        kuvVar = e.e(jsonObject.get(kuh.f14600a).getAsString());
                    }
                    kuh.e(kuvVar, jsonObject.get(kuh.c).getAsString(), jsonObject.get(kuh.f14600a).getAsString(), hiHealthData);
                    e.e(jsonObject.get(kuh.f14600a).getAsString(), kuvVar);
                }
            }
        }
    }

    private boolean a(JsonObject jsonObject, int i) {
        kuk.b e;
        int c = kuh.c(jsonObject.get(kuh.c).getAsString());
        int asInt = jsonObject.get(kuh.d).getAsInt();
        String asString = jsonObject.get(kuh.f14600a).getAsString();
        if (i < 3 || (!(c == 4 || c == 5) || (e = kuf.a().e(asInt)) == null)) {
            return false;
        }
        for (kuk.b.d dVar : e.a()) {
            if (asString.equals(dVar.d())) {
                List<kuk.b.d.e> f = dVar.f();
                if (CollectionUtils.d(f)) {
                    continue;
                } else {
                    Iterator<kuk.b.d.e> it = f.iterator();
                    while (it.hasNext()) {
                        if (kuh.c(it.next().e()) == c) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kum e(String str) {
        return (kum) moj.e(str, kum.class);
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess_aggregateData", "doAction callback is null");
            return;
        }
        List<HiDataAggregateProOption> preProcess = preProcess(str);
        if (koq.b(preProcess)) {
            LogUtil.d("HiHealthProcess_aggregateData", "HiDataAggregateProOption list is empty");
            iBaseResponseCallback.d(-1, "");
        } else {
            LogUtil.d("HiHealthProcess_aggregateData", "aggregateHiHealthDataProEx ", preProcess);
            HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataProEx(preProcess, new d(this, str, iBaseResponseCallback));
        }
    }

    static class d implements HiAggregateListenerEx {

        /* renamed from: a, reason: collision with root package name */
        private final IBaseResponseCallback f14624a;
        private final WeakReference<kvf> c;
        private final String e;

        d(kvf kvfVar, String str, IBaseResponseCallback iBaseResponseCallback) {
            this.c = new WeakReference<>(kvfVar);
            this.e = str;
            this.f14624a = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(final SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            LogUtil.d("HiHealthProcess_aggregateData", "aggregateHiHealthDataProEx success, data is ", sparseArray);
            if (this.f14624a == null) {
                ReleaseLogUtil.c("HiHealthProcess_aggregateData", "doAction callback is null");
                return;
            }
            final kvf kvfVar = this.c.get();
            if (kvfVar == null) {
                LogUtil.c("HiHealthProcess_aggregateData", "process is null");
                this.f14624a.d(-1, "");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: kvd
                    @Override // java.lang.Runnable
                    public final void run() {
                        kvf.d.this.bRn_(kvfVar, sparseArray);
                    }
                });
            }
        }

        /* synthetic */ void bRn_(kvf kvfVar, SparseArray sparseArray) {
            this.f14624a.d(0, kvfVar.postProcess(sparseArray, kvfVar.e(this.e)));
        }
    }
}
