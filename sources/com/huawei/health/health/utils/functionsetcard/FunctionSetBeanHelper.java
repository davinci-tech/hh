package com.huawei.health.health.utils.functionsetcard;

import android.content.ContentValues;
import android.content.Context;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.dsm;
import defpackage.jdr;
import defpackage.jdx;
import defpackage.jfa;
import health.compact.a.EnvironmentUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FunctionSetBeanHelper {
    private static volatile FunctionSetBeanHelper e;

    /* renamed from: a, reason: collision with root package name */
    private Context f2484a;
    private JSONObject d;
    private String h;
    public String c = "0";
    private List<SpBean> b = new CopyOnWriteArrayList();
    private List<String> g = new ArrayList<String>() { // from class: com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper.1
        {
            add("FunctionSetWeightCardReader");
            add("FunctionSetHeartRateReader");
            add("FunctionSetStressCardReader");
            add("FunctionSetBloodSugarCardReader");
            add("FunctionSetTemperatureCardReader");
            add("SportsRecordingCardReader");
            add("FunctionSetBloodOxygenCardReader");
            add("FunctionSetSleepCardReader");
            add("FunctionSetBloodPressureReader");
        }
    };

    private FunctionSetBeanHelper() {
        Context e2 = BaseApplication.e();
        this.f2484a = e2;
        a(e2);
        g();
        if (this.c.equals("0")) {
            LogUtil.a("FunctionSetBeanHelper", "get mHuid");
            ThreadPoolManager.d().execute(new Runnable() { // from class: dpt
                @Override // java.lang.Runnable
                public final void run() {
                    FunctionSetBeanHelper.this.b();
                }
            });
        }
    }

    public /* synthetic */ void b() {
        this.c = LoginInit.getInstance(this.f2484a).getUsetId();
    }

    public static final FunctionSetBeanHelper c() {
        if (e == null) {
            synchronized (FunctionSetBeanHelper.class) {
                if (e == null) {
                    e = new FunctionSetBeanHelper();
                }
            }
        }
        return e;
    }

    private void g() {
        this.h = jfa.h(dsm.c, "health_model_data_cache");
    }

    public String a() {
        return this.h;
    }

    public void d(JSONObject jSONObject) {
        this.d = jSONObject;
        SharedPreferenceManager.c(Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), "enterMemberListTime", jSONObject.toString());
    }

    public JSONObject d() {
        JSONObject jSONObject = this.d;
        if (jSONObject != null) {
            return jSONObject;
        }
        try {
            return new JSONObject(SharedPreferenceManager.e(Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), "enterMemberListTime", ""));
        } catch (JSONException e2) {
            LogUtil.b("FunctionSetBeanHelper", ExceptionUtils.d(e2));
            return null;
        }
    }

    public HiHealthData a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<SpBean> it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SpBean next = it.next();
            if (str.equals(next.getKey())) {
                HiHealthData val = next.getVal();
                if (val == null) {
                    LogUtil.a("FunctionSetBeanHelper", str, ", data is null");
                    return null;
                }
                ContentValues valueHolder = val.getValueHolder();
                if (valueHolder != null && valueHolder.size() != 0) {
                    return val;
                }
                LogUtil.a("FunctionSetBeanHelper", str, ", contentValues is null");
            }
        }
        return null;
    }

    private void a(Context context) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            List<SpBean> b = b(context);
            if (b != null && !b.isEmpty()) {
                LogUtil.a("FunctionSetBeanHelper", b.toString());
                e(b);
                LogUtil.a("FunctionSetBeanHelper", "readCacheData success. times=", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
            LogUtil.a("FunctionSetBeanHelper", "getCardSp is null");
            List<SpBean> f = f();
            if (f.isEmpty()) {
                LogUtil.a("FunctionSetBeanHelper", "getEveryCardSp is null");
                return;
            }
            e(f);
            jdx.b(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetBeanHelper.this.i();
                    FunctionSetBeanHelper functionSetBeanHelper = FunctionSetBeanHelper.this;
                    functionSetBeanHelper.d((List<SpBean>) functionSetBeanHelper.b);
                }
            });
            LogUtil.a("FunctionSetBeanHelper", "readCacheData success. times=", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        } catch (ClassCastException unused) {
            LogUtil.b("FunctionSetBeanHelper", "ClassCastException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        for (int i = 0; i < this.g.size(); i++) {
            SharedPreferenceUtil.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).writeHealthData(this.g.get(i), null);
        }
        SharedPreferenceUtil.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).writeHealthData("version", EnvironmentUtils.a());
    }

    private void e(List<SpBean> list) {
        this.b.clear();
        for (SpBean spBean : list) {
            if (spBean != null) {
                String key = spBean.getKey();
                HiHealthData val = spBean.getVal();
                if (!TextUtils.isEmpty(key) && val != null) {
                    this.b.add(new SpBean(key, val));
                }
            }
        }
    }

    private List<SpBean> f() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.g.size(); i++) {
            String str = this.g.get(i);
            String readHealthData = SharedPreferenceUtil.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).readHealthData(str);
            if (TextUtils.isEmpty(readHealthData)) {
                LogUtil.a("FunctionSetBeanHelper", "cardData is null");
            } else {
                HiHealthData hiHealthData = (HiHealthData) jdr.bFW_(readHealthData, HiHealthData.CREATOR, null);
                if (hiHealthData == null) {
                    LogUtil.a("FunctionSetBeanHelper", "healthData is null");
                } else {
                    LogUtil.a("FunctionSetBeanHelper", "has sp data: ", str);
                    arrayList.add(new SpBean(str, hiHealthData));
                }
            }
        }
        return arrayList;
    }

    private List<SpBean> b(Context context) {
        if (context == null) {
            LogUtil.a("FunctionSetBeanHelper", "context: ", null);
            return null;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(10100), "AllCardReader");
        ArrayList arrayList = new ArrayList();
        jdr.d(b, getClass().getClassLoader(), null, arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SpBean> list) {
        if (SharedPreferenceManager.e(this.f2484a, Integer.toString(10100), "AllCardReader", jdr.b(list, null), new StorageParams()) == 0) {
            LogUtil.a("FunctionSetBeanHelper", "saveCardSp success");
        } else {
            LogUtil.a("FunctionSetBeanHelper", "saveCardSp failed");
        }
    }

    public void e() {
        e = null;
    }

    public final void e(final String str, final HiHealthData hiHealthData) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper.5
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetBeanHelper.this.a(str, hiHealthData);
                }
            });
        } else {
            a(str, hiHealthData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, HiHealthData hiHealthData) {
        HiHealthData hiHealthData2;
        String str2;
        int i;
        if (this.b == null) {
            LogUtil.a("FunctionSetBeanHelper", "mCardSpMap is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("FunctionSetBeanHelper", "tag is null");
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.b.size()) {
                hiHealthData2 = null;
                str2 = "";
                i = -1;
                break;
            } else {
                SpBean spBean = this.b.get(i2);
                if (str.equals(spBean.getKey())) {
                    hiHealthData2 = spBean.getVal();
                    i = i2;
                    str2 = str;
                    break;
                }
                i2++;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            this.b.add(new SpBean(str, hiHealthData));
            d(this.b);
            return;
        }
        if (hiHealthData2 == null && hiHealthData == null) {
            LogUtil.a("FunctionSetBeanHelper", "isSameData");
            return;
        }
        if (hiHealthData2 == null || hiHealthData == null) {
            this.b.set(i, new SpBean(str, hiHealthData));
            d(this.b);
        } else if (hiHealthData2.equals(hiHealthData)) {
            LogUtil.a("FunctionSetBeanHelper", "isSameData");
        } else {
            this.b.set(i, new SpBean(str, hiHealthData));
            d(this.b);
        }
    }

    public static class SpBean implements Parcelable {
        public static final Parcelable.Creator<SpBean> CREATOR = new Parcelable.Creator<SpBean>() { // from class: com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper.SpBean.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: YG_, reason: merged with bridge method [inline-methods] */
            public SpBean createFromParcel(Parcel parcel) {
                return new SpBean(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SpBean[] newArray(int i) {
                return new SpBean[i];
            }
        };

        @SerializedName(MedalConstants.EVENT_KEY)
        private String key;

        @SerializedName("val")
        private HiHealthData val;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SpBean(String str, HiHealthData hiHealthData) {
            this.key = str;
            this.val = hiHealthData;
        }

        public SpBean(Parcel parcel) {
            this.key = parcel.readString();
            this.val = (HiHealthData) parcel.readParcelable(HiHealthData.class.getClassLoader());
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public HiHealthData getVal() {
            return this.val;
        }

        public void setVal(HiHealthData hiHealthData) {
            this.val = hiHealthData;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.key);
            parcel.writeParcelable(this.val, i);
        }
    }
}
