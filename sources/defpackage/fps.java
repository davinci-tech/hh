package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.suggestion.ui.fitness.helper.MediaPlayCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

/* loaded from: classes4.dex */
public class fps {
    private static MediaPlayer b;
    private static ArrayList<Integer> c;
    private static MediaPlayCallBack d;

    public static void b(Context context, ArrayList<Integer> arrayList) {
        c();
        if (context == null) {
            LogUtil.h("Suggestion_VoicePlayHelper", "startMedia context is null");
            return;
        }
        String str = context.getExternalFilesDir("Sound") + File.separator + "sug_sound.mp3";
        try {
            e(context, arrayList, str);
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                b = mediaPlayer;
                mediaPlayer.setDataSource(str);
                b.prepare();
                b.start();
                b.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: fps.2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer2) {
                        if (mediaPlayer2 != null) {
                            mediaPlayer2.stop();
                            mediaPlayer2.release();
                        }
                        MediaPlayer unused = fps.b = null;
                    }
                });
            } catch (IOException e) {
                LogUtil.b("Suggestion_VoicePlayHelper", "startMedia ProcessFused()", LogAnonymous.b((Throwable) e));
                MediaPlayer mediaPlayer2 = b;
                if (mediaPlayer2 != null) {
                    mediaPlayer2.release();
                    b = null;
                }
            }
        } catch (IOException e2) {
            LogUtil.b("Suggestion_VoicePlayHelper", "startMedia ProcessFused()", LogAnonymous.b((Throwable) e2));
        }
    }

    private static void e(Context context, ArrayList<Integer> arrayList, String str) throws IOException {
        SequenceInputStream sequenceInputStream;
        String canonicalPath;
        if (context == null || koq.b(arrayList)) {
            LogUtil.h("Suggestion_VoicePlayHelper", "mergeSoundFile context or soundIdList is null ");
            return;
        }
        File file = new File(CommonUtil.d(str));
        FileOutputStream fileOutputStream = null;
        try {
            try {
                sequenceInputStream = new SequenceInputStream(d(context, arrayList, str, file).elements());
                try {
                    canonicalPath = file.getCanonicalPath();
                } catch (IOException e) {
                    e = e;
                    LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(sequenceInputStream);
                    return;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(sequenceInputStream);
                    return;
                } catch (IllegalStateException e3) {
                    e = e3;
                    LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(sequenceInputStream);
                    return;
                } catch (SecurityException e4) {
                    e = e4;
                    LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
                    FileUtils.d(fileOutputStream);
                    FileUtils.d(sequenceInputStream);
                    return;
                }
            } catch (Throwable th) {
                th = th;
                FileUtils.d((Closeable) null);
                FileUtils.d((Closeable) null);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            sequenceInputStream = null;
            LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
            FileUtils.d(fileOutputStream);
            FileUtils.d(sequenceInputStream);
            return;
        } catch (IllegalArgumentException e6) {
            e = e6;
            sequenceInputStream = null;
            LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
            FileUtils.d(fileOutputStream);
            FileUtils.d(sequenceInputStream);
            return;
        } catch (IllegalStateException e7) {
            e = e7;
            sequenceInputStream = null;
            LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
            FileUtils.d(fileOutputStream);
            FileUtils.d(sequenceInputStream);
            return;
        } catch (SecurityException e8) {
            e = e8;
            sequenceInputStream = null;
            LogUtil.b("Suggestion_VoicePlayHelper", "ProcessFused()", LogAnonymous.b(e));
            FileUtils.d(fileOutputStream);
            FileUtils.d(sequenceInputStream);
            return;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d((Closeable) null);
            FileUtils.d((Closeable) null);
            throw th;
        }
        if (canonicalPath != null && canonicalPath.equals(CommonUtil.d(canonicalPath))) {
            fileOutputStream = org.apache.commons.io.FileUtils.openOutputStream(file);
            byte[] bArr = new byte[1024];
            while (sequenceInputStream.read(bArr) != -1) {
                fileOutputStream.write(bArr);
            }
            FileUtils.d(fileOutputStream);
            FileUtils.d(sequenceInputStream);
            return;
        }
        LogUtil.h("Suggestion_VoicePlayHelper", "mergeSoundFile, file path not valid");
        FileUtils.d((Closeable) null);
        FileUtils.d(sequenceInputStream);
    }

    private static Vector<InputStream> d(Context context, ArrayList<Integer> arrayList, String str, File file) {
        if (file.exists() && !new File(str).delete()) {
            LogUtil.b("Suggestion_VoicePlayHelper", "file can't delete");
        }
        Vector<InputStream> vector = new Vector<>();
        try {
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                vector.add(new GZIPInputStream(context.getResources().openRawResource(it.next().intValue())));
            }
        } catch (Resources.NotFoundException | IOException e) {
            LogUtil.b("Suggestion_VoicePlayHelper", "mergeSoundFile() exception = ", e.getMessage());
        }
        return vector;
    }

    private static void c() {
        MediaPlayer mediaPlayer = b;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        b.stop();
        b = null;
        if (c != null) {
            MediaPlayCallBack mediaPlayCallBack = d;
            if (mediaPlayCallBack != null) {
                mediaPlayCallBack.mediaPlayCallBack(1, 2, -1);
                d = null;
            }
            c = null;
        }
        MediaPlayCallBack mediaPlayCallBack2 = d;
        if (mediaPlayCallBack2 != null) {
            mediaPlayCallBack2.mediaPlayCallBack(0, 2, -1);
        }
    }
}
