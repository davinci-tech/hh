package com.huawei.hms.feature.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.hms.feature.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    public static final int STATUS_ONCREATED = 1;
    public static final int STATUS_ONCREATEVIEW = 2;
    public static final int STATUS_ONINFLATE = 0;
    public static final int STATUS_ONRESUME = 5;
    public static final int STATUS_ONSTART = 4;

    /* renamed from: a, reason: collision with root package name */
    private static final String f4484a = "DeferredLifecycleHelper";
    private T b;
    private Bundle c;
    private LinkedList<g> d;
    private OnDelegateCreatedListener<T> e = new a();

    public interface g {
        int a();

        void a(LifecycleDelegate lifecycleDelegate);
    }

    public abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public void onStop() {
        T t = this.b;
        if (t != null) {
            t.onStop();
        } else {
            a(4);
        }
    }

    public void onStart() {
        a((Bundle) null, new e());
    }

    public void onSaveInstanceState(Bundle bundle) {
        T t = this.b;
        if (t != null) {
            t.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public void onResume() {
        a((Bundle) null, new f());
    }

    public void onPause() {
        T t = this.b;
        if (t != null) {
            t.onPause();
        } else {
            a(5);
        }
    }

    public void onLowMemory() {
        T t = this.b;
        if (t != null) {
            t.onLowMemory();
        }
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        a(bundle2, new b(activity, bundle, bundle2));
    }

    public void onDestroyView() {
        T t = this.b;
        if (t != null) {
            t.onDestroyView();
        } else {
            a(1);
        }
    }

    public void onDestroy() {
        T t = this.b;
        if (t != null) {
            t.onDestroy();
        } else {
            a(0);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        a(bundle, new d(frameLayout, layoutInflater, viewGroup, bundle));
        return frameLayout;
    }

    public void onCreate(Bundle bundle) {
        a(bundle, new c(bundle));
    }

    public T getDelegate() {
        return this.b;
    }

    private void a(Bundle bundle, g gVar) {
        T t = this.b;
        if (t != null) {
            gVar.a(t);
            return;
        }
        if (this.d == null) {
            this.d = new LinkedList<>();
        }
        this.d.add(gVar);
        if (bundle != null) {
            Bundle bundle2 = this.c;
            if (bundle2 == null) {
                Object clone = bundle.clone();
                if (clone != null && (clone instanceof Bundle)) {
                    this.c = (Bundle) clone;
                }
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.e);
    }

    private void a(int i) {
        while (!this.d.isEmpty() && this.d.getLast().a() >= i) {
            this.d.removeLast();
        }
    }

    public class b implements g {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4486a;
        public final /* synthetic */ Bundle b;
        public final /* synthetic */ Bundle c;

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public int a() {
            return 0;
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public void a(LifecycleDelegate lifecycleDelegate) {
            DeferredLifecycleHelper.this.b.onInflate(this.f4486a, this.b, this.c);
        }

        public b(Activity activity, Bundle bundle, Bundle bundle2) {
            this.f4486a = activity;
            this.b = bundle;
            this.c = bundle2;
        }
    }

    public class c implements g {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f4487a;

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public int a() {
            return 1;
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public void a(LifecycleDelegate lifecycleDelegate) {
            Log.d(DeferredLifecycleHelper.f4484a, "IDelegateLifeCycleCall onCreate:");
            lifecycleDelegate.onCreate(this.f4487a);
        }

        public c(Bundle bundle) {
            this.f4487a = bundle;
        }
    }

    public class d implements g {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ FrameLayout f4488a;
        public final /* synthetic */ LayoutInflater b;
        public final /* synthetic */ ViewGroup c;
        public final /* synthetic */ Bundle d;

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public int a() {
            return 2;
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public void a(LifecycleDelegate lifecycleDelegate) {
            this.f4488a.removeAllViews();
            this.f4488a.addView(DeferredLifecycleHelper.this.b.onCreateView(this.b, this.c, this.d));
        }

        public d(FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            this.f4488a = frameLayout;
            this.b = layoutInflater;
            this.c = viewGroup;
            this.d = bundle;
        }
    }

    public class e implements g {
        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public int a() {
            return 4;
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public void a(LifecycleDelegate lifecycleDelegate) {
            Log.d(DeferredLifecycleHelper.f4484a, "IDelegateLifeCycleCall onStart:");
            lifecycleDelegate.onStart();
        }

        public e() {
        }
    }

    public class f implements g {
        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public int a() {
            return 5;
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper.g
        public void a(LifecycleDelegate lifecycleDelegate) {
            Log.d(DeferredLifecycleHelper.f4484a, "IDelegateLifeCycleCall onResume:");
            lifecycleDelegate.onResume();
        }

        public f() {
        }
    }

    public class a implements OnDelegateCreatedListener<T> {
        @Override // com.huawei.hms.feature.dynamic.OnDelegateCreatedListener
        public void onDelegateCreated(T t) {
            DeferredLifecycleHelper.this.b = t;
            Iterator it = DeferredLifecycleHelper.this.d.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(DeferredLifecycleHelper.this.b);
            }
            DeferredLifecycleHelper.this.d.clear();
            DeferredLifecycleHelper.this.c = null;
        }

        public a() {
        }
    }
}
