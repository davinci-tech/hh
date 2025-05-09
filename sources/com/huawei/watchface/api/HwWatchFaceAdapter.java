package com.huawei.watchface.api;

import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.view.View;
import com.huawei.watchface.mvp.model.datatype.TransFileInfo;
import com.huawei.watchface.mvp.model.datatype.TransferBusiType;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public interface HwWatchFaceAdapter extends PluginBaseAdapter {
    void commonStopTransfer(String str, int i, IBaseResponseCallback iBaseResponseCallback);

    void commonTransferFile(TransferBusiType transferBusiType, TransFileInfo transFileInfo, ParcelFileDescriptor parcelFileDescriptor, int i, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback);

    void commonTransferFile(TransferBusiType transferBusiType, TransFileInfo transFileInfo, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback);

    void commonTransferFile(String str, String str2, int i, IFileTransferStateCallback iFileTransferStateCallback);

    String getAccessToken();

    String getCommonCountryCode();

    Map<String, String> getDeviceInfo();

    List<Map<String, String>> getDisableWearInfo();

    Map<String, Object> getHealthSettingInfo();

    Map<String, Integer> getHealthVersionType();

    Map<String, Object> getHmsLiteAccountInfo(Context context);

    boolean getIsTaskExecuting();

    String getServerToken();

    int getServerTokenType();

    boolean ifAllowLogin();

    void initFullSdkGrs();

    void installApp(String str);

    boolean isHmsLiteEnable(Context context);

    void launchActivity(Context context, Intent intent);

    void loginByHealthHms(Context context, ILoginCallback iLoginCallback);

    void loginByHealthHmsLite(Context context, IBaseResponseCallback iBaseResponseCallback);

    void printLog(String str, String str2, int i, String str3);

    void printReleaseLog(String str, String str2, int i, String str3);

    void refreshAccessToken(ILoginCallback iLoginCallback);

    void registerHealthResponseListener(WatchFaceHealthResponseListener watchFaceHealthResponseListener);

    void sendBluetoothCommand(int i, int i2, ByteBuffer byteBuffer);

    void shareLink(String str);

    void showHealthAppSettingGuide(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);

    void startRequestFile(String str, int i, boolean z, IPhotoFileCallback iPhotoFileCallback);

    void unRegisterHealthResponseListener();
}
