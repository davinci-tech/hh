package defpackage;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.lang.ref.WeakReference;

/* loaded from: classes8.dex */
public class fpt extends Handler {
    private WeakReference<LongCoachView> b;
    private int c;

    public fpt(LongCoachView longCoachView) {
        this.b = new WeakReference<>(longCoachView);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.h("Suggestion_UpdateUiHandler", "UpdateUiHandler msg is null!");
            return;
        }
        super.handleMessage(message);
        LongCoachView longCoachView = this.b.get();
        if (longCoachView == null) {
            LogUtil.h("Suggestion_UpdateUiHandler", "UpdateUiHandler msg is null!");
            return;
        }
        if (message.what == 1200) {
            LogUtil.h("Suggestion_UpdateUiHandler", "last updateTime is 5 mins ago");
            return;
        }
        if (message.what == 1201 && longCoachView.a() != null && longCoachView.a().getVisibility() == 0) {
            longCoachView.a().setVisibility(8);
            return;
        }
        if (message.what == 1202) {
            d(longCoachView);
            return;
        }
        if (message.what == 1204 && longCoachView.getLockLayout() != null) {
            longCoachView.getLockLayout().setVisibility(fnz.aBG_(longCoachView.getLockLayout()));
        } else if (message.what == 1203 && longCoachView.getLockLayout() != null && longCoachView.getLockLayout().getVisibility() == 0) {
            longCoachView.getLockLayout().setVisibility(8);
        } else {
            LogUtil.c("Suggestion_UpdateUiHandler", "Message mStation unexpected");
        }
    }

    private void d(LongCoachView longCoachView) {
        MediaPlayer aCq_ = longCoachView.g().aCq_();
        if (aCq_ != null && aCq_.getCurrentPosition() != this.c) {
            HealthTextView n = longCoachView.n();
            n.setVisibility(0);
            int currentPosition = aCq_.getCurrentPosition();
            this.c = currentPosition;
            fph.aCC_(currentPosition, n, longCoachView.l(), longCoachView.k());
        }
        sendEmptyMessageDelayed(1202, 500L);
    }
}
