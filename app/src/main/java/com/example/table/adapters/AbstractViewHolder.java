package com.example.table.adapters;

import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder{
    public enum SelectionState {SELECTED, UNSELECTED, SHADOWED}

    // Default value
    @NonNull
    private SelectionState m_eState = SelectionState.UNSELECTED;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setSelected(@NonNull SelectionState selectionState) {
        m_eState = selectionState;

        if (selectionState == SelectionState.SELECTED) {
            itemView.setSelected(true);
        } else if (selectionState == SelectionState.UNSELECTED) {
            itemView.setSelected(false);
        }
    }

    public boolean isSelected() {
        return m_eState == SelectionState.SELECTED;
    }

    public boolean isShadowed() {
        return m_eState == SelectionState.SHADOWED;
    }

    public void setBackgroundColor(@ColorInt int p_nColor) {
        itemView.setBackgroundColor(p_nColor);
    }

    public void onViewRecycled() {
    }

    public boolean onFailedToRecycleView() {
        return false;
    }

}
