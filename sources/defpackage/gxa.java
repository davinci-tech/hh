package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes4.dex */
public class gxa implements IVoiceEngine {

    /* renamed from: a, reason: collision with root package name */
    private static AudioManager f12985a;
    private Context e;
    private MediaPlayer k;
    private boolean f = false;
    private HashMap<Integer, Integer> j = new HashMap<>(16);
    private HandlerThread i = null;
    private Handler h = null;
    private ExtendHandler c = null;
    private Handler g = new Handler();
    private MediaPlayer.OnCompletionListener b = null;
    private boolean m = true;
    private AudioManager.OnAudioFocusChangeListener d = new AudioManager.OnAudioFocusChangeListener() { // from class: gxa.5
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -3) {
                LogUtil.a("Track_MediaPlayerVoiceEngine", "AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                return;
            }
            if (i == -2) {
                LogUtil.a("Track_MediaPlayerVoiceEngine", "AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT");
                return;
            }
            if (i == -1) {
                LogUtil.a("Track_MediaPlayerVoiceEngine", "AudioFocus: received AUDIOFOCUS_LOSS");
                gxa.this.a();
            } else {
                if (i != 1) {
                    return;
                }
                LogUtil.a("Track_MediaPlayerVoiceEngine", "AudioFocus: received AUDIOFOCUS_GAIN");
            }
        }
    };

    public gxa(Context context, Map<Integer, Integer> map) {
        LogUtil.a("Track_MediaPlayerVoiceEngine", "MediaPlayerVoiceEngine", " START");
        if (context == null) {
            throw new RuntimeException("MediaPlayerVoiceEngine invalid params in constructor");
        }
        this.e = context;
        c(map);
        d();
    }

    public void b() {
        LogUtil.a("Track_MediaPlayerVoiceEngine", "stopVoice");
        if (this.k != null) {
            try {
                LogUtil.a("Track_MediaPlayerVoiceEngine", "stopVoice() stop and release mediaplayer");
                this.k.stop();
                this.k.release();
                this.k = null;
            } catch (IllegalStateException unused) {
                LogUtil.b("Track_MediaPlayerVoiceEngine", "IllegalStateException during onDestroy");
            }
        }
        h();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void stopVoice() {
        if (this.c != null) {
            Message message = new Message();
            message.what = 102;
            this.c.sendMessage(message);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void playVoice(Object obj, boolean z) {
        boolean z2 = obj instanceof List;
        if (!z2 && !(obj instanceof String)) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "music", " playVoice() content is not List<Integer>!");
            return;
        }
        if (e() == 0) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "MyThread run() request is AudioManager.AUDIOFOCUS_REQUEST_FAILED");
            return;
        }
        h();
        if (!this.f) {
            LogUtil.a("Track_MediaPlayerVoiceEngine", "music", " onPlaySpeak() isFinishedLoad is false!");
            if (z2) {
                d(obj, 1000L);
                return;
            } else {
                b((String) obj);
                return;
            }
        }
        if (z) {
            if (z2) {
                d(obj, mxb.a().e() ? 200L : 0L);
                return;
            } else {
                b((String) obj);
                return;
            }
        }
        if (z2) {
            b(obj);
        } else {
            b((String) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Object obj) {
        if (!this.f) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "playVoice() isFinishedLoad is false!");
        } else {
            b(new Runnable() { // from class: gxa.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.c("Track_MediaPlayerVoiceEngine", "MyThread run() enter : ", Integer.valueOf(hashCode()));
                    if (gxa.this.e() == 0) {
                        LogUtil.b("Track_MediaPlayerVoiceEngine", "MyThread run() request is AudioManager.AUDIOFOCUS_REQUEST_FAILED");
                        return;
                    }
                    LogUtil.a("Track_MediaPlayerVoiceEngine", "MyThread run() speakTexts : ", obj);
                    StringBuffer stringBuffer = new StringBuffer(16);
                    stringBuffer.append(gxa.this.e.getExternalFilesDir("Sound")).append(File.separator).append("sound.mp3");
                    String stringBuffer2 = stringBuffer.toString();
                    try {
                        gxa.this.d(obj);
                        if (new File(stringBuffer2).exists()) {
                            gxa.this.b(stringBuffer2);
                            LogUtil.c("Track_MediaPlayerVoiceEngine", "MyThread run() end : ", Integer.valueOf(hashCode()));
                        }
                    } catch (IOException unused) {
                        LogUtil.b("Track_MediaPlayerVoiceEngine", "playVoice PorcessFused()");
                    }
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void destroy() {
        stopVoice();
        i();
        LogUtil.a("Track_MediaPlayerVoiceEngine", "destroy()");
    }

    private void c(final Map<Integer, Integer> map) {
        if (map == null || map.size() == 0) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "music", " initSoundPool() voiceResource is null!");
            return;
        }
        Object systemService = this.e.getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            f12985a = (AudioManager) systemService;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: gxa.3
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : map.entrySet()) {
                    gxa.this.j.put((Integer) entry.getKey(), (Integer) entry.getValue());
                }
                gxa.this.f = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Object obj, final long j) {
        this.g.postDelayed(new Runnable() { // from class: gxa.4
            @Override // java.lang.Runnable
            public void run() {
                if (gxa.this.f) {
                    gxa.this.b(obj);
                } else {
                    gxa.this.d(obj, j);
                }
            }
        }, j);
    }

    private void h() {
        ExtendHandler extendHandler = this.c;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e() {
        int i = b(this.e) == 0 ? 3 : 2;
        AudioManager audioManager = f12985a;
        if (audioManager == null) {
            ReleaseLogUtil.c("Track_MediaPlayerVoiceEngine", "mAudioManager == null");
            return 0;
        }
        int requestAudioFocus = audioManager.requestAudioFocus(this.d, 3, i);
        ReleaseLogUtil.e("Track_MediaPlayerVoiceEngine", "musicFocus = ", Integer.valueOf(requestAudioFocus));
        return requestAudioFocus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c() {
        if (f12985a == null || gxd.a().c() || !this.m) {
            return 0;
        }
        return f12985a.abandonAudioFocus(this.d);
    }

    private int b(Context context) {
        if (context == null) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "acquireSportMusicControlType context is null");
            return 0;
        }
        return CommonUtil.e(SharedPreferenceManager.b(context, Integer.toString(20002), "sport_music_control_type"), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.g.post(new Runnable() { // from class: gxa.1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    private void d() {
        LogUtil.a("Track_MediaPlayerVoiceEngine", "startHandlerThread() enter");
        if (this.c == null) {
            this.c = HandlerCenter.yt_(new Handler.Callback() { // from class: gxb
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return gxa.this.aVn_(message);
                }
            }, "SoundVoiceHandlerThread");
        }
    }

    /* synthetic */ boolean aVn_(Message message) {
        LogUtil.a("Track_MediaPlayerVoiceEngine", "SoundHandler()  handleMessage");
        int i = message.what;
        if (i != 101) {
            if (i != 102) {
                return false;
            }
            b();
            return true;
        }
        if (!(message.obj instanceof String)) {
            return true;
        }
        e((String) message.obj);
        return true;
    }

    private void i() {
        LogUtil.a("Track_MediaPlayerVoiceEngine", "stopHandlerThread() enter");
        ExtendHandler extendHandler = this.c;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.c.quit(false);
            this.c = null;
        }
    }

    private void b(Runnable runnable) {
        ExtendHandler extendHandler = this.c;
        if (extendHandler != null) {
            extendHandler.postTask(runnable);
        } else {
            LogUtil.a("Track_MediaPlayerVoiceEngine", "postRunnableToHandlerThread() mHandler is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.c != null) {
            Message message = new Message();
            message.what = 101;
            message.obj = str;
            this.c.sendMessage(message);
        }
    }

    private void e(String str) {
        if (this.k == null) {
            this.k = new MediaPlayer();
        }
        this.k.reset();
        LogUtil.a("Track_MediaPlayerVoiceEngine", "playSound");
        try {
            String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
            if (this.e != null && normalize.contains("assert")) {
                Object systemService = this.e.getSystemService(PresenterUtils.AUDIO);
                if (systemService instanceof AudioManager) {
                    LogUtil.a("Track_MediaPlayerVoiceEngine", "playSound() voice volume", Integer.valueOf(((AudioManager) systemService).getStreamVolume(3)));
                    AssetFileDescriptor openFd = this.e.getAssets().openFd(normalize.replace("assert", ""));
                    this.k.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    this.k.prepare();
                    this.k.start();
                    this.k.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: gxa.10
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            LogUtil.a("Track_MediaPlayerVoiceEngine", "playSound release");
                            gxa.this.k.release();
                            gxa.this.k = null;
                            if (gxa.this.b != null) {
                                gxa.this.b.onCompletion(mediaPlayer);
                            }
                            gxa.this.c();
                        }
                    });
                }
                LogUtil.a("Track_MediaPlayerVoiceEngine", "playSound ", "object invalid type");
                return;
            }
            this.k.setDataSource(normalize);
            this.k.prepare();
            this.k.start();
            this.k.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: gxa.10
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    LogUtil.a("Track_MediaPlayerVoiceEngine", "playSound release");
                    gxa.this.k.release();
                    gxa.this.k = null;
                    if (gxa.this.b != null) {
                        gxa.this.b.onCompletion(mediaPlayer);
                    }
                    gxa.this.c();
                }
            });
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException unused) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "playSound PorcessFused()");
            MediaPlayer mediaPlayer = this.k;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                this.k = null;
            }
            c();
            gut.aUn_(BaseApplication.getContext(), new Intent("ACTION_RESET_MEDIA_IDLE"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(this.e.getExternalFilesDir("Sound")).append(File.separator).append("sound.mp3");
        String stringBuffer2 = stringBuffer.toString();
        File file = new File(stringBuffer2);
        if (file.exists() && !new File(stringBuffer2).delete()) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", Integer.valueOf(R.string._2130850269_res_0x7f0231dd));
        }
        Vector<InputStream> vector = new Vector<>();
        boolean z = false;
        try {
        } catch (Resources.NotFoundException e) {
            e = e;
            LogUtil.b("Track_MediaPlayerVoiceEngine", e.getMessage());
        } catch (FileNotFoundException unused) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "file not found exception");
        } catch (IOException e2) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "mergeSoundFile() IOException: ", LogAnonymous.b((Throwable) e2));
        } catch (IndexOutOfBoundsException e3) {
            e = e3;
            LogUtil.b("Track_MediaPlayerVoiceEngine", e.getMessage());
        } catch (SecurityException e4) {
            e = e4;
            LogUtil.b("Track_MediaPlayerVoiceEngine", e.getMessage());
        }
        if (!(obj instanceof List)) {
            LogUtil.b("Track_MediaPlayerVoiceEngine", "preFactories  not instanceof  List");
            return;
        }
        z = ((List) obj).get(0) instanceof Integer;
        if (z) {
            for (Integer num : (List) obj) {
                if (this.j.get(num) == null) {
                    vector.add(this.e.getResources().openRawResource(num.intValue()));
                } else {
                    vector.add(this.e.getResources().openRawResource(this.j.get(num).intValue()));
                }
            }
        } else {
            vector = c((List<String>) obj);
        }
        c(file, vector, z);
    }

    private Vector c(List<String> list) throws IOException {
        Vector vector = new Vector();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("assert")) {
                    vector.add(this.e.getAssets().open(str.replace("assert", "")));
                } else {
                    vector.add(new FileInputStream(str));
                }
            }
        }
        return vector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c9 A[LOOP:4: B:54:0x00c3->B:56:0x00c9, LOOP_END] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.io.Closeable, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.Closeable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.io.File r8, java.util.Vector<java.io.InputStream> r9, boolean r10) {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.util.Enumeration r2 = r9.elements()
            java.lang.String r3 = "Track_MediaPlayerVoiceEngine"
            if (r10 == 0) goto L3d
            java.util.Vector r10 = new java.util.Vector
            int r4 = r9.size()
            r10.<init>(r4)
        L15:
            boolean r4 = r2.hasMoreElements()
            if (r4 == 0) goto L39
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch: java.io.IOException -> L2a
            java.lang.Object r5 = r2.nextElement()     // Catch: java.io.IOException -> L2a
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch: java.io.IOException -> L2a
            r4.<init>(r5)     // Catch: java.io.IOException -> L2a
            r10.add(r4)     // Catch: java.io.IOException -> L2a
            goto L15
        L2a:
            r4 = move-exception
            java.lang.String r5 = "extract gzip failed "
            java.lang.String r4 = com.huawei.haf.common.exception.ExceptionUtils.d(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r5, r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r3, r4)
            goto L15
        L39:
            java.util.Enumeration r2 = r10.elements()
        L3d:
            r10 = 0
            r4 = 0
            java.io.SequenceInputStream r5 = new java.io.SequenceInputStream     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            java.io.FileOutputStream r8 = org.apache.commons.io.FileUtils.openOutputStream(r8)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L71 java.lang.Throwable -> L73
        L4c:
            int r4 = r5.read(r2)     // Catch: java.lang.Throwable -> L71 java.lang.Throwable -> L73
            r6 = -1
            if (r4 == r6) goto L57
            r8.write(r2, r10, r4)     // Catch: java.lang.Throwable -> L71 java.lang.Throwable -> L73
            goto L4c
        L57:
            com.huawei.haf.common.os.FileUtils.d(r8)
            com.huawei.haf.common.os.FileUtils.d(r5)
            java.util.Iterator r8 = r9.iterator()
        L61:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto La2
            java.lang.Object r9 = r8.next()
            java.io.InputStream r9 = (java.io.InputStream) r9
            com.huawei.haf.common.os.FileUtils.d(r9)
            goto L61
        L71:
            r10 = move-exception
            goto Lb7
        L73:
            r4 = r8
            goto L77
        L75:
            r10 = move-exception
            goto Lb8
        L77:
            r8 = r4
            r4 = r5
            goto L7e
        L7a:
            r8 = move-exception
            r5 = r4
            goto Lb9
        L7d:
            r8 = r4
        L7e:
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = "writeToFile PorcessFused()"
            r2[r10] = r5     // Catch: java.lang.Throwable -> Lb5
            com.huawei.hwlogsmodel.LogUtil.b(r3, r2)     // Catch: java.lang.Throwable -> Lb5
            com.huawei.haf.common.os.FileUtils.d(r8)
            com.huawei.haf.common.os.FileUtils.d(r4)
            java.util.Iterator r8 = r9.iterator()
        L92:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto La2
            java.lang.Object r9 = r8.next()
            java.io.InputStream r9 = (java.io.InputStream) r9
            com.huawei.haf.common.os.FileUtils.d(r9)
            goto L92
        La2:
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r0
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            java.lang.String r9 = "writeToFile end"
            java.lang.Object[] r8 = new java.lang.Object[]{r9, r8}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r8)
            return
        Lb5:
            r10 = move-exception
            r5 = r4
        Lb7:
            r4 = r8
        Lb8:
            r8 = r10
        Lb9:
            com.huawei.haf.common.os.FileUtils.d(r4)
            com.huawei.haf.common.os.FileUtils.d(r5)
            java.util.Iterator r9 = r9.iterator()
        Lc3:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto Ld3
            java.lang.Object r10 = r9.next()
            java.io.InputStream r10 = (java.io.InputStream) r10
            com.huawei.haf.common.os.FileUtils.d(r10)
            goto Lc3
        Ld3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gxa.c(java.io.File, java.util.Vector, boolean):void");
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void registerVoicePlayCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.b = onCompletionListener;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void unregisterVoicePlayCompletionListener() {
        this.b = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine
    public void setVoiceIntentBufferState(boolean z) {
        this.m = z;
    }
}
