package com.huawei.watchface.api;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.util.SafeStringBuilder;
import com.huawei.up.model.UserInfomation;
import com.huawei.watchface.an;
import com.huawei.watchface.api.BatchReportRepository;
import com.huawei.watchface.d;
import com.huawei.watchface.da;
import com.huawei.watchface.dg;
import com.huawei.watchface.dp;
import com.huawei.watchface.dz;
import com.huawei.watchface.ec;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.h;
import com.huawei.watchface.i;
import com.huawei.watchface.j;
import com.huawei.watchface.m;
import com.huawei.watchface.manager.BaseWatchFaceManager;
import com.huawei.watchface.manager.WatchFaceKaleidoscopeManager;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.manager.WatchFaceWearManager;
import com.huawei.watchface.mvp.model.datatype.CommandJsonInfo;
import com.huawei.watchface.mvp.model.datatype.DeviceCapability;
import com.huawei.watchface.mvp.model.datatype.DeviceInfo;
import com.huawei.watchface.mvp.model.datatype.KaleidoscopeStruct;
import com.huawei.watchface.mvp.model.datatype.RegisterInfo;
import com.huawei.watchface.mvp.model.datatype.ScreenInfo;
import com.huawei.watchface.mvp.model.datatype.Tlv;
import com.huawei.watchface.mvp.model.datatype.TlvException;
import com.huawei.watchface.mvp.model.datatype.TlvFather;
import com.huawei.watchface.mvp.model.datatype.TlvUtils;
import com.huawei.watchface.mvp.model.datatype.VideoStruct;
import com.huawei.watchface.mvp.model.datatype.WatchDeviceInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceKaleidoscopeInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFacePhotoInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceVideoInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceWearInfo;
import com.huawei.watchface.mvp.model.datatype.WatchInfoForH5;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.datatype.WearStruct;
import com.huawei.watchface.mvp.model.filedownload.FilePuller;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.mvp.model.helper.systemparam.SystemParamNames;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceThemeAlbumInfo;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import com.huawei.watchface.utils.callback.IAuthDenyCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener;
import defpackage.lsk;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HwWatchFaceBtManager {
    private static final int BACKGROUND_IMAGE_INDEX = 4;
    private static final int BACKGROUND_IMAGE_LIST = 2;
    private static final int BACKGROUND_IMAGE_NAME = 5;
    private static final int BACKGROUND_IMAGE_NUM = 1;
    private static final int BACKGROUND_IMAGE_STRUCT = 3;
    private static final int CAN_INTELLECT_COLOR_VALUE = 1;
    private static final String CLASS_NAME_OF_HANDOFF_SDK = "lsk";
    private static final int DEFAULT_TAG_LENGTH = 1;
    private static final int DEFAULT_TAG_LENGTH_64 = 8;
    private static final int DEVICE_CONNECT_STATUS_RESPONSE = 2;
    private static final int GET_CLOCK_TYPE_CAPABILITY = 5;
    private static final int GET_CUR_STYLE_INDEX = 10;
    private static final int GET_KALEIDOSCOPE_CURRENT_MATERIAL_IMAGE_INDEX = 7;
    private static final int GET_KALEIDOSCOPE_HANDS_STYLE_INDEX = 8;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_INDEX = 5;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_LIST = 3;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_NAME = 6;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_STRUCT = 4;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_TYPE = 2;
    private static final int GET_KALEIDOSCOPE_MAX_MATERIAL_IMAGES = 1;
    private static final int GET_KALEIDOSCOPE_TYPE = 9;
    private static final int GET_MAX_STYLE_NUM = 1;
    private static final int GET_PREVIEW_NAME = 9;
    private static final int GET_TINK_CLOCK_OPTION = 12;
    private static final int GET_WEAR_IMAGE_OPTION = 11;
    private static final int GET_WEAR_IMAGE_RADIUS = 13;
    private static final int GET_WEAR_STYLE_ID = 8;
    private static final int GET_WEAR_STYLE_LIST = 6;
    private static final int GET_WEAR_STYLE_NUM = 2;
    private static final int GET_WEAR_STYLE_STRUCT = 7;
    private static final int GET_WEAR_STYLE_TYPE = 3;
    private static final int GET_WEAR_TYPE_CAPABILITY = 4;
    private static final int MAX_VIDEO_NUM = 1;
    private static final int NEED_TRANSFER_NUM = 8;
    public static final int NOT_SUPPORT_DIAL_UNITE = 0;
    private static final int PORTRAIT_MODE = 13;
    private static final int PORTRAIT_MODE_VALUE = 11;
    private static final int PORT_POSITION_INDEX = 12;
    private static final int PORT_POSITION_INDEX_VALUE = 10;
    private static final int POSITION_INDEX_VALUE = 6;
    private static final int SET_BG_IMAGE_NAME = 7;
    private static final int SET_CLOCK_STYLE_COLOR = 9;
    private static final int SET_CLOCK_STYLE_INDEX = 8;
    private static final int SET_CUR_WEAR_IMAGE_INDEX = 10;
    private static final int SET_KALEIDOSCOPE_CURRENT_MATERIAL_IMAGE_INDEX = 6;
    private static final int SET_KALEIDOSCOPE_HANDS_STYLE_INDEX = 7;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_INDEX = 4;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_LIST = 2;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_NAME = 5;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_NUM = 1;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_STRUCT = 3;
    private static final int SET_KALEIDOSCOPE_TRANSFER_NUM = 9;
    private static final int SET_KALEIDOSCOPE_TYPE = 8;
    private static final int SET_PREVIEW_NAME_VALUE = 5;
    private static final int SET_WEAR_STYLE_ID_VALUE = 4;
    private static final int SET_WEAR_STYLE_LIST_VALUE = 2;
    private static final int SET_WEAR_STYLE_NUM_VALUE = 1;
    private static final int SET_WEAR_STYLE_STRUCT_VALUE = 3;
    private static final int SET_WEAR_TRANSFER_NUM = 11;
    private static final int SET_WEAR_TYPE_VALUE = 6;
    private static final int STYLE_INDEX_VALUE = 7;
    public static final int SUPPORT_DIAL_UNITE = 5;
    private static final String TAG = "HwWatchFaceBtManager";
    private static final int TRANSFER_PREVIEW_NUM = 13;
    private static final int TRANSFER_VIDEO_NUM = 14;
    private static final int VALUE_TYPE_INDEX_VALUE = 9;
    private static final int VIDEO_ATTRIBUTE = 7;
    private static final int VIDEO_INDEX = 5;
    private static final int VIDEO_LIST = 3;
    private static final int VIDEO_NAME = 6;
    private static final int VIDEO_NUM = 1;
    private static final int VIDEO_POSITION_INDEX = 10;
    private static final int VIDEO_PREVIEW_NAME = 8;
    private static final int VIDEO_STATUS = 9;
    private static final int VIDEO_STRUCT = 4;
    private static final int VIDEO_STYLE_INDEX = 11;
    private static final int VIDEO_TYPE = 2;
    private static final int VIDEO_VALUE_TYPE_INDEX = 12;
    private static final int WATCH_FACE_WEAR = 4;
    private static final int WATCH_FACE_WEAR_TYPE = 1;
    private static volatile HwWatchFaceBtManager sInstance;
    List<BaseWatchFaceManager> destroyListeners;
    private boolean isGetAlbumPackageName;
    private boolean isGetKaleidoscopePackageName;
    private boolean isGetVideoAlbumPackageName;
    private boolean isGetWatchfaceLocal;
    private boolean isGetWearAlbumPackageName;
    private IAuthDenyCallback mAuthDenyCallback;
    private Context mContext;
    private Map<String, String> mDefaultWatchInfo;
    private boolean mIsJudgeSecondAuth;
    private boolean mIsSupportWatchFaceAppId;
    private int mKaleidoscopeOptionType;
    private int mPhotoOptionType;
    private int mVideoOptionType;
    private WatchFaceSupportInfo mVirtualWatchFaceSupportInfo;
    private List<WatchDeviceInfo> mWatchDeviceInfoList;
    private WatchFaceSupportInfo mWatchFaceSupportInfo;
    private int mWearOptionType;
    private OperateWatchFaceCallback registerH5Callback;
    private static Map<Integer, List<IBaseResponseCallback>> sCommandCallbacks = new HashMap(20);
    private static IBaseResponseCallback sCallBackForH5 = null;
    private static WatchResourcesInfo mDeviceCurrentWatchFace = null;
    private ConcurrentHashMap<String, RegisterInfo.RegisterBean> registerH5 = new ConcurrentHashMap<>();
    private TlvUtils mTlvUtils = new TlvUtils();
    private WatchFacePhotoInfo mWatchFacePhotoInfo = new WatchFacePhotoInfo();
    private WatchFaceVideoInfo mWatchFaceVideoInfo = new WatchFaceVideoInfo();
    private WatchFaceKaleidoscopeInfo mWatchFaceKaleidoscopeInfo = new WatchFaceKaleidoscopeInfo();
    private WatchFaceWearInfo mWatchFaceWearInfo = new WatchFaceWearInfo();
    private CopyOnWriteArrayList<WatchResourcesInfo> mWatchFaces = new CopyOnWriteArrayList<>();
    private ArrayList<WatchResourcesInfo> mTempWatchFaces = new ArrayList<>(20);
    private HashMap<String, WatchResourcesInfo> mHashWatchFaces = new HashMap<>(20);
    private boolean isTaskExecuting = false;
    private LinkedHashMap<String, IBaseResponseCallback> mOperateCallbacks = new LinkedHashMap<>();
    private WatchFaceHealthResponseListener mHealthResponseListener = new WatchFaceHealthResponseListener() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda0
        @Override // com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener
        public final void onResponse(int i, int i2, Object obj) {
            HwWatchFaceBtManager.this.m878lambda$new$0$comhuaweiwatchfaceapiHwWatchFaceBtManager(i, i2, obj);
        }
    };
    private ResultCallback mResultCallback = new ResultCallback() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda1
        @Override // com.huawei.hihealth.listener.ResultCallback
        public final void onResult(int i, Object obj) {
            HwWatchFaceBtManager.this.m879lambda$new$1$comhuaweiwatchfaceapiHwWatchFaceBtManager(i, obj);
        }
    };

    static /* synthetic */ void lambda$dealReport$3(int i, Object obj) {
    }

    private void reportDeviceInfoStatus() {
    }

    public boolean isTaskExecuting() {
        return this.isTaskExecuting;
    }

    public void setTaskExecuting(boolean z) {
        this.isTaskExecuting = z;
    }

    public void setRegisterH5(String str) {
        String str2 = TAG;
        HwLog.i(str2, "setregisterH5() entry json :" + str);
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "setregisterH5() json is null");
            return;
        }
        dg.b(this.registerH5);
        RegisterInfo registerInfo = (RegisterInfo) GsonHelper.fromJson(str, RegisterInfo.class);
        if (registerInfo == null) {
            HwLog.i(str2, "setregisterH5() registerInfo is null");
            return;
        }
        if (ArrayUtils.isEmpty(registerInfo.getList())) {
            HwLog.i(str2, "setregisterH5() registerInfo.getList() is null");
            return;
        }
        List<RegisterInfo.RegisterBean> list = registerInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            RegisterInfo.RegisterBean registerBean = list.get(i);
            if (registerBean != null) {
                dg.a(this.registerH5, registerBean.getServiceId() + "_" + registerBean.getCommandId(), registerBean);
            }
        }
    }

    public void clearRegisterH5() {
        dg.b(this.registerH5);
    }

    public void setRegisterH5Callback(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.registerH5Callback = operateWatchFaceCallback;
    }

    /* renamed from: lambda$new$0$com-huawei-watchface-api-HwWatchFaceBtManager, reason: not valid java name */
    /* synthetic */ void m878lambda$new$0$comhuaweiwatchfaceapiHwWatchFaceBtManager(int i, int i2, Object obj) {
        if (obj == null) {
            HwLog.e(TAG, "mHealthResponseListener() objData is null.");
            return;
        }
        HwLog.i(TAG, "mHealthResponseListener() responseType: " + i + ", statusCode: " + i2);
        if (obj instanceof byte[]) {
            handleCallbackResult((byte[]) obj);
        }
        if (i == 2) {
            assemblingBtConnectInfo(i2);
            WatchFacePhotoAlbumManager.getInstance(this.mContext).a(false, false);
            WatchFaceWearManager.getInstance(this.mContext).a(false, false);
            WatchFaceKaleidoscopeManager.getInstance(this.mContext).a(false, false);
        }
    }

    private void assemblingBtConnectInfo(int i) {
        HwLog.i(TAG, "assemblingBtConnectInfo() statusCode: " + i);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mDeviceConnectState", i);
            jSONObject.put("mDeviceName", HwWatchFaceApi.getInstance(this.mContext).getDeviceName());
            notifyBtConnectStatus(jSONObject.toString());
        } catch (JSONException unused) {
            HwLog.e(TAG, "assemblingBtConnectInfo()() JSONException occured.");
        }
    }

    /* renamed from: lambda$new$1$com-huawei-watchface-api-HwWatchFaceBtManager, reason: not valid java name */
    /* synthetic */ void m879lambda$new$1$comhuaweiwatchfaceapiHwWatchFaceBtManager(int i, Object obj) {
        String str = TAG;
        HwLog.i(str, "ResultCallback() errorCode: " + i);
        if (1001 == i || 1002 == i) {
            if (this.mAuthDenyCallback == null) {
                HwLog.e(str, "ResultCallback() mAuthDenyCallback is null.");
                return;
            }
            Map<Integer, List<IBaseResponseCallback>> map = sCommandCallbacks;
            if (map == null || map.size() <= 0) {
                return;
            }
            if (this.mIsJudgeSecondAuth) {
                this.mAuthDenyCallback.onRequestAuthDeny(true);
                this.mIsJudgeSecondAuth = false;
                return;
            } else {
                this.mAuthDenyCallback.onRequestAuthDeny(false);
                return;
            }
        }
        if (!(obj instanceof String)) {
            HwLog.e(str, "ResultCallback() invalid object.");
            return;
        }
        String str2 = (String) obj;
        if (TextUtils.isEmpty(str2)) {
            HwLog.w(str, "ResultCallback() object is empty.");
            return;
        }
        if (i != 800) {
            if (i == 801) {
                notifyBtConnectStatus(str2);
                return;
            } else {
                HwLog.e(str, "ResultCallback() invalid errorCode.");
                return;
            }
        }
        if (str2.length() < CommonUtils.w()) {
            HwLog.i(str, "ResultCallback() data is error.");
        } else {
            HwLog.i(str, "ResultCallback() COMMAND_TYPE_WATCH_FACE.");
            handleCallbackResult(da.a(str2));
        }
    }

    private HwWatchFaceBtManager(Context context) {
        HwLog.i(TAG, "HwWatchFaceBtManager() enter.");
        this.mContext = context;
        registerHealthResponseListener();
        i.a(this.mContext).a(this.mResultCallback);
        j.a(this.mContext).a(this.mResultCallback);
    }

    public static HwWatchFaceBtManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (HwWatchFaceBtManager.class) {
                if (sInstance == null) {
                    Environment.setApplication((Application) context.getApplicationContext());
                    sInstance = new HwWatchFaceBtManager(Environment.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static Object getCommandCallbackCallbackList() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (HwWatchFaceBtManager.class) {
            map = sCommandCallbacks;
        }
        return map;
    }

    private static IBaseResponseCallback getCallBackForH5() {
        return sCallBackForH5;
    }

    public static void setCallBackForH5(IBaseResponseCallback iBaseResponseCallback) {
        sCallBackForH5 = iBaseResponseCallback;
    }

    private void handleCallbackResult(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            HwLog.w(TAG, "handleCallbackResult() data illegal");
            return;
        }
        if (Environment.sIsAarInTheme && !isCommandForThemeWatch(bArr)) {
            HwLog.w(TAG, "handleCallbackResult() command not for theme watch.");
            return;
        }
        int a2 = da.a(bArr[0]);
        String str = TAG;
        HwLog.i(str, "handleCallbackResult() serviceId: " + a2);
        if (a2 == 39) {
            if (needLocalSendBluetooth(a2, bArr)) {
                handleWatchFaceCallbackResult(bArr);
            }
        } else if (a2 == 40) {
            if (needLocalSendBluetooth(a2, bArr)) {
                i.a(this.mContext).a(bArr);
            }
        } else {
            if (a2 == 44) {
                if (needLocalSendBluetooth(a2, bArr)) {
                    j.a(this.mContext).a(bArr);
                    return;
                }
                return;
            }
            HwLog.w(str, "handleCallbackResult() ServiceId illegal");
        }
    }

    private boolean needLocalSendBluetooth(int i, byte[] bArr) {
        try {
            byte b = bArr[1];
            String str = i + "_" + ((int) b);
            if (!dg.a((Map<?, ?>) this.registerH5, (Object) str) || this.registerH5Callback == null) {
                return true;
            }
            RegisterInfo.RegisterBean registerBean = this.registerH5.get(str);
            String a2 = da.a(bArr);
            String str2 = TAG;
            HwLog.w(str2, "handleCallbackResult() registerH5 : " + registerBean.getBluetoothTlvName() + ":::" + i + "_" + ((int) b) + ":::resultInfo::" + a2);
            this.registerH5Callback.handleCallbackResult(a2, b);
            if (registerBean == null || registerBean.getType() != 1) {
                return false;
            }
            HwLog.w(str2, "handleCallbackResult() registerH5 and aar :" + registerBean.getBluetoothTlvName());
            return true;
        } catch (Exception e) {
            HwLog.e(TAG, "needLocalSendBluetooth() error :" + HwLog.printException(e));
            return true;
        }
    }

    private boolean isCommandForThemeWatch(byte[] bArr) {
        String substring = SafeString.substring(da.a(bArr), 4, CommonUtils.w());
        HwLog.i(TAG, "isCommandForThemeWatch() appId: " + substring);
        return substring.equalsIgnoreCase(CommonUtils.c());
    }

    private void handleWatchFaceCallbackResult(byte[] bArr) {
        byte b = bArr[1];
        String str = TAG;
        HwLog.w(str, "handleCallbackResult() commandId: " + ((int) b));
        switch (b) {
            case 1:
                HwWatchFaceManager.getInstance(this.mContext).setIsBtConnect(true);
                handleDeviceInfo(bArr);
                writeWatchFaceSupportInfo2Sp();
                reportSuccess();
                break;
            case 2:
                handleFaceInfo(bArr);
                break;
            case 3:
                handleApply(bArr);
                break;
            case 4:
                handleErrorCode(bArr, 4);
                break;
            case 5:
                handleReportStatus(bArr);
                break;
            case 6:
                handleNameInfo(bArr);
                break;
            case 7:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                HwLog.w(str, "handleCallbackResult() illegal CommandId");
                break;
            case 8:
                handlePhotoInfo(bArr);
                break;
            case 9:
                handlePhotoStatus(bArr);
                break;
            case 15:
                handleVideoInfo(bArr);
                break;
            case 16:
                handleVideoStatus(bArr);
                break;
            case 17:
                handleKaleidoscopeInfo(bArr);
                break;
            case 18:
                handleKaleidoscopeStatus(bArr);
                break;
            case 19:
                handleWearInfo(bArr);
                break;
            case 20:
                handleWearStatus(bArr);
                break;
        }
    }

    private void handleDeviceInfo(byte[] bArr) {
        synchronized (this) {
            resetWatchFaceSupportInfo();
            if (this.mWatchFaceSupportInfo == null) {
                this.mWatchFaceSupportInfo = new WatchFaceSupportInfo();
            }
            String a2 = da.a(bArr);
            String str = TAG;
            HwLog.i(str, "5.39.1 handleDeviceInfo() info: " + a2);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("errorCode", String.valueOf(System.currentTimeMillis()));
            linkedHashMap.put(BaseEvent.KEY_DESCINFO, a2);
            linkedHashMap.put("title", "5.39.1");
            new ec("tlv", linkedHashMap).a_();
            try {
                TlvFather builderTlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w()));
                List<Tlv> tlvList = builderTlvList.getTlvList();
                if (tlvList != null && !tlvList.isEmpty()) {
                    for (Tlv tlv : tlvList) {
                        handleDeviceInfoTlv(IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                    }
                } else {
                    HwLog.w(str, "handleDeviceInfo() tlv error");
                }
                reportDeviceInfoStatus();
                handleDeviceScreenInfoTlv(builderTlvList.getTlvFatherList());
            } catch (TlvException unused) {
                HwLog.e(TAG, "handleDeviceInfo() TlvException occured.");
            }
        }
    }

    private void handleDeviceScreenInfoTlv(List<TlvFather> list) {
        if (ArrayUtils.isEmpty(list)) {
            HwLog.e(TAG, "handleDeviceScreenInfoTlv() tlvFatherList is empty.");
            return;
        }
        HwLog.i(TAG, "handleDeviceScreenInfoTlv() tlvFatherList size: " + list.size());
        TlvFather tlvFather = null;
        TlvFather tlvFather2 = null;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                tlvFather = list.get(i);
            } else if (i == 1) {
                tlvFather2 = list.get(i);
            }
        }
        handleCompatibleTlvInfo(tlvFather);
        handlePurchasedTlvInfo(tlvFather2);
    }

    private void handleCompatibleTlvInfo(TlvFather tlvFather) {
        if (tlvFather != null) {
            List<TlvFather> tlvFatherList = tlvFather.getTlvFatherList();
            ArrayList arrayList = new ArrayList(20);
            if (ArrayUtils.isEmpty(tlvFatherList)) {
                HwLog.e(TAG, "handleCompatibleTlvInfo() compatibleTlvList is empty.");
                this.mWatchFaceSupportInfo.setCompatibleList(arrayList);
                return;
            }
            for (TlvFather tlvFather2 : tlvFatherList) {
                ScreenInfo screenInfo = new ScreenInfo();
                for (Tlv tlv : tlvFather2.getTlvList()) {
                    switch (IntegerUtils.b(tlv.getTag(), 16)) {
                        case 9:
                            screenInfo.setWidth(IntegerUtils.b(tlv.getValue(), 16));
                            HwLog.i(TAG, "handleCompatibleTlvInfo() setWidth:" + screenInfo.getWidth());
                            break;
                        case 10:
                            screenInfo.setHeight(IntegerUtils.b(tlv.getValue(), 16));
                            HwLog.i(TAG, "handleCompatibleTlvInfo() setHeight:" + screenInfo.getHeight());
                            break;
                        case 11:
                            screenInfo.setSupportVersion(CommonUtils.h(da.b(tlv.getValue())));
                            HwLog.i(TAG, "handleCompatibleTlvInfo() getSupportVersion:" + screenInfo.getSupportVersion());
                            break;
                        default:
                            HwLog.w(TAG, "handleCompatibleTlvInfo() invalid type.");
                            break;
                    }
                }
                arrayList.add(screenInfo);
            }
            this.mWatchFaceSupportInfo.setCompatibleList(arrayList);
            return;
        }
        HwLog.e(TAG, "handleCompatibleTlvInfo() compatibleTlvFather is empty.");
    }

    private void handlePurchasedTlvInfo(TlvFather tlvFather) {
        if (tlvFather != null) {
            List<TlvFather> tlvFatherList = tlvFather.getTlvFatherList();
            ArrayList arrayList = new ArrayList(20);
            if (ArrayUtils.isEmpty(tlvFatherList)) {
                HwLog.e(TAG, "handlePurchasedTlvInfo() purchasedTlvList is empty.");
                this.mWatchFaceSupportInfo.setPurchasedList(arrayList);
                return;
            }
            for (TlvFather tlvFather2 : tlvFatherList) {
                ScreenInfo screenInfo = new ScreenInfo();
                for (Tlv tlv : tlvFather2.getTlvList()) {
                    switch (IntegerUtils.b(tlv.getTag(), 16)) {
                        case 14:
                            screenInfo.setWidth(IntegerUtils.b(tlv.getValue(), 16));
                            HwLog.i(TAG, "handlePurchasedTlvInfo() setWidth:" + screenInfo.getWidth());
                            break;
                        case 15:
                            screenInfo.setHeight(IntegerUtils.b(tlv.getValue(), 16));
                            HwLog.i(TAG, "handlePurchasedTlvInfo() setHeight:" + screenInfo.getHeight());
                            break;
                        case 16:
                            screenInfo.setSupportVersion(da.b(tlv.getValue()));
                            HwLog.i(TAG, "handlePurchasedTlvInfo() getSupportVersion:" + screenInfo.getSupportVersion());
                            break;
                        default:
                            HwLog.w(TAG, "handlePurchasedTlvInfo() invalid type.");
                            break;
                    }
                }
                arrayList.add(screenInfo);
            }
            this.mWatchFaceSupportInfo.setPurchasedList(arrayList);
            return;
        }
        HwLog.e(TAG, "handlePurchasedTlvInfo() purchasedTlvFather is empty.");
    }

    private void writeWatchFaceSupportInfo2Sp() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return;
        }
        dz.a("watch_max_version", watchFaceSupportInfo.getWatchFaceMaxVersion());
        dz.a("watch_other_version", watchFaceSupportInfo.getOtherWatchFaceVersion());
        dz.a("watch_version", watchFaceSupportInfo.getOtherWatchFaceVersion() + "," + watchFaceSupportInfo.getWatchFaceMaxVersion());
        dz.a("watch_screen", watchFaceSupportInfo.getWatchFaceScreen());
        dz.a("supportCapability", watchFaceSupportInfo.getSupportCapability());
    }

    public boolean isCloudOpenSync() {
        String a2 = NewSystemParamManager.a(SystemParamNames.SYSTEM_WATCHFACE_MODEL_SYNC, "", true);
        HwLog.i(TAG, "client_watchface_model_sync :" + a2);
        return "1".equalsIgnoreCase(a2);
    }

    private void handleDeviceInfoTlv(int i, String str) {
        if (i == 17) {
            this.mWatchFaceSupportInfo.setSupportCapability(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleDeviceInfoTlv() setSupportCapability:" + this.mWatchFaceSupportInfo.getSupportCapability());
            return;
        }
        if (i == 18) {
            this.mWatchFaceSupportInfo.setPowerLevel(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleDeviceInfoTlv() setPowerLevel:" + this.mWatchFaceSupportInfo.getPowerLevel());
            return;
        }
        if (i != 127) {
            switch (i) {
                case 1:
                    this.mWatchFaceSupportInfo.setWatchFaceMaxVersion(CommonUtils.h(da.b(str)));
                    HwLog.i(TAG, "handleDeviceInfoTlv() WatchFace_MaxVersion:" + this.mWatchFaceSupportInfo.getWatchFaceMaxVersion());
                    break;
                case 2:
                    this.mWatchFaceSupportInfo.setWatchFaceWidth(IntegerUtils.b(str, 16));
                    HwLog.i(TAG, "handleDeviceInfoTlv() WatchFace_Width:" + this.mWatchFaceSupportInfo.getWatchFaceWidth());
                    break;
                case 3:
                    this.mWatchFaceSupportInfo.setWatchFaceHeight(IntegerUtils.b(str, 16));
                    HwLog.i(TAG, "handleDeviceInfoTlv() setWatchFace_Height:" + this.mWatchFaceSupportInfo.getWatchFaceHeight());
                    break;
                case 4:
                    int b = IntegerUtils.b(str, 16);
                    ArrayList arrayList = new ArrayList(20);
                    if ((b & 1) == 1) {
                        arrayList.add(1);
                    }
                    this.mWatchFaceSupportInfo.setWatchFaceSupportFileType(arrayList);
                    HwLog.i(TAG, "handleDeviceInfoTlv() setWatchFace_SupportFileType:" + this.mWatchFaceSupportInfo.getWatchFaceSupportFileType());
                    break;
                case 5:
                    this.mWatchFaceSupportInfo.setWatchFaceSort(IntegerUtils.b(str, 16) == 1);
                    HwLog.i(TAG, "handleDeviceInfoTlv() setWatchFace_Sort:" + this.mWatchFaceSupportInfo.isWatchFaceSort());
                    break;
                case 6:
                    this.mWatchFaceSupportInfo.setOtherWatchFaceVersion(CommonUtils.h(da.b(str)));
                    HwLog.i(TAG, "handleDeviceInfoTlv() OtherThemeVersion:" + this.mWatchFaceSupportInfo.getOtherWatchFaceVersion());
                    break;
                default:
                    switch (i) {
                        case 20:
                            this.mWatchFaceSupportInfo.setPortraitFieldMode(IntegerUtils.b(str, 16));
                            HwLog.i(TAG, "handleDeviceInfoTlv() setPortraitFieldMode:" + this.mWatchFaceSupportInfo.getPortraitFieldMode());
                            break;
                        case 21:
                            this.mWatchFaceSupportInfo.setSetTimeIndividually(IntegerUtils.b(str, 16));
                            HwLog.i(TAG, "handleDeviceInfoTlv() setSetTimeIndividually:" + this.mWatchFaceSupportInfo.getSetTimeIndividually());
                            break;
                        case 22:
                            this.mWatchFaceSupportInfo.setIsWearSupport(IntegerUtils.b(str, 16));
                            HwLog.i(TAG, "handleDeviceInfoTlv() setIsWearSupport:" + this.mWatchFaceSupportInfo.getIsWearSupport());
                            break;
                        case 23:
                            this.mWatchFaceSupportInfo.setIsSupportSync(IntegerUtils.b(str, 16) == 1);
                            HwLog.i(TAG, "handleDeviceInfoTlv() setIsSupportSync:" + this.mWatchFaceSupportInfo.isIsSupportSync());
                            break;
                        case 24:
                            this.mWatchFaceSupportInfo.setWatchfaceMode(IntegerUtils.b(str, 16));
                            HwLog.i(TAG, "handleDeviceInfoTlv() setCurrentModel:" + this.mWatchFaceSupportInfo.getWatchfaceMode());
                            break;
                        case 25:
                            this.mWatchFaceSupportInfo.setSupportOneTouchAbility(IntegerUtils.b(str, 16) == 1);
                            this.mWatchFaceSupportInfo.setSupportNewAlbum(true);
                            HwLog.i(TAG, "handleDeviceInfoTlv() setSupportOneTouchAbility:" + this.mWatchFaceSupportInfo.isSupportOneTouchAbility());
                            break;
                        default:
                            HwLog.w(TAG, "handleDeviceInfoTlv() default type:" + i);
                            break;
                    }
            }
            return;
        }
        this.mWatchFaceSupportInfo.setCommonErrorCode(IntegerUtils.b(str, 16));
        HwLog.i(TAG, "handleDeviceInfoTlv() setCommonErrorCode:" + this.mWatchFaceSupportInfo.getCommonErrorCode());
    }

    private void reportSuccess() {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(1);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onResponse(101, null);
                }
                sCommandCallbacks.remove(1);
            } else {
                HwLog.w(TAG, "reportSuccess() callback error");
            }
        }
    }

    private void handleFaceInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.2 handleFaceInfo() info: " + a2);
        this.mTempWatchFaces.clear();
        this.mHashWatchFaces.clear();
        try {
            TlvFather tlvFather = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvFatherList().get(0);
            HwLog.i(str, "handleFaceInfo() size:" + tlvFather.getTlvFatherList().size());
            for (TlvFather tlvFather2 : tlvFather.getTlvFatherList()) {
                WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
                for (Tlv tlv : tlvFather2.getTlvList()) {
                    int b = IntegerUtils.b(tlv.getTag(), 16);
                    if (b == 3) {
                        watchResourcesInfo.setWatchInfoId(watchResourcesInfo.markDownVipFreeWatchFace(CommonUtils.h(da.b(tlv.getValue()))));
                        HwLog.i(TAG, "handleFaceInfo() setWatchInfo_Id:" + watchResourcesInfo.getWatchInfoId());
                    } else if (b == 4) {
                        watchResourcesInfo.setWatchInfoVersion(CommonUtils.h(da.b(tlv.getValue())));
                        HwLog.i(TAG, "handleFaceInfo() setWatchInfo_Version:" + watchResourcesInfo.getWatchInfoVersion());
                    } else if (b == 5) {
                        watchResourcesInfo.setWatchInfoType(IntegerUtils.b(tlv.getValue(), 16));
                        HwLog.i(TAG, "handleFaceInfo() setWatchInfo_Type:" + watchResourcesInfo.getWatchInfoType());
                    } else if (b == 7) {
                        watchResourcesInfo.setWatchExpandType(IntegerUtils.b(tlv.getValue(), 16));
                        HwLog.i(TAG, "handleFaceInfo() setWatchExpandType:" + watchResourcesInfo.getWatchExpandType());
                    } else {
                        HwLog.w(TAG, "handleFaceInfo(), nothing to do");
                    }
                }
                d.a().a(watchResourcesInfo);
                HwLog.i(TAG, "handleFaceInfo() setChangeHopidList_Id:" + watchResourcesInfo.getWatchInfoId());
                this.mTempWatchFaces.add(watchResourcesInfo);
                if (watchResourcesInfo.getWatchInfoId() != null) {
                    this.mHashWatchFaces.put(watchResourcesInfo.getWatchInfoId(), watchResourcesInfo);
                }
            }
            d.a().a(this.mHashWatchFaces, this.mTempWatchFaces);
            resetWatchInfoList();
            if (mDeviceCurrentWatchFace != null) {
                HwLog.i(TAG, "handleFaceInfo() mDeviceCurrentWatchFace:" + mDeviceCurrentWatchFace.getWatchInfoId());
            }
            BatchReportRepository.update(this.mContext, mDeviceCurrentWatchFace, new BatchReportRepository.BatchReportUpdateListener() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda2
                @Override // com.huawei.watchface.api.BatchReportRepository.BatchReportUpdateListener
                public final void onWatchFaceInfoUpdated(WatchResourcesInfo watchResourcesInfo2) {
                    HwWatchFaceBtManager.mDeviceCurrentWatchFace = watchResourcesInfo2;
                }
            });
            reportSuccessFaceInfo();
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleFaceInfo() TlvException");
        } catch (IndexOutOfBoundsException unused2) {
            HwLog.e(TAG, "handleFaceInfo() IndexOutOfBoundsException");
        }
    }

    public WatchResourcesInfo getWatchResourcesInfo(String str) {
        return this.mHashWatchFaces.get(str);
    }

    private void resetWatchInfoList() {
        resetWatchFaces();
        notifyH5DiyPackageName();
        t.a().g();
        if (getInstance(Environment.getApplicationContext()).isSupportSync()) {
            dealModeChange();
        }
    }

    private void resetWatchFaces() {
        synchronized (this) {
            this.mWatchFaces.clear();
            this.mWatchFaces.addAll(this.mTempWatchFaces);
            HwLog.i(TAG, "resetWatchInfoList() watchFace size:" + this.mWatchFaces.size());
        }
    }

    private CopyOnWriteArrayList<WatchResourcesInfo> getWatchFaces() {
        CopyOnWriteArrayList<WatchResourcesInfo> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.mWatchFaces;
        }
        return copyOnWriteArrayList;
    }

    private void notifyH5DiyPackageName() {
        if (this.isGetAlbumPackageName) {
            HwWatchFaceManager.getInstance(this.mContext).notifyPhotoPackageName(getPhotoPackageName());
        }
        if (this.isGetKaleidoscopePackageName) {
            HwWatchFaceManager.getInstance(this.mContext).notifyKaleidoscopePackageName(getKaleidoscopePackageName());
        }
        if (this.isGetWearAlbumPackageName) {
            HwWatchFaceManager.getInstance(this.mContext).notifyWearAlbumPackageName(getWearPackageName());
        }
        if (this.isGetVideoAlbumPackageName) {
            HwWatchFaceManager.getInstance(this.mContext).notifyVideoAlbumPackageName(getVideoPackageName());
        }
        if (this.isGetWatchfaceLocal) {
            HwWatchFaceManager.getInstance(this.mContext).notifyGetWatchfaceStore();
        }
    }

    private void dealModeChange() {
        boolean a2 = dp.a("mode_change_not_refresh", false);
        String str = TAG;
        HwLog.i(str, "dealModeChange() not_refresh = " + a2);
        if (a2) {
            dp.b("mode_change_not_refresh", false);
            boolean a3 = dp.a("mode_change", false);
            HwLog.i(str, "dealModeChange() modeChange = " + a3);
            if (a3) {
                Context context = this.mContext;
                if (context == null) {
                    HwLog.i(str, "dealModeChange() mContext == null");
                } else {
                    HwWatchFaceManager.getInstance(context).toNotifyWatchfaceModeChange();
                    HwLog.i(str, "dealModeChange() modeChange  toNotifyWatchfaceModeChange");
                }
            }
        }
    }

    private void reportSuccessFaceInfo() {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(2);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onResponse(101, null);
                }
                sCommandCallbacks.remove(2);
            } else {
                HwLog.w(TAG, "reportSuccessFaceInfo() callback error");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handleApply(byte[] r10) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.api.HwWatchFaceBtManager.handleApply(byte[]):void");
    }

    private void reportForUi(boolean z, int i, String str) {
        synchronized (getCommandCallbackCallbackList()) {
            dealReport(z, i, str);
        }
    }

    private void dealReport(boolean z, int i, String str) {
        if (TextUtils.isEmpty(str) && i != 0) {
            Iterator<Map.Entry<String, IBaseResponseCallback>> it = this.mOperateCallbacks.entrySet().iterator();
            while (it.hasNext()) {
                IBaseResponseCallback value = it.next().getValue();
                if (value != null) {
                    value.onResponse(104, str);
                }
            }
            this.mOperateCallbacks.clear();
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = this.mOperateCallbacks.get(str);
        if (iBaseResponseCallback == null) {
            HwLog.e(TAG, "dealReport() callback is null, hiTopVersion: " + str);
            return;
        }
        if (i != 0) {
            iBaseResponseCallback.onResponse(104, str);
        } else if (z) {
            iBaseResponseCallback.onResponse(105, str);
        } else {
            iBaseResponseCallback.onResponse(103, str);
            getWatchInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda4
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    HwWatchFaceBtManager.lambda$dealReport$3(i2, obj);
                }
            });
        }
        this.mOperateCallbacks.remove(str);
    }

    private void handleErrorCode(byte[] bArr, int i) {
        String a2 = da.a(bArr);
        HwLog.i(TAG, "5.39.4 handleErrorCode(), info:" + a2);
        int i2 = 0;
        try {
            i2 = IntegerUtils.b(this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList().get(0).getValue(), 16);
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleErrorCode() TlvException");
        }
        String str = TAG;
        HwLog.i(str, "handleErrorCode(): " + i2);
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                if (i2 == 100000) {
                    list.get(list.size() - 1).onResponse(101, null);
                    if (getCallBackForH5() != null) {
                        getCallBackForH5().onResponse(106, null);
                    }
                } else {
                    list.get(list.size() - 1).onResponse(102, null);
                }
                sCommandCallbacks.remove(Integer.valueOf(i));
            } else {
                HwLog.w(str, "handleErrorCode() callback error");
            }
        }
    }

    private void handleReportStatus(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.5 handleReportStatus() info:" + a2);
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        int i = 0;
        int i2 = 100000;
        try {
            List<Tlv> tlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                for (Tlv tlv : tlvList) {
                    int b = IntegerUtils.b(tlv.getTag(), 16);
                    String value = tlv.getValue();
                    if (b == 1) {
                        watchResourcesInfo.setWatchInfoId(watchResourcesInfo.markDownVipFreeWatchFace(da.b(value)));
                        HwLog.i(TAG, "handleReportStatus() setWatchInfo_Id:" + watchResourcesInfo.getWatchInfoId());
                    } else if (b == 2) {
                        watchResourcesInfo.setWatchInfoVersion(da.b(value));
                        HwLog.i(TAG, "handleReportStatus() setWatchInfo_Version:" + watchResourcesInfo.getWatchInfoVersion());
                    } else if (b == 3) {
                        i = IntegerUtils.b(value, 16);
                        HwLog.i(TAG, "handleReportStatus() reportType:" + i);
                    } else if (b == 4) {
                        watchResourcesInfo.setFailedNum(IntegerUtils.b(value, 16));
                        HwLog.i(TAG, "handleReportStatus() failed num:" + watchResourcesInfo.getFailedNum());
                    } else if (b == 127) {
                        i2 = IntegerUtils.b(value, 16);
                        HwLog.i(TAG, "handleReportStatus() errorCode:" + i2);
                    } else {
                        HwLog.w(TAG, "handleReportStatus(), nothing to do:" + b);
                    }
                    if (watchResourcesInfo.isVipFreeWatchFace() && i == 3) {
                        HwLog.w(TAG, "handleReportStatus(), isVipFreeWatchFace");
                        HwWatchFaceManager.getInstance(this.mContext).removeVipFreeWatchFace(watchResourcesInfo.getWatchInfoId());
                    }
                }
            } else {
                HwLog.w(str, "handleReportStatus() tlvList error");
            }
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleReportStatus() TlvException");
        }
        reportAck(watchResourcesInfo, i2);
        reportStatusForUi(i, watchResourcesInfo);
    }

    private void reportStatusForUi(int i, WatchResourcesInfo watchResourcesInfo) {
        if (getCallBackForH5() != null) {
            switch (i) {
                case 1:
                    getCallBackForH5().onResponse(106, watchResourcesInfo);
                    break;
                case 2:
                    getCallBackForH5().onResponse(107, watchResourcesInfo);
                    break;
                case 3:
                    getCallBackForH5().onResponse(108, watchResourcesInfo);
                    break;
                case 4:
                case 7:
                case 8:
                case 9:
                    reportPhotoReceivedStatus(i, watchResourcesInfo);
                    break;
                case 5:
                    getCallBackForH5().onResponse(109, watchResourcesInfo);
                    break;
                case 6:
                    getCallBackForH5().onResponse(110, watchResourcesInfo);
                    break;
                default:
                    HwLog.w(TAG, "reportStatusForUi() error");
                    break;
            }
        }
        reportPhotoReceivedStatus(i, watchResourcesInfo);
    }

    private void reportPhotoReceivedStatus(int i, WatchResourcesInfo watchResourcesInfo) {
        int i2;
        if (i != 4 && i != 7 && i != 8 && i != 9) {
            HwLog.w(TAG, "reportPhotoReceivedStatus() reportType not HwWatchFaceBtConstant.STATUS_PHOTO_RECEIVE");
            return;
        }
        if (i == 4) {
            i2 = 109;
        } else if (i == 7) {
            i2 = 16;
        } else if (i == 8) {
            i2 = 18;
        } else if (i != 9) {
            HwLog.w(TAG, "reportPhotoReceivedStatus() invalid reportType.");
            i2 = -1;
        } else {
            i2 = 20;
        }
        String str = TAG;
        HwLog.w(str, "reportPhotoReceivedStatus() reportType: " + i + ", key: " + i2);
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(Integer.valueOf(i2));
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onResponse(101, Integer.valueOf(watchResourcesInfo.getFailedNum()));
                    HwLog.i(TAG, "reportPhotoReceivedStatus() send File callback success, filed number:" + watchResourcesInfo.getFailedNum());
                }
                sCommandCallbacks.remove(Integer.valueOf(i2));
            } else {
                HwLog.w(str, "reportPhotoReceivedStatus() send file callback error");
            }
            if (i == 4 && sCallBackForH5 != null && (HwWatchFaceApi.getInstance(this.mContext).getIsTaskExecuting() || this.isTaskExecuting)) {
                sCallBackForH5.onResponse(115, null);
                this.isTaskExecuting = false;
            }
        }
    }

    private void handleNameInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.6 handleNameInfo() info: " + a2);
        if (TextUtils.isEmpty(a2) || a2.length() < CommonUtils.w()) {
            HwLog.w(str, "handleNameInfo() data is error");
            return;
        }
        try {
            TlvFather tlvFather = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvFatherList().get(0);
            HwLog.i(str, "handleNameInfo() size:" + tlvFather.getTlvFatherList().size());
            HashMap<String, WatchResourcesInfo> hashMap = new HashMap<>(20);
            Iterator<TlvFather> it = tlvFather.getTlvFatherList().iterator();
            while (it.hasNext()) {
                handleOneTlv(hashMap, it.next());
            }
            HwLog.i(TAG, "handleNameInfo() End, nameMap size:" + hashMap.size());
            reportWatchFaceNameInfo(hashMap);
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleNameInfo() TlvException");
        } catch (IndexOutOfBoundsException unused2) {
            HwLog.e(TAG, "handleNameInfo() IndexOutOfBoundsException");
        } catch (NumberFormatException unused3) {
            HwLog.e(TAG, "handleNameInfo() NumberFormatException");
        }
    }

    private void handleOneTlv(HashMap<String, WatchResourcesInfo> hashMap, TlvFather tlvFather) {
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        String str = "";
        for (Tlv tlv : tlvFather.getTlvList()) {
            int b = IntegerUtils.b(tlv.getTag(), 16);
            if (b == 4) {
                str = watchResourcesInfo.markDownVipFreeWatchFace(da.b(tlv.getValue()));
                HwLog.i(TAG, "handleOneTlv() watchFaceId:" + str);
            } else if (b == 5) {
                watchResourcesInfo.setWatchInfoName(da.b(tlv.getValue()));
                HwLog.i(TAG, "handleOneTlv() watchFaceName:" + watchResourcesInfo.getWatchInfoName());
            } else if (b == 6) {
                watchResourcesInfo.setWatchInfoBrief(da.b(tlv.getValue()));
                HwLog.i(TAG, "handleOneTlv() watchFaceBrief:" + watchResourcesInfo.getWatchInfoBrief());
            } else if (b == 7) {
                watchResourcesInfo.setWatchInfoSize(IntegerUtils.b(tlv.getValue(), 16));
                HwLog.i(TAG, "handleOneTlv() watchFaceSize:" + watchResourcesInfo.getWatchInfoSize());
            } else {
                HwLog.w(TAG, "handleOneTlv() default:" + IntegerUtils.b(tlv.getTag(), 16));
            }
        }
        HwLog.i(TAG, "handleOneTlv() watchFaceId:" + str + ", watchFaceInfo:" + watchResourcesInfo.toString());
        hashMap.put(str, watchResourcesInfo);
    }

    private void reportWatchFaceNameInfo(HashMap<String, WatchResourcesInfo> hashMap) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(6);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).onResponse(101, hashMap);
                sCommandCallbacks.remove(6);
            } else {
                HwLog.w(TAG, "reportWatchFaceNameInfo() callback error");
            }
        }
    }

    private void handlePhotoInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.8 handlePhotoInfo() info:" + a2);
        try {
            TlvFather builderTlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w()));
            List<Tlv> tlvList = builderTlvList.getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                for (Tlv tlv : tlvList) {
                    handlePhotoInfoTlv(IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                }
            } else {
                HwLog.w(str, "handlePhotoInfo() tlvList error");
            }
            HwLog.i(TAG, "handlePhotoInfo() struct size:" + builderTlvList.getTlvFatherList().size());
            if (!builderTlvList.getTlvFatherList().isEmpty()) {
                handleBackground(builderTlvList.getTlvFatherList().get(0));
            } else {
                this.mWatchFacePhotoInfo.setBackgroundList(new ArrayList<>(20));
            }
        } catch (TlvException unused) {
            HwLog.e(TAG, "handlePhotoInfo() TlvException");
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(8);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).onResponse(101, this.mWatchFacePhotoInfo);
                sCommandCallbacks.remove(8);
            } else {
                HwLog.w(TAG, "handlePhotoInfo() callback error");
            }
        }
    }

    private void handleBackground(TlvFather tlvFather) {
        HwLog.i(TAG, "handleBackground() in.");
        TreeMap treeMap = new TreeMap();
        TreeMap<Integer, Integer> treeMap2 = new TreeMap<>();
        TreeMap<Integer, Integer> treeMap3 = new TreeMap<>();
        Iterator<TlvFather> it = tlvFather.getTlvFatherList().iterator();
        String str = "";
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        while (it.hasNext()) {
            for (Tlv tlv : it.next().getTlvList()) {
                int b = IntegerUtils.b(tlv.getTag());
                if (b == 6) {
                    i3 = IntegerUtils.b(tlv.getValue());
                } else if (b == 7) {
                    str = da.b(tlv.getValue());
                } else if (b == 12) {
                    i = IntegerUtils.b(tlv.getValue());
                } else if (b == 13) {
                    i2 = IntegerUtils.b(tlv.getValue());
                    if (i2 == 1) {
                        if (!new File(WatchFacePhotoAlbumManager.getInstance(this.mContext).getBgPath() + str).exists() && !TextUtils.isEmpty(str)) {
                            i2 = 3;
                        }
                    }
                } else {
                    HwLog.w(TAG, "handleBackground() error");
                }
            }
            if (i3 != -1 && !TextUtils.isEmpty(str)) {
                treeMap.put(Integer.valueOf(i3), str);
            }
            if (i3 != -1 && i2 != -1) {
                treeMap2.put(Integer.valueOf(i3), Integer.valueOf(i2));
            }
            if (i3 != -1 && i != -1) {
                treeMap3.put(Integer.valueOf(i3), Integer.valueOf(i));
            }
        }
        HwLog.i(TAG, "handleBackground() map.size" + treeMap.size());
        Iterator it2 = treeMap.keySet().iterator();
        ArrayList<String> arrayList = new ArrayList<>(20);
        while (it2.hasNext()) {
            arrayList.add((String) treeMap.get(it2.next()));
        }
        HwLog.i(TAG, "handleBackground() backgroundList.size: " + arrayList.size());
        this.mWatchFacePhotoInfo.setBackgroundList(arrayList);
        this.mWatchFacePhotoInfo.setPortraitModeList(getList(treeMap2));
        this.mWatchFacePhotoInfo.setPortPositionIndexList(getList(treeMap3));
    }

    private ArrayList<Integer> getList(TreeMap<Integer, Integer> treeMap) {
        Iterator<Integer> it = treeMap.keySet().iterator();
        ArrayList<Integer> arrayList = new ArrayList<>(20);
        while (it.hasNext()) {
            arrayList.add(treeMap.get(it.next()));
        }
        return arrayList;
    }

    private void handlePhotoInfoTlv(int i, String str) {
        if (i == 1) {
            this.mWatchFacePhotoInfo.setMaxBackgroundImages(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handlePhotoInfoTlv() setMaxBackgroundImages:" + this.mWatchFacePhotoInfo.getMaxBackgroundImages());
        }
        if (i == 2) {
            if (IntegerUtils.b(str, 16) == 1) {
                this.mWatchFacePhotoInfo.setSupportIntellectColor(true);
            } else {
                this.mWatchFacePhotoInfo.setSupportIntellectColor(false);
            }
            HwLog.i(TAG, "handlePhotoInfoTlv() setSupportIntellectColor:" + this.mWatchFacePhotoInfo.isSupportIntellectColor());
            return;
        }
        if (i == 3) {
            this.mWatchFacePhotoInfo.setBackgroundType(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handlePhotoInfoTlv() setBackgroundType:" + this.mWatchFacePhotoInfo.getBackgroundType());
            return;
        }
        switch (i) {
            case 8:
                this.mWatchFacePhotoInfo.setPositionIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handlePhotoInfoTlv() setPositionIndex:" + this.mWatchFacePhotoInfo.getPositionIndex());
                break;
            case 9:
                this.mWatchFacePhotoInfo.setStyleIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handlePhotoInfoTlv() setStyleIndex:" + this.mWatchFacePhotoInfo.getStyleIndex());
                break;
            case 10:
                this.mWatchFacePhotoInfo.setDataIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handlePhotoInfoTlv() setDataIndex:" + this.mWatchFacePhotoInfo.getDataIndex());
                break;
            case 11:
                this.mWatchFacePhotoInfo.setBgImageOption(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handlePhotoInfoTlv() setBgImageOption:" + this.mWatchFacePhotoInfo.getBgImageOption());
                break;
            case 12:
                this.mWatchFacePhotoInfo.setPositionIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handlePhotoInfoTlv() setPortPositionIndex:" + this.mWatchFacePhotoInfo.getPortPositionIndex());
                break;
            case 13:
                this.mWatchFacePhotoInfo.setPortraitMode(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handleDeviceInfoTlv() setPortraitMode:" + this.mWatchFacePhotoInfo.getPortraitMode());
                break;
            default:
                HwLog.w(TAG, "handlePhotoInfoTlv() default type:" + i);
                break;
        }
    }

    private void handlePhotoStatus(byte[] bArr) {
        int i;
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.9 handlePhotoStatus() info: " + a2);
        int i2 = 0;
        try {
            List<Tlv> tlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                int i3 = 0;
                for (Tlv tlv : tlvList) {
                    try {
                        int b = IntegerUtils.b(tlv.getTag(), 16);
                        String value = tlv.getValue();
                        if (b == 8) {
                            i2 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handlePhotoStatus() transferNum:" + i2);
                        } else if (b == 127) {
                            i3 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handlePhotoStatus() HwWatchFaceBtConstant.ERROR_CODE:" + i3);
                        } else {
                            HwLog.w(TAG, "handlePhotoStatus(), nothing to do");
                        }
                    } catch (TlvException unused) {
                        i = i2;
                        i2 = i3;
                        HwLog.e(TAG, "handlePhotoStatus() error");
                        reportPhotoStatus(i2, i);
                    }
                }
                i = i2;
                i2 = i3;
            } else {
                HwLog.w(str, "handlePhotoStatus() tlv error");
                i = 0;
            }
        } catch (TlvException unused2) {
            i = i2;
        }
        reportPhotoStatus(i2, i);
    }

    private void reportPhotoStatus(int i, int i2) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(109);
            if (list == null || list.isEmpty()) {
                HwLog.w(TAG, "reportPhotoStatus() callback error");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).onResponse(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                HwLog.i(TAG, "reportPhotoStatus() need transfer num:" + i2);
                list.get(list.size() + (-1)).onResponse(111, Integer.valueOf(i2));
            } else {
                HwLog.i(TAG, "reportPhotoStatus() errorCode is failed");
                list.get(list.size() - 1).onResponse(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    public void removePhotoCallback() {
        HwLog.d(TAG, "removePhotoCallback enter");
        List<IBaseResponseCallback> list = sCommandCallbacks.get(109);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(list.size() - 1);
    }

    private void handleVideoInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.15 handleVideoInfo() info:" + a2);
        try {
            TlvFather builderTlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(15)));
            List<Tlv> tlvList = builderTlvList.getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                for (Tlv tlv : tlvList) {
                    handleVideoInfoTlv(IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                }
            } else {
                HwLog.w(str, "handleVideoInfo() tlvList error");
            }
            HwLog.i(TAG, "handleVideoInfo() struct size:" + builderTlvList.getTlvFatherList().size());
            if (!builderTlvList.getTlvFatherList().isEmpty()) {
                handleVideoStruct(builderTlvList.getTlvFatherList().get(0));
            } else {
                this.mWatchFaceVideoInfo.setVideoList(new ArrayList<>(20));
            }
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleVideoInfo() TlvException");
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(15);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).onResponse(101, this.mWatchFaceVideoInfo);
                sCommandCallbacks.remove(15);
            } else {
                HwLog.w(TAG, "handleVideoInfo() callback error");
            }
        }
    }

    private void handleVideoInfoTlv(int i, String str) {
        if (i == 1) {
            this.mWatchFaceVideoInfo.setMaxVideoNum(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleVideoInfoTlv() setMaxVideoNum:" + this.mWatchFaceVideoInfo.getMaxVideoNum());
        }
        if (i == 2) {
            this.mWatchFaceVideoInfo.setVideoType(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleVideoInfoTlv() setVideoType:" + this.mWatchFaceVideoInfo.getVideoType());
            return;
        }
        switch (i) {
            case 10:
                this.mWatchFaceVideoInfo.setPositionIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handleVideoInfoTlv() setPositionIndex:" + this.mWatchFaceVideoInfo.getPositionIndex());
                break;
            case 11:
                this.mWatchFaceVideoInfo.setStyleIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handleVideoInfoTlv() setStyleIndex:" + this.mWatchFaceVideoInfo.getStyleIndex());
                break;
            case 12:
                this.mWatchFaceVideoInfo.setDataIndex(IntegerUtils.b(str, 16));
                HwLog.i(TAG, "handleVideoInfoTlv() setDataIndex:" + this.mWatchFaceVideoInfo.getDataIndex());
                break;
            default:
                HwLog.w(TAG, "handleVideoInfoTlv() default type:" + i);
                break;
        }
    }

    private void handleVideoStruct(TlvFather tlvFather) {
        HwLog.i(TAG, "handleVideoStruct() enter.");
        TreeMap treeMap = new TreeMap();
        Iterator<TlvFather> it = tlvFather.getTlvFatherList().iterator();
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        String str = "";
        while (it.hasNext()) {
            for (Tlv tlv : it.next().getTlvList()) {
                switch (IntegerUtils.b(tlv.getTag())) {
                    case 5:
                        i3 = IntegerUtils.b(tlv.getValue());
                        HwLog.i(TAG, "handleVideoStruct() videoIndex: " + i3);
                        break;
                    case 6:
                        str = da.b(tlv.getValue());
                        HwLog.i(TAG, "handleVideoStruct() videoName: " + str);
                        break;
                    case 7:
                        i2 = IntegerUtils.b(tlv.getValue());
                        HwLog.i(TAG, "handleVideoStruct() videoAttribute: " + i2);
                        break;
                    case 8:
                        String b = da.b(tlv.getValue());
                        HwLog.i(TAG, "handleVideoStruct() videoPreViewName: " + b);
                        break;
                    case 9:
                        i = IntegerUtils.b(tlv.getValue());
                        HwLog.i(TAG, "handleVideoStruct() videoStatus: " + i);
                        break;
                    default:
                        HwLog.w(TAG, "handleVideoStruct() error");
                        break;
                }
            }
            if (i3 != -1 && !TextUtils.isEmpty(str) && i2 != -1) {
                treeMap.put(Integer.valueOf(i3), new VideoStruct(str, "", i2, i));
            }
        }
        HwLog.i(TAG, "handleVideoStruct() map.size: " + treeMap.size());
        Iterator it2 = treeMap.keySet().iterator();
        ArrayList<VideoStruct> arrayList = new ArrayList<>(20);
        while (it2.hasNext()) {
            arrayList.add((VideoStruct) treeMap.get(it2.next()));
        }
        HwLog.i(TAG, "handleVideoStruct() videoStructs.size: " + arrayList.size());
        this.mWatchFaceVideoInfo.setVideoList(arrayList);
    }

    private void handleVideoStatus(byte[] bArr) {
        int i;
        int i2;
        int i3;
        List<Tlv> tlvList;
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.16 handleVideoStatus() info: " + a2);
        int i4 = 0;
        try {
            tlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(16))).getTlvList();
        } catch (TlvException unused) {
            i = 0;
            i2 = 0;
        }
        if (tlvList != null && !tlvList.isEmpty()) {
            i2 = 0;
            i3 = 0;
            for (Tlv tlv : tlvList) {
                try {
                    int b = IntegerUtils.b(tlv.getTag(), 16);
                    String value = tlv.getValue();
                    if (b == 13) {
                        i4 = IntegerUtils.b(value, 16);
                        HwLog.i(TAG, "handleVideoStatus() transferPreviewNum:" + i4);
                    } else if (b == 14) {
                        i3 = IntegerUtils.b(value, 16);
                        HwLog.i(TAG, "handleVideoStatus() transferVideoNum:" + i3);
                    } else if (b == 127) {
                        i2 = IntegerUtils.b(value, 16);
                        HwLog.i(TAG, "handleVideoStatus() HwWatchFaceBtConstant.ERROR_CODE:" + i2);
                    } else {
                        HwLog.w(TAG, "handleVideoStatus() nothing to do.");
                    }
                } catch (TlvException unused2) {
                    i = i4;
                    i4 = i3;
                    HwLog.e(TAG, "handleVideoStatus() error.");
                    i3 = i4;
                    i4 = i2;
                    reportVideoStatus(i4, i, i3);
                }
            }
            i = i4;
            i4 = i2;
            reportVideoStatus(i4, i, i3);
        }
        HwLog.w(str, "handleVideoStatus() tlv error.");
        i = 0;
        i3 = 0;
        reportVideoStatus(i4, i, i3);
    }

    private void reportVideoStatus(int i, int i2, int i3) {
        String str = TAG;
        HwLog.i(str, "reportVideoStatus() transferPreviewNum: " + i2 + ", transferVideoNum: " + i3);
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(16);
            if (list == null || list.isEmpty()) {
                HwLog.w(str, "reportVideoStatus() callback error.");
            } else if (i == 100000 && i2 == 0 && i3 == 0) {
                list.get(list.size() - 1).onResponse(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && (i2 > 0 || i3 > 0)) {
                HwLog.i(str, "reportVideoStatus() need transfer.");
                list.get(list.size() - 1).onResponse(112, Integer.valueOf(i3));
            } else {
                HwLog.i(str, "reportVideoStatus() errorCode is failed.");
                list.get(list.size() - 1).onResponse(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    private void handleKaleidoscopeInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.17 handleKaleidoscopeInfo() info: " + a2);
        try {
            TlvFather builderTlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(17)));
            List<Tlv> tlvList = builderTlvList.getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                for (Tlv tlv : tlvList) {
                    handleKaleidoscopeInfoTlv(IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                }
            } else {
                HwLog.w(str, "handleKaleidoscopeInfo() tlvList error");
            }
            HwLog.i(TAG, "handleKaleidoscopeInfo() struct size:" + builderTlvList.getTlvFatherList().size());
            if (!builderTlvList.getTlvFatherList().isEmpty()) {
                handleKaleidoscopeStruct(builderTlvList.getTlvFatherList().get(0));
            } else {
                this.mWatchFaceKaleidoscopeInfo.setMaterialImageList(new ArrayList<>(20));
            }
        } catch (TlvException unused) {
            HwLog.e(TAG, "handleKaleidoscopeInfo() TlvException");
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(17);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).onResponse(101, this.mWatchFaceKaleidoscopeInfo);
                sCommandCallbacks.remove(17);
            } else {
                HwLog.w(TAG, "handleKaleidoscopeInfo() callback error");
            }
        }
    }

    private void handleKaleidoscopeInfoTlv(int i, String str) {
        if (i == 1) {
            this.mWatchFaceKaleidoscopeInfo.setMaxMaterialImages(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleKaleidoscopeInfoTlv() setMaxMaterialImages:" + this.mWatchFaceKaleidoscopeInfo.getMaxMaterialImages());
            return;
        }
        if (i == 2) {
            this.mWatchFaceKaleidoscopeInfo.setMaterialImageType(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleKaleidoscopeInfoTlv() setMaterialImageType:" + this.mWatchFaceKaleidoscopeInfo.getMaterialImageType());
            return;
        }
        if (i == 7) {
            this.mWatchFaceKaleidoscopeInfo.setCurrentMaterialImageIndex(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleKaleidoscopeInfoTlv() setCurrentMaterialImageIndex:" + this.mWatchFaceKaleidoscopeInfo.getCurrentMaterialImageIndex());
            return;
        }
        if (i == 8) {
            this.mWatchFaceKaleidoscopeInfo.setHandsStyleIndex(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleKaleidoscopeInfoTlv() setHandsStyleIndex:" + this.mWatchFaceKaleidoscopeInfo.getHandsStyleIndex());
            return;
        }
        if (i == 9) {
            this.mWatchFaceKaleidoscopeInfo.setKaleidoscopeType(IntegerUtils.b(str, 16));
            HwLog.i(TAG, "handleKaleidoscopeInfoTlv() setKaleidoscopeType:" + this.mWatchFaceKaleidoscopeInfo.getKaleidoscopeType());
            return;
        }
        HwLog.w(TAG, "handleKaleidoscopeInfoTlv() default type:" + i);
    }

    private void handleKaleidoscopeStruct(TlvFather tlvFather) {
        HwLog.i(TAG, "handleKaleidoscopeStruct() enter.");
        ArrayList<KaleidoscopeStruct> arrayList = new ArrayList<>();
        Iterator<TlvFather> it = tlvFather.getTlvFatherList().iterator();
        int i = -1;
        String str = "";
        while (it.hasNext()) {
            for (Tlv tlv : it.next().getTlvList()) {
                int b = IntegerUtils.b(tlv.getTag());
                if (b == 5) {
                    i = IntegerUtils.b(tlv.getValue());
                    HwLog.i(TAG, "handleKaleidoscopeStruct() materialImageIndex: " + i);
                } else if (b == 6) {
                    str = da.b(tlv.getValue());
                    HwLog.i(TAG, "handleKaleidoscopeStruct() materialImageName: " + str);
                } else {
                    HwLog.w(TAG, "handleKaleidoscopeStruct() error");
                }
            }
            arrayList.add(new KaleidoscopeStruct(i, str));
        }
        HwLog.i(TAG, "handleKaleidoscopeStruct() kaleidoscopeStructs.size: " + arrayList.size());
        this.mWatchFaceKaleidoscopeInfo.setMaterialImageList(arrayList);
    }

    private void handleKaleidoscopeStatus(byte[] bArr) {
        int i;
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.18 handleKaleidoscopeStatus() info: " + a2);
        int i2 = 0;
        try {
            List<Tlv> tlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(18))).getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                int i3 = 0;
                for (Tlv tlv : tlvList) {
                    try {
                        int b = IntegerUtils.b(tlv.getTag(), 16);
                        String value = tlv.getValue();
                        if (b == 9) {
                            i2 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handleKaleidoscopeStatus() transferNum:" + i2);
                        } else if (b == 127) {
                            i3 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handleKaleidoscopeStatus() HwWatchFaceBtConstant.ERROR_CODE:" + i3);
                        } else {
                            HwLog.w(TAG, "handleKaleidoscopeStatus() nothing to do.");
                        }
                    } catch (TlvException unused) {
                        i = i2;
                        i2 = i3;
                        HwLog.e(TAG, "handleKaleidoscopeStatus() error.");
                        reportKaleidoscopeStatus(i2, i);
                    }
                }
                i = i2;
                i2 = i3;
            } else {
                HwLog.w(str, "handleKaleidoscopeStatus() tlv error.");
                i = 0;
            }
        } catch (TlvException unused2) {
            i = i2;
        }
        reportKaleidoscopeStatus(i2, i);
    }

    private void reportKaleidoscopeStatus(int i, int i2) {
        String str = TAG;
        HwLog.i(str, "reportKaleidoscopeStatus() transferNum: " + i2);
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(18);
            if (list == null || list.isEmpty()) {
                HwLog.w(str, "reportKaleidoscopeStatus() callback error.");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).onResponse(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                HwLog.i(str, "reportKaleidoscopeStatus() need transfer.");
                list.get(list.size() - 1).onResponse(113, Integer.valueOf(i2));
            } else {
                HwLog.i(str, "reportKaleidoscopeStatus() errorCode is failed.");
                list.get(list.size() - 1).onResponse(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    public void getDeviceInfoForUi(IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "getDeviceInfoForUi() enter.");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(1);
                if (list == null) {
                    HwLog.i(str, "getDeviceInfoForUi() have no callback.");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(1, arrayList);
                } else {
                    HwLog.i(str, "getDeviceInfoForUi() have callback, add.");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        getDeviceInfo();
    }

    private void getDeviceInfo() {
        String str = CommonUtils.a() + (da.a(1) + da.a(0)) + (da.a(2) + da.a(0)) + (da.a(3) + da.a(0)) + (da.a(4) + da.a(0)) + (da.a(5) + da.a(0)) + (da.a(20) + da.a(0)) + (da.a(21) + da.a(0));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(1);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(TAG, "5.39.1 getDeviceInfo() commandJsonInfo: " + commandJsonInfo.toString());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("errorCode", String.valueOf(System.currentTimeMillis()));
        linkedHashMap.put(BaseEvent.KEY_DESCINFO, commandJsonInfo.toString());
        linkedHashMap.put("title", "5.39.1");
        new ec("tlv", linkedHashMap).a_();
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void getWatchInfoForUi(IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "getWatchInfoForUi() enter.");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(2);
                if (list == null) {
                    HwLog.i(str, "getWatchInfoForUi() have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(2, arrayList);
                } else {
                    HwLog.i(str, "getWatchInfoForUi() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                HwWatchFaceBtManager.this.getDeviceWatchInfo();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceWatchInfo() {
        String str = da.a(1) + da.a(0);
        int serverMode = HwWatchFaceApi.getInstance(this.mContext).getServerMode();
        String str2 = TAG;
        HwLog.i(str2, "getDeviceWatchInfo() serverMode: " + serverMode);
        String str3 = CommonUtils.a() + str + (da.a(6) + da.a(1) + da.a(serverMode));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(2);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str3);
        HwLog.i(str2, "5.39.2 getDeviceWatchInfo() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void operateDevice(WatchResourcesInfo watchResourcesInfo, int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        String str = TAG;
        HwLog.i(str, "operateDevice() enter." + i);
        synchronized (getCommandCallbackCallbackList()) {
            if (i == 1) {
                try {
                    dz.b(String.valueOf(System.currentTimeMillis()));
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (2 == i) {
                d.a().b(watchResourcesInfo);
            } else {
                watchResourcesInfo.setWatchInfoId(d.a().a(watchResourcesInfo.getWatchInfoId()));
            }
            if (iBaseResponseCallback != null) {
                String str2 = watchResourcesInfo.getWatchInfoId() + "_" + watchResourcesInfo.getWatchInfoVersion();
                HwLog.i(str, "operateDevice() hiTopVersion: " + str2);
                this.mOperateCallbacks.put(str2, iBaseResponseCallback);
            }
        }
        sendOperateType(watchResourcesInfo, i, z);
    }

    public void operateDevice(WatchResourcesInfo watchResourcesInfo, int i, IBaseResponseCallback iBaseResponseCallback) {
        operateDevice(watchResourcesInfo, i, iBaseResponseCallback, false);
    }

    private void sendOperateType(WatchResourcesInfo watchResourcesInfo, int i, boolean z) {
        String commandHexString = getCommandHexString(watchResourcesInfo, i);
        if (z && checkIsSync(watchResourcesInfo)) {
            commandHexString = commandHexString + (da.a(7) + da.a(1) + da.a(1));
        }
        setCommandJsonInfo(commandHexString);
    }

    private void setCommandJsonInfo(String str) {
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(3);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(TAG, "5.39.3 sendOperateType() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    private String getCommandHexString(WatchResourcesInfo watchResourcesInfo, int i) {
        String str;
        String str2;
        String c = da.c(watchResourcesInfo.markUpVipFreeWatchFace(watchResourcesInfo.getWatchInfoId()));
        String str3 = da.a(1) + da.a(c.length() / 2) + c;
        String c2 = da.c(watchResourcesInfo.getWatchInfoVersion());
        String str4 = da.a(2) + da.a(c2.length() / 2) + c2;
        String str5 = da.a(3) + da.a(1) + da.a(i);
        String watchScreen = watchResourcesInfo.getWatchScreen();
        if (TextUtils.isEmpty(watchScreen)) {
            str = "";
            str2 = "";
        } else {
            HwLog.i(TAG, "sendOperateType() watchScreen: " + watchScreen);
            int parseInt = Integer.parseInt(SafeString.substring(watchScreen, 0, watchScreen.lastIndexOf("*")));
            int parseInt2 = Integer.parseInt(SafeString.substring(watchScreen, watchScreen.lastIndexOf("*") + 1));
            str = da.a(5) + da.a(da.a(parseInt).length() / 2) + da.a(parseInt);
            str2 = da.a(6) + da.a(da.a(parseInt2).length() / 2) + da.a(parseInt2);
        }
        return CommonUtils.a() + str3 + str4 + str5 + str + str2;
    }

    private boolean checkIsSync(WatchResourcesInfo watchResourcesInfo) {
        String buildTaskId;
        boolean isSupportSync = isSupportSync();
        String str = TAG;
        HwLog.i(str, " checkIsSync() isSupportSync:" + isSupportSync);
        if (!isSupportSync) {
            return false;
        }
        String watchInfoId = watchResourcesInfo.getWatchInfoId();
        String watchInfoVersion = watchResourcesInfo.getWatchInfoVersion();
        if (watchResourcesInfo.isVipFreeWatchFace()) {
            buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(WatchResourcesInfo.markUpHiTopId(watchInfoId), watchInfoVersion);
        } else {
            buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(watchInfoId, watchInfoVersion);
        }
        boolean a2 = m.a().a(buildTaskId);
        HwLog.i(str, "checkIsSync() checkRst: " + a2);
        return a2;
    }

    public void sortWatchList(ArrayList<WatchResourcesInfo> arrayList, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "sortWatchList() enter.");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(4);
                if (list == null) {
                    HwLog.i(str, "sortWatchList() have no callback");
                    ArrayList arrayList2 = new ArrayList(20);
                    arrayList2.add(iBaseResponseCallback);
                    sCommandCallbacks.put(4, arrayList2);
                } else {
                    HwLog.i(str, "sortWatchList() have callback,add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        sendInfoList(arrayList);
    }

    private void sendInfoList(ArrayList<WatchResourcesInfo> arrayList) {
        StringBuilder sb = new StringBuilder(20);
        Iterator<WatchResourcesInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            sb.append(next.markUpVipFreeWatchFace(next.getWatchInfoId()));
            sb.append(";");
        }
        String c = da.c(SafeStringBuilder.substring(sb, 0, sb.length() - 1));
        String str = CommonUtils.a() + da.a(1) + da.b(c.length() / 2) + c;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(4);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(TAG, "5.39.4 sendInfoList() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    private void reportAck(WatchResourcesInfo watchResourcesInfo, int i) {
        if (100000 != i) {
            HwLog.e(TAG, "reportAck() errorCode: " + i);
            return;
        }
        String c = da.c(watchResourcesInfo.markUpVipFreeWatchFace(watchResourcesInfo.getWatchInfoId()));
        String str = da.a(1) + da.a(c.length() / 2) + c;
        String c2 = da.c(watchResourcesInfo.getWatchInfoVersion());
        String str2 = CommonUtils.a() + str + (da.a(2) + da.a(c2.length() / 2) + c2) + (da.a(127) + da.a(4) + da.b(i));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(5);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str2);
        HwLog.i(TAG, "5.39.5 reportAck() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void getWatchFaceName(ArrayList<String> arrayList, int i, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "getWatchFaceName() enter.");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(6);
                if (list == null) {
                    HwLog.i(str, "getWatchFaceName() have no callback");
                    ArrayList arrayList2 = new ArrayList(20);
                    arrayList2.add(iBaseResponseCallback);
                    sCommandCallbacks.put(6, arrayList2);
                } else {
                    HwLog.i(str, "getWatchFaceName() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        sendGetNameCommand(arrayList, i);
    }

    private void sendGetNameCommand(ArrayList<String> arrayList, int i) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        String str = da.a(1) + da.a(1) + da.a(i);
        StringBuilder sb = new StringBuilder(20);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (this.mHashWatchFaces.containsKey(next) && this.mHashWatchFaces.get(next).isVipFreeWatchFace()) {
                next = WatchResourcesInfo.markUpHiTopId(next);
            }
            String str2 = da.a(4) + da.a(da.c(next).length() / 2) + da.c(next);
            sb.append(da.a(131) + da.a(str2.length() / 2) + str2);
        }
        String str3 = CommonUtils.a() + str + (da.a(OldToNewMotionPath.SPORT_TYPE_TENNIS) + da.b(sb.length() / 2) + ((Object) sb));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(6);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str3);
        HwLog.i(TAG, "5.39.6 sendGetNameCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void getWatchFacePhotoInfo(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(8);
                if (list == null) {
                    HwLog.i(TAG, "getWatchFacePhotoInfo() have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(8, arrayList);
                } else {
                    HwLog.i(TAG, "getWatchFacePhotoInfo() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        HwLog.i(TAG, "getWatchFacePhotoInfo() requestType:" + i);
        this.mPhotoOptionType = i;
        getPhotoWatchInfo(i);
    }

    private void getPhotoWatchInfo(int i) {
        String str = da.a(1) + da.a(0);
        String str2 = da.a(2) + da.a(0);
        String str3 = da.a(3) + da.a(0);
        String str4 = da.a(4) + da.a(0);
        String str5 = da.a(13) + da.a(0);
        String str6 = da.a(12) + da.a(0);
        StringBuilder sb = new StringBuilder(20);
        if ((i & 1) == 1) {
            sb.append(da.a(8) + da.a(0));
        }
        if ((i & 2) == 2) {
            sb.append(da.a(9) + da.a(0));
        }
        if ((i & 4) == 4) {
            sb.append(da.a(10) + da.a(0));
        }
        String str7 = CommonUtils.a() + str + str2 + str3 + str4 + str6 + str5 + sb.toString();
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(8);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str7);
        HwLog.i(TAG, "5.39.8 getPhotoWatchInfo() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void h5ToSendDeviceCommand(String str, int i, int i2, int i3, String str2) {
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(i);
        commandJsonInfo.setCommandId(i2);
        commandJsonInfo.setCommandType(i3);
        commandJsonInfo.setDeviceCommand(str2);
        HwLog.i(TAG, "H5toSendDeviceCommand info: tlvName :" + str + ",commandJsonInfo:" + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void setPhotoInfoToDeviceCallback(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(109);
                if (list == null) {
                    HwLog.i(TAG, "savePhotoInfoToDevice() have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(109, arrayList);
                } else {
                    HwLog.i(TAG, "savePhotoInfoToDevice() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void savePhotoInfoToDevice(WatchFacePhotoInfo watchFacePhotoInfo, LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "savePhotoInfoToDevice() enter.");
        if (watchFacePhotoInfo == null) {
            iBaseResponseCallback.onResponse(102, "");
            HwLog.w(str, "savePhotoInfoToDevice() error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(109);
                if (list == null) {
                    HwLog.i(str, "savePhotoInfoToDevice() have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(109, arrayList);
                } else {
                    HwLog.i(str, "savePhotoInfoToDevice() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        makePhotoInfoCommand(watchFacePhotoInfo, latonaWatchFaceThemeAlbumInfo);
    }

    private void makePhotoInfoCommand(WatchFacePhotoInfo watchFacePhotoInfo, LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        String str;
        String str2;
        String str3;
        ArrayList<String> arrayList = latonaWatchFaceThemeAlbumInfo.getmPortPositionIndexList();
        ArrayList<String> arrayList2 = latonaWatchFaceThemeAlbumInfo.getmPortraitModeList();
        WatchFaceSupportInfo watchFaceSupportInfo = getInstance(this.mContext).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return;
        }
        int i = 1;
        boolean z = watchFaceSupportInfo.getPortraitFieldMode() == 1;
        StringBuilder sb = new StringBuilder(20);
        Iterator<String> it = watchFacePhotoInfo.getBackgroundList().iterator();
        int i2 = 1;
        while (it.hasNext()) {
            String next = it.next();
            String str4 = (da.a(4) + da.a(i) + da.a(i2)) + (da.a(5) + da.a(da.c(next).length() / 2) + da.c(next));
            if (z) {
                if (arrayList2.size() > 0) {
                    try {
                        str2 = arrayList2.get(i2 - 1);
                    } catch (Exception unused) {
                        HwLog.i(TAG, "makePhotoInfoCommand old H5 Index mode err");
                        str2 = "0";
                    }
                    if (str2.equalsIgnoreCase("3")) {
                        str2 = "1";
                    }
                } else {
                    str2 = "0";
                }
                String str5 = da.a(11) + da.a(i) + da.a(Integer.parseInt(str2));
                if (arrayList.size() > 0) {
                    try {
                        str3 = arrayList.get(i2 - 1);
                    } catch (Exception unused2) {
                        str3 = latonaWatchFaceThemeAlbumInfo.getPositionIndex();
                        HwLog.i(TAG, "makePhotoInfoCommand old H5 Index position err");
                    }
                } else {
                    str3 = "1";
                }
                if (str3.equals("0")) {
                    str3 = "1";
                }
                str4 = str4 + (da.a(10) + da.a(1) + da.a(Integer.parseInt((str2.equalsIgnoreCase("0") && str3.equalsIgnoreCase("3")) ? "1" : str3))) + str5;
            }
            sb.append(da.a(131) + da.a(str4.length() / 2) + str4);
            i2++;
            i = 1;
        }
        StringBuilder sb2 = new StringBuilder(20);
        String str6 = TAG;
        HwLog.i(str6, "makePhotoInfoCommand() type: " + this.mPhotoOptionType);
        if ((this.mPhotoOptionType & 1) == 1) {
            sb2.append(da.a(6) + da.a(1) + da.a(watchFacePhotoInfo.getPositionIndex()));
        }
        if ((this.mPhotoOptionType & 2) == 2) {
            sb2.append(da.a(7) + da.a(1) + da.a(watchFacePhotoInfo.getStyleIndex()));
        }
        if ((this.mPhotoOptionType & 4) == 4) {
            sb2.append(da.a(9) + da.a(1) + da.a(watchFacePhotoInfo.getDataIndex()));
        }
        int size = watchFacePhotoInfo.getBackgroundList().size();
        String str7 = da.a(1) + da.a(1) + da.a(size);
        if (size == 0) {
            str = str7 + ((Object) sb2);
        } else {
            str = str7 + (da.a(OldToNewMotionPath.SPORT_TYPE_TENNIS) + da.b(sb.length() / 2) + ((Object) sb)) + ((Object) sb2);
        }
        String str8 = CommonUtils.a() + str;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(9);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str8);
        HwLog.i(str6, "5.39.9 makePhotoInfoCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void getWatchFaceVideoInfo(int i, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "getWatchFaceVideoInfo() requestType: " + i);
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(15);
                if (list == null) {
                    HwLog.i(str, "getWatchFaceVideoInfo() have no callback.");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(15, arrayList);
                } else {
                    HwLog.i(str, "getWatchFaceVideoInfo() have callback, add.");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        this.mVideoOptionType = i;
        getWatchFaceVideoInfoCommand(i);
    }

    private void getWatchFaceVideoInfoCommand(int i) {
        String str = da.a(1) + da.a(0);
        String str2 = da.a(2) + da.a(0);
        String str3 = da.a(3) + da.a(0);
        StringBuilder sb = new StringBuilder(20);
        if ((i & 1) == 1) {
            sb.append(da.a(10));
            sb.append(da.a(0));
        }
        if ((i & 2) == 2) {
            sb.append(da.a(11));
            sb.append(da.a(0));
        }
        if ((i & 4) == 4) {
            sb.append(da.a(12));
            sb.append(da.a(0));
        }
        String str4 = CommonUtils.b() + str + str2 + str3 + sb.toString();
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(15);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str4);
        HwLog.i(TAG, "5.39.15 getWatchFaceVideoInfoCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void saveWatchFaceVideoInfo(WatchFaceVideoInfo watchFaceVideoInfo, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "saveWatchFaceVideoInfo() enter.");
        if (watchFaceVideoInfo == null) {
            iBaseResponseCallback.onResponse(102, "");
            HwLog.w(str, "saveWatchFaceVideoInfo() error.");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(16);
                if (list == null) {
                    HwLog.i(str, "saveWatchFaceVideoInfo() have no callback.");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(16, arrayList);
                } else {
                    HwLog.i(str, "saveWatchFaceVideoInfo() have callback, add.");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        makeVideoInfoCommand(watchFaceVideoInfo);
    }

    private void makeVideoInfoCommand(WatchFaceVideoInfo watchFaceVideoInfo) {
        String str;
        StringBuilder sb = new StringBuilder(20);
        Iterator<VideoStruct> it = watchFaceVideoInfo.getVideoList().iterator();
        int i = 1;
        while (it.hasNext()) {
            VideoStruct next = it.next();
            String str2 = (da.a(5) + da.a(1) + da.a(i)) + (da.a(6) + da.a(da.c(next.getVideoName()).length() / 2) + da.c(next.getVideoName())) + (da.a(7) + da.a(1) + da.a(next.getVideoAttribute())) + (da.a(8) + da.a(da.c(next.getVideoPreviewName()).length() / 2) + da.c(next.getVideoPreviewName())) + (da.a(9) + da.a(1) + da.a(next.getVideoStatus()));
            sb.append(da.a(UserInfomation.WEIGHT_DEFAULT_ENGLISH) + da.a(str2.length() / 2) + str2);
            i++;
        }
        StringBuilder sb2 = new StringBuilder(20);
        String str3 = TAG;
        HwLog.i(str3, "makeVideoInfoCommand() mVideoOptionType: " + this.mVideoOptionType);
        if ((this.mVideoOptionType & 1) == 1) {
            sb2.append(da.a(10) + da.a(1) + da.a(watchFaceVideoInfo.getPositionIndex()));
        }
        if ((this.mVideoOptionType & 2) == 2) {
            sb2.append(da.a(11) + da.a(1) + da.a(watchFaceVideoInfo.getStyleIndex()));
        }
        if ((this.mVideoOptionType & 4) == 4) {
            sb2.append(da.a(12) + da.a(1) + da.a(watchFaceVideoInfo.getDataIndex()));
        }
        int size = watchFaceVideoInfo.getVideoList().size();
        String str4 = da.a(1) + da.a(1) + da.a(size);
        String str5 = da.a(2) + da.a(1) + da.a(watchFaceVideoInfo.getVideoType());
        if (size == 0) {
            str = str4 + str5 + ((Object) sb2);
        } else {
            str = str4 + str5 + (da.a(131) + da.b(sb.length() / 2) + ((Object) sb)) + ((Object) sb2);
        }
        String str6 = CommonUtils.b() + str;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(16);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str6);
        HwLog.i(str3, "5.39.16 makeVideoInfoCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void getKaleidoscopeWatchFaceInfo(int i, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "getKaleidoscopeWatchFaceInfo() requestType: " + i);
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(17);
                if (list == null) {
                    HwLog.i(str, "getKaleidoscopeWatchFaceInfo() have no callback.");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(17, arrayList);
                } else {
                    HwLog.i(str, "getKaleidoscopeWatchFaceInfo() have callback, add.");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        this.mKaleidoscopeOptionType = i;
        sendKaleidoscopeWatchFaceCommand(i);
    }

    private void sendKaleidoscopeWatchFaceCommand(int i) {
        String str = da.a(1) + da.a(0);
        String str2 = da.a(2) + da.a(0);
        String str3 = da.a(3) + da.a(0);
        StringBuilder sb = new StringBuilder(20);
        if ((i & 1) == 1) {
            sb.append(da.a(7));
            sb.append(da.a(0));
        }
        if ((i & 2) == 2) {
            sb.append(da.a(8));
            sb.append(da.a(0));
        }
        if ((i & 4) == 4) {
            sb.append(da.a(9));
            sb.append(da.a(0));
        }
        String str4 = CommonUtils.b() + str + str2 + str3 + sb.toString();
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(17);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str4);
        HwLog.i(TAG, "5.39.17 sendKaleidoscopeWatchFaceCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void saveKaleidoscopeInfoToDevice(WatchFaceKaleidoscopeInfo watchFaceKaleidoscopeInfo, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "saveKaleidoscopeInfoToDevice() enter.");
        if (watchFaceKaleidoscopeInfo == null) {
            iBaseResponseCallback.onResponse(102, "");
            HwLog.w(str, "saveKaleidoscopeInfoToDevice() error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(18);
                if (list == null) {
                    HwLog.i(str, "saveKaleidoscopeInfoToDevice() have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(18, arrayList);
                } else {
                    HwLog.i(str, "saveKaleidoscopeInfoToDevice() have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        makeKaleidoscopeInfoCommand(watchFaceKaleidoscopeInfo);
    }

    private void makeKaleidoscopeInfoCommand(WatchFaceKaleidoscopeInfo watchFaceKaleidoscopeInfo) {
        String str;
        StringBuilder sb = new StringBuilder(20);
        Iterator<KaleidoscopeStruct> it = watchFaceKaleidoscopeInfo.getMaterialImageList().iterator();
        while (it.hasNext()) {
            KaleidoscopeStruct next = it.next();
            String str2 = (da.a(4) + da.a(1) + da.a(next.getMaterialImageIndex())) + (da.a(5) + da.a(da.c(next.getMaterialImageName()).length() / 2) + da.c(next.getMaterialImageName()));
            sb.append(da.a(131) + da.a(str2.length() / 2) + str2);
        }
        StringBuilder sb2 = new StringBuilder(20);
        String str3 = TAG;
        HwLog.i(str3, "makeKaleidoscopeInfoCommand() mKaleidoscopeOptionType: " + this.mKaleidoscopeOptionType);
        if ((this.mKaleidoscopeOptionType & 1) == 1) {
            sb2.append(da.a(6) + da.a(1) + da.a(watchFaceKaleidoscopeInfo.getCurrentMaterialImageIndex()));
        }
        if ((this.mKaleidoscopeOptionType & 2) == 2) {
            sb2.append(da.a(7) + da.a(1) + da.a(watchFaceKaleidoscopeInfo.getHandsStyleIndex()));
        }
        if ((this.mKaleidoscopeOptionType & 4) == 4) {
            sb2.append(da.a(8) + da.a(1) + da.a(watchFaceKaleidoscopeInfo.getKaleidoscopeType()));
        }
        int size = watchFaceKaleidoscopeInfo.getMaterialImageList().size();
        String str4 = da.a(1) + da.a(1) + da.a(size);
        if (size == 0) {
            str = str4 + ((Object) sb2);
        } else {
            str = str4 + (da.a(OldToNewMotionPath.SPORT_TYPE_TENNIS) + da.b(sb.length() / 2) + ((Object) sb)) + ((Object) sb2);
        }
        String str5 = CommonUtils.b() + str;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(18);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str5);
        HwLog.i(str3, "5.39.18 makeKaleidoscopeInfoCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public WatchFaceSupportInfo getWatchFaceSupportInfo() {
        synchronized (this) {
            if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 1) {
                return this.mWatchFaceSupportInfo;
            }
            WatchFaceSupportInfo watchFaceSupportInfo = this.mWatchFaceSupportInfo;
            if (watchFaceSupportInfo != null) {
                return watchFaceSupportInfo;
            }
            if (this.mVirtualWatchFaceSupportInfo == null) {
                this.mVirtualWatchFaceSupportInfo = WatchFaceSupportInfo.createInstance();
            }
            return this.mVirtualWatchFaceSupportInfo;
        }
    }

    public WatchResourcesInfo getCurrentWatchFace() {
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            if ((next.getWatchInfoType() & 1) == 1) {
                HwLog.i(TAG, "getCurrentWatchFace() watchResourcesInfo: " + next.getWatchInfoId());
                return next;
            }
        }
        return watchResourcesInfo;
    }

    public ArrayList<WatchResourcesInfo> getAllWatchInfo() {
        ArrayList<WatchResourcesInfo> arrayList = new ArrayList<>(20);
        arrayList.addAll(getWatchFaces());
        return arrayList;
    }

    public HashMap<String, WatchResourcesInfo> getAllWatchInfoHash() {
        HashMap<String, WatchResourcesInfo> hashMap = new HashMap<>(20);
        hashMap.putAll(this.mHashWatchFaces);
        return hashMap;
    }

    public boolean getVipFreeFromCache(String str) {
        WatchResourcesInfo watchResourcesInfo = this.mHashWatchFaces.get(str);
        if (watchResourcesInfo == null) {
            WatchResourcesInfo vipFreeWatchFace = HwWatchFaceManager.getInstance(this.mContext).getVipFreeWatchFace(str);
            return vipFreeWatchFace != null && vipFreeWatchFace.isVipFreeWatchFace();
        }
        return watchResourcesInfo.isVipFreeWatchFace();
    }

    public ArrayList<WatchResourcesInfo> getPresetWatchInfo() {
        ArrayList<WatchResourcesInfo> arrayList = new ArrayList<>(20);
        Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            if ((next.getWatchInfoType() & 2) == 2) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public ArrayList<WatchResourcesInfo> getNoPresetWatchInfo() {
        ArrayList<WatchResourcesInfo> arrayList = new ArrayList<>(20);
        if (this.mWatchFaces.isEmpty()) {
            this.isGetWatchfaceLocal = true;
            return arrayList;
        }
        Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            if ((next.getWatchInfoType() & 2) == 0) {
                arrayList.add(next);
            }
        }
        this.isGetWatchfaceLocal = false;
        return arrayList;
    }

    public ArrayList<WatchResourcesInfo> getEditWatchInfo() {
        ArrayList<WatchResourcesInfo> arrayList = new ArrayList<>(20);
        Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            if ((next.getWatchInfoType() & 8) == 8) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public String getVideoPackageName() {
        synchronized (HwWatchFaceBtManager.class) {
            if (this.mWatchFaces.isEmpty()) {
                this.isGetVideoAlbumPackageName = true;
                return "";
            }
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 16) == 16) {
                    String watchInfoId = next.getWatchInfoId();
                    HwLog.i(TAG, "getVideoPackageName() videoPackageName: " + watchInfoId);
                    this.isGetVideoAlbumPackageName = false;
                    return watchInfoId;
                }
            }
            return "";
        }
    }

    public String getPhotoPackageName() {
        synchronized (HwWatchFaceBtManager.class) {
            if (this.mWatchFaces.isEmpty()) {
                this.isGetAlbumPackageName = true;
                return "";
            }
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 32) == 32) {
                    String watchInfoId = next.getWatchInfoId();
                    HwLog.i(TAG, "getPhotoPackageName() photoPackageName: " + watchInfoId);
                    this.isGetAlbumPackageName = false;
                    return watchInfoId;
                }
            }
            return "";
        }
    }

    public String getWearPackageName() {
        HwLog.i(TAG, "getWearPackageName() enter: ");
        synchronized (HwWatchFaceBtManager.class) {
            if (this.mWatchFaces.isEmpty()) {
                this.isGetWearAlbumPackageName = true;
                return "";
            }
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if (next.getWatchExpandType() == 4) {
                    String watchInfoId = next.getWatchInfoId();
                    HwLog.i(TAG, "getWearPackageName() wearPackageName: " + watchInfoId);
                    this.isGetWearAlbumPackageName = false;
                    return watchInfoId;
                }
            }
            return "";
        }
    }

    public String getTryOutWatchFacePackageNameAndVersion() {
        synchronized (HwWatchFaceBtManager.class) {
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 64) == 64) {
                    String str = next.getWatchInfoId() + "_" + next.getWatchInfoVersion();
                    HwLog.i(TAG, "getTryOutWatchFacePackageNameAndVersion() tryOutPackageNameAndVersion: " + str);
                    return str;
                }
            }
            return "";
        }
    }

    public String getTryOutWatchFacePackageName() {
        synchronized (HwWatchFaceBtManager.class) {
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 64) == 64) {
                    String watchInfoId = next.getWatchInfoId();
                    HwLog.i(TAG, "getTryOutWatchFacePackageName() tryOutPackageName: " + watchInfoId);
                    return watchInfoId;
                }
            }
            return "";
        }
    }

    public WatchResourcesInfo getTryOutWatchFaceInfo() {
        synchronized (HwWatchFaceBtManager.class) {
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 64) == 64) {
                    return next;
                }
            }
            return null;
        }
    }

    public String getKaleidoscopePackageName() {
        synchronized (HwWatchFaceBtManager.class) {
            if (this.mWatchFaces.isEmpty()) {
                this.isGetKaleidoscopePackageName = true;
                return "";
            }
            Iterator<WatchResourcesInfo> it = getWatchFaces().iterator();
            while (it.hasNext()) {
                WatchResourcesInfo next = it.next();
                if ((next.getWatchInfoType() & 128) == 128) {
                    String watchInfoId = next.getWatchInfoId();
                    HwLog.i(TAG, "getKaleidoscopePackageName() KaleidoscopePackageName: " + watchInfoId);
                    this.isGetKaleidoscopePackageName = false;
                    return watchInfoId;
                }
            }
            return "";
        }
    }

    public WatchFacePhotoInfo getWatchFacePhotoInfo() {
        return this.mWatchFacePhotoInfo;
    }

    public void addDestroyListener(BaseWatchFaceManager baseWatchFaceManager) {
        HwLog.i(TAG, "addDestroyListener() enter.");
        if (this.destroyListeners == null) {
            this.destroyListeners = new ArrayList();
        }
        this.destroyListeners.add(baseWatchFaceManager);
    }

    public void destroy() {
        HwLog.i(TAG, "destroy() enter.");
        destroyInstance();
        i.a(Environment.getApplicationContext()).a();
        j.a(Environment.getApplicationContext()).a();
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).onDestroy();
        WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).onDestroy();
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).onDestroy();
        Iterator<BaseWatchFaceManager> it = this.destroyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    private static void destroyInstance() {
        synchronized (HwWatchFaceBtManager.class) {
            HwLog.i(TAG, "destroyInstance() enter.");
            sInstance.unRegisterHealthResponseListener();
            sInstance = null;
            mDeviceCurrentWatchFace = null;
        }
    }

    public WatchFaceWearInfo getWatchFaceWearInfo() {
        return this.mWatchFaceWearInfo;
    }

    public void getDeviceList(final ResultCallback resultCallback) {
        HwLog.i(TAG, "getDeviceList() enter.");
        h.a(this.mContext, new ResultCallback() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager.1
            @Override // com.huawei.hihealth.listener.ResultCallback
            public void onResult(int i, Object obj) {
                HwLog.i(HwWatchFaceBtManager.TAG, "getDeviceList() onResult resultCode: " + i);
                if (i != 0) {
                    HwLog.i(HwWatchFaceBtManager.TAG, "getDeviceList() device connect failed.");
                    resultCallback.onResult(1, "");
                    return;
                }
                if (obj == null) {
                    HwLog.e(HwWatchFaceBtManager.TAG, "getDeviceList() onResult detailInfo is null.");
                    return;
                }
                HwWatchFaceBtManager.this.mWatchDeviceInfoList = (List) new Gson().fromJson(obj.toString(), new TypeToken<List<WatchDeviceInfo>>() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager.1.1
                }.getType());
                WatchDeviceInfo connectWatchDeviceInfo = HwWatchFaceBtManager.this.getConnectWatchDeviceInfo();
                if (connectWatchDeviceInfo == null) {
                    HwLog.i(HwWatchFaceBtManager.TAG, "getDeviceList() deviceInfo is null.");
                    resultCallback.onResult(2, "");
                } else {
                    HwWatchFaceBtManager.this.setDeviceCapability(connectWatchDeviceInfo);
                    HwWatchFaceBtManager.this.setCurrentDeviceName(connectWatchDeviceInfo);
                    resultCallback.onResult(0, "");
                    HwWatchFaceBtManager.this.writeDeviceInfo2Sp(connectWatchDeviceInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceCapability(WatchDeviceInfo watchDeviceInfo) {
        if (watchDeviceInfo == null) {
            HwLog.w(TAG, "setDeviceCapability() watchDeviceInfo is null.");
            return;
        }
        DeviceCapability deviceCapability = (DeviceCapability) new Gson().fromJson(watchDeviceInfo.getDeviceCapability(), DeviceCapability.class);
        if (deviceCapability == null) {
            HwLog.w(TAG, "setDeviceCapability() capability is null.");
        } else {
            this.mIsSupportWatchFaceAppId = deviceCapability.isSupportWatchFaceAppId();
        }
    }

    public boolean isSupportWatchFaceAppId() {
        return this.mIsSupportWatchFaceAppId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentDeviceName(WatchDeviceInfo watchDeviceInfo) {
        if (watchDeviceInfo == null) {
            HwLog.i(TAG, "setCurrentDeviceName() watchDeviceInfo is null.");
            return;
        }
        HwLog.i(TAG, "setCurrentDeviceName() currentDeviceName: " + watchDeviceInfo.getDeviceName());
        HwWatchFaceManager.getInstance(this.mContext).setCurrentDeviceName(watchDeviceInfo.getDeviceName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeDeviceInfo2Sp(WatchDeviceInfo watchDeviceInfo) {
        if (watchDeviceInfo == null) {
            HwLog.i(TAG, "writeDeviceInfo2Sp() watchDeviceInfo is null.");
        } else {
            dz.a("buildNumber", watchDeviceInfo.getSoftVersion());
            dz.a("phoneType", watchDeviceInfo.getDeviceModel());
        }
    }

    private void notifyBtConnectStatus(String str) {
        HwLog.i(TAG, "notifyBtConnectStatus() btMsg: " + str);
        try {
            Application applicationContext = Environment.getApplicationContext();
            if (applicationContext != null) {
                HwWatchFaceApi.getInstance(applicationContext).setConnectStatus(null);
                HwWatchFaceApi.getInstance(applicationContext).setDeviceInfo(null);
                HwWatchFaceManager.getInstance(applicationContext).setLastPercentage(-1);
            }
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("mDeviceConnectState");
            String optString = jSONObject.optString("mDeviceName");
            if (2 == optInt || 3 == optInt) {
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setDeviceConnectState(optInt);
                deviceInfo.setDeviceName(optString);
                Intent intent = new Intent("com.huawei.watchface.action.CONNECTION_STATE_CHANGED");
                intent.putExtra("deviceinfo", deviceInfo);
                LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
            }
        } catch (JSONException unused) {
            HwLog.i(TAG, "notifyBtConnectStatus() JSONException occured.");
        } catch (Exception unused2) {
            HwLog.i(TAG, "notifyBtConnectStatus() Exception occured.");
        }
    }

    public WatchDeviceInfo getConnectWatchDeviceInfo() {
        if (Environment.sIsAarInTheme) {
            if (ArrayUtils.isEmpty(this.mWatchDeviceInfoList)) {
                HwLog.e(TAG, "getConnectWatchDeviceInfo() mWatchDeviceInfoList is null.");
                return null;
            }
            for (WatchDeviceInfo watchDeviceInfo : this.mWatchDeviceInfoList) {
                if (watchDeviceInfo.getDeviceConnectState() == 2) {
                    return watchDeviceInfo;
                }
            }
            return null;
        }
        if (ArrayUtils.a(HwWatchFaceApi.getInstance(this.mContext).getDeviceInfo())) {
            HwLog.e(TAG, "getConnectWatchDeviceInfo() mapInfo is null.");
            return null;
        }
        WatchDeviceInfo watchDeviceInfo2 = new WatchDeviceInfo();
        watchDeviceInfo2.setDeviceModel(HwWatchFaceApi.getInstance(this.mContext).getDeviceModel());
        watchDeviceInfo2.setDeviceName(HwWatchFaceApi.getInstance(this.mContext).getDeviceName());
        watchDeviceInfo2.setSoftVersion(HwWatchFaceApi.getInstance(this.mContext).getSoftVersion());
        return watchDeviceInfo2;
    }

    public void setOnAuthDenyCallback(IAuthDenyCallback iAuthDenyCallback) {
        this.mAuthDenyCallback = iAuthDenyCallback;
    }

    public void judgeDataShareAuth(final IBaseResponseCallback iBaseResponseCallback) {
        HwLog.i(TAG, "judgeDataShareAuth() enter.");
        getDeviceList(new ResultCallback() { // from class: com.huawei.watchface.api.HwWatchFaceBtManager$$ExternalSyntheticLambda3
            @Override // com.huawei.hihealth.listener.ResultCallback
            public final void onResult(int i, Object obj) {
                HwWatchFaceBtManager.this.m877x3708c1fb(iBaseResponseCallback, i, obj);
            }
        });
    }

    /* renamed from: lambda$judgeDataShareAuth$4$com-huawei-watchface-api-HwWatchFaceBtManager, reason: not valid java name */
    /* synthetic */ void m877x3708c1fb(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        HwLog.i(TAG, "judgeDataShareAuth() getDeviceList onResult i: " + i);
        if (i == 0) {
            this.mIsJudgeSecondAuth = true;
            getDeviceInfoForUi(iBaseResponseCallback);
        } else if (2 == i) {
            iBaseResponseCallback.onResponse(2, "");
        } else {
            iBaseResponseCallback.onResponse(1, "");
        }
    }

    public String getSoftVersion() {
        WatchDeviceInfo connectWatchDeviceInfo = getConnectWatchDeviceInfo();
        if (connectWatchDeviceInfo == null) {
            HwLog.e(TAG, "getSoftVersion() watchDeviceInfo is null.");
            return dz.a("buildNumber");
        }
        return connectWatchDeviceInfo.getSoftVersion();
    }

    public String getDeviceModel() {
        WatchDeviceInfo connectWatchDeviceInfo = getConnectWatchDeviceInfo();
        if (connectWatchDeviceInfo == null) {
            HwLog.e(TAG, "getDeviceModel() watchDeviceInfo is null.");
            return dz.a("phoneType");
        }
        return connectWatchDeviceInfo.getDeviceModel();
    }

    public String getDeviceName() {
        WatchDeviceInfo connectWatchDeviceInfo = getConnectWatchDeviceInfo();
        if (connectWatchDeviceInfo == null) {
            HwLog.e(TAG, "getDeviceModel() watchDeviceInfo is null.");
            return "";
        }
        return connectWatchDeviceInfo.getDeviceName();
    }

    public String getWatchVersion() {
        String str;
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.i(TAG, "getWatchVersion() mWatchFaceSupportInfo is null.");
            return dz.a("watch_version");
        }
        String otherWatchFaceVersion = watchFaceSupportInfo.getOtherWatchFaceVersion();
        if (TextUtils.isEmpty(otherWatchFaceVersion)) {
            str = watchFaceSupportInfo.getWatchFaceMaxVersion();
        } else {
            str = otherWatchFaceVersion + "," + watchFaceSupportInfo.getWatchFaceMaxVersion();
        }
        return TextUtils.equals("null,null", str) ? dz.a("watch_version") : str;
    }

    public String getWatchFaceMaxVersion(boolean z, boolean z2) {
        if (z2) {
            HwLog.i(TAG, "getWatchFaceScreen() isVirtual");
            return getInstance(Environment.getApplicationContext()).getVirtualWatchInfo("watch_max_version");
        }
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.i(TAG, "getWatchFaceMaxVersion() mWatchFaceSupportInfo is null.");
            return z ? dz.a("watch_max_version") : "";
        }
        String watchFaceMaxVersion = watchFaceSupportInfo.getWatchFaceMaxVersion();
        if (TextUtils.isEmpty(watchFaceMaxVersion)) {
            return z ? dz.a("watch_max_version") : "";
        }
        return watchFaceMaxVersion;
    }

    public int getWatchfaceMode() {
        WatchFaceSupportInfo watchFaceSupportInfo;
        if (isCloudOpenSync() && (watchFaceSupportInfo = getWatchFaceSupportInfo()) != null) {
            return watchFaceSupportInfo.getWatchfaceMode();
        }
        return -1;
    }

    public String getWatchFaceMaxVersonBySurport() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return "";
        }
        String watchFaceMaxVersion = watchFaceSupportInfo.getWatchFaceMaxVersion();
        List<ScreenInfo> compatibleList = watchFaceSupportInfo.getCompatibleList();
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(watchFaceMaxVersion)) {
            arrayList.add(watchFaceMaxVersion);
        }
        Iterator<ScreenInfo> it = compatibleList.iterator();
        while (it.hasNext()) {
            for (String str : it.next().getSupportVersion().split(",")) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        }
        String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        if (ArrayUtils.isEmpty(strArr)) {
            HwLog.i(TAG, "getWatchFaceMaxVersonBySurport()  versionStrings is null ");
            return "";
        }
        Arrays.sort(strArr);
        return (String) ArrayUtils.a(strArr, strArr.length - 1);
    }

    public String getWatchFaceOtherVersion() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.i(TAG, "getWatchFaceOtherVersion() mWatchFaceSupportInfo is null.");
            return dz.a("watch_other_version");
        }
        String otherWatchFaceVersion = watchFaceSupportInfo.getOtherWatchFaceVersion();
        return TextUtils.isEmpty(otherWatchFaceVersion) ? dz.a("watch_other_version") : otherWatchFaceVersion;
    }

    public String getWatchFaceScreen(boolean z, boolean z2) {
        if (z2) {
            HwLog.i(TAG, "getWatchFaceScreen() isVirtual");
            return getInstance(Environment.getApplicationContext()).getVirtualWatchInfo("watch_screen");
        }
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.i(TAG, "getWatchFaceScreen() mWatchFaceSupportInfo is null.");
            return z ? dz.a("watch_screen") : "";
        }
        String watchFaceScreen = watchFaceSupportInfo.getWatchFaceScreen();
        if (TextUtils.equals("0*0", watchFaceScreen)) {
            watchFaceScreen = z ? dz.a("watch_screen") : "";
        }
        HwLog.i(TAG, "getWatchFaceScreen() screen: " + watchFaceScreen);
        return watchFaceScreen;
    }

    public void registerHealthResponseListener() {
        if (!Environment.sIsAarInTheme) {
            HwWatchFaceApi.getInstance(this.mContext).registerHealthResponseListener(this.mHealthResponseListener);
        } else {
            HwLog.i(TAG, "registerHealthResponseListener() sIsAarInTheme.");
        }
    }

    private void unRegisterHealthResponseListener() {
        HwWatchFaceApi.getInstance(this.mContext).unRegisterHealthResponseListener();
    }

    public WatchFaceVideoInfo getWatchFaceVideoInfo() {
        return this.mWatchFaceVideoInfo;
    }

    public boolean isSupportTryOut() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return false;
        }
        return ((watchFaceSupportInfo.getSupportCapability() & 1) == 1) && t.a().i();
    }

    public boolean isSupportDialUnite() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo != null) {
            return (watchFaceSupportInfo.getSupportCapability() & 4) == 4;
        }
        HwLog.d(TAG, "getWatchFaceSupportInfo is null");
        return (dz.b("supportCapability", getDefaultSupportDialUnite()) & 4) == 4;
    }

    public int getDefaultSupportDialUnite() {
        try {
            String deviceName = HwWatchFaceApi.getInstance(this.mContext).getDeviceName();
            String deviceModel = HwWatchFaceApi.getInstance(this.mContext).getDeviceModel();
            HwLog.d(TAG, "getDefaultSupportDialUnite deviceName:" + deviceName + ",deviceModel:" + deviceModel);
            JSONObject jSONObject = new JSONObject(NewSystemParamManager.getSystemParam("client_support_dial_unite", "{\"supportDeviceName\":[\"HUAWEI WATCH FIT\"],\"supportDeviceModel\":[\"Molly\"],\"upDeviceName\":[\"HUAWEI Band_8\",\"HUAWEI WATCH GT_3\"]}"));
            JSONArray optJSONArray = jSONObject.optJSONArray("supportDeviceName");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("supportDeviceModel");
            JSONArray optJSONArray3 = jSONObject.optJSONArray("upDeviceName");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    HwLog.d(TAG, "tempName:" + optString);
                    if (deviceName != null && deviceName.contains(optString)) {
                        return 5;
                    }
                }
            }
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    String optString2 = optJSONArray2.optString(i2);
                    HwLog.d(TAG, "tempModel:" + optString2);
                    if (deviceModel != null && deviceModel.contains(optString2)) {
                        return 5;
                    }
                }
            }
            if (optJSONArray3 != null) {
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    String optString3 = optJSONArray3.optString(i3);
                    HwLog.d(TAG, "tempUpDeviceName:" + optString3);
                    String[] split = optString3.split("_");
                    if (split.length >= 2) {
                        String str = split[0];
                        String str2 = split[1];
                        String a2 = an.a(deviceName, str + " (\\d+)", 1);
                        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(str2) && IntegerUtils.getInteger(a2, -1) >= IntegerUtils.getInteger(str2, 0)) {
                            return 5;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            HwLog.i(TAG, "json parse error : " + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.i(TAG, "json parse Exception : " + HwLog.printException(e2));
        }
        return 0;
    }

    private void resetWatchFaceSupportInfo() {
        HwLog.i(TAG, "resetWatchFaceSupportInfo() enter.");
        if (this.mWatchFaceSupportInfo == null) {
            return;
        }
        this.mWatchFaceSupportInfo = null;
        this.mVirtualWatchFaceSupportInfo = null;
    }

    public void resetWatchFaceSupportInfoAndWatchFaces() {
        HwLog.i(TAG, "resetWatchFaceSupportInfoAndWatchFaces() enter.");
        CopyOnWriteArrayList<WatchResourcesInfo> copyOnWriteArrayList = this.mWatchFaces;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        resetWatchFaceSupportInfo();
    }

    public WatchFaceKaleidoscopeInfo getWatchFaceKaleidoscopeInfo() {
        return this.mWatchFaceKaleidoscopeInfo;
    }

    public String getVirtualWatchInfo(String str) {
        HwLog.i(TAG, "getDefaultWatchInfo() enter key ==" + str);
        if (this.mDefaultWatchInfo == null) {
            this.mDefaultWatchInfo = WatchInfoForH5.createGalileoMap();
        }
        return this.mDefaultWatchInfo.get(str) == null ? "" : this.mDefaultWatchInfo.get(str);
    }

    private void handleWearInfo(byte[] bArr) {
        String a2 = da.a(bArr);
        this.mWatchFaceWearInfo = null;
        this.mWatchFaceWearInfo = new WatchFaceWearInfo();
        String str = TAG;
        HwLog.i(str, "5.39.19 handleWearInfo() info: " + a2);
        try {
            TlvFather builderTlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(19)));
            List<Tlv> tlvList = builderTlvList.getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                for (Tlv tlv : tlvList) {
                    handleWearInfoTlv(IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                }
            } else {
                HwLog.w(str, "handleWearInfo() tlvList error");
            }
            if (!builderTlvList.getTlvFatherList().isEmpty()) {
                handleWearInfoTlv(builderTlvList.getTlvFatherList().get(0));
            } else {
                this.mWatchFaceWearInfo.setWearList(new ArrayList<>(20));
            }
        } catch (TlvException e) {
            HwLog.e(TAG, "handleWearInfo() TlvException" + HwLog.printException((Exception) e));
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(19);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).onResponse(101, this.mWatchFaceWearInfo);
                sCommandCallbacks.remove(19);
            } else {
                HwLog.w(TAG, "handleWearInfo() callback error");
            }
        }
    }

    private void handleWearInfoTlv(int i, String str) {
        if (i == 1) {
            this.mWatchFaceWearInfo.setMaxStyleNum(IntegerUtils.b(str, 16));
            HwLog.d(TAG, "handleWearInfoTlv() setMaxStyleNum: " + this.mWatchFaceWearInfo.getMaxStyleNum());
        }
        if (i == 2) {
            this.mWatchFaceWearInfo.setStyleCount(IntegerUtils.b(str, 16));
            HwLog.d(TAG, "handleWearInfoTlv() setStyleCount: " + this.mWatchFaceWearInfo.getStyleCount());
            return;
        }
        if (i == 3) {
            this.mWatchFaceWearInfo.setWearStyleType(IntegerUtils.b(str, 16));
            HwLog.d(TAG, "handleWearInfoTlv() setWearStyleType: " + this.mWatchFaceWearInfo.getWearStyleType());
            return;
        }
        if (i == 4) {
            this.mWatchFaceWearInfo.setWearTypeCapability(IntegerUtils.c(str, 16));
            HwLog.d(TAG, "handleWearInfoTlv() setWearTypeCapability: " + this.mWatchFaceWearInfo.getWearTypeCapability());
            return;
        }
        if (i == 5) {
            this.mWatchFaceWearInfo.setClockTypeCapability(IntegerUtils.c(str, 16));
            HwLog.d(TAG, "handleWearInfoTlv() setClockTypeCapability: " + this.mWatchFaceWearInfo.getClockTypeCapability());
            return;
        }
        switch (i) {
            case 10:
                this.mWatchFaceWearInfo.setCurStyleIndex(IntegerUtils.c(str, 16));
                HwLog.d(TAG, "handleWearInfoTlv() setCurStyleIndex: " + this.mWatchFaceWearInfo.getCurStyleIndex());
                break;
            case 11:
                this.mWatchFaceWearInfo.setWearImageOption(IntegerUtils.b(str, 16));
                HwLog.d(TAG, "handleWearInfoTlv() setWearImageOption: " + this.mWatchFaceWearInfo.getWearImageOption());
                break;
            case 12:
                this.mWatchFaceWearInfo.setTintClockOption(IntegerUtils.b(str, 16));
                HwLog.d(TAG, "handleWearInfoTlv() setTintClockOption: " + this.mWatchFaceWearInfo.getTintClockOption());
                break;
            case 13:
                this.mWatchFaceWearInfo.setRectRadius(IntegerUtils.b(str, 16));
                HwLog.d(TAG, "handleWearInfoTlv() setRectRadius: " + this.mWatchFaceWearInfo.getRectRadius());
                break;
            default:
                HwLog.w(TAG, "handleWearInfoTlv() default type:" + i);
                break;
        }
    }

    private void handleWearInfoTlv(TlvFather tlvFather) {
        HwLog.i(TAG, "handleWearInfoTlv() enter.");
        ArrayList<WearStruct> arrayList = new ArrayList<>();
        Iterator<TlvFather> it = tlvFather.getTlvFatherList().iterator();
        long j = -1;
        String str = "";
        while (it.hasNext()) {
            for (Tlv tlv : it.next().getTlvList()) {
                int b = IntegerUtils.b(tlv.getTag());
                if (b == 8) {
                    j = IntegerUtils.c(tlv.getValue());
                    HwLog.i(TAG, "handleWearInfoTlv styleId=" + j);
                } else if (b == 9) {
                    str = da.b(tlv.getValue());
                    HwLog.i(TAG, "handleWearInfoTlv previewName=" + str);
                } else {
                    HwLog.w(TAG, "handleWearInfoTlv() error");
                }
            }
            arrayList.add(new WearStruct(j, str));
        }
        this.mWatchFaceWearInfo.setWearList(arrayList);
    }

    private void handleWearStatus(byte[] bArr) {
        int i;
        String a2 = da.a(bArr);
        String str = TAG;
        HwLog.i(str, "5.39.20 handleWearStatus() info: " + a2);
        int i2 = 0;
        try {
            List<Tlv> tlvList = this.mTlvUtils.builderTlvList(SafeString.substring(a2, CommonUtils.b(20))).getTlvList();
            if (tlvList != null && !tlvList.isEmpty()) {
                int i3 = 0;
                for (Tlv tlv : tlvList) {
                    try {
                        int b = IntegerUtils.b(tlv.getTag(), 16);
                        String value = tlv.getValue();
                        if (b == 11) {
                            i2 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handleWearStatus() transferNum:" + i2);
                        } else if (b == 127) {
                            i3 = IntegerUtils.b(value, 16);
                            HwLog.i(TAG, "handleWearStatus() errorCode:" + i3);
                        } else {
                            HwLog.w(TAG, "handleWearStatus() nothing to do.");
                        }
                    } catch (TlvException e) {
                        e = e;
                        int i4 = i2;
                        i2 = i3;
                        i = i4;
                        HwLog.e(TAG, "handleWearStatus() error." + HwLog.printException((Exception) e));
                        reportWearStatus(i2, i);
                    }
                }
                int i5 = i2;
                i2 = i3;
                i = i5;
            } else {
                HwLog.e(str, "handleWearStatus() tlv error.");
                i = 0;
            }
        } catch (TlvException e2) {
            e = e2;
            i = i2;
        }
        reportWearStatus(i2, i);
    }

    private void reportWearStatus(int i, int i2) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(20);
            if (list == null || list.isEmpty()) {
                HwLog.w(TAG, "reportWearStatus() callback error.");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).onResponse(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                HwLog.i(TAG, "reportWearStatus() need transfer.");
                list.get(list.size() - 1).onResponse(114, Integer.valueOf(i2));
            } else {
                HwLog.i(TAG, "reportWearStatus() errorCode is failed.");
                list.get(list.size() - 1).onResponse(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    public void getWatchFaceWearInfo(int i, IBaseResponseCallback iBaseResponseCallback) {
        HwLog.i(TAG, "getWatchFaceWearInfo() enter");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(19);
                if (list == null) {
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(19, arrayList);
                } else {
                    list.add(iBaseResponseCallback);
                }
            }
        }
        this.mWearOptionType = i;
        sendWearWatchFaceCommand();
    }

    private void sendWearWatchFaceCommand() {
        String str = CommonUtils.a() + (da.a(1) + da.a(0)) + (da.a(2) + da.a(0)) + (da.a(3) + da.a(0)) + (da.a(4) + da.a(0)) + (da.a(5) + da.a(0)) + (da.a(6) + da.a(0)) + (da.a(8) + da.a(0)) + (da.a(9) + da.a(0)) + (da.a(10) + da.a(0));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(19);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(TAG, "5.39.19 sendWearWatchFaceCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public void saveWearInfoToDevice(WatchFaceWearInfo watchFaceWearInfo, IBaseResponseCallback iBaseResponseCallback) {
        String str = TAG;
        HwLog.i(str, "saveWearInfoToDevice() enter." + GsonHelper.toJson(watchFaceWearInfo));
        if (watchFaceWearInfo == null) {
            iBaseResponseCallback.onResponse(102, "");
            HwLog.w(str, "saveWearInfoToDevice() error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(20);
                if (list == null) {
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(20, arrayList);
                } else {
                    list.add(iBaseResponseCallback);
                }
            }
        }
        makeWearInfoCommand(watchFaceWearInfo);
    }

    private void makeWearInfoCommand(WatchFaceWearInfo watchFaceWearInfo) {
        StringBuilder sb = new StringBuilder(20);
        Iterator<WearStruct> it = watchFaceWearInfo.getWearList().iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            String str = (da.a(4) + da.a(8) + da.a(next.getCurStyleIndex())) + (da.a(5) + da.a(da.c(next.getWearPreviewName()).length() / 2) + da.c(next.getWearPreviewName())) + (da.a(6) + da.a(1) + da.a(1)) + (da.a(7) + da.a(da.c(next.getClockResource()).length() / 2) + da.c(next.getClockResource())) + (da.a(8) + da.a(1) + da.a(next.getClockType())) + (da.a(9) + da.a(da.c(next.getClockColor()).length() / 2) + da.c(next.getClockColor()));
            sb.append(da.a(131) + da.a(str.length() / 2) + str);
        }
        int length = sb.length();
        sb.append(da.a(10) + da.a(8) + da.a(watchFaceWearInfo.getCurStyleIndex()));
        int size = watchFaceWearInfo.getWearList().size();
        String str2 = da.a(1) + da.a(1) + da.a(size);
        if (size != 0) {
            str2 = str2 + (da.a(OldToNewMotionPath.SPORT_TYPE_TENNIS) + da.b(length / 2) + ((Object) sb));
        }
        String str3 = CommonUtils.b() + str2;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(39);
        commandJsonInfo.setCommandId(20);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str3);
        HwLog.i(TAG, "5.39.20 makeWearInfoCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.mContext, commandJsonInfo, this.mResultCallback);
    }

    public boolean isSupportedHumanPortrait() {
        synchronized (this) {
            WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
            if (watchFaceSupportInfo != null) {
                return watchFaceSupportInfo.isPortraitModeSupported();
            }
            HwLog.i(TAG, "isSupportedHumanPortrait() watchFaceSupportInfo is null");
            return false;
        }
    }

    public boolean isSupportSync() {
        WatchFaceSupportInfo watchFaceSupportInfo;
        if (isCloudOpenSync() && (watchFaceSupportInfo = getWatchFaceSupportInfo()) != null) {
            return watchFaceSupportInfo.isIsSupportSync();
        }
        return false;
    }

    public boolean isSportGTModel() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        return watchFaceSupportInfo != null && watchFaceSupportInfo.getWatchfaceMode() == 0;
    }

    public String getWatchfaceModel() {
        WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return String.valueOf(-1);
        }
        return String.valueOf(watchFaceSupportInfo.getWatchfaceMode());
    }

    public boolean isSupportOneTouchAbility() {
        synchronized (this) {
            WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
            boolean z = false;
            if (watchFaceSupportInfo == null) {
                return false;
            }
            if (watchFaceSupportInfo.isSupportOneTouchAbility()) {
                if (isPhoneSupport()) {
                    z = true;
                }
            }
            return z;
        }
    }

    public boolean isSupportNewAlbum() {
        synchronized (this) {
            WatchFaceSupportInfo watchFaceSupportInfo = getWatchFaceSupportInfo();
            if (watchFaceSupportInfo == null) {
                return false;
            }
            return watchFaceSupportInfo.isSupportNewAlbum();
        }
    }

    private boolean isPhoneSupport() {
        PackageManager packageManager = Environment.getApplicationContext().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.nfc");
        HwLog.i(TAG, "PackageManager isSupportNfc:" + hasSystemFeature);
        return hasSystemFeature && isHandoffServiceSupported();
    }

    public static boolean isHandoffServiceSupported() {
        boolean z = false;
        try {
            Class.forName(CLASS_NAME_OF_HANDOFF_SDK);
            String e = lsk.c().e();
            String str = TAG;
            HwLog.i(str, "HwOneHopSdk versionCodeString :" + e);
            if (TextUtils.isEmpty(e)) {
                return false;
            }
            String[] split = e.split("\\.");
            if (split.length > 3) {
                z = (CommonUtils.b(split[1], 0) != 2 || CommonUtils.b(split[2], 0) < 1) ? CommonUtils.b(split[1], 0) > 2 : true;
            }
            HwLog.i(str, "isHandoffServiceSupported:" + z);
            return z;
        } catch (ClassNotFoundException unused) {
            HwLog.e(TAG, "isHandoffServiceSupported can not find HwHandoffSdk");
            return false;
        }
    }

    public void refreshUi(Context context) {
        String str = TAG;
        HwLog.i(str, "refreshUi enter");
        if (this.mWatchFaceSupportInfo == null) {
            HwLog.d(str, "mWatchFaceSupportInfo is null");
            HwWatchFaceManager.getInstance(context).getDeviceInfoByBt(false);
        }
        if (this.mWatchFaces.isEmpty()) {
            HwLog.d(str, "mWatchFaces is empty");
            HwWatchFaceManager.getInstance(context).doRefreshUi();
        }
    }
}
