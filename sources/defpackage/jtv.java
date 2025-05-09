package defpackage;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;

/* loaded from: classes.dex */
public class jtv {

    /* renamed from: a, reason: collision with root package name */
    private static jtv f14084a;
    private static final Object c = new Object();
    private static final Object e = new Object();
    private Context b;
    private MediaPlayer l = null;
    private AudioManager d = null;
    private int k = 0;
    private int f = 0;
    private int i = 0;
    private int o = 0;
    private boolean h = false;
    private boolean g = false;
    private Handler j = new Handler(Looper.getMainLooper()) { // from class: jtv.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                jtv.this.g();
                jtv.this.a(2);
            } else {
                LogUtil.h("HwFindPhoneMgr", "mHandler unknown command");
            }
        }
    };

    private jtv(Context context) {
        this.b = context;
    }

    public void a() {
        Handler handler = this.j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.j = null;
        }
        d();
    }

    private static void d() {
        synchronized (c) {
            f14084a = null;
        }
    }

    public static jtv b(Context context) {
        jtv jtvVar;
        synchronized (c) {
            if (f14084a == null) {
                f14084a = new jtv(context);
            }
            jtvVar = f14084a;
        }
        return jtvVar;
    }

    public void a(byte[] bArr) {
        ReleaseLogUtil.e("DEVMGR_HwFindPhoneMgr", "handleFindPhoneOperationReport() enter");
        if (bArr == null) {
            LogUtil.h("HwFindPhoneMgr", "handleFindPhoneOperationReport ,dataInfos is null, return");
            return;
        }
        if (bArr.length < 5) {
            LogUtil.h("HwFindPhoneMgr", "handleFindPhoneOperationReport ,length less than 5, return");
            return;
        }
        if (bArr[1] == 1) {
            byte b = bArr[4];
            if (b == 1) {
                LogUtil.a("HwFindPhoneMgr", "operation = ", Byte.valueOf(b), ",Find phone start");
                e();
            } else if (b == 2) {
                LogUtil.a("HwFindPhoneMgr", "operation = ", Byte.valueOf(b), ",Find phone stop");
                g();
            } else {
                LogUtil.h("HwFindPhoneMgr", "handleFindPhoneOperationReport unknown command");
            }
        }
    }

    private void e() {
        try {
            MediaPlayer mediaPlayer = this.l;
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                LogUtil.h("HwFindPhoneMgr", "startFindPhone isPlaying");
                return;
            }
            synchronized (e) {
                Object systemService = this.b.getSystemService(PresenterUtils.AUDIO);
                if (systemService instanceof AudioManager) {
                    this.d = (AudioManager) systemService;
                }
                AudioManager audioManager = this.d;
                if (audioManager != null) {
                    this.k = audioManager.getStreamMaxVolume(3);
                    this.f = this.d.getStreamVolume(3);
                    this.o = this.d.getStreamMaxVolume(0);
                    this.i = this.d.getStreamVolume(0);
                    this.h = this.d.isSpeakerphoneOn();
                    LogUtil.a("HwFindPhoneMgr", "startFindPhone mCurrentRingVolume:", Integer.valueOf(this.f), ", mMaxRingVolume:", Integer.valueOf(this.k), " mIsSpeakerphoneOn:", Boolean.valueOf(this.h), " mCurrentCallVolume:", Integer.valueOf(this.i), ", mMaxCallVolume:", Integer.valueOf(this.o));
                }
                b();
                Message obtain = Message.obtain();
                obtain.what = 1;
                Handler handler = this.j;
                if (handler != null) {
                    handler.sendMessageDelayed(obtain, 13000L);
                }
            }
        } catch (IllegalStateException unused) {
            LogUtil.b("HwFindPhoneMgr", "startFindPhone IllegalStateException");
            sqo.r("startFindPhone IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("HwFindPhoneMgr", "Stop phone lost alert");
        synchronized (e) {
            Handler handler = this.j;
            if (handler != null) {
                handler.removeMessages(1);
            }
            if (this.g) {
                LogUtil.h("HwFindPhoneMgr", "stopFindPhone mIsStoppingPlayRing is true");
                return;
            }
            this.g = true;
            i();
            this.g = false;
        }
    }

    private void b() {
        LogUtil.a("HwFindPhoneMgr", "startPlayRing");
        AudioManager audioManager = this.d;
        if (audioManager != null) {
            audioManager.requestAudioFocus(null, 3, 4);
            this.d.setMode(3);
            if (CommonUtil.bf()) {
                c();
            } else {
                this.d.setSpeakerphoneOn(true);
                this.d.stopBluetoothSco();
                this.d.setBluetoothScoOn(false);
            }
        }
        try {
            if (this.l == null) {
                String language = Locale.getDefault().getLanguage();
                LogUtil.a("HwFindPhoneMgr", "Language Type", language);
                if (MLAsrConstants.LAN_ZH.equals(language)) {
                    this.l = MediaPlayer.create(this.b, R.raw._2131886228_res_0x7f120094);
                } else {
                    this.l = MediaPlayer.create(this.b, R.raw._2131886229_res_0x7f120095);
                }
            }
            MediaPlayer mediaPlayer = this.l;
            if (mediaPlayer == null) {
                LogUtil.h("HwFindPhoneMgr", "mMediaPlayer is null");
                return;
            }
            mediaPlayer.setAudioStreamType(3);
            this.l.setLooping(true);
            a(this.k, 3);
            a(this.o, 0);
            this.l.start();
            LogUtil.a("HwFindPhoneMgr", "startPlayRing start");
        } catch (IllegalStateException e2) {
            LogUtil.b("HwFindPhoneMgr", "startPlayRing fos IllegalStateException = ", e2.getMessage());
            sqo.r("startPlayRing fos IllegalStateException = " + e2.getMessage());
        } catch (Exception e3) {
            LogUtil.b("HwFindPhoneMgr", "startPlayRing fos Exception ", ExceptionUtils.d(e3));
            sqo.r("startPlayRing fos Exception " + ExceptionUtils.d(e3));
        }
    }

    private void c() {
        LogUtil.a("HwFindPhoneMgr", "setHonorFindPhone enter.");
        if (this.d.isBluetoothScoOn()) {
            this.d.stopBluetoothSco();
            this.d.setBluetoothScoOn(false);
        }
        this.d.setSpeakerphoneOn(true);
    }

    private void i() {
        try {
            if (this.l != null) {
                LogUtil.a("HwFindPhoneMgr", "stopPlayRing mMediaPlayer");
                this.l.stop();
                this.l.reset();
                this.l.release();
                this.l = null;
            }
        } catch (IllegalStateException unused) {
            LogUtil.b("stopPlayRing IllegalStateException", new Object[0]);
            sqo.r("stopPlayRing IllegalStateException");
        }
        AudioManager audioManager = this.d;
        if (audioManager == null) {
            LogUtil.h("HwFindPhoneMgr", "stopPlayRing mAudioManager is null");
            return;
        }
        audioManager.setMode(0);
        this.d.setSpeakerphoneOn(this.h);
        a(this.f, 3);
        a(this.i, 0);
        this.d.abandonAudioFocus(null);
        LogUtil.a("HwFindPhoneMgr", "stopPlayRing end");
    }

    private void a(int i, int i2) {
        AudioManager audioManager = this.d;
        if (audioManager != null) {
            try {
                audioManager.setStreamVolume(i2, i, 0);
                LogUtil.a("HwFindPhoneMgr", "setRingVolume: ", Integer.valueOf(i), ", streamType:", Integer.valueOf(i2));
            } catch (Exception e2) {
                LogUtil.b("HwFindPhoneMgr", "setRingVolume Exception ", ExceptionUtils.d(e2));
                sqo.r("setRingVolume Exception " + ExceptionUtils.d(e2));
            }
        }
    }

    public void a(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(11);
        deviceCommand.setCommandID(2);
        deviceCommand.setNeedAck(false);
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        jsz.b(this.b).sendDeviceData(deviceCommand);
    }
}
