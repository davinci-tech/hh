package com.huawei.ui.device.activity.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.views.music.MusicLinearLayoutManager;
import com.huawei.ui.device.views.music.MusicMenuViewDialog;
import defpackage.gge;
import defpackage.jds;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jjm;
import defpackage.koq;
import defpackage.nqc;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MusicSongsActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private MusicSongsActivity f9135a;
    private ArrayList<String> c;
    private LinearLayout h;
    private LinearLayout i;
    private RelativeLayout l;
    private LinearLayout p;
    private e q;
    private HealthProgressBar r;
    private HealthRecycleView s;
    private MusicLinearLayoutManager t;
    private HealthToolBar u;
    private CustomTitleBar v;
    private d f = new d(this);
    private MusicMenu b = new MusicMenu();
    private MusicMenu j = new MusicMenu();
    private boolean n = false;
    private boolean o = false;
    private List<MusicSong> m = new ArrayList(16);
    private HashMap<Integer, Boolean> k = new HashMap<>(16);
    private int g = 2;
    private Map<String, Object> e = new HashMap(16);
    private HealthToolBar.OnSingleTapListener x = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.2
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (MusicSongsActivity.this.g != 2) {
                MusicSongsActivity.this.c();
                return;
            }
            if (i == 1) {
                MusicSongsActivity.this.e();
                gge.e(AnalyticsValue.HEALTH_MUSIC_MENU_ALL_SELECT_1090088.value(), MusicSongsActivity.this.e);
            } else {
                if (i != 2) {
                    return;
                }
                if (!nsn.o()) {
                    MusicSongsActivity.this.a();
                } else {
                    LogUtil.h("MusicSongsActivity", "click to fast!");
                }
            }
        }
    };
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("MusicSongsActivity", "device connect state onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("MusicSongsActivity", "device connect state onReceive intent :", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("MusicSongsActivity", "device connect state onReceive deviceInfo is null.");
                    return;
                }
                if (!jjj.e(deviceInfo)) {
                    LogUtil.h("MusicSongsActivity", "This device does not have the correspond capability.");
                    return;
                }
                MusicSongsActivity.this.g = deviceInfo.getDeviceConnectState();
                int i = MusicSongsActivity.this.g;
                if (i == 1) {
                    LogUtil.a("MusicSongsActivity", "DEVICE_CONNECTING");
                    return;
                }
                if (i == 2) {
                    LogUtil.a("MusicSongsActivity", "DEVICE_CONNECTED");
                } else if (i == 3) {
                    LogUtil.a("MusicSongsActivity", "DEVICE_DISCONNECTED");
                } else {
                    LogUtil.h("MusicSongsActivity", "DEVICE_CONNECT_STATUS_OTHER");
                }
            }
        }
    };

    class e extends RecyclerView.Adapter<b> {

        /* renamed from: a, reason: collision with root package name */
        private HashMap<Integer, Integer> f9142a = new HashMap<>(16);
        private final LayoutInflater c;
        private List<MusicSong> d;
        private View e;
        private b i;

        class b extends RecyclerView.ViewHolder {

            /* renamed from: a, reason: collision with root package name */
            private HealthTextView f9144a;
            private HealthCheckBox b;
            private ImageView c;
            private HealthTextView d;
            private ImageView f;

            b(View view) {
                super(view);
                this.f9144a = (HealthTextView) e.this.e.findViewById(R.id.music_song_name_tv);
                this.d = (HealthTextView) e.this.e.findViewById(R.id.music_song_singer_tv);
                this.c = (ImageView) e.this.e.findViewById(R.id.imageview_more_or_select);
                this.b = (HealthCheckBox) e.this.e.findViewById(R.id.cb_more_or_select);
                this.f = (ImageView) e.this.e.findViewById(R.id.under_line);
            }

            private void c(final int i, final boolean z, final MusicSong musicSong) {
                e.this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.e.b.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (z) {
                            MusicSongsActivity.this.k.put(Integer.valueOf(i), false);
                            b.this.b.setChecked(false);
                            MusicSongsActivity.this.m.remove(musicSong);
                            if (MusicSongsActivity.this.m.size() != e.this.d.size()) {
                                MusicSongsActivity.this.o = false;
                                MusicSongsActivity.this.b((List<MusicSong>) e.this.d);
                                MusicSongsActivity.this.u.setIcon(1, R.drawable._2131430296_res_0x7f0b0b98);
                            }
                        } else if (MusicSongsActivity.this.m.size() < 500) {
                            MusicSongsActivity.this.k.put(Integer.valueOf(i), true);
                            b.this.b.setChecked(true);
                            MusicSongsActivity.this.m.add(musicSong);
                            if (MusicSongsActivity.this.m.size() == e.this.d.size() || (jjm.e().i() && MusicSongsActivity.this.m.size() >= 500)) {
                                MusicSongsActivity.this.o = true;
                                MusicSongsActivity.this.u.setIconTitle(1, MusicSongsActivity.this.getResources().getString(R.string._2130841400_res_0x7f020f38));
                                MusicSongsActivity.this.u.setIcon(1, R.drawable._2131430281_res_0x7f0b0b89);
                            }
                        } else {
                            nrh.c(MusicSongsActivity.this.f9135a, MusicSongsActivity.this.getResources().getString(R.string._2130847718_res_0x7f0227e6));
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        }
                        e.this.notifyDataSetChanged();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                if (MusicSongsActivity.this.m.size() > 0) {
                    MusicSongsActivity.this.v.setTitleText(MusicSongsActivity.this.getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(MusicSongsActivity.this.m.size())));
                } else {
                    MusicSongsActivity.this.v.setTitleText(MusicSongsActivity.this.getResources().getString(R.string._2130837613_res_0x7f02006d));
                }
            }

            public void e(MusicSong musicSong, final int i) {
                if (musicSong == null) {
                    return;
                }
                this.f9144a.setText(musicSong.getSongName());
                this.d.setText(musicSong.getSongSingerName());
                this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.e.b.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        e.this.cRc_(view, i);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                this.c.setVisibility(MusicSongsActivity.this.n ? 8 : 0);
                this.b.setVisibility(MusicSongsActivity.this.n ? 0 : 8);
                boolean booleanValue = ((Boolean) MusicSongsActivity.this.k.get(Integer.valueOf(i))).booleanValue();
                this.b.setChecked(booleanValue);
                if (!MusicSongsActivity.this.n) {
                    View view = e.this.e;
                    e eVar = e.this;
                    view.setOnLongClickListener(eVar.new ViewOnLongClickListenerC0253e(i, eVar.d));
                } else {
                    c(i, booleanValue, musicSong);
                }
                if (MusicSongsActivity.this.m.size() == 0) {
                    MusicSongsActivity.this.u.setIconVisible(2, 8);
                } else {
                    MusicSongsActivity.this.u.setIconVisible(2, 0);
                }
                if (e.this.d.size() - 1 == i) {
                    this.f.setVisibility(8);
                } else if (CommonUtil.ar()) {
                    this.f.setVisibility(8);
                } else {
                    this.f.setVisibility(0);
                }
            }
        }

        e(List<MusicSong> list, int i) {
            this.d = new ArrayList(16);
            this.c = LayoutInflater.from(MusicSongsActivity.this.f9135a);
            this.d = list;
            MusicSongsActivity.this.k = new HashMap(16);
            if (MusicSongsActivity.this.n) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    this.f9142a.put(Integer.valueOf(i2), 0);
                    MusicSongsActivity.this.k.put(Integer.valueOf(i2), false);
                }
            } else {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    this.f9142a.put(Integer.valueOf(i3), 8);
                    MusicSongsActivity.this.k.put(Integer.valueOf(i3), false);
                }
            }
            if (!MusicSongsActivity.this.n || i < 0) {
                return;
            }
            MusicSongsActivity.this.k.put(Integer.valueOf(i), true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<MusicSong> list = this.d;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: cRd_, reason: merged with bridge method [inline-methods] */
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            this.e = this.c.inflate(R.layout.activity_music_song_list_item, viewGroup, false);
            b bVar = new b(this.e);
            this.i = bVar;
            bVar.setIsRecyclable(false);
            return this.i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, int i) {
            if (this.d.isEmpty() || i < 0 || i >= this.d.size()) {
                return;
            }
            bVar.e(this.d.get(i), i);
        }

        /* renamed from: com.huawei.ui.device.activity.music.MusicSongsActivity$e$e, reason: collision with other inner class name */
        class ViewOnLongClickListenerC0253e implements View.OnLongClickListener {
            private List<MusicSong> b;
            private int c;

            ViewOnLongClickListenerC0253e(int i, List<MusicSong> list) {
                this.c = i;
                this.b = list;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                MusicSongsActivity.this.n = true;
                MusicSongsActivity.this.m.clear();
                int i = this.c;
                if (i >= 0 && i < this.b.size()) {
                    MusicSongsActivity.this.m.add(this.b.get(this.c));
                }
                MusicSongsActivity.this.e(MusicSongsActivity.this.n);
                for (int i2 = 0; i2 < this.b.size(); i2++) {
                    MusicSongsActivity.this.q.f9142a.put(Integer.valueOf(i2), 0);
                }
                MusicSongsActivity.this.k.put(Integer.valueOf(this.c), true);
                e.this.notifyDataSetChanged();
                gge.e(AnalyticsValue.HEALTH_MUSIC_MENU_LONG_CLICK_1090087.value(), MusicSongsActivity.this.e);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cRc_(View view, final int i) {
            View inflate = LayoutInflater.from(MusicSongsActivity.this).inflate(R.layout.delete_toolbar_popupwindow, (ViewGroup) null);
            final nqc nqcVar = new nqc(MusicSongsActivity.this.f9135a, inflate);
            nqcVar.cEh_(view, 14);
            inflate.findViewById(R.id.delete_music_ll).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.e.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (MusicSongsActivity.this.g == 2) {
                        MusicSongsActivity.this.d(i);
                        nqc nqcVar2 = nqcVar;
                        if (nqcVar2 != null) {
                            nqcVar2.b();
                        }
                        gge.e(AnalyticsValue.HEALTH_MUSIC_DELETE_SONG_FROM_MENU_1090089.value(), MusicSongsActivity.this.e);
                    } else {
                        MusicSongsActivity.this.c();
                    }
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }

    static class d extends BaseHandler<MusicSongsActivity> {
        d(MusicSongsActivity musicSongsActivity) {
            super(musicSongsActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cRe_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MusicSongsActivity musicSongsActivity, Message message) {
            switch (message.what) {
                case 1000:
                    musicSongsActivity.p.setVisibility(8);
                    musicSongsActivity.e(musicSongsActivity.n);
                    break;
                case 1001:
                    musicSongsActivity.e(musicSongsActivity.n);
                    break;
                case 1002:
                    musicSongsActivity.v.setTitleText(musicSongsActivity.b.getMemuNewName());
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        nrh.d(this.f9135a, getResources().getString(R.string._2130843214_res_0x7f02164e));
    }

    private void m() {
        LogUtil.a("MusicSongsActivity", "Enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.f9135a, this.d, intentFilter, LocalBroadcast.c, null);
    }

    private void p() {
        try {
            unregisterReceiver(this.d);
        } catch (IllegalArgumentException e2) {
            LogUtil.a("MusicSongsActivity", e2.getMessage());
        }
    }

    private void k() {
        MusicMenu musicMenu = this.b;
        if (musicMenu != null) {
            if (musicMenu.getMusicSongsList().size() == 0) {
                this.h.setVisibility(8);
                this.i.setVisibility(0);
            } else {
                this.h.setVisibility(0);
                this.i.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        if (z) {
            this.v.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428439_res_0x7f0b0457), nsf.h(R.string._2130850611_res_0x7f023333));
            this.v.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(this.m.size())));
            this.v.setRightTextButtonVisibility(8);
            this.u.setVisibility(0);
            if (this.m.size() == 500) {
                this.u.setIconTitle(1, getResources().getString(R.string._2130841400_res_0x7f020f38));
                this.u.setIcon(1, R.drawable._2131430281_res_0x7f0b0b89);
                this.o = true;
            } else {
                b(this.b.getMusicSongsList());
                this.u.setIcon(1, R.drawable._2131430296_res_0x7f0b0b98);
                this.o = false;
            }
        } else {
            this.v.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R.string._2130850617_res_0x7f023339));
            MusicMenu musicMenu = this.b;
            if (musicMenu != null) {
                this.v.setTitleText(musicMenu.getMenuName());
            }
            this.v.setRightTextButtonVisibility(0);
            this.u.setVisibility(8);
            this.m.clear();
            MusicMenu musicMenu2 = this.b;
            if (musicMenu2 != null) {
                this.q = new e(musicMenu2.getMusicSongsList(), -1);
            }
            this.s.setAdapter(this.q);
        }
        k();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_music_menu_songs);
        this.f9135a = this;
        this.e.put("click", "1");
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.c = intent.getStringArrayListExtra("menu_list");
            } catch (ArrayIndexOutOfBoundsException e2) {
                LogUtil.b("MusicSongsActivity", "get mAllMenuNameList error:", e2.getMessage());
            }
            MusicMenu d2 = jjm.e().d();
            if (d2 != null) {
                this.b = d2;
            }
            MusicMenu b = jjm.e().b();
            if (b != null) {
                this.j = b;
            }
            this.g = intent.getIntExtra("current_bluetooth_status", 2);
            if (intent.getIntExtra("song_num", 16) == 0) {
                d();
            }
        }
        m();
        l();
        j();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.n) {
            e(false);
            this.n = false;
        } else {
            super.onBackPressed();
        }
    }

    private void l() {
        this.s = (HealthRecycleView) findViewById(R.id.musics_songs_in_menu_list);
        this.h = (LinearLayout) findViewById(R.id.data_ll);
        this.i = (LinearLayout) findViewById(R.id.empty_ll);
        this.p = (LinearLayout) findViewById(R.id.load_ll);
        this.r = (HealthProgressBar) findViewById(R.id.load_progressbar);
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.load_progressbar);
        this.r = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        f();
        g();
        h();
    }

    private void h() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout_music_load_all_tips);
        this.l = relativeLayout;
        relativeLayout.setVisibility(this.b.getMusicSongsList().size() == this.b.getMenuMusicCount() ? 8 : 0);
        findViewById(R.id.load_all_music_confirm_tv).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicSongsActivity.this.l.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void i() {
        this.v.setRightTextButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View inflate = LayoutInflater.from(MusicSongsActivity.this.f9135a).inflate(R.layout.popview_menu_more_operation, (ViewGroup) null);
                final nqc nqcVar = new nqc(MusicSongsActivity.this.f9135a, inflate);
                nqcVar.cEh_(view, 14);
                inflate.findViewById(R.id.add_music_rl).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.8.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (MusicSongsActivity.this.g == 2) {
                            MusicSongsActivity.this.d();
                            nqcVar.b();
                            gge.e(AnalyticsValue.HEALTH_MUSIC_ADD_MUSIC_TO_MENU_1090092.value(), MusicSongsActivity.this.e);
                        } else {
                            MusicSongsActivity.this.c();
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                inflate.findViewById(R.id.rename_music_rl).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.8.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (MusicSongsActivity.this.g == 2) {
                            MusicSongsActivity.this.o();
                            nqcVar.b();
                        } else {
                            MusicSongsActivity.this.c();
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                inflate.findViewById(R.id.del_menu_rl).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.8.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (MusicSongsActivity.this.g == 2) {
                            MusicSongsActivity.this.b();
                            nqcVar.b();
                            gge.e(AnalyticsValue.HEALTH_MUSIC_DELETE_MENU_1090091.value(), MusicSongsActivity.this.e);
                        } else {
                            MusicSongsActivity.this.c();
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.local_music_resource_titlebar);
        this.v = customTitleBar;
        MusicMenu musicMenu = this.b;
        if (musicMenu != null) {
            customTitleBar.setTitleText(musicMenu.getMenuName());
        }
        this.v.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.v.setRightTextButtonClickable(true);
        this.v.setRightTextButtonVisibility(0);
        i();
        this.v.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicSongsActivity.this.n) {
                    MusicSongsActivity.this.e(false);
                    MusicSongsActivity.this.n = false;
                } else {
                    MusicSongsActivity.this.n();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void f() {
        this.u = (HealthToolBar) findViewById(R.id.bottom_operate_toolbar);
        this.u.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.u.setOnSingleTapListener(this.x);
        this.u.setIcon(1, R.drawable._2131430296_res_0x7f0b0b98);
        b(this.b.getMusicSongsList());
        this.u.setIcon(2, R.drawable._2131429906_res_0x7f0b0a12);
        this.u.setIconTitle(2, getResources().getString(R.string._2130843228_res_0x7f02165c));
        this.u.setIconVisible(3, 8);
    }

    private void j() {
        if (this.b == null) {
            return;
        }
        MusicLinearLayoutManager musicLinearLayoutManager = new MusicLinearLayoutManager(this.f9135a);
        this.t = musicLinearLayoutManager;
        this.s.setLayoutManager(musicLinearLayoutManager);
        e eVar = new e(this.b.getMusicSongsList(), -1);
        this.q = eVar;
        this.s.setAdapter(eVar);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Intent intent = new Intent(this.f9135a, (Class<?>) DefaultMenuSongsListActivity.class);
        MusicMenu musicMenu = new MusicMenu();
        musicMenu.getMusicSongsList().addAll(this.j.getMusicSongsList());
        if (this.b != null) {
            musicMenu.getMusicSongsList().removeAll(this.b.getMusicSongsList());
        }
        jjm.e().d(musicMenu);
        jjm.e().e(this.b);
        startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        final MusicMenuViewDialog.Builder builder = new MusicMenuViewDialog.Builder(this.f9135a);
        MusicMenuViewDialog b = builder.b();
        if (b == null) {
            LogUtil.a("MusicSongsActivity", "modifyMenuNameByUser failed! Create dialog error!");
            return;
        }
        b.show();
        builder.d().setText(R.string._2130843229_res_0x7f02165d);
        builder.cVB_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicSongsActivity.this.a(builder.cVA_().getText().toString());
                gge.e(AnalyticsValue.HEALTH_MUSIC_RENAME_MENU_1090090.value(), MusicSongsActivity.this.e);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cVA_().addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.6
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (MusicSongsActivity.this.c.contains(editable.toString())) {
                    builder.c(false);
                    builder.a().setError(MusicSongsActivity.this.getString(R.string._2130843235_res_0x7f021663));
                } else {
                    builder.c(true);
                    builder.a().setError("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        LogUtil.a("MusicSongsActivity", "modifyMenuName enter");
        try {
            final OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(2);
            operationStruct.setFolderIndex(Integer.parseInt(this.b.getMenuIndex()));
            operationStruct.setFolderName(str);
            jjd.b(this.f9135a).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("MusicSongsActivity", "receive modifyMenuName rsp time:", Long.valueOf(System.currentTimeMillis()));
                    if (i == 100000) {
                        MusicSongsActivity.this.b.setMemuNewName(operationStruct.getFolderName());
                        Iterator it = MusicSongsActivity.this.c.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (((String) it.next()).equals(MusicSongsActivity.this.b.getMenuName())) {
                                it.remove();
                                MusicSongsActivity.this.c.add(operationStruct.getFolderName());
                                MusicSongsActivity.this.b.setMenuName(operationStruct.getFolderName());
                                break;
                            }
                        }
                        Message obtainMessage = MusicSongsActivity.this.f.obtainMessage();
                        obtainMessage.what = 1002;
                        MusicSongsActivity.this.f.sendMessage(obtainMessage);
                        return;
                    }
                    LogUtil.a("MusicSongsActivity", "modifyMenuName failed, errCode:", Integer.valueOf(i));
                }
            });
            return true;
        } catch (NumberFormatException e2) {
            LogUtil.a("MusicSongsActivity", "modifyMenuName failed, exception:", e2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (!this.o) {
            this.u.setIconTitle(1, getResources().getString(R.string._2130841400_res_0x7f020f38));
            this.u.setIcon(1, R.drawable._2131430281_res_0x7f0b0b89);
            this.o = true;
            MusicMenu musicMenu = this.b;
            if (musicMenu != null) {
                if (musicMenu.getMusicSongsList().size() <= 500) {
                    q();
                } else {
                    r();
                }
            }
            this.q.notifyDataSetChanged();
            return;
        }
        b(this.b.getMusicSongsList());
        this.u.setIcon(1, R.drawable._2131430296_res_0x7f0b0b98);
        this.o = false;
        this.m.clear();
        for (int i = 0; i < this.b.getMusicSongsList().size(); i++) {
            this.k.put(Integer.valueOf(i), false);
        }
        this.q.notifyDataSetChanged();
    }

    private void q() {
        this.m.clear();
        this.m.addAll(this.b.getMusicSongsList());
        for (int i = 0; i < this.b.getMusicSongsList().size(); i++) {
            this.k.put(Integer.valueOf(i), true);
        }
    }

    private void r() {
        int i = 0;
        if (this.m.size() == 0) {
            this.m.addAll(this.b.getMusicSongsList().subList(0, 500));
            while (i < 500) {
                this.k.put(Integer.valueOf(i), true);
                i++;
            }
            return;
        }
        ArrayList<MusicSong> musicSongsList = this.b.getMusicSongsList();
        while (i < musicSongsList.size() && this.m.size() < 500) {
            if (!this.m.contains(musicSongsList.get(i))) {
                this.m.add(musicSongsList.get(i));
                this.k.put(Integer.valueOf(i), true);
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        LogUtil.a("MusicSongsActivity", "deleteAllMenuInfo enter");
        try {
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(1);
            operationStruct.setFolderIndex(Integer.parseInt(this.b.getMenuIndex()));
            jjd.b(this.f9135a).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.15
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("MusicSongsActivity", "receive deleteAllMenuInfo rsp");
                    if (i != 100000) {
                        LogUtil.a("MusicSongsActivity", "deleteAllMenuInfo failed, errCode:", Integer.valueOf(i));
                        MusicSongsActivity.this.finish();
                    } else {
                        Intent intent = new Intent();
                        jjm.e().e(MusicSongsActivity.this.b);
                        MusicSongsActivity.this.setResult(3, intent);
                        MusicSongsActivity.this.finish();
                    }
                }
            });
        } catch (NumberFormatException e2) {
            LogUtil.a("MusicSongsActivity", "deleteAllMenuInfo failed, exception:", e2);
            finish();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        LogUtil.a("MusicSongsActivity", "deleteSongFromMenu enter");
        this.p.setVisibility(0);
        ArrayList arrayList = new ArrayList(16);
        try {
            Iterator<MusicSong> it = this.m.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(Integer.parseInt(it.next().getSongIndex())));
            }
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(4);
            operationStruct.setFolderIndex(Integer.parseInt(this.b.getMenuIndex()));
            operationStruct.setMusicIndexs(arrayList);
            jjd.b(this.f9135a).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("MusicSongsActivity", "receive deleteSongFromMenu rsp");
                    if (i == 100000) {
                        MusicSongsActivity.this.o = false;
                        MusicSongsActivity.this.n = false;
                        if (MusicSongsActivity.this.b != null) {
                            MusicSongsActivity.this.b.removeRealMusicCount(MusicSongsActivity.this.m);
                        }
                        LogUtil.a("MusicSongsActivity", "deleteSongFromMenu succeed!");
                    } else {
                        LogUtil.a("MusicSongsActivity", "deleteSongFromMenu failed, errCode:", Integer.valueOf(i));
                    }
                    Message obtainMessage = MusicSongsActivity.this.f.obtainMessage();
                    obtainMessage.what = 1000;
                    MusicSongsActivity.this.f.sendMessage(obtainMessage);
                }
            });
            return true;
        } catch (NumberFormatException e2) {
            LogUtil.a("MusicSongsActivity", "delete music in folder failed, exception:", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(final int i) {
        String str;
        LogUtil.a("MusicSongsActivity", "deleteSongFromMenuByUser enter time:");
        try {
            ArrayList arrayList = new ArrayList(16);
            final ArrayList<MusicSong> arrayList2 = new ArrayList<>(16);
            MusicMenu musicMenu = this.b;
            if (musicMenu == null) {
                str = "0";
            } else {
                arrayList2 = musicMenu.getMusicSongsList();
                str = this.b.getMenuIndex();
            }
            if (!koq.c(arrayList2) || arrayList2.size() <= i) {
                return true;
            }
            arrayList.add(Integer.valueOf(Integer.parseInt(arrayList2.get(i).getSongIndex())));
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(4);
            operationStruct.setFolderIndex(jds.c(str, 10));
            operationStruct.setMusicIndexs(arrayList);
            jjd.b(this.f9135a).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicSongsActivity.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("MusicSongsActivity", "receive deleteSongFromMenuByUser rsp");
                    if (i2 != 100000) {
                        LogUtil.a("MusicSongsActivity", "delete music in folder failed,errCode:", Integer.valueOf(i2));
                        return;
                    }
                    arrayList2.remove(i);
                    MusicSongsActivity.this.b.setMenuMusicCount(MusicSongsActivity.this.b.getMenuMusicCount() - 1);
                    Message obtainMessage = MusicSongsActivity.this.f.obtainMessage();
                    obtainMessage.what = 1001;
                    MusicSongsActivity.this.f.sendMessage(obtainMessage);
                    LogUtil.a("MusicSongsActivity", "delete music in folder succeed!");
                }
            });
            return true;
        } catch (NumberFormatException e2) {
            LogUtil.a("MusicSongsActivity", "delete music in folder failed, exception:", e2);
            return false;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        p();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            if (keyEvent.getAction() != 0 || keyEvent.getRepeatCount() != 0) {
                return true;
            }
            if (this.n) {
                e(false);
                this.n = false;
                return true;
            }
            n();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("MusicSongsActivity", "onActivityResult enter, req:", Integer.valueOf(i), "result:", Integer.valueOf(i2));
        if (intent == null) {
            LogUtil.a("MusicSongsActivity", "return data is null");
            return;
        }
        if (i != 1) {
            return;
        }
        MusicMenu d2 = jjm.e().d();
        if (d2 == null) {
            LogUtil.a("MusicSongsActivity", "get menu is null");
            return;
        }
        MusicMenu musicMenu = this.b;
        if (musicMenu != null) {
            musicMenu.addRealMusicCount(d2.getMusicSongsList());
            this.q = new e(this.b.getMusicSongsList(), -1);
        }
        this.s.setAdapter(this.q);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Intent intent = new Intent();
        jjm.e().e(this.b);
        setResult(1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<MusicSong> list) {
        this.u.setIconTitle(1, getResources().getString(R.string._2130841399_res_0x7f020f37));
        if (koq.b(list)) {
            LogUtil.a("MusicSongsActivity", "musicSongList is empty");
        } else {
            if (!jjm.e().i() || list.size() <= 500) {
                return;
            }
            this.u.setIconTitle(1, getResources().getString(R.string._2130847719_res_0x7f0227e7));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
