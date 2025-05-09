package com.huawei.watchface.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.dh;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.KaleidoscopeStruct;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.mvp.model.datatype.WatchFaceKaleidoscopeInfo;
import com.huawei.watchface.mvp.model.kaleidoscope.basic.KaleidoscopeDraw;
import com.huawei.watchface.mvp.model.latona.BackgroundOptionalConfig;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceThemeAlbumInfo;
import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class WatchFaceKaleidoscopeManager extends BaseWatchFaceManager {
    private static final String h = "WatchFaceKaleidoscopeManager";
    private static final String[] i = {"res", "hwt", "parsing"};
    private static WatchFaceKaleidoscopeManager j;
    private ArrayList<String> k;
    private ArrayList<KaleidoscopeStruct> l;
    private ArrayList<KaleidoscopeStruct> m;
    private List<Integer> n;
    private final ArrayList<String> o;

    private WatchFaceKaleidoscopeManager(Context context) {
        super(context, WatchFaceConstant.ROOT_PATH_KALEIDOSCOPE, true);
        this.k = new ArrayList<>(32);
        this.l = new ArrayList<>(32);
        this.m = new ArrayList<>(32);
        this.n = new ArrayList(32);
        this.o = new ArrayList<>(20);
    }

    public static WatchFaceKaleidoscopeManager getInstance(Context context) {
        if (j == null) {
            synchronized (WatchFaceKaleidoscopeManager.class) {
                if (j == null) {
                    j = new WatchFaceKaleidoscopeManager(context.getApplicationContext());
                }
            }
        }
        return j;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void parseCustomWatchFaceConfig(ElementsProvider elementsProvider) {
        HwLog.i(h, "parseCustomWatchFaceConfig() enter.");
        h();
        i();
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void clearInvalidData() {
        this.mWatchFaceProvider.setLatonaBackgroundPathList(null);
        this.mWatchFaceProvider.setLatonaBackgroundNameList(null);
        this.mWatchFaceProvider.setDefaultVideoStructs(null);
        this.mWatchFaceProvider.setVideoStructs(null);
    }

    private void h() {
        this.mWatchFaceProvider.combineHandsStyleResourcePath(this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res");
    }

    private void i() {
        j();
        k();
    }

    private void j() {
        String str = this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res" + File.separator;
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null) {
            HwLog.w(h, "setDefaultBackgroundOptionalConfig() resourceFiles is null.");
            return;
        }
        for (BackgroundOptionalConfig backgroundOptionalConfig : this.mWatchFaceProvider.getDefaultBackgroundOptionalConfigList()) {
            String resPreview = backgroundOptionalConfig.getResPreview();
            String str2 = str + resPreview;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            int i2 = 0;
            sb.append(SafeString.substring(resPreview, 0, resPreview.lastIndexOf(".")));
            sb.append(File.separator);
            String sb2 = sb.toString();
            a(KaleidoscopeDraw.a.HONEYCOMB.toString(), str2, sb2, "honeycomb.png");
            a(KaleidoscopeDraw.a.RADIAL.toString(), str2, sb2, "radia.png");
            int length = listFiles.length;
            while (true) {
                if (i2 < length) {
                    File file = listFiles[i2];
                    if (file.isFile() && file.getName().equals(resPreview)) {
                        backgroundOptionalConfig.setResPreview(str2);
                        backgroundOptionalConfig.setHoneycombBgPath(sb2 + "honeycomb.png");
                        backgroundOptionalConfig.setRadiationBgPath(sb2 + "radia.png");
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    private void k() {
        this.k.clear();
        File file = new File(this.mBackgroundDir);
        if (!file.exists()) {
            HwLog.w(h, "setBackgroundOptionalConfig failed, watchFaceSvgPath don't exist");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            HwLog.w(h, "setBackgroundOptionalConfig childFileNames is null or not enough length");
            this.mWatchFaceProvider.setBackgroundOptionalConfigList(null);
            return;
        }
        ArrayList<BackgroundOptionalConfig> arrayList = new ArrayList<>();
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (file2.isFile() && name.endsWith(".png")) {
                this.k.add(name);
                String str = this.mBackgroundDir + name;
                String str2 = this.mBackgroundDir + SafeString.substring(name, 0, name.lastIndexOf(".")) + File.separator;
                a(KaleidoscopeDraw.a.HONEYCOMB.toString(), str, str2, "honeycomb.png");
                a(KaleidoscopeDraw.a.RADIAL.toString(), str, str2, "radia.png");
                BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
                backgroundOptionalConfig.setResPreview(this.mBackgroundDir + name);
                backgroundOptionalConfig.setHoneycombBgPath(str2 + "honeycomb.png");
                backgroundOptionalConfig.setRadiationBgPath(str2 + "radia.png");
                arrayList.add(backgroundOptionalConfig);
            }
        }
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList);
        HwLog.i(h, "setBackgroundOptionalConfig local backgrounds :" + this.k.toString());
    }

    private void a(String str, String str2, String str3, String str4) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.e(h, "generateKaleidoscopeBg() mOperateWatchFaceCallback is null.");
            return;
        }
        Context customWebViewContext = this.mOperateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.e(h, "generateKaleidoscopeBg() context is null.");
            return;
        }
        if (PversionSdUtils.getFile(str3 + str4).exists()) {
            HwLog.i(h, "generateKaleidoscopeBg() desFile exists.");
        } else if (customWebViewContext instanceof Activity) {
            WatchFaceBitmapUtil.saveBitmapToFile(new KaleidoscopeDraw(customWebViewContext, str2, str).a(), Bitmap.CompressFormat.PNG, str3, str4);
        }
    }

    private String a(boolean z) {
        if (z) {
            return WatchFaceProvider.getInstance(this.mContext).transmitWatchFaceInfo();
        }
        return this.mWatchFaceProvider.transmitLatonaWatchFaceInfo();
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void setTransferTotalCount(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        for (BackgroundOptionalConfig backgroundOptionalConfig : latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList()) {
            Iterator<KaleidoscopeStruct> it = this.l.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (backgroundOptionalConfig.getResPreview().contains(it.next().getSubMaterialImageName())) {
                        break;
                    }
                } else {
                    HwLog.iBetaLog(h, "setTransferTotalCount() noContain: " + backgroundOptionalConfig.getResPreview());
                    this.mTransferTotalCount = this.mTransferTotalCount + 1;
                    break;
                }
            }
        }
        HwLog.i(h, "setTransferTotalCount() mTransferTotalCount: " + this.mTransferTotalCount);
    }

    private void l() {
        File[] listFiles;
        File file = PversionSdUtils.getFile(this.mBackgroundDir);
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            HwLog.d(h, "DeleteImageFile :" + file2.delete());
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void notifyWatchFaceReady(boolean z) {
        this.mIsSyncWatchFace = false;
        String a2 = a(false);
        if (!z) {
            this.mOperateWatchFaceCallback.transmitWatchFaceKaleidoscopeInfo(a2);
        } else {
            notifyH5Ready(a2);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceInfo(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.i(h, "transmitWatchFaceInfo() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceKaleidoscopeInfo(str);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceLoadingProgress(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "transmitWatchFaceLoadingProgress() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceKaleidoscopeLoadingProgress(str);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getDeviceInfoByBt() {
        HwLog.i(h, "getDeviceInfoByBt() enter.");
        this.mHwWatchFaceBtManager.getKaleidoscopeWatchFaceInfo(n(), new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFaceKaleidoscopeManager.1
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i2, Object obj) {
                WatchFaceKaleidoscopeManager.this.l.clear();
                HwLog.i(WatchFaceKaleidoscopeManager.h, "getDeviceInfoByBt(): errorCode:" + i2);
                WatchFaceKaleidoscopeManager.this.a(i2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, Object obj) {
        if (i2 == 101) {
            if (obj instanceof WatchFaceKaleidoscopeInfo) {
                a((WatchFaceKaleidoscopeInfo) obj);
                return;
            } else {
                m();
                return;
            }
        }
        b(i2);
    }

    private void b(int i2) {
        HwLog.w(h, "getDeviceInfoByBt() failed :" + i2);
        ArrayList<BackgroundOptionalConfig> arrayList = new ArrayList<>(32);
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList);
        this.mWatchFaceProvider.setDefaultBackgroundOptionalConfigList(arrayList);
        notifyWatchFaceReady(false);
    }

    private void m() {
        HwLog.w(h, "getDeviceInfoByBt() objectData is null");
        this.k.clear();
        l();
        ArrayList<BackgroundOptionalConfig> arrayList = new ArrayList<>(32);
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList);
        this.mWatchFaceProvider.setDefaultBackgroundOptionalConfigList(arrayList);
        notifyWatchFaceReady(false);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getNextFileByBt(int i2) {
        getFileByBt(i2, this.m.size(), 23, this.m.get(i2).getMaterialImageName());
    }

    private void a(WatchFaceKaleidoscopeInfo watchFaceKaleidoscopeInfo) {
        int maxMaterialImages = watchFaceKaleidoscopeInfo.getMaxMaterialImages();
        int currentMaterialImageIndex = watchFaceKaleidoscopeInfo.getCurrentMaterialImageIndex();
        int handsStyleIndex = watchFaceKaleidoscopeInfo.getHandsStyleIndex();
        int kaleidoscopeType = watchFaceKaleidoscopeInfo.getKaleidoscopeType();
        int materialImageType = watchFaceKaleidoscopeInfo.getMaterialImageType();
        ArrayList<KaleidoscopeStruct> a2 = a(watchFaceKaleidoscopeInfo.getMaterialImageList());
        String str = h;
        HwLog.i(str, "didGetDeviceInfoFromBt() maxBackgrounds: " + maxMaterialImages + ", backgroundIndex: " + currentMaterialImageIndex + ", handStyleIndex: " + handsStyleIndex + ", styleIndex: " + kaleidoscopeType + ", backgroundType: " + materialImageType + ", kaleidoscopeStructList: " + a2.toString());
        this.mWatchFaceProvider.setKaleidoscopeInfo(String.valueOf(currentMaterialImageIndex), String.valueOf(handsStyleIndex), String.valueOf(kaleidoscopeType), materialImageType, String.valueOf(maxMaterialImages));
        this.l.addAll(a2);
        b(this.l);
        this.mTransferTotalCount = this.l.size();
        if (this.m.size() > 0) {
            HwLog.i(str, "didGetDeviceInfoFromBt() LocalImagesNotFound");
            getFileByBt(0, this.m.size(), 23, this.m.get(0).getMaterialImageName());
            return;
        }
        HwLog.i(str, "didGetDeviceInfoFromBt() LocalExistsAllBackgroundImages");
        this.k.clear();
        Iterator<KaleidoscopeStruct> it = a2.iterator();
        while (it.hasNext()) {
            this.k.add(it.next().getMaterialImageName());
        }
        notifyWatchFaceReady(false);
    }

    private ArrayList<KaleidoscopeStruct> a(ArrayList<KaleidoscopeStruct> arrayList) {
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(h, "getUnDefaultKaleidoscopeStructs() kaleidoscopeStructs is null or empty.");
            return arrayList;
        }
        List<BackgroundOptionalConfig> defaultBackgroundOptionalConfigList = this.mWatchFaceProvider.getDefaultBackgroundOptionalConfigList();
        ArrayList<KaleidoscopeStruct> arrayList2 = new ArrayList<>(32);
        arrayList2.addAll(arrayList);
        Iterator<KaleidoscopeStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            KaleidoscopeStruct next = it.next();
            for (BackgroundOptionalConfig backgroundOptionalConfig : defaultBackgroundOptionalConfigList) {
                if (SafeString.substring(backgroundOptionalConfig.getResPreview(), backgroundOptionalConfig.getResPreview().lastIndexOf("/") + 1).contains(next.getSubMaterialImageName())) {
                    arrayList2.remove(next);
                }
            }
        }
        return arrayList2;
    }

    private int n() {
        int isSupportBackgroundOption = LatonaWatchFaceProvider.getInstance(this.mContext).isSupportBackgroundOption() | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportHandStyleOption() << 1) | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportStyleOption() << 2);
        HwLog.i(h, "getRequestType() requestType: " + isSupportBackgroundOption);
        return isSupportBackgroundOption;
    }

    private void b(ArrayList<KaleidoscopeStruct> arrayList) {
        this.m.clear();
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = this.mWatchFaceProvider.getBackgroundOptionalConfigList();
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(h, "checkLocalNotFoundBackground() kaleidoscopeStructs is empty");
            a(arrayList, backgroundOptionalConfigList);
            return;
        }
        if (ArrayUtils.isEmpty(this.k)) {
            HwLog.w(h, "checkLocalNotFoundBackground() backgroundPhotos is null or empty");
            this.m.addAll(arrayList);
            return;
        }
        Iterator<KaleidoscopeStruct> it = arrayList.iterator();
        while (it.hasNext()) {
            KaleidoscopeStruct next = it.next();
            Iterator<BackgroundOptionalConfig> it2 = backgroundOptionalConfigList.iterator();
            while (true) {
                if (it2.hasNext()) {
                    BackgroundOptionalConfig next2 = it2.next();
                    String resPreview = next2.getResPreview();
                    if (SafeString.substring(resPreview, resPreview.lastIndexOf("/") + 1).contains(next.getSubMaterialImageName())) {
                        next2.setIndex(next.getMaterialImageIndex() + "");
                        break;
                    }
                } else {
                    HwLog.i(h, "checkLocalNotFoundBackground() has not: " + next.getMaterialImageName());
                    this.m.add(next);
                    break;
                }
            }
        }
        a(arrayList, backgroundOptionalConfigList);
    }

    private void a(ArrayList<KaleidoscopeStruct> arrayList, List<BackgroundOptionalConfig> list) {
        boolean z;
        if (ArrayUtils.isEmpty(list)) {
            HwLog.w(h, "deleteRedundantKaleidoscopeData() backgroundOptionalConfigList is empty.");
            return;
        }
        if (ArrayUtils.isEmpty(arrayList)) {
            HwLog.w(h, "deleteRedundantKaleidoscopeData() videoStructs is empty.");
            Iterator<BackgroundOptionalConfig> it = list.iterator();
            while (it.hasNext()) {
                String resPreview = it.next().getResPreview();
                boolean a2 = FileHelper.a(resPreview);
                boolean a3 = FileHelper.a(SafeString.substring(resPreview, 0, resPreview.lastIndexOf(".")) + File.separator);
                HwLog.d(h, "deleteRedundantKaleidoscopeData() delete resPreview: " + resPreview + ", isBgDeleted: " + a2 + ", isDirDeleted: " + a3);
            }
            this.mWatchFaceProvider.setBackgroundOptionalConfigList(null);
            return;
        }
        ArrayList<BackgroundOptionalConfig> arrayList2 = new ArrayList<>();
        arrayList2.addAll(list);
        for (BackgroundOptionalConfig backgroundOptionalConfig : list) {
            Iterator<KaleidoscopeStruct> it2 = arrayList.iterator();
            String str = "";
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                KaleidoscopeStruct next = it2.next();
                String resPreview2 = backgroundOptionalConfig.getResPreview();
                if (SafeString.substring(resPreview2, resPreview2.lastIndexOf("/") + 1).contains(next.getSubMaterialImageName())) {
                    HwLog.i(h, "deleteRedundantKaleidoscopeData ==" + next.getSubMaterialImageName());
                    arrayList2.get(list.indexOf(backgroundOptionalConfig)).setIndex(next.getMaterialImageIndex() + "");
                    str = resPreview2;
                    z = true;
                    break;
                }
                str = resPreview2;
            }
            String str2 = h;
            HwLog.i(str2, "deleteRedundantKaleidoscopeData() isWatchHasFile: " + z);
            if (!z) {
                HwLog.d(str2, "deleteRedundantKaleidoscopeData() delete resPreview: " + str + ", isBgDeleted: " + FileHelper.a(str) + ", isDirDeleted: " + FileHelper.a(SafeString.substring(str, 0, str.lastIndexOf(".")) + File.separator));
                arrayList2.remove(backgroundOptionalConfig);
            }
        }
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(arrayList2);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected boolean devicesCallbackFileInfoCheckFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(ContentResource.FILE_NAME);
            int optInt = jSONObject.optInt("fileType");
            if (TextUtils.isEmpty(optString)) {
                HwLog.i(h, "devicesCallbackPhotoInfoCheck -- fileName is empty");
                return true;
            }
            if (optInt != 14) {
                HwLog.i(h, "devicesCallbackPhotoInfoCheck -- fileType: " + optInt);
                return true;
            }
            if (ArrayUtils.isEmpty(this.o)) {
                HwLog.i(h, "devicesCallbackPhotoInfoCheck -- mBackgroundList is empty");
                return true;
            }
            return !this.o.contains(optString);
        } catch (JSONException e) {
            HwLog.e(h, "devicesCallbackPhotoInfoCheck -- JSONException" + HwLog.printException((Exception) e));
            return true;
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void saveFileFromDevice(String str, int i2) {
        int i3;
        if (TextUtils.isEmpty(str)) {
            HwLog.w(h, "saveFileFromDevice() sourcePath is empty.");
            return;
        }
        WatchFaceKaleidoscopeInfo watchFaceKaleidoscopeInfo = this.mHwWatchFaceBtManager.getWatchFaceKaleidoscopeInfo();
        File file = new File(str);
        String name = file.getName();
        String str2 = h;
        HwLog.d(str2, "saveFileFromDevice() name :" + name + ",length:" + file.length());
        if (name.contains(".")) {
            name = SafeString.substring(name, 0, name.lastIndexOf("."));
        }
        String str3 = this.mBackgroundDir + name + ".png";
        if (watchFaceKaleidoscopeInfo.getMaterialImageType() == 2) {
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
        HwLog.i(h, "saveFileFromDevice() containIndex: " + i3 + ", pngPath: " + str3);
        String str4 = this.mBackgroundDir + name + File.separator;
        a(KaleidoscopeDraw.a.HONEYCOMB.toString(), str3, str4, "honeycomb.png");
        a(KaleidoscopeDraw.a.RADIAL.toString(), str3, str4, "radia.png");
        BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
        backgroundOptionalConfig.setIndex(this.m.get(i2).getMaterialImageIndex() + "");
        backgroundOptionalConfig.setResPreview(str3);
        backgroundOptionalConfig.setHoneycombBgPath(str4 + "honeycomb.png");
        backgroundOptionalConfig.setRadiationBgPath(str4 + "radia.png");
        backgroundOptionalConfigList.add(backgroundOptionalConfig);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void updateNativeFiles(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        String str = h;
        HwLog.i(str, "updateNativeFiles() enter");
        File[] listFiles = new File(this.mBackgroundDir).listFiles();
        if (ArrayUtils.isEmpty(listFiles)) {
            HwLog.w(str, "updateNativeFiles() native dir has no imageFiles.");
            return;
        }
        HwLog.i(str, "updateNativeFiles() CheckingBackgroundFiles");
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList();
        if (ArrayUtils.isEmpty(backgroundOptionalConfigList)) {
            backgroundOptionalConfigList = new ArrayList<>(32);
        }
        for (File file : listFiles) {
            if (!ArrayUtils.a(i, file.getName())) {
                Iterator<BackgroundOptionalConfig> it = backgroundOptionalConfigList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getResPreview().contains(file.getName())) {
                            break;
                        }
                    } else {
                        HwLog.i(h, "updateNativeFiles() isDelete: " + FileHelper.a(file));
                        break;
                    }
                }
            }
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundOptionalConfigList(backgroundOptionalConfigList);
    }

    private void c(ArrayList<String> arrayList) {
        int bgImageOption = this.mHwWatchFaceBtManager != null ? this.mHwWatchFaceBtManager.getWatchFaceKaleidoscopeInfo().getBgImageOption() : 0;
        HwLog.i(h, "createBinBackground binFileType: " + bgImageOption);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.endsWith(".png")) {
                String str = h;
                HwLog.i(str, "createBinBackground png name :" + next);
                String str2 = this.mBackgroundDir + next;
                String replace = SafeString.replace(next, ".png", WatchFaceConstant.BIN_SUFFIX);
                HwLog.i(str, "createBinBackground bin name :" + replace);
                WatchFaceBitmapUtil.getInstance().a(str2, this.mBackgroundDir + replace, bgImageOption);
            }
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void sendWatchFaceInfoToDevice(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        HwLog.i(h, "sendKaleidoscopeInfoToDevice() enter.");
        WatchFaceKaleidoscopeInfo watchFaceKaleidoscopeInfo = new WatchFaceKaleidoscopeInfo();
        ArrayList arrayList = new ArrayList(32);
        ArrayList<KaleidoscopeStruct> arrayList2 = new ArrayList<>();
        for (BackgroundOptionalConfig backgroundOptionalConfig : latonaWatchFaceThemeAlbumInfo.getDefaultBackgroundOptionalConfigList()) {
            arrayList2.add(new KaleidoscopeStruct(Integer.parseInt(backgroundOptionalConfig.getIndex()), SafeString.substring(backgroundOptionalConfig.getResPreview(), backgroundOptionalConfig.getResPreview().lastIndexOf("/") + 1)));
        }
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = latonaWatchFaceThemeAlbumInfo.getBackgroundOptionalConfigList();
        if (!ArrayUtils.isEmpty(backgroundOptionalConfigList)) {
            for (BackgroundOptionalConfig backgroundOptionalConfig2 : backgroundOptionalConfigList) {
                String substring = SafeString.substring(backgroundOptionalConfig2.getResPreview(), backgroundOptionalConfig2.getResPreview().lastIndexOf("/") + 1);
                if (HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceKaleidoscopeInfo().getMaterialImageType() == 2) {
                    String replace = SafeString.replace(substring, "png", "bin");
                    arrayList.add(replace);
                    arrayList.add(substring);
                    substring = replace;
                } else {
                    arrayList.add(substring);
                }
                arrayList2.add(new KaleidoscopeStruct(Integer.parseInt(backgroundOptionalConfig2.getIndex()), substring));
            }
        }
        this.o.clear();
        this.o.addAll(arrayList);
        watchFaceKaleidoscopeInfo.setMaterialImageList(arrayList2);
        watchFaceKaleidoscopeInfo.setCurrentMaterialImageIndex(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getBackgroundIndex()));
        watchFaceKaleidoscopeInfo.setHandsStyleIndex(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getHandStyleIndex()));
        watchFaceKaleidoscopeInfo.setKaleidoscopeType(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getStyleIndex()));
        HwLog.i(h, "sendKaleidoscopeInfoToDevice() kaleidoscopeStructs: " + arrayList2.toString() + ", currentMaterialImageIndex: " + watchFaceKaleidoscopeInfo.getCurrentMaterialImageIndex() + ", handsStyleIndex: " + watchFaceKaleidoscopeInfo.getHandsStyleIndex() + ", styleIndex: " + watchFaceKaleidoscopeInfo.getKaleidoscopeType());
        c(this.o);
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        this.mCurrentTransferPercent = 0;
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
        this.mHwWatchFaceBtManager.saveKaleidoscopeInfoToDevice(watchFaceKaleidoscopeInfo, new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFaceKaleidoscopeManager$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFaceKaleidoscopeManager.this.b(i2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i2, Object obj) {
        HwLog.i(h, "sendKaleidoscopeInfoToDevice() errorCode: " + i2);
        if (i2 == 101) {
            onSaveSuccess(obj);
        } else if (i2 == 113) {
            onSaveToTransferBackgrounds(obj, 1);
        } else {
            onSaveFailed();
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceInstallResult(int i2) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.e(h, "transmitWatchFaceInstallResult() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceKaleidoscopeInstallResult(i2);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected List<Integer> getFileType() {
        if (!Utils.isEmpty(this.n)) {
            return this.n;
        }
        this.n.add(14);
        return this.n;
    }

    public String d() {
        if (this.mHwWatchFaceBtManager == null) {
            HwLog.w(h, "getKaleidoscopePackageName() mHwWatchBtFaceManager is null");
            return "";
        }
        String kaleidoscopePackageName = this.mHwWatchFaceBtManager.getKaleidoscopePackageName();
        HwLog.i(h, "getKaleidoscopePackageName() kaleidoscopePackageName:" + kaleidoscopePackageName);
        return kaleidoscopePackageName;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    public void onDestroy() {
        HwLog.i(h, "onDestroy() enter.");
        super.onDestroy();
        o();
    }

    private static void o() {
        synchronized (WatchFaceKaleidoscopeManager.class) {
            if (j != null) {
                HwLog.i(h, "destroyInstance() enter.");
                j = null;
            }
        }
    }

    public void choosePicToClip() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "choosePicToClip() mOperateWatchFaceCallback is null");
        } else {
            this.mOperateWatchFaceCallback.chooseToClip(2, false);
        }
    }

    public int e() {
        if (this.mWatchFaceProvider == null) {
            return 0;
        }
        List<BackgroundOptionalConfig> backgroundOptionalConfigList = this.mWatchFaceProvider.getBackgroundOptionalConfigList();
        if (ArrayUtils.isEmpty(backgroundOptionalConfigList)) {
            return ArrayUtils.a(this.mWatchFaceProvider.getDefaultBackgroundOptionalConfigList());
        }
        int i2 = 0;
        for (BackgroundOptionalConfig backgroundOptionalConfig : backgroundOptionalConfigList) {
            if (i2 < dh.a(backgroundOptionalConfig.getIndex(), 0)) {
                i2 = dh.a(backgroundOptionalConfig.getIndex(), 0);
            }
        }
        HwLog.w(h, "getBgMaxIndex() bgMaxIndex: " + i2);
        return i2;
    }

    public void f() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "resetKaleidoscopeCurrentMaxIndex() mOperateWatchFaceCallback is null");
        } else {
            this.mOperateWatchFaceCallback.resetKaleidoscopeCurrentMaxIndex();
        }
    }
}
