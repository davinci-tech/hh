package com.huawei.ui.main.stories.fitness.activity.step;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor;
import com.huawei.ui.main.stories.fitness.views.fitnessdata.DayStepSourceItemAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import defpackage.koq;
import defpackage.pwc;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DayStepSourceListActivity extends BaseActivity implements DayStepSourceItemAdapter.OnDayStepSourceItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9895a;
    private DayStepSourceItemAdapter c;
    private HealthCardView f;
    private HealthRecycleView h;
    private CustomTitleBar i;
    private List<HiHealthData> j = new ArrayList(10);
    private List<pwc> e = new ArrayList(10);
    private d d = new d(this);
    private FitnessStepDetailInteractor b = new FitnessStepDetailInteractor(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_step_source_list);
        c();
        a();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.fitnessdata.DayStepSourceItemAdapter.OnDayStepSourceItemClickListener
    public void onItemClick(int i) {
        PageModelArgs b = b();
        List<HiHealthData> list = this.j;
        if (list == null || list.size() < i) {
            LogUtil.h("SCUI_DayStepSourceListActivity", "onItemClick mSourceList is null or size is less than position");
            return;
        }
        PrivacyDataModel d2 = d(this.j.get(i));
        Intent intent = new Intent();
        intent.putExtra("extra_page_model_args", b);
        intent.putExtra("extra_privacy_data_model", d2);
        intent.setClass(this, PrivacyDetailActivity.class);
        startActivity(intent);
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ctb_step_source_title);
        this.i = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.f = (HealthCardView) findViewById(R.id.hcv_RecyclerView_container);
        this.h = (HealthRecycleView) findViewById(R.id.hrv_step_source_item);
        this.f9895a = (LinearLayout) findViewById(R.id.no_data_source);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("SCUI_DayStepSourceListActivity", "initData intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        long longExtra = intent.getLongExtra("point_step_time", System.currentTimeMillis());
        this.i.setTitleText(DateFormatUtil.d(longExtra, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMMDD));
        d();
        b(stringExtra, longExtra);
    }

    private void d() {
        this.h.setLayoutManager(new HealthLinearLayoutManager(BaseApplication.getContext()));
        DayStepSourceItemAdapter dayStepSourceItemAdapter = new DayStepSourceItemAdapter(BaseApplication.getContext(), this.e);
        this.c = dayStepSourceItemAdapter;
        this.h.setAdapter(dayStepSourceItemAdapter);
        this.c.e(this);
    }

    private String d(int i) {
        return getResources().getQuantityString(R.plurals._2130903205_res_0x7f0300a5, i, UnitUtil.e(i, 1, 0));
    }

    private void b(String str, long j) {
        this.b.d(str, DateFormatUtil.b(j), new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepSourceListActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    LogUtil.h("SCUI_DayStepSourceListActivity", "getDayStepDataById objData is null");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.obj = obj;
                DayStepSourceListActivity.this.d.sendMessage(obtain);
            }
        });
    }

    private PageModelArgs b() {
        PageModelArgs pageModelArgs = new PageModelArgs();
        pageModelArgs.setDataSource(3);
        pageModelArgs.setPageType(100);
        return pageModelArgs;
    }

    private PrivacyDataModel d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("SCUI_DayStepSourceListActivity", "buildPrivacyDataModel hiHealthData is null");
            return null;
        }
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        privacyDataModel.setStartTime(hiHealthData.getStartTime());
        privacyDataModel.setEndTime(hiHealthData.getEndTime());
        privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
        privacyDataModel.setClientId(hiHealthData.getClientId());
        privacyDataModel.setIntValue(hiHealthData.getIntValue());
        return privacyDataModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list) {
        this.j = list;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                double value = hiHealthData.getValue();
                long startTime = hiHealthData.getStartTime();
                long endTime = hiHealthData.getEndTime();
                pwc pwcVar = new pwc();
                pwcVar.a(e(startTime, endTime));
                pwcVar.e(d((int) value));
                this.e.add(pwcVar);
            }
        }
        if (koq.b(this.e)) {
            LogUtil.a("SCUI_DayStepSourceListActivity", "refreshStepSourceList mList is empty");
            g();
        } else {
            this.c.notifyDataSetChanged();
        }
    }

    private String e(long j, long j2) {
        return getString(R$string.IDS_hw_pressure_grade_range, new Object[]{DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE), DateFormatUtil.d(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f.setVisibility(0);
        this.f9895a.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.f.setVisibility(8);
        this.f9895a.setVisibility(0);
    }

    static class d extends BaseHandler<DayStepSourceListActivity> {
        d(DayStepSourceListActivity dayStepSourceListActivity) {
            super(dayStepSourceListActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dud_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DayStepSourceListActivity dayStepSourceListActivity, Message message) {
            if (message == null || message.what != 100) {
                LogUtil.h("SCUI_DayStepSourceListActivity", "handleMessageWhenReferenceNotNull message.what is not MSG_REQUEST_STEP_POINT_DATA");
                dayStepSourceListActivity.g();
                return;
            }
            Object obj = message.obj;
            if (!(obj instanceof List)) {
                LogUtil.h("SCUI_DayStepSourceListActivity", "handleMessageWhenReferenceNotNull obj is not instanceof List");
                dayStepSourceListActivity.g();
                return;
            }
            List list = (List) obj;
            if (!koq.b(list)) {
                dayStepSourceListActivity.e();
                dayStepSourceListActivity.b(list);
            } else {
                LogUtil.a("SCUI_DayStepSourceListActivity", "handleMessageWhenReferenceNotNull list is null or empty");
                dayStepSourceListActivity.g();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        d dVar = this.d;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
