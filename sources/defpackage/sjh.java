package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.hihealth.HiHealthActivities;
import com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import com.huawei.ui.thirdpartservice.syncdata.constants.RuntasticOauthConstant;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToRuntasticApi;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class sjh extends BasePlatformHandler {
    private Set<Integer> c;
    private final SyncDataToRuntasticApi e;

    public sjh(Context context, BasePlatformHandler.SyncResultListener syncResultListener) {
        super(context, syncResultListener);
        this.e = (SyncDataToRuntasticApi) lqi.d().b(SyncDataToRuntasticApi.class);
        init();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public OauthManager getOauthManager() {
        return sjd.d();
    }

    /* synthetic */ SyncTask c(HiHealthData hiHealthData, File file) {
        return new sji(hiHealthData, getGpxType(hiHealthData.getSubType()), file, this.e);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public BasePlatformHandler.GenerateSyncDataTaskFactory getGenerateSyncDataTaskFactory() {
        return new BasePlatformHandler.GenerateSyncDataTaskFactory() { // from class: sje
            @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler.GenerateSyncDataTaskFactory
            public final SyncTask generateSyncDataTask(HiHealthData hiHealthData, File file) {
                return sjh.this.c(hiHealthData, file);
            }
        };
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public Set<Integer> getSyncDataType() {
        if (this.c == null) {
            this.c = new HashSet();
        }
        this.c.addAll(RuntasticOauthConstant.SYNC_DATA_TYPE);
        return Collections.unmodifiableSet(this.c);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public boolean needUpload(HiHealthData hiHealthData) {
        return super.needUpload(hiHealthData) && hiHealthData.getInt("trackdata_deviceType") != 32;
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getSynchronizedTimeStampsKey() {
        return "key_runtastic_synchronized_time";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getPlatform() {
        return "Runtastic";
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler
    public String getGpxType(int i) {
        if (i == 266) {
            return "swimming";
        }
        if (i == 280) {
            return "running";
        }
        if (i == 282) {
            return "hiking";
        }
        switch (i) {
            case 217:
                return "alpine_skiing";
            case 218:
                return "snowboarding";
            case 219:
                return "cross_country_skiing";
            default:
                switch (i) {
                    case 257:
                        return "walking";
                    case 258:
                        return "running";
                    case 259:
                        return HiHealthActivities.CYCLING;
                    case 260:
                        return "hiking";
                    default:
                        return "";
                }
        }
    }
}
