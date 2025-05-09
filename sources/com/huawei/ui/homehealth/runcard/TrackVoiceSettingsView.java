package com.huawei.ui.homehealth.runcard;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView;
import com.zhangyue.iReader.sdk.scheme.ISchemeListener;
import defpackage.gws;
import defpackage.hpg;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.owp;
import defpackage.tye;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TrackVoiceSettingsView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomViewDialog f9528a;
    private HealthTextView aa;
    private HealthDivider ab;
    private HealthImageView ac;
    private ImageView ad;
    private HealthImageView ae;
    private ImageView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthDivider ai;
    private ImageView aj;
    private HealthTextView ak;
    private gws al;
    private HealthSwitchButton am;
    private Handler an;
    private HealthTextView ao;
    private HealthTextView ap;
    private int aq;
    private LinearLayout ar;
    private int as;
    private HealthTextView at;
    private HealthTextView au;
    private int av;
    private HealthTextView aw;
    private HealthTextView ax;
    private int[] ay;
    private HealthTextView az;
    private HealthSwitchButton b;
    private int ba;
    private List<String> c;
    private Activity d;
    private Context e;
    private ImageView f;
    private RelativeLayout g;
    private int[] h;
    private IBaseResponseCallback i;
    private Map<String, ArrayList<String>> j;
    private boolean k;
    private HealthDivider l;
    private boolean m;
    private boolean n;
    private ImageView o;
    private RelativeLayout p;
    private boolean q;
    private RelativeLayout r;
    private RelativeLayout s;
    private RelativeLayout t;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private LinearLayout x;
    private LinearLayout y;
    private HealthDivider z;

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i) {
        return i == 0 ? 2 : 1;
    }

    static class d implements HiDataReadResultListener {
        private WeakReference<TrackVoiceSettingsView> b;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private d(TrackVoiceSettingsView trackVoiceSettingsView) {
            this.b = new WeakReference<>(trackVoiceSettingsView);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            TrackVoiceSettingsView trackVoiceSettingsView = this.b.get();
            if (trackVoiceSettingsView == null) {
                LogUtil.h("Track_TrackVoiceSettingsView", "onResponse: activity or mTrackVoiceSettingsView is null");
                return;
            }
            if (!koq.e(obj, HiSampleConfig.class)) {
                LogUtil.h("Track_TrackVoiceSettingsView", "onResponse: objData is not instanceof HiSampleConfig");
                trackVoiceSettingsView.e();
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("Track_TrackVoiceSettingsView", "onResponse: list is empty");
                trackVoiceSettingsView.e();
                return;
            }
            SportBeat sportBeat = (SportBeat) HiJsonUtil.e(((HiSampleConfig) list.get(0)).getConfigData(), SportBeat.class);
            if (sportBeat != null) {
                trackVoiceSettingsView.a(sportBeat);
            } else {
                LogUtil.h("Track_TrackVoiceSettingsView", "onResponse: sportBeat is null, loading default value");
                trackVoiceSettingsView.e();
            }
        }
    }

    public static class c implements DownloadCallback<Object> {
        private final WeakReference<TrackVoiceSettingsView> e;

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
        }

        private c(TrackVoiceSettingsView trackVoiceSettingsView) {
            this.e = new WeakReference<>(trackVoiceSettingsView);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFinish(Object obj) {
            final TrackVoiceSettingsView trackVoiceSettingsView = this.e.get();
            LogUtil.a("Track_TrackVoiceSettingsView", "mDownloadIndexFileListener onFinish, data = ", obj.toString());
            if (trackVoiceSettingsView != null && trackVoiceSettingsView.al != null) {
                if (trackVoiceSettingsView.al.i()) {
                    if (trackVoiceSettingsView.i == null || !trackVoiceSettingsView.al.d()) {
                        boolean h = trackVoiceSettingsView.al.h();
                        if (trackVoiceSettingsView.ar == null || !h) {
                            return;
                        }
                        LogUtil.a("Track_TrackVoiceSettingsView", "showUpdateDialog");
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: opz
                            @Override // java.lang.Runnable
                            public final void run() {
                                TrackVoiceSettingsView.this.ar.setVisibility(0);
                            }
                        });
                        return;
                    }
                    trackVoiceSettingsView.al.d(trackVoiceSettingsView.i);
                    return;
                }
                LogUtil.h("Track_TrackVoiceSettingsView", "mDownloadIndexFileListener moveIndexFile failed.");
                return;
            }
            LogUtil.h("Track_TrackVoiceSettingsView", "mDownloadIndexFileListener mSmartCoachDownloadUtils is null.");
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            LogUtil.h("Track_TrackVoiceSettingsView", "mDownloadIndexFileListener downloadIndexFile failed.");
        }
    }

    public TrackVoiceSettingsView(Context context) {
        super(context);
        this.d = null;
        this.q = true;
        this.m = true;
        this.ba = 10;
        this.am = null;
        this.l = null;
        this.s = null;
        this.w = null;
        this.at = null;
        this.aw = null;
        this.o = null;
        this.r = null;
        this.u = null;
        this.ar = null;
        this.ak = null;
        this.ao = null;
        this.al = null;
        this.ap = null;
        this.b = null;
        this.k = true;
        this.c = new ArrayList(10);
        this.j = new HashMap(10);
        this.t = null;
        this.au = null;
        this.ax = null;
        this.y = null;
        this.ai = null;
        this.as = 0;
        this.aq = 0;
        this.ag = null;
        this.f = null;
        this.ad = null;
        this.af = null;
        this.aj = null;
        this.v = null;
        this.p = null;
        this.z = null;
        this.aa = null;
        this.an = new a(this);
        this.i = new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == -1) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            TrackVoiceSettingsView.this.g.setVisibility(0);
                        }
                    });
                }
            }
        };
    }

    public TrackVoiceSettingsView(Context context, int i, boolean z, Activity activity) {
        super(context);
        this.d = null;
        this.q = true;
        this.m = true;
        this.ba = 10;
        this.am = null;
        this.l = null;
        this.s = null;
        this.w = null;
        this.at = null;
        this.aw = null;
        this.o = null;
        this.r = null;
        this.u = null;
        this.ar = null;
        this.ak = null;
        this.ao = null;
        this.al = null;
        this.ap = null;
        this.b = null;
        this.k = true;
        this.c = new ArrayList(10);
        this.j = new HashMap(10);
        this.t = null;
        this.au = null;
        this.ax = null;
        this.y = null;
        this.ai = null;
        this.as = 0;
        this.aq = 0;
        this.ag = null;
        this.f = null;
        this.ad = null;
        this.af = null;
        this.aj = null;
        this.v = null;
        this.p = null;
        this.z = null;
        this.aa = null;
        this.an = new a(this);
        this.i = new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == -1) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            TrackVoiceSettingsView.this.g.setVisibility(0);
                        }
                    });
                }
            }
        };
        this.e = context;
        this.av = i;
        this.n = z;
        this.d = activity;
        i();
        s();
    }

    private void s() {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.ac.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ad.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.af.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ag.setGravity(GravityCompat.START);
            this.aj.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.ac.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.ad.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.af.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.ag.setGravity(GravityCompat.END);
            this.aj.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (UnitUtil.h()) {
            this.v.setVisibility(8);
        }
        af();
        d();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!v() && aa() && !UnitUtil.h()) {
            this.l.setVisibility(0);
            this.y.setVisibility(0);
            this.ai.setVisibility(0);
            this.y.setOnClickListener(this);
            o();
            return;
        }
        this.y.setVisibility(8);
        this.ai.setVisibility(8);
        this.l.setVisibility(8);
    }

    private boolean aa() {
        return (!y() && this.q) || (y() && this.m);
    }

    private void af() {
        this.w.setVisibility(8);
        if (w() || u() || this.n) {
            this.v.setVisibility(8);
            this.s.setVisibility(8);
            this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
            this.d.findViewById(R.id.layout_track_voice_heart_setting_top_line).setVisibility(8);
            return;
        }
        if (y()) {
            this.p.setVisibility(8);
            this.z.setVisibility(8);
            this.v.setVisibility(0);
            this.w.setVisibility(0);
            this.s.setVisibility(8);
            this.r.setVisibility(8);
            this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
            this.d.findViewById(R.id.img_auto_pause_bottom_line).setVisibility(8);
            this.u.setVisibility(8);
            l();
            return;
        }
        if (x()) {
            this.v.setVisibility(0);
            this.d.findViewById(R.id.layout_audio_reminders).setVisibility(8);
            this.ab.setVisibility(8);
            this.l.setVisibility(8);
            this.y.setVisibility(8);
            this.ai.setVisibility(8);
            this.w.setVisibility(8);
            this.s.setVisibility(8);
            this.r.setVisibility(8);
            this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    private boolean x() {
        int i;
        return UnitUtil.h() && ((i = this.av) == 258 || i == 264);
    }

    private void l() {
        String b2 = SharedPreferenceManager.b(this.e, String.valueOf(20002), "voice_rope_skpping");
        if (TextUtils.isEmpty(b2) || CommonUtil.h(b2) == 1) {
            this.m = true;
            this.am.setChecked(true);
            this.w.setVisibility(0);
        } else {
            this.m = false;
            this.am.setChecked(false);
            this.w.setVisibility(8);
        }
        e(SharedPreferenceManager.b(this.e, Integer.toString(20002), "value_upper_voice_interval"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return this.av == 283;
    }

    private void d() {
        if (ac() && !w() && !u() && !this.n && !y()) {
            tye.e(BaseApplication.getContext(), "huaweisport", "q3@!DF5*&$9MrhCS", "tingshu", new ISchemeListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.11
                @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                public void onSuccess(Object obj) {
                    if (obj != null) {
                        LogUtil.a("Track_TrackVoiceSettingsView", "checkSupportListenBook");
                    } else {
                        LogUtil.h("Track_TrackVoiceSettingsView", "checkSupportListenBook obj is null");
                    }
                    if (TrackVoiceSettingsView.this.an != null) {
                        TrackVoiceSettingsView.this.an.sendEmptyMessage(112);
                    } else {
                        LogUtil.h("Track_TrackVoiceSettingsView", "checkSupportListenBook onSuccess and mHandler is null");
                    }
                }

                @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                public void onError(int i, String str) {
                    LogUtil.h("Track_TrackVoiceSettingsView", str, " errorCode ", Integer.valueOf(i));
                    if (TrackVoiceSettingsView.this.an != null) {
                        TrackVoiceSettingsView.this.an.sendEmptyMessage(113);
                    } else {
                        LogUtil.h("Track_TrackVoiceSettingsView", "checkSupportListenBook ", str, " and mHandler is null");
                    }
                }
            });
        } else {
            this.p.setVisibility(8);
            this.z.setVisibility(8);
        }
    }

    private boolean ac() {
        return CommonUtil.bh() && !Utils.o();
    }

    private void i() {
        this.ad = (ImageView) this.d.findViewById(R.id.img_track_listenType_interval_value);
        this.p = (RelativeLayout) this.d.findViewById(R.id.layout_track_listenType_setting);
        this.z = (HealthDivider) this.d.findViewById(R.id.img_track_listen_type_bottom_line);
        this.aa = (HealthTextView) this.d.findViewById(R.id.txt_track_sport_type_interval_value);
        this.v = (LinearLayout) this.d.findViewById(R.id.layout_track_voice_settings);
        this.am = (HealthSwitchButton) this.d.findViewById(R.id.switch_track_voice_setting);
        this.l = (HealthDivider) this.d.findViewById(R.id.divide_line_interval_voice);
        this.ab = (HealthDivider) this.d.findViewById(R.id.metronome_divider);
        LinearLayout linearLayout = (LinearLayout) this.d.findViewById(R.id.layout_track_metronome);
        this.x = linearLayout;
        linearLayout.setOnClickListener(this);
        this.ah = (HealthTextView) this.d.findViewById(R.id.txt_metronome_frequency);
        this.ae = (HealthImageView) this.d.findViewById(R.id.metronome_red_point);
        this.ac = (HealthImageView) this.d.findViewById(R.id.img_metronome_arrow_image);
        this.af = (ImageView) this.d.findViewById(R.id.img_track_music_control_interval_value);
        this.y = (LinearLayout) this.d.findViewById(R.id.layout_track_music_control);
        this.ai = (HealthDivider) this.d.findViewById(R.id.divide_line_music_control);
        this.ag = (HealthTextView) this.d.findViewById(R.id.txt_track_music_control_interval_value);
        this.aj = (ImageView) this.d.findViewById(R.id.img_track_target_arrow_image);
        LinearLayout linearLayout2 = (LinearLayout) this.d.findViewById(R.id.layout_track_voice_rate);
        this.w = linearLayout2;
        linearLayout2.setOnClickListener(this);
        this.az = (HealthTextView) this.d.findViewById(R.id.txt_track_rope_interval_unit);
        RelativeLayout relativeLayout = (RelativeLayout) this.d.findViewById(R.id.layout_track_voice_interval_setting);
        this.s = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.at = (HealthTextView) this.d.findViewById(R.id.txt_track_voice_interval);
        this.aw = (HealthTextView) this.d.findViewById(R.id.txt_track_voice_interval_value);
        this.o = (ImageView) this.d.findViewById(R.id.img_track_voice_interval_value);
        this.r = (RelativeLayout) this.d.findViewById(R.id.layout_base_voice_play);
        this.u = (LinearLayout) this.d.findViewById(R.id.track_voice_smart_coach);
        if (LanguageUtil.m(this.e) && !Utils.o() && !this.n) {
            n();
            this.s.setVisibility(8);
            this.l.setVisibility(8);
            this.r.setVisibility(0);
            this.u.setVisibility(0);
            SharedPreferenceManager.e(this.e, Integer.toString(20002), "COLLECT_SMART_COACH_SET_TIPS", "true", new StorageParams());
            q();
        } else {
            p();
            this.s.setVisibility(0);
            this.l.setVisibility(0);
            this.r.setVisibility(8);
            this.u.setVisibility(8);
        }
        k();
        r();
        this.q = owp.c(BaseApplication.getContext(), "voice_enable_type");
        ae();
        m();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.b("Track_TrackVoiceSettingsView", "is click fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        if (view.getId() == R.id.layout_track_listenType_setting) {
            a(R.string._2130842533_res_0x7f0213a5, false);
            hashMap.put("type", 11);
        } else {
            if (view.getId() == R.id.layout_track_metronome) {
                ad();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view.getId() == R.id.layout_track_music_control) {
                a(R.string._2130844707_res_0x7f021c23, true);
                hashMap.put("type", 17);
            } else if (view.getId() == R.id.layout_track_voice_interval_setting) {
                ai();
                hashMap.put("type", 2);
            } else if (view.getId() == R.id.layout_track_voice_rate) {
                j();
                hashMap.put("type", 2);
            } else if (view.getId() == R.id.layout_base_voice_play) {
                LogUtil.a("Track_TrackVoiceSettingsView", "start SportAssistBaseVoiceSettingsActivity");
                try {
                    this.d.startActivity(new Intent(this.e, (Class<?>) SportAssistBaseVoiceSettingsActivity.class));
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("Track_TrackVoiceSettingsView", "onClick() exception: ", LogAnonymous.b((Throwable) e));
                }
            } else {
                LogUtil.h("Track_TrackVoiceSettingsView", "wrong view id");
            }
        }
        ixx.d().d(this.e, AnalyticsValue.MOTION_TRACK_1040023.value(), hashMap, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ad() {
        Intent intent = new Intent(this.e, (Class<?>) SportMetronomeActivity.class);
        intent.putExtra("jump_source", 1);
        intent.putExtra("is_support_metronome", true);
        this.e.startActivity(intent);
    }

    private void j() {
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setPickerCount(1, new boolean[]{false});
        healthMultiNumberPicker.setDisplayedValues(0, new String[]{this.e.getResources().getQuantityString(R.plurals._2130903134_res_0x7f03005e, 10, 10), this.e.getResources().getQuantityString(R.plurals._2130903134_res_0x7f03005e, 100, 100)}, CommonUtil.e(SharedPreferenceManager.b(this.e, Integer.toString(20002), "value_upper_voice_interval"), 10) == 10 ? 0 : 1);
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.13
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker2, int i2, int i3) {
                if (i3 == 0) {
                    TrackVoiceSettingsView.this.ba = 10;
                } else {
                    TrackVoiceSettingsView.this.ba = 100;
                }
            }
        });
        b(healthMultiNumberPicker);
    }

    private void b(final HealthMultiNumberPicker healthMultiNumberPicker) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        builder.d(R.string._2130842146_res_0x7f021222).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (healthMultiNumberPicker.getSelectedLocations()[0] == 0) {
                    TrackVoiceSettingsView.this.ba = 10;
                } else {
                    TrackVoiceSettingsView.this.ba = 100;
                }
                TrackVoiceSettingsView.this.an();
                TrackVoiceSettingsView.this.e(UnitUtil.e(r0.ba, 1, 0));
                HashMap hashMap = new HashMap(2);
                hashMap.put("click", 1);
                hashMap.put("frequency", Integer.valueOf(TrackVoiceSettingsView.this.ba));
                ixx.d().d(TrackVoiceSettingsView.this.e, AnalyticsValue.HEALTH_SKIPPING_FREQUENCY_2040128.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.f9528a = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "value_upper_voice_interval", UnitUtil.e(this.ba, 1, 0), new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        int e = CommonUtil.e(str, this.ba);
        this.az.setText(this.e.getResources().getQuantityString(R.plurals._2130903134_res_0x7f03005e, e, Integer.valueOf(e)));
    }

    private void ai() {
        int d2 = owp.d(BaseApplication.getContext());
        int i = 0;
        int i2 = 1;
        if (d2 == 1) {
            int i3 = owp.i(BaseApplication.getContext());
            while (true) {
                int[] iArr = this.ay;
                if (i >= iArr.length) {
                    i = 1;
                    break;
                } else if (i3 == iArr[i]) {
                    break;
                } else {
                    i++;
                }
            }
            i2 = i;
            i = 1;
        } else if (d2 == 2) {
            int a2 = owp.a(BaseApplication.getContext());
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.h;
                if (i4 >= iArr2.length) {
                    break;
                }
                if (a2 == iArr2[i4]) {
                    i2 = i4;
                    break;
                }
                i4++;
            }
        }
        d(i, i2);
    }

    private void d(int i, int i2) {
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        List<String> list = this.c;
        healthMultiNumberPicker.setDisplayedValues(0, (String[]) list.toArray(new String[list.size()]), 0);
        ArrayList<String> arrayList = this.j.get(this.c.get(i));
        healthMultiNumberPicker.setDisplayedValues(1, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        healthMultiNumberPicker.a(new int[]{i, i2}, arrayList.size());
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.12
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i3, HealthMultiNumberPicker healthMultiNumberPicker2, int i4, int i5) {
                if (i3 == 0) {
                    if (i5 < TrackVoiceSettingsView.this.c.size() && TrackVoiceSettingsView.this.j.containsKey(TrackVoiceSettingsView.this.c.get(i5))) {
                        ArrayList arrayList2 = (ArrayList) TrackVoiceSettingsView.this.j.get(TrackVoiceSettingsView.this.c.get(i5));
                        healthMultiNumberPicker2.setDisplayedValues(1, (String[]) arrayList2.toArray(new String[arrayList2.size()]), 1);
                    } else {
                        LogUtil.h("Track_TrackVoiceSettingsView", "the voice type is not valid");
                    }
                }
            }
        });
        final CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        builder.d(R.string._2130842146_res_0x7f021222).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                TrackVoiceSettingsView.this.e(TrackVoiceSettingsView.this.b(selectedLocations[0]), selectedLocations[1]);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                builder.b(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.f9528a = e;
        e.show();
    }

    private void a(int i, final boolean z) {
        String[] strArr;
        int sportMusicType;
        LogUtil.a("Track_TrackVoiceSettingsView", "showVoiceControlDialog");
        Context context = this.e;
        if (context == null) {
            LogUtil.a("Track_TrackVoiceSettingsView", "showSportMusicControlDialog, mContext is null");
            return;
        }
        if (!(context.getSystemService("layout_inflater") instanceof LayoutInflater)) {
            LogUtil.h("Track_TrackVoiceSettingsView", "showVoiceControlDialog, inflater not instance of LayoutInflater");
            return;
        }
        View inflate = ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.hw_show_muisc_type_view, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        builder.d(i).czh_(inflate, 0, 0).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TrackVoiceSettingsView.this.an != null) {
                    TrackVoiceSettingsView.this.an.sendEmptyMessage(z ? 115 : 111);
                } else {
                    LogUtil.h("Track_TrackVoiceSettingsView", "settingListenType mHandle is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f9528a = builder.e();
        if (inflate != null) {
            if (z) {
                strArr = new String[]{this.e.getResources().getString(R.string._2130844708_res_0x7f021c24), this.e.getResources().getString(R.string._2130844709_res_0x7f021c25)};
                sportMusicType = getSportMusicControlType();
            } else {
                strArr = new String[]{this.e.getResources().getString(R.string._2130842049_res_0x7f0211c1), this.e.getResources().getString(R.string._2130842532_res_0x7f0213a4)};
                sportMusicType = getSportMusicType();
            }
            dfu_(inflate, strArr, sportMusicType, z);
            this.f9528a.show();
            return;
        }
        LogUtil.h("Track_TrackVoiceSettingsView", "showSettingListenTypeDialog() dialog layout fail");
        this.f9528a = null;
    }

    private void dfu_(View view, String[] strArr, int i, final boolean z) {
        ListView listView = (ListView) view.findViewById(R.id.custom_listview_layout);
        final ListenTypeAdapter listenTypeAdapter = new ListenTypeAdapter(strArr, this.e, i, z);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) listenTypeAdapter);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(1);
        listView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                return motionEvent != null && motionEvent.getAction() == 2;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                if (z) {
                    TrackVoiceSettingsView.this.c(i2, listenTypeAdapter);
                } else {
                    TrackVoiceSettingsView.this.d(i2, listenTypeAdapter);
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view2, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, ListenTypeAdapter listenTypeAdapter) {
        if (i == 0) {
            this.aq = 0;
        } else if (i == 1) {
            this.aq = 1;
        } else {
            LogUtil.a("Track_TrackVoiceSettingsView", "position = ", Integer.valueOf(i));
        }
        owp.b(this.e, this.aq);
        listenTypeAdapter.d(this.aq);
        ag();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        int sportMusicControlType = getSportMusicControlType();
        HashMap hashMap = new HashMap(1);
        if (sportMusicControlType == 0) {
            this.ag.setText(R.string._2130844708_res_0x7f021c24);
            hashMap.put("type", 9);
        } else {
            this.ag.setText(R.string._2130844709_res_0x7f021c25);
            hashMap.put("type", 10);
        }
        ixx.d().d(this.e, AnalyticsValue.MOTION_TRACK_1040023.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, ListenTypeAdapter listenTypeAdapter) {
        if (i == 0) {
            this.as = 0;
        } else if (i == 1) {
            this.as = 1;
        } else {
            LogUtil.a("Track_TrackVoiceSettingsView", "position = ", Integer.valueOf(i));
        }
        owp.d(this.e, this.as);
        listenTypeAdapter.d(this.as);
        al();
        b();
    }

    private void b() {
        CustomViewDialog customViewDialog = this.f9528a;
        if (customViewDialog != null) {
            customViewDialog.cancel();
            this.f9528a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        int sportMusicType = getSportMusicType();
        HashMap hashMap = new HashMap(1);
        if (sportMusicType == 0) {
            this.aa.setText(R.string._2130842049_res_0x7f0211c1);
            hashMap.put("type", 7);
        } else {
            this.aa.setText(R.string._2130842532_res_0x7f0213a4);
            hashMap.put("type", 8);
        }
        ixx.d().d(this.e, AnalyticsValue.MOTION_TRACK_1040023.value(), hashMap, 0);
    }

    private int getSportMusicType() {
        return owp.e(this.e);
    }

    private int getSportMusicControlType() {
        return owp.b(this.e);
    }

    private void m() {
        if (y()) {
            return;
        }
        boolean c2 = owp.c(BaseApplication.getContext(), "voice_enable_type");
        this.q = c2;
        this.am.setChecked(c2);
        e(this.q);
    }

    private void r() {
        if (LanguageUtil.m(this.e) && !Utils.o()) {
            t();
            return;
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.o.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.o.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (w() || u()) {
            this.s.setVisibility(8);
            this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
        }
    }

    private void t() {
        RelativeLayout relativeLayout = (RelativeLayout) this.d.findViewById(R.id.layout_base_voice_play);
        this.t = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.au = (HealthTextView) this.d.findViewById(R.id.textview_base_voice_play);
        this.ax = (HealthTextView) this.d.findViewById(R.id.textview_base_voice_play_setting_tips);
        this.f = (ImageView) this.d.findViewById(R.id.img_base_voice_play);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.f.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.f.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (w() || u()) {
            this.t.setVisibility(8);
            this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    private void k() {
        this.am.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (TrackVoiceSettingsView.this.y()) {
                    TrackVoiceSettingsView.this.m = z;
                    TrackVoiceSettingsView.this.z();
                } else {
                    TrackVoiceSettingsView.this.q = z;
                    TrackVoiceSettingsView.this.ae();
                }
                TrackVoiceSettingsView.this.am.setChecked(z);
                TrackVoiceSettingsView.this.h();
                if (compoundButton.isPressed()) {
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("click", 1);
                    if (TrackVoiceSettingsView.this.q) {
                        hashMap.put("type", 1);
                    } else {
                        hashMap.put("type", 0);
                    }
                    ixx.d().d(TrackVoiceSettingsView.this.e, AnalyticsValue.HEALTH_SKIPPING_SWITCH_2040127.value(), hashMap, 0);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (this.m) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
        setVoiceParams(this.m);
    }

    private void setVoiceParams(boolean z) {
        String num;
        if (z) {
            num = Integer.toString(1);
        } else {
            num = Integer.toString(0);
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "voice_rope_skpping", num, new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        if (this.q) {
            owp.a(BaseApplication.getContext(), "voice_enable_type", true);
            e(true);
            if (LanguageUtil.m(this.e) && !Utils.o() && !this.n) {
                this.d.findViewById(R.id.divide_line_voice).setVisibility(0);
            } else {
                this.l.setVisibility(0);
                this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
            }
            if (ab() && !Utils.o()) {
                this.u.setVisibility(0);
                this.r.setVisibility(0);
                this.l.setVisibility(0);
                return;
            }
            this.l.setVisibility(8);
            return;
        }
        owp.a(BaseApplication.getContext(), "voice_enable_type", false);
        e(false);
        if (LanguageUtil.m(this.e) && !Utils.o()) {
            this.u.setVisibility(8);
        }
        this.r.setVisibility(8);
        this.d.findViewById(R.id.divide_line_voice).setVisibility(8);
    }

    private void o() {
        int b2 = owp.b(BaseApplication.getContext());
        this.aq = b2;
        if (b2 == 0) {
            this.ag.setText(R.string._2130844708_res_0x7f021c24);
        } else {
            this.ag.setText(R.string._2130844709_res_0x7f021c25);
        }
    }

    private boolean v() {
        return nsn.ae(this.e) || w() || u() || this.n;
    }

    private void e(boolean z) {
        if (LanguageUtil.m(this.e) && !Utils.o()) {
            a(z);
            return;
        }
        this.s.setClickable(z);
        if (z) {
            this.s.setVisibility(0);
            this.at.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.aw.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            this.s.setVisibility(8);
            this.at.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
            this.aw.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        }
    }

    private void a(boolean z) {
        this.t.setClickable(z);
        if (z) {
            this.au.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.ax.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            this.au.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
            this.ax.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        }
    }

    private void n() {
        f();
        this.ap = (HealthTextView) this.d.findViewById(R.id.textview_smart_coach_tips);
        this.b = (HealthSwitchButton) this.d.findViewById(R.id.switch_smart_coach);
        g();
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TrackVoiceSettingsView.this.k = z;
                TrackVoiceSettingsView.this.b.setChecked(TrackVoiceSettingsView.this.k);
                owp.a(BaseApplication.getContext(), "smart_coach_enable_type", TrackVoiceSettingsView.this.k);
                LogUtil.a("Track_TrackVoiceSettingsView", "initSmartCoachData is ", Boolean.valueOf(TrackVoiceSettingsView.this.k));
                if (!TrackVoiceSettingsView.this.q) {
                    LogUtil.h("Track_TrackVoiceSettingsView", "initSmartCoach mIsVoiceEnable is false");
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                if (!Utils.o() && TrackVoiceSettingsView.this.ab() && z) {
                    TrackVoiceSettingsView.this.al = new gws();
                    TrackVoiceSettingsView.this.al.aVg_(TrackVoiceSettingsView.this.d, new c());
                }
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                if (TrackVoiceSettingsView.this.k) {
                    hashMap.put("type", 0);
                } else {
                    hashMap.put("type", 1);
                }
                ixx.d().d(TrackVoiceSettingsView.this.e, AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void q() {
        boolean c2 = owp.c(BaseApplication.getContext(), "smart_coach_enable_type");
        this.k = c2;
        this.b.setChecked(c2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ab() {
        return (!LanguageUtil.m(this.e) || w() || u()) ? false : true;
    }

    private boolean w() {
        int i = this.av;
        return i == 10001 || i == 137;
    }

    private boolean u() {
        return this.av == 286;
    }

    private void f() {
        this.ar = (LinearLayout) this.d.findViewById(R.id.smart_coach_update_tips_card);
        HealthTextView healthTextView = (HealthTextView) this.d.findViewById(R.id.ignore_update);
        this.ak = healthTextView;
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrackVoiceSettingsView.this.ar.setVisibility(8);
                if (TrackVoiceSettingsView.this.al != null) {
                    TrackVoiceSettingsView.this.al.g();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthTextView healthTextView2 = (HealthTextView) this.d.findViewById(R.id.update_text);
        this.ao = healthTextView2;
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrackVoiceSettingsView.this.ar.setVisibility(8);
                if (TrackVoiceSettingsView.this.al != null) {
                    TrackVoiceSettingsView.this.al.d(TrackVoiceSettingsView.this.i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) this.d.findViewById(R.id.update_error_layout);
        this.g = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    TrackVoiceSettingsView.this.g.setVisibility(8);
                    if (TrackVoiceSettingsView.this.al != null) {
                        TrackVoiceSettingsView.this.al.d(TrackVoiceSettingsView.this.i);
                    }
                } else {
                    LogUtil.a("Track_TrackVoiceSettingsView", "Network is not Connected ");
                    nrh.b(BaseApplication.getContext(), R.string._2130841392_res_0x7f020f30);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.10
                @Override // java.lang.Runnable
                public void run() {
                    TrackVoiceSettingsView.this.g();
                }
            });
        } else {
            final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
            new Handler(this.e.getMainLooper()).post(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.TrackVoiceSettingsView.8
                @Override // java.lang.Runnable
                public void run() {
                    StringBuilder sb = new StringBuilder(TrackVoiceSettingsView.this.e.getResources().getString(R.string._2130850154_res_0x7f02316a));
                    if (!TextUtils.isEmpty(url)) {
                        String string = TrackVoiceSettingsView.this.e.getResources().getString(R.string._2130841591_res_0x7f020ff7);
                        sb.append(string);
                        String sb2 = sb.toString();
                        SpannableString spannableString = new SpannableString(sb2);
                        int length = sb2.length();
                        spannableString.setSpan(new UrlSpanNoUnderline(url + "/huawei_health_coach/EMUI10.0/C001B001/index.html"), length - string.length(), length, 33);
                        TrackVoiceSettingsView.this.ap.setMovementMethod(LinkMovementMethod.getInstance());
                        TrackVoiceSettingsView.this.ap.setText(spannableString);
                        return;
                    }
                    LogUtil.h("Track_TrackVoiceSettingsView", "configGrsUrl url is null");
                    TrackVoiceSettingsView.this.ap.setText(sb.toString());
                }
            });
        }
    }

    public void a() {
        int i = this.av;
        if ((i == 258 || i == 264) && hpg.c() && (this.d instanceof SportAssistSettingsActivity)) {
            LogUtil.a("Track_TrackVoiceSettingsView", "refresh, getSportBeatFromDb");
            SportMetronomeActivity.b(new d());
        } else {
            this.ab.setVisibility(8);
            this.x.setVisibility(8);
        }
    }

    public void e() {
        a((SportBeat) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final SportBeat sportBeat) {
        HandlerExecutor.a(new Runnable() { // from class: oqd
            @Override // java.lang.Runnable
            public final void run() {
                TrackVoiceSettingsView.this.d(sportBeat);
            }
        });
    }

    public /* synthetic */ void d(SportBeat sportBeat) {
        if (this.d == null) {
            LogUtil.h("Track_TrackVoiceSettingsView", "initMetronomeData, mActivity is null");
            return;
        }
        if (sportBeat != null && sportBeat.isOpen()) {
            int frequency = sportBeat.getFrequency();
            this.ah.setText(getResources().getQuantityString(R.plurals._2130903308_res_0x7f03010c, frequency, UnitUtil.e(frequency, 1, 0)));
        } else {
            this.ah.setText(getResources().getString(R.string._2130844048_res_0x7f021990));
        }
        this.ae.setVisibility(SharedPreferenceManager.a(Integer.toString(20002), "metronome_page_show", false) ? 8 : 0);
    }

    public static class UrlSpanNoUnderline extends URLSpan {
        UrlSpanNoUnderline(String str) {
            super(str);
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View view) {
            Uri parse = Uri.parse(getURL());
            Context context = view.getContext();
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
            intent.putExtra("com.android.browser.application_id", context.getPackageName());
            try {
                nsn.cLM_(intent, "com.android.browser.application_id", context, nsf.h(R.string._2130847432_res_0x7f0226c8));
            } catch (ActivityNotFoundException unused) {
                LogUtil.h("URLSpan", "Actvity was not found for intent, " + intent.toString());
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(Color.parseColor("#fb6522"));
            textPaint.setUnderlineText(false);
        }
    }

    private void p() {
        this.h = new int[]{500, 1000, 2000, 3000};
        this.ay = new int[]{5, 10, 15, 20};
        c();
        int d2 = owp.d(BaseApplication.getContext());
        if (d2 == 0) {
            owp.i(BaseApplication.getContext(), 2);
            owp.c(BaseApplication.getContext(), 1000);
            e(2, 1);
            return;
        }
        e(owp.d(BaseApplication.getContext()), a(d2));
    }

    private void c() {
        this.c.add(getResources().getString(R.string._2130842147_res_0x7f021223));
        this.c.add(getResources().getString(R.string._2130842148_res_0x7f021224));
        ArrayList<String> arrayList = new ArrayList<>(10);
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        try {
            int[] iArr = this.ay;
            if (iArr != null && iArr.length > 0) {
                for (int i : iArr) {
                    arrayList.add(getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(i, 1, 0)));
                }
            }
            int[] iArr2 = this.h;
            if (iArr2 != null && iArr2.length > 0) {
                for (int i2 = 0; i2 < this.h.length; i2++) {
                    if (i2 == 0) {
                        arrayList2.add(getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(this.h[i2] / 1000.0d, 1, 1)));
                    } else {
                        arrayList2.add(getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(this.h[i2] / 1000.0d, 1, 0)));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            LogUtil.h("Track_TrackVoiceSettingsView", "IndexOutOfBoundsException ", e.getMessage());
        }
        this.j.put(this.c.get(0), arrayList2);
        this.j.put(this.c.get(1), arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        owp.i(BaseApplication.getContext(), i);
        try {
            if (i == 1) {
                int[] iArr = this.ay;
                if (iArr != null) {
                    if (iArr.length > i2) {
                        this.aw.setText(this.j.get(this.c.get(1)).get(i2));
                    }
                    if (i2 < this.ay.length && i2 >= 0) {
                        owp.g(BaseApplication.getContext(), this.ay[i2]);
                        return;
                    }
                    owp.g(BaseApplication.getContext(), this.ay[0]);
                    return;
                }
                return;
            }
            int[] iArr2 = this.h;
            if (iArr2 != null) {
                if (iArr2.length > i2) {
                    this.aw.setText(this.j.get(this.c.get(0)).get(i2));
                }
                if (i2 < this.h.length && i2 >= 0) {
                    owp.c(BaseApplication.getContext(), this.h[i2]);
                    return;
                }
                owp.c(BaseApplication.getContext(), this.h[0]);
            }
        } catch (IndexOutOfBoundsException e) {
            LogUtil.b("Track_TrackVoiceSettingsView", "updateVoiceInterval IndexOutOfBoundsException ", e.getMessage());
        }
    }

    private int a(int i) {
        int i2 = 0;
        if (i == 2) {
            int a2 = owp.a(BaseApplication.getContext());
            while (true) {
                int[] iArr = this.h;
                if (i2 >= iArr.length) {
                    break;
                }
                if (a2 == iArr[i2]) {
                    return i2;
                }
                i2++;
            }
        } else if (i == 1) {
            int i3 = owp.i(BaseApplication.getContext());
            while (true) {
                int[] iArr2 = this.ay;
                if (i2 >= iArr2.length) {
                    break;
                }
                if (i3 == iArr2[i2]) {
                    return i2;
                }
                i2++;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 112) {
            this.p.setVisibility(0);
            this.z.setVisibility(0);
            this.p.setOnClickListener(this);
            ah();
            return;
        }
        if (i == 113) {
            this.p.setVisibility(8);
            this.z.setVisibility(8);
        } else {
            LogUtil.h("Track_TrackVoiceSettingsView", "showSportMusic, listenStatus = ", Integer.valueOf(i));
        }
    }

    private void ah() {
        int e = owp.e(BaseApplication.getContext());
        this.as = e;
        if (e == 0) {
            this.aa.setText(R.string._2130842049_res_0x7f0211c1);
        } else {
            this.aa.setText(R.string._2130842532_res_0x7f0213a4);
        }
    }

    static class a extends BaseHandler<TrackVoiceSettingsView> {
        WeakReference<TrackVoiceSettingsView> c;

        a(TrackVoiceSettingsView trackVoiceSettingsView) {
            super(trackVoiceSettingsView);
            this.c = new WeakReference<>(trackVoiceSettingsView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dfv_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TrackVoiceSettingsView trackVoiceSettingsView, Message message) {
            TrackVoiceSettingsView trackVoiceSettingsView2;
            if (message == null || (trackVoiceSettingsView2 = this.c.get()) == null) {
                return;
            }
            switch (message.what) {
                case 111:
                    trackVoiceSettingsView2.al();
                    break;
                case 112:
                    trackVoiceSettingsView2.c(112);
                    break;
                case 113:
                    trackVoiceSettingsView2.c(113);
                    break;
                case 115:
                    trackVoiceSettingsView2.ag();
                    break;
            }
        }
    }

    public class ListenTypeAdapter extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private Context f9535a;
        private boolean b;
        private int c;
        private String[] e;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public ListenTypeAdapter(String[] strArr, Context context, int i, boolean z) {
            if (strArr != null) {
                this.e = (String[]) strArr.clone();
            }
            this.f9535a = context;
            this.c = i;
            this.b = z;
        }

        public void d(int i, b bVar) {
            if (bVar == null || bVar.d == null) {
                LogUtil.h("Track_TrackVoiceSettingsView", "updateCheckedItem viewHolder is null");
                return;
            }
            if (this.b) {
                int i2 = this.c;
                if (i2 == 0 && i == 0) {
                    TrackVoiceSettingsView.this.aq = 0;
                    bVar.d.setChecked(true);
                    return;
                } else if (i2 == 1 && i == 1) {
                    TrackVoiceSettingsView.this.aq = 1;
                    bVar.d.setChecked(true);
                    return;
                } else {
                    bVar.d.setChecked(false);
                    return;
                }
            }
            int i3 = this.c;
            if (i3 == 0 && i == 0) {
                TrackVoiceSettingsView.this.as = 0;
                bVar.d.setChecked(true);
            } else if (i3 == 1 && i == 1) {
                TrackVoiceSettingsView.this.as = 1;
                bVar.d.setChecked(true);
            } else {
                bVar.d.setChecked(false);
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            String[] strArr = this.e;
            if (strArr == null) {
                LogUtil.h("Track_TrackVoiceSettingsView", "getCount: mType is null");
                return 0;
            }
            return strArr.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            String[] strArr = this.e;
            if (strArr == null) {
                LogUtil.h("Track_TrackVoiceSettingsView", "getItem: mType is null");
                return "";
            }
            if (i >= 0 && i < strArr.length) {
                return strArr[i];
            }
            return strArr[0];
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = View.inflate(this.f9535a, R.layout.custom_list_item_single_choice, null);
                bVar = new b();
                bVar.d = (CheckedTextView) view.findViewById(R.id.text1);
                bVar.f9536a = (HealthDivider) view.findViewById(R.id.divide_line);
                view.setTag(bVar);
            } else if (view.getTag() instanceof b) {
                bVar = (b) view.getTag();
            } else {
                LogUtil.a("Track_TrackVoiceSettingsView", "!view.getTag() instanceof ViewHolder");
                return view;
            }
            String[] strArr = this.e;
            if (strArr == null) {
                LogUtil.h("Track_TrackVoiceSettingsView", "getView: mType is null");
                return view;
            }
            if (i >= 0 && i < strArr.length) {
                if (strArr[i].equals(this.f9535a.getResources().getString(this.b ? R.string._2130844708_res_0x7f021c24 : R.string._2130842049_res_0x7f0211c1)) && bVar.f9536a != null) {
                    bVar.f9536a.setVisibility(0);
                }
                if (bVar.d != null) {
                    bVar.d.setText(this.e[i]);
                }
                d(i, bVar);
            }
            return view;
        }

        public void d(int i) {
            this.c = i;
            notifyDataSetChanged();
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HealthDivider f9536a;
        CheckedTextView d;

        b() {
        }
    }
}
