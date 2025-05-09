package com.huawei.ui.device.views.music;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.views.music.LocalMusicSearchThreadManager;
import defpackage.pf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class LocalMusicSearchThreadManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f9316a = new Object();
    private int b = 0;
    private SearchCallback c;
    private e d;
    private ExtendHandler e;

    public interface SearchCallback {
        void onSearchResult(List<MusicSong> list);
    }

    public enum SearchType {
        SONG,
        SINGER,
        ALBUM,
        FOLDER
    }

    public void c() {
        this.e = HandlerCenter.yt_(new Handler.Callback() { // from class: oca
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return LocalMusicSearchThreadManager.cVv_(message);
            }
        }, "searchLocalSong");
    }

    public static /* synthetic */ boolean cVv_(Message message) {
        LogUtil.h("LocalMusicSearchThreadManager", "No handleMessage");
        return false;
    }

    public void e(SearchCallback searchCallback) {
        this.c = searchCallback;
    }

    public void e(String str, List<MusicSong> list, SearchType searchType) {
        ExtendHandler extendHandler = this.e;
        if (extendHandler == null) {
            return;
        }
        e eVar = this.d;
        if (eVar != null) {
            extendHandler.removeTasks(eVar);
        }
        int i = this.b + 1;
        this.b = i;
        e eVar2 = new e(this, str, list, searchType, i);
        this.d = eVar2;
        this.e.postTask(eVar2);
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private List<MusicSong> f9318a;
        private String c;
        private WeakReference<LocalMusicSearchThreadManager> d;
        private int e;
        private SearchType f;

        e(LocalMusicSearchThreadManager localMusicSearchThreadManager, String str, List<MusicSong> list, SearchType searchType, int i) {
            this.c = str;
            synchronized (LocalMusicSearchThreadManager.f9316a) {
                this.f9318a = new ArrayList(list);
            }
            this.d = new WeakReference<>(localMusicSearchThreadManager);
            this.f = searchType;
            this.e = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            LocalMusicSearchThreadManager localMusicSearchThreadManager;
            WeakReference<LocalMusicSearchThreadManager> weakReference = this.d;
            if (weakReference == null || (localMusicSearchThreadManager = weakReference.get()) == null) {
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            List<MusicSong> list = this.f9318a;
            if (list == null || list.size() < 1) {
                return;
            }
            synchronized (LocalMusicSearchThreadManager.f9316a) {
                for (MusicSong musicSong : this.f9318a) {
                    int i = AnonymousClass4.f9317a[this.f.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i == 4 && LocalMusicSearchThreadManager.this.e(musicSong.getFileName(), this.c)) {
                                    arrayList.add(musicSong);
                                }
                            } else if (LocalMusicSearchThreadManager.this.e(musicSong.getAlbumName(), this.c)) {
                                arrayList.add(musicSong);
                            }
                        } else if (LocalMusicSearchThreadManager.this.e(musicSong.getSongSingerName(), this.c)) {
                            arrayList.add(musicSong);
                        }
                    } else if (LocalMusicSearchThreadManager.this.e(musicSong.getSongName(), this.c)) {
                        arrayList.add(musicSong);
                    }
                }
            }
            if (this.e != localMusicSearchThreadManager.b) {
                LogUtil.a("LocalMusicSearchThreadManager", "searchResult mNumber :" + this.e + ",mThreadNumber:" + LocalMusicSearchThreadManager.this.b);
                return;
            }
            LogUtil.a("LocalMusicSearchThreadManager", "searchResult size:" + arrayList.size());
            if (localMusicSearchThreadManager.c != null) {
                localMusicSearchThreadManager.c.onSearchResult(arrayList);
            }
        }
    }

    /* renamed from: com.huawei.ui.device.views.music.LocalMusicSearchThreadManager$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9317a;

        static {
            int[] iArr = new int[SearchType.values().length];
            f9317a = iArr;
            try {
                iArr[SearchType.SONG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9317a[SearchType.SINGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9317a[SearchType.ALBUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9317a[SearchType.FOLDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str, String str2) {
        LogUtil.a("LocalMusicSearchThreadManager", "isMatch songName :" + str + ",songKey:" + str2);
        if (str == null || str2 == null) {
            return false;
        }
        boolean contains = str.contains(str2);
        if (a(str).contains(str2.toUpperCase(Locale.ENGLISH))) {
            return true;
        }
        return contains;
    }

    private String a(String str) {
        StringBuffer stringBuffer = new StringBuffer(16);
        for (int i = 0; i < str.length(); i++) {
            String d = pf.d(Character.toString(str.charAt(i)), "");
            if (!TextUtils.isEmpty(d)) {
                String upperCase = d.substring(0, 1).toUpperCase(Locale.ENGLISH);
                if (upperCase.matches("[A-Z]")) {
                    stringBuffer.append(upperCase);
                } else {
                    stringBuffer.append("#");
                }
            }
        }
        return stringBuffer.toString();
    }
}
