package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.adapter.SportAllCourseListAdapter;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.SportAllCourseListFragment;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import defpackage.ary;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class SportAllCourseListFragment extends PolymericDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<String> f3137a;
    private LinearLayout b;
    private boolean c;
    private OnFitnessStatusChangeCallback d = new OnFitnessStatusChangeCallback() { // from class: fmz
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            SportAllCourseListFragment.this.d();
        }
    };
    private boolean e;
    private HealthToolBar j;

    public /* synthetic */ void d() {
        if (this.mDataViewModel == null) {
            LogUtil.h("Suggestion_SportAllCourseListFragment", "mCustomCourseStatusChangeCallback, mAllCourseViewModel is null.");
            return;
        }
        if (TextUtils.equals(this.mDataViewModel.d(), "RUNNING_COURSE") && this.mDataViewModel.c() == 9999) {
            LogUtil.a("Suggestion_SportAllCourseListFragment", "mWorkoutStatusChangeCallback is refreshView");
            getPolymericData(this.mDataViewModel.c(this.mDataViewModel.c()), new ArrayList(), false);
            return;
        }
        LogUtil.b("Suggestion_SportAllCourseListFragment", "pageType:", this.mDataViewModel.d(), " cur nav:", Integer.valueOf(this.mDataViewModel.c()));
    }

    public static SportAllCourseListFragment e(boolean z, ArrayList<String> arrayList, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("KEY_SUG_COURSE_ADD_STATUS", z);
        bundle.putStringArrayList("KEY_SUG_COURSE_ADD_IDS", arrayList);
        bundle.putBoolean("KEY_SUG_COURSE_REPLACE_STATUS", z2);
        SportAllCourseListFragment sportAllCourseListFragment = new SportAllCourseListFragment();
        sportAllCourseListFragment.setArguments(bundle);
        return sportAllCourseListFragment;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public View initView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View initView = super.initView(layoutInflater, viewGroup);
        this.b = (LinearLayout) initView.findViewById(R.id.plan_confirm_add_course);
        final HealthButton healthButton = (HealthButton) initView.findViewById(R.id.plan_confirm_add_course_btn);
        if (this.e || this.c) {
            this.b.setVisibility(0);
            healthButton.setEnabled(false);
        }
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.SportAllCourseListFragment.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                HealthButton healthButton2;
                if (objArr != null) {
                    Object obj = objArr[0];
                    if ((obj instanceof Boolean) && (healthButton2 = healthButton) != null) {
                        if (!Boolean.valueOf(obj.equals(Boolean.valueOf(healthButton2.isEnabled()))).booleanValue()) {
                            healthButton.setEnabled(((Boolean) objArr[0]).booleanValue());
                        } else {
                            LogUtil.a("Suggestion_SportAllCourseListFragment", "do not change the button status");
                        }
                    }
                }
            }
        }, "PLAN_CONFIRM_VISIBILITY");
        if (this.c) {
            healthButton.setText(R.string._2130848401_res_0x7f022a91);
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: fnb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportAllCourseListFragment.this.aBb_(view);
            }
        });
        aBa_(initView);
        return initView;
    }

    public /* synthetic */ void aBb_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public void showNoDataTips() {
        showTips((TextUtils.equals(this.mDataViewModel.d(), "RUNNING_COURSE") && this.mDataViewModel.c() == 1) ? R.string._2130848671_res_0x7f022b9f : R.string._2130848453_res_0x7f022ac5, R.drawable._2131429871_res_0x7f0b09ef);
    }

    private void aBa_(View view) {
        this.j = (HealthToolBar) view.findViewById(R.id.toolbar_new);
        this.j.cHc_(View.inflate(getContext(), R.layout.hw_toolbar_bottomview, null));
        this.j.setIcon(2, R.drawable._2131430269_res_0x7f0b0b7d);
        this.j.setIconTitle(2, getResources().getString(R.string._2130848664_res_0x7f022b98).toUpperCase(Locale.ENGLISH));
        this.j.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: fnc
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                SportAllCourseListFragment.this.a(i);
            }
        });
        this.j.setVisibility(8);
    }

    public /* synthetic */ void a(int i) {
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_SportAllCourseListFragment", "setOnSingleTapListener click button too fast.");
            return;
        }
        FragmentActivity activity = getActivity();
        if (i == 2 && activity != null && isAdded()) {
            Intent intent = new Intent();
            intent.setClass(activity, CustomCourseActivity.class);
            startActivity(intent);
        }
    }

    private void b() {
        if (this.mPolymericDataAdapter == null || !(this.mPolymericDataAdapter instanceof SportAllCourseListAdapter)) {
            LogUtil.h("Suggestion_SportAllCourseListFragment", "submitAddCourseInfo, mCourseAdapter is null.");
            return;
        }
        Intent intent = new Intent();
        SportAllCourseListAdapter sportAllCourseListAdapter = (SportAllCourseListAdapter) this.mPolymericDataAdapter;
        intent.putStringArrayListExtra("KEY_SUG_COURSE_IDS_RESULT", sportAllCourseListAdapter.d());
        intent.putStringArrayListExtra("KEY_SUG_COURSE_NAMES_RESULT", sportAllCourseListAdapter.a());
        intent.putExtra("KEY_SUG_COURSE_ADD_STATUS", this.e);
        intent.putExtra("KEY_SUG_COURSE_REPLACE_STATUS", this.c);
        JsonObject jsonObject = new JsonObject();
        final JsonArray jsonArray = new JsonArray();
        sportAllCourseListAdapter.d().forEach(new Consumer() { // from class: fnf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                JsonArray.this.add((String) obj);
            }
        });
        jsonObject.add("KEY_SUG_COURSE_IDS_RESULT", jsonArray);
        final JsonArray jsonArray2 = new JsonArray();
        sportAllCourseListAdapter.a().forEach(new Consumer() { // from class: fnf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                JsonArray.this.add((String) obj);
            }
        });
        jsonObject.add("KEY_SUG_COURSE_NAMES_RESULT", jsonArray2);
        jsonObject.addProperty("KEY_SUG_COURSE_ADD_STATUS", Boolean.valueOf(this.e));
        jsonObject.addProperty("KEY_SUG_COURSE_REPLACE_STATUS", Boolean.valueOf(this.c));
        intent.putExtra("result", jsonObject.toString());
        requireActivity().setResult(-1, intent);
        requireActivity().finish();
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public void getPolymericData(Navigation navigation, List<FilterCondition> list, boolean z) {
        HealthToolBar healthToolBar = this.j;
        if (healthToolBar != null) {
            healthToolBar.setVisibility(navigation.getValue() == 9999 ? 0 : 8);
        }
        super.getPolymericData(navigation, list, z);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public void initArgument() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a("Suggestion_SportAllCourseListFragment", "initArgument, arguments is null.");
            return;
        }
        this.e = arguments.getBoolean("KEY_SUG_COURSE_ADD_STATUS", false);
        this.c = arguments.getBoolean("KEY_SUG_COURSE_REPLACE_STATUS", false);
        try {
            this.f3137a = arguments.getStringArrayList("KEY_SUG_COURSE_ADD_IDS");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("Suggestion_SportAllCourseListFragment", "get addCourseId list fail");
        }
        ary.a().e(this.d, "CUSTOM_COURSE_UPDATE");
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public PolymericDataAdapter newPolymericDataAdapter() {
        Navigation c = this.mDataViewModel.c(this.mDataViewModel.c());
        return new SportAllCourseListAdapter(new ArrayList(), this.mRecycleViewFitWorks, R.layout.sug_item_sport_all_course_list, this.e, this.f3137a, this.mDataViewModel.d(), c != null ? c.getName() : "", this.c);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ary.a().c(this.d, "CUSTOM_COURSE_UPDATE");
        this.d = null;
        ObserverManagerUtil.e("PLAN_CONFIRM_VISIBILITY");
    }
}
