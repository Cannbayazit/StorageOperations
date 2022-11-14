package com.canbayazit.depolamaislemleri

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonYaz.setOnClickListener {

            //hariciYaz()
            dahiliYaz()

        }
        buttonOku.setOnClickListener {

           // hariciOku()
            dahiliOku()
        }
        buttonSil.setOnClickListener {

            //hariciSil()
            dahiliSil()
        }



    }

    fun hariciYaz(){

        try {
            val yol = applicationContext.getExternalFilesDir(null)?.absolutePath

            val dosya = File(yol,"dosyam.txt")
            if (!dosya.exists()){
                dosya.createNewFile()
            }

            val fw = FileWriter(dosya)
            val yazici = BufferedWriter(fw)

            yazici.write(editTextTextGirdi.text.toString())

            yazici.flush()
            yazici.close()
            fw.close()

            editTextTextGirdi.setText("")

        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    fun hariciOku(){
        try {
            val yol = applicationContext.getExternalFilesDir(null)?.absolutePath
            val dosya = File(yol,"dosyam.txt")

            val fr = FileReader(dosya)
            val okuyucu = BufferedReader(fr)

            val sb = java.lang.StringBuilder()

            var satir : String? = null

            while ({satir = okuyucu.readLine();satir}()!= null){

                sb.append(satir+"\n")

            }

            okuyucu.close()
            editTextTextGirdi.setText(sb.toString())



        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    fun hariciSil(){
        try {
            val yol = applicationContext.getExternalFilesDir(null)?.absolutePath
            val dosya = File(yol,"dosyam.txt")

            dosya.delete()

        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun dahiliYaz(){


        try {

            val fo = openFileOutput("dosyam.txt",Context.MODE_PRIVATE)
            val yazici = OutputStreamWriter(fo)

            yazici.write(editTextTextGirdi.text.toString())
            yazici.close()

            editTextTextGirdi.setText("")


        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    fun dahiliOku(){
        try {

            val fi = openFileInput("dosyam.txt")
            val isr = InputStreamReader(fi)
            val okuyucu = BufferedReader(isr)

            val sb = java.lang.StringBuilder()

            var satir : String? = null

            while ({satir = okuyucu.readLine();satir}()!= null){

                sb.append(satir+"\n")

            }

            okuyucu.close()
            editTextTextGirdi.setText(sb.toString())





        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun dahiliSil(){

        val dir = filesDir
        val dosya = File(dir,"dosyam.txt")

        dosya.delete()

    }

}