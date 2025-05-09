package com.huawei.ui.homehealth.healthheadlinecard;

import android.content.Context;
import android.media.session.PlaybackState;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.gms.common.util.BiConsumer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.marqueetext.HealthHeadLineMarqueeTextView;
import com.huawei.ui.commonui.utils.TagResourcePolicy;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder;
import com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import com.huawei.ui.main.R$string;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.enq;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.ojw;
import defpackage.oko;
import defpackage.okx;
import defpackage.oli;
import defpackage.oly;
import defpackage.omn;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthHeadLinesCardViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private String f9457a;
    private volatile String b;
    private final ImageView c;
    private final HealthTextView d;
    private final oli e;
    private final HealthHeadLineMarqueeTextView g;
    private final ImageView i;

    static class d extends MediaStatusSubscriberAdapter {
        private WeakReference<HealthHeadLinesCardViewHolder> c;

        /* synthetic */ d(HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder, AnonymousClass1 anonymousClass1) {
            this(healthHeadLinesCardViewHolder);
        }

        private d(HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder) {
            this.c = new WeakReference<>(healthHeadLinesCardViewHolder);
        }

        @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder = this.c.get();
            if (healthHeadLinesCardViewHolder == null) {
                LogUtil.b("HealthHeadLinesCardViewHolder", "holder ref is null");
            } else {
                healthHeadLinesCardViewHolder.d(playbackState.getState());
            }
        }

        @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
        public void onMediaChanged(enq enqVar, int i) {
            HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder = this.c.get();
            if (healthHeadLinesCardViewHolder != null) {
                if (healthHeadLinesCardViewHolder.e == null) {
                    LogUtil.b("HealthHeadLinesCardViewHolder", "mMediaCenter is null");
                    return;
                } else {
                    LogUtil.a("HealthHeadLinesCardViewHolder", "onMediaChanged audioItem = ", enqVar, ", itemIndex = ", Integer.valueOf(i));
                    healthHeadLinesCardViewHolder.d(enqVar);
                    return;
                }
            }
            LogUtil.b("HealthHeadLinesCardViewHolder", "holder ref is null");
        }
    }

    private void dci_(View view) {
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelOffset2 = getContext().getResources().getDimensionPixelOffset(R.dimen._2131362365_res_0x7f0a023d);
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(dimensionPixelOffset + ((Integer) safeRegionWidth.first).intValue());
            marginLayoutParams.setMarginEnd(dimensionPixelOffset2 + ((Integer) safeRegionWidth.second).intValue());
            view.setLayoutParams(marginLayoutParams);
        }
    }

    HealthHeadLinesCardViewHolder(HealthHeadLinesCardData healthHeadLinesCardData, View view, Context context, boolean z) {
        super(view, context, z);
        this.e = oli.a();
        dci_(view);
        this.g = (HealthHeadLineMarqueeTextView) view.findViewById(R.id.health_headline_song_name);
        this.d = (HealthTextView) view.findViewById(R.id.health_headline_description);
        this.c = (ImageView) view.findViewById(R.id.health_headlines_cover);
        ImageView imageView = (ImageView) view.findViewById(R.id.health_headlines_play_or_pause);
        this.i = imageView;
        view.setOnClickListener(new View.OnClickListener() { // from class: okl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthHeadLinesCardViewHolder.this.dcj_(view2);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: okm
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return HealthHeadLinesCardViewHolder.this.dck_(view2);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: okn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthHeadLinesCardViewHolder.dcg_(HealthHeadLinesCardViewHolder.this, view2);
            }
        });
        c();
    }

    public /* synthetic */ void dcj_(View view) {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: okf
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HealthHeadLinesCardViewHolder.this.e(i, obj);
            }
        }, "");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            e();
        } else {
            LogUtil.h("HealthHeadLinesCardViewHolder", "getViewClickListener errorCode = ", Integer.valueOf(i));
        }
    }

    public /* synthetic */ boolean dck_(View view) {
        LogUtil.a("HealthHeadLinesCardViewHolder", "OnLongClickListener true");
        a();
        a(1);
        return true;
    }

    private void c() {
        this.e.a(new d(this, null));
    }

    public void d(enq enqVar, int i) {
        d(enqVar);
        d(i);
    }

    /* renamed from: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ enq d;

        AnonymousClass1(enq enqVar) {
            this.d = enqVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            HealthHeadLinesCardViewHolder.this.b = this.d.h();
            HealthHeadLinesCardViewHolder.this.g.setText(this.d.n());
            HealthHeadLinesCardViewHolder.this.d.setText(this.d.f());
            if (HealthHeadLinesCardViewHolder.this.f9457a == null || !HealthHeadLinesCardViewHolder.this.f9457a.equals(this.d.e())) {
                HealthHeadLinesCardViewHolder.this.c.setImageDrawable(null);
                ImageView imageView = HealthHeadLinesCardViewHolder.this.c;
                String e = this.d.e();
                int i = nrf.d;
                String h = this.d.h();
                final enq enqVar = this.d;
                nrf.cJf_(imageView, e, i, h, new TagResourcePolicy() { // from class: okk
                    @Override // com.huawei.ui.commonui.utils.TagResourcePolicy
                    public final boolean filterTagResource(String str) {
                        return HealthHeadLinesCardViewHolder.AnonymousClass1.this.b(enqVar, str);
                    }
                });
                HealthHeadLinesCardViewHolder.this.f9457a = this.d.e();
            }
            LogUtil.a("HealthHeadLinesCardViewHolder", "refreshUi, media info = ", this.d);
        }

        public /* synthetic */ boolean b(enq enqVar, String str) {
            return TextUtils.equals(HealthHeadLinesCardViewHolder.this.b, enqVar.h());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enq enqVar) {
        HandlerExecutor.e(new AnonymousClass1(enqVar));
    }

    public void d(final int i) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HealthHeadLinesCardViewHolder", "refreshPlaybackStateUi, playState:", Integer.valueOf(i));
                if (i != 6) {
                    HealthHeadLinesCardViewHolder.this.i.setImageResource(i == 3 ? R.drawable._2131430235_res_0x7f0b0b5b : R.drawable._2131430253_res_0x7f0b0b6d);
                    jcf.bEz_(HealthHeadLinesCardViewHolder.this.i, nsf.h(i == 3 ? R.string._2130839739_res_0x7f0208bb : R.string._2130845280_res_0x7f021e60));
                    HealthHeadLinesCardViewHolder.this.g.setSelected(i == 3);
                    return;
                }
                LogUtil.a("HealthHeadLinesCardViewHolder", "playState is loading, don't change the icon");
            }
        });
    }

    private void a() {
        LogUtil.a("HealthHeadLinesCardViewHolder", "showDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(getContext());
        builder.b(R.string.IDS_health_headlines_title);
        builder.d(R.string._2130839692_res_0x7f02088c);
        builder.c(true);
        builder.cyU_(R.string._2130845109_res_0x7f021db5, new View.OnClickListener() { // from class: okh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthHeadLinesCardViewHolder.this.dcl_(view);
            }
        });
        builder.cyR_(R.string._2130839643_res_0x7f02085b, new View.OnClickListener() { // from class: oki
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthHeadLinesCardViewHolder.this.dcm_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dcl_(View view) {
        LogUtil.a("HealthHeadLinesCardViewHolder", "cancel");
        a(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dcm_(View view) {
        LogUtil.a("HealthHeadLinesCardViewHolder", "healthHeadlines not display");
        if (TextUtils.equals(oli.a().q(), "headLine")) {
            LogUtil.a("HealthHeadLinesCardViewHolder", "hide headline card, set pause when playing health headline.");
            oli.a().a(-1);
        }
        oko.e(false);
        ObserverManagerUtil.c("HOME_RECYCLE_VIEW_MOVE", false, false);
        a(3);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        LogUtil.a("HealthHeadLinesCardViewHolder", "onDestroy");
        oli.a().e(new enq());
        oli.a().d();
    }

    private void e() {
        LogUtil.a("HealthHeadLinesCardViewHolder", "goToH5Page");
        bzs e = bzs.e();
        enq f = oli.a().f();
        String d2 = f != null ? f.d() : "";
        H5proUtil.initH5pro();
        H5ProClient.startH5MiniProgram(getContext(), "com.huawei.health.h5.health-headlines", new H5ProLaunchOption.Builder().addCustomizeJsModule("healthHeadlines", okx.class).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement")).addCustomizeArg("clickPosition", "normal").addCustomizeArg("date", d2).addCustomizeArg("enterPosition", "2").setImmerse().setStatusBarTextBlack(true).enableSlowWholeDocumentDraw().showStatusBar().build());
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        LogUtil.a("HealthHeadLinesCardViewHolder", "HEALTH_HEADLINES_CLOSE_1100054 healthHeadLinesMap = ", hashMap);
        ixx.d().d(getContext(), AnalyticsValue.HEALTH_HEADLINES_CLOSE_1100054.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder.4
                @Override // java.lang.Runnable
                public void run() {
                    HealthHeadLinesCardViewHolder.this.e(i);
                }
            });
        } else {
            this.i.setImageResource(i);
        }
    }

    private void dch_(View view) {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: okj
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HealthHeadLinesCardViewHolder.this.c(i, obj);
            }
        }, "");
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i != 0) {
            LogUtil.b("HealthHeadLinesCardViewHolder", "playOrPauseIconClickEvent login reject");
        } else {
            d();
        }
    }

    private void d() {
        final oli a2 = oli.a();
        List<enq> t = a2.t();
        if (koq.b(t)) {
            LogUtil.a("HealthHeadLinesCardViewHolder", "playList is empty, need to setPlayList again!");
            final BiConsumer biConsumer = new BiConsumer() { // from class: okg
                @Override // com.google.android.gms.common.util.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    HealthHeadLinesCardViewHolder.this.b(a2, (List) obj, (List) obj2);
                }
            };
            omn.d(new MediaCenterCallback() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder.5
                @Override // com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback
                public void onResponse(int i, String str) {
                    LogUtil.a("HealthHeadLinesCardViewHolder", "checkWifiDialogBeforePlay when play = ", Integer.valueOf(i));
                    if (i == 0) {
                        ojw.a((BiConsumer<List<enq>, List<oly>>) biConsumer, "", 0, "");
                    } else {
                        LogUtil.a("HealthHeadLinesCardViewHolder", "check mobile data dialog click cancel");
                    }
                }
            });
            return;
        }
        if ("headLine" != a2.q()) {
            a2.b(t, a2.c(), a2.g(), null);
            a2.y();
            e(R$drawable.ic_pause_hh);
            a2.c(a2.g(), 1);
            return;
        }
        int e = a2.e();
        LogUtil.a("HealthHeadLinesCardViewHolder", "playOrPauseIconClickEvent state: ", Integer.valueOf(e));
        if (e != 3) {
            if (!CommonUtil.aa(getContext())) {
                nrh.c(getContext(), getContext().getResources().getString(R$string.IDS_network_connect_error));
                return;
            } else {
                omn.d(new MediaCenterCallback() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardViewHolder.3
                    @Override // com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback
                    public void onResponse(int i, String str) {
                        LogUtil.a("HealthHeadLinesCardViewHolder", "checkWifiDialogBeforePlay when play = ", Integer.valueOf(i));
                        if (i == 0) {
                            HealthHeadLinesCardViewHolder.this.e(R$drawable.ic_pause_hh);
                            a2.d(1);
                        } else {
                            LogUtil.a("HealthHeadLinesCardViewHolder", "check mobile data dialog click cancel");
                        }
                    }
                });
                jcf.bEz_(this.i, nsf.h(R.string._2130839739_res_0x7f0208bb));
                return;
            }
        }
        jcf.bEz_(this.i, nsf.h(R.string._2130845280_res_0x7f021e60));
        e(R$drawable.ic_play_hh);
        a2.ad();
    }

    public /* synthetic */ void b(oli oliVar, List list, List list2) {
        if (koq.b(list)) {
            LogUtil.b("HealthHeadLinesCardViewHolder", "audioItems is empty");
            return;
        }
        oliVar.b(list, list2, oliVar.g(), null);
        oliVar.y();
        e(R$drawable.ic_pause_hh);
        oliVar.c(0L, 1);
    }

    public static void dcg_(HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder, View view) {
        healthHeadLinesCardViewHolder.dch_(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
