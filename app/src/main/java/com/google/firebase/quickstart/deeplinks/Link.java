package com.google.firebase.quickstart.deeplinks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class Link extends AppCompatActivity {
    EditText et_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldactivity_link);
        et_value = (EditText) findViewById(R.id.et_value1);
//        https://n632p.app.goo.gl/8KTE
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://www.appsbee.com/"))
                .setDynamicLinkDomain("n632p.app.goo.gl")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.google.firebase.quickstart.deeplinks").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();

        et_value.setText(""+dynamicLinkUri);
        System.out.println("!!dynamicLinkUri==="+dynamicLinkUri);
    }
}
