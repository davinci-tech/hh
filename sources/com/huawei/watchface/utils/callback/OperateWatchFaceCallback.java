package com.huawei.watchface.utils.callback;

import android.content.Context;

/* loaded from: classes7.dex */
public interface OperateWatchFaceCallback {
    void callGetWatchFaceDeleteIdJsFunction();

    void callTransmitProgressJsFunction(String str, int i, String str2);

    void cancelSearchImage(boolean z, boolean z2);

    void chooseToClip(int i, boolean z);

    Context getCustomWebViewContext();

    int getSoftKeyboardHeight();

    void handleCallbackResult(String str, int i);

    void hideLoadingDialog();

    void notifyGetShortCutIsCreate();

    void notifyGetWatchfaceStore();

    void notifyKaleidoscopePackageName(String str);

    void notifyPhotoPackageName(String str);

    void notifyRefreshPage(String str);

    void notifyVideoAlbumPackageName(String str);

    void notifyWatchfaceModeChange(int i);

    void notifyWearAlbumPackageName(String str);

    void reselectionWearTransmit();

    void resetKaleidoscopeCurrentMaxIndex();

    void resetWatchFaceAlbumInfoStatus(int i);

    void saveSuccessComeback();

    void setWatchFaceConfirmButton();

    void showLoadingDialog(String str);

    void showToast(String str);

    void transmitAppGoBack();

    void transmitCancelInstallWatchFaceResult(String str, String str2, int i);

    void transmitDecryptResult(int i, String str);

    void transmitDeleteWatchFaceResult(String str, String str2, int i);

    void transmitDeviceConnectState(boolean z, String str);

    void transmitDownloadProgressWatchFace(String str, int i, String str2);

    void transmitDownloadUrl(int i, String str);

    void transmitDownloadWatchFaceResult(String str, String str2, int i);

    void transmitFinishPay(String str);

    void transmitGetResult();

    void transmitHealthChildStatusChange(String str);

    void transmitInstallWatchFaceResult(String str, String str2, int i);

    void transmitLoadingOpen();

    void transmitMembershipDialogStatus();

    void transmitPayWatchFaceResult(String str, String str2, String str3);

    void transmitRefreshPullData();

    void transmitSearchToImgCut();

    void transmitSelectImageFailed();

    void transmitSetDefaultWatchFaceResult(String str, String str2, int i);

    void transmitTryOutOver(String str, String str2, String str3);

    void transmitUploadImageResult(int i, String str);

    void transmitUserTokenUpdate();

    void transmitWatchFaceAlbumApplyResult(int i);

    void transmitWatchFaceAlbumInstallResult(int i);

    void transmitWatchFaceGoBack();

    void transmitWatchFaceKaleidoscopeInfo(String str);

    void transmitWatchFaceKaleidoscopeInstallResult(int i);

    void transmitWatchFaceKaleidoscopeLoadingProgress(String str);

    void transmitWatchFaceLoginResult(String str);

    void transmitWatchFaceNames(String str);

    void transmitWatchFaceThemeAlbumInfo(String str);

    void transmitWatchFaceThemeAlbumLoadingProgress(String str);

    void transmitWatchFaceThemeWearInfo(String str);

    void transmitWatchFaceThemeWearLoadingProgress(String str);

    void transmitWatchFaceTimeWidgetColor(int i);

    void transmitWatchFaceVideoAlbumInfo(String str);

    void transmitWatchFaceVideoAlbumLoadingProgress(String str);

    void transmitWatchFaceVideoInstallResult(int i);

    void transmitWatchFaceWearInstallResult(int i);

    void transmitWatchInfoChange(int i);
}
