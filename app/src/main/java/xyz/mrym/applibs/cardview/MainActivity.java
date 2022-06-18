package xyz.mrym.applibs.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import xyz.mrym.applibs.cardviewlib.MyBaseCardLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBaseCardLayout mrymCardView = findViewById(R.id.daoyin_card1);
        mrymCardView.setDaoyinCardClickListener((pager_daoyin, taskName, daoyin_name) -> {
            System.out.println(pager_daoyin + " = " + taskName + " = " + daoyin_name);
        });

    }
}