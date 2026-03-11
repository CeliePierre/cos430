.data
cartItems:   .space 480              # Reserve space for cart items
buffer:      .space 20               # Buffer for input
itemPrompt:  .asciiz "Enter the name of the item: "  # Prompt for item name
pricePrompt: .asciiz "Enter the price of the item: " # Prompt for item price
newLine:     .asciiz "\n"            # New line character
nullChar:    .asciiz "\0"            # Null character for end of strings
another:     .asciiz "Is there another item to add to the cart? (yes/no): " # Prompt for adding another item
space:       .asciiz " "             # Space character
comma:       .asciiz ", "            # Comma followed by a space for formatting

.text
.globl copyString
main:
    la $s0, cartItems        # Load base address of cartItems into $s0
    li $s1, 0                # Initialize counter to 0
    li $s2, 20               # Set max count for array to 20

inLoop:  
    bge $s1, $s2, endInLoop  # If counter reaches 20, exit loop

    # Print item prompt
    li $v0, 4
    la $a0, itemPrompt
    syscall

    # Read item name
    la $a0, buffer
    li $a1, 20
    li $v0, 8
    syscall

    # Store item name

    la $t0, buffer
    la $t1, ($s0)
    addi $s0, $s0, 20
    jal copyString

    # Print price prompt
    li $v0, 4
    la $a0, pricePrompt
    syscall

    # Read item price
    li $v0, 5
    syscall
    move $s6, $v0

    # Store item price
    sw $s6, ($s0)
    addi $s0, $s0, 4

    # Increment counter
    addi $s1, $s1, 1

    # Print another item prompt
    li $v0, 4
    la $a0, another
    syscall

    # Read response
    li $v0, 5
    syscall
    beqz $v0, endInLoop  # if answer is 0, go to endInLoop
    b inLoop             # else, repeat loop

endInLoop:    
    # Further processing

    # Example: Call a function with $s1 (count) as an argument
    # Note: Implement the function 'printCart' and 'sort' as per your requirement
    # jal printCart
    # jal sort
    
    
      # Manual stack operations for saving registers
    addiu $sp, $sp, -12   # Allocate stack space for 3 registers
    sw $ra, 8($sp)        # Save return address
    sw $t0, 4($sp)        # Save $t0
    sw $t1, 0($sp)        # Save $t1

    # Initialize registers
    move $t0, $a0         # Source string address
    move $t1, $a1         # Destination string address
    li $t6, 0             # Counter for 19 characters

    la $t3, newLine
    lb $t4, 0($t3)        # Load newline character
    la $t3, nullChar
    lb $t5, 0($t3)        # Load null character
    la $t3, comma
    lb $t7, 0($t3)        # Load comma character

copyLoop:
    lb $t2, 0($t0)        # Load byte from source
    beq $t2, $t4, fill    # If newline, go to fill
    beq $t2, $t5, endFill # If null character, finish
    beq $t6, 19, endFill  # If counter reaches 19, finish
    sb $t2, 0($t1)        # Store byte in destination

    # Increment pointers and counter
    addiu $t0, $t0, 1
    addiu $t1, $t1, 1
    addiu $t6, $t6, 1
    j copyLoop

fill:
    sb $t7, 0($t1)        # Store comma in destination
    addiu $t1, $t1, 1     # Increment destination pointer
    addiu $t6, $t6, 1     # Increment counter
    j copyLoop

endFill:
    sb $t5, 0($t1)        # Store null character in destination

    # Restore registers from stack
    lw $t1, 0($sp)
    lw $t0, 4($sp)
    lw $ra, 8($sp)
    addiu $sp, $sp, 12    # Deallocate stack space
    jr $ra                # Return from function