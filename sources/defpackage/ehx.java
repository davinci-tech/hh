package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.userprofile.GetCommentReq;
import com.huawei.hwcloudmodel.model.userprofile.GetCommentRsp;
import com.huawei.hwcloudmodel.model.userprofile.UpdateCommentReq;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class ehx {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12022a = new Object();
    private static volatile ehx c;
    private jbx b;
    private ExecutorService d;
    private final Context e;

    private ehx(Context context) {
        this.b = null;
        this.e = context.getApplicationContext();
        if (this.b == null) {
            this.b = jbx.d();
        }
        if (this.d == null) {
            this.d = Executors.newSingleThreadExecutor();
        }
    }

    public static ehx e(Context context) {
        if (c == null) {
            synchronized (f12022a) {
                if (c == null) {
                    c = new ehx(context);
                }
            }
        }
        return c;
    }

    public void e() {
        LogUtil.a("MarketCommentMgr", "MarketCommentCloud_download_enter ");
        if (!Utils.o()) {
            this.b.c(new GetCommentReq(), new ICloudOperationResult<GetCommentRsp>() { // from class: ehx.4
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void operationResult(GetCommentRsp getCommentRsp, String str, boolean z) {
                    if (!z || getCommentRsp == null) {
                        return;
                    }
                    int remainder = getCommentRsp.getRemainder();
                    LogUtil.a("MarketCommentMgr", "MarketCommentCloud_download_remainder ", Integer.valueOf(remainder));
                    SharedPreferenceManager.e(ehx.this.e, Integer.toString(10000), "market_comment_status", Integer.toString(remainder), new StorageParams());
                }
            });
        }
        ExecutorService executorService = this.d;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: ehx.5
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(ehx.this.e).getUserPreference("custom.market_comment_time");
                    if (userPreference != null) {
                        SharedPreferenceManager.e(ehx.this.e, Integer.toString(10000), "market_comment_time", userPreference.getValue(), new StorageParams());
                        LogUtil.a("MarketCommentMgr", "MarketCommentCloud_comment time ", userPreference.getValue());
                    }
                }
            });
        }
    }

    public void c() {
        LogUtil.a("MarketCommentMgr", "MarketCommentCloud_upload_enter ");
        if (Utils.o()) {
            LogUtil.a("MarketCommentMgr", "uploadCommentStatus is oversea");
            return;
        }
        this.b.d(new UpdateCommentReq(), new ICloudOperationResult<CloudCommonReponse>() { // from class: ehx.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
                if (z) {
                    SharedPreferenceManager.e(ehx.this.e, Integer.toString(10000), "market_comment_status", Integer.toString(0), new StorageParams());
                    LogUtil.a("MarketCommentMgr", "MarketCommentCloud_comment uploadCommentStatus success ");
                }
            }
        });
        final String valueOf = String.valueOf(System.currentTimeMillis());
        SharedPreferenceManager.e(this.e, Integer.toString(10000), "market_comment_time", valueOf, new StorageParams());
        ExecutorService executorService = this.d;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: ehx.2
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference hiUserPreference = new HiUserPreference();
                    hiUserPreference.setKey("custom.market_comment_time");
                    hiUserPreference.setValue(valueOf);
                    HiHealthManager.d(ehx.this.e).setUserPreference(hiUserPreference);
                    HiSyncOption hiSyncOption = new HiSyncOption();
                    hiSyncOption.setSyncModel(2);
                    hiSyncOption.setSyncAction(0);
                    hiSyncOption.setSyncDataType(10026);
                    hiSyncOption.setSyncMethod(2);
                    HiHealthNativeApi.a(ehx.this.e).synCloud(hiSyncOption, null);
                    LogUtil.a("MarketCommentMgr", "MarketCommentCloud_up_to_cloud time", valueOf);
                }
            });
        }
    }

    public void d() {
        LogUtil.a("MarketCommentMgr", "Oversea_MarketCommentCloud_upload_enter ");
        final String valueOf = String.valueOf(System.currentTimeMillis());
        SharedPreferenceManager.e(this.e, Integer.toString(10000), "market_comment_time", valueOf, new StorageParams());
        ExecutorService executorService = this.d;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: ehx.1
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference hiUserPreference = new HiUserPreference();
                    hiUserPreference.setKey("custom.market_comment_time");
                    hiUserPreference.setValue(valueOf);
                    HiHealthManager.d(ehx.this.e).setUserPreference(hiUserPreference);
                    LogUtil.a("MarketCommentMgr", "Oversea_MarketCommentCloud_up_to_cloud time", valueOf);
                }
            });
        }
    }
}
