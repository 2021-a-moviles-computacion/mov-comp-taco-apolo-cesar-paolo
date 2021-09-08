package com.example.firebase_uno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebase_uno.dto.FirestoreProductoDTO
import com.example.firebase_uno.dto.FirestoreRestauranteDTO
import com.example.firebase_uno.dto.OrdenDTO
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {

    var arrayRestGeneral = ArrayList<FirestoreRestauranteDTO>()
    var arraProdGeneral = ArrayList<FirestoreProductoDTO>()
    var selectedProd = FirestoreProductoDTO()
    var selectedRest = FirestoreRestauranteDTO()
    var arrayOrder = ArrayList<OrdenDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)


        val tv_header = findViewById<TextView>(R.id.tv_header)
        tv_header.text = "PRODUCTO \t\t VALOR UNITARIO \t\t CANTIDAD \t\t TOTAL"

        var docuRest:(MutableList<DocumentSnapshot>)
        var docuProd:(MutableList<DocumentSnapshot>)

        var arrayRest= ArrayList<FirestoreRestauranteDTO>()
        var arrayProd = ArrayList<FirestoreProductoDTO>()

        val db = Firebase.firestore
        val refRest = db.collection("restaurante")
        val refProd = db.collection("producto")


        refRest
            .get()
            .addOnSuccessListener {
                docuRest  = it.documents
                docuRest.forEach { iteracion ->

                    var objRest = iteracion.toObject(FirestoreRestauranteDTO::class.java)
                    objRest!!.uid = iteracion.id

                    arrayRest.add(objRest)

                }

        arrayRestGeneral=arrayRest

        refProd
            .get()
            .addOnSuccessListener {
                docuProd  = it.documents
                docuProd.forEach { iteracion ->

                    var objProd = iteracion.toObject(FirestoreProductoDTO::class.java)
                    objProd!!.uid    = iteracion.id
                    objProd!!.nombre = iteracion.get("nombre").toString()
                    objProd!!.precio = iteracion.get("precio").toString().toDouble()

                    arrayProd.add(objProd)

                }

                arraProdGeneral=arrayProd

                val spRest = findViewById<Spinner>(R.id.sp_restaurantes)
                val spProd = findViewById<Spinner>(R.id.sp_producto)

                val restAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    arrayRestGeneral)
                spRest.adapter = restAdapter

                val prodAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    arraProdGeneral)
                spProd.adapter = prodAdapter

            }

            }


        val spRest = findViewById<Spinner>(R.id.sp_restaurantes)
        val spProd = findViewById<Spinner>(R.id.sp_producto)

        spRest.onItemSelectedListener = object:
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedRest = arrayRestGeneral[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }


        }

        spProd.onItemSelectedListener = object:
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProd = arraProdGeneral[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }


        }


        val etProdAmount = findViewById<EditText>(R.id.et_cantidad_producto)
        val addBtn = findViewById<Button>(R.id.btn_add_product_list)


        val lvProducts = findViewById<ListView>(R.id.lv_productos)
        val lvProductsadapter = ArrayAdapter(
            this,
            android.R.layout.simple_selectable_list_item,
            arrayOrder)

        lvProducts.adapter = lvProductsadapter


        addBtn.setOnClickListener {

            var etAmountproductChanged:String = etProdAmount.text.toString()

            if(etAmountproductChanged == ""){
                etAmountproductChanged = "1"
            }
            val orden = OrdenDTO(
                selectedProd.nombre,
                selectedProd.precio!!,
                etAmountproductChanged.toInt()
            )

            addItemsListView(orden,lvProductsadapter)
            etProdAmount.text.clear()

        }

        val completeorderBtn = findViewById<Button>(R.id.btn_completar_pedido)

        completeorderBtn
            .setOnClickListener {
                if (lvProducts != null){
                    Log.i("Orden", "Productos añadidos: $arrayOrder")
                    Toast.makeText(
                        this,
                        "Orden realizada",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    Toast.makeText(
                        this,
                        "Orden vacía",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

    }

    fun addItemsListView(obj:OrdenDTO, adaptador: ArrayAdapter<OrdenDTO>){

        val tvTotalPayOrder = findViewById<TextView>(R.id.tv_total_pay)

        arrayOrder.add(obj)
        adaptador.notifyDataSetChanged()
        tvTotalPayOrder.text =
            (
                tvTotalPayOrder.text.toString().toDouble() + obj.calculateTotalOrder()
            ).toString()

    }

    fun deleteItemListViewProducts(obj:OrdenDTO, adaptador: ArrayAdapter<OrdenDTO>){

        val lvProds = findViewById<ListView>(R.id.lv_productos)

        lvProds.onItemLongClickListener
        arrayOrder.remove(obj)
        adaptador.notifyDataSetChanged()



    }

}