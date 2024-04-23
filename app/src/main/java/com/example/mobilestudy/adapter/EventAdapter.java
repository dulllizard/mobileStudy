package com.example.mobilestudy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilestudy.R;
import com.example.mobilestudy.dto.Event;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName, eventPlace;

        public ImageView imagePreview;
        public Button cardGoingButton;

        public Button showMoreButton;
        public Button deleteButton;

        public EventViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.eventName);
            eventPlace = view.findViewById(R.id.eventPlace);
            cardGoingButton = view.findViewById(R.id.cardGoingButton);
            showMoreButton = view.findViewById(R.id.cardShowMoreButton);
            imagePreview = view.findViewById(R.id.imagePreview);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void setFilteredList(List<Event> filteredList) {
        this.eventList = filteredList;
        notifyDataSetChanged();
    }

    public void setCards(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventPlace.setText(event.getEventPlace());
        holder.cardGoingButton.setText(event.getIsFavorite() ? "Не пойду" : "Пойду");
        holder.showMoreButton.setText("Показать");
        Picasso.get().load(event.getImagePreview()).into(holder.imagePreview);

        holder.cardGoingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGoingListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        buttonGoingListener.onGoingClick(position);
                    }
                }
            }
        });

        holder.showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonShowMoreClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        buttonShowMoreClickListener.onShowMoreClick(position);
                    }
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonDeleteListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        buttonDeleteListener.onDeleteClick(position);
                    }
                }
            }
        });
    }

    public interface OnButtonGoingClickListener {
        void onGoingClick(int position);
    }

    public interface OnButtonShowMoreClickListener {
        void onShowMoreClick(int position);
    }

    public interface onButtonDeleteListener {
        void onDeleteClick(int position);
    }

    private onButtonDeleteListener buttonDeleteListener;

    private OnButtonShowMoreClickListener buttonShowMoreClickListener;
    private OnButtonGoingClickListener buttonGoingListener;

    public void setOnButtonGoingClickListener(OnButtonGoingClickListener listener) {
        buttonGoingListener = listener;
    }

    public void setOnButtonShowMoreClickListener(OnButtonShowMoreClickListener listener) {
        buttonShowMoreClickListener = listener;
    }

    public void setOnButtonDeleteClickListener(onButtonDeleteListener listener) {
        buttonDeleteListener = listener;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
