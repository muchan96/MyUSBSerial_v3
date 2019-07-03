package com.example.myusbserial_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        UsbSerialDriver usb = UsbSerialProber.acquire(manager);
        if (usb != null) {
            try {
                usb.open();
                usb.setBaudRate(9600);
                start_read_thread(); // シリアル通信を読むスレッドを起動
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}