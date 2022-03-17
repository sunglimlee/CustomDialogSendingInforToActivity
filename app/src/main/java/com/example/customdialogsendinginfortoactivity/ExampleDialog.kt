package com.example.customdialogsendinginfortoactivity

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment

class ExampleDialog : AppCompatDialogFragment() {
    //이거를 만들어 놓은건 좋은데 기존에 값이 있어야 하는데 어디에서 findViewById를 할 수 있는거지? inflater이후에?
    //여기서 Error가 나왔지? ?을 넣고 null을 붙였다. 그랬더니 이상이 없다..
    private lateinit var editTextUserName : EditText
    private lateinit var editTextPassword : EditText
    private var listener : ExampleDialogListener? = null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            listener = activity as ExampleDialog.ExampleDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ExampleDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        //layoutInflater를 activity에서 받아서 가지고 있는 이유가 무엇일까???
        //layoutInflater는 xml을 불러오기 위한 거잖아.. xml layout을 부르기 위한 것일까?
        //그럼 layoutInflater는 어떻게 사용하는 걸까?
        var inflater = LayoutInflater.from(activity)
        //inflater를 통해서 레이아웃을 inflate를 할수 있구나.. 그리고 나오는 건 View가 나오고..
        var view : View = inflater!!.inflate(R.layout.layout_dialog, null)
        //이렇게 setView를 통해서 내가 만든 view를 넣어줄 수 있구나.. 그럼 내가 만든 다이얼로그 마음대로 만들수 있다는거네..
        //아주 좋은데..
        //그렇구나.. view안에서 findViewbyId를 해야하는구나... 맞잖나.... 그전에는 Activity가 RootView였고 그게 this니깐
        //그냥 findViewById가 가능했던거고.. 그리고 잘봐라.. findViewById 이름 자체에 View가 들어있다는 사실을...
        editTextUserName = view.findViewById(R.id.edit_username) as EditText
        editTextPassword = view.findViewById(R.id.edit_password) as EditText
        builder.setView(view)
            .setTitle("Login")
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->  })
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                try {
                    val username = editTextUserName.text.toString()
                    val password = editTextPassword.text.toString()
                    //Log.e("usernamepassword", " username 값은 ")
                    listener?.applyTexts(username, password)
                } catch (e: Exception) {
                }

            })

        return builder.create()
    }
    public interface ExampleDialogListener {
        //기억하자. applyText는 외부에서 오는거라서 Interface를 사용하는거고..
        //여기에서 사용할 것들이 있기때문에 파라미터를 만들어놓은거다.
        fun applyTexts(username : String, password : String)
    }
}