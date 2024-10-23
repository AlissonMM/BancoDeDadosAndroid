package br.edu.fatec.bancodedados;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.fatec.bancodedados.model.AlunoAdapter;
import br.edu.fatec.bancodedados.dao.AlunoDAO;
import br.edu.fatec.bancodedados.model.Aluno;

public class TelaListView extends AppCompatActivity {

    ListView lv1;
    private AlunoDAO dao;
    private List<Aluno> alunos;
    AlunoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_list_view);

        dao = new AlunoDAO(this);
        alunos = dao.obterTodos();

        lv1 = findViewById(R.id.lv1);

        // Cria o adaptador e configura na ListView
        adapter = new AlunoAdapter(this, alunos);
        lv1.setAdapter(adapter);

        // Adiciona o listener de clique nos itens da ListView
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtém o aluno clicado a partir da posição
                Aluno alunoClicado = alunos.get(position);

                // Exemplo de ação: Exibir um Toast com o nome do aluno
                Toast.makeText(TelaListView.this, "Clicou em: " + alunoClicado.getNome(), Toast.LENGTH_SHORT).show();

                // Exemplo: Passar para outra tela com os detalhes do aluno
                Intent intent = new Intent(TelaListView.this, Manutencao.class);
                intent.putExtra("edtId", alunoClicado.getId());
                intent.putExtra("edtNome", alunoClicado.getNome());
                intent.putExtra("edtCpf", alunoClicado.getCpf());
                intent.putExtra("edtTelefone", alunoClicado.getTelefone());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume(); // Chame o método super
        alunos.clear(); // Limpa a lista existente
        alunos.addAll(dao.obterTodos()); // Atualiza a lista de alunos
        adapter.notifyDataSetChanged(); // Notifica o adaptador que os dados mudaram
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.novo) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.sair) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
