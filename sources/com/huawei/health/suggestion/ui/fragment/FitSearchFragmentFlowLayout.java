package com.huawei.health.suggestion.ui.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.FlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import java.util.LinkedList;

/* loaded from: classes8.dex */
public class FitSearchFragmentFlowLayout extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private FowAdapter f3230a;
    private FlowLayout b;

    public interface FowAdapter {
        LinkedList<String> getShowData();

        void saveShowData(String str);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_fit_search_flow, viewGroup, false);
        aFP_(inflate);
        return inflate;
    }

    private void aFP_(View view) {
        this.b = (FlowLayout) view.findViewById(R.id.search_flowlayout);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void d(FlowLayout.OnTextClickListener onTextClickListener) {
        this.b.setTextOnClickListener(onTextClickListener);
    }

    public void e(FowAdapter fowAdapter) {
        if (fowAdapter == null) {
            return;
        }
        this.f3230a = fowAdapter;
        this.b.c(getActivity(), fowAdapter.getShowData());
    }

    public void aFQ_(Fragment fragment) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        FragmentTransaction beginTransaction = fragment.getFragmentManager().beginTransaction();
        beginTransaction.hide(this);
        beginTransaction.commitAllowingStateLoss();
    }

    public void aFR_(Fragment fragment) {
        if (this.f3230a != null && getActivity() != null) {
            this.b.c(getActivity(), this.f3230a.getShowData());
        }
        if (getActivity() == null || !isAdded()) {
            return;
        }
        FragmentTransaction beginTransaction = fragment.getFragmentManager().beginTransaction();
        beginTransaction.show(this);
        beginTransaction.commitAllowingStateLoss();
    }

    public void d(String str) {
        FowAdapter fowAdapter = this.f3230a;
        if (fowAdapter != null) {
            fowAdapter.saveShowData(str);
        }
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
