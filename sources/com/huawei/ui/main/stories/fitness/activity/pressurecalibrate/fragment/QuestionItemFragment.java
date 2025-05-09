package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateResultActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateResultGuideActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.OptionsListAdapter;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.QuestionItemFragment;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.NoScrollListView;
import defpackage.gnm;
import defpackage.gyg;
import defpackage.nla;
import defpackage.nsn;
import defpackage.psm;
import defpackage.ptb;
import defpackage.ptt;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class QuestionItemFragment extends PressureQuestionFragment {
    private int c;
    private OptionsListAdapter d;
    private HealthButton f;
    private NoScrollListView g;
    private ptb h;
    private HealthButton i;
    private int j;
    private View k;
    private HealthTextView l;
    private boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9874a = false;
    private Intent e = null;

    @Override // com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureQuestionFragment
    protected int setContentView() {
        return R.layout.pressure_calibrate_viewpager_item;
    }

    public static QuestionItemFragment e(int i) {
        QuestionItemFragment questionItemFragment = new QuestionItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key_index", i);
        questionItemFragment.setArguments(bundle);
        return questionItemFragment;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureQuestionFragment
    protected void lazyLoad() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            int i = arguments.getInt("key_index");
            this.c = i;
            this.j = i + 1;
            List<ptb> q = ptt.d().q();
            if (i >= 0 && i < q.size()) {
                this.h = q.get(i);
            }
            LogUtil.a("PressureMeasureMessage", "QuestionItemFragment index = ", Integer.valueOf(i));
        }
        ((WindowManager) getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(new DisplayMetrics());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        View contentView = getContentView();
        HealthTextView healthTextView = (HealthTextView) contentView.findViewById(R.id.hw_pressure_calibrate_pager_question_number);
        spannableStringBuilder.append((CharSequence) UnitUtil.e(this.j, 1, 0)).append((CharSequence) "/").append((CharSequence) UnitUtil.e(ptt.d().q().size(), 1, 0));
        healthTextView.setText(spannableStringBuilder);
        LogUtil.a("PressureMeasureMessage", "pager index = ", Integer.valueOf(this.c));
        spannableStringBuilder.clear();
        spannableStringBuilder.append((CharSequence) UnitUtil.e(this.h.d(), 1, 0));
        if (LanguageUtil.ag(getContext())) {
            spannableStringBuilder.append((CharSequence) ".");
        } else {
            spannableStringBuilder.append((CharSequence) "、");
        }
        spannableStringBuilder.append((CharSequence) this.h.c());
        ((HealthTextView) contentView.findViewById(R.id.hw_pressure_calibrate_pager_question_description)).setText(spannableStringBuilder);
        this.i = (HealthButton) contentView.findViewById(R.id.hw_question_btn_change_pager);
        this.k = contentView.findViewById(R.id.hw_pressure_calibrate_view);
        this.f = (HealthButton) contentView.findViewById(R.id.hw_pressure_calibrate_pager_question_btn_submit);
        a();
        HealthTextView healthTextView2 = (HealthTextView) contentView.findViewById(R.id.hw_wifi_device_guide_pressure_calibrate_tv);
        this.l = healthTextView2;
        healthTextView2.setVisibility(8);
        b();
        h();
        d();
        e();
    }

    private void h() {
        int e = ptt.d().e(this.c + 1);
        if (e == -1) {
            LogUtil.h("PressureMeasureMessage", "selectedItem is none");
            return;
        }
        LogUtil.a("PressureMeasureMessage", "selectedItem is ", Integer.valueOf(e));
        this.g.setItemChecked(e, true);
        this.d.notifyDataSetChanged();
    }

    private void a() {
        this.i.setText(getString(R$string.IDS_hw_pressure_previous));
        int b = nla.b(4, 2);
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        layoutParams.width = b;
        this.i.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.f.getLayoutParams();
        layoutParams2.width = b;
        this.f.setLayoutParams(layoutParams2);
        int i = this.c;
        if (i == 0) {
            this.f.setText(getString(R$string.IDS_hw_pressure_next));
            this.i.setVisibility(8);
            this.k.setVisibility(8);
            this.f.setVisibility(this.f9874a ? 0 : 8);
            return;
        }
        if (i == ptt.d().q().size() - 1) {
            this.f.setText(R$string.IDS_hw_show_card_pressure_calibrate_answer_question_submit);
        } else {
            this.f.setText(getString(R$string.IDS_hw_pressure_next));
        }
        this.i.setVisibility(0);
        this.k.setVisibility(this.f9874a ? 0 : 8);
        this.f.setVisibility(this.f9874a ? 0 : 8);
    }

    private void b() {
        this.g = (NoScrollListView) getContentView().findViewById(R.id.hw_pressure_calibrate_pager_question_options);
        OptionsListAdapter optionsListAdapter = new OptionsListAdapter(getActivity(), this.h.b(), this.g);
        this.d = optionsListAdapter;
        this.g.setAdapter((ListAdapter) optionsListAdapter);
        this.g.setChoiceMode(1);
        this.g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.QuestionItemFragment$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                QuestionItemFragment.this.dtq_(adapterView, view, i, j);
            }
        });
    }

    /* synthetic */ void dtq_(AdapterView adapterView, View view, int i, long j) {
        this.f9874a = true;
        a("com.huawei.ui.pressure.question.next");
        LogUtil.a("PressureMeasureMessage", "getQuestionId = ", Integer.valueOf(this.h.d()));
        if (j >= 0 && j < this.h.b().size()) {
            int i2 = (int) j;
            LogUtil.a("PressureMeasureMessage", "id name = ", this.h.b().get(i2).a());
            ptt.d().a(this.h.d(), this.h.b().get(i2).a());
        }
        if (this.h.d() == ptt.d().q().size()) {
            this.b = true;
            this.k.setVisibility(0);
            this.f.setVisibility(0);
            if ("wifi_device".equals(ptt.d().n())) {
                this.l.setText(String.format(getContext().getResources().getString(R$string.IDS_device_wifi_pressure_calibrate_question_prompt), "10"));
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(8);
            }
        }
        this.d.notifyDataSetChanged();
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private void d() {
        this.i.setOnClickListener(new View.OnClickListener() { // from class: ptp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuestionItemFragment.this.dtr_(view);
            }
        });
    }

    public /* synthetic */ void dtr_(View view) {
        if (nsn.a(500)) {
            LogUtil.h("PressureMeasureMessage", "mPreviousPageBtn fast click");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a("com.huawei.ui.pressure.question.previous");
            this.d.notifyDataSetChanged();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: ptr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuestionItemFragment.this.dts_(view);
            }
        });
    }

    public /* synthetic */ void dts_(View view) {
        if (nsn.a(500)) {
            LogUtil.h("PressureMeasureMessage", "mSubmitBtn fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.h.d() != ptt.d().q().size()) {
            a("com.huawei.ui.pressure.question.next");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.b) {
            this.b = false;
            int k = (int) ptt.d().k();
            ptt.d().b(k);
            LogUtil.a("PressureMeasureMessage", "Answer question score = ", Integer.valueOf(k));
            ptt.d().b(System.currentTimeMillis());
            if (ptt.d().b() == 0) {
                ptt.d().b(System.currentTimeMillis());
            }
            c();
            psm.e().e(true);
            ptt.d().i(false);
            String n = ptt.d().n();
            String r = ptt.d().r();
            if ("wifi_device".equals(n)) {
                if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false) && !gyg.b(getContext())) {
                    i();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                String p = ptt.d().p();
                Intent intent = new Intent(getContext(), (Class<?>) WifiDevicePressureCalibrateResultGuideActivity.class);
                this.e = intent;
                intent.putExtra("health_wifi_device_productId", p);
                this.e.putExtra("wifi_device_calibrate_score", k);
                this.e.putExtra("health_wifi_device_userId", r);
                gnm.aPB_(getContext(), this.e);
                getActivity().finish();
            } else {
                j();
            }
        }
        this.d.notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        Context context = getContext();
        if (context == null) {
            ReleaseLogUtil.d("PressureMeasureMessage", "gotoNextPage context == null");
            return;
        }
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void c() {
        if (ptt.d().b() - ptt.d().t() > 60000) {
            ptt.d().b(true);
            if (psm.e().j()) {
                return;
            }
            psm.e().c(2, false);
        }
    }

    private void j() {
        Intent intent = new Intent(getContext(), (Class<?>) PressureCalibrateResultActivity.class);
        this.e = intent;
        intent.putExtra("pressure_is_have_datas", ptt.d().g());
        this.e.putExtra("from_health_record", ptt.d().c());
        this.e.putExtra("press_auto_monitor", ptt.d().i());
        this.e.putExtra(ArkUIXConstants.FROM_TYPE, ptt.d().a());
        gnm.aPB_(getContext(), this.e);
        getActivity().finish();
    }

    private void i() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(getContext());
        builder.b(getContext().getString(R$string.IDS_hw_health_show_common_dialog_title)).e(getContext().getString(R$string.IDS_hw_show_set_about_privacy_connectting_error)).cyV_(getContext().getString(R$string.IDS_hw_pressure_known), new View.OnClickListener() { // from class: ptm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuestionItemFragment.this.dtt_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    public /* synthetic */ void dtt_(View view) {
        getActivity().finish();
        ViewClickInstrumentation.clickOnView(view);
    }
}
