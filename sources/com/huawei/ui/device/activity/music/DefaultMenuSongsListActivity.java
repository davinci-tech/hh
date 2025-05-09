package com.huawei.ui.device.activity.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jjm;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DefaultMenuSongsListActivity extends BaseActivity {
    private c c;
    private DefaultMenuSongsListActivity e;
    private ListView f;
    private LinearLayout i;
    private HealthProgressBar j;
    private CustomTitleBar k;
    private e h = new e(this);
    private MusicMenu b = new MusicMenu();
    private MusicMenu m = new MusicMenu();
    private MusicMenu d = new MusicMenu();
    private int g = 2;

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f9111a = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DefaultMenuSongsListActivity", "mConnectStateChangedReceiver intent is null.");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("DefaultMenuSongsListActivity", "mConnectStateChangedReceiver intent:", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("DefaultMenuSongsListActivity", "mConnectStateChangedReceiver deviceInfo is null.");
                    return;
                }
                if (!jjj.e(deviceInfo)) {
                    LogUtil.h("DefaultMenuSongsListActivity", "This device does not have the correspond capability.");
                    return;
                }
                DefaultMenuSongsListActivity.this.g = deviceInfo.getDeviceConnectState();
                int i = DefaultMenuSongsListActivity.this.g;
                if (i == 1) {
                    LogUtil.a("DefaultMenuSongsListActivity", "current device connecting.");
                    return;
                }
                if (i == 2) {
                    LogUtil.a("DefaultMenuSongsListActivity", "current device connected.");
                } else if (i == 3) {
                    LogUtil.a("DefaultMenuSongsListActivity", "current device disconnected.");
                } else {
                    LogUtil.h("DefaultMenuSongsListActivity", "current device state is unknown.");
                }
            }
        }
    };

    private void c() {
        LogUtil.a("DefaultMenuSongsListActivity", "register connect state broadcast.");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.e, this.f9111a, intentFilter, LocalBroadcast.c, null);
    }

    private void i() {
        LogUtil.a("DefaultMenuSongsListActivity", "unregister connect state broadcast.");
        try {
            unregisterReceiver(this.f9111a);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DefaultMenuSongsListActivity", "unregister connect state broadcast occur IllegalArgumentException.");
        }
    }

    class c extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private List<MusicSong> f9114a;
        private final LayoutInflater b;
        private boolean[] c;
        private a d;
        private View g;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        class a {

            /* renamed from: a, reason: collision with root package name */
            ImageView f9115a;
            ImageView b;
            HealthCheckBox c;
            HealthTextView d;
            HealthTextView e;

            a() {
            }
        }

        c(List<MusicSong> list) {
            this.b = LayoutInflater.from(DefaultMenuSongsListActivity.this.e);
            this.f9114a = list;
            if (list != null) {
                this.c = new boolean[list.size()];
            }
        }

        public int e() {
            List<MusicSong> list = this.f9114a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<MusicSong> list = this.f9114a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MusicSong getItem(int i) {
            if (i < 0 || i > this.f9114a.size() - 1) {
                return null;
            }
            return this.f9114a.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                this.g = this.b.inflate(R.layout.activity_music_song_list_item, (ViewGroup) null);
                a aVar = new a();
                this.d = aVar;
                aVar.d = (HealthTextView) this.g.findViewById(R.id.music_song_name_tv);
                this.d.e = (HealthTextView) this.g.findViewById(R.id.music_song_singer_tv);
                this.d.c = (HealthCheckBox) this.g.findViewById(R.id.cb_more_or_select);
                this.d.f9115a = (ImageView) this.g.findViewById(R.id.imageview_more_or_select);
                this.d.b = (ImageView) this.g.findViewById(R.id.under_line);
                this.g.setTag(this.d);
            } else {
                this.g = view;
                Object tag = view.getTag();
                if (tag instanceof a) {
                    this.d = (a) tag;
                }
            }
            MusicSong item = getItem(i);
            if (item != null) {
                this.d.d.setText(item.getSongName());
                this.d.e.setText(item.getSongSingerName());
            }
            this.d.c.setVisibility(0);
            this.d.f9115a.setVisibility(8);
            if (this.f9114a.size() - 1 == i) {
                this.d.b.setVisibility(8);
            } else if (CommonUtil.ar()) {
                this.d.b.setVisibility(8);
            } else {
                this.d.b.setVisibility(0);
            }
            this.d.c.setFocusable(false);
            this.d.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.c.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    int i2;
                    if (c.this.c != null && (i2 = i) >= 0 && i2 < c.this.c.length) {
                        c.this.c[i] = z;
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            boolean[] zArr = this.c;
            if (zArr != null && i >= 0 && i < zArr.length) {
                this.d.c.setChecked(this.c[i]);
            }
            return this.g;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_default_menu_songs_list);
        this.e = this;
        if (getIntent() != null) {
            MusicMenu b = jjm.e().b();
            if (b != null) {
                this.d = b;
            }
            MusicMenu d = jjm.e().d();
            if (d != null) {
                this.b = d;
            }
        }
        c();
        e();
        a();
    }

    private void e() {
        d();
        this.f = (ListView) findViewById(R.id.default_menu_songs_listview);
        this.i = (LinearLayout) findViewById(R.id.load_ll);
        this.j = (HealthProgressBar) findViewById(R.id.load_progressbar);
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.load_progressbar);
        this.j = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
    }

    private void a() {
        c cVar = new c(this.d.getMusicSongsList());
        this.c = cVar;
        this.f.setAdapter((ListAdapter) cVar);
        this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Object tag = view.getTag();
                if (tag instanceof c.a) {
                    c.a aVar = (c.a) tag;
                    if (DefaultMenuSongsListActivity.this.c.c != null && i >= 0 && i < DefaultMenuSongsListActivity.this.c.c.length) {
                        if (DefaultMenuSongsListActivity.this.c.c[i]) {
                            DefaultMenuSongsListActivity.this.c.c[i] = false;
                            aVar.c.setChecked(false);
                        } else {
                            DefaultMenuSongsListActivity.this.c.c[i] = true;
                            aVar.c.setChecked(true);
                        }
                    }
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.default_menu_songs_titlebar);
        this.k = customTitleBar;
        customTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R.string._2130841395_res_0x7f020f33));
        this.k.setRightTextButtonClickable(true);
        this.k.setRightTextButtonVisibility(0);
        this.k.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DefaultMenuSongsListActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.k.setRightTextButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DefaultMenuSongsListActivity.this.g == 2) {
                    if (!nsn.o()) {
                        DefaultMenuSongsListActivity.this.i.setVisibility(0);
                        LogUtil.a("DefaultMenuSongsListActivity", "initTitleBarView UPDATE_UI_MENU_VISIBLE");
                        DefaultMenuSongsListActivity.this.b();
                    } else {
                        LogUtil.a("DefaultMenuSongsListActivity", "click to fast!");
                    }
                } else {
                    nrh.d(DefaultMenuSongsListActivity.this.e, DefaultMenuSongsListActivity.this.getResources().getString(R.string._2130843214_res_0x7f02164e));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.m = new MusicMenu();
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < this.c.e(); i++) {
            try {
                if (this.c.c[i]) {
                    MusicSong musicSong = (MusicSong) this.c.f9114a.get(i);
                    this.m.getMusicSongsList().add(musicSong);
                    arrayList.add(Integer.valueOf(Integer.parseInt(musicSong.getSongIndex())));
                }
            } catch (NumberFormatException unused) {
                LogUtil.a("DefaultMenuSongsListActivity", "get mCurrentMenu's index is null");
                finish();
                return;
            }
        }
        OperationStruct operationStruct = new OperationStruct();
        operationStruct.setOperationType(3);
        operationStruct.setFolderIndex(Integer.parseInt(this.b.getMenuIndex()));
        operationStruct.setMusicIndexs(arrayList);
        jjd.b(this.e).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.DefaultMenuSongsListActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 100000) {
                    Intent intent = new Intent();
                    jjm.e().e(DefaultMenuSongsListActivity.this.m);
                    DefaultMenuSongsListActivity.this.setResult(1, intent);
                    DefaultMenuSongsListActivity.this.finish();
                } else {
                    LogUtil.a("DefaultMenuSongsListActivity", "add music:", " errCode:", Integer.valueOf(i2));
                    DefaultMenuSongsListActivity.this.finish();
                }
                Message obtainMessage = DefaultMenuSongsListActivity.this.h.obtainMessage();
                obtainMessage.what = 1000;
                DefaultMenuSongsListActivity.this.h.sendMessage(obtainMessage);
                LogUtil.a("DefaultMenuSongsListActivity", "notifyMenuActivityRefreshData UPDATE_UI_MENU_GONE");
            }
        });
    }

    static class e extends BaseHandler<DefaultMenuSongsListActivity> {
        e(DefaultMenuSongsListActivity defaultMenuSongsListActivity) {
            super(defaultMenuSongsListActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cQE_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DefaultMenuSongsListActivity defaultMenuSongsListActivity, Message message) {
            if (message.what == 1000) {
                defaultMenuSongsListActivity.i.setVisibility(8);
            } else {
                LogUtil.a("DefaultMenuSongsListActivity", "handleMessageWhenReferenceNotNull switch default ");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
