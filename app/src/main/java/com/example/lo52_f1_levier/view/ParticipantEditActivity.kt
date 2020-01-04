package com.example.lo52_f1_levier.view

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lo52_f1_levier.DAO.CoureurDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Coureur
import kotlinx.android.synthetic.main.activity_paticipant_edit.*

class ParticipantEditActivity : AppCompatActivity() {

    var runnerId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paticipant_edit)
        val coureurDao = CoureurDao(this)

        runnerId = intent.getIntExtra("runnerId", -1)
        if(runnerId != -1){
            var res : Cursor? = coureurDao.getCoureur(runnerId)
            res?.moveToFirst()

            firstName.setText(res?.getString(res?.getColumnIndexOrThrow(Coureur.CoureurTable.CNAME)))
            lastName.setText(res?.getString(res?.getColumnIndexOrThrow(Coureur.CoureurTable.SURNAME)))
        }

        btn_addParticipant.setOnClickListener{
            if(firstName.text.toString() != "" && lastName.text.toString() != ""){
                val coureurDao = CoureurDao(this)
                coureurDao.updateCoureur(runnerId,runnerId,
                    firstName.text.toString(), lastName.text.toString())
                Toast.makeText(this, "Modification enregistrée", Toast.LENGTH_SHORT).show()
            }
            else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Attention")
                builder.setMessage("Veuillez bien remplir toutes les informations nécessaires.")
                builder.show()

            }
        }
    }
}
