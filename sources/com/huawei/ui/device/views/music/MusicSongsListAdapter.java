package com.huawei.ui.device.views.music;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.device.activity.music.MusicMainActivity;
import com.huawei.ui.device.views.music.MenuListAdapter;
import com.huawei.ui.device.views.music.MusicSongsListAdapter;
import defpackage.gge;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jjm;
import defpackage.koq;
import defpackage.nqc;
import defpackage.nrh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MusicSongsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final LayoutInflater f9323a;
    private Context b;
    private Map<String, Object> c;
    private boolean[] d;
    private MusicMainActivity.f e;
    private boolean f;
    private List<MusicMenu> i;
    private List<MusicSong> j;

    public MusicSongsListAdapter(Context context, List<MusicSong> list, List<MusicMenu> list2) {
        this.j = new ArrayList(16);
        this.i = new ArrayList(16);
        HashMap hashMap = new HashMap(16);
        this.c = hashMap;
        this.f = true;
        this.b = context;
        hashMap.put("click", "1");
        this.f9323a = LayoutInflater.from(this.b);
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "musicSongList size: ", Integer.valueOf(list.size()));
        arrayList.removeAll(list);
        arrayList.addAll(list);
        this.j = arrayList;
        this.i = list2;
        this.d = new boolean[arrayList.size()];
        Context context2 = this.b;
        if (context2 instanceof MusicMainActivity) {
            this.e = ((MusicMainActivity) context2).i();
        }
    }

    public void a(List<MusicSong> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.j.addAll(list);
        this.d = new boolean[this.j.size()];
    }

    public void c(MusicSong musicSong) {
        if (musicSong == null) {
            return;
        }
        this.j.remove(musicSong);
    }

    public boolean[] c() {
        boolean[] zArr = this.d;
        return zArr == null ? new boolean[0] : zArr;
    }

    public void b(boolean z) {
        this.f = z;
        notifyItemChanged(this.j.size());
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9331a;
        private HealthTextView b;
        private View c;
        private HealthCheckBox e;
        private HealthTextView f;
        private ImageView g;

        public SongViewHolder(View view) {
            super(view);
            this.c = view;
            this.b = (HealthTextView) view.findViewById(R.id.music_song_name_tv);
            this.f = (HealthTextView) view.findViewById(R.id.music_song_singer_tv);
            this.f9331a = (ImageView) view.findViewById(R.id.imageview_more_or_select);
            HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.cb_more_or_select);
            this.e = healthCheckBox;
            healthCheckBox.setClickable(false);
            this.g = (ImageView) view.findViewById(R.id.under_line);
            this.c.setVisibility(8);
        }

        public void b(final MusicSong musicSong, int i) {
            if (musicSong == null) {
                LogUtil.h("MusicMainActivity_MusicSongsListAdapter", "song is null.");
                this.c.setVisibility(8);
                return;
            }
            this.c.setVisibility(0);
            this.b.setText(musicSong.getSongName());
            this.f.setText(musicSong.getSongSingerName());
            this.f9331a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.SongViewHolder.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "click  mMoreOperationImageView");
                    MusicSongsListAdapter.this.cVF_(view, musicSong);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f9331a.setVisibility(((MusicMainActivity) MusicSongsListAdapter.this.b).g() ? 8 : 0);
            this.e.setVisibility(((MusicMainActivity) MusicSongsListAdapter.this.b).g() ? 0 : 8);
            if (!((MusicMainActivity) MusicSongsListAdapter.this.b).g()) {
                View view = this.itemView;
                MusicSongsListAdapter musicSongsListAdapter = MusicSongsListAdapter.this;
                view.setOnLongClickListener(musicSongsListAdapter.new c(i, musicSongsListAdapter.j));
            }
            if (MusicSongsListAdapter.this.d != null && i >= 0 && i < MusicSongsListAdapter.this.d.length) {
                this.e.setChecked(MusicSongsListAdapter.this.d[i]);
            }
            b(i, musicSong);
            if (MusicSongsListAdapter.this.j.size() - 1 == i) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
            }
        }

        private void b(final int i, final MusicSong musicSong) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.SongViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int i2;
                    if (MusicSongsListAdapter.this.d != null && (i2 = i) >= 0 && i2 < MusicSongsListAdapter.this.d.length) {
                        if (MusicSongsListAdapter.this.b instanceof MusicMainActivity) {
                            if (((MusicMainActivity) MusicSongsListAdapter.this.b).g()) {
                                if (MusicSongsListAdapter.this.d[i]) {
                                    MusicSongsListAdapter.this.d[i] = false;
                                    SongViewHolder.this.e.setChecked(false);
                                    ((MusicMainActivity) MusicSongsListAdapter.this.b).e().remove(musicSong);
                                } else if (((MusicMainActivity) MusicSongsListAdapter.this.b).e().size() < 500) {
                                    MusicSongsListAdapter.this.d[i] = true;
                                    if (!((MusicMainActivity) MusicSongsListAdapter.this.b).e().contains(MusicSongsListAdapter.this.j.get(i))) {
                                        ((MusicMainActivity) MusicSongsListAdapter.this.b).e().add(musicSong);
                                    }
                                    SongViewHolder.this.e.setChecked(true);
                                } else {
                                    nrh.c(MusicSongsListAdapter.this.b, MusicSongsListAdapter.this.b.getResources().getString(R.string._2130847718_res_0x7f0227e6));
                                    ViewClickInstrumentation.clickOnView(view);
                                    return;
                                }
                                ((MusicMainActivity) MusicSongsListAdapter.this.b).j();
                            }
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        }
                        LogUtil.h("MusicMainActivity_MusicSongsListAdapter", "mContext is not MusicMainActivity");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private HealthTextView d;
        private HealthProgressBar e;

        public LoadMoreViewHolder(View view) {
            super(view);
            this.e = (HealthProgressBar) view.findViewById(R.id.progressbar_load_more);
            this.d = (HealthTextView) view.findViewById(R.id.tv_load_more);
        }

        public void a() {
            if (MusicSongsListAdapter.this.f) {
                this.e.setVisibility(0);
                this.d.setText(R.string._2130845086_res_0x7f021d9e);
            } else {
                this.e.setVisibility(8);
                this.d.setText(R.string._2130845085_res_0x7f021d9d);
            }
            this.d.setOnClickListener(new View.OnClickListener() { // from class: oce
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MusicSongsListAdapter.LoadMoreViewHolder.this.cVG_(view);
                }
            });
        }

        public /* synthetic */ void cVG_(View view) {
            if (MusicSongsListAdapter.this.f || jjm.e().j()) {
                LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "LoadMoreViewHolder setData load more : reject");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.e.setVisibility(0);
            this.d.setText(R.string._2130845086_res_0x7f021d9e);
            if (MusicSongsListAdapter.this.e != null) {
                MusicSongsListAdapter.this.e.sendEmptyMessage(1015);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public List<MusicSong> a() {
        return this.j;
    }

    public void e(List<MusicSong> list) {
        this.j = list;
        c(list);
    }

    private void c(List list) {
        if (list == null) {
            return;
        }
        this.d = new boolean[list.size()];
        for (int i = 0; i < ((MusicMainActivity) this.b).e().size(); i++) {
            String songName = ((MusicMainActivity) this.b).e().get(i).getSongName();
            for (int i2 = 0; i2 < list.size(); i2++) {
                Object obj = list.get(i2);
                if ((obj instanceof MusicSong) && songName.equals(((MusicSong) obj).getSongName())) {
                    this.d[i2] = true;
                }
            }
        }
    }

    class c implements View.OnLongClickListener {

        /* renamed from: a, reason: collision with root package name */
        private List<MusicSong> f9334a;
        private int b;

        c(int i, List<MusicSong> list) {
            this.b = i;
            this.f9334a = list;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int i;
            int i2;
            MusicMainActivity musicMainActivity = (MusicMainActivity) MusicSongsListAdapter.this.b;
            musicMainActivity.e().clear();
            List<MusicSong> list = this.f9334a;
            if (list != null && (i2 = this.b) >= 0 && i2 < list.size()) {
                musicMainActivity.e().add(this.f9334a.get(this.b));
            }
            musicMainActivity.b(true);
            musicMainActivity.c(8);
            if (this.f9334a != null) {
                for (int i3 = 0; i3 < this.f9334a.size(); i3++) {
                    musicMainActivity.cQQ_().put(i3, 0);
                }
            }
            if (MusicSongsListAdapter.this.d != null && (i = this.b) >= 0 && i < MusicSongsListAdapter.this.d.length) {
                MusicSongsListAdapter.this.d[this.b] = true;
            }
            MusicSongsListAdapter.this.notifyDataSetChanged();
            gge.e(AnalyticsValue.HEALTH_MUSIC_HOME_LONG_CLICK_1090085.value(), MusicSongsListAdapter.this.c);
            return true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.j == null) {
            return 0;
        }
        if (!jjm.e().a()) {
            return this.j.size();
        }
        return this.j.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (jjm.e().a() && koq.c(this.j) && i == this.j.size()) {
            LogUtil.c("MusicMainActivity_MusicSongsListAdapter", "getItemViewType, View Type is VIEW_TYPE_LOAD_MORE");
            return 1;
        }
        return super.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "onCreateViewHolder viewType : ", Integer.valueOf(i));
        if (i == 1) {
            return new LoadMoreViewHolder(this.f9323a.inflate(R.layout.item_music_load_more, viewGroup, false));
        }
        return new SongViewHolder(this.f9323a.inflate(R.layout.activity_music_song_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "onBindViewHolder position : ", Integer.valueOf(i));
        if (this.j.isEmpty()) {
            LogUtil.h("MusicMainActivity_MusicSongsListAdapter", "onBindViewHolder mList is empty");
            return;
        }
        if (viewHolder instanceof SongViewHolder) {
            if (i < 0 || i >= this.j.size()) {
                return;
            }
            ((SongViewHolder) viewHolder).b(this.j.get(i), i);
            return;
        }
        if (viewHolder instanceof LoadMoreViewHolder) {
            ((LoadMoreViewHolder) viewHolder).a();
        } else {
            LogUtil.h("MusicMainActivity_MusicSongsListAdapter", "onBindViewHolder others");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cVF_(View view, final MusicSong musicSong) {
        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "showPopMenu");
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.popview_mainactivity_songsitem_more_operation, (ViewGroup) null);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.move_song_to_menu_rl);
        if (jjj.b()) {
            relativeLayout.setVisibility(8);
        }
        final nqc nqcVar = new nqc(this.b, inflate);
        nqcVar.cEh_(view, 0);
        final MusicMainActivity musicMainActivity = (MusicMainActivity) this.b;
        inflate.findViewById(R.id.move_song_to_menu_rl).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (musicMainActivity.a() != 2) {
                    MusicSongsListAdapter.this.b();
                } else {
                    nqc nqcVar2 = nqcVar;
                    if (nqcVar2 != null) {
                        nqcVar2.b();
                    }
                    if (MusicSongsListAdapter.this.i.size() > 0) {
                        MusicSongsListAdapter.this.d(musicSong);
                        gge.e(AnalyticsValue.HEALTH_MUSIC_MOVE_SONG_TO_MENU_1090083.value(), MusicSongsListAdapter.this.c);
                    } else {
                        ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                        arrayList.add(musicSong);
                        ((MusicMainActivity) MusicSongsListAdapter.this.b).c(true, arrayList, MusicSongsListAdapter.this.i.size());
                        gge.e(AnalyticsValue.HEALTH_MUSIC_CREATE_NEW_SONG_MENU_1090082.value(), MusicSongsListAdapter.this.c);
                    }
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        inflate.findViewById(R.id.del_song_from_menu_rl).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (musicMainActivity.a() != 2) {
                    MusicSongsListAdapter.this.b();
                } else {
                    nqc nqcVar2 = nqcVar;
                    if (nqcVar2 != null) {
                        nqcVar2.b();
                    }
                    MusicSongsListAdapter.this.b(musicSong);
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context = this.b;
        nrh.d(context, context.getResources().getString(R.string._2130843214_res_0x7f02164e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final MusicSong musicSong) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.b);
        if (this.i.size() <= 0) {
            builder.e(this.b.getResources().getString(R.string._2130843366_res_0x7f0216e6));
        } else {
            builder.e(this.b.getResources().getString(R.string._2130843216_res_0x7f021650));
        }
        builder.e(this.b.getResources().getString(R.string._2130843366_res_0x7f0216e6));
        builder.czC_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicSongsListAdapter.this.b instanceof MusicMainActivity) {
                    int a2 = ((MusicMainActivity) MusicSongsListAdapter.this.b).a();
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "deleteMusicTip confirm deviceConnectState : ", Integer.valueOf(a2));
                    if (a2 == 2) {
                        MusicSongsListAdapter.this.e(musicSong);
                        gge.e(AnalyticsValue.HEALTH_MUSIC_DELETE_SONG_1090084.value(), MusicSongsListAdapter.this.c);
                    } else {
                        MusicSongsListAdapter.this.b();
                    }
                } else {
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "deleteMusicTip confirm : mContext is not MusicMainActivity");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "cancel!");
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

    private void cVE_(RecyclerView recyclerView, View view, final MusicSong musicSong) {
        MenuListAdapter menuListAdapter = new MenuListAdapter(this.b, this.i);
        recyclerView.setAdapter(menuListAdapter);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.b);
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        builder.czh_(view, 0, 0);
        builder.c(false);
        final CustomViewDialog e = builder.e();
        if (e != null) {
            e.setCancelable(false);
            e.show();
        }
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.create_new_menu);
        if ((this.b instanceof MusicMainActivity) && this.i.size() >= ((MusicMainActivity) this.b).d()) {
            healthTextView.setVisibility(8);
        }
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                e.dismiss();
                ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                arrayList.add(musicSong);
                ((MusicMainActivity) MusicSongsListAdapter.this.b).c(true, arrayList, MusicSongsListAdapter.this.i.size());
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        menuListAdapter.c(new AnonymousClass8(menuListAdapter, musicSong, e));
    }

    /* renamed from: com.huawei.ui.device.views.music.MusicSongsListAdapter$8, reason: invalid class name */
    public class AnonymousClass8 implements MenuListAdapter.OnItemClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CustomViewDialog f9328a;
        final /* synthetic */ MenuListAdapter c;
        final /* synthetic */ MusicSong e;

        AnonymousClass8(MenuListAdapter menuListAdapter, MusicSong musicSong, CustomViewDialog customViewDialog) {
            this.c = menuListAdapter;
            this.e = musicSong;
            this.f9328a = customViewDialog;
        }

        @Override // com.huawei.ui.device.views.music.MenuListAdapter.OnItemClickListener
        public void onItemClick(View view, int i) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "onItemClick enter time:", Long.valueOf(System.currentTimeMillis()));
            MusicSongsListAdapter.this.a(this.c, this.e, i);
            final CustomViewDialog customViewDialog = this.f9328a;
            view.postDelayed(new Runnable() { // from class: ocd
                @Override // java.lang.Runnable
                public final void run() {
                    MusicSongsListAdapter.AnonymousClass8.d(CustomViewDialog.this);
                }
            }, 300L);
        }

        public static /* synthetic */ void d(CustomViewDialog customViewDialog) {
            if (customViewDialog != null) {
                customViewDialog.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MusicSong musicSong) {
        View inflate = this.f9323a.inflate(R.layout.dialog_add_to_menu, (ViewGroup) null);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.menu_list);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.b));
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        healthRecycleView.setIsScroll(false);
        cVE_(healthRecycleView, inflate, musicSong);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MenuListAdapter menuListAdapter, final MusicSong musicSong, int i) {
        menuListAdapter.a(i);
        int size = menuListAdapter.c().size();
        if (i < 0 || i >= size) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "menuItemClick failed, position is outof index");
            return;
        }
        final MusicMenu musicMenu = menuListAdapter.c().get(i);
        if (musicMenu.getMusicSongsList().contains(musicSong)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        try {
            arrayList.add(Integer.valueOf(musicSong.getSongIndex()));
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(3);
            operationStruct.setFolderIndex(Integer.parseInt(musicMenu.getMenuIndex()));
            operationStruct.setMusicIndexs(arrayList);
            jjd.b(this.b).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "receive rsp time:", Long.valueOf(System.currentTimeMillis()));
                    if (i2 == 100000) {
                        musicMenu.getMusicSongsList().add(musicSong);
                        MusicMenu musicMenu2 = musicMenu;
                        musicMenu2.setMenuMusicCount(musicMenu2.getMenuMusicCount() + 1);
                        Message obtainMessage = MusicSongsListAdapter.this.e.obtainMessage();
                        obtainMessage.what = 1001;
                        MusicSongsListAdapter.this.e.sendMessage(obtainMessage);
                        return;
                    }
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "add music in folder failed,", " errCode:", Integer.valueOf(i2));
                }
            });
        } catch (NumberFormatException e) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "get mCurrentMenu's index is null,exception:", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final MusicSong musicSong) {
        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "deleteSongFromMenuByUser enter");
        if (musicSong == null) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "deleteSongFromMenuByUser failed, song is null!");
            return;
        }
        if (TextUtils.isEmpty(musicSong.getFileName())) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "deleteSongFromMenuByUser failed, fileName is empty!");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        try {
            arrayList.add(Integer.valueOf(musicSong.getSongIndex()));
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(4);
            operationStruct.setFolderIndex(0);
            operationStruct.setMusicIndexs(arrayList);
            jjd.b(this.b).a(operationStruct, new IBaseResponseCallback() { // from class: com.huawei.ui.device.views.music.MusicSongsListAdapter.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "reveice deleteSongFromMenuByUser rsp");
                    if (i == 100000) {
                        for (MusicMenu musicMenu : MusicSongsListAdapter.this.i) {
                            if (musicMenu.getMusicSongsList().contains(musicSong)) {
                                musicMenu.getMusicSongsList().remove(musicSong);
                                musicMenu.setMenuMusicCount(musicMenu.getMenuMusicCount() - 1);
                            }
                        }
                        Message obtainMessage = MusicSongsListAdapter.this.e.obtainMessage();
                        obtainMessage.obj = musicSong;
                        obtainMessage.what = 1000;
                        MusicSongsListAdapter.this.e.sendMessage(obtainMessage);
                        LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "delete music in folder succeed!");
                        return;
                    }
                    LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "delete music in folder failed, errCode:", Integer.valueOf(i));
                }
            });
        } catch (NumberFormatException e) {
            LogUtil.a("MusicMainActivity_MusicSongsListAdapter", "delete music in folder failed, exception:", e);
        }
    }
}
