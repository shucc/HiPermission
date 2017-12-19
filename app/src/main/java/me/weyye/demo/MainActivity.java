package me.weyye.demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_select_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocalPhoto();
            }
        });
        findViewById(R.id.btn_all_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyAllPermission();
            }
        });
    }

    private void selectLocalPhoto() {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.permission_cus_item_camera), R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.permission_cus_item_storage), R.drawable.permission_ic_storage));
        HiPermission.create(this)
                .title(getString(R.string.permission_cus_title))
                .permissions(permissionItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))
                .msg(getString(R.string.permission_cus_photo))
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "选择本地图片", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }

    private void applyAllPermission() {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.permission_cus_item_camera), R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.permission_cus_item_storage), R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, getString(R.string.permission_cus_item_phone), R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, getString(R.string.permission_cus_item_location), R.drawable.permission_ic_location));
        HiPermission.create(this)
                .title(getString(R.string.permission_cus_title))
                .permissions(permissionItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()))
                .style(R.style.CustomPermissionStyle)
                .msg(getString(R.string.permission_cus_all))
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "申请所有权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }
}
