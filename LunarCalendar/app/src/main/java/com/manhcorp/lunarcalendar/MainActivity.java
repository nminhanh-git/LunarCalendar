package com.manhcorp.lunarcalendar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText yearEditText;
    Button showMeBtn;
    TextView lunarTextView;
    ImageView lunarImage;
    ArrayList<String> listCan;
    ArrayList<String> listChi;
    ArrayList<Integer> listAnh;
    String languageToLoad = "welcome_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        yearEditText = (EditText) findViewById(R.id.YearEditText);
        showMeBtn = (Button) findViewById(R.id.ShowMeBtn);
        lunarTextView = (TextView) findViewById(R.id.LunarTextView);
        lunarImage = (ImageView) findViewById(R.id.LunarImage);
        listCan = new ArrayList<>();
        addCan();
        listChi = new ArrayList<>();
        addChi();
        listAnh = new ArrayList<>();
        addAnh();
        showMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function();
            }
        });
        yearEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                registerForContextMenu(yearEditText);
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.languageItem:
                showLanguageChooseDialog();
                return true;
            case R.id.infoItem:
                Intent infoIntent = new Intent(MainActivity.this, InfoActivity.class);
                MainActivity.this.startActivity(infoIntent);
                return true;
            case R.id.exitItem:
                showExitDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clearAllItem:
                yearEditText.setText("");
                lunarTextView.setText("");
                Toast.makeText(MainActivity.this, R.string.toast_cleared, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.selectAllItem:
                yearEditText.selectAll();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void addCan() {
        listCan.add("Canh");
        listCan.add("Tân");
        listCan.add("Nhâm");
        listCan.add("Quý");
        listCan.add("Giáp");
        listCan.add("Ất");
        listCan.add("Bính");
        listCan.add("Đinh");
        listCan.add("Mậu");
        listCan.add("Kỷ");
    }

    public void addChi() {
        listChi.add("Thân");
        listChi.add("Dậu");
        listChi.add("Tuất");
        listChi.add("Hợi");
        listChi.add("Tý");
        listChi.add("Sửu");
        listChi.add("Dần");
        listChi.add("Mão");
        listChi.add("Thìn");
        listChi.add("Tỵ");
        listChi.add("Ngọ");
        listChi.add("Mùi");
    }

    public void addAnh() {
        listAnh.add(R.drawable.than);
        listAnh.add(R.drawable.dau);
        listAnh.add(R.drawable.tuat);
        listAnh.add(R.drawable.hoi);
        listAnh.add(R.drawable.ti);
        listAnh.add(R.drawable.suu);
        listAnh.add(R.drawable.dan);
        listAnh.add(R.drawable.mao);
        listAnh.add(R.drawable.thin);
        listAnh.add(R.drawable.ty);
        listAnh.add(R.drawable.ngo);
        listAnh.add(R.drawable.mui);
    }

    public void function() {
        int year;
        if (yearEditText.getText().toString().equals("")) {
            year = 0;
            Toast.makeText(MainActivity.this, R.string.toast_empty_year, Toast.LENGTH_SHORT).show();
            lunarTextView.setText("");
            lunarImage.setImageResource(0);
        } else {
            year = Integer.valueOf(yearEditText.getText().toString());
            lunarTextView.setText(listCan.get(year % 10) + " " + listChi.get(year % 12));
            lunarImage.setImageResource(listAnh.get(year % 12));
        }
    }

    public void loadLanguage(String language) {
        Locale locale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.setLocale(locale);
        res.updateConfiguration(config, dm);
        this.finish();
        Intent intent = new Intent(this, Welcome_Activity.class);
        startActivity(intent);
    }

    public void showLanguageChooseDialog() {
        final String[] languageList = {"English", "Tiếng Việt"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle(R.string.choose_language_title).
                setSingleChoiceItems(languageList, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (languageList[i].equals("English")) {
                            languageToLoad = "welcome_text";
                        }
                        if (languageList[i].equals("Tiếng Việt")) {
                            languageToLoad = "vi";
                        }
                    }
                }).setPositiveButton(R.string.ok_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loadLanguage(languageToLoad);
            }
        }).setNegativeButton(R.string.cancel_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog languageDialog = builder.create();
        languageDialog.show();
    }

    public void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.exit_app_message).setPositiveButton(R.string.exit_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }).setNegativeButton(R.string.cancel_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ExitDialog = builder.create();
        ExitDialog.show();
    }
}
