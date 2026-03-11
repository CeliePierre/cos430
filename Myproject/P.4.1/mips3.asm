.data
cartItems:   .space 480              # Reserve space for cart items
buffer:      .space 20               # Buffer for input
itemPrompt:  .asciiz "Enter the name of the item: "  # Prompt for item name
pricePrompt: .asciiz "Enter the price of the item: " # Prompt for item price
newLine:     .asciiz "\n"            # New line character
nullChar:    .asciiz "\0"            # Null character for end of strings
another:     .asciiz "Is there another item to add to the cart? (yes/no): " # Prompt for adding another item
space:       .asciiz " "             # Space character
unsortedTitle: .asciiz "Unsorted Cart Items:\n"  # Title for unsorted items

    
.text
main:
    la $t0, cartItems        # Load base address of cartItems into $t0
    li $t1, 0                # Initialize counter ($t1) to 0
    li $t2, 20               # Set max count for array to 20

    # Other initializations
    lb $s4, newLine
    lb $s5, nullChar
    j input_loop            # Jump to input loop
    
    
    input_loop:
    bge $t1, $t2, after_input  # Check if max items reached

    # Item name input
    la $a0, itemPrompt      # Load address of itemPrompt
    li $v0, 4               # System call code for print string
    syscall                 # Print prompt for item name

    la $a0, buffer          # Load address of buffer for input
    li $a1, 20              # Set maximum input length
    li $v0, 8               # System call code for read string
    syscall                 # Read input into buffer

    # Assuming a function to copy string from buffer to cartItems
    # Add the logic to copy the name from buffer to the appropriate location in cartItems
    # You need to calculate the offset based on $t1 (the current item index)

    # Item price input
    la $a0, pricePrompt     # Load address of pricePrompt
    li $v0, 4               # System call code for print string
    syscall                 # Print prompt for item price

    li $v0, 5               # System call code for read integer
    syscall                 # Read integer (price)
    # Store the price in cartItems after the name

    # Ask if the user wants to add another item
    la $a0, another         # Load address of another prompt
    li $v0, 4               # System call code for print string
    syscall                 # Print prompt

    li $v0, 5               # System call code for read integer (assuming yes=1/no=0)
    syscall                 # Read response

    beqz $v0, after_input   # If response is 0 (no), exit loop

    # Increment counter and prepare for next iteration
    addi $t1, $t1, 1        # Increment counter
    j input_loop            # Jump back to the start of the loop
    
    
    
 after_input:
    # Print a title or header for the unsorted items, if desired
    # For example:
    li $v0, 4                   # System call code for print string
    la $a0, unsortedTitle       # Assuming unsortedTitle is a string like ".asciiz 'Unsorted Items:\n'"
    syscall                     # Print the title

    # Loop to print each item in the unsorted cart
    li $t1, 0                   # Initialize counter for loop
    la $t4, cartItems           # Load base address of cartItems

print_unsorted_loop:
    bge $t1, $t2, sort_items    # Check if all items have been printed, then jump to sorting

    # Calculate address for the current item
    li $t3, 24                  # Assuming each cart item takes 24 bytes
    mul $t3, $t1, $t3           # Calculate offset
    add $t3, $t4, $t3           # Get address of current item

    # Print the name of the current item
    li $v0, 4                   # System call code for print string
    add $a0, $t3, 0             # Set $a0 to the address of the item's name
    syscall                     # Print item name

    # Print the price of the current item
    li $v0, 1                   # System call code for print integer
    lw $a0, 20($t3)             # Assuming price is stored 20 bytes from start of item
    syscall                     # Print item price

    # Print a new line
    li $v0, 4                   # System call code for print string
    la $a0, newLine             # Load address of new line character
    syscall                     # Print new line

    addi $t1, $t1, 1            # Increment loop counter
    j print_unsorted_loop       # Jump back to the start of the loop for next item
  
    sort_items:
    li $t0, 0                  # Outer loop counter, i
    li $t1, 1                  # Inner loop counter, j
    li $t3, 1                  # Flag to track swapping

bubble_sort_outer_loop:
    move $t0, $zero            # Reset outer loop counter
    li $t3, 0                  # Reset swap flag

    # Inner loop starts from 1 because bubble sort compares i and i+1
    li $t1, 1                  # Reset inner loop counter
bubble_sort_inner_loop:
    blt $t1, $t2, bubble_sort_compare  # Continue if j < number of items - 1

    # If no swaps in this pass, array is sorted
    beqz $t3, print_sorted_cart
    j bubble_sort_outer_loop

bubble_sort_compare:
    # Calculate addresses of current (i) and next (i+1) items
    li $t4, 24                 # Size of each item, adjust as needed
    mul $t5, $t0, $t4
    la $t6, cartItems
    add $t6, $t6, $t5          # Address of current item

    mul $t5, $t1, $t4
    la $t7, cartItems
    add $t7, $t7, $t5          # Address of next item

    # Compare prices of current and next item
    lw $t8, 20($t6)            # Load price of current item
    lw $t9, 20($t7)            # Load price of next item
    ble $t8, $t9, bubble_sort_next_iteration

    # Swap items if current item's price is greater than next
   swap_items:
    # Example: Swapping two integers
    lw $t0, 0($a0)   # Load first integer
    lw $t1, 0($a1)   # Load second integer

    sw $t0, 0($a1)   # Store first integer in second location
    sw $t1, 0($a0)   # Store second integer in first location

    jr $ra           # Return from subroutine
bubble_sort_next_iteration:
    addi $t0, $t0, 1           # Increment i
    addi $t1, $t1, 1           # Increment j
    j bubble_sort_inner_loop
    
    
    #print_unsorted_loop:
    #li $t1, 0                  # Initialize loop counter (for item index)
    #li $t3, 24                 # Assume each item takes 24 bytes in cartItems
    
    
    # After reading the name into buffer
# Calculate the address for storing the name
li $t3, 24                  # Size of each CartItem
mul $t4, $t1, $t3           # Offset for current item
la $t5, cartItems
add $a2, $t5, $t4           # Address for the name of the current item
# Now copy the name from buffer to $a2

# After reading the price into $v0
addi $a2, $a2, 20           # Move to price part of CartItem
sw $v0, 0($a2)              # Store the price
 
    

print_each_item:
    bge $t1, $t2, print_done   # If all items printed, exit loop

    # Calculate address of current item
    mul $t4, $t1, $t3          # Offset for current item
    la $t5, cartItems          # Base address of cartItems
    add $t5, $t5, $t4          # Address of current item

    # Print the name of the item
    li $v0, 4                  # syscall code for print string
    add $a0, $t5, 0            # Address of the name part of the current item
    syscall

    # Print a space or separator
    li $v0, 4                  # syscall code for print string
    la $a0, space              # Address of space character
    syscall

    # Print the price of the item
    li $v0, 1                  # syscall code for print integer
    lw $a0, 20($t5)            # Assuming price is stored 20 bytes from start of item
    syscall

    # Print a new line
    li $v0, 4                  # syscall code for print string
    la $a0, newLine            # Address of new line character
    syscall

    # Increment counter and loop back
    addi $t1, $t1, 1
    j print_each_item

print_done:
    # Decide next step: sorting or ending program
    j sort_items   # Uncomment to proceed to sorting
    # j end_program # Uncomment to end the program
    
    print_sorted_cart:
    li $t1, 0                  # Initialize loop counter (for item index)
    li $t3, 24                 # Assume each item takes 24 bytes in cartItems

print_each_sorted_item:
    bge $t1, $t2, end_program  # If all items printed, exit loop and end program

    # Calculate address of current item
    mul $t4, $t1, $t3          # Offset for current item
    la $t5, cartItems          # Base address of cartItems
    add $t5, $t5, $t4          # Address of current item

    # Print the name of the item
    li $v0, 4                  # syscall code for print string
    add $a0, $t5, 0            # Address of the name part of the current item
    syscall

    # Print a space or separator
    li $v0, 4                  # syscall code for print string
    la $a0, space              # Address of space character
    syscall

    # Print the price of the item
    li $v0, 1                  # syscall code for print integer
    lw $a0, 20($t5)            # Assuming price is stored 20 bytes from start of item
    syscall

    # Print a new line
    li $v0, 4                  # syscall code for print string
    la $a0, newLine            # Address of new line character
    syscall

    # Increment counter and loop back
    addi $t1, $t1, 1
    j print_each_sorted_item
    
    
    cleanString:
    # Save registers
    addi $sp, $sp, -8     # Allocate space on stack for 2 registers
    sw $t0, 4($sp)        # Save $t0
    sw $t1, 0($sp)        # Save $t1

    # Load arguments from stack
    lw $t0, 12($sp)       # Load source address
    lw $t1, 8($sp)        # Load destination address

    li $t6, 0             # Initialize counter
    lb $t2, ($t0)         # Load first byte from source

copyLoop:
    beq $t2, $zero, endcopy # If null char, go to endcopy
    beq $t2, $s4, endcopy   # If newline, go to endcopy
    sb $t2, 0($t1)          # Store byte in destination

    # Increment addresses and counter
    addi $t0, $t0, 1
    addi $t1, $t1, 1
    addi $t6, $t6, 1
    blt $t6, 19, copyLoop   # If counter less than 19, repeat loop

endcopy:
    # Fill the rest with spaces
    la $t4, space           # Address of space character
    lb $t4, 0($t4)          # Load space character

fill:
    blt $t6, 19, fill_loop  # Fill until we reach 19 characters
    j endFill

fill_loop:
    sb $t4, 0($t1)          # Store space in destination
    addi $t1, $t1, 1        # Move to next byte
    addi $t6, $t6, 1        # Increment counter
    j fill

endFill:
    # Finish string with a null char
    la $t5, nullChar
    lb $t5, 0($t5)
    sb $t5, 0($t1)

    # Restore registers
    lw $t1, 0($sp)
    lw $t0, 4($sp)
    addi $sp, $sp, 8       # Deallocate space from stack
    jr $ra                 # Return from function

    
    
    end_program:
    li $v0, 10
    syscall
    
