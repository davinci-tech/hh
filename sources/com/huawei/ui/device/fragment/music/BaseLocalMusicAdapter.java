package com.huawei.ui.device.fragment.music;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.music.LocalMusicResourceActivity;
import defpackage.koq;
import defpackage.oao;
import defpackage.ocb;
import defpackage.ocg;
import defpackage.och;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class BaseLocalMusicAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<MusicSong> f9286a;
    private Context b;
    private List<MusicSong> d;
    private boolean[] f;
    private boolean[] h;
    private int l;
    private String m;
    private List<MusicSong> o = new ArrayList(16);
    private int g = 0;
    private HashMap<String, ArrayList<MusicSong>> k = new HashMap<>(16);
    private ArrayList<ocg> n = new ArrayList<>(16);
    private HashMap<String, ArrayList<MusicSong>> e = new HashMap<>(16);
    private ArrayList<ocb> c = new ArrayList<>(16);
    private HashMap<String, ArrayList<MusicSong>> j = new HashMap<>(16);
    private ArrayList<och> i = new ArrayList<>(16);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 4;
    }

    public BaseLocalMusicAdapter(Context context, List<MusicSong> list, List<MusicSong> list2) {
        this.b = context;
        this.d = list2;
        e(list);
        this.f9286a = oao.d().b();
    }

    private void e(final List<MusicSong> list) {
        if (list != null) {
            this.o = list;
            int size = list.size();
            this.g = size;
            this.h = new boolean[size];
            this.f = new boolean[size];
            if (this.d == null) {
                return;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicAdapter.5
                @Override // java.lang.Runnable
                public void run() {
                    for (int i = 0; i < BaseLocalMusicAdapter.this.g; i++) {
                        MusicSong musicSong = (MusicSong) list.get(i);
                        if (musicSong != null) {
                            BaseLocalMusicAdapter.this.b(musicSong, i);
                        }
                    }
                    LogUtil.a("BaseLocalMusicAdapter", "initList, mIsAddedFlags:", Arrays.toString(BaseLocalMusicAdapter.this.f));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MusicSong musicSong, int i) {
        for (MusicSong musicSong2 : this.d) {
            if (musicSong2.getSongName() != null && musicSong2.getSongName().equals(musicSong.getSongName())) {
                if (musicSong2.getSongSingerName() != null) {
                    if (musicSong2.getSongSingerName().equals(musicSong.getSongSingerName())) {
                        b(true, i);
                    }
                } else {
                    b(true, i);
                }
            }
        }
    }

    private void b(boolean z, int i) {
        boolean[] zArr = this.f;
        if (i >= zArr.length) {
            return;
        }
        zArr[i] = z;
    }

    public List<MusicSong> e() {
        ArrayList arrayList = new ArrayList();
        if (!koq.b(this.o) && this.o.size() <= this.f.length) {
            for (int i = 0; i < this.o.size(); i++) {
                if (!this.f[i]) {
                    arrayList.add(this.o.get(i));
                    LogUtil.a("BaseLocalMusicAdapter", "getNonAddedSongList, name:", this.o.get(i).getSongName(), "singer:", this.o.get(i).getSongSingerName());
                }
            }
        }
        return arrayList;
    }

    public boolean[] c() {
        boolean[] zArr = this.f;
        return zArr == null ? new boolean[0] : zArr;
    }

    public boolean[] b() {
        boolean[] zArr = this.h;
        return zArr == null ? new boolean[0] : zArr;
    }

    protected void a(List<MusicSong> list) {
        if (list == null) {
            LogUtil.h("BaseLocalMusicAdapter", "dataLists is null.");
            return;
        }
        LogUtil.a("BaseLocalMusicAdapter", "mItemType : " + this.l, "dataLists :" + list.size(), "mSortType :" + this.m);
        if (MusicSong.SORT_TYPE_SINGER.equals(this.m) && this.l == 1) {
            this.n.clear();
            this.k.clear();
            b(list);
            Collections.sort(this.n, ocg.a());
        } else if (MusicSong.SORT_TYPE_ALBUM.equals(this.m) && this.l == 2) {
            this.c.clear();
            this.e.clear();
            c(list);
            Collections.sort(this.c, ocb.c());
        } else if (MusicSong.SORT_TYPE_FOLDER.equals(this.m) && this.l == 3) {
            this.i.clear();
            this.j.clear();
            d(list);
            Collections.sort(this.i, och.e());
        } else {
            Collections.sort(list, MusicSong.getSortByNameInstance());
        }
        this.o = list;
        if (this.l == 0) {
            if (this.f9286a != null) {
                j(list);
            }
            if (this.d != null) {
                i(list);
            }
        }
    }

    private List<ocg> b(List<MusicSong> list) {
        for (MusicSong musicSong : list) {
            String songSingerName = musicSong.getSongSingerName();
            if (TextUtils.isEmpty(songSingerName)) {
                break;
            }
            if (this.k.containsKey(songSingerName)) {
                this.k.get(songSingerName).add(musicSong);
            } else {
                ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                arrayList.add(musicSong);
                this.k.put(songSingerName, arrayList);
            }
        }
        for (Map.Entry<String, ArrayList<MusicSong>> entry : this.k.entrySet()) {
            ocg ocgVar = new ocg();
            ocgVar.b(entry.getKey());
            ocgVar.a(entry.getValue());
            this.n.add(ocgVar);
        }
        return this.n;
    }

    private List<ocb> c(List<MusicSong> list) {
        for (MusicSong musicSong : list) {
            String albumName = musicSong.getAlbumName();
            if (TextUtils.isEmpty(albumName)) {
                break;
            }
            if (this.e.containsKey(albumName)) {
                this.e.get(albumName).add(musicSong);
            } else {
                ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                arrayList.add(musicSong);
                this.e.put(albumName, arrayList);
            }
        }
        for (Map.Entry<String, ArrayList<MusicSong>> entry : this.e.entrySet()) {
            ocb ocbVar = new ocb();
            ocbVar.c(entry.getKey());
            ocbVar.e(entry.getValue());
            this.c.add(ocbVar);
        }
        return this.c;
    }

    private List<och> d(List<MusicSong> list) {
        for (MusicSong musicSong : list) {
            String songFilePath = musicSong.getSongFilePath();
            if (TextUtils.isEmpty(songFilePath)) {
                break;
            }
            int lastIndexOf = musicSong.getSongFilePath().lastIndexOf("/");
            if (lastIndexOf > 0) {
                songFilePath = musicSong.getSongFilePath().substring(0, lastIndexOf);
            }
            if (this.j.containsKey(songFilePath)) {
                this.j.get(songFilePath).add(musicSong);
            } else {
                ArrayList<MusicSong> arrayList = new ArrayList<>(16);
                arrayList.add(musicSong);
                this.j.put(songFilePath, arrayList);
            }
        }
        for (Map.Entry<String, ArrayList<MusicSong>> entry : this.j.entrySet()) {
            och ochVar = new och();
            ochVar.e(entry.getKey());
            ochVar.b(entry.getValue());
            this.i.add(ochVar);
        }
        return this.i;
    }

    private void j(List list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        this.g = size;
        this.h = new boolean[size];
        MusicSong musicSong = null;
        for (int i = 0; i < this.f9286a.size(); i++) {
            String songName = this.f9286a.get(i).getSongName();
            for (int i2 = 0; i2 < list.size(); i2++) {
                Object obj = list.get(i2);
                if (obj instanceof MusicSong) {
                    musicSong = (MusicSong) obj;
                }
                if (musicSong != null && songName.equals(musicSong.getSongName())) {
                    this.h[i2] = true;
                }
            }
        }
    }

    private void i(final List list) {
        if (list == null) {
            return;
        }
        this.f = new boolean[list.size()];
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Object obj = list.get(i);
                    if (obj instanceof MusicSong) {
                        BaseLocalMusicAdapter.this.b((MusicSong) obj, i);
                    }
                }
                LogUtil.a("BaseLocalMusicAdapter", "processAddedSongsFromPre, mIsAddedFlags:", Arrays.toString(BaseLocalMusicAdapter.this.f));
            }
        });
    }

    public void i() {
        if (this.f9286a == null) {
            return;
        }
        j(this.o);
    }

    public int d(String str) {
        List<MusicSong> list = this.o;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < this.o.size(); i++) {
                if (this.o.get(i).getFirstLetter(this.m).equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int b(String str) {
        ArrayList<ocg> arrayList = this.n;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i = 0; i < this.n.size(); i++) {
                if (this.n.get(i).e().equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int a(String str) {
        ArrayList<ocb> arrayList = this.c;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i = 0; i < this.c.size(); i++) {
                if (this.c.get(i).b().equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int c(String str) {
        ArrayList<och> arrayList = this.i;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i = 0; i < this.i.size(); i++) {
                if (this.i.get(i).d().equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void e(String str) {
        this.m = str;
    }

    public void e(int i) {
        this.l = i;
    }

    public int a() {
        return this.l;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int i = this.l;
        if (i == 0) {
            return this.o.size();
        }
        if (i == 1) {
            return this.n.size();
        }
        if (i == 2) {
            return this.c.size();
        }
        return this.i.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) {
            LogUtil.b("BaseLocalMusicAdapter", "getItem position is wrong");
            return new Object();
        }
        int i2 = this.l;
        if (i2 == 0) {
            return this.o.get(i);
        }
        if (i2 == 1) {
            return this.n.get(i).d();
        }
        if (i2 == 2) {
            return this.c.get(i).e();
        }
        return this.i.get(i).a();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        int i2 = this.l;
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 1;
        }
        return i2 == 2 ? 2 : 3;
    }

    public ArrayList<MusicSong> d() {
        return this.f9286a;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return cTx_(i, view);
    }

    private View cTx_(int i, View view) {
        View view2;
        a aVar;
        d dVar;
        int itemViewType = getItemViewType(i);
        a aVar2 = null;
        if (view == null) {
            if (itemViewType == 0) {
                d dVar2 = new d();
                View inflate = LayoutInflater.from(this.b).inflate(R.layout.activity_music_song_list_item, (ViewGroup) null);
                dVar2.f9289a = (HealthTextView) inflate.findViewById(R.id.music_song_name_tv);
                dVar2.b = (HealthTextView) inflate.findViewById(R.id.music_song_singer_tv);
                dVar2.e = (ImageView) inflate.findViewById(R.id.imageview_more_or_select);
                dVar2.c = (HealthCheckBox) inflate.findViewById(R.id.cb_more_or_select);
                dVar2.d = (ImageView) inflate.findViewById(R.id.under_line);
                inflate.setTag(dVar2);
                dVar = dVar2;
                view = inflate;
            } else if (itemViewType == 1 || itemViewType == 2 || itemViewType == 3) {
                aVar = new a();
                view2 = LayoutInflater.from(this.b).inflate(R.layout.item_music_singer_list_item, (ViewGroup) null);
                aVar.c = (HealthTextView) view2.findViewById(R.id.hw_music_singer_name_tv);
                aVar.b = (HealthTextView) view2.findViewById(R.id.hw_music_singer_count);
                aVar.f9288a = (ImageView) view2.findViewById(R.id.under_line);
                view2.setTag(aVar);
                aVar2 = aVar;
                view = view2;
                dVar = null;
            } else {
                LogUtil.a("BaseLocalMusicAdapter", "other types!");
                dVar = null;
            }
        } else if (itemViewType == 1 || itemViewType == 2 || itemViewType == 3) {
            view2 = view;
            aVar = (a) view.getTag();
            aVar2 = aVar;
            view = view2;
            dVar = null;
        } else if (itemViewType == 0 && (view.getTag() instanceof d)) {
            dVar = (d) view.getTag();
        } else {
            LogUtil.h("BaseLocalMusicAdapter", "other types!");
            dVar = null;
        }
        cTy_(view, aVar2, dVar, i);
        return view;
    }

    private void cTy_(View view, a aVar, d dVar, int i) {
        if (i < 0 || i >= getCount()) {
            LogUtil.b("BaseLocalMusicAdapter", "setData position is wrong");
            return;
        }
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            cTz_(view, dVar, i);
            return;
        }
        if (itemViewType == 1) {
            d(aVar, i);
        } else if (itemViewType == 2) {
            e(aVar, i);
        } else {
            if (itemViewType != 3) {
                return;
            }
            a(aVar, i);
        }
    }

    private void d(a aVar) {
        if (CommonUtil.ar()) {
            aVar.f9288a.setVisibility(8);
        } else {
            aVar.f9288a.setVisibility(0);
        }
    }

    private void a(a aVar, int i) {
        if (aVar == null) {
            LogUtil.h("BaseLocalMusicAdapter", "setSingerData, viewHolder is null");
            return;
        }
        aVar.c.setText(this.i.get(i).c());
        aVar.b.setText(this.b.getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, 0, Integer.valueOf(this.i.get(i).a().size())));
        if (this.i.size() - 1 == i) {
            aVar.f9288a.setVisibility(8);
        } else {
            d(aVar);
        }
    }

    private void e(a aVar, int i) {
        if (aVar == null) {
            LogUtil.h("BaseLocalMusicAdapter", "setSingerData, viewHolder is null");
            return;
        }
        if (this.c.get(i) != null) {
            aVar.c.setText(this.c.get(i).d());
        }
        aVar.b.setText(this.b.getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, 0, Integer.valueOf(this.c.get(i).e().size())));
        if (this.c.size() - 1 == i) {
            aVar.f9288a.setVisibility(8);
        } else {
            d(aVar);
        }
    }

    private void d(a aVar, int i) {
        if (aVar == null) {
            LogUtil.h("BaseLocalMusicAdapter", "setSingerData, viewHolder is null");
            return;
        }
        aVar.c.setText(this.n.get(i).b());
        aVar.b.setText(this.b.getResources().getQuantityString(R.plurals._2130903257_res_0x7f0300d9, 0, Integer.valueOf(this.n.get(i).d().size())));
        if (this.n.size() - 1 == i) {
            aVar.f9288a.setVisibility(8);
        } else {
            d(aVar);
        }
    }

    private void cTz_(View view, d dVar, int i) {
        if (dVar == null) {
            LogUtil.h("BaseLocalMusicAdapter", "viewHolderDetail is null");
            return;
        }
        dVar.f9289a.setText(this.o.get(i).getSongName());
        dVar.b.setText(this.o.get(i).getSongSingerName());
        dVar.e.setVisibility(8);
        dVar.c.setVisibility(0);
        if (this.o.size() - 1 == i) {
            dVar.d.setVisibility(8);
        } else if (CommonUtil.ar()) {
            dVar.d.setVisibility(8);
        } else {
            dVar.d.setVisibility(0);
        }
        dVar.c.setFocusable(false);
        cTw_(view, dVar, i);
    }

    private void cTw_(View view, final d dVar, final int i) {
        boolean[] zArr = this.f;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return;
        }
        final boolean z = zArr[i];
        dVar.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicAdapter.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                BaseLocalMusicAdapter.this.e(z2, z, i);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        if (z) {
            dVar.c.setChecked(true);
        }
        dVar.c.setChecked(this.h[i]);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!z) {
                    dVar.c.setChecked(!dVar.c.isChecked());
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        a(dVar, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, boolean z2, int i) {
        d(z, z2, i);
        boolean z3 = false;
        boolean[] zArr = this.f;
        if (zArr.length == this.h.length && zArr.length > 0) {
            z3 = j();
        }
        if (this.m.equals(((LocalMusicResourceActivity) this.b).b())) {
            Context context = this.b;
            if (context instanceof LocalMusicResourceActivity) {
                ((LocalMusicResourceActivity) context).e(this.f9286a.size(), z3);
            }
        }
    }

    private void d(boolean z, boolean z2, int i) {
        List<MusicSong> list;
        if (!z2) {
            boolean[] zArr = this.h;
            if (zArr == null || i < 0 || i >= zArr.length) {
                LogUtil.h("BaseLocalMusicAdapter", "position is ArrayIndexOutOfBoundsException");
                return;
            }
            zArr[i] = z;
        }
        if (!z) {
            if (z2 || (list = this.o) == null || i >= list.size()) {
                return;
            }
            this.f9286a.remove(this.o.get(i));
            return;
        }
        List<MusicSong> list2 = this.o;
        if (list2 == null || i >= list2.size() || z2 || this.f9286a.contains(this.o.get(i))) {
            return;
        }
        this.f9286a.add(this.o.get(i));
    }

    private Boolean j() {
        int i = 0;
        while (true) {
            boolean[] zArr = this.f;
            if (i < zArr.length) {
                if (!zArr[i] && !this.h[i]) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }

    private void a(d dVar, int i) {
        if (this.f[i]) {
            dVar.c.setChecked(true);
            dVar.c.setEnabled(false);
            dVar.c.setOnCheckedChangeListener(null);
            return;
        }
        dVar.c.setEnabled(true);
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        ImageView f9288a;
        HealthTextView b;
        HealthTextView c;

        a() {
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9289a;
        HealthTextView b;
        HealthCheckBox c;
        ImageView d;
        ImageView e;

        d() {
        }
    }
}
