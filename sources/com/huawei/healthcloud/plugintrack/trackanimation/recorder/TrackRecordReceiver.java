package com.huawei.healthcloud.plugintrack.trackanimation.recorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class TrackRecordReceiver extends BroadcastReceiver {
    private IntentFilter b;
    private Context c;
    private TrackRecordListener e;

    public interface TrackRecordListener {
        void onRecordFinish(long j, boolean z);

        void onRecordStart();
    }

    public TrackRecordReceiver(Context context) {
        this.c = context;
    }

    public void a(Context context, TrackRecordListener trackRecordListener) {
        this.e = trackRecordListener;
        IntentFilter intentFilter = new IntentFilter();
        this.b = intentFilter;
        intentFilter.addAction("sta_track_record_start");
        this.b.addAction("sta_track_record_finish");
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
        LocalBroadcastManager.getInstance(context).registerReceiver(this, this.b);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.c("Suggestion_TrackRecordReceiver", "onReceive: intent is null");
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            ReleaseLogUtil.c("Suggestion_TrackRecordReceiver", "onReceive: action is empty");
            return;
        }
        if (this.e == null) {
            ReleaseLogUtil.c("Suggestion_TrackRecordReceiver", "onReceive: listener is null");
            return;
        }
        ReleaseLogUtil.e("Suggestion_TrackRecordReceiver", "onReceive: action is ", action);
        action.hashCode();
        if (action.equals("sta_track_record_finish")) {
            this.e.onRecordFinish(intent.getLongExtra("data_key_video_duration", 0L), intent.getBooleanExtra("data_key_video_record_error", false));
        } else if (action.equals("sta_track_record_start")) {
            this.e.onRecordStart();
        }
    }

    public void e() {
        LocalBroadcastManager.getInstance(this.c).unregisterReceiver(this);
        this.c = null;
    }

    public void a() {
        Context context = this.c;
        if (context != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("ats_track_record_stop"));
        }
    }

    public void b() {
        Context context = this.c;
        if (context != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("ats_track_record_stop_when_pause"));
        }
    }
}
