package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.core.view.PointerIconCompat;
import com.apprichtap.haptic.player.IHapticEffectPerformer;
import com.apprichtap.haptic.player.IHapticPlayer;
import com.apprichtap.haptic.player.PlayerEventCallback;
import com.apprichtap.haptic.sync.SyncCallback;
import defpackage.nc;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes8.dex */
public class nd implements IHapticPlayer {

    /* renamed from: a, reason: collision with root package name */
    private final IHapticEffectPerformer f15260a;
    private final nf b;
    private final d c;
    private final HandlerThread d;
    private final ExecutorService e = Executors.newFixedThreadPool(1);

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void updateHapticParameter(int i, int i2, int i3) {
        if (i < 0 || 511 < i) {
            nc.b.a("Customizable-Player", "updateHapticParameter, ignore invalid intensity:" + i);
        } else {
            this.b.f15281a = i;
        }
        if (-100 > i2 || 100 < i2) {
            nc.b.a("Customizable-Player", "updateHapticParameter, ignore invalid freq:" + i2);
        } else {
            this.b.d = i2;
        }
        if (i3 >= 0) {
            this.b.g = i3;
            return;
        }
        nc.b.a("Customizable-Player", "updateHapticParameter, ignore invalid interval:" + i3);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void unregisterPlayerEventCallback() {
        this.b.u = null;
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void stop() {
        if (6 == this.b.d() || 7 == this.b.d() || 9 == this.b.d()) {
            d dVar = this.c;
            dVar.bs_(dVar.obtainMessage(1003), 0, true);
            this.b.c();
            this.b.e(8, 0);
            return;
        }
        nc.b.c("Customizable-Player", "call stop() in invalid status:" + this.b.d() + ", do nothing!");
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void start() {
        if (5 != this.b.d() && 7 != this.b.d() && 9 != this.b.d()) {
            nc.b.c("Customizable-Player", "call start() in invalid status:" + this.b.d() + ", do nothing!");
            return;
        }
        nc.b.e("Customizable-Player", "  start() in, mCurrentHePausePosition:" + this.b.j);
        if (9 == this.b.d()) {
            nc.b.e("Customizable-Player", "  start() return, already COMPLETED, start from 0");
            this.b.j = 0;
        }
        d dVar = this.c;
        dVar.bs_(dVar.obtainMessage(1005), 0, true);
        this.b.e(6, 0);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void setSwitching(boolean z) {
        this.b.m = z;
        this.f15260a.swapVibrationIndex(z);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void setSpeed(float f) {
        if (5 != this.b.d() && 7 != this.b.d() && 6 != this.b.d() && 9 != this.b.d()) {
            nc.b.b("Customizable-Player", "failed to setSpeedMultiple, status:" + this.b.d());
            return;
        }
        if (f == this.b.n) {
            return;
        }
        int currentPosition = getCurrentPosition();
        int e2 = my.e(this.b.l);
        double d2 = (currentPosition * 1.0d) / e2;
        nc.b.e("Customizable-Player", "setSpeedMultiple, positionOfOriginal:" + currentPosition + ",durationOfOriginal:" + e2 + ",progress：" + d2);
        int d3 = this.b.d();
        if (6 == d3) {
            pause();
        }
        nf nfVar = this.b;
        nfVar.n = f;
        nfVar.c = my.e(nfVar.l, f);
        nc.d("speed_up_" + f, this.b.c);
        nf nfVar2 = this.b;
        nfVar2.f = my.c(nfVar2.c);
        int e3 = my.e(this.b.c);
        this.b.j = (int) (d2 * e3);
        nc.b.e("Customizable-Player", "setSpeedUpMultiple, speedUpDuration:" + e3 + ",mStartPosition:" + this.b.j);
        if (6 == d3) {
            start();
        }
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void setLooping(boolean z) {
        nf nfVar;
        int i;
        if (z) {
            nfVar = this.b;
            i = Integer.MAX_VALUE;
        } else {
            nfVar = this.b;
            i = 1;
        }
        nfVar.e = i;
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void setDataSource(String str, int i, int i2, SyncCallback syncCallback) {
        setDataSource(str, i, i2, 0, 0, syncCallback);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0047, code lost:
    
        if (r6 < (-100)) goto L16;
     */
    @Override // com.apprichtap.haptic.player.IHapticPlayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setDataSource(java.lang.String r4, int r5, int r6, int r7, int r8, com.apprichtap.haptic.sync.SyncCallback r9) {
        /*
            r3 = this;
            nf r0 = r3.b
            int r0 = r0.d()
            java.lang.String r1 = "Customizable-Player"
            if (r0 == 0) goto L27
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "setDataSource in invalid status:"
            r4.<init>(r5)
            nf r5 = r3.b
            int r5 = r5.d()
            r4.append(r5)
            java.lang.String r5 = ",do nothing!"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            nc.b.c(r1, r4)
            return
        L27:
            nf r0 = r3.b
            r0.c()
            nf r0 = r3.b
            r0.c = r4
            r0.f15281a = r5
            r0.d = r6
            r4 = 0
            r2 = 511(0x1ff, float:7.16E-43)
            if (r5 <= r2) goto L3c
            r0.f15281a = r2
            goto L40
        L3c:
            if (r5 >= 0) goto L40
            r0.f15281a = r4
        L40:
            r5 = 100
            if (r6 <= r5) goto L45
            goto L49
        L45:
            r5 = -100
            if (r6 >= r5) goto L4b
        L49:
            r0.d = r5
        L4b:
            r0.i = r9
            if (r7 < 0) goto L53
            int r5 = r7 + 1
            r0.e = r5
        L53:
            r5 = -1
            if (r5 != r7) goto L5b
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0.e = r5
        L5b:
            if (r8 < 0) goto L5f
            r0.g = r8
        L5f:
            java.lang.String r5 = "will change to initialized!"
            nc.b.e(r1, r5)
            nf r5 = r3.b
            r6 = 3
            r5.e(r6, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nd.setDataSource(java.lang.String, int, int, int, int, com.apprichtap.haptic.sync.SyncCallback):void");
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void setDataSource(File file, int i, int i2, SyncCallback syncCallback) {
        setDataSource(nc.e(file), i, i2, syncCallback);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void seekTo(int i) {
        nc.b.e("Customizable-Player", "  seekTo() in,  to position:" + i + ",speed：" + this.b.n);
        if (!nf.e(this.b.f)) {
            nc.b.b("Customizable-Player", "  seekTo() return - HE invalid or prepare() not be called.");
            return;
        }
        nf nfVar = this.b;
        int i2 = (int) (i / nfVar.n);
        if (i2 < 0 || i2 > nfVar.f.getDuration()) {
            nc.b.e("Customizable-Player", "  seekTo() return, position invalid, position:" + i2);
        } else {
            d dVar = this.c;
            dVar.bs_(dVar.obtainMessage(1003), 0, true);
            d dVar2 = this.c;
            dVar2.bs_(dVar2.obtainMessage(1008, i2, i), 0, false);
        }
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void reset() {
        if (isPlaying()) {
            d dVar = this.c;
            dVar.bs_(dVar.obtainMessage(1003), 0, true);
        }
        this.b.c();
        this.b.e(0, 0);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void release() {
        nc.b.a("Customizable-Player", " released!");
        if (isPlaying()) {
            d dVar = this.c;
            dVar.bs_(dVar.obtainMessage(1003), 0, true);
        }
        this.b.c();
        this.b.e(1, 0);
        HandlerThread handlerThread = this.d;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        ExecutorService executorService = this.e;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void registerPlayerEventCallback(PlayerEventCallback playerEventCallback) {
        this.b.u = playerEventCallback;
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void prepare() {
        if (3 != this.b.d() && 8 != this.b.d()) {
            nc.b.c("Customizable-Player", "call prepare() in invalid status:" + this.b.d() + ", do nothing!");
            return;
        }
        if (1 == my.d(this.b.c)) {
            nf nfVar = this.b;
            nfVar.c = my.b(nfVar.c);
        }
        nf nfVar2 = this.b;
        String str = nfVar2.c;
        if (str != null && nfVar2.i != null) {
            int c2 = my.c(str, 2);
            int duration = this.b.i.getDuration();
            nc.b.e("Customizable-Player", "prepare() heDuration:" + c2 + ",mediaDuration:" + duration);
            if (duration <= 0) {
                nc.b.b("Customizable-Player", "prepare() , SyncCallback getDuration <= 0, invalid value and may not work!");
            }
            nf nfVar3 = this.b;
            nfVar3.c = my.b(nfVar3.c, duration);
        }
        nf nfVar4 = this.b;
        String str2 = nfVar4.c;
        nfVar4.l = str2;
        na c3 = my.c(str2);
        if (!nf.e(c3)) {
            nc.b.b("Customizable-Player", "prepare error, invalid HE");
            this.b.c();
            this.b.e(2, 4097);
            return;
        }
        nf nfVar5 = this.b;
        nfVar5.f = c3;
        nfVar5.b();
        this.f15260a.setSenderIdKey(this.b.k);
        this.f15260a.setGain(255);
        this.b.e(5, 0);
        nc.d("prepared.he", this.b.c);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public void pause() {
        if (6 != this.b.d()) {
            nc.b.e("Customizable-Player", "  pause() return, not STARTED");
            return;
        }
        nc.b.e("Customizable-Player", "  pause() in, mStartPosition:" + this.b.j + "mCurrentPosition:" + this.b.h);
        d dVar = this.c;
        dVar.bs_(dVar.obtainMessage(1003), 0, true);
        d dVar2 = this.c;
        dVar2.bs_(dVar2.obtainMessage(1006), 0, false);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public boolean isPlaying() {
        return 6 == this.b.d();
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public boolean getSwitching() {
        return this.b.m;
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public float getSpeed() {
        return this.b.n;
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public int getDuration() {
        String str = this.b.l;
        if (str == null) {
            return 0;
        }
        return my.c(str, 2);
    }

    @Override // com.apprichtap.haptic.player.IHapticPlayer
    public int getCurrentPosition() {
        nf nfVar = this.b;
        SyncCallback syncCallback = nfVar.i;
        if (syncCallback != null) {
            return syncCallback.getCurrentPosition();
        }
        int d2 = nfVar.d();
        if (d2 == 6) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            return (int) (((elapsedRealtime - r2.b) + r2.j) * this.b.n);
        }
        if (d2 == 7) {
            return (int) (r0.j * this.b.n);
        }
        if (d2 != 9) {
            return 0;
        }
        return getDuration();
    }

    class a implements Handler.Callback {
        int c;

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i;
            int i2;
            int i3;
            d dVar;
            int max;
            Message message2;
            d dVar2;
            Message obtainMessage;
            d dVar3;
            nd ndVar;
            StringBuilder sb;
            int d;
            d dVar4;
            Message message3;
            nd ndVar2;
            nd ndVar3;
            try {
                nc.b.e("Customizable-Player", "msg what:" + message.what + ", arg:" + message.arg1 + ",mStatus:" + nd.this.b.d());
                i = message.what;
                i2 = 1002;
                i3 = 2;
            } catch (Throwable th) {
                nc.b.b("Customizable-Player", "checking it！！！");
                th.printStackTrace();
                return false;
            }
            if (i == 1020) {
                if (6 != nd.this.b.d()) {
                    sb = new StringBuilder("ignore MSG_SYNC_WITH_MEDIA as status:");
                    d = nd.this.b.d();
                    sb.append(d);
                    nc.b.e("Customizable-Player", sb.toString());
                    return false;
                }
                if (nd.this.b.o > nd.this.b.a() - 1) {
                    return false;
                }
                if (nd.this.b.i != null) {
                    int currentPosition = (int) (nd.this.b.i.getCurrentPosition() / nd.this.b.n);
                    nc.b.e("Customizable-Player", " mediaPosition: " + currentPosition + ",lastMediaPosition:" + this.c);
                    int i4 = this.c;
                    if (currentPosition <= i4) {
                        if (currentPosition < i4) {
                            nf nfVar = nd.this.b;
                            int i5 = nfVar.q + 1;
                            nfVar.q = i5;
                            if (i5 < nd.this.b.e) {
                                dVar3 = nd.this.c;
                                ndVar = nd.this;
                                dVar4 = ndVar.c;
                                i2 = 1007;
                            }
                        }
                        dVar2 = nd.this.c;
                        obtainMessage = nd.this.c.obtainMessage(PointerIconCompat.TYPE_GRAB);
                        dVar2.bs_(obtainMessage, i3, true);
                        return false;
                    }
                    int i6 = nd.this.b.f.b.get(nd.this.b.o).d;
                    nc.b.e("Customizable-Player", " mediaPosition: " + currentPosition + " ,patternTime:" + i6);
                    this.c = currentPosition;
                    dVar = nd.this.c;
                    Message obtainMessage2 = nd.this.c.obtainMessage(1002);
                    max = Math.max(i6 - currentPosition, 0);
                    message2 = obtainMessage2;
                    dVar.bs_(message2, max, true);
                    return false;
                }
                return false;
            }
            if (i != 1099) {
                switch (i) {
                    case 1001:
                        if (nd.this.b.o == 0) {
                            nd.this.b.b = SystemClock.elapsedRealtime();
                        }
                        if (nd.this.b.t == null || "".equals(nd.this.b.t)) {
                            max = nd.this.b.f.b.get(nd.this.b.o).d - nd.this.b.r;
                            nd.this.b.r = nd.this.b.f.b.get(nd.this.b.o).d;
                            nd.this.b.s = nd.this.b.f.b.get(nd.this.b.o).a();
                            nd.this.b.p = my.e(nd.this.b.f.b.get(nd.this.b.o), true);
                            if (nd.this.b.i == null) {
                                nc.b.e("Customizable-Player", "will play pattern after waitTime :" + max);
                                dVar = nd.this.c;
                                message2 = nd.this.c.obtainMessage(1002);
                            } else {
                                int i7 = max - 20;
                                nc.b.e("Customizable-Player", "waitToPatternTimeInAdvance:" + i7);
                                dVar = nd.this.c;
                                message2 = nd.this.c.obtainMessage(PointerIconCompat.TYPE_GRAB);
                                max = Math.max(i7, 0);
                            }
                            dVar.bs_(message2, max, true);
                            break;
                        } else {
                            nc.b.e("Customizable-Player", "mRemainderHEString:" + nd.this.b.t);
                            nd.this.b.r = nd.this.b.j;
                            nd.this.b.o = nd.this.b.f.e(nd.this.b.j);
                            nd.this.b.p = my.a(nd.this.b.c, nd.this.b.j);
                            nd.this.b.s = my.c(nd.this.b.p, 2);
                            nc.b.e("Customizable-Player", "MSG_CHECK_NEXT_PATTERN resume paused pattern,  mCurrentPatternIndex:" + nd.this.b.o + ",mCurrentPatternString:" + nd.this.b.p);
                            nd.this.b.t = null;
                            dVar3 = nd.this.c;
                            dVar4 = nd.this.c;
                            break;
                        }
                        break;
                    case 1002:
                        nc.b.e("Customizable-Player", "MSG_PLAY_PATTERN mFreq" + nd.this.b.d + ",mAmplitude:" + nd.this.b.f15281a + "mCurrentPlayingInfo.mCurrentPatternIndex:" + nd.this.b.o);
                        if (6 != nd.this.b.d()) {
                            sb = new StringBuilder("ignore MSG_PLAY_PATTERN as status:");
                            d = nd.this.b.d();
                            sb.append(d);
                            nc.b.e("Customizable-Player", sb.toString());
                            break;
                        } else {
                            nd.this.e.execute(nd.this.new c(nd.this.b.p + "", nd.this.b.f15281a, nd.this.b.d, nd.this.f15260a.getRichTapCoreMajorVersion()));
                            if (nd.this.b.o + 1 <= nd.this.b.a() - 1) {
                                nd.this.b.o++;
                                dVar3 = nd.this.c;
                                ndVar3 = nd.this;
                                message3 = ndVar3.c.obtainMessage(1001);
                                dVar3.bs_(message3, 0, true);
                                break;
                            } else {
                                nf nfVar2 = nd.this.b;
                                int i8 = nfVar2.q + 1;
                                nfVar2.q = i8;
                                if (i8 >= nd.this.b.e) {
                                    dVar2 = nd.this.c;
                                    obtainMessage = nd.this.c.obtainMessage(1099);
                                    ndVar2 = nd.this;
                                } else {
                                    dVar2 = nd.this.c;
                                    obtainMessage = nd.this.c.obtainMessage(1007);
                                    ndVar2 = nd.this;
                                }
                                i3 = ndVar2.b.s;
                                dVar2.bs_(obtainMessage, i3, true);
                                break;
                            }
                        }
                    case 1003:
                        nd.this.f15260a.stop();
                        break;
                    default:
                        switch (i) {
                            case 1005:
                                this.c = 0;
                                nc.b.e("Customizable-Player", "MSG_START mStartPosition:" + nd.this.b.j + ", lastMediaPosition:" + this.c);
                                if (nd.this.b.j >= 0 && nd.this.b.j <= nd.this.b.f.getDuration()) {
                                    nc.b.e("Customizable-Player", "MSG_START will generate remainder and partial pattern!");
                                    nd.this.b.t = my.d(nd.this.b.c, nd.this.b.j);
                                    if (nd.this.b.t == null) {
                                        nc.b.e("Customizable-Player", "null == mCurrentPlayingInfo.mRemainderHEString");
                                        nf nfVar3 = nd.this.b;
                                        int i9 = nfVar3.q + 1;
                                        nfVar3.q = i9;
                                        if (i9 >= nd.this.b.e) {
                                            nc.b.e("Customizable-Player", "last loop finished!");
                                            dVar3 = nd.this.c;
                                            i2 = 1099;
                                            dVar4 = nd.this.c;
                                            break;
                                        } else {
                                            nd.this.b.e(6, 0);
                                            dVar3 = nd.this.c;
                                            ndVar = nd.this;
                                            dVar4 = ndVar.c;
                                            i2 = 1007;
                                            break;
                                        }
                                    }
                                }
                                nd.this.b.b = SystemClock.elapsedRealtime();
                                nd.this.b.e(6, 0);
                                dVar3 = nd.this.c;
                                ndVar3 = nd.this;
                                message3 = ndVar3.c.obtainMessage(1001);
                                dVar3.bs_(message3, 0, true);
                                break;
                            case 1006:
                                if (nd.this.b.i != null) {
                                    try {
                                        nd.this.b.j = (int) (nd.this.b.i.getCurrentPosition() / nd.this.b.n);
                                    } catch (Throwable th2) {
                                        th2.printStackTrace();
                                        nd.this.b.j = 0;
                                    }
                                    nc.b.e("Customizable-Player", "MSG_PAUSE, mStartPosition:" + nd.this.b.j);
                                } else {
                                    long elapsedRealtime = SystemClock.elapsedRealtime() - nd.this.b.b;
                                    if (elapsedRealtime < 0) {
                                        nc.b.b("Customizable-Player", "pause delta < 0");
                                        nd.this.b.j = 0;
                                        nd.this.b.e(2, 0);
                                        break;
                                    } else {
                                        nd.this.b.j = (int) (r5.j + elapsedRealtime);
                                        nc.b.e("Customizable-Player", "MSG_PAUSE mStartPosition:" + nd.this.b.j);
                                    }
                                }
                                nd.this.b.e(7, 0);
                                break;
                            case 1007:
                                nd.this.b.j = 0;
                                nd.this.b.o = 0;
                                nd.this.b.r = 0;
                                this.c = 0;
                                dVar2 = nd.this.c;
                                obtainMessage = nd.this.c.obtainMessage(1001);
                                i3 = nd.this.b.g;
                                dVar2.bs_(obtainMessage, i3, true);
                                break;
                            case 1008:
                                nd.this.b.j = message.arg1;
                                this.c = 0;
                                if (6 == nd.this.b.d()) {
                                    nd.this.c.bs_(nd.this.c.obtainMessage(1005), 0, true);
                                } else {
                                    nc.b.a("Customizable-Player", "MSG_SEEK status:" + nd.this.b.d());
                                }
                                if (nd.this.b.u != null) {
                                    nd.this.b.u.onSeekCompleted(message.arg2);
                                    break;
                                }
                                break;
                        }
                }
                return false;
            }
            nd.this.b.j = 0;
            nd.this.b.o = 0;
            nd.this.b.q = 0;
            nd.this.b.r = 0;
            this.c = 0;
            nd.this.b.e(9, 0);
            return false;
            message3 = dVar4.obtainMessage(i2);
            dVar3.bs_(message3, 0, true);
            return false;
        }

        private a() {
            this.c = 0;
        }
    }

    static class d extends Handler {
        public boolean bs_(Message message, int i, boolean z) {
            if (z) {
                a();
            }
            return sendMessageDelayed(message, i);
        }

        public void a() {
            removeMessages(1005);
            removeMessages(1006);
            removeMessages(1008);
            removeMessages(1099);
            removeMessages(1007);
            removeMessages(1003);
            removeMessages(1001);
            removeMessages(PointerIconCompat.TYPE_GRAB);
            removeMessages(1002);
        }

        d(Looper looper, Handler.Callback callback) {
            super(looper, callback);
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        int f15262a;
        int b;
        int c;
        String e;

        @Override // java.lang.Runnable
        public void run() {
            nd.this.f15260a.start(my.e(this.e, this.f15262a, this.b, this.c));
        }

        c(String str, int i, int i2, int i3) {
            this.e = str;
            this.f15262a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public nd(IHapticEffectPerformer iHapticEffectPerformer) {
        nf nfVar = new nf();
        this.b = nfVar;
        nc.b.a("Customizable-Player", "initialize!");
        if (iHapticEffectPerformer == null) {
            nc.b.b("Customizable-Player", "CustomizableHapticPlayer() null == performer");
            nfVar.e(2, 0);
        }
        this.f15260a = iHapticEffectPerformer;
        HandlerThread handlerThread = new HandlerThread("Customizable-Player");
        this.d = handlerThread;
        handlerThread.start();
        this.c = new d(handlerThread.getLooper(), new a());
    }
}
