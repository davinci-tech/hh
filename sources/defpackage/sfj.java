package defpackage;

import android.app.Activity;
import android.content.Intent;
import com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback;
import com.huawei.ui.thirdpartservice.activity.komoot.KomootConnectActivity;
import com.huawei.ui.thirdpartservice.activity.komoot.KomootHealthActivity;

/* loaded from: classes7.dex */
public class sfj extends CheckConnectCallback {
    public sfj(Activity activity) {
        super(activity);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback
    public void startToDisConnectActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) KomootHealthActivity.class));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback
    public void startToConnectActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) KomootConnectActivity.class));
    }
}
