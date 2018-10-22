package ufjf.dcc196.trb01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InscritosEventoAdapter extends RecyclerView.Adapter<InscritosEventoAdapter.ViewHolderInscritosEvento> implements View.OnClickListener {

    private List<Aluno> inscritos;

    public InscritosEventoAdapter(List<Aluno> lista) {
        this.inscritos = lista;
    }

    @NonNull
    @Override
    public InscritosEventoAdapter.ViewHolderInscritosEvento onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.aluno_layout, viewGroup, false);
        ViewHolderInscritosEvento holderEvento = new ViewHolderInscritosEvento(view);

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull InscritosEventoAdapter.ViewHolderInscritosEvento viewHolder, int i) {
        if ((inscritos != null) && (inscritos.size() > 0)) {
            Aluno aluno = inscritos.get(i);
            viewHolder.txtNome.setText(aluno.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return inscritos.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolderInscritosEvento extends RecyclerView.ViewHolder {

        public TextView txtNome;

        public ViewHolderInscritosEvento(@NonNull View itemView) {

            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.txtNomeAluno);

        }
    }

}
