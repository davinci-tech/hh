package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordReceiver;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordService;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.StorageParams;

/* loaded from: classes4.dex */
public class hbk {
    private Context b;
    private TrackRecorder f;
    private String g;
    private CommonSingleCallback<Boolean> h;
    private long j;
    private TrackRecordReceiver m;
    private long n;
    private MediaProjectionManager e = null;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f13064a = false;
    private boolean d = false;
    private Handler i = new Handler(Looper.getMainLooper()) { // from class: hbk.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 15790320) {
                return;
            }
            LogUtil.a("Suggestion_RecordManager", "handleMessage: ", Integer.valueOf(message.what));
            hbk.this.t();
        }
    };
    private hbl o = new hbl();
    private gww k = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));

    public hbk(Context context, CommonSingleCallback commonSingleCallback) {
        this.h = commonSingleCallback;
        this.b = context;
        o();
        m();
    }

    public void d(long j) {
        this.j = j;
    }

    private void o() {
        TrackRecordReceiver trackRecordReceiver = new TrackRecordReceiver(this.b);
        this.m = trackRecordReceiver;
        trackRecordReceiver.a(this.b, new TrackRecordReceiver.TrackRecordListener() { // from class: hbk.5
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordReceiver.TrackRecordListener
            public void onRecordStart() {
                hbk.this.c = false;
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordReceiver.TrackRecordListener
            public void onRecordFinish(long j, boolean z) {
                hbk.this.n = j;
                hbk.this.d = z;
                hbk.this.t();
            }
        });
    }

    private void m() {
        if (this.e != null) {
            return;
        }
        Object systemService = this.b.getSystemService("media_projection");
        if (systemService instanceof MediaProjectionManager) {
            this.e = (MediaProjectionManager) systemService;
        }
    }

    public void aYt_(int i, Intent intent, boolean z) {
        if (this.e == null) {
            return;
        }
        this.g = this.k.n();
        this.o.c(this.f13064a);
        this.n = 0L;
        if (PermissionUtil.c()) {
            aYs_(i, intent, z);
            return;
        }
        MediaProjection mediaProjection = this.e.getMediaProjection(i, intent);
        if (mediaProjection == null) {
            LogUtil.b("Suggestion_RecordManager", "media projection is null");
        } else {
            this.c = false;
            aYr_(mediaProjection, z);
        }
    }

    private void aYs_(int i, Intent intent, boolean z) {
        Intent intent2 = new Intent(this.b, (Class<?>) TrackRecordService.class);
        intent2.putExtra("duration", this.j);
        intent2.putExtra("isShort", this.f13064a);
        intent2.putExtra("mMusicResourcePath", this.g);
        intent2.putExtra("mVideoPath", this.o.b());
        if (this.o.aYE_() != null) {
            intent2.putExtra("mVideoUri", this.o.aYE_().toString());
        }
        intent2.putExtra("resultCode", i);
        intent2.putExtra("isHdVideoType", z);
        intent2.putExtra("data", intent);
        try {
            this.b.startForegroundService(intent2);
        } catch (SecurityException e) {
            LogUtil.h("Suggestion_RecordManager", "startRecordService: startForegroundService error ", LogAnonymous.b((Throwable) e));
        }
    }

    private void aYr_(MediaProjection mediaProjection, boolean z) {
        try {
            TrackRecorder handler = new hbn().a(this.f13064a).c(this.j).aYw_(mediaProjection, this.o.b(), nsn.ag(this.b), z).setAudioFilePath(this.g).setHandler(this.i);
            this.f = handler;
            handler.start();
        } catch (IllegalArgumentException e) {
            LogUtil.b("Suggestion_RecordManager", LogAnonymous.b((Throwable) e));
        }
    }

    public void i() {
        this.c = true;
    }

    public boolean e() {
        TrackRecorder trackRecorder = this.f;
        if (trackRecorder != null) {
            return trackRecorder.isRunSuccess();
        }
        return !this.d;
    }

    public void g() {
        TrackRecorder trackRecorder = this.f;
        if (trackRecorder != null) {
            trackRecorder.stopScreen();
        }
        TrackRecordReceiver trackRecordReceiver = this.m;
        if (trackRecordReceiver != null) {
            if (this.c) {
                trackRecordReceiver.a();
            } else {
                trackRecordReceiver.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        f();
        CommonSingleCallback<Boolean> commonSingleCallback = this.h;
        if (commonSingleCallback != null) {
            commonSingleCallback.callback(Boolean.valueOf(k()));
        }
        if (k()) {
            h();
        } else {
            r();
        }
        this.f = null;
    }

    private boolean k() {
        return e() && this.c;
    }

    public void j() {
        MediaProjectionManager mediaProjectionManager = this.e;
        if (mediaProjectionManager != null) {
            Intent createScreenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent();
            if (createScreenCaptureIntent == null) {
                LogUtil.h("Suggestion_RecordManager", "startRecord: captureIntent is null");
                return;
            }
            Context context = this.b;
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(createScreenCaptureIntent, 101);
                return;
            }
            return;
        }
        LogUtil.b("Suggestion_RecordManager", "Current API level is lower than 21");
    }

    private void f() {
        TrackRecorder trackRecorder = this.f;
        if (trackRecorder != null) {
            if (trackRecorder.isRunSuccess()) {
                return;
            }
            this.c = false;
            return;
        }
        ReleaseLogUtil.b("Suggestion_RecordManager", "checkRecordSuccess: recorder and receiver is null");
    }

    private void r() {
        ReleaseLogUtil.a("Suggestion_RecordManager", "updateGalleryAbnormally start");
        if (this.f != null || this.d) {
            this.o.c();
            Context context = this.b;
            if (context != null) {
                Toast.makeText(context, context.getString(R.string._2130846046_res_0x7f02215e), 0).show();
                return;
            } else {
                ReleaseLogUtil.a("Suggestion_RecordManager", "updateGalleryAbnormally: context is null");
                return;
            }
        }
        ReleaseLogUtil.c("Suggestion_RecordManager", "stopRecordNormally mRecorder is null.");
    }

    public void h() {
        TrackRecorder trackRecorder = this.f;
        if (trackRecorder != null) {
            this.n = trackRecorder.getVideoDuration();
        } else {
            LogUtil.a("Suggestion_RecordManager", "stopRecordNormally mRecorder is null. is record on service");
        }
        if (this.n > 0) {
            if (PermissionUtil.a()) {
                ReleaseLogUtil.b("Suggestion_RecordManager", "saveVideoToAlbumInQ()");
                this.o.a();
                l();
                return;
            } else {
                ReleaseLogUtil.b("Suggestion_RecordManager", "saveVideoToAlbum()");
                n();
                return;
            }
        }
        this.o.c();
        Context context = this.b;
        if (context != null) {
            Toast.makeText(context, context.getString(R.string._2130846046_res_0x7f02215e), 0).show();
        } else {
            ReleaseLogUtil.a("Suggestion_RecordManager", "updateGalleryNormally: context is null");
        }
    }

    private void n() {
        LogUtil.a("Suggestion_RecordManager", "saveVideoToAlbum: ");
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "");
        contentValues.put("duration", Long.valueOf(this.n));
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("_data", this.o.b());
        Context context = this.b;
        if (context == null) {
            LogUtil.h("Suggestion_RecordManager", "saveVideoToAlbum: context is null");
        } else {
            context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
            l();
        }
    }

    private void l() {
        Context context = this.b;
        if (context == null) {
            LogUtil.h("Suggestion_RecordManager", "showSaveSuccessToast: context is null");
            return;
        }
        Toast makeText = Toast.makeText(context, context.getString(R.string._2130846047_res_0x7f02215f), 0);
        makeText.setGravity(80, 0, nsn.c(this.b, 110.0f));
        makeText.show();
    }

    public boolean b() {
        return this.f13064a;
    }

    public void d(boolean z) {
        this.f13064a = z;
    }

    public String a() {
        return this.o.b();
    }

    public Uri aYu_() {
        return this.o.aYE_();
    }

    public void c() {
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        TrackRecordReceiver trackRecordReceiver = this.m;
        if (trackRecordReceiver != null) {
            trackRecordReceiver.e();
        }
        this.b.stopService(new Intent(this.b, (Class<?>) TrackRecordService.class));
        this.b = null;
    }
}
