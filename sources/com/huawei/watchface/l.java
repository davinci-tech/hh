package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.mvp.model.datatype.FileCreateResponse;
import com.huawei.watchface.mvp.model.datatype.MaterialFileInfo;
import com.huawei.watchface.mvp.model.datatype.MaterialFileUploadInfo;
import com.huawei.watchface.mvp.model.datatype.WiseContentHeaders;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes7.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static l f11055a;
    private String b;
    private String c;
    private OperateWatchFaceCallback d;

    public static l a() {
        if (f11055a == null) {
            synchronized (l.class) {
                if (f11055a == null) {
                    f11055a = new l();
                }
            }
        }
        return f11055a;
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.d = operateWatchFaceCallback;
    }

    public String b() {
        return TextUtils.isEmpty(this.b) ? "" : aj.b(this.b);
    }

    public void a(String str) {
        if (this.d == null) {
            HwLog.i("HwSearchImageManager", "transmitSearchToImgCutResult mOperateWatchFaceCallback is null");
        } else {
            if (!TextUtils.isEmpty(str)) {
                this.b = str;
                this.d.transmitSearchToImgCut();
                c();
                return;
            }
            this.d.transmitSelectImageFailed();
        }
    }

    private void c() {
        bj bjVar = new bj(d());
        FileCreateResponse c = bjVar.c(bjVar.c());
        if (c == null) {
            HwLog.i("HwSearchImageManager", "fileCreateResponse is null");
            this.d.transmitUploadImageResult(201, null);
            return;
        }
        if (c.getResultCode() == 0) {
            HwLog.i("HwSearchImageManager", "fileCreate is successful");
            MaterialFileUploadInfo materialFileUploadInfo = c.getMaterialFileUploadInfo().get(0);
            a(materialFileUploadInfo.getUploadInfo().getUrl(), a(materialFileUploadInfo.getUploadInfo().getHeaders()), FileHelper.e(this.b), materialFileUploadInfo.getFileId());
            return;
        }
        HwLog.e("HwSearchImageManager", "fileCreate response is failed result code is :" + c.getResultCode());
        this.d.transmitUploadImageResult(c.getResultCode(), null);
    }

    private void a(String str, LinkedHashMap<String, String> linkedHashMap, File file, final String str2) {
        new cb(str, linkedHashMap, file, new ResponseListener<Integer>() { // from class: com.huawei.watchface.l.1
            @Override // com.huawei.watchface.api.ResponseListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(Integer num) {
                if (l.this.d == null) {
                    HwLog.i("HwSearchImageManager", "sendUploadImage mOperateWatchFaceCallback is null");
                    return;
                }
                HwLog.i("HwSearchImageManager", "sendUploadImage onResponse is " + num);
                l.this.d.transmitUploadImageResult(num.intValue(), str2);
            }

            @Override // com.huawei.watchface.api.ResponseListener
            public void onError() {
                if (l.this.d != null) {
                    l.this.d.transmitUploadImageResult(201, str2);
                } else {
                    HwLog.i("HwSearchImageManager", "sendUploadImage mOperateWatchFaceCallback is null");
                }
            }
        });
    }

    private LinkedHashMap<String, String> a(WiseContentHeaders wiseContentHeaders) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            linkedHashMap.put("x-amz-content-sha256", wiseContentHeaders.getSha256Value().toLowerCase(Locale.ENGLISH));
            linkedHashMap.put("Authorization", wiseContentHeaders.getAuthorization());
            linkedHashMap.put("x-amz-date", wiseContentHeaders.getRequestTime());
            linkedHashMap.put("Host", wiseContentHeaders.getHost());
            linkedHashMap.put("Content-Type", wiseContentHeaders.getContentType());
        } catch (Exception e) {
            HwLog.i("HwSearchImageManager", "Upload headers error " + HwLog.printException(e));
        }
        return linkedHashMap;
    }

    private List<MaterialFileInfo> d() {
        MaterialFileInfo materialFileInfo;
        File e;
        ArrayList arrayList = new ArrayList();
        try {
            materialFileInfo = new MaterialFileInfo();
            e = FileHelper.e(this.b);
        } catch (Exception e2) {
            HwLog.i("HwSearchImageManager", "fileCreate error " + HwLog.printException(e2));
        }
        if (e == null) {
            HwLog.e("HwSearchImageManager", "getMaterialInfo bitmapFile is null");
            return arrayList;
        }
        String name = e.getName();
        String path = e.getPath();
        materialFileInfo.setFileName(name);
        materialFileInfo.setIndex("1");
        materialFileInfo.setAppContentId(e());
        materialFileInfo.setFileSha256(FileHelper.sha256File(path).toLowerCase(Locale.ENGLISH));
        materialFileInfo.setContentType(100);
        byte[] c = FileHelper.c(e);
        materialFileInfo.setFileSize(c == null ? 0 : c.length);
        arrayList.add(materialFileInfo);
        return arrayList;
    }

    private String e() {
        return UUID.randomUUID().toString();
    }

    public void b(String str) {
        HwLog.i("HwSearchImageManager", "choosePicToH5Search routIndex:" + str);
        this.c = str;
        OperateWatchFaceCallback operateWatchFaceCallback = this.d;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.chooseToClip(4, false);
        }
    }

    public void a(boolean z, boolean z2) {
        HwLog.i("HwSearchImageManager", "cancelSearchImage isGoBack:" + z2);
        OperateWatchFaceCallback operateWatchFaceCallback = this.d;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.cancelSearchImage(z, z2);
        }
        de.d();
    }
}
