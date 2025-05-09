package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class occ {
    private MusicSong b;
    private List<MusicSong> c = new ArrayList(16);
    private ArrayList<String> f = new ArrayList<>(16);
    private HashMap<String, MusicSong> i = new HashMap<>(16);
    private static final Uri e = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private static String[] d = {"_id", "title", "artist", "_display_name", MusicSong.SORT_TYPE_ALBUM, "artist_id", "album_id", "_size", "duration", "date_added", "date_modified", "_data", "is_music", "track"};

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f15611a = Collections.unmodifiableList(new ArrayList<String>() { // from class: occ.5
        {
            add("kgm");
            add("kgg");
            add("mgg2");
            add("mgg0");
            add("mflac0");
        }
    });

    public List<MusicSong> c(Context context, ArrayList<String> arrayList) {
        LogUtil.a("LocalMusicHandler", "getLocalMusicList enter!");
        if (context == null || arrayList == null || arrayList.size() == 0) {
            LogUtil.a("LocalMusicHandler", "get device supportmusictypelist is null!");
            return this.c;
        }
        this.f.clear();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            this.f.add(it.next().toLowerCase(Locale.ENGLISH));
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            LogUtil.a("LocalMusicHandler", "getContentResolver is null!");
            return this.c;
        }
        Cursor cVu_ = cVu_(contentResolver);
        if (cVu_ == null) {
            LogUtil.h("LocalMusicHandler", "cursor == null.");
            return this.c;
        }
        while (cVu_.moveToNext()) {
            this.b = new MusicSong();
            String string = cVu_.getString(cVu_.getColumnIndexOrThrow("title"));
            this.b.setSongName(string);
            this.b.setSongFilePath(cVu_.getString(cVu_.getColumnIndexOrThrow("_data")));
            this.b.setSourceId(cVu_.getLong(cVu_.getColumnIndex("_id")));
            this.b.setSongSingerName(cVu_.getString(cVu_.getColumnIndexOrThrow("artist")));
            this.b.setSongSize(cVu_.getLong(cVu_.getColumnIndexOrThrow("_size")));
            this.b.setAlbumName(cVu_.getString(cVu_.getColumnIndexOrThrow(MusicSong.SORT_TYPE_ALBUM)));
            String string2 = cVu_.getString(cVu_.getColumnIndexOrThrow("_display_name"));
            this.b.setFileName(string2);
            LogUtil.a("LocalMusicHandler", "set music songName = ", string, "; fileName = ", string2);
            d(this.b);
        }
        cVu_.close();
        return this.c;
    }

    private static Cursor cVu_(ContentResolver contentResolver) {
        if (contentResolver == null) {
            return null;
        }
        Uri uri = e;
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        try {
            return contentResolver.query(uri, d, "(is_music=? OR is_music is null)", (String[]) arrayList.toArray(new String[arrayList.size()]), "is_music");
        } catch (SQLiteException unused) {
            LogUtil.b("LocalMusicHandler", "getLocalMusicList SQLiteException");
            return null;
        }
    }

    public boolean b(String str) {
        LogUtil.a("LocalMusicHandler", "isSupportedMusic, filePath:", str);
        String a2 = a(str, ".");
        if (!TextUtils.isEmpty(a2)) {
            a2 = a2.toLowerCase(Locale.ENGLISH);
        }
        if (a2 == null) {
            return false;
        }
        LogUtil.a("LocalMusicHandler", "isSupportedMusic:", a2);
        return this.f.contains(a2);
    }

    public boolean e(String str) {
        String c = c(str, "\\.");
        if (!TextUtils.isEmpty(c)) {
            c = c.toLowerCase(Locale.ENGLISH);
        }
        if (c == null) {
            return false;
        }
        String trim = c.replaceAll("\\(.*?\\)", "").trim();
        ReleaseLogUtil.e("DEVMGR_LocalMusicHandler", "isRightSong:", trim);
        return f15611a.contains(trim);
    }

    private String a(String str, String str2) {
        if (str == null || str2 == null) {
            LogUtil.a("LocalMusicHandler", "getFileSuffix filepath null!");
            return null;
        }
        String e2 = e(FileUtils.getFile(str));
        if (e2 != null) {
            int lastIndexOf = e2.lastIndexOf(str2) + 1;
            if (lastIndexOf < 0 || lastIndexOf >= e2.length()) {
                LogUtil.a("LocalMusicHandler", "getFileSuffix suffix invalid!");
            } else {
                return e2.substring(lastIndexOf);
            }
        }
        return null;
    }

    private String c(String str, String str2) {
        if (str == null || str2 == null) {
            ReleaseLogUtil.e("DEVMGR_LocalMusicHandler", "getFileSuffix filepath null!");
            return null;
        }
        String e2 = e(FileUtils.getFile(str));
        if (e2 == null) {
            return null;
        }
        e2.split(str2);
        String[] split = e2.split(str2);
        if (split.length < 2) {
            ReleaseLogUtil.e("DEVMGR_LocalMusicHandler", "canonicalPath is less 2");
            return null;
        }
        return split[split.length - 2];
    }

    public String e(File file) {
        if (file == null) {
            LogUtil.a("LocalMusicHandler", "getCanonicalPath filepath null!");
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.a("LocalMusicHandler", "getCanonicalPath suffix invalid,error:", e2.getMessage());
            return null;
        }
    }

    private void d(MusicSong musicSong) {
        String[] split;
        LogUtil.a("LocalMusicHandler", "filterLocalMusicToMyList enter.");
        if (musicSong == null || musicSong.getSongSize() <= 0) {
            return;
        }
        String fileName = musicSong.getFileName();
        if (e(fileName)) {
            ReleaseLogUtil.e("DEVMGR_LocalMusicHandler", "fileName is isDoubleSuffixInvalid");
            return;
        }
        if (b(fileName) && this.i.get(fileName) == null && musicSong.getSongName() != null) {
            if (musicSong.getSongName().contains(Constants.LINK) && (split = musicSong.getSongName().split(Constants.LINK)) != null && split.length > 1) {
                musicSong.setSongSingerName(split[0].replace(" ", ""));
                musicSong.setSongName(split[1].replace(" ", ""));
            }
            String a2 = a(fileName, ".");
            if (!TextUtils.isEmpty(a2)) {
                a2 = a2.toLowerCase(Locale.ENGLISH);
            }
            if (a2 == null) {
                return;
            }
            musicSong.setSuffix(a2);
            if (!new File(musicSong.getSongFilePath()).exists()) {
                LogUtil.h("LocalMusicHandler", musicSong.getSongFilePath(), " is not exists");
            } else {
                this.c.add(musicSong);
                this.i.put(fileName, musicSong);
            }
        }
        LogUtil.a("LocalMusicHandler", "filterLocalMusicToMyList size:", Integer.valueOf(this.c.size()));
    }
}
