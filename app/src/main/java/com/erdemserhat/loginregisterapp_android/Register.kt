package com.erdemserhat.loginregisterapp_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.erdemserhat.loginregisterapp_android.databinding.FragmentRegisterBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //for view binding
    private var fragmentRegisterBinding:FragmentRegisterBinding?=null
    private lateinit var binding:FragmentRegisterBinding

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
        return inflater.inflate(R.layout.fragment_register, container, false)

        //Room


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Register().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        fragmentRegisterBinding=binding

        /**
         * Home Button implementation
         */

        binding.homeButton.setOnClickListener {
            val login:Login=Login();
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame,login)
            transaction.commit()
        }

        binding.registerButton.setOnClickListener {
            val inputViews:Array<TextView> = arrayOf(binding.editTextRegisterEmail,binding.editTextRegisterUsername,binding.editTextRegisterPassword)
            val isFormValid =FormValidator.validateForm(inputViews)
            if(isFormValid==true){
                //Database operation to make user register
                val user = User(
                    binding.editTextRegisterUsername.text.toString(),
                    binding.editTextRegisterEmail.text.toString(),
                    binding.editTextRegisterPassword.text.toString()
                    )
                val referenceContext = binding.homeButton.context;
                val database:LocalDatabase=LocalDatabase(referenceContext)
                database.insertUser(user)
                Toast.makeText(referenceContext,"You registered !",Toast.LENGTH_SHORT).show()
                val transaction:FragmentTransaction = parentFragmentManager.beginTransaction()
                val login:Login= Login()
                transaction.replace(R.id.mainFrame,login)
                transaction.addToBackStack(null)
                transaction.commit()


                }



            }
        }



    }