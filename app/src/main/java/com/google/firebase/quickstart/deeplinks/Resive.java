package com.google.firebase.quickstart.deeplinks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteReferral;

public class Resive extends AppCompatActivity {
    private static final String TAG = "Resive";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldactivity_resive);
        textView = ((TextView) findViewById(R.id.link_view_receive));
//        FirebaseDynamicLinks.getInstance()
//                .getDynamicLink(getIntent())
//                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
//                    @Override
//                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
//                        // Get deep link from result (may be null if no link is found)
//                        Uri deepLink = null;
//                        if (pendingDynamicLinkData != null) {
//                            deepLink = pendingDynamicLinkData.getLink();
//                        }
//
//
//                        // Handle the deep link. For example, open the linked
//                        // content, or apply promotional credit to the user's
//                        // account.
//                        // ...
//
//                        // [START_EXCLUDE]
//                        // Display deep link in the UI
//                        if (deepLink != null) {
//                            Snackbar.make(findViewById(android.R.id.content),
//                                    "Found deep link!", Snackbar.LENGTH_LONG).show();
//
//                            ((TextView) findViewById(R.id.link_view_receive))
//                                    .setText(deepLink.toString());
//                        } else {
//                            Log.d(TAG, "getDynamicLink: no link found");
//                        }
//                        // [END_EXCLUDE]
//                    }
//                })
//                .addOnFailureListener(this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "getDynamicLink:onFailure", e);
//                    }
//                });
        // [END get_deep_link]
        textView.setText("");
        String invitationId = AppInviteReferral.getInvitationId(getIntent());
        String deepLink = AppInviteReferral.getDeepLink(getIntent());

        textView .setText(invitationId+"-----------"+deepLink.toString());
    }
}
