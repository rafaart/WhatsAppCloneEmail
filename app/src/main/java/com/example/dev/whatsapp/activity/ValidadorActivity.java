package com.example.dev.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dev.whatsapp.R;
import com.example.dev.whatsapp.helper.Preferencias;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

public class ValidadorActivity extends AppCompatActivity {

    private EditText codigo;
    private Button botaoValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        codigo = findViewById(R.id.editTextValidacaoID);
        botaoValidar = findViewById(R.id.buttonValidacaoID);

        SimpleMaskFormatter mascaraTextoValidacao = new SimpleMaskFormatter("NNNNNN");
        MaskTextWatcher sentinelaTextoValidacao = new MaskTextWatcher(codigo, mascaraTextoValidacao);

        codigo.addTextChangedListener(sentinelaTextoValidacao);
        botaoValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperar dados da classe Preferencias
                Preferencias preferencias = new Preferencias(ValidadorActivity.this);
                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                String tokenGerado = usuario.get("token");
                String tokenDigitado = codigo.getText().toString();

                if (tokenDigitado.equals(tokenGerado)){

                    Toast.makeText(ValidadorActivity.this, "Token validado", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(ValidadorActivity.this, "Token não confere", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
