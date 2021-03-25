package com.example.atmfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        LocationListener,
        CompoundButton.OnCheckedChangeListener{

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    CheckBox changemap;
    int map_style;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        changemap = findViewById(R.id.CB_changemap);
        changemap.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if(changemap.isChecked()==true)
        {
            changemap.setButtonDrawable(R.drawable.check_true);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        if(changemap.isChecked()==false)
        {
            changemap.setButtonDrawable(R.drawable.check_false);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        onCheckedChanged(changemap,true);
        onCheckedChanged(changemap,false);
        Intent it = getIntent();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

            int choose = it.getIntExtra("bank_choose", 0);
            switch (choose) {
                case 1: //臺灣銀行
                    BOT();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 5 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 2: //臺灣土地銀行
                    LBOT();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 5 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 3: //合作金庫商業銀行
                    TCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 6 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 4: //第一商業銀行
                    FCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 2 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 5: //華南商業銀行
                    HNCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 3 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 6: //彰化商業銀行
                    CHCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 3 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 7: //上海商業儲蓄銀行
                    TSCSB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 8: //台北富邦商業銀行
                    TFCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 2 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 9: //國泰世華商業銀行
                    CUB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 30 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 10: //高雄銀行
                    BOK();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 11: //兆豐國際商業銀行
                    MICB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 3 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 12: //台灣中小企業銀行
                    TBB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 3 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 13: //新光商業銀行
                    SKCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 6 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 14: //陽信商業銀行
                    SB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 2 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 15: //三信商業銀行
                    CCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 16: //聯邦商業銀行
                    UBOT();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 6 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 17: //元大商業銀行
                    YCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 7 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 18: //永豐商業銀行
                    BS();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 2 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 19: //玉山商業銀行
                    ESCB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 5 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 20: //凱基商業銀行
                    KGB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 21: //台新國際商業銀行
                    TIB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 18 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 22: //日盛國際商業銀行
                    JICB();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 23: //中國信託
                    CTBC();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 42 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 24: //屏東市農會
                    PTFA();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 1 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 25: //中華郵政
                    CWP();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 23 + " 處", Toast.LENGTH_LONG).show();
                    break;
                case 26:
                    Total();
                    Toast.makeText(this, "符合您搜尋的地點共有： " + 179 + " 處", Toast.LENGTH_LONG).show();
                default:
                    break;
            }
        }
        mMap.getUiSettings().setZoomControlsEnabled(true); //右下角的放大縮小
        mMap.getUiSettings().setCompassEnabled(true); //指南針
    }

    public void Total() //全部
    {
        BOT();
        LBOT();
        TCB();
        FCB();
        HNCB();
        CHCB();
        TSCSB();
        TFCB();
        CUB();
        BOK();
        MICB();
        TBB();
        SKCB();
        SB();
        CCB();
        UBOT();
        YCB();
        BS();
        ESCB();
        KGB();
        TIB();
        JICB();
        CTBC();
        PTFA();
        CWP();
    }
    public void BOT() //臺灣銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[5];
        arrlng[0] = it.getDoubleExtra("BOT_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("BOT_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("BOT_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("BOT_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("BOT_location05_lng", 0);

        Double[] arrlat = new Double[5];
        arrlat[0] = it.getDoubleExtra("BOT_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("BOT_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("BOT_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("BOT_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("BOT_location05_lat", 0);

        String[] arrtitle = new String[5];
        arrtitle[0] = it.getStringExtra("BOT_location01_name");
        arrtitle[1] = it.getStringExtra("BOT_location02_name");
        arrtitle[2] = it.getStringExtra("BOT_location03_name");
        arrtitle[3] = it.getStringExtra("BOT_location04_name");
        arrtitle[4] = it.getStringExtra("BOT_location05_name");

        int i=0;
        for (i = 0; i < arrtitle.length; i++)
        {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];
            if (dbelng == arrlng[0])
            {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("臺灣銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
            else if (dbelng == arrlng[1])
            {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("臺灣銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
            else
            {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("臺灣銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void LBOT() //臺灣土地銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[5];
        arrlng[0] = it.getDoubleExtra("LBOT_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("LBOT_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("LBOT_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("LBOT_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("LBOT_location05_lng", 0);

        Double[] arrlat = new Double[5];
        arrlat[0] = it.getDoubleExtra("LBOT_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("LBOT_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("LBOT_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("LBOT_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("LBOT_location05_lat", 0);

        String[] arrtitle = new String[5];
        arrtitle[0] = it.getStringExtra("LBOT_location01_name");
        arrtitle[1] = it.getStringExtra("LBOT_location02_name");
        arrtitle[2] = it.getStringExtra("LBOT_location03_name");
        arrtitle[3] = it.getStringExtra("LBOT_location04_name");
        arrtitle[4] = it.getStringExtra("LBOT_location05_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("臺灣土地銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("臺灣土地銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void TCB() //合作金庫商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[6];
        arrlng[0] = it.getDoubleExtra("TCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("TCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("TCB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("TCB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("TCB_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("TCB_location06_lng", 0);

        Double[] arrlat = new Double[6];
        arrlat[0] = it.getDoubleExtra("TCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("TCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("TCB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("TCB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("TCB_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("TCB_location06_lat", 0);

        String[] arrtitle = new String[6];
        arrtitle[0] = it.getStringExtra("TCB_location01_name");
        arrtitle[1] = it.getStringExtra("TCB_location02_name");
        arrtitle[2] = it.getStringExtra("TCB_location03_name");
        arrtitle[3] = it.getStringExtra("TCB_location04_name");
        arrtitle[4] = it.getStringExtra("TCB_location05_name");
        arrtitle[5] = it.getStringExtra("TCB_location06_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[2] || dbelng == arrlng[3]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("合作金庫商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("合作金庫商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void FCB() //第一商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[2];
        arrlng[0] = it.getDoubleExtra("FCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("FCB_location02_lng", 0);

        Double[] arrlat = new Double[2];
        arrlat[0] = it.getDoubleExtra("FCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("FCB_location02_lat", 0);

        String[] arrtitle = new String[2];
        arrtitle[0] = it.getStringExtra("FCB_location01_name");
        arrtitle[1] = it.getStringExtra("FCB_location02_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("第一商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("第一商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void HNCB() //華南商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[3];
        arrlng[0] = it.getDoubleExtra("HNCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("HNCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("HNCB_location03_lng", 0);

        Double[] arrlat = new Double[3];
        arrlat[0] = it.getDoubleExtra("HNCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("HNCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("HNCB_location03_lat", 0);

        String[] arrtitle = new String[3];
        arrtitle[0] = it.getStringExtra("HNCB_location01_name");
        arrtitle[1] = it.getStringExtra("HNCB_location02_name");
        arrtitle[2] = it.getStringExtra("HNCB_location03_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[2]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("華南商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("華南商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void CHCB() //彰化商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[3];
        arrlng[0] = it.getDoubleExtra("CHCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("CHCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("CHCB_location03_lng", 0);

        Double[] arrlat = new Double[3];
        arrlat[0] = it.getDoubleExtra("CHCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("CHCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("CHCB_location03_lat", 0);

        String[] arrtitle = new String[3];
        arrtitle[0] = it.getStringExtra("CHCB_location01_name");
        arrtitle[1] = it.getStringExtra("CHCB_location02_name");
        arrtitle[2] = it.getStringExtra("CHCB_location03_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[1]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("彰化商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("彰化商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void TSCSB() //上海商業儲蓄銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("TSCSB_location01_lng", 0);

        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("TSCSB_location01_lat", 0);

        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("TSCSB_location01_name");
        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("上海商業儲蓄銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("上海商業儲蓄銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void TFCB() //台北富邦商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[2];
        arrlng[0] = it.getDoubleExtra("TFCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("TFCB_location02_lng", 0);

        Double[] arrlat = new Double[2];
        arrlat[0] = it.getDoubleExtra("TFCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("TFCB_location02_lat", 0);

        String[] arrtitle = new String[2];
        arrtitle[0] = it.getStringExtra("TFCB_location01_name");
        arrtitle[1] = it.getStringExtra("TFCB_location02_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台北富邦商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台北富邦商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void CUB() //國泰世華商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[30];
        arrlng[0] = it.getDoubleExtra("CUB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("CUB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("CUB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("CUB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("CUB_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("CUB_location06_lng", 0);
        arrlng[6] = it.getDoubleExtra("CUB_location07_lng", 0);
        arrlng[7] = it.getDoubleExtra("CUB_location08_lng", 0);
        arrlng[8] = it.getDoubleExtra("CUB_location09_lng", 0);
        arrlng[9] = it.getDoubleExtra("CUB_location10_lng", 0);
        arrlng[10] = it.getDoubleExtra("CUB_location11_lng", 0);
        arrlng[11] = it.getDoubleExtra("CUB_location12_lng", 0);
        arrlng[12] = it.getDoubleExtra("CUB_location13_lng", 0);
        arrlng[13] = it.getDoubleExtra("CUB_location14_lng", 0);
        arrlng[14] = it.getDoubleExtra("CUB_location15_lng", 0);
        arrlng[15] = it.getDoubleExtra("CUB_location16_lng", 0);
        arrlng[16] = it.getDoubleExtra("CUB_location17_lng", 0);
        arrlng[17] = it.getDoubleExtra("CUB_location18_lng", 0);
        arrlng[18] = it.getDoubleExtra("CUB_location19_lng", 0);
        arrlng[19] = it.getDoubleExtra("CUB_location20_lng", 0);
        arrlng[20] = it.getDoubleExtra("CUB_location21_lng", 0);
        arrlng[21] = it.getDoubleExtra("CUB_location22_lng", 0);
        arrlng[22] = it.getDoubleExtra("CUB_location23_lng", 0);
        arrlng[23] = it.getDoubleExtra("CUB_location24_lng", 0);
        arrlng[24] = it.getDoubleExtra("CUB_location25_lng", 0);
        arrlng[25] = it.getDoubleExtra("CUB_location26_lng", 0);
        arrlng[26] = it.getDoubleExtra("CUB_location27_lng", 0);
        arrlng[27] = it.getDoubleExtra("CUB_location28_lng", 0);
        arrlng[28] = it.getDoubleExtra("CUB_location29_lng", 0);
        arrlng[29] = it.getDoubleExtra("CUB_location30_lng", 0);

        Double[] arrlat = new Double[30];
        arrlat[0] = it.getDoubleExtra("CUB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("CUB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("CUB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("CUB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("CUB_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("CUB_location06_lat", 0);
        arrlat[6] = it.getDoubleExtra("CUB_location07_lat", 0);
        arrlat[7] = it.getDoubleExtra("CUB_location08_lat", 0);
        arrlat[8] = it.getDoubleExtra("CUB_location09_lat", 0);
        arrlat[9] = it.getDoubleExtra("CUB_location10_lat", 0);
        arrlat[10] = it.getDoubleExtra("CUB_location11_lat", 0);
        arrlat[11] = it.getDoubleExtra("CUB_location12_lat", 0);
        arrlat[12] = it.getDoubleExtra("CUB_location13_lat", 0);
        arrlat[13] = it.getDoubleExtra("CUB_location14_lat", 0);
        arrlat[14] = it.getDoubleExtra("CUB_location15_lat", 0);
        arrlat[15] = it.getDoubleExtra("CUB_location16_lat", 0);
        arrlat[16] = it.getDoubleExtra("CUB_location17_lat", 0);
        arrlat[17] = it.getDoubleExtra("CUB_location18_lat", 0);
        arrlat[18] = it.getDoubleExtra("CUB_location19_lat", 0);
        arrlat[19] = it.getDoubleExtra("CUB_location20_lat", 0);
        arrlat[20] = it.getDoubleExtra("CUB_location21_lat", 0);
        arrlat[21] = it.getDoubleExtra("CUB_location22_lat", 0);
        arrlat[22] = it.getDoubleExtra("CUB_location23_lat", 0);
        arrlat[23] = it.getDoubleExtra("CUB_location24_lat", 0);
        arrlat[24] = it.getDoubleExtra("CUB_location25_lat", 0);
        arrlat[25] = it.getDoubleExtra("CUB_location26_lat", 0);
        arrlat[26] = it.getDoubleExtra("CUB_location27_lat", 0);
        arrlat[27] = it.getDoubleExtra("CUB_location28_lat", 0);
        arrlat[28] = it.getDoubleExtra("CUB_location29_lat", 0);
        arrlat[29] = it.getDoubleExtra("CUB_location30_lat", 0);

        String[] arrtitle = new String[30];
        arrtitle[0] = it.getStringExtra("CUB_location01_name");
        arrtitle[1] = it.getStringExtra("CUB_location02_name");
        arrtitle[2] = it.getStringExtra("CUB_location03_name");
        arrtitle[3] = it.getStringExtra("CUB_location04_name");
        arrtitle[4] = it.getStringExtra("CUB_location05_name");
        arrtitle[5] = it.getStringExtra("CUB_location06_name");
        arrtitle[6] = it.getStringExtra("CUB_location07_name");
        arrtitle[7] = it.getStringExtra("CUB_location08_name");
        arrtitle[8] = it.getStringExtra("CUB_location09_name");
        arrtitle[9] = it.getStringExtra("CUB_location10_name");
        arrtitle[10] = it.getStringExtra("CUB_location11_name");
        arrtitle[11] = it.getStringExtra("CUB_location12_name");
        arrtitle[12] = it.getStringExtra("CUB_location13_name");
        arrtitle[13] = it.getStringExtra("CUB_location14_name");
        arrtitle[14] = it.getStringExtra("CUB_location15_name");
        arrtitle[15] = it.getStringExtra("CUB_location16_name");
        arrtitle[16] = it.getStringExtra("CUB_location17_name");
        arrtitle[17] = it.getStringExtra("CUB_location18_name");
        arrtitle[18] = it.getStringExtra("CUB_location19_name");
        arrtitle[19] = it.getStringExtra("CUB_location20_name");
        arrtitle[20] = it.getStringExtra("CUB_location21_name");
        arrtitle[21] = it.getStringExtra("CUB_location22_name");
        arrtitle[22] = it.getStringExtra("CUB_location23_name");
        arrtitle[23] = it.getStringExtra("CUB_location24_name");
        arrtitle[24] = it.getStringExtra("CUB_location25_name");
        arrtitle[25] = it.getStringExtra("CUB_location26_name");
        arrtitle[26] = it.getStringExtra("CUB_location27_name");
        arrtitle[27] = it.getStringExtra("CUB_location28_name");
        arrtitle[28] = it.getStringExtra("CUB_location29_name");
        arrtitle[29] = it.getStringExtra("CUB_location30_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[22]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("國泰世華商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("國泰世華商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void BOK() //高雄銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("BOK_location01_lng", 0);

        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("BOK_location01_lat", 0);

        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("BOK_location01_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("高雄銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("高雄銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void MICB() //兆豐國際商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[3];
        arrlng[0] = it.getDoubleExtra("MICB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("MICB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("MICB_location03_lng", 0);

        Double[] arrlat = new Double[3];
        arrlat[0] = it.getDoubleExtra("MICB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("MICB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("MICB_location03_lat", 0);

        String[] arrtitle = new String[3];
        arrtitle[0] = it.getStringExtra("MICB_location01_name");
        arrtitle[1] = it.getStringExtra("MICB_location02_name");
        arrtitle[2] = it.getStringExtra("MICB_location03_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[1]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("兆豐國際商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("兆豐國際商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void TBB() //台灣中小企業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[3];
        arrlng[0] = it.getDoubleExtra("TBB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("TBB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("TBB_location03_lng", 0);

        Double[] arrlat = new Double[3];
        arrlat[0] = it.getDoubleExtra("TBB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("TBB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("TBB_location03_lat", 0);

        String[] arrtitle = new String[3];
        arrtitle[0] = it.getStringExtra("TBB_location01_name");
        arrtitle[1] = it.getStringExtra("TBB_location02_name");
        arrtitle[2] = it.getStringExtra("TBB_location03_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[1]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台灣中小企業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台灣中小企業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void SKCB() //新光商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[6];
        arrlng[0] = it.getDoubleExtra("SKCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("SKCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("SKCB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("SKCB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("SKCB_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("SKCB_location06_lng", 0);

        Double[] arrlat = new Double[6];
        arrlat[0] = it.getDoubleExtra("SKCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("SKCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("SKCB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("SKCB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("SKCB_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("SKCB_location06_lat", 0);

        String[] arrtitle = new String[6];
        arrtitle[0] = it.getStringExtra("SKCB_location01_name");
        arrtitle[1] = it.getStringExtra("SKCB_location02_name");
        arrtitle[2] = it.getStringExtra("SKCB_location03_name");
        arrtitle[3] = it.getStringExtra("SKCB_location04_name");
        arrtitle[4] = it.getStringExtra("SKCB_location05_name");
        arrtitle[5] = it.getStringExtra("SKCB_location06_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[1] || dbelng == arrlng[2]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("新光商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("新光商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void SB() //陽信商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[2];
        arrlng[0] = it.getDoubleExtra("SB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("SB_location02_lng", 0);

        Double[] arrlat = new Double[2];
        arrlat[0] = it.getDoubleExtra("SB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("SB_location02_lat", 0);

        String[] arrtitle = new String[2];
        arrtitle[0] = it.getStringExtra("SB_location01_name");
        arrtitle[1] = it.getStringExtra("SB_location02_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("陽信商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("陽信商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void CCB() //三信商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("CCB_location01_lng", 0);
        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("CCB_location01_lat", 0);
        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("CCB_location01_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            LatLng position = new LatLng(dbelng, dbelat);
            mMap.addMarker(new MarkerOptions().position(position).title("三信商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

    public void UBOT() //聯邦商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[6];
        arrlng[0] = it.getDoubleExtra("UBOT_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("UBOT_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("UBOT_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("UBOT_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("UBOT_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("UBOT_location06_lng", 0);

        Double[] arrlat = new Double[6];
        arrlat[0] = it.getDoubleExtra("UBOT_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("UBOT_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("UBOT_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("UBOT_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("UBOT_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("UBOT_location06_lat", 0);

        String[] arrtitle = new String[6];
        arrtitle[0] = it.getStringExtra("UBOT_location01_name");
        arrtitle[1] = it.getStringExtra("UBOT_location02_name");
        arrtitle[2] = it.getStringExtra("UBOT_location03_name");
        arrtitle[3] = it.getStringExtra("UBOT_location04_name");
        arrtitle[4] = it.getStringExtra("UBOT_location05_name");
        arrtitle[5] = it.getStringExtra("UBOT_location06_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[5]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("聯邦商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("聯邦商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void YCB() //元大商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[7];
        arrlng[0] = it.getDoubleExtra("YCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("YCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("YCB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("YCB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("YCB_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("YCB_location06_lng", 0);
        arrlng[6] = it.getDoubleExtra("YCB_location07_lng", 0);

        Double[] arrlat = new Double[7];
        arrlat[0] = it.getDoubleExtra("YCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("YCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("YCB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("YCB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("YCB_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("YCB_location06_lat", 0);
        arrlat[6] = it.getDoubleExtra("YCB_location07_lat", 0);

        String[] arrtitle = new String[7];
        arrtitle[0] = it.getStringExtra("YCB_location01_name");
        arrtitle[1] = it.getStringExtra("YCB_location02_name");
        arrtitle[2] = it.getStringExtra("YCB_location03_name");
        arrtitle[3] = it.getStringExtra("YCB_location04_name");
        arrtitle[4] = it.getStringExtra("YCB_location05_name");
        arrtitle[5] = it.getStringExtra("YCB_location06_name");
        arrtitle[6] = it.getStringExtra("YCB_location07_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[4] || dbelng == arrlng[5]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("元大商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("元大商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void BS() //永豐商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[2];
        arrlng[0] = it.getDoubleExtra("BS_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("BS_location02_lng", 0);

        Double[] arrlat = new Double[2];
        arrlat[0] = it.getDoubleExtra("BS_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("BS_location02_lat", 0);

        String[] arrtitle = new String[2];
        arrtitle[0] = it.getStringExtra("BS_location01_name");
        arrtitle[1] = it.getStringExtra("BS_location02_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("永豐商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("永豐商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void ESCB() //玉山商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[5];
        arrlng[0] = it.getDoubleExtra("ESCB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("ESCB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("ESCB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("ESCB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("ESCB_location05_lng", 0);

        Double[] arrlat = new Double[5];
        arrlat[0] = it.getDoubleExtra("ESCB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("ESCB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("ESCB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("ESCB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("ESCB_location05_lat", 0);

        String[] arrtitle = new String[5];
        arrtitle[0] = it.getStringExtra("ESCB_location01_name");
        arrtitle[1] = it.getStringExtra("ESCB_location02_name");
        arrtitle[2] = it.getStringExtra("ESCB_location03_name");
        arrtitle[3] = it.getStringExtra("ESCB_location04_name");
        arrtitle[4] = it.getStringExtra("ESCB_location05_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[1]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("玉山商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("玉山商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void KGB() //凱基商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("KGB_location01_lng", 0);
        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("KGB_location01_lat", 0);
        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("KGB_location01_name");
        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            LatLng position = new LatLng(dbelng, dbelat);
            mMap.addMarker(new MarkerOptions().position(position).title("凱基商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

    public void TIB() //台新國際商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[18];
        arrlng[0] = it.getDoubleExtra("TIB_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("TIB_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("TIB_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("TIB_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("TIB_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("TIB_location06_lng", 0);
        arrlng[6] = it.getDoubleExtra("TIB_location07_lng", 0);
        arrlng[7] = it.getDoubleExtra("TIB_location08_lng", 0);
        arrlng[8] = it.getDoubleExtra("TIB_location09_lng", 0);
        arrlng[9] = it.getDoubleExtra("TIB_location10_lng", 0);
        arrlng[10] = it.getDoubleExtra("TIB_location11_lng", 0);
        arrlng[11] = it.getDoubleExtra("TIB_location12_lng", 0);
        arrlng[12] = it.getDoubleExtra("TIB_location13_lng", 0);
        arrlng[13] = it.getDoubleExtra("TIB_location14_lng", 0);
        arrlng[14] = it.getDoubleExtra("TIB_location15_lng", 0);
        arrlng[15] = it.getDoubleExtra("TIB_location16_lng", 0);
        arrlng[16] = it.getDoubleExtra("TIB_location17_lng", 0);
        arrlng[17] = it.getDoubleExtra("TIB_location18_lng", 0);

        Double[] arrlat = new Double[18];
        arrlat[0] = it.getDoubleExtra("TIB_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("TIB_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("TIB_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("TIB_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("TIB_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("TIB_location06_lat", 0);
        arrlat[6] = it.getDoubleExtra("TIB_location07_lat", 0);
        arrlat[7] = it.getDoubleExtra("TIB_location08_lat", 0);
        arrlat[8] = it.getDoubleExtra("TIB_location09_lat", 0);
        arrlat[9] = it.getDoubleExtra("TIB_location10_lat", 0);
        arrlat[10] = it.getDoubleExtra("TIB_location11_lat", 0);
        arrlat[11] = it.getDoubleExtra("TIB_location12_lat", 0);
        arrlat[12] = it.getDoubleExtra("TIB_location13_lat", 0);
        arrlat[13] = it.getDoubleExtra("TIB_location14_lat", 0);
        arrlat[14] = it.getDoubleExtra("TIB_location15_lat", 0);
        arrlat[15] = it.getDoubleExtra("TIB_location16_lat", 0);
        arrlat[16] = it.getDoubleExtra("TIB_location17_lat", 0);
        arrlat[17] = it.getDoubleExtra("TIB_location18_lat", 0);

        String[] arrtitle = new String[18];
        arrtitle[0] = it.getStringExtra("TIB_location01_name");
        arrtitle[1] = it.getStringExtra("TIB_location02_name");
        arrtitle[2] = it.getStringExtra("TIB_location03_name");
        arrtitle[3] = it.getStringExtra("TIB_location04_name");
        arrtitle[4] = it.getStringExtra("TIB_location05_name");
        arrtitle[5] = it.getStringExtra("TIB_location06_name");
        arrtitle[6] = it.getStringExtra("TIB_location07_name");
        arrtitle[7] = it.getStringExtra("TIB_location08_name");
        arrtitle[8] = it.getStringExtra("TIB_location09_name");
        arrtitle[9] = it.getStringExtra("TIB_location10_name");
        arrtitle[10] = it.getStringExtra("TIB_location11_name");
        arrtitle[11] = it.getStringExtra("TIB_location12_name");
        arrtitle[12] = it.getStringExtra("TIB_location13_name");
        arrtitle[13] = it.getStringExtra("TIB_location14_name");
        arrtitle[14] = it.getStringExtra("TIB_location15_name");
        arrtitle[15] = it.getStringExtra("TIB_location16_name");
        arrtitle[16] = it.getStringExtra("TIB_location17_name");
        arrtitle[17] = it.getStringExtra("TIB_location18_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[14]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台新國際商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("台新國際商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void JICB() //日盛國際商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("JICB_location01_lng", 0);
        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("JICB_location01_lat", 0);
        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("JICB_location01_name");
        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            LatLng position = new LatLng(dbelng, dbelat);
            mMap.addMarker(new MarkerOptions().position(position).title("日盛國際商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

    public void CTBC() //中國信託商業銀行
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[42];
        arrlng[0] = it.getDoubleExtra("CTBC_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("CTBC_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("CTBC_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("CTBC_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("CTBC_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("CTBC_location06_lng", 0);
        arrlng[6] = it.getDoubleExtra("CTBC_location07_lng", 0);
        arrlng[7] = it.getDoubleExtra("CTBC_location08_lng", 0);
        arrlng[8] = it.getDoubleExtra("CTBC_location09_lng", 0);
        arrlng[9] = it.getDoubleExtra("CTBC_location10_lng", 0);
        arrlng[10] = it.getDoubleExtra("CTBC_location11_lng", 0);
        arrlng[11] = it.getDoubleExtra("CTBC_location12_lng", 0);
        arrlng[12] = it.getDoubleExtra("CTBC_location13_lng", 0);
        arrlng[13] = it.getDoubleExtra("CTBC_location14_lng", 0);
        arrlng[14] = it.getDoubleExtra("CTBC_location15_lng", 0);
        arrlng[15] = it.getDoubleExtra("CTBC_location16_lng", 0);
        arrlng[16] = it.getDoubleExtra("CTBC_location17_lng", 0);
        arrlng[17] = it.getDoubleExtra("CTBC_location18_lng", 0);
        arrlng[18] = it.getDoubleExtra("CTBC_location19_lng", 0);
        arrlng[19] = it.getDoubleExtra("CTBC_location20_lng", 0);
        arrlng[20] = it.getDoubleExtra("CTBC_location21_lng", 0);
        arrlng[21] = it.getDoubleExtra("CTBC_location22_lng", 0);
        arrlng[22] = it.getDoubleExtra("CTBC_location23_lng", 0);
        arrlng[23] = it.getDoubleExtra("CTBC_location24_lng", 0);
        arrlng[24] = it.getDoubleExtra("CTBC_location25_lng", 0);
        arrlng[25] = it.getDoubleExtra("CTBC_location26_lng", 0);
        arrlng[26] = it.getDoubleExtra("CTBC_location27_lng", 0);
        arrlng[27] = it.getDoubleExtra("CTBC_location28_lng", 0);
        arrlng[28] = it.getDoubleExtra("CTBC_location29_lng", 0);
        arrlng[29] = it.getDoubleExtra("CTBC_location30_lng", 0);
        arrlng[30] = it.getDoubleExtra("CTBC_location31_lng", 0);
        arrlng[31] = it.getDoubleExtra("CTBC_location32_lng", 0);
        arrlng[32] = it.getDoubleExtra("CTBC_location33_lng", 0);
        arrlng[33] = it.getDoubleExtra("CTBC_location34_lng", 0);
        arrlng[34] = it.getDoubleExtra("CTBC_location35_lng", 0);
        arrlng[35] = it.getDoubleExtra("CTBC_location36_lng", 0);
        arrlng[36] = it.getDoubleExtra("CTBC_location37_lng", 0);
        arrlng[37] = it.getDoubleExtra("CTBC_location38_lng", 0);
        arrlng[38] = it.getDoubleExtra("CTBC_location39_lng", 0);
        arrlng[39] = it.getDoubleExtra("CTBC_location40_lng", 0);
        arrlng[40] = it.getDoubleExtra("CTBC_location41_lng", 0);
        arrlng[41] = it.getDoubleExtra("CTBC_location42_lng", 0);

        Double[] arrlat = new Double[42];
        arrlat[0] = it.getDoubleExtra("CTBC_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("CTBC_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("CTBC_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("CTBC_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("CTBC_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("CTBC_location06_lat", 0);
        arrlat[6] = it.getDoubleExtra("CTBC_location07_lat", 0);
        arrlat[7] = it.getDoubleExtra("CTBC_location08_lat", 0);
        arrlat[8] = it.getDoubleExtra("CTBC_location09_lat", 0);
        arrlat[9] = it.getDoubleExtra("CTBC_location10_lat", 0);
        arrlat[10] = it.getDoubleExtra("CTBC_location11_lat", 0);
        arrlat[11] = it.getDoubleExtra("CTBC_location12_lat", 0);
        arrlat[12] = it.getDoubleExtra("CTBC_location13_lat", 0);
        arrlat[13] = it.getDoubleExtra("CTBC_location14_lat", 0);
        arrlat[14] = it.getDoubleExtra("CTBC_location15_lat", 0);
        arrlat[15] = it.getDoubleExtra("CTBC_location16_lat", 0);
        arrlat[16] = it.getDoubleExtra("CTBC_location17_lat", 0);
        arrlat[17] = it.getDoubleExtra("CTBC_location18_lat", 0);
        arrlat[18] = it.getDoubleExtra("CTBC_location19_lat", 0);
        arrlat[19] = it.getDoubleExtra("CTBC_location20_lat", 0);
        arrlat[20] = it.getDoubleExtra("CTBC_location21_lat", 0);
        arrlat[21] = it.getDoubleExtra("CTBC_location22_lat", 0);
        arrlat[22] = it.getDoubleExtra("CTBC_location23_lat", 0);
        arrlat[23] = it.getDoubleExtra("CTBC_location24_lat", 0);
        arrlat[24] = it.getDoubleExtra("CTBC_location25_lat", 0);
        arrlat[25] = it.getDoubleExtra("CTBC_location26_lat", 0);
        arrlat[26] = it.getDoubleExtra("CTBC_location27_lat", 0);
        arrlat[27] = it.getDoubleExtra("CTBC_location28_lat", 0);
        arrlat[28] = it.getDoubleExtra("CTBC_location29_lat", 0);
        arrlat[29] = it.getDoubleExtra("CTBC_location30_lat", 0);
        arrlat[30] = it.getDoubleExtra("CTBC_location31_lat", 0);
        arrlat[31] = it.getDoubleExtra("CTBC_location32_lat", 0);
        arrlat[32] = it.getDoubleExtra("CTBC_location33_lat", 0);
        arrlat[33] = it.getDoubleExtra("CTBC_location34_lat", 0);
        arrlat[34] = it.getDoubleExtra("CTBC_location35_lat", 0);
        arrlat[35] = it.getDoubleExtra("CTBC_location36_lat", 0);
        arrlat[36] = it.getDoubleExtra("CTBC_location37_lat", 0);
        arrlat[37] = it.getDoubleExtra("CTBC_location38_lat", 0);
        arrlat[38] = it.getDoubleExtra("CTBC_location39_lat", 0);
        arrlat[39] = it.getDoubleExtra("CTBC_location40_lat", 0);
        arrlat[40] = it.getDoubleExtra("CTBC_location41_lat", 0);
        arrlat[41] = it.getDoubleExtra("CTBC_location42_lat", 0);


        String[] arrtitle = new String[42];
        arrtitle[0] = it.getStringExtra("CTBC_location01_name");
        arrtitle[1] = it.getStringExtra("CTBC_location02_name");
        arrtitle[2] = it.getStringExtra("CTBC_location03_name");
        arrtitle[3] = it.getStringExtra("CTBC_location04_name");
        arrtitle[4] = it.getStringExtra("CTBC_location05_name");
        arrtitle[5] = it.getStringExtra("CTBC_location06_name");
        arrtitle[6] = it.getStringExtra("CTBC_location07_name");
        arrtitle[7] = it.getStringExtra("CTBC_location08_name");
        arrtitle[8] = it.getStringExtra("CTBC_location09_name");
        arrtitle[9] = it.getStringExtra("CTBC_location10_name");
        arrtitle[10] = it.getStringExtra("CTBC_location11_name");
        arrtitle[11] = it.getStringExtra("CTBC_location12_name");
        arrtitle[12] = it.getStringExtra("CTBC_location13_name");
        arrtitle[13] = it.getStringExtra("CTBC_location14_name");
        arrtitle[14] = it.getStringExtra("CTBC_location15_name");
        arrtitle[15] = it.getStringExtra("CTBC_location16_name");
        arrtitle[16] = it.getStringExtra("CTBC_location17_name");
        arrtitle[17] = it.getStringExtra("CTBC_location18_name");
        arrtitle[18] = it.getStringExtra("CTBC_location19_name");
        arrtitle[19] = it.getStringExtra("CTBC_location20_name");
        arrtitle[20] = it.getStringExtra("CTBC_location21_name");
        arrtitle[21] = it.getStringExtra("CTBC_location22_name");
        arrtitle[22] = it.getStringExtra("CTBC_location23_name");
        arrtitle[23] = it.getStringExtra("CTBC_location24_name");
        arrtitle[24] = it.getStringExtra("CTBC_location25_name");
        arrtitle[25] = it.getStringExtra("CTBC_location26_name");
        arrtitle[26] = it.getStringExtra("CTBC_location27_name");
        arrtitle[27] = it.getStringExtra("CTBC_location28_name");
        arrtitle[28] = it.getStringExtra("CTBC_location29_name");
        arrtitle[29] = it.getStringExtra("CTBC_location30_name");
        arrtitle[30] = it.getStringExtra("CTBC_location31_name");
        arrtitle[31] = it.getStringExtra("CTBC_location32_name");
        arrtitle[32] = it.getStringExtra("CTBC_location33_name");
        arrtitle[33] = it.getStringExtra("CTBC_location34_name");
        arrtitle[34] = it.getStringExtra("CTBC_location35_name");
        arrtitle[35] = it.getStringExtra("CTBC_location36_name");
        arrtitle[36] = it.getStringExtra("CTBC_location37_name");
        arrtitle[37] = it.getStringExtra("CTBC_location38_name");
        arrtitle[38] = it.getStringExtra("CTBC_location39_name");
        arrtitle[39] = it.getStringExtra("CTBC_location40_name");
        arrtitle[40] = it.getStringExtra("CTBC_location41_name");
        arrtitle[41] = it.getStringExtra("CTBC_location42_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("中國信託商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("中國信託商業銀行").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public void PTFA() //屏東市農會
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[1];
        arrlng[0] = it.getDoubleExtra("PTFA_location01_lng", 0);
        Double[] arrlat = new Double[1];
        arrlat[0] = it.getDoubleExtra("PTFA_location01_lat", 0);
        String[] arrtitle = new String[1];
        arrtitle[0] = it.getStringExtra("PTFA_location01_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            LatLng position = new LatLng(dbelng, dbelat);
            mMap.addMarker(new MarkerOptions().position(position).title("農會").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

    public void CWP() //中華郵政
    {
        Intent it = getIntent();
        Double[] arrlng = new Double[23];
        arrlng[0] = it.getDoubleExtra("CWP_location01_lng", 0);
        arrlng[1] = it.getDoubleExtra("CWP_location02_lng", 0);
        arrlng[2] = it.getDoubleExtra("CWP_location03_lng", 0);
        arrlng[3] = it.getDoubleExtra("CWP_location04_lng", 0);
        arrlng[4] = it.getDoubleExtra("CWP_location05_lng", 0);
        arrlng[5] = it.getDoubleExtra("CWP_location06_lng", 0);
        arrlng[6] = it.getDoubleExtra("CWP_location07_lng", 0);
        arrlng[7] = it.getDoubleExtra("CWP_location08_lng", 0);
        arrlng[8] = it.getDoubleExtra("CWP_location09_lng", 0);
        arrlng[9] = it.getDoubleExtra("CWP_location10_lng", 0);
        arrlng[10] = it.getDoubleExtra("CWP_location11_lng", 0);
        arrlng[11] = it.getDoubleExtra("CWP_location12_lng", 0);
        arrlng[12] = it.getDoubleExtra("CWP_location13_lng", 0);
        arrlng[13] = it.getDoubleExtra("CWP_location14_lng", 0);
        arrlng[14] = it.getDoubleExtra("CWP_location15_lng", 0);
        arrlng[15] = it.getDoubleExtra("CWP_location16_lng", 0);
        arrlng[16] = it.getDoubleExtra("CWP_location17_lng", 0);
        arrlng[17] = it.getDoubleExtra("CWP_location18_lng", 0);
        arrlng[18] = it.getDoubleExtra("CWP_location19_lng", 0);
        arrlng[19] = it.getDoubleExtra("CWP_location20_lng", 0);
        arrlng[20] = it.getDoubleExtra("CWP_location21_lng", 0);
        arrlng[21] = it.getDoubleExtra("CWP_location22_lng", 0);
        arrlng[22] = it.getDoubleExtra("CWP_location23_lng", 0);

        Double[] arrlat = new Double[23];
        arrlat[0] = it.getDoubleExtra("CWP_location01_lat", 0);
        arrlat[1] = it.getDoubleExtra("CWP_location02_lat", 0);
        arrlat[2] = it.getDoubleExtra("CWP_location03_lat", 0);
        arrlat[3] = it.getDoubleExtra("CWP_location04_lat", 0);
        arrlat[4] = it.getDoubleExtra("CWP_location05_lat", 0);
        arrlat[5] = it.getDoubleExtra("CWP_location06_lat", 0);
        arrlat[6] = it.getDoubleExtra("CWP_location07_lat", 0);
        arrlat[7] = it.getDoubleExtra("CWP_location08_lat", 0);
        arrlat[8] = it.getDoubleExtra("CWP_location09_lat", 0);
        arrlat[9] = it.getDoubleExtra("CWP_location10_lat", 0);
        arrlat[10] = it.getDoubleExtra("CWP_location11_lat", 0);
        arrlat[11] = it.getDoubleExtra("CWP_location12_lat", 0);
        arrlat[12] = it.getDoubleExtra("CWP_location13_lat", 0);
        arrlat[13] = it.getDoubleExtra("CWP_location14_lat", 0);
        arrlat[14] = it.getDoubleExtra("CWP_location15_lat", 0);
        arrlat[15] = it.getDoubleExtra("CWP_location16_lat", 0);
        arrlat[16] = it.getDoubleExtra("CWP_location17_lat", 0);
        arrlat[17] = it.getDoubleExtra("CWP_location18_lat", 0);
        arrlat[18] = it.getDoubleExtra("CWP_location19_lat", 0);
        arrlat[19] = it.getDoubleExtra("CWP_location20_lat", 0);
        arrlat[20] = it.getDoubleExtra("CWP_location21_lat", 0);
        arrlat[21] = it.getDoubleExtra("CWP_location22_lat", 0);
        arrlat[22] = it.getDoubleExtra("CWP_location23_lat", 0);

        String[] arrtitle = new String[23];
        arrtitle[0] = it.getStringExtra("CWP_location01_name");
        arrtitle[1] = it.getStringExtra("CWP_location02_name");
        arrtitle[2] = it.getStringExtra("CWP_location03_name");
        arrtitle[3] = it.getStringExtra("CWP_location04_name");
        arrtitle[4] = it.getStringExtra("CWP_location05_name");
        arrtitle[5] = it.getStringExtra("CWP_location06_name");
        arrtitle[6] = it.getStringExtra("CWP_location07_name");
        arrtitle[7] = it.getStringExtra("CWP_location08_name");
        arrtitle[8] = it.getStringExtra("CWP_location09_name");
        arrtitle[9] = it.getStringExtra("CWP_location10_name");
        arrtitle[10] = it.getStringExtra("CWP_location11_name");
        arrtitle[11] = it.getStringExtra("CWP_location12_name");
        arrtitle[12] = it.getStringExtra("CWP_location13_name");
        arrtitle[13] = it.getStringExtra("CWP_location14_name");
        arrtitle[14] = it.getStringExtra("CWP_location15_name");
        arrtitle[15] = it.getStringExtra("CWP_location16_name");
        arrtitle[16] = it.getStringExtra("CWP_location17_name");
        arrtitle[17] = it.getStringExtra("CWP_location18_name");
        arrtitle[18] = it.getStringExtra("CWP_location19_name");
        arrtitle[19] = it.getStringExtra("CWP_location20_name");
        arrtitle[20] = it.getStringExtra("CWP_location21_name");
        arrtitle[21] = it.getStringExtra("CWP_location22_name");
        arrtitle[22] = it.getStringExtra("CWP_location23_name");

        int i;
        for (i = 0; i < arrtitle.length; i++) {
            double dbelng = arrlng[i];
            double dbelat = arrlat[i];

            if (dbelng == arrlng[0] || dbelng == arrlng[1] || dbelng == arrlng[6] || dbelng == arrlng[11] || dbelng == arrlng[14] || dbelng == arrlng[16] || dbelng == arrlng[18] || dbelng == arrlng[19]) {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("中華郵政").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                LatLng position = new LatLng(dbelng, dbelat);
                mMap.addMarker(new MarkerOptions().position(position).title("中華郵政").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bank)).snippet(arrtitle[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
        }
    }

    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("您目前的位置");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(11));

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Toast.makeText(this, marker.getSnippet(), Toast.LENGTH_SHORT).show();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.item_alertdialog,null);
        AlertDialog.Builder Alert = new AlertDialog.Builder(this);
        TextView phone = view.findViewById(R.id.Txv_phone);
        TextView location = view.findViewById(R.id.Txv_location);
        TextView clock = view.findViewById(R.id.Txv_clock);

        if(marker.getSnippet().equals("臺灣銀行(中屏分行)"))
        {
            phone.setText("08-767-7001");
            location.setText("屏東縣屏東市中華路9號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("臺灣銀行(屏東分行)"))
        {
            phone.setText("08-732-8141");
            location.setText("屏東縣屏東市中山路43號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("臺灣土地銀行(屏東分行)"))
        {
            phone.setText("08-732-5131");
            location.setText("屏東縣屏東市逢甲路78號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("合作金庫商業銀行(屏東分行)"))
        {
            phone.setText("08-734-3611");
            location.setText("屏東縣屏東市中正路42號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("合作金庫商業銀行(屏南分行)"))
        {
            phone.setText("08-732-6391");
            location.setText("屏東縣屏東市民生路287號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("第一商業銀行(屏東分行)"))
        {
            phone.setText("08-732-5111");
            location.setText("屏東縣屏東市民生路308號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("華南商業銀行(屏東分行)"))
        {
            phone.setText("08-732-3831");
            location.setText("屏東縣屏東市復興路36號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("彰化商業銀行(屏東分行)"))
        {
            phone.setText("08-734-2705");
            location.setText("屏東縣屏東市中正路117之2號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("上海商業儲蓄銀行(屏東分行)"))
        {
            phone.setText("08-738-5111");
            location.setText("屏東縣屏東市中正路468號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("台北富邦商業銀行(屏東分行)"))
        {
            phone.setText("02-8751-6665");
            location.setText("屏東縣屏東市公園路21-1號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("國泰世華商業銀行(屏東分行)"))
        {
            phone.setText("08-733-0456");
            location.setText("屏東縣屏東市中正路125號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("高雄銀行(屏東分行)"))
        {
            phone.setText("08-736-8811");
            location.setText("屏東縣屏東市中正路152號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("兆豐國際商業銀行(屏東分行)"))
        {
            phone.setText("08-732-3586");
            location.setText("屏東縣屏東市民族路213號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("台灣中小企業銀行(屏東分行)"))
        {
            phone.setText("08-732-7171");
            location.setText("屏東縣屏東市漢口街7號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("新光商業銀行(東園分行)"))
        {
            phone.setText("08-722-8306");
            location.setText("屏東縣屏東市廣東路63號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("新光商業銀行(屏東分行)"))
        {
            phone.setText("08-733-9911");
            location.setText("屏東縣屏東市中正路123號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("陽信商業銀行(屏東分行)"))
        {
            phone.setText("08-732-6123");
            location.setText("屏東縣屏東市中正路70號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("聯邦商業銀行(屏東分行)"))
        {
            phone.setText("08-732-6777");
            location.setText("屏東縣屏東市民族路172號");
            clock.setText("週一~週五 09:00-16:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("元大商業銀行(屏東分行)"))
        {
            phone.setText("08-735-0426");
            location.setText("屏東縣屏東市廣東路690號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("元大商業銀行(屏榮分行)"))
        {
            phone.setText("08-722-6060");
            location.setText("屏東縣屏東市廣東路115號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("永豐商業銀行(屏東分行)"))
        {
            phone.setText("08-732-3322");
            location.setText("屏東縣屏東市復興北路14號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("玉山商業銀行(屏東分行)"))
        {
            phone.setText("08-733-1313");
            location.setText("屏東縣屏東市永福路9號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("凱基商業銀行(屏東分行)"))
        {
            phone.setText("08-738-5678");
            location.setText("屏東縣屏東市廣東路451號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("台新國際商業銀行(屏東分行)"))
        {
            phone.setText("08-721-7777");
            location.setText("屏東縣屏東市廣東路103號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("日盛國際商業銀行(屏東分行)"))
        {
            phone.setText("08-732-6688");
            location.setText("屏東縣屏東市仁愛路88之2號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("中國信託商業銀行(屏東分行)"))
        {
            phone.setText("08-738-3000");
            location.setText("屏東縣屏東市自由路450號");
            clock.setText("週一~週五 09:00-15:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("屏東市農會"))
        {
            phone.setText("08-800-0871");
            location.setText("屏東縣屏東市民生路79之9號");
            clock.setText("週一~週五 09:00-16:30");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("農會資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("大埔郵局"))
        {
            phone.setText("08-732-6609");
            location.setText("屏東縣屏東市民族路182-2號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("中正路郵局"))
        {
            phone.setText("08-732-7905");
            location.setText("屏東縣屏東市中正路247號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("六塊厝郵局"))
        {
            phone.setText("08-765-4356");
            location.setText("屏東縣屏東市光復路304號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("公館郵局"))
        {
            phone.setText("08-752-2521");
            location.setText("屏東縣屏東市龍華路18號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("北平路郵局"))
        {
            phone.setText("08-732-6608");
            location.setText("屏東縣屏東市北平路26號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("民生路郵局"))
        {
            phone.setText("08-732-3310");
            location.setText("屏東縣屏東市民生路250號");
            clock.setText("週一~週五 08:30-18:00\n週六 09:00-12:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("永安郵局"))
        {
            phone.setText("08-753-5942");
            location.setText("屏東縣屏東市棒球路57號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("林森路郵局"))
        {
            phone.setText("08-722-5848");
            location.setText("屏東縣屏東市林森路30-5號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("厚生郵局"))
        {
            phone.setText("08-752-2841");
            location.setText("屏東縣屏東市建國路182號");
            clock.setText("週一~週五 08:30-17:00\n週六 09:00-12:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("海豐郵局"))
        {
            phone.setText("08-736-7224");
            location.setText("屏東縣屏東市海豐街36之4號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("崇蘭郵局"))
        {
            phone.setText("08-733-0072");
            location.setText("屏東縣屏東市廣東路990號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("廣東路郵局"))
        {
            phone.setText("08-737-5450");
            location.setText("屏東縣屏東市廣東路458-5號");
            clock.setText("週一~週五 08:30-17:00\n週六 09:00-12:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("頭前溪郵局"))
        {
            phone.setText("08-752-0804");
            location.setText("屏東縣屏東市清進巷79之6號");
            clock.setText("週一~週五 08:30-12:30\n13:30~17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
        if(marker.getSnippet().equals("歸來郵局"))
        {
            phone.setText("08-722-4840");
            location.setText("屏東縣屏東市民生路61之7號");
            clock.setText("週一~週五 08:30-17:00");
            Alert.setIcon(R.drawable.star);
            Alert.setTitle("分行資訊");
            Alert.setView(view);
            Alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
            Alert.show();
        }
    }
}

