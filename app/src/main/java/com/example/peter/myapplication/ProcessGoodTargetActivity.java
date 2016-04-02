package com.example.peter.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2016/3/29.
 */
public class ProcessGoodTargetActivity extends AppCompatActivity {

    private ListView goodTargetlistView;
    private TargetDAO targetDAO;
    private List<TargetEntity> goodTargetList;
    private TargetAdapter targetAdapter;
    private TargetEntity selectTargetEntity;
    private ItemDAO itemDAO;
    private Item userItem;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_good_target);

        userItem = (Item) getIntent().getSerializableExtra("userItem");
        if(userItem == null){
            Toast.makeText(this, "取得使用者資料失敗", Toast.LENGTH_LONG).show();
            finish();
        }

        targetDAO = new TargetDAO(getApplicationContext());
        itemDAO = new ItemDAO(getApplicationContext());


        processViews();

        goodTargetList = getGoodTargetList();

//        int layoutId = android.R.layout.simple_list_item_1;
        int layoutId = R.layout.single_target;
        targetAdapter = new TargetAdapter(this, layoutId, goodTargetList);
        goodTargetlistView.setAdapter(targetAdapter);
        goodTargetlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
//                ListView listView = (ListView) parent;
//                Toast.makeText(
//                        ProcessGoodTargetActivity.this, "ClickItem", Toast.LENGTH_LONG).show();
                //                selectTargetEntity = (TargetEntity) parent.getAdapter().getItem(position);
                selectTargetEntity = (TargetEntity) parent.getItemAtPosition(position);
                startActivityForResult(
                        new Intent(ProcessGoodTargetActivity.this, TargetActionMenuActivity.class), 0);
            }
        });
    }

    private void processViews() {
        goodTargetlistView = (ListView) findViewById(R.id.goodTargetListView);

    }

    public void clickGoodTarget(View view) {
//        Toast.makeText(this, "執行Target", Toast.LENGTH_LONG).show();
        startActivityForResult(
                new Intent(this, TargetActionMenuActivity.class), 0);
    }


    public ArrayList<TargetEntity> getGoodTargetList() {
        return targetDAO.getGoodTargetList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 如果被啟動的Activity元件傳回確定的結果
        if (resultCode == Activity.RESULT_OK) {
//            Toast.makeText(this, "執行Target : " + selectTargetEntity.getTargetName(), Toast.LENGTH_LONG).show();
            userItem.setUserPoint(userItem.getUserPoint() + selectTargetEntity.getPoint());
            itemDAO.update(userItem);
        }
    }

    public void done(View view){
        Intent result = getIntent();
        result.putExtra("userItem", userItem);
        setResult(Activity.RESULT_OK, result);
        finish();
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "ProcessGoodTarget Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.peter.myapplication/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "ProcessGoodTarget Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.peter.myapplication/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
}
