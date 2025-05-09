package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.healthcloud.plugintrack.runningroute.utils.CoordinatorAdapter;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.exg;
import defpackage.gzb;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.slf4j.Marker;

/* loaded from: classes4.dex */
public class gzb {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13012a;
    private HealthBottomSheet b;
    private boolean c;
    private ClockingRankAdapter d;
    private Context e;
    private ClockingRankAdapter f;
    private TabLayout i;
    private BaseResponseCallback<String> j;
    private ViewPager k;
    private CoordinatorAdapter l;
    private LinearLayout o;
    private Map<String, exg> n = new ConcurrentHashMap();
    private List<ClockingRankAdapter.d> h = new ArrayList();
    private List<ClockingRankAdapter.d> g = new ArrayList();

    private int e(int i) {
        return i == 0 ? R.drawable._2131430304_res_0x7f0b0ba0 : i == 1 ? R.drawable._2131430305_res_0x7f0b0ba1 : i == 2 ? R.drawable._2131430306_res_0x7f0b0ba2 : i + 1;
    }

    public gzb(Context context, TabLayout tabLayout, ViewPager viewPager) {
        this.e = context;
        this.i = tabLayout;
        this.k = viewPager;
    }

    public void c(HealthBottomSheet healthBottomSheet) {
        this.b = healthBottomSheet;
    }

    public void aXe_(LinearLayout linearLayout) {
        this.o = linearLayout;
    }

    public void d(BaseResponseCallback baseResponseCallback) {
        this.j = baseResponseCallback;
    }

    public void a(enc encVar) {
        Object[] objArr = new Object[2];
        objArr[0] = "bindHotPathDetailToView, hotPathDetailInfo: ";
        objArr[1] = encVar == null ? Constants.NULL : encVar.toString();
        LogUtil.a("RunningRouteRankHolder", objArr);
        if (encVar == null) {
            LogUtil.a("RunningRouteRankHolder", "hotPathDetailInfo is null");
            return;
        }
        List<ClockingRankAdapter.d> e = e(encVar);
        List<ClockingRankAdapter.d> c = c(encVar);
        this.c = koq.c(e);
        this.f13012a = koq.c(c);
        if (this.c) {
            this.h.addAll(e);
        }
        if (this.f13012a) {
            this.g.addAll(c);
        }
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.fragment_clocking_rank, (ViewGroup) null);
        View inflate2 = LayoutInflater.from(this.e).inflate(R.layout.fragment_clocking_rank, (ViewGroup) null);
        LinearLayout linearLayout = this.o;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        if (this.c) {
            HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.fragment_clocking_rank_recyclerview);
            healthRecycleView.a(false);
            healthRecycleView.d(false);
            int size = e.size();
            this.k.getLayoutParams().height = (this.e.getResources().getDimensionPixelOffset(R.dimen._2131363088_res_0x7f0a0510) * (size <= 30 ? size + 1 : 31)) + this.e.getResources().getDimensionPixelOffset(R.dimen._2131362886_res_0x7f0a0446);
            ClockingRankAdapter clockingRankAdapter = new ClockingRankAdapter(e);
            this.d = clockingRankAdapter;
            healthRecycleView.setAdapter(clockingRankAdapter);
            healthRecycleView.setLayoutManager(new LinearLayoutManager(this.e));
            c();
            b(encVar);
            aWZ_(encVar, inflate);
        } else {
            nsy.cMA_(inflate.findViewById(R.id.clock_rank_view), 8);
            nsy.cMA_((HealthCardView) inflate.findViewById(R.id.no_rank_cardview_layout), 0);
            this.k.getLayoutParams().height = this.e.getResources().getDimensionPixelOffset(R.dimen._2131363013_res_0x7f0a04c5) - this.e.getResources().getDimensionPixelOffset(R.dimen._2131363088_res_0x7f0a0510);
            BaseResponseCallback<String> baseResponseCallback = this.j;
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(0, null);
            } else {
                LogUtil.h("RunningRouteRankHolder", "mPunchRankCallBack == null");
            }
        }
        if (this.f13012a) {
            HealthRecycleView healthRecycleView2 = (HealthRecycleView) inflate2.findViewById(R.id.fragment_clocking_rank_recyclerview);
            healthRecycleView2.a(false);
            healthRecycleView2.d(false);
            ClockingRankAdapter clockingRankAdapter2 = new ClockingRankAdapter(c);
            this.f = clockingRankAdapter2;
            healthRecycleView2.setAdapter(clockingRankAdapter2);
            healthRecycleView2.setLayoutManager(new LinearLayoutManager(this.e));
            d(encVar);
            aXc_(encVar, inflate2);
        } else {
            inflate2 = LayoutInflater.from(this.e).inflate(R.layout.fragment_clocking_rank, (ViewGroup) null);
            nsy.cMA_(inflate2.findViewById(R.id.clock_rank_view), 8);
            nsy.cMA_((HealthCardView) inflate2.findViewById(R.id.no_rank_cardview_layout), 0);
        }
        aWW_(inflate, inflate2);
    }

    private void b(final enc encVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gzb.1
            @Override // java.lang.Runnable
            public void run() {
                List<eng> i = encVar.i();
                CountDownLatch countDownLatch = new CountDownLatch(i.size());
                for (int i2 = 0; i2 < i.size(); i2++) {
                    eng engVar = i.get(i2);
                    if (engVar != null) {
                        gzb.this.e(i2, engVar.e(), countDownLatch);
                    } else {
                        LogUtil.h("RunningRouteRankHolder", "requestCloudBindNum userInfo == null");
                        countDownLatch.countDown();
                    }
                }
                try {
                    LogUtil.a("RunningRouteRankHolder", "requestCloudBindNum await:", Boolean.valueOf(countDownLatch.await(10L, TimeUnit.SECONDS)));
                    HandlerExecutor.e(new Runnable() { // from class: gzb.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("RunningRouteRankHolder", "mNumRankAdapter notifyDataSetChanged");
                            gzb.this.c();
                            gzb.this.d.notifyDataSetChanged();
                        }
                    });
                } catch (InterruptedException unused) {
                    LogUtil.a("RunningRouteRankHolder", "requestCloudBindNum InterruptedException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007b, code lost:
    
        if (android.text.TextUtils.isEmpty(r0) != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c() {
        /*
            r7 = this;
            android.widget.LinearLayout r0 = r7.o
            if (r0 != 0) goto L10
            java.lang.String r0 = "mUserImageLayout == null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "RunningRouteRankHolder"
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L10:
            r0.removeAllViews()
            java.util.List<com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d> r0 = r7.h
            boolean r0 = defpackage.koq.c(r0)
            if (r0 == 0) goto L87
            java.util.List<com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d> r0 = r7.h
            int r0 = r0.size()
            r1 = 4
            if (r0 <= r1) goto L25
            r0 = r1
        L25:
            r1 = 0
            r2 = r1
        L27:
            if (r2 >= r0) goto L69
            android.widget.ImageView r3 = new android.widget.ImageView
            android.content.Context r4 = r7.e
            r3.<init>(r4)
            android.widget.LinearLayout r4 = r7.o
            r4.addView(r3)
            android.content.Context r4 = r7.e
            android.content.res.Resources r4 = r4.getResources()
            r5 = 2131362973(0x7f0a049d, float:1.8345742E38)
            float r4 = r4.getDimension(r5)
            int r4 = (int) r4
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams
            r5.<init>(r4, r4)
            android.content.Context r4 = r7.e
            android.content.res.Resources r4 = r4.getResources()
            r6 = 2131363063(0x7f0a04f7, float:1.8345924E38)
            float r4 = r4.getDimension(r6)
            int r4 = (int) r4
            r5.rightMargin = r4
            r3.setLayoutParams(r5)
            java.util.List<com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d> r4 = r7.h
            java.lang.Object r4 = r4.get(r2)
            com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d r4 = (com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter.d) r4
            r7.aXd_(r4, r3)
            int r2 = r2 + 1
            goto L27
        L69:
            if (r0 <= 0) goto L7d
            java.util.List<com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d> r0 = r7.h
            java.lang.Object r0 = r0.get(r1)
            com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter$d r0 = (com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter.d) r0
            java.lang.String r0 = r0.d()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L7f
        L7d:
            java.lang.String r0 = ""
        L7f:
            com.huawei.health.userprofilemgr.model.BaseResponseCallback<java.lang.String> r1 = r7.j
            if (r1 == 0) goto L87
            r2 = 1
            r1.onResponse(r2, r0)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gzb.c():void");
    }

    private void aXd_(ClockingRankAdapter.d dVar, final ImageView imageView) {
        if (imageView == null) {
            LogUtil.h("RunningRouteRankHolder", "setUserImage imageView==null");
        } else if (TextUtils.isEmpty(dVar.d())) {
            imageView.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        } else {
            nrf.cIT_(imageView, dVar.d(), 90, 0, new AsyncLoadDrawableCallback() { // from class: gyx
                @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                public final void getDrawable(Drawable drawable) {
                    gzb.aWX_(imageView, drawable);
                }
            });
        }
    }

    static /* synthetic */ void aWX_(ImageView imageView, Drawable drawable) {
        if (drawable == null) {
            imageView.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        }
    }

    private void d(final enc encVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gzb.4
            @Override // java.lang.Runnable
            public void run() {
                List<eng> t = encVar.t();
                CountDownLatch countDownLatch = new CountDownLatch(t.size());
                for (int i = 0; i < t.size(); i++) {
                    eng engVar = t.get(i);
                    if (engVar != null) {
                        gzb.this.a(i, engVar.e(), countDownLatch);
                    } else {
                        LogUtil.h("RunningRouteRankHolder", "requestCloudBindTime userInfo == null");
                        countDownLatch.countDown();
                    }
                }
                try {
                    LogUtil.a("RunningRouteRankHolder", "requestCloudBindTime await:", Boolean.valueOf(countDownLatch.await(10L, TimeUnit.SECONDS)));
                    HandlerExecutor.e(new Runnable() { // from class: gzb.4.3
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("RunningRouteRankHolder", "mTimeRankAdapter notifyDataSetChanged");
                            gzb.this.f.notifyDataSetChanged();
                        }
                    });
                } catch (InterruptedException unused) {
                    LogUtil.a("RunningRouteRankHolder", "requestCloudBindTime InterruptedException");
                }
            }
        });
    }

    private List<ClockingRankAdapter.d> e(enc encVar) {
        if (encVar == null) {
            LogUtil.a("RunningRouteRankHolder", "hotPathDetailInfo is null");
            return null;
        }
        List<eng> i = encVar.i();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i.size(); i2++) {
            eng engVar = i.get(i2);
            if (engVar != null) {
                arrayList.add(new ClockingRankAdapter.d(e(i2), "", "", this.e.getResources().getQuantityString(R.plurals._2130903416_res_0x7f030178, engVar.b(), Integer.valueOf(engVar.b()))));
            }
        }
        return arrayList;
    }

    private List<ClockingRankAdapter.d> c(enc encVar) {
        if (encVar == null) {
            LogUtil.a("RunningRouteRankHolder", "hotPathDetailInfo is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<eng> t = encVar.t();
        if (t != null) {
            for (int i = 0; i < t.size(); i++) {
                eng engVar = t.get(i);
                if (engVar != null) {
                    arrayList.add(new ClockingRankAdapter.d(e(i), "", "", RunningRouteUtils.a(engVar.a())));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final String str, final CountDownLatch countDownLatch) {
        LogUtil.a("RunningRouteRankHolder", "requestRunnerNumRankImage, huid: ", str);
        if (this.n.containsKey(str) && this.n.get(str) != null) {
            d(this.n.get(str), i, countDownLatch, this.h, this.d);
            countDownLatch.countDown();
        } else {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getOtherUserInfo(str, new UserInfoCallback<exg>() { // from class: gzb.5
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exg exgVar) {
                    LogUtil.a("RunningRouteRankHolder", "getOtherUserInfo infoCallback");
                    if (exgVar != null && exgVar.b() != null && exgVar.b().e() != null) {
                        gzb.this.n.put(str, exgVar);
                        gzb gzbVar = gzb.this;
                        gzbVar.d(exgVar, i, countDownLatch, gzbVar.h, gzb.this.d);
                        countDownLatch.countDown();
                        return;
                    }
                    LogUtil.h("RunningRouteRankHolder", "requestRunnerNumRankImage info or GetOtherUserInfoRsp or otherUserInfo is null");
                    countDownLatch.countDown();
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i2) {
                    LogUtil.b("RunningRouteRankHolder", "errorCallback errorCode is ", Integer.valueOf(i2));
                    countDownLatch.countDown();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(exg exgVar, int i, CountDownLatch countDownLatch, List<ClockingRankAdapter.d> list, ClockingRankAdapter clockingRankAdapter) {
        exg.b e = exgVar.b().e();
        if (e == null) {
            return;
        }
        LogUtil.a("RunningRouteRankHolder", "userInfo: ", e.toString());
        ClockingRankAdapter.d dVar = list.get(i);
        dVar.d(e.c());
        dVar.e(e.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final String str, final CountDownLatch countDownLatch) {
        LogUtil.a("RunningRouteRankHolder", "requestRunnerTimeRankImage, huid: ", str);
        if (this.n.containsKey(str) && this.n.get(str) != null) {
            d(this.n.get(str), i, countDownLatch, this.g, this.f);
            countDownLatch.countDown();
        } else {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getOtherUserInfo(str, new UserInfoCallback<exg>() { // from class: gzb.2
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exg exgVar) {
                    LogUtil.a("RunningRouteRankHolder", "getOtherUserInfo infoCallback");
                    if (exgVar != null && exgVar.b() != null && exgVar.b().e() != null) {
                        gzb.this.n.put(str, exgVar);
                        gzb gzbVar = gzb.this;
                        gzbVar.d(exgVar, i, countDownLatch, gzbVar.g, gzb.this.f);
                        countDownLatch.countDown();
                        return;
                    }
                    LogUtil.h("RunningRouteRankHolder", "requestRunnerTimeRankImage info or GetOtherUserInfoRsp or otherUserInfo is null");
                    countDownLatch.countDown();
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i2) {
                    LogUtil.b("RunningRouteRankHolder", "errorCallback errorCode is ", Integer.valueOf(i2));
                    countDownLatch.countDown();
                }
            });
        }
    }

    private void aWW_(View view, View view2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(view);
        arrayList.add(view2);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.e.getString(R.string._2130845867_res_0x7f0220ab));
        arrayList2.add(this.e.getString(R.string._2130845868_res_0x7f0220ac));
        CoordinatorAdapter coordinatorAdapter = new CoordinatorAdapter(arrayList, arrayList2);
        this.l = coordinatorAdapter;
        this.k.setAdapter(coordinatorAdapter);
        this.i.setupWithViewPager(this.k);
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass3());
    }

    /* renamed from: gzb$3, reason: invalid class name */
    class AnonymousClass3 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass3() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            gzb.this.i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int tabCount = gzb.this.i.getTabCount();
            for (final int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = gzb.this.i.getTabAt(i);
                if (tabAt == null) {
                    LogUtil.h("RunningRouteRankHolder", "bindRankView tab is null");
                } else {
                    TabLayout.TabView tabView = tabAt.view;
                    if (tabView == null) {
                        LogUtil.h("RunningRouteRankHolder", "bindRankView tabView is null");
                    } else {
                        tabView.setOnLongClickListener(new View.OnLongClickListener() { // from class: gyy
                            @Override // android.view.View.OnLongClickListener
                            public final boolean onLongClick(View view) {
                                return gzb.AnonymousClass3.this.aXf_(i, view);
                            }
                        });
                    }
                }
            }
        }

        /* synthetic */ boolean aXf_(int i, View view) {
            if (!gzb.this.i.isSelected()) {
                gzb.this.k.setCurrentItem(i);
            }
            if (gzb.this.b == null) {
                return true;
            }
            gzb.this.b.setSheetState(HwBottomSheet.SheetState.EXPANDED);
            return true;
        }
    }

    private void aWZ_(enc encVar, View view) {
        List<eng> i = encVar.i();
        String huidOrDefault = LoginInit.getInstance(BaseApplication.e()).getHuidOrDefault();
        ImageView imageView = (ImageView) view.findViewById(R.id.user_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        nsy.cMr_((HealthTextView) view.findViewById(R.id.user_name), this.e.getString(R.string._2130845886_res_0x7f0220be));
        aXa_(huidOrDefault, imageView);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.rank_num);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.rank_image);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.clock_times);
        nsy.cMA_(healthTextView, 0);
        nsy.cMr_(healthTextView, "--");
        nsy.cMr_(healthTextView2, "--");
        nsy.cMA_(imageView2, 8);
        nsy.cMA_(healthTextView, 0);
        for (int i2 = 0; i2 < i.size(); i2++) {
            LogUtil.a("RunningRouteRankHolder", "userHuid: ", huidOrDefault, ", otherRunnerHuid: ", String.valueOf(i.get(i2).e()));
            if (huidOrDefault.equals(i.get(i2).e())) {
                if (i2 == 0 || i2 == 1 || i2 == 2) {
                    imageView2.setImageResource(e(i2));
                    nsy.cMA_(imageView2, 0);
                    nsy.cMA_(healthTextView, 8);
                } else {
                    nsy.cMr_(healthTextView, String.valueOf(i2 + 1));
                    nsy.cMA_(imageView2, 8);
                    nsy.cMA_(healthTextView, 0);
                }
                int b = i.get(i2).b();
                nsy.cMr_(healthTextView2, this.e.getResources().getQuantityString(R.plurals._2130903416_res_0x7f030178, b, Integer.valueOf(b)));
                return;
            }
            if (encVar.j() != 0) {
                nsy.cMr_(healthTextView2, this.e.getResources().getQuantityString(R.plurals._2130903416_res_0x7f030178, (int) encVar.j(), Integer.valueOf((int) encVar.j())));
                nsy.cMr_(healthTextView, "--");
                nsy.cMA_(imageView2, 8);
                nsy.cMA_(healthTextView, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aWY_(exg exgVar, final ImageView imageView) {
        final exg.b e = exgVar.b().e();
        if (e == null) {
            return;
        }
        LogUtil.a("RunningRouteRankHolder", "otherUserInfo: ", e.toString());
        HandlerExecutor.e(new Runnable() { // from class: gzb.8
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(e.c())) {
                    return;
                }
                nrf.cIT_(imageView, e.c(), 90, 0, new AsyncLoadDrawableCallback() { // from class: gzb.8.1
                    @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                    public void getDrawable(Drawable drawable) {
                        if (drawable == null) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
                        }
                    }
                });
            }
        });
    }

    private void aXc_(enc encVar, View view) {
        String huidOrDefault = LoginInit.getInstance(BaseApplication.e()).getHuidOrDefault();
        ImageView imageView = (ImageView) view.findViewById(R.id.user_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        nsy.cMr_((HealthTextView) view.findViewById(R.id.user_name), this.e.getString(R.string._2130845886_res_0x7f0220be));
        aXb_(huidOrDefault, imageView);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.rank_image);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.rank_num);
        nsy.cMr_(healthTextView, UnitUtil.e(30.0d, 1, 0) + Marker.ANY_NON_NULL_MARKER);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.clock_times);
        nsy.cMr_(healthTextView, "--");
        nsy.cMr_(healthTextView2, "--");
        nsy.cMA_(healthTextView, 0);
        nsy.cMA_(healthTextView2, 0);
        List<eng> t = encVar.t();
        for (int i = 0; i < t.size(); i++) {
            LogUtil.a("RunningRouteRankHolder", "userHuid: ", huidOrDefault, ", otherRunnerHuid: ", String.valueOf(t.get(i).e()));
            if (huidOrDefault.equals(t.get(i).e())) {
                if (i == 0 || i == 1 || i == 2) {
                    imageView2.setImageResource(e(i));
                    nsy.cMA_(imageView2, 0);
                    nsy.cMA_(healthTextView, 8);
                } else {
                    nsy.cMr_(healthTextView, String.valueOf(i + 1));
                    nsy.cMA_(healthTextView, 0);
                    nsy.cMA_(imageView2, 8);
                }
                nsy.cMr_(healthTextView2, RunningRouteUtils.a(t.get(i).a()));
                return;
            }
            if (encVar.c() != 0) {
                nsy.cMr_(healthTextView2, RunningRouteUtils.a(encVar.c()));
                nsy.cMr_(healthTextView, "--");
                nsy.cMA_(healthTextView, 0);
                nsy.cMA_(imageView2, 8);
            }
        }
    }

    private void aXa_(final String str, final ImageView imageView) {
        if (this.n.containsKey(str) && this.n.get(str) != null) {
            aWY_(this.n.get(str), imageView);
        } else {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getOtherUserInfo(str, new UserInfoCallback<exg>() { // from class: gzb.7
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exg exgVar) {
                    LogUtil.a("RunningRouteRankHolder", "getOtherUserInfo infoCallback");
                    if (exgVar == null || exgVar.b() == null || exgVar.b().e() == null) {
                        return;
                    }
                    gzb.this.n.put(str, exgVar);
                    gzb.this.aWY_(exgVar, imageView);
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.b("RunningRouteRankHolder", "errorCallback errorCode is ", Integer.valueOf(i));
                }
            });
        }
    }

    private void aXb_(final String str, final ImageView imageView) {
        if (this.n.containsKey(str) && this.n.get(str) != null) {
            aWY_(this.n.get(str), imageView);
        } else {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getOtherUserInfo(str, new UserInfoCallback<exg>() { // from class: gzb.6
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exg exgVar) {
                    LogUtil.a("RunningRouteRankHolder", "getOtherUserInfo infoCallback");
                    if (exgVar == null || exgVar.b() == null || exgVar.b().e() == null) {
                        return;
                    }
                    gzb.this.n.put(str, exgVar);
                    gzb.this.aWY_(exgVar, imageView);
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.b("RunningRouteRankHolder", "errorCallback errorCode is ", Integer.valueOf(i));
                }
            });
        }
    }
}
