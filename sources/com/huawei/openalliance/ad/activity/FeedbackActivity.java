package com.huawei.openalliance.ad.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.huawei.android.widget.ToastEx;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.ads.AdFeedbackListener;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.cu;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.hh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ad;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.feedback.FeedbackView;
import com.huawei.openalliance.ad.views.h;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class FeedbackActivity extends com.huawei.openalliance.ad.activity.a implements com.huawei.openalliance.ad.views.feedback.b, h.a {
    private static WeakReference<Context> w;
    private ContentRecord q;
    private com.huawei.openalliance.ad.inter.data.e r;
    private qq s;
    private cu v;
    private int y;
    private static Map<Integer, AdFeedbackListener> t = new HashMap();
    private static Map<Integer, AdFeedbackListener> u = new HashMap();
    private static SecureRandom x = new SecureRandom();
    private static WeakReference<View> A = null;
    private static int[] B = null;
    private boolean z = false;
    private hh C = null;

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.e
    protected void a() {
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected int d() {
        return R.layout.hiad_activity_feedback;
    }

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onDestroy() {
        View view;
        super.onDestroy();
        dc.a((com.huawei.openalliance.ad.inter.data.e) null);
        ho.b("FeedbackActivity", "onDestroy %s, instance: %s", Integer.valueOf(hashCode()), Integer.valueOf(this.y));
        a(this.y);
        if (this.l != null) {
            this.l.setScreenLockCallBack(null);
        }
        WeakReference<View> weakReference = A;
        if (weakReference == null || (view = weakReference.get()) == null || this.C == null) {
            return;
        }
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this.C);
    }

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onCreate(Bundle bundle) {
        View view;
        try {
            super.onCreate(bundle);
            WeakReference<View> weakReference = A;
            if (weakReference != null && (view = weakReference.get()) != null) {
                this.C = new hh(this, view, B);
                view.getViewTreeObserver().addOnGlobalLayoutListener(this.C);
            }
            if (Constants.MAGEZINE_PKG_NAME.equalsIgnoreCase(getApplicationContext().getPackageName())) {
                if (Build.VERSION.SDK_INT >= 27) {
                    setShowWhenLocked(true);
                } else {
                    ho.c("FeedbackActivity", "api is too low to support showWhenLocked.");
                }
            }
            if (dd.b((Context) this)) {
                this.z = true;
            }
            m();
            com.huawei.openalliance.ad.analysis.h hVar = new com.huawei.openalliance.ad.analysis.h(this);
            this.v = hVar;
            hVar.f(this.q, "0");
            ou ouVar = new ou(this, sc.a(this, this.q.e()));
            this.s = ouVar;
            ouVar.a(this.q);
            WeakReference<Context> weakReference2 = w;
            if (weakReference2 != null) {
                dd.a(this, weakReference2.get());
            }
            try {
                getWindow().clearFlags(1048576);
            } catch (Throwable th) {
                ho.c("FeedbackActivity", "clear flag err: %s.", th.getClass().getSimpleName());
            }
            ho.b("FeedbackActivity", "onCreate: %s, instance: %s", Integer.valueOf(hashCode()), Integer.valueOf(this.y));
        } catch (Throwable th2) {
            ho.c("FeedbackActivity", "init error: %s", th2.getClass().getSimpleName());
            a(u.get(Integer.valueOf(this.y)));
            finish();
        }
    }

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.views.h.a
    public void l() {
        ho.b("FeedbackActivity", "onLockScreenOpen.");
        try {
            Task<Boolean> a2 = HiAd.getInstance(this).getAskForUnlockScreen().a(this);
            ho.b("FeedbackActivity", "task is set.");
            c cVar = new c(this, this.l);
            a aVar = new a();
            b bVar = new b();
            a2.addOnSuccessListener(cVar);
            a2.addOnCanceledListener(aVar);
            a2.addOnFailureListener(bVar);
        } catch (NullPointerException unused) {
            ho.c("FeedbackActivity", "get task err, NullPointerException");
        } catch (Throwable th) {
            ho.c("FeedbackActivity", "listen task err: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected boolean i() {
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.r = (com.huawei.openalliance.ad.inter.data.e) safeIntent.getSerializableExtra(MapKeyNames.NATIVE_AD);
        this.y = safeIntent.getIntExtra(MapKeyNames.INSTANCE_ID, 0);
        com.huawei.openalliance.ad.inter.data.e eVar = this.r;
        ContentRecord m = eVar == null ? dc.m() : pd.a(eVar);
        this.q = m;
        if (m == null) {
            return false;
        }
        return super.i();
    }

    @Override // com.huawei.openalliance.ad.activity.a
    public void e() {
        f();
        this.l.a(this.h, this.i);
        this.l.setAdContent(this.q);
        this.l.setFeedbackListener(this);
        this.l.setScreenLockCallBack(this);
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected void c() {
        this.e = (RelativeLayout) findViewById(R.id.feedback_activity_root);
        this.f = findViewById(R.id.margin_view);
        this.g = findViewById(R.id.feedback_anchor_view);
        this.j = (FeedbackView) findViewById(R.id.top_feedback_view);
        this.m = (ImageView) findViewById(R.id.top_feedback_iv);
        this.k = (FeedbackView) findViewById(R.id.bottom_feedback_view);
        this.n = (ImageView) findViewById(R.id.bottom_feedback_iv);
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected void b() {
        a(u.get(Integer.valueOf(this.y)));
    }

    @Override // com.huawei.openalliance.ad.views.feedback.b
    public void a(int i, FeedbackInfo feedbackInfo) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            arrayList.add(feedbackInfo);
        } catch (Throwable th) {
            ho.c("FeedbackActivity", "itemClickAction error: %s", th.getClass().getSimpleName());
        }
        if (i != 1) {
            if (i == 2) {
                a(arrayList);
            } else if (i != 3) {
                ho.a("FeedbackActivity", "invalid feedback type");
            }
            finish();
        }
        a(i, arrayList);
        finish();
    }

    private void m() {
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.FeedbackActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FeedbackActivity.this.v != null) {
                    FeedbackActivity.this.v.f(FeedbackActivity.this.q, "3");
                }
                FeedbackActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(List<FeedbackInfo> list) {
        Toast makeText = Toast.makeText(getApplicationContext(), R.string._2130851093_res_0x7f023515, 0);
        if (this.z) {
            a(makeText);
        } else {
            makeText.show();
        }
        qq qqVar = this.s;
        if (qqVar != null) {
            qqVar.a(list);
        }
        cu cuVar = this.v;
        if (cuVar != null) {
            cuVar.f(this.q, "1");
        }
        AdFeedbackListener adFeedbackListener = t.get(Integer.valueOf(this.y));
        AdFeedbackListener adFeedbackListener2 = u.get(Integer.valueOf(this.y));
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(adFeedbackListener != null);
        objArr[1] = Boolean.valueOf(adFeedbackListener2 != null);
        ho.b("FeedbackActivity", "pos, innerListener: %s, listener: %s", objArr);
        if (adFeedbackListener != null) {
            adFeedbackListener.onAdLiked();
        }
        if (adFeedbackListener2 != null) {
            adFeedbackListener2.onAdLiked();
        }
    }

    private static void a(com.huawei.openalliance.ad.views.feedback.c cVar, View view) {
        if (cVar.d() != null) {
            w = new WeakReference<>(cVar.d());
        } else {
            w = new WeakReference<>(view.getContext());
        }
    }

    private static void a(AdFeedbackListener adFeedbackListener) {
        if (adFeedbackListener != null) {
            adFeedbackListener.onAdFeedbackShowFailed();
        }
    }

    private void a(Toast toast) {
        try {
            if (toast == null) {
                ho.b("FeedbackActivity", "toast is null.");
                return;
            }
            WindowManager.LayoutParams windowParams = ToastEx.getWindowParams(toast);
            if (windowParams == null) {
                ho.b("FeedbackActivity", "params is null.");
            } else {
                windowParams.flags |= 524288;
                toast.show();
            }
        } catch (Throwable th) {
            ho.c("FeedbackActivity", "show toast err: %s.", th.getClass().getSimpleName());
        }
    }

    public static void a(Context context, com.huawei.openalliance.ad.views.feedback.c cVar) {
        if (cVar == null) {
            return;
        }
        if (ad.a()) {
            ho.b("FeedbackActivity", "fast click");
            return;
        }
        AdFeedbackListener c2 = cVar.c();
        com.huawei.openalliance.ad.inter.data.e l = dc.l();
        if (l == null || cVar.a() == null || !ad.a(l.getFeedbackInfoList())) {
            ho.d("FeedbackActivity", "startFeedbackActivity fail: invalid parameter.");
            a(c2);
            return;
        }
        int nextInt = x.nextInt(Integer.MAX_VALUE);
        t.put(Integer.valueOf(nextInt), cVar.b());
        u.put(Integer.valueOf(nextInt), c2);
        try {
            View a2 = cVar.a();
            A = new WeakReference<>(a2);
            int[] iArr = new int[2];
            a(cVar, a2);
            a2.getLocationInWindow(iArr);
            ho.b("FeedbackActivity", "startFeedbackActivity, anchorView.getLocationInWindow [x,y]= %d, %d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
            int[] iArr2 = new int[2];
            a2.getLocationOnScreen(iArr2);
            B = iArr2;
            int[] iArr3 = {a2.getMeasuredWidth(), a2.getMeasuredHeight()};
            Intent intent = new Intent(context, (Class<?>) FeedbackActivity.class);
            intent.putExtra(MapKeyNames.ANCHOR_LOCATION, iArr);
            intent.putExtra(MapKeyNames.ANCHOR_SIZE, iArr3);
            intent.setFlags(65536);
            intent.putExtra(MapKeyNames.NATIVE_AD, l);
            intent.putExtra(MapKeyNames.INSTANCE_ID, nextInt);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
        } catch (Throwable th) {
            ho.c("FeedbackActivity", "startFeedbackActivity error: %s", th.getClass().getSimpleName());
            a(c2);
            a(nextInt);
        }
    }

    private void a(int i, List<FeedbackInfo> list) {
        Toast makeText = Toast.makeText(getApplicationContext(), R.string._2130851094_res_0x7f023516, 0);
        if (this.z) {
            a(makeText);
        } else {
            makeText.show();
        }
        qq qqVar = this.s;
        if (qqVar != null) {
            qqVar.b(list);
        }
        cu cuVar = this.v;
        if (cuVar != null) {
            cuVar.f(this.q, 1 == i ? "2" : "4");
        }
        AdFeedbackListener adFeedbackListener = t.get(Integer.valueOf(this.y));
        AdFeedbackListener adFeedbackListener2 = u.get(Integer.valueOf(this.y));
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(adFeedbackListener != null);
        objArr[1] = Boolean.valueOf(adFeedbackListener2 != null);
        ho.b("FeedbackActivity", "neg, innerListener: %s, listener: %s", objArr);
        if (adFeedbackListener != null) {
            adFeedbackListener.onAdDisliked();
        }
        if (adFeedbackListener2 != null) {
            adFeedbackListener2.onAdDisliked();
        }
    }

    static class a implements OnCanceledListener {
        @Override // com.huawei.hmf.tasks.OnCanceledListener
        public void onCanceled() {
            ho.c("FeedbackActivity", "unlock screen canceled.");
        }

        private a() {
        }
    }

    static class b implements OnFailureListener {
        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            ho.c("FeedbackActivity", "unlock fail, exception is %s.", exc.getMessage());
        }

        private b() {
        }
    }

    private static void a(int i) {
        t.remove(Integer.valueOf(i));
        u.remove(Integer.valueOf(i));
    }

    static class c implements OnSuccessListener<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<FeedbackActivity> f6567a;
        private WeakReference<h> b;

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            ho.b("FeedbackActivity", "unlock result is %s.", bool);
            FeedbackActivity feedbackActivity = this.f6567a.get();
            h hVar = this.b.get();
            if (bool.booleanValue()) {
                if (hVar != null) {
                    hVar.e();
                    return;
                } else {
                    ho.c("FeedbackActivity", "weak reference PPSBaseDialogContentView is null");
                    return;
                }
            }
            if (feedbackActivity == null) {
                ho.c("FeedbackActivity", "weak reference feedback activity is null.");
            } else {
                feedbackActivity.finish();
            }
        }

        public c(FeedbackActivity feedbackActivity, h hVar) {
            this.f6567a = new WeakReference<>(feedbackActivity);
            this.b = new WeakReference<>(hVar);
        }
    }
}
