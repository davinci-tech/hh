package com.huawei.pluginsocialshare.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.socialshare.model.CaasShareCallBack;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.interactors.HwCaasShareInteractors;
import defpackage.fdu;
import defpackage.mto;
import defpackage.mud;
import defpackage.mwd;

@ApiDefine(uri = SocialShareCenterApi.class)
@Singleton
/* loaded from: classes6.dex */
public class SocialShareCenterImpl implements SocialShareCenterApi {

    /* renamed from: a, reason: collision with root package name */
    private HwCaasShareInteractors f8525a;

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void exeShare(fdu fduVar, Context context) {
        mto.b().c(fduVar, context);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void exeShare(Context context, int i, fdu fduVar) {
        mto.b().e(context, i, fduVar);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void choosePic(Activity activity) {
        mud.cpy_(activity);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void takePhoto(Activity activity) {
        mud.cpE_(activity);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void startCrop(Activity activity, Intent intent) {
        mud.cpD_(activity, intent);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void startCrop(Activity activity) {
        mud.cpC_(activity);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void saveToLocalGallery(Context context, Bitmap bitmap) {
        mwd.cqD_(context, bitmap);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void initCaas(Context context, CaasShareCallBack caasShareCallBack) {
        HwCaasShareInteractors hwCaasShareInteractors = new HwCaasShareInteractors(context);
        this.f8525a = hwCaasShareInteractors;
        hwCaasShareInteractors.b(caasShareCallBack);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void sendShareMsgInfo(int i, HwShareMsgInfo hwShareMsgInfo) {
        HwCaasShareInteractors hwCaasShareInteractors = this.f8525a;
        if (hwCaasShareInteractors == null) {
            LogUtil.h("Share_SocialShareCenterImpl", "HwCaasShareInteractors is null");
        } else {
            hwCaasShareInteractors.c(i, hwShareMsgInfo);
        }
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void releaseCaas() {
        HwCaasShareInteractors hwCaasShareInteractors = this.f8525a;
        if (hwCaasShareInteractors == null) {
            LogUtil.h("Share_SocialShareCenterImpl", "HwCaasShareInteractors is null");
        } else {
            hwCaasShareInteractors.e();
        }
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public String getShareNickName() {
        return mwd.b();
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void setIsShowUserInfo(boolean z) {
        mwd.e(z);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public boolean isShowUserInfo() {
        return mwd.i();
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void updateShareUserView(String str, View... viewArr) {
        updateShareUserView(str, 4, viewArr);
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void updateShareUserView(String str, int i, View... viewArr) {
        if (isShowUserInfo()) {
            i = 0;
        }
        LogUtil.h("Share_SocialShareCenterImpl", "updateShareUserView tag:", str);
        for (View view : viewArr) {
            if (view == null) {
                LogUtil.h("Share_SocialShareCenterImpl", str + "updateShareUserView view null");
            } else {
                view.setVisibility(i);
            }
        }
    }

    @Override // com.huawei.health.socialshare.api.SocialShareCenterApi
    public void showUserinfoDisableDialog(Context context, int i, int i2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        mwd.cqJ_(context, i, i2, onClickListener, onClickListener2);
    }
}
