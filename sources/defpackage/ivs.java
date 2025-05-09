package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealthservice.updatemanager.command.UpdateCommand;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class ivs implements UpdateCommand {
    @Override // com.huawei.hihealthservice.updatemanager.command.UpdateCommand
    public void execute(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener, Context context) throws RemoteException {
        if (iDataOperateListener == null || hiDataUpdateOption == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        int b = igl.b(hiDataUpdateOption.getString("old_huid"), hiDataUpdateOption.getString("new_huid"), hiDataUpdateOption.getBoolean("copy_sync_status"));
        arrayList.add(Integer.valueOf(b));
        iDataOperateListener.onResult(b, arrayList);
    }
}
