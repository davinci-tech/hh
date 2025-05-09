package com.huawei.hmf.services.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes9.dex */
public class ActivityResultFragment extends Fragment {
    private static final String REPORT_FRAGMENT_TAG = "com.huawei.hmf.report_fragment_tag";
    private static final String TAG = "ActivityResultFragment";
    Set<Integer> mTobeReleaseRequestCode = new HashSet();

    public static Fragment injectIfNeededIn(Activity activity, int i) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG);
        if (findFragmentByTag == null) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                findFragmentByTag = awaitCreateAndAddFragment(fragmentManager);
            } else {
                findFragmentByTag = createAndAddFragment(fragmentManager);
            }
        }
        if (findFragmentByTag instanceof ActivityResultFragment) {
            ((ActivityResultFragment) findFragmentByTag).mTobeReleaseRequestCode.add(Integer.valueOf(i));
        }
        return findFragmentByTag;
    }

    private static Fragment awaitCreateAndAddFragment(final FragmentManager fragmentManager) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        try {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hmf.services.ui.ActivityResultFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    Fragment findFragmentByTag = fragmentManager.findFragmentByTag(ActivityResultFragment.REPORT_FRAGMENT_TAG);
                    if (findFragmentByTag == null) {
                        findFragmentByTag = ActivityResultFragment.createAndAddFragment(fragmentManager);
                    }
                    atomicReference.set(findFragmentByTag);
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        } catch (InterruptedException unused) {
        }
        return (Fragment) atomicReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Fragment createAndAddFragment(FragmentManager fragmentManager) {
        ActivityResultFragment activityResultFragment;
        try {
            activityResultFragment = new ActivityResultFragment();
        } catch (Exception e) {
            e = e;
            activityResultFragment = null;
        }
        try {
            fragmentManager.beginTransaction().add(activityResultFragment, REPORT_FRAGMENT_TAG).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        } catch (Exception e2) {
            e = e2;
            Log.e(TAG, "create fragment failed." + e.getMessage());
            return activityResultFragment;
        }
        return activityResultFragment;
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mTobeReleaseRequestCode.remove(Integer.valueOf(i));
        Launcher.getLauncher().onActivityResult(getActivity(), i, i2, intent);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() == null || !getActivity().isFinishing()) {
            return;
        }
        Iterator<Integer> it = this.mTobeReleaseRequestCode.iterator();
        while (it.hasNext()) {
            Launcher.getLauncher().removeActivityCallback(it.next().intValue());
        }
        this.mTobeReleaseRequestCode.clear();
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
