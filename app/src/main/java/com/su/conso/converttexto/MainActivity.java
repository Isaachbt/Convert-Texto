package com.su.conso.converttexto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.su.conso.converttexto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private  static String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.layoutTrocarTxt.setVisibility(View.INVISIBLE);
      onclick();
    }

    private void onclick() {
        binding.btnGerar.setOnClickListener(view1 -> {
            try {
                text = binding.editText.getText().toString();
                if (binding.rdMaiuscula.isChecked()) {
                    binding.editText.setText(text.toUpperCase());
                    copiarVisibilidade();
                } else if (binding.rdMinuscula.isChecked()) {
                    binding.editText.setText(text.toLowerCase());
                    copiarVisibilidade();
                } else if (binding.rdAoContrario.isChecked()) {
                    String letraInvertida = new StringBuilder(text).reverse().toString();
                    binding.editText.setText(letraInvertida);
                    copiarVisibilidade();
                } else if (binding.rdTrocarLetra.isChecked()) {
                    trocarLetra();
                    copiarVisibilidade();
                } else {
                    String aviso = getString(R.string.aviso);
                    Toast.makeText(MainActivity.this, aviso, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            binding.editTextLetra.setText("");
            binding.editMudarPraEssa.setText("");
        });

        binding.imgBtnCopiar.setOnClickListener(view12 -> {
            if (!binding.editText.getText().toString().isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(binding.editText.getText().toString());
                Toast.makeText(MainActivity.this, "Copiado com sucesso.", Toast.LENGTH_SHORT).show();
                binding.imgBtnCopiar.setVisibility(View.INVISIBLE);
            }
        });

        binding.rdTrocarLetra.setOnClickListener(view -> {
            binding.layoutTrocarTxt.setVisibility(View.VISIBLE);
        });
        binding.rdMaiuscula.setOnClickListener(view -> {
            binding.layoutTrocarTxt.setVisibility(View.INVISIBLE);
        });
        binding.rdAoContrario.setOnClickListener(view -> {
            binding.layoutTrocarTxt.setVisibility(View.INVISIBLE);
        });
        binding.rdMinuscula.setOnClickListener(view -> {
            binding.layoutTrocarTxt.setVisibility(View.INVISIBLE);
        });
    }
    private void copiarVisibilidade(){
        binding.imgBtnCopiar.setVisibility(View.VISIBLE);
    }

    private void trocarLetra() {
        try {
            String mudarLetra = text.replace(binding.editTextLetra.getText().toString(), binding.editMudarPraEssa.getText().toString());
            binding.editText.setText(mudarLetra);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Erro ao substituir letra.", Toast.LENGTH_SHORT).show();
        }
        binding.editTextLetra.setText("");
        binding.editMudarPraEssa.setText("");
    }
}