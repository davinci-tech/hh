package com.huawei.health.ecologydevice.ui.healthdata.fragment;

import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.healthdata.adapter.BloodSugarFoodAdapter;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.dea;
import defpackage.deb;
import defpackage.dgk;
import defpackage.dql;
import defpackage.drd;
import defpackage.jdx;
import defpackage.msp;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class BloodSugarFeedbackFragment extends BloodSugarBaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2323a;
    private HealthColumnLinearLayout c;
    private BloodSugarFoodAdapter d;
    private HealthTextView e;
    private String f;
    private HealthRecycleView h;
    private String i;
    private boolean j;
    private String o;
    private final Handler g = new Handler();
    private final List<dgk> b = new ArrayList(16);

    public interface BloodSugarCallback {
        void result(int i, JSONObject jSONObject);
    }

    public interface UpDataFileListener {
        void onFinish();
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected void initView(View view) {
        super.initView(view);
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.item_blood_sugar_feedback, (ViewGroup) null);
        if (inflate == null) {
            LogUtil.h("BloodSugarFeedbackFragment", "viewChild is null");
            return;
        }
        this.mContainerLayout.addView(inflate);
        this.e = (HealthTextView) inflate.findViewById(R.id.blood_sugar_desc);
        this.f2323a = (HealthTextView) inflate.findViewById(R.id.blood_sugar_desc_by_blood_number);
        this.c = (HealthColumnLinearLayout) inflate.findViewById(R.id.blood_sugar_food_container);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.blood_sugar_foods_recycler_view);
        this.h = healthRecycleView;
        healthRecycleView.setLayoutManager(new GridLayoutManager(this.mActivity, 4) { // from class: com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment.5
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.mButtonConfirm.setText(R.string._2130845961_res_0x7f022109);
        this.mButtonConfirm.setOnClickListener(new View.OnClickListener() { // from class: dfy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarFeedbackFragment.this.Ul_(view2);
            }
        });
    }

    public /* synthetic */ void Ul_(View view) {
        if (this.mActivity.d() == 0) {
            this.mActivity.i();
        } else {
            this.mActivity.o();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected void initData() {
        super.initData();
        this.f = CommonUtil.c();
        String x = CommonUtil.x();
        this.i = x;
        LogUtil.c("BloodSugarFeedbackFragment", "current language : ", this.f, x);
        if (this.d == null) {
            this.d = new BloodSugarFoodAdapter(this.mActivity);
        }
        this.h.setAdapter(this.d);
    }

    private void i() {
        this.f2323a.setText(deb.e(this.mTimePeriod));
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected HiHealthData getHealthData() {
        return this.mActivity.g();
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected void updateView() {
        super.updateView();
        updateTopDateView(true);
        final int c = deb.c(this.mActivity, this.mTimePeriod, this.mValue);
        if (c == 1001) {
            this.j = true;
        } else {
            this.j = false;
            this.c.setVisibility(8);
        }
        a(new UpDataFileListener() { // from class: dgg
            @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment.UpDataFileListener
            public final void onFinish() {
                BloodSugarFeedbackFragment.this.a(c);
            }
        });
        i();
    }

    public /* synthetic */ void a(int i) {
        LogUtil.a("BloodSugarFeedbackFragment", "download onFinish");
        d(i);
        if (this.j) {
            e();
        }
    }

    private void a(UpDataFileListener upDataFileListener) {
        LogUtil.a("BloodSugarFeedbackFragment", "startDownload");
        HashMap hashMap = new HashMap(2);
        hashMap.put("configType", "bloodsugar");
        drd.e(new dql("com.huawei.health_Sport_Common", hashMap), "blood_sugar_suggestion", 7, new e(upDataFileListener));
    }

    private void d(int i) {
        final String valueOf = String.valueOf(i);
        if (i == 1003) {
            valueOf = i + "_" + this.mTimePeriod;
        }
        LogUtil.a("BloodSugarFeedbackFragment", "read file current level : ", valueOf);
        e(new BloodSugarCallback() { // from class: dfx
            @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment.BloodSugarCallback
            public final void result(int i2, JSONObject jSONObject) {
                BloodSugarFeedbackFragment.this.a(valueOf, i2, jSONObject);
            }
        });
    }

    public /* synthetic */ void a(String str, int i, JSONObject jSONObject) {
        LogUtil.a("BloodSugarFeedbackFragment", "load blood file result code : ", Integer.valueOf(i));
        if (i != 0 || jSONObject == null) {
            LogUtil.h("BloodSugarFeedbackFragment", "load blood file fail");
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("bloodSugarLevel");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                c(jSONObject2, jSONObject2.getString(OpAnalyticsConstants.OPERATION_ID), str);
            }
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("BloodSugarFeedbackFragment", "not find res id");
        } catch (NumberFormatException unused2) {
            LogUtil.b("BloodSugarFeedbackFragment", "num error");
        } catch (JSONException unused3) {
            LogUtil.b("BloodSugarFeedbackFragment", "JSONException ");
        }
    }

    private void e(final BloodSugarCallback bloodSugarCallback) {
        jdx.b(new Runnable() { // from class: dfz
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarFeedbackFragment.this.b(bloodSugarCallback);
            }
        });
    }

    public /* synthetic */ void b(BloodSugarCallback bloodSugarCallback) {
        String c = Utils.c(d() + "sugar_suggestion_config.json");
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("BloodSugarFeedbackFragment", "getBloodSuggestionDesc read json is null");
            bloodSugarCallback.result(1, null);
        } else {
            try {
                bloodSugarCallback.result(0, new JSONObject(c));
            } catch (JSONException unused) {
                LogUtil.b("BloodSugarFeedbackFragment", "getBloodSuggestionDesc JSONException");
                bloodSugarCallback.result(1, null);
            }
        }
    }

    private void c(JSONObject jSONObject, String str, String str2) throws JSONException {
        final String replace;
        LogUtil.a("BloodSugarFeedbackFragment", "current id is ", str, ", compare id is ", str2);
        if (!str2.equals(str)) {
            LogUtil.c("BloodSugarFeedbackFragment", "id is different from compareId");
            return;
        }
        String string = jSONObject.getString("suggesionDesc");
        LogUtil.a("BloodSugarFeedbackFragment", "serviceId is ", string);
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("BloodSugarFeedbackFragment", "serviceId is null");
            return;
        }
        String str3 = d() + "strings";
        String d = dea.d(string, str3, this.f, this.i);
        LogUtil.a("BloodSugarFeedbackFragment", "path is ", str3);
        if (TextUtils.isEmpty(d)) {
            replace = dea.e(str3 + "/strings.xml", string);
        } else {
            replace = d.replace("\\", "");
            LogUtil.a("BloodSugarFeedbackFragment", "desc is ", replace);
        }
        e(jSONObject, jSONObject.keys(), replace);
        LogUtil.a("BloodSugarFeedbackFragment", "current desc ", this.o);
        this.g.post(new Runnable() { // from class: dgd
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarFeedbackFragment.this.d(replace);
            }
        });
    }

    public /* synthetic */ void d(String str) {
        if (!TextUtils.isEmpty(this.o)) {
            this.e.setText(this.o);
        } else {
            this.e.setText(str);
        }
    }

    private void e(JSONObject jSONObject, Iterator<String> it, String str) throws JSONException {
        this.o = "";
        while (it.hasNext()) {
            String next = it.next();
            LogUtil.a("BloodSugarFeedbackFragment", "replace key is ", next, ", desc is ", str);
            if ("placeholder".equals(next)) {
                this.o = e(str, jSONObject.getJSONArray("placeholder"));
            }
        }
    }

    private String e(String str, JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || TextUtils.isEmpty(str)) {
            LogUtil.h("BloodSugarFeedbackFragment", "placeholder or description is null");
            return str;
        }
        LogUtil.c("BloodSugarFeedbackFragment", "current desc ", str);
        int length = jSONArray.length();
        if (length == 1) {
            return d(str, jSONArray);
        }
        if (length == 2) {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("value");
            JSONObject jSONObject2 = jSONArray.getJSONObject(1);
            return c(str, string, string2, jSONObject2.getString("type"), jSONObject2.getString("value"));
        }
        return d(str, jSONArray);
    }

    private String c(String str, String str2, String str3, String str4, String str5) {
        if ("100".equals(str2)) {
            if ("100".equals(str4)) {
                return String.format(Locale.ROOT, str, Integer.valueOf(nsn.e(str3)), Integer.valueOf(nsn.e(str5)));
            }
            return String.format(Locale.ROOT, str, Integer.valueOf(nsn.e(str3)), Float.valueOf(nsn.f(str5)));
        }
        if ("101".equals(str2)) {
            if ("101".equals(str4)) {
                return String.format(Locale.ROOT, str, Float.valueOf(nsn.f(str3)), Float.valueOf(nsn.f(str5)));
            }
            return String.format(Locale.ROOT, str, Float.valueOf(nsn.f(str3)), Integer.valueOf(nsn.e(str5)));
        }
        LogUtil.c("BloodSugarFeedbackFragment", "no replace");
        return "";
    }

    private String d(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = jSONArray.getJSONObject(0);
        String string = jSONObject.getString("type");
        String string2 = jSONObject.getString("value");
        if ("100".equals(string)) {
            return String.format(Locale.ROOT, str, Integer.valueOf(nsn.e(string2)));
        }
        if (string.equals("101")) {
            return String.format(Locale.ROOT, str, Float.valueOf(nsn.f(string2)));
        }
        LogUtil.c("BloodSugarFeedbackFragment", "no replace");
        return "";
    }

    private void e() {
        d(new BloodSugarCallback() { // from class: dgf
            @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment.BloodSugarCallback
            public final void result(int i, JSONObject jSONObject) {
                BloodSugarFeedbackFragment.this.e(i, jSONObject);
            }
        });
    }

    public /* synthetic */ void e(int i, JSONObject jSONObject) {
        if (i != 0 || jSONObject == null) {
            LogUtil.h("BloodSugarFeedbackFragment", "readBloodSugarFoodList fail");
            return;
        }
        try {
            c(jSONObject.getJSONArray("sugarFood"));
            this.g.post(new Runnable() { // from class: dgb
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarFeedbackFragment.this.b();
                }
            });
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("BloodSugarFeedbackFragment", "not find res id");
        } catch (JSONException unused2) {
            LogUtil.b("BloodSugarFeedbackFragment", "JSONException ");
        }
    }

    public /* synthetic */ void b() {
        this.d.a(this.b);
        this.c.setVisibility(0);
    }

    private void c(JSONArray jSONArray) throws JSONException {
        this.b.clear();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            dgk dgkVar = new dgk();
            a(jSONObject, dgkVar, jSONObject.getString("name"));
            dgkVar.b(d() + "images" + File.separator + jSONObject.getString("pictureUrl"));
            if (this.b.size() < 4) {
                this.b.add(dgkVar);
            }
        }
    }

    private void a(JSONObject jSONObject, dgk dgkVar, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BloodSugarFeedbackFragment", "serviceId is null");
            return;
        }
        String d = dea.d(str, d() + "strings", this.f, this.i);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            LogUtil.c("BloodSugarFeedbackFragment", "replace key is ", next);
            if ("placeholder".equals(next)) {
                d = e(d, jSONObject.getJSONArray("placeholder"));
            }
        }
        dgkVar.e(d);
    }

    private void d(final BloodSugarCallback bloodSugarCallback) {
        jdx.b(new Runnable() { // from class: dga
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarFeedbackFragment.this.c(bloodSugarCallback);
            }
        });
    }

    public /* synthetic */ void c(BloodSugarCallback bloodSugarCallback) {
        String c = Utils.c(d() + "sugar_food_config.json");
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("BloodSugarFeedbackFragment", "getSugarFoodList json is null");
            bloodSugarCallback.result(1, null);
        } else {
            try {
                bloodSugarCallback.result(0, new JSONObject(c));
            } catch (JSONException unused) {
                LogUtil.b("BloodSugarFeedbackFragment", "getTaskCardPathList JSONException");
                bloodSugarCallback.result(1, null);
            }
        }
    }

    static class e implements DownloadCallback<File> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<UpDataFileListener> f2324a;

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
        }

        e(UpDataFileListener upDataFileListener) {
            this.f2324a = new WeakReference<>(upDataFileListener);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onFinish(File file) {
            if (!file.exists()) {
                LogUtil.h("BloodSugarFeedbackFragment", "onFinish data is: file is null");
                return;
            }
            LogUtil.a("BloodSugarFeedbackFragment", "download success");
            msp.c(BloodSugarFeedbackFragment.c(), file.getParent());
            UpDataFileListener upDataFileListener = this.f2324a.get();
            if (upDataFileListener == null) {
                LogUtil.h("BloodSugarFeedbackFragment", "upDataFileListener is null");
            } else {
                upDataFileListener.onFinish();
            }
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            LogUtil.b("BloodSugarFeedbackFragment", "onFail: errCode = ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c() {
        return drd.d("com.huawei.health_Sport_Common", "blood_sugar_suggestion", "zip");
    }

    private String d() {
        String c = c();
        if (TextUtils.isEmpty(c)) {
            return "";
        }
        File file = new File(c);
        if (!file.exists()) {
            return "";
        }
        return file.getParent() + File.separator + "blood_sugar_suggestions" + File.separator;
    }
}
