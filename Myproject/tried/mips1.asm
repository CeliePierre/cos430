###########################################################
#  joel kalala
#  Playing with :
#  Simple input output of string and intergers
#  stopping execution
###########################################################
# Macro to halt the program
.macro halt
li $v0, 10
syscall
.end_macro

# Macro to print a string given its label
.macro printStrAtLabel(%lbl)
li $v0, 4
la $a0, %lbl
syscall
.end_macro

# Macro to print the contents of a register
.macro printInt(%reg)
move $a0, %reg
li $v0, 1
syscall
.end_macro

# Macro to print a 32-bit integer located at a memory address
.macro printIntAt(%addr)
lw $a0, %addr
li $v0, 1
syscall
.end_macro

# Macro to read an integer from the keyboard and move it into a specified register
.macro readInt(%reg)
li $v0, 5
syscall
move %reg, $v0
.end_macro

################# END OF MACROS #####################

.data
welcomeMsg: .asciiz "Greetings! I’m your robot bank teller.\n"
promptMsg:  .asciiz "How many power-of-two dollars do you want?\n"
outputMsg:  .asciiz "Here are your $"
binobucks:  .asciiz " Bin-O-Bucks:\n"
denoms:     .asciiz " dollar bill\n"

denominations: .word 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1
counts:       .space 44   # Store counts for each denomination

.text
main:
    # Print the welcome message
    li $v0, 4
    la $a0, welcomeMsg
    syscall

    # Prompt user for input
    li $v0, 4
    la $a0, promptMsg
    syscall
    
    li $v0, 5
    syscall
    
    move $t0, $v0          # Store user input in $t0

    # Print the output message
    li $v0, 4
    la $a0, outputMsg
    syscall

    move $a0, $t0
    li $v0, 1
    syscall

    li $v0, 4
    la $a0, binobucks
    syscall

    # Start converting user input to denominations
    la $t1, denominations # Load the address of the denominations array to $t1
    la $t2, counts        # Load the address of the counts array to $t2

loop:
    lw $t3, 0($t1)        # Load current denomination to $t3

    # Dividing user input by current denomination
    DIV $t0, $t3
    mflo $t4              # Quotient to $t4
    mfhi $t5              # Remainder to $t5

    sw $t4, 0($t2)        # Save the quotient in the counts array

    # Printing the quotient
    move $a0, $t4
    li $v0, 1
    syscall

    # Printing the denomination
    li $v0, 4
    la $a0, denoms
    syscall

    # SRL instruction
    srl $t6, $t3, 1       # Shift right $t3 by one bit and store in $t6

    # SLL instruction
    sll $t7, $t4, 1       # Shift left $t4 by one bit and store in $t7

    # MUL instruction
    MUL $t8, $t6, $t7     # Multiply $t6 by $t7 and store in $t8

    # SUB instruction
    SUB $t0, $t0, $t8     # Subtract $t8 from $t0 and store in $t0

    add $t1, $t1, 4       # Point to the next denomination
    add $t2, $t2, 4       # Point to the next count slot
    blt $t1, $t1, end     # If we have processed all denominations, exit the loop

    j loop

end:
    # Exit
    li $v0, 10
    syscall