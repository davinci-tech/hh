package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.HiCommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hmj {
    private Context b;
    private String c;
    private HealthTextView d;
    private HealthTextView e;
    private String k;
    private ConstraintLayout l;
    private InterfaceHiMap m;
    private View.OnClickListener n;
    private eml o;
    private View p;
    private SharedPreferences r;
    private HealthBubbleLayout s;
    private List<String> t = null;
    private List<Integer> q = null;
    private boolean h = false;
    private boolean g = true;
    private emf i = null;
    private String f = null;

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f13249a = null;
    private boolean j = false;

    public hmj(Context context, View view, View.OnClickListener onClickListener) {
        this.b = context;
        this.p = view;
        this.n = onClickListener;
        a();
    }

    private void a() {
        this.l = (ConstraintLayout) this.p.findViewById(R.id.route_draw_layout_tab);
        HealthTextView healthTextView = (HealthTextView) this.p.findViewById(R.id.route_draw_btn_track);
        this.d = healthTextView;
        healthTextView.setOnClickListener(this.n);
        HealthTextView healthTextView2 = (HealthTextView) this.p.findViewById(R.id.route_draw_btn_shape);
        this.e = healthTextView2;
        healthTextView2.setOnClickListener(this.n);
        HealthBubbleLayout healthBubbleLayout = (HealthBubbleLayout) this.p.findViewById(R.id.route_draw_switch_bubble);
        this.s = healthBubbleLayout;
        healthBubbleLayout.setOnClickListener(new View.OnClickListener() { // from class: hmn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                hmj.this.bkZ_(view);
            }
        });
    }

    /* synthetic */ void bkZ_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(boolean z) {
        if (this.m == null || this.h == z) {
            return;
        }
        this.h = z;
        if (this.g) {
            d();
            b();
            this.g = false;
        }
        c(this.d, !z);
        c(this.e, z);
        this.m.setOverlayVisible(this.k, z);
        List<String> list = this.t;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.m.setLineVisible(it.next(), z);
                LogUtil.c("Track_RouteDrawViewHolder", "setLineVisible");
            }
        }
        List<Integer> list2 = this.q;
        if (list2 != null) {
            for (Integer num : list2) {
                if (z) {
                    this.m.showMarker(num.intValue(), null);
                } else {
                    this.m.hideMarker(num.intValue(), null);
                }
            }
        }
        if (z) {
            this.m.changeTrackColor(this.b.getResources().getColor(R.color._2131299052_res_0x7f090aec));
        } else {
            this.m.resetTrackColor();
        }
    }

    public void e(Map<String, String> map, final e eVar, final a aVar) {
        String str = map.get("hotPathId");
        this.f = map.get("cpPunchState");
        hjr.d(str, new HotTrackDrawCustomTarget<Bitmap>() { // from class: hmj.5
            @Override // com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget
            public void onGetHotTrackDetailInfo(enc encVar) {
                if (encVar != null) {
                    hmj.this.j = true;
                    hmj.this.c = encVar.e();
                    hmj.this.i = encVar.a();
                    if (hmj.this.i == null) {
                        LogUtil.b("Track_RouteDrawViewHolder", "cpPoint is null in onGetHotTrackDetailInfo");
                        return;
                    }
                    hmj hmjVar = hmj.this;
                    hmjVar.o = hmjVar.i.c();
                    nrf.b(encVar.m().b(), aVar);
                    return;
                }
                LogUtil.b("Track_RouteDrawViewHolder", "hotPathDetailInfo is null in onGetHotTrackDetailInfo");
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: bla_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                hmj.this.f13249a = bitmap;
                e eVar2 = eVar;
                if (eVar2 != null) {
                    eVar2.onSuccess(null);
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.h("Track_RouteDrawViewHolder", "onLoadCleared");
            }
        });
    }

    public void e() {
        ConstraintLayout constraintLayout = this.l;
        if (constraintLayout == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mLayoutRouteDrawTab is null in mRouteDrawViewHolder");
            return;
        }
        if (!this.j) {
            LogUtil.b("Track_RouteDrawViewHolder", "failed to request route draw data");
            return;
        }
        constraintLayout.setVisibility(0);
        SharedPreferences sharedPreferences = this.b.getSharedPreferences(Integer.toString(20002), 0);
        this.r = sharedPreferences;
        if (sharedPreferences == null) {
            LogUtil.h("Track_RouteDrawViewHolder", "sharedPreferences is null in showTrackAndShapeButton");
        } else if (sharedPreferences.getBoolean("displayedSwitchPreviewBubble", false)) {
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
        }
    }

    private void b() {
        this.s.setVisibility(8);
        SharedPreferences sharedPreferences = this.r;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("displayedSwitchPreviewBubble", true);
            edit.apply();
        }
    }

    public void d(InterfaceHiMap interfaceHiMap, boolean z) {
        this.m = interfaceHiMap;
        if (z) {
            e();
        }
        c(this.i, this.f, this.c);
        bkY_(this.o, this.f13249a, this.c);
    }

    private void bkY_(eml emlVar, Bitmap bitmap, String str) {
        if (this.m == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in drawRouteDrawBoard");
            return;
        }
        if (emlVar == null || bitmap == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "param is null in drawRouteDrawBoard");
            return;
        }
        this.k = this.m.addOverlay(new hlj().e(gwe.c(gwe.c(emlVar.e()), str, this.m.getMapEngineType())).d((float) emlVar.d()).a((float) emlVar.c()).bhS_(bitmap).c(11.0f).a(false));
    }

    private void c(emf emfVar, String str, String str2) {
        if (this.m == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in drawShapeAndCpPoint");
            return;
        }
        if (emfVar == null) {
            LogUtil.h("Track_RouteDrawViewHolder", "cpPoint is null in drawRouteDrawShape");
            return;
        }
        List<GpsPoint> e2 = emfVar.e();
        if (HiCommonUtil.d(e2)) {
            LogUtil.h("Track_RouteDrawViewHolder", "cpGpsList is null in loadCpPoint");
            return;
        }
        ArrayList arrayList = new ArrayList(e2.size());
        Iterator<GpsPoint> it = e2.iterator();
        while (it.hasNext()) {
            arrayList.add(gwe.c(it.next()));
        }
        List<hjd> c = gwe.c(arrayList, str2, this.m.getMapEngineType());
        b(c, str);
        d(c, emfVar.a());
    }

    private void b(List<hjd> list, String str) {
        if (this.m == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in drawCpPoint");
            return;
        }
        String[] split = str.substring(1, str.length() - 1).split(",");
        if (split.length != list.size()) {
            LogUtil.b("Track_RouteDrawViewHolder", "param is error in drawCpPoint");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            hjd hjdVar = list.get(i);
            hlg hlgVar = new hlg();
            hlgVar.d(hjdVar).bhQ_(nrf.cHF_(nsf.cKq_("1".equals(split[i]) ? R.drawable._2131431310_res_0x7f0b0f8e : R.drawable._2131431311_res_0x7f0b0f8f))).b(true).e(0.5f, 0.5f).a(false).a(13);
            if (this.q == null) {
                this.q = new ArrayList();
            }
            this.q.add(Integer.valueOf(this.m.addMarker(hlgVar, null)));
            if (i == list.size() - 1) {
                this.m.addMarker(hlgVar, null);
            }
        }
    }

    private void d(List<hjd> list, List<emg> list2) {
        if (this.m == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in drawRouteDrawShape");
            return;
        }
        for (emg emgVar : list2) {
            hjd hjdVar = list.get(emgVar.e());
            hjd hjdVar2 = list.get(emgVar.b());
            hle hleVar = new hle();
            hleVar.a(false).a(hjdVar).c(hjdVar2).c(BaseApplication.getContext().getColor(R.color._2131296821_res_0x7f090235)).b(hag.a(8.0f)).c(false).a(12.0f);
            if (this.t == null) {
                this.t = new ArrayList();
            }
            this.t.add(this.m.drawLines(hleVar));
        }
    }

    public void d() {
        if (this.m == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in moveRouteDrawShapeCenter");
            return;
        }
        eml emlVar = this.o;
        if (emlVar == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "mMap is null in moveRouteDrawShapeCenter");
            return;
        }
        this.m.animateCamera(new hlc().e(gwe.c(gwe.c(emlVar.e()), (int) this.o.d())).a(hag.a(36.0f)).e((float) this.o.c()), 0);
        this.m.scrollBy(0.0f, (this.p == null || this.l == null) ? 0.0f : (r0.getHeight() - this.l.getBottom()) / 2.0f);
    }

    private void c(HealthTextView healthTextView, boolean z) {
        if (healthTextView == null) {
            LogUtil.b("Track_RouteDrawViewHolder", "button is null in changeRouteDrawBtnBack");
        } else if (z) {
            healthTextView.setBackgroundResource(R.drawable._2131431302_res_0x7f0b0f86);
            healthTextView.setTextColor(BaseApplication.getContext().getColor(R.color._2131299017_res_0x7f090ac9));
        } else {
            healthTextView.setBackgroundResource(R.drawable._2131428733_res_0x7f0b057d);
            healthTextView.setTextColor(BaseApplication.getContext().getColor(R.color._2131299018_res_0x7f090aca));
        }
    }

    public void c() {
        this.l = null;
        this.d = null;
        this.e = null;
    }

    public static class e extends UiCallback {
        private final WeakReference<TrackScreenFrag> d;

        public e(TrackScreenFrag trackScreenFrag) {
            this.d = new WeakReference<>(trackScreenFrag);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_RouteDrawViewHolder", "RouteDrawUiCallback is fail");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onSuccess(Object obj) {
            TrackScreenFrag trackScreenFrag = this.d.get();
            if (trackScreenFrag != null) {
                trackScreenFrag.d();
            } else {
                LogUtil.b("Track_RouteDrawViewHolder", "success trackScreenFrag is null");
            }
        }
    }

    public static class a extends CustomTarget<Bitmap> {
        private final WeakReference<TrackScreenFrag> b;

        public a(TrackScreenFrag trackScreenFrag) {
            this.b = new WeakReference<>(trackScreenFrag);
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: blb_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
            TrackScreenFrag trackScreenFrag = this.b.get();
            if (trackScreenFrag != null) {
                trackScreenFrag.bgm_(bitmap);
            } else {
                LogUtil.b("Track_RouteDrawViewHolder", "trackScreenFrag is null in ThumbnailCallback");
            }
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
            LogUtil.h("Track_RouteDrawViewHolder", "ThumbnailCallback onLoadCleared");
        }
    }
}
