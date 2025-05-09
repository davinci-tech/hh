package com.huawei.profile.account;

import com.huawei.profile.coordinator.exception.ProfileRequestException;
import java.util.Map;

/* loaded from: classes6.dex */
public interface AccountClientSpi {
    Map<String, String> generateRequestHeader() throws ProfileRequestException;

    boolean isExpiredAccount();

    void registerAccountReceiver();

    void setAccount(Account account);

    void setAccountInvalid();

    void updateExpiredAccount();
}
