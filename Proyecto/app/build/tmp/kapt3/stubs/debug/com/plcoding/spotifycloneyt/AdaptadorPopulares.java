package com.plcoding.spotifycloneyt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/plcoding/spotifycloneyt/AdaptadorPopulares;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/plcoding/spotifycloneyt/AdaptadorPopulares$MyViewHolder;", "actividad", "Lcom/plcoding/spotifycloneyt/Artist;", "listaPopulares", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Lcom/plcoding/spotifycloneyt/Artist;Ljava/util/List;Landroidx/recyclerview/widget/RecyclerView;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MyViewHolder", "app_debug"})
public final class AdaptadorPopulares extends androidx.recyclerview.widget.RecyclerView.Adapter<com.plcoding.spotifycloneyt.AdaptadorPopulares.MyViewHolder> {
    private final com.plcoding.spotifycloneyt.Artist actividad = null;
    private final java.util.List<?> listaPopulares = null;
    private final androidx.recyclerview.widget.RecyclerView recyclerView = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.plcoding.spotifycloneyt.AdaptadorPopulares.MyViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.plcoding.spotifycloneyt.AdaptadorPopulares.MyViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public AdaptadorPopulares(@org.jetbrains.annotations.NotNull()
    com.plcoding.spotifycloneyt.Artist actividad, @org.jetbrains.annotations.NotNull()
    java.util.List<?> listaPopulares, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/plcoding/spotifycloneyt/AdaptadorPopulares$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/plcoding/spotifycloneyt/AdaptadorPopulares;Landroid/view/View;)V", "btnItem", "getBtnItem", "()Landroid/view/View;", "imagenItem", "Landroid/widget/ImageView;", "getImagenItem", "()Landroid/widget/ImageView;", "nombreItem", "Landroid/widget/TextView;", "getNombreItem", "()Landroid/widget/TextView;", "numReproducciones", "getNumReproducciones", "numberItem", "getNumberItem", "app_debug"})
    public final class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView numberItem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imagenItem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView nombreItem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView numReproducciones = null;
        @org.jetbrains.annotations.NotNull()
        private final android.view.View btnItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getNumberItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImagenItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getNombreItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getNumReproducciones() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.View getBtnItem() {
            return null;
        }
        
        public MyViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
    }
}