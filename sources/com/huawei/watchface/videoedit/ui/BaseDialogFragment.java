package com.huawei.watchface.videoedit.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import java.util.Objects;

/* loaded from: classes9.dex */
public abstract class BaseDialogFragment extends DialogFragment {
    protected Dialog mDialog;
    protected int mRotation;

    public abstract void setRotation(int i);

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        this.mDialog = onCreateDialog;
        ((Window) Objects.requireNonNull(onCreateDialog.getWindow())).requestFeature(1);
        return this.mDialog;
    }

    public void showDialog(FragmentManager fragmentManager, int i) {
        this.mRotation = i;
        super.show(fragmentManager, getClass().getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
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
