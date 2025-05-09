package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.dialog.DialogResourcesListener;

/* loaded from: classes6.dex */
public class nlu extends BaseAchieveDialog {
    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public byte generateAllTextViewLayoutFlags() {
        return (byte) 0;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void onAnimSizeMeasured(int i, int i2) {
    }

    public nlu(Context context, AchieveDialogFactory.DialogType dialogType, DialogResourcesListener dialogResourcesListener) {
        super(context, dialogType, dialogResourcesListener);
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public String toString() {
        return "ThreeLeafAchieveDialog";
    }
}
