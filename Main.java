package gachon.mpclass.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {  // implements Button.OnClickListener{
    TextView url;
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button webbutton = (Button) findViewById(R.id.button1);
        Button callbutton = (Button) findViewById(R.id.button2);
        Button loginbutton=findViewById(R.id.login);
         url = findViewById(R.id.url);
         phone = findViewById(R.id.phone);
        EditText Sname=findViewById(R.id.name);
        EditText classification=findViewById(R.id.clasi);
        EditText IDnumber=findViewById(R.id.shnumber);



        webbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString()));
                startActivity(intent);
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phone.getText().toString()));
                                              startActivity(intent);
                                          }

        });
        loginbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent= new Intent(MainActivity.this, InfoActivity.class);
                Toast.makeText(getApplicationContext(),"Student info \n Name:"+Sname.getText().toString()+"\nDepartment: "+classification.getText().toString()+"\nID: "+IDnumber.getText().toString()
                        ,Toast.LENGTH_LONG).show();
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK){
                String tempurl=data.getStringExtra("geturl");
                url.setText(tempurl);
                String tempphone=data.getStringExtra("phones");
                phone.setText(tempphone);
            }
            else if(resultCode==RESULT_CANCELED)
            {
        }
    }

}
}