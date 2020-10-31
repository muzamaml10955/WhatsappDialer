package com.awsixn.awh.whatsappdialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
    private boolean whatsappInstalledOrNot;
   String number;
    //=et.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editTextPhone);
        button=findViewById(R.id.whatsappbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = editText.getText().toString();



                boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                if (isWhatsappInstalled) {
                    Uri uri = Uri.parse("smsto:" + number);
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } else {
                   // Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("market://details?id=com.whatsapp");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goToMarket);

                }




            }
        });
    }
    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}