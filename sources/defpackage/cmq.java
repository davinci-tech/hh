package defpackage;

import android.view.View;
import com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class cmq implements View.OnClickListener {
    private int b;
    private final WeakReference<GuestUserInfoGuideFragment> d;

    public cmq(GuestUserInfoGuideFragment guestUserInfoGuideFragment, int i) {
        this.d = new WeakReference<>(guestUserInfoGuideFragment);
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.d("GenderSelectListener", "GenderSelectListener onClick(), gender:", Integer.valueOf(this.b));
        GuestUserInfoGuideFragment guestUserInfoGuideFragment = this.d.get();
        if (guestUserInfoGuideFragment == null) {
            LogUtil.e("GenderSelectListener", "GenderSelectListener UserInfoActivity =null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            guestUserInfoGuideFragment.dealGenderClicked(this.b);
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
