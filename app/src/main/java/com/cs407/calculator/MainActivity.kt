package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)

        // References to buttons
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)

        // Set up button click listeners
        addButton.setOnClickListener {
            performOperation(input1, input2, "add")
        }

        subtractButton.setOnClickListener {
            performOperation(input1, input2, "subtract")
        }

        multiplyButton.setOnClickListener {
            performOperation(input1, input2, "multiply")
        }

        divideButton.setOnClickListener {
            performOperation(input1, input2, "divide")
        }
    }

    // Function to perform arithmetic operations
    private fun performOperation(input1: EditText, input2: EditText, operation: String) {
        val number1 = input1.text.toString().toIntOrNull()
        val number2 = input2.text.toString().toIntOrNull()

        // Check if inputs are valid integers
        if (number1 == null || number2 == null) {
            Toast.makeText(this, "Please enter valid integers", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform the operation and handle divide-by-zero case
        val result = when (operation) {
            "add" -> number1 + number2
            "subtract" -> number1 - number2
            "multiply" -> number1 * number2
            "divide" -> {
                if (number2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                number1 / number2
            }
            else -> 0
        }

        // Use Intent to pass the result to ResultActivity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("RESULT", result)
        }
        startActivity(intent)
    }
}

