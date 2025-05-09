# Bluetooth Communication Details

## Supported Devices

The system supports a diverse range of Bluetooth devices, each using different protocols and communication methods. Let's explore the device ecosystem in detail.

### Android Wear Devices
Android Wear devices represent a sophisticated category of smartwatches that utilize a specialized communication protocol. These devices are identified by their unique characteristics:

- Device identification: Devices are marked with either "AndroidWear" as their identifier or have identifiers ending with "smart_watch"
- Protocol type: These devices use a custom protocol (type 0 or 5) that's specifically designed for Android Wear integration
- Connection method: They leverage the Android Wear API for communication, which provides a more robust and feature-rich connection compared to standard Bluetooth protocols

The system handles these devices through the `BTDeviceAWService` class, which manages the complex communication requirements of Android Wear devices. This service ensures proper handshake protocols, data synchronization, and state management specific to the Android Wear ecosystem.

### Bluetooth Low Energy (BLE) Devices
BLE devices form the backbone of the health monitoring system, providing efficient, low-power communication for various health metrics. These devices are characterized by:

- Protocol type: Type 1 devices using the BLE protocol
- Connection method: GATT (Generic Attribute Profile) based communication
- Use cases: Primarily used for continuous health monitoring, including:
  - Heart rate monitoring
  - Step counting
  - Sleep tracking
  - Activity recognition

The BLE implementation is handled through the `BluetoothGattCallback` system, which manages the complex state machine of BLE connections. This includes service discovery, characteristic reading/writing, and notification handling.

### Bluetooth Classic Devices
Classic Bluetooth devices represent the traditional Bluetooth communication method, used for devices requiring higher bandwidth or legacy support:

- Protocol type: Type 2 devices using Bluetooth Classic
- Connection method: Standard Bluetooth socket-based communication
- Use cases: Typically used for:
  - Audio devices
  - High-bandwidth data transfer
  - Legacy device support

The classic Bluetooth implementation is managed through the `ceu` (ClassicDevice) class, which handles the traditional Bluetooth socket connections and data transfer protocols.

### Device Type Detection
The system employs a sophisticated device type detection mechanism that works as follows:

1. Initial device discovery through the Bluetooth adapter
2. Protocol type determination based on device characteristics
3. Appropriate service initialization based on the detected type

The device type detection is implemented in the `izy` class, which contains the logic for identifying and categorizing different device types. This is crucial for ensuring proper protocol selection and connection handling.

### Protocol Selection
The system uses a protocol selection mechanism that determines the appropriate communication method based on the device type:

```java
// From izy.java - Protocol selection
private BTDeviceServiceBase bDv_(BluetoothDevice bluetoothDevice, String str, BtDeviceStateCallback btDeviceStateCallback, int i, String str2) {
    int i2 = this.r;
    if (-1 == i2) {
        return null;
    }
    if (i2 == 0) {
        return WearableSwitchAdapter.a().d(null, btDeviceStateCallback);
    }
    if (i2 == 1) {
        return new izp(this.ac, bluetoothDevice, btDeviceStateCallback, i);
    }
    if (i2 == 2) {
        izt iztVar = new izt(this.ac, bluetoothDevice, str, btDeviceStateCallback);
        this.j = new izo(this, this.ac, this.i, this.y);
        return iztVar;
    }
    if (i2 == 5) {
        return WearableSwitchAdapter.a().d(str2, btDeviceStateCallback);
    }
    return null;
}
```

This code demonstrates how the system selects the appropriate service implementation based on the device type. Each type (0, 1, 2, 5) corresponds to a different protocol and communication method, ensuring optimal device support and functionality.

## BLE Communication Protocol

The Bluetooth Low Energy (BLE) protocol implementation is a sophisticated system that handles device discovery, connection establishment, and data transfer. Let's examine each component in detail.

### Device Discovery

The BLE discovery process is implemented through a combination of scanning and filtering mechanisms. The system uses the `BluetoothAdapter` to initiate device discovery and employs a callback system to handle discovered devices:

```java
// From bkt.java - Device discovery implementation
public boolean d() {
    if (this.c == null) {
        this.c = BluetoothAdapter.getDefaultAdapter();
    }
    try {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.startDiscovery();
        }
        return false;
    } catch (SecurityException unused) {
        LogUtil.e("BrDeviceAdapterUtil", "discoveryBrDevice SecurityException");
        return false;
    }
}
```

This code initiates the device discovery process. The system maintains a reference to the Bluetooth adapter and starts the discovery process when requested. Error handling is implemented to catch security exceptions that might occur during the discovery process.

### Connection Establishment

The BLE connection process is handled through the GATT (Generic Attribute Profile) system. The connection establishment involves several steps:

1. Initial connection to the device
2. Service discovery
3. Characteristic configuration
4. Notification setup

The connection process is implemented in the `izt` class, which extends `BluetoothGattCallback`:

```java
// From izt.java - Connection handling
private BluetoothGattCallback o = new BluetoothGattCallback() {
    @Override
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        izt.this.bDJ_(bluetoothGatt, i, i2);
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        izt.this.e(i);
    }

    @Override
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        izt.this.d(i);
    }

    @Override
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        izt.this.b(i);
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        izt.this.bDI_(bluetoothGattCharacteristic);
    }

    @Override
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        izt.this.a(i);
    }
};
```

This callback implementation handles all aspects of the GATT connection lifecycle. The `onConnectionStateChange` method manages connection state transitions, while `onServicesDiscovered` handles the discovery of available services. The characteristic-related callbacks manage data transfer and notifications.

### Data Flow Management

The BLE data flow is managed through a combination of read, write, and notification operations. The system implements a sophisticated data handling mechanism:

```java
// From cjl.java - Data flow management
public void write(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
    UUID serviceUUID = cez.ad;
    UUID characteristicUUID = cez.i;
    
    // Determine the appropriate service and characteristic UUIDs based on the task type
    BleTaskQueueUtil.TaskType taskType = cjqVar.f();
    if (taskType == BleTaskQueueUtil.TaskType.SET_AGE) {
        serviceUUID = cez.ai;
        characteristicUUID = cez.f671a;
    } else if (taskType == BleTaskQueueUtil.TaskType.SET_GENDER) {
        serviceUUID = cez.ai;
        characteristicUUID = cez.f;
    } else if (taskType == BleTaskQueueUtil.TaskType.SET_HEIGHT) {
        serviceUUID = cez.ai;
        characteristicUUID = cez.h;
    } else if (taskType == BleTaskQueueUtil.TaskType.CLEAR_USER_INFO) {
        serviceUUID = cez.af;
        characteristicUUID = cez.k;
    }

    // Execute the write operation
    if (this.r) {
        this.u = iAsynBleTaskCallback;
        boolean isWriteSuccess = b(cjqVar, serviceUUID.toString(), characteristicUUID.toString());
        LogUtil.a("PluginDevice_WspMeasureController", "isWriteSuccess:", Boolean.valueOf(isWriteSuccess));
    } else {
        BluetoothGatt gatt = this.b;
        if (gatt == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "write mBluetoothGatt is null");
            return;
        }

        BluetoothGattService service = gatt.getService(serviceUUID);
        if (service == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "write gattService is null");
            return;
        }

        BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUUID);
        if (characteristic == null) {
            LogUtil.h("PluginDevice_WspMeasureController", "write gattCharacteristic is null");
            return;
        }

        this.u = iAsynBleTaskCallback;
        byte[] data = cjqVar.c();
        boolean isSuccess = FM_(characteristic, data);
        LogUtil.a("PluginDevice_WspMeasureController", "Write key:", taskType.toString(), " isSuccess:", Boolean.valueOf(isSuccess));
    }
}
```

This implementation demonstrates the complex data flow management in BLE communication. The system:
1. Determines the appropriate service and characteristic UUIDs based on the task type
2. Handles both synchronous and asynchronous write operations
3. Manages error conditions and logging
4. Provides callback mechanisms for operation completion

### Error Handling and Recovery

The BLE implementation includes robust error handling and recovery mechanisms:

```java
// From izt.java - Error handling
public void bDJ_(BluetoothGatt bluetoothGatt, int i, int i2) {
    LogUtil.c("EcologyDevice_MuMuMeasureController", "doConnectionStateChange status:", Integer.valueOf(i));
    if (i2 == 2) {
        try {
            this.d = bluetoothGatt;
            this.k = 2;
            bluetoothGatt.discoverServices();
            return;
        } catch (SecurityException e2) {
            LogUtil.b("EcologyDevice_MuMuMeasureController", "doConnectionStateChange SecurityException:", ExceptionUtils.d(e2));
            return;
        }
    }
    if (i2 == 0) {
        this.k = 0;
        cleanup();
        j();
        return;
    }
    LogUtil.a("EcologyDevice_MuMuMeasureController", "doConnectionStateChange other newState:", Integer.valueOf(i2));
}
```

This code shows how the system handles connection state changes and implements recovery mechanisms. When a connection is lost (state 0), the system:
1. Updates the connection state
2. Performs cleanup operations
3. Attempts to reestablish the connection

The implementation also includes comprehensive logging to track connection state changes and potential issues.

### Protocol Version Management

The BLE implementation supports multiple protocol versions and includes mechanisms for version detection and handling:

```java
// From izp.java - Protocol version handling
private void l(DeviceInfo deviceInfo, byte[] bArr) {
    String d = blq.d(bArr);
    LogUtil.c("BTDeviceSendCommandUtil", "Notification info: ", d);
    
    // Protocol version handling
    if (c(bArr)) {
        bArr = iyt.a(bArr[1], bArr[2], bArr[3], blq.a(d));
        blt.d("BTDeviceSendCommandUtil", bArr, "after V1--->V2 Notification info: ");
    }
    
    // Data processing and forwarding
    if (bArr != null) {
        this.y.onDataReceived(deviceInfo, bArr.length, bArr);
    } else {
        LogUtil.a("BTDeviceSendCommandUtil", "0xA0200008", "notificationData is null.");
    }
}
```

This code demonstrates how the system handles different protocol versions. When receiving data, it:
1. Checks the protocol version
2. Converts data between versions if necessary
3. Forwards the processed data to the appropriate handlers

The protocol version management ensures compatibility with different device generations and firmware versions.

## Bluetooth Classic Communication Protocol

The Bluetooth Classic protocol implementation provides support for traditional Bluetooth devices that require higher bandwidth or legacy support. Let's examine the implementation details.

### Device Discovery and Connection

The Classic Bluetooth implementation is handled through the `ceu` (ClassicDevice) class, which extends `MeasurableDevice`. The discovery and connection process is implemented as follows:

```java
// From ceu.java - Classic Bluetooth device implementation
public class ceu extends MeasurableDevice {
    protected BluetoothDevice b;
    private IDeviceEventHandler c;
    private cew e;

    private ceu(BluetoothDevice bluetoothDevice) {
        LogUtil.a("ClassicDevice", "ClassicDevice is constructed");
        this.b = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bluetoothDevice.getAddress());
        this.e = new cew(this);
    }

    @Override
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        this.c = iDeviceEventHandler;
        this.e.a(iDeviceEventHandler);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            cpp.a().registerReceiver(this.e, intentFilter);
        } catch (SecurityException e2) {
            LogUtil.b("ClassicDevice", "doCreateBond SecurityException:", ExceptionUtils.d(e2));
        }
        return Ei_(this.b);
    }
}
```

This implementation shows how Classic Bluetooth devices are initialized and bonded. The system:
1. Creates a device instance with the provided Bluetooth device
2. Sets up event handlers for bond state changes
3. Initiates the bonding process

### Data Transfer Implementation

The data transfer in Classic Bluetooth is implemented using Bluetooth sockets. The system uses a dedicated thread for data transfer:

```java
// From bhv.java - Data transfer implementation
public void rN_(final BluetoothSocket bluetoothSocket, final int i) {
    synchronized (e) {
        // Clean up any existing connection
        if (this.l != null) {
            this.l.c();
            this.l = null;
        }
        
        // Initialize new data transfer thread
        a aVar2 = new a(bluetoothSocket, i);
        this.l = aVar2;
        
        // Set up error handling
        aVar2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable th) {
                LogUtil.a("InoperableBrPhysicalService", "data transfer thread occur uncaughtException.");
                synchronized (bhv.e) {
                    // Attempt recovery on failure
                    if (bhv.this.l != null) {
                        bhv.this.l.c();
                        bhv.this.l = null;
                    }
                    bhv.this.l = new a(bluetoothSocket, i);
                    bhv.this.l.start();
                }
            }
        });
        this.l.start();
    }
}
```

The data transfer thread implementation shows how the system handles continuous data transfer:

```java
// From bhv.java - Data transfer thread
class a extends Thread {
    private final BluetoothSocket f380a;
    private boolean b;
    private final InputStream c;
    private final OutputStream d;
    private String g;
    private bkk h;
    private int i;

    private a(BluetoothSocket bluetoothSocket, int i) {
        this.f380a = bluetoothSocket;
        this.i = i;
        this.g = "InoperableBrPhysicalService" + i;
        LogUtil.c("InoperableBrPhysicalService", "Start DataTransferThread2.mSocketChannel ", Integer.valueOf(this.i), "mTag: ", this.g);
        OutputStream outputStream = null;
        try {
            InputStream inputStream = bluetoothSocket.getInputStream();
            try {
                outputStream = bluetoothSocket.getOutputStream();
            } catch (IOException unused) {
                LogUtil.e(this.g, "Get Input Stream Handle exception");
                this.c = inputStream;
                this.d = outputStream;
                bkk bkkVar = new bkk(bhv.this.mDeviceInfo, bhv.this.mMessageReceiveCallback, i);
                this.h = bkkVar;
                bkkVar.start();
            }
        } catch (IOException unused2) {
            inputStream = null;
        }
        this.c = inputStream;
        this.d = outputStream;
        bkk bkkVar2 = new bkk(bhv.this.mDeviceInfo, bhv.this.mMessageReceiveCallback, i);
        this.h = bkkVar2;
        bkkVar2.start();
    }
}
```

This implementation demonstrates how the system:
1. Creates input and output streams for data transfer
2. Sets up message handling callbacks
3. Manages the data transfer thread lifecycle

### Connection State Management

The Classic Bluetooth implementation includes sophisticated connection state management:

```java
// From cew.java - Connection state management
class cew extends BroadcastReceiver {
    private BluetoothHeadset f668a;
    private IDeviceEventHandler b;
    private BluetoothDevice c;
    private ceu d;
    private BluetoothDevice e;
    private BluetoothProfile.ServiceListener h = new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogUtil.c("ClassicDeviceHelper", "ClassicDevice onServiceConnected");
            if (i == 1) {
                LogUtil.c("ClassicDeviceHelper", "ClassicDevice onServiceConnected HEADSET");
                if (bluetoothProfile instanceof BluetoothHeadset) {
                    cew.this.f668a = (BluetoothHeadset) bluetoothProfile;
                }
                cew cewVar = cew.this;
                LogUtil.c("ClassicDeviceHelper", "ClassicDevice connectHsp returnValue is ", Boolean.valueOf(cewVar.Ep_(cewVar.f668a, cew.this.e)));
            }
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, cew.this.f668a);
        }

        @Override
        public void onServiceDisconnected(int i) {
            if (i == 1) {
                cew.this.f668a = null;
            }
        }
    };
}
```

This code shows how the system:
1. Manages Bluetooth profile connections
2. Handles service connection and disconnection events
3. Maintains state information for connected devices

### Error Handling and Recovery

The Classic Bluetooth implementation includes robust error handling mechanisms:

```java
// From ceu.java - Error handling
@Override
public boolean doRemoveBond() {
    BluetoothDevice bluetoothDevice = this.b;
    if (bluetoothDevice == null) {
        LogUtil.h("ClassicDevice", "doRemoveBond mDevice is null");
        return false;
    }
    updateDeviceUsedTime(2, bluetoothDevice.getAddress());
    if (this.b.getBondState() == 12) {
        try {
            Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
            Boolean bool = method.invoke(this.b, new Object[0]) instanceof Boolean ? (Boolean) method.invoke(this.b, new Object[0]) : null;
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LogUtil.b("ClassicDevice", "doRemoveBond ", ExceptionUtils.d(e));
        }
    }
    return false;
}
```

This implementation demonstrates how the system:
1. Handles bond removal operations
2. Manages device state transitions
3. Implements error recovery mechanisms
4. Maintains detailed logging for debugging

The Classic Bluetooth implementation provides a robust foundation for traditional Bluetooth device support, with comprehensive error handling and state management capabilities. 