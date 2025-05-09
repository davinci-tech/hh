package com.huawei.ui.device.activity.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jjj;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class MusicUnCompletedActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f9148a;
    private c b;
    private HealthButton d;
    private HealthButton e;
    private LinearLayout f;
    private ListView g;
    private LinearLayout i;
    private ArrayList<MusicSong> j = new ArrayList<>(16);
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.music.MusicUnCompletedActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("MusicUnCompletedActivity", "mConnectStateChangedReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("MusicUnCompletedActivity", "mConnectStateChangedReceiver onReceive intent: ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    if (intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                        if (deviceInfo == null) {
                            LogUtil.h("MusicUnCompletedActivity", "mConnectStateChangedReceiver onReceive currentDeviceInfo is null.");
                            return;
                        }
                        if (jjj.e(deviceInfo)) {
                            MusicUnCompletedActivity.this.f9148a = deviceInfo.getDeviceConnectState();
                            int i = MusicUnCompletedActivity.this.f9148a;
                            if (i == 1) {
                                LogUtil.a("MusicUnCompletedActivity", "DEVICE_CONNECTING");
                                return;
                            }
                            if (i == 2) {
                                LogUtil.a("MusicUnCompletedActivity", "DEVICE_CONNECTED");
                                MusicUnCompletedActivity.this.finish();
                                return;
                            } else if (i == 3) {
                                LogUtil.a("MusicUnCompletedActivity", "DEVICE_DISCONNECTED");
                                return;
                            } else {
                                LogUtil.h("MusicUnCompletedActivity", "DEVICE_CONNECT_STATUS_OTHER");
                                return;
                            }
                        }
                        LogUtil.h("MusicUnCompletedActivity", "This device does not have the correspond capability.");
                    }
                } catch (BadParcelableException e) {
                    LogUtil.b("MusicUnCompletedActivity", "BadParcelableException:", ExceptionUtils.d(e));
                } catch (ClassCastException e2) {
                    LogUtil.b("MusicUnCompletedActivity", "ClassCastException:", ExceptionUtils.d(e2));
                }
            }
        }
    };

    private void b() {
        LogUtil.a("MusicUnCompletedActivity", "Enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this, this.c, intentFilter, LocalBroadcast.c, null);
    }

    private void e() {
        try {
            unregisterReceiver(this.c);
        } catch (IllegalArgumentException e) {
            LogUtil.a("MusicUnCompletedActivity", e.getMessage());
        }
    }

    class c extends BaseAdapter {
        private List<MusicSong> c;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        c(List<MusicSong> list) {
            this.c = new ArrayList(16);
            if (list != null) {
                this.c = list;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.c.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (i >= 0 && i < this.c.size()) {
                return this.c.get(i);
            }
            return this.c.get(0);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar = null;
            if (view == null) {
                b bVar2 = new b();
                View inflate = LayoutInflater.from(MusicUnCompletedActivity.this.getApplicationContext()).inflate(R.layout.un_completed_item, (ViewGroup) null);
                bVar2.c = (HealthTextView) inflate.findViewById(R.id.music_song_name_tv);
                bVar2.e = (HealthTextView) inflate.findViewById(R.id.music_song_singer_tv);
                bVar2.d = (ImageView) inflate.findViewById(R.id.under_line);
                inflate.setTag(bVar2);
                bVar = bVar2;
                view = inflate;
            } else if (view.getTag() instanceof b) {
                bVar = (b) view.getTag();
            }
            if (bVar != null && i >= 0 && i < this.c.size()) {
                bVar.c.setText(this.c.get(i).getSongName());
                bVar.e.setText(this.c.get(i).getSongSingerName());
                if (this.c.size() - 1 == i) {
                    bVar.d.setVisibility(8);
                } else if (CommonUtil.ar()) {
                    bVar.d.setVisibility(8);
                } else {
                    bVar.d.setVisibility(0);
                }
            }
            return view;
        }

        class b {
            HealthTextView c;
            ImageView d;
            HealthTextView e;

            b() {
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_music_un_completed_layout);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.j = intent.getParcelableArrayListExtra("un_finished_song_list");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("MusicUnCompletedActivity", "get list error:", e.getMessage());
            }
            this.f9148a = intent.getIntExtra("current_bluetooth_status", 3);
        }
        b();
        c();
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e();
    }

    private void c() {
        this.d = (HealthButton) findViewById(R.id.continue_add_uncompletedsong_btn);
        if (!d()) {
            this.d.setVisibility(8);
        }
        this.e = (HealthButton) findViewById(R.id.giveup_add_uncompletedsongs_btn);
        this.g = (ListView) findViewById(R.id.listview);
        this.i = (LinearLayout) findViewById(R.id.btn_horizontal_ll);
        this.f = (LinearLayout) findViewById(R.id.btn_vertical_ll);
        if (LanguageUtil.bt(this) || LanguageUtil.a(this) || LanguageUtil.ar(this)) {
            this.i.setVisibility(8);
            this.f.setVisibility(0);
        } else {
            this.i.setVisibility(0);
            this.f.setVisibility(8);
        }
    }

    private void a() {
        c cVar = new c(this.j);
        this.b = cVar;
        this.g.setAdapter((ListAdapter) cVar);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicUnCompletedActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicUnCompletedActivity.this.setResult(1, new Intent());
                MusicUnCompletedActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicUnCompletedActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicUnCompletedActivity.this.f9148a == 2) {
                    MusicUnCompletedActivity.this.setResult(2, new Intent());
                    MusicUnCompletedActivity.this.finish();
                } else {
                    Toast.makeText(MusicUnCompletedActivity.this.getBaseContext(), MusicUnCompletedActivity.this.getResources().getString(R.string._2130843214_res_0x7f02164e), 0).show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean d() {
        ArrayList<MusicSong> arrayList = this.j;
        boolean z = false;
        if (arrayList != null && arrayList.size() >= 1) {
            Iterator<MusicSong> it = this.j.iterator();
            while (it.hasNext()) {
                MusicSong next = it.next();
                if (next.getSyncFailedErrorCode() != 2 && next.getSyncFailedErrorCode() != 1) {
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
