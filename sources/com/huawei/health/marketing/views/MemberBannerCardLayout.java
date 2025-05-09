package com.huawei.health.marketing.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.marketing.views.MemberBannerCardLayout;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.DeviceBenefits;
import defpackage.eim;
import defpackage.gpn;
import defpackage.koq;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class MemberBannerCardLayout extends SportBannerLayout {
    private List<LinearLayout> g;
    private Observer h;
    private c j;
    private int k;
    private MemberInfoCardView n;

    @Override // com.huawei.health.marketing.views.SportBannerLayout
    protected boolean getIsForceWholeScreen() {
        return true;
    }

    public MemberBannerCardLayout(Context context) {
        super(context);
        this.h = new a(this);
        this.g = new ArrayList();
        this.k = 2;
        ObserverManagerUtil.d(this.h, "REFRESH_MEMBER_INFO_CARD_VIEW");
        this.j = new c();
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.j, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void apw_(FrameLayout frameLayout) {
        LogUtil.a("MemberBannerCardLayout", "addMemberInfoView");
        View view = this.n;
        if (view != null) {
            frameLayout.removeView(view);
        }
        this.n = new MemberInfoCardView(this.c, this.g, this.k);
        setNeedExtraHeight(true);
        LogUtil.a("MemberBannerCardLayout", "addMemberInfoView getDeviceBenefits");
        eim.e(new d(this));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 81;
        frameLayout.addView(this.n, layoutParams);
        setExtraCardHeight(getCardHeight());
    }

    @Override // com.huawei.health.marketing.views.SportBannerLayout
    protected void c() {
        apw_(this.i);
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        this.e.setFocusBoxColor(-1);
        this.e.setFocusDotColor(-1);
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen._2131363129_res_0x7f0a0539);
            layoutParams2.gravity = 81;
            this.e.setLayoutParams(layoutParams2);
            this.e.bringToFront();
        }
        this.e.bringToFront();
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams3.height = -2;
        layoutParams3.setMargins(0, 0, 0, 0);
        this.i.setLayoutParams(layoutParams3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCardHeight() {
        MemberInfoCardView memberInfoCardView = this.n;
        if (memberInfoCardView == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = memberInfoCardView.getLayoutParams();
        this.n.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 0), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 0));
        return this.n.getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class d implements IBaseResponseCallback {
        private WeakReference<MemberBannerCardLayout> b;

        public d(MemberBannerCardLayout memberBannerCardLayout) {
            this.b = new WeakReference<>(memberBannerCardLayout);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MemberBannerCardLayout memberBannerCardLayout = this.b.get();
            if (memberBannerCardLayout != null) {
                if (memberBannerCardLayout.n == null) {
                    LogUtil.h("MemberBannerCardLayout", "memberInfoView is null");
                    return;
                } else {
                    LogUtil.a("MemberBannerCardLayout", "GetVipInfoResponseCallback errorCode = ", Integer.valueOf(i));
                    a(i, obj, memberBannerCardLayout);
                    return;
                }
            }
            LogUtil.h("MemberBannerCardLayout", "MemberBannerCardLayout is null");
        }

        private void a(int i, Object obj, final MemberBannerCardLayout memberBannerCardLayout) {
            boolean isBrowseMode = LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode();
            memberBannerCardLayout.n.setMsgViewList(null);
            memberBannerCardLayout.g.clear();
            if (i == 0 && koq.e(obj, DeviceBenefits.class) && !isBrowseMode) {
                List list = (List) obj;
                LogUtil.a("MemberBannerCardLayout", "handleResultType deviceBenefitsList is ", list.toString());
                if (koq.c(list)) {
                    List<LinearLayout> d = gpn.d(memberBannerCardLayout.c, (List<DeviceBenefits>) list, false);
                    LogUtil.a("MemberBannerCardLayout", "handleResultType deviceBenefitViewList is ", d);
                    if (koq.c(d)) {
                        LogUtil.a("MemberBannerCardLayout", "handleResultType deviceBenefitViewList isNotEmpty");
                        memberBannerCardLayout.setNeedExtraHeight(true);
                        memberBannerCardLayout.n.setMsgViewList(d);
                        memberBannerCardLayout.g.addAll(d);
                        memberBannerCardLayout.k = 1;
                        HandlerExecutor.e(new AnonymousClass5(memberBannerCardLayout));
                        return;
                    }
                }
            }
            LogUtil.a("MemberBannerCardLayout", "handleResultType downcode");
            if (memberBannerCardLayout.k == 1) {
                LogUtil.a("MemberBannerCardLayout", "handleResultType showType change");
                memberBannerCardLayout.k = 2;
                memberBannerCardLayout.setNeedExtraHeight(false);
                memberBannerCardLayout.n.d = false;
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.marketing.views.MemberBannerCardLayout.d.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Handler handler = memberBannerCardLayout.n.e;
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.what = 11;
                        handler.sendMessage(obtainMessage);
                        memberBannerCardLayout.a();
                    }
                });
            }
        }

        /* renamed from: com.huawei.health.marketing.views.MemberBannerCardLayout$d$5, reason: invalid class name */
        public class AnonymousClass5 implements Runnable {
            final /* synthetic */ MemberBannerCardLayout d;

            AnonymousClass5(MemberBannerCardLayout memberBannerCardLayout) {
                this.d = memberBannerCardLayout;
            }

            @Override // java.lang.Runnable
            public void run() {
                Handler handler = this.d.n.e;
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 10;
                handler.sendMessage(obtainMessage);
                if (this.d.n.d) {
                    return;
                }
                final MemberBannerCardLayout memberBannerCardLayout = this.d;
                handler.post(new Runnable() { // from class: eke
                    @Override // java.lang.Runnable
                    public final void run() {
                        MemberBannerCardLayout.d.AnonymousClass5.b(MemberBannerCardLayout.this);
                    }
                });
            }

            public static /* synthetic */ void b(MemberBannerCardLayout memberBannerCardLayout) {
                memberBannerCardLayout.setExtraCardHeight(memberBannerCardLayout.getCardHeight());
                memberBannerCardLayout.a();
            }
        }
    }

    static class a implements Observer {
        private final WeakReference<MemberBannerCardLayout> e;

        public a(MemberBannerCardLayout memberBannerCardLayout) {
            this.e = new WeakReference<>(memberBannerCardLayout);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            final MemberBannerCardLayout memberBannerCardLayout = this.e.get();
            if (memberBannerCardLayout == null) {
                return;
            }
            LogUtil.a("MemberBannerCardLayout", "initMoreView now");
            int cardHeight = memberBannerCardLayout.getCardHeight();
            if (!memberBannerCardLayout.getNeedExtraHeight() || memberBannerCardLayout.getExtraCardHeight() != cardHeight) {
                LogUtil.a("MemberBannerCardLayout", "height change, layout.resetLayout");
                memberBannerCardLayout.setNeedExtraHeight(true);
                memberBannerCardLayout.setExtraCardHeight(cardHeight);
                memberBannerCardLayout.a();
            }
            LogUtil.a("MemberBannerCardLayout", "notify getDeviceBenefits");
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.marketing.views.MemberBannerCardLayout.a.5
                @Override // java.lang.Runnable
                public void run() {
                    eim.e(new d(memberBannerCardLayout));
                }
            });
        }
    }

    static class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("MemberBannerCardLayout", "device connect state changed");
            ObserverManagerUtil.c("REFRESH_MEMBER_INFO_CARD_VIEW", true);
        }
    }

    @Override // com.huawei.health.marketing.views.SportBannerLayout, com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("MemberBannerCardLayout", "on destroy");
        ObserverManagerUtil.e(this.h, "REFRESH_MEMBER_INFO_CARD_VIEW");
        BaseApplication.getContext().unregisterReceiver(this.j);
        super.onDestroy();
    }

    @Override // com.huawei.health.marketing.views.SportBannerLayout, com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onActivityResult(int i, int i2, Intent intent) {
        MemberInfoCardView memberInfoCardView = this.n;
        if (memberInfoCardView != null) {
            memberInfoCardView.apA_(i, i2, intent);
        }
    }
}
