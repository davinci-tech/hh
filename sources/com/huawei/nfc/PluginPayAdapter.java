package com.huawei.nfc;

import com.huawei.pluginbase.PluginBaseAdapter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface PluginPayAdapter extends PluginBaseAdapter {
    public static final String DEVICE_BT_TYPE = "device_bt_type";
    public static final String DEVICE_CATEGORY = "device_category";
    public static final String KEY_DEVICE_INFO_BT_VERSION = "BT_version";
    public static final String KEY_DEVICE_INFO_CERT_MODEL = "device_cert_model";
    public static final String KEY_DEVICE_INFO_MODEL = "device_model";
    public static final String KEY_DEVICE_INFO_NAME = "device_name";
    public static final String KEY_DEVICE_INFO_SN = "device_sn";
    public static final String KEY_DEVICE_INFO_SOFT_VERSION = "soft_version";
    public static final String KEY_DEVICE_INFO_UDID = "device_udid";
    public static final String KEY_OPEN_CHANNEL_APDU = "apdu";
    public static final String KEY_OPEN_CHANNEL_ID = "channelID";

    boolean addBusCard(String str, String str2, String str3);

    boolean addCardToWatch(String str);

    int autheticateMifareSector(byte[] bArr, int i, int i2);

    void cardEvent(String str, int i);

    void closeChannel();

    void closeChannelSnb();

    boolean deleteCard(String str);

    String getAccessConfigInfo();

    String getBlockData(byte[] bArr, int i, int i2);

    String getBtcInfoResponse();

    String getCardInfo(int i, int i2);

    String getCplc();

    int getDeviceBluetoothType();

    int getDeviceConnectState();

    Map<String, String> getDeviceInfo();

    int getDeviceProtocol();

    int getLockscreenStatus();

    String getNfcCommandResponse(int i, String str);

    String getNfcTagInfo();

    String getServiceCountryCode();

    String getUserId();

    String getWalletAbility();

    boolean isDeviceBand(int i);

    boolean isSendCardHciAndAmountRuler(String str, Map<String, String> map, List<String> list);

    boolean isWalletOpenCard();

    boolean notificationOpenPageOfBand(String str);

    boolean notifyAfterTransferFile(List<Map<String, Object>> list);

    List<String> obtainCardList();

    Map<String, String> openChannel(String str, int i);

    byte[] openChannelSnb(byte[] bArr, int i);

    void registerHealthResponseListener(WalletResponseListener walletResponseListener);

    int sendAccessCardReadEvent(int i);

    int sendAccessCardRfInfo(String str, String str2);

    int sendAccount(String str);

    String sendApdu(String str, String str2);

    void sendBluetoothCommand(int i, int i2, ByteBuffer byteBuffer);

    byte[] transmitSnb(byte[] bArr);

    void unRegisterHealthResponseListener();

    boolean updateCardInfo(String str);
}
