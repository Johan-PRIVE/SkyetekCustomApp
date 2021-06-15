package main;


//import java.nio.charset.StandardCharsets;
import org.hid4java.*;
import org.hid4java.event.HidServicesEvent;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
/**
 * <p>Demonstrate the USB HID interface using a production Bitcoin Trezor</p>
 *
 * @since 0.0.1
 */
public class SkyetekConnector implements HidServicesListener {

    private static final Integer VENDOR_ID = 0x3eb;
    private static final Integer PRODUCT_ID = 0xffffaa55;
    private static final int PACKET_LENGTH = 65;
    public static final String SERIAL_NUMBER = null;
    HidServicesSpecification hidServicesSpecification;
    HidServices hidServices;
    String manufacturer = "SKYETEK INC";
    HidDevice hidDevice ;//= new HidDevice(null,null);
    
    public SkyetekConnector() 
    {
    	
    }

    
    public void executeExample() throws HidException {
    	
    	initHidServices();
    	//this.hidServices.shutdown();
    	
    	selector();
    	
    	//sendMessage();
    	
    	reader();
    	
        killer();

    }
    
    public void initHidServices() 
    {
    	// Configure to use custom specification
        this.hidServicesSpecification = new HidServicesSpecification();
        this.hidServicesSpecification.setAutoShutdown(true);
        this.hidServicesSpecification.setScanInterval(500);
        this.hidServicesSpecification.setPauseInterval(5000);
        this.hidServicesSpecification.setScanMode(ScanMode.SCAN_AT_FIXED_INTERVAL_WITH_PAUSE_AFTER_WRITE);
        
        // Get HID services using custom specification
        this.hidServices = HidManager.getHidServices(this.hidServicesSpecification);
        this.hidServices.addHidServicesListener(this);

        // Start the services
        System.out.println("Starting HID services.");
        this.hidServices.start();
    }
    
    public void selector() 
    {
    	System.out.println("Enumerating attached devices...");

        // Provide a list of attached devices
        for (HidDevice hidDeviceS : this.hidServices.getAttachedHidDevices()) {
            //System.out.println(hidDevice);
            //System.out.println(hidDevice.getProductId());
            if (hidDeviceS.getManufacturer().equals(this.manufacturer)) {
        		System.out.println(hidDeviceS.getProduct());
        		System.out.println(hidDeviceS.getProductId());
        		System.out.println(hidDeviceS);
        		this.hidDevice = hidDeviceS;
        	}
        }
    }

    public void reader() 
    {
    	 // Open the device device by Vendor ID and Product ID with wildcard serial number
        HidDevice hidDevice = this.hidServices.getHidDevice(VENDOR_ID, PRODUCT_ID, SERIAL_NUMBER);
        if (hidDevice != null) {
            // Consider overriding dropReportIdZero on Windows
            // if you see "The parameter is incorrect"
            // HidApi.dropReportIdZero = true;

            // Device is already attached and successfully opened so send message
            //sendMessage(hidDevice);
            byte[] arr = new byte[256];
            int read = hidDevice.read(arr);
            System.out.println(arr);
            System.out.println(read);
        }
    }
    
    public void killer() 
    {
    	//System.out.printf("Waiting 10s to demonstrate attach/detach handling. Watch for slow response after write if configured.%n");

        // Stop the main thread to demonstrate attach and detach events
        //sleepUninterruptibly(10, TimeUnit.SECONDS);
        System.out.println("shutdown ok");
        // Shut down and rely on auto-shutdown hook to clear HidApi resources
        this.hidServices.shutdown();
    }
    
    public String specialReader() 
    {
    	int val ;
    	byte data[] = new byte[PACKET_LENGTH];
    	String text = "";
        //System.out.println("Read:");
        // Prepare to read a single data packet
        boolean moreData = true;
        while (moreData) {

            // This method will now block for 500ms or until data is read
            int timer= 100;
            //System.out.println("Waiting "+timer+" until message or stop");
            val = this.hidDevice.read(data, timer);
            
            switch (val) {
                case -1:
                    System.err.println(this.hidDevice.getLastErrorMessage());
                    //text= "";
                    break;
                case 0:
                    moreData = false;
                    System.out.print(".");
                    //text = "";
                    break;
                default:
                    //System.out.print("< [");
              
                   // for (byte b : data) {
                        //System.out.printf(" %02x", b);
                    	//System.out.print(b + " ");
                    	//response[b]=(b);
                    //}
                    //System.out.println("]");
                    //System.out.println(data.toString());
                    //System.out.println(bytesToHex(data));
                    //System.out.println(hexToAscii(bytesToHex(data)));
                    text = hexToAscii(bytesToHex(data));
                    moreData = false;
                    break;
            }
        }
        return text;
    }
    
	@SuppressWarnings("exports")
	public void hidDeviceAttached(HidServicesEvent event) {
        System.out.println("Device attached: " + event);
        // Add serial number when more than one device with the same
        // vendor ID and product ID will be present at the same time
        if (event.getHidDevice().isVidPidSerial(VENDOR_ID, PRODUCT_ID, null)) {
            //sendMessage(event.getHidDevice());
        }
    }

    @SuppressWarnings("exports")
	public void hidDeviceDetached(HidServicesEvent event) {
        System.err.println("Device detached: " + event);
    }

    @SuppressWarnings("exports")
	public void hidFailure(HidServicesEvent event) {
        System.err.println("HID failure: " + event);
    }
    
    public void sendMessage(byte[] message) {
    	//decrypt message
    	/*System.out.print("[ ");
        for (byte z : message) {
            //System.out.printf(" %02x", b);
        	System.out.print(z + " ");
        }
        System.out.println("]");
        
        System.out.print("[ ");
        for (byte z : message) {
            System.out.printf(" %02x", z);
        	
        }
        System.out.println("]");
        
        System.out.println("show Byte message:");
        System.out.println(new String(message));
        */
        
    	//System.out.println("Write:");
        // Ensure device is open after an attach/detach event
        if (!hidDevice.isOpen()) {
            hidDevice.open();
        }
        
        //send message
        int val = this.hidDevice.write(message, message.length , (byte) 0x00);//... ,PACKET_LENGTH, ...
        //verify message is sent
        if (val >= 0) {
            //System.out.println("> [" + val + "]");
        } else {
            System.err.println(this.hidDevice.getLastErrorMessage());
        }

    }
    
    
    /**
     * Invokes {@code unit.}{@link java.util.concurrent.TimeUnit#sleep(long) sleep(sleepFor)}
     * uninterruptibly.
     */
    public static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(sleepFor);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    // TimeUnit.sleep() treats negative timeouts just like zero.
                    NANOSECONDS.sleep(remainingNanos);
                    return;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @SuppressWarnings("exports")
	public void RFIDReader (HidDevice hidDevice) {
    	// Configure to use custom specification
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        hidServicesSpecification.setAutoShutdown(true);
        hidServicesSpecification.setScanInterval(500);
        hidServicesSpecification.setPauseInterval(5000);
        hidServicesSpecification.setScanMode(ScanMode.SCAN_AT_FIXED_INTERVAL_WITH_PAUSE_AFTER_WRITE);

        // Get HID services using custom specification
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
        hidServices.addHidServicesListener(this);

        // Start the services
        System.out.println("Starting HID services.");
        hidServices.start();
                
    	//String manufacturer = ("SKYETEK INC");
    	
    	if (hidDevice.getManufacturer().equals(this.manufacturer)) {
    		System.out.println(hidDevice.getProduct());
    		System.out.println(hidDevice.getProductId());
    	}
    	//hidServices.shutdown();
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        
        return output.toString();
    }
}
