package defpackage;

import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.main.stories.me.activity.BindUserInfoActivity;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class rgv implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<BindUserInfoActivity> f16756a;
    private int d;

    public rgv(BindUserInfoActivity bindUserInfoActivity, int i) {
        this.f16756a = null;
        this.f16756a = new WeakReference<>(bindUserInfoActivity);
        this.d = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BindUserInfoActivity bindUserInfoActivity;
        LogUtil.d("MyGenderOnClickListener", "MyGenderOnClickListener onClick(), gender:", Integer.valueOf(this.d));
        WeakReference<BindUserInfoActivity> weakReference = this.f16756a;
        if (weakReference != null && (bindUserInfoActivity = weakReference.get()) != null) {
            bindUserInfoActivity.b(this.d);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
