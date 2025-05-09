package com.huawei.health.dailyactivity.impl;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.dailyactivity.impl.ThreeCircleCloudResourceHelper;
import com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper;
import com.huawei.health.healthcloudconfig.helper.CheckResourceUpdateCallback;
import com.huawei.health.healthcloudconfig.helper.CloudResourceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dqh;
import defpackage.dqi;
import defpackage.dqs;
import defpackage.drd;
import defpackage.drm;
import defpackage.dro;
import defpackage.njg;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ThreeCircleCloudResourceHelper extends BaseCloudResourceHelper {
    private static final int DEFAULT_UPDATE_INTERVAL = 1;
    private static final String RELEASE_TAG = "R_3CircCloudResHlp";
    private static final String TAG = "ThreeCircleCloudResourceHelper";
    private static final String THREE_CIRCLE_CONFIG_FLAG = "three_circle";
    private static final String THREE_CIRCLE_CONFIG_ID;
    private static final List<String> THREE_CIRCLE_FILE_ID_LIST;
    private static final Map<String, String> THREE_CIRCLE_MATCH_RULES;
    private static final int UNZIP_CODE_FAIL = -1;
    private String mAppRuleFilePath;
    private String mAppStringsFilePath;
    private ThreeCircleStringParser mThreeCircleStringParser;

    static {
        THREE_CIRCLE_CONFIG_ID = CommonUtil.cc() ? "com.huawei.health_threecircle" : "com.huawei.health_ThreeCircle_Android";
        THREE_CIRCLE_FILE_ID_LIST = new ArrayList<String>() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleCloudResourceHelper.1
            {
                add("rule");
                add("strings");
            }
        };
        THREE_CIRCLE_MATCH_RULES = new LinkedHashMap<String, String>() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleCloudResourceHelper.2
            {
                put("appVersion", "V100");
            }
        };
    }

    public static ThreeCircleCloudResourceHelper getInstance() {
        return a.b;
    }

    private ThreeCircleCloudResourceHelper() {
        super(THREE_CIRCLE_CONFIG_FLAG, 1);
        this.mAppStringsFilePath = null;
        this.mAppRuleFilePath = null;
    }

    public void checkUpdateForThreeCircle() {
        LogUtil.a(TAG, "begin to checkUpdate for threeCircle");
        super.checkUpdate(THREE_CIRCLE_CONFIG_ID, THREE_CIRCLE_MATCH_RULES, new CheckResourceUpdateCallback() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleCloudResourceHelper.5
            @Override // com.huawei.health.healthcloudconfig.helper.CheckResourceUpdateCallback
            public void onSuccess(String str, boolean z) {
                LogUtil.a(ThreeCircleCloudResourceHelper.TAG, "check update finished, need update = ", Boolean.valueOf(z));
                if (z) {
                    LogUtil.a(ThreeCircleCloudResourceHelper.TAG, "need update, begin to download");
                    ThreeCircleCloudResourceHelper.this.downloadFilesForThreeCircle();
                }
            }

            @Override // com.huawei.health.healthcloudconfig.helper.CheckResourceUpdateCallback
            public void onFailure(String str, String str2) {
                ThreeCircleCloudResourceHelper.this.onFailureForUpdate(ThreeCircleCloudResourceHelper.TAG, "cannot get file from cloud: " + str2, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFilesForThreeCircle() {
        LogUtil.a(TAG, "begin to download files");
        super.downloadFiles(THREE_CIRCLE_CONFIG_ID, THREE_CIRCLE_FILE_ID_LIST, THREE_CIRCLE_MATCH_RULES, 1, new CloudResourceCallback() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleCloudResourceHelper.3
            @Override // com.huawei.health.healthcloudconfig.helper.CloudResourceCallback
            public void onSuccess(String str, List<drm> list) {
                LogUtil.a(ThreeCircleCloudResourceHelper.TAG, "download files success");
                ThreeCircleCloudResourceHelper.this.unzipResourcePkg(list);
            }

            @Override // com.huawei.health.healthcloudconfig.helper.CloudResourceCallback
            public void onFailure(String str, String str2) {
                ReleaseLogUtil.c(ThreeCircleCloudResourceHelper.RELEASE_TAG, "download files failed");
            }
        });
    }

    @Override // com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper
    public void handleResponse(dqi dqiVar, String str, List<String> list, CloudResourceCallback cloudResourceCallback) {
        List<dqh> e = dqiVar.e();
        LinkedList linkedList = new LinkedList();
        for (dqh dqhVar : e) {
            for (String str2 : list) {
                if (str2.equals(dqhVar.a())) {
                    String c = dqhVar.c();
                    String d = dqhVar.d();
                    String d2 = drd.d(str, str2, d);
                    if (TextUtils.isEmpty(c) || TextUtils.isEmpty(d) || TextUtils.isEmpty(d2)) {
                        onFailureForDownload(RELEASE_TAG, "version or downloadUrl or filePath is empty", cloudResourceCallback);
                    } else {
                        File file = new File(d2);
                        String str3 = file.getParent() + File.separator + str2 + "." + c + "." + d2.split("\\.")[r11.length - 1];
                        File file2 = new File(str3);
                        LogUtil.a(TAG, "defaultFilePath = ", d2, ", newFileName = ", str3);
                        boolean renameTo = file.renameTo(file2);
                        String path = file.getPath();
                        if (!renameTo) {
                            ReleaseLogUtil.e(RELEASE_TAG, "renameFile failed: maybe file exists");
                        } else {
                            path = file2.getPath();
                        }
                        drm drmVar = new drm();
                        drmVar.d(str2);
                        drmVar.e(CommonUtils.h(c));
                        drmVar.c(path);
                        linkedList.add(drmVar);
                    }
                }
                if (cloudResourceCallback != null) {
                    cloudResourceCallback.onSuccess(TAG, linkedList);
                }
            }
        }
    }

    @Override // com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper
    public List<dqs> getLocalFileData(File[] fileArr) {
        LinkedList linkedList = new LinkedList();
        for (File file : fileArr) {
            String name = file.getName();
            LogUtil.a(TAG, "get fileName = ", name);
            String[] split = name.split("\\.");
            if (split.length != 3) {
                ReleaseLogUtil.d(RELEASE_TAG, "fileName is invalid");
            } else {
                dqs dqsVar = new dqs();
                dqsVar.a(split[0]);
                dqsVar.e(CommonUtils.h(split[1]));
                linkedList.add(dqsVar);
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unzipResourcePkg(List<drm> list) {
        LogUtil.a(TAG, "begin to unzip resource package");
        Iterator<drm> it = list.iterator();
        while (it.hasNext()) {
            String a2 = it.next().a();
            if (a2.endsWith(".zip")) {
                int e = dro.e(a2, a2.split(".zip")[0]);
                LogUtil.a(TAG, "resultCode = ", Integer.valueOf(e));
                if (e == -1) {
                    ReleaseLogUtil.e(RELEASE_TAG, "unzip failed");
                }
            }
        }
        deleteOldFiles();
    }

    private void deleteOldFiles() {
        LogUtil.a(TAG, "begin to delete old files");
        File[] listFiles = new File(drd.d(THREE_CIRCLE_CONFIG_ID, (String) null, (String) null)).listFiles();
        if (listFiles == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "fileList is empty");
            return;
        }
        Arrays.sort(listFiles, new Comparator() { // from class: cec
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ThreeCircleCloudResourceHelper.lambda$deleteOldFiles$0((File) obj, (File) obj2);
            }
        });
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        for (File file : listFiles) {
            String name = file.getName();
            if (file.isDirectory()) {
                linkedList3.add(name);
            } else if (name.endsWith("json")) {
                linkedList.add(name);
            } else if (name.endsWith(".zip")) {
                linkedList2.add(name);
            }
        }
        LogUtil.a(TAG, "jsonFile = ", linkedList, ", zipFile = ", linkedList2, ", unzipFile = ", linkedList3);
        for (File file2 : listFiles) {
            String name2 = file2.getName();
            boolean z = !linkedList.isEmpty() && name2.equals(linkedList.get(0));
            boolean z2 = !linkedList2.isEmpty() && name2.equals(linkedList2.get(0));
            if ((!linkedList3.isEmpty() && name2.equals(linkedList3.get(0))) || z || z2) {
                ReleaseLogUtil.e(RELEASE_TAG, name2, " is latest, keep it");
            } else if (file2.isDirectory()) {
                FileUtils.b(file2);
                ReleaseLogUtil.e(RELEASE_TAG, "deleteSourceFile sourceFile = ", name2);
            } else {
                ReleaseLogUtil.e(RELEASE_TAG, "deleteSourceFile sourceFile = ", name2, ", isDeleted = ", Boolean.valueOf(FileUtils.d(file2)));
            }
        }
    }

    public static /* synthetic */ int lambda$deleteOldFiles$0(File file, File file2) {
        return (int) (file2.lastModified() - file.lastModified());
    }

    private void initThreeCircleParser() {
        File[] listFiles = new File(drd.d(THREE_CIRCLE_CONFIG_ID, (String) null, (String) null)).listFiles();
        if (listFiles == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "fileList is empty");
            this.mThreeCircleStringParser = new ThreeCircleStringParser("", "");
            return;
        }
        for (File file : listFiles) {
            String name = file.getName();
            if (file.isDirectory()) {
                this.mAppStringsFilePath = drd.d(THREE_CIRCLE_CONFIG_ID, name, (String) null);
            }
            if (name.endsWith("json")) {
                this.mAppRuleFilePath = drd.d(THREE_CIRCLE_CONFIG_ID, name, (String) null);
            }
        }
        LogUtil.a(TAG, "mAppStringsFilePath = ", this.mAppStringsFilePath, ", mAppRuleFilePath = ", this.mAppRuleFilePath);
        this.mThreeCircleStringParser = new ThreeCircleStringParser(this.mAppRuleFilePath, this.mAppStringsFilePath);
    }

    private ThreeCircleStringParser getThreeCircleParser() {
        if (isNeedParserInit()) {
            initThreeCircleParser();
        }
        return this.mThreeCircleStringParser;
    }

    private boolean isNeedParserInit() {
        if (this.mThreeCircleStringParser == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "mThreeCircleParseApi is null, begin to init mThreeCircleParseApi");
            return true;
        }
        if (TextUtils.isEmpty(this.mAppRuleFilePath)) {
            ReleaseLogUtil.d(RELEASE_TAG, "mAppRuleFilePath is null, begin to init mThreeCircleParseApi");
            return true;
        }
        if (!TextUtils.isEmpty(this.mAppStringsFilePath)) {
            return false;
        }
        ReleaseLogUtil.d(RELEASE_TAG, "mAppStringsFilePath is null, begin to init mThreeCircleParseApi");
        return true;
    }

    public njg queryRules(String str, String str2) {
        ReleaseLogUtil.e(TAG, "queryRules called");
        ThreeCircleStringParser threeCircleParser = getThreeCircleParser();
        this.mThreeCircleStringParser = threeCircleParser;
        if (threeCircleParser == null) {
            ReleaseLogUtil.d(TAG, "mThreeCircleParseApi is null");
            return null;
        }
        return threeCircleParser.queryRules(str, str2);
    }

    public String getPromptMessage(njg njgVar, Map<String, String> map) {
        ReleaseLogUtil.e(TAG, "getPromptMessage called");
        ThreeCircleStringParser threeCircleParser = getThreeCircleParser();
        this.mThreeCircleStringParser = threeCircleParser;
        if (threeCircleParser == null) {
            ReleaseLogUtil.d(TAG, "mThreeCircleParseApi is null");
            return null;
        }
        return threeCircleParser.getPromptMessage(njgVar, map);
    }

    static class a {
        private static final ThreeCircleCloudResourceHelper b = new ThreeCircleCloudResourceHelper();
    }
}
