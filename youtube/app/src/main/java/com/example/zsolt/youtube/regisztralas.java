package com.example.zsolt.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class regisztralas extends AppCompatActivity {
    TextView beleptx;
    EditText nevet,emailet,jelszoet;
    Button regisztraltbn;
    String nev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztralas);
        beleptx=findViewById(R.id.belepv);
        nevet=findViewById(R.id.nevet);
        emailet=findViewById(R.id.emailet);
        jelszoet=findViewById(R.id.jelszoet);
        regisztraltbn=findViewById(R.id.regisztral);

        beleptx.setOnClickListener(v -> startActivity(new Intent(regisztralas.this,belepes.class)));

        regisztraltbn.setOnClickListener(v -> {
            nev=nevet.getText().toString();
            String email=emailet.getText().toString();
            String jelszo=jelszoet.getText().toString();

            if (!nev.trim().isEmpty()&&!email.trim().isEmpty()&&!jelszo.trim().isEmpty()){
                try {
                    JSONObject kuld = new JSONObject();
                    kuld.put("nev",nev);
                    kuld.put("email", email);
                    kuld.put("jelszo", jelszo);
                    new regisztraltatas().execute(kuld);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                if (nev.trim().isEmpty())
                {
                    TextInputLayout nevit=findViewById(R.id.nevit);
                    nevit.setError("Töltsd ki ezt a mezőt!");
                }
                if (email.trim().isEmpty())
                {
                    TextInputLayout emailit=findViewById(R.id.emailit);
                    emailit.setError("Töltsd ki ezt a mezőt!");
                }
                if (jelszo.trim().isEmpty())
                {
                    TextInputLayout jelszoit=findViewById(R.id.jelszoit);
                    jelszoit.setError("Töltsd ki ezt a mezőt!");
                }
            }
        });
    }



    class regisztraltatas extends AsyncTask<JSONObject,JSONObject,JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            JSONObject json = jsonObjects[0];
            HttpClient client = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(client.getParams(),5000);

            JSONObject jsonResponse = null;
            HttpPost post = new HttpPost("http://192.168.42.101/youtubeproj/regisztral.php");//az ipcimet a cmd-ből tudjuk lekérni az ipconfig paranccsal
            try {
                StringEntity se = new StringEntity("json="+json.toString());
                post.addHeader("content-type","application/x-www-form-urlencoded");
                post.setEntity(se);

                HttpResponse response;
                response=client.execute(post);
                String res = org.apache.http.util.EntityUtils.toString(response.getEntity());
                jsonResponse=new JSONObject(res);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject obj) {
            super.onPostExecute(obj);
            if (obj !=null)
            {
                try {
                    if (obj.getString("allapot").equals("1"))
                    {
                        Toast.makeText(getApplicationContext(),obj.getString("uzenet"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(regisztralas.this,MainActivity.class);
                        intent.putExtra("nev",nev);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),obj.getString("uzenet"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Sikeretelen csatlakozás a szerverhez ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}