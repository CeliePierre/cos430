###########################################################
#  joel kalala
# project 5 :  the towers of Hanoi 
###########################################################
.data
requestInput: .asciiz "Enter number of disks: "
moveDisk:     .asciiz "Move disk "
fromPeg:      .asciiz " from peg "
toPeg:        .asciiz " to peg "
newline:      .asciiz "\n"

.text
.globl main

# main procedure: program execution begins here
main:
    li $v0, 4              # System call code for print string
    la $a0, requestInput   # Load address of input request string
    syscall                # Perform system call (print string)

    li $v0, 5              # System call code for read integer
    syscall                # Perform system call (read integer)

    addi $a0, $v0, 0       # Move read integer (number of disks) into $a0
    li $a1, 65             # Load ASCII value of 'A' (start peg) into $a1
    li $a2, 66             # Load ASCII value of 'B' (end peg) into $a2
    li $a3, 67             # Load ASCII value of 'C' (auxiliary peg) into $a3

    jal hanoi              # Jump to hanoi procedure
    j Leave                # Jump to Leave procedure after hanoi is done

# hanoi procedure: solves the Towers of Hanoi puzzle recursively
hanoi:
    addi $sp, $sp, -20     # Allocate space on stack for saved registers
    sw $ra, 16($sp)        # Save return address
    sw $a0, 12($sp)        # Save number of disks
    sw $a1, 8($sp)         # Save start peg
    sw $a2, 4($sp)         # Save end peg
    sw $a3, 0($sp)         # Save auxiliary peg

    slti $t0, $a0, 1       # Check if base case reached (n < 1)
    beq $t0, $zero, Skip   # If not base case, skip restoring stack
    addi $sp, $sp, 20      # Restore stack pointer for base case
    jr $ra                 # Return to caller

Skip:
    addi $a0, $a0, -1      # Decrement number of disks

    # Swap pegs for recursive call
    add $t0, $a2, $zero   # Temporarily hold end peg
    add $a2, $a3, $zero   # Swap end peg and auxiliary peg
    add $a3, $t0, $zero   # Complete the swap
    jal hanoi             # Recursive call

    # Restore values from stack after recursive call
    lw $a3, 0($sp)
    lw $a2, 4($sp)
    lw $a1, 8($sp)
    lw $a0, 12($sp)
    lw $ra, 16($sp)
    addi $sp, $sp, 20     # Restore stack pointer

    # Save number of disks temporarily
    add $t0, $a0, $zero

    # Printing move instructions
    li $v0, 4
    la $a0, moveDisk
    syscall

    li $v0, 1
    add $a0, $t0, $zero
    syscall

    li $v0, 4
    la $a0, fromPeg
    syscall

    # Print start peg character
    li $v0, 11
    add $a0, $zero, $a1
    syscall

    li $v0, 4
    la $a0, toPeg
    syscall

    # Print destination peg character
    li $v0, 11
    add $a0, $zero, $a2
    syscall

    # Print newline character
    li $v0, 4
    la $a0, newline
    syscall

    # Continue recursive calls
    add $a0, $t0, $zero
    addi $a0, $a0, -1

    # Swap pegs for next call
    add $t0, $a1, $zero
    add $a1, $a3, $zero
    add $a3, $t0, $zero
    j hanoi

    jr $ra

# Leave procedure: Ends the program
Leave:
    li $v0, 10             # System call code for exit
    syscall                # Perform system call (exit)