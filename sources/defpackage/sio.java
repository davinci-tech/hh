package defpackage;

import android.content.Context;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToKomootApi;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class sio extends BasePlatformHandler {

    /* renamed from: a, reason: collision with root package name */
    private Set<Integer> f17070a;
    private final int[] b;
    private final SyncDataToKomootApi c;

    public sio(Context context, BasePlatformHandler.SyncResultListener syncResultListener) {
        super(context, syncResultListener);
        this.b = new int[]{257, 258, 259};
        this.c = (SyncDataToKomootApi) lqi.d().b(SyncDataToKomootApi.class);
        init();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public OauthManager getOauthManager() {
        return sir.c();
    }

    /* synthetic */ SyncTask e(HiHealthData hiHealthData, File file) {
        return new siu(hiHealthData, getGpxType(hiHealthData.getSubType()), file, this.c);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public BasePlatformHandler.GenerateSyncDataTaskFactory getGenerateSyncDataTaskFactory() {
        return new BasePlatformHandler.GenerateSyncDataTaskFactory() { // from class: siw
            @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler.GenerateSyncDataTaskFactory
            public final SyncTask generateSyncDataTask(HiHealthData hiHealthData, File file) {
                return sio.this.e(hiHealthData, file);
            }
        };
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public Set<Integer> getSyncDataType() {
        if (this.f17070a == null) {
            this.f17070a = new HashSet();
        }
        this.f17070a.addAll(Arrays.asList(ArrayUtils.toWrapperArray(this.b)));
        return Collections.unmodifiableSet(this.f17070a);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public boolean needUpload(HiHealthData hiHealthData) {
        return super.needUpload(hiHealthData) && hiHealthData.getInt("trackdata_deviceType") != 32;
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getSynchronizedTimeStampsKey() {
        return "key_komoot_synchronized_time";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getPlatform() {
        return "Komoot";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getGpxType(int i) {
        switch (i) {
            case 257:
                return "hike";
            case 258:
                return "jogging";
            case 259:
                return "touringbicycle";
            default:
                return "";
        }
    }
}
