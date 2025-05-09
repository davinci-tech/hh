package defpackage;

import android.content.ContentValues;
import com.huawei.health.device.api.DeviceDataBaseHelperApi;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = DeviceDataBaseHelperApi.class)
/* loaded from: classes3.dex */
public class dym implements DeviceDataBaseHelperApi {
    @Override // com.huawei.health.device.api.DeviceDataBaseHelperApi
    public long insert(ContentValues contentValues) {
        return cen.b().DQ_("device", contentValues);
    }

    @Override // com.huawei.health.device.api.DeviceDataBaseHelperApi
    public long update(ContentValues contentValues, String str, String[] strArr) {
        return cen.b().DU_("device", contentValues, str, strArr);
    }
}
