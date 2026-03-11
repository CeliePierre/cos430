###########################################################
#   joel kalala
#   Playing with :
#   Simple input output of string and intergers
#    stopping execution
###########################################################
.data

.text

main:

    ##################################################
    # Q1
    li	$s0,	10
    li	$s1,	10
    bne	$s0, $s1, endQ1
    addi	$s0, $s0, 1
    add	$s0, $s0, $s0
endQ1:
    # Print the value of $s0 for Q1
    move $a0, $s0
    li $v0, 1
    syscall

    ##################################################
    # Q2
    li	$s0,	25
    bne	$s0, 25, endQ2
    addi	$s0, $s0, 10
endQ2:
    # Print the value of $s0 for Q2
    move $a0, $s0
    li $v0, 1
    syscall

    ##################################################
    # Q3
    blt	$s0, $zero, endQ3
    addi	$s0, $s0, 1
endQ3:
    # Print the value of $s0 for Q3
    move $a0, $s0
    li $v0, 1
    syscall

    ##################################################
    # Q4
    bge	$s0, $zero, endQ4
    addi	$s0, $s0, 1
endQ4:
    # Print the value of $s0 for Q4
    move $a0, $s0
    li $v0, 1
    syscall

    ##################################################
    # Q5
    blt	$s0, $zero, endQ5
    bgt	$s0, $s1, endQ5
    addi	$s0, $s0, 1
endQ5:
    # Print the value of $s0 for Q5
    move $a0, $s0
    li $v0, 1
    syscall

    ##################################################
    # Q6
    slt	$t0, $s0, $s1        # set if $s0 < $s1
    slt	$t1, $s2, $s0        # set if $s2 < $s0
    or	$t2, $t0, $t1       # Logical OR the results
    beq	$t2, $zero, endQ6   # branch if both conditions are false
    addi	$s3, $s3, 1
endQ6:
    # Print the value of $s3 for Q6
    move $a0, $s3
    li $v0, 1
    syscall

    # Exit
    li $v0, 10
    syscall