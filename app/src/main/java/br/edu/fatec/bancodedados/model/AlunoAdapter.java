package br.edu.fatec.bancodedados.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.fatec.bancodedados.R;
import br.edu.fatec.bancodedados.model.Aluno;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    private List<Aluno> alunos;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        super(context, 0, alunos);
        this.alunos = alunos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifica se a view já existe, se não, infla uma nova
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_aluno, parent, false);
        }

        // Obtém o aluno a partir da lista
        Aluno aluno = alunos.get(position);

        // Atualiza os dados nos TextViews
        TextView idTextView = convertView.findViewById(R.id.id);
        TextView nomeTextView = convertView.findViewById(R.id.nome);
        TextView cpfTextView = convertView.findViewById(R.id.cpf);
        TextView telefoneTextView = convertView.findViewById(R.id.telefone);

        // Atualizando os TextViews com os dados do aluno
        idTextView.setText(String.valueOf(aluno.getId())); // Converte o Integer para String
        nomeTextView.setText(aluno.getNome());
        cpfTextView.setText(aluno.getCpf());
        telefoneTextView.setText(aluno.getTelefone());

        return convertView;
    }
}
