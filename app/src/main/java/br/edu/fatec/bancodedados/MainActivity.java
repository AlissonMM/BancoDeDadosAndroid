package br.edu.fatec.bancodedados;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.edu.fatec.bancodedados.dao.AlunoDAO;
import br.edu.fatec.bancodedados.model.Aluno;

public class MainActivity extends AppCompatActivity {


    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private AlunoDAO dao;
    private List<Aluno> alunos;
    private Aluno aluno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);


        }
        public void novo(View v) {
            edtNome.setText(null);
            edtCpf.setText(null);
            edtTelefone.setText(null);


    }

    public void salvar(View view) {
        // Pega os dados da tela
        String nome = edtNome.getText().toString().trim();
        String cpf = edtCpf.getText().toString().trim();
        String telefone = edtTelefone.getText().toString().trim();

        // Verifica se algum campo está vazio
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            // Exibe uma mensagem de erro se algum campo estiver vazio
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se o telefone contém apenas números
        if (!telefone.matches("\\d+")) {
            // Exibe uma mensagem de erro se o telefone não tiver apenas números
            Toast.makeText(getApplicationContext(), "O telefone deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se o CPF contém apenas números
        if (!cpf.matches("\\d+")) {
            // Exibe uma mensagem de erro se o CPF não tiver apenas números
            Toast.makeText(getApplicationContext(), "O CPF deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Se todos os campos estiverem preenchidos corretamente, cria o objeto Aluno
        aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);

        // Abre o BD
        dao = new AlunoDAO(this);

        // Salva o aluno
        long id = dao.insert(aluno);

        // Exibe uma mensagem de sucesso
        Toast.makeText(getApplicationContext(), "Aluno inserido com o ID " + id, Toast.LENGTH_LONG).show();
    }




}