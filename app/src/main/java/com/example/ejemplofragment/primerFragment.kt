package com.example.ejemplofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [primerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class primerFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_primer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al botón
        val btnSendWhatsApp: Button = view.findViewById(R.id.btn_send_whatsapp)
        val btnSendEmail: Button = view.findViewById(R.id.btn_send_email)

        // Configurar el clic del botón WhatssApp
        btnSendWhatsApp.setOnClickListener {
            sendWhatsAppMessage()
        }

        // Configurar el clic del botón de Email
        btnSendEmail.setOnClickListener {
            sendEmail()
        }

    }

    private fun sendWhatsAppMessage() {
        // Número de teléfono (debe incluir el código de país)
        val phoneNumber = "+591375318030"  // Reemplaza con el número al que deseas enviar el mensaje

        // Mensaje a enviar
        val message = "Hola, este es un mensaje enviado desde mi app."

        // Crear el intent para abrir WhatsApp
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$phoneNumber/?text=${Uri.encode(message)}")
        }

        // Verificar si hay alguna aplicación que pueda manejar este intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Mostrar un mensaje si WhatsApp no está instalado
            Toast.makeText(requireContext(), "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmail() {
        // Dirección de correo a la que enviar el mensaje
        val emailAddress = "aesistemas24@gmail.com"  // Reemplaza con la dirección de correo deseada

        // Asunto y mensaje a enviar
        val subject = "Asunto del mensaje"
        val message = "Hola, este es un mensaje enviado desde mi app."

        // Crear el intent para enviar el email
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Solo las apps de correo deben manejar esto
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        // Verificar si hay alguna aplicación que pueda manejar este intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Mostrar un mensaje si no hay ninguna aplicación de correo instalada
            Toast.makeText(requireContext(), "No hay ninguna aplicación de correo instalada", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment primerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            primerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}