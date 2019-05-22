package com.lulin.assignment2;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lulin.assignment2.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

enum Action {
    Addition,
    Subtraction,
    Multiplication,
    Division
}

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private Action currentAction;
    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        reset();

        //region Number click listener
        binding.btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "0");
            }
        });

        binding.btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "1");
            }
        });

        binding.btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "2");
            }
        });

        binding.btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "3");
            }
        });

        binding.btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            binding.txtCurrent.setText(binding.txtCurrent.getText() + "4");
            }
        });

        binding.btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "5");
            }
        });

        binding.btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "6");
            }
        });

        binding.btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "7");
            }
        });

        binding.btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "8");
            }
        });

        binding.btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtCurrent.setText(binding.txtCurrent.getText() + "9");
            }
        });
        //endregion

        //region Operator click listener
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = Action.Addition;
                computeCalculation();
                binding.txtOutput.setText(decimalFormat.format(valueOne) + "+");
                binding.txtCurrent.setText(null);
            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = Action.Subtraction;
                computeCalculation();
                binding.txtOutput.setText(decimalFormat.format(valueOne) + "-");
                binding.txtCurrent.setText(null);
            }
        });

        binding.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = Action.Multiplication;
                computeCalculation();
                binding.txtOutput.setText(decimalFormat.format(valueOne) + "*");
                binding.txtCurrent.setText(null);
            }
        });

        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = Action.Division;
                computeCalculation();
                binding.txtOutput.setText(decimalFormat.format(valueOne) + "/");
                binding.txtCurrent.setText(null);
            }
        });

        binding.btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAction == null) {
                    // User presses equals twice
                    return;
                }
                computeCalculation();
                if (Double.isNaN(valueOne)) {
                    // Reset everything
                    reset();
                } else {
                    binding.txtOutput.setText(binding.txtOutput.getText().toString() +
                            decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                    binding.txtCurrent.setText(decimalFormat.format(valueOne));
                    currentAction = null;
                    valueOne = Double.NaN;
                }
            }
        });
        //endregion

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete one character from current input
                CharSequence currentText = binding.txtCurrent.getText();
                binding.txtCurrent.setText(currentText.subSequence(0, currentText.length() - 1));
            }
        });

        binding.btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add point to current input if possible
                CharSequence currentText = binding.txtCurrent.getText();
                if (currentText.toString().indexOf('.') >= 0) {
                    // If the input already has point, don't do anything
                    return;
                }
                binding.txtCurrent.setText(currentText + ".");
            }
        });
    }

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            // If user presses two operator twice
            if (binding.txtCurrent.getText().length() == 0) {
                return;
            }
            valueTwo = Double.parseDouble(binding.txtCurrent.getText().toString());
            binding.txtCurrent.setText(null);
            switch (currentAction) {
                case Addition: {
                    valueOne += valueTwo;
                    break;
                }
                case Subtraction: {
                    valueOne -= valueTwo;
                    break;
                }
                case Multiplication: {
                    valueOne *= valueTwo;
                    break;
                }
                case Division: {
                    if (valueTwo == 0.0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(R.string.errorMessage).setTitle(R.string.errorTitle);
                        AlertDialog dialog = builder.create();
                        valueOne = Double.NaN;
                        dialog.show();
                        return;
                    }
                    valueOne /= valueTwo;
                    break;
                }
            }
        }
        else {
            valueOne = Double.parseDouble(binding.txtCurrent.getText().toString());
        }
    }

    private void reset() {
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        binding.txtOutput.setText(null);
        binding.txtCurrent.setText(null);
    }
}
