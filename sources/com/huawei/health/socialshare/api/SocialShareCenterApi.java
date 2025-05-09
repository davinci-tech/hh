package com.huawei.health.socialshare.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import com.huawei.health.socialshare.model.CaasShareCallBack;
import defpackage.fdu;

/* loaded from: classes4.dex */
public interface SocialShareCenterApi {
    void choosePic(Activity activity);

    void exeShare(Context context, int i, fdu fduVar);

    void exeShare(fdu fduVar, Context context);

    String getShareNickName();

    void initCaas(Context context, CaasShareCallBack caasShareCallBack);

    boolean isShowUserInfo();

    void releaseCaas();

    void saveToLocalGallery(Context context, Bitmap bitmap);

    void sendShareMsgInfo(int i, HwShareMsgInfo hwShareMsgInfo);

    void setIsShowUserInfo(boolean z);

    void showUserinfoDisableDialog(Context context, int i, int i2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);

    void startCrop(Activity activity);

    void startCrop(Activity activity, Intent intent);

    void takePhoto(Activity activity);

    void updateShareUserView(String str, int i, View... viewArr);

    void updateShareUserView(String str, View... viewArr);
}
