package br.com.cotemig.projetofinal.helpers

import android.content.Context

class SharedPreferencesHelpers {

    companion object {

        fun saveString(context: Context, fileName: String, key: String, value: String){
            // cria pasta no app no modo privado para salvar login
            var preferences = context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            // abre modo de edicao
            var editor = preferences.edit()

            // usar criptografia AINDA A FAZER


            editor.putString(key,value)
            editor.apply()

        }

        fun readString(context: Context, fileName: String, key: String, default: String) : String? {
            // cria pasta no app no modo privado para salvar login
            var preferences = context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            return preferences.getString(key, default)
        }

        fun readString(context: Context, fileName: String, key: String) : String? {
            // cria pasta no app no modo privado para salvar login
            var preferences = context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            return preferences.getString(key, "")
        }

        fun delete(context: Context, fileName: String, key: String){
            var preferences = context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            var editor = preferences.edit()
            editor.remove(key)
            editor.apply()
        }
    }
}