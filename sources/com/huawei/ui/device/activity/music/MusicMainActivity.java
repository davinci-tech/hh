package com.huawei.ui.device.activity.music;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicControllerCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct;
import com.huawei.hwdevice.mainprocess.service.SyncMusicService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.music.MusicMainActivity;
import com.huawei.ui.device.views.music.LocalMusicSearchThreadManager;
import com.huawei.ui.device.views.music.MenuListAdapter;
import com.huawei.ui.device.views.music.MusicMenuViewDialog;
import com.huawei.ui.device.views.music.MusicNestedScrollLayout;
import com.huawei.ui.device.views.music.MusicSongsListAdapter;
import defpackage.gge;
import defpackage.jfq;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jjm;
import defpackage.jkb;
import defpackage.jkg;
import defpackage.jpt;
import defpackage.jpy;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class MusicMainActivity extends BaseActivity implements View.OnClickListener, LocalMusicSearchThreadManager.SearchCallback {
    private static ArrayList<MusicSong> c = new ArrayList<>(16);
    private static boolean d = false;
    private ImageView ab;
    private String ac;
    private HealthSearchView ad;
    private jfq ae;
    private jjd af;
    private CustomProgressDialog.Builder aj;
    private RelativeLayout an;
    private CustomProgressDialog ao;
    private HealthProgressBar aq;
    private String as;
    private b at;
    private HealthToolBar au;
    private HealthSubHeader aw;
    private HealthTextView ay;
    private MusicNestedScrollLayout az;
    private LinearLayout ba;
    private RelativeLayout bb;
    private TextView bc;
    private String be;
    private LocalMusicSearchThreadManager bf;
    private HealthTextView bg;
    private LinearLayout bh;
    private SyncMusicBinder bl;
    private LinearLayout bm;
    private TextView bn;
    private HealthProgressBar bo;
    private LinearLayout bp;
    private HealthTextView bq;
    private HealthToolBar br;
    private HealthTextView bv;
    private HealthButton e;
    private MusicSongsListAdapter f;
    private LinearLayout g;
    private RecyclerView h;
    private LinearLayout i;
    private RecyclerView j;
    private MusicMainActivity k;
    private HealthSubHeader l;
    private CustomTitleBar n;
    private TextView o;
    private LinearLayout r;
    private HealthTextView u;
    private LinearLayout v;
    private HealthTextView w;
    private View y;
    private HealthTextView z;
    private int bd = 0;
    private int ar = 0;
    private int ap = 0;
    private int av = 0;
    private List<String> bi = new ArrayList(16);
    private ArrayList<MusicMenu> q = new ArrayList<>(16);
    private MusicMenu t = new MusicMenu();
    private f aa = new f(this);
    private ArrayList<MusicSong> bw = new ArrayList<>(16);
    private ArrayList<MusicSong> x = new ArrayList<>(16);
    private boolean ag = false;
    private ArrayList<MusicSong> am = new ArrayList<>(16);
    private SparseIntArray ai = new SparseIntArray(16);
    private boolean ah = false;
    private String p = "";
    private int s = 2;
    private d bj = new d(this);
    private Map<String, Object> b = new HashMap(16);
    private ServiceConnection bk = new ServiceConnection() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof SyncMusicBinder) {
                MusicMainActivity.this.bl = (SyncMusicBinder) iBinder;
            }
            if (MusicMainActivity.this.bl != null) {
                MusicMainActivity.this.bl.setSyncMusicListener(MusicMainActivity.this.ah());
                MusicMainActivity.this.bl.notifyUiIsSyncMusicTaskRunning();
                MusicMainActivity.this.u();
                return;
            }
            LogUtil.a("MusicMainActivity", "onServiceConnected binder is null!");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.a("MusicMainActivity", "onServiceDisconnected");
        }
    };
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("MusicMainActivity", "onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("MusicMainActivity", "onReceive intent : ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("MusicMainActivity", "onReceive deviceInfo is null.");
                } else {
                    if (!jjj.e(deviceInfo)) {
                        LogUtil.h("MusicMainActivity", "This device does not have the correspond capability.");
                        return;
                    }
                    MusicMainActivity.this.s = deviceInfo.getDeviceConnectState();
                    LogUtil.a("MusicMainActivity", "mConnectStateChangedReceiver, mDeviceConnectState:", Integer.valueOf(MusicMainActivity.this.s));
                }
            }
        }
    };
    private HealthToolBar.OnSingleTapListener bu = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.21
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (!nsn.o()) {
                if (MusicMainActivity.this.s != 2) {
                    MusicMainActivity.this.l();
                    return;
                }
                if (i == 1) {
                    LogUtil.a("MusicMainActivity", "click addSong");
                    MusicMainActivity.this.t();
                    gge.e(AnalyticsValue.HEALTH_MUSIC_ADD_MUSIC_1090081.value(), MusicMainActivity.this.b);
                    return;
                } else {
                    if (i == 2) {
                        ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                        if (MusicMainActivity.this.q.size() >= MusicMainActivity.this.ap) {
                            nrh.c(MusicMainActivity.this.k, MusicMainActivity.this.getResources().getString(R.string._2130843243_res_0x7f02166b));
                            return;
                        } else {
                            MusicMainActivity.this.c(false, arrayList, 1);
                            gge.e(AnalyticsValue.HEALTH_MUSIC_CREATE_NEW_SONG_MENU_1090082.value(), MusicMainActivity.this.b);
                            return;
                        }
                    }
                    LogUtil.h("MusicMainActivity", "onSingleTapListener default.");
                    return;
                }
            }
            LogUtil.h("MusicMainActivity", "click to fast!");
        }
    };
    private HealthToolBar.OnSingleTapListener bs = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.27
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (i == 1) {
                MusicMainActivity.this.k();
                return;
            }
            if (i == 2) {
                MusicMainActivity.this.s();
                gge.e(AnalyticsValue.HEALTH_MUSIC_HOME_ALL_SELECT_1090086.value(), MusicMainActivity.this.b);
            } else {
                if (i == 3) {
                    if (MusicMainActivity.this.s == 2) {
                        MusicMainActivity.this.q();
                        return;
                    } else {
                        MusicMainActivity.this.l();
                        return;
                    }
                }
                LogUtil.h("MusicMainActivity", "onMultiSelectSingleTapListener default.");
            }
        }
    };
    private boolean al = false;
    private int ax = 0;
    private boolean ak = false;

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f9119a = new AnonymousClass5();

    public static /* synthetic */ boolean cQN_(View view, MotionEvent motionEvent) {
        return true;
    }

    static /* synthetic */ int u(MusicMainActivity musicMainActivity) {
        int i = musicMainActivity.av;
        musicMainActivity.av = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        int i = AnonymousClass23.e[PermissionUtil.e(this.k, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE).ordinal()];
        if (i == 1) {
            f();
            LogUtil.a("MusicMainActivity", "GRANTED");
        } else if (i == 2) {
            LogUtil.a("MusicMainActivity", "DENIED");
            PermissionUtil.b(this.k, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new CustomPermissionAction(this.k) { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.29
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    MusicMainActivity.this.f();
                }
            });
        } else if (i == 3) {
            LogUtil.a("MusicMainActivity", "FOREVER_DENIED");
            PermissionUtil.b(this.k, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new CustomPermissionAction(this.k) { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.26
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    MusicMainActivity.this.f();
                }
            });
        } else {
            LogUtil.a("MusicMainActivity", "default");
        }
    }

    /* renamed from: com.huawei.ui.device.activity.music.MusicMainActivity$23, reason: invalid class name */
    static /* synthetic */ class AnonymousClass23 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[PermissionUtil.PermissionResult.values().length];
            e = iArr;
            try {
                iArr[PermissionUtil.PermissionResult.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[PermissionUtil.PermissionResult.DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[PermissionUtil.PermissionResult.FOREVER_DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public int a() {
        return this.s;
    }

    public f i() {
        return this.aa;
    }

    public boolean g() {
        return this.ag;
    }

    public SparseIntArray cQQ_() {
        return this.ai;
    }

    public List<MusicSong> e() {
        return this.am;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int w() {
        return this.bd;
    }

    public int d() {
        return this.ap;
    }

    public static class f extends BaseHandler<MusicMainActivity> {
        f(MusicMainActivity musicMainActivity) {
            super(musicMainActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cQX_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MusicMainActivity musicMainActivity, Message message) {
            switch (message.what) {
                case 999:
                case 1001:
                    e(musicMainActivity);
                    break;
                case 1000:
                    cQV_(musicMainActivity, message);
                    break;
                case 1002:
                case 1003:
                default:
                    cQS_(message, musicMainActivity);
                    break;
                case 1004:
                case 1008:
                case 1009:
                    musicMainActivity.g.setVisibility(8);
                    musicMainActivity.bb();
                    break;
                case 1005:
                    cQW_(musicMainActivity, message);
                    break;
                case 1006:
                    musicMainActivity.b(false);
                    break;
                case 1007:
                    MusicMainActivity.cQO_(musicMainActivity, message);
                    break;
            }
        }

        private void cQS_(Message message, MusicMainActivity musicMainActivity) {
            int i = message.what;
            if (i == 1002) {
                c(musicMainActivity);
            }
            if (i == 1003) {
                cQU_(musicMainActivity, message);
                return;
            }
            switch (i) {
                case 1010:
                    cQT_(musicMainActivity, message);
                    break;
                case 1011:
                    a(musicMainActivity);
                    break;
                case 1012:
                    d(musicMainActivity);
                    break;
                case 1013:
                    musicMainActivity.g.setVisibility(8);
                    break;
                case 1014:
                    musicMainActivity.au();
                    break;
                case 1015:
                    musicMainActivity.am();
                    break;
                default:
                    LogUtil.h("MusicMainActivity", "handleMessageWhenReferenceNotNull unknown msg");
                    break;
            }
        }

        private void a(MusicMainActivity musicMainActivity) {
            musicMainActivity.f.e(new ArrayList(16));
            musicMainActivity.f.notifyDataSetChanged();
        }

        private void cQT_(MusicMainActivity musicMainActivity, Message message) {
            if (koq.e(message.obj, MusicSong.class)) {
                d(musicMainActivity, (List) message.obj);
            }
        }

        private void d(MusicMainActivity musicMainActivity) {
            if (musicMainActivity.ak) {
                musicMainActivity.g.setVisibility(0);
                musicMainActivity.v.setVisibility(8);
            } else {
                musicMainActivity.g.setVisibility(0);
                musicMainActivity.bb();
            }
        }

        private void d(MusicMainActivity musicMainActivity, List<MusicSong> list) {
            if (list.size() == musicMainActivity.t.getMusicSongsList().size()) {
                musicMainActivity.au.setIconVisible(2, 0);
            } else {
                musicMainActivity.au.setIconVisible(2, 8);
            }
            musicMainActivity.f.e(list);
            musicMainActivity.f.notifyDataSetChanged();
        }

        private void c(MusicMainActivity musicMainActivity) {
            LogUtil.a("MusicMainActivity", "storage memory size is ", Integer.valueOf(musicMainActivity.w()));
            if (musicMainActivity.w() >= 500) {
                musicMainActivity.w.setVisibility(8);
                musicMainActivity.bv.setVisibility(8);
            } else {
                String b = nsf.b(R.string._2130847433_res_0x7f0226c9, Formatter.formatFileSize(musicMainActivity.getApplicationContext(), 500000000L));
                musicMainActivity.w.setText(b);
                musicMainActivity.bv.setText(b);
            }
            if (jjm.e().i()) {
                return;
            }
            LogUtil.a("MusicMainActivity", "initShowAllTipsView is not support");
            jjm.e().d(false);
        }

        private void e(MusicMainActivity musicMainActivity) {
            musicMainActivity.at.notifyDataSetChanged();
            musicMainActivity.b(false);
            musicMainActivity.bb();
            musicMainActivity.aj();
        }

        private void cQW_(MusicMainActivity musicMainActivity, Message message) {
            if (message == null) {
                LogUtil.h("MusicMainActivity", "updateUiWhenOneSongIsBeingTransfer, message is null.");
                return;
            }
            try {
                Bundle data = message.getData();
                if (data != null) {
                    musicMainActivity.e(data.getString("SONGNAME"), data.getString("PROGRESSTEXT"));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("MusicMainActivity", "getData error:", ExceptionUtils.d(e));
            }
        }

        private void cQU_(MusicMainActivity musicMainActivity, Message message) {
            musicMainActivity.q.clear();
            if (koq.e(message.obj, MusicMenu.class)) {
                Iterator it = ((ArrayList) message.obj).iterator();
                while (it.hasNext()) {
                    MusicMenu musicMenu = (MusicMenu) it.next();
                    if ("0".equals(musicMenu.getMenuIndex())) {
                        musicMainActivity.t = musicMenu;
                    } else {
                        musicMainActivity.q.add(musicMenu);
                    }
                }
                musicMainActivity.f = new MusicSongsListAdapter(musicMainActivity, musicMainActivity.t.getMusicSongsList(), musicMainActivity.q);
                musicMainActivity.h.setAdapter(musicMainActivity.f);
                musicMainActivity.f.notifyDataSetChanged();
                musicMainActivity.at.notifyDataSetChanged();
                musicMainActivity.bb();
                musicMainActivity.aj();
            }
        }

        private void cQV_(MusicMainActivity musicMainActivity, Message message) {
            if (message.obj instanceof MusicSong) {
                MusicSong musicSong = (MusicSong) message.obj;
                musicMainActivity.t.getMusicSongsList().remove(musicSong);
                MusicMainActivity.u(musicMainActivity);
                musicMainActivity.aj();
                if (musicMainActivity.f != null) {
                    musicMainActivity.f.c(musicSong);
                    musicMainActivity.at.notifyDataSetChanged();
                    musicMainActivity.f.notifyDataSetChanged();
                }
                musicMainActivity.bb();
                musicMainActivity.aq();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cQO_(MusicMainActivity musicMainActivity, Message message) {
        musicMainActivity.bo.setProgress(message.arg1);
        musicMainActivity.bc.setText(String.format(Locale.ENGLISH, "%d%%", Integer.valueOf(message.arg1)));
    }

    class b extends RecyclerView.Adapter<C0252b> {
        C0252b b;
        View d;

        private b() {
        }

        /* renamed from: com.huawei.ui.device.activity.music.MusicMainActivity$b$b, reason: collision with other inner class name */
        class C0252b extends RecyclerView.ViewHolder {
            private LinearLayout b;
            private HealthTextView c;
            private HealthTextView d;
            private ImageView e;

            C0252b(View view) {
                super(view);
                this.c = (HealthTextView) b.this.d.findViewById(R.id.music_menu_name_tv);
                this.d = (HealthTextView) b.this.d.findViewById(R.id.music_song_sum_tv);
                this.b = (LinearLayout) b.this.d.findViewById(R.id.under_line_layout);
                this.e = (ImageView) b.this.d.findViewById(R.id.music_song_sum_arrow_iv);
            }

            public void a(final MusicMenu musicMenu, int i) {
                if (musicMenu == null) {
                    return;
                }
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    this.e.setImageResource(R.mipmap._2131820654_res_0x7f11006e);
                } else {
                    this.e.setImageResource(R.drawable._2131430054_res_0x7f0b0aa6);
                }
                this.c.setText(musicMenu.getMenuName());
                this.d.setText(MusicMainActivity.this.getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, musicMenu.getMenuMusicCount(), Integer.valueOf(musicMenu.getMenuMusicCount())));
                b.this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.b.b.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("MusicMainActivity", "Click item, Entered MusicSongsActivity, the menu name=", musicMenu.getMenuName());
                        Intent intent = new Intent();
                        intent.setClass(MusicMainActivity.this, MusicSongsActivity.class);
                        jjm.e().e(musicMenu);
                        jjm.e().d(MusicMainActivity.this.t);
                        intent.putStringArrayListExtra("menu_list", MusicMainActivity.this.v());
                        intent.putExtra("current_bluetooth_status", MusicMainActivity.this.s);
                        MusicMainActivity.this.startActivityForResult(intent, 1);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                if (MusicMainActivity.this.v().size() - 1 == i) {
                    this.b.setVisibility(8);
                } else {
                    this.b.setVisibility(0);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (MusicMainActivity.this.q == null) {
                return 0;
            }
            return MusicMainActivity.this.q.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: cQR_, reason: merged with bridge method [inline-methods] */
        public C0252b onCreateViewHolder(ViewGroup viewGroup, int i) {
            this.d = LayoutInflater.from(MusicMainActivity.this.k).inflate(R.layout.activity_music_menu_list_item, viewGroup, false);
            C0252b c0252b = new C0252b(this.d);
            this.b = c0252b;
            c0252b.setIsRecyclable(false);
            return this.b;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(C0252b c0252b, int i) {
            if (MusicMainActivity.this.q.isEmpty() || i < 0 || i >= MusicMainActivity.this.q.size()) {
                return;
            }
            c0252b.a((MusicMenu) MusicMainActivity.this.q.get(i), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> v() {
        ArrayList<String> arrayList = new ArrayList<>(16);
        ArrayList<MusicMenu> arrayList2 = this.q;
        if (arrayList2 == null) {
            return arrayList;
        }
        Iterator<MusicMenu> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getMenuName());
        }
        return arrayList;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.click_to_watch_tv) {
            Intent intent = new Intent(this.k, (Class<?>) MusicUnCompletedActivity.class);
            intent.putExtra("un_finished_song_list", this.bw);
            intent.putExtra("current_bluetooth_status", this.s);
            startActivityForResult(intent, 3);
        } else if (id == R.id.add_btn) {
            LogUtil.a("MusicMainActivity", "click addSong");
            if (this.s == 2) {
                if (getString(R.string._2130841394_res_0x7f020f32).equals(this.e.getText().toString())) {
                    f();
                } else {
                    at();
                    aa();
                }
            } else {
                l();
            }
        } else if (id == R.id.cancel_tv) {
            r();
        } else if (id == R.id.give_up_tv) {
            m();
        } else {
            LogUtil.h("MusicMainActivity", "onClick default.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_music_main);
        this.k = this;
        this.ak = true;
        this.b.put("click", "1");
        this.af = jjd.b(this.k);
        this.ae = jfq.c();
        bc();
        this.ac = getString(R.string._2130843273_res_0x7f021689);
        this.as = getString(R.string._2130841415_res_0x7f020f47);
        af();
        ab();
        u();
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.s == 2) {
            if (this.q.size() <= 0) {
                c(true, this.am, this.q.size());
                return;
            } else {
                c(this.am);
                return;
            }
        }
        l();
    }

    private void n() {
        Intent intent = new Intent(this, (Class<?>) SyncMusicService.class);
        intent.putExtra("device_id", this.p);
        bindService(intent, this.bk, 1);
    }

    private void bc() {
        LogUtil.a("MusicMainActivity", "Enter registerConnectStateBroadcast.");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.k, this.m, intentFilter, LocalBroadcast.c, null);
    }

    private void az() {
        try {
            unregisterReceiver(this.m);
        } catch (IllegalArgumentException unused) {
            LogUtil.a("MusicMainActivity", "unregisterConnectStateChangedBroadcast IllegalArgumentException.");
        }
    }

    private void at() {
        LogUtil.a("MusicMainActivity", "refreshUiWhenLoadingData enter");
        this.az.setVisibility(8);
        this.br.setVisibility(8);
        this.bb.setVisibility(0);
        this.bp.setVisibility(8);
        this.ab.setVisibility(8);
        this.aq.setVisibility(0);
        this.z.setText(this.as);
        this.i.setVisibility(8);
    }

    private void ap() {
        LogUtil.a("MusicMainActivity", "refreshUiIfHasNoData enter");
        this.az.setVisibility(8);
        this.br.setVisibility(8);
        this.bb.setVisibility(0);
        this.bp.setVisibility(0);
        this.ab.setVisibility(0);
        this.aq.setVisibility(8);
        this.z.setText(this.ac);
        this.i.setVisibility(0);
        this.l.setHeadTitleText(getString(R.string._2130843232_res_0x7f021660));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        LogUtil.a("MusicMainActivity", "refreshUiTimeoutFromDevice enter");
        this.az.setVisibility(8);
        this.br.setVisibility(8);
        this.bb.setVisibility(0);
        this.bp.setVisibility(8);
        this.ab.setVisibility(0);
        if (LanguageUtil.bc(this.k)) {
            this.ab.setImageDrawable(getResources().getDrawable(R.drawable._2131430317_res_0x7f0b0bad));
        } else {
            this.ab.setImageDrawable(getResources().getDrawable(R.drawable._2131430316_res_0x7f0b0bac));
        }
        this.aq.setVisibility(8);
        this.z.setText(getResources().getString(R.string._2130843631_res_0x7f0217ef));
        this.i.setVisibility(0);
        this.e.setText(getString(R.string._2130837642_res_0x7f02008a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bb() {
        if (this.q.size() == 0 && this.t.getMusicSongsList().size() == 0 && this.av <= 0) {
            ap();
        } else if (this.q.size() == 0 && this.t.getMusicSongsList().size() != 0) {
            ax();
        } else if (this.q.size() != 0 && this.t.getMusicSongsList().size() == 0) {
            av();
        } else {
            aw();
        }
        if (this.al) {
            this.v.setVisibility(8);
            aw();
            z();
            return;
        }
        ArrayList<MusicSong> arrayList = this.bw;
        if (arrayList != null && arrayList.size() > 0) {
            this.v.setVisibility(0);
            aw();
            z();
            return;
        }
        this.v.setVisibility(8);
    }

    private void z() {
        if (this.q.size() == 0) {
            this.y.setVisibility(8);
            this.aw.setVisibility(8);
        }
        if (this.t.getMusicSongsList().size() == 0) {
            this.l.setVisibility(8);
            this.y.setVisibility(8);
        }
    }

    private void aw() {
        LogUtil.a("MusicMainActivity", "refreshUiIfHasSongsAndMenus enter");
        this.az.setVisibility(0);
        this.y.setVisibility(0);
        this.bh.setVisibility(0);
        this.aw.setVisibility(0);
        this.l.setVisibility(0);
        this.br.setVisibility(0);
        this.bb.setVisibility(8);
        this.i.setVisibility(8);
        this.l.setHeadTitleText(jjm.e().b(this.t.getMusicSongsList().size(), this.av));
    }

    private void ax() {
        LogUtil.a("MusicMainActivity", "refreshUiIfOnlyHasSongs enter");
        this.az.setVisibility(0);
        this.y.setVisibility(8);
        this.bh.setVisibility(0);
        this.br.setVisibility(0);
        this.bb.setVisibility(8);
        this.i.setVisibility(8);
        this.l.setVisibility(0);
        this.aw.setVisibility(8);
        this.l.setHeadTitleText(jjm.e().b(this.t.getMusicSongsList().size(), this.av));
    }

    private void av() {
        LogUtil.a("MusicMainActivity", "refreshUiIfOnlyHasMenus enter");
        this.az.setVisibility(0);
        this.y.setVisibility(8);
        this.aw.setVisibility(0);
        this.bh.setVisibility(8);
        this.br.setVisibility(0);
        this.bb.setVisibility(8);
        this.i.setVisibility(8);
        this.l.setHeadTitleText(getString(R.string._2130843232_res_0x7f021660));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SyncMusicBinder.SyncMusicStatusListener ah() {
        return new SyncMusicBinder.SyncMusicStatusListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.28
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onGetSyncTaskRunningStatus(boolean z, int i) {
                LogUtil.a("MusicMainActivity", "onGetSyncTaskRunningStatus:", Boolean.valueOf(z), "musicAppType:", Integer.valueOf(i));
                MusicMainActivity.this.e(z, i);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onGetSyncMusicProgress(int i) {
                MusicMainActivity.this.a(i);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onGetSyncMusicNameAndTotalProgress(String str, String str2) {
                MusicMainActivity.this.c(str, str2);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onSyncOneMusicFinished(MusicSong musicSong) {
                MusicMainActivity.this.b(musicSong);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onSyncAllMusicsFinished(List<MusicSong> list, List<MusicSong> list2) {
                MusicMainActivity.this.b(list, list2);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder.SyncMusicStatusListener
            public void onBlueToothDisconnect(List<MusicSong> list, List<MusicSong> list2) {
                MusicMainActivity.this.e(list, list2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final List<MusicSong> list, final List<MusicSong> list2) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.30
            @Override // java.lang.Runnable
            public void run() {
                MusicMainActivity.this.x.clear();
                MusicMainActivity.this.x.addAll(list);
                if (!list2.isEmpty()) {
                    MusicMainActivity musicMainActivity = MusicMainActivity.this;
                    nrh.d(musicMainActivity, musicMainActivity.getResources().getQuantityString(R.plurals._2130903325_res_0x7f03011d, list2.size(), Integer.valueOf(list2.size())));
                }
                MusicMainActivity.this.bq.setVisibility(0);
                MusicMainActivity.this.bq.setText(MusicMainActivity.this.getResources().getString(R.string._2130843218_res_0x7f021652));
                MusicMainActivity.this.ao();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<MusicSong> list, final List<MusicSong> list2) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.32
            @Override // java.lang.Runnable
            public void run() {
                if (!MusicMainActivity.this.al) {
                    MusicMainActivity.this.x.clear();
                }
                MusicMainActivity.this.x.addAll(list);
                if (!list2.isEmpty()) {
                    MusicMainActivity musicMainActivity = MusicMainActivity.this;
                    nrh.d(musicMainActivity, musicMainActivity.getResources().getQuantityString(R.plurals._2130903325_res_0x7f03011d, list2.size(), Integer.valueOf(list2.size())));
                }
                MusicMainActivity.this.c((List<MusicSong>) list);
                MusicMainActivity.this.ao();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MusicSong musicSong) {
        if (musicSong == null || TextUtils.isEmpty(musicSong.getSongName())) {
            LogUtil.a("MusicMainActivity", "onSyncOneMusicFinished song is null!");
            return;
        }
        LogUtil.a("MusicMainActivity", "onSyncOneMusicFinished");
        this.t.getMusicSongsList().add(0, musicSong);
        this.av++;
        aj();
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 1006;
        this.aa.sendMessage(obtainMessage);
        aq();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<MusicSong> list) {
        LogUtil.a("MusicMainActivity", "mFailedTransferedMusicList size:", Integer.valueOf(this.x.size()));
        this.bq.setVisibility(8);
        this.u.setVisibility(8);
        this.ay.setVisibility(8);
        boolean z = true;
        for (MusicSong musicSong : new ArrayList(list)) {
            if (musicSong.getSyncFailedErrorCode() == 140007) {
                this.bq.setVisibility(0);
                this.bq.setText(getResources().getString(R.string._2130843272_res_0x7f021688));
                z = false;
            }
            if (musicSong.getSyncFailedErrorCode() == 2) {
                LogUtil.a("MusicMainActivity", "Format Failed Song Name :", musicSong.getFileName());
                this.u.setVisibility(0);
                this.u.setText(getResources().getString(R.string._2130843399_res_0x7f021707));
                z = false;
            }
            if (musicSong.getSyncFailedErrorCode() == 1) {
                LogUtil.a("MusicMainActivity", "Sample Rate Failed Song Name :", musicSong.getFileName());
                this.ay.setVisibility(0);
                this.ay.setText(getResources().getString(R.string._2130845270_res_0x7f021e56));
                z = false;
            }
        }
        if (z) {
            LogUtil.a("MusicMainActivity", "DefaultTransferError");
            this.bq.setVisibility(0);
            this.bq.setText(getResources().getString(R.string._2130843219_res_0x7f021653));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aq() {
        jjd.b(this.k).b(new NegotiationData(), new c(this.k));
    }

    private void af() {
        ai();
        this.r.setOnTouchListener(new View.OnTouchListener() { // from class: nwo
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MusicMainActivity.cQN_(view, motionEvent);
            }
        });
        ae();
        at();
        ad();
    }

    private void ad() {
        this.n.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicMainActivity.this.ag) {
                    MusicMainActivity.this.b(false);
                } else {
                    MusicMainActivity.this.finish();
                }
                MusicMainActivity.this.aj();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void ae() {
        this.br.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.br.setOnSingleTapListener(this.bu);
        this.br.setIcon(1, R.drawable._2131429693_res_0x7f0b093d);
        this.br.setIconTitle(1, getResources().getString(R.string._2130843211_res_0x7f02164b));
        if (!jjj.b()) {
            this.br.setIcon(2, R.drawable._2131430455_res_0x7f0b0c37);
            this.br.setIconTitle(2, getResources().getString(R.string._2130843226_res_0x7f02165a));
        }
        this.br.setIconVisible(3, 8);
        this.au.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.au.setOnSingleTapListener(this.bs);
        if (!jjj.b()) {
            this.au.setIcon(1, R.drawable._2131430455_res_0x7f0b0c37);
            this.au.setIconTitle(1, getResources().getString(R.string._2130843212_res_0x7f02164c));
        }
        this.au.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        a(this.t.getMusicSongsList());
        this.au.setIcon(3, R.drawable._2131429906_res_0x7f0b0a12);
        this.au.setIconTitle(3, getResources().getString(R.string._2130841438_res_0x7f020f5e));
    }

    private void ai() {
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.load_progressbar);
        this.aq = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.r = (LinearLayout) findViewById(R.id.delete_load_ll);
        ((HealthProgressBar) findViewById(R.id.delete_load_progressbar)).setLayerType(1, null);
        this.bv = (HealthTextView) findViewById(R.id.watch_savings_remaining_tv);
        this.w = (HealthTextView) findViewById(R.id.empty_watch_savings_remaining_tv);
        this.n = (CustomTitleBar) findViewById(R.id.music_home_title_bar);
        this.az = (MusicNestedScrollLayout) findViewById(R.id.my_musics_scrollview);
        this.bm = (LinearLayout) findViewById(R.id.tip_layout);
        this.ba = (LinearLayout) findViewById(R.id.hw_search_layout);
        this.ad = (HealthSearchView) findViewById(R.id.music_search_view);
        this.y = findViewById(R.id.menu_songs_divider);
        this.bh = (LinearLayout) findViewById(R.id.all_songs_list_layout);
        this.aw = (HealthSubHeader) findViewById(R.id.my_song_list_sub_header);
        this.l = (HealthSubHeader) findViewById(R.id.all_songs_sub_header);
        this.br = (HealthToolBar) findViewById(R.id.bottom_operate_toolbar);
        this.au = (HealthToolBar) findViewById(R.id.multi_select_toolbar);
        this.bb = (RelativeLayout) findViewById(R.id.layout_when_no_data);
        this.i = (LinearLayout) findViewById(R.id.add_layout_when_no_data);
        this.bp = (LinearLayout) findViewById(R.id.linear_two_text_tips);
        this.ab = (ImageView) findViewById(R.id.has_no_music_image);
        this.z = (HealthTextView) findViewById(R.id.has_no_music_textview);
        HealthButton healthButton = (HealthButton) findViewById(R.id.add_btn);
        this.e = healthButton;
        healthButton.setOnClickListener(this);
        this.g = (LinearLayout) findViewById(R.id.add_music_to_watch_layout);
        this.v = (LinearLayout) findViewById(R.id.disconnect_device_layout);
        this.bq = (HealthTextView) findViewById(R.id.transfer_failed_tips_tv);
        this.u = (HealthTextView) findViewById(R.id.format_failed_tips_tv);
        this.ay = (HealthTextView) findViewById(R.id.sampleRate_failed_tips_tv);
        ((TextView) findViewById(R.id.cancel_tv)).setOnClickListener(this);
        ((HealthTextView) findViewById(R.id.give_up_tv)).setOnClickListener(this);
        this.j = (RecyclerView) findViewById(R.id.all_musics_menu_list);
        ag();
        this.o = (TextView) findViewById(R.id.adding_music_name_text_view);
        this.bc = (TextView) findViewById(R.id.transfer_music_progress_text);
        this.bo = (HealthProgressBar) findViewById(R.id.transfer_music_progress_bar);
        this.bn = (TextView) findViewById(R.id.music_description_boot_title_one);
        ((HealthTextView) findViewById(R.id.click_to_watch_tv)).setOnClickListener(this);
        this.an = (RelativeLayout) findViewById(R.id.layout_music_show_all_tips);
        this.bg = (HealthTextView) findViewById(R.id.show_all_music_title_tv);
        findViewById(R.id.show_all_music_tv).setOnClickListener(this.f9119a);
    }

    private void ag() {
        this.h = (RecyclerView) findViewById(R.id.all_music_songs_list);
        this.h.setLayoutManager(new LinearLayoutManager(this.k));
        this.h.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.3

            /* renamed from: a, reason: collision with root package name */
            private boolean f9126a = false;

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 || !this.f9126a) {
                    LogUtil.h("MusicMainActivity", "onScrollStateChanged newState is not idle; mIsToLast : ", Boolean.valueOf(this.f9126a));
                    return;
                }
                if (!jjm.e().a()) {
                    LogUtil.h("MusicMainActivity", "onScrollStateChanged is not load more");
                    return;
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!(layoutManager instanceof LinearLayoutManager)) {
                    LogUtil.h("MusicMainActivity", "onScrollStateChanged is not instance of LinearLayoutManager");
                    return;
                }
                int findLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                List<MusicSong> a2 = MusicMainActivity.this.f.a();
                if (a2 == null || a2.isEmpty()) {
                    LogUtil.h("MusicMainActivity", "onScrollStateChanged songList have not data");
                    return;
                }
                LogUtil.a("MusicMainActivity", "onScrollStateChanged, lastVisiblePosition :", Integer.valueOf(findLastVisibleItemPosition), ", isLoadingMore :", Boolean.valueOf(jjm.e().j()));
                if (findLastVisibleItemPosition != a2.size() || jjm.e().j()) {
                    return;
                }
                MusicMainActivity.this.am();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.f9126a = i2 > 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        jjm.e().d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(final int i, final Object obj) {
                LogUtil.a("MusicMainActivity", "loadMoreMusic onResponse errorCode : ", Integer.valueOf(i));
                MusicMainActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        int i2 = i;
                        if (i2 != 1) {
                            if (i2 == 2) {
                                MusicMainActivity.this.f.b(false);
                                return;
                            } else {
                                LogUtil.h("MusicMainActivity", "loadMoreMusic onResponse default");
                                return;
                            }
                        }
                        Object obj2 = obj;
                        if (obj2 instanceof jkb) {
                            jkb jkbVar = (jkb) obj2;
                            MusicMainActivity.this.f.b(true);
                            List<MusicSong> e2 = jkbVar.e();
                            if (e2 != null) {
                                MusicMainActivity.this.c(e2, MusicMainActivity.this.t.getMusicSongsList());
                                MusicMainActivity.this.f.a(e2);
                                MusicMainActivity.this.f.notifyDataSetChanged();
                                MusicMainActivity.this.aj();
                            }
                            MusicMainActivity.this.d(jkbVar.c());
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<MusicSong> list, List<MusicSong> list2) {
        if (list == null || list2 == null) {
            LogUtil.b("MusicMainActivity", "musicSongListAddAll, param is null.");
            return;
        }
        for (MusicSong musicSong : list) {
            if (!list2.contains(musicSong)) {
                list2.add(musicSong);
            }
        }
    }

    /* renamed from: com.huawei.ui.device.activity.music.MusicMainActivity$5, reason: invalid class name */
    class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (nsn.o()) {
                LogUtil.h("MusicMainActivity", "clickLoadAllMusicListener, isFastClick.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (MusicMainActivity.this.a() != 2) {
                MusicMainActivity.this.l();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            MusicMainActivity.this.ac();
            String format = String.format(Locale.ROOT, MusicMainActivity.this.getResources().getString(R.string._2130842706_res_0x7f021452), 0, Integer.valueOf(MusicMainActivity.this.av - MusicMainActivity.this.t.getMusicSongsList().size()));
            MusicMainActivity.this.aj.d(0);
            MusicMainActivity.this.aj.e(format);
            if (!MusicMainActivity.this.ao.isShowing()) {
                MusicMainActivity.this.ao.show();
            }
            jjm.e().d(MusicMainActivity.this.t.getMusicSongsList().size(), MusicMainActivity.this.av, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.5.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(final int i, final Object obj) {
                    LogUtil.a("MusicMainActivity", "clickLoadAllMusicListener, loadAllMusic onResponse errorCode :", Integer.valueOf(i));
                    if (i == 1 && (obj instanceof jkb)) {
                        MusicMainActivity.this.d(((jkb) obj).c());
                    } else {
                        MusicMainActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.5.2.5
                            @Override // java.lang.Runnable
                            public void run() {
                                Object obj2 = obj;
                                MusicMainActivity.this.d(i, obj2 instanceof jkg ? (jkg) obj2 : null);
                            }
                        });
                    }
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, jkg jkgVar) {
        ac();
        if (i == 1) {
            p();
            ba();
            return;
        }
        if (i == 3) {
            p();
            jjm.e().b(false);
            return;
        }
        if (jkgVar == null) {
            LogUtil.h("MusicMainActivity", "showLoadingMusicDialog, musicLoadStruct is null.");
            return;
        }
        this.aj.d(jkgVar.a());
        this.aj.e(jkgVar.c());
        if (!this.ao.isShowing()) {
            this.ao.show();
        }
        MusicSong e2 = jkgVar.e();
        if (!this.t.getMusicSongsList().contains(e2)) {
            this.t.getMusicSongsList().add(e2);
            b(false);
        }
        this.l.setHeadTitleText(jjm.e().b(this.t.getMusicSongsList().size(), this.av));
        aj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        if (this.ao == null) {
            CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.k);
            this.aj = builder;
            builder.d(getResources().getString(R.string._2130845086_res_0x7f021d9e));
            this.aj.cyH_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    jjm.e().b(true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomProgressDialog e2 = this.aj.e();
            this.ao = e2;
            e2.setCancelable(false);
            this.ao.setCanceledOnTouchOutside(false);
        }
    }

    private void p() {
        this.ao.dismiss();
        this.aj.d(0);
        this.aj.c(0);
        this.aj.e("");
    }

    private void ba() {
        nrh.e(this.k, R.string._2130846010_res_0x7f02213a);
    }

    private void ab() {
        Intent intent = getIntent();
        if (intent != null) {
            this.p = intent.getStringExtra("device_id");
        }
        aa();
        this.at = new b();
        this.j.setLayoutManager(new LinearLayoutManager(this.k));
        this.j.setAdapter(this.at);
        MusicSongsListAdapter musicSongsListAdapter = new MusicSongsListAdapter(this, this.t.getMusicSongsList(), this.q);
        this.f = musicSongsListAdapter;
        this.h.setAdapter(musicSongsListAdapter);
        LocalMusicSearchThreadManager localMusicSearchThreadManager = new LocalMusicSearchThreadManager();
        this.bf = localMusicSearchThreadManager;
        localMusicSearchThreadManager.c();
        this.bf.e(this);
        this.ad.setOnQueryTextListener(this.bj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        int size = this.t.getMusicSongsList().size();
        if (!jjm.e().i() || size == this.av || this.ag) {
            this.an.setVisibility(8);
            return;
        }
        Resources resources = getResources();
        int i = this.av;
        String quantityString = resources.getQuantityString(R.plurals._2130903350_res_0x7f030136, i, Integer.valueOf(i));
        String quantityString2 = getResources().getQuantityString(R.plurals._2130903350_res_0x7f030136, size, Integer.valueOf(size));
        int a2 = jjm.e().a(this.av, size);
        this.bg.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130846008_res_0x7f022138), quantityString, quantityString2, getResources().getQuantityString(R.plurals._2130903351_res_0x7f030137, a2, Integer.valueOf(a2))));
        this.an.setVisibility(0);
    }

    public void c(int i) {
        this.an.setVisibility(i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.ag) {
            b(false);
        } else {
            super.onBackPressed();
        }
    }

    public void j() {
        if (this.am.size() == this.t.getMusicSongsList().size() || this.am.size() >= 500) {
            this.ah = true;
            this.au.setIconTitle(2, getResources().getString(R.string._2130841400_res_0x7f020f38));
            this.au.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
            if (!jjj.b()) {
                this.au.setIconVisible(1, 0);
            }
            this.au.setIconVisible(3, 0);
        } else if (this.am.size() == 0) {
            this.ah = false;
            a(this.t.getMusicSongsList());
            this.au.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            this.au.setIconVisible(1, 8);
            this.au.setIconVisible(3, 8);
        } else {
            this.ah = false;
            a(this.t.getMusicSongsList());
            this.au.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            if (!jjj.b()) {
                this.au.setIconVisible(1, 0);
            }
            this.au.setIconVisible(3, 0);
        }
        if (this.am.size() > 0) {
            this.n.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(this.am.size())));
        } else {
            this.n.setTitleText(getResources().getString(R.string._2130837613_res_0x7f02006d));
        }
    }

    public void b(boolean z) {
        this.ag = z;
        if (z) {
            ar();
        } else {
            as();
        }
    }

    private void as() {
        this.br.setVisibility(0);
        this.au.setVisibility(8);
        this.bm.setVisibility(0);
        this.ba.setVisibility(8);
        this.j.setVisibility(0);
        this.aw.setVisibility(0);
        if (this.al && this.ax != 2) {
            this.g.setVisibility(0);
        } else {
            ArrayList<MusicSong> arrayList = this.bw;
            if (arrayList != null && arrayList.size() > 0) {
                this.v.setVisibility(0);
            } else {
                this.v.setVisibility(8);
            }
        }
        if (this.q.size() == 0) {
            this.y.setVisibility(8);
        } else {
            this.y.setVisibility(0);
        }
        this.l.setHeadTitleText(jjm.e().b(this.t.getMusicSongsList().size(), this.av));
        this.n.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R.string._2130850617_res_0x7f023339));
        this.n.setTitleText(getResources().getString(R.string._2130842049_res_0x7f0211c1));
        this.n.setRightTextButtonVisibility(8);
        this.am.clear();
        this.ah = false;
        if (!jjj.b()) {
            this.au.setIconVisible(1, 0);
        }
        this.au.setIconVisible(2, 0);
        a(this.t.getMusicSongsList());
        this.au.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.au.setIconVisible(3, 0);
        if (this.t.getMusicSongsList().size() > 0 && this.bh.getVisibility() == 8) {
            this.bh.setVisibility(0);
        }
        MusicSongsListAdapter musicSongsListAdapter = new MusicSongsListAdapter(this.k, this.t.getMusicSongsList(), this.q);
        this.f = musicSongsListAdapter;
        this.h.setAdapter(musicSongsListAdapter);
        this.f.notifyDataSetChanged();
        aj();
    }

    private void ar() {
        this.br.setVisibility(8);
        this.au.setVisibility(0);
        this.bm.setVisibility(8);
        this.ba.setVisibility(0);
        this.j.setVisibility(8);
        this.aw.setVisibility(8);
        this.g.setVisibility(8);
        this.v.setVisibility(8);
        this.y.setVisibility(8);
        if (this.t.getMusicSongsList().size() == 1) {
            this.au.setIconTitle(2, getResources().getString(R.string._2130841400_res_0x7f020f38));
            this.au.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
            this.ah = true;
        }
        this.l.setHeadTitleText(jjm.e().d(this.t.getMusicSongsList().size(), this.av));
        this.n.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428439_res_0x7f0b0457), nsf.h(R.string._2130850611_res_0x7f023333));
        this.n.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(this.am.size())));
        this.n.setRightTextButtonVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<MusicStruct> list) {
        if (list == null || list.isEmpty() || this.q.isEmpty()) {
            LogUtil.a("MusicMainActivity", "dealOnGetPageFolderRsp, return!");
            return;
        }
        for (MusicStruct musicStruct : list) {
            Iterator<MusicMenu> it = this.q.iterator();
            while (it.hasNext()) {
                MusicMenu next = it.next();
                if (next.getMusicStructSongIndexList().contains(Integer.valueOf(musicStruct.getMusicIndex()))) {
                    MusicSong musicSong = new MusicSong();
                    musicSong.setFileName(musicStruct.getFileName());
                    musicSong.setSongName(musicStruct.getMusicName());
                    musicSong.setSongSingerName(musicStruct.getMusicSinger());
                    musicSong.setSongIndex(String.valueOf(musicStruct.getMusicIndex()));
                    if (!next.getMusicSongsList().contains(musicSong)) {
                        next.getMusicSongsList().add(musicSong);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(List<MusicFolderStruct> list, MusicMainActivity musicMainActivity) {
        if (list == null || list.size() == 0) {
            LogUtil.a("MusicMainActivity", "onGetFolders musicFolderStructure is empty!");
            return;
        }
        if (list.get(0).getMusicStructList() == null) {
            LogUtil.a("MusicMainActivity", "onGetFolders defaultmusiclist is empty!");
        }
        ArrayList arrayList = new ArrayList(16);
        for (MusicFolderStruct musicFolderStruct : list) {
            MusicMenu musicMenu = new MusicMenu();
            musicMenu.fillMusicMenuDataByBlueTooth(musicFolderStruct);
            arrayList.add(musicMenu);
        }
        Message obtainMessage = musicMainActivity.aa.obtainMessage();
        obtainMessage.what = 1003;
        obtainMessage.obj = arrayList;
        musicMainActivity.aa.sendMessage(obtainMessage);
    }

    private void aa() {
        LogUtil.a("MusicMainActivity", "initDeviceMusicLibrary enter");
        this.af.e(new a(this.k));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        jjd.b(this.k).b(new NegotiationData(), new e(this.k));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(NegotiationData negotiationData, MusicMainActivity musicMainActivity) {
        SyncMusicBinder syncMusicBinder = musicMainActivity.bl;
        if (syncMusicBinder != null && syncMusicBinder.getService() != null) {
            LogUtil.a("MusicMainActivity", "setSupportFormat");
            musicMainActivity.bl.getService().e(negotiationData.getMp3SampleRate());
            musicMainActivity.bl.getService().e(negotiationData.getAacSupportAdts());
            musicMainActivity.bl.getService().b(negotiationData.getTypeStructList());
            return;
        }
        LogUtil.h("MusicMainActivity", "Service is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<MusicSong> list) {
        LogUtil.a("MusicMainActivity", "deleteSongListFromMenuByUser enter");
        if (list == null) {
            LogUtil.h("MusicMainActivity", "deleteSongListFromMenuByUser, songList is null.");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        try {
            Iterator<MusicSong> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(Integer.parseInt(it.next().getSongIndex())));
            }
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(4);
            operationStruct.setFolderIndex(0);
            operationStruct.setMusicIndexs(arrayList);
            this.af.a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 100000) {
                        LogUtil.a("MusicMainActivity", "receive deleteSongFromMenuByUser rsp");
                        MusicMainActivity.this.e((List<MusicSong>) list);
                    } else {
                        LogUtil.a("MusicMainActivity", "deleteSongListFromMenuByUser failed, errCode:", Integer.valueOf(i));
                    }
                    MusicMainActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.7.4
                        @Override // java.lang.Runnable
                        public void run() {
                            MusicMainActivity.this.r.setVisibility(8);
                        }
                    });
                }
            });
        } catch (NumberFormatException e2) {
            LogUtil.a("MusicMainActivity", "deleteSongListFromMenuByUser failed, exception:", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<MusicSong> list) {
        for (MusicSong musicSong : list) {
            String fileName = musicSong.getFileName();
            Iterator<MusicSong> it = this.t.getMusicSongsList().iterator();
            while (it.hasNext()) {
                if (it.next().getFileName().equals(fileName)) {
                    it.remove();
                    this.av--;
                }
            }
            Iterator<MusicMenu> it2 = this.q.iterator();
            while (it2.hasNext()) {
                MusicMenu next = it2.next();
                if (next.getMusicSongsList().contains(musicSong)) {
                    next.getMusicSongsList().remove(musicSong);
                    next.setMenuMusicCount(next.getMenuMusicCount() - 1);
                }
            }
        }
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 999;
        this.aa.sendMessage(obtainMessage);
        aq();
        LogUtil.a("MusicMainActivity", "deleteSongListFromMenuByUser succeed!");
    }

    private void c(final ArrayList<MusicSong> arrayList) {
        if (arrayList != null) {
            LogUtil.a("MusicMainActivity", "addMusicToMenu enter, songList size:", Integer.valueOf(arrayList.size()));
        }
        View inflate = LayoutInflater.from(this.k).inflate(R.layout.dialog_add_to_menu, (ViewGroup) null);
        MenuListAdapter menuListAdapter = new MenuListAdapter(this.k, this.q);
        cQM_(inflate, menuListAdapter);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.k);
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czh_(inflate, 0, 0);
        builder.c(false);
        final CustomViewDialog e2 = builder.e();
        if (e2 != null) {
            e2.setCancelable(false);
            e2.show();
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.create_new_menu);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomViewDialog customViewDialog = e2;
                if (customViewDialog != null) {
                    customViewDialog.dismiss();
                }
                MusicMainActivity musicMainActivity = MusicMainActivity.this;
                musicMainActivity.c(true, arrayList, musicMainActivity.q.size());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (this.q.size() >= this.ap) {
            healthTextView.setVisibility(8);
        }
        menuListAdapter.c(new AnonymousClass10(menuListAdapter, arrayList, e2));
    }

    /* renamed from: com.huawei.ui.device.activity.music.MusicMainActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements MenuListAdapter.OnItemClickListener {
        final /* synthetic */ ArrayList b;
        final /* synthetic */ MenuListAdapter c;
        final /* synthetic */ CustomViewDialog d;

        AnonymousClass10(MenuListAdapter menuListAdapter, ArrayList arrayList, CustomViewDialog customViewDialog) {
            this.c = menuListAdapter;
            this.b = arrayList;
            this.d = customViewDialog;
        }

        @Override // com.huawei.ui.device.views.music.MenuListAdapter.OnItemClickListener
        public void onItemClick(View view, int i) {
            if (this.c.c() != null) {
                int size = this.c.c().size();
                if (i < 0 || i >= size) {
                    return;
                }
                this.c.a(i);
                MusicMainActivity.this.a(this.c.c().get(i), (ArrayList<MusicSong>) this.b);
                final CustomViewDialog customViewDialog = this.d;
                view.postDelayed(new Runnable() { // from class: nwu
                    @Override // java.lang.Runnable
                    public final void run() {
                        MusicMainActivity.AnonymousClass10.this.c(customViewDialog);
                    }
                }, 300L);
            }
        }

        public /* synthetic */ void c(CustomViewDialog customViewDialog) {
            if (customViewDialog != null) {
                customViewDialog.dismiss();
            }
            MusicMainActivity.this.b(false);
        }
    }

    private void cQM_(View view, MenuListAdapter menuListAdapter) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.menu_list);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.k));
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setAdapter(menuListAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final MusicMenu musicMenu, ArrayList<MusicSong> arrayList) {
        if (arrayList != null) {
            LogUtil.a("MusicMainActivity", "addSongsToMenuByLongClick enter, songList size:", Integer.valueOf(arrayList.size()));
            final ArrayList arrayList2 = new ArrayList(16);
            try {
                ArrayList arrayList3 = new ArrayList(16);
                Iterator<MusicSong> it = arrayList.iterator();
                while (it.hasNext()) {
                    MusicSong next = it.next();
                    if (!musicMenu.getMusicSongsList().contains(next)) {
                        arrayList3.add(Integer.valueOf(Integer.parseInt(next.getSongIndex())));
                        arrayList2.add(next);
                    }
                }
                musicMenu.addRealMusicCount(arrayList2);
                Message obtainMessage = this.aa.obtainMessage();
                obtainMessage.what = 1001;
                this.aa.sendMessage(obtainMessage);
                LogUtil.a("MusicMainActivity", "addSongsToMenuByLongClick enter, songsIndexList size:", Integer.valueOf(arrayList3.size()));
                OperationStruct operationStruct = new OperationStruct();
                operationStruct.setOperationType(3);
                operationStruct.setFolderIndex(Integer.parseInt(musicMenu.getMenuIndex()));
                operationStruct.setMusicIndexs(arrayList3);
                jjd.b(this.k).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.14
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("MusicMainActivity", "addSongsToMenuByLongClick onResponse, errorCode:", Integer.valueOf(i));
                        if (i != 100000) {
                            musicMenu.removeRealMusicCount(arrayList2);
                            Message obtainMessage2 = MusicMainActivity.this.aa.obtainMessage();
                            obtainMessage2.what = 1001;
                            MusicMainActivity.this.aa.sendMessage(obtainMessage2);
                        }
                    }
                });
            } catch (NumberFormatException unused) {
                LogUtil.a("MusicMainActivity", "get mCurrentMenu's index is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (!this.ah) {
            this.au.setIconTitle(2, getResources().getString(R.string._2130841400_res_0x7f020f38));
            this.au.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
            if (!jjj.b()) {
                this.au.setIconVisible(1, 0);
            }
            this.au.setIconVisible(3, 0);
            this.ah = true;
            if (this.t.getMusicSongsList().size() <= 500) {
                this.am.clear();
                this.am.addAll(this.t.getMusicSongsList());
                Arrays.fill(this.f.c(), true);
            } else {
                Arrays.fill(this.f.c(), 0, ay(), true);
            }
        } else {
            a(this.t.getMusicSongsList());
            this.au.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            this.au.setIconVisible(1, 8);
            this.au.setIconVisible(3, 8);
            this.ah = false;
            this.am.clear();
            Arrays.fill(this.f.c(), false);
        }
        this.f.notifyDataSetChanged();
        j();
    }

    private int ay() {
        int i = 0;
        if (this.am.size() == 0) {
            this.am.addAll(this.t.getMusicSongsList().subList(0, 500));
            return 500;
        }
        ArrayList<MusicSong> musicSongsList = this.t.getMusicSongsList();
        while (i < musicSongsList.size() && this.am.size() < 500) {
            if (!this.am.contains(musicSongsList.get(i))) {
                this.am.add(musicSongsList.get(i));
            }
            i++;
        }
        return i;
    }

    private void an() {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.k);
        builder.e(R.string._2130843233_res_0x7f021661);
        builder.c(getString(R.string._2130843274_res_0x7f02168a));
        builder.cyn_(R.string._2130841554_res_0x7f020fd2, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean unused = MusicMainActivity.d = MusicMainActivity.this.x();
                if (MusicMainActivity.this.al() || jjj.d()) {
                    MusicMainActivity.this.h();
                } else {
                    MusicMainActivity.this.y();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean al() {
        if (!d) {
            return true;
        }
        if (!jjj.e()) {
            return false;
        }
        LogUtil.a("MusicMainActivity", "isNotSupportedOnlineMusic, device dismiss online music.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        nrh.d(this.k, getResources().getString(R.string._2130843214_res_0x7f02164e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.al = false;
        this.ax = 0;
        SyncMusicBinder syncMusicBinder = this.bl;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyServiceCancel();
        }
        LogUtil.a("MusicMainActivity", "cancelTransferSongByUser enter, mIsSyncMusicTaskAlive is:", Boolean.valueOf(this.al));
        this.bw.clear();
        this.x.clear();
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 1008;
        this.aa.sendMessage(obtainMessage);
    }

    private void m() {
        this.bw.clear();
        this.x.clear();
        SyncMusicBinder syncMusicBinder = this.bl;
        if (syncMusicBinder != null) {
            syncMusicBinder.notifyServiceCancel();
        }
        this.bq.setVisibility(8);
        this.u.setVisibility(8);
        this.ay.setVisibility(8);
        this.v.setVisibility(8);
        bb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.al) {
            nrh.d(this.k, getResources().getString(R.string._2130843275_res_0x7f02168b));
            return;
        }
        if (jpy.b(this.p) < 10) {
            an();
            return;
        }
        d = x();
        if (al() || jjj.d()) {
            h();
        } else {
            y();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        PermissionUtil.b(this.k, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new CustomPermissionAction(this.k) { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.15
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                gge.e(AnalyticsValue.HEALTH_MUSIC_LOCAL_MUSIC_1090093.value(), MusicMainActivity.this.b);
                Intent intent = new Intent();
                intent.setClass(MusicMainActivity.this, LocalMusicResourceActivity.class);
                Bundle bundle = new Bundle();
                jjm.e().d(MusicMainActivity.this.t);
                bundle.putInt("max_music_number", MusicMainActivity.this.ar);
                bundle.putInt("watch_remaining_space", MusicMainActivity.this.bd);
                bundle.putInt("musicCount", MusicMainActivity.this.av);
                if (MusicMainActivity.this.bi instanceof ArrayList) {
                    bundle.putStringArrayList("device_support_music_type_list", (ArrayList) MusicMainActivity.this.bi);
                }
                intent.putExtras(bundle);
                MusicMainActivity.this.startActivityForResult(intent, 2);
            }
        });
    }

    public void c(final boolean z, final ArrayList<MusicSong> arrayList, int i) {
        final MusicMenuViewDialog.Builder builder = new MusicMenuViewDialog.Builder(this.k);
        MusicMenuViewDialog b2 = builder.b();
        if (b2 == null) {
            LogUtil.a("MusicMainActivity", "createNewSongMenu failed, Create dialog is null");
            return;
        }
        b2.show();
        if (i == 0) {
            builder.d().setText(R.string._2130843225_res_0x7f021659);
        } else {
            builder.d().setText(R.string._2130843215_res_0x7f02164f);
        }
        builder.cVB_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicMainActivity.this.a(builder.cVA_().getText().toString(), z, (ArrayList<MusicSong>) arrayList);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cVA_().addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.20
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() != 0) {
                    if (MusicMainActivity.this.v().contains(editable.toString())) {
                        builder.c(false);
                        builder.a().setError(MusicMainActivity.this.getString(R.string._2130843235_res_0x7f021663));
                        return;
                    } else {
                        builder.c(true);
                        builder.a().setError("");
                        return;
                    }
                }
                builder.c(false);
                builder.a().setError("");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final boolean z, ArrayList<MusicSong> arrayList) {
        LogUtil.a("MusicMainActivity", "enter addNewMenu method ");
        c = arrayList;
        OperationStruct operationStruct = new OperationStruct();
        operationStruct.setOperationType(0);
        operationStruct.setFolderName(str);
        this.af.a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.19
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                LogUtil.a("MusicMainActivity", "receive rsp index:", Integer.valueOf(intValue));
                if (i == 100000) {
                    LogUtil.a("MusicMainActivity", "receive success rsp");
                    MusicMenu musicMenu = new MusicMenu();
                    musicMenu.setMenuName(str);
                    musicMenu.setMenuIndex(String.valueOf(intValue));
                    MusicMainActivity.this.q.add(musicMenu);
                    if (z) {
                        MusicMainActivity.this.a(musicMenu, (List<MusicSong>) MusicMainActivity.c);
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(MusicMainActivity.this, MusicSongsActivity.class);
                    jjm.e().e(musicMenu);
                    jjm.e().d(MusicMainActivity.this.t);
                    intent.putExtra("song_num", 0);
                    intent.putStringArrayListExtra("menu_list", MusicMainActivity.this.v());
                    MusicMainActivity.this.startActivityForResult(intent, 1);
                    Message obtainMessage = MusicMainActivity.this.aa.obtainMessage();
                    obtainMessage.what = 1001;
                    MusicMainActivity.this.aa.sendMessage(obtainMessage);
                    return;
                }
                LogUtil.a("MusicMainActivity", "addNewMenu failed, errCode:", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x() {
        ContentProviderClient contentProviderClient;
        LogUtil.a("MusicMainActivity", "refreshHwMusicSupportFlag enter:");
        ContentResolver contentResolver = getContentResolver();
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                if (contentResolver == null) {
                    LogUtil.h("MusicMainActivity", "getBundleFromHwMusic: resolver is null");
                    return false;
                }
                contentProviderClient = contentResolver.acquireUnstableContentProviderClient(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"));
                try {
                    if (contentProviderClient == null) {
                        LogUtil.h("MusicMainActivity", "getHwMusicAccountSendToWear, client is null.");
                        if (contentProviderClient != null) {
                            contentProviderClient.close();
                        }
                        return false;
                    }
                    Bundle call = contentResolver.call(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"), "getSupportFlag", (String) null, (Bundle) null);
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    if (call != null && !call.isEmpty()) {
                        LogUtil.a("MusicMainActivity", "bundle =", call.getString("supportLevel"));
                        return "1".equals(call.getString("supportLevel"));
                    }
                    LogUtil.a("MusicMainActivity", "bundle is empty!");
                    return false;
                } catch (RuntimeException unused) {
                    contentProviderClient2 = contentProviderClient;
                    LogUtil.b("MusicMainActivity", "getHwMusicSupportFlag DeadObjectException");
                    if (contentProviderClient2 != null) {
                        contentProviderClient2.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                contentProviderClient = null;
            }
        } catch (RuntimeException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.k);
        builder.e(this.k.getResources().getString(R.string._2130843366_res_0x7f0216e6));
        builder.czC_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicMainActivity.this.r.setVisibility(0);
                MusicMainActivity musicMainActivity = MusicMainActivity.this;
                musicMainActivity.b(musicMainActivity.am);
                gge.e(AnalyticsValue.HEALTH_MUSIC_DELETE_SONG_1090084.value(), MusicMainActivity.this.b);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MusicMainActivity", "deleteMusicTip click cancel.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        if (e2.isShowing()) {
            return;
        }
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("MusicMainActivity", "mIsSupportHuaweiMusic :", Boolean.valueOf(d));
        if (d) {
            gge.e(AnalyticsValue.HEALTH_MUSIC_ONLINE_MUSIC_1090094.value(), this.b);
            LogUtil.a("MusicMainActivity", "go to huawei music");
            Bundle bundle = new Bundle();
            DeviceInfo d2 = jpt.d("MusicMainActivity");
            bundle.putString("deviceId", d2 != null ? d2.getDeviceIdentify() : "");
            bundle.putString("from", getPackageName());
            bundle.putInt("needback", 1);
            bundle.putInt("channelid", 2);
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwmediacenter://com.android.mediacenter/healthpage?needback=1"));
            intent.putExtra("data", bundle);
            cQP_(intent, this.k);
        }
    }

    private void cQP_(Intent intent, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (resolveActivity != null) {
                ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
                intent.setComponent(componentName);
                nsn.cLM_(intent, componentName.getPackageName(), this, this.k.getResources().getString(R.string._2130843372_res_0x7f0216ec));
                return;
            }
            LogUtil.h("MusicMainActivity", "startActivitySecurity can not find activity");
            return;
        }
        LogUtil.h("MusicMainActivity", "startActivitySecurity can not get packageManager");
    }

    private void r() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.k);
        builder.e(getResources().getString(R.string._2130843332_res_0x7f0216c4));
        builder.czC_(R.string._2130843333_res_0x7f0216c5, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MusicMainActivity", "cancelTransferTip click agree.");
                MusicMainActivity.this.o();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MusicMainActivity", "cancelTransferTip click cancel.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        if (e2.isShowing()) {
            return;
        }
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final MusicMenu musicMenu, List<MusicSong> list) {
        if (list == null) {
            LogUtil.h("MusicMainActivity", "addMusicToMenu songList is null.");
            return;
        }
        LogUtil.a("MusicMainActivity", "addMusicToMenu songList size: ", Integer.valueOf(list.size()));
        final ArrayList arrayList = new ArrayList(16);
        try {
            ArrayList arrayList2 = new ArrayList(16);
            for (MusicSong musicSong : list) {
                if (!musicMenu.getMusicSongsList().contains(musicSong)) {
                    arrayList2.add(Integer.valueOf(Integer.parseInt(musicSong.getSongIndex())));
                    arrayList.add(musicSong);
                }
            }
            musicMenu.addRealMusicCount(arrayList);
            Message obtainMessage = this.aa.obtainMessage();
            obtainMessage.what = 1001;
            this.aa.sendMessage(obtainMessage);
            LogUtil.a("MusicMainActivity", "addSongsToMenuByLongClick enter, songsIndexList size:", Integer.valueOf(arrayList2.size()));
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(3);
            operationStruct.setFolderIndex(Integer.parseInt(musicMenu.getMenuIndex()));
            operationStruct.setMusicIndexs(arrayList2);
            jjd.b(this.k).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.24
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("MusicMainActivity", "addMusicToMenu onResponse errorCode: ", Integer.valueOf(i));
                    if (i != 100000) {
                        musicMenu.removeRealMusicCount(arrayList);
                        Message obtainMessage2 = MusicMainActivity.this.aa.obtainMessage();
                        obtainMessage2.what = 1001;
                        MusicMainActivity.this.aa.sendMessage(obtainMessage2);
                    }
                }
            });
        } catch (NumberFormatException unused) {
            LogUtil.a("MusicMainActivity", "addMusicToMenu occur NumberFormatException.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("MusicMainActivity", "Enter onPause.");
        this.ak = false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        az();
        ServiceConnection serviceConnection = this.bk;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            this.bk = null;
        }
        SyncMusicBinder syncMusicBinder = this.bl;
        if (syncMusicBinder != null) {
            syncMusicBinder.setSyncMusicListener(null);
            this.bl = null;
        }
        jjd.b(BaseApplication.getContext()).a();
        jjm.e().h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        this.bw.clear();
        this.bw.addAll(this.x);
        LogUtil.a("MusicMainActivity", "mUnFinishedSongsLists size:", Integer.valueOf(this.bw.size()));
        Iterator<MusicSong> it = this.bw.iterator();
        while (it.hasNext()) {
            if (it.next().getMusicAppType() != 0) {
                it.remove();
            }
        }
        LogUtil.a("MusicMainActivity", "mUnFinishedSongsLists has local music size:", Integer.valueOf(this.bw.size()));
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 1004;
        this.aa.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, int i) {
        LogUtil.a("MusicMainActivity", "refreshAddMusicLayoutOnCreate enter, mIsSyncMusicTaskAlive is:", Boolean.valueOf(z));
        this.al = z;
        this.ax = i;
        if (z && i != 2) {
            SyncMusicBinder syncMusicBinder = this.bl;
            if (syncMusicBinder != null) {
                syncMusicBinder.notifyUiCurrentMusicProgress();
                this.bl.notifyUiCurrentMusicNameAndTotalProgress();
            }
            Message obtainMessage = this.aa.obtainMessage();
            obtainMessage.what = 1012;
            this.aa.sendMessage(obtainMessage);
            return;
        }
        Message obtainMessage2 = this.aa.obtainMessage();
        obtainMessage2.what = 1013;
        this.aa.sendMessage(obtainMessage2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.what = 1007;
        this.aa.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        Message obtainMessage = this.aa.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("SONGNAME", str);
        bundle.putString("PROGRESSTEXT", str2);
        obtainMessage.setData(bundle);
        obtainMessage.what = 1005;
        this.aa.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        this.g.setVisibility(0);
        this.o.setText(str);
        this.bn.setText(str2);
    }

    private void e(ArrayList<MusicSong> arrayList) {
        LogUtil.a("MusicMainActivity", "enter addSongToDefaultMenu method");
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        if (!this.al) {
            this.x.clear();
        }
        if (this.ae != null) {
            this.al = true;
            bb();
            Intent intent = new Intent();
            intent.setClass(this.k, SyncMusicService.class);
            intent.setAction("syncMusicListTaskBegin");
            startService(intent);
        } else {
            LogUtil.b("MusicMainActivity", "transferMusicFile Error:mHwDeviceConfigManager is null");
        }
        this.az.postDelayed(new Runnable() { // from class: com.huawei.ui.device.activity.music.MusicMainActivity.22
            @Override // java.lang.Runnable
            public void run() {
                MusicMainActivity.this.az.smoothScrollTo(0, 0);
            }
        }, 100L);
    }

    private void d(MusicMenu musicMenu) {
        LogUtil.a("MusicMainActivity", "updateMusicMenuInfoByReturn enter");
        if (musicMenu == null) {
            LogUtil.a("MusicMainActivity", "onActivityResult， menu is null");
            return;
        }
        String menuName = musicMenu.getMenuName();
        Iterator<MusicMenu> it = this.q.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MusicMenu next = it.next();
            if (next.getMenuName().equals(menuName)) {
                String memuNewName = musicMenu.getMemuNewName();
                if (!TextUtils.isEmpty(memuNewName)) {
                    next.setMenuName(memuNewName);
                }
                next.setMenuMusicCount(musicMenu.getMenuMusicCount());
            }
        }
        ak();
    }

    private void ak() {
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 1001;
        this.aa.sendMessage(obtainMessage);
    }

    private void b(String str) {
        LogUtil.a("MusicMainActivity", "deleteMusicMenuByReturn enter");
        Iterator<MusicMenu> it = this.q.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().getMenuName().equals(str)) {
                it.remove();
                LogUtil.a("MusicMainActivity", "deleteMusicMenuByReturn succeed");
                break;
            }
        }
        b bVar = new b();
        this.at = bVar;
        this.j.setAdapter(bVar);
        Message obtainMessage = this.aa.obtainMessage();
        obtainMessage.what = 1001;
        this.aa.sendMessage(obtainMessage);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("MusicMainActivity", "onActivityResult enter, req:", Integer.valueOf(i), ";resultcode:", Integer.valueOf(i2));
        if (intent == null) {
            LogUtil.a("MusicMainActivity", "onActivityResult， data is null");
            return;
        }
        if (i == 1) {
            d(i2);
            return;
        }
        if (i == 2) {
            ArrayList<MusicSong> c2 = jjm.e().c();
            if (c2 != null) {
                e(c2);
                return;
            } else {
                LogUtil.a("MusicMainActivity", "onActivityResult， songList is null");
                return;
            }
        }
        if (i != 3) {
            LogUtil.h("MusicMainActivity", "onActivityResult, default.");
            return;
        }
        if (i2 == 1) {
            m();
            return;
        }
        if (i2 == 2) {
            this.v.setVisibility(8);
            ArrayList<MusicSong> arrayList = this.bw;
            if (arrayList == null || arrayList.size() == 0) {
                LogUtil.a("MusicMainActivity", "mUnFinishedSongsLists is empty, something wrong!");
                return;
            } else {
                LogUtil.a("MusicMainActivity", "mUnFinishedSongsLists size:", Integer.valueOf(this.bw.size()));
                e(this.bw);
                return;
            }
        }
        LogUtil.h("MusicMainActivity", "UN_COMPLETED_MUSIC_REQUEST_CODE, default.");
    }

    private void d(int i) {
        MusicMenu d2 = jjm.e().d();
        if (d2 == null) {
            LogUtil.a("MusicMainActivity", "onActivityResult， menu is null");
            return;
        }
        String memuNewName = d2.getMemuNewName();
        String menuName = d2.getMenuName();
        LogUtil.a("MusicMainActivity", "onActivityResult receive returnmenu, menu name =", menuName, ";menu new name=", memuNewName);
        if (i == 1) {
            d(d2);
        } else if (i == 3) {
            b(menuName);
        } else {
            LogUtil.h("MusicMainActivity", "not support operation!");
        }
    }

    static class d implements SearchView.OnQueryTextListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MusicMainActivity> f9133a;

        d(MusicMainActivity musicMainActivity) {
            if (musicMainActivity != null) {
                this.f9133a = new WeakReference<>(musicMainActivity);
            }
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            return c(str);
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            return c(str);
        }

        private boolean c(String str) {
            MusicMainActivity musicMainActivity = this.f9133a.get();
            if (musicMainActivity == null || musicMainActivity.aa == null) {
                return false;
            }
            if (!TextUtils.isEmpty(str)) {
                musicMainActivity.aa.sendEmptyMessage(1011);
                musicMainActivity.be = str;
                musicMainActivity.bf.e(musicMainActivity.be, musicMainActivity.t.getMusicSongsList(), LocalMusicSearchThreadManager.SearchType.SONG);
                return true;
            }
            musicMainActivity.be = null;
            Message obtain = Message.obtain();
            obtain.what = 1010;
            obtain.obj = musicMainActivity.t.getMusicSongsList();
            musicMainActivity.aa.sendMessage(obtain);
            return true;
        }
    }

    @Override // com.huawei.ui.device.views.music.LocalMusicSearchThreadManager.SearchCallback
    public void onSearchResult(List<MusicSong> list) {
        if (this.aa != null) {
            Message obtain = Message.obtain();
            obtain.what = 1010;
            obtain.obj = list;
            this.aa.sendMessage(obtain);
        }
    }

    private void a(List<MusicSong> list) {
        this.au.setIconTitle(2, getResources().getString(R.string._2130841399_res_0x7f020f37));
        if (koq.b(list)) {
            LogUtil.h("MusicMainActivity", "musicSongList is empty");
        } else {
            if (!jjm.e().i() || list.size() <= 500) {
                return;
            }
            this.au.setIconTitle(2, getResources().getString(R.string._2130847719_res_0x7f0227e7));
        }
    }

    static class a implements MusicControllerCallbackInterface {
        private WeakReference<MusicMainActivity> c;

        public a(MusicMainActivity musicMainActivity) {
            this.c = new WeakReference<>(musicMainActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicControllerCallbackInterface
        public void onGetFolders(List<MusicFolderStruct> list, int i) {
            MusicMainActivity musicMainActivity = this.c.get();
            if (musicMainActivity == null || musicMainActivity.aa == null) {
                LogUtil.a("MusicMainActivity", "onGetFolders activity or handler is null");
                return;
            }
            if (i == 100000) {
                LogUtil.a("MusicMainActivity", "receive onGetFolders rsp");
                MusicMainActivity.e(list, musicMainActivity);
            } else {
                LogUtil.a("MusicMainActivity", "onGetFolders failed, error_code=", Integer.valueOf(i));
                Message obtainMessage = musicMainActivity.aa.obtainMessage();
                obtainMessage.what = 1014;
                musicMainActivity.aa.sendMessage(obtainMessage);
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicControllerCallbackInterface
        public void onGetNegotiationData(NegotiationData negotiationData, int i) {
            MusicMainActivity musicMainActivity = this.c.get();
            if (musicMainActivity == null || musicMainActivity.aa == null) {
                LogUtil.a("MusicMainActivity", "onGetNegotiationData activity or handler is null");
                return;
            }
            if (i == 100000) {
                musicMainActivity.bi = negotiationData.getMusicFormatList();
                if (musicMainActivity.bi.size() == 0) {
                    LogUtil.a("MusicMainActivity", "receive onGetNegotiationData SupportMusicTypeList is null!");
                }
                musicMainActivity.bd = negotiationData.getStorageSpace();
                LogUtil.a("MusicMainActivity", "receive onGetNegotiationData memory size:", Integer.valueOf(musicMainActivity.bd));
                musicMainActivity.ar = negotiationData.getMaxMusicNumber();
                LogUtil.a("MusicMainActivity", "receive onGetNegotiationData max music number:", Integer.valueOf(musicMainActivity.ar));
                musicMainActivity.av = negotiationData.getMusicCount();
                LogUtil.a("MusicMainActivity", "receive onGetNegotiationData music count:", Integer.valueOf(musicMainActivity.av));
                jjm.e().b(negotiationData);
                Message obtainMessage = musicMainActivity.aa.obtainMessage();
                obtainMessage.what = 1002;
                musicMainActivity.aa.sendMessage(obtainMessage);
                return;
            }
            LogUtil.a("MusicMainActivity", "onGetNegotiationData failed, errCode=", Integer.valueOf(i));
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicControllerCallbackInterface
        public void onGetMusicFileInfo(int i, String str, int i2) {
            LogUtil.a("MusicMainActivity", "receive device's getMusicFileInfo request! musicIndex:", Integer.valueOf(i), "; fileName:", str);
        }
    }

    static class e implements NegotiationCallbackInterface {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MusicMainActivity> f9134a;

        public e(MusicMainActivity musicMainActivity) {
            this.f9134a = new WeakReference<>(musicMainActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface
        public void onGetNegotiationData(NegotiationData negotiationData, int i) {
            MusicMainActivity musicMainActivity = this.f9134a.get();
            if (negotiationData == null || musicMainActivity == null) {
                LogUtil.h("MusicMainActivity", "onGetNegotiationData negotiationData == null or activity == null");
                return;
            }
            if (i == 100000) {
                LogUtil.a("MusicMainActivity", "onGetNegotiationData rsp,menuSize:", Integer.valueOf(negotiationData.getMaxFolderNumber()));
                musicMainActivity.bd = negotiationData.getStorageSpace();
                LogUtil.a("MusicMainActivity", "receive onGetNegotiationData memory size:", Integer.valueOf(musicMainActivity.bd));
                musicMainActivity.ap = negotiationData.getMaxFolderNumber();
                MusicMainActivity.a(negotiationData, musicMainActivity);
                return;
            }
            LogUtil.a("MusicMainActivity", "onGetNegotiationData rsp, errorCode:", Integer.valueOf(i));
        }
    }

    static class c implements NegotiationCallbackInterface {
        private WeakReference<MusicMainActivity> c;

        public c(MusicMainActivity musicMainActivity) {
            this.c = new WeakReference<>(musicMainActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface
        public void onGetNegotiationData(NegotiationData negotiationData, int i) {
            MusicMainActivity musicMainActivity = this.c.get();
            if (musicMainActivity == null || musicMainActivity.aa == null) {
                LogUtil.a("MusicMainActivity", "onGetNegotiationData activity or handler is null");
                return;
            }
            LogUtil.a("MusicMainActivity", "onGetNegotiationData rsp, errorCode:", Integer.valueOf(i));
            if (i == 100000) {
                LogUtil.a("MusicMainActivity", "onGetNegotiationData rsp,memory:", Integer.valueOf(negotiationData.getStorageSpace()));
                musicMainActivity.bd = negotiationData.getStorageSpace();
                Message obtainMessage = musicMainActivity.aa.obtainMessage();
                obtainMessage.what = 1002;
                musicMainActivity.aa.sendMessage(obtainMessage);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
