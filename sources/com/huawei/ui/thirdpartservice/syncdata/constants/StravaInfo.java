package com.huawei.ui.thirdpartservice.syncdata.constants;

/* loaded from: classes8.dex */
public interface StravaInfo {
    public static final String STRAVA_ACTIVITY_UPDATE = "/api/v3/activities/";
    public static final String STRAVA_AUTHORIZATION_URL = "/oauth/authorize?response_type=code&client_id=%s&scope=activity:write,activity:read&approval_prompt=auto&redirect_uri=%s";
    public static final String STRAVA_GET_UPLOAD = "/api/v3/uploads/";
    public static final String STRAVA_REDIRECT_URI = "/cch5/HuaweiHealth/activity/web/html/strava.html";
    public static final String STRAVA_UPLOAD_PATH = "/api/v3/uploads";

    String getClientId();
}
