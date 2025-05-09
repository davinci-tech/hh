package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class eoa {

    /* renamed from: a, reason: collision with root package name */
    private PendingIntent f12116a;
    private volatile boolean b;
    private volatile enq c;
    private Notification.Builder d;
    private final Context e;
    private PendingIntent f;
    private final MediaSession.Token g;
    private Bitmap j;
    private final Handler i = new Handler(Looper.getMainLooper());
    private final NotificationManagerCompat h = NotificationManagerCompat.from(BaseApplication.e());

    public eoa(Context context, MediaSession.Token token) {
        this.e = context;
        this.g = token;
        b();
    }

    private void b() {
        Notification.Builder xf_ = jdh.c().xf_();
        this.d = xf_;
        xf_.setSmallIcon(R.mipmap._2131820756_res_0x7f1100d4);
        this.d.setOngoing(true);
        this.d.setAutoCancel(false);
        Intent intent = new Intent("notification_removed");
        intent.setPackage(BaseApplication.d());
        PendingIntent broadcast = PendingIntent.getBroadcast(BaseApplication.e(), 0, intent, 201326592);
        this.f12116a = broadcast;
        this.d.setDeleteIntent(broadcast);
        Intent intent2 = new Intent("notification_play_or_pause_btn_clicked");
        intent2.setPackage(BaseApplication.d());
        this.f = PendingIntent.getBroadcast(BaseApplication.e(), 0, intent2, 201326592);
    }

    public void arA_(PendingIntent pendingIntent) {
        this.d.setContentIntent(pendingIntent);
    }

    public void a(final enq enqVar, final boolean z) {
        this.b = false;
        ThreadPoolManager.d().execute(new Runnable() { // from class: eoc
            @Override // java.lang.Runnable
            public final void run() {
                eoa.this.c(enqVar, z);
            }
        });
    }

    /* synthetic */ void c(enq enqVar, final boolean z) {
        if (enqVar == null) {
            return;
        }
        if (!enqVar.equals(this.c)) {
            this.c = enqVar;
            this.j = null;
        }
        if (this.j != null) {
            this.i.post(new Runnable() { // from class: enz
                @Override // java.lang.Runnable
                public final void run() {
                    eoa.this.a(z);
                }
            });
            return;
        }
        Bitmap arN_ = eow.arN_(enqVar.e());
        if (arN_ == null) {
            LogUtil.a("AudioNotificationHelper", "load Audio icon failed, url:" + enqVar.e());
            this.j = null;
        } else {
            Bitmap cJJ_ = nrf.cJJ_(arN_, GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, GlMapUtil.DEVICE_DISPLAY_DPI_HIGH);
            if (cJJ_ != null && !cJJ_.isRecycled()) {
                this.j = nrf.cJq_(cJJ_, cJJ_.getWidth(), cJJ_.getHeight(), 53);
            }
        }
        this.i.post(new Runnable() { // from class: eod
            @Override // java.lang.Runnable
            public final void run() {
                eoa.this.c(z);
            }
        });
    }

    /* synthetic */ void a(boolean z) {
        ary_(this.c, this.j, z);
    }

    /* synthetic */ void c(boolean z) {
        ary_(this.c, this.j, z);
    }

    private void ary_(enq enqVar, Bitmap bitmap, boolean z) {
        RemoteViews remoteViews;
        synchronized (this) {
            if (enqVar == null) {
                LogUtil.e("AudioNotificationHelper", "createRemoteViews failed, audioItem is null");
                return;
            }
            if (this.b) {
                LogUtil.c("AudioNotificationHelper", "this is a post with bitmap(a delay msg), but notification has already been canceled, skip this post");
                return;
            }
            if (eow.c()) {
                remoteViews = arz_(this.e.getPackageName());
            } else {
                RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.music_notification);
                if (Build.VERSION.SDK_INT >= 31 && !BaseActivity.isMiui()) {
                    int c = !CommonUtil.bf() ? 0 : nsn.c(BaseApplication.e(), 16.0f);
                    remoteViews2.setViewPadding(R.id.whole_notification_view, 0, c, c, c);
                    remoteViews2.setViewVisibility(R.id.music_rela, 8);
                    remoteViews2.setViewVisibility(R.id.id_iv_close_icon, 8);
                    remoteViews2.setViewVisibility(R.id.id_iv_close_icon_2, 0);
                    remoteViews2.setOnClickPendingIntent(R.id.id_iv_close_icon_2, this.f12116a);
                } else {
                    remoteViews2.setViewVisibility(R.id.id_iv_close_icon_2, 8);
                    remoteViews2.setOnClickPendingIntent(R.id.id_iv_close_icon, this.f12116a);
                }
                remoteViews = remoteViews2;
            }
            remoteViews.setTextViewText(R.id.notification_song_title, TextUtils.isEmpty(enqVar.n()) ? "" : enqVar.n());
            remoteViews.setTextViewText(R.id.notification_song_subtitle, TextUtils.isEmpty(enqVar.f()) ? "" : enqVar.f());
            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.notification_cover_image, bitmap);
            }
            if (z) {
                remoteViews.setImageViewResource(R.id.notification_play, R.drawable._2131429725_res_0x7f0b095d);
            } else {
                remoteViews.setImageViewResource(R.id.notification_play, R.drawable._2131429726_res_0x7f0b095e);
            }
            remoteViews.setOnClickPendingIntent(R.id.notification_play, this.f);
            this.d.setCustomContentView(remoteViews);
            if (eow.b() || CommonUtil.bm()) {
                this.d.setStyle(new Notification.MediaStyle().setMediaSession(this.g));
            }
            LogUtil.c("AudioNotificationHelper", "post a new notification with isPlaying: " + z);
            try {
                if ((this.e instanceof Service) && this.h.areNotificationsEnabled()) {
                    ((Service) this.e).startForeground(20220913, this.d.build());
                }
            } catch (Exception e) {
                c();
                ReleaseLogUtil.c("R_AudioNotificationHelper", "updateNotificationView Exception ", ExceptionUtils.d(e));
            }
        }
    }

    private void c() {
        if (ActivityCompat.checkSelfPermission(this.e, Constants.POST_NOTIFICATIONS) != 0) {
            LogUtil.c("AudioNotificationHelper", "not has notification permission");
        } else {
            this.h.notify(20220913, this.d.build());
        }
    }

    private RemoteViews arz_(String str) {
        RemoteViews remoteViews = new RemoteViews(str, R.layout.music_notification_harmony_3_0);
        if (Build.VERSION.SDK_INT < 31) {
            int c = nsn.c(BaseApplication.e(), 8.0f);
            remoteViews.setViewPadding(R.id.whole_notification_view, c, 0, c, 0);
        }
        return remoteViews;
    }

    public void e() {
        LogUtil.c("AudioNotificationHelper", "audio notification cancelled...");
        if ((this.e instanceof Service) && this.h.areNotificationsEnabled()) {
            ((Service) this.e).stopForeground(true);
            this.b = true;
        }
    }
}
