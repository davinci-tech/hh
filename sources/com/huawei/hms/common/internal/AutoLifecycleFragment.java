package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.SparseArray;
import com.huawei.hms.api.HuaweiApiClient;

/* loaded from: classes4.dex */
public class AutoLifecycleFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private final SparseArray<a> f4443a = new SparseArray<>();
    private boolean b;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public final HuaweiApiClient f4444a;
        protected final int b;

        public a(int i, HuaweiApiClient huaweiApiClient) {
            this.f4444a = huaweiApiClient;
            this.b = i;
        }

        public void a() {
            this.f4444a.disconnect();
        }
    }

    public static AutoLifecycleFragment getInstance(Activity activity) {
        Preconditions.checkMainThread("Must be called on the main thread");
        try {
            AutoLifecycleFragment autoLifecycleFragment = (AutoLifecycleFragment) activity.getFragmentManager().findFragmentByTag("HmsAutoLifecycleFrag");
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (autoLifecycleFragment != null) {
                return autoLifecycleFragment;
            }
            AutoLifecycleFragment autoLifecycleFragment2 = new AutoLifecycleFragment();
            fragmentManager.beginTransaction().add(autoLifecycleFragment2, "HmsAutoLifecycleFrag").commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            return autoLifecycleFragment2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag HmsAutoLifecycleFrag is not a AutoLifecycleFragment", e);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.b = true;
        for (int i = 0; i < this.f4443a.size(); i++) {
            this.f4443a.valueAt(i).f4444a.connect((Activity) null);
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.b = false;
        for (int i = 0; i < this.f4443a.size(); i++) {
            this.f4443a.valueAt(i).f4444a.disconnect();
        }
    }

    public void startAutoMange(int i, HuaweiApiClient huaweiApiClient) {
        Preconditions.checkNotNull(huaweiApiClient, "HuaweiApiClient instance cannot be null");
        Preconditions.checkState(this.f4443a.indexOfKey(i) < 0, "Already managing a HuaweiApiClient with this clientId: " + i);
        this.f4443a.put(i, new a(i, huaweiApiClient));
        if (this.b) {
            huaweiApiClient.connect((Activity) null);
        }
    }

    public void stopAutoManage(int i) {
        a aVar = this.f4443a.get(i);
        this.f4443a.remove(i);
        if (aVar != null) {
            aVar.a();
        }
    }
}
