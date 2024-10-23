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

public class Manutencao extends AppCompatActivity {

    EditText edtIdMa;
    EditText edtNomeMa;
    EditText edtCPFMa;
    EditText edtTelefoneMa;

    private AlunoDAO dao;
    private List<Aluno> alunos;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manutencao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa os campos de entrada
        edtIdMa = findViewById(R.id.edtIdMa);
        edtNomeMa = findViewById(R.id.edtNomeMa);
        edtCPFMa = findViewById(R.id.edtCPFMa);
        edtTelefoneMa = findViewById(R.id.edtTelefoneMa);

        // Inicializa o DAO
        dao = new AlunoDAO(this);

        // Recebe os dados passados pela Intent e preenche os campos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            edtIdMa.setText(String.valueOf(extras.getInt("edtId")));  // Recebe o ID
            edtNomeMa.setText(extras.getString("edtNome"));           // Recebe o Nome
            edtCPFMa.setText(extras.getString("edtCpf"));             // Recebe o CPF
            edtTelefoneMa.setText(extras.getString("edtTelefone"));   // Recebe o Telefone
        }
    }

    public void alterar(View view) {
        String idStr = edtIdMa.getText().toString().trim();
        String nome = edtNomeMa.getText().toString().trim();
        String cpf = edtCPFMa.getText().toString().trim();
        String telefone = edtTelefoneMa.getText().toString().trim();

        // Verifica se o ID está vazio
        if (idStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, insira um ID válido!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se o ID contém apenas números
        if (!idStr.matches("\\d+")) {
            Toast.makeText(getApplicationContext(), "O ID deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se o nome, CPF e telefone não estão vazios
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se CPF e telefone contêm apenas números
        if (!cpf.matches("\\d+")) {
            Toast.makeText(getApplicationContext(), "O CPF deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        if (!telefone.matches("\\d+")) {
            Toast.makeText(getApplicationContext(), "O telefone deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Se todas as verificações passarem, atualiza o aluno
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(idStr)); // Converte o ID para inteiro
        a.setNome(nome);
        a.setCpf(cpf);
        a.setTelefone(telefone);

        dao.update(a);
        edtIdMa.setText(null);
        edtNomeMa.setText(null);
        edtCPFMa.setText(null);
        edtTelefoneMa.setText(null);

        Toast.makeText(getApplicationContext(), "Aluno alterado!", Toast.LENGTH_LONG).show();
    }

    public void excluir(View view) {
        String idStr = edtIdMa.getText().toString().trim(); // Pega o ID da caixa de texto

        // Verifica se o ID está vazio
        if (idStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, insira um ID válido!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Verifica se o ID contém apenas números
        if (!idStr.matches("\\d+")) {
            Toast.makeText(getApplicationContext(), "O ID deve conter apenas números!", Toast.LENGTH_LONG).show();
            return; // Interrompe a execução do método
        }

        // Converte o ID para inteiro
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(idStr)); // Define o ID do aluno

        dao = new AlunoDAO(this);
        dao.delete(a); // Exclui o aluno com o ID informado

        edtIdMa.setText(null);
        edtNomeMa.setText(null);
        edtCPFMa.setText(null);
        edtTelefoneMa.setText(null);

        Toast.makeText(getApplicationContext(), "Aluno Excluído!", Toast.LENGTH_LONG).show();
    }
}
