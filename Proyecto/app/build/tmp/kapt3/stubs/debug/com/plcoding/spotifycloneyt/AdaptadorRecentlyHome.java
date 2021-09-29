package com.plcoding.spotifycloneyt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/plcoding/spotifycloneyt/AdaptadorRecentlyHome;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/plcoding/spotifycloneyt/AdaptadorRecentlyHome$MyViewHolder;", "actividad", "Lcom/plcoding/spotifycloneyt/Home;", "listaItemsRecently", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Lcom/plcoding/spotifycloneyt/Home;Ljava/util/List;Landroidx/recyclerview/widget/RecyclerView;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MyViewHolder", "app_debug"})
public final class AdaptadorRecentlyHome extends androidx.recyclerview.widget.RecyclerView.Adapter<com.plcoding.spotifycloneyt.AdaptadorRecentlyHome.MyViewHolder> {
    private final com.plcoding.spotifycloneyt.Home actividad = null;
    private final java.util.List<?> listaItemsRecently = null;
    private final androidx.recyclerview.widget.RecyclerView recyclerView = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.plcoding.spotifycloneyt.AdaptadorRecentlyHome.MyViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.plcoding.spotifycloneyt.AdaptadorRecentlyHome.MyViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public AdaptadorRecentlyHome(@org.jetbrains.annotations.NotNull()
    com.plcoding.spotifycloneyt.Home actividad, @org.jetbrains.annotations.NotNull()
    java.util.List<?> listaItemsRecently, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/plcoding/spotifycloneyt/AdaptadorRecentlyHome$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/plcoding/spotifycloneyt/AdaptadorRecentlyHome;Landroid/view/View;)V", "btnItem", "Landroidx/cardview/widget/CardView;", "getBtnItem", "()Landroidx/cardview/widget/CardView;", "imagenItem", "Landroid/widget/ImageView;", "getImagenItem", "()Landroid/widget/ImageView;", "nombreItem", "Landroid/widget/TextView;", "getNombreItem", "()Landroid/widget/TextView;", "app_debug"})
    public final class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView nombreItem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imagenItem = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.cardview.widget.CardView btnItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getNombreItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImagenItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.cardview.widget.CardView getBtnItem() {
            return null;
        }
        
        public MyViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
    }
}