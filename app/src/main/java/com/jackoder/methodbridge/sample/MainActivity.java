package com.jackoder.methodbridge.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.jackoder.methodbridge.MethodBridge;
import com.jackoder.methodbridge.ann.ExportMethod;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MethodBridge.registerAnnotatedClass(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, new ContentFragment());
        transaction.commit();

        /*boolean retBool = MethodBridge.call("emptyMethod");
        boolean[] retBools = MethodBridge.call("emptyMethod");

        String retString = MethodBridge.call("emptyMethod");
        String[] retStrings = MethodBridge.call("emptyMethod");

        byte retByte = MethodBridge.call("emptyMethod");
        byte[] retByte2 = MethodBridge.call("emptyMethod");

        char retChar = MethodBridge.call("emptyMethod");
        char[] retChars = MethodBridge.call("emptyMethod");

        short retShort = MethodBridge.call("emptyMethod");
        short[] retShorts = MethodBridge.call("emptyMethod");

        int retInt = MethodBridge.call("emptyMethod");
        int[] retInts = MethodBridge.call("emptyMethod");

        long retLong = MethodBridge.call("emptyMethod");
        long[] retLongs = MethodBridge.call("emptyMethod");

        float retFloat = MethodBridge.call("emptyMethod");
        float[] retFloats = MethodBridge.call("emptyMethod");

        double retDouble = MethodBridge.call("emptyMethod");
        double[] retDoubles = MethodBridge.call("emptyMethod");*/
    }

    @ExportMethod(name = "print")
    public void printLog() {
        Log.e("MainActivity", "call printLog success");
    }

    @ExportMethod(name = "ShareData")
    public String getShareData() {
        return "Share Data";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MethodBridge.unregisterAnnotatedClass(this);
    }

    public void toast(View view) {
        MethodBridge.call("Toast", "From MainActivity");
    }
}
