package com.huawei.health.suggestion.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import defpackage.nsn;

/* loaded from: classes8.dex */
public class FitnessSearchFragmentBar extends Fragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthSearchView f3232a;
    private OnQueryTextListener b;
    private View c;
    private Context d;
    private ImageView e;

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_fit_search_bar, viewGroup, false);
        if (getActivity() != null) {
            this.d = getActivity().getApplicationContext();
        }
        aFU_(inflate);
        c();
        return inflate;
    }

    private void aFU_(View view) {
        this.c = view.findViewById(R.id.statusbar_panel);
        HealthSearchView healthSearchView = (HealthSearchView) view.findViewById(R.id.course_search_view);
        this.f3232a = healthSearchView;
        nsn.a(healthSearchView, R.id.hwsearchview_search_src_icon, R.drawable._2131431370_res_0x7f0b0fca);
        if (this.d == null) {
            return;
        }
        ImageView imageView = (ImageView) this.f3232a.findViewById(R.id.hwsearchview_back_button);
        this.e = imageView;
        imageView.setOnClickListener(this);
        e();
    }

    private void c() {
        this.f3232a.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.4
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                FitnessSearchFragmentBar.this.b();
                FitnessSearchFragmentBar.this.b.onQueryTextSubmit(str);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                if (FitnessSearchFragmentBar.this.b != null) {
                    return FitnessSearchFragmentBar.this.b.onQueryTextChange(str);
                }
                return false;
            }
        });
        this.f3232a.setFocusable(true);
        this.f3232a.setFocusableInTouchMode(true);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        HealthSearchView healthSearchView = this.f3232a;
        if (healthSearchView != null) {
            healthSearchView.setOnQueryTextListener(null);
        }
        if (this.b != null) {
            this.b = null;
        }
    }

    private void e() {
        ConstraintLayout.LayoutParams b = b(this.d);
        View view = this.c;
        if (view != null) {
            view.setLayoutParams(b);
        }
    }

    public static ConstraintLayout.LayoutParams b(Context context) {
        int r = nsn.r(context);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-1, r);
        Activity activity = BaseApplication.getActivity();
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        if (activity == null) {
            return layoutParams;
        }
        boolean isInMultiWindowMode = activity.isInMultiWindowMode();
        boolean cLg_ = nsn.cLg_(activity);
        if (isInMultiWindowMode && cLg_) {
            layoutParams.topMargin = -r;
        }
        return layoutParams;
    }

    public void a() {
        this.f3232a.requestFocus();
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void aFV_(View.OnClickListener onClickListener) {
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void d(String str) {
        if (str != null) {
            b();
            this.b.onQueryTextSubmit(str);
            this.f3232a.setQuery(str, true);
        }
    }

    public void e(OnQueryTextListener onQueryTextListener) {
        this.b = onQueryTextListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.hwsearchview_back_button && getActivity() != null) {
            getActivity().finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Object systemService = getActivity().getApplicationContext().getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            ((InputMethodManager) systemService).hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void e(String str) {
        this.f3232a.setQueryHint(str);
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
