package defpackage;

import android.app.Activity;
import android.content.Intent;
import com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback;
import com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticConnectActivity;
import com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticHealthActivity;

/* loaded from: classes8.dex */
public class sfy extends CheckConnectCallback {
    public sfy(Activity activity) {
        super(activity);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback
    public void startToDisConnectActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) RuntasticHealthActivity.class));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback
    public void startToConnectActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) RuntasticConnectActivity.class));
    }
}
