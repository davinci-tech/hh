package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.suggestion.model.FitnessShareRecord;
import com.huawei.health.suggestion.ui.view.share.FitnessShareNewDetailView;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.fdu;
import defpackage.fdz;
import defpackage.feb;
import defpackage.feh;
import defpackage.fjh;
import defpackage.gge;
import defpackage.ixx;
import defpackage.jcu;
import defpackage.jdv;
import defpackage.koq;
import defpackage.moe;
import defpackage.nsy;
import defpackage.sqc;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/* loaded from: classes4.dex */
public class FitnessResultInteractor {
    private static final int[] b = {R$drawable.fitness_background_1, R$drawable.share_geometry_1, R$drawable.share_geometry_2, R$drawable.share_geometry_3};
    private ArrayList<View> aa;
    private float ad;
    private String ae;
    private d c;
    private Activity e;
    private float f;
    private int g;
    private String h;
    private Context i;
    private int k;
    private boolean l;
    private int m;
    private String n;
    private long o;
    private FitnessShareNewDetailView p;
    private int q;
    private String s;
    private float t;
    private File u;
    private View v;
    private RelativeLayout w;
    private feh x;
    private HealthViewPager z;
    private int j = 0;
    private int r = 0;

    /* renamed from: a, reason: collision with root package name */
    private int[] f3119a = {5};
    private ArrayList<Integer> d = new ArrayList<>(16);
    private CommonDialog21 ab = null;
    private Handler y = new c(Looper.getMainLooper(), this);
    private SocialShareCenterApi ac = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);

    public FitnessResultInteractor(Context context) {
        this.i = null;
        this.i = context;
    }

    public void aAN_(Activity activity, Bundle bundle) {
        if (bundle == null || this.i == null) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "onCreate ", "bundle is null or mContext is null");
            return;
        }
        a(activity.getResources().getString(R$string.IDS_motiontrack_common_ui_waiting));
        this.e = activity;
        this.s = bundle.getString("train_name", "--");
        this.ad = bundle.getFloat("train_duration", 0.0f);
        this.f = bundle.getFloat("calorie", 0.0f);
        this.t = bundle.getFloat("percent", 0.0f);
        this.q = bundle.getInt("levels_count", 0);
        this.o = bundle.getLong("exercise_current_time", System.currentTimeMillis());
        this.j = bundle.getInt("entrance", 0);
        this.ae = bundle.getString("workoutid", null);
        this.k = bundle.getInt("action_count", 0);
        this.m = bundle.getInt("exercise_count", 0);
        this.g = bundle.getInt(DeviceCategoryFragment.DEVICE_TYPE, 0);
        this.h = DateUtils.formatDateTime(this.i.getApplicationContext(), this.o, 21);
        aAL_(activity);
        View view = this.v;
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessResultInteractor.3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    LogUtil.a("Suggestion_FitnessResultInteractor", "onGlobalLayout");
                    FitnessResultInteractor.this.y.sendEmptyMessage(1);
                    FitnessResultInteractor.this.v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        if (this.g == 3) {
            this.r = bundle.getInt("product_id", 0);
        }
        this.n = bundle.getString("isAICourse");
    }

    static class c extends BaseHandler<FitnessResultInteractor> {
        public c(Looper looper, FitnessResultInteractor fitnessResultInteractor) {
            super(looper, fitnessResultInteractor);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aAO_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(FitnessResultInteractor fitnessResultInteractor, Message message) {
            if (message.what != 1) {
                return;
            }
            fitnessResultInteractor.g();
            fitnessResultInteractor.d();
        }
    }

    private void aAL_(Activity activity) {
        if (activity == null) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "initShareView ", "activity is null");
            return;
        }
        this.w = (RelativeLayout) nsy.cMc_(activity, R.id.share_viewpager_layout);
        this.aa = new ArrayList<>(1);
        f();
        HealthViewPager healthViewPager = (HealthViewPager) nsy.cMc_(activity, R.id.view_pager);
        this.z = healthViewPager;
        healthViewPager.setOffscreenPageLimit(1);
        this.z.setPageTransformer(true, new HealthViewPager.PageTransformer() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessResultInteractor.1
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.PageTransformer
            public void transformPage(View view, float f) {
            }
        });
        this.w.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessResultInteractor.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return FitnessResultInteractor.this.z.dispatchTouchEvent(motionEvent);
            }
        });
        d dVar = new d(this.aa);
        this.c = dVar;
        this.z.setAdapter(dVar);
    }

    private void f() {
        Context context = this.i;
        if (context == null) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "initShareNewDetail ", "mContext is null");
            return;
        }
        FitnessShareNewDetailView fitnessShareNewDetailView = (FitnessShareNewDetailView) View.inflate(context, R.layout.sug_layout_share_new, null).findViewById(R.id.fitness_share_new_detail);
        this.p = fitnessShareNewDetailView;
        this.v = fitnessShareNewDetailView;
        h();
        this.aa.add(this.p);
    }

    private void h() {
        this.x = e();
        this.l = Utils.o() || !LanguageUtil.m(this.i);
        i();
    }

    private List<feb> c() {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(Arrays.asList(1005, Integer.valueOf(PointerIconCompat.TYPE_GRAB), 1021, 1022));
        if (LanguageUtil.m(this.i)) {
            arrayList = new ArrayList(Arrays.asList(Integer.valueOf(R$drawable.sport_share_watermark_1005), Integer.valueOf(R$drawable.sport_share_watermark_1020), Integer.valueOf(R$drawable.sport_share_watermark_1021), Integer.valueOf(R$drawable.sport_share_watermark_1022)));
        } else {
            arrayList = new ArrayList(Arrays.asList(Integer.valueOf(R$drawable.sport_share_watermark_1005_english), Integer.valueOf(R$drawable.sport_share_watermark_1020_english), Integer.valueOf(R$drawable.sport_share_watermark_1021_english), Integer.valueOf(R$drawable.sport_share_watermark_1022_english)));
        }
        ArrayList arrayList3 = new ArrayList(4);
        for (int i = 0; i < 4; i++) {
            feb febVar = new feb();
            febVar.e(((Integer) arrayList.get(i)).intValue());
            febVar.d(((Integer) arrayList2.get(i)).intValue());
            arrayList3.add(febVar);
        }
        return arrayList3;
    }

    private void i() {
        this.d.clear();
        d(this.d, b);
    }

    private void d(List<Integer> list, int[] iArr) {
        for (int i : iArr) {
            list.add(Integer.valueOf(i));
        }
    }

    static class d extends HealthPagerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private ArrayList<View> f3121a;

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        d(ArrayList arrayList) {
            this.f3121a = arrayList;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (koq.b(this.f3121a, i)) {
                LogUtil.h("Suggestion_FitnessResultInteractor", "The position is out of bound");
                return null;
            }
            ViewParent parent = this.f3121a.get(i).getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeAllViews();
            }
            viewGroup.addView(this.f3121a.get(i));
            return this.f3121a.get(i);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return this.f3121a.size();
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }
    }

    private List<Bitmap> b(List<View> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            Bitmap bGg_ = jdv.bGg_(it.next());
            if (bGg_ == null) {
                LogUtil.h("Suggestion_FitnessResultInteractor", "screenShotBitmap is null");
            } else {
                arrayList.add(bGg_);
            }
        }
        return arrayList;
    }

    private List<String> j() {
        ArrayList arrayList = new ArrayList(this.aa.size());
        List<Bitmap> b2 = b(this.aa);
        LogUtil.a("Suggestion_FitnessResultInteractor", "saveShareBitmap shareBitmapList size", Integer.valueOf(b2.size()));
        for (int i = 0; i < b2.size(); i++) {
            arrayList.add(aAM_(b2.get(i), i));
        }
        return arrayList;
    }

    private String aAM_(Bitmap bitmap, int i) {
        String str;
        FileOutputStream openOutputStream;
        File file = new File(jcu.f);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "saveBitmapToFile:mkdirs error");
        }
        File file2 = new File(file, i + ".jpg");
        this.u = file2;
        try {
            str = file2.getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "saveBitmapToFile getCanonicalPath exception");
            str = "";
        }
        try {
            openOutputStream = FileUtils.openOutputStream(this.u);
            try {
            } catch (Throwable th) {
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "saveBitmapToFile:IOException ");
        } catch (IllegalArgumentException e) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "saveBitmapToFile:IllegalArgumentException ", LogAnonymous.b((Throwable) e));
        }
        if (!sqc.a(this.u, str)) {
            LogUtil.h("Suggestion_FitnessResultInteractor", "invalidate file path");
            if (this.u.exists()) {
                this.u.deleteOnExit();
            }
            if (openOutputStream == null) {
                return null;
            }
            openOutputStream.close();
            return null;
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, openOutputStream);
        this.u.deleteOnExit();
        openOutputStream.flush();
        if (openOutputStream != null) {
            openOutputStream.close();
        }
        return str;
    }

    public void b() {
        File file = this.u;
        if (file != null && file.exists() && !this.u.delete()) {
            LogUtil.a("Suggestion_FitnessResultInteractor", "onDestroy, delete file failed");
        }
        Handler handler = this.y;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private FitnessShareRecord a() {
        FitnessShareRecord fitnessShareRecord = new FitnessShareRecord();
        fitnessShareRecord.setCalorie(this.f);
        fitnessShareRecord.setActionCount(this.k);
        int i = this.g;
        if (i == 3) {
            fitnessShareRecord.setDeviceType(this.r);
        } else {
            fitnessShareRecord.setDeviceType(i);
        }
        fitnessShareRecord.setExerciseName(this.s);
        fitnessShareRecord.setExerciseTime(this.o);
        fitnessShareRecord.setExerciseTimes(this.m);
        fitnessShareRecord.setDuration(this.ad);
        fitnessShareRecord.setLevelCount(this.q);
        return fitnessShareRecord;
    }

    private feh e() {
        fjh fjhVar = new fjh(this.i, a());
        fjhVar.b();
        return fjhVar.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        List<String> j = j();
        if (koq.b(j)) {
            LogUtil.b("Suggestion_FitnessResultInteractor", "shareImage shareBitmapList size is error 0, pls try again");
            return;
        }
        LogUtil.a("Suggestion_FitnessResultInteractor", "shareImage shareBitmapList size", Integer.valueOf(j.size()));
        ixx.d().d(this.i, "1130014", d(0), 0);
        fdz fdzVar = new fdz();
        fdzVar.c(true);
        fdzVar.d(j.get(0));
        fdzVar.d(this.d);
        fdzVar.b(10001);
        fdzVar.b(this.x);
        fdzVar.b(d(0));
        fdu fduVar = new fdu(6);
        fdzVar.b(c());
        fduVar.b(fdzVar);
        this.ac.exeShare(c(fduVar), this.i);
    }

    private fdu c(fdu fduVar) {
        fduVar.b(AnalyticsValue.HEALTH_SHARE_FITNESS_REPORT_SHARE_2100009.value());
        fduVar.i(false);
        fduVar.b(2);
        return fduVar;
    }

    private Map<String, Object> d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("workout_name", this.s);
        if (gge.c()) {
            hashMap.put("finish_rate", gge.c(this.t));
            hashMap.put("workout_TimeInMinutes", moe.g(this.ad));
        }
        if (i >= 0) {
            int[] iArr = this.f3119a;
            if (i < iArr.length) {
                hashMap.put("share_result", Integer.valueOf(iArr[i]));
            }
        }
        hashMap.put("entrance", Integer.valueOf(this.j));
        hashMap.put("workout_id", this.ae);
        hashMap.put("wear_type", Integer.valueOf(this.g));
        hashMap.put("isAICourse", this.n);
        return hashMap;
    }

    public void a(String str) {
        LogUtil.a("Suggestion_FitnessResultInteractor", "openWaitingDialog");
        if (this.ab != null && !this.e.isDestroyed() && !this.e.isFinishing()) {
            this.ab.dismiss();
            this.ab = null;
        }
        new CommonDialog21(this.i, R.style.app_update_dialogActivity);
        CommonDialog21 a2 = CommonDialog21.a(this.i);
        this.ab = a2;
        a2.e(str);
        this.ab.setCancelable(false);
        this.ab.a();
    }

    public void d() {
        LogUtil.a("Suggestion_FitnessResultInteractor", "closeWaitingDialog");
        if (this.ab != null) {
            if (!this.e.isDestroyed() && !this.e.isFinishing() && this.ab.isShowing()) {
                this.ab.dismiss();
            }
            this.ab = null;
        }
    }
}
