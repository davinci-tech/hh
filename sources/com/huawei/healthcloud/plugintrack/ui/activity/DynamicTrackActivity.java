package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils;
import com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.DynamicCurve;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.MusicManager;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelector;
import com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor;
import com.huawei.healthcloud.plugintrack.ui.adapter.CustomMapStyleAdapter;
import com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter;
import com.huawei.healthcloud.plugintrack.ui.constraints.DynamicTrackContract;
import com.huawei.healthcloud.plugintrack.ui.fragment.PersonalizedTrackFragment;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.healthcloud.plugintrack.ui.presenters.DynamicTrackPresenter;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackDialogViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cwa;
import defpackage.dpg;
import defpackage.gwg;
import defpackage.hah;
import defpackage.hak;
import defpackage.han;
import defpackage.hbk;
import defpackage.hbz;
import defpackage.hcd;
import defpackage.hci;
import defpackage.hcj;
import defpackage.hcm;
import defpackage.hcr;
import defpackage.hgi;
import defpackage.hgj;
import defpackage.hji;
import defpackage.hkt;
import defpackage.koq;
import defpackage.kts;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class DynamicTrackActivity extends BaseActivity implements DynamicTrackContract.Iview, CommonSingleCallback<Boolean> {
    private static boolean d = false;
    private LinearLayout aa;
    private HealthRecycleView ab;
    private LinearLayout ac;
    private HealthRecycleView ad;
    private ConstraintLayout ae;
    private HealthTextView af;
    private CustomTextAlertDialog ag;
    private hgi ai;
    private HealthTextView aj;
    private HealthTextView ak;
    private HealthTextView al;
    private HealthTextView am;
    private HealthTextView an;
    private RelativeLayout ao;
    private HealthTextView ap;
    private HealthTextView aq;
    private HealthTextView ar;
    private HealthRadioButton as;
    private RelativeLayout au;
    private HealthRadioButton av;
    private ImageView aw;
    private HealthTextView ax;
    private boolean ay;
    private hkt az;
    private hcd b;
    private boolean bg;
    private HealthTextView bo;
    private RelativeLayout bp;
    private HealthTextView br;
    private hak bu;
    private hah bw;
    private RelativeLayout bx;
    private CommonDialog21 bz;
    private HealthTextView c;
    private HealthTextView cd;
    private MapInteractor ce;
    private LinearLayout cg;
    private List<hak> ch;
    private LinearLayout ci;
    private HealthTextView cj;
    private HealthTextView ck;
    private ImageView cl;
    private CustomMapStyleAdapter co;
    private List<hah> cp;
    private LinearLayout cr;
    private FrameLayout cs;
    private HealthTextView ct;
    private ImageView cu;
    private HealthTextView cw;
    private FrameLayout cx;
    private LinearLayout cy;
    private ImageView cz;
    private LinearLayout da;
    private LinearLayout db;
    private HealthTextView dc;
    private ImageView dd;
    private FrameLayout de;
    private ImageView df;
    private HealthTextView dg;
    private FrameLayout dh;
    private ImageView di;
    private HealthTextView dj;
    private LinearLayout dk;
    private FrameLayout dl;
    private LinearLayout dm;

    /* renamed from: do, reason: not valid java name */
    private HealthTextView f2do;
    private hgj dq;
    private RelativeLayout dr;
    private HealthRadioButton ds;
    private RelativeLayout dt;
    private MusicManager du;
    private PersonalizedTrackFragment dv;
    private HealthTextView dw;
    private HealthButton dx;
    private RelativeLayout dy;
    private HealthTextView dz;
    private HealthCheckBox e;
    private int eb;
    private HealthTextView ed;
    private DynamicTrackContract.Ipresenter ee;
    private int eg;
    private RelativeLayout eh;
    private HealthTextView ek;
    private RelativeLayout el;
    private ImageView em;
    private ShareSelector en;
    private String eo;
    private LinearLayout ep;
    private RelativeLayout eq;
    private LinearLayout er;
    private RelativeLayout es;
    private HealthRadioButton et;
    private ImageView eu;
    private HealthTextView ev;
    private ImageView ew;
    private HealthTextView ex;
    private TrackDialogViewHolder ey;
    private VideoView ez;
    private LinearLayout f;
    private ImageView fa;
    private FrameLayout fc;
    private ImageView fd;
    private String fe;
    private Bitmap ff;
    private HealthTextView fg;
    private CustomViewDialog fh;
    private LinearLayout fi;
    private HealthTextView fj;
    private View fm;
    private ImageView fn;
    private HealthTextView g;
    private LinearLayout h;
    private HealthDivider i;
    private LinearLayout j;
    private ImageView l;
    private HealthTextView m;
    private ImageView n;
    private ImageButton o;
    private String p;
    private Context q;
    private hak r;
    private String s;
    private MapTypeDescription.MapType t;
    private hah u;
    private String v;
    private HealthTextView w;
    private RelativeLayout x;
    private LinearLayout y;
    private HealthTextView z;
    private boolean bm = false;
    private HiMapHolder ba = null;
    private int cm = -1;
    private InterfaceHiMap cf = null;
    private ReTrackSimplify ea = null;
    private ArrayList<LatLong> ef = null;
    private ArrayList<LenLatLong> ca = null;
    private hbk ei = null;

    /* renamed from: a, reason: collision with root package name */
    private a f3641a = null;
    private InterfaceReTrack cc = null;
    private DynamicCurve ah = null;
    private boolean bj = false;
    private boolean bi = false;
    private boolean bb = false;
    private MapTypeDescription.MapType bt = MapTypeDescription.MapType.MAP_TYPE_SATELLITE;
    private int cb = 0;
    private int bs = 0;
    private boolean bd = true;
    private int ej = 0;
    private String fb = "retrack_mode_complex";
    private boolean bc = false;
    private boolean bl = true;
    private boolean bq = false;
    private boolean bh = false;
    private boolean bf = false;
    private boolean be = false;
    private int bn = -1;
    private int bv = -1;
    private HashMap<Integer, List<PhotoModel>> cv = null;
    private Map<PhotoModel, Bitmap> ec = new HashMap();
    private HashMap<Integer, ArrayList<PhotoModel>> dp = new HashMap<>();
    private HashMap<Integer, List<Bitmap>> cn = new HashMap<>();
    private Runnable by = null;
    private boolean bk = false;
    private IBaseResponseCallback cq = new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 1) {
                DynamicTrackActivity dynamicTrackActivity = DynamicTrackActivity.this;
                dynamicTrackActivity.r = dynamicTrackActivity.bu;
                DynamicTrackActivity dynamicTrackActivity2 = DynamicTrackActivity.this;
                dynamicTrackActivity2.t = dynamicTrackActivity2.bt;
                DynamicTrackActivity dynamicTrackActivity3 = DynamicTrackActivity.this;
                dynamicTrackActivity3.bn = dynamicTrackActivity3.bs;
                DynamicTrackActivity dynamicTrackActivity4 = DynamicTrackActivity.this;
                dynamicTrackActivity4.bv = dynamicTrackActivity4.cb;
                DynamicTrackActivity dynamicTrackActivity5 = DynamicTrackActivity.this;
                dynamicTrackActivity5.u = dynamicTrackActivity5.bw;
                if (DynamicTrackActivity.this.cm != DynamicTrackActivity.this.eb) {
                    DynamicTrackActivity dynamicTrackActivity6 = DynamicTrackActivity.this;
                    dynamicTrackActivity6.cm = dynamicTrackActivity6.eb;
                    DynamicTrackActivity.this.bbA_(null);
                }
                DynamicTrackActivity dynamicTrackActivity7 = DynamicTrackActivity.this;
                dynamicTrackActivity7.d(dynamicTrackActivity7.bt, DynamicTrackActivity.this.r);
                DynamicTrackActivity.this.cc.setCustomMarkInfo(DynamicTrackActivity.this.u);
                DynamicTrackActivity.this.cc.resetLine();
                return;
            }
            if (!(obj instanceof List)) {
                LogUtil.b("Track_DynamicTrackActivity", "data exception");
                DynamicTrackActivity.this.bh();
                DynamicTrackActivity.this.ak();
                return;
            }
            List list = (List) obj;
            if (koq.e(list.get(0), hak.class)) {
                DynamicTrackActivity.this.ch = (List) list.get(0);
                if (!DynamicTrackActivity.this.bm && DynamicTrackActivity.this.ch != null && DynamicTrackActivity.this.ch.size() >= 9) {
                    DynamicTrackActivity.this.ch.remove(2);
                }
                if (DynamicTrackActivity.this.bm && DynamicTrackActivity.this.ch != null && DynamicTrackActivity.this.ch.size() == 8) {
                    DynamicTrackActivity.this.n();
                }
            }
            if (koq.e(list.get(1), hah.class)) {
                DynamicTrackActivity.this.cp = (List) list.get(1);
            }
            if (koq.b(DynamicTrackActivity.this.ch)) {
                DynamicTrackActivity.this.bh();
            }
            if (DynamicTrackActivity.this.bm && DynamicTrackActivity.this.ch.size() == 2) {
                DynamicTrackActivity.this.n();
            }
            if (!DynamicTrackActivity.this.bm && DynamicTrackActivity.this.ch.size() == 3) {
                DynamicTrackActivity.this.ch.remove(2);
            }
            DynamicTrackActivity.this.ak();
        }
    };
    private Handler dn = new c(Looper.getMainLooper(), this);
    private View.OnClickListener k = new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.15
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null) {
                LogUtil.b("Track_DynamicTrackActivity", "view is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (nsn.a(500)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view.getId() == R.id.retrack_start_retrack) {
                DynamicTrackActivity.this.ba();
            } else if (view.getId() == R.id.retrack_back_btn) {
                DynamicTrackActivity.this.v();
            } else if (view.getId() == R.id.track_edit_ok_btn) {
                DynamicTrackActivity.this.bc();
                DynamicTrackActivity.this.a(true);
            } else if (view.getId() == R.id.retrack_normal_video_rl) {
                DynamicTrackActivity.this.j(false);
            } else if (view.getId() == R.id.retrack_fast_video_rl) {
                DynamicTrackActivity.this.j(true);
            } else if (view.getId() == R.id.track_personalized_btn) {
                DynamicTrackActivity.this.bi();
            } else if (view.getId() == R.id.switch_layout) {
                DynamicTrackActivity.this.bo();
                hcj.a(DynamicTrackActivity.this.q, DynamicTrackActivity.this.ea.getSportType(), DynamicTrackActivity.this.ay, 1);
            } else {
                LogUtil.b("Track_DynamicTrackActivity", "error view id.");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private IBaseResponseCallback at = new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.14
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 1 && obj != null && (obj instanceof hcm)) {
                hcm hcmVar = (hcm) obj;
                hcmVar.a(DynamicTrackActivity.this.v);
                hcmVar.d(DynamicTrackActivity.this.eo);
                hcmVar.c(DynamicTrackActivity.this.p);
                hcmVar.a(!"retrack_mode_complex".equals(DynamicTrackActivity.this.fb) ? 1 : 0);
                hcmVar.c(DynamicTrackActivity.this.ay);
                hcmVar.e(DynamicTrackActivity.this.ea.getSportType());
                hcj.e(DynamicTrackActivity.this.q, hcmVar);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        hak hakVar = new hak();
        hakVar.c("true");
        hakVar.a(5);
        hakVar.e(true);
        hakVar.c(false);
        this.ch.add(2, hakVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ba() {
        kts.d(1);
        if (this.ej == 4 || nsn.ac(this.q)) {
            bp();
            this.bi = true;
        } else {
            bd();
            this.bi = false;
        }
        hcj.d(this.q, this.ea.getSportType(), this.ay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bo() {
        DynamicTrackContract.Ipresenter ipresenter = this.ee;
        if (ipresenter != null) {
            ipresenter.switchPremiumEdition(this.bm);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(boolean z) {
        if (this.bq && !this.bm) {
            i(z);
        } else {
            h(z);
        }
    }

    public void initAlbumView(View view) {
        hcd hcdVar = this.b;
        if (hcdVar != null) {
            hcdVar.aZD_(view);
            this.b.e(this.ay);
            this.b.b(this.dp, this.ec, this.cn);
            this.b.i();
        }
    }

    public void i() {
        hcd hcdVar = this.b;
        if (hcdVar != null) {
            hcdVar.g();
        }
    }

    public void h() {
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.resetLine();
        }
    }

    public void o() {
        MusicManager musicManager = this.du;
        if (musicManager != null) {
            musicManager.a();
        }
    }

    public void g() {
        hgj hgjVar = this.dq;
        if (hgjVar != null) {
            hgjVar.b();
        }
    }

    public void b(boolean z) {
        this.b.d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bi() {
        if (this.dv == null) {
            this.dv = new PersonalizedTrackFragment();
        }
        this.dv.c(this.eg);
        this.dv.b(this.bm);
        this.b.a(this.bm);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!this.dv.isAdded()) {
            beginTransaction.add(R.id.track_retrack_root, this.dv);
        }
        beginTransaction.show(this.dv);
        beginTransaction.commit();
        ai();
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.changeEditStatus(true);
        }
        hcj.a(this.q, this.ea.getSportType(), this.ay);
    }

    private void ai() {
        this.ci.setVisibility(8);
        this.dx.setVisibility(8);
        this.ep.setVisibility(8);
        this.en.setVisibility(8);
        this.y.setVisibility(8);
        this.dt.setVisibility(8);
        this.aa.setVisibility(8);
        this.j.setVisibility(8);
        this.n.setVisibility(0);
        this.ae.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.dv == null) {
            LogUtil.h("Track_DynamicTrackActivity", "[hidePersonalizedView] mPersonalizedTrackFragment is null.");
            return;
        }
        if (!z) {
            hcd hcdVar = this.b;
            if (hcdVar != null && hcdVar.c()) {
                this.b.e();
                return;
            }
            hcd hcdVar2 = this.b;
            if (hcdVar2 != null && hcdVar2.b()) {
                this.b.d();
                return;
            }
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!this.dv.isAdded()) {
            LogUtil.h("Track_DynamicTrackActivity", "[hidePersonalizedView] mPersonalizedTrackFragment is not added.");
            return;
        }
        beginTransaction.hide(this.dv);
        beginTransaction.commit();
        q();
        hgj hgjVar = this.dq;
        if (hgjVar != null) {
            hgjVar.e(z);
        }
        d(z);
        e(z);
    }

    private void d(boolean z) {
        HealthRadioButton healthRadioButton = this.ds;
        if (healthRadioButton == null || this.e == null || !z || this.av == null || this.cc == null) {
            return;
        }
        d(healthRadioButton.isChecked(), this.av.isChecked());
        this.fb = this.ds.isChecked() ? "retrack_mode_simple" : "retrack_mode_complex";
        this.bl = this.e.isChecked();
        bb();
        this.cc.setIsSupportArea(this.bl);
    }

    private void e(boolean z) {
        hcd hcdVar = this.b;
        if (hcdVar != null) {
            hcdVar.b(this.at);
        }
        if (!z) {
            this.cq.d(1, null);
        }
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.changeEditStatus(false);
        }
    }

    private void q() {
        this.ci.setVisibility(0);
        al();
        this.dx.setVisibility(0);
        nsy.cMA_(this.ep, 8);
        this.dt.setVisibility(0);
        this.aa.setVisibility(0);
        this.j.setVisibility(0);
        this.n.setVisibility(8);
        this.ae.setClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        this.ch = new ArrayList();
        LogUtil.b("Track_DynamicTrackActivity", "mMapStyleInfo data is null ");
        if (this.cm == 1) {
            for (int i = 1; i < 5; i++) {
                hak hakVar = new hak();
                hakVar.c("true");
                hakVar.a(i);
                this.ch.add(hakVar);
            }
            return;
        }
        hak hakVar2 = new hak();
        hakVar2.c("true");
        hakVar2.a(1);
        this.ch.add(hakVar2);
        hak hakVar3 = new hak();
        hakVar3.c("true");
        hakVar3.a(3);
        this.ch.add(hakVar3);
        if (this.bm) {
            n();
        }
    }

    public void chooseMusic(View view) {
        if (view == null) {
            LogUtil.b("Track_DynamicTrackActivity", "[chooseMusic] view is null");
            return;
        }
        if (CommonUtil.aa(this.q) || !DynamicTrackDownloadUtils.d().g()) {
            this.dq.bdh_(view);
        } else if (DynamicTrackDownloadUtils.d().g()) {
            nrh.d(BaseApplication.getContext(), getString(R.string._2130841392_res_0x7f020f30));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.en.getVisibility() == 0) {
            LogUtil.a("Track_DynamicTrackActivity", "setPreviewStatus by exitCurrentAct");
            bj();
            InterfaceReTrack interfaceReTrack = this.cc;
            if (interfaceReTrack != null) {
                interfaceReTrack.reset();
                return;
            }
            return;
        }
        if (this.n.getVisibility() == 0) {
            a(false);
        } else if (this.b.a()) {
            bl();
        } else {
            finish();
        }
    }

    public void b() {
        if (this.cc.getTotalDuration() >= 15000) {
            br();
        } else if (this.bq) {
            e();
        } else {
            h(false);
        }
    }

    private void h(final boolean z) {
        TrackDialogViewHolder trackDialogViewHolder;
        if (this.ey == null) {
            this.ey = new TrackDialogViewHolder(this.q).c(new CommonSingleCallback() { // from class: hel
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    DynamicTrackActivity.this.b((Boolean) obj);
                }
            }).a(new CommonSingleCallback() { // from class: heh
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    DynamicTrackActivity.this.c((Boolean) obj);
                }
            }).d(new CommonSingleCallback() { // from class: hej
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    DynamicTrackActivity.this.e(z, (Boolean) obj);
                }
            });
        }
        CustomViewDialog customViewDialog = this.fh;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        if (this.bm && !this.bq && (trackDialogViewHolder = this.ey) != null) {
            trackDialogViewHolder.d(this.bc);
        } else {
            i(z);
        }
    }

    public /* synthetic */ void b(Boolean bool) {
        this.bc = bool.booleanValue();
    }

    public /* synthetic */ void c(Boolean bool) {
        this.bq = bool.booleanValue();
    }

    public /* synthetic */ void e(boolean z, Boolean bool) {
        if (bool.booleanValue()) {
            be();
            i(z);
        }
    }

    private void be() {
        SharedPreferences.Editor edit = this.q.getSharedPreferences("retrack_file", 0).edit();
        edit.putBoolean("retrack_play_mode_tip_key", this.bq);
        edit.putBoolean("retrack_play_mode_key", this.bc);
        edit.commit();
    }

    private void br() {
        if (this.fh == null) {
            this.fh = new CustomViewDialog.Builder(this.q).a(this.q.getString(R.string._2130839882_res_0x7f02094a)).czg_(this.fm).czd_(this.q.getString(R.string._2130841130_res_0x7f020e2a).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DynamicTrackActivity.this.fh.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.fh.show();
    }

    private void bd() {
        PermissionUtil.b(this.q, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.q) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.17
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                if (DynamicTrackActivity.this.ei == null) {
                    DynamicTrackActivity dynamicTrackActivity = DynamicTrackActivity.this;
                    dynamicTrackActivity.ei = new hbk(dynamicTrackActivity, dynamicTrackActivity);
                }
                DynamicTrackActivity.this.ei.d(DynamicTrackActivity.this.cc.getTotalDuration());
                DynamicTrackActivity.this.b();
            }
        });
    }

    private void i(boolean z) {
        CustomViewDialog customViewDialog = this.fh;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        hbk hbkVar = this.ei;
        if (hbkVar != null) {
            hbkVar.d(z);
            e();
        }
    }

    public void e() {
        PermissionUtil.b(this, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.q) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.16
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                DynamicTrackActivity.this.ei.j();
            }
        });
        this.ej = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        this.bj = false;
        hbk hbkVar = this.ei;
        if (hbkVar != null) {
            hbkVar.i();
            this.ei.g();
        }
        this.du.a();
        l();
        aa();
        e(this.ea.getValidTotalDistance());
        getWindow().clearFlags(128);
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "handleEndRetrack.");
        kts.c(1);
    }

    private void l() {
        a aVar = this.f3641a;
        if (aVar != null) {
            aVar.a();
            this.f3641a = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Track_DynamicTrackActivity", "onCreate.");
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (!d) {
            LogUtil.h("Track_DynamicTrackActivity", "is not from track detail activity");
            finish();
        }
        cancelAdaptRingRegion();
        this.q = this;
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.track_retrack);
        int d2 = gwg.d(this.q);
        this.cm = d2;
        this.eb = d2;
        if (!az()) {
            ReleaseLogUtil.c("Track_DynamicTrackActivity", "init data fail, finish.");
            finish();
            return;
        }
        this.ee = new DynamicTrackPresenter(this);
        getLifecycle().addObserver(this.ee);
        am();
        ax();
        au();
        MusicManager musicManager = new MusicManager(this);
        this.du = musicManager;
        musicManager.b();
        this.cc = new ReTrackDisplay(this.dn, this.ef, this.ca, this.ea).e(true).setContext(this.q).setTrackPhoto(this.ew, this.eu, this.fd).setTrackVideo(this.ez).setTrackPhotoBackground(this.fc);
        HiMapHolder hiMapHolder = (HiMapHolder) findViewById(R.id.sport_track_map);
        this.ba = hiMapHolder;
        hiMapHolder.setRadius(0.0f);
        bbB_(bundle);
        this.ai = new hgi(this);
        aj();
        a();
        k();
        hcj.a(this.q, this.ea.getSportType(), this.ay, 0);
        hcd hcdVar = new hcd(this.q, this.cc);
        this.b = hcdVar;
        hcdVar.aZG_(this.dn);
        this.cc.setScreenWidth(getResources().getDisplayMetrics().widthPixels);
        this.cc.setScreenHeight(getResources().getDisplayMetrics().heightPixels);
        this.cc.setIsHideKmMarker(this.ea.getSportType() == 222);
        m();
        DynamicTrackContract.Ipresenter ipresenter = this.ee;
        if (ipresenter != null) {
            ipresenter.judgeVipStatus();
        }
    }

    private void aj() {
        han.e();
        this.dq = new hgj(this);
        this.ce = new MapInteractor(this, this.q, this.cm);
        this.dq.b(new CustomMusicStyleAdapter.MusicItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.18
            @Override // com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.MusicItemClickListener
            public void onClickListener(String str, String str2) {
                DynamicTrackActivity.this.eo = str2;
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.MusicItemClickListener
            public void onMuteClick() {
                DynamicTrackActivity.this.eo = null;
            }
        });
        this.ce.e(new MapInteractor.MarkResetListener() { // from class: hei
            @Override // com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.MarkResetListener
            public final void onResetMarker(hah hahVar, int i) {
                DynamicTrackActivity.this.e(hahVar, i);
            }
        });
    }

    public /* synthetic */ void e(hah hahVar, int i) {
        this.bv = i;
        this.u = hahVar;
        this.cb = i;
        this.bw = hahVar;
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.setCustomMarkInfo(hahVar);
            this.cc.reset();
        }
        bc();
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity$19, reason: invalid class name */
    class AnonymousClass19 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ Bundle b;
        final /* synthetic */ View d;

        AnonymousClass19(View view, Bundle bundle) {
            this.d = view;
            this.b = bundle;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            if (DynamicTrackActivity.this.isFinishing()) {
                return;
            }
            DynamicTrackActivity.this.dn.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.19.3
                @Override // java.lang.Runnable
                public void run() {
                    if (DynamicTrackActivity.this.isFinishing() || DynamicTrackActivity.this.bh) {
                        return;
                    }
                    NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(DynamicTrackActivity.this.q);
                    builder.e(R.string._2130841392_res_0x7f020f30).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.19.3.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            DynamicTrackActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    NoTitleCustomAlertDialog e = builder.e();
                    e.setCanceledOnTouchOutside(false);
                    e.show();
                }
            }, 15000L);
            DynamicTrackActivity.this.bbA_(this.b);
        }
    }

    private void bbB_(Bundle bundle) {
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass19(decorView, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bbA_(final Bundle bundle) {
        int i = this.cm;
        if (i == 1) {
            this.ba.bhK_(null, this.q);
            bbz_(bundle);
        } else {
            if (i == 2) {
                this.ba.a(this.q, (Fragment) null, new SyncMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.21
                    @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
                    public void onMapReady(InterfaceHiMap interfaceHiMap) {
                        DynamicTrackActivity.this.bbz_(bundle);
                    }
                });
                return;
            }
            if (i == 3) {
                this.ck.setVisibility(8);
                this.ck.setText(this.q.getResources().getString(R.string._2130845162_res_0x7f021dea));
                this.ba.b(this.q, new SyncMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.5
                    @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
                    public void onMapReady(InterfaceHiMap interfaceHiMap) {
                        DynamicTrackActivity.this.bbz_(bundle);
                    }
                });
            } else {
                this.ba.bhJ_(null, this.q);
                bbz_(bundle);
            }
        }
    }

    private void bf() {
        if (this.bk) {
            LinearLayout linearLayout = this.h;
            if (linearLayout != null) {
                linearLayout.setClickable(false);
            }
            ConstraintLayout constraintLayout = this.ae;
            if (constraintLayout != null) {
                constraintLayout.setClickable(false);
            }
            InterfaceHiMap interfaceHiMap = this.cf;
            if (interfaceHiMap != null) {
                interfaceHiMap.setAllGesturesEnabled(true);
            }
            InterfaceReTrack interfaceReTrack = this.cc;
            if (interfaceReTrack != null) {
                interfaceReTrack.setCustomMarkInfo(null);
                this.cc.resetLine();
            }
            LogUtil.a("Track_DynamicTrackActivity", "setMapGesturesEnabled: ", this.t);
            this.bk = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bbz_(Bundle bundle) {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "initMapDisplayEngine with ", Integer.valueOf(this.cm));
        InterfaceHiMap hiMap = this.ba.getHiMap();
        this.cf = hiMap;
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack instanceof ReTrackEngine) {
            ((ReTrackEngine) interfaceReTrack).setMapEngine(hiMap);
            this.cc.onCreate(bundle);
            this.cc.onResume();
            this.cc.reset();
        }
        as();
        bf();
    }

    private void k() {
        if (this.q == null) {
            LogUtil.a("Track_DynamicTrackActivity", "showAbnormalTrackDialog mContext is null");
        } else {
            if (this.ea.getIsCompleteTrack()) {
                return;
            }
            ReleaseLogUtil.e("Track_DynamicTrackActivity", "gps exist abnormal data.");
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.q);
            builder.e(this.q.getString(R.string._2130839889_res_0x7f020951)).czz_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.e().show();
        }
    }

    public void f() {
        if (this.dr != null && this.ao != null && this.e != null) {
            this.ds.setChecked(!"retrack_mode_complex".equals(this.fb));
            this.as.setChecked("retrack_mode_complex".equals(this.fb));
            this.e.setChecked(this.bl);
        }
        HealthRadioButton healthRadioButton = this.av;
        if (healthRadioButton != null && this.et != null) {
            if (this.bc && this.bm) {
                healthRadioButton.setChecked(true);
                this.et.setChecked(false);
            } else {
                healthRadioButton.setChecked(false);
                this.et.setChecked(true);
            }
        }
        nsy.cMA_(this.fi, this.bm ? 0 : 8);
        nsy.cMA_(this.fj, this.bm ? 0 : 8);
    }

    private void d(boolean z, boolean z2) {
        SharedPreferences.Editor edit = this.q.getSharedPreferences("retrack_file", 0).edit();
        if (z) {
            this.cc.setAlgorithmStrength(TrackAnimationControl.Strength.LOW);
            edit.putString("retrack_mode_key", "retrack_mode_simple");
        } else {
            this.cc.setAlgorithmStrength(TrackAnimationControl.Strength.HIGH);
            edit.putString("retrack_mode_key", "retrack_mode_complex");
        }
        edit.putBoolean("retrack_play_mode_key", z2);
        edit.commit();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("Track_DynamicTrackActivity", "onDestroy.");
        super.onDestroy();
        bc();
        this.bj = false;
        Handler handler = this.dn;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.onDestroy();
        }
        MusicManager musicManager = this.du;
        if (musicManager != null) {
            musicManager.a();
        }
        hbk hbkVar = this.ei;
        if (hbkVar != null) {
            hbkVar.g();
            this.ei.c();
        }
        if (han.e().d()) {
            ReleaseLogUtil.e("Track_DynamicTrackActivity", "Abnormal end");
            han.e().c();
            han.a();
        }
        DynamicTrackDownloadUtils.e();
        l();
        HiMapHolder hiMapHolder = this.ba;
        if (hiMapHolder != null) {
            hiMapHolder.b();
        }
        if (this.by != null) {
            ThreadPoolManager.d().a(this.by);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        PersonalizedTrackFragment personalizedTrackFragment;
        LogUtil.a("Track_DynamicTrackActivity", "onStart.");
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (this.dv == null && fragments.size() > 0) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Iterator<Fragment> it = fragments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    personalizedTrackFragment = null;
                    break;
                }
                Fragment next = it.next();
                if (next instanceof PersonalizedTrackFragment) {
                    personalizedTrackFragment = (PersonalizedTrackFragment) next;
                    break;
                }
            }
            if (personalizedTrackFragment != null) {
                beginTransaction.hide(personalizedTrackFragment);
                beginTransaction.remove(personalizedTrackFragment);
                beginTransaction.commit();
            }
        }
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "onResume.");
        super.onResume();
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.onResume();
        }
        if (this.en.getVisibility() == 0) {
            return;
        }
        if (this.bj && this.bb) {
            LogUtil.a("Track_DynamicTrackActivity", "is authorizeProcess return");
            return;
        }
        if (this.cc != null) {
            LogUtil.a("Track_DynamicTrackActivity", "setPreviewStatus by onResume");
            this.cc.reset();
            PersonalizedTrackFragment personalizedTrackFragment = this.dv;
            if (personalizedTrackFragment == null || personalizedTrackFragment.isHidden()) {
                bj();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "onPause.");
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "onStop.");
        super.onStop();
        this.bj = false;
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.onPause();
        }
        MusicManager musicManager = this.du;
        if (musicManager != null) {
            musicManager.a();
        }
        hbk hbkVar = this.ei;
        if (hbkVar != null) {
            hbkVar.g();
        }
        l();
        r();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("Track_DynamicTrackActivity", "onBackPressed.");
        if (this.bj || this.en.getVisibility() == 0) {
            ReleaseLogUtil.e("Track_DynamicTrackActivity", "setPreviewStatus by onBackPressed");
            this.bj = false;
            bj();
            InterfaceReTrack interfaceReTrack = this.cc;
            if (interfaceReTrack != null) {
                interfaceReTrack.reset();
                return;
            }
            return;
        }
        PersonalizedTrackFragment personalizedTrackFragment = this.dv;
        if (personalizedTrackFragment != null && !personalizedTrackFragment.isHidden()) {
            a(false);
            return;
        }
        hcd hcdVar = this.b;
        if (hcdVar != null && hcdVar.a()) {
            bl();
        } else {
            super.onBackPressed();
        }
    }

    private boolean az() {
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.c("Track_DynamicTrackActivity", "intent is null");
            return false;
        }
        Bundle bundleExtra = intent.getBundleExtra("track_detail_data_bundle");
        if (bundleExtra == null) {
            ReleaseLogUtil.c("Track_DynamicTrackActivity", "bundle is null.");
            return false;
        }
        if (bundleExtra.getSerializable("simplify_data") instanceof ReTrackSimplify) {
            this.ea = (ReTrackSimplify) bundleExtra.getSerializable("simplify_data");
            if (hbz.b(bundleExtra.getSerializable("lens_data"), LenLatLong.class)) {
                this.ca = (ArrayList) bundleExtra.getSerializable("lens_data");
                if (hbz.b(bundleExtra.getSerializable("retrack_data"), LatLong.class)) {
                    this.ef = (ArrayList) bundleExtra.getSerializable("retrack_data");
                    if (hbz.b(bundleExtra.getSerializable("matched_photos"), Integer.class, PhotoModel.class)) {
                        this.cv = (HashMap) bundleExtra.getSerializable("matched_photos");
                    }
                    this.az = new hkt(0.0f, 0.2f, 0.2f, 1.0f);
                    return true;
                }
                ReleaseLogUtil.c("Track_DynamicTrackActivity", "mRetrackData is null, finish this activity.");
                return false;
            }
            ReleaseLogUtil.c("Track_DynamicTrackActivity", "mLensData is null, finish this activity.");
            return false;
        }
        ReleaseLogUtil.c("Track_DynamicTrackActivity", "mReTrackSy is null, finish this activity.");
        return false;
    }

    private void au() {
        this.fe = hcr.b();
        this.ff = hcr.bah_(this.q);
        if (!TextUtils.isEmpty(this.fe)) {
            this.fg.setText(this.fe);
        }
        Bitmap bitmap = this.ff;
        if (bitmap != null) {
            this.fa.setImageBitmap(bitmap);
        }
        this.ah.e(this.ea, this.ef);
        this.ev.setText(UnitUtil.a("yyyy/M/d", this.ea.getStartTimeStamp()));
        e(this.ea.getValidTotalDistance());
        t();
        bg();
        if (this.ea.getDeviceId() <= 0 && TextUtils.isEmpty(this.ea.getProductId())) {
            this.ar.setText(this.q.getString(R.string.IDS_app_name_health));
        } else {
            int deviceId = this.ea.getDeviceId();
            Context context = this.q;
            String d2 = cwa.d(deviceId, context, context.getPackageName(), this.ea.getProductId());
            HealthTextView healthTextView = this.ar;
            if (TextUtils.isEmpty(d2)) {
                d2 = this.q.getString(R.string.IDS_app_name_health);
            }
            healthTextView.setText(d2);
        }
        if (LanguageUtil.bc(this.q)) {
            this.l.setBackground(nrz.cKn_(this.q, R.drawable._2131431201_res_0x7f0b0f21));
            this.o.setBackgroundResource(R.drawable._2131431805_res_0x7f0b117d);
            this.ah.setRotationY(180.0f);
            this.em.setRotationY(180.0f);
        } else {
            this.l.setBackgroundResource(R.drawable._2131431201_res_0x7f0b0f21);
            this.o.setBackgroundResource(R.drawable._2131431806_res_0x7f0b117e);
        }
        bm();
        cp(this);
    }

    private void t() {
        if (this.ea.getSportType() == 222) {
            this.ex.setVisibility(8);
            this.es.setVisibility(8);
            this.er.setVisibility(0);
            int[] c2 = dpg.c(this.ea.getTotalTime());
            int i = c2[0];
            int i2 = c2[1];
            int i3 = c2[2];
            if (i != 0) {
                this.aj.setText(UnitUtil.e(i, 1, 0));
                this.am.setText(this.q.getResources().getQuantityString(R.plurals._2130903331_res_0x7f030123, i, ""));
            }
            this.aq.setText(UnitUtil.e(i3, 1, 0));
            if (i == 0 && i2 == 0) {
                this.al.setText(this.q.getResources().getQuantityString(R.plurals._2130903370_res_0x7f03014a, i3, ""));
            } else {
                this.an.setText(UnitUtil.e(i2, 1, 0));
                this.ak.setText(this.q.getResources().getQuantityString(R.plurals._2130903379_res_0x7f030153, i2, ""));
                this.al.setText(this.q.getResources().getQuantityString(R.plurals._2130903368_res_0x7f030148, i3, ""));
            }
            this.bo.setText(hcr.c(this.ea.getAvgSpeedType(), (float) this.ea.getAvgSpeedField()));
            this.br.setText(hcr.d(this.q, this.ea.getAvgSpeedType()));
            this.br.setMaxWidth(R.dimen._2131363588_res_0x7f0a0704);
            ae();
            return;
        }
        this.ex.setText(hji.c(this.ea.getTotalTime()));
        this.dz.setText(hcr.c(this.ea.getAvgSpeedType(), (float) this.ea.getAvgSpeedField()));
        this.dw.setText(hcr.d(this.q, this.ea.getAvgSpeedType()));
        this.bo.setText(hji.c((int) this.ea.getTotalCalories()));
    }

    private void ae() {
        this.dz.setVisibility(8);
        this.dw.setVisibility(8);
    }

    private void bm() {
        if (this.q.getSharedPreferences("retrack_file", 0).getBoolean("has_use_personalized_map", false)) {
            return;
        }
        this.y.setVisibility(0);
    }

    private void as() {
        SharedPreferences sharedPreferences = this.q.getSharedPreferences("retrack_file", 0);
        String string = sharedPreferences.getString(hcr.e(this.cm), hcr.b(this.cm));
        if (this.t == null) {
            this.t = hcr.d(string);
        }
        int i = this.cm;
        if (i == 1) {
            this.r = (hak) new Gson().fromJson(sharedPreferences.getString("track_gaode_custom_map_69", null), hak.class);
            this.bn = sharedPreferences.getInt("track_mode_custom_map_position_69", -1);
        } else if (i == 2) {
            this.r = (hak) new Gson().fromJson(sharedPreferences.getString("new_track_google_custom_map", null), hak.class);
            this.bn = sharedPreferences.getInt("new_track_goolge_custom_map_position", -1);
        } else if (i == 3) {
            this.r = (hak) new Gson().fromJson(sharedPreferences.getString("new_track_hms_custom_map", null), hak.class);
            this.bn = sharedPreferences.getInt("new_track_hms_custom_map_position", -1);
        }
        this.cc.changeMapType(this.t, this.r);
        String string2 = sharedPreferences.getString("new_track_mode_custom_mark", null);
        this.bv = sharedPreferences.getInt("new_track_mode_custom_mark_position", -1);
        hah hahVar = (hah) new Gson().fromJson(string2, hah.class);
        this.u = hahVar;
        this.cc.setCustomMarkInfo(hahVar);
        this.bs = this.bn;
        this.bu = this.r;
        this.bt = this.t;
        this.cb = this.bv;
        this.bw = this.u;
        this.cc.reset();
        c(this.t, this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bc() {
        SharedPreferences.Editor edit = this.q.getSharedPreferences("retrack_file", 0).edit();
        int i = this.cm;
        if (i == 1) {
            edit.putString("track_gaode_custom_map_69", new Gson().toJson(this.r));
            edit.putInt("track_mode_custom_map_position_69", this.bn);
        } else if (i == 2) {
            edit.putString("new_track_google_custom_map", new Gson().toJson(this.r));
            edit.putInt("new_track_goolge_custom_map_position", this.bn);
        } else if (i == 3) {
            edit.putString("new_track_hms_custom_map", new Gson().toJson(this.r));
            edit.putInt("new_track_hms_custom_map_position", this.bn);
        }
        edit.putString("new_track_mode_custom_mark", new Gson().toJson(this.u));
        edit.putInt("new_track_mode_custom_mark_position", this.bv);
        edit.commit();
        this.bs = this.bn;
        this.bt = this.t;
        this.bu = this.r;
        this.bw = this.u;
        this.cb = this.bv;
    }

    private void d(int i) {
        LogUtil.a("Track_DynamicTrackActivity", "TextColor is ", Integer.valueOf(i));
        this.fg.setTextColor(i);
        this.ar.setTextColor(i);
        this.ev.setTextColor(i);
        this.af.setTextColor(i);
        this.z.setTextColor(i);
        this.ex.setTextColor(i);
        this.aj.setTextColor(i);
        this.am.setTextColor(i);
        this.an.setTextColor(i);
        this.ak.setTextColor(i);
        this.aq.setTextColor(i);
        this.al.setTextColor(i);
        this.dz.setTextColor(i);
        this.dw.setTextColor(i);
        this.bo.setTextColor(i);
        this.br.setTextColor(i);
        this.ct.setTextColor(i);
        this.cw.setTextColor(i);
        this.dj.setTextColor(i);
        this.f2do.setTextColor(i);
        this.dc.setTextColor(i);
        this.ax.setTextColor(i);
        this.dg.setTextColor(i);
        this.ek.setTextColor(i);
        this.m.setTextColor(i);
        this.c.setTextColor(i);
        this.em.setBackground(nrf.cJH_(this.q.getResources().getDrawable(hcr.a(this.ea.getSportType())), i));
        this.ck.setTextColor(i);
        if (i == -1) {
            this.w.setTextColor(this.q.getResources().getColor(R.color._2131296998_res_0x7f0902e6));
            this.cd.setTextColor(this.q.getResources().getColor(R.color._2131296998_res_0x7f0902e6));
            this.aw.setImageDrawable(this.q.getResources().getDrawable(R.drawable._2131428465_res_0x7f0b0471));
        } else {
            this.w.setTextColor(i);
            this.cd.setTextColor(i);
            this.aw.setImageDrawable(this.q.getResources().getDrawable(R.drawable._2131428466_res_0x7f0b0472));
        }
    }

    private void e(double d2) {
        this.af.setText(hcr.e(d2, this.q));
        this.z.setText(hcr.b(this.q));
    }

    private void am() {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
            final View decorView = getWindow().getDecorView();
            if (decorView == null) {
                LogUtil.b("Track_DynamicTrackActivity", "decorView is null.");
            } else {
                decorView.setSystemUiVisibility(1280);
                decorView.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
                        if (rootWindowInsets == null) {
                            LogUtil.b("Track_DynamicTrackActivity", "windowInsets is null");
                            return;
                        }
                        DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
                        if (displayCutout == null) {
                            LogUtil.b("Track_DynamicTrackActivity", "displayCutout is null.");
                            return;
                        }
                        DynamicTrackActivity.this.eg = displayCutout.getSafeInsetTop();
                        DynamicTrackActivity dynamicTrackActivity = DynamicTrackActivity.this;
                        dynamicTrackActivity.ae = (ConstraintLayout) dynamicTrackActivity.findViewById(R.id.retrack_content);
                        if (DynamicTrackActivity.this.ae.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) DynamicTrackActivity.this.ae.getLayoutParams();
                            layoutParams.topMargin = DynamicTrackActivity.this.eg;
                            DynamicTrackActivity.this.ae.setLayoutParams(layoutParams);
                        }
                        LogUtil.a("Track_DynamicTrackActivity", "current notch screen height is: ", Integer.valueOf(DynamicTrackActivity.this.eg));
                    }
                });
            }
        }
    }

    private void ax() {
        af();
        ah();
        av();
        aw();
        ag();
        ao();
        ar();
        at();
        an();
        aq();
        ap();
    }

    private void ar() {
        this.ew = (ImageView) findViewById(R.id.track_photo1);
        this.eu = (ImageView) findViewById(R.id.track_photo2);
        this.fd = (ImageView) findViewById(R.id.track_photo3);
        this.ez = (VideoView) findViewById(R.id.track_video);
        this.fc = (FrameLayout) findViewById(R.id.track_photo_background);
    }

    private void af() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.retrack_content);
        this.ae = constraintLayout;
        constraintLayout.setVisibility(8);
        this.h = (LinearLayout) findViewById(R.id.dynamic_track_background_layout);
        this.j = (LinearLayout) findViewById(R.id.dynamic_track_bottom_mask);
        this.bx = (RelativeLayout) findViewById(R.id.retrack_detail_map_londing_rl);
        ImageView imageView = (ImageView) findViewById(R.id.sport_track_map_mask);
        this.cl = imageView;
        if (this.cm == 2) {
            imageView.setVisibility(8);
        }
        this.ck = (HealthTextView) findViewById(R.id.retrack_map_logo);
    }

    private void ah() {
        this.cg = (LinearLayout) findViewById(R.id.retrack_health_logo_ll);
        this.cd = (HealthTextView) findViewById(R.id.retrack_logo_text);
        this.aw = (ImageView) findViewById(R.id.retrack_logo);
    }

    private void aw() {
        ImageButton imageButton = (ImageButton) findViewById(R.id.retrack_start_retrack);
        this.o = imageButton;
        imageButton.setOnClickListener(this.k);
        this.ci = (LinearLayout) findViewById(R.id.map_play_guide_layout);
        this.cj = (HealthTextView) findViewById(R.id.map_play_guide);
        al();
        ImageView imageView = (ImageView) findViewById(R.id.retrack_back_btn);
        this.l = imageView;
        imageView.setOnClickListener(this.k);
        ImageView imageView2 = (ImageView) findViewById(R.id.track_edit_ok_btn);
        this.n = imageView2;
        imageView2.setImageDrawable(this.q.getResources().getDrawable(R.drawable._2131431955_res_0x7f0b1213));
        this.n.setOnClickListener(this.k);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.map_style_guide_layout);
        this.y = linearLayout;
        linearLayout.setOnClickListener(this.k);
        HealthButton healthButton = (HealthButton) findViewById(R.id.track_personalized_btn);
        this.dx = healthButton;
        healthButton.setOnClickListener(this.k);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.switch_layout);
        this.ep = linearLayout2;
        nsy.cMA_(linearLayout2, 8);
        this.ed = (HealthTextView) findViewById(R.id.premium_edition_text);
        this.g = (HealthTextView) findViewById(R.id.basic_edition_text);
        this.fn = (ImageView) findViewById(R.id.vip_mark);
        this.ep.setOnClickListener(this.k);
    }

    private void ag() {
        this.ah = (DynamicCurve) findViewById(R.id.dynamicCurve);
        this.x = (RelativeLayout) findViewById(R.id.retrack_curve_title_rl);
        this.w = (HealthTextView) findViewById(R.id.retrack_curve_title);
    }

    private void ao() {
        this.af = (HealthTextView) findViewById(R.id.retrack_distance_value);
        Typeface createFromAsset = Typeface.createFromAsset(this.q.getAssets(), "fonts/hw-italic.ttf");
        this.af.setTypeface(createFromAsset);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.retrack_time_value);
        this.ex = healthTextView;
        healthTextView.setTypeface(createFromAsset);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.retrack_day_value_exp);
        this.aj = healthTextView2;
        healthTextView2.setTypeface(createFromAsset);
        this.am = (HealthTextView) findViewById(R.id.retrack_day_unit_exp);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.retrack_hour_value_exp);
        this.an = healthTextView3;
        healthTextView3.setTypeface(createFromAsset);
        this.ak = (HealthTextView) findViewById(R.id.retrack_hour_unit_exp);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.retrack_min_value_exp);
        this.aq = healthTextView4;
        healthTextView4.setTypeface(createFromAsset);
        this.al = (HealthTextView) findViewById(R.id.retrack_min_unit_exp);
        this.ex.setTypeface(createFromAsset);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.retrack_pace_value);
        this.dz = healthTextView5;
        healthTextView5.setTypeface(createFromAsset);
        HealthTextView healthTextView6 = (HealthTextView) findViewById(R.id.retrack_kcal_value);
        this.bo = healthTextView6;
        healthTextView6.setTypeface(createFromAsset);
        this.z = (HealthTextView) findViewById(R.id.retrack_distance_unit);
        this.es = (RelativeLayout) findViewById(R.id.retrack_time_rl);
        this.er = (LinearLayout) findViewById(R.id.retrack_time_rl_exp);
        this.dy = (RelativeLayout) findViewById(R.id.retrack_pace_rl);
        this.bp = (RelativeLayout) findViewById(R.id.retrack_kcal_rl);
        this.cy = (LinearLayout) findViewById(R.id.retrack_max_data_ll);
        this.f = (LinearLayout) findViewById(R.id.retrack_barrage_ll);
        this.em = (ImageView) findViewById(R.id.retrack_sport_type);
        this.dt = (RelativeLayout) findViewById(R.id.retrack_other_data_rl);
        this.aa = (LinearLayout) findViewById(R.id.retrack_distance_ll);
        this.em.setBackground(this.q.getResources().getDrawable(hcr.a(this.ea.getSportType())));
        this.dw = (HealthTextView) findViewById(R.id.retrack_pace_unit);
        this.br = (HealthTextView) findViewById(R.id.retrack_kcal_unit);
    }

    private void av() {
        this.fa = (ImageView) findViewById(R.id.retrack_user_header);
        this.fg = (HealthTextView) findViewById(R.id.retrack_user_name);
        this.ev = (HealthTextView) findViewById(R.id.retrack_track_date);
        this.ar = (HealthTextView) findViewById(R.id.retrack_gps_source);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        this.fg.setTextColor(i);
        this.ev.setTextColor(i);
        this.ar.setTextColor(i);
    }

    public void initViewSelectLayout(View view) {
        if (view == null) {
            LogUtil.b("Track_DynamicTrackActivity", "[initViewSelectLayout] view is null");
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.retrack_view_north_up);
        this.dr = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: hed
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DynamicTrackActivity.this.bbG_(view2);
            }
        });
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.retrack_view_follow_route);
        this.ao = relativeLayout2;
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: hec
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DynamicTrackActivity.this.bbH_(view2);
            }
        });
        this.fi = (LinearLayout) view.findViewById(R.id.retrack_play_video_type_layout);
        this.fj = (HealthTextView) view.findViewById(R.id.track_save_video_title);
        HealthTextView healthTextView = (HealthTextView) this.fi.findViewById(R.id.one_type);
        HealthTextView healthTextView2 = (HealthTextView) this.fi.findViewById(R.id.other_type);
        healthTextView.setText(getString(R.string._2130845844_res_0x7f022094, new Object[]{720}));
        healthTextView2.setText(getString(R.string._2130845845_res_0x7f022095, new Object[]{1080}));
        RelativeLayout relativeLayout3 = (RelativeLayout) this.fi.findViewById(R.id.standard_video);
        this.eq = relativeLayout3;
        relativeLayout3.setOnClickListener(new View.OnClickListener() { // from class: hef
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DynamicTrackActivity.this.bbI_(view2);
            }
        });
        RelativeLayout relativeLayout4 = (RelativeLayout) this.fi.findViewById(R.id.hd_video);
        this.au = relativeLayout4;
        relativeLayout4.setOnClickListener(new View.OnClickListener() { // from class: hee
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DynamicTrackActivity.this.bbJ_(view2);
            }
        });
        HealthRadioButton healthRadioButton = (HealthRadioButton) view.findViewById(R.id.retrack_view_north_up_radioButton);
        this.ds = healthRadioButton;
        healthRadioButton.setClickable(false);
        HealthRadioButton healthRadioButton2 = (HealthRadioButton) view.findViewById(R.id.retrack_view_follow_route_radioButton);
        this.as = healthRadioButton2;
        healthRadioButton2.setClickable(false);
        HealthRadioButton healthRadioButton3 = (HealthRadioButton) this.fi.findViewById(R.id.save_to_my_route_radio_button);
        this.et = healthRadioButton3;
        healthRadioButton3.setClickable(false);
        this.av = (HealthRadioButton) this.fi.findViewById(R.id.rb_export_route);
        this.et.setChecked(false);
        this.av.setChecked(false);
        this.av.setClickable(false);
        HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.retrack_view_area_checkBox);
        this.e = healthCheckBox;
        healthCheckBox.setClickable(false);
        bby_(view);
        f();
    }

    public /* synthetic */ void bbG_(View view) {
        c(this.ds, this.as);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bbH_(View view) {
        c(this.as, this.ds);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bbI_(View view) {
        this.bc = false;
        c(this.et, this.av);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bbJ_(View view) {
        this.bc = true;
        c(this.av, this.et);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bby_(View view) {
        this.i = (HealthDivider) view.findViewById(R.id.retrack_view_area_divider);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.retrack_view_area);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: heg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DynamicTrackActivity.this.bbF_(view2);
            }
        });
        if (this.cm == 1) {
            this.i.setVisibility(8);
            relativeLayout.setVisibility(8);
            this.bl = false;
            InterfaceReTrack interfaceReTrack = this.cc;
            if (interfaceReTrack != null) {
                interfaceReTrack.setIsSupportArea(false);
            }
        }
    }

    public /* synthetic */ void bbF_(View view) {
        HealthCheckBox healthCheckBox = this.e;
        if (healthCheckBox != null) {
            healthCheckBox.setChecked(!healthCheckBox.isChecked());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2) {
        if (healthRadioButton != null) {
            healthRadioButton.setChecked(true);
        }
        if (healthRadioButton2 != null) {
            healthRadioButton2.setChecked(false);
        }
    }

    private void bb() {
        SharedPreferences.Editor edit = this.q.getSharedPreferences("retrack_file", 0).edit();
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.setIsSupportArea(this.bl);
        }
        edit.putBoolean("retrack_area_key", this.bl);
        edit.commit();
    }

    private void ap() {
        SharedPreferences sharedPreferences = this.q.getSharedPreferences("retrack_file", 0);
        this.fb = sharedPreferences.getString("retrack_mode_key", "retrack_mode_complex");
        this.bl = sharedPreferences.getBoolean("retrack_area_key", true);
        this.bq = sharedPreferences.getBoolean("retrack_play_mode_tip_key", false);
        this.bc = sharedPreferences.getBoolean("retrack_play_mode_key", false);
    }

    private void aq() {
        this.en = (ShareSelector) findViewById(R.id.share_selector);
        if (Utils.o()) {
            this.en.setShareWeChatFriendsVisibility(8);
            this.en.setShareWeChatVisibility(8);
            this.en.setSinaVisibility(8);
            this.en.setFamilyVisibility(8);
            this.en.setLocalShareVisibility(8);
        } else {
            this.en.setMoreVisibility(8);
        }
        this.en.setClickCallback(new ShareSelectorClickCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.2
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onWeChatShare() {
                DynamicTrackActivity.this.e(1);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onWeChatFriendShare() {
                DynamicTrackActivity.this.e(2);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onSinaShare() {
                DynamicTrackActivity.this.e(3);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onFamilyShare() {
                DynamicTrackActivity.this.e(11);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onLocalShare() {
                DynamicTrackActivity.this.e(12);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelectorClickCallback
            public void onMoreShare() {
                DynamicTrackActivity.this.e(5);
            }
        });
    }

    private void bk() {
        if (this.bz == null) {
            this.bz = CommonDialog21.a(this.q);
        }
        this.bz.setCancelable(false);
        this.bz.e(this.q.getString(R.string._2130841528_res_0x7f020fb8));
        this.bz.a();
    }

    private void r() {
        CommonDialog21 commonDialog21 = this.bz;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.bz.dismiss();
        this.bz = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        hcj.e(this.q, this.ea.getSportType(), this.ay, i);
        if (!CommonUtil.aa(this.q)) {
            nrh.d(BaseApplication.getContext(), getString(R.string._2130841392_res_0x7f020f30));
            return;
        }
        if (i == 12) {
            if (this.ei == null) {
                this.ei = new hbk(this, this);
            }
            this.ei.h();
            return;
        }
        if (i == 5) {
            this.ai.bde_(this.ei.a(), this.ea.getSportType(), "shareSystem", this.ei.aYu_());
            return;
        }
        if (i == 11) {
            bk();
            this.ai.bde_(this.ei.a(), this.ea.getSportType(), "sharefamily", this.ei.aYu_());
            return;
        }
        if (Utils.c(this.q, b(i))) {
            if (i == 2 || i == 3) {
                a(i);
                return;
            } else {
                if (i == 1) {
                    this.ai.bde_(this.ei.a(), this.ea.getSportType(), "shareWeChat", this.ei.aYu_());
                    return;
                }
                return;
            }
        }
        if (i == 1 || i == 2) {
            nrh.b(BaseApplication.getContext(), R.string._2130839536_res_0x7f0207f0);
        } else if (i == 3) {
            nrh.b(BaseApplication.getContext(), R.string._2130839537_res_0x7f0207f1);
        }
    }

    private void a(final int i) {
        String str;
        String str2;
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        if (i == 3) {
            str = getString(R.string._2130843657_res_0x7f021809);
            str2 = getString(R.string._2130843656_res_0x7f021808);
        } else if (i == 2) {
            str = getString(R.string._2130843627_res_0x7f0217eb);
            str2 = getString(R.string._2130843626_res_0x7f0217ea);
        } else {
            str = "";
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        builder.e(str2);
        builder.czE_(str, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = i;
                if (i2 == 3) {
                    DynamicTrackActivity.this.y();
                } else if (i2 == 2) {
                    DynamicTrackActivity.this.u();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czA_(getString(R.string._2130841554_res_0x7f020fd2), new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        try {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Track_DynamicTrackActivity", LogAnonymous.b((Throwable) e2));
            nrh.b(BaseApplication.getContext(), R.string._2130839536_res_0x7f0207f0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        try {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("sinaweibo://splash"));
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Track_DynamicTrackActivity", LogAnonymous.b((Throwable) e2));
            nrh.b(BaseApplication.getContext(), R.string._2130839537_res_0x7f0207f1);
        }
    }

    private void at() {
        View inflate = View.inflate(this.q, R.layout.retrack_video_select, null);
        this.fm = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.retrack_normal_video_rl);
        this.el = relativeLayout;
        relativeLayout.setOnClickListener(this.k);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.fm.findViewById(R.id.retrack_fast_video_rl);
        this.eh = relativeLayout2;
        relativeLayout2.setOnClickListener(this.k);
        this.ap = (HealthTextView) this.fm.findViewById(R.id.retrack_videotype_fast);
        if (CommonUtil.bq()) {
            this.ap.setText(this.q.getString(R.string._2130839925_res_0x7f020975));
        } else {
            this.ap.setText(this.q.getString(R.string._2130839884_res_0x7f02094c, 15));
        }
    }

    public void initMapTypeLayout(View view) {
        this.ad = (HealthRecycleView) view.findViewById(R.id.map_custom_style_select);
        this.ab = (HealthRecycleView) view.findViewById(R.id.track_custom_style_select);
        this.ac = (LinearLayout) view.findViewById(R.id.custom_mark_title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        CustomMapStyleAdapter p = p();
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(this);
        healthLinearLayoutManager.setOrientation(0);
        this.ad.setLayoutManager(healthLinearLayoutManager);
        this.ad.setAdapter(p);
        if (koq.b(this.cp)) {
            LogUtil.b("Track_DynamicTrackActivity", "mMarkInfo data is null ");
            this.ab.setVisibility(8);
            this.ac.setVisibility(8);
            return;
        }
        this.ab.setVisibility(0);
        this.ac.setVisibility(0);
        CustomMapStyleAdapter s = s();
        HealthLinearLayoutManager healthLinearLayoutManager2 = new HealthLinearLayoutManager(this);
        healthLinearLayoutManager2.setOrientation(0);
        this.ab.setLayoutManager(healthLinearLayoutManager2);
        this.ab.setAdapter(s);
    }

    private CustomMapStyleAdapter s() {
        CustomMapStyleAdapter customMapStyleAdapter = new CustomMapStyleAdapter(this.q, this.cp, this.bv, new MapInteractor.MapItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.9
            @Override // com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.MapItemClickListener
            public void onClickListener(Object obj, int i) {
                if (!(obj instanceof hah)) {
                    LogUtil.h("Track_DynamicTrackActivity", "object is not mark");
                    return;
                }
                hah hahVar = (hah) obj;
                DynamicTrackActivity.this.bv = i;
                DynamicTrackActivity.this.v = "";
                if (!TextUtils.isEmpty(hahVar.b())) {
                    DynamicTrackActivity.this.u = null;
                } else {
                    DynamicTrackActivity.this.u = hahVar;
                    DynamicTrackActivity dynamicTrackActivity = DynamicTrackActivity.this;
                    dynamicTrackActivity.v = dynamicTrackActivity.u.a();
                }
                DynamicTrackActivity.this.cc.setCustomMarkInfo(DynamicTrackActivity.this.u);
                DynamicTrackActivity.this.cc.resetLine();
                hcj.d(DynamicTrackActivity.this.q, DynamicTrackActivity.this.x(), DynamicTrackActivity.this.s);
            }
        });
        this.co = customMapStyleAdapter;
        return customMapStyleAdapter;
    }

    private CustomMapStyleAdapter p() {
        CustomMapStyleAdapter customMapStyleAdapter = new CustomMapStyleAdapter(this.q, this.ch, this.bn, this.t, this.cm, new MapInteractor.MapItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.8
            @Override // com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.MapItemClickListener
            public void onClickListener(Object obj, int i) {
                if (obj instanceof hak) {
                    if (DynamicTrackActivity.this.bn == i) {
                        LogUtil.h("Track_DynamicTrackActivity", "onClickListener: click repeated");
                        return;
                    } else {
                        DynamicTrackActivity.this.a(i, (hak) obj);
                        return;
                    }
                }
                LogUtil.h("Track_DynamicTrackActivity", "object is not map");
            }
        });
        this.bn = customMapStyleAdapter.e();
        return customMapStyleAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, hak hakVar) {
        this.bk = true;
        this.bn = i;
        this.r = hakVar;
        if (!TextUtils.isEmpty(hakVar.c())) {
            this.t = hcr.d(hakVar.b());
        } else {
            this.t = MapTypeDescription.MapType.MAP_TYPE_CUSTOM;
        }
        d(this.t, this.r);
        c(hakVar);
        InterfaceReTrack interfaceReTrack = this.cc;
        if (interfaceReTrack != null) {
            interfaceReTrack.reset();
        }
        e(hakVar);
    }

    private void c(hak hakVar) {
        InterfaceReTrack interfaceReTrack;
        this.ay = hakVar.l();
        CustomMapStyleAdapter customMapStyleAdapter = this.co;
        int itemCount = customMapStyleAdapter != null ? customMapStyleAdapter.getItemCount() : 0;
        if (this.ay) {
            this.cm = 3;
            if (3 != this.eb) {
                bbA_(null);
            }
            if (koq.d(this.cp, 0) && this.co != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.cp.get(0));
                this.co.e(arrayList);
                this.u = null;
                this.bv = 0;
            }
        } else {
            if (this.cm != this.eb) {
                LogUtil.a("Track_DynamicTrackActivity", "onClickListener: loadMap mMapType = ", this.cm + " mPreMapType = ", Integer.valueOf(this.eb));
                this.cm = this.eb;
                bbA_(null);
            }
            CustomMapStyleAdapter customMapStyleAdapter2 = this.co;
            if (customMapStyleAdapter2 != null) {
                customMapStyleAdapter2.e(this.cp);
            }
        }
        CustomMapStyleAdapter customMapStyleAdapter3 = this.co;
        if (customMapStyleAdapter3 == null || itemCount == customMapStyleAdapter3.getItemCount() || (interfaceReTrack = this.cc) == null) {
            return;
        }
        interfaceReTrack.setCustomMarkInfo(this.u);
        this.cc.resetLine();
    }

    private void e(hak hakVar) {
        this.p = hakVar.j();
        this.s = String.valueOf(hakVar.b());
        hcj.d(this.q, x(), this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public hcm x() {
        hcm hcmVar = new hcm();
        hcmVar.e(this.ea.getSportType());
        hcmVar.a(!"retrack_mode_complex".equals(this.fb) ? 1 : 0);
        hcmVar.d(this.eo);
        hcmVar.a(this.v);
        return hcmVar;
    }

    private void an() {
        View inflate = View.inflate(this.q, R.layout.retrack_max_heartrate, null);
        if (inflate instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) inflate;
            this.de = frameLayout;
            this.df = (ImageView) frameLayout.findViewById(R.id.retrack_max_heartrate_frame);
            this.db = (LinearLayout) this.de.findViewById(R.id.retrack_max_heartrate_text_ll);
            this.dc = (HealthTextView) this.de.findViewById(R.id.retrack_max_heartrate_value);
            this.ax = (HealthTextView) this.de.findViewById(R.id.retrack_max_heartrate_unit);
            bbx_(this.df, R.drawable.retrack_ic_max_heartrate);
        }
        View inflate2 = View.inflate(this.q, R.layout.retrack_max_pace, null);
        if (inflate2 instanceof FrameLayout) {
            FrameLayout frameLayout2 = (FrameLayout) inflate2;
            this.dh = frameLayout2;
            this.dd = (ImageView) frameLayout2.findViewById(R.id.retrack_max_pace_frame);
            this.dk = (LinearLayout) this.dh.findViewById(R.id.retrack_max_pace_text_ll);
            this.dj = (HealthTextView) this.dh.findViewById(R.id.retrack_max_pace_value);
            this.dg = (HealthTextView) this.dh.findViewById(R.id.retrack_max_pace_unit);
            bbx_(this.dd, R.drawable._2131431210_res_0x7f0b0f2a);
        }
        View inflate3 = View.inflate(this.q, R.layout.retrack_max_speed, null);
        if (inflate3 instanceof FrameLayout) {
            FrameLayout frameLayout3 = (FrameLayout) inflate3;
            this.dl = frameLayout3;
            this.di = (ImageView) frameLayout3.findViewById(R.id.retrack_max_speed_frame);
            this.dm = (LinearLayout) this.dl.findViewById(R.id.retrack_max_speed_text_ll);
            this.f2do = (HealthTextView) this.dl.findViewById(R.id.retrack_max_speed_value);
            this.ek = (HealthTextView) this.dl.findViewById(R.id.retrack_max_speed_unit);
            bbx_(this.di, R.drawable._2131431211_res_0x7f0b0f2b);
        }
        View inflate4 = View.inflate(this.q, R.layout.retrack_max_cadence, null);
        if (inflate4 instanceof FrameLayout) {
            FrameLayout frameLayout4 = (FrameLayout) inflate4;
            this.cx = frameLayout4;
            this.cz = (ImageView) frameLayout4.findViewById(R.id.retrack_max_cadence_frame);
            this.da = (LinearLayout) this.cx.findViewById(R.id.retrack_max_cadence_text_ll);
            this.cw = (HealthTextView) this.cx.findViewById(R.id.retrack_max_cadence_value);
            this.m = (HealthTextView) this.cx.findViewById(R.id.retrack_max_cadence_unit);
            bbx_(this.cz, R.drawable._2131431208_res_0x7f0b0f28);
        }
        View inflate5 = View.inflate(this.q, R.layout.retrack_max_altitude, null);
        if (inflate5 instanceof FrameLayout) {
            FrameLayout frameLayout5 = (FrameLayout) inflate5;
            this.cs = frameLayout5;
            this.cu = (ImageView) frameLayout5.findViewById(R.id.retrack_max_altitude_frame);
            this.cr = (LinearLayout) this.cs.findViewById(R.id.retrack_max_altitude_text_ll);
            this.ct = (HealthTextView) this.cs.findViewById(R.id.retrack_max_altitude_value);
            this.c = (HealthTextView) this.cs.findViewById(R.id.retrack_max_altitude_unit);
            bbx_(this.cu, R.drawable._2131431207_res_0x7f0b0f27);
        }
    }

    private void bbx_(ImageView imageView, int i) {
        if (LanguageUtil.bc(getApplicationContext())) {
            BitmapDrawable cKn_ = nrz.cKn_(getApplicationContext(), i);
            if (cKn_ != null) {
                imageView.setImageDrawable(cKn_);
                return;
            }
            return;
        }
        imageView.setImageResource(i);
    }

    private void bp() {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "startRetrack");
        getWindow().addFlags(128);
        if (this.bj) {
            LogUtil.a("Track_DynamicTrackActivity", "click too fast, return.");
            return;
        }
        this.f3641a = new a();
        this.bj = true;
        this.dn.sendEmptyMessage(1);
        this.du.e();
        this.cc.startReTrack();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        hbk hbkVar;
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Track_DynamicTrackActivity", "onActivityResult.");
        if (i2 == -1) {
            if (i == 5) {
                if (intent != null) {
                    bbC_(intent);
                }
            } else {
                if (i == 6) {
                    ArrayList arrayList = new ArrayList();
                    Message obtain = Message.obtain();
                    obtain.what = 528;
                    obtain.obj = arrayList;
                    this.dn.sendMessage(obtain);
                    return;
                }
                if (i == 101 && (hbkVar = this.ei) != null) {
                    hbkVar.aYt_(i2, intent, this.bm ? this.bc : false);
                    this.bb = true;
                    hcj.b(this.q, this.ea.getSportType(), this.ay, this.ei.b(), this.bc ? 1 : 0);
                    bp();
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        LogUtil.a("Track_DynamicTrackActivity", "onWindowFocusChanged,isHasFocus= ", Boolean.valueOf(z), Boolean.valueOf(this.bd));
        super.onWindowFocusChanged(z);
        if (z) {
            hcr.bai_(this);
            if (this.bd && CommonUtil.aa(this.q)) {
                this.dq.a(false);
                LogUtil.a("Track_DynamicTrackActivity", "download music");
                this.ce.b();
            }
            this.bd = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        new AlphaAnimation(0.0f, 1.0f).setDuration(400L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.10
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                LogUtil.a("Track_DynamicTrackActivity", "handleStartViewVisible animationEnd");
                if (DynamicTrackActivity.this.bj) {
                    DynamicTrackActivity.this.ci.setVisibility(8);
                    DynamicTrackActivity.this.dx.setVisibility(8);
                    DynamicTrackActivity.this.ep.setVisibility(8);
                    DynamicTrackActivity.this.l.setVisibility(8);
                    DynamicTrackActivity.this.en.setVisibility(8);
                    DynamicTrackActivity.this.y.setVisibility(8);
                    return;
                }
                LogUtil.a("Track_DynamicTrackActivity", "mIsPlayingTrack is false");
            }
        });
        this.ci.startAnimation(alphaAnimation);
        this.dx.startAnimation(alphaAnimation);
        this.l.startAnimation(alphaAnimation);
        if (this.y.getVisibility() == 0) {
            this.y.startAnimation(alphaAnimation);
        }
        this.h.setClickable(true);
    }

    private void aa() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        new AlphaAnimation(1.0f, 0.0f).setDuration(400L);
        this.ci.setVisibility(0);
        al();
        if (this.bi) {
            bbE_(alphaAnimation);
            bbD_(alphaAnimation);
        }
        this.l.setVisibility(0);
        this.f.setVisibility(8);
        this.ci.startAnimation(alphaAnimation);
        this.l.startAnimation(alphaAnimation);
        this.h.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500L);
        this.ae.setVisibility(0);
        this.ae.startAnimation(alphaAnimation);
        this.ba.setVisibility(0);
        this.ah.setVisibility(8);
        this.x.setVisibility(8);
        this.cy.setVisibility(8);
        this.f.setVisibility(8);
        this.cg.setVisibility(8);
        this.bx.setVisibility(8);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setDuration(500L);
        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.11
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                DynamicTrackActivity.this.cl.setVisibility(8);
            }
        });
        this.cl.startAnimation(alphaAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setDuration(400L);
        alphaAnimation2.setFillAfter(true);
        this.er.startAnimation(alphaAnimation2);
        this.es.startAnimation(alphaAnimation2);
        this.dy.startAnimation(alphaAnimation2);
        this.bp.startAnimation(alphaAnimation2);
        this.ah.setVisibility(0);
        this.x.setVisibility(0);
        this.cg.setVisibility(0);
        this.cy.setVisibility(0);
        this.f.setVisibility(0);
        this.ah.startAnimation(alphaAnimation);
        this.x.startAnimation(alphaAnimation);
        this.cg.startAnimation(alphaAnimation);
        this.cy.startAnimation(alphaAnimation);
    }

    private void bl() {
        if (this.ag == null) {
            this.ag = new CustomTextAlertDialog.Builder(this.q).b(R.string._2130839949_res_0x7f02098d).d(R.string._2130839950_res_0x7f02098e).cyR_(R.string._2130839728_res_0x7f0208b0, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DynamicTrackActivity.this.ag.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyU_(R.string._2130839948_res_0x7f02098c, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DynamicTrackActivity.this.finish();
                    DynamicTrackActivity.this.ag.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
        }
        this.ag.show();
    }

    public void c() {
        if (CommonUtil.aa(this.q) || !han.e().h()) {
            this.ce.d(this.cq);
        } else if (han.e().h()) {
            nrh.d(BaseApplication.getContext(), getString(R.string._2130841392_res_0x7f020f30));
        }
    }

    private void bg() {
        switch (this.ea.getCurveType()) {
            case 32:
                this.w.setText(R.string._2130839878_res_0x7f020946);
                break;
            case 33:
                this.w.setText(R.string._2130839877_res_0x7f020945);
                break;
            case 34:
                this.w.setText(R.string._2130839879_res_0x7f020947);
                break;
        }
    }

    public void a() {
        if (ay()) {
            return;
        }
        nrh.c(BaseApplication.getContext(), this.q.getString(R.string._2130841392_res_0x7f020f30));
        finish();
    }

    private boolean ay() {
        ConnectivityManager xz_ = CommonUtils.xz_();
        if (xz_ == null) {
            LogUtil.b("Track_DynamicTrackActivity", "connectivity is null.");
            return false;
        }
        NetworkInfo[] allNetworkInfo = xz_.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    private void bj() {
        ab();
        hbk hbkVar = this.ei;
        if (hbkVar != null) {
            hbkVar.g();
        }
        l();
        this.du.a();
        e(this.ea.getValidTotalDistance());
        this.h.setClickable(false);
        this.ej = 0;
    }

    private void ab() {
        this.l.setVisibility(0);
        this.ci.setVisibility(0);
        al();
        if (this.ea.getSportType() == 222) {
            this.er.setVisibility(0);
        } else {
            this.es.setVisibility(0);
        }
        this.er.clearAnimation();
        this.es.clearAnimation();
        this.dy.clearAnimation();
        this.dy.setVisibility(0);
        this.bp.clearAnimation();
        this.bp.setVisibility(0);
        this.dx.setVisibility(0);
        nsy.cMA_(this.ep, 8);
        this.dx.clearAnimation();
        this.cg.clearAnimation();
        this.cg.setVisibility(8);
        this.ah.clearAnimation();
        this.x.clearAnimation();
        this.ah.setVisibility(8);
        this.x.setVisibility(8);
        this.en.setVisibility(8);
        this.cy.removeAllViews();
        this.f.removeAllViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MapTypeDescription.MapType mapType, hak hakVar) {
        if (hakVar == null) {
            this.cc.changeMapType(mapType, null);
            c(mapType, (hak) null);
        } else if (!TextUtils.isEmpty(hakVar.c())) {
            this.cc.changeMapType(mapType, null);
            c(mapType, (hak) null);
        } else {
            this.cc.changeMapType(mapType, hakVar);
            c(mapType, hakVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MapTypeDescription.MapType mapType, hak hakVar) {
        LogUtil.a("Track_DynamicTrackActivity", "Current map style is ", this.t, "Current map type is ", Integer.valueOf(this.cm));
        this.bg = false;
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL || mapType == MapTypeDescription.MapType.DEFAULT_MAP_THREE_D) {
            d(-16777216);
            return;
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.bg = true;
            d(-1);
        } else if (mapType == MapTypeDescription.MapType.MAP_TYPE_NAVI) {
            d(-16777216);
        } else if (mapType == MapTypeDescription.MapType.MAP_TYPE_CUSTOM && hakVar != null) {
            this.bg = "true".equals(hakVar.d());
            d("true".equals(hakVar.d()) ? -1 : -16777216);
        } else {
            d(-1);
        }
    }

    public static void c(boolean z) {
        d = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.constraints.DynamicTrackContract.Iview
    public void judgeVipResult(boolean z) {
        this.bm = z;
        runOnUiThread(new Runnable() { // from class: hek
            @Override // java.lang.Runnable
            public final void run() {
                DynamicTrackActivity.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        int i;
        int i2;
        if (this.bm) {
            nsy.cMA_(this.g, 0);
            nsy.cMA_(this.ed, 8);
            nsy.cMA_(this.fn, 8);
            if (this.cm != 1 || (i2 = this.bn) < 2) {
                return;
            }
            this.bn = i2 + 1;
            return;
        }
        nsy.cMA_(this.ed, 0);
        nsy.cMA_(this.g, 8);
        nsy.cMA_(this.fn, 0);
        if (this.ay) {
            a(0, this.ch.get(0));
        } else {
            if (this.cm != 1 || (i = this.bn) <= 2) {
                return;
            }
            this.bn = i - 1;
        }
    }

    public void c(String str) {
        hcj.e(this.q, this.ea.getSportType(), this.ay, str);
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        private Handler f3646a;
        private List<Integer> b;
        private Pair<Integer, Double> d;
        private int e;
        private boolean f;

        private a() {
            this.b = new ArrayList(10);
            this.f3646a = new e(Looper.getMainLooper(), this);
            this.f = false;
        }

        private void h() {
            if (((Integer) this.d.first).intValue() == 1) {
                this.e = 1;
                return;
            }
            if (((Integer) this.d.first).intValue() == 3) {
                if (DynamicTrackActivity.this.ea.getMaxSpeedType() != 18) {
                    if (DynamicTrackActivity.this.ea.getMaxSpeedType() == 17) {
                        this.e = 3;
                        return;
                    } else {
                        this.e = 5;
                        return;
                    }
                }
                this.e = 4;
                return;
            }
            if (((Integer) this.d.first).intValue() == 2) {
                this.e = 2;
            } else {
                LogUtil.b("Track_DynamicTrackActivity", "dataType error.");
            }
        }

        public void bbY_(Pair<Integer, Double> pair) {
            if (pair == null) {
                LogUtil.b("Track_DynamicTrackActivity", "animateKeyDataAppear pair is null.");
                return;
            }
            this.d = pair;
            h();
            hcr.bak_(1, 0, this.f3646a);
            hcr.bak_(2, 300, this.f3646a);
            this.b.add(Integer.valueOf(this.e));
        }

        public void b(String str) {
            DynamicTrackActivity.this.f.setVisibility(0);
            if (!this.f) {
                hcr.baj_(DynamicTrackActivity.this.f, DynamicTrackActivity.this.bg);
                this.f = true;
            }
            hcr.bal_(5, 0, this.f3646a, str);
        }

        public void c() {
            int i = 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                i += 100;
                hcr.bam_(3, this.b.get((r2.size() - 1) - i2), i, this.f3646a);
            }
        }

        public void a() {
            this.e = -1;
            this.b.clear();
            DynamicTrackActivity.this.cy.removeAllViews();
            DynamicTrackActivity.this.f.removeAllViews();
            j();
        }

        private void j() {
            DynamicTrackActivity.this.ct.setText("");
            DynamicTrackActivity.this.c.setText("");
            DynamicTrackActivity.this.cw.setText("");
            DynamicTrackActivity.this.m.setText("");
            DynamicTrackActivity.this.f2do.setText("");
            DynamicTrackActivity.this.ek.setText("");
            DynamicTrackActivity.this.dj.setText("");
            DynamicTrackActivity.this.dg.setText("");
            DynamicTrackActivity.this.dc.setText("");
            DynamicTrackActivity.this.ax.setText("");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public View bbV_(int i) {
            View view = new View(DynamicTrackActivity.this.q);
            if (i == 1) {
                return DynamicTrackActivity.this.cs;
            }
            if (i == 2) {
                return DynamicTrackActivity.this.de;
            }
            if (i == 3) {
                return DynamicTrackActivity.this.dl;
            }
            if (i == 4) {
                return DynamicTrackActivity.this.cx;
            }
            if (i == 5) {
                return DynamicTrackActivity.this.dh;
            }
            LogUtil.b("Track_DynamicTrackActivity", "getFrameLayout error type.");
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public View bbW_(int i) {
            View view = new View(DynamicTrackActivity.this.q);
            if (i == 1) {
                return DynamicTrackActivity.this.cu;
            }
            if (i == 2) {
                return DynamicTrackActivity.this.df;
            }
            if (i == 3) {
                return DynamicTrackActivity.this.di;
            }
            if (i == 4) {
                return DynamicTrackActivity.this.cz;
            }
            if (i == 5) {
                return DynamicTrackActivity.this.dd;
            }
            LogUtil.b("Track_DynamicTrackActivity", "getImageView error type.");
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public View bbX_(int i) {
            View view = new View(DynamicTrackActivity.this.q);
            if (i == 1) {
                return DynamicTrackActivity.this.cr;
            }
            if (i == 2) {
                return DynamicTrackActivity.this.db;
            }
            if (i == 3) {
                return DynamicTrackActivity.this.dm;
            }
            if (i == 4) {
                return DynamicTrackActivity.this.da;
            }
            if (i == 5) {
                return DynamicTrackActivity.this.dk;
            }
            LogUtil.b("Track_DynamicTrackActivity", "getLinearLayout error type.");
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.e == -1) {
                return;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.bottomMargin = 8;
            int i = this.e;
            if (i == 1) {
                DynamicTrackActivity.this.cy.addView(DynamicTrackActivity.this.cs, 0, layoutParams);
                return;
            }
            if (i == 2) {
                DynamicTrackActivity.this.cy.addView(DynamicTrackActivity.this.de, 0, layoutParams);
                return;
            }
            if (i == 3) {
                DynamicTrackActivity.this.cy.addView(DynamicTrackActivity.this.dl, 0, layoutParams);
                return;
            }
            if (i == 4) {
                DynamicTrackActivity.this.cy.addView(DynamicTrackActivity.this.cx, 0, layoutParams);
            } else if (i == 5) {
                DynamicTrackActivity.this.cy.addView(DynamicTrackActivity.this.dh, 0, layoutParams);
            } else {
                LogUtil.b("Track_DynamicTrackActivity", "error type.");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            int i = this.e;
            if (i == -1) {
                return;
            }
            if (i == 1) {
                DynamicTrackActivity.this.ct.setText(hcr.a(((Double) this.d.second).doubleValue()));
                DynamicTrackActivity.this.c.setText(hcr.a(DynamicTrackActivity.this.q));
                return;
            }
            if (i == 2) {
                DynamicTrackActivity.this.dc.setText(hcr.d(((Double) this.d.second).doubleValue()));
                DynamicTrackActivity.this.ax.setText(DynamicTrackActivity.this.q.getResources().getQuantityString(R.plurals._2130903131_res_0x7f03005b, 1, ""));
                return;
            }
            if (i == 3) {
                DynamicTrackActivity.this.f2do.setText(hcr.c(((Double) this.d.second).doubleValue()));
                DynamicTrackActivity.this.ek.setText(hcr.c(DynamicTrackActivity.this.q));
            } else if (i == 4) {
                DynamicTrackActivity.this.cw.setText(hcr.d(((Double) this.d.second).doubleValue()));
                DynamicTrackActivity.this.m.setText(DynamicTrackActivity.this.q.getString(R.string._2130839766_res_0x7f0208d6));
            } else {
                if (i == 5) {
                    double doubleValue = ((Double) this.d.second).doubleValue();
                    DynamicTrackActivity.this.dg.setText(hcr.d(DynamicTrackActivity.this.q));
                    DynamicTrackActivity.this.dj.setText(hji.c((float) doubleValue));
                    return;
                }
                LogUtil.b("Track_DynamicTrackActivity", "error type.");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(String str) {
            View inflate = View.inflate(DynamicTrackActivity.this.q, R.layout.trackalbum_text_barrage, null);
            if (inflate instanceof LinearLayout) {
                HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.retrack_barrage_value);
                healthTextView.setText(hci.aZU_(DynamicTrackActivity.this.q, str, (int) healthTextView.getTextSize()));
                DynamicTrackActivity.this.f.addView(inflate, 0, new LinearLayout.LayoutParams(-2, -2));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (DynamicTrackActivity.this.f == null || DynamicTrackActivity.this.f.getChildCount() <= 0) {
                return;
            }
            DynamicTrackActivity.this.f.removeView(DynamicTrackActivity.this.f.getChildAt(DynamicTrackActivity.this.f.getChildCount() - 1));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i) {
            if (this.e == -1) {
                return;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(400.0f, 0.0f, 0.0f, 0.0f);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setDuration(350L);
            animationSet.setInterpolator(DynamicTrackActivity.this.az);
            animationSet.setFillAfter(true);
            if (i == 0) {
                DynamicTrackActivity.this.er.startAnimation(animationSet);
                DynamicTrackActivity.this.es.startAnimation(animationSet);
            } else if (i == 1) {
                DynamicTrackActivity.this.dy.startAnimation(animationSet);
            } else {
                DynamicTrackActivity.this.bp.startAnimation(animationSet);
            }
            LogUtil.a("Track_DynamicTrackActivity", "animateOtherDataAppear, index = ", Integer.valueOf(i));
        }

        private void f() {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(400L);
            alphaAnimation.setFillAfter(true);
            DynamicTrackActivity.this.ah.startAnimation(alphaAnimation);
            DynamicTrackActivity.this.x.startAnimation(alphaAnimation);
            DynamicTrackActivity.this.cg.startAnimation(alphaAnimation);
        }

        public void b() {
            f();
            c();
            hcr.bam_(4, 0, (this.b.size() * 400) + 1000, this.f3646a);
            hcr.bam_(4, 1, (this.b.size() * 400) + 1500, this.f3646a);
            hcr.bam_(4, 2, (this.b.size() * 400) + 2000, this.f3646a);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        ShareSelector shareSelector = this.en;
        if (shareSelector == null) {
            LogUtil.b("Track_DynamicTrackActivity", "mShareSelector == null");
            return;
        }
        if (this.bj || shareSelector.getVisibility() == 0) {
            LogUtil.a("Track_DynamicTrackActivity", "setPreviewStatus by onBackPressed");
            this.bj = false;
            bj();
            InterfaceReTrack interfaceReTrack = this.cc;
            if (interfaceReTrack != null) {
                interfaceReTrack.reset();
            }
        }
        al();
    }

    private void al() {
        if (nsn.ac(this.q)) {
            this.cj.setText(R.string._2130839716_res_0x7f0208a4);
        } else {
            this.cj.setText(R.string._2130846045_res_0x7f02215d);
        }
    }

    private void m() {
        Handler handler = this.dn;
        if (handler != null) {
            handler.sendEmptyMessage(118);
        }
        LogUtil.a("Track_DynamicTrackActivity", "asynLoadPhoto IS_IGNORE_MATCH_PHOTOS");
    }

    public boolean d() {
        return this.bh;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void bbC_(android.content.Intent r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "selected_image_list"
            java.util.ArrayList r1 = r7.getParcelableArrayListExtra(r1)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L37
            java.lang.String r2 = "selected_video_list"
            java.util.ArrayList r0 = r7.getParcelableArrayListExtra(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            java.lang.String r2 = "selected_video_duration_is_long"
            r3 = 0
            boolean r7 = r7.getBooleanExtra(r2, r3)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            if (r7 == 0) goto L49
            android.content.Context r7 = r6.q     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            r4 = 10
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            r2[r3] = r4     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            r3 = 2130840068(0x7f020a04, float:1.7285164E38)
            java.lang.String r7 = r7.getString(r3, r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            defpackage.nrh.d(r2, r7)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L32
            goto L49
        L32:
            r7 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L39
        L37:
            r7 = move-exception
            r1 = r0
        L39:
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r2 = "Track_DynamicTrackActivity"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r7)
            r5 = r1
            r1 = r0
            r0 = r5
        L49:
            if (r1 != 0) goto L4e
            if (r0 != 0) goto L4e
            return
        L4e:
            hcd r7 = r6.b
            if (r7 == 0) goto L6a
            android.os.Message r7 = android.os.Message.obtain()
            if (r1 == 0) goto L5f
            r0 = 519(0x207, float:7.27E-43)
            r7.what = r0
            r7.obj = r1
            goto L65
        L5f:
            r1 = 528(0x210, float:7.4E-43)
            r7.what = r1
            r7.obj = r0
        L65:
            android.os.Handler r0 = r6.dn
            r0.sendMessage(r7)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity.bbC_(android.content.Intent):void");
    }

    static class c extends BaseHandler<DynamicTrackActivity> {
        public c(Looper looper, DynamicTrackActivity dynamicTrackActivity) {
            super(looper, dynamicTrackActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bbR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DynamicTrackActivity dynamicTrackActivity, Message message) {
            bbN_(dynamicTrackActivity, message);
            dynamicTrackActivity.cc.messageHandle(message);
            if (dynamicTrackActivity.b != null) {
                dynamicTrackActivity.b.aZF_(message);
            }
        }

        private void bbN_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                dynamicTrackActivity.bb = false;
                dynamicTrackActivity.ac();
                dynamicTrackActivity.ah.b();
                return;
            }
            if (i == 16) {
                c(dynamicTrackActivity);
                return;
            }
            if (i == 32) {
                sendEmptyMessageDelayed(2, ProfileExtendConstants.TIME_OUT);
                return;
            }
            if (i == 51) {
                if (dynamicTrackActivity.f3641a != null) {
                    dynamicTrackActivity.f3641a.b();
                }
            } else {
                if (i == 64) {
                    dynamicTrackActivity.ad();
                    return;
                }
                if (i == 96) {
                    bbQ_(dynamicTrackActivity, message);
                    return;
                }
                if (i == 118) {
                    d(dynamicTrackActivity);
                } else if (i == 131) {
                    DynamicTrackActivity.cp(dynamicTrackActivity);
                } else {
                    bbO_(dynamicTrackActivity, message);
                }
            }
        }

        private void bbO_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            int i = message.what;
            if (i == 2) {
                dynamicTrackActivity.w();
                return;
            }
            if (i == 97) {
                bbM_(dynamicTrackActivity, message);
                return;
            }
            if (i == 99) {
                dynamicTrackActivity.ah.c(((Integer) message.obj).intValue());
                return;
            }
            if (i == 117) {
                bbL_(dynamicTrackActivity, message);
                return;
            }
            if (i == 152) {
                dynamicTrackActivity.z();
                return;
            }
            if (i == 120) {
                b(dynamicTrackActivity);
                return;
            }
            if (i == 121) {
                dynamicTrackActivity.c(-1);
            } else if (i == 128) {
                dynamicTrackActivity.c(dynamicTrackActivity.t, dynamicTrackActivity.r);
            } else {
                if (i != 129) {
                    return;
                }
                bbP_(dynamicTrackActivity, message);
            }
        }

        private void bbP_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            if (!(message.obj instanceof Integer) || dynamicTrackActivity.b == null) {
                return;
            }
            dynamicTrackActivity.b.c(((Integer) message.obj).intValue());
        }

        private void b(DynamicTrackActivity dynamicTrackActivity) {
            if (dynamicTrackActivity.f3641a != null) {
                dynamicTrackActivity.f3641a.e();
            }
        }

        private void bbL_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            if (dynamicTrackActivity.f3641a == null || !(message.obj instanceof String)) {
                return;
            }
            dynamicTrackActivity.f3641a.b((String) message.obj);
        }

        private void bbM_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            if (dynamicTrackActivity.f3641a == null || !hbz.d(message.obj, Integer.class, Double.class)) {
                return;
            }
            dynamicTrackActivity.f3641a.bbY_((Pair) message.obj);
        }

        private void d(DynamicTrackActivity dynamicTrackActivity) {
            dynamicTrackActivity.be = true;
            if (dynamicTrackActivity.bf) {
                dynamicTrackActivity.bh = true;
                sendEmptyMessageDelayed(152, 500L);
                dynamicTrackActivity.bf = false;
                dynamicTrackActivity.be = false;
            }
        }

        private void c(DynamicTrackActivity dynamicTrackActivity) {
            dynamicTrackActivity.bf = true;
            if (dynamicTrackActivity.be) {
                dynamicTrackActivity.bh = true;
                sendEmptyMessageDelayed(152, 500L);
                dynamicTrackActivity.bf = false;
                dynamicTrackActivity.be = false;
            }
        }

        private void bbQ_(DynamicTrackActivity dynamicTrackActivity, Message message) {
            if ((message.obj instanceof String) && dynamicTrackActivity.bj) {
                dynamicTrackActivity.af.setText(hcr.c((String) message.obj, dynamicTrackActivity.ea.getValidTotalDistance()));
            }
        }
    }

    static class e extends BaseHandler<a> {
        public e(Looper looper, a aVar) {
            super(looper, aVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bbK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(a aVar, Message message) {
            int i = message.what;
            if (i == 1) {
                aVar.d();
                hcr.bae_(aVar.bbW_(aVar.e));
                return;
            }
            if (i == 2) {
                aVar.g();
                hcr.bad_(aVar.bbX_(aVar.e));
            } else {
                if (i == 3) {
                    hcr.baf_(aVar.bbV_(((Integer) message.obj).intValue()));
                    return;
                }
                if (i == 4) {
                    aVar.a(((Integer) message.obj).intValue());
                } else if (i == 5 && (message.obj instanceof String)) {
                    aVar.c((String) message.obj);
                }
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void callback(Boolean bool) {
        ReleaseLogUtil.e("Track_DynamicTrackActivity", "record result with ", bool);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        if (bool.booleanValue()) {
            bbE_(alphaAnimation);
        } else {
            this.ej = 0;
        }
        bbD_(alphaAnimation);
    }

    private void bbE_(AlphaAnimation alphaAnimation) {
        ShareSelector shareSelector = this.en;
        if (shareSelector != null && this.ej == 4) {
            shareSelector.b();
            if (alphaAnimation != null) {
                this.en.startAnimation(alphaAnimation);
            }
        }
    }

    private void bbD_(Animation animation) {
        HealthButton healthButton = this.dx;
        if (healthButton == null || healthButton.getVisibility() == 0) {
            return;
        }
        this.dx.setVisibility(0);
        if (animation != null) {
            this.dx.startAnimation(animation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cp(DynamicTrackActivity dynamicTrackActivity) {
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).updateShareUserView("Track_DynamicTrackActivity", 8, dynamicTrackActivity.fg, dynamicTrackActivity.fa);
    }

    private String b(int i) {
        if (i == 1 || i == 2) {
            return "com.tencent.mm";
        }
        if (i == 3) {
            return "com.sina.weibo";
        }
        return null;
    }
}
