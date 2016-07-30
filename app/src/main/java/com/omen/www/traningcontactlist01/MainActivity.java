package com.omen.www.traningcontactlist01;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import rebus.permissionutils.PermissionManager;

public class MainActivity extends AppCompatActivity {
    private ListView mContactListview;
    private List<String> mContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContactListview = (ListView) findViewById(R.id._activity_main_contact_list);
        mContactList = new ArrayList<>();
        readContactInfo();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mContactList);
        mContactListview.setAdapter(adapter);
    }

    private void readContactInfo() {
        Cursor cursor = null;
        /*cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()){
            String displayName=cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
        }*/
        cursor = getContentResolver().query(
                Phone.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {

            String displayName = cursor.getString(
                    cursor.getColumnIndex(Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleResult(requestCode, permissions, grantResults);
    }
}
