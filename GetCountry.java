// Extension Author - Nisarga Adhikary (hello@nisarga.me)

package xyz.nisarga.GetCountry; // package name

// Imports
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.*;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.annotations.DesignerComponent;
import java.net.*;
import java.util.ArrayList;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.*;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.net.*;
import java.io.*;
import java.util.*;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyManager.*;

@DesignerComponent(version = 1, // designer component 
    description = "GetCountry Extension By Nisarga Adhikary." , // extension description
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "images/extension.png") // extension icon
@SimpleObject(external = true)
public class GetCountry extends AndroidNonvisibleComponent {
    private ComponentContainer container;
    private PackageManager pm;
    private Context context;
    public GetCountry(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        this.context=container.$context();
    }

    @SimpleFunction(description = "Get Country Of User")
    public String GetUserCountry() {
        try {
          final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
          final String simCountry = tm.getSimCountryIso();
          if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
            return simCountry.toLowerCase(Locale.US);
          }
          else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
            String networkCountry = tm.getNetworkCountryIso();
            if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
              return networkCountry.toLowerCase(Locale.US);
            }
          }
        }
        catch (Exception e) { }
        return null;
      }

      @SimpleEvent()
    public void ErrorOccurred(String errorMessage){
        EventDispatcher.dispatchEvent(this,"ErrorOccurred",errorMessage);
    }
}

