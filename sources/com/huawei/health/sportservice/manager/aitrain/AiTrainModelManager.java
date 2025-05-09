package com.huawei.health.sportservice.manager.aitrain;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginfitnesssport.OnCheckStandListenerWrapper;
import com.huawei.pluginfitnesssport.OnTrainSkeletonListenerWrapper;
import defpackage.fgj;
import defpackage.mon;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 1, name = ComponentName.MODEL_MANAGER)
/* loaded from: classes8.dex */
public class AiTrainModelManager implements ManagerComponent {
    private static final int INIT_MODEL_FAIL = 1;
    private static final int INIT_MODEL_SUCCESS = 0;
    private static final String TAG = "SportService_AiTrainModelManager";
    private fgj mAiModelInfo;
    private fgj mLastAiModelInfo;
    private boolean mHasInitAiModel = false;
    private int mInitModelStatus = 1;
    private Context mContext = BaseApplication.e();

    static /* synthetic */ void lambda$setModelInfo$3(int i, Object obj) {
    }

    static /* synthetic */ void lambda$unloadModel$4(int i, Boolean bool) {
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.AI_ROPE_PARAS.equals(sportParamsType) && (obj instanceof fgj)) {
            updateAiRopeModelInfo(obj);
            return;
        }
        if (SportParamsType.AI_ROPE_INPUT_IMAGE.equals(sportParamsType) && (obj instanceof Image)) {
            setImage((Image) obj);
            return;
        }
        if (SportParamsType.AI_TRAIN_TEMPLATE_DATA.equals(sportParamsType) && (obj instanceof String)) {
            LogUtil.a(TAG, "setAiTrainTemplate");
            setAiTrainTemplateAndType((String) obj);
        }
        if (SportParamsType.PRE_STATUS_SOURCE_LISTENER.equals(sportParamsType) && (obj instanceof OnCheckStandListenerWrapper)) {
            LogUtil.a(TAG, "setAiTrainListener, OnCheckStandListenerWrapper, paraData ", obj);
            setPreTrainListener((OnCheckStandListenerWrapper) obj);
        }
        if (SportParamsType.AI_TRAIN_SOURCE_LISTENER.equals(sportParamsType) && (obj instanceof OnTrainSkeletonListenerWrapper)) {
            LogUtil.a(TAG, "setAiTrainListener");
            setAiTrainingListener((OnTrainSkeletonListenerWrapper) obj);
        }
    }

    private void updateAiRopeModelInfo(Object obj) {
        fgj fgjVar = this.mAiModelInfo;
        if (fgjVar != null && fgjVar.equals(obj)) {
            LogUtil.a(TAG, "mAiModelInfo is not null or not change");
            return;
        }
        this.mAiModelInfo = (fgj) obj;
        if (this.mHasInitAiModel) {
            setModelInfo();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.AI_ROPE_PARAS.equals(sportParamsType) || SportParamsType.AI_ROPE_INPUT_IMAGE.equals(sportParamsType) || SportParamsType.AI_TRAIN_SOURCE_LISTENER.equals(sportParamsType) || SportParamsType.AI_TRAIN_TEMPLATE_DATA.equals(sportParamsType) || SportParamsType.PRE_STATUS_SOURCE_LISTENER.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AiTrainModelManager.this.m482x5bbc2f4d();
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-aitrain-AiTrainModelManager, reason: not valid java name */
    /* synthetic */ void m482x5bbc2f4d() {
        BaseSportManager.getInstance().setIsAlreadyInit(false);
        initAiTrainProxy();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initAiTrainProxy() {
        /*
            r6 = this;
            r0 = 1
            fgj r1 = r6.mAiModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            if (r1 == 0) goto L34
            int r1 = r1.c()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            if (r1 == 0) goto L34
            fgj r1 = r6.mAiModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            int r1 = r1.a()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            if (r1 != 0) goto L14
            goto L34
        L14:
            mon r1 = defpackage.mon.d()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            android.content.Context r2 = r6.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            fgj r3 = r6.mAiModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            int r3 = r3.c()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            fgj r4 = r6.mAiModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            int r4 = r4.a()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda1 r5 = new com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda1     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r5.<init>()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r1.d(r2, r3, r4, r5)     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r6.mHasInitAiModel = r0     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r6.setModelInfoAfterInit()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            goto L5e
        L34:
            mon r1 = defpackage.mon.d()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            android.content.Context r2 = r6.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda0 r3 = new com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda0     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r3.<init>()     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            r4 = 1080(0x438, float:1.513E-42)
            r5 = 2160(0x870, float:3.027E-42)
            r1.d(r2, r4, r5, r3)     // Catch: java.lang.UnsatisfiedLinkError -> L47 java.lang.Exception -> L49
            goto L5e
        L47:
            r1 = move-exception
            goto L4a
        L49:
            r1 = move-exception
        L4a:
            java.lang.String r2 = "onPreSport"
            r6.uploadErrorMessage(r1, r2)
            java.lang.String r2 = "onPreSport() error: "
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            java.lang.String r2 = "SportService_AiTrainModelManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r1)
        L5e:
            r1 = 0
            r6.mInitModelStatus = r1
            com.huawei.health.sportservice.manager.BaseSportManager r2 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            int r3 = r6.mInitModelStatus
            if (r3 != 0) goto L6a
            goto L6b
        L6a:
            r0 = r1
        L6b:
            r2.setIsAlreadyInit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager.initAiTrainProxy():void");
    }

    /* renamed from: lambda$initAiTrainProxy$1$com-huawei-health-sportservice-manager-aitrain-AiTrainModelManager, reason: not valid java name */
    /* synthetic */ void m480x97aeb2fe(int i, Object obj) {
        this.mInitModelStatus = i;
    }

    /* renamed from: lambda$initAiTrainProxy$2$com-huawei-health-sportservice-manager-aitrain-AiTrainModelManager, reason: not valid java name */
    /* synthetic */ void m481x341caf5d(int i, Object obj) {
        this.mInitModelStatus = i;
    }

    public void setImage(Image image) {
        try {
            mon.d().cmQ_(image);
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "setImage");
            ReleaseLogUtil.c(TAG, "setImage() error: ", LogAnonymous.b(e));
        }
    }

    private void setModelInfoAfterInit() {
        fgj fgjVar = this.mLastAiModelInfo;
        if (fgjVar != null && fgjVar.equals(this.mAiModelInfo)) {
            LogUtil.a(TAG, "mLastAiModelInfo equals mAiModelInfo");
        } else {
            setModelInfo();
        }
    }

    private int setModelInfo() {
        fgj fgjVar = this.mAiModelInfo;
        if (fgjVar == null) {
            LogUtil.a(TAG, "mAiModelInfo == null");
            return 1;
        }
        try {
            this.mLastAiModelInfo = fgjVar;
            mon.d().b(this.mAiModelInfo.c(), this.mAiModelInfo.a(), this.mAiModelInfo.b(), new BaseResponseCallback() { // from class: com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda3
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    AiTrainModelManager.lambda$setModelInfo$3(i, obj);
                }
            });
            return 0;
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "setModelInfo");
            ReleaseLogUtil.c(TAG, "setModelInfo() error: ", LogAnonymous.b(e));
            this.mLastAiModelInfo = null;
            return 1;
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        try {
            unloadModel();
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "onStopSport");
            ReleaseLogUtil.c(TAG, "onStopSport() error: ", LogAnonymous.b(e));
        }
    }

    private void unloadModel() {
        mon.d().a(new BaseResponseCallback() { // from class: com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager$$ExternalSyntheticLambda4
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                AiTrainModelManager.lambda$unloadModel$4(i, (Boolean) obj);
            }
        });
    }

    private void setPreTrainListener(OnCheckStandListenerWrapper onCheckStandListenerWrapper) {
        if (onCheckStandListenerWrapper != null) {
            try {
                mon.d().b(onCheckStandListenerWrapper);
                LogUtil.a(TAG, "setAiTrainListener");
            } catch (Exception | UnsatisfiedLinkError e) {
                uploadErrorMessage(e, "setAiTrainListener");
                ReleaseLogUtil.c(TAG, "setAiTrainListener error: ", LogAnonymous.b(e));
            }
        }
    }

    private void setAiTrainingListener(OnTrainSkeletonListenerWrapper onTrainSkeletonListenerWrapper) {
        if (onTrainSkeletonListenerWrapper != null) {
            try {
                mon.d().b((OnCheckStandListenerWrapper) null);
                mon.d().d(onTrainSkeletonListenerWrapper);
            } catch (Exception | UnsatisfiedLinkError e) {
                uploadErrorMessage(e, "setAiTrainListener");
                ReleaseLogUtil.c(TAG, "setAiTrainListener error: ", LogAnonymous.b(e));
            }
        }
    }

    private void setAiTrainTemplateAndType(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                LogUtil.a(TAG, "setAiTrainTemplateAndType");
                mon.d().b(str, new BaseResponseCallback<Integer>() { // from class: com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager.1
                    @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                    public void onResponse(int i, Integer num) {
                        LogUtil.a(AiTrainModelManager.TAG, "errorCode ", Integer.valueOf(i), " data ", num);
                    }
                });
            }
            mon.d().a(1);
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "setAiTrainTemplateAndType");
            ReleaseLogUtil.c(TAG, "setAiTrainTemplateAndType error: ", LogAnonymous.b(e));
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "destroy()");
        try {
            unloadModel();
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "destroy");
            ReleaseLogUtil.c(TAG, "destroy() error: ", LogAnonymous.b(e));
        }
    }

    private void uploadErrorMessage(Throwable th, String str) {
        String d = DfxUtils.d(Thread.currentThread(), th);
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("AITrainModelManagerException", str + "_" + d);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a(TAG, "onResumeSport()");
        mon.d().a();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport()");
        mon.d().b();
    }
}
