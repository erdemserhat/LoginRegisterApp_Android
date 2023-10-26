package com.erdemserhat.loginregisterapp_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erdemserhat.loginregisterapp_android.databinding.FragmentLoginBinding
import com.erdemserhat.loginregisterapp_android.user_input_exceptions.IllegalUsernameException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    //View binding object
    private var fragmentMainBinding: FragmentLoginBinding? = null
    lateinit var binding:FragmentLoginBinding


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //You can now access whole the view components.
        binding = FragmentLoginBinding.bind(view)
        fragmentMainBinding=binding

        binding.loginButton.setOnClickListener {
            validUserInformationToLogin()
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()

        //When Fragment is destroyed fragmentMainBinding will be assigned to null....
        fragmentMainBinding=null;
    }

    private fun validUserInformationToLogin(){
        var username:String
        try{
            if(binding.editTextUsername.text==null || binding.editTextUsername.text.toString()?.length==0  || binding.editTextUsername.text?.trim().toString().isEmpty()){
                throw IllegalUsernameException("Please enter a valid username")

            }else{
                username=binding.editTextUsername.toString()
            }



        }catch (ex:IllegalUsernameException){
            Toast.makeText(binding.editTextUsername.context, ex.message, Toast.LENGTH_SHORT).show()




        }

    }
}