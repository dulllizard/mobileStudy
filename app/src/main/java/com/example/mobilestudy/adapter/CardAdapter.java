package com.example.mobilestudy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilestudy.R;
import com.example.mobilestudy.dto.Card;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Card> cardList;

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName, eventPlace;

        public ImageView imagePreview;
        public Button cardGoingButton;

        public Button showMoreButton;

        public CardViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.eventName);
            eventPlace = view.findViewById(R.id.eventPlace);
            cardGoingButton = view.findViewById(R.id.cardGoingButton);
            showMoreButton = view.findViewById(R.id.cardShowMoreButton);
            imagePreview = view.findViewById(R.id.imagePreview);
        }
    }

    public CardAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void setCards(List<Card> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.eventName.setText(card.getEventName());
        holder.eventPlace.setText(card.getEventPlace());
        holder.cardGoingButton.setText(card.getIsFavorite() ? "Не пойду" : "Пойду");
        holder.showMoreButton.setText("Показать");
        Picasso.get().load(card.getImagePreview()).into(holder.imagePreview);

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
    }

    public interface OnButtonGoingClickListener {
        void onGoingClick(int position);
    }

    public interface OnButtonShowMoreClickListener {
        void onShowMoreClick(int position);
    }

    private OnButtonShowMoreClickListener buttonShowMoreClickListener;
    private OnButtonGoingClickListener buttonGoingListener;

    public void setOnButtonGoingClickListener(OnButtonGoingClickListener listener) {
        buttonGoingListener = listener;
    }

    public void setOnButtonShowMoreClickListener(OnButtonShowMoreClickListener listener) {
        buttonShowMoreClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void removeItem(int position) {
        cardList.remove(position);
        notifyItemRemoved(position);
    }
}
