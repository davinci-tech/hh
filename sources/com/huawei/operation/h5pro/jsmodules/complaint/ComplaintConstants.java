package com.huawei.operation.h5pro.jsmodules.complaint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ComplaintConstants {
    public static final String ADDITIONAL_CONTEXT_PARAM_KEY = "additionalContext";
    public static final int BI_EVENT_COMPLAINT_SUBMIT = 1;
    public static final String BI_KEY_COMPLAINT_SUBMIT = "20401105";
    public static final String COMPLAINT_APP_ID = "50018";
    public static final String CONTENT_ID_PARAM_KEY = "contentId";
    public static final String GROUP_ID_PARAM_KEY = "groupId";
    public static final String SCENE_ID_PARAM_KEY = "sceneId";
    public static final String SUB_SCENE_ID_PARAM_KEY = "subSceneId";
    public static final String CONTENT_TITLE_PARAM_KEY = "contentTitle";
    public static final String MEMBER_ID_PARAM_KEY = "memberId";
    public static final String MEMBER_NAME_PARAM_KEY = "memberName";
    public static final String CHAT_EVIDENCE_PARAM_KEY = "chatEvidence";
    public static final String GROUP_NAME_PARAM_KEY = "groupName";
    public static final List<String> COMPLAINT_PARAM_KEYS = Collections.unmodifiableList(Arrays.asList(SCENE_ID_PARAM_KEY, SUB_SCENE_ID_PARAM_KEY, "contentId", CONTENT_TITLE_PARAM_KEY, "groupId", MEMBER_ID_PARAM_KEY, MEMBER_NAME_PARAM_KEY, CHAT_EVIDENCE_PARAM_KEY, GROUP_NAME_PARAM_KEY));

    private ComplaintConstants() {
    }
}
