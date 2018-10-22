package ufjf.dcc196.trb01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventosInscritosAdapter extends RecyclerView.Adapter<EventosInscritosAdapter.ViewHolderEventosInscritos> implements View.OnClickListener {

    private List<Evento> eventosInscritos;

    public EventosInscritosAdapter(List<Evento> inscritos) {
        this.eventosInscritos = inscritos;
    }

    @NonNull
    @Override
    public EventosInscritosAdapter.ViewHolderEventosInscritos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.evento_layout, viewGroup, false);
        ViewHolderEventosInscritos holderEvento = new ViewHolderEventosInscritos(view);

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventosInscritosAdapter.ViewHolderEventosInscritos viewHolder, int i) {

        if ((eventosInscritos != null) && (eventosInscritos.size() > 0)) {
            Evento evento = eventosInscritos.get(i);
            viewHolder.txtTitulo.setText(evento.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        return eventosInscritos.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolderEventosInscritos extends RecyclerView.ViewHolder {

        public TextView txtTitulo;


        public ViewHolderEventosInscritos(@NonNull final View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);


        }
    }


}
