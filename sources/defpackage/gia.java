package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;

/* loaded from: classes4.dex */
public class gia {
    private boolean b = false;
    private final BroadcastReceiver d = new BroadcastReceiver() { // from class: gia.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("Suggestion_VolumeUtil", "registerSystemVolumeReceiver intent is null");
                return;
            }
            if (Constants.VOLUME_CHANGED_ACTION.equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 3);
                LogUtil.a("Suggestion_VolumeUtil", "registerSystemVolumeReceiver volume change streamType = ", Integer.valueOf(intExtra));
                if (intExtra != 3) {
                    return;
                }
                if (gia.this.b) {
                    gia.this.b = false;
                    return;
                }
                AudioManager audioManager = (AudioManager) BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
                if (audioManager == null) {
                    LogUtil.b("Suggestion_VolumeUtil", "AudioManager is null");
                    return;
                }
                int intExtra2 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", audioManager.getStreamVolume(3));
                int intExtra3 = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", audioManager.getStreamVolume(3));
                LogUtil.a("Suggestion_VolumeUtil", "volume change cutValue = ", Integer.valueOf(intExtra2), " oldValue = ", Integer.valueOf(intExtra3));
                CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
                newBuilder.setCurrentVolume((int) ((intExtra2 * 100.0d) / audioManager.getStreamMaxVolume(3)));
                if (intExtra2 != intExtra3) {
                    CoachController.d().d(CoachController.StatusSource.APP, newBuilder);
                } else {
                    LogUtil.b("Suggestion_VolumeUtil", "volume is not change!");
                }
            }
        }
    };

    public void d(int i) {
        AudioManager audioManager;
        if (i >= 0 && (audioManager = (AudioManager) BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO)) != null) {
            audioManager.setStreamVolume(3, (int) ((i / 100.0d) * audioManager.getStreamMaxVolume(3)), 4);
            this.b = true;
        }
    }

    public boolean e() {
        return this.b;
    }

    public void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.VOLUME_CHANGED_ACTION);
        BaseApplication.getContext().registerReceiver(this.d, intentFilter);
    }

    public void d() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.d);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Suggestion_VolumeUtil", "unregister broadcast volume IllegalArgumentException.");
        }
    }
}
