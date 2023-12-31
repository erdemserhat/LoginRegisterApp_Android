package com.erdemserhat.loginregisterapp_android

import android.app.AlertDialog
import android.app.Dialog
import android.location.Address
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.erdemserhat.loginregisterapp_android.databinding.FragmentLoginBinding

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
            var control =FormValidator.validateForm(arrayOf<TextView>(binding.editTextUsername,binding.editTextPassword))
            if(control==true){
                val user = User(username = binding.editTextUsername.text.toString(), password=binding.editTextPassword.text.toString())
                val referenceContext=binding.editTextUsername.context
                val isUserRegistered= LocalDatabase(referenceContext).isUserRegistered(user)
                Toast.makeText(referenceContext,if(isUserRegistered) "Welcome, ${user.username} !" else "Wrong Information !",Toast.LENGTH_SHORT).show()
            }

        }

        binding.signInButton.setOnClickListener {
            val register:Register= Register()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame,register)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        binding.checkBoxShowPassword.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Metni görünür hale getirin
                binding.editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // Metni gizli hale getirin
                binding.editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.resetPassword.setOnClickListener{
            val referenceContext = binding.editTextUsername.context
            val builder= AlertDialog.Builder(referenceContext)

            builder.setTitle("Reset Password")

            //Dialog Context
            val input=EditText(referenceContext)
            input.hint = "type your email address"
            builder.setView(input)
            var dialog:Dialog? = null

            builder.setPositiveButton("SEND") { _, _ ->
                val userInput = input.text.toString()
                val isInputValid = FormValidator.validateForm(arrayOf(input))
                if(isInputValid==true){




                }

            }

            builder.setNegativeButton("CANCEL") { _, _ ->
                dialog?.cancel()
                Toast.makeText(referenceContext,"Cancelled",Toast.LENGTH_SHORT).show()

            }

            dialog = builder.create()
            dialog.show()






        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        //When Fragment is destroyed fragmentMainBinding will be assigned to null....
        fragmentMainBinding=null;
    }


    }





