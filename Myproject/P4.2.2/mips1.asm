.data
    cartItems: .space 480  # 20 CartItems * 24 bytes each
    inputNameMsg: .asciiz "Enter the name of the item --> "
    inputPriceMsg: .asciiz "\nEnter the price of the item --> "
    anotherItemMsg: .asciiz "\nIs there another item to add to the cart? 1 for yes. --> "
    unsortedMsg: .asciiz "\nUnSorted Cart:\n" # Message for Unsorted cart
    sortedMsg: .asciiz "\nSorted Cart:\n"  # Message for sorted cart
    newLine: .asciiz "\n"
    Comma: .asciiz ",             "  # Comma followed by a space for formatting

.text
.globl main
main:
    # Initial setup, $s0 stores the number of items, initially 0
    li $s0, 0

    # Main loop for input
input_loop:
    bge $s0, 20, print_unsorted_loop  # Check if we reached 20 items

    # Input for name
    li $v0, 4
    la $a0, inputNameMsg
    syscall

    # Calculate the address for storing the name
    li $t2, 24           # Size of each CartItem
    mul $a1, $s0, $t2    # Offset for current item
    la $a2, cartItems    # Base address of cartItems
    add $a2, $a2, $a1    # Address for the name of the current item

    # Read the name
    li $v0, 8
    move $a0, $a2
    li $a1, 20           # Max 20 bytes for name
    syscall

    # Input for price
    li $v0, 4
    la $a0, inputPriceMsg
    syscall

    li $v0, 5            # Read int syscall for price
    syscall

    # Store the price
    addi $a2, $a2, 20    # Move to price part of CartItem
    sw $v0, 0($a2)       # Store the price

    addi $s0, $s0, 1     # Increment item count

# Check if user wants to add another item
    li $v0, 4
    la $a0, anotherItemMsg
    syscall

    li $v0, 5
    syscall
    beqz $v0, after_input
    j input_loop

after_input:
    # Print a newline after the input loop
    li $v0, 4
    la $a0, newLine
    syscall

    # Print unsorted cart title
    li $v0, 4
    la $a0, unsortedMsg
    syscall

    # Print unsorted cart items
    j print_unsorted_loop
    
    # Print unsorted cart title
    li $v0, 4
    la $a0, unsortedMsg  # Load the address of the "Unsorted Cart" message
    syscall
    
    # Unsorted cart printing loop
    li $t1, 0  # Counter for the loop
    
# Bubble Sort Algorithm to sort cartItems by price
sort_items:
    li $t0, 0  # Outer loop index
    li $t1, 1  # Inner loop index
    li $t6, 1  # Flag to track if a swap occurred

bubble_sort_outer_loop:
    move $t0, $zero
    li $t6, 0  # Reset swap flag

    li $t1, 1  # Inner loop starts from 1
bubble_sort_inner_loop:
    blt $t1, $s0, bubble_sort_compare
    beqz $t6, print_sorted_cart  # If no swaps, then array is sorted
    j bubble_sort_outer_loop

bubble_sort_compare:
    # Calculate addresses for current and next items
    li $t2, 24  # Assuming 24 bytes per CartItem
    mul $t3, $t0, $t2
    la $t4, cartItems
    add $t4, $t4, $t3  # Address of current item

    mul $t3, $t1, $t2
    la $t5, cartItems
    add $t5, $t5, $t3  # Address of next item

    # Compare prices
    lw $t7, 20($t4)  # Load price of current item
    lw $t8, 20($t5)  # Load price of next item
    ble $t7, $t8, bubble_sort_skip_swap

    # Swap items
    # Implement swap logic here
    li $t6, 1  # Set swap flag

bubble_sort_skip_swap:
    addi $t0, $t0, 1
    addi $t1, $t1, 1
    j bubble_sort_inner_loop

# Print Sorted Cart
print_sorted_cart:
    li $v0, 4
    la $a0, sortedMsg
    syscall

    li $t1, 0  # Counter for loop
print_sorted_loop:
    bge $t1, $s0, end_program

    # Calculate address of current item
    li $t2, 24
    mul $t3, $t1, $t2
    la $t4, cartItems
    add $t4, $t4, $t3

    # Print the name
    li $v0, 4
    add $a0, $t4, 0
    syscall

    # Print comma and space
    li $v0, 4
    la $a0, Comma
    syscall

    # Print the price
    lw $a0, 20($t4)
    li $v0, 1
    syscall

    # Newline
    li $v0, 4
    la $a0, newLine
    syscall

    addi $t1, $t1, 1
    j print_sorted_loop
  


print_unsorted_loop:
    bge $t1, $s0, sort_items  # If all items have been printed, proceed to sorting

    # Calculate the address of the current item
    li $t2, 24                # Size of each CartItem
    mul $t3, $t1, $t2         # Calculate offset for current item
    la $t4, cartItems         # Load base address of cartItems
    add $t3, $t4, $t3         # Address of the current item

    # Print the name
    li $v0, 4                 # syscall to print string
    add $a0, $t3, 0           # Address of the name part of the current item
    syscall

    # Print comma and spaces
    li $v0, 4
   la $a0, Comma
    syscall

    # Print the price
    lw $a0, 20($t3)           # Load the price (assuming price is stored 20 bytes from the start of CartItem)
    li $v0, 1                 # syscall to print int
    syscall

    # Print a newline
    li $v0, 4
    la $a0, newLine
    syscall

    addi $t1, $t1, 1          # Increment the loop counter
    j print_unsorted_loop     # Jump back to the start of the loop for the next item






    # Calculate the address of the current item
    li $t2, 24  # Size of each CartItem
    mul $t3, $t1, $t2  # Calculate offset
    la $t4, cartItems  # Base address of cartItems
    add $t4, $t4, $t3  # Address of the current item

    # Print the name
    li $v0, 4  # syscall to print string
    add $a0, $t4, 0  # Address of the name part
    syscall

    # Print comma and spaces as a separator
    li $v0, 4
   la $a0, Comma

    syscall

    # Print the price
    lw $a0, 20($t4)  # Load the price
    li $v0, 1  # syscall to print int
    syscall

    # Print a newline
    li $v0, 4
    la $a0, newLine
    syscall

    addi $t1, $t1, 1  # Increment counter
    j print_sorted_loop
    
end_program:
    # End of program
    li $v0, 10
    syscall