package com.example.a2dam.ad_actividad_3a;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String NOM="fichero.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText= (EditText) findViewById(R.id.editText);
        final Button btnAfegir= (Button) findViewById(R.id.buttonAfegir);
        final Button btnMostrar= (Button) findViewById(R.id.buttonMostrar);
        final TextView tvMensaje= (TextView) findViewById(R.id.textViewMensaje);

        btnAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guardar texto en el fichero
                try {
                    FileOutputStream fos= openFileOutput(NOM, Context.MODE_PRIVATE);
                    String cadenaOutput= editText.getText().toString();
                    DataOutputStream dos= new DataOutputStream(fos);
                    dos.writeBytes(cadenaOutput);
                    dos.close();
                    fos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                //Se vacía el texto
                editText.setText("");
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mostrar el texto guardado
                try{
                    FileInputStream fis= openFileInput(NOM);
                    DataInputStream dis= new DataInputStream(fis);
                    byte[] buff= new byte[1000];
                    dis.read(buff);
                    tvMensaje.setText(new String(buff));
                    dis.close();
                    fis.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
