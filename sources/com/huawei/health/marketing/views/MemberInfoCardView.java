package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.MemberTypeInfo;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ase;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.eil;
import defpackage.gpn;
import defpackage.koq;
import defpackage.moj;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MemberInfoCardView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2874a;
    private ImageView aa;
    public Context b;
    private boolean c;
    public boolean d;
    public Handler e;
    private boolean f;
    private ConstraintLayout g;
    private HealthCardView h;
    private boolean i;
    private HealthTextView j;
    private int k;
    private CopyOnWriteArrayList<LinearLayout> l;
    private LinearLayout m;
    private HealthTextView n;
    private ViewFlipper o;
    private List<Product> p;
    private final View.OnClickListener q;
    private final View.OnClickListener r;
    private Observer s;
    private List<LinearLayout> t;
    private final View.OnClickListener u;
    private Product v;
    private HealthTextView w;
    private View x;
    private HealthTextView y;

    static class e extends BaseHandler<MemberInfoCardView> {
        e(MemberInfoCardView memberInfoCardView) {
            super(memberInfoCardView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: apB_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MemberInfoCardView memberInfoCardView, Message message) {
            if (memberInfoCardView == null || message == null) {
                LogUtil.h("MemberInfoCardView", "handleMessageWhenReferenceNotNull fragment or msg is null");
                return;
            }
            int i = message.what;
            if (i == 10) {
                memberInfoCardView.a((List<LinearLayout>) null);
            } else {
                if (i != 11) {
                    return;
                }
                memberInfoCardView.a(BaseApplication.e());
            }
        }
    }

    /* renamed from: com.huawei.health.marketing.views.MemberInfoCardView$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!LoginInit.getInstance(MemberInfoCardView.this.b).isBrowseMode()) {
                if (MemberInfoCardView.this.k == -1 || MemberInfoCardView.this.k == 5) {
                    dqj.c(2);
                } else {
                    dqj.a(2);
                }
                MemberInfoCardView.this.e();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LoginInit.getInstance(MemberInfoCardView.this.b).browsingToLogin(new IBaseResponseCallback() { // from class: ekg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("MemberInfoCardView", "isBrowseMode");
                }
            }, "");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public MemberInfoCardView(Context context, List<LinearLayout> list, int i) {
        super(context);
        this.e = new e(this);
        this.c = true;
        this.l = new CopyOnWriteArrayList<>();
        this.t = new ArrayList();
        this.d = false;
        this.p = new CopyOnWriteArrayList();
        this.i = true;
        this.f = false;
        this.s = new c(this);
        this.q = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dqj.a(2);
                MemberInfoCardView.this.e("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CustomRenewal&urlType=4");
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.u = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MemberInfoCardView.this.k == 4 || MemberInfoCardView.this.k == 5) {
                    dqj.c(2);
                } else {
                    dqj.a(2);
                }
                MemberInfoCardView.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.r = new AnonymousClass1();
        this.b = context;
        a(context);
        if (koq.c(list) && i == 1) {
            a(list);
        }
    }

    public MemberInfoCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new e(this);
        this.c = true;
        this.l = new CopyOnWriteArrayList<>();
        this.t = new ArrayList();
        this.d = false;
        this.p = new CopyOnWriteArrayList();
        this.i = true;
        this.f = false;
        this.s = new c(this);
        this.q = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dqj.a(2);
                MemberInfoCardView.this.e("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CustomRenewal&urlType=4");
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.u = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MemberInfoCardView.this.k == 4 || MemberInfoCardView.this.k == 5) {
                    dqj.c(2);
                } else {
                    dqj.a(2);
                }
                MemberInfoCardView.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.r = new AnonymousClass1();
    }

    public MemberInfoCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new e(this);
        this.c = true;
        this.l = new CopyOnWriteArrayList<>();
        this.t = new ArrayList();
        this.d = false;
        this.p = new CopyOnWriteArrayList();
        this.i = true;
        this.f = false;
        this.s = new c(this);
        this.q = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dqj.a(2);
                MemberInfoCardView.this.e("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CustomRenewal&urlType=4");
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.u = new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MemberInfoCardView.this.k == 4 || MemberInfoCardView.this.k == 5) {
                    dqj.c(2);
                } else {
                    dqj.a(2);
                }
                MemberInfoCardView.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.r = new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        Log.i("MemberInfoCardView", "initNormalView");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (this.x == null) {
            this.x = layoutInflater.inflate(R.layout.member_card_msg_view_layout, this);
        }
        if (this.h == null) {
            this.h = (HealthCardView) this.x.findViewById(R.id.member_info_card_view);
        }
        if (this.g == null) {
            this.g = (ConstraintLayout) this.x.findViewById(R.id.member_info_card_layout);
        }
        if (this.m == null) {
            this.m = (LinearLayout) this.x.findViewById(R.id.member_info_card_content);
        }
        if (this.f2874a == null) {
            this.f2874a = (ImageView) this.x.findViewById(R.id.vip_card_wavy_image);
        }
        if (this.f2874a.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f2874a.getLayoutParams();
            layoutParams.bottomMargin = nrr.e(this.b, -6.0f);
            this.f2874a.setLayoutParams(layoutParams);
        }
        apz_(context, this.m, this.h);
        if (this.o == null) {
            this.o = (ViewFlipper) this.x.findViewById(R.id.member_inbox_layout);
        }
        this.o.setVisibility(8);
        dqj.e(false);
        d();
    }

    private void apz_(Context context, LinearLayout linearLayout, HealthCardView healthCardView) {
        if (linearLayout == null || healthCardView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            layoutParams2.setMarginStart(nrr.b(this.b) + ((Integer) safeRegionWidth.first).intValue());
            layoutParams2.setMarginEnd(nrr.b(this.b) + ((Integer) safeRegionWidth.second).intValue());
            layoutParams2.height = -2;
            linearLayout.setLayoutParams(layoutParams2);
        }
        if (this.d) {
            apy_(linearLayout, healthCardView);
        } else {
            requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<LinearLayout> list) {
        Log.i("MemberInfoCardView", "initMsgView");
        this.d = true;
        LayoutInflater layoutInflater = (LayoutInflater) this.b.getSystemService("layout_inflater");
        if (this.x == null) {
            this.x = layoutInflater.inflate(R.layout.member_card_msg_view_layout, this);
        }
        if (this.h == null) {
            this.h = (HealthCardView) this.x.findViewById(R.id.member_info_card_view);
        }
        if (this.m == null) {
            this.m = (LinearLayout) this.x.findViewById(R.id.member_info_card_content);
        }
        if (this.f2874a == null) {
            this.f2874a = (ImageView) this.x.findViewById(R.id.vip_card_wavy_image);
        }
        if (this.f2874a.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f2874a.getLayoutParams();
            layoutParams.bottomMargin = nrr.e(this.b, -14.0f);
            this.f2874a.setLayoutParams(layoutParams);
        }
        this.m.setBackgroundResource(R.drawable._2131431390_res_0x7f0b0fde);
        if (this.g == null) {
            this.g = (ConstraintLayout) this.x.findViewById(R.id.member_info_card_layout);
        }
        apy_(this.m, this.h);
        if (this.o == null) {
            this.o = (ViewFlipper) this.x.findViewById(R.id.member_inbox_layout);
        }
        e(list);
    }

    private void apy_(LinearLayout linearLayout, HealthCardView healthCardView) {
        if (linearLayout == null || healthCardView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.height = -2;
            LogUtil.a("MemberInfoCardView", "refreshMsgCardLayout height", Integer.valueOf(layoutParams2.height));
            layoutParams2.setMargins(nrr.b(this.b) + ((Integer) safeRegionWidth.first).intValue(), nrr.e(this.b, 10.0f), nrr.b(this.b) + ((Integer) safeRegionWidth.second).intValue(), 0);
            linearLayout.setLayoutParams(layoutParams2);
        }
        ViewGroup.LayoutParams layoutParams3 = healthCardView.getLayoutParams();
        if (layoutParams3 instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
            layoutParams4.height = -2;
            LogUtil.a("MemberInfoCardView", "refreshNormalCardLayout cardView height", Integer.valueOf(layoutParams4.height));
            healthCardView.setLayoutParams(layoutParams4);
        }
        if (this.w == null) {
            this.w = (HealthTextView) this.x.findViewById(R.id.member_card_user_name);
        }
        apx_(this.w, 16, 10, 0, 0);
        if (this.aa == null) {
            this.aa = (ImageView) this.x.findViewById(R.id.member_card_vip_icon);
        }
        apx_(this.aa, 4, 6, 0, 0);
        if (this.y == null) {
            this.y = (HealthTextView) this.x.findViewById(R.id.member_card_subtitle);
        }
        apx_(this.y, 16, 2, 0, 10);
    }

    private boolean c() {
        int i = this.b.getResources().getConfiguration().densityDpi;
        LogUtil.a("MemberInfoCardView", "nowDpi ", Integer.valueOf(i));
        return i > gpn.c();
    }

    private void apx_(View view, int i, int i2, int i3, int i4) {
        if (view == null) {
            LogUtil.h("MemberInfoCardView", "view is null when adjustMsgLayoutMargin.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(nrr.e(this.b, i), nrr.e(this.b, i2), nrr.e(this.b, i3), nrr.e(this.b, i4));
            view.setLayoutParams(layoutParams2);
        }
    }

    private void d() {
        this.w = (HealthTextView) this.x.findViewById(R.id.member_card_user_name);
        this.aa = (ImageView) this.x.findViewById(R.id.member_card_vip_icon);
        HealthTextView healthTextView = (HealthTextView) this.x.findViewById(R.id.member_card_subtitle);
        this.y = healthTextView;
        if (healthTextView != null && c()) {
            this.y.setMaxWidth(nrr.e(this.b, 200.0f));
        }
        this.n = (HealthTextView) this.x.findViewById(R.id.member_card_renewal_manage_text);
        this.j = (HealthTextView) this.x.findViewById(R.id.member_card_button);
        nsy.cMn_(this.n, this.q);
        if (this.c) {
            ObserverManagerUtil.d(this.s, "MEMBER_TYPE_SELECTED");
            ObserverManagerUtil.d(this.s, "USER_MEMBER_INFO_UPDATE");
            this.c = false;
        }
        this.x.setClickable(true);
        a();
    }

    public void e(List<LinearLayout> list) {
        LogUtil.a("MemberInfoCardView", "refreshVipDescriptionInfo");
        if (koq.c(list)) {
            d(list);
        } else if (koq.c(this.l)) {
            d(this.l);
        } else {
            this.o.setVisibility(8);
            LogUtil.a("MemberInfoCardView", "refreshVipDescriptionInfo mMsgViewList is empty");
        }
    }

    private void d(List<LinearLayout> list) {
        this.o.setVisibility(0);
        int i = this.k;
        dqj.b(1, (i == -1 || i == 5) ? 1 : 2);
        dqj.e(true);
        this.o.stopFlipping();
        this.o.removeAllViews();
        Iterator<LinearLayout> it = list.iterator();
        while (it.hasNext()) {
            this.o.addView(it.next());
        }
        if (list.size() > 1) {
            this.o.startFlipping();
        }
        if (c() && (this.o.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.getLayoutParams();
            layoutParams.height = nrr.e(this.b, 24.0f);
            layoutParams.setMargins(0, 4, 0, 14);
            this.o.setLayoutParams(layoutParams);
        }
    }

    private void a() {
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LogUtil.a("MemberInfoCardView", "initData isBrowseMode");
            b();
        } else {
            g();
            i();
            f();
            h();
        }
    }

    public List<LinearLayout> getMsgViewList() {
        return this.l;
    }

    public void setMsgViewList(List<LinearLayout> list) {
        this.l.clear();
        if (koq.c(list)) {
            this.l.addAll(list);
        }
    }

    public void setIsMsgView(boolean z) {
        this.d = z;
    }

    public List<LinearLayout> getOuterMsgViewList() {
        return this.t;
    }

    public void setOuterMsgViewList(List<LinearLayout> list) {
        this.t = list;
    }

    private void f() {
        UserMemberInfo a2 = gpn.a();
        if (a2 != null) {
            LogUtil.a("MemberInfoCardView", "refreshWithCachedMemberInfo");
            c(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ThreadPoolManager.d().execute(new g(this));
    }

    static class g implements Runnable {
        private WeakReference<MemberInfoCardView> d;

        g(MemberInfoCardView memberInfoCardView) {
            this.d = new WeakReference<>(memberInfoCardView);
        }

        @Override // java.lang.Runnable
        public void run() {
            VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
            if (vipApi == null) {
                LogUtil.a("MemberInfoCardView", "vipApi is null");
            } else {
                vipApi.getVipInfo(new VipCallback() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.g.3
                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onSuccess(Object obj) {
                        MemberInfoCardView memberInfoCardView = (MemberInfoCardView) g.this.d.get();
                        if (memberInfoCardView != null && (obj instanceof UserMemberInfo)) {
                            LogUtil.a("MemberInfoCardView", "getVipInfo refresh");
                            memberInfoCardView.c((UserMemberInfo) obj);
                        } else {
                            LogUtil.b("MemberInfoCardView", "result is not UserMemberInfo");
                        }
                    }

                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onFailure(int i, String str) {
                        MemberInfoCardView memberInfoCardView = (MemberInfoCardView) g.this.d.get();
                        LogUtil.b("MemberInfoCardView", "getVipMemberInfo onFailure.");
                        if (memberInfoCardView == null || !LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
                            return;
                        }
                        LogUtil.a("MemberInfoCardView", "getVipMemberInfo isBrowseMode");
                        memberInfoCardView.b();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        UserMemberInfo userMemberInfo = new UserMemberInfo();
        userMemberInfo.setMemberFlag(-1);
        SharedPreferenceManager.e(this.b, Integer.toString(10000), "USER_VIP_INFO_KEY", moj.e(userMemberInfo), new StorageParams());
        c(userMemberInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        final String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1002);
        if (!TextUtils.isEmpty(accountInfo)) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.2
                @Override // java.lang.Runnable
                public void run() {
                    nsy.cMr_(MemberInfoCardView.this.w, accountInfo);
                }
            });
        }
        HiHealthManager.d(this.b).fetchUserData(new a(this));
    }

    private int e(UserMemberInfo userMemberInfo) {
        if (userMemberInfo == null || userMemberInfo.getMemberFlag() == -1) {
            return -1;
        }
        long nowTime = userMemberInfo.getNowTime();
        long expireTime = userMemberInfo.getExpireTime();
        int autoRenew = userMemberInfo.getAutoRenew();
        int b2 = eil.b(nowTime, expireTime);
        if (nowTime > expireTime) {
            return ase.c() ? 4 : 5;
        }
        if (autoRenew == 1 && this.f) {
            return 1;
        }
        return b2 <= 7 ? 3 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final UserMemberInfo userMemberInfo) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.3
                @Override // java.lang.Runnable
                public void run() {
                    MemberInfoCardView.this.c(userMemberInfo);
                }
            });
            return;
        }
        int e2 = e(userMemberInfo);
        this.k = e2;
        LogUtil.a("MemberInfoCardView", "refreshLayout, status: ", Integer.valueOf(e2));
        b(e2);
        a(e2);
        b(userMemberInfo, e2);
        d(e2);
    }

    private void b(int i) {
        nsy.cMz_(this.h, i == 4 ? R.drawable._2131430973_res_0x7f0b0e3d : R.drawable._2131430974_res_0x7f0b0e3e);
    }

    private void a(int i) {
        LogUtil.a("MemberInfoCardView", "refreshUserNameLayout");
        if (Utils.f()) {
            nsy.cMq_(this.w, R$string.IDS_member_life);
        }
        nsy.cMu_(this.w, ContextCompat.getColor(getContext(), i == 4 ? R.color._2131299239_res_0x7f090ba7 : R.color._2131299313_res_0x7f090bf1));
        if (i == 3 || i == 1 || i == 2) {
            nsy.cMA_(this.aa, 0);
            nsy.cMp_(this.aa, R.drawable._2131430190_res_0x7f0b0b2e);
        }
    }

    private void b(UserMemberInfo userMemberInfo, int i) {
        nsy.cMu_(this.y, ContextCompat.getColor(getContext(), i == 4 ? R.color._2131296921_res_0x7f090299 : R.color._2131299312_res_0x7f090bf0));
        nsy.cMs_(this.y, d(userMemberInfo, i), true);
    }

    private String d(UserMemberInfo userMemberInfo, int i) {
        if (userMemberInfo == null) {
            LogUtil.h("MemberInfoCardView", "getMemberCardSubtitle userMemberInfo is null");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R$string.IDS_member_life));
        sb.append(" ");
        if (i == -1) {
            return getResources().getString(R$string.IDS_user_member_not_vip_subtitle);
        }
        if (i == 1 || i == 2) {
            sb.append(getResources().getString(R$string.IDS_vip_valid_until, DateFormatUtil.b(userMemberInfo.getExpireTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMMDD)));
        } else {
            if (i != 3) {
                if (i != 4 && i != 5) {
                    return "";
                }
                return getResources().getString(R$string.IDS_vip_expired_subtitle, e(userMemberInfo.getExpireTime()));
            }
            int b2 = eil.b(userMemberInfo.getNowTime(), userMemberInfo.getExpireTime());
            if (b2 >= 1) {
                sb.append(getResources().getString(R$string.IDS_vip_expire_until, getResources().getQuantityString(R.plurals._2130903367_res_0x7f030147, b2, String.valueOf(b2))));
            } else {
                return this.b.getString(R$string.IDS_vip_des_will_expire_today, DateFormatUtil.b(userMemberInfo.getExpireTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE));
            }
        }
        return sb.toString();
    }

    private String e(long j) {
        return UnitUtil.a(new Date(j), 20);
    }

    private void d(int i) {
        if (i == 3 || i == 2 || i == 1) {
            nsy.cMr_(this.j, getResources().getString(R$string.IDS_vip_renewal));
            nsy.cMn_(this.j, this.u);
        } else if (i == 4 || i == 5) {
            nsy.cMr_(this.j, getResources().getString(R$string.IDS_user_vip_renew));
            nsy.cMn_(this.j, this.r);
        } else if (i == -1) {
            nsy.cMr_(this.j, getResources().getString(R$string.IDS_vip_open_immediately));
            nsy.cMn_(this.j, this.r);
        }
        nsy.cMA_(this.j, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ThreadPoolManager.d().execute(new d(this));
    }

    static class d implements Runnable {
        private WeakReference<MemberInfoCardView> d;

        d(MemberInfoCardView memberInfoCardView) {
            this.d = new WeakReference<>(memberInfoCardView);
        }

        @Override // java.lang.Runnable
        public void run() {
            ((VipApi) Services.c("vip", VipApi.class)).getVipType(new IBaseResponseCallback() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.d.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List b = MemberInfoCardView.b(obj);
                    if (koq.b(b)) {
                        return;
                    }
                    PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
                    if (d.this.d.get() != null && payApi != null) {
                        MemberInfoCardView memberInfoCardView = (MemberInfoCardView) d.this.d.get();
                        memberInfoCardView.p.clear();
                        memberInfoCardView.p.addAll(b);
                        List c = MemberInfoCardView.c((List<Product>) memberInfoCardView.p);
                        payApi.getProductState(c.toString(), new b(c));
                        return;
                    }
                    LogUtil.h("MemberInfoCardView", "getProductState payApi is null.");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> c(List<Product> list) {
        ArrayList arrayList = new ArrayList();
        if (list.isEmpty()) {
            return arrayList;
        }
        for (Product product : list) {
            if (product != null && product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
                arrayList.add(product.getProductId());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int i;
        LogUtil.a("MemberInfoCardView", "jumpTypeSelectActivity");
        if (nsn.o()) {
            LogUtil.a("MemberInfoCardView", "isFastClick");
            return;
        }
        if (this.v != null && !koq.b(this.p)) {
            for (Product product : this.p) {
                if (product != null && product.getProductId().equals(this.v.getProductId())) {
                    i = this.p.indexOf(product);
                    break;
                }
            }
        }
        i = 0;
        AppRouter.b("/OperationBundle/MemberTypeSelectActivity").c("MEMBER_TYPE_SELECT_INDEX", i).b(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031).c(this.b);
    }

    public void apA_(int i, int i2, Intent intent) {
        Product product;
        LogUtil.a("MemberInfoCardView", "onActivityResult resultCode: ", Integer.valueOf(i2));
        if (i != 10000) {
            return;
        }
        int intExtra = intent != null ? intent.getIntExtra("shoppingResult", -1) : -1;
        UserMemberInfo a2 = gpn.a();
        boolean z = a2 == null || a2.getMemberFlag() == -1;
        if (intExtra != 0 || (product = this.v) == null) {
            return;
        }
        dqj.d(this.v.getProductName(), 1, dpx.a(product) ? 2 : 3, !z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Product> b(Object obj) {
        if (!koq.e(obj, MemberTypeInfo.class)) {
            LogUtil.a("MemberInfoCardView", "objData not list of MemberTypeInfo");
            return null;
        }
        for (MemberTypeInfo memberTypeInfo : (List) obj) {
            if (memberTypeInfo != null && memberTypeInfo.getMemberType() == 1) {
                List<Product> products = memberTypeInfo.getProducts();
                if (!koq.b(products)) {
                    Collections.sort(products, new Comparator<Product>() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.10
                        @Override // java.util.Comparator
                        /* renamed from: c, reason: merged with bridge method [inline-methods] */
                        public int compare(Product product, Product product2) {
                            return product.getPriority() - product2.getPriority();
                        }
                    });
                    return products;
                }
            }
        }
        return null;
    }

    static class b implements IBaseResponseCallback {
        private List<String> b;
        private WeakReference<MemberInfoCardView> d;

        private b(MemberInfoCardView memberInfoCardView, List<String> list) {
            this.d = new WeakReference<>(memberInfoCardView);
            this.b = list;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[4];
            objArr[0] = "MemberProductStateCallback onResponse: errorCode = ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = " objData = ";
            objArr[3] = obj == null ? null : obj.toString();
            LogUtil.a("MemberInfoCardView", objArr);
            MemberInfoCardView memberInfoCardView = this.d.get();
            if (memberInfoCardView == null) {
                LogUtil.h("MemberInfoCardView", "onResponse: memberInfoCardView is null");
                return;
            }
            if (i != 0) {
                LogUtil.h("MemberInfoCardView", "getProductState FAIL.", Integer.valueOf(i));
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                Iterator<String> it = this.b.iterator();
                while (it.hasNext()) {
                    String optString = jSONObject.optString(it.next());
                    if (!TextUtils.isEmpty(optString) && optString.equals("3")) {
                        memberInfoCardView.f = true;
                        memberInfoCardView.c(gpn.a());
                        return;
                    }
                }
            } catch (JSONException e) {
                LogUtil.h("MemberInfoCardView", "getProductState JSONException e.", e.getMessage());
            }
        }
    }

    static class a implements HiCommonListener {
        private final WeakReference<MemberInfoCardView> c;

        public a(MemberInfoCardView memberInfoCardView) {
            this.c = new WeakReference<>(memberInfoCardView);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            final MemberInfoCardView memberInfoCardView;
            WeakReference<MemberInfoCardView> weakReference = this.c;
            if (weakReference == null || (memberInfoCardView = weakReference.get()) == null) {
                return;
            }
            if (!koq.e(obj, HiUserInfo.class)) {
                LogUtil.h("MemberInfoCardView", "getDataPlatformInfo data is null or error");
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("MemberInfoCardView", "getDataPlatformInfo userInfos is empty");
                return;
            }
            LogUtil.a("MemberInfoCardView", "fetchUserData onSuccess");
            final String name = ((HiUserInfo) list.get(0)).getName();
            if (TextUtils.isEmpty(name)) {
                LogUtil.h("MemberInfoCardView", "username is empty");
            } else {
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.marketing.views.MemberInfoCardView.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        nsy.cMr_(memberInfoCardView.w, name);
                    }
                });
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("MemberInfoCardView", "getDataPlatformInfo onFailure.");
        }
    }

    static class c implements Observer {
        private final WeakReference<MemberInfoCardView> d;

        public c(MemberInfoCardView memberInfoCardView) {
            this.d = new WeakReference<>(memberInfoCardView);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            MemberInfoCardView memberInfoCardView = this.d.get();
            if (TextUtils.equals(str, "MEMBER_TYPE_SELECTED") && objArr != null && memberInfoCardView != null && objArr.length > 0 && (objArr[0] instanceof Product)) {
                LogUtil.a("MemberInfoCardView", "selected product updated");
                memberInfoCardView.v = (Product) objArr[0];
            }
            if (!TextUtils.equals(str, "USER_MEMBER_INFO_UPDATE") || memberInfoCardView == null) {
                return;
            }
            c(memberInfoCardView, objArr);
            memberInfoCardView.g();
        }

        private void c(MemberInfoCardView memberInfoCardView, Object... objArr) {
            LogUtil.a("MemberInfoCardView", "user member info updated");
            memberInfoCardView.i();
            if (objArr != null && objArr.length > 0) {
                Object obj = objArr[0];
                if (obj instanceof UserMemberInfo) {
                    memberInfoCardView.c((UserMemberInfo) obj);
                    return;
                }
            }
            memberInfoCardView.h();
        }
    }
}
