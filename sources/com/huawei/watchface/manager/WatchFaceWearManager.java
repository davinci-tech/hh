package com.huawei.watchface.manager;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.dh;
import com.huawei.watchface.er;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.mvp.model.datatype.WatchFaceWearInfo;
import com.huawei.watchface.mvp.model.datatype.WearStruct;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.latona.BackgroundOptionalConfig;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceThemeAlbumInfo;
import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import com.huawei.watchface.utils.analytice.data.AnalyticsGeneralEvent;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class WatchFaceWearManager extends BaseWatchFaceManager {
    private static final String i = "WatchFaceWearManager";
    private static volatile WatchFaceWearManager l;
    private static final String[] r = {"res", "hwt", "parsing", "resources"};
    private static String s;
    private static Uri t;
    protected final String h;
    private OnWearSdkEvent j;
    private final Map<Integer, Integer> k;
    private List<Integer> m;
    private ArrayList<WearStruct> n;
    private ArrayList<WearStruct> o;
    private ArrayList<String> p;
    private ArrayList<WearStruct> q;

    public interface OnWearSdkEvent {
        void setSupportedClocks(ArrayList<Integer> arrayList);
    }

    public WatchFaceWearManager(Context context) {
        super(context, WatchFaceConstant.WEAR_PREVIEW_ROOT_PATH, true);
        this.k = new HashMap<Integer, Integer>() { // from class: com.huawei.watchface.manager.WatchFaceWearManager.1
            private static final long serialVersionUID = 4970895796906924736L;

            {
                put(0, 1);
                put(1, 2);
                put(2, 3);
                put(3, 4);
                put(4, 7);
                put(5, 8);
                put(6, 9);
                put(7, 10);
                put(8, 11);
            }
        };
        this.m = new ArrayList(32);
        this.n = new ArrayList<>(32);
        this.o = new ArrayList<>(32);
        this.p = new ArrayList<>(20);
        this.q = new ArrayList<>(32);
        this.h = this.c + "resources/";
    }

    public String getWatchFaceRootDir() {
        return this.c;
    }

    public String getWatchFaceResourcePath() {
        return this.h;
    }

    public String getFilePathFromPicker() {
        return s;
    }

    public Uri getOriginalUriFromPicker() {
        return t;
    }

    public static WatchFaceWearManager getInstance(Context context) {
        if (l == null) {
            synchronized (WatchFaceWearManager.class) {
                if (l == null) {
                    l = new WatchFaceWearManager(context.getApplicationContext());
                }
            }
        }
        return l;
    }

    public void setWearSdkCallback(OnWearSdkEvent onWearSdkEvent) {
        this.j = onWearSdkEvent;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    public void setWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.mOperateWatchFaceCallback = operateWatchFaceCallback;
    }

    private void a(ArrayList<String> arrayList) {
        int d = d();
        HwLog.i(i, "createBinBackground enter: ");
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(WatchFaceConstant.PREVIEW_RES) && next.endsWith(".png")) {
                String replace = SafeString.replace(next, ".png", WatchFaceConstant.BIN_SUFFIX);
                String str = this.mBackgroundDir + next;
                String str2 = this.mBackgroundDir + replace;
                String replace2 = SafeString.replace(str, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                String replace3 = SafeString.replace(str2, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                WatchFaceBitmapUtil.getInstance().a(str, str2, d);
                WatchFaceBitmapUtil.getInstance().a(replace2, replace3, d);
            }
        }
    }

    public int d() {
        WatchFaceWearInfo watchFaceWearInfo;
        if (this.mHwWatchFaceBtManager == null || (watchFaceWearInfo = this.mHwWatchFaceBtManager.getWatchFaceWearInfo()) == null) {
            return 0;
        }
        return watchFaceWearInfo.getWearImageOption();
    }

    public int e() {
        WatchFaceWearInfo watchFaceWearInfo;
        if (this.mHwWatchFaceBtManager == null || (watchFaceWearInfo = this.mHwWatchFaceBtManager.getWatchFaceWearInfo()) == null) {
            return 0;
        }
        return watchFaceWearInfo.getRectRadius();
    }

    public void f() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(i, "chooseWearPicToClip() failed mOperateWatchFaceCallback is null");
        } else {
            this.mOperateWatchFaceCallback.chooseToClip(3, false);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getDeviceInfoByBt() {
        HwLog.i(i, "getDeviceInfoByBt() enter.");
        this.mHwWatchFaceBtManager.getWatchFaceWearInfo(i(), new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFaceWearManager.2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i2, Object obj) {
                WatchFaceWearManager.this.n.clear();
                HwLog.i(WatchFaceWearManager.i, "getDeviceInfoByBt(): errorCode:" + i2);
                if (i2 != 101) {
                    HwLog.w(WatchFaceWearManager.i, "getDeviceInfoByBt() failed :" + i2);
                    WatchFaceWearManager.this.a(20002, "Error code: " + i2);
                    ArrayList<BackgroundOptionalConfig> arrayList = new ArrayList<>(32);
                    WatchFaceWearManager.this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList);
                    WatchFaceWearManager.this.mWatchFaceProvider.setDefaultBackgroundOptionalConfigList(arrayList);
                    WatchFaceWearManager.this.notifyWatchFaceReady(false);
                    return;
                }
                if (obj instanceof WatchFaceWearInfo) {
                    WatchFaceWearManager.this.a((WatchFaceWearInfo) obj);
                    return;
                }
                HwLog.w(WatchFaceWearManager.i, "getDeviceInfoByBt() objectData is null");
                WatchFaceWearManager.this.o.clear();
                WatchFaceWearManager.this.j();
                ArrayList<BackgroundOptionalConfig> arrayList2 = new ArrayList<>(32);
                WatchFaceWearManager.this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList2);
                WatchFaceWearManager.this.mWatchFaceProvider.setDefaultBackgroundOptionalConfigList(arrayList2);
                WatchFaceWearManager.this.notifyWatchFaceReady(false);
            }
        });
    }

    private int i() {
        int isSupportPositionOption = LatonaWatchFaceProvider.getInstance(this.mContext).isSupportPositionOption() | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportStyleOption() << 1) | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportDataOption() << 2);
        HwLog.i(i, "getRequestType() requestType: " + isSupportPositionOption);
        return isSupportPositionOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        File[] listFiles;
        File file = PversionSdUtils.getFile(this.mBackgroundDir);
        if (file == null || !file.exists()) {
            HwLog.w(i, "createWearWatchFace() -- file does not exist.");
            return;
        }
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            HwLog.d(i, "DeleteImageFile :" + file2.delete());
        }
    }

    private ArrayList<WearStruct> b(ArrayList<WearStruct> arrayList) {
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(i, "getUnDefaultWearStructs() wearStructs is null or empty.");
            return arrayList;
        }
        List<BackgroundOptionalConfig> defaultBackgroundOptionalConfigList = this.mWatchFaceProvider.getDefaultBackgroundOptionalConfigList();
        ArrayList<WearStruct> arrayList2 = new ArrayList<>(32);
        arrayList2.addAll(arrayList);
        Iterator<WearStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            for (BackgroundOptionalConfig backgroundOptionalConfig : defaultBackgroundOptionalConfigList) {
                if (SafeString.substring(backgroundOptionalConfig.getResPreview(), backgroundOptionalConfig.getResPreview().lastIndexOf("/") + 1).contains(next.getWearPreviewName())) {
                    arrayList2.remove(next);
                }
            }
        }
        return arrayList2;
    }

    private ArrayList<WearStruct> a(ArrayList<WearStruct> arrayList, String str, String str2) {
        if (arrayList == null || arrayList.size() == 0) {
            HwLog.i(i, "transformWearList() -- struct list is null or empty.");
            return arrayList;
        }
        Iterator<WearStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            if (next.getWearPreviewName() != null) {
                next.setWearPreviewName(SafeString.replace(next.getWearPreviewName(), str, str2));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WatchFaceWearInfo watchFaceWearInfo) {
        int styleCount = watchFaceWearInfo.getStyleCount();
        int maxStyleNum = watchFaceWearInfo.getMaxStyleNum();
        int wearStyleType = watchFaceWearInfo.getWearStyleType();
        long wearTypeCapability = watchFaceWearInfo.getWearTypeCapability();
        long curStyleIndex = watchFaceWearInfo.getCurStyleIndex();
        long clockTypeCapability = watchFaceWearInfo.getClockTypeCapability();
        if (clockTypeCapability >= 0) {
            if (this.j != null) {
                HwLog.i(i, "didGetDeviceInfoFromBt() -- mOnWearSdkEvent is not null");
                this.j.setSupportedClocks(b(Long.toBinaryString(clockTypeCapability)));
            }
            HwLog.i(i, "didGetDeviceInfoFromBt() -- support clock type.");
        } else {
            HwLog.w(i, "didGetDeviceInfoFromBt() -- clockTypeCapability is not supported.");
        }
        ArrayList<WearStruct> b = b(a(watchFaceWearInfo.getWearList(), WatchFaceConstant.BIN_SUFFIX, ".png"));
        this.mWatchFaceProvider.setWearInfo(styleCount, maxStyleNum, wearStyleType, wearTypeCapability, curStyleIndex, clockTypeCapability);
        this.n.addAll(b);
        String str = i;
        HwLog.i(str, "didGetDeviceInfoFromBt =" + GsonHelper.toJson(this.n));
        c(this.n);
        this.mTransferTotalCount = this.n.size();
        if (this.q.size() > 0) {
            HwLog.i(str, "didGetDeviceInfoFromBt() LocalImagesNotFound");
            getFileByBt(0, this.q.size(), 25, this.q.get(0).getWearPreviewName());
            return;
        }
        HwLog.i(str, "didGetDeviceInfoFromBt() LocalExistsAllBackgroundImages");
        this.o.clear();
        Iterator<WearStruct> it = b.iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            this.o.add(new WearStruct(next.getCurStyleIndex(), next.getWearPreviewName()));
        }
        notifyWatchFaceReady(false);
    }

    private ArrayList<Integer> b(String str) {
        Integer num;
        String str2 = i;
        HwLog.i(str2, "getSupportedClockCapability() -- enter.");
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (str.length() == 0) {
            HwLog.i(str2, "getSupportedClockCapability() -- supportCapability is empty.");
            return arrayList;
        }
        try {
            String sb = new StringBuilder(str).reverse().toString();
            for (int i2 = 0; i2 < sb.length(); i2++) {
                if (sb.charAt(i2) == '1' && (num = this.k.get(Integer.valueOf(i2))) != null) {
                    arrayList.add(num);
                }
            }
        } catch (Exception e) {
            HwLog.e(i, "getSupportedClockCapability() -- exception msg = " + HwLog.printException(e));
            a(20004, HwLog.printException(e));
        }
        return arrayList;
    }

    private void c(ArrayList<WearStruct> arrayList) {
        String str = i;
        HwLog.w(str, "checkLocalNotFoundBackground() enter");
        this.q.clear();
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = this.mWatchFaceProvider.getBackgroundOptionalConfigList();
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(str, "checkLocalNotFoundBackground() wearStructs is empty");
            a(arrayList, backgroundOptionalConfigList);
            return;
        }
        HwLog.i(str, "checkLocalNotFoundWear =" + GsonHelper.toJson(this.o));
        if (ArrayUtils.isEmpty(this.o)) {
            HwLog.w(str, "checkLocalNotFoundBackground() backgroundPhotos is null or empty");
            this.q.addAll(a(arrayList, ".png", WatchFaceConstant.BIN_SUFFIX));
            return;
        }
        Iterator<WearStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            Iterator<BackgroundOptionalConfig> it2 = backgroundOptionalConfigList.iterator();
            while (true) {
                if (it2.hasNext()) {
                    BackgroundOptionalConfig next2 = it2.next();
                    String resPreview = next2.getResPreview();
                    if (SafeString.substring(resPreview, resPreview.lastIndexOf("/") + 1).contains(next.getWearPreviewName())) {
                        next2.setIndex(next.getCurStyleIndex() + "");
                        break;
                    }
                } else {
                    HwLog.d(i, "checkLocalNotFoundBackground() has not: " + next.getWearPreviewName());
                    if (next.getWearPreviewName() != null) {
                        next.setWearPreviewName(SafeString.replace(next.getWearPreviewName(), ".png", WatchFaceConstant.BIN_SUFFIX));
                    }
                    this.q.add(next);
                }
            }
        }
        a(arrayList, backgroundOptionalConfigList);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getNextFileByBt(int i2) {
        getFileByBt(i2, this.q.size(), 25, this.q.get(i2).getWearPreviewName());
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void saveFileFromDevice(String str, int i2) {
        int i3;
        String str2 = i;
        HwLog.i(str2, "saveFileFromDevice() -- enter.");
        if (TextUtils.isEmpty(str)) {
            HwLog.w(str2, "saveFileFromDevice() sourcePath is empty.");
            return;
        }
        FlavorConfig.safeHwLog(str2, "saveFileFromDevice sourcePath =" + str);
        WatchFaceWearInfo watchFaceWearInfo = this.mHwWatchFaceBtManager.getWatchFaceWearInfo();
        File file = PversionSdUtils.getFile(str);
        if (file == null || !file.exists()) {
            HwLog.i(str2, "saveFileFromDevice() -- file does not exist.");
            return;
        }
        String name = file.getName();
        HwLog.d(str2, "saveFileFromDevice() name :" + name + ",length:" + file.length());
        if (name.contains(".")) {
            name = SafeString.substring(name, 0, name.lastIndexOf("."));
        }
        String str3 = this.mBackgroundDir + name + ".png";
        if (watchFaceWearInfo.getWearStyleType() == 2) {
            WatchFaceBitmapUtil.getInstance().a(str, str3);
            HwLog.d(str2, "saveFileFromDevice() ConvertBinToPng :" + name + ".png");
        } else {
            HwLog.i(str2, "saveFileFromDevice() BackgroundTypeIsPng, copyToBackgroundDir");
            FileHelper.copyFileToParsingDir(str, str3);
        }
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = this.mWatchFaceProvider.getBackgroundOptionalConfigList();
        Iterator<BackgroundOptionalConfig> it = backgroundOptionalConfigList.iterator();
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            BackgroundOptionalConfig next = it.next();
            if (next.getResPreview().contains(str3)) {
                i3 = backgroundOptionalConfigList.indexOf(next);
                break;
            }
        }
        if (i3 != -1) {
            backgroundOptionalConfigList.remove(i3);
        }
        BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
        backgroundOptionalConfig.setIndex(this.q.get(i2).getCurStyleIndex() + "");
        backgroundOptionalConfig.setResPreview(str3);
        backgroundOptionalConfigList.add(backgroundOptionalConfig);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void notifyWatchFaceReady(boolean z) {
        HwLog.i(i, "notifyWatchFaceReady() -- enter.");
        this.mIsSyncWatchFace = false;
        String a2 = a(false);
        if (!z) {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeWearInfo(a2);
        } else {
            notifyH5Ready(a2);
        }
    }

    private void a(ArrayList<WearStruct> arrayList, List<BackgroundOptionalConfig> list) {
        String str = i;
        HwLog.w(str, "deleteRedundantWearData() enter");
        if (ArrayUtils.isEmpty(list)) {
            HwLog.w(str, "deleteRedundantWearData() backgroundOptionalConfigList is empty.");
            return;
        }
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(str, "deleteRedundantWearData() wearStructs is empty.");
            Iterator<BackgroundOptionalConfig> it = list.iterator();
            while (it.hasNext()) {
                FileHelper.a(it.next().getResPreview());
            }
            this.mWatchFaceProvider.setBackgroundOptionalConfigList(null);
            return;
        }
        ArrayList<BackgroundOptionalConfig> arrayList2 = new ArrayList<>();
        for (BackgroundOptionalConfig backgroundOptionalConfig : list) {
            boolean z = false;
            String str2 = "";
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    break;
                }
                WearStruct wearStruct = arrayList.get(i2);
                String resPreview = backgroundOptionalConfig.getResPreview();
                if (SafeString.substring(resPreview, resPreview.lastIndexOf("/") + 1).contains(wearStruct.getWearPreviewName())) {
                    backgroundOptionalConfig.setResPreview(this.mBackgroundDir + wearStruct.getWearPreviewName());
                    backgroundOptionalConfig.setIndex(wearStruct.getCurStyleIndex() + "");
                    backgroundOptionalConfig.setSortTag(i2);
                    arrayList2.add(backgroundOptionalConfig);
                    str2 = resPreview;
                    z = true;
                    break;
                }
                i2++;
                str2 = resPreview;
            }
            String str3 = i;
            HwLog.i(str3, "deleteRedundantWearData() isWatchHasFile: " + z);
            if (!z) {
                HwLog.d(str3, "deleteRedundantWearData() delete resPreview: " + str2 + ", isBgDeleted: " + FileHelper.a(str2));
            }
        }
        Collections.sort(arrayList2, new Comparator<BackgroundOptionalConfig>() { // from class: com.huawei.watchface.manager.WatchFaceWearManager.3
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(BackgroundOptionalConfig backgroundOptionalConfig2, BackgroundOptionalConfig backgroundOptionalConfig3) {
                if (backgroundOptionalConfig2 == null || backgroundOptionalConfig3 == null) {
                    return 0;
                }
                return backgroundOptionalConfig2.getSortTag() - backgroundOptionalConfig3.getSortTag();
            }
        });
        HwLog.w(i, "deleteRedundantWearData() backgroundOptionalConfigListTemp:" + GsonHelper.toJson(arrayList2));
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList2);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceLoadingProgress(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(i, "notifyWatchFaceLoadingProgress() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeWearLoadingProgress(str);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void updateNativeFiles(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        String str = i;
        HwLog.i(str, "updateNativeFiles() enter");
        File file = PversionSdUtils.getFile(this.mBackgroundDir);
        if (file == null || !file.exists()) {
            HwLog.i(str, "updateNativeFiles() -- file does not exist.");
            return;
        }
        File[] listFiles = file.listFiles();
        if (ArrayUtils.isEmpty(listFiles)) {
            HwLog.w(str, "updateNativeFiles() native dir has no imageFiles.");
            return;
        }
        HwLog.i(str, "updateNativeFiles() CheckingBackgroundFiles");
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList();
        if (ArrayUtils.isEmpty(backgroundOptionalConfigList)) {
            HwLog.i(str, "updateNativeFiles() -- backgroundOptionalConfigs is empty");
            backgroundOptionalConfigList = new ArrayList<>(32);
        }
        for (File file2 : listFiles) {
            if (ArrayUtils.a(r, file2.getName())) {
                HwLog.w(i, "updateNativeFiles() -- DEFAULT_WEAR_DIR");
            } else if (!file2.getName().contains("_")) {
                HwLog.d(i, "updateNativeFiles() isDelete: " + FileHelper.a(file2));
            } else {
                Iterator<BackgroundOptionalConfig> it = backgroundOptionalConfigList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        BackgroundOptionalConfig next = it.next();
                        int lastIndexOf = next.getResPreview().lastIndexOf("_");
                        int lastIndexOf2 = file2.getName().lastIndexOf("_");
                        if (lastIndexOf == -1) {
                            HwLog.w(i, "updateNativeFiles() -- backgroundResIndex = -1");
                        } else if (SafeString.substring(next.getResPreview(), 0, lastIndexOf).contains(SafeString.substring(file2.getName(), 0, lastIndexOf2)) && !file2.getName().contains(WatchFaceConstant.BIN_SUFFIX)) {
                            break;
                        }
                    } else {
                        HwLog.d(i, "updateNativeFiles() isDelete: " + FileHelper.a(file2));
                        break;
                    }
                }
            }
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundOptionalConfigList(backgroundOptionalConfigList);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void setTransferTotalCount(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        for (BackgroundOptionalConfig backgroundOptionalConfig : latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList()) {
            Iterator<WearStruct> it = this.n.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (backgroundOptionalConfig.getResPreview().contains(it.next().getWearPreviewName())) {
                        break;
                    }
                } else {
                    HwLog.d(i, "setTransferTotalCount() noContain: " + backgroundOptionalConfig.getResPreview());
                    this.mTransferTotalCount = this.mTransferTotalCount + 1;
                    break;
                }
            }
        }
        HwLog.i(i, "mTransferTotalCount " + this.mTransferTotalCount);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected boolean devicesCallbackFileInfoCheckFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(ContentResource.FILE_NAME);
            int optInt = jSONObject.optInt("fileType");
            if (TextUtils.isEmpty(optString)) {
                HwLog.i(i, "devicesCallbackVideoInfoCheck -- fileName is empty");
                return true;
            }
            if (optInt != 16 && optInt != 17) {
                HwLog.e(i, "devicesCallbackVideoInfoCheck -- fileType: " + optInt);
                return true;
            }
            if (ArrayUtils.isEmpty(this.p)) {
                HwLog.e(i, "devicesCallbackVideoInfoCheck -- mWearList is empty");
                return true;
            }
            return !this.p.contains(SafeString.replace(optString, WatchFaceConstant.WALLPAPER_RES, WatchFaceConstant.PREVIEW_RES));
        } catch (JSONException e) {
            HwLog.e(i, "devicesCallbackVideoInfoCheck -- JSONException" + HwLog.printException((Exception) e));
            a(20001, HwLog.printException((Exception) e));
            return true;
        }
    }

    private ArrayList<WearStruct> d(ArrayList<WearStruct> arrayList) {
        String str;
        String str2 = i;
        HwLog.i(str2, "updateWearStructListByXmlRes() -- enter.");
        ArrayList<WearStruct> arrayList2 = new ArrayList<>();
        File file = PversionSdUtils.getFile(this.mBackgroundDir);
        if (file == null || !file.exists()) {
            HwLog.i(str2, "updateWearStructListByXmlRes() -- file does not exist.");
            return arrayList2;
        }
        try {
            str = file.getCanonicalPath();
        } catch (IOException e) {
            HwLog.e(i, "updateWearStructListByXmlRes() -- exception msg = " + HwLog.printException((Exception) e));
            a(20006, HwLog.printException((Exception) e));
            str = "";
        }
        Iterator<WearStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            WearStruct next = it.next();
            String wearPreviewName = next.getWearPreviewName();
            if (TextUtils.isEmpty(wearPreviewName)) {
                HwLog.i(i, "updateWearStructListByXmlRes() -- wearResName is empty");
            } else if (wearPreviewName.contains(WatchFaceConstant.PRESET_RES)) {
                HwLog.i(i, "updateWearStructListByXmlRes() -- filter out preset resource.");
                arrayList2.add(next);
            } else {
                String str3 = i;
                HwLog.i(str3, "updateWearStructListByXmlRes wearResName =" + wearPreviewName);
                File file2 = PversionSdUtils.getFile(SafeString.replace(str + "/" + SafeString.replace(wearPreviewName, ".png", WatchFaceConstant.XML_SUFFIX), WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES));
                if (file2 == null || !file2.exists()) {
                    HwLog.w(str3, "createWearWatchFace() -- resource file does not exist.");
                    String wearPreviewName2 = next.getWearPreviewName();
                    if (!TextUtils.isEmpty(wearPreviewName2)) {
                        next.setClockResource(SafeString.replace(SafeString.replace(wearPreviewName2, "preview.png", "wallpaper.bin"), "preview.bin", "wallpaper.bin"));
                        next.setWearPreviewName(SafeString.replace(wearPreviewName2, ".png", WatchFaceConstant.BIN_SUFFIX));
                    }
                    arrayList2.add(next);
                } else {
                    a(a(file2), arrayList2, next, wearPreviewName);
                }
            }
        }
        return arrayList2;
    }

    private void a(Properties properties, ArrayList<WearStruct> arrayList, WearStruct wearStruct, String str) {
        String str2 = i;
        HwLog.i(str2, "getResourceInfoModel() -- enter.");
        if (properties == null || properties.isEmpty()) {
            HwLog.w(str2, "setResourceInfoModel() -- property is null or empty.");
            return;
        }
        WearStruct wearStruct2 = new WearStruct();
        wearStruct2.setWearPreviewName(SafeString.replace(wearStruct.getWearPreviewName(), ".png", WatchFaceConstant.BIN_SUFFIX));
        wearStruct2.setWearStyleId(wearStruct.getCurStyleIndex());
        wearStruct2.setCurStyleIndex(wearStruct.getCurStyleIndex());
        Object obj = properties.get("clock_type");
        if (obj instanceof String) {
            wearStruct2.setClockType(Integer.parseInt((String) obj));
        } else {
            wearStruct2.setClockType(1);
        }
        wearStruct2.setClockResource(SafeString.replace(SafeString.replace(str, "preview.png", "wallpaper.bin"), "preview.bin", "wallpaper.bin"));
        Object obj2 = properties.get("clock_color_list");
        if (obj2 instanceof String) {
            wearStruct2.setClockColor((String) obj2);
        } else {
            wearStruct2.setClockColor("");
        }
        arrayList.add(wearStruct2);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void sendWatchFaceInfoToDevice(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        WatchFaceWearInfo watchFaceWearInfo = new WatchFaceWearInfo();
        ArrayList<WearStruct> arrayList = new ArrayList<>(32);
        ArrayList arrayList2 = new ArrayList(32);
        for (BackgroundOptionalConfig backgroundOptionalConfig : latonaWatchFaceThemeAlbumInfo.getDefaultBackgroundOptionalConfigList()) {
            long a2 = dh.a(backgroundOptionalConfig.getIndex(), -1L);
            if (a2 != -1) {
                WearStruct wearStruct = new WearStruct();
                wearStruct.setWearStyleId(a2);
                wearStruct.setWearPreviewName(SafeString.substring(backgroundOptionalConfig.getResPreview(), backgroundOptionalConfig.getResPreview().lastIndexOf("/") + 1));
                wearStruct.setCurStyleIndex(a2);
                arrayList.add(wearStruct);
            } else {
                HwLog.w(i, "sendWatchFaceInfoToDevice() -- index cannot be string " + backgroundOptionalConfig.getIndex());
            }
        }
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList();
        if (!ArrayUtils.isEmpty(backgroundOptionalConfigList)) {
            for (BackgroundOptionalConfig backgroundOptionalConfig2 : backgroundOptionalConfigList) {
                String substring = SafeString.substring(backgroundOptionalConfig2.getResPreview(), backgroundOptionalConfig2.getResPreview().lastIndexOf("/") + 1);
                if (!substring.contains(".png") && !substring.contains(WatchFaceConstant.PREVIEW_RES)) {
                    HwLog.i(i, "sendWatchFaceInfoToDevice() -- filter out xml resource.");
                } else {
                    if (HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceWearInfo().getWearStyleType() == 2) {
                        arrayList2.add(SafeString.replace(substring, ".png", WatchFaceConstant.BIN_SUFFIX));
                    }
                    arrayList2.add(substring);
                    arrayList.add(new WearStruct(dh.a(backgroundOptionalConfig2.getIndex(), -1L), substring));
                }
            }
        }
        ArrayList<WearStruct> d = d(arrayList);
        this.p.clear();
        this.p.addAll(arrayList2);
        watchFaceWearInfo.setWearList(d);
        watchFaceWearInfo.setWearStyleType(latonaWatchFaceThemeAlbumInfo.getWearStyleType());
        watchFaceWearInfo.setCurStyleIndex(latonaWatchFaceThemeAlbumInfo.getCurStyleIndex());
        watchFaceWearInfo.setStyleCount(latonaWatchFaceThemeAlbumInfo.getStyleCount());
        watchFaceWearInfo.setWearTypeCapability(latonaWatchFaceThemeAlbumInfo.getWearTypeCapability());
        watchFaceWearInfo.setClockTypeCapability(latonaWatchFaceThemeAlbumInfo.getClockTypeCapability());
        watchFaceWearInfo.setMaxStyleNum(latonaWatchFaceThemeAlbumInfo.getMaxStyleNum());
        e(this.p);
        a(this.p);
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        this.mCurrentTransferPercent = 0;
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
        this.mHwWatchFaceBtManager.saveWearInfoToDevice(watchFaceWearInfo, new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFaceWearManager$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFaceWearManager.this.a(i2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i2, Object obj) {
        if (i2 == 101) {
            onSaveSuccess(obj);
        } else if (i2 == 114) {
            onSaveToTransferBackgrounds(obj, 2);
        } else {
            onSaveFailed();
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceInstallResult(int i2) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.e(i, "transmitWatchFaceInstallResult() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceWearInstallResult(i2);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected List<Integer> getFileType() {
        if (!Utils.isEmpty(this.m)) {
            return this.m;
        }
        this.m.add(16);
        this.m.add(17);
        return this.m;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    public void onDestroy() {
        HwLog.i(i, "onDestroy() enter.");
        super.onDestroy();
        k();
    }

    private static void k() {
        synchronized (WatchFaceWearManager.class) {
            if (l != null) {
                HwLog.i(i, "destroyInstance() enter.");
                l = null;
            }
        }
    }

    private String a(boolean z) {
        if (z) {
            return WatchFaceProvider.getInstance(this.mContext).transmitWatchFaceInfo();
        }
        return this.mWatchFaceProvider.transmitLatonaWatchFaceInfo();
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceInfo(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.i(i, "transmitWatchFaceInfo() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeWearInfo(str);
        }
    }

    private void l() {
        this.mWatchFaceProvider.combineStyleResourcePath(this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res");
    }

    private void m() {
        this.mWatchFaceProvider.combineDataResourcePath(this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res");
    }

    private Properties a(File file) {
        String str = i;
        HwLog.w(str, "loadProperties() -- enter.");
        Properties properties = new Properties();
        if (!file.exists()) {
            HwLog.w(str, "loadProperties() -- not found wallpaper resource");
            return null;
        }
        if (!file.getName().contains(WatchFaceConstant.XML_SUFFIX)) {
            HwLog.i(str, "loadProperties() -- fileName is not match with resource.");
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                properties.loadFromXML(fileInputStream);
                fileInputStream.close();
                return properties;
            } finally {
            }
        } catch (IOException e) {
            HwLog.e(i, "loadProperties() -- exception msg = " + HwLog.printException((Exception) e));
            a(20005, HwLog.printException((Exception) e));
            return null;
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void parseCustomWatchFaceConfig(ElementsProvider elementsProvider) {
        l();
        m();
        n();
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void clearInvalidData() {
        this.mWatchFaceProvider.setLatonaBackgroundPathList(null);
        this.mWatchFaceProvider.setLatonaBackgroundNameList(null);
        this.mWatchFaceProvider.setDefaultVideoStructs(null);
        this.mWatchFaceProvider.setVideoStructs(null);
    }

    private void n() {
        o();
        p();
    }

    private void o() {
        String str = i;
        HwLog.w(str, "setDefaultBackgroundOptionalConfig() enter");
        String str2 = this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res" + File.separator;
        File file = PversionSdUtils.getFile(str2);
        if (file == null || !file.exists()) {
            HwLog.i(str, "createWearWatchFace() -- resourceFile does not exist.");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            HwLog.w(str, "setDefaultBackgroundOptionalConfig() resourceFiles is null.");
            return;
        }
        for (BackgroundOptionalConfig backgroundOptionalConfig : this.mWatchFaceProvider.getDefaultBackgroundOptionalConfigList()) {
            String resPreview = backgroundOptionalConfig.getResPreview();
            String str3 = str2 + resPreview;
            int length = listFiles.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    File file2 = listFiles[i2];
                    if (file2.isFile() && file2.getName().equals(resPreview)) {
                        backgroundOptionalConfig.setResPreview(str3);
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    private void p() {
        String str = i;
        HwLog.w(str, "setBackgroundOptionalConfig() -- enter");
        this.o.clear();
        File file = PversionSdUtils.getFile(this.mBackgroundDir);
        if (file == null || !file.exists()) {
            HwLog.i(str, "createWearWatchFace() -- file does not exist.");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            HwLog.w(str, "setBackgroundOptionalConfig childFileNames is null or empty");
            this.mWatchFaceProvider.setBackgroundOptionalConfigList(null);
            return;
        }
        ArrayList<BackgroundOptionalConfig> arrayList = new ArrayList<>();
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (file2.isFile() && name.endsWith(".png") && name.contains(WatchFaceConstant.PREVIEW_RES)) {
                try {
                    String substring = SafeString.substring(name, 0, name.lastIndexOf("_"));
                    this.o.add(new WearStruct(dh.a(substring, 0L), name));
                    BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
                    backgroundOptionalConfig.setResPreview(this.mBackgroundDir + name);
                    backgroundOptionalConfig.setIndex(substring);
                    arrayList.add(backgroundOptionalConfig);
                } catch (Exception e) {
                    HwLog.e(i, "setBackgroundOptionalConfig() -- exception msg = " + HwLog.printException(e));
                    a(20007, HwLog.printException(e));
                }
            }
        }
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList);
    }

    private void e(ArrayList<String> arrayList) {
        int watchFaceWidth = LatonaWatchFaceProvider.getInstance(this.mContext).getWatchFaceWidth();
        int watchFaceHeight = LatonaWatchFaceProvider.getInstance(this.mContext).getWatchFaceHeight();
        int e = getInstance(this.mContext).e();
        HwLog.d(i, "createRoundRectPngRes r:" + e);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(WatchFaceConstant.PREVIEW_RES) && next.endsWith(".png")) {
                String str = this.mBackgroundDir + next;
                String replace = SafeString.replace(str, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                try {
                    WatchFaceBitmapUtil.getInstance().a(str, watchFaceWidth, watchFaceHeight, e);
                    WatchFaceBitmapUtil.getInstance().a(replace, watchFaceWidth, watchFaceHeight, e);
                } catch (IOException e2) {
                    HwLog.e(i, "createRoundRectPngRes() -- exception msg = " + HwLog.printException((Exception) e2));
                    a(20008, HwLog.printException((Exception) e2));
                } catch (Exception e3) {
                    HwLog.e(i, "createRoundRectPngRes() -- exception msg = " + HwLog.printException(e3));
                    a(20008, HwLog.printException(e3));
                } catch (OutOfMemoryError e4) {
                    HwLog.e(i, "createRoundRectPngRes:OutOfMemoryError:" + HwLog.printException((Error) e4));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str) {
        AnalyticsGeneralEvent analyticsGeneralEvent = new AnalyticsGeneralEvent();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("errorCode", String.valueOf(i2));
        hashMap.put(BaseEvent.KEY_DESCINFO, er.a(i2));
        hashMap.put(BaseEvent.KEY_CONTENTINFO, str);
        analyticsGeneralEvent.sendEvent("WATCHFACE_WEAR", hashMap);
    }

    public void g() {
        String str = i;
        HwLog.i(str, "reselectionWearTransmit() -- enter.");
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(str, "reselectionWearTransmit() mOperateWatchFaceCallback is null");
        } else {
            HwLog.i(str, "reselectionWearTransmit() -- executing adapter.");
            this.mOperateWatchFaceCallback.reselectionWearTransmit();
        }
    }

    public void a(String str, Uri uri) {
        HwLog.i(i, "setSelectedResourcesFromPicker() -- enter");
        s = str;
        t = uri;
    }

    public String getCustomResourcePath() {
        return this.mHwtOperateDir + this.mWatchFaceName + File.separator + FlavorConfig.SERVICE_WATCH_FACE + File.separator + "resources" + File.separator;
    }
}
