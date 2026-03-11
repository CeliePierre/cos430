###########################################################
#  joel kalala
#   Playing with :
#     Simple input output of string and intergers
#     stopping execution
###########################################################
.data
welcomeMsg:    .asciiz "Greetings! I’m your robot bank teller.\n"
promptMsg:     .asciiz "How many power-of-two dollars do you want?\n"
outputMsg:     .asciiz "Here are your $"
binobucks:     .asciiz " Bin-O-Bucks:\n"
denoms:        .asciiz " dollar bill\n"
space:         .asciiz " "

denominations: .word 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1
counts:        .space 44   # Store counts for each denomination

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
    la $t1, denominations # Address of the denominations array
    li $t9, 11            # Number of denominations

loop:
    lw $t3, 0($t1)        # Load current denomination

    # Dividing user input by current denomination
    DIV $t0, $t3
    mflo $t4              # Quotient
    mfhi $t0              # Remainder to $t0 for next loop

    # Printing the quotient
    move $a0, $t4
    li $v0, 1
    syscall

    # Printing a space for better formatting
    li $v0, 4
    la $a0, space
    syscall

    # Printing the denomination value
    move $a0, $t3
    li $v0, 1
    syscall

    # Printing the "dollar bill" string
    li $v0, 4
    la $a0, denoms
    syscall

    add $t1, $t1, 4       # Point to the next denomination
    sub $t9, $t9, 1       # Decrement denomination counter
    bgtz $t9, loop        # If more denominations, loop

end:
    # Exit
    li $v0, 10
    syscall