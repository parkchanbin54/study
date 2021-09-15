package gachon.mpclass.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_main);

        Button back=findViewById(R.id.back);
        EditText url22 = findViewById(R.id.url2);
        EditText phone2 = findViewById(R.id.phone2);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent();

                intent.putExtra("geturl",url22.getText().toString());
                intent.putExtra("phones",phone2.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        }

    );
    }
}
//
