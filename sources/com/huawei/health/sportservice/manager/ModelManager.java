package com.huawei.health.sportservice.manager;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.Display;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper;
import com.huawei.pluginsport.airopeskipping.OnJumpRopeListenerWrapper;
import defpackage.fgj;
import defpackage.mwo;
import defpackage.mwq;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 1, name = ComponentName.MODEL_MANAGER)
/* loaded from: classes8.dex */
public class ModelManager implements ManagerComponent {
    private static final int CROSS_JUMP_EXAM_TYPE = 4;
    private static final int INIT_MODEL_FAIL = 1;
    private static final int INIT_MODEL_SUCCESS = 0;
    private static final int LONG_JUMP_EXAM_TYPE = 3;
    private static final int PREPARE_STAGE = 0;
    private static final int SPORT_STAGE = 1;
    private static final int STAND_FLEXION_EXAM_TYPE = 1;
    private static final String STAND_FLEXION_MODEL_EXCEPTION = "StandFlexionModelManagerException";
    private static final int SUPINE_LEG_RAISE_EXAM_TYPE = 2;
    private static final String TAG = "SportService_ModelManager";
    private fgj mAiRopeModelInfo;
    private fgj mLastAiRopeModelInfo;
    private int mSportType;
    private boolean mHasInitAiModel = false;
    private int mInitModelStatus = 1;
    private Context mContext = BaseApplication.e();

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        this.mSportType = BaseSportManager.getInstance().getSportType();
        if (SportParamsType.AI_ROPE_PARAS.equals(sportParamsType) && (obj instanceof fgj)) {
            updateAiRopeModelInfo(obj);
            return;
        }
        if (SportParamsType.AI_ROPE_INPUT_IMAGE.equals(sportParamsType) && (obj instanceof Image)) {
            setImage((Image) obj);
            return;
        }
        if (SportParamsType.AI_ROPE_SOURCE_LISTENER.equals(sportParamsType) && (obj instanceof OnJumpRopeListenerWrapper)) {
            setAiRopeListener((OnJumpRopeListenerWrapper) obj);
        }
        if (SportParamsType.AI_SPORT_EXAM_SOURCE_LISTENER.equals(sportParamsType) && (obj instanceof OnSportCodeListenerWrapper)) {
            setStandFlexionListener((OnSportCodeListenerWrapper) obj);
        }
    }

    private void updateAiRopeModelInfo(Object obj) {
        fgj fgjVar = this.mAiRopeModelInfo;
        if (fgjVar != null && fgjVar.equals(obj)) {
            LogUtil.a(TAG, "mAiRopeModelInfo is not null or no change");
            return;
        }
        this.mAiRopeModelInfo = (fgj) obj;
        if (this.mHasInitAiModel) {
            setModelInfo();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.AI_ROPE_PARAS.equals(sportParamsType) || SportParamsType.AI_ROPE_INPUT_IMAGE.equals(sportParamsType) || SportParamsType.AI_ROPE_SOURCE_LISTENER.equals(sportParamsType) || SportParamsType.AI_SPORT_EXAM_SOURCE_LISTENER.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.manager.ModelManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ModelManager.this.m466xd8d9d06e();
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-ModelManager, reason: not valid java name */
    /* synthetic */ void m466xd8d9d06e() {
        BaseSportManager.getInstance().setIsAlreadyInit(false);
        if (isAiForwardFlexSportType()) {
            initStandFlexionProxy();
        } else {
            initAiRopeProxy();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initAiRopeProxy() {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            fgj r2 = r6.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            if (r2 == 0) goto L3a
            int r2 = r2.c()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            if (r2 == 0) goto L3a
            fgj r2 = r6.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            int r2 = r2.a()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            if (r2 != 0) goto L15
            goto L3a
        L15:
            mwq r2 = defpackage.mwq.d()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            android.content.Context r3 = r6.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            fgj r4 = r6.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            int r4 = r4.c()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            fgj r5 = r6.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            int r5 = r5.a()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            int r2 = r2.initModel(r3, r4, r5)     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r6.mInitModelStatus = r2     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            mwq r2 = defpackage.mwq.d()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r2.setStage(r0)     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r6.mHasInitAiModel = r1     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r6.setModelInfoAfterInit()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            goto L62
        L3a:
            mwq r2 = defpackage.mwq.d()     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            android.content.Context r3 = r6.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r4 = 1080(0x438, float:1.513E-42)
            r5 = 2160(0x870, float:3.027E-42)
            int r2 = r2.initModel(r3, r4, r5)     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            r6.mInitModelStatus = r2     // Catch: java.lang.UnsatisfiedLinkError -> L4b java.lang.Exception -> L4d
            goto L62
        L4b:
            r2 = move-exception
            goto L4e
        L4d:
            r2 = move-exception
        L4e:
            java.lang.String r3 = "onPreSport"
            r6.uploadErrorMessage(r2, r3)
            java.lang.String r3 = "onPreSport() error: "
            java.lang.String r2 = health.compact.a.LogAnonymous.b(r2)
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            java.lang.String r3 = "SportService_ModelManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r3, r2)
        L62:
            com.huawei.health.sportservice.manager.BaseSportManager r2 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            int r3 = r6.mInitModelStatus
            if (r3 != 0) goto L6b
            r0 = r1
        L6b:
            r2.setIsAlreadyInit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.manager.ModelManager.initAiRopeProxy():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initStandFlexionProxy() {
        /*
            r7 = this;
            java.lang.String r0 = "SportService_ModelManager"
            r1 = 0
            r2 = 1
            fgj r3 = r7.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            if (r3 == 0) goto L4a
            int r3 = r3.c()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            if (r3 == 0) goto L4a
            fgj r3 = r7.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r3 = r3.a()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            if (r3 != 0) goto L17
            goto L4a
        L17:
            mwo r3 = defpackage.mwo.d()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r4 = r7.getExamType()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r3.setExamType(r4)     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            mwo r3 = defpackage.mwo.d()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            android.content.Context r4 = r7.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            fgj r5 = r7.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r5 = r5.c()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            fgj r6 = r7.mAiRopeModelInfo     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r6 = r6.a()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r3 = r3.initModel(r4, r5, r6)     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r7.mInitModelStatus = r3     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            mwo r3 = defpackage.mwo.d()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r3.setExamStage(r1)     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r7.mHasInitAiModel = r2     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r7.setSurfaceRotation()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r7.setModelInfo()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            goto L7b
        L4a:
            mwo r3 = defpackage.mwo.d()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            int r4 = r7.getExamType()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r3.setExamType(r4)     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            mwo r3 = defpackage.mwo.d()     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            android.content.Context r4 = r7.mContext     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r5 = 1080(0x438, float:1.513E-42)
            r6 = 2160(0x870, float:3.027E-42)
            int r3 = r3.initModel(r4, r5, r6)     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            r7.mInitModelStatus = r3     // Catch: java.lang.UnsatisfiedLinkError -> L66 java.lang.Exception -> L68
            goto L7b
        L66:
            r3 = move-exception
            goto L69
        L68:
            r3 = move-exception
        L69:
            java.lang.String r4 = "onPreSport"
            r7.uploadErrorMessage(r3, r4)
            java.lang.String r4 = "onPreSport() error: "
            java.lang.String r3 = health.compact.a.LogAnonymous.b(r3)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r3)
        L7b:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = "initProxy() mInitModelStatus: "
            r3[r1] = r4
            int r4 = r7.mInitModelStatus
            if (r4 != 0) goto L88
            r4 = r2
            goto L89
        L88:
            r4 = r1
        L89:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3[r2] = r4
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r3)
            com.huawei.health.sportservice.manager.BaseSportManager r0 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            int r3 = r7.mInitModelStatus
            if (r3 != 0) goto L9b
            r1 = r2
        L9b:
            r0.setIsAlreadyInit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.manager.ModelManager.initStandFlexionProxy():void");
    }

    private void setSurfaceRotation() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h(TAG, "setSurfaceRotation activity == null");
            return;
        }
        Display defaultDisplay = wa_.getWindowManager().getDefaultDisplay();
        if (defaultDisplay == null) {
            LogUtil.h(TAG, "setSurfaceRotation display == null");
        } else {
            int rotation = defaultDisplay.getRotation();
            ReleaseLogUtil.e(TAG, "setSurfaceRotation result:", Integer.valueOf(mwo.d().setSurfaceRotation(rotation)), "rotation:", Integer.valueOf(rotation));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        try {
            if (isAiForwardFlexSportType()) {
                mwo.d().setExamStage(1);
            } else {
                mwq.d().setStage(1);
            }
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "onStartSport");
            ReleaseLogUtil.c(TAG, "onStartSport() error: ", LogAnonymous.b(e));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        try {
            if (isAiForwardFlexSportType()) {
                mwo.d().setExamStage(1);
            }
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "onResumeSport");
            ReleaseLogUtil.c(TAG, "onResumeSport() error: ", LogAnonymous.b(e));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        try {
            if (isAiForwardFlexSportType()) {
                mwo.d().setExamStage(0);
            }
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "onPauseSport");
            ReleaseLogUtil.c(TAG, "onPauseSport() error: ", LogAnonymous.b(e));
        }
    }

    private void setImage(Image image) {
        try {
            if (isAiForwardFlexSportType()) {
                mwo.d().setImage(image);
            } else {
                mwq.d().setImage(image);
            }
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "setImage");
            ReleaseLogUtil.c(TAG, "setImage() error: ", LogAnonymous.b(e));
        }
    }

    private boolean isAiForwardFlexSportType() {
        return SportSupportUtil.e(this.mSportType);
    }

    private void setModelInfoAfterInit() {
        fgj fgjVar = this.mLastAiRopeModelInfo;
        if (fgjVar != null && fgjVar.equals(this.mAiRopeModelInfo)) {
            LogUtil.a(TAG, "mLastAiRopeModelInfo equals mAiRopeModelInfo");
        } else {
            setModelInfo();
        }
    }

    private int setModelInfo() {
        fgj fgjVar = this.mAiRopeModelInfo;
        if (fgjVar == null) {
            LogUtil.a(TAG, "mAiRopeModelInfo == null");
            return 1;
        }
        try {
            this.mLastAiRopeModelInfo = fgjVar;
            if (isAiForwardFlexSportType()) {
                return mwo.d().setModelInfo(this.mAiRopeModelInfo.c(), this.mAiRopeModelInfo.a(), this.mAiRopeModelInfo.b());
            }
            return mwq.d().setModelInfo(this.mAiRopeModelInfo.c(), this.mAiRopeModelInfo.a(), this.mAiRopeModelInfo.b());
        } catch (Exception | UnsatisfiedLinkError e) {
            uploadErrorMessage(e, "setModelInfo");
            ReleaseLogUtil.c(TAG, "setModelInfo() error: ", LogAnonymous.b(e));
            this.mLastAiRopeModelInfo = null;
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
        if (isAiForwardFlexSportType()) {
            mwo.d().unloadModel();
        } else {
            mwq.d().unloadModel();
        }
    }

    public void setAiRopeListener(OnJumpRopeListenerWrapper onJumpRopeListenerWrapper) {
        if (onJumpRopeListenerWrapper != null) {
            try {
                mwq.d().setRopeListener(onJumpRopeListenerWrapper);
            } catch (Exception | UnsatisfiedLinkError e) {
                uploadErrorMessage(e, "setAiRopeListener");
                ReleaseLogUtil.c(TAG, "setAiRopeListener() error: ", LogAnonymous.b(e));
            }
        }
    }

    public void setStandFlexionListener(OnSportCodeListenerWrapper onSportCodeListenerWrapper) {
        if (onSportCodeListenerWrapper != null) {
            try {
                mwo.d().setSportCodeListener(onSportCodeListenerWrapper);
            } catch (Exception | UnsatisfiedLinkError e) {
                uploadErrorMessage(e, "setStandFlexionListener");
                ReleaseLogUtil.c(TAG, "setStandFlexionListener error: ", LogAnonymous.b(e));
            }
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
        if (isAiForwardFlexSportType()) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(STAND_FLEXION_MODEL_EXCEPTION, str + "_" + d);
            return;
        }
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("AIRopeModelManagerException", str + "_" + d);
    }

    private int getExamType() {
        LogUtil.a(TAG, "getExamType(), sportType", Integer.valueOf(this.mSportType));
        switch (this.mSportType) {
            case 400001:
                return 2;
            case 400002:
                return 3;
            case 400003:
                return 4;
            default:
                return 1;
        }
    }
}
