package ufjf.dcc196.trb01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento> {

    private List<Evento> dados;

    public EventoAdapter(List<Evento> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.evento_layout, viewGroup, false);
        ViewHolderEvento holderEvento = new ViewHolderEvento(view);

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento viewHolder, int i) {
        if ((dados != null) && (dados.size() > 0)) {

            Evento evento = dados.get(i);

            viewHolder.txtTitulo.setText(evento.titulo);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEvento extends RecyclerView.ViewHolder {

        public TextView txtTitulo;

        public ViewHolderEvento(@NonNull View itemView) {
            super(itemView);

            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);
        }
    }

}
