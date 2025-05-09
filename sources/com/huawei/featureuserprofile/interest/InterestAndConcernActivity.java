package com.huawei.featureuserprofile.interest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.featureuserprofile.interest.InterestAndConcernActivity;
import com.huawei.featureuserprofile.interest.adapter.InterestAndConcernAdapter;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.bzw;
import defpackage.jal;
import defpackage.jao;
import defpackage.jaq;
import defpackage.jar;
import defpackage.jas;
import defpackage.jat;
import defpackage.jau;
import defpackage.jav;
import defpackage.jaw;
import defpackage.jbs;
import health.compact.a.HiCommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class InterestAndConcernActivity extends BaseActivity {
    private static final List<String> c;

    /* renamed from: a, reason: collision with root package name */
    private Context f1983a;
    private HealthRecycleView f;
    private CustomTitleBar g;
    private InterestAndConcernAdapter h;
    private List<jao> k;
    private CommonDialog21 m;
    private RelativeLayout o;
    private ArrayList<String> q = new ArrayList<>(10);
    private ArrayList<String> i = new ArrayList<>(10);
    private ArrayList<String> e = new ArrayList<>();
    private ArrayList<Boolean> d = new ArrayList<>();
    private List<String> b = new ArrayList();
    private e t = new e(this);
    private b l = new b(this);
    private a n = new a(this);
    private Handler j = new d(this);

    private boolean e(char c2) {
        return c2 == '1';
    }

    static {
        ArrayList arrayList = new ArrayList(10);
        c = arrayList;
        arrayList.add("跑步");
        arrayList.add("骑行");
        arrayList.add("走路");
        arrayList.add("健身");
        arrayList.add("体重管理");
        arrayList.add("睡眠质量");
        arrayList.add("高血压管理");
        arrayList.add("糖尿病管理");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1983a = this;
        setContentView(R.layout.hw_health_interest_and_concern_layout);
        cancelAdaptRingRegion();
        j();
        i();
    }

    private void j() {
        k();
        this.o = (RelativeLayout) findViewById(R.id.interest_relative);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.interest_healthrecycleview);
        this.f = healthRecycleView;
        healthRecycleView.c(false);
        this.f.b(false);
        this.g = (CustomTitleBar) findViewById(R.id.me_userInfo_titlebar);
        if (LanguageUtil.f(this.f1983a)) {
            this.g.setTitleSize(16.0f);
        }
    }

    private void i() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: brz
            @Override // java.lang.Runnable
            public final void run() {
                InterestAndConcernActivity.this.d();
            }
        });
        this.g.setRightButtonClickable(true);
        this.g.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.interest.InterestAndConcernActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (InterestAndConcernActivity.this.d.size() > 0 && InterestAndConcernActivity.this.d.size() == InterestAndConcernActivity.this.e.size()) {
                    InterestAndConcernActivity.this.h();
                    SharedPreferenceManager.c("privacy_center", "hobby", String.valueOf(System.currentTimeMillis()));
                    if (InterestAndConcernActivity.this.f1983a instanceof Activity) {
                        ((Activity) InterestAndConcernActivity.this.f1983a).finish();
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public /* synthetic */ void d() {
        jal jalVar = new jal();
        jalVar.d("777913529bb71e1a");
        jalVar.c("新用户兴趣标签");
        jbs.a(this.f1983a).c(jalVar, this.t);
    }

    private void k() {
        if (this.m == null) {
            this.m = CommonDialog21.a(this.f1983a);
        }
        this.m.setCancelable(true);
        this.m.e(this.f1983a.getString(R.string._2130841528_res_0x7f020fb8));
        this.m.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CommonDialog21 commonDialog21 = this.m;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.m.dismiss();
        this.m = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Handler handler;
        ArrayList arrayList = new ArrayList();
        jas e2 = jbs.a(BaseApplication.getContext()).e(new jar());
        if (e2 != null && e2.getResultCode().intValue() == 0) {
            LogUtil.h("SMART_InterestAndConcernActivity", "User tag");
            for (jau jauVar : e2.b()) {
                if (jauVar != null && !HiCommonUtil.d(jauVar.e())) {
                    arrayList.addAll(jauVar.e());
                }
            }
            b((List<String>) arrayList, true);
            LogUtil.a("SMART_InterestAndConcernActivity", "allLabelList :", this.e.toString(), "allLabelBooleanList :", this.d.toString(), ",labelValueAll.size():", Integer.valueOf(arrayList.size()));
            if (arrayList.size() == 0) {
                HiUserPreference userPreference = HiHealthManager.d(this.f1983a).getUserPreference("custom.onboarding_concern_status");
                if (userPreference != null && (handler = this.j) != null) {
                    this.j.sendMessage(handler.obtainMessage(2, userPreference));
                } else {
                    LogUtil.a("SMART_InterestAndConcernActivity", "hiUserPreference is null");
                    b bVar = this.l;
                    if (bVar != null) {
                        bVar.sendEmptyMessage(3);
                    }
                }
            } else {
                LogUtil.a("SMART_InterestAndConcernActivity", "mSportHealthInfoHandler is", this.l);
                b bVar2 = this.l;
                if (bVar2 != null) {
                    bVar2.sendEmptyMessage(3);
                }
            }
            e();
            return;
        }
        LogUtil.a("SMART_InterestAndConcernActivity", "rsp is null");
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiUserPreference hiUserPreference) {
        if (hiUserPreference != null && !TextUtils.isEmpty(hiUserPreference.getValue()) && this.d.size() > 0 && hiUserPreference.getValue().length() == c.size()) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                List<String> list = c;
                if (i >= list.size()) {
                    break;
                }
                if (e(hiUserPreference.getValue().charAt(i))) {
                    arrayList.add(list.get(i));
                }
                i++;
            }
            b((List<String>) arrayList, false);
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("SMART_InterestAndConcernActivity", "enter adapterInit ", "allLabelList :", this.e.toString(), "allLabelBooleanList :", this.d.toString());
        this.h = new InterestAndConcernAdapter(this, this.q, this.e, this.d);
        this.f.setLayoutManager(new HealthLinearLayoutManager(this.f1983a));
        this.f.setAdapter(this.h);
        this.h.c(new InterestAndConcernAdapter.OnCheckBoxClickListener() { // from class: brx
            @Override // com.huawei.featureuserprofile.interest.adapter.InterestAndConcernAdapter.OnCheckBoxClickListener
            public final void onCheckBoxClick(boolean z, int i) {
                InterestAndConcernActivity.this.d(z, i);
            }
        });
    }

    public /* synthetic */ void d(boolean z, int i) {
        this.d.set(i, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        n();
        ThreadPoolManager.d().execute(new Runnable() { // from class: bse
            @Override // java.lang.Runnable
            public final void run() {
                InterestAndConcernActivity.this.a();
            }
        });
        bzw.e().setEvent(this.f1983a, String.valueOf(1100), new HashMap());
    }

    public /* synthetic */ void a() {
        Context context = BaseApplication.getContext();
        String sb = d(this.b).toString();
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.onboarding_concern_status");
        hiUserPreference.setValue(sb);
        HiHealthManager.d(context).setUserPreference(hiUserPreference);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(context).synCloud(hiSyncOption, null);
        LogUtil.a("SMART_InterestAndConcernActivity", "Interest_up_to_cloud", sb);
        if ("00000000".equals(sb)) {
            return;
        }
        SharedPreferenceManager.e(context, Integer.toString(10000), "hw_health_have_concern", Integer.toString(1), new StorageParams());
    }

    private void n() {
        jau jauVar = new jau();
        jauVar.e("运动喜好");
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < this.q.size(); i++) {
            if (this.d.get(i).booleanValue()) {
                arrayList.add(this.q.get(i));
            }
        }
        this.b.addAll(arrayList);
        jauVar.a(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(jauVar);
        LogUtil.a("SMART_InterestAndConcernActivity", "sportFavorUpload:", jauVar.a(), " labelValues:", jauVar.e());
        a(arrayList2);
    }

    private void a(List<jau> list) {
        jau jauVar = new jau();
        jauVar.e("健康偏好");
        ArrayList arrayList = new ArrayList();
        int size = this.q.size();
        for (int i = 0; i < this.i.size(); i++) {
            if (this.d.get(size + i).booleanValue()) {
                arrayList.add(this.i.get(i));
            }
        }
        this.b.addAll(arrayList);
        jauVar.a(arrayList);
        list.add(jauVar);
        jaw jawVar = new jaw();
        jawVar.e(list);
        LogUtil.a("SMART_InterestAndConcernActivity", "healthLabelUpload:", jauVar.a(), " labelValues:", jauVar.e());
        jbs.a(this).e(jawVar, this.n);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        e();
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("SMART_InterestAndConcernActivity", "onDestroy");
        super.onDestroy();
        if (this.j != null) {
            this.j = null;
        }
        if (this.l != null) {
            this.l = null;
        }
        e();
    }

    /* loaded from: classes7.dex */
    static class d extends BaseHandler<InterestAndConcernActivity> {
        public d(InterestAndConcernActivity interestAndConcernActivity) {
            super(interestAndConcernActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: tM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InterestAndConcernActivity interestAndConcernActivity, Message message) {
            if (message.what == 2 && (message.obj instanceof HiUserPreference)) {
                interestAndConcernActivity.d((HiUserPreference) message.obj);
            }
        }
    }

    private void b(List<String> list, boolean z) {
        Iterator<String> it = this.q.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (list.contains(it.next())) {
                if (z) {
                    this.d.add(true);
                } else {
                    this.d.set(i, true);
                }
            } else if (z) {
                this.d.add(false);
            } else {
                ArrayList<Boolean> arrayList = this.d;
                arrayList.set(i, arrayList.get(i));
            }
            i++;
        }
        e(list, z);
    }

    private void e(List<String> list, boolean z) {
        Iterator<String> it = this.i.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (list.contains(it.next())) {
                if (z) {
                    this.d.add(true);
                } else {
                    this.d.set(this.q.size() + i, true);
                }
            } else if (z) {
                this.d.add(false);
            } else {
                this.d.set(this.q.size() + i, this.d.get(this.q.size() + i));
            }
            i++;
        }
    }

    private StringBuilder d(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            if (list.contains(it.next())) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        LogUtil.a("SMART_InterestAndConcernActivity", "selectSportHealth :", sb);
        return sb;
    }

    /* loaded from: classes7.dex */
    static class a implements ResultCallback<CloudCommonReponse> {
        WeakReference<InterestAndConcernActivity> b;

        public a(InterestAndConcernActivity interestAndConcernActivity) {
            this.b = new WeakReference<>(interestAndConcernActivity);
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(CloudCommonReponse cloudCommonReponse) {
            InterestAndConcernActivity interestAndConcernActivity = this.b.get();
            if (interestAndConcernActivity == null) {
                LogUtil.h("SMART_InterestAndConcernActivity", "InterestSaveLabelCallback activity null");
                return;
            }
            if (cloudCommonReponse.getResultCode().intValue() != 0) {
                LogUtil.h("SMART_InterestAndConcernActivity", "InterestSaveLabelCallback upload label fail reason is [", cloudCommonReponse.getResultCode(), ", " + cloudCommonReponse.getResultDesc(), "]");
                interestAndConcernActivity.c();
                return;
            }
            LogUtil.a("SMART_InterestAndConcernActivity", "InterestSaveLabelCallback upload label success");
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            LogUtil.b("SMART_InterestAndConcernActivity", "InterestSaveLabelCallback fail, error is ", th.getMessage());
            InterestAndConcernActivity interestAndConcernActivity = this.b.get();
            if (interestAndConcernActivity != null) {
                interestAndConcernActivity.c();
            } else {
                LogUtil.h("SMART_InterestAndConcernActivity", "InterestSaveLabelCallback activity null");
            }
        }
    }

    /* loaded from: classes7.dex */
    static class e implements ResultCallback<jaq> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<InterestAndConcernActivity> f1985a;

        public e(InterestAndConcernActivity interestAndConcernActivity) {
            this.f1985a = new WeakReference<>(interestAndConcernActivity);
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(jaq jaqVar) {
            InterestAndConcernActivity interestAndConcernActivity = this.f1985a.get();
            if (interestAndConcernActivity == null) {
                LogUtil.h("SMART_InterestAndConcernActivity", "SportHealthLabelCallback activity null");
                return;
            }
            if (jaqVar.getResultCode().intValue() != 0) {
                LogUtil.b("SMART_InterestAndConcernActivity", "LabelCallback rsp resultCode is", jaqVar.getResultCode(), " ", jaqVar.getResultDesc());
                interestAndConcernActivity.c();
            } else {
                if (jaqVar.e() != null && jaqVar.e().c() != null) {
                    interestAndConcernActivity.k = jaqVar.e().c();
                    b bVar = interestAndConcernActivity.l;
                    if (bVar != null) {
                        bVar.sendEmptyMessage(1);
                        return;
                    }
                    return;
                }
                LogUtil.b("SMART_InterestAndConcernActivity", "SportHealthLabelCallback rsp body data null");
                interestAndConcernActivity.c();
            }
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            LogUtil.b("SMART_InterestAndConcernActivity", "SportHealthLabelCallback Label fail, pageClose, error is ", th.getMessage());
            InterestAndConcernActivity interestAndConcernActivity = this.f1985a.get();
            if (interestAndConcernActivity != null) {
                interestAndConcernActivity.c();
            } else {
                LogUtil.h("SMART_InterestAndConcernActivity", "SportHealthLabelCallback activity null");
            }
        }
    }

    /* loaded from: classes7.dex */
    static class b extends BaseHandler<InterestAndConcernActivity> {
        public b(InterestAndConcernActivity interestAndConcernActivity) {
            super(interestAndConcernActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: tN_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InterestAndConcernActivity interestAndConcernActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                interestAndConcernActivity.g();
                return;
            }
            if (i == 3) {
                interestAndConcernActivity.b();
            } else {
                if (i != 4) {
                    return;
                }
                interestAndConcernActivity.o.setVisibility(0);
                interestAndConcernActivity.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        List<jao> list = this.k;
        if (list == null) {
            LogUtil.h("SMART_InterestAndConcernActivity", "interestAndConcern labelRules is null");
            return;
        }
        for (jao jaoVar : list) {
            if (jaoVar == null) {
                LogUtil.h("SMART_InterestAndConcernActivity", "interestAndConcern labeRule null skip");
            } else if ("运动喜好".equals(jaoVar.e())) {
                b(jaoVar.a(), this.q);
            } else if ("健康偏好".equals(jaoVar.e())) {
                b(jaoVar.a(), this.i);
            } else {
                LogUtil.a("SMART_InterestAndConcernActivity", "interestAndConcern one labelRule");
            }
        }
        this.e.addAll(this.q);
        this.e.addAll(this.i);
        ThreadPoolManager.d().execute(new Runnable() { // from class: bsd
            @Override // java.lang.Runnable
            public final void run() {
                InterestAndConcernActivity.this.f();
            }
        });
    }

    private void b(jav javVar, ArrayList<String> arrayList) {
        if (javVar == null || javVar.a() == null) {
            LogUtil.a("SMART_InterestAndConcernActivity", "interestAndConcern label from cloud has empty data");
            return;
        }
        List<jat> a2 = javVar.a();
        for (int i = 0; i < a2.size(); i++) {
            arrayList.add(a2.get(i).e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        b bVar = this.l;
        if (bVar != null) {
            bVar.sendEmptyMessage(4);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
