package com.huawei.skinner.base;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentResultOwner;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.skinner.internal.IDynamicNewView;
import defpackage.ncg;
import defpackage.ndd;
import java.util.List;

/* loaded from: classes9.dex */
public class SkinBaseFragment extends Fragment implements IDynamicNewView {
    private IDynamicNewView b;
    private boolean c = true;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDynamicNewView) {
            this.b = (IDynamicNewView) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!this.c) {
            Fragment parentFragment = getParentFragment();
            boolean z = false;
            boolean z2 = parentFragment == null || !(parentFragment instanceof SkinBaseFragment) || ((SkinBaseFragment) parentFragment).c;
            FragmentActivity activity = getActivity();
            boolean isSkinEnable = activity instanceof SkinBaseFragmentActivity ? ((SkinBaseFragmentActivity) activity).isSkinEnable() : true;
            boolean z3 = parentFragment != null && z2;
            if (parentFragment == null && isSkinEnable) {
                z = true;
            }
            if (z3 || z) {
                view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, getActivity().getTheme()));
            }
        }
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, String str, int i) {
        IDynamicNewView iDynamicNewView;
        if (!this.c || (iDynamicNewView = this.b) == null) {
            return;
        }
        iDynamicNewView.dynamicAddSkinableView(view, str, i);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, List<ncg> list) {
        IDynamicNewView iDynamicNewView;
        if (!this.c || (iDynamicNewView = this.b) == null) {
            return;
        }
        iDynamicNewView.dynamicAddSkinableView(view, list);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        if (!e()) {
            FragmentResultOwner childFragmentManager = getChildFragmentManager();
            if (childFragmentManager instanceof LayoutInflater.Factory2) {
                LayoutInflater cloneInContext = getActivity().getLayoutInflater().cloneInContext(getContext());
                ndd.a(LayoutInflater.class, cloneInContext, "mFactory", null);
                ndd.a(LayoutInflater.class, cloneInContext, "mFactory2", null);
                LayoutInflaterCompat.setFactory2(cloneInContext, (LayoutInflater.Factory2) childFragmentManager);
                return cloneInContext;
            }
        }
        return super.onGetLayoutInflater(bundle);
    }

    public boolean e() {
        return this.c;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
