package com.huawei.ui.device.activity.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.fragment.music.AlbumFragment;
import com.huawei.ui.device.fragment.music.BaseLocalMusicAdapter;
import com.huawei.ui.device.fragment.music.BaseLocalMusicFragment;
import com.huawei.ui.device.fragment.music.FolderFragment;
import com.huawei.ui.device.fragment.music.SingerFragment;
import com.huawei.ui.device.fragment.music.SongFragment;
import defpackage.jjj;
import defpackage.jjm;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.occ;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class LocalMusicResourceActivity extends BaseActivity {
    private HealthViewPager ac;
    private String b;
    private LocalMusicResourceActivity d;
    private LinearLayout g;
    private BaseLocalMusicFragment h;
    private LinearLayout m;
    private HealthProgressBar r;
    private HealthSubTabWidget t;
    private ArrayList<String> u;
    private HealthToolBar v;
    private CustomTitleBar z;
    private int y = 0;
    private int q = 0;
    private int p = 0;
    private boolean l = false;
    private int f = 2;
    private MusicMenu i = new MusicMenu();
    private List<MusicSong> s = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<MusicSong> f9116a = new ArrayList<>(16);
    private SongFragment w = null;
    private SingerFragment x = null;
    private AlbumFragment e = null;
    private FolderFragment k = null;
    private ArrayList<Fragment> o = new ArrayList<>(16);
    private String j = "";
    private d n = new d(this);
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("LocalMusicResourceActivity", "receiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("LocalMusicResourceActivity", "receiver intent: ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("LocalMusicResourceActivity", "receiver deviceInfo is null");
                    return;
                }
                if (!jjj.e(deviceInfo)) {
                    LogUtil.h("LocalMusicResourceActivity", "This device does not have the correspond capability.");
                    return;
                }
                LocalMusicResourceActivity.this.f = deviceInfo.getDeviceConnectState();
                int i = LocalMusicResourceActivity.this.f;
                if (i == 1) {
                    LogUtil.a("LocalMusicResourceActivity", "device connecting");
                    return;
                }
                if (i == 2) {
                    LogUtil.a("LocalMusicResourceActivity", "device connected");
                } else if (i == 3) {
                    LogUtil.a("LocalMusicResourceActivity", "device disconnected");
                } else {
                    LogUtil.h("LocalMusicResourceActivity", "device state is unknown");
                }
            }
        }
    };

    private void n() {
        try {
            unregisterReceiver(this.c);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("LocalMusicResourceActivity", "unregister broadcast occur IllegalArgumentException.");
        }
    }

    private void m() {
        LogUtil.a("LocalMusicResourceActivity", "Enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.d, this.c, intentFilter, LocalBroadcast.c, null);
    }

    static class d extends BaseHandler<LocalMusicResourceActivity> {
        d(LocalMusicResourceActivity localMusicResourceActivity) {
            super(localMusicResourceActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cQH_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(LocalMusicResourceActivity localMusicResourceActivity, Message message) {
            if (localMusicResourceActivity == null || message == null) {
                return;
            }
            if (message.what == 1014) {
                c(localMusicResourceActivity);
            } else {
                LogUtil.h("LocalMusicResourceActivity", "handleMessageWhenReferenceNotNull unknown message");
            }
        }

        private void c(LocalMusicResourceActivity localMusicResourceActivity) {
            LogUtil.a("LocalMusicResourceActivity", "refreshLocalMusicUi enter");
            localMusicResourceActivity.t.setVisibility(0);
            localMusicResourceActivity.g.setVisibility(0);
            d(localMusicResourceActivity);
            localMusicResourceActivity.m.setVisibility(8);
        }

        private void d(LocalMusicResourceActivity localMusicResourceActivity) {
            if (localMusicResourceActivity.s == null || localMusicResourceActivity.f9116a == null || localMusicResourceActivity.v == null) {
                return;
            }
            if (localMusicResourceActivity.s.size() == localMusicResourceActivity.f9116a.size()) {
                localMusicResourceActivity.v.setVisibility(8);
            } else {
                localMusicResourceActivity.v.setVisibility(0);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        setContentView(R.layout.activity_local_music_resouce);
        this.d = this;
        this.z = (CustomTitleBar) findViewById(R.id.local_music_resource_titlebar);
        this.t = (HealthSubTabWidget) findViewById(R.id.hw_local_music_tabs);
        this.ac = (HealthViewPager) findViewById(R.id.hw_local_music_viewpager);
        this.v = (HealthToolBar) findViewById(R.id.select_toolbar);
        this.g = (LinearLayout) findViewById(R.id.local_music_data_layout);
        this.m = (LinearLayout) findViewById(R.id.local_music_load_layout);
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.local_music_load_progressbar);
        this.r = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.i = jjm.e().b();
            this.u = extras.getStringArrayList("device_support_music_type_list");
            this.q = extras.getInt("max_music_number", this.q);
            this.y = extras.getInt("watch_remaining_space", this.y);
            this.p = extras.getInt("musicCount", this.p);
        }
        m();
        h();
        l();
        f();
        HealthViewPager healthViewPager = this.ac;
        if (healthViewPager != null) {
            healthViewPager.setScrollHeightArea(200);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LocalMusicResourceActivity.this.j();
            }
        });
        this.j = SharedPreferenceManager.b(this, Integer.toString(10024), "local_music_is_first_enter");
        this.z.setRightButtonClickable(false);
        this.z.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430219_res_0x7f0b0b4b), nsf.h(R.string._2130841395_res_0x7f020f33));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("LocalMusicResourceActivity", "initLocalMusicList enter");
        occ occVar = new occ();
        ArrayList<MusicSong> arrayList = new ArrayList<>(16);
        List<MusicSong> c = occVar.c(this.d, this.u);
        this.s = c;
        for (MusicSong musicSong : c) {
            if (this.i != null) {
                LogUtil.a("LocalMusicResourceActivity", "localeName:", musicSong.getSongName(), "localeSinger:", musicSong.getSongSingerName());
                Iterator<MusicSong> it = this.i.getMusicSongsList().iterator();
                while (it.hasNext()) {
                    c(musicSong, it.next(), arrayList);
                }
            } else {
                LogUtil.h("LocalMusicResourceActivity", "mDefaultMusicMenu is null.");
            }
        }
        LogUtil.a("LocalMusicResourceActivity", "mLocalMusicLists size:", Integer.valueOf(this.s.size()), "repeatMusicList size:", Integer.valueOf(arrayList.size()));
        this.f9116a = arrayList;
        LogUtil.a("LocalMusicResourceActivity", "send UPDATE_LOCAL_MUSIC_UI_MESSAGE:");
        Message obtainMessage = this.n.obtainMessage();
        obtainMessage.what = 1014;
        this.n.sendMessage(obtainMessage);
    }

    private void c(MusicSong musicSong, MusicSong musicSong2, ArrayList<MusicSong> arrayList) {
        if (musicSong2.getSongName() == null || !musicSong2.getSongName().equals(musicSong.getSongName())) {
            return;
        }
        LogUtil.a("LocalMusicResourceActivity", "remoteName:", musicSong2.getSongName(), "remoteSinger:", musicSong2.getSongSingerName());
        if (musicSong2.getSongSingerName() != null) {
            if (musicSong2.getSongSingerName().equals(musicSong.getSongSingerName())) {
                arrayList.add(musicSong);
                return;
            }
            return;
        }
        arrayList.add(musicSong);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getResources().getString(R.string._2130843236_res_0x7f021664));
        builder.czz_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(LocalMusicResourceActivity.this, Integer.toString(10024), "local_music_is_first_enter", "true", new StorageParams());
                LocalMusicResourceActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    private void h() {
        this.z.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R.string._2130841395_res_0x7f020f33));
        this.z.setRightButtonVisibility(0);
        this.z.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalMusicResourceActivity.this.f == 2) {
                    if (!"true".equals(LocalMusicResourceActivity.this.j)) {
                        LocalMusicResourceActivity.this.i();
                    } else {
                        LocalMusicResourceActivity.this.g();
                    }
                } else {
                    nrh.d(LocalMusicResourceActivity.this.d, LocalMusicResourceActivity.this.getResources().getString(R.string._2130843214_res_0x7f02164e));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.z.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalMusicResourceActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ArrayList<MusicSong> arrayList = new ArrayList<>(16);
        ArrayList<Fragment> arrayList2 = this.o;
        if (arrayList2 != null && arrayList2.size() > 0) {
            BaseLocalMusicFragment baseLocalMusicFragment = this.o.get(0) instanceof BaseLocalMusicFragment ? (BaseLocalMusicFragment) this.o.get(0) : null;
            if (baseLocalMusicFragment != null) {
                arrayList.addAll(baseLocalMusicFragment.e());
            }
        }
        LogUtil.a("LocalMusicResourceActivity", "setRightTextButtonOnClickListener size==", Integer.valueOf(arrayList.size()));
        if (arrayList.size() > 0) {
            if (c(arrayList)) {
                LogUtil.a("LocalMusicResourceActivity", "insufficientSpaceWatch");
                return;
            }
            Intent intent = new Intent();
            jjm.e().e(arrayList);
            setResult(1, intent);
            finish();
        }
    }

    private boolean c(ArrayList<MusicSong> arrayList) {
        int i = this.q;
        int size = arrayList.size();
        int size2 = this.i.getMusicSongsList().size();
        if (jjm.e().i()) {
            size2 = this.p;
        }
        LogUtil.b("LocalMusicResourceActivity", "maxMusicNumber: ", Integer.valueOf(i), " addSongListsSize: ", Integer.valueOf(size), " tempMusicCount: ", Integer.valueOf(size2));
        Iterator<MusicSong> it = arrayList.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().getSongSize();
        }
        long j2 = j / 1048576;
        int i2 = this.y;
        LogUtil.b("LocalMusicResourceActivity", "sumConversionMusicListMemory:", Long.valueOf(j2), " storageMemory:", Integer.valueOf(this.y));
        if (i2 < j2) {
            c(R.string._2130843223_res_0x7f021657, getResources().getQuantityString(R.plurals._2130903259_res_0x7f0300db, 0, Integer.valueOf(this.y), Integer.valueOf(this.y / 5)));
            return true;
        }
        if (size + size2 <= i) {
            return false;
        }
        c(R.string._2130843363_res_0x7f0216e3, getResources().getQuantityString(R.plurals._2130903263_res_0x7f0300df, 0, Integer.valueOf(i)));
        return true;
    }

    private void c(int i, String str) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.d);
        builder.e(i);
        builder.c(str);
        builder.cyn_(R.string._2130841554_res_0x7f020fd2, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                LogUtil.a("LocalMusicResourceActivity", "OK!");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        });
        CustomAlertDialog c = builder.c();
        if (c != null) {
            c.show();
        }
    }

    private void l() {
        this.w = new SongFragment();
        this.x = new SingerFragment();
        this.e = new AlbumFragment();
        this.k = new FolderFragment();
        this.o.add(this.w);
        this.o.add(this.x);
        this.o.add(this.e);
        this.o.add(this.k);
        b(this.w);
        k();
        this.ac.setOffscreenPageLimit(16);
        this.b = MusicSong.SORT_TYPE_SONG;
        t();
    }

    private void f() {
        this.v.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.v.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.v.setIconTitle(2, getResources().getString(R.string._2130841399_res_0x7f020f37));
        this.v.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.7
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public void onSingleTap(int i) {
                if (i == 2) {
                    LocalMusicResourceActivity.this.h.b(LocalMusicResourceActivity.this.l);
                }
            }
        });
    }

    private void k() {
        nqx nqxVar = new nqx(this, this.ac, this.t);
        nqxVar.c(this.t.c(getResources().getString(R.string._2130843238_res_0x7f021666)), this.w, true);
        nqxVar.c(this.t.c(getResources().getString(R.string._2130843239_res_0x7f021667)), this.x, false);
        nqxVar.c(this.t.c(getResources().getString(R.string._2130843240_res_0x7f021668)), this.e, false);
        nqxVar.c(this.t.c(getResources().getString(R.string._2130843241_res_0x7f021669)), this.k, false);
    }

    private void t() {
        this.ac.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.device.activity.music.LocalMusicResourceActivity.9
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("LocalMusicResourceActivity", "CoreSleep onPageSelected position = ", Integer.valueOf(i));
                if (i == 0) {
                    LocalMusicResourceActivity.this.b = MusicSong.SORT_TYPE_SONG;
                    if (LocalMusicResourceActivity.this.s == null || LocalMusicResourceActivity.this.f9116a == null || LocalMusicResourceActivity.this.v == null) {
                        return;
                    }
                    if (LocalMusicResourceActivity.this.s.size() == LocalMusicResourceActivity.this.f9116a.size()) {
                        LocalMusicResourceActivity.this.v.setVisibility(8);
                        return;
                    } else {
                        LocalMusicResourceActivity.this.v.setVisibility(0);
                        return;
                    }
                }
                if (i == 1) {
                    LocalMusicResourceActivity.this.b = MusicSong.SORT_TYPE_SINGER;
                    LocalMusicResourceActivity.this.o();
                } else if (i == 2) {
                    LocalMusicResourceActivity.this.b = MusicSong.SORT_TYPE_ALBUM;
                    LocalMusicResourceActivity.this.o();
                } else if (i == 3) {
                    LocalMusicResourceActivity.this.b = MusicSong.SORT_TYPE_FOLDER;
                    LocalMusicResourceActivity.this.o();
                } else {
                    LogUtil.a("LocalMusicResourceActivity", "no this fragment");
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        BaseLocalMusicFragment baseLocalMusicFragment = this.h;
        if (baseLocalMusicFragment == null) {
            return;
        }
        BaseLocalMusicAdapter d2 = baseLocalMusicFragment.d();
        if (d2 != null && d2.a() == 0) {
            this.h.c();
        }
        HealthToolBar healthToolBar = this.v;
        if (healthToolBar != null) {
            healthToolBar.setVisibility(8);
        }
    }

    public List<MusicSong> e() {
        return this.s;
    }

    public List<String> a() {
        return this.u;
    }

    public String b() {
        return this.b;
    }

    public HealthToolBar c() {
        return this.v;
    }

    public List<MusicSong> d() {
        return this.f9116a;
    }

    public void b(BaseLocalMusicFragment baseLocalMusicFragment) {
        this.h = baseLocalMusicFragment;
    }

    public void e(int i, Boolean bool) {
        if (i == 0) {
            this.z.setTitleText(getResources().getString(R.string._2130843211_res_0x7f02164b));
            this.z.setRightButtonClickable(false);
            this.z.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430219_res_0x7f0b0b4b), nsf.h(R.string._2130841395_res_0x7f020f33));
        } else {
            this.z.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(i)));
            this.z.setRightButtonClickable(true);
            this.z.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R.string._2130841395_res_0x7f020f33));
        }
        if (bool.booleanValue()) {
            this.l = true;
            this.v.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
            this.v.setIconTitle(2, getResources().getString(R.string._2130841400_res_0x7f020f38));
        } else {
            this.l = false;
            this.v.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            this.v.setIconTitle(2, getResources().getString(R.string._2130841399_res_0x7f020f37));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BaseLocalMusicFragment baseLocalMusicFragment = this.h;
        if (baseLocalMusicFragment != null) {
            if (!baseLocalMusicFragment.c() || isFinishing()) {
                return;
            }
            super.onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        n();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
