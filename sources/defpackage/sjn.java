package defpackage;

import android.content.Context;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToStravaApi;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class sjn extends BasePlatformHandler {

    /* renamed from: a, reason: collision with root package name */
    private Set<Integer> f17079a;
    private final SyncDataToStravaApi c;
    private final int[] d;

    public sjn(Context context, BasePlatformHandler.SyncResultListener syncResultListener) {
        super(context, syncResultListener);
        this.d = new int[]{257, 258, 259};
        this.c = (SyncDataToStravaApi) lqi.d().b(SyncDataToStravaApi.class);
        init();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public OauthManager getOauthManager() {
        return sjo.d();
    }

    /* synthetic */ SyncTask b(HiHealthData hiHealthData, File file) {
        return new sjp(hiHealthData, getGpxType(hiHealthData.getSubType()), file, this.c);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public BasePlatformHandler.GenerateSyncDataTaskFactory getGenerateSyncDataTaskFactory() {
        return new BasePlatformHandler.GenerateSyncDataTaskFactory() { // from class: sjq
            @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler.GenerateSyncDataTaskFactory
            public final SyncTask generateSyncDataTask(HiHealthData hiHealthData, File file) {
                return sjn.this.b(hiHealthData, file);
            }
        };
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public Set<Integer> getSyncDataType() {
        if (this.f17079a == null) {
            this.f17079a = new HashSet();
        }
        this.f17079a.addAll(Arrays.asList(ArrayUtils.toWrapperArray(this.d)));
        return Collections.unmodifiableSet(this.f17079a);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getSynchronizedTimeStampsKey() {
        return "key_strava_synchronized_time";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getPlatform() {
        return "Strava";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getGpxType(int i) {
        switch (i) {
            case 257:
                return "Walk";
            case 258:
                return "Run";
            case 259:
                return "Ride";
            default:
                return "";
        }
    }
}
