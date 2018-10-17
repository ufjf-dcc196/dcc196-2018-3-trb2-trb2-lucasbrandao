package ufjf.dcc196.trb01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        ViewHolderAluno holderAluno = new ViewHolderAluno(view);

        return holderAluno;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteAdapter.ViewHolderAluno viewHolder, int i) {

        if ((dados != null) && (dados.size() > 0)) {

            Aluno aluno = dados.get(i);

            viewHolder.txtNome.setText(aluno.nome);
            viewHolder.txtEmail.setText(aluno.email);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size() ;
    }


    public class ViewHolderAluno extends RecyclerView.ViewHolder {

        public TextView txtNome;
        public TextView txtEmail;

        public ViewHolderAluno(@NonNull View itemView) {
            super(itemView);

            txtNome     = (TextView) itemView.findViewById(R.id.txtNomeAluno);
            txtEmail    = (TextView) itemView.findViewById(R.id.txtEmailAluno);
        }
    }
}
