.data
    # Constants and strings declarations
    message: .asciiz "Move disk from peg A to peg C\n"

.text
.globl main
main:
    # Initial setup
    li $a0, 3        # Number of disks
    li $a1, 1        # Source peg (A)
    li $a2, 2        # Auxiliary peg (B)
    li $a3, 3        # Destination peg (C)
    # Push parameters for the initial call to Hanoi
    addi $sp, $sp, -16   # Allocate space on stack for 4 arguments
    sw $a0, 12($sp)
    sw $a1, 8($sp)
    sw $a2, 4($sp)
    sw $a3, 0($sp)
    # Call the Hanoi function
    jal Hanoi
    # Finish and exit program
    li $v0, 10       # Exit syscall
    syscall

Hanoi:
    # Preserve $ra and other registers
    addi $sp, $sp, -4
    sw $ra, 0($sp)
    # Allocate stack space
    # Load arguments from stack
    lw $a0, 16($sp)  # Number of disks
    lw $a1, 12($sp)  # Source peg
    lw $a2, 8($sp)   # Auxiliary peg
    lw $a3, 4($sp)   # Destination peg
    # Base case check
    beqz $a0, endHanoi
    # Recursive calls with parameters pushed onto the stack
    # Move n-1 disks from source to auxiliary
    addi $a0, $a0, -1
    sw $a0, 16($sp)
    sw $a3, 12($sp)
    sw $a2, 8($sp)
    sw $a1, 4($sp)
    jal Hanoi
    # Move disk from source to destination (display message)
    li $v0, 4
    la $a0, message
    syscall
    # Move n-1 disks from auxiliary to destination
    lw $a0, 16($sp)
    addi $a0, $a0, -1
    sw $a0, 16($sp)
    sw $a1, 12($sp)
    sw $a3, 8($sp)
    sw $a2, 4($sp)
    jal Hanoi
    # Restore registers
endHanoi:
    lw $ra, 0($sp)
    addi $sp, $sp, 4
    # Release stack space
    addi $sp, $sp, 16
    # Return from the function
    jr $ra
