package com.huawei.watchface.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.ct;
import com.huawei.watchface.cv;
import com.huawei.watchface.cz;
import com.huawei.watchface.de;
import com.huawei.watchface.dh;
import com.huawei.watchface.dp;
import com.huawei.watchface.dz;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.SmartPickBean;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.mvp.model.datatype.WatchFaceColorFilterBean;
import com.huawei.watchface.mvp.model.datatype.WatchFacePhotoInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFacePhotoNewInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceThemeAlbumInfo;
import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class WatchFacePhotoAlbumManager extends BaseWatchFaceManager {
    private static final String h = "WatchFacePhotoAlbumManager";
    private static WatchFacePhotoAlbumManager i;
    private String j;
    private String k;
    private WatchFacePhotoNewInfo l;
    private ArrayList<String> m;
    private ArrayList<WatchFacePhotoNewInfo.BackgroundBean> n;
    private ArrayList<String> o;
    private ArrayList<String> p;
    private List<Integer> q;
    private final ArrayList<String> r;

    private WatchFacePhotoAlbumManager(Context context) {
        super(context, "/watchfacePhoto/", false);
        this.m = new ArrayList<>(32);
        this.n = new ArrayList<>(32);
        this.o = new ArrayList<>(32);
        this.p = new ArrayList<>(32);
        this.q = new ArrayList(32);
        this.r = new ArrayList<>(20);
        this.j = this.c + "watchFaceImgCache/";
        this.k = this.c + "watchFaceBackgroundCache/";
    }

    public static WatchFacePhotoAlbumManager getInstance(Context context) {
        if (i == null) {
            synchronized (WatchFacePhotoAlbumManager.class) {
                if (i == null) {
                    i = new WatchFacePhotoAlbumManager(context.getApplicationContext());
                }
            }
        }
        return i;
    }

    public boolean d() {
        return this.b;
    }

    private void p() {
        this.mWatchFaceProvider.combineStyleResourcePath(this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res");
    }

    private void q() {
        this.mWatchFaceProvider.combineDataResourcePath(this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res");
    }

    protected void e() {
        r();
        this.m.clear();
        File file = new File(this.mBackgroundDir);
        if (!file.exists()) {
            HwLog.w(h, "setWatchFaceSvgPath failed, watchFaceSvgPath don't exist");
            return;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            HwLog.w(h, "watchFaceSvgPath childFileNames is null or not enough length");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        for (String str : list) {
            if (!this.mIsSyncWatchFace || !str.endsWith(WatchFaceConstant.BIN_SUFFIX)) {
                this.m.add(str);
                arrayList2.add(str);
                arrayList.add(this.mBackgroundDir + str);
            }
        }
        this.mWatchFaceProvider.setLatonaBackgroundPathList(arrayList);
        this.mWatchFaceProvider.setLatonaBackgroundNameList(arrayList2);
    }

    public String f() {
        File file = new File(this.k);
        if (!file.exists()) {
            HwLog.w(h, "getAlbumPreviewPath failed, mBackgroundDir don't exist");
            return "";
        }
        String b = dp.b(cv.i(), "album_background", (String) null);
        if (b == null) {
            return "";
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            HwLog.w(h, "getAlbumPreviewPath childFileNames is null or not enough length");
            return "";
        }
        Arrays.sort(list);
        for (int length = list.length - 1; length >= 0; length--) {
            if (b.contains(list[length])) {
                HwLog.d(h, "getAlbumPreviewPath file name:" + list[length]);
                return this.k + list[length];
            }
        }
        return "";
    }

    protected void g() {
        this.m.clear();
        File file = new File(this.mBackgroundDir);
        if (!file.exists()) {
            HwLog.w(h, "setWatchFaceSvgPath failed, watchFaceSvgPath don't exist");
            return;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            HwLog.w(h, "watchFaceSvgPath childFileNames is null or not enough length");
            return;
        }
        for (String str : list) {
            if (!this.mIsSyncWatchFace || !str.endsWith(WatchFaceConstant.BIN_SUFFIX)) {
                if (new File(this.mBackgroundDir + File.separator + str).isDirectory()) {
                    HwLog.e(h, "setBackgroundPathNew tmp is directory:" + str);
                } else {
                    this.m.add(str);
                }
            }
        }
        if (this.l.getBackgroundList() == null) {
            HwLog.d(h, "watchFacePhotoNewInfo backgroundList is null");
            return;
        }
        Iterator<WatchFacePhotoNewInfo.BackgroundBean> it = this.l.getBackgroundList().iterator();
        while (it.hasNext()) {
            WatchFacePhotoNewInfo.BackgroundBean next = it.next();
            if (TextUtils.isEmpty(next.getBackgroundName())) {
                return;
            }
            next.setBackgroundPath(this.mBackgroundDir + next.getBackgroundName());
        }
    }

    private void r() {
        String str = this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + "res" + File.separator;
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null) {
            HwLog.w(h, "setDefaultBackground resourceFiles is null");
            return;
        }
        for (String str2 : this.mWatchFaceProvider.getBackgroundResourceActive()) {
            for (File file : listFiles) {
                if (file.getName().equals(str2)) {
                    this.mWatchFaceProvider.setDefaultBackground(str2, str + str2);
                }
            }
        }
    }

    private String a(boolean z) {
        if (z) {
            return WatchFaceProvider.getInstance(this.mContext).transmitWatchFaceInfo();
        }
        return this.mWatchFaceProvider.transmitLatonaWatchFaceInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        File[] listFiles;
        File file = new File(this.mBackgroundDir);
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            HwLog.d(h, "DeleteImageFile :" + file2.delete());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WatchFacePhotoInfo watchFacePhotoInfo) {
        int maxBackgroundImages = watchFacePhotoInfo.getMaxBackgroundImages();
        int positionIndex = watchFacePhotoInfo.getPositionIndex();
        int styleIndex = watchFacePhotoInfo.getStyleIndex();
        int dataIndex = watchFacePhotoInfo.getDataIndex();
        int backgroundType = watchFacePhotoInfo.getBackgroundType();
        boolean isSupportIntellectColor = watchFacePhotoInfo.isSupportIntellectColor();
        int portraitMode = watchFacePhotoInfo.getPortraitMode();
        int portPositionIndex = watchFacePhotoInfo.getPortPositionIndex();
        ArrayList<String> backgroundList = watchFacePhotoInfo.getBackgroundList();
        String str = h;
        HwLog.i(str, "didGetDeviceInfoFromBt() WatchFacePhotoInfo: " + GsonHelper.toJson(watchFacePhotoInfo));
        this.mWatchFaceProvider.setLatonaThemeAlbumInfo(String.valueOf(positionIndex), String.valueOf(styleIndex), String.valueOf(dataIndex), backgroundType, String.valueOf(maxBackgroundImages), isSupportIntellectColor, String.valueOf(portraitMode), String.valueOf(portPositionIndex));
        this.o.addAll(backgroundList);
        a(this.o);
        this.mTransferTotalCount = this.p.size();
        this.mWatchFaceProvider.setPortModeList(a(watchFacePhotoInfo.getPortraitModeList()));
        this.mWatchFaceProvider.setPortPositionIndeList(a(watchFacePhotoInfo.getPortPositionIndexList()));
        if (this.p.size() > 0) {
            HwLog.i(str, "didGetDeviceInfoFromBt() LocalImagesNotFound");
            getFileByBt(0, this.p.size(), 1, this.p.get(0));
            return;
        }
        HwLog.i(str, "didGetDeviceInfoFromBt() LocalExistsAllBackgroundImages");
        this.m.clear();
        this.m.addAll(watchFacePhotoInfo.getBackgroundList());
        ArrayList arrayList = new ArrayList(32);
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mBackgroundDir + it.next());
        }
        this.mWatchFaceProvider.setLatonaBackgroundNameList(this.m);
        this.mWatchFaceProvider.setLatonaBackgroundPathList(arrayList);
        notifyWatchFaceReady(false);
    }

    private void a(WatchFacePhotoNewInfo watchFacePhotoNewInfo) {
        this.o.clear();
        ArrayList<WatchFacePhotoNewInfo.BackgroundBean> backgroundList = watchFacePhotoNewInfo.getBackgroundList();
        if (backgroundList == null || backgroundList.isEmpty()) {
            HwLog.d(h, "backgrounds is null or empty");
            return;
        }
        Iterator<WatchFacePhotoNewInfo.BackgroundBean> it = backgroundList.iterator();
        while (it.hasNext()) {
            WatchFacePhotoNewInfo.BackgroundBean next = it.next();
            if (!TextUtils.isEmpty(next.getBackgroundName())) {
                this.o.add(next.getBackgroundName());
            }
        }
        a(this.o);
        this.mTransferTotalCount = this.p.size();
        String str = h;
        HwLog.i(str, "syncDeviceInfoFromBt() mNoContainsPhotos: " + GsonHelper.toJson(this.p));
        if (this.p.size() > 0) {
            HwLog.i(str, "syncDeviceInfoFromBt() LocalImagesNotFound");
            getFileByBt(0, this.p.size(), 1, this.p.get(0));
            return;
        }
        HwLog.i(str, "syncDeviceInfoFromBt() LocalExistsAllBackgroundImages");
        this.n.clear();
        this.n.addAll(backgroundList);
        Iterator<WatchFacePhotoNewInfo.BackgroundBean> it2 = this.n.iterator();
        while (it2.hasNext()) {
            WatchFacePhotoNewInfo.BackgroundBean next2 = it2.next();
            next2.setBackgroundPath(this.mBackgroundDir + next2.getBackgroundName());
        }
        this.l.setBackgroundList(this.n);
        notifyWatchFaceReady(false);
    }

    private ArrayList<String> a(List<Integer> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (list.size() <= 0) {
            return arrayList;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        return arrayList;
    }

    private int t() {
        return LatonaWatchFaceProvider.getInstance(this.mContext).isSupportPositionOption() | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportStyleOption() << 1) | (LatonaWatchFaceProvider.getInstance(this.mContext).isSupportDataOption() << 2);
    }

    private void a(ArrayList<String> arrayList) {
        this.p.clear();
        HwSfpPolicyManagerHelper.setFilePathLabel(this.mBackgroundDir);
        if (arrayList == null) {
            HwLog.w(h, "checkLocalNotFoundBackground() backgrounds is null");
            return;
        }
        ArrayList<String> arrayList2 = this.m;
        if (arrayList2 == null || arrayList2.size() == 0) {
            HwLog.w(h, "checkLocalNotFoundBackground() backgroundPhotos is null or empty");
            this.p.addAll(arrayList);
            return;
        }
        HwLog.i(h, "checkLocalNotFoundBackground backgrounds=" + GsonHelper.toJson(arrayList));
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String replace = SafeString.replace(next, ".png", "_fg.png");
            if (!this.m.contains(next) && !this.m.contains(replace)) {
                this.p.add(next);
            }
        }
        ArrayList arrayList3 = new ArrayList(32);
        Iterator<String> it2 = this.m.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            File file = new File(this.mBackgroundDir + next2);
            if (file.exists()) {
                boolean isTaskExecuting = HwWatchFaceApi.getInstance(this.mContext).getIsTaskExecuting();
                if (!arrayList.contains(SafeString.replace(SafeString.replace(SafeString.replace(SafeString.replace(next2, BaseWatchFaceManager.ORIGINFG_SUFFIX, ""), BaseWatchFaceManager.ORIGINBG_SUFFIX, ""), BaseWatchFaceManager.FG_SUFFIX, ""), BaseWatchFaceManager.BG_SUFFIX, "")) && !isTaskExecuting) {
                    HwLog.i(h, "checkLocalNotFoundBackground() file delete: " + next2);
                    CommonUtils.b(file);
                } else if (next2.endsWith(".png") && !next2.contains(BaseWatchFaceManager.FG_SUFFIX) && !next2.contains(BaseWatchFaceManager.BG_SUFFIX) && !next2.contains(BaseWatchFaceManager.ORIGINBG_SUFFIX) && !next2.contains(BaseWatchFaceManager.ORIGINFG_SUFFIX)) {
                    HwLog.i(h, "checkLocalNotFoundBackground() " + next2);
                    arrayList3.add(this.mBackgroundDir + next2);
                } else {
                    HwLog.i(h, "checkLocalNotFoundBackground() not added: " + next2);
                }
            }
        }
        this.mWatchFaceProvider.setLatonaBackgroundPathList(arrayList3);
    }

    private void a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        String str;
        int i2;
        int i3;
        int h2 = h();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            String str2 = arrayList.get(i4);
            HwLog.i(h, "createBinBackground :name= " + str2);
            if (str2.endsWith(".png")) {
                try {
                    str = arrayList2.get(i4);
                } catch (Exception e) {
                    HwLog.e(h, "createBinBackground Exception:" + HwLog.printException(e));
                    str = "0";
                }
                HwLog.i(h, "createBinBackground mode = " + str);
                WatchFaceSupportInfo watchFaceSupportInfo = this.mHwWatchFaceBtManager.getWatchFaceSupportInfo();
                if (watchFaceSupportInfo != null) {
                    int watchFaceWidth = watchFaceSupportInfo.getWatchFaceWidth();
                    i3 = watchFaceSupportInfo.getWatchFaceHeight();
                    i2 = watchFaceWidth;
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (str.equalsIgnoreCase("0")) {
                    String str3 = this.mBackgroundDir + str2;
                    WatchFaceSupportInfo watchFaceSupportInfo2 = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
                    if (watchFaceSupportInfo2 != null && watchFaceSupportInfo2.isPortraitModeSupported()) {
                        WatchFaceBitmapUtil.getInstance().cropPngToRound(str3);
                    }
                    WatchFaceBitmapUtil.getInstance().a(str3, this.mBackgroundDir + SafeString.replace(str2, ".png", WatchFaceConstant.BIN_SUFFIX), h2, i2, i3);
                } else if (str.equalsIgnoreCase("1") || str.equalsIgnoreCase("2")) {
                    WatchFaceBitmapUtil.getInstance().a(this.mBackgroundDir + SafeString.replace(str2, ".png", "") + "_fg.png", this.mBackgroundDir + SafeString.replace(str2, ".png", "") + "_bg.png", this.mBackgroundDir + SafeString.replace(str2, ".png", WatchFaceConstant.BIN_SUFFIX), h2, i2, i3);
                }
            }
        }
    }

    public int h() {
        if (this.mHwWatchFaceBtManager != null) {
            return this.mHwWatchFaceBtManager.getWatchFacePhotoInfo().getBgImageOption();
        }
        return 0;
    }

    public void i() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "transmitWatchFaceGoBack, mOperateWatchFaceCallback is null");
        } else {
            HwLog.i(h, "enter transmitWatchFaceGoBack");
            this.mOperateWatchFaceCallback.transmitWatchFaceGoBack();
        }
    }

    public String j() {
        if (this.mHwWatchFaceBtManager == null) {
            HwLog.w(h, "mHwWatchBtFaceManager is null");
            return "";
        }
        String photoPackageName = this.mHwWatchFaceBtManager.getPhotoPackageName();
        HwLog.i(h, "getAlbumPackageName:" + photoPackageName);
        return photoPackageName;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    public void onDestroy() {
        HwLog.i(h, "onDestroy() enter.");
        super.onDestroy();
        u();
    }

    private static void u() {
        synchronized (WatchFacePhotoAlbumManager.class) {
            if (i != null) {
                HwLog.i(h, "destroyInstance() enter.");
                i = null;
            }
        }
    }

    public void k() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "choosePicToClip() failed mOperateWatchFaceCallback is null");
        } else {
            this.mOperateWatchFaceCallback.chooseToClip(0, false);
        }
    }

    public void l() {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "choosePicToH5Clip() failed mOperateWatchFaceCallback is null");
        } else {
            this.mOperateWatchFaceCallback.chooseToClip(0, true);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "obtainWidgetColor failed mOperateWatchFaceCallback is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.w(h, "obtainWidgetColor failed watchFacePath is null");
            return;
        }
        try {
            int parseInt = Integer.parseInt(str2);
            int parseInt2 = Integer.parseInt(str3);
            int parseInt3 = Integer.parseInt(str4);
            int parseInt4 = Integer.parseInt(str5);
            Bitmap decodeFile = BitmapFactory.decodeFile(CommonUtils.filterFilePath(str));
            if (decodeFile == null) {
                HwLog.w(h, "obtainWidgetColor failed bitmap is null");
            } else {
                this.mOperateWatchFaceCallback.transmitWatchFaceTimeWidgetColor(ct.a(decodeFile, new Rect(parseInt, parseInt2, parseInt3, parseInt4)));
            }
        } catch (NumberFormatException unused) {
            HwLog.e(h, "obtainWidgetColor NumberFormatException");
        } catch (Exception unused2) {
            HwLog.e(h, "obtainWidgetColor exception");
        } catch (OutOfMemoryError e) {
            HwLog.e(h, "obtainWidgetColor OutOfMemoryError " + HwLog.printException((Error) e));
        }
    }

    public String m() {
        return this.j;
    }

    public String n() {
        return this.k;
    }

    public String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            HwLog.w(h, "getColorPickPicName -- the params is empty");
            return "";
        }
        String i2 = i(str);
        String h2 = h(str2);
        String g = g(str3);
        if (TextUtils.isEmpty(i2) || TextUtils.isEmpty(h2) || TextUtils.isEmpty(g)) {
            return "";
        }
        return i2 + "_" + h2 + "_" + g;
    }

    private String g(String str) {
        if (str.contains("/")) {
            String[] split = str.split("/");
            if (!ArrayUtils.isEmpty(split)) {
                return split[split.length - 1];
            }
        }
        return "";
    }

    private String h(String str) {
        if (str.contains(",")) {
            String[] split = str.split(",");
            if (!ArrayUtils.isEmpty(split)) {
                StringBuilder sb = new StringBuilder();
                for (String str2 : split) {
                    sb.append(str2);
                }
                return sb.toString();
            }
        }
        return "";
    }

    private String i(String str) {
        String str2;
        if (str.contains("/")) {
            String[] split = str.split("/");
            if (!ArrayUtils.isEmpty(split) && (str2 = split[split.length - 1]) != null && str2.contains(".")) {
                String[] split2 = str2.split("[.]");
                if (!ArrayUtils.isEmpty(split2)) {
                    return split2[0];
                }
            }
        }
        return "";
    }

    public String a(String str, int i2, String str2, int i3) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(h, "getColorPickPicName -- the params is empty");
            return "";
        }
        if (i3 == 2) {
            if (TextUtils.isEmpty(str2)) {
                return "";
            }
            String[] split = str2.split("[.]");
            if (ArrayUtils.isEmpty(split)) {
                return "";
            }
            return split[0] + "_" + Math.abs(i2) + ".png";
        }
        String i4 = i(str);
        if (TextUtils.isEmpty(i4)) {
            return "";
        }
        return i4 + "_" + Math.abs(i2) + ".png";
    }

    private Rect j(String str) {
        Rect rect = new Rect();
        if (TextUtils.isEmpty(str)) {
            HwLog.w(h, "getRect -- positionRect is empty");
            return rect;
        }
        if (!str.contains(",")) {
            HwLog.w(h, "getRect -- positionRect not contains ,");
            return rect;
        }
        String[] split = str.split(",");
        if (ArrayUtils.isEmpty(split) || split.length != 4) {
            HwLog.w(h, "getRect -- positionRectArray length not 4");
            return rect;
        }
        rect.left = dh.a(split[0], 0);
        rect.top = dh.a(split[1], 0);
        rect.right = dh.a(split[2], 0);
        rect.bottom = dh.a(split[3], 0);
        return rect;
    }

    public String[] a(String str, String str2, String str3, String str4, int i2) {
        String[] strArr = {"", ""};
        if (!TextUtils.isEmpty(str4)) {
            String[] split = str4.split("/");
            if (split == null || split.length < 1) {
                HwLog.d(h, "getSmartPicture is not right");
                return strArr;
            }
            str3 = b(str4, split[split.length - 1], this.j, true);
        } else if (!TextUtils.isEmpty(str3) && str3.contains("||")) {
            String[] split2 = str3.split("\\|\\|");
            if (split2 == null || split2.length < 2) {
                HwLog.d(h, "needSetColorPicPath is not right");
                return strArr;
            }
            str3 = a(split2[1], split2[0], this.j, true);
        }
        String a2 = a(str, str2, str3);
        if (TextUtils.isEmpty(a2)) {
            HwLog.w(h, "getSmartPicture -- newResPreviewPicName is empty");
            return strArr;
        }
        String str5 = this.j + a2;
        File file = PversionSdUtils.getFile(str5);
        if (file != null && file.exists()) {
            HwLog.i(h, "getSmartPicture -- Existed image.Back to H5");
            strArr[0] = str5;
            return strArr;
        }
        if (i2 == -1) {
            Bitmap safeDecodeFile = WatchFaceBitmapUtil.getSafeDecodeFile(str);
            if (safeDecodeFile == null) {
                HwLog.w(h, "getSmartPicture -- watchFaceBgBitmap is null");
                return strArr;
            }
            i2 = ct.a(safeDecodeFile, j(str2));
        }
        strArr[1] = "#" + Integer.toHexString(i2);
        Bitmap safeDecodeFile2 = WatchFaceBitmapUtil.getSafeDecodeFile(str3);
        if (safeDecodeFile2 == null) {
            HwLog.w(h, "getSmartPicture -- resPreviewBitmap is null");
            return strArr;
        }
        Bitmap a3 = WatchFaceBitmapUtil.a(safeDecodeFile2, i2);
        if (a3 == null) {
            HwLog.w(h, "getSmartPicture -- newResPreviewBitmap is null");
            return strArr;
        }
        WatchFaceBitmapUtil.saveBitmapToFile(a3, Bitmap.CompressFormat.PNG, this.j, a2);
        safeDecodeFile2.recycle();
        a3.recycle();
        File file2 = PversionSdUtils.getFile(str5);
        if (file2 != null && file2.exists()) {
            HwLog.i(h, "getSmartPicture -- New pic save success.Back to H5");
            strArr[0] = str5;
            return strArr;
        }
        HwLog.w(h, "getSmartPicture -- new pic save failed");
        return strArr;
    }

    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(h, "getSmartPictureList -- resJsonStr is empty");
            return "";
        }
        List<SmartPickBean> list = (List) GsonHelper.fromJsonToArray(str, new TypeToken<List<SmartPickBean>>() { // from class: com.huawei.watchface.manager.WatchFacePhotoAlbumManager.1
        }.getType());
        if (ArrayUtils.isEmpty(list)) {
            HwLog.w(h, "getSmartPictureList -- smartPickBeanList is empty");
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (SmartPickBean smartPickBean : list) {
            SmartPickBean smartPickBean2 = new SmartPickBean();
            smartPickBean2.setWatchFaceBgPath(smartPickBean.getWatchFaceBgPath());
            smartPickBean2.setPositionRect(smartPickBean.getPositionRect());
            String[] a2 = a(smartPickBean.getWatchFaceBgPath(), smartPickBean.getPositionRect(), smartPickBean.getResPreviewPath(), smartPickBean.getSourceUrl(), -1);
            smartPickBean2.setResPreviewPath(a2[0]);
            smartPickBean2.setColorString(a2[1]);
            if (!TextUtils.isEmpty(smartPickBean.getResFeaturePath())) {
                smartPickBean2.setResFeaturePath(a(smartPickBean.getWatchFaceBgPath(), smartPickBean.getPositionRect(), smartPickBean.getResFeaturePath(), smartPickBean.getSourceUrl(), TextUtils.isEmpty(a2[1]) ? -1 : Color.parseColor(a2[1]))[0]);
            }
            arrayList.add(smartPickBean2);
        }
        HwLog.i(h, "getSmartPictureList -- data deal success");
        return GsonHelper.toJson(arrayList);
    }

    public String c(String str) {
        String a2;
        String str2 = h;
        HwLog.d(str2, "getColorPictures enter");
        try {
            WatchFaceColorFilterBean watchFaceColorFilterBean = (WatchFaceColorFilterBean) GsonHelper.fromJson(str, WatchFaceColorFilterBean.class);
            if (watchFaceColorFilterBean == null) {
                HwLog.d(str2, "colorFilterBean is null");
                return str;
            }
            int type = watchFaceColorFilterBean.getType();
            HwLog.d(str2, "colorFilterBean type:" + type);
            List<WatchFaceColorFilterBean.WatchFaceBackgroundBean> watchFaceBgArr = watchFaceColorFilterBean.getWatchFaceBgArr();
            if (watchFaceBgArr == null) {
                HwLog.d(str2, "watchFaceBgArr is null");
                return str;
            }
            for (int i2 = 0; i2 < watchFaceBgArr.size(); i2++) {
                WatchFaceColorFilterBean.WatchFaceBackgroundBean watchFaceBackgroundBean = watchFaceBgArr.get(i2);
                String str3 = "";
                if (type == 2) {
                    if (!TextUtils.isEmpty(watchFaceBackgroundBean.getSourceUrl())) {
                        a2 = b(watchFaceBackgroundBean.getSourceUrl(), watchFaceBackgroundBean.getFileName(), this.j, true);
                    } else {
                        a2 = a(watchFaceBackgroundBean.getBase64(), watchFaceBackgroundBean.getFileName(), this.j, true);
                    }
                    if (!TextUtils.isEmpty(a2)) {
                        String a3 = a(a2, watchFaceBackgroundBean.getColorStr(), watchFaceBackgroundBean.getFileName(), type);
                        watchFaceBackgroundBean.setBase64("");
                        str3 = a3;
                    }
                } else if (type == 1) {
                    str3 = a(watchFaceBackgroundBean.getFilePath(), watchFaceBackgroundBean.getColorStr(), watchFaceBackgroundBean.getFileName(), type);
                }
                watchFaceBackgroundBean.setResultPath(str3);
            }
            HwLog.d(h, "getColorPictures return");
            return GsonHelper.toJson(watchFaceColorFilterBean);
        } catch (Exception e) {
            HwLog.d(h, "getColorPictures Exception:" + HwLog.printException(e));
            return str;
        }
    }

    private String b(String str, final String str2, final String str3, boolean z) {
        String str4 = h;
        HwLog.d(str4, "getImagePathFromUrl fileName:" + str2);
        final long currentTimeMillis = System.currentTimeMillis();
        final String[] strArr = new String[1];
        if (new File(str3 + str2).exists() && z) {
            HwLog.d(str4, "getTimePath Exists.");
            return str3 + str2;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            cz.a(this.mContext, str, new cz.a() { // from class: com.huawei.watchface.manager.WatchFacePhotoAlbumManager$$ExternalSyntheticLambda0
                @Override // com.huawei.watchface.cz.a
                public final void onResourceReady(Bitmap bitmap) {
                    WatchFacePhotoAlbumManager.a(str3, str2, strArr, countDownLatch, currentTimeMillis, bitmap);
                }
            });
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                HwLog.w(str4, "getImagePathFromUrl time out!");
                strArr[0] = "";
            }
        } catch (InterruptedException e) {
            HwLog.e(h, "getImagePathFromUrl InterruptedException:" + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e(h, "getImagePathFromUrl Exception:" + HwLog.printException(e2));
        }
        return strArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, String str2, String[] strArr, CountDownLatch countDownLatch, long j, Bitmap bitmap) {
        WatchFaceBitmapUtil.saveBitmapToFile(bitmap, Bitmap.CompressFormat.PNG, str, str2);
        strArr[0] = str + str2;
        countDownLatch.countDown();
        HwLog.d(h, "get url time:" + (System.currentTimeMillis() - j));
    }

    public void d(String str) {
        File[] listFiles;
        File file = new File(this.k);
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!TextUtils.equals(file2.getName(), str)) {
                HwLog.d(h, "DeleteWatchFaceImgCacheFile :" + file2.getName() + ",delete:" + file2.delete());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d4, code lost:
    
        if (r8 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b7, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d7, code lost:
    
        com.huawei.watchface.utils.HwLog.d(com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h, "getTimePath close IOException");
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b5, code lost:
    
        if (r8 == null) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            r4 = this;
            java.lang.String r0 = "getTimePath close IOException"
            java.lang.String r1 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getTimePath fileName:"
            r2.<init>(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            com.huawei.watchface.utils.HwLog.d(r1, r2)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L46
            if (r8 == 0) goto L46
            java.lang.String r5 = "getTimePath Exists."
            com.huawei.watchface.utils.HwLog.d(r1, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            return r5
        L46:
            r8 = 0
            java.io.File r1 = com.huawei.watchface.mvp.model.crypt.PversionSdUtils.getFile(r7)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            if (r2 != 0) goto L54
            com.huawei.watchface.utils.CommonUtils.a(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
        L54:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            r1.<init>()     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            r1.append(r7)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            r1.append(r6)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            java.io.File r1 = com.huawei.watchface.mvp.model.crypt.PversionSdUtils.getFile(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            byte[] r5 = com.huawei.watchface.aj.a(r5)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            java.io.FileOutputStream r2 = com.huawei.watchface.mvp.model.crypt.PversionSdUtils.a(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c java.io.IOException -> Lbb
            r2.write(r5)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            r5.<init>()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            r5.append(r7)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            r5.append(r6)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            java.lang.String r8 = r5.toString()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            com.huawei.watchface.utils.HwSfpPolicyManagerHelper.setDefaultCeLabel(r1)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92 java.io.IOException -> L96
            if (r2 == 0) goto Ldd
            r2.close()     // Catch: java.io.IOException -> L8a
            goto Ldd
        L8a:
            java.lang.String r5 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h
            com.huawei.watchface.utils.HwLog.d(r5, r0)
            goto Ldd
        L90:
            r5 = move-exception
            goto Ldf
        L92:
            r5 = move-exception
            r6 = r8
            r8 = r2
            goto L9e
        L96:
            r5 = move-exception
            r6 = r8
            r8 = r2
            goto Lbd
        L9a:
            r5 = move-exception
            goto Lde
        L9c:
            r5 = move-exception
            r6 = r8
        L9e:
            java.lang.String r7 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h     // Catch: java.lang.Throwable -> L9a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9a
            java.lang.String r2 = "getTimePath Exception:"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L9a
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)     // Catch: java.lang.Throwable -> L9a
            r1.append(r5)     // Catch: java.lang.Throwable -> L9a
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L9a
            com.huawei.watchface.utils.HwLog.d(r7, r5)     // Catch: java.lang.Throwable -> L9a
            if (r8 == 0) goto Ldc
        Lb7:
            r8.close()     // Catch: java.io.IOException -> Ld7
            goto Ldc
        Lbb:
            r5 = move-exception
            r6 = r8
        Lbd:
            java.lang.String r7 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h     // Catch: java.lang.Throwable -> L9a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9a
            java.lang.String r2 = "getTimePath IOException:"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L9a
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)     // Catch: java.lang.Throwable -> L9a
            r1.append(r5)     // Catch: java.lang.Throwable -> L9a
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L9a
            com.huawei.watchface.utils.HwLog.d(r7, r5)     // Catch: java.lang.Throwable -> L9a
            if (r8 == 0) goto Ldc
            goto Lb7
        Ld7:
            java.lang.String r5 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h
            com.huawei.watchface.utils.HwLog.d(r5, r0)
        Ldc:
            r8 = r6
        Ldd:
            return r8
        Lde:
            r2 = r8
        Ldf:
            if (r2 == 0) goto Lea
            r2.close()     // Catch: java.io.IOException -> Le5
            goto Lea
        Le5:
            java.lang.String r6 = com.huawei.watchface.manager.WatchFacePhotoAlbumManager.h
            com.huawei.watchface.utils.HwLog.d(r6, r0)
        Lea:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.manager.WatchFacePhotoAlbumManager.a(java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    public String a(String str, String str2, String str3, int i2) {
        Bitmap bitmap;
        Bitmap bitmap2;
        String str4 = h;
        HwLog.d(str4, "getColorPicture enter ,colorStr:" + str2);
        try {
            int parseColor = Color.parseColor(str2);
            String a2 = a(str, parseColor, str3, i2);
            if (TextUtils.isEmpty(a2)) {
                HwLog.w(str4, "getColorPicture -- newColorPreviewPicName is empty");
                return str;
            }
            String str5 = this.j + a2;
            HwLog.d(str4, "newColorPreviewPicName:" + a2);
            File file = PversionSdUtils.getFile(str5);
            if (file != null && file.exists()) {
                HwLog.i(str4, "getColorPicture -- Existed image.Back to H5");
                return str5;
            }
            if (i2 == 1) {
                bitmap = WatchFaceBitmapUtil.getSafeDecodeFile(str);
                bitmap2 = WatchFaceBitmapUtil.b(bitmap, parseColor);
            } else if (i2 == 2) {
                bitmap = WatchFaceBitmapUtil.getSafeDecodeFile(str);
                bitmap2 = WatchFaceBitmapUtil.a(bitmap, parseColor);
            } else {
                bitmap = null;
                bitmap2 = null;
            }
            if (bitmap2 == null) {
                HwLog.w(str4, "getColorPicture -- newColorPreviewBitmap is null");
                return str;
            }
            WatchFaceBitmapUtil.saveBitmapToFile(bitmap2, Bitmap.CompressFormat.PNG, this.j, a2);
            if (bitmap != null) {
                bitmap.recycle();
            }
            bitmap2.recycle();
            File file2 = PversionSdUtils.getFile(str5);
            if (file2 != null && file2.exists()) {
                HwLog.i(str4, "getColorPicture -- New pic save success.Back to H5");
                return str5;
            }
            HwLog.w(str4, "getColorPicture -- new pic save failed");
            return str;
        } catch (Exception e) {
            HwLog.d(h, "color str is error:" + HwLog.printException(e));
            return str;
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void preTransferFinish() {
        this.mWatchFaceProvider.setLatonaBackgroundNameList(this.o);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getDeviceInfoByBt() {
        HwLog.i(h, "getDeviceInfoByBt() enter.");
        this.mHwWatchFaceBtManager.getWatchFacePhotoInfo(t(), new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFacePhotoAlbumManager.2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i2, Object obj) {
                WatchFacePhotoAlbumManager.this.o.clear();
                HwLog.i(WatchFacePhotoAlbumManager.h, "getDeviceInfoByBt(): errorCode:" + i2);
                if (i2 != 101) {
                    HwLog.w(WatchFacePhotoAlbumManager.h, "getDeviceInfoByBt() failed :" + i2);
                    ArrayList<String> arrayList = new ArrayList<>(32);
                    WatchFacePhotoAlbumManager.this.mWatchFaceProvider.setLatonaBackgroundNameList(arrayList);
                    WatchFacePhotoAlbumManager.this.mWatchFaceProvider.setLatonaBackgroundPathList(arrayList);
                    WatchFacePhotoAlbumManager.this.notifyWatchFaceReady(false);
                    return;
                }
                if (obj instanceof WatchFacePhotoInfo) {
                    WatchFacePhotoAlbumManager.this.a((WatchFacePhotoInfo) obj);
                    return;
                }
                HwLog.w(WatchFacePhotoAlbumManager.h, "getDeviceInfoByBt() objectData is null");
                WatchFacePhotoAlbumManager.this.m.clear();
                WatchFacePhotoAlbumManager.this.s();
                WatchFacePhotoAlbumManager.this.mWatchFaceProvider.setLatonaBackgroundNameList(WatchFacePhotoAlbumManager.this.m);
                WatchFacePhotoAlbumManager.this.mWatchFaceProvider.setLatonaBackgroundPathList(new ArrayList(32));
                WatchFacePhotoAlbumManager.this.notifyWatchFaceReady(false);
            }
        });
    }

    protected void e(String str) {
        String str2 = h;
        HwLog.d(str2, "syncDeviceInfoByBt");
        try {
            WatchFacePhotoNewInfo watchFacePhotoNewInfo = (WatchFacePhotoNewInfo) GsonHelper.fromJson(str, WatchFacePhotoNewInfo.class);
            this.l = watchFacePhotoNewInfo;
            if (watchFacePhotoNewInfo == null) {
                HwLog.d(str2, "watchFacePhotoNewInfo is null");
                return;
            }
            if (this.mHwWatchFaceBtManager != null && this.mHwWatchFaceBtManager.getWatchFacePhotoInfo() != null) {
                this.mHwWatchFaceBtManager.getWatchFacePhotoInfo().setBackgroundType(this.l.getBackgroundImageType());
                this.mHwWatchFaceBtManager.getWatchFacePhotoInfo().setBgImageOption(this.l.getBackgroundImageOption());
            }
            g();
            if (this.mIsSyncWatchFace) {
                return;
            }
            this.mTransferCompleteCount = 0;
            this.mCurrentTransferPercent = 0;
            a(this.l);
        } catch (Exception e) {
            HwLog.d(h, "syncDeviceInfoByBt Exception" + HwLog.printException(e));
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void getNextFileByBt(int i2) {
        getFileByBt(i2, this.p.size(), 1, this.p.get(i2));
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void saveFileFromDevice(String str, int i2) {
        if (str == null) {
            HwLog.w(h, "saveFileFromDevice() Source Path is null");
            return;
        }
        WatchFacePhotoInfo watchFacePhotoInfo = this.mHwWatchFaceBtManager.getWatchFacePhotoInfo();
        String str2 = h;
        HwLog.i(str2, "saveFileFromDevice() WatchFacePhotoInfo: " + GsonHelper.toJson(watchFacePhotoInfo));
        File file = new File(str);
        String name = file.getName();
        HwLog.d(str2, "saveFileFromDevice() name :" + name + ",length:" + file.length());
        boolean z = false;
        if (name.contains(".")) {
            name = SafeString.substring(name, 0, name.lastIndexOf("."));
        }
        String str3 = this.mBackgroundDir + name + ".png";
        int indexOf = watchFacePhotoInfo.getBackgroundList().indexOf(name + ".png");
        if (indexOf != -1 && watchFacePhotoInfo.getPortraitModeList().size() > 0) {
            try {
                if (watchFacePhotoInfo.getPortraitModeList().get(indexOf).intValue() != 0) {
                    z = true;
                }
            } catch (Exception e) {
                HwLog.i(h, "getBackgroundList() portraitMode error: " + HwLog.printException(e));
            }
        }
        String str4 = h;
        HwLog.i(str4, "getBackgroundList() portraitMode isPortrait: " + z);
        if (watchFacePhotoInfo.getBackgroundType() == 2) {
            WatchFaceBitmapUtil.getInstance().a(str, str3);
        } else {
            HwLog.i(str4, "Background Type Is Png, copyToBackgroundDir");
            FileHelper.copyFileToParsingDir(str, str3);
        }
        List<String> latonaBackgroundPathList = this.mWatchFaceProvider.getLatonaBackgroundPathList();
        ArrayList arrayList = new ArrayList(32);
        if (!ArrayUtils.isEmpty(latonaBackgroundPathList)) {
            arrayList.addAll(latonaBackgroundPathList);
        }
        if (ArrayUtils.isEmpty(latonaBackgroundPathList) || !latonaBackgroundPathList.contains(str3)) {
            arrayList.add(str3);
            this.mWatchFaceProvider.setLatonaBackgroundPathList(arrayList);
        }
        WatchFacePhotoNewInfo watchFacePhotoNewInfo = this.l;
        if (watchFacePhotoNewInfo == null || watchFacePhotoNewInfo.getBackgroundList() == null || this.l.getBackgroundList().isEmpty()) {
            return;
        }
        Iterator<WatchFacePhotoNewInfo.BackgroundBean> it = this.l.getBackgroundList().iterator();
        while (it.hasNext()) {
            WatchFacePhotoNewInfo.BackgroundBean next = it.next();
            if (TextUtils.isEmpty(next.getBackgroundName())) {
                return;
            }
            if (next.getBackgroundName().contains(name)) {
                next.setBackgroundPath(str3);
            }
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void notifyWatchFaceReady(boolean z) {
        this.mIsSyncWatchFace = false;
        v();
        if (this.b && this.mOperateWatchFaceCallback != null) {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeAlbumInfo(new Gson().toJson(this.l));
            return;
        }
        String a2 = a(false);
        if (!z && this.mOperateWatchFaceCallback != null) {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeAlbumInfo(a2);
        } else {
            notifyH5Ready(a2);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceLoadingProgress(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.w(h, "notifyWatchFaceLoadingProgress() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeAlbumLoadingProgress(str);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void updateNativeFiles(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        String str = h;
        HwLog.i(str, "updateNativeFiles() enter");
        File[] listFiles = new File(this.mBackgroundDir).listFiles();
        if (listFiles == null) {
            HwLog.w(str, "updateNativeFiles() BackgroundsDirContainsNoFile");
            return;
        }
        HwLog.i(str, "updateNativeFiles() CheckingBackgroundFiles");
        ArrayList<String> backgroundList = latonaWatchFaceThemeAlbumInfo.getBackgroundList();
        if (backgroundList == null) {
            backgroundList = new ArrayList<>(32);
        }
        for (File file : listFiles) {
            String name = file.getName();
            boolean contains = backgroundList.contains(name);
            boolean contains2 = backgroundList.contains(SafeString.replace(SafeString.replace(SafeString.replace(SafeString.replace(name, BaseWatchFaceManager.ORIGINBG_SUFFIX, ""), BaseWatchFaceManager.ORIGINFG_SUFFIX, ""), BaseWatchFaceManager.FG_SUFFIX, ""), BaseWatchFaceManager.BG_SUFFIX, ""));
            if (!contains && !contains2) {
                HwLog.d(h, "updateNativeFiles() DeleteBackgroundFile :" + name + ",delete :" + file.delete());
            }
        }
        latonaWatchFaceThemeAlbumInfo.setBackgroundList(backgroundList);
        HwLog.i(h, "updateNativeFiles() CheckBackgroundFilesFinish");
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void setTransferTotalCount(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        Iterator<String> it = latonaWatchFaceThemeAlbumInfo.getBackgroundList().iterator();
        while (it.hasNext()) {
            if (!this.o.contains(it.next())) {
                this.mTransferTotalCount++;
            }
        }
        HwLog.i(h, "setTransferTotalCount() BeforeSave, willTransfer TotalBgCount =" + this.mTransferTotalCount);
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected boolean devicesCallbackFileInfoCheckFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(ContentResource.FILE_NAME);
            int optInt = jSONObject.optInt("fileType");
            if (TextUtils.isEmpty(optString)) {
                HwLog.i(h, "devicesCallbackFileInfoCheckFail -- fileName is empty");
                return true;
            }
            if (optInt != 3) {
                HwLog.i(h, "devicesCallbackFileInfoCheckFail -- fileType: " + optInt);
                return true;
            }
            if (ArrayUtils.isEmpty(this.r)) {
                HwLog.i(h, "devicesCallbackFileInfoCheckFail -- mBackgroundList is empty");
                return true;
            }
            return !this.r.contains(optString);
        } catch (JSONException e) {
            HwLog.e(h, "devicesCallbackFileInfoCheckFail -- JSONException" + HwLog.printException((Exception) e));
            return true;
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void sendWatchFaceInfoToDevice(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        HwLog.i(h, "sendWatchFaceInfoToDevice() enter.");
        WatchFacePhotoInfo watchFacePhotoInfo = new WatchFacePhotoInfo();
        ArrayList<String> arrayList = new ArrayList<>(32);
        ArrayList<String> backgroundList = latonaWatchFaceThemeAlbumInfo.getBackgroundList();
        ArrayList<String> arrayList2 = latonaWatchFaceThemeAlbumInfo.getmPortraitModeList();
        if (backgroundList.size() > 0) {
            arrayList.addAll(backgroundList);
        }
        this.r.clear();
        this.r.addAll(arrayList);
        watchFacePhotoInfo.setBackgroundList(arrayList);
        watchFacePhotoInfo.setStyleIndex(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getStyleIndex()));
        watchFacePhotoInfo.setDataIndex(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getDataIndex()));
        if (latonaWatchFaceThemeAlbumInfo.getPositionIndex() != null) {
            watchFacePhotoInfo.setPositionIndex(Integer.parseInt(latonaWatchFaceThemeAlbumInfo.getPositionIndex()));
        } else {
            watchFacePhotoInfo.setPositionIndex(1);
        }
        a(backgroundList, arrayList2);
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        this.mCurrentTransferPercent = 0;
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
        this.mHwWatchFaceBtManager.savePhotoInfoToDevice(watchFacePhotoInfo, latonaWatchFaceThemeAlbumInfo, new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFacePhotoAlbumManager$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFacePhotoAlbumManager.this.b(i2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i2, Object obj) {
        HwLog.i(h, "sendWatchFaceInfoToDevice() resultCode: " + i2);
        if (i2 == 101) {
            onSaveSuccess(obj);
            v();
        } else if (i2 == 111) {
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
            this.mOperateWatchFaceCallback.transmitWatchFaceAlbumInstallResult(i2);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected List<Integer> getFileType() {
        if (!Utils.isEmpty(this.q)) {
            return this.q;
        }
        this.q.add(3);
        return this.q;
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void transmitWatchFaceInfo(String str) {
        if (this.mOperateWatchFaceCallback == null) {
            HwLog.i(h, "transmitWatchFaceInfo() mOperateWatchFaceCallback is null.");
        } else {
            this.mOperateWatchFaceCallback.transmitWatchFaceThemeAlbumInfo(str);
        }
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void parseCustomWatchFaceConfig(ElementsProvider elementsProvider) {
        p();
        q();
        e();
    }

    @Override // com.huawei.watchface.manager.BaseWatchFaceManager
    protected void clearInvalidData() {
        this.mWatchFaceProvider.setVideoStructs(null);
        this.mWatchFaceProvider.setDefaultVideoStructs(null);
        this.mWatchFaceProvider.setDefaultBackgroundOptionalConfigList(null);
        this.mWatchFaceProvider.setBackgroundOptionalConfigList(null);
    }

    public String getBgPath() {
        return this.mBackgroundDir;
    }

    public void f(String str) {
        if (Math.abs(System.currentTimeMillis() - this.f) < 1000) {
            HwLog.i(h, "saveWatchFaceInfoV2 disabled Click");
            return;
        }
        this.f = System.currentTimeMillis();
        try {
            dz.b(String.valueOf(System.currentTimeMillis()));
            LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = (LatonaWatchFaceThemeAlbumInfo) GsonHelper.fromJson(str, LatonaWatchFaceThemeAlbumInfo.class);
            ArrayList<String> backgroundList = latonaWatchFaceThemeAlbumInfo.getBackgroundList();
            ArrayList<String> arrayList = latonaWatchFaceThemeAlbumInfo.getmPortraitModeList();
            if (backgroundList == null) {
                HwLog.w(h, "saveWatchFaceInfoV2() watchFaceInfo is null.");
                return;
            }
            HwLog.i(h, "saveWatchFaceInfoV2() isClickSaveButton.");
            b();
            e(backgroundList);
            d(backgroundList);
            b(backgroundList, arrayList);
        } catch (Exception e) {
            HwLog.e(h, "saveWatchFaceInfoV2() exception occured :" + HwLog.printException(e));
            c();
        }
    }

    private void b(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        HwLog.i(h, "sendWatchFaceInfoToDeviceV2() enter.");
        new WatchFacePhotoInfo();
        ArrayList arrayList3 = new ArrayList(32);
        if (arrayList.size() > 0) {
            arrayList3.addAll(arrayList);
        }
        this.r.clear();
        this.r.addAll(arrayList3);
        a(arrayList, arrayList2);
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        this.mCurrentTransferPercent = 0;
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
        this.mHwWatchFaceBtManager.setPhotoInfoToDeviceCallback(new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.WatchFacePhotoAlbumManager$$ExternalSyntheticLambda2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFacePhotoAlbumManager.this.a(i2, obj);
            }
        });
        this.mOperateWatchFaceCallback.transmitWatchFaceAlbumApplyResult(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i2, Object obj) {
        HwLog.i(h, "sendWatchFaceInfoToDeviceV2() resultCode: " + i2);
        if (i2 == 101) {
            onSaveSuccess(obj);
            b(this.r);
        } else if (i2 == 111) {
            onSaveToTransferBackgrounds(obj, 1);
        } else {
            onSaveFailed();
        }
    }

    private void b(ArrayList<String> arrayList) {
        v();
        c(arrayList);
    }

    private void c(ArrayList<String> arrayList) {
        String str = h;
        HwLog.d(str, "updatePhotoOrgCache");
        if (arrayList == null) {
            return;
        }
        ConcurrentHashMap<String, HashMap<String, String>> c = de.c();
        if (c == null) {
            HwLog.d(str, "orgMap is null");
            return;
        }
        Iterator<Map.Entry<String, HashMap<String, String>>> it = c.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> next = it.next();
            if (!arrayList.contains(next.getKey())) {
                it.remove();
                HwLog.d(h, "remove cache:" + next.getKey());
            }
        }
        dp.a("watchfacePhotoOrgKey", new Gson().toJson(c), "album_background");
    }

    private void v() {
        String str = h;
        HwLog.i(str, "updateBackgrounds");
        this.m.clear();
        File file = new File(this.mBackgroundDir);
        if (!file.exists()) {
            HwLog.w(str, "setWatchFaceSvgPath failed, watchFaceSvgPath don't exist");
            return;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            HwLog.w(str, "watchFaceSvgPath childFileNames is null or not enough length");
            return;
        }
        for (String str2 : list) {
            if (!this.mIsSyncWatchFace || !str2.endsWith(WatchFaceConstant.BIN_SUFFIX)) {
                if (new File(this.mBackgroundDir + File.separator + str2).isDirectory()) {
                    HwLog.e(h, "setBackgroundPathNew tmp is directory:" + str2);
                } else {
                    this.m.add(str2);
                }
            }
        }
    }

    private void d(ArrayList<String> arrayList) {
        if (ArrayUtils.isEmpty(arrayList)) {
            return;
        }
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!this.o.contains(it.next())) {
                this.mTransferTotalCount++;
            }
        }
        HwLog.i(h, "setTransferTotalCountV2() BeforeSave, willTransfer TotalBgCount =" + this.mTransferTotalCount);
    }

    private void e(ArrayList<String> arrayList) {
        File[] listFiles = new File(this.mBackgroundDir).listFiles();
        if (listFiles == null) {
            HwLog.w(h, "updateNativeFiles() BackgroundsDirContainsNoFile");
            return;
        }
        HwLog.i(h, "updateNativeFiles() CheckingBackgroundFiles");
        if (arrayList == null) {
            arrayList = new ArrayList<>(32);
        }
        for (File file : listFiles) {
            String name = file.getName();
            boolean contains = arrayList.contains(name);
            boolean contains2 = arrayList.contains(SafeString.replace(SafeString.replace(SafeString.replace(SafeString.replace(name, BaseWatchFaceManager.ORIGINBG_SUFFIX, ""), BaseWatchFaceManager.ORIGINFG_SUFFIX, ""), BaseWatchFaceManager.FG_SUFFIX, ""), BaseWatchFaceManager.BG_SUFFIX, ""));
            if (!contains && !contains2) {
                HwLog.d(h, "updateNativeFiles() DeleteBackgroundFile :" + name + "delete :" + file.delete());
            }
        }
    }

    public void a(int i2, int i3) {
        HwLog.i(h, "sendWatchFaceInfoToDevice() resultCode: " + i2);
        if (i2 == 101) {
            onSaveSuccess(Integer.valueOf(i3));
            b(this.r);
            this.mHwWatchFaceBtManager.removePhotoCallback();
        } else if (i2 == 111) {
            onSaveToTransferBackgrounds(Integer.valueOf(i3), 1);
        } else {
            onSaveFailed();
            this.mHwWatchFaceBtManager.removePhotoCallback();
        }
    }
}
