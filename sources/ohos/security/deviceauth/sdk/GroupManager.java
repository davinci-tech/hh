package ohos.security.deviceauth.sdk;

import android.content.Context;
import java.util.List;

/* loaded from: classes7.dex */
public interface GroupManager {
    int addMemberToGroup(long j, String str, String str2);

    int confirmRequest(long j, String str, String str2);

    int createGroup(long j, String str, String str2);

    int deleteGroup(long j, String str, String str2);

    int deleteMemberFromGroup(long j, String str, String str2);

    void destroyService();

    List<String> getGroupInfo(String str, String str2);

    int initService(Context context);

    boolean isDeviceInGroup(String str, String str2, String str3);

    int processData(long j, byte[] bArr);

    int registerCallback(String str, DeviceAuthCallback deviceAuthCallback);

    int unregisterCallback(String str);
}
