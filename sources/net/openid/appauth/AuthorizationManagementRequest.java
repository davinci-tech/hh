package net.openid.appauth;

import android.net.Uri;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public interface AuthorizationManagementRequest {
    String getState();

    JSONObject jsonSerialize();

    String jsonSerializeString();

    Uri toUri();
}
