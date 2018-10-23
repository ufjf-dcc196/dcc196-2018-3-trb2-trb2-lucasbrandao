package ufjf.dcc196.trb01;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolderAluno> {

    private List<Aluno> dados;

    public ParticipanteAdapter(List<Aluno> dados) {
        this.dados = dados;
    }


    @NonNull
    @Override
    public ParticipanteAdapter.ViewHolderAluno onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.aluno_layout, viewGroup, false);
        ViewHolderAluno holderAluno = new ViewHolderAluno(view, viewGroup.getContext());

        return holderAluno;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteAdapter.ViewHolderAluno viewHolder, int i) {

        if ((dados != null) && (dados.size() > 0)) {

            Aluno aluno = dados.get(i);

            viewHolder.txtNome.setText(aluno.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size() ;
    }


    public class ViewHolderAluno extends RecyclerView.ViewHolder {

        public TextView txtNome;

        public ViewHolderAluno(@NonNull View itemView, final Context context) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNomeAluno);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dados.size() > 0) {
                        Aluno aluno = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, DetalhesAluno.class);
                        intent.putExtra("DADOS_PARTICIPANTE", aluno);
                        intent.putExtra("ALUNOPOSITION", getLayoutPosition());
                        ((AppCompatActivity)context).startActivityForResult(intent, MainActivity.REQUEST_DETALHESPARTICIPANTE);

                        MainActivity.alunoPosition = getLayoutPosition();
                    }
                }
            });

        }
    }
}
