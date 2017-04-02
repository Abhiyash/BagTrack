package com.example.abhiyash.bagtrack;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Abhiyash on 01-Apr-17.
 */

public class NewService extends Service {
    SharedPreferences sp;
    Firebase fb;
    String s1,s2;
    com.firebase.client.Query q;
    public NewService()
    {

    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        super.onCreate();
        Firebase.setAndroidContext(this);
        fb=new Firebase("https://hackathonapp-52b62.firebaseio.com/ScanningHistory");
        sp=getSharedPreferences("My",MODE_PRIVATE);
        s1=sp.getString("Ticket_id","");
        s2=sp.getString("Baggage_id","");
        Toast.makeText(this, s1+"/"+s2, Toast.LENGTH_SHORT).show();



        try {
            q = fb.orderByKey();
            q.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Toast.makeText(MyService.this, "OnchildAdded", Toast.LENGTH_SHORT).show();
                    /*for (DataSnapshot sn : dataSnapshot.getChildren()) {
                       WorkArey2 wa2=dataSnapshot.getValue(WorkArey2.class);
                       String s4 = wa2.getBag_id();
                        if (s4.equals(s2)) {
                            String s3 = wa2.getScanner_id();
                            if (s3.equals("21")) {
                                //  Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                // c1.setChecked(true);
                            }
                            if (s3.equals("201")) {
                                //Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                //c2.setChecked(true);
                                // c1.isSelected(true);
                            }
                            if (s3.equals("7")) {
                                //Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                //c3.setChecked(true);
                                // c1.isSelected(true);
                            }

                        }

                    }*/
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //Toast.makeText(MyService.this, "Onchildchanged", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot sn : dataSnapshot.getChildren()) {

                        WorkArey2 wa2=dataSnapshot.getValue(WorkArey2.class);

                        String s4 = wa2.bag_id;
                        if (s4.equals(s2)) {

                            String s3 = wa2.scanner_id;
                            if (s3.equals("21")) {
                                //  c1.setChecked(true);
                                //Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                Intent it23 = new Intent(NewService.this, Tb1.class);
                                PendingIntent pd = PendingIntent.getActivities(NewService.this, 123, new Intent[]{it23}, 0);
                                NotificationCompat.Builder b = new NotificationCompat.Builder(NewService.this);
                                b.setTicker("Update for your baggage");
                                b.setSmallIcon(R.drawable.checked);
                                b.setContentTitle("Bag_Track Notifications");
                                b.setAutoCancel(true);
                                b.setContentIntent(pd);
                                Notification nf = b.build();
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(1, nf);
                                //finish();
                                // c1.isSelected(true);
                            }
                            if (s3.equals("201")) {

                                Intent it23 = new Intent(NewService.this, Tb1.class);
                                PendingIntent pd = PendingIntent.getActivities(NewService.this, 123, new Intent[]{it23}, 0);
                                NotificationCompat.Builder b = new NotificationCompat.Builder(NewService.this);
                                b.setTicker("Update for your baggage");
                                b.setSmallIcon(R.drawable.checked);
                                b.setContentTitle("Bag_Track Notifications");
                                b.setAutoCancel(true);
                                b.setContentIntent(pd);
                                Notification nf = b.build();
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(1, nf);
                                //Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                //c2.setChecked(true);
                                // c1.isSelected(true);
                            }
                            if (s3.equals("7")) {

                                Intent it23 = new Intent(NewService.this, Tb1.class);
                                PendingIntent pd = PendingIntent.getActivities(NewService.this, 123, new Intent[]{it23}, 0);
                                NotificationCompat.Builder b = new NotificationCompat.Builder(NewService.this);
                                b.setTicker("Update for your baggage");
                                b.setSmallIcon(R.drawable.checked);
                                b.setContentTitle("Bag_Track Notifications");
                                b.setAutoCancel(true);
                                b.setContentIntent(pd);
                                Notification nf = b.build();
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(1, nf);
                                // Toast.makeText(Tb2.this, s2+""+s3, Toast.LENGTH_SHORT).show();
                                //   c3.setChecked(true);
                                // c1.isSelected(true);
                            }

                        }
                        //   Toast.makeText(Tb2.this, fb13.getBaggage_id()+","+fb13.getScanner_id(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(NewService.this, "" + e, Toast.LENGTH_SHORT).show();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
