package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.wear.wallet.ui.dialog.HwDialogInterface;
import com.huawei.wear.wallet.ui.dialog.HwProgressDialogCustom;
import com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface;
import com.huawei.wear.wallet.ui.dialog.WidgetBuilder;

/* loaded from: classes9.dex */
public class tll implements WidgetBuilder.IBuilder {
    static {
        WidgetBuilder.d(new tll());
    }

    @Override // com.huawei.wear.wallet.ui.dialog.WidgetBuilder.IBuilder
    public HwDialogInterface createDialog(Context context) {
        return new tln(context, R.style.WalletCustomDialogTheme);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.WidgetBuilder.IBuilder
    public HwProgressDialogInterface createProgressDialog(Context context) {
        return new HwProgressDialogCustom(context, R.style.CustomDialogTheme);
    }
}
