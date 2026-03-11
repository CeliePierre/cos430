.data
prompt:  .asciiz "Press enter to continue to the next test."

q1_str:  .asciiz "Q1: "
q2_str:  .asciiz "Q2: "
q3_str:  .asciiz "Q3: "
q4_str:  .asciiz "Q4: "
q5_str:  .asciiz "Q5: "
q6_str:  .asciiz "Q6: "
newline: .asciiz "\n"
.text
main:
###################################################
 # Q1 True Case
    li	$s0,	10
    li	$s1,	10          # First test value
    bne	$s0, $s1, Q1SecondTest
    addi	$s0, $s0, 1
    add	$s0, $s0, $s0

    # Print Q1 output for True case
    li $v0, 4
    la $a0, q1_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Wait for user input before moving to the second test
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall

Q1SecondTest:
    li	$s0,	10
    li	$s1,	20          # Second test value
    bne	$s0, $s1, endQ1False
    addi	$s0, $s0, 1
    add	$s0, $s0, $s0

    # Print Q1 output for False case
    li $v0, 4
    la $a0, q1_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ1False:
####################################################
 # Q2 True Case
    li	$s0,	25
    bne	$s0, 25, Q2SecondTest
    addi	$s0, $s0, 10

    # Print Q2 output for True case
    li $v0, 4
    la $a0, q2_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Wait for user input before moving to the second test
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall

Q2SecondTest:
    li	$s0,	10          # Changed for false scenario
    bne	$s0, 25, endQ2False
    addi	$s0, $s0, 10

    # Print Q2 output for False case
    li $v0, 4
    la $a0, q2_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ2False:

##################################################
    # Q3 True Case
    li  $s0, 5           # Initialize a test value
    blt	$s0, $zero, Q3SecondTest
    addi	$s0, $s0, 1

    # Print Q3 output for True case
    li $v0, 4
    la $a0, q3_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Wait for user input before moving to the second test
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall

Q3SecondTest:
    li  $s0, -5          # Changed for false scenario
    blt	$s0, $zero, endQ3False
    addi	$s0, $s0, 1

    # Print Q3 output for False case
    li $v0, 4
    la $a0, q3_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ3False:
##################################################
   # Q4 True Case
    li  $s0, -5            # Value to make the condition True
    bge	$s0, $zero, Q4SecondTest
    addi	$s0, $s0, 1

    # Print Q4 output for True case
    li $v0, 4
    la $a0, q4_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Wait for user input before moving to the second test
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall

Q4SecondTest:
    li  $s0, 5            # Value to make the condition False
    bge	$s0, $zero, endQ4False
    addi	$s0, $s0, 1

    # Print Q4 output for False case
    li $v0, 4
    la $a0, q4_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ4False:

    ##################################################
    # Q5 True Case
    li  $s0, 5             # Values to make the condition True
    li  $s1, 10
    blt	$s0, $zero, Q5SecondTest
    bgt	$s0, $s1, Q5SecondTest
    addi	$s0, $s0, 1

    # Print Q5 output for True case
    li $v0, 4
    la $a0, q5_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Wait for user input before moving to the second test
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall

Q5SecondTest:
    li  $s0, 15            # Values to make the condition False
    li  $s1, 10
    blt	$s0, $zero, endQ5False
    bgt	$s0, $s1, endQ5False
    addi	$s0, $s0, 1

    # Print Q5 output for False case
    li $v0, 4
    la $a0, q5_str
    syscall
    move $a0, $s0
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ5False:
     ##################################################
   Q6SecondTest:
    li  $s0, 8             # Initialize a test value to make the condition False
    li  $s1, 10
    li  $s2, 6
    li  $s3, 0            # Resetting the value of $s3 for the False case

    # Conditions: ($s0 >= $s1 || $s0 <= $s2)
    slt	$t0, $s0, $s1
    slt	$t1, $s2, $s0

    or	$t2, $t0, $t1
    beq	$t2, $zero, endQ6False
    addi	$s3, $s3, 1

    # Print Q6 output for False case
    li $v0, 4
    la $a0, q6_str
    syscall
    move $a0, $s3
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newline
    syscall

endQ6False:

    # Optionally, you can add another prompt for the user here
    # if you'd like to pause before moving to the next question.
    li $v0, 4
    la $a0, prompt
    syscall
    li $v0, 8            # Read string syscall
    la $a0, prompt       # Use the prompt buffer to get the input
    li $a1, 2            # Read 2 characters (one character and the newline)
    syscall
