package com.huawei.phoneservice.feedbackcommon.network;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.j;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class FeedbackWebConstants {
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONNECTIOIN = "connectioin";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DATA_OPERATION = "/v2/dataOperation?appID=";
    public static final String FEEDBACK_DETAIL_URL = "hwc/detail";
    public static final String FEEDBACK_NEW_UPLOAD_INFO = "/feedback/api/v1/log/reUploadInfo";
    public static final String FEEDBACK_NOTIFY_SUCCESS = "/feedback/api/v1/log/notify";
    public static final String FEEDBACK_UPLOAD_INFO = "/feedback/api/v1/log/uploadInfo";
    public static final String HEADER_ACCESSTOKEN = "accessToken";
    public static final String HEAD_CONTENT_TYPE_KEY = "Content-type";
    public static final String HEAD_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded;charset=utf-8";
    public static final String HISTORY_FEEDBACK_URL = "hwc/queryFeedbackMsg";
    public static final String HOST = "Host";
    public static final String INVALID_FILE_NAME_PRE = "../";
    public static final String NEW_UPLOAD_INFO = "/v2/getNewUploadInfo?appID=";
    public static final String NOTIFY_UPLOAD_SUCC = "/v2/notifyUploadSucc?appID=";
    public static final String POST = "POST";
    public static final String PROBLEM_SUGGEST_FILES_URL = "/feedback/uploads";
    public static final String PROBLEM_SUGGEST_FILES_URL_V2 = "/feedback/v2/uploads";
    public static final String QUERY_FEEDBACK_FORHD = "hwc/queryForHD";
    public static final String QUESTION_FEEDBACK_SUBMIT = "hwc/submit";
    public static final String QUESTION_FEEDBACK_SUBMIT_FORHD = "hwc/updateForHD";
    public static final String RATE_URL = "hwc/rate";
    public static final String REQUEST_ISO_LANGUAGE_URL = "ccpc/queryLangMappingISO";
    public static final String REQUEST_PRIVACY_NOTICE_URL = "ccpc/queryIntimeNoticeInfo";
    public static final String REQUEST_QUESTION_TYPE_URL = "hwc/category";
    public static final String SERVER_DOMAIN = "/v2/getServerDomain?appID=";
    public static final String SET_READ_URL = "hwc/setRead";
    public static final String SHA_256 = "SHA-256";
    public static final int SHA_256_MEIOSIS = 40;
    public static final String SPLIT_V = "splitV";
    public static final String SUFFIX = ".zip";
    public static final String TIME_FORMATS = "yyyyMMddhh24mmss";
    public static final String UPLOAD_INFO = "/v2/getUploadInfo?appID=";
    public static final String USER_AGENT = "user-agent";
    public static final String X_AMZ_CONTENT_SHA256 = "x-amz-content-sha256";
    public static final String X_AMZ_DATE = "x-amz-date";

    public static String getZipFilePath(Context context) {
        return context.getFilesDir().getAbsolutePath().replace("/files", "/zips");
    }

    public static String getLogPath(Context context) throws IOException {
        File externalFilesDir;
        String sdk = j.c().getSdk(FaqConstants.FAQ_LOG_ROOT_PATH);
        return !TextUtils.isEmpty(sdk) ? sdk : (!"Y".equalsIgnoreCase(j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_LOG_SDCARD)) || (externalFilesDir = context.getExternalFilesDir("")) == null) ? context.getFilesDir().getAbsolutePath() : externalFilesDir.getCanonicalPath();
    }
}
