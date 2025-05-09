package defpackage;

import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.hihealth.IRealTimeDataCallback;

/* loaded from: classes7.dex */
public class iqf {
    private IRealTimeDataCallback callback;
    private int permissionTypeId;
    private String permissionTypeName;
    private ITrackDataForDeveloper trackDataForDeveloper;

    public iqf(int i, String str, IRealTimeDataCallback iRealTimeDataCallback, ITrackDataForDeveloper iTrackDataForDeveloper) {
        this.permissionTypeId = i;
        this.permissionTypeName = str;
        this.callback = iRealTimeDataCallback;
        this.trackDataForDeveloper = iTrackDataForDeveloper;
    }

    public int getPermissionTypeId() {
        return this.permissionTypeId;
    }

    public String getPermissionTypeName() {
        return this.permissionTypeName;
    }

    public IRealTimeDataCallback getCallback() {
        return this.callback;
    }

    public ITrackDataForDeveloper getTrackDataForDeveloper() {
        return this.trackDataForDeveloper;
    }
}
