package com.huawei.hms.kit.awareness;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.kit.awareness.donate.DonateResponse;
import com.huawei.hms.kit.awareness.donate.ServiceOpenIdResponse;
import com.huawei.hms.kit.awareness.donate.message.InsightIntent;
import com.huawei.hms.kit.awareness.donate.message.Message;
import java.util.List;

/* loaded from: classes4.dex */
public interface DonateClient extends Client {
    Task<DonateResponse> deleteEntity(String str, String[] strArr);

    Task<DonateResponse> deleteIntent(String str);

    Task<DonateResponse> deleteIntent(String str, String[] strArr);

    Task<ServiceOpenIdResponse> getServiceOpenId(boolean z);

    Task<DonateResponse> sendMessage(Message message);

    DonateClient setAgreePrivacy(boolean z);

    Task<DonateResponse> shareIntent(List<InsightIntent> list);
}
