package com.huawei.ui.thirdpartservice.syncdata.constants;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public interface RuntasticOauthConstant {
    public static final String RUNTASTIC_AUTHORIZATION_URL = "/v1/as/authorization.oauth2?client_id=%s&scope=openid pa_activity pa_identity&response_type=code&pfidpadapterid=refid&redirect_uri=%s";
    public static final String RUNTASTIC_CLIENT_ID = "papi_huawei";
    public static final String RUNTASTIC_REDIRECT_URI = "/cch5/HuaweiHealth/activity/web/html/runtastic.html";
    public static final String RUNTASTIC_UPLOAD_PATH = "/v0/activities";
    public static final List<Integer> SYNC_DATA_TYPE = Collections.unmodifiableList(Arrays.asList(257, 258, 259, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM), 260, 282, 217, 219, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE), 218));
}
