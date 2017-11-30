/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.quickstart.deeplinks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.pddstudio.urlshortener.URLShortener;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String DEEP_LINK_URL = "http://www.google.com/..........";
    String result = "";
    TextView link_view_send;

    // [START on_create]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // [START_EXCLUDE]
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_main);
        link_view_send = (TextView) findViewById(R.id.link_view_send);
        // Validate that the developer has set the app code.
        validateAppCode();

        // Create a deep link and display it in the UI
        final Uri deepLink = buildDeepLink(Uri.parse(DEEP_LINK_URL), 0);

        try {
            result = java.net.URLDecoder.decode(String.valueOf(deepLink), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        URLShortener.shortUrl(result, new URLShortener.LoadingCallback() {
            @Override
            public void startedLoading() {
            }

            @Override
            public void finishedLoading(@Nullable String shortUrl) {
                if (shortUrl != null) {
                    result = shortUrl;
                    link_view_send.setText(result);
                }
            }
        });


        // Share button click listener
        findViewById(R.id.button_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDeepLink(link_view_send.getText().toString());
            }
        });

    }
    // [END on_create]

    /**
     * Build a Firebase Dynamic Link.
     * https://firebase.google.com/docs/dynamic-links/android/create#create-a-dynamic-link-from-parameters
     *
     * @param deepLink   the deep link your app will open. This link must be a valid URL and use the
     *                   HTTP or HTTPS scheme.
     * @param minVersion the {@code versionCode} of the minimum version of your app that can open
     *                   the deep link. If the installed app is an older version, the user is taken
     *                   to the Play store to upgrade the app. Pass 0 if you do not
     *                   require a minimum version.
     * @return a {@link Uri} representing a properly formed deep link.
     */
    @VisibleForTesting
    public Uri buildDeepLink(@NonNull Uri deepLink, int minVersion) {
        String domain = getString(R.string.app_code1) + ".app.goo.gl";

        ///////////////////////////





        /////////////////////////////


        // Set dynamic link parameters:
        //  * Domain (required)
        //  * Android Parameters (required)
        //  * Deep link
        // [START build_dynamic_link]
        DynamicLink.Builder builder = FirebaseDynamicLinks.getInstance()
                .createDynamicLink()
                .setDynamicLinkDomain(domain)
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder("com.google.firebase.quickstart.deeplinks")
                                .setMinimumVersion(minVersion)
                                .build())
                .setLink(deepLink)
                ;

        // Build the dynamic link
//        DynamicLink link = builder.buildDynamicLink();
        DynamicLink link = builder.buildDynamicLink();
        // [END build_dynamic_link]

        // Return the dynamic link as a URI
        return link.getUri();
    }

    private void shareDeepLink(String deepLink) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Firebase Deep Link");
        intent.putExtra(Intent.EXTRA_TEXT, deepLink);

        startActivity(intent);
    }

    private void validateAppCode() {
        String appCode = getString(R.string.app_code1);
        if (appCode.contains("YOUR_app_code")) {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid Configuration")
                    .setMessage("Please set your app code in app/build.gradle")
                    .setPositiveButton(android.R.string.ok, null)
                    .create().show();
        }
    }
}
