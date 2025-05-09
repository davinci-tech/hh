package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwcloudmodel.contentcenter.ContentCenterApi;
import com.huawei.hwcloudmodel.contentcenter.UploadProgressListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.videoedit.utils.Utils;
import defpackage.jaj;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class izz {
    public List<jaj> c(List<String> list, int i, int i2, UploadProgressListener uploadProgressListener) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (a(it.next(), uploadProgressListener)) {
                return Collections.EMPTY_LIST;
            }
        }
        return a(list, i, i2, uploadProgressListener);
    }

    private boolean a(String str, UploadProgressListener uploadProgressListener) {
        File file = new File(str);
        if (!FileUtils.c(file)) {
            LogUtil.b("MomentsPublishManager", "filePath illegal");
            uploadProgressListener.onError(1, "filePath illegal");
            return true;
        }
        if (c(file.getName())) {
            return false;
        }
        LogUtil.b("MomentsPublishManager", "filename invalid");
        uploadProgressListener.onError(1, "filename invalid");
        return true;
    }

    private boolean c(String str) {
        return str.replace(".", "").matches("[a-zA-Z0-9]+");
    }

    private List<jaj> a(List<String> list, int i, int i2, UploadProgressListener uploadProgressListener) {
        ArrayList arrayList = new ArrayList();
        jak d = d(list, i, i2);
        LogUtil.a("MomentsPublishManager", "file extract done");
        jan e = jbs.a(BaseApplication.e()).e(d);
        if (e == null) {
            LogUtil.b("MomentsPublishManager", "UploadFileInfoRsp null");
            uploadProgressListener.onError(2, "UploadFileInfoRsp null");
            return Collections.EMPTY_LIST;
        }
        List<jam> d2 = e.d();
        if (a(list, uploadProgressListener, d2)) {
            return Collections.EMPTY_LIST;
        }
        float f = 0.0f;
        for (int i3 = 0; i3 < d2.size(); i3++) {
            jam jamVar = d2.get(i3);
            LogUtil.c("MomentsPublishManager", "fileId:", jamVar.c(), " filename:", jamVar.a(), " headers:", jamVar.e(), " method:", jamVar.d(), " url:", jamVar.b());
            jaj.d dVar = new jaj.d();
            if (jamVar.a().contains(".webp")) {
                dVar.b(jamVar.c());
                dVar.a(list.get(i3));
            }
            if (jamVar.a().contains(Utils.SUFFIX_MP4)) {
                dVar.c(jamVar.c());
                dVar.e(list.get(i3));
            }
            arrayList.add(dVar.c());
            if (TextUtils.isEmpty(jamVar.d()) || TextUtils.isEmpty(jamVar.b()) || jamVar.e() == null) {
                LogUtil.b("MomentsPublishManager", "fileUploadInfo param null");
                uploadProgressListener.onError(2, "fileUploadInfo param null");
                return Collections.EMPTY_LIST;
            }
            if (e(jamVar, list.get(i3))) {
                f += 1.0f / d2.size();
                LogUtil.a("MomentsPublishManager", "uploadFile progress:", Float.valueOf(f));
                uploadProgressListener.onProgress(Math.min(f, 1.0f));
            } else {
                uploadProgressListener.onError(3, "upload file fail at index:" + i3);
                return Collections.EMPTY_LIST;
            }
        }
        b(uploadProgressListener, f);
        return arrayList;
    }

    private jak d(List<String> list, int i, int i2) {
        jak jakVar = new jak();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next(), i, i2));
        }
        jakVar.e(arrayList);
        return jakVar;
    }

    private boolean a(List<String> list, UploadProgressListener uploadProgressListener, List<jam> list2) {
        if (koq.b(list2)) {
            LogUtil.b("MomentsPublishManager", "empty fileUploadInfos");
            uploadProgressListener.onError(2, "empty fileUploadInfos");
            return true;
        }
        if (list2.size() == list.size()) {
            return false;
        }
        LogUtil.b("MomentsPublishManager", "fileUploadInfos size error");
        uploadProgressListener.onError(2, "fileUploadInfos size error");
        return true;
    }

    private void b(UploadProgressListener uploadProgressListener, float f) {
        if (f < 1.0f) {
            uploadProgressListener.onProgress(1.0f);
        }
    }

    private boolean e(jam jamVar, String str) {
        jag jagVar = new jag();
        jagVar.b(jamVar.e());
        jagVar.b(jamVar.d());
        jagVar.e(jamVar.b());
        return a(jagVar, str);
    }

    private boolean a(jag jagVar, String str) {
        Response<String> execute;
        String c = jagVar.c();
        if (c == null) {
            LogUtil.b("MomentsPublishManager", " uploadMethod null");
            return false;
        }
        String upperCase = c.toUpperCase(Locale.ENGLISH);
        if (!ProfileRequestConstants.PUT_TYPE.equals(upperCase) && !"POST".equals(upperCase)) {
            LogUtil.h("MomentsPublishManager", "support put | post only now");
            return false;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : jagVar.a().keySet()) {
            hashMap.put(str2, jagVar.a().get(str2).getAsString());
        }
        LogUtil.c("MomentsPublishManager", "uploadFile Headers:", hashMap);
        if (!hashMap.containsKey("Content-Type")) {
            LogUtil.h("MomentsPublishManager", "content-type not in headers");
            return false;
        }
        LogUtil.c("MomentsPublishManager", "partFilePath:", str, " partFileSize:", Long.valueOf(new File(str).length()));
        RequestBody create = RequestBody.create(MediaType.parse((String) hashMap.get("Content-Type")), new File(str));
        ContentCenterApi contentCenterApi = (ContentCenterApi) lqi.d().b(ContentCenterApi.class);
        if (contentCenterApi == null) {
            LogUtil.b("MomentsPublishManager", "upload to ContentCenter api null");
            return false;
        }
        try {
            LogUtil.c("MomentsPublishManager", "requestBody content-length:", Long.valueOf(create.contentLength()));
            if (ProfileRequestConstants.PUT_TYPE.equals(upperCase)) {
                execute = contentCenterApi.putToContentCenter(jagVar.b(), hashMap, create).execute();
            } else {
                execute = contentCenterApi.postToContentCenter(jagVar.b(), hashMap, create).execute();
            }
        } catch (IOException e) {
            LogUtil.h("MomentsPublishManager", "upload exception:", e.getMessage());
        }
        if (execute == null) {
            LogUtil.b("MomentsPublishManager", "upload to ContentCenter rsp null");
            return false;
        }
        if (execute.isSuccessful()) {
            LogUtil.a("MomentsPublishManager", "upload success");
            return true;
        }
        LogUtil.b("MomentsPublishManager", "upload fail:", Integer.valueOf(execute.getCode()), execute.getErrorBody());
        return false;
    }

    private jai a(String str, int i, int i2) {
        File file = new File(str);
        jai jaiVar = new jai();
        jaiVar.a(i);
        jaiVar.e(1);
        jaiVar.e(file.getName());
        jaiVar.d((int) file.length());
        jaiVar.c(SecurityUtils.c(file));
        jaiVar.b(i2);
        return jaiVar;
    }
}
