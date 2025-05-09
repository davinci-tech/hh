package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelReq;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwcloudmodel.model.unite.UserRunLevelData;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes4.dex */
public class hqa {
    private boolean b;

    /* renamed from: a, reason: collision with root package name */
    private ffp f13315a = new ffp();
    private HiUserPreference d = new HiUserPreference();
    private Context c = BaseApplication.e();

    public void b(final Context context, final int i) {
        if (context == null) {
            ReleaseLogUtil.c("Track_RQDataManager", "pushRQDataToDevice: context is null");
        } else {
            jdx.b(new Runnable() { // from class: hqa.5
                @Override // java.lang.Runnable
                public void run() {
                    if (hqa.this.b) {
                        ReleaseLogUtil.e("Track_RQDataManager", "RqData has been pushed!");
                        return;
                    }
                    GetRunLevelReq getRunLevelReq = new GetRunLevelReq();
                    LogUtil.d("Track_RQDataManager", "testInitData: ", Integer.valueOf(i));
                    getRunLevelReq.setStartDay(i);
                    getRunLevelReq.setEndDay(i);
                    getRunLevelReq.setQueryType(0);
                    HashSet hashSet = new HashSet(16);
                    hashSet.add(1);
                    hashSet.add(2);
                    hashSet.add(3);
                    hashSet.add(4);
                    hashSet.add(5);
                    hashSet.add(6);
                    getRunLevelReq.setTypes(hashSet);
                    LogUtil.d("Track_RQDataManager", getRunLevelReq.toString());
                    jbs.a(context).d(getRunLevelReq, new ResultCallback<GetRunLevelRsp>() { // from class: hqa.5.5
                        @Override // com.huawei.networkclient.ResultCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(GetRunLevelRsp getRunLevelRsp) {
                            ReleaseLogUtil.e("Track_RQDataManager", "pushRqDataToDevice: GetRunLevelRsp success");
                            hqa.this.c(getRunLevelRsp);
                            if (hqa.this.f13315a != null) {
                                hqa.this.c(context, i, hqa.this.f13315a);
                            } else {
                                ReleaseLogUtil.c("Track_RQDataManager", "Failed to parse rqData");
                            }
                        }

                        @Override // com.huawei.networkclient.ResultCallback
                        public void onFailure(Throwable th) {
                            ReleaseLogUtil.c("Track_RQDataManager", "getRunLevelDetail: request fail");
                            if (TextUtils.isEmpty(th.getMessage())) {
                                ReleaseLogUtil.c("Track_RQDataManager", "getRunLevelDetail: request fail and throwable.getMessage is null");
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(GetRunLevelRsp getRunLevelRsp) {
        if (getRunLevelRsp == null) {
            ReleaseLogUtil.c("Track_RQDataManager", "getRqData runLevelRsp is null");
            return;
        }
        Map<Integer, RunLevelDetail> data = getRunLevelRsp.getData();
        UserRunLevelData userRunLevelData = getRunLevelRsp.getUserRunLevelData();
        if (data == null || userRunLevelData == null) {
            ReleaseLogUtil.c("Track_RQDataManager", "runLevelDetails or userRunLevelData is null");
            return;
        }
        this.f13315a.b(data);
        this.f13315a.c(userRunLevelData);
        this.f13315a.c((int) (System.currentTimeMillis() / 1000));
        ReleaseLogUtil.e("Track_RQDataManager", "runLevelRsp convert to rqdata success!");
    }

    public void c(Context context, int i, ffp ffpVar) {
        if (ffpVar == null) {
            ReleaseLogUtil.d("Track_RQDataManager", "pushToDevice rqData is null");
            return;
        }
        RunLevelDetail runLevelDetail = ffpVar.b().get(Integer.valueOf(i));
        UserRunLevelData d = ffpVar.d();
        if (runLevelDetail == null || d == null) {
            ReleaseLogUtil.c("Track_RQDataManager", "runLevelDetail or userRunLevelData is null");
        } else {
            hqb.d(context).a(ffpVar, i, false, new IBaseResponseCallback() { // from class: hqa.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.d("Track_RQDataManager", "mDeviceResponseCallback errorCode = ", Integer.valueOf(i2));
                    if (i2 == 0) {
                        ReleaseLogUtil.e("Track_RQDataManager", "mDeviceResponseCallback success");
                        hqa.this.d.setKey("track.rq_config");
                        HashMap hashMap = new HashMap();
                        hashMap.put("isSyncOrNot", Boolean.toString(true));
                        hqa.this.d.setValue(moj.e(hashMap));
                        HiHealthManager.d(hqa.this.c).setUserPreference(hqa.this.d);
                        return;
                    }
                    ReleaseLogUtil.e("Track_RQDataManager", "mDeviceResponseCallback something error");
                }
            });
        }
    }

    public void a(Context context, int i, int i2, int i3, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            return;
        }
        GetRunLevelReq getRunLevelReq = new GetRunLevelReq();
        getRunLevelReq.setStartDay(i);
        getRunLevelReq.setEndDay(i2);
        getRunLevelReq.setQueryType(i3);
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        getRunLevelReq.setTypes(hashSet);
        LogUtil.d("Track_RQDataManager", getRunLevelReq.toString());
        jbs.a(context.getApplicationContext()).d(getRunLevelReq, new ResultCallback<GetRunLevelRsp>() { // from class: hqa.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetRunLevelRsp getRunLevelRsp) {
                if (getRunLevelRsp == null) {
                    ReleaseLogUtil.d("getRunLevel, resp is null", new Object[0]);
                    return;
                }
                if (getRunLevelRsp.getUserRunLevelData() != null) {
                    ReleaseLogUtil.e("Track_RQDataManager", getRunLevelRsp.printString());
                } else {
                    ReleaseLogUtil.d("Track_RQDataManager", "userRunLevel is null");
                }
                if (getRunLevelRsp.getResultCode().intValue() == 0) {
                    iBaseResponseCallback.d(200, getRunLevelRsp);
                } else {
                    iBaseResponseCallback.d(2, getRunLevelRsp);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("Track_RQDataManager", "getRunlevel error:", th.getMessage());
                iBaseResponseCallback.d(2, null);
            }
        });
    }
}
