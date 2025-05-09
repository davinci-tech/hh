package com.huawei.ui.device.fragment.music;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import com.huawei.health.R;
import com.huawei.health.suggestion.util.StaticHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.device.activity.music.LocalMusicResourceActivity;
import com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView;
import com.huawei.ui.device.views.music.LocalMusicSearchThreadManager;
import defpackage.oao;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BaseLocalMusicFragment extends BaseFragment implements LocalMusicSearchThreadManager.SearchCallback {

    /* renamed from: a, reason: collision with root package name */
    protected Context f9290a;
    protected BaseLocalMusicAdapter b;
    protected HealthSearchView c;
    protected AlphabetIndexWaveSideBarView d;
    protected List<MusicSong> e;
    protected ListView f;
    protected String g;
    protected LocalMusicSearchThreadManager h;
    protected List<String> i;
    protected List<MusicSong> j;
    private LinearLayout l;
    protected HealthTextView m;
    protected HealthTextView n;
    private LinearLayout o;
    private a k = new a(this);
    private e p = new e(this);
    private ArrayList<MusicSong> r = new ArrayList<>(16);

    public boolean c() {
        return true;
    }

    protected static class a extends StaticHandler<BaseLocalMusicFragment> {
        a(BaseLocalMusicFragment baseLocalMusicFragment) {
            super(baseLocalMusicFragment);
        }

        @Override // com.huawei.health.suggestion.util.StaticHandler
        /* renamed from: cTA_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(BaseLocalMusicFragment baseLocalMusicFragment, Message message) {
            if (baseLocalMusicFragment == null || message == null || baseLocalMusicFragment.b == null) {
                return;
            }
            int i = message.what;
            boolean z = true;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                baseLocalMusicFragment.b.a(new ArrayList(16));
                baseLocalMusicFragment.b.notifyDataSetChanged();
                return;
            }
            if (message.obj instanceof List) {
                baseLocalMusicFragment.b.a((List<MusicSong>) message.obj);
            }
            boolean[] c = baseLocalMusicFragment.b.c();
            boolean[] b = baseLocalMusicFragment.b.b();
            for (boolean z2 : c) {
                if (!z2) {
                    z = false;
                }
            }
            if (baseLocalMusicFragment.f9290a instanceof LocalMusicResourceActivity) {
                if (!z && baseLocalMusicFragment.b.a() == 0) {
                    ((LocalMusicResourceActivity) baseLocalMusicFragment.f9290a).c().setVisibility(0);
                } else {
                    ((LocalMusicResourceActivity) baseLocalMusicFragment.f9290a).c().setVisibility(8);
                }
            }
            boolean z3 = false;
            if (c.length == b.length && c.length > 0) {
                z3 = d(c, b);
            }
            if (baseLocalMusicFragment.f9290a instanceof LocalMusicResourceActivity) {
                ((LocalMusicResourceActivity) baseLocalMusicFragment.f9290a).e(baseLocalMusicFragment.b.d().size(), z3);
            }
            baseLocalMusicFragment.f.requestLayout();
            baseLocalMusicFragment.b.notifyDataSetChanged();
        }

        private Boolean d(boolean[] zArr, boolean[] zArr2) {
            for (int i = 0; i < zArr.length; i++) {
                if (!zArr[i] && !zArr2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_music_song_layout, viewGroup, false);
        this.m = (HealthTextView) inflate.findViewById(R.id.hw_local_music_tip);
        this.n = (HealthTextView) inflate.findViewById(R.id.music_empty_format_tips);
        this.c = (HealthSearchView) inflate.findViewById(R.id.music_search_view);
        this.f = (ListView) inflate.findViewById(R.id.hw_local_songs_list);
        this.d = (AlphabetIndexWaveSideBarView) inflate.findViewById(R.id.bar_list);
        this.o = (LinearLayout) inflate.findViewById(R.id.data_ll);
        this.l = (LinearLayout) inflate.findViewById(R.id.empty_ll);
        this.f9290a = getActivity();
        this.c.setOnQueryTextListener(this.p);
        this.j = new ArrayList(16);
        if (getActivity() instanceof LocalMusicResourceActivity) {
            LocalMusicResourceActivity localMusicResourceActivity = (LocalMusicResourceActivity) getActivity();
            this.j.addAll(localMusicResourceActivity.e());
            this.e = localMusicResourceActivity.d();
            this.i = localMusicResourceActivity.a();
        }
        b();
        BaseLocalMusicAdapter baseLocalMusicAdapter = new BaseLocalMusicAdapter(this.f9290a, this.j, this.e);
        this.b = baseLocalMusicAdapter;
        this.f.setAdapter((ListAdapter) baseLocalMusicAdapter);
        LocalMusicSearchThreadManager localMusicSearchThreadManager = new LocalMusicSearchThreadManager();
        this.h = localMusicSearchThreadManager;
        localMusicSearchThreadManager.c();
        this.h.e(this);
        a();
        if (this.j.size() > 0) {
            this.o.setVisibility(0);
        } else {
            this.l.setVisibility(0);
        }
        return inflate;
    }

    private void b() {
        List<String> list = this.i;
        if (list == null || list.isEmpty()) {
            LogUtil.h("BaseLocalMusicFragment", "mSupportMusicTypeFilterList is empty.");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (this.i.contains("MP3")) {
            arrayList.add(getResources().getString(R.string._2130844392_res_0x7f021ae8));
        }
        if (this.i.contains("AAC")) {
            arrayList.add(getResources().getString(R.string._2130844393_res_0x7f021ae9));
        }
        if (this.i.contains("WAV")) {
            arrayList.add(getResources().getString(R.string._2130844394_res_0x7f021aea));
        }
        if (this.i.contains("FLAC")) {
            arrayList.add(getResources().getString(R.string._2130844395_res_0x7f021aeb));
        }
        if (this.i.contains("APE")) {
            arrayList.add(getResources().getString(R.string._2130844396_res_0x7f021aec));
        }
        if (this.i.contains("OGG")) {
            arrayList.add(getResources().getString(R.string._2130844397_res_0x7f021aed));
        }
        if (this.i.contains("M4A")) {
            arrayList.add(getResources().getString(R.string._2130844398_res_0x7f021aee));
        }
        if (this.i.contains("OPUS")) {
            arrayList.add(getResources().getString(R.string._2130844399_res_0x7f021aef));
        }
        if (this.i.contains("AMR")) {
            arrayList.add(getResources().getString(R.string._2130845008_res_0x7f021d50));
        }
        if (this.i.contains("IMY")) {
            arrayList.add(getResources().getString(R.string._2130845009_res_0x7f021d51));
        }
        String b = b(arrayList);
        this.m.setText(getResources().getString(R.string._2130844391_res_0x7f021ae7, b));
        this.n.setText(getResources().getString(R.string._2130844391_res_0x7f021ae7, b));
    }

    private String b(List<String> list) {
        switch (list.size()) {
            case 1:
                return list.get(0);
            case 2:
                return getResources().getString(R.string._2130844422_res_0x7f021b06, list.get(0), list.get(1));
            case 3:
                return getResources().getString(R.string._2130844423_res_0x7f021b07, list.get(0), list.get(1), list.get(2));
            case 4:
                return getResources().getString(R.string._2130844424_res_0x7f021b08, list.get(0), list.get(1), list.get(2), list.get(3));
            case 5:
                return getResources().getString(R.string._2130844425_res_0x7f021b09, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
            case 6:
                return getResources().getString(R.string._2130844426_res_0x7f021b0a, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            case 7:
                return getResources().getString(R.string._2130844427_res_0x7f021b0b, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
            case 8:
                return e(list);
            default:
                return c(list);
        }
    }

    private String e(List<String> list) {
        return getResources().getString(R.string._2130844428_res_0x7f021b0c, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
    }

    private String c(List<String> list) {
        int size = list.size();
        if (size != 9) {
            return size != 10 ? "" : getResources().getString(R.string._2130845011_res_0x7f021d53, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9));
        }
        return getResources().getString(R.string._2130845010_res_0x7f021d52, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8));
    }

    protected void c(String str) {
        if (MusicSong.SORT_TYPE_SONG.equals(str)) {
            this.b.e(0);
            this.b.e(MusicSong.SORT_TYPE_SONG);
        } else if (MusicSong.SORT_TYPE_SINGER.equals(str)) {
            this.m.setVisibility(8);
            this.b.e(1);
            this.b.e(MusicSong.SORT_TYPE_SINGER);
        } else if (MusicSong.SORT_TYPE_ALBUM.equals(str)) {
            this.m.setVisibility(8);
            this.b.e(2);
            this.b.e(MusicSong.SORT_TYPE_ALBUM);
        } else if (MusicSong.SORT_TYPE_FOLDER.equals(str)) {
            this.m.setVisibility(8);
            this.b.e(3);
            this.b.e(MusicSong.SORT_TYPE_FOLDER);
        } else {
            LogUtil.a("BaseLocalMusicFragment", "error type");
        }
        this.b.a(this.j);
        this.b.notifyDataSetChanged();
    }

    private void a() {
        this.d.setOnLetterChangeListener(new AlphabetIndexWaveSideBarView.OnLetterChangeListener() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicFragment.2
            @Override // com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView.OnLetterChangeListener
            public void onLetterChange(String str) {
                int c;
                if (BaseLocalMusicFragment.this.b.a() == 0) {
                    c = BaseLocalMusicFragment.this.b.d(str);
                } else if (BaseLocalMusicFragment.this.b.a() == 1) {
                    c = BaseLocalMusicFragment.this.b.b(str);
                } else if (BaseLocalMusicFragment.this.b.a() == 2) {
                    c = BaseLocalMusicFragment.this.b.a(str);
                } else {
                    c = BaseLocalMusicFragment.this.b.c(str);
                }
                if (c == -1 || BaseLocalMusicFragment.this.f == null) {
                    return;
                }
                BaseLocalMusicFragment.this.f.setSelection(c);
            }
        });
        this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.fragment.music.BaseLocalMusicFragment.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (BaseLocalMusicFragment.this.b == null) {
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                if (BaseLocalMusicFragment.this.b.a() != 0) {
                    BaseLocalMusicFragment.this.r.clear();
                    if (BaseLocalMusicFragment.this.b.getItem(i) instanceof ArrayList) {
                        BaseLocalMusicFragment.this.r.addAll((ArrayList) BaseLocalMusicFragment.this.b.getItem(i));
                    }
                    LogUtil.a("BaseLocalMusicFragment", "mSongsSubSingers :" + BaseLocalMusicFragment.this.r.size());
                    BaseLocalMusicFragment.this.b.e(0);
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = BaseLocalMusicFragment.this.r;
                    BaseLocalMusicFragment.this.k.sendMessage(obtain);
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    @Override // com.huawei.ui.device.views.music.LocalMusicSearchThreadManager.SearchCallback
    public void onSearchResult(List<MusicSong> list) {
        if (this.k != null) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = list;
            this.k.sendMessage(obtain);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (getActivity() == null || !z) {
            return;
        }
        if (getActivity() instanceof LocalMusicResourceActivity) {
            ((LocalMusicResourceActivity) getActivity()).b(this);
        }
        BaseLocalMusicAdapter baseLocalMusicAdapter = this.b;
        if (baseLocalMusicAdapter != null) {
            baseLocalMusicAdapter.i();
            this.b.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            oao.d().e();
        }
    }

    static class e implements SearchView.OnQueryTextListener {
        private WeakReference<BaseLocalMusicFragment> c;

        e(BaseLocalMusicFragment baseLocalMusicFragment) {
            if (baseLocalMusicFragment != null) {
                this.c = new WeakReference<>(baseLocalMusicFragment);
            }
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            BaseLocalMusicFragment baseLocalMusicFragment = this.c.get();
            if (baseLocalMusicFragment == null || baseLocalMusicFragment.k == null) {
                return false;
            }
            if (!TextUtils.isEmpty(str)) {
                baseLocalMusicFragment.a(str);
                return true;
            }
            baseLocalMusicFragment.j();
            return true;
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            BaseLocalMusicFragment baseLocalMusicFragment = this.c.get();
            if (baseLocalMusicFragment == null || baseLocalMusicFragment.k == null) {
                return false;
            }
            if (!TextUtils.isEmpty(str)) {
                baseLocalMusicFragment.a(str);
                return true;
            }
            baseLocalMusicFragment.j();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LocalMusicSearchThreadManager.SearchType searchType;
        this.k.sendEmptyMessage(2);
        this.g = str;
        if (this.b.a() != 0) {
            if (this instanceof SingerFragment) {
                searchType = LocalMusicSearchThreadManager.SearchType.SINGER;
            } else if (this instanceof AlbumFragment) {
                searchType = LocalMusicSearchThreadManager.SearchType.ALBUM;
            } else if (this instanceof FolderFragment) {
                searchType = LocalMusicSearchThreadManager.SearchType.FOLDER;
            } else {
                searchType = LocalMusicSearchThreadManager.SearchType.SONG;
            }
        } else {
            searchType = LocalMusicSearchThreadManager.SearchType.SONG;
        }
        this.h.e(this.g, this.j, searchType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.g = null;
        Message obtain = Message.obtain();
        obtain.what = 1;
        if (this instanceof SongFragment) {
            obtain.obj = this.j;
        } else if (this.b.a() != 0) {
            obtain.obj = this.j;
        } else {
            obtain.obj = this.r;
        }
        this.k.sendMessage(obtain);
    }

    public ArrayList<MusicSong> e() {
        BaseLocalMusicAdapter baseLocalMusicAdapter = this.b;
        if (baseLocalMusicAdapter != null) {
            return baseLocalMusicAdapter.d();
        }
        return null;
    }

    public BaseLocalMusicAdapter d() {
        return this.b;
    }

    public void b(boolean z) {
        if (z) {
            for (int i = 0; i < this.b.b().length; i++) {
                this.b.b()[i] = false;
            }
            ArrayList<MusicSong> e2 = e();
            if (e2 != null) {
                e2.removeAll(this.b.e());
            }
        } else {
            for (int i2 = 0; i2 < this.b.b().length; i2++) {
                this.b.b()[i2] = true;
            }
            ArrayList arrayList = new ArrayList(16);
            for (MusicSong musicSong : this.b.e()) {
                if (!this.e.contains(musicSong.getFileName())) {
                    arrayList.add(musicSong);
                }
            }
            ArrayList<MusicSong> e3 = e();
            if (e3 != null) {
                e3.removeAll(arrayList);
                e3.addAll(arrayList);
            }
        }
        this.b.notifyDataSetChanged();
        if (getActivity() instanceof LocalMusicResourceActivity) {
            ((LocalMusicResourceActivity) getActivity()).e(this.b.e().size(), Boolean.valueOf(!z));
        }
    }
}
