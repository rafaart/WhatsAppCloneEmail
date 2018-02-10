package com.example.dev.whatsapp.activity;

import android.content.Intent;
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

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = findViewById(R.id.editTextNomeLoginID);
        telefone = findViewById(R.id.editTextTelLoginID);
        codArea = findViewById(R.id.editTextAreaLoginID);
        codPais = findViewById(R.id.editTextPaisID);
        cadastrar = findViewById(R.id.buttonCadastrarID);

        SimpleMaskFormatter smfPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter smfArea = new SimpleMaskFormatter("(NN)");
        SimpleMaskFormatter smfTel = new SimpleMaskFormatter("NNNNN-NNNN");

        MaskTextWatcher mtwArea = new MaskTextWatcher(codArea, smfArea);
        MaskTextWatcher mtwPais = new MaskTextWatcher(codPais, smfPais);
        MaskTextWatcher mtwTel = new MaskTextWatcher(telefone, smfTel);

        codPais.addTextChangedListener(mtwPais);
        codArea.addTextChangedListener(mtwArea);
        telefone.addTextChangedListener(mtwTel);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUsuario = nome.getText().toString();
                String telefoneUsuario =
                        codPais.getText().toString()+
                        codArea.getText().toString()+
                        telefone.getText().toString();

                String telefoneSemFormatacao = telefoneUsuario.replace("+","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("(","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(")","");

                //gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(999999-100000)+100000;
                String token = String.valueOf(numeroRandomico);

                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                Toast.makeText(LoginActivity.this, token, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
