package com.zyou.intentshare;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnShareChooser;
    private Button btnShareToSina;
    private Button btnShareToWeixin;
    private Button btnShareToWeinxinCircle;
    private Button btnShareToQQ;
    private Button btnShareToQQZone;
    private Button btnShareToEmail;
    private Button btnShareToSMS;


    private final String SINA = "com.sina.weibo";
    private final String WEINXIN = "com.tencent.mm";
    private final String WEINXIN_CIRCLE = "com.weixin.circel";
    private final String QQ = "com.tencent.mobileqq";
    private final String QQ_ZONE = "com.qzone";
    private final String EMAIL = "com.android.email";
    private final String SMS = "com.android.mms";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShareChooser = (Button) findViewById(R.id.btn_share_chooser);
        btnShareToSina = (Button) findViewById(R.id.btn_share_to_sina);
        btnShareToWeixin = (Button) findViewById(R.id.btn_share_to_weixin);
        btnShareToWeinxinCircle = (Button) findViewById(R.id.btn_share_to_weixin_circle);
        btnShareToQQ = (Button) findViewById(R.id.btn_share_to_qq);
        btnShareToQQZone = (Button) findViewById(R.id.btn_share_to_qq_zone);
        btnShareToEmail = (Button) findViewById(R.id.btn_share_to_email);
        btnShareToSMS = (Button) findViewById(R.id.btn_share_to_sms);


        btnShareChooser.setOnClickListener(this);
        btnShareToSina.setOnClickListener(this);
        btnShareToWeixin.setOnClickListener(this);
        btnShareToWeinxinCircle.setOnClickListener(this);
        btnShareToQQ.setOnClickListener(this);
        btnShareToQQZone.setOnClickListener(this);
        btnShareToEmail.setOnClickListener(this);
        btnShareToSMS.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btn_share_chooser){
            shareToChooser();
        }
        if(id==R.id.btn_share_to_sina){
            shareTo(SINA);
        }
        if(id==R.id.btn_share_to_weixin){
            shareTo(WEINXIN);
         }
        if(id==R.id.btn_share_to_weixin_circle){
            shareTo(WEINXIN_CIRCLE);
        }
        if(id==R.id.btn_share_to_qq){
            shareTo(QQ);
        }
        if(id==R.id.btn_share_to_qq_zone){
            shareTo(QQ_ZONE);
        }
        if(id==R.id.btn_share_to_email){
            shareTo(EMAIL);
        }
        if(id==R.id.btn_share_to_sms){
            shareTo(SMS);
        }

    }


    private void shareToChooser() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "你好 ");
        intent.putExtra(Intent.EXTRA_TITLE, "我是标题");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "请选择"));
    }

    private void shareTo(String packageName) {
        /*if (!checkInstallation(packageName)) {
            Toast.makeText(this, "没有安装此程序", Toast.LENGTH_SHORT).show();
            drumpToGoogle(packageName);
            return;
        }*/
        if(packageName.equals(WEINXIN_CIRCLE)){
            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.setComponent(comp);

            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            startActivity(intent);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "你好 ");
        intent.setPackage(packageName);
        startActivity(intent);
    }

    /**
     * 跳转至Google市场
     *
     * @param packageName 应用的包名
     */
    private void drumpToGoogle(final String packageName) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("是否去Google市场下载该应用？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("market://details?id=" + packageName);
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 判断是否安装该应用
     *
     * @param packageName 应用的包名
     */
    public boolean checkInstallation(String packageName) {
        try {
            this.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
