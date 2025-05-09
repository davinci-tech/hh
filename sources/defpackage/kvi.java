package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwonesdk.process.HiHealthProcess;
import defpackage.kvi;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kvi implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HiDataReadProOption preProcess(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiHealthProcess__readData", "preProcess request is empty");
            return null;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (str.contains(kuh.j)) {
            kuo kuoVar = (kuo) moj.e(str, kuo.class);
            LogUtil.d("HiHealthProcess__readData", "Request is ", kuoVar);
            if (kuoVar == null) {
                ReleaseLogUtil.c("HiHealthProcess__readData", "samplePointReadRequest is null");
                return null;
            }
            hiDataReadOption.setType(CommonUtil.b(kuf.a().e(kuoVar.g())));
            e(kuoVar, hiDataReadOption);
        } else if (str.contains(kuh.f)) {
            kuw kuwVar = (kuw) moj.e(str, kuw.class);
            LogUtil.d("HiHealthProcess__readData", "Request is ", kuwVar);
            if (kuwVar == null) {
                ReleaseLogUtil.c("HiHealthProcess__readData", "sampleStatReadRequest is null");
                return null;
            }
            hiDataReadOption.setType(kuf.a().c(kuwVar.n().a(), kuwVar.g()));
            e(kuwVar, hiDataReadOption);
        } else if (str.contains(kuh.g)) {
            kut kutVar = (kut) moj.e(str, kut.class);
            LogUtil.d("HiHealthProcess__readData", "Request is ", kutVar);
            if (kutVar == null) {
                ReleaseLogUtil.c("HiHealthProcess__readData", "sampleStatReadRequest is null");
                return null;
            }
            hiDataReadOption.setType(kutVar.m());
            e(kutVar, hiDataReadOption);
            return new HiDataReadProOption.Builder().e(hiDataReadOption).b(!kutVar.g().d() ? 1 : 0).e();
        }
        if (hiDataReadOption.getType() == null || hiDataReadOption.getType().length == 0) {
            LogUtil.c("HiHealthProcess__readData", "types is empty");
            return null;
        }
        return new HiDataReadProOption.Builder().e(hiDataReadOption).e();
    }

    private static HiDataReadOption e(kuq kuqVar, HiDataReadOption hiDataReadOption) {
        if (kuqVar.d() != 0) {
            hiDataReadOption.setTimeInterval(kuqVar.e(), kuqVar.d());
        } else {
            try {
                hiDataReadOption.setTimeInterval(DateFormatUtil.b(DateFormatUtil.d(kuqVar.b(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY).getTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_8), DateFormatUtil.b(DateFormatUtil.d(kuqVar.c(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY).getTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_8), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            } catch (ParseException unused) {
                LogUtil.e("HiHealthProcess__readData", "ParseException");
            }
        }
        if (kuqVar.a() != null) {
            hiDataReadOption.setDeviceUuid(kuqVar.a().a());
            hiDataReadOption.setReadType(2);
        } else {
            hiDataReadOption.setReadType(0);
        }
        hiDataReadOption.setSortOrder(kuqVar.h());
        hiDataReadOption.setCount(kuqVar.i());
        return hiDataReadOption;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        if (!(obj instanceof SparseArray)) {
            LogUtil.c("HiHealthProcess__readData", "postProcess null");
            return "[]";
        }
        if ((obj2 instanceof kuo) || (obj2 instanceof kut)) {
            SparseArray sparseArray = (SparseArray) obj;
            ArrayList<kuu> arrayList = new ArrayList<>();
            for (Map.Entry entry : ((kuq) obj2).j().entrySet()) {
                e((List) sparseArray.get(((Integer) entry.getKey()).intValue()), (List) entry.getValue(), arrayList);
            }
            LogUtil.d("HiHealthProcess__readData", "arkUiDataList is ", moj.e(arrayList));
            return moj.e(arrayList);
        }
        if (!(obj2 instanceof kuw)) {
            return "[]";
        }
        SparseArray sparseArray2 = (SparseArray) obj;
        ArrayList<kuu> arrayList2 = new ArrayList<>();
        for (Map.Entry<Integer, JsonObject> entry2 : ((kuw) obj2).j().entrySet()) {
            d((List<HiHealthData>) sparseArray2.get(entry2.getKey().intValue()), entry2.getValue(), arrayList2);
        }
        LogUtil.d("HiHealthProcess__readData", "arkUiDataList is ", moj.e(arrayList2));
        return moj.e(arrayList2);
    }

    private void e(List<HiHealthData> list, List<JsonObject> list2, ArrayList<kuu> arrayList) {
        if (CollectionUtils.d(list)) {
            return;
        }
        for (JsonObject jsonObject : list2) {
            for (HiHealthData hiHealthData : list) {
                kuu b = kuh.b(arrayList, hiHealthData);
                if (b == null) {
                    b = new kuu(hiHealthData);
                    b.e(new kun(jsonObject.get(kuh.d).getAsInt(), jsonObject.get(kuh.f14600a).getAsString()));
                    arrayList.add(b);
                }
                b.b(hiHealthData.getString("trackdata_deviceType"));
                c(jsonObject, hiHealthData, b);
            }
        }
    }

    private void c(JsonObject jsonObject, HiHealthData hiHealthData, kuu kuuVar) {
        boolean asBoolean = jsonObject.get(kuh.b) != null ? jsonObject.get(kuh.b).getAsBoolean() : false;
        Object obj = "";
        String asString = (!asBoolean || jsonObject.get(kuh.e) == null) ? "" : jsonObject.get(kuh.e).getAsString();
        if (TextUtils.isEmpty(asString)) {
            obj = asBoolean ? hiHealthData.getMetaData() : Double.valueOf(hiHealthData.getValue());
        } else {
            JsonObject asJsonObject = JsonParser.parseString(hiHealthData.getMetaData()).getAsJsonObject();
            if (asJsonObject.has(asString)) {
                obj = asJsonObject.get(asString);
                if (!nsn.c(String.valueOf(obj))) {
                    obj = String.valueOf(obj);
                }
            }
        }
        kuuVar.e(jsonObject.get(kuh.f14600a).getAsString(), obj);
    }

    private void d(List<HiHealthData> list, JsonObject jsonObject, ArrayList<kuu> arrayList) {
        if (CollectionUtils.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            kuu d2 = kuh.d(arrayList, hiHealthData);
            if (d2 == null) {
                d2 = new kuu(hiHealthData);
                d2.e(new kun(jsonObject.get(kuh.d).getAsInt(), jsonObject.get(kuh.f14600a).getAsString()));
                arrayList.add(d2);
            }
            d(jsonObject, hiHealthData, d2);
        }
    }

    private void d(JsonObject jsonObject, HiHealthData hiHealthData, kuu kuuVar) {
        Map<String, Object> a2 = kuuVar.a();
        Map hashMap = new HashMap();
        if (a2.containsKey(jsonObject.get(kuh.h).getAsString())) {
            Object obj = a2.get(jsonObject.get(kuh.h).getAsString());
            if (obj instanceof Map) {
                hashMap = (Map) obj;
            }
        }
        hashMap.put(jsonObject.get(kuh.f14600a).getAsString(), Double.valueOf(hiHealthData.getValue()));
        kuuVar.e(jsonObject.get(kuh.h).getAsString(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kuq e(String str) {
        if (str.contains(kuh.j)) {
            return (kuq) moj.e(str, kuo.class);
        }
        if (str.contains(kuh.f)) {
            return (kuq) moj.e(str, kuw.class);
        }
        if (str.contains(kuh.g)) {
            return (kuq) moj.e(str, kut.class);
        }
        return null;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("HiHealthProcess__readData", "doAction callback is null");
            return;
        }
        HiDataReadProOption preProcess = preProcess(str);
        LogUtil.d("HiHealthProcess__readData", "preProcess readHiHealthDataPro", preProcess);
        if (preProcess == null) {
            iBaseResponseCallback.d(-1, "");
            ReleaseLogUtil.c("HiHealthProcess__readData", "doAction option is null");
        } else {
            if (c(str, iBaseResponseCallback)) {
                return;
            }
            HiHealthManager.d(BaseApplication.e()).readHiHealthDataPro(preProcess, new d(this, str, iBaseResponseCallback));
        }
    }

    private boolean c(String str, final IBaseResponseCallback iBaseResponseCallback) {
        final kuq e = e(str);
        if (!(e instanceof kuo) || ((kuo) e).g()[0] != DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()) {
            return false;
        }
        LogUtil.d("HiHealthProcess__readData", "customAction enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: kvj
            @Override // java.lang.Runnable
            public final void run() {
                kuh.e(kuf.a().a(((kuo) kuq.this).g()), new Object[]{"", iBaseResponseCallback, ""});
            }
        });
        return true;
    }

    static class d implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final String f14628a;
        private final IBaseResponseCallback b;
        private final WeakReference<kvi> d;

        d(kvi kviVar, String str, IBaseResponseCallback iBaseResponseCallback) {
            this.d = new WeakReference<>(kviVar);
            this.f14628a = str;
            this.b = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(final Object obj, int i, int i2) {
            LogUtil.d("HiHealthProcess__readData", "readHiHealthDataPro success, data is ", obj);
            final kvi kviVar = this.d.get();
            if (kviVar != null && this.b != null) {
                final kuq e = kviVar.e(this.f14628a);
                if (e == null) {
                    this.b.d(-1, "");
                    return;
                } else {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: kvk
                        @Override // java.lang.Runnable
                        public final void run() {
                            kvi.d.this.c(kviVar, obj, e);
                        }
                    });
                    return;
                }
            }
            LogUtil.c("HiHealthProcess__readData", "process or callback is null");
        }

        /* synthetic */ void c(kvi kviVar, Object obj, kuq kuqVar) {
            String a2;
            String postProcess = kviVar.postProcess(obj, kuqVar);
            if (kuqVar instanceof kuo) {
                a2 = kuf.a().a(((kuo) kuqVar).g());
            } else {
                a2 = kuqVar instanceof kut ? kuf.a().a(((kut) kuqVar).m()) : "";
            }
            if (!TextUtils.isEmpty(a2)) {
                kuh.e(a2, new Object[]{postProcess, this.b, obj});
            } else {
                this.b.d(0, postProcess);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.d("HiHealthProcess__readData", "readHiHealthDataPro, data is ", obj);
            this.b.d(-1, "");
        }
    }
}
